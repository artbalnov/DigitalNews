package artbalnov.digitalnewsrefactored.domain.repositories;

import java.util.ArrayList;
import java.util.List;

import artbalnov.digitalnewsrefactored.domain.models.PostModel;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Repository for posts, provides CRUD operations
 */

public class PostRepository {

    public void savePost(PostModel postModel) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(
                bgRealm -> bgRealm.copyToRealmOrUpdate(postModel),
                realm::close,
                error -> realm.close());
    }


    public void savePosts(List<PostModel> postModels) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(
                bgRealm -> bgRealm.copyToRealmOrUpdate(postModels),
                realm::close,
                error -> realm.close());
    }


    public Observable<List<PostModel>> getAllSavedPosts() {
        Realm realm = Realm.getDefaultInstance();

        return realm.where(PostModel.class)
                .findAllAsync()
                .asObservable()
                .filter(RealmResults::isLoaded)
                .first()
                .map(realmResults -> {
                    ArrayList<PostModel> postList = new ArrayList<>(realmResults.size());
                    postList.addAll(realm.copyFromRealm(realmResults));

                    return postList;
                });

    }

    public Observable<List<PostModel>> getFavoritePosts() {
        Realm realm = Realm.getDefaultInstance();

        return realm.where(PostModel.class)
                .equalTo("isLiked", true)
                .findAllAsync()
                .asObservable()
                .filter(RealmResults::isLoaded)
                .first()
                .map(realmResults -> {
                    ArrayList<PostModel> postList = new ArrayList<>(realmResults.size());
                    postList.addAll(realm.copyFromRealm(realmResults));
                    return postList;
                });

    }

}
