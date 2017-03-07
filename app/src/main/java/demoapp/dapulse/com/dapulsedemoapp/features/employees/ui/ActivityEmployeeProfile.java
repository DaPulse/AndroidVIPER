package demoapp.dapulse.com.dapulsedemoapp.features.employees.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import demoapp.dapulse.com.dapulsedemoapp.NavigationHandler;
import demoapp.dapulse.com.dapulsedemoapp.R;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.Employee;

/**
 * Created by danfa on 25/02/2017.
 */

public class ActivityEmployeeProfile extends AppCompatActivity {

    @BindView(R.id.employee_phone)
    TextView phone;

    @BindView(R.id.employee_view)
    ItemViewEmployee employeeItemView;

    @BindView(R.id.employee_mail)
    TextView email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        //sometimes there's no point in having a presenter
        Employee employee = getIntent().getExtras().getParcelable(NavigationHandler.EXTRA_MANAGER);
        if (employee != null) {
            employeeItemView.showEmployee(employee);
            phone.setText(employee.phone);
            email.setText(employee.email);
        }

    }
}
