package com.shul.michalizralevitch.shul2;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by michalizralevitch on 27/07/16.
 */
public class FireBase extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(getApplicationContext());
    }



}
