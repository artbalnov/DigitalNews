package artbalnov.digitalnewsrefactored.application;

import javax.inject.Singleton;

import artbalnov.digitalnewsrefactored.base.di.base.BaseComponent;
import dagger.Component;

/**
 * Base Component Interface that provide dependencies from different app modules
 */


@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent extends BaseComponent {
}
