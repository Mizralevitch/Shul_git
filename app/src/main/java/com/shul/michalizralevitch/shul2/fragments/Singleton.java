package com.shul.michalizralevitch.shul2.fragments;

import android.app.Application;

import com.firebase.client.Firebase;
import com.shul.michalizralevitch.shul2.BlogPost;

/**
 * Created by michalizralevitch on 03/08/16.
 */
public class Singleton extends Application{

    //Class to save the token from Firebase

    private String str;
    private BlogPost blogPost;
    private Boolean manager = false;

    private static Singleton singleInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(getApplicationContext());
        singleInstance = this;
    }
    public static Singleton getSingleInstance(){
        if (singleInstance==null){
            singleInstance=new Singleton();
        }
        return singleInstance;
    }

    public void setBlogPost(BlogPost blogPost) {
        this.blogPost = blogPost;
    }


    public BlogPost getBlogPost() {
        return blogPost;
    }



    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Boolean getManager() {
        return manager;
    }

    public void setManager(Boolean manager) {
        this.manager = manager;
    }
}
