package com.jdkgroup.googleintegration;

public interface GoogleLoginListener {
  void onGoogleAuthSignIn(String authToken, String userId);
  void onGoogleAuthSignInFailed(String errorMessage);
  void onGoogleAuthSignOut();
}
