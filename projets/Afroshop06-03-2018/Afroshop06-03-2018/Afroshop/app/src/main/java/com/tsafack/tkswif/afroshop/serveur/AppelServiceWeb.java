package com.tsafack.tkswif.afroshop.serveur;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.CategorieArticle;
import com.tsafack.tkswif.afroshop.gestionDesUtilisateurs.configuration.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by messomo on 11/27/2017.
 */

public abstract class AppelServiceWeb {
    SessionManager sessionManager;
    String URL = "";

    public void appelService (final Context context, final String action, final HashMap<String, String> values, int methodRequette, boolean isIdUser){

        sessionManager = new SessionManager(context);
        String s = context.getResources().getString(R.string.progress_dialog_appel_service_web);
        final ProgressDialog dialog = new ProgressDialog(context);
                dialog.setMessage(s);
                dialog.setCancelable(false);
        dialog.show();

        //**************************************************//
        RequestQueue queue = Volley.newRequestQueue(context);
        //Toast.makeText(context, ActionsRequettes.TRAITEMENT + action +"/"+ sessionManager.getUser().getIdUtilisateur(), Toast.LENGTH_LONG).show();

        String id = values.get("id");
        URL = ActionsRequettes.TRAITEMENT + action;
        if(isIdUser){
            URL = ActionsRequettes.TRAITEMENT + action +"/"+ sessionManager.getUser().getIdUtilisateur() + "/" + id;
        }
        StringRequest strReq = new StringRequest(methodRequette,
                URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//                Toast.makeText(context, "response : " + response.toString(), Toast.LENGTH_SHORT).show();
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                postExecute(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                NetworkResponse networkResponse = error.networkResponse;
                String errorMessage = "Unknown error";
                if (networkResponse == null) {
                    if (error.getClass().equals(TimeoutError.class)) {
                        errorMessage = "Request timeout";
                    } else if (error.getClass().equals(NoConnectionError.class)) {
                        errorMessage = "Failed to connect server";
                    }
                } else {
                    String result = new String(networkResponse.data);
                    try {
                        JSONObject response = new JSONObject(result);
                        String status = response.getString("status");
                        String message = response.getString("message");

                        Log.e("Error Status", status);
                        Log.e("Error Message", message);

                        if (networkResponse.statusCode == 404) {
                            errorMessage = "Resource not found";
                        } else if (networkResponse.statusCode == 401) {
                            errorMessage = message+" Please login again";
                        } else if (networkResponse.statusCode == 400) {
                            errorMessage = message+ " Check your inputs";
                        } else if (networkResponse.statusCode == 500) {
                            errorMessage = message+" Something is getting wrong";
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("Error", errorMessage);
                Toast.makeText(context, errorMessage + error.toString(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params = values;
//                params.put(ActionsRequettes.ACTION, action);
                return params;
            }

        };

        // Adding request to request queue
        queue.add(strReq);
    }


    public abstract void postExecute(String response);
}
