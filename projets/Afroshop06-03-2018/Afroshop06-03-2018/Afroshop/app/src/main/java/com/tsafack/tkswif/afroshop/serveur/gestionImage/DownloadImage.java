package com.tsafack.tkswif.afroshop.serveur.gestionImage;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by leds on 3/13/2018.
 */

public abstract class DownloadImage {

    public void download (String url, Context context){

        RequestQueue queue = Volley.newRequestQueue(context);
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                onPostExcecute(response);
            }
        }, 1000, 500, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onErrror(error);
            }
        });
        queue.add(imageRequest);
    }

    public abstract void onErrror(VolleyError error);

    public abstract void onPostExcecute(Bitmap response);

}
