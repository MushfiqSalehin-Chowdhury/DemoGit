package com.example.demogit;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private List <Repository> repoList= new ArrayList<>();
    Button btn,btn1;
    private RecyclerView recyclerView;
    private RepoAdapter mAdapter;
    RecyclerView.LayoutManager mlayoutManager;
    String heross[];
    ListView listView;
    String x;
    Call<List<Repository>> call;
    Call<List<Developers>> callDev;
    Spinner dropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        recyclerView= findViewById(R.id.Recycle);
        listView= findViewById(R.id.listView);
        RecyclerView.LayoutManager mlayoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"Today", "This Week","This Month"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
        btn=findViewById(R.id.Repository);
        btn1=findViewById(R.id.Developers);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();

        //FOR SHOWING REPOSITORIES

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder newRequest = request.newBuilder().header("Authorization", "TXVzaGZpcVNhbGVoaW4tQ2hvd2RodXJ5OjkxN2h1Yi5jMG0jI0BA");
                        return chain.proceed(newRequest.build());
                    }
                });

            Retrofit retrofit = new Retrofit.Builder().baseUrl(Repo.Base_URL).
                    client(okHttpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create()).build();
            Repo api = retrofit.create(Repo.class);
            switch (item){
                case "Today":
                    repoList.clear();
                    call = api.getRepositoryt();
                    x= "Today";
                    break;
                case "This Week":
                    repoList.clear();
                    call = api.getRepositoryw();
                    x="This Week";
                    break;
                case "This Month":
                    repoList.clear();
                    call = api.getRepositorym();
                    x= "This Month";
                    break;
            }
            call.enqueue(new Callback<List<Repository>>() {
                @Override
                public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                    List<Repository> repositories =response.body();
                    heross =new String[repositories.size()];
                    for (int i = 0; i< repositories.size(); i++){
                        /*for (int j=0;j<repositories.get(i).getBuiltBy().size();j++){
                           *//* BuiltBy builtBy= new BuiltBy(repositories.get(i).getBuiltBy().get(i).getUsername());
                            builders.add(builtBy);*//*
                           heross[j]= repositories.get(i).getBuiltBy().get(i).getUsername();
                        }*/
                        Repository repository = new Repository(repositories.get(i).getAuthor()
                                ,repositories.get(i).getName(),
                                repositories.get(i).getDescription(),
                                repositories.get(i).getLanguage(),
                                repositories.get(i).getLanguageColor(),
                                repositories.get(i).getUrl(),
                                repositories.get(i).getStars(),
                                repositories.get(i).getForks(),
                                repositories.get(i).getCurrentPeriodStars())
                        ;
                        repoList.add(repository);
                        mAdapter=new RepoAdapter(repoList);
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                }
                @Override
                public void onFailure(Call<List<Repository>> call, Throwable t) {
                    Log.i("Errorr",t.getMessage().toString());
                }
            });

            //****FOR SHOWING DEVELOPERS****////

        Retrofit retrofit1 = new Retrofit.Builder().baseUrl(Devs.Base_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Devs api1 = retrofit1.create(Devs.class);
        switch (x){
            case "Today":
                repoList.clear();
                callDev = api1.getDevt();
                break;
            case "This Week":
                repoList.clear();
                callDev = api1.getDevw();
                break;
            case "This Month":
                repoList.clear();
                callDev = api1.getDevm();
                break;
        }
        callDev.enqueue(new Callback<List<Developers>>() {
            @Override
            public void onResponse(Call<List<Developers>> call, Response<List<Developers>> response) {
                List<Developers> repositories =response.body();
                heross =new String[repositories.size()];
                List<String> hero = new ArrayList<>();
                Log.i("Size: ",String.valueOf(repositories.size()));
                for (int i = 0; i< repositories.size(); i++){
                    hero.add(repositories.get(i).getUsername());
                }
                try {
                    listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,hero));
                    //hero.clear();
                }
                catch (Exception e){
                    Log.i("list",String.valueOf(e));
                }

            }
            @Override
            public void onFailure(Call<List<Developers>> call, Throwable t) {
                Log.i("Error1",t.getMessage().toString());
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    public void Repositories  (View view) {
        btn.setBackgroundColor(Color.parseColor("#ffffffff"));
        btn1.setBackgroundColor(Color.parseColor("#ff00ddff"));
        recyclerView.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
    }

    public void GithubSignin(View view)  {
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }

    public void Developers(View view) {

        btn1.setBackgroundColor(Color.parseColor("#ffffffff"));
        btn.setBackgroundColor(Color.parseColor("#ff00ddff"));
        recyclerView.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);

    }
}
