package demoapp.dapulse.com.dapulsedemoapp.features.employees.ui;

import android.os.Bundle;

import butterknife.ButterKnife;
import demoapp.dapulse.com.dapulsedemoapp.NavigationHandler;
import demoapp.dapulse.com.dapulsedemoapp.R;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.Employee;

public class ActivityManagerView extends ActivityBaseEmployee {


    private Employee manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        ButterKnife.bind(this);
        bindEmployeePresenter();

        manager = getIntent().getParcelableExtra(NavigationHandler.EXTRA_MANAGER);
        presenter.showManagerView(manager);
    }

    @Override
    protected void loadData() {
        presenter.showManagerEmployees(manager);
    }
}
