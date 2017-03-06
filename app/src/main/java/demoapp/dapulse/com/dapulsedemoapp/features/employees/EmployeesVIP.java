package demoapp.dapulse.com.dapulsedemoapp.features.employees;

import android.support.annotation.Nullable;

import java.util.List;

import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.Employee;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.EmployeeResponse;
import rx.Observable;

/**
 * Created by ofertour on 06/02/2017.
 */

public interface EmployeesVIP {

    interface Interactor {
        Observable<EmployeeResponse> loadEmployees();

        Observable<List<Employee>> getTopLevelManagement();

        Observable<List<Employee>> getDepartmentEmployees(String department);

        Observable<List<Employee>> getManagerEmployees(int managerId);

        Observable<Employee> getEmployee(int id);

        Observable<String> getCompanyName();
    }

    interface Presenter {
        Observable<EmployeeResponse> loadCompany();

        Observable<String> getCompanyName();

        Observable<List<Employee>> getTopLevelManagement();

        Observable<List<Employee>> getDepartmentEmployees(String department);

        Observable<Employee> getEmployeeById(int id);

        Observable<List<Employee>> getManagerEmployeesByManagerId(int managerId);
    }


    interface Repository {
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
