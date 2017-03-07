package demoapp.dapulse.com.dapulsedemoapp;

import android.app.Application;
import android.os.StrictMode;

import demoapp.dapulse.com.dapulsedemoapp.dagger.ApplicationComponent;
import demoapp.dapulse.com.dapulsedemoapp.dagger.ApplicationModule;
import demoapp.dapulse.com.dapulsedemoapp.dagger.DaggerApplicationComponent;
import demoapp.dapulse.com.dapulsedemoapp.dagger.NetworkModule;
import io.realm.Realm;

/**
 * Created by ofertour on 06/02/2017.
 */

public class DemoApp extends Application {

    public static final String TAG = "DemoApp";

    private ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyDeath()
                .build());

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
