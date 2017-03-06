package demoapp.dapulse.com.dapulsedemoapp.dagger;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demoapp.dapulse.com.dapulsedemoapp.BuildConfig;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.EmployeesVIP;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.repo.EmployeeRepo;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.repo.RealmEmployeeConverter;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

@Module
public class NetworkModule {

    private final String mBaseUrl;

    public NetworkModule(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Provides @Singleton
    public RestAdapter provideRestAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint(mBaseUrl)
                .setConverter(new GsonConverter(new Gson()))
                .setLogLevel((BuildConfig.DEBUG ?
                        RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE))
                .build();
    }

    @Provides @Singleton
    public ServerApi provideAPIService(RestAdapter restAdapter) {
        return restAdapter.create(ServerApi.class);
    }



    @Provides @Singleton
    RealmEmployeeConverter provideConverter() {
        return new RealmEmployeeConverter();
    }
}