package com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.traitement;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.kosalgeek.android.json.JsonConverter;
import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.CategorieArticle;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.adapter.articlesAdapter;
import com.tsafack.tkswif.afroshop.serveur.ActionsRequettes;
import com.tsafack.tkswif.afroshop.serveur.AppelServiceWeb;
import com.tsafack.tkswif.afroshop.serveur.ClesCommunicationServeur;
import com.tsafack.tkswif.afroshop.sqLite.GestionCategorieArtice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tsamo ulrich on 26/03/2018.
 */

public class GestionCategorie {

    private Context context;
    GestionCategorieArtice gestionCategorieArtice;
    final Resources res;

    public GestionCategorie(Context context){
        this.context = context;
        gestionCategorieArtice = new GestionCategorieArtice(context);
        res = context.getResources();
    }

    public articlesAdapter listerCategorie (final RecyclerView recyclerView){
        final ArrayList<CategorieArticle> categorieList = new ArrayList<>();

        final articlesAdapter[] adapter = new articlesAdapter[1];
        HashMap<String, String> values = new HashMap<>();

        AppelServiceWeb serviceWeb = new AppelServiceWeb() {
            @Override
            public void postExecute(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getInt("error") == 0) {
                        JSONArray array = jsonObject.getJSONArray("data");
                        gestionCategorieArtice.deleteCategorieArticle();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            CategorieArticle categorieArticle = new CategorieArticle();
                            categorieArticle.setIdCategorieArticle(object.getString(ClesCommunicationServeur.ID_CATEGORIE_ARTICLE));
//                            categorieArticle.setImageCategorieArticle(object.getString(ClesCommunicationServeur.IMAGE_CATEGORIE_ARTICLE));
                            categorieArticle.setLibelleCategorieArticle(object.getString(ClesCommunicationServeur.LIBELLE_CATEGORIE_ARTICLE));
                            categorieArticle.setTypeCategorieArticle(object.getString(ClesCommunicationServeur.TYPE_CATEGORIE_ARTICLE));
                            //categorieArticle.setDateCreationCategorieArticle(Date.valueOf(object.getString(ClesCommunicationServeur.DATE_CREATION_CATEGORIE_ARTICLE)));
                            try{
                                JSONArray galerie = object.getJSONArray("maGalerie");
                                JSONObject image = galerie.getJSONObject(0);
                                categorieArticle.setImageCategorieArticle(image.getString("urlMediaCategorieArticle"));
                            }
                            catch (Exception e){
                                categorieArticle.setImageCategorieArticle("");
                            }
                            categorieList.add(categorieArticle);
                            gestionCategorieArtice.addCategorieArticle(categorieArticle);
                        }
                        adapter[0] = new articlesAdapter(context, categorieList);
                        recyclerView.setAdapter(adapter[0]);
                    }
                    else {
                        Toast.makeText(context, res.getString(R.string.erreur_service_web), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                }
//                ArrayList<CategorieArticle> categorieList = new JsonConverter<CategorieArticle>().toArrayList(response, CategorieArticle.class);
                adapter[0] = new articlesAdapter(context, categorieList);
                recyclerView.setAdapter(adapter[0]);

            }
        };
        serviceWeb.appelService(context, ActionsRequettes.ACTION_LISTER_CATEGORIE, values, Request.Method.POST, true);
        return adapter[0];
    }

}
