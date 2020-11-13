package com.example.docvision;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Requests {
    @Multipart
    @POST("posts_image")
    Call<String> postimage(@Part MultipartBody.Part image_file);

    @GET("doOP/")
    Call<String> doOP(@Query("operation") String op,
                      @Query("image") String image);

}
