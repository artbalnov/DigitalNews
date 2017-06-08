package artbalnov.digitalnewsrefactored.application;

import android.app.Application;

import artbalnov.digitalnewsrefactored.base.di.api.HasComponent;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Application class used for initialize database and build Dagger dependencies graph
 */

public class App extends Application implements HasComponent<AppComponent> {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build());

        mAppComponent = provideComponent();
    }

    @Override
    public AppComponent provideComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @Override
    public AppComponent getComponent() {
        return mAppComponent;
    }

}
