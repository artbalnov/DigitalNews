package artbalnov.digitalnewsrefactored.base.presenters;



import artbalnov.digitalnewsrefactored.base.views.IView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Base Presenter class that attach to {@link artbalnov.digitalnewsrefactored.base.activities.BaseActivity}.
 * or {@link artbalnov.digitalnewsrefactored.base.fragments.BaseFragment} according to MVP pattern
 * */

public abstract class BasePresenter<View extends IView> implements IPresenter<View> {

    protected View mView;

    private CompositeDisposable mCompositeDisposable;

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

    protected void registerSubscription(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }


        mCompositeDisposable.add(disposable);

    }

    protected void onFirstAttach() {

    }

    @Override
    public final void detachView() {
        onDetach();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
        mView = null;
    }

    protected void onDetach() {

    }

    protected View getView() {
        return mView;
    }

}
