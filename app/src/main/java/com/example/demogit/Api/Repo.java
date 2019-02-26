package com.example.demogit.Api;

import com.example.demogit.Model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Repo {
    String Base_URL = "https://github-trending-api.now.sh/";


    @GET("repositories?since=today")
    Call<List<Repository>> getRepositoryt ();
    @GET("repositories?since=weekly")
    Call<List<Repository>> getRepositoryw ();
    @GET("repositories?since=monthly")
    Call<List<Repository>> getRepositorym ();

}
