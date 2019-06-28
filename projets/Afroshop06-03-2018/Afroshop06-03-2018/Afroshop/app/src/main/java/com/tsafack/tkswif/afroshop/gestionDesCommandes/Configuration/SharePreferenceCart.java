package com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.Article;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.ArticleCommande;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.ArticlePanier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ESPACE-TEK on 13/02/2018.
 */

public class SharePreferenceCart {

    private SharedPreferences pref;
    private Context context;
    private Gson gson;
    SharedPreferences.Editor editor;

    // CONSTANTES
    private static String REF_ARTICLE = "REFARTICLE";
    private static final String SHARED_PREF = "shared_pref";
    private static  final String ARTICLE_COUNT = "ARTICLE_COUNT";

    public SharePreferenceCart(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    public void addArticlesToCart(ArrayList<ArticlePanier> articleList) {
        String articleListString = gson.toJson(articleList);
        editor = pref.edit();
        editor.putString(REF_ARTICLE, articleListString);
        editor.apply();
    }

    public List<ArticlePanier> retrieveArticlesFromCart() {
        String productString = pref.getString(REF_ARTICLE, "");
        ArticlePanier[] articlesInCart = gson.fromJson(productString, ArticlePanier[].class);
        return convertObjectArrayToListObject(articlesInCart);
    }

    public void deleteArticleOfCart(String reference){
        List<ArticlePanier> articlePaniers = retrieveArticlesFromCart();
        for(int i = 0; i<articlePaniers.size(); i++){
            if(articlePaniers.get(i).getRefArticle().equalsIgnoreCase(reference)){
                articlePaniers.remove(i);
            }
        }
        addArticlesToCart((ArrayList<ArticlePanier>) articlePaniers);
    }

    public void deleteAllArticleOfCart (){
        editor = pref.edit();
        editor.clear();
        editor.apply();
    }

    public void updateArticleOfCart(double quantite, String idAgent, int position){
        List<ArticlePanier> articlePaniers = retrieveArticlesFromCart();
        articlePaniers.get(position).setQuantiteArticlePanier(quantite);
        articlePaniers.get(position).setIdAgent(idAgent);
        addArticlesToCart((ArrayList<ArticlePanier>) articlePaniers);
    }

    public void addArticleCount(int articleCount) {
        editor = pref.edit();
        editor.putInt(ARTICLE_COUNT, articleCount);
        editor.apply();
    }

    public int retriveArticleCount(){return pref.getInt(ARTICLE_COUNT, 0);}

    private List<ArticlePanier> convertObjectArrayToListObject(ArticlePanier[] articleInCart) {
        List<ArticlePanier> mArticle = new ArrayList<ArticlePanier>();
        if (articleInCart != null)
            Collections.addAll(mArticle, articleInCart);
        return mArticle;
    }
}