package demoapp.dapulse.com.dapulsedemoapp.dagger;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public final class ApplicationModule {
    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides @Named("application")
    @Singleton
    Context provideApplicationContext() {
        return context;
    }

    @Provides
    @Singleton
    SharedPreferences providePrefs() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


}
