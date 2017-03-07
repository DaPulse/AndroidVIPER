package demoapp.dapulse.com.dapulsedemoapp.features.employees.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import demoapp.dapulse.com.dapulsedemoapp.DemoApp;
import demoapp.dapulse.com.dapulsedemoapp.base.BasePresenter;
import demoapp.dapulse.com.dapulsedemoapp.dagger.DaggerEmployeesComponent;
import demoapp.dapulse.com.dapulsedemoapp.dagger.EmployeesComponent;
import demoapp.dapulse.com.dapulsedemoapp.dagger.EmployeesModule;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.EmployeePresenter;

/**
 * Created by danfa on 25/02/2017.
 */

public abstract class ActivityBaseEmployee extends AppCompatActivity {


    @Inject
    protected EmployeePresenter presenter;

    protected void bindEmployeePresenter() {
        //setup Dagger to resolve all Dependencies
        EmployeesComponent employeeComponent = DaggerEmployeesComponent.builder()
                .applicationComponent(((DemoApp) getApplication()).getAppComponent())
                .employeesModule(new EmployeesModule(this))
                .build();
        employeeComponent.inject(this);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        BasePresenter.RestoreView restoreView = presenter.getRestoreView();
        if (savedInstanceState == null || restoreView == null || !restoreView.canRestore(savedInstanceState)) {
            loadData();
        } else {
            restoreView.restoreData(savedInstanceState);
        }
    }

    protected abstract void loadData();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BasePresenter.RestoreView restoreView = presenter.getRestoreView();
        if (restoreView != null) {
            restoreView.saveToBundle(outState);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }
}
