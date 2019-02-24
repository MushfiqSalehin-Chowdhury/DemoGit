package com.example.demogit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Devs {

    String Base_URL = "https://github-trending-api.now.sh/";

    @GET("developers?since=today")
    Call<List<Developers>> getDevt ();
    @GET("developers?since=weekly")
    Call<List<Developers>> getDevw ();
    @GET("developers?since=monthly")
    Call<List<Developers>> getDevm ();


}
