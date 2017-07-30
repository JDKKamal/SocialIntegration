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

import com.google.gson.Gson;
import com.jdkgroup.facebookintegration.FacebookLoginHelper;
import com.jdkgroup.facebookintegration.FacebookLoginListener;
import com.jdkgroup.facebookintegration.FacebookModel;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity implements FacebookLoginListener, View.OnClickListener {

    private AppCompatActivity appCompatActivity;

    private Gson gson;

    private FacebookLoginHelper facebookLoginHelper;

    private AppCompatImageView ivAppFacebookLogin, ivAppGoogleLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appCompatActivity = this;
        gson = new Gson();

        //getKeyHash();
        facebookLoginHelper = new FacebookLoginHelper(this);

        ivAppFacebookLogin = (AppCompatImageView) findViewById(R.id.ivAppFacebookLogin);
        ivAppGoogleLogin = (AppCompatImageView) findViewById(R.id.ivAppGoogleLogin);

        ivAppFacebookLogin.setOnClickListener(this);
        ivAppGoogleLogin.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookLoginHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onFbSignInFail(String errorMessage) {

    }

    @Override
    public void onFbSignInSuccess(FacebookModel facebookModel) {
        System.out.println("Tag" + "Facebook data" + gson.toJson(facebookModel));
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

                break;
        }
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
