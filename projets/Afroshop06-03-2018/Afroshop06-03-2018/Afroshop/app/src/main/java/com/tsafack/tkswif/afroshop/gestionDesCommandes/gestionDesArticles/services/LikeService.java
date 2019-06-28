package com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.traitement.GestionArticle;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.traitement.GestionCommande;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.Extra;

public class LikeService extends Service {
    public LikeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String idProduit;
        idProduit = (new Intent()).getStringExtra(Extra.ID_PRODUIT);
        GestionArticle gestionArticle = new GestionArticle(this);
        gestionArticle.ajouterJAime(idProduit);
    }
}
