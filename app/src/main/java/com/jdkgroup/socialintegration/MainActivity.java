package com.jdkgroup.socialintegration;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jdkgroup.facebookintegration.FacebookLoginHelper;
import com.jdkgroup.facebookintegration.FacebookLoginListener;
import com.jdkgroup.facebookintegration.FacebookLoginModel;
import com.jdkgroup.googleintegration.GoogleLoginHelper;
import com.jdkgroup.googleintegration.GoogleLoginListener;
import com.jdkgroup.googleintegration.GoogleLoginModel;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity implements FacebookLoginListener, GoogleLoginListener, View.OnClickListener {

    private AppCompatActivity appCompatActivity;

    private Gson gson;

    private FacebookLoginHelper facebookLoginHelper;
    private GoogleLoginHelper googleLoginHelper;

    private AppCompatImageView ivAppFacebookLogin, ivAppGoogleLogin;

    /*
    *  https://developers.google.com/identity/sign-in/android/start-integrating
    *  GOOGLE LOGIN DEVELOPERS KEY
    */

    /*
    * TODO
    * EMULATOR GOOGLE LOGIN TOKEN NOT GET ONLY SUPPORT PAY STORE AVAILABLE
    * WHEN RELEASE KEY GENERATE THEN KEYSTORE BOTH FACEBOOK AND GOOGLE
    *
    * */

    /* TODO RELEASE APK KEY GENERATE PUT build.gradle
    signingConfigs {
        release {
            storeFile file("KEY STORE PATH HERE")
            storePassword ""
            keyAlias ""
            keyPassword ""
        }
    }

    buildTypes {
        release {
            signingConfig signingConfigs.release
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appCompatActivity = this;
        gson = new Gson();

        //getKeyHash();
        facebookLoginHelper = new FacebookLoginHelper(this);
        googleLoginHelper = new GoogleLoginHelper(this, appCompatActivity, "Client_id get detail google-service.json");

        ivAppFacebookLogin = (AppCompatImageView) findViewById(R.id.ivAppFacebookLogin);
        ivAppGoogleLogin = (AppCompatImageView) findViewById(R.id.ivAppGoogleLogin);

        ivAppFacebookLogin.setOnClickListener(this);
        ivAppGoogleLogin.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookLoginHelper.onActivityResult(requestCode, resultCode, data);
        googleLoginHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onFbSignInFail(String errorMessage) {
        Toast.makeText(appCompatActivity, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFbSignInSuccess(FacebookLoginModel facebookLoginModel) {
        System.out.println("Tag" + "Facebook login data" + gson.toJson(facebookLoginModel));
    }

    @Override
    public void onFBSignOut() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivAppFacebookLogin:
                facebookLoginHelper.performSignOut(); //FACEBOOK LOGOUT

                facebookLoginHelper.performSignIn(this);
                break;

            case R.id.ivAppGoogleLogin:
                googleLoginHelper.performSignIn(this);
                break;
        }
    }

    @Override
    public void onGoogleAuthSignIn(GoogleLoginModel googleLoginModel) {
        System.out.println("Tag" + "Google login data" + gson.toJson(googleLoginModel));
    }

    @Override
    public void onGoogleAuthSignInFailed(String errorMessage) {
        Toast.makeText(appCompatActivity, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGoogleAuthSignOut() {

    }

    private void getKeyHash() {
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("package name get AndroidManifest.xml (com.jdkgroup.socialintegration)", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("Hash key facebook", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (Exception e) {
            Log.e("no such an algorithm", e.toString());
        }
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
