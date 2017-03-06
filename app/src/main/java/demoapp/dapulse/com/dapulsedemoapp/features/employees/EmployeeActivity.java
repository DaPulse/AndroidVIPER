package demoapp.dapulse.com.dapulsedemoapp.features.employees;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import demoapp.dapulse.com.dapulsedemoapp.R;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.base.BaseEmployeeActivity;

public class EmployeeActivity extends BaseEmployeeActivity {

    @BindView(R.id.text_view)
    TextView companyNameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            presenter.loadCompany().subscribe(employeeResponse -> loadDataExample());
        } else {
            loadDataExample();
        }
    }


    /************************************************************************
     * Here's some examples on how to get the data and subscribe to it.
     ***********************************************************************/
    private void loadDataExample() {

        presenter.getCompanyName().subscribe(company -> companyNameTv.setText(company));

        presenter.getTopLevelManagement().subscribe(employees -> {
            Log.d("EmployeeActivity", "got top level management data");
        });

        presenter.getDepartmentEmployees("Customer Success").subscribe(list -> {
            Log.d("EmployeeActivity", "got department data: " + list.size());
        });

        presenter.getEmployeeById(16).subscribe(employee -> {
            Log.d("EmployeeActivity", "got employee data");
        });

        presenter.getManagerEmployeesByManagerId(10).subscribe(employees -> {
            Log.d("EmployeeActivity", "got manager employees data");
        });
    }
}
