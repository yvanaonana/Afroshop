package com.tsafack.tkswif.afroshop.gestionDesUtilisateurs.configuration;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.Utilisateur;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.activities.AjouterProduitActivity;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.activities.categories_articles;
import com.tsafack.tkswif.afroshop.gestionDesUtilisateurs.activities.LoginActivity;
import com.tsafack.tkswif.afroshop.gestionDesUtilisateurs.services.EnregistrementUser;
import com.tsafack.tkswif.afroshop.serveur.ActionsRequettes;
import com.tsafack.tkswif.afroshop.serveur.AppelServiceWeb;
import com.tsafack.tkswif.afroshop.serveur.ClesCommunicationServeur;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.HashMap;

/**
 * Created by leds on 3/19/2018.
 */

public class GestionUtilisateur {

    Context context;
    View view;
    SessionManager sessionManager;

    public GestionUtilisateur(Context context, View view){
        this.context = context;
        this.view = view;
        sessionManager = new SessionManager(context);
    }




}
