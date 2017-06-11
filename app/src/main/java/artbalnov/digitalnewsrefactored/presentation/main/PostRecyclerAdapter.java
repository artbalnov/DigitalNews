package artbalnov.digitalnewsrefactored.presentation.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import artbalnov.digitalnewsrefactored.R;
import artbalnov.digitalnewsrefactored.constants.Constants;
import artbalnov.digitalnewsrefactored.domain.models.PostModel;
import artbalnov.digitalnewsrefactored.utils.DateFormatter;


public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {

    private Context mContext;

    private List<PostModel> mPostList;

    private PostClickListener mPostClickListener;

    public PostRecyclerAdapter(Context context) {
        this.mPostList = new ArrayList<>();
        this.mContext = context;
    }


    /**
     * Set new post list replacing current post list
     */
    public void setPostList(List<PostModel> postList) {
        this.mPostList = postList;
        notifyDataSetChanged();
    }

    /**
     * Appends all of the post models in the current post list to the end of this list
     * */
    public void addNewPostList(List<PostModel> newPostList) {
        this.mPostList.addAll(newPostList);

        notifyItemRangeInserted(mPostList.size(), newPostList.size());
    }

    public void setPostClickListener(PostClickListener postClickListener) {
        this.mPostClickListener = postClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PostModel postModel = mPostList.get(position);

        if (mPostClickListener != null) {
            holder.itemView.setOnClickListener(view -> mPostClickListener.onPostClicked(postModel));
            holder.likeBtn.setOnClickListener(view -> onLikeClicked(postModel, (ImageView) view));
        }

        holder.portalName.setText(postModel.portal);
        holder.postDate.setText(DateFormatter.formatDateFromMs(DateFormatter.DD_MM_YYYY_HH_MM, postModel.postTime));
        holder.postText.setText(postModel.title);

        setPortalIcon(postModel.portal, holder.portalIcon);

        setLikeBackground(postModel.isLiked, holder.likeBtn);

        if (!postModel.viewsCount.equals(Constants.EMPTY_VALUE))
            holder.postViews.setText(postModel.viewsCount);

        Picasso.with(mContext)
                .load(postModel.thumbnail)
                .placeholder(R.drawable.ic_post_placeholder)
                .into(holder.postThumbnail);
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView portalName, postDate, postText, postViews;
        ImageView postThumbnail, portalIcon, likeBtn;


        ViewHolder(View itemView) {
            super(itemView);

            portalName = (TextView) itemView.findViewById(R.id.tvPostPortalName);
            postDate = (TextView) itemView.findViewById(R.id.tvPostDate);
            postText = (TextView) itemView.findViewById(R.id.tvPostTitle);
            postViews = (TextView) itemView.findViewById(R.id.tvPostViews);
            postThumbnail = (ImageView) itemView.findViewById(R.id.ivPostThumbnail);
            portalIcon = (ImageView) itemView.findViewById(R.id.ivPostPortalIcon);
            likeBtn = (ImageView) itemView.findViewById(R.id.ivBtnLike);
        }
    }

    public interface PostClickListener {

        void onPostClicked(PostModel postModel);

        void onLikeClicked(PostModel postModel);
    }

    private void setPortalIcon(String portalName, ImageView portalIcon) {
        switch (portalName) {
            case Constants.PLAYGROUND:
                portalIcon.setImageResource(R.drawable.ic_plground);
                break;
            case Constants.GEEKTIMES:
                portalIcon.setImageResource(R.drawable.ic_geek);
                break;
            case Constants.HABRAHABR:
                portalIcon.setImageResource(R.drawable.ic_habr);
                break;
            case Constants.TPROGER:
                portalIcon.setImageResource(R.drawable.ic_tproger);
                break;
        }
    }

    private void setLikeBackground(boolean isLiked, ImageView likeBtn) {
        int resBackground = isLiked ? R.drawable.ic_liked : R.drawable.ic_like;

        likeBtn.setImageResource(resBackground);
    }

    private void onLikeClicked(PostModel postModel, ImageView likeBtn) {
        Animation pulse = AnimationUtils.loadAnimation(mContext, R.anim.pulse);
        likeBtn.startAnimation(pulse);
        postModel.isLiked = !postModel.isLiked;
        setLikeBackground(postModel.isLiked, likeBtn);

        mPostClickListener.onLikeClicked(postModel);
    }


}
