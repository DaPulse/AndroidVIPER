package demoapp.dapulse.com.dapulsedemoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demoapp.dapulse.com.dapulsedemoapp.features.employees.EmployeeActivity;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, EmployeeActivity.class));
        finish();
    }
}
