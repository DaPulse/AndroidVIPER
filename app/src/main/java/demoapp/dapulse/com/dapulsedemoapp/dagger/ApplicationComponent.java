package demoapp.dapulse.com.dapulsedemoapp.dagger;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import demoapp.dapulse.com.dapulsedemoapp.NavigationHandler;

@Singleton
@Component(modules={ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    ServerApi getServerApi();
    SharedPreferences getPrefs();
    NavigationHandler getNavigationHandler();
}
