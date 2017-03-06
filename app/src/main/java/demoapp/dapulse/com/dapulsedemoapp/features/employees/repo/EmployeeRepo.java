package demoapp.dapulse.com.dapulsedemoapp.features.employees.repo;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import demoapp.dapulse.com.dapulsedemoapp.features.employees.EmployeesVIP;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.Employee;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.EmployeeResponse;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by ofertour on 06/02/2017.
 */

public class EmployeeRepo implements EmployeesVIP.Repository {

    private static final String KEY_COMPANY_NAME = "company_name";
    private final SharedPreferences prefs;
    private final RealmEmployeeConverter converter;


    public EmployeeRepo(SharedPreferences prefs, RealmEmployeeConverter converter) {
        this.prefs = prefs;
        this.converter = converter;
    }

    @Override
    public String getCompanyName() {
        return prefs.getString(KEY_COMPANY_NAME, "");
    }

    @Override
    public boolean saveData(EmployeeResponse response) {
        prefs.edit().putString(KEY_COMPANY_NAME, response.companyName).apply();
        Realm realm = Realm.getDefaultInstance();
        RealmList<RealmEmployee> list = new RealmList<>();
        for (Employee employee : response.employees) {
            list.add(converter.convertToRealm(employee));
        }

        try {
            realm.beginTransaction();
            realm.where(RealmEmployee.class).findAll().deleteAllFromRealm();

            realm.copyToRealmOrUpdate(list);
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            Log.e("employee repo", "error saving employees to db", e);
            realm.cancelTransaction();
            return false;
        } finally {
            realm.close();
        }
    }

    @Override
    public List<Employee> getManagerEmployees(int managerId) {
        Realm realm = Realm.getDefaultInstance();
        try {
            RealmResults<RealmEmployee> all = realm.where(RealmEmployee.class).equalTo("managerId", managerId).findAll();
            ArrayList<Employee> list = new ArrayList<>(all.size());
            for (RealmEmployee realmEmployee : all) {
                list.add(converter.convertFromRealm(realmEmployee));
            }
            return list;
        } finally {
            realm.close();
        }
    }

    @Override
    public List<Employee> getAll() {
        Realm realm = Realm.getDefaultInstance();
        try {
            RealmResults<RealmEmployee> all = realm.where(RealmEmployee.class).findAll();
            ArrayList<Employee> list = new ArrayList<>(all.size());
            for (RealmEmployee realmEmployee : all) {
                list.add(converter.convertFromRealm(realmEmployee));
            }
            return list;
        } finally {
            realm.close();
        }
    }

    @Override
    public List<Employee> getTopLevelManagement() {
        Realm realm = Realm.getDefaultInstance();
        try {
            RealmResults<RealmEmployee> all = realm.where(RealmEmployee.class).isNull("managerId").findAll();
            ArrayList<Employee> list = new ArrayList<>(all.size());
            for (RealmEmployee realmEmployee : all) {
                list.add(converter.convertFromRealm(realmEmployee));
            }
            return list;
        } finally {
            realm.close();
        }
    }

    @Override
    public List<Employee> getDepartmentEmployees(String department) {
        Realm realm = Realm.getDefaultInstance();
        try {
            RealmResults<RealmEmployee> all = realm.where(RealmEmployee.class).equalTo("department", department).findAll();
            ArrayList<Employee> list = new ArrayList<>(all.size());
            for (RealmEmployee realmEmployee : all) {
                list.add(converter.convertFromRealm(realmEmployee));
            }
            return list;
        } finally {
            realm.close();
        }
    }

    @Override
    @Nullable
    public Employee getEmployee(int id) {
        Realm realm = Realm.getDefaultInstance();
        try {
            RealmResults<RealmEmployee> all = realm.where(RealmEmployee.class).equalTo("id", id).findAll();
            if (!all.isEmpty()) {
                return converter.convertFromRealm(all.first());
            } else {
                return null;
            }
        } finally {
            realm.close();
        }
    }
}
