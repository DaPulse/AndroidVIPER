package demoapp.dapulse.com.dapulsedemoapp.features.employees.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import demoapp.dapulse.com.dapulsedemoapp.DemoApp;
import demoapp.dapulse.com.dapulsedemoapp.dagger.DaggerEmployeesComponent;
import demoapp.dapulse.com.dapulsedemoapp.dagger.EmployeesComponent;
import demoapp.dapulse.com.dapulsedemoapp.dagger.EmployeesModule;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.EmployeePresenter;

/**
 * Created by ofertour on 26/02/2017.
 */

public class BaseEmployeeActivity extends AppCompatActivity {

    @Inject
    protected EmployeePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //setup Dagger to resolve all Dependencies - Ignore this part
        EmployeesComponent employeeComponent = DaggerEmployeesComponent.builder()
                .applicationComponent(((DemoApp) getApplication()).getAppComponent())
                .employeesModule(new EmployeesModule(this))
                .build();
        employeeComponent.inject(this);
    }


    protected EmployeePresenter getPresenter() {
        return presenter;
    }
}
