package demoapp.dapulse.com.dapulsedemoapp.features.employees;

import java.util.List;

import javax.inject.Inject;

import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.Employee;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.EmployeeResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ofertour on 06/02/2017.
 */

public class EmployeePresenter implements EmployeesVIP.Presenter {

    private final EmployeesVIP.Interactor interactor;

    @Inject
    public EmployeePresenter(EmployeesVIP.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public Observable<EmployeeResponse> loadCompany() {
        return interactor.loadEmployees().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getCompanyName() {
        return interactor.getCompanyName().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Employee>> getTopLevelManagement() {
        return interactor.getTopLevelManagement().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Employee>> getDepartmentEmployees(String department) {
        return interactor.getDepartmentEmployees(department).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Employee> getEmployeeById(int id) {
        return interactor.getEmployee(id).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Employee>> getManagerEmployeesByManagerId(int managerId) {
        return interactor.getManagerEmployees(managerId).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }


}
