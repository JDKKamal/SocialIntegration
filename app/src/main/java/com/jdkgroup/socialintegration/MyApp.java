package com.jdkgroup.socialintegration;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

public class MyApp extends MultiDexApplication {
    private static MyApp context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        MultiDex.install(this);
    }
}
