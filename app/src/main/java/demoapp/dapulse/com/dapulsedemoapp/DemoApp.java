package demoapp.dapulse.com.dapulsedemoapp;

import android.app.Application;

import demoapp.dapulse.com.dapulsedemoapp.dagger.ApplicationComponent;
import demoapp.dapulse.com.dapulsedemoapp.dagger.ApplicationModule;
import demoapp.dapulse.com.dapulsedemoapp.dagger.DaggerApplicationComponent;
import demoapp.dapulse.com.dapulsedemoapp.dagger.NetworkModule;
import io.realm.Realm;

/**
 * Created by ofertour on 06/02/2017.
 */

public class DemoApp extends Application{


    private ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule("https://dapulse-mobile-test.herokuapp.com/"))
                .build();

        Realm.init(this);
    }

    public ApplicationComponent getAppComponent() {
        return mAppComponent;
    }
}
