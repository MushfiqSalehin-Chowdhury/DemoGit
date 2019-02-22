package com.example.demogit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private List <Repository> repoList= new ArrayList<>();
    private RecyclerView recyclerView;
    private RepoAdapter mAdapter;
    private  final int space = 20;
    RecyclerView.LayoutManager mlayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.Recycle);
        RecyclerView.LayoutManager mlayoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"Today", "This Week","This Month"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

    }

    public void GithubSignin(View view) {
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();
        if (item.equals("Today")){
            repoList.clear();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Repo.Base_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            Repo api = retrofit.create(Repo.class);

            Call<List<Repository>> call = api.getRepositoryt();
            call.enqueue(new Callback<List<Repository>>() {
                @Override
                public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                    List<Repository> repositories =response.body();
                    String heross[] =new String[repositories.size()];
                    List<String> builders = new ArrayList<>();
                    for (int i = 0; i< repositories.size(); i++){
                        //heross[i]="Authors :" + repositories.get(i).getAuthor() + repositories.get(i).getDescription();
                        Repository repository = new Repository(repositories.get(i).getAuthor()
                                ,repositories.get(i).getName(),
                                repositories.get(i).getDescription(),
                                repositories.get(i).getLanguage(),
                                repositories.get(i).getLanguageColor(),
                                repositories.get(i).getStars(),
                                repositories.get(i).getForks(),
                                repositories.get(i).getCurrentPeriodStars());
                        repoList.add(repository);
                /*for (int j=0 ; j< repositories.get(i).getBuiltBy().size(); j++){
                    }*/
                        //Log.i("size : ",builders.toString() );
                        //  builders.clear();
                    }
                    mAdapter=new RepoAdapter(repoList);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onFailure(Call<List<Repository>> call, Throwable t) {

                    Log.i("Errorr",t.getMessage().toString());

                }
            });
        }
        else if (item.equals("This Week")){
            repoList.clear();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Repo.Base_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            Repo api = retrofit.create(Repo.class);

            Call<List<Repository>> call = api.getRepositoryw();
            call.enqueue(new Callback<List<Repository>>() {
                @Override
                public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                    List<Repository> repositories =response.body();
                    String heross[] =new String[repositories.size()];
                    List<String> builders = new ArrayList<>();
                    for (int i = 0; i< repositories.size(); i++){
                        //heross[i]="Authors :" + repositories.get(i).getAuthor() + repositories.get(i).getDescription();
                        Repository repository = new Repository(repositories.get(i).getAuthor()
                                ,repositories.get(i).getName(),
                                repositories.get(i).getDescription(),
                                repositories.get(i).getLanguage(),
                                repositories.get(i).getLanguageColor(),
                                repositories.get(i).getStars(),
                                repositories.get(i).getForks(),
                                repositories.get(i).getCurrentPeriodStars());
                        repoList.add(repository);
                /*for (int j=0 ; j< repositories.get(i).getBuiltBy().size(); j++){
                    }*/
                        //Log.i("size : ",builders.toString() );
                        //  builders.clear();
                    }
                    mAdapter=new RepoAdapter(repoList);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onFailure(Call<List<Repository>> call, Throwable t) {

                    Log.i("Errorr",t.getMessage().toString());

                }
            });
        }
        else{
            repoList.clear();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Repo.Base_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            Repo api = retrofit.create(Repo.class);

            Call<List<Repository>> call = api.getRepositorym();
            call.enqueue(new Callback<List<Repository>>() {
                @Override
                public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                    List<Repository> repositories =response.body();
                    String heross[] =new String[repositories.size()];
                    List<String> builders = new ArrayList<>();
                    for (int i = 0; i< repositories.size(); i++){
                        //heross[i]="Authors :" + repositories.get(i).getAuthor() + repositories.get(i).getDescription();
                        Repository repository = new Repository(repositories.get(i).getAuthor()
                                ,repositories.get(i).getName(),
                                repositories.get(i).getDescription(),
                                repositories.get(i).getLanguage(),
                                repositories.get(i).getLanguageColor(),
                                repositories.get(i).getStars(),
                                repositories.get(i).getForks(),
                                repositories.get(i).getCurrentPeriodStars());
                        repoList.add(repository);
                /*for (int j=0 ; j< repositories.get(i).getBuiltBy().size(); j++){
                    }*/
                        //Log.i("size : ",builders.toString() );
                        //  builders.clear();
                    }
                    mAdapter=new RepoAdapter(repoList);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onFailure(Call<List<Repository>> call, Throwable t) {

                    Log.i("Errorr",t.getMessage().toString());

                }
            });
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
