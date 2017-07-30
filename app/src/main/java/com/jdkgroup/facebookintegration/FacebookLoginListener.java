package com.jdkgroup.facebookintegration;

public interface FacebookLoginListener {
    void onFbSignInFail(String errorMessage);
    void onFbSignInSuccess(FacebookModel facebookModel);
    void onFBSignOut();
}
