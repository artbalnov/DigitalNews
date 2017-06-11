package artbalnov.digitalnewsrefactored.domain.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PostModel extends RealmObject {

    @SerializedName("TITLE")
    @PrimaryKey
    public String title;

    @SerializedName("VIEWS")
    public String viewsCount;

    @SerializedName("TIME")
    public long postTime;

    @SerializedName("COMMENTS")
    public String commentsCount;

    @SerializedName("PORTAL")
    public String portal;

    @SerializedName("IMAGE")
    public String thumbnail;

    @SerializedName("LIKES")
    public String likes;

    @SerializedName("REF")
    public String postUrl;

    public boolean isLiked;
}
