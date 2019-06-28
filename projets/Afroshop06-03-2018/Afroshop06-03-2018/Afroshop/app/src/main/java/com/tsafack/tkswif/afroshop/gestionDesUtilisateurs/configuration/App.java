package com.tsafack.tkswif.afroshop.gestionDesUtilisateurs.configuration;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by leds on 5/16/2018.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
