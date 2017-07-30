package com.jdkgroup.socialintegration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jdkgroup.splashscreen.MySplashScreen;
import com.jdkgroup.splashscreen.TimeExecuteListener;

public class SplashScreen extends AppCompatActivity implements TimeExecuteListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MySplashScreen mySplashScreen = new MySplashScreen(this);
        setContentView(mySplashScreen);
        mySplashScreen.setImageInImageView(R.drawable.ic_facebook);
        mySplashScreen.setTimeExecuteListener(this,5000);
    }

    @Override
    public void onExecute() {
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
