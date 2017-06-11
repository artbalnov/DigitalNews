package artbalnov.digitalnewsrefactored.presentation.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.List;

import artbalnov.digitalnewsrefactored.R;
import artbalnov.digitalnewsrefactored.application.AppComponent;
import artbalnov.digitalnewsrefactored.base.activities.BaseActivity;
import artbalnov.digitalnewsrefactored.base.annotations.LayoutResourceId;
import artbalnov.digitalnewsrefactored.base.di.api.HasComponent;
import artbalnov.digitalnewsrefactored.base.di.scopes.PerActivity;
import artbalnov.digitalnewsrefactored.domain.models.PostModel;

@PerActivity
@LayoutResourceId(R.layout.activity_main)
public class MainActivity extends BaseActivity<MainComponent, MainPresenter> implements MainView {

    private RecyclerView mPostList;
    private PostRecyclerAdapter mPostAdapter;

    private SwipeRefreshLayout mSwipeRefresher;


    @Override
    protected void renderView(Bundle savedInstanceState) {
        initToolbar();

        initRefresher();

        initList();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initRefresher() {
        mSwipeRefresher = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mSwipeRefresher.setOnRefreshListener(() -> mPresenter.loadPosts());
    }

    private void initList() {
        mPostList = (RecyclerView) findViewById(R.id.rvPostList);
        mPostList.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.post_divider));
        mPostList.addItemDecoration(dividerItemDecoration);

        mPostAdapter = new PostRecyclerAdapter(this);
        mPostAdapter.setPostClickListener(new PostRecyclerAdapter.PostClickListener() {
            @Override
            public void onPostClicked(PostModel postModel) {

            }

            @Override
            public void onLikeClicked(PostModel postModel) {
                mPresenter.onLikeClicked(postModel);
            }
        });

        mPostList.setAdapter(mPostAdapter);
    }


    @Override
    public void setPostList(List<PostModel> postList) {
        mPostAdapter.setPostList(postList);
    }

    @Override
    public void addPostList(List<PostModel> postList) {
        mPostAdapter.addNewPostList(postList);
    }


    @Override
    public void hideLoader() {
        mSwipeRefresher.setRefreshing(false);
    }

    @Override
    public void showError(String errorMessage){
        new AlertDialog.Builder(this)
                .setTitle(R.string.error_title)
                .setMessage(errorMessage)
                .setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onInjectionFinished(Bundle savedInstanceState) {
        mPresenter.attach(this);
    }

    @Override
    public MainComponent provideComponent() {
        HasComponent<AppComponent> hasComponent = (HasComponent<AppComponent>) getApplication();
        return DaggerMainComponent
                .builder()
                .appComponent(hasComponent.getComponent())
                .build();
    }
}
