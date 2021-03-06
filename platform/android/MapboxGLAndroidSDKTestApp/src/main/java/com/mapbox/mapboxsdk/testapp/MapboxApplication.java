package com.mapbox.mapboxsdk.testapp;

import android.app.Application;
import android.os.StrictMode;

import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.squareup.leakcanary.LeakCanary;

public class MapboxApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MapboxAccountManager.start(getApplicationContext(), getString(R.string.mapbox_access_token));
        LeakCanary.install(this);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }
}
