package com.jitendera.jitenderaarya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jitendera.jitenderaarya.Model.Articles;
import com.jitendera.jitenderaarya.Model.Headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NewsInWrittenForm extends AppCompatActivity {

    RecyclerView recyclerView;

    com.jitendera.jitenderaarya.Adapter adapter;
    final  String API_KEY="d2df6a66321b4346b86d9e139b26100c";
    Button button;
    ImageButton floatingActionButton;
    List<Articles> articles=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_in_written_form);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        button=findViewById(R.id.refreshButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final String country=getCountry();
       // fetchJSON(country,API_KEY);
        floatingActionButton=(ImageButton)findViewById(R.id.floating);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewsInWrittenForm.this, "Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        retrieveJson(country,API_KEY);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveJson(country,API_KEY);
            }
        });

    }

   /* private void fetchJSON(String country, String api_key) {
        Call<Headlines> call = ApiClient.getInstance().getApi().getHeadlines(country,API_KEY);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful()&& response.body().getArticles()!=null){
                    articles.clear();
                    articles=response.body().getArticles();
                    adapter = new com.jitendera.jitenderaarya.Adapter(NewsInWrittenForm.this,articles);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Toast.makeText(NewsInWrittenForm.this,"Please Check your InterNet Connection",Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    public  void retrieveJson(String country,String apiKey)
    {
        Call<Headlines> call=ApiClient.getInstance().getApi().getHeadlines(country,apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles()!=null){
                    articles.clear();
                    articles=response.body().getArticles();
                    adapter = new com.jitendera.jitenderaarya.Adapter(NewsInWrittenForm.this,articles);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {

                Toast.makeText(NewsInWrittenForm.this,"Please Check your InterNet Connection",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public String getCountry()
    {
        Locale locale=Locale.getDefault();
        String country=locale.getCountry();
        return country.toLowerCase();
    }
}
