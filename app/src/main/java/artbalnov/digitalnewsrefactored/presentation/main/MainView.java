package artbalnov.digitalnewsrefactored.presentation.main;


import java.util.List;

import artbalnov.digitalnewsrefactored.base.views.IView;
import artbalnov.digitalnewsrefactored.domain.models.PostModel;

public interface MainView extends IView {

    void setPostList(List<PostModel> postList);
    void addPostList(List<PostModel> postList);

    void hideLoader();

    void showError(String errorMessage);
}
