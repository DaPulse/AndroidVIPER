package demoapp.dapulse.com.dapulsedemoapp.features.employees.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ofertour on 06/02/2017.
 */

public class EmployeeResponse {
    @SerializedName("company_name")
    @Expose
    public String companyName;
    @SerializedName("employees")
    @Expose
    public List<Employee> employees = null;
}
