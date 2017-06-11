package artbalnov.digitalnewsrefactored.presentation.main.favorite;

import artbalnov.digitalnewsrefactored.application.AppComponent;
import artbalnov.digitalnewsrefactored.base.di.base.BasePresentationComponent;
import artbalnov.digitalnewsrefactored.base.di.scopes.PerActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
interface FavoriteComponent extends BasePresentationComponent<FavoriteActivity>{
}
