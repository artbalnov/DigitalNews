package artbalnov.digitalnewsrefactored.domain.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostsModel {

    @SerializedName("ARTICLES")
    public List<PostModel> postList;
}
