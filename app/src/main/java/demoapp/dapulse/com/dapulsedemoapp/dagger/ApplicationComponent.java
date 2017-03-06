package demoapp.dapulse.com.dapulsedemoapp.dagger;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    ServerApi getServerApi();
    SharedPreferences getPrefs();
}
