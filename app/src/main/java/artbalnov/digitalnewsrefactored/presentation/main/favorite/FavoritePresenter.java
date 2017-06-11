package artbalnov.digitalnewsrefactored.presentation.main.favorite;

import android.util.Log;

import javax.inject.Inject;

import artbalnov.digitalnewsrefactored.base.di.scopes.PerActivity;
import artbalnov.digitalnewsrefactored.base.presenters.BasePresenter;
import artbalnov.digitalnewsrefactored.domain.models.PostModel;
import artbalnov.digitalnewsrefactored.domain.usecases.PostUseCase;

@PerActivity
class FavoritePresenter extends BasePresenter<FavoriteView> {

    private PostUseCase mPostUseCase;

    @Inject
    FavoritePresenter(PostUseCase postUseCase) {
        this.mPostUseCase = postUseCase;
    }

    @Override
    protected void onFirstAttach() {
        loadFavoritePosts();
    }

    void loadFavoritePosts() {
        registerSubscription(mPostUseCase.getFavoritePosts()
                .subscribe(postList -> {
                    if (!postList.isEmpty())
                        mView.setPostList(postList);
                    else mView.showEmptyFavoriteAlert();
                }));
    }

    void onLikeClicked(PostModel postModel) {
        mPostUseCase.savePost(postModel);
    }
}
