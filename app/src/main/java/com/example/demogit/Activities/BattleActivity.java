package com.example.demogit.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demogit.Api.User;
import com.example.demogit.Model.Player;
import com.example.demogit.Model.UserRepo;
import com.example.demogit.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BattleActivity extends AppCompatActivity {

    EditText playername;
    TextView loggedinName,winner1,winner2,draw;
    Call<Player> call;
    TextView tv,tv1;
    public int point,point1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        playername= findViewById(R.id.player2);
        loggedinName= findViewById(R.id.player1);
        tv=findViewById(R.id.points);
        tv1= findViewById(R.id.points1);
        winner1=findViewById(R.id.winner1);
        winner2 = findViewById(R.id.winner2);
        draw=findViewById(R.id.draw);
        Intent i = getIntent();
        String text = i.getStringExtra ( "Username");
        loggedinName.setText(text);
        loggedinName.setPaintFlags(loggedinName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder newRequest = request.newBuilder().header("Authorization", "TXVzaGZpcVNhbGVoaW4tQ2hvd2RodXJ5OjkxN2h1Yi5jMG0jI0BA");
                        return chain.proceed(newRequest.build());
                    }
                });
        Retrofit retrofit = new Retrofit.Builder().baseUrl(User.Base_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        User api = retrofit.create(User.class);
        call = api.getPlayer(text);
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                Player repositories =response.body();
                point= repositories.getFollowers()+repositories.getFollowing();
                tv1.setText("Followers :"+repositories.getFollowers().toString()+"\n"+
                        "Following: "+repositories.getFollowing().toString()+"\n"+
                        "Public Reositories: "+ repositories.getPublicRepos().toString()
                );
                tv1.setPaintFlags(tv1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            }
            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Log.i("Error1",t.getMessage().toString());
            }
        });
    }
    public void findPlayer(View view) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder newRequest = request.newBuilder().header("Authorization", " 5ceac502214f05c703c16f3fac3f6478acfd2052");
                        return chain.proceed(newRequest.build());
                    }
                });
        Retrofit retrofit = new Retrofit.Builder().baseUrl(User.Base_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        User api = retrofit.create(User.class);
        String text = playername.getText().toString();
        if(!TextUtils.isEmpty(text)) {
            call = api.getPlayer(text);
            call.enqueue(new Callback<Player>() {
                @Override
                public void onResponse(Call<Player> call, Response<Player> response) {
                    Player repositories = response.body();
                    point1 = repositories.getFollowers() + repositories.getFollowing();
                    tv.setText("Followers :" + repositories.getFollowers().toString() + "\n" +
                            "Following: " + repositories.getFollowing().toString() + "\n" +
                            "Public Reositories: " + repositories.getPublicRepos().toString()
                    );
                    tv.setPaintFlags(tv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                }
                @Override
                public void onFailure(Call<Player> call, Throwable t) {
                    Log.i("Error1", t.getMessage().toString());
                }
            });
        }
        else {
            tv.setText("Invalid Player Name");
        }
    }
    public void result(View view) {

        if (point>point1){
            winner1.setText("WINNER");
        }
        else if (point<point1)
            winner2.setText("WINNER");
        else if(point1==point)
            draw.setText("DRAW");
    }

    public void reSet(View view) {
        tv.setText("");
        playername.setText("");
        winner1.setText("");
        winner2.setText("");
        draw.setText("");
    }
}