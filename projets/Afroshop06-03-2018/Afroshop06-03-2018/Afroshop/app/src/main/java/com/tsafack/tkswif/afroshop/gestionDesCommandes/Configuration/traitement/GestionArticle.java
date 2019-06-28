package com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.traitement;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.Article;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.MediaArticle;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.Utilisateur;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.ArticleCustom;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.adapter.MyArticlesAdapter;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.adapter.articlesAdapter;
import com.tsafack.tkswif.afroshop.gestionDesUtilisateurs.configuration.SessionManager;
import com.tsafack.tkswif.afroshop.serveur.ActionsRequettes;
import com.tsafack.tkswif.afroshop.serveur.AppelServiceWeb;
import com.tsafack.tkswif.afroshop.serveur.ClesCommunicationServeur;
import com.tsafack.tkswif.afroshop.serveur.MultipartRequestCall;
import com.tsafack.tkswif.afroshop.serveur.gestionImage.DataPart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.MultipartUploadTask;
import net.gotev.uploadservice.UploadNotificationConfig;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by yvana on 26/02/2018.
 */

public class GestionArticle {

    Context context;
    SessionManager manager;

    /**
     * contructeur de la classe qui initialise le context
     * @param context
     */
    public GestionArticle (Context context){
        this.context = context;
        manager = new SessionManager(context);
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * methode qui enregistre un article avec toutes ses images
     * @param article l'article à enregistrer
     * @param filePath l'ensemble des chemins d'accès des images
     */
    public void enregistrerArticle (Article article, ArrayList<String> filePath){

        Utilisateur user = manager.getUser();

        try {
            String uploadId = UUID.randomUUID().toString();
            MultipartUploadRequest multipartUploadRequest = new MultipartUploadRequest(context, uploadId, ActionsRequettes.TRAITEMENT + ActionsRequettes.ACTION_PUBLIER_PRODUIT + "/"+ manager.getUser().getIdUtilisateur());

            for (int i = 0; i < filePath.size(); i++){
                multipartUploadRequest.addFileToUpload(filePath.get(i), ClesCommunicationServeur.IMAGE_ARTICLE_+i);
            }
//            if (!filePath.get(0.equalsIgnoreCase("")){
//                multipartUploadRequest.addFileToUpload(filePath[0], ClesCommunicationServeur.IMAGE_ARTICLE_1);
//            }
//            if (!filePath[1].equalsIgnoreCase("")){
//                multipartUploadRequest.addFileToUpload(filePath[1], ClesCommunicationServeur.IMAGE_ARTICLE_2);
//            }
//            if (!filePath[2].equalsIgnoreCase("")){
//                multipartUploadRequest.addFileToUpload(filePath[2], ClesCommunicationServeur.IMAGE_ARTICLE_3);
//            }
//            if (!filePath[3].equalsIgnoreCase("")){
//                multipartUploadRequest.addFileToUpload(filePath[3], ClesCommunicationServeur.IMAGE_ARTICLE_4);
//            }
//            if (!filePath[4].equalsIgnoreCase("")){
//                multipartUploadRequest.addFileToUpload(filePath[4], ClesCommunicationServeur.IMAGE_ARTICLE_5);
//            }
            multipartUploadRequest.addParameter(ClesCommunicationServeur.LIB_ARTICLE, article.getLibArticle())
                    .addParameter(ClesCommunicationServeur.ID_CATEGORIE_ARTICLE, article.getIdCategorieArticle())
                    .addParameter(ClesCommunicationServeur.PRIX_UNITAIRE_ARTICLE, String.valueOf(article.getPrixUnitaireArticle()))
                    .addParameter(ClesCommunicationServeur.QTE_INITIALE_ARTICLE, String.valueOf(article.getQteInitialeArticle()))
                    .addParameter(ClesCommunicationServeur.DESCRIPTION_ARTICLE, article.getDescriptionArticle())
                    .addParameter(ClesCommunicationServeur.ID_UTILISATEUR, user.getIdUtilisateur())
                    .addParameter(ClesCommunicationServeur.NUMERO_NORMALISATION_ARTICLE, article.getNumeroNormalisationArticle())
                    .addParameter(ClesCommunicationServeur.REGION_ARTICLE, article.getRegionArticle())
                    .addParameter(ClesCommunicationServeur.LOCALITE_ARTICLE, article.getQuartierArticle())
                    .addParameter(ClesCommunicationServeur.VILLE_ARTICLE, article.getVilleArticle())
                    .addParameter(ClesCommunicationServeur.PAYS_ARTICLE, article.getPaysArticle())
                    .addParameter(ClesCommunicationServeur.UNITE_ARTICLE, article.getUniteArticle())
                    .addParameter(ClesCommunicationServeur.MENTION_ARTICLE, article.isMentionArticle()+"")//Adding text parameter to the request
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload




        } catch (Exception exc) {
            Toast.makeText(context, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }

//        HashMap<String, String> values = new HashMap<>();
//        values.put(ClesCommunicationServeur.LIB_ARTICLE, article.getLibArticle());
//        values.put(ClesCommunicationServeur.ID_CATEGORIE_ARTICLE, article.getIdCategorieArticle());
//        values.put(ClesCommunicationServeur.PRIX_UNITAIRE_ARTICLE, String.valueOf(article.getPrixUnitaireArticle()));
//        values.put(ClesCommunicationServeur.QTE_INITIALE_ARTICLE, String.valueOf(article.getQteInitialeArticle()));
//        values.put(ClesCommunicationServeur.DESCRIPTION_ARTICLE, article.getDescriptionArticle());
//        values.put(ClesCommunicationServeur.ID_UTILISATEUR, user.getIdUtilisateur());
//        values.put(ClesCommunicationServeur.NUMERO_NORMALISATION_ARTICLE, article.getNumeroNormalisationArticle());
//        values.put(ClesCommunicationServeur.REGION_ARTICLE, article.getRegionArticle());
//        values.put(ClesCommunicationServeur.LOCALITE_ARTICLE, article.getQuartierArticle());
//        values.put(ClesCommunicationServeur.VILLE_ARTICLE, article.getVilleArticle());
//        values.put(ClesCommunicationServeur.MENTION_ARTICLE, article.isMentionArticle()+"");

//        HashMap<String, DataPart> byteValues = new HashMap<>();
//        byteValues.put(ClesCommunicationServeur.IMAGE_ARTICLE_1, new DataPart("image 1", getFileDataFromDrawable(bitmap[0]), "image"));
//        if (bitmap[1] != null){
//            byteValues.put(ClesCommunicationServeur.IMAGE_ARTICLE_2, new DataPart("image 2", getFileDataFromDrawable(bitmap[1]), "image"));
//        }
//        if (bitmap[2] != null) {
//            byteValues.put(ClesCommunicationServeur.IMAGE_ARTICLE_3, new DataPart("image 3", getFileDataFromDrawable(bitmap[2]), "image"));
//
//        }
//        if (bitmap[3] != null) {
//            byteValues.put(ClesCommunicationServeur.IMAGE_ARTICLE_4, new DataPart("image 4", getFileDataFromDrawable(bitmap[3]), "image"));
//
//        }
//
//
//        MultipartRequestCall multipartRequestCall = new MultipartRequestCall() {
//            @Override
//            public void postExecute(NetworkResponse response) {
//                Toast.makeText(context, "response "+response.headers, Toast.LENGTH_LONG).show();
//            }
//        };
//        multipartRequestCall.appelMultipartRequest(context, ActionsRequettes.ACTION_PUBLIER_PRODUIT,
//                values, byteValues, Request.Method.POST);
    }

    /**
     * Methode qui liste les produits par categories
     * @param categorie c'est la categorie pour laquelle on veut la liste
     * @param recyclerView le composant dans lequel la liste sera affichee
     * @return la liste des articles dans un adapter
     */
    public MyArticlesAdapter listerArticlesCategorie (String categorie, final RecyclerView recyclerView){
        final MyArticlesAdapter[] adapter = {null};
        final List<ArticleCustom> articles = new ArrayList<>();

        HashMap<String, String> values = new HashMap<>();
        values.put("id", categorie);

        AppelServiceWeb appelServiceWeb = new AppelServiceWeb() {
            @Override
            public void postExecute(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getInt("error") == 0){
                        JSONArray array = jsonObject.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++){
                            JSONObject object = array.getJSONObject(i);
                            ArticleCustom article = new ArticleCustom();
                            article.setRefArticle(object.getString(ClesCommunicationServeur.REF_ARTICLE));
                            article.setLibArticle(object.getString(ClesCommunicationServeur.LIB_ARTICLE));
                            article.setDescriptionArticle(object.getString(ClesCommunicationServeur.DESCRIPTION_ARTICLE));
                            article.setDatePublicationArticle(object.getString(ClesCommunicationServeur.DATE_PUBLICATION_ARTICLE));
                            JSONObject object1 = object.getJSONObject(ClesCommunicationServeur.ID_CATEGORIE_ARTICLE);
                            article.setCategorieArticle(object1.getString(ClesCommunicationServeur.LIBELLE_CATEGORIE_ARTICLE));
                            JSONObject object2 = object.getJSONObject(ClesCommunicationServeur.ID_FOURNISSEUR);
                            article.setIdFournisseurArticle(object2.getString(ClesCommunicationServeur.ID_UTILISATEUR));
                            if (object.getInt(ClesCommunicationServeur.MENTION_ARTICLE) == 1){
                                article.setMentionArticle(true);
                            }else{
                                article.setMentionArticle(false);
                            }
//                            article.setNombreAimeArticle(object.getInt(ClesCommunicationServeur.NOMBRE_AIME_ARTICLE));
                            article.setNumNormalisationArticle(object.getString(ClesCommunicationServeur.NUMERO_NORMALISATION_ARTICLE));
                            article.setPrixUnitaireArticle(object.getDouble(ClesCommunicationServeur.PRIX_UNITAIRE_ARTICLE));
                            article.setQteInitialeArticle(object.getDouble(ClesCommunicationServeur.QTE_STOCK_ARTICLE));
                            article.setUniteArticle(object.getString(ClesCommunicationServeur.UNITE_ARTICLE));
                            article.setRegionArticle(object.getString(ClesCommunicationServeur.REGION_ARTICLE));
                            article.setVilleArticle(object.getString(ClesCommunicationServeur.VILLE_ARTICLE));
                            article.setQuartierArticle(ClesCommunicationServeur.LOCALITE_ARTICLE);
                            if(object.getInt(ClesCommunicationServeur.AIME_ARTICLE) == 1){
                                article.setAimeArticle(true);
                            }
                            else
                                article.setAimeArticle(false);
//                        article.setQteRestanteArticle(object.getDouble(ClesCommunicationServeur.QTE_ARTICLE_COMMANDE));
                            //maintenant il reste a recuperer les images d'un produit. cela peut etre donne par un objet JSON a
                            //part entiere
                            JSONArray array1 = object.getJSONArray("maGalerie");
                            List<MediaArticle> mediaArticles = new ArrayList<>();
                            for(int j = 0; j < array1.length(); j++){
                                JSONObject object3 = array1.getJSONObject(j);

                                MediaArticle mediaArticle = new MediaArticle();
                                mediaArticle.setUrlMedia(object3.getString(ClesCommunicationServeur.URL_MEDIA_ARTICLE));
                                mediaArticle.setTypeCategorieArticle(object3.getString(ClesCommunicationServeur.TYPE_MEDIA_ARTICLE));
                                mediaArticles.add(mediaArticle);
                            }
                            article.setImages((ArrayList<MediaArticle>) mediaArticles);
                            articles.add(article);
                        }
                        adapter[0] = new MyArticlesAdapter(context, articles);
                        recyclerView.setAdapter(adapter[0]);
                    }
                    else {
                        Toast.makeText(context, context.getResources().getString(R.string.erreur_reponse_service_web), Toast.LENGTH_LONG);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, context.getResources().getString(R.string.erreur_convertion) + e.toString(), Toast.LENGTH_LONG).show();
//                    articles.add(new ArticleCustom("rer", "carottes", "legumes",
//                            "bien roujes venant du nord cameroun", 200,
//                            100, 1000, "20-4-2018",
//                            "KG", "kjshds", "shgddhe",
//                            "yvana", 3, false, false,
//                            "Est", "Yaounde", "damas"));
//                    adapter[0] = new MyArticlesAdapter(context, (ArrayList<ArticleCustom>) articles);
//                    recyclerView.setAdapter(adapter[0]);
                    e.printStackTrace();
                }
            }

        };
        appelServiceWeb.appelService(context, ActionsRequettes.ACTION_LISTER_PRODUIT_CATEGORIE, values, Request.Method.POST, true);
        return adapter[0];
    }

    /**
     * Methode qui modifie un article en le prenant en parametre
     * @param article
     */
    public void modifierArticle (Article article) {
        HashMap<String, String> values = new HashMap<>();
        values.put(ClesCommunicationServeur.REF_ARTICLE, article.getRefArticle());
        values.put(ClesCommunicationServeur.LIB_ARTICLE, article.getLibArticle());
        values.put(ClesCommunicationServeur.PRIX_UNITAIRE_ARTICLE, String.valueOf(article.getPrixUnitaireArticle()));
        values.put(ClesCommunicationServeur.QTE_INITIALE_ARTICLE, String.valueOf(article.getQteInitialeArticle()));
        values.put(ClesCommunicationServeur.DESCRIPTION_ARTICLE, article.getDescriptionArticle());

        AppelServiceWeb appelServiceWeb = new AppelServiceWeb() {
            @Override
            public void postExecute(String response) {
            }

        };
        appelServiceWeb.appelService(context, ActionsRequettes.ACTION_MODIFIER_PRODUIT, values, Request.Method.POST, true);
    }

    /**
     * Methode permettant de supprimer un article
     * @param id
     */
    public void supprimerArticle(String id){
        HashMap<String, String> values = new HashMap<>();
        values.put(ClesCommunicationServeur.REF_ARTICLE, id);

        AppelServiceWeb appelServiceWeb = new AppelServiceWeb() {
            @Override
            public void postExecute(String response) {
            }

        };
        appelServiceWeb.appelService(context, ActionsRequettes.ACTION_SUPPRIMER_PRODUIT, values, Request.Method.POST, true);
    }

    /**
     * Methode qui est appelee lorsqu'un utiisateur aime un produit
     * @param idProduit
     */
    public void ajouterJAime (String idProduit) {
        HashMap<String, String> values = new HashMap<>();

        Utilisateur user = manager.getUser();
        values.put(ClesCommunicationServeur.REF_ARTICLE, idProduit);
        values.put(ClesCommunicationServeur.ID_UTILISATEUR, user.getIdUtilisateur());
        AppelServiceWeb appelServiceWeb = new AppelServiceWeb() {
            @Override
            public void postExecute(String response) {
            }

        };
        appelServiceWeb.appelService(context, ActionsRequettes.ACTION_AJOUTER_J_AIME, values, Request.Method.POST, true);
    }

    /**
     * Cette methode permet de lister les articles par utilisateurs
     * @param recyclerView ceci est le recycler view qui permet d'afficher les articles
     * @return
     */
    public MyArticlesAdapter listerArticlesUtilisateur (final RecyclerView recyclerView) {
        final Resources res = context.getResources();
        final ArrayList<ArticleCustom> articles = new ArrayList<>();
        final MyArticlesAdapter[] adapter = {null};

        Utilisateur user = manager.getUser();
        HashMap<String, String> values = new HashMap<>();
        values.put(ClesCommunicationServeur.ID_UTILISATEUR, user.getIdUtilisateur());

        AppelServiceWeb appelServiceWeb = new AppelServiceWeb() {
            @Override
               public void postExecute(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        ArticleCustom article = new ArticleCustom();
                        article.setRefArticle(object.getString(ClesCommunicationServeur.REF_ARTICLE));
                        article.setLibArticle(object.getString(ClesCommunicationServeur.LIB_ARTICLE));
                        article.setDescriptionArticle(object.getString(ClesCommunicationServeur.DESCRIPTION_ARTICLE));
                        article.setDatePublicationArticle(object.getString(ClesCommunicationServeur.DATE_PUBLICATION_ARTICLE));
                        article.setCategorieArticle(object.getString(ClesCommunicationServeur.LIBELLE_CATEGORIE_ARTICLE));
                        article.setIdFournisseurArticle(object.getString(ClesCommunicationServeur.ID_UTILISATEUR));

                        if (object.getInt(ClesCommunicationServeur.MENTION_ARTICLE) == 1){
                            article.setMentionArticle(true);
                        }else{
                            article.setMentionArticle(false);
                        }

                        article.setNombreAimeArticle(object.getInt(ClesCommunicationServeur.NOMBRE_AIME_ARTICLE));
                        article.setNumNormalisationArticle(object.getString(ClesCommunicationServeur.NUMERO_NORMALISATION_ARTICLE));
                        article.setPrixUnitaireArticle(object.getDouble(ClesCommunicationServeur.PRIX_UNITAIRE_ARTICLE));
                        article.setQteInitialeArticle(object.getDouble(ClesCommunicationServeur.QTE_INITIALE_ARTICLE));
                        article.setUniteArticle(object.getString(ClesCommunicationServeur.UNITE_ARTICLE));
                        article.setQteRestanteArticle(object.getDouble(ClesCommunicationServeur.QTE_ARTICLE_COMMANDE));

                        if(object.getInt(ClesCommunicationServeur.AIME_ARTICLE) == 1){
                            article.setAimeArticle(true);
                        }
                        else
                            article.setAimeArticle(false);

                        //maintenant il reste a recuperer les images d'un produit. cela peut etre donne par un objet JSON a
                        //part entiere
                        articles.add(article);
                    }
                    adapter[0] = new MyArticlesAdapter(context, (ArrayList<ArticleCustom>) articles);
                    recyclerView.setAdapter(adapter[0]);
                } catch (JSONException e) {
                    Toast.makeText(context, res.getString(R.string.erreur_convertion), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

        };
        appelServiceWeb.appelService(context, ActionsRequettes.ACTION_LISTER_PRODUIT_UTILISATEUR, values, Request.Method.POST, true);
        return adapter[0];
    }

    /**
     * Cette methode retourne les 20 prooduits les plus aime
     * @return
     */
    public articlesAdapter listerArticlesPlusPopulaires (){
        final articlesAdapter[] adapter = {null};
        final List<ArticleCustom> articles = new ArrayList<>();

        HashMap<String, String> values = new HashMap<>();

        AppelServiceWeb appelServiceWeb = new AppelServiceWeb() {
            @Override
            public void postExecute(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        ArticleCustom article = new ArticleCustom();
                        article.setRefArticle(object.getString(ClesCommunicationServeur.REF_ARTICLE));
                        article.setLibArticle(object.getString(ClesCommunicationServeur.LIB_ARTICLE));
                        article.setDescriptionArticle(object.getString(ClesCommunicationServeur.DESCRIPTION_ARTICLE));
                        article.setDatePublicationArticle(object.getString(ClesCommunicationServeur.DATE_PUBLICATION_ARTICLE));
                        article.setCategorieArticle(object.getString(ClesCommunicationServeur.LIBELLE_CATEGORIE_ARTICLE));
                        article.setIdFournisseurArticle(object.getString(ClesCommunicationServeur.ID_UTILISATEUR));
                        article.setMentionArticle(object.getBoolean(ClesCommunicationServeur.MENTION_ARTICLE));
                        article.setNombreAimeArticle(object.getInt(ClesCommunicationServeur.NOMBRE_AIME_ARTICLE));
                        article.setNumNormalisationArticle(object.getString(ClesCommunicationServeur.NUMERO_NORMALISATION_ARTICLE));
                        article.setPrixUnitaireArticle(object.getDouble(ClesCommunicationServeur.PRIX_UNITAIRE_ARTICLE));
                        article.setQteInitialeArticle(object.getDouble(ClesCommunicationServeur.QTE_INITIALE_ARTICLE));
                        article.setUniteArticle(object.getString(ClesCommunicationServeur.UNITE_ARTICLE));
                        article.setQteRestanteArticle(object.getDouble(ClesCommunicationServeur.QTE_ARTICLE_COMMANDE));
                        article.setAimeArticle(object.getBoolean(ClesCommunicationServeur.AIME_ARTICLE));
                        //maintenant il reste a recuperer les images d'un produit. cela peut etre donne par un objet JSON a
                        //part entiere
                        articles.add(article);
                    }
//                    adapter[0] = new articlesAdapter(context, (ArrayList<ArticleCustom>) articles);
                } catch (JSONException e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

        };
        appelServiceWeb.appelService(context, ActionsRequettes.ACTION_LISTER_PRODUIT_LES_PLUS_POPULAIRES, values, Request.Method.GET, true);
        return adapter[0];
    }

    /**
     * Cette mathode affiche les 20 articles ajooutes recemment
     * @return
     */
    public articlesAdapter listerArticlesLesPlusRecents (){
        final articlesAdapter[] adapter = {null};
        final List<ArticleCustom> articles = new ArrayList<>();

        HashMap<String, String> values = new HashMap<>();

        AppelServiceWeb appelServiceWeb = new AppelServiceWeb() {
            @Override
            public void postExecute(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        ArticleCustom article = new ArticleCustom();
                        article.setRefArticle(object.getString(ClesCommunicationServeur.REF_ARTICLE));
                        article.setLibArticle(object.getString(ClesCommunicationServeur.LIB_ARTICLE));
                        article.setDescriptionArticle(object.getString(ClesCommunicationServeur.DESCRIPTION_ARTICLE));
                        article.setDatePublicationArticle(object.getString(ClesCommunicationServeur.DATE_PUBLICATION_ARTICLE));
                        article.setCategorieArticle(object.getString(ClesCommunicationServeur.LIBELLE_CATEGORIE_ARTICLE));
                        article.setIdFournisseurArticle(object.getString(ClesCommunicationServeur.ID_UTILISATEUR));
                        article.setMentionArticle(object.getBoolean(ClesCommunicationServeur.MENTION_ARTICLE));
                        article.setNombreAimeArticle(object.getInt(ClesCommunicationServeur.NOMBRE_AIME_ARTICLE));
                        article.setNumNormalisationArticle(object.getString(ClesCommunicationServeur.NUMERO_NORMALISATION_ARTICLE));
                        article.setPrixUnitaireArticle(object.getDouble(ClesCommunicationServeur.PRIX_UNITAIRE_ARTICLE));
                        article.setQteInitialeArticle(object.getDouble(ClesCommunicationServeur.QTE_INITIALE_ARTICLE));
                        article.setUniteArticle(object.getString(ClesCommunicationServeur.UNITE_ARTICLE));
                        article.setQteRestanteArticle(object.getDouble(ClesCommunicationServeur.QTE_ARTICLE_COMMANDE));
                        article.setAimeArticle(object.getBoolean(ClesCommunicationServeur.AIME_ARTICLE));
                        //maintenant il reste a recuperer les images d'un produit. cela peut etre donne par un objet JSON a
                        //part entiere
                        articles.add(article);
                    }
//                    adapter[0] = new articlesAdapter(context, (ArrayList<ArticleCustom>) articles);
                } catch (JSONException e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

        };
        appelServiceWeb.appelService(context, ActionsRequettes.ACTION_LISTER_PRODUIT_LES_PLUS_RECENTS, values, Request.Method.GET, true);
        return adapter[0];
    }

    /**
     * Cette methode affiche les 20 articles qui ont le plus grand nombre de commande
     * @return
     */
    public articlesAdapter listerArticleLesPlusCommandes (){
        final articlesAdapter[] adapter = {null};
        final List<ArticleCustom> articles = new ArrayList<>();

        HashMap<String, String> values = new HashMap<>();

        AppelServiceWeb appelServiceWeb = new AppelServiceWeb() {
            @Override
            public void postExecute(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        ArticleCustom article = new ArticleCustom();
                        article.setRefArticle(object.getString(ClesCommunicationServeur.REF_ARTICLE));
                        article.setLibArticle(object.getString(ClesCommunicationServeur.LIB_ARTICLE));
                        article.setDescriptionArticle(object.getString(ClesCommunicationServeur.DESCRIPTION_ARTICLE));
                        article.setDatePublicationArticle(object.getString(ClesCommunicationServeur.DATE_PUBLICATION_ARTICLE));
                        article.setCategorieArticle(object.getString(ClesCommunicationServeur.LIBELLE_CATEGORIE_ARTICLE));
                        article.setIdFournisseurArticle(object.getString(ClesCommunicationServeur.ID_UTILISATEUR));
                        article.setMentionArticle(object.getBoolean(ClesCommunicationServeur.MENTION_ARTICLE));
                        article.setNombreAimeArticle(object.getInt(ClesCommunicationServeur.NOMBRE_AIME_ARTICLE));
                        article.setNumNormalisationArticle(object.getString(ClesCommunicationServeur.NUMERO_NORMALISATION_ARTICLE));
                        article.setPrixUnitaireArticle(object.getDouble(ClesCommunicationServeur.PRIX_UNITAIRE_ARTICLE));
                        article.setQteInitialeArticle(object.getDouble(ClesCommunicationServeur.QTE_INITIALE_ARTICLE));
                        article.setUniteArticle(object.getString(ClesCommunicationServeur.UNITE_ARTICLE));
                        article.setQteRestanteArticle(object.getDouble(ClesCommunicationServeur.QTE_ARTICLE_COMMANDE));
                        article.setAimeArticle(object.getBoolean(ClesCommunicationServeur.AIME_ARTICLE));
                        //maintenant il reste a recuperer les images d'un produit. cela peut etre donne par un objet JSON a
                        //part entiere
                        articles.add(article);
                    }
//                    adapter[0] = new articlesAdapter(context, (ArrayList<ArticleCustom>) articles);
                } catch (JSONException e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

        };
        appelServiceWeb.appelService(context, ActionsRequettes.ACTION_LISTER_PRODUIT_LES_PLUS_COMMANDES, values, Request.Method.GET, true);
        return adapter[0];
    }

}
