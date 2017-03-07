package demoapp.dapulse.com.dapulsedemoapp.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import demoapp.dapulse.com.dapulsedemoapp.NavigationHandler;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.EmployeeInteractor;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.EmployeePresenter;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.EmployeeView;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.EmployeesVIP;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.repo.EmployeeRepo;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.ui.AdapterEmployees;

@Module
public class EmployeesModule {
    private final AppCompatActivity activity;

    public EmployeesModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @Named("activity")
    @ActivityScope
    Context provideActivityContext() {
        return activity;
    }


    @Provides
    @ActivityScope
    EmployeePresenter providePresenter(EmployeesVIP.Interactor interactor, EmployeesVIP.View view, NavigationHandler navigationHandler) {
        return new EmployeePresenter(activity, interactor, view, navigationHandler);
    }

    @Provides
    @ActivityScope
    EmployeesVIP.Interactor provideInteractor(ServerApi serverApi, EmployeesVIP.Repository repository) {
        return new EmployeeInteractor(serverApi, repository);
    }

    @Provides
    @ActivityScope
    EmployeesVIP.Repository provideRepo(SharedPreferences prefs) {
        return new EmployeeRepo(prefs);
    }

    @Provides
    @ActivityScope
    EmployeesVIP.View provideView(AdapterEmployees adapter) {
        return new EmployeeView(activity, adapter);
    }

    @Provides
    @ActivityScope
    LayoutInflater provideLayoutInflater() {
        return activity.getLayoutInflater();
    }

    @Provides
    @ActivityScope
    AdapterEmployees provideAapter(LayoutInflater inflater) {
        return new AdapterEmployees(inflater);
    }


}