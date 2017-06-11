package artbalnov.digitalnewsrefactored.presentation.main;


import java.util.List;

import javax.inject.Inject;

import artbalnov.digitalnewsrefactored.base.di.scopes.PerActivity;
import artbalnov.digitalnewsrefactored.base.presenters.BasePresenter;
import artbalnov.digitalnewsrefactored.domain.models.PostModel;
import artbalnov.digitalnewsrefactored.domain.usecases.PostUseCase;
import rx.Observer;

@PerActivity
class MainPresenter extends BasePresenter<MainView> {

    private PostUseCase mPostUseCase;

    @Inject
    MainPresenter(PostUseCase postUseCasey) {
        this.mPostUseCase = postUseCasey;
    }

    @Override
    protected void onFirstAttach() {
        loadPosts();
    }

    void onLikeClicked(PostModel postModel) {
        mPostUseCase.savePost(postModel);
    }

    void loadPosts() {
        registerSubscription(mPostUseCase.getPosts().subscribe(new Observer<List<PostModel>>() {
            @Override
            public void onCompleted() {
                mView.hideLoader();
            }

            @Override
            public void onError(Throwable e) {
                mView.hideLoader();
                mView.showError(e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onNext(List<PostModel> postList) {
                mView.setPostList(postList);
            }
        }));
    }
}
