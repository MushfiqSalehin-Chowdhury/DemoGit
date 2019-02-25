package com.example.demogit;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfilePageActivity extends AppCompatActivity {

    Call<List<UserRepo>> call;
    String heross;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        listView=findViewById(R.id.User_repo_List);


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
        call = api.getOwner();
        call.enqueue(new Callback<List<UserRepo>>() {
            @Override
            public void onResponse(Call<List<UserRepo>> call, Response<List<UserRepo>> response) {
                List<UserRepo> repositories =response.body();
                //heross =new String[repositories.size()];
                List<String> hero = new ArrayList<>();
                Log.i("Size: ",String.valueOf(repositories.size()));
                for (int i = 0; i< repositories.size(); i++){
                    hero.add("Repo Name : " + repositories.get(i).getName())
                    ;
                }
                try {
                    // (new ArrayAdapter<String>(getApplicationContext(),R.layout.dev_list,hero));
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                            (getApplicationContext(), android.R.layout.simple_list_item_1, hero){
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent){
                            /// Get the Item from ListView
                            View view = super.getView(position, convertView, parent);

                            TextView tv = (TextView) view.findViewById(android.R.id.text1);

                            // Set the text size 25 dip for ListView each item
                            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
                            tv.setTextColor(Color.BLUE);


                            // Return the view
                            return view;
                        }
                    };

                    // DataBind ListView with items from ArrayAdapter
                    listView.setAdapter(arrayAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        }
                    });
                    //hero.clear();
                }
                catch (Exception e){
                    Log.i("list",String.valueOf(e));
                }
            }
            @Override
            public void onFailure(Call<List<UserRepo>> call, Throwable t) {
                Log.i("Error1",t.getMessage().toString());
            }
        });





    }


    public void Logout(View view) {
        finish();
        startActivity(new Intent(ProfilePageActivity.this,MainActivity.class));
    }
}
