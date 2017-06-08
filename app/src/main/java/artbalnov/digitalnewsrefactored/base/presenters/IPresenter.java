package artbalnov.digitalnewsrefactored.base.presenters;


import artbalnov.digitalnewsrefactored.base.views.IView;

public interface IPresenter<View extends IView> {
    void attach(View view);

    void detachView();
}
