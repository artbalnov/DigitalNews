package artbalnov.digitalnewsrefactored.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;

/**
 * Base class that provide app level dependencies
 */


@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    public Context provideContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    public SharedPreferences provideSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
