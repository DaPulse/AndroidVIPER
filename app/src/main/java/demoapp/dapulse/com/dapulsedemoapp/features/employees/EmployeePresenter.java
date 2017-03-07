package demoapp.dapulse.com.dapulsedemoapp.features.employees;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import javax.inject.Inject;

import demoapp.dapulse.com.dapulsedemoapp.NavigationHandler;
import demoapp.dapulse.com.dapulsedemoapp.base.BasePresenter;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.Employee;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.EmployeeResponse;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ofertour on 06/02/2017.
 */

public class EmployeePresenter extends BasePresenter implements EmployeesVIP.Presenter {

    private static final String TAG = "EmployeePresenter";
    private final AppCompatActivity activity;
    private final EmployeesVIP.Interactor interactor;
    private EmployeesVIP.View view;
    private final NavigationHandler navigationHandler;

    @Inject
    public EmployeePresenter(AppCompatActivity activity, EmployeesVIP.Interactor interactor, EmployeesVIP.View view, NavigationHandler navigationHandler) {
        this.activity = activity;
        this.interactor = interactor;
        this.view = view;
        this.navigationHandler = navigationHandler;
    }

    @Override
    public void onResume() {
        view.registerEmployeeListClicks(employee -> {
            if (employee.isManager) {
                navigationHandler.navigateToManagerView(activity, employee);
            } else {
                navigationHandler.navigateToProfilePage(activity, employee);
            }
            Log.d(TAG, "employee clicked");
        });
    }

    @Override
    @Nullable
    public RestoreView getRestoreView() {
        return view;
    }

    @Override
    public Observable<EmployeeResponse> loadCompany() {
        return interactor.loadEmployees().observeOn(AndroidSchedulers.mainThread()).doOnNext(employeeResponse -> view.setPageTitle(employeeResponse.companyName));
    }

    @Override
    public Observable<String> getCompanyName() {
        return interactor.getCompanyName().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void showTopLevelManagement() {
        Subscription subscribe = interactor.getTopLevelManagement()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(employees -> view.onUsersLoaded(employees),
                        throwable -> Log.e(getClass().getName(), "error loading top management", throwable));
        addSubscription(subscribe);
    }

    @Override
    public void showManagerView(Employee manager) {
        view.showManagerLayout(manager, employee -> navigationHandler.navigateToProfilePage(activity, employee));
    }

    @Override
    public void showManagerEmployees(Employee manager) {
        Subscription subscribe = interactor.getManagerEmployees(manager.id)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(employees -> view.onUsersLoaded(employees));
        addSubscription(subscribe);
    }

    @Override
    public Observable<ArrayList<Employee>> getDepartmentEmployees(String department) {
        return interactor.getDepartmentEmployees(department).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Employee> getEmployeeById(int id) {
        return interactor.getEmployee(id).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }




}
