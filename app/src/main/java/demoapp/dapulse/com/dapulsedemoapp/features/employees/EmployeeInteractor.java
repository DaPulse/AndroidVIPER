package demoapp.dapulse.com.dapulsedemoapp.features.employees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.inject.Inject;
import demoapp.dapulse.com.dapulsedemoapp.dagger.ServerApi;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.Employee;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.EmployeeResponse;
import rx.Observable;

/**
 * Created by ofertour on 06/02/2017.
 */

public class EmployeeInteractor implements EmployeesVIP.Interactor {

    private final ServerApi serverApi;
    private final EmployeesVIP.Repository repository;


    private Comparator<Employee> employeeComparator = (e1, e2) -> {
        if ((e1.isManager && e2.isManager) || (!e1.isManager && !e2.isManager)) return e1.name.compareTo(e2.name);
        else if (e1.isManager) return -1;
        else return 1;
    };

    @Inject
    public EmployeeInteractor(ServerApi serverApi, EmployeesVIP.Repository repository) {
        this.serverApi = serverApi;
        this.repository = repository;
    }

    @Override
    public Observable<EmployeeResponse> loadEmployees() {
        //try to get from db first, if it's empty go to server and save data to db
        Observable<EmployeeResponse> server = serverApi.getEmployees().doOnNext(repository::saveData);
        return repository.getResponse()
                .switchIfEmpty(server);
    }

    @Override
    public Observable<ArrayList<Employee>> getTopLevelManagement() {
        return Observable.create(subscriber -> {
            ArrayList<Employee> topLevelManagement = new ArrayList<>(repository.getTopLevelManagement());
            Collections.sort(topLevelManagement,employeeComparator );
            subscriber.onNext(topLevelManagement);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<ArrayList<Employee>> getDepartmentEmployees(String department) {
        return Observable.create(subscriber -> {
            ArrayList<Employee> departmentEmployees = new ArrayList<>(repository.getDepartmentEmployees(department));
            Collections.sort(departmentEmployees,employeeComparator );
            subscriber.onNext(departmentEmployees);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<ArrayList<Employee>> getManagerEmployees(int managerId) {
        return Observable.create(subscriber -> {
            ArrayList<Employee> managerEmployees = new ArrayList<>(repository.getManagerEmployees(managerId));
            Collections.sort(managerEmployees,employeeComparator );
            subscriber.onNext(managerEmployees);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<Employee> getEmployee(int id) {
        return Observable.create(subscriber -> {
            Employee employee = repository.getEmployee(id);
            subscriber.onNext(employee);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<String> getCompanyName() {
        return Observable.create(subscriber -> {
            subscriber.onNext(repository.getCompanyName());
            subscriber.onCompleted();
        });
    }
}
