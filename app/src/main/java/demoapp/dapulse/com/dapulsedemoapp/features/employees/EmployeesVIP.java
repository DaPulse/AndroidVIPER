package demoapp.dapulse.com.dapulsedemoapp.features.employees;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import demoapp.dapulse.com.dapulsedemoapp.base.BasePresenter;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.Employee;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.EmployeeResponse;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

/**
 * Created by ofertour on 06/02/2017.
 */

public interface EmployeesVIP {


    interface View extends BasePresenter.RestoreView {
        void onUsersLoaded(ArrayList<Employee> employees);

        void showManagerLayout(Employee manager, Action1<Employee> clickAction);

        void setPageTitle(String companyName);

        void registerEmployeeListClicks(Action1<Employee> employeeSubscriber);

    }

    interface Interactor {
        Observable<EmployeeResponse> loadEmployees();

        Observable<ArrayList<Employee>> getTopLevelManagement();

        Observable<ArrayList<Employee>> getDepartmentEmployees(String department);

        Observable<ArrayList<Employee>> getManagerEmployees(int managerId);

        Observable<Employee> getEmployee(int id);

        Observable<String> getCompanyName();
    }

    interface Presenter {
        Observable<EmployeeResponse> loadCompany();

        Observable<String> getCompanyName();

        void showTopLevelManagement();

        Observable<ArrayList<Employee>> getDepartmentEmployees(String department);

        Observable<Employee> getEmployeeById(int id);

        void showManagerView(Employee managerId);
        void showManagerEmployees(Employee manager);
    }

    interface Repository {

        Observable<EmployeeResponse> getResponse();
        String getCompanyName();

        boolean saveData(EmployeeResponse response);

        List<Employee> getManagerEmployees(int managerId);

        List<Employee> getAll();

        List<Employee> getTopLevelManagement();

        List<Employee> getDepartmentEmployees(String department);

        @Nullable
        Employee getEmployee(int id);
    }

}
