package dk.easj.anbo.mvvmrestpostsexample.rest;

import java.util.List;

import dk.easj.anbo.mvvmrestpostsexample.model.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TypicodeService {
    @GET("/posts")
    Call<List<Post>> getPosts(/*@Query("id") String id*/);
}
