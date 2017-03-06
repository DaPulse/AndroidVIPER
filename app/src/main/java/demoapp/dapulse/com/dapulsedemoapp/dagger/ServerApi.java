package demoapp.dapulse.com.dapulsedemoapp.dagger;

import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.EmployeeResponse;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by ofertour on 01/02/2017.
 */
public interface ServerApi {

    @GET("/")
    Observable<EmployeeResponse> getEmployees();
}
