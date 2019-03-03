package com.example.demogit.Activities;

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
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demogit.Api.User;
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

public class ProfilePageActivity extends AppCompatActivity {

    Call<List<UserRepo>> call;
    String heross,text;
    ListView listView;
    TextView userName;
    ProgressBar progressBar;
   // EditText email;
    //public AutoCompleteTextView mEmailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        listView=findViewById(R.id.User_repo_List);
        progressBar=findViewById(R.id.progress);
        progressBar.setProgress(2);
        userName= findViewById(R.id.UserName);
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

        Retrofit retrofit = new Retrofit.Builder().client(okHttpClientBuilder.build()).baseUrl(User.Base_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        User api = retrofit.create(User.class);
        Intent i = getIntent();
        text = i.getStringExtra ( "TextBox");
        userName.setText(text);
        call = api.getOwner(text);
       // Log.i("nameee: ",email.getText().toString());
        call.enqueue(new Callback<List<UserRepo>>() {
            @Override
            public void onResponse(Call<List<UserRepo>> call, Response<List<UserRepo>> response) {
                List<UserRepo> repositories =response.body();
                //heross =new String[repositories.size()];
                List<String> repo = new ArrayList<>();
                for (int i = 0; i< repositories.size(); i++){
                    repo.add("Repo Name : " + repositories.get(i).getName())
                    ;
                }
                try {
                    // (new ArrayAdapter<String>(getApplicationContext(),R.layout.dev_list,hero));
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                            (getApplicationContext(), android.R.layout.simple_list_item_1, repo){
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

                    Thread.sleep(3000);
                    progressBar.setVisibility(View.GONE);
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
                    Log.i("listttt",String.valueOf(e));
                }
            }
            @Override
            public void onFailure(Call<List<UserRepo>> call, Throwable t) {
                try{
                    Log.i("Error1",t.getMessage().toString());
                    Toast.makeText(ProfilePageActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Log.i("er",String.valueOf(e));
                }
            }
        });

    }
    public void Logout(View view) {
        finish();
        startActivity(new Intent(ProfilePageActivity.this, MainActivity.class));
    }

    public void gitBattle(View view) {
        Intent intent = new Intent (this,BattleActivity.class );
        intent.putExtra ( "TextBox",text );
        startActivity(intent);
    }
}
