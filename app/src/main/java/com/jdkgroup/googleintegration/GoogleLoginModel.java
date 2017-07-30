package com.jdkgroup.googleintegration;

import android.net.Uri;

public class GoogleLoginModel
{
    private String authtoken, id, name, email;
    private Uri personPhoto;

    public GoogleLoginModel(String authtoken, String id, String name, String email, Uri personPhoto) {
        this.authtoken = authtoken;
        this.id = id;
        this.name = name;
        this.email = email;
        this.personPhoto = personPhoto;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Uri getProfilePicture() {
        return personPhoto;
    }

    public void setProfilePicture(Uri profilePicture) {
        this.personPhoto = profilePicture;
    }
}
