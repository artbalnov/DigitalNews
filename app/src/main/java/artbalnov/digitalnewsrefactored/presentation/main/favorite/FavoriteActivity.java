package artbalnov.digitalnewsrefactored.presentation.main.favorite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import artbalnov.digitalnewsrefactored.R;
import artbalnov.digitalnewsrefactored.application.AppComponent;
import artbalnov.digitalnewsrefactored.base.activities.BaseActivity;
import artbalnov.digitalnewsrefactored.base.annotations.LayoutResourceId;
import artbalnov.digitalnewsrefactored.base.di.api.HasComponent;
import artbalnov.digitalnewsrefactored.base.di.scopes.PerActivity;
import artbalnov.digitalnewsrefactored.domain.models.PostModel;
import artbalnov.digitalnewsrefactored.presentation.main.PostRecyclerAdapter;

@PerActivity
@LayoutResourceId(R.layout.activity_favorite)
public class FavoriteActivity extends BaseActivity<FavoriteComponent, FavoritePresenter> implements FavoriteView {

    private RecyclerView mPostList;
    private PostRecyclerAdapter mPostAdapter;


    @Override
    protected void renderView(Bundle savedInstanceState) {
        initToolbar();

        initList();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    public void showEmptyFavoriteAlert() {
        Toast.makeText(this, R.string.empty_favorite_page, Toast.LENGTH_SHORT).show();
    }

    public static void startActivity(Context context) {
        Intent starter = new Intent(context, FavoriteActivity.class);
        context.startActivity(starter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return true;
    }

    @Override
    protected void onInjectionFinished(Bundle savedInstanceState) {
        mPresenter.attach(this);
    }

    @Override
    public FavoriteComponent provideComponent() {
        HasComponent<AppComponent> hasComponent = (HasComponent<AppComponent>) getApplication();
        return DaggerFavoriteComponent
                .builder()
                .appComponent(hasComponent.getComponent())
                .build();

    }

}
