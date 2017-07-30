package com.jdkgroup.facebookintegration;

public interface FacebookLoginListener {
    void onFbSignInFail(String errorMessage);
    void onFbSignInSuccess(FacebookLoginModel facebookLoginModel);
    void onFBSignOut();
}
