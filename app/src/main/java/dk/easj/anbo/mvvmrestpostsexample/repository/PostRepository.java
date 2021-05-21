package dk.easj.anbo.mvvmrestpostsexample.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import dk.easj.anbo.mvvmrestpostsexample.model.Post;
import dk.easj.anbo.mvvmrestpostsexample.rest.TypicodeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRepository {
    private static final String TypicodeUrl = "https://jsonplaceholder.typicode.com/";

    private TypicodeService typicodeService;
    private MutableLiveData<List<Post>> postsLiveData;

    public PostRepository() {
        postsLiveData = new MutableLiveData<>();

        final Retrofit build = new Retrofit.Builder().baseUrl(TypicodeUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        typicodeService = build.create(TypicodeService.class);
    }

    public void getPosts() {
        Log.d("klimpe", "PostRepository getPosts");
        Call<List<Post>> call = typicodeService.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    postsLiveData.postValue(response.body());
                }
                else {
                    // TODO should handle errors: LiveData<String> errorMessage
                    Log.d("klimpe", "response status code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                postsLiveData.postValue(null);
            }
        });
    }

    public LiveData<List<Post>> getPostsLiveData() {
        return postsLiveData;
    }
}
