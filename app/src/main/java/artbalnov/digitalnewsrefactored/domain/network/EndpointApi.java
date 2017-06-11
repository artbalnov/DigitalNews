package artbalnov.digitalnewsrefactored.domain.network;


import artbalnov.digitalnewsrefactored.domain.models.PostsModel;

import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * An interface that provides endpoints on the server side
 * */

public interface EndpointApi {

    @GET("/")
    Observable<Response<PostsModel>> getAllPosts();

}
