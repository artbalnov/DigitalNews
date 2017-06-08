package artbalnov.digitalnewsrefactored.base.api;

import artbalnov.digitalnewsrefactored.base.presenters.IPresenter;


public interface HasPresenter<Presenter extends IPresenter> {
    void injectPresenter(Presenter presenter);
}
