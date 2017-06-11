package artbalnov.digitalnewsrefactored.presentation.main.favorite;

import java.util.List;

import artbalnov.digitalnewsrefactored.base.di.scopes.PerActivity;
import artbalnov.digitalnewsrefactored.base.views.IView;
import artbalnov.digitalnewsrefactored.domain.models.PostModel;

@PerActivity
interface FavoriteView extends IView {
    void setPostList(List<PostModel> postList);

    void showEmptyFavoriteAlert();
}
