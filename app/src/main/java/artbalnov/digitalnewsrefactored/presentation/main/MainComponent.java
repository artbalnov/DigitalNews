package artbalnov.digitalnewsrefactored.presentation.main;

import artbalnov.digitalnewsrefactored.application.AppComponent;
import artbalnov.digitalnewsrefactored.base.di.base.BasePresentationComponent;
import artbalnov.digitalnewsrefactored.base.di.scopes.PerActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface MainComponent extends BasePresentationComponent<MainActivity> {

}
