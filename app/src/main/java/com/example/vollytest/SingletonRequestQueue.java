package com.example.vollytest;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingletonRequestQueue {
    private static SingletonRequestQueue mInstance ;
    private RequestQueue mRequestQueue ;
    private Context mContext ;

    private SingletonRequestQueue(Context context){
        this.mContext = context ;
        mRequestQueue = getRequestQueue();
    }// end constructor

    public static synchronized SingletonRequestQueue getInstance(Context context){
        if(mInstance == null){
            mInstance = new SingletonRequestQueue(context);
        } // end if
        return mInstance ;
    }// end getInstance()

    public  RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        } // end if
        return mRequestQueue ;
    }// end getRequestQueue()
}
