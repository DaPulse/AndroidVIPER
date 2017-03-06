package demoapp.dapulse.com.dapulsedemoapp.features.employees;

import java.util.List;

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

    @Inject
    public EmployeeInteractor(ServerApi serverApi, EmployeesVIP.Repository repository) {
        this.serverApi = serverApi;
        this.repository = repository;
    }

    @Override
    public Observable<EmployeeResponse> loadEmployees() {
        return serverApi.getEmployees().map(response -> {
            repository.saveData(response);
            return response;
        });
    }

    @Override
    public Observable<List<Employee>> getTopLevelManagement() {
        return Observable.create(subscriber -> {
            List<Employee> topLevelManagement = repository.getTopLevelManagement();
            subscriber.onNext(topLevelManagement);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<List<Employee>> getDepartmentEmployees(String department) {
        return Observable.create(subscriber -> {
            List<Employee> departmentEmployees = repository.getDepartmentEmployees(department);
            subscriber.onNext(departmentEmployees);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<List<Employee>> getManagerEmployees(int managerId) {
        return Observable.create(subscriber -> {
            List<Employee> managerEmployees = repository.getManagerEmployees(managerId);
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
