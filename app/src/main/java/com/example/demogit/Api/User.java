package com.example.demogit.Api;

import com.example.demogit.Model.Player;
import com.example.demogit.Model.UserRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface User {
    String Base_URL = "https://api.github.com/users/";

    @GET("{user_name}/repos")
    Call<List<UserRepo>> getOwner (@Path("user_name") String userName);

    @GET("{user_name}")
    Call<Player> getPlayer (@Path("user_name") String userName);



}
