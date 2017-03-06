package demoapp.dapulse.com.dapulsedemoapp.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.EmployeeInteractor;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.EmployeePresenter;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.EmployeesVIP;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.repo.EmployeeRepo;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.repo.RealmEmployeeConverter;

@Module
public class EmployeesModule {
    private final AppCompatActivity activity;

    public EmployeesModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides @Named("activity")
    @ActivityScope
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    @ActivityScope
    EmployeesVIP.Interactor provideInteractor(ServerApi serverApi,  EmployeesVIP.Repository repository) {
        return new EmployeeInteractor(serverApi, repository);
    }

    @Provides
    @ActivityScope
    EmployeesVIP.Repository provideRepo(RealmEmployeeConverter converter, SharedPreferences prefs) {
       return new EmployeeRepo(prefs, converter);
    }



    @Provides
    @ActivityScope
    EmployeePresenter providePresenter(EmployeesVIP.Interactor interactor) {
        return new EmployeePresenter(interactor);
    }


}