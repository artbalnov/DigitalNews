package artbalnov.digitalnewsrefactored.application;

import android.content.Context;

import javax.inject.Singleton;

import artbalnov.digitalnewsrefactored.base.di.base.BaseComponent;
import artbalnov.digitalnewsrefactored.domain.network.EndpointApi;
import artbalnov.digitalnewsrefactored.domain.repositories.PostRepository;
import dagger.Component;

/**
 * Base Component Interface that provides dependencies from different app modules
 */


@Singleton
@Component(modules = {AppModule.class, NetworkingModule.class})
public interface AppComponent extends BaseComponent {
     Context provideContext();

     EndpointApi provideEndpointApi();

     PostRepository postRepository();
}
