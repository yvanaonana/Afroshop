package com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.traitement.GestionArticle;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.adapter.articlesAdapter;

public class AfficherImagesArticle extends Service {
    public AfficherImagesArticle() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        GestionArticle gestionArticle = new GestionArticle(getApplicationContext());
        Toast.makeText(getApplicationContext(), " test services", Toast.LENGTH_LONG).show();
    }
}
