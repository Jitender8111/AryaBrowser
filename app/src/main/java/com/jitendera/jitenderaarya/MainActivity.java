package com.jitendera.jitenderaarya;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
//import android.webkit.URLUtil;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btntvForTab;
    ImageButton btnrefresh;
    ImageButton btngo,btnmic,btnBookmarks;
    EditText edttexturl;
    WebView webViewNew;
    ProgressBar Webprogressbar;
            String myCurrentUrl;
    BottomNavigationView navigationView;
    int counter=0;
    MyDbHandler dbHandler;
    myDbHandlerBook dbHandlerbook;
     private int tabCounting =1;
    String urss;
    int desktopmodesValue =0;
    public String geturi="",gettitle="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbars);
        btngo = findViewById(R.id.btngo);
        btnmic = findViewById(R.id.btnmic);
        btnBookmarks = findViewById(R.id.btnAddtobookmarks);
        btnrefresh = findViewById(R.id.btnrefresh);
        webViewNew = findViewById(R.id.webViewNew);
        Webprogressbar = findViewById(R.id.Webprogressbar);
        edttexturl = findViewById(R.id.edttexturl);
        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        edttexturl.setOnEditorActionListener(editorListener);
        btntvForTab = findViewById(R.id.btnTextViewForTab);


        dbHandler= new MyDbHandler(this,null,null,1);
        dbHandlerbook=new myDbHandlerBook(this,null,null,1);

        saveData();

        //------------------------------------webViewClient--------------------------------------------------------------------------

        //------------------------------------webViewClient--------------------------------------------------------------------------

        //------------------------------------webViewClient--------------------------------------------------------------------------



        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        // ----------object of check connection method and oyther method -------------------------------------------------------

        checkConnection();


        //-----------------end of method-----------------------------------------------------------------------------------------


        //--------------------------------------------Bottom Navigation View -------------------------------------------------------


           navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
               @Override
               public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                   switch(menuItem.getItemId()){

                       case R.id.navigation_Back:
                           onBackPressed();
                           break;

                       case  R.id.navigation_Forward:
                           onForwardPressed();
                           break;

                       case R.id.navigation_dashboard:
                           Intent popupwindow = new Intent(MainActivity.this,WindowPopup.class);
                           startActivity(popupwindow);

                           break;
                       case R.id.navigation_home:
                           Intent its = new Intent(MainActivity.this,MainActivity.class);
                           startActivity(its);
                           finish();
                           break;

                       case R.id.navigation_download:
                           Intent downld = new Intent(MainActivity.this,DownloadWithPauseResmueNew.class);
                          startActivity(downld);
                           break;


                   }


                   return false;
               }
           });






        //------------------------------------Using For searchView List Automatically arriving when Search--------------------------------
/*

        edttexturl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.performClick();
                final int Drawable_Right =2;
                if (event.getAction() ==MotionEvent.ACTION_UP){
                   */
/* if (event.getRawX() >= (edttexturl.getRight() - edttexturl.getCompoundDrawables()[Drawable_Right].getBounds().width()))*//*

                        startActivity(new Intent(MainActivity.this,SearchAutomaticallyforUrl.class));

                }
                return true;
            }
        });
*/

        //----------------------end of Search----------------------------------------------------------------------------------------------



        //----------------------------------------------End of bottom view----------------------------------------------------------

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
       /*AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);*/


     btntvForTab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             Toast.makeText(MainActivity.this, "New Tab Open", Toast.LENGTH_SHORT).show();
             tabCounting++;
             btntvForTab.setText(""+tabCounting);

             Intent sendurl = new Intent(view.getContext(),detailsDownloadRightClicks.class);
             Intent sendurlss = new Intent(view.getContext(),DownloadDropeDownPage.class);
             sendurl.putExtra("myurl",geturi);
             sendurl.putExtra("myurll",gettitle);
             sendurlss.putExtra("myurll",gettitle);
             startActivity(sendurl);



         }
     });

        edttexturl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edttexturl.selectAll();
            }
        });
        btnBookmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBookPressed();
                Toast.makeText(MainActivity.this, "Page Added in Bookmarks", Toast.LENGTH_SHORT).show();
            }
        });
        btnmic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent micintent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                micintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                micintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());


         // if mic or voice recognization is not support check by this code
                if (micintent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(micintent,10);
                    saveData();
                }else
                {
                    Toast.makeText(MainActivity.this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
                }


            }

        });

        webViewNew.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(final String url, final String userAgent, String contentDisposition, String mimetype, long contentLength) {
                //Checking runtime permission for devices above Marshmallow.
                saveData();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                        Log.v("TAG", "Permission is granted");
                        downloadDialog(url, userAgent, contentDisposition, mimetype);
                        saveData();
                    } else {

                        Log.v("TAG", "Permission is revoked");
                        //requesting permissions.
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                    }
                } else {
                    //Code for devices below API 23 or Marshmallow
                    Log.v("TAG", "Permission is granted");
                    downloadDialog(url, userAgent, contentDisposition, mimetype);

                }
            }
        });

       btngo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT).show();

               String urls = edttexturl.getText().toString();

              /* if (!urls.startsWith("http://") || !urls.startsWith("https://") && !urls.endsWith(".com")){
                   urls=urls;
               }*/

               if(!urls.startsWith("http://"))
               {
                   urls ="http://www.google.com/search?q=" +urls;
               }


               saveData();
               //-------------------------------------end-----------------------------------------------------------------------------------

               webViewNew = (WebView)findViewById(R.id.webViewNew);
              /* webViewNew.getSettings().setDomStorageEnabled(true);*/
               webViewNew.setWebViewClient(new WebViewClient(){

                   @Override
                   public void onPageStarted(WebView view, String urls, Bitmap favicon) {
                       Webprogressbar.setVisibility(View.VISIBLE);
                       edttexturl.setText(urls);
                       //saveData();
                       super.onPageStarted(view, urls, favicon);

                   }

                   @Override
                   public void onPageFinished(WebView view, String url) {
                       Webprogressbar.setVisibility(view.GONE);

                       myCurrentUrl = url;

                       super.onPageFinished(view, url);
                       saveData();
                   }
               }); //this line used for own page load if we cant write so the page is load external page like chrome and uc browser
               webViewNew.loadUrl(urls);

               WebSettings webSetting = webViewNew.getSettings();
               webSetting.setJavaScriptEnabled(true);


           }
       });
  //---------------it is not refresh button it is clear edit text in one click button--------------------------------
       btnrefresh.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

                   edttexturl.selectAll();
                   edttexturl.setText("");

          // use for open keypad when clear all text from edittext using this button
               InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
               imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                edttexturl.requestFocus();


           }
       });
 //--------------------------------------------------------------------------------------------------------------------

        //-------------------------------------Web View Client or Page finish etc-------------------------------------------------------------

        webViewNew.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Webprogressbar.setVisibility(View.VISIBLE);
                edttexturl.setText(url);
                saveData();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Webprogressbar.setVisibility(view.GONE);
                myCurrentUrl = url;
                saveData();
                super.onPageFinished(view, url);

            }
        });


        // --------------------------------------------get Intent From Bookmarks and history-----------------------------------------------

        webViewNew.loadUrl("http://www.google.com");

        if(getIntent().getStringExtra("urls")!=null){
            webViewNew.loadUrl(getIntent().getStringExtra("urls"));

        }
        //---------------------------------------end of bookmarks-------------------------------------------------------------------

        WebSettings webSetting = webViewNew.getSettings();
        webSetting.setJavaScriptEnabled(true);

       /* webViewNew.getSettings().setDomStorageEnabled(true);*/
        Webprogressbar.setProgress(100);
        webViewNew.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Webprogressbar.setProgress(newProgress);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });

        //--------------------------------------------------------------------------------------------------



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toptoolbarthreedot,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {

            case R.id.history:
                Intent his = new Intent(MainActivity.this,history.class);
                startActivity(his);
                break;

            case R.id.ScanBarCode:
                Intent scan=new Intent(MainActivity.this,ReaderActivityForScanQrCode.class);
                startActivity(scan);
                break;

            case R.id.setting:
                Intent setting = new Intent(MainActivity.this,BrowserSettings.class);
                startActivity(setting);

                break;
            case R.id.helpAndFeedback:
                break;
            case R.id.favouritestar:
                Intent bomarks  = new Intent(MainActivity.this,bookmarks.class);
                startActivity(bomarks);
                break;
            case R.id.download:
                 Intent dd =new Intent(MainActivity.this,DownloadWithPauseResmueNew.class);
              startActivity(dd);
                 break;
            case R.id.DesktopMode:


                if (desktopmodesValue==0){
                    setDesktopMode(webViewNew,true);
                    desktopmodesValue=1;
                }else{
                    setDesktopMode(webViewNew,false);
                    desktopmodesValue=0;
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        webViewNew.saveState(outState);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        webViewNew.restoreState(savedInstanceState);
        saveData();
    }

    private TextView.OnEditorActionListener editorListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch (actionId){
                case EditorInfo.IME_ACTION_GO:
                    Toast.makeText(MainActivity.this, "loading...", Toast.LENGTH_SHORT).show();


                    String st =edttexturl.getText().toString();
                    if (st.startsWith("http://www.")&& st.endsWith(".com")){
                         st = st;
                    }
                    else if (!st.startsWith("http://")&& !st.endsWith(".com"))
                    {
                       /* st="http://"+st;*/
                        st ="http://www.google.com/search?q=" +st;
                    }else if (!st.startsWith("https://")&& !st.endsWith(".com")){
                        st="https://"+st;
                    }else{
                        st ="http://www.google.com/search?q=" +st;
                    }


                    webViewNew.loadUrl(st);
                    saveData();

                    /* finish();*/
                    prog();


            }

            return false;
        }
    };



    @Override
    public void onBackPressed() {

        if (webViewNew.canGoBack()){
            webViewNew.goBack();


        }else {
            /* super.onBackPressed();*/

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Are you sure you want to Exit?");

            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    MainActivity.super.onBackPressed();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }


    }

    public void onForwardPressed() {
        if (webViewNew.canGoForward()){
            webViewNew.goForward();
            saveData();
        }else {
            Toast.makeText(this, "Can't Go Further", Toast.LENGTH_SHORT).show();
        }
    }


 //--------------------------------------------go button of keybord processing----------------------------------------------

    /*private TextView.OnEditorActionListener editorListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch (actionId){
                case EditorInfo.IME_ACTION_GO:
                    Toast.makeText(MainActivity.this, "loading...", Toast.LENGTH_SHORT).show();

                    String urll = edttexturl.getText().toString();

                    if(!urll.startsWith("http://")  )
                    {
                        urll ="http://www.google.com/search?q="+urll;

                    }

                     prog();

                    webViewNew.getSettings().setLoadsImagesAutomatically(true);
                    webViewNew.getSettings().setJavaScriptEnabled(true);
                    webViewNew.loadUrl(urll);


            }

            return false;
        }
    };*/



//--------------------------------------------Top Toolbar Navigation---------------------------------------------------------

    public void RefreshMethod(MenuItem item) {

       webViewNew.reload();



    }
    private void onBookPressed()
    {
        Websites web=new Websites(webViewNew.getUrl());
        dbHandlerbook.addUrl(web);
        saveData();
    }
    private void saveData()
    {
        Websites webv=new Websites(webViewNew.getUrl());
        dbHandler.addUrl(webv);
    }


    public void aboutDeveloperMethod(MenuItem item) {

       Intent develpoerIntent = new Intent(MainActivity.this,AboutDevelopers.class);
        startActivity(develpoerIntent);
    }

    public void downloadMethods(MenuItem item) {

     Intent dd =new Intent(MainActivity.this,DownloadWithPauseResmueNew.class);
        startActivity(dd);
    }




  //--------------------------------check connection in  android mobile using this method----------------------------------------

    //------------------------------Desktop mode -------------------------------------------

    public void setDesktopMode(WebView wbview,boolean enabled){

         String newUesrAgent = webViewNew.getSettings().getUserAgentString();
         if (enabled){

             try{

                 String ua = webViewNew.getSettings().getUserAgentString();
                 String androidDosString = webViewNew.getSettings().getUserAgentString().substring(ua.indexOf("("),ua.indexOf(")")+1);
                 newUesrAgent = webViewNew.getSettings().getUserAgentString().replace(androidDosString,"X11; Linux x86_64");

             }catch(Exception e){
                 e.printStackTrace();
             }
         }else{
             newUesrAgent=null;
         }
         webViewNew.getSettings().setUserAgentString(newUesrAgent);
         webViewNew.getSettings().setUseWideViewPort(enabled);
         webViewNew.getSettings().setLoadWithOverviewMode(enabled);
         webViewNew.reload();

    }

    //---------------------------------End of desktop Mode-------------------------------------------


    public  void checkConnection(){

        ConnectivityManager mngr = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork =  mngr.getActiveNetworkInfo();
        if(null!=activeNetwork){

            if(activeNetwork.getType()==ConnectivityManager.TYPE_WIFI){
                Toast.makeText(this, "Wifi Enable", Toast.LENGTH_SHORT).show();

            }
            else if (activeNetwork.getType()==ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(this, "Mobile Data Enable", Toast.LENGTH_SHORT).show();

            }
        }
        else{
            Toast.makeText(this, "Please Check Your Internet Connection", Toast.LENGTH_LONG).show();

        }
    }

  //--------------------------------------End of check connection----------------------------------------------------------------


    //---------------------------------progressbar method-------------------------------------------

    public  void prog() {
        Webprogressbar = findViewById(R.id.Webprogressbar);

        final Timer t = new Timer();
        final TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter+=2;
                Webprogressbar.setProgress(counter);
                if(counter==100){
                    t.cancel();
                }
            }
        };
        t.schedule(tt,0,50);

    }
//--------------------this method used for voice recognization----------------------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:
                if (resultCode == RESULT_OK && data != null){
                    ArrayList<String> result =data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edttexturl.setText(result.get(0));

                  //----------------------------------------automatic run after voice recognise------------------------

                    Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT).show();

                    String urls = edttexturl.getText().toString();

              /* if (!urls.startsWith("http://") || !urls.startsWith("https://") && !urls.endsWith(".com")){
                   urls=urls;
               }*/

                    if(!urls.startsWith("http://"))
                    {
                        urls ="http://www.google.com/search?q=" +urls;
                    }



                    //-------------------------------------end-----------------------------------------------------------------------------------

                    webViewNew = (WebView)findViewById(R.id.webViewNew);
                   /* webViewNew.getSettings().setDomStorageEnabled(true);*/
                    webViewNew.setWebViewClient(new WebViewClient(){

                        @Override
                        public void onPageStarted(WebView view, String urls, Bitmap favicon) {
                            Webprogressbar.setVisibility(View.VISIBLE);
                            edttexturl.setText(urls);
                            super.onPageStarted(view, urls, favicon);
                        }

                        @Override
                        public void onPageFinished(WebView view, String url) {
                            Webprogressbar.setVisibility(view.GONE);

                            myCurrentUrl = url;

                            super.onPageFinished(view, url);
                        }
                    }); //this line used for own page load if we cant write so the page is load external page like chrome and uc browser
                    webViewNew.loadUrl(urls);

                    WebSettings webSetting = webViewNew.getSettings();
                    webSetting.setJavaScriptEnabled(true);
                    saveData();

                  //---------------------------------------------------------------------------------------------------
                }
                break;
        }
    }

    //downloadDialog Method

    public void downloadDialog(final String url, final String userAgent, String contentDisposition, String mimetype) {
        //getting filename from url.
         final String filename = URLUtil.guessFileName(url, contentDisposition, mimetype);
        //alertdialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

     View vvr = getLayoutInflater().inflate(R.layout.custome_dialog_for_download,null);
        Button buttonYes = vvr.findViewById(R.id.Button_Yes);
        Button buttonNo = vvr.findViewById(R.id.Button_No);
        EditText EditTextFileName = vvr.findViewById(R.id.EditTextFile_Name);
        EditText EditTextFileUrl = vvr.findViewById(R.id.EditTextFile_Url);

        builder.setView(vvr);
        final  AlertDialog alertDialogs = builder.create();
        alertDialogs.setCancelable(false);

        EditTextFileName.setText(filename);
        EditTextFileUrl.setText(url);
        final String ur= EditTextFileUrl.getText().toString();
        final String fi = EditTextFileName.getText().toString();

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentUrls = new Intent(view.getContext(),DownloadWithPauseResmueNew.class);
                intentUrls.putExtra("urlss",ur);
                intentUrls.putExtra("filenames",fi);

                startActivity(intentUrls);

                alertDialogs.dismiss();

            }
        });
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                alertDialogs.dismiss();

            }
        });


      /*  //title of alertdialog
        builder.setTitle(R.string.download_title);
        //message of alertdialog
        builder.setMessage(getString(R.string.download_file) + ' ' + filename);



        //if Yes button clicks.
        saveData();*//*
      *//*  builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //DownloadManager.Request created with url.
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                //cookie
                String cookie = CookieManager.getInstance().getCookie(url);
                //Add cookie and User-Agent to request
                request.addRequestHeader("Cookie", cookie);
                request.addRequestHeader("User-Agent", userAgent);
                //file scanned by MediaScannar
                request.allowScanningByMediaScanner();
                //Download is visible and its progress, after completion too.
                String urls = url;
                String title = filename;
                sendurl(urls,title);


                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                //DownloadManager created
                DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                //Saving files in Download folder
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
                //download enqued
                downloadManager.enqueue(request);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //cancel the dialog if Cancel clicks

                String urls = url;
                String title = filename;
                sendurl(urls,title);
                dialog.cancel();
                webViewNew.goBack();



            }

        });*//*
        //alertdialog shows.*/
        alertDialogs.show();

    }
     public void sendurl(String geturl, String title){
             geturi = geturl;
             gettitle = title;

     }

}

