package artbalnov.digitalnewsrefactored.domain.usecases;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import artbalnov.digitalnewsrefactored.R;
import artbalnov.digitalnewsrefactored.domain.models.PostModel;
import artbalnov.digitalnewsrefactored.domain.network.EndpointApi;
import artbalnov.digitalnewsrefactored.domain.repositories.PostRepository;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * It servers as a binder between Presentation layer and Data Layer.
 * The data source is {@link artbalnov.digitalnewsrefactored.domain.network.EndpointApi} and {@link artbalnov.digitalnewsrefactored.domain.repositories.PostRepository}
 */

public class PostUseCase {

    private Context mContext;

    private EndpointApi mEndpointApi;
    private PostRepository mPostRepository;

    @Inject
    public PostUseCase(Context context, EndpointApi endpointApi, PostRepository postRepository) {
        this.mEndpointApi = endpointApi;
        this.mPostRepository = postRepository;
        this.mContext = context;
    }

    public Observable<List<PostModel>> getPosts() {
        return mEndpointApi.getAllPosts()
                .flatMap(postResponse -> {
                    if (postResponse.isSuccessful() && postResponse.body() != null)
                        return Observable.just(postResponse.body().postList);
                    else
                        return Observable.error(new Exception(mContext.getString(R.string.error_code_is) + postResponse.code()));
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<PostModel>> getFavoritePosts() {
        return mPostRepository.getFavoritePosts();
    }

    public void savePost(PostModel postModel) {
        mPostRepository.savePost(postModel);
    }

    public void savePosts(List<PostModel> postModels) {
        mPostRepository.savePosts(postModels);
    }
}
