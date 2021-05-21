package dk.easj.anbo.mvvmrestpostsexample.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dk.easj.anbo.mvvmrestpostsexample.repository.PostRepository;

public class PostsViewModel extends ViewModel {
    private PostRepository repository;
    private LiveData<List<Post>> postsLiveData;

    public PostsViewModel() {
        repository = new PostRepository();
        postsLiveData = repository.getPostsLiveData();
    }

    public void getPosts() {
        //Log.d("klimpe", "PostsViewModel ");
        repository.getPosts();
    }

    public LiveData<List<Post>> getPostsLiveData() {
        return postsLiveData;
    }
}
