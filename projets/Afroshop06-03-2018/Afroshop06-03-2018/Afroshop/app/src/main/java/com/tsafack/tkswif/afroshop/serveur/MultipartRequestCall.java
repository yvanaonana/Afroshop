package com.tsafack.tkswif.afroshop.serveur;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.serveur.gestionImage.DataPart;
import com.tsafack.tkswif.afroshop.serveur.gestionImage.VolleyMultipartRequest;
import com.tsafack.tkswif.afroshop.serveur.gestionImage.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leds on 3/22/2018.
 */

public abstract class MultipartRequestCall {

    /**
     * cette methode permet d'envoyer plusieurs medias a la fois medias
     *
     * @param context le context de l'application
     * @param action l'action qui sera appelee des que le service commencera
     * @param values les valeurs ecrites qui seront inseres dans la base de donnee
     * @param byteValues les multimedias qui seront envoyes
     * @param methodRequest le type de la methode
     */

    public void appelMultipartRequest (final Context context, final String action, final HashMap<String, String> values,
                                       final HashMap<String, DataPart> byteValues, int methodRequest){

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(context.getResources().getString(R.string.progress_dialog_appel_service_web));
        dialog.show();



        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(methodRequest, ActionsRequettes.TRAITEMENT,
                new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                postExecute(response);
                dialog.dismiss();
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
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params;
                params = values;
                params.put("action", action);
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params;
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView
                params = byteValues;
                return params;
            }
        };

        Volley.newRequestQueue(context).add(multipartRequest);
    }

    public abstract void postExecute(NetworkResponse response);
}
