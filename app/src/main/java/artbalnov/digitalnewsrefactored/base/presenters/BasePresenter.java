package artbalnov.digitalnewsrefactored.base.presenters;



import artbalnov.digitalnewsrefactored.base.views.IView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Base Presenter class that attach to {@link artbalnov.digitalnewsrefactored.base.activities.BaseActivity}.
 * or {@link artbalnov.digitalnewsrefactored.base.fragments.BaseFragment} according to MVP pattern
 * */

public abstract class BasePresenter<View extends IView> implements IPresenter<View> {

    protected View mView;

    private CompositeSubscription mCompositeSubscription;

    private boolean mIsFirstAttach = true;

    @Override
    public final void attach(View view) {
        this.mView = view;
        onAttach();
        if (mIsFirstAttach) {
            onFirstAttach();
            mIsFirstAttach = false;
        }
    }

    protected void onAttach() {

    }

    protected void registerSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }


        mCompositeSubscription.add(subscription);

    }

    protected void onFirstAttach() {

    }

    @Override
    public final void detachView() {
        onDetach();
        if (mCompositeSubscription != null) {
            mCompositeSubscription.clear();
        }
        mView = null;
    }

    protected void onDetach() {

    }

    protected View getView() {
        return mView;
    }

}
