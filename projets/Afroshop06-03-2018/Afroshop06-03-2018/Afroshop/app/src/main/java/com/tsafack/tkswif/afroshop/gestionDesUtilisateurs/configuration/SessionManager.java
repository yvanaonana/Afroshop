package com.tsafack.tkswif.afroshop.gestionDesUtilisateurs.configuration;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.Utilisateur;

/**
 * Created by yvana on 13/02/2018.
 */

public class SessionManager {

    private static final String KEY_USER = "user";
    private static String TAG = SessionManager.class.getName();

    // Shared preferences file name
    private static final String PREF_NAME = "email";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    private Gson gson;


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public void setUser (Utilisateur user){
        String chaine = gson.toJson(user);
        editor.putString(KEY_USER, chaine);
        editor.apply();
    }

    public Utilisateur getUser(){
        Utilisateur user = null;
        String chaine = pref.getString(KEY_USER, "");
        user = gson.fromJson(chaine, Utilisateur.class);
        return user;
    }
}
