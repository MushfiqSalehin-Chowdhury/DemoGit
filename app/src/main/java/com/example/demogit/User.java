package com.example.demogit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface User {
    String Base_URL = "https://api.github.com/users/h-sumon/";

    @GET("repos")
    Call<List<UserRepo>> getOwner ();

}
