package artbalnov.digitalnewsrefactored.base.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import javax.inject.Inject;

import artbalnov.digitalnewsrefactored.base.annotations.LayoutResourceId;
import artbalnov.digitalnewsrefactored.base.api.HasPresenter;
import artbalnov.digitalnewsrefactored.base.di.api.HasComponent;
import artbalnov.digitalnewsrefactored.base.di.base.BasePresentationComponent;
import artbalnov.digitalnewsrefactored.base.presenters.BasePresenter;


/**
 * Base Fragment class that inject Presenter layer according to MVP pattern
 */

public abstract class BaseFragment<Component extends BasePresentationComponent, Presenter extends BasePresenter>
        extends Fragment
        implements HasComponent<Component>, HasPresenter<Presenter> {

    protected Presenter mPresenter;
    private Component mComponent;
    protected Context mLastContext;
    protected View mRootView;

    @Override
    @Inject
    public void injectPresenter(Presenter presenter) {
        mPresenter = presenter;
    }

    public View findViewById(int id) {
        return mRootView.findViewById(id);
    }

    protected abstract void onInjectionFinished();

    protected void afterAttach(Context context) {
        mLastContext = context;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        afterAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        afterAttach(activity);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected int getLayoutId() {
        LayoutResourceId layoutResourceId = this.getClass().getAnnotation(LayoutResourceId.class);
        if (layoutResourceId != null) {
            return layoutResourceId.value();
        }
        return -1;
    }

    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(getLayoutId(), parent, false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = createView(inflater, container);
        onRootViewCreated(savedInstanceState);
        if (savedInstanceState != null) {
            restoreState(savedInstanceState);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mComponent == null) {
            mComponent = provideComponent();
            mComponent.inject(this);
        }
        onInjectionFinished();
    }

    protected void restoreState(@NonNull Bundle savedInstanceState) {

    }

    protected abstract void onRootViewCreated(@Nullable Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
        mLastContext = null;
        mRootView = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Component getComponent() {
        return mComponent;
    }
}
