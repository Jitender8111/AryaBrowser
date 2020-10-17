package com.jitendera.jitenderaarya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class history extends AppCompatActivity {

    MyDbHandler dbHandler=new MyDbHandler(this,null,null,1);
    WebView mywebview;
    //android.app.ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        final List<String> sites=dbHandler.databaseToString();
        if(sites.size()>0){
            ArrayAdapter myadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sites);
            ListView mylist=(ListView)findViewById(R.id.listview);
            mylist.setAdapter(myadapter);


            mylist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                    /*String url=sites.get(i);
                    Toast.makeText(history.this, "item Selected : "+url +" deleted", Toast.LENGTH_LONG).show();
                        dbHandler.deleteUrl(url);*/

                    return false;
                }
            });

            mylist.setOnItemClickListener(

                    new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String url=sites.get(position);
                            Intent intent = new Intent(view.getContext(),MainActivity.class);
                            intent.putExtra("urls",url);
                            startActivity(intent);
                            finish();
                        }
                    }
            );
        }
    }
}
