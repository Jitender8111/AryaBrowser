package com.jitendera.jitenderaarya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchAutomaticallyforUrl extends AppCompatActivity {

    SearchView serachViewss;
    ListView ListViewForSearcView;
    ArrayList<String> listForSrch = new ArrayList<>();
    ArrayAdapter arrayAdapterForsrch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_automaticallyfor_url);

         serachViewss = findViewById(R.id.SerachViewss);
         ListViewForSearcView = findViewById(R.id.ListViewForSearcView);


        listForSrch.add("Moviesfilx");
        listForSrch.add("filmygod");
        listForSrch.add("9xmovies");
        listForSrch.add("bolly4u");
        listForSrch.add("sarkariresult");

        arrayAdapterForsrch = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listForSrch);
        ListViewForSearcView.setAdapter(arrayAdapterForsrch);

        ListViewForSearcView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = ListViewForSearcView.getAdapter().getItem(i);
                String itemstr = String.valueOf(item);
                ValidateUrl(itemstr);

            }
        });



        serachViewss.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (listForSrch.contains(query)){
                    arrayAdapterForsrch.getFilter().filter(query);
                    ValidateUrl(query);

                }else{
                    Toast.makeText(SearchAutomaticallyforUrl.this, "No Text Found", Toast.LENGTH_SHORT).show();
                }
                ValidateUrl(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (listForSrch.isEmpty()){

                }else{
                    arrayAdapterForsrch.getFilter().filter(newText);
                }
                return false;
            }
        });


    }

    private void ValidateUrl(String url){

        String fix = "https://www.google.com/search?q=";
        if (!url.startsWith("http://") && !url.startsWith("https://") && !url.endsWith(".com")){
            url=fix+url;
        }
        if (url.endsWith(".com") || url.endsWith(".as") || url.endsWith(".uk") || url.endsWith(".org") || url.endsWith(".in") || url.endsWith(".net"))
        {
            if (!url.startsWith("http://") && !url.startsWith("https://")){
                url ="https://"+url;
            }
        }
        Intent iit = new Intent(SearchAutomaticallyforUrl.this,MainActivity.class);
        Bundle b = new Bundle();
        b.putString("stuff",url);

       iit.putExtras(b);
        startActivity(iit);



    }


}
