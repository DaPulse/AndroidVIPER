package demoapp.dapulse.com.dapulsedemoapp.features.employees.ui;

import android.os.Bundle;

import butterknife.ButterKnife;
import demoapp.dapulse.com.dapulsedemoapp.R;
import demoapp.dapulse.com.dapulsedemoapp.base.BasePresenter;
import rx.Subscription;

public class ActivityTopLevelManagement extends ActivityBaseEmployee {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_managers);
        ButterKnife.bind(this);
        bindEmployeePresenter();
    }

    @Override
    protected void loadData() {
        Subscription subscribe = presenter.loadCompany()
                .subscribe(employeeResponse -> presenter.showTopLevelManagement());
        presenter.addSubscription(subscribe);
    }
}
