package demoapp.dapulse.com.dapulsedemoapp.features.employees.repo;

import javax.inject.Inject;

import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.Employee;

/**
 * Created by ofertour on 06/02/2017.
 */

public class RealmEmployeeConverter {

    @Inject
    public RealmEmployeeConverter() {
    }

    RealmEmployee convertToRealm(Employee employee) {
        RealmEmployee realmEmployee = new RealmEmployee();
        realmEmployee.id = employee.id;
        realmEmployee.department = employee.department;
        realmEmployee.email = employee.email;
        realmEmployee.isManager = employee.isManager;
        realmEmployee.name = employee.name;
        realmEmployee.managerId = employee.managerId;
        realmEmployee.phone = employee.phone;
        realmEmployee.profilePic = employee.profilePic;
        realmEmployee.title = employee.title;
        return realmEmployee;
    }


    Employee convertFromRealm(RealmEmployee realmEmployee) {
        Employee employee = new Employee();
        employee.id = realmEmployee.id;
        employee.department = realmEmployee.department;
        employee.email = realmEmployee.email;
        employee.isManager = realmEmployee.isManager;
        employee.name = realmEmployee.name;
        employee.managerId = realmEmployee.managerId;
        employee.phone = realmEmployee.phone;
        employee.profilePic = realmEmployee.profilePic;
        employee.title = realmEmployee.title;
        return employee;
    }
}
