package artbalnov.digitalnewsrefactored.base.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import artbalnov.digitalnewsrefactored.base.annotations.LayoutResourceId;
import artbalnov.digitalnewsrefactored.base.api.HasPresenter;
import artbalnov.digitalnewsrefactored.base.di.api.HasComponent;
import artbalnov.digitalnewsrefactored.base.di.base.BasePresentationComponent;
import artbalnov.digitalnewsrefactored.base.presenters.BasePresenter;

/**
* Base Activity class that inject {@link Presenter} layer according to MVP pattern
*/

public abstract class BaseActivity<Component extends BasePresentationComponent,
        Presenter extends BasePresenter>
        extends AppCompatActivity
        implements HasComponent<Component>, HasPresenter<Presenter> {

    //Presenter Reference, which is going to be injected when initialization finished work
    protected Presenter mPresenter;

    //Component Reference
    private Component mComponent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //gets LayoutResourceId Annotation from the actual class
        LayoutResourceId layoutResourceId = this.getClass().getAnnotation(LayoutResourceId.class);
        //if Annotation found, its argument passed to setContentView method
        if (layoutResourceId != null) {
            setContentView(layoutResourceId.value());
        }
        //invokes renderView abstract method
        renderView(savedInstanceState);
        mComponent = provideComponent();
        //injects actual activity's instance to Component ObjectGraph
        mComponent.inject(this);
        onInjectionFinished(savedInstanceState);
    }


    //invoked exactly after setContentView
    protected abstract void renderView(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //detaches activity object from presenter, to avoid memory leaks
        mPresenter.detachView();
    }

    //invoked by Dagger2 Component
    @Override
    @Inject
    public void injectPresenter(Presenter presenter) {
        mPresenter = presenter;
    }

    //invoked by onPostCreate lifecycle method, when initialization finished work
    protected abstract void onInjectionFinished(Bundle savedInstanceState);

    @Override
    public Component getComponent() {
        return mComponent;
    }

    public void restartActivity() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        finish();
        startActivity(intent);
    }
}
