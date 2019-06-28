package com.tsafack.tkswif.afroshop.gestionDesUtilisateurs.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class EnregistrementUser extends Service {
    public EnregistrementUser() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Toast.makeText(getApplicationContext(), "nest ce pas", Toast.LENGTH_LONG).show();
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "reussi", Toast.LENGTH_LONG).show();
//        GestionUtilisateur utilisateur = new GestionUtilisateur()
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String user = intent.getStringExtra("id");
        Toast.makeText(getApplicationContext(), user, Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }
}
