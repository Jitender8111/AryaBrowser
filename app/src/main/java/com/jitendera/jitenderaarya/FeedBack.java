package com.jitendera.jitenderaarya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FeedBack extends AppCompatActivity {

    Button btnSendfeedback,btnCancelfeedback;
   private  EditText edtTextto,edtSubject,edtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        btnSendfeedback = findViewById(R.id.btnsendfeeddback);
        btnCancelfeedback = findViewById(R.id.btnCancelfeedback);
        edtTextto = findViewById(R.id.edtTextTo);
        edtSubject = findViewById(R.id.edtSubject);
        edtMessage = findViewById(R.id.edtMessage);

        DisplayMetrics dm = new DisplayMetrics();   // this object use to show the resolution of current activity or window
        getWindowManager().getDefaultDisplay().getMetrics(dm);   // get resolutions and pass in this method

        int width = dm.widthPixels;
        int height = dm.heightPixels;


        getWindow().setLayout( (int) (width*.9),(int) (height*.7));  // .6 means height in 60% and .8 means width is 80% show on screen


        btnSendfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();

                 /* String subject ="Fast And Secure Browser";
                String message = "Arya Browser";
                Intent mail = new Intent(Intent.ACTION_SENDTO,Uri.fromParts("Mail To","jaibharatmata811@gmail.com",null));
                mail.putExtra(Intent.EXTRA_SUBJECT,subject);
                mail.putExtra(Intent.EXTRA_TEXT,message);
                startActivity(Intent.createChooser(mail,"kumarjitender811@gmail.com"));*/



            }
        });

        btnCancelfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(FeedBack.this,WindowPopup.class);
                startActivity(i);
                finish();
            }
        });




    }
    private void sendMail(){

        String recipientList = edtTextto.getText().toString();
        String[] recipient = recipientList.split(",");

        String subject = edtSubject.getText().toString();
        String message = edtMessage.getText().toString();

        Intent  intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recipient);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"choose an email client"));


    }
}
