package com.example.vollytest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import javax.xml.transform.ErrorListener;

public class MainActivity extends AppCompatActivity {
    private static final String SERVER_URL = "http://192.168.1.3/background.png";
    Button btn_getImage ;
    ImageView imageView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image_from_server_id);
        btn_getImage = findViewById(R.id.btn_getImage_id);

        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext() , "Error"+error.getLocalizedMessage() , Toast.LENGTH_LONG).show();
                VolleyLog.wtf(error.getMessage() , "utf-8");
            }
        } ;
        //final RequestQueue queue = Volley.newRequestQueue(this);
        final RequestQueue queue = SingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
        btn_getImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageRequest imageRequest = new ImageRequest(SERVER_URL, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        if(response !=null){
                            imageView.setImageBitmap(response);
                        }
                    }
                } ,
                        400 , 400 , ImageView.ScaleType.CENTER_CROP , Bitmap.Config.ARGB_8888 , errorListener);
                queue.add(imageRequest);

            } // end onClick()
        });
    } // end onCreate()
} // end class
