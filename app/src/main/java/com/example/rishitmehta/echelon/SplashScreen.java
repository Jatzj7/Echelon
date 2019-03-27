package com.example.rishitmehta.echelon;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class SplashScreen extends AppCompatActivity {

    private GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

     gifImageView = (GifImageView)findViewById(R.id.gifimageview);

     try{
         InputStream inputStream = getAssets().open("splash_screen.gif");
         byte[] bytes = IOUtils.toByteArray(inputStream);
         gifImageView.setBytes(bytes);
         gifImageView.startAnimation();

     } catch (IOException e) {

     }

     new Handler().postDelayed(new Runnable() {
         @Override
         public void run() {
             SplashScreen.this.startActivity(new Intent(SplashScreen.this,LoginPage.class));
             SplashScreen.this.finish();
         }
     },2100);

    }
}
