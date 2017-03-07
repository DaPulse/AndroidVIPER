package demoapp.dapulse.com.dapulsedemoapp.features.employees.repo;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import demoapp.dapulse.com.dapulsedemoapp.DemoApp;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.EmployeesVIP;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.Employee;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.EmployeeResponse;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by ofertour on 06/02/2017.
 */

public class EmployeeRepo implements EmployeesVIP.Repository {

    private static final String KEY_COMPANY_NAME = "company_name";
    private final SharedPreferences prefs;


    public EmployeeRepo(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    @Override
    public Observable<EmployeeResponse> getResponse() {
        return Observable.create(subscriber -> {
            String companyName = getCompanyName();
            List<Employee> all = getAll();

            if (!TextUtils.isEmpty(companyName) && !all.isEmpty()) {
                Log.d(DemoApp.TAG, "returning cached data");
                subscriber.onNext(new EmployeeResponse(companyName, all));
            }
            subscriber.onCompleted();
        });
    }

    @Override
    public String getCompanyName() {
        return prefs.getString(KEY_COMPANY_NAME, "");
    }

    @Override
    public boolean saveData(EmployeeResponse response) {
        Log.d(DemoApp.TAG, "saving new data from server");
        prefs.edit().putString(KEY_COMPANY_NAME, response.companyName).apply();
        Realm realm = Realm.getDefaultInstance();


        try {
            realm.beginTransaction();
            realm.where(Employee.class).findAll().deleteAllFromRealm();

            realm.copyToRealmOrUpdate(response.employees);
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
            return realm.copyFromRealm(realm.where(Employee.class).equalTo("managerId", managerId).findAll());
        } finally {
            realm.close();
        }
    }

    @Override
    public List<Employee> getAll() {
        Realm realm = Realm.getDefaultInstance();
        try {
            return realm.copyFromRealm(realm.where(Employee.class).findAll());
        } finally {
            realm.close();
        }
    }

    @Override
    public List<Employee> getTopLevelManagement() {
        Realm realm = Realm.getDefaultInstance();
        try {
            return realm.copyFromRealm(realm.where(Employee.class).isNull("managerId").findAll());
        } finally {
            realm.close();
        }
    }

    @Override
    public List<Employee> getDepartmentEmployees(String department) {
        Realm realm = Realm.getDefaultInstance();
        try {
            return realm.copyFromRealm(realm.where(Employee.class).equalTo("department", department).findAll());
        } finally {
            realm.close();
        }
    }

    @Override
    @Nullable
    public Employee getEmployee(int id) {
        Realm realm = Realm.getDefaultInstance();
        try {
            return realm.copyFromRealm(realm.where(Employee.class).equalTo("id", id).findFirst());
        } finally {
            realm.close();
        }
    }
}
