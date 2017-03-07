package demoapp.dapulse.com.dapulsedemoapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;

import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.Employee;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.ui.ActivityEmployeeProfile;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.ui.ActivityManagerView;

/**
 * Created by danfa on 25/02/2017.
 */

public class NavigationHandler {
    public static final String EXTRA_MANAGER = "extra_manager";

    public void navigateToManagerView(Context from, Employee manager) {
        Intent intent = new Intent(from, ActivityManagerView.class);
        intent.putExtra(EXTRA_MANAGER, manager);
        ActivityCompat.startActivity(from, intent, null);
    }

    public void navigateToProfilePage(Context from, Employee employee) {
        Intent intent = new Intent(from, ActivityEmployeeProfile.class);
        intent.putExtra(EXTRA_MANAGER, employee);
        ActivityCompat.startActivity(from, intent, null);
    }
}
