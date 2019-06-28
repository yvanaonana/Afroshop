package com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.traitement;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.Agence;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.AgenceBancaire;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.Agent;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.ArticleCommandeCustom;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.CommamdeCustom;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.SharePreferenceCart;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.suiviDesCommandes.activities.CardActivity;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.suiviDesCommandes.adapter.ListCommandeAdapter;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.suiviDesCommandes.adapter.ListeAgencesAdapter;
import com.tsafack.tkswif.afroshop.gestionDesUtilisateurs.configuration.SessionManager;
import com.tsafack.tkswif.afroshop.serveur.ActionsRequettes;
import com.tsafack.tkswif.afroshop.serveur.AppelServiceWeb;
import com.tsafack.tkswif.afroshop.serveur.ClesCommunicationServeur;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yvana on 27/02/2018.
 */

public class GestionCommande {

    Context context;
    final Resources res;
    SessionManager sessionManager;

    public GestionCommande(Context context){
        this.context = context;

        res = context.getResources();
        sessionManager = new SessionManager(context);
    }

    public void enregisterCommande(final String commande){
        HashMap<String, String> values = new HashMap<>();
        values.put(ClesCommunicationServeur.PANIER, commande);

        AppelServiceWeb appelServiceWeb = new AppelServiceWeb() {
            @Override
            public void postExecute(String response) {
                SharePreferenceCart preferenceCart = new SharePreferenceCart(context);
                preferenceCart.deleteAllArticleOfCart();
            }

        };

        appelServiceWeb.appelService(context, ActionsRequettes.ACTION_PASSER_COMMANDE, values, Request.Method.POST, true);

    }

    public void supprimerCommande(){

    }

    public ArrayList<CommamdeCustom> listerCommandePasse (final ListView listView){

        final ArrayList<CommamdeCustom> commamdeCustoms = new ArrayList<>();
        String user = sessionManager.getUser().getIdUtilisateur();

        HashMap <String, String> values = new HashMap<>();
        values.put(ClesCommunicationServeur.ID_UTILISATEUR, user);

        AppelServiceWeb serviceWeb = new AppelServiceWeb() {
            @Override
            public void postExecute(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0 ; i<array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        CommamdeCustom commamdeCustom = new CommamdeCustom();
                        commamdeCustom.setDatePassationCommande(object.getString(ClesCommunicationServeur.DATE_COMMANDE));
//                        commamdeCustom.setEvolutionCommande(object.getInt());
                        commamdeCustom.setIdCommande(object.getString(ClesCommunicationServeur.ID_COMMANDE));
                        commamdeCustom.setPrixTotal(object.getDouble(ClesCommunicationServeur.PRIX_TOTAL_ARTICLE));

                        JSONArray array1 = object.getJSONArray(ClesCommunicationServeur.ARTICLES_COMMANDE);
                        ArrayList<ArticleCommandeCustom> articleCommandeCustoms = new ArrayList<>();
                        for (int j = 0; j < array1.length(); j++){

                            JSONObject object1 = array1.getJSONObject(j);

                            ArticleCommandeCustom articleCommandeCustom = new ArticleCommandeCustom();
                            articleCommandeCustom.setIdAgent(object1.getString(ClesCommunicationServeur.ID_AGENT));
                            articleCommandeCustom.setIdFournisseurArticle(object1.getString(ClesCommunicationServeur.ID_FOURNISSEUR));
                            articleCommandeCustom.setLibelleArticleCommandeCustom(object1.getString(ClesCommunicationServeur.LIB_ARTICLE_COMMANDE));
                            articleCommandeCustom.setRefArticleCommandeCustom(object1.getString(ClesCommunicationServeur.REF_ARTICLE_COMMANDE));
                            articleCommandeCustom.setRefArticle(object1.getString(ClesCommunicationServeur.REF_ARTICLE));
                            articleCommandeCustom.setRegionArticle(object1.getString(ClesCommunicationServeur.REGION_ARTICLE));
                            articleCommandeCustom.setPrixUnitaireArticleCommandeCustom(object1.getDouble(ClesCommunicationServeur.PRIX_UNITAIRE_ARTICLE));
                            articleCommandeCustom.setQuantiteArticleCommandeCustom(object1.getDouble(ClesCommunicationServeur.QTE_ARTICLE_COMMANDE));

                            articleCommandeCustoms.add(articleCommandeCustom);
                        }

                        commamdeCustom.setArticlePaniers(articleCommandeCustoms);

                        commamdeCustoms.add(commamdeCustom);
                    }
                    ListCommandeAdapter adapter = new ListCommandeAdapter(context, commamdeCustoms);
                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

        };
        serviceWeb.appelService(context, ActionsRequettes.ACTION_LISTER_COMMANDE_PASSEE, values, Request.Method.POST, true);
        return commamdeCustoms;
    }

    public ArrayList<CommamdeCustom> listerCommandeRecu (final ListView listView){

        final ArrayList<CommamdeCustom> commamdeCustoms = new ArrayList<>();
        String user = sessionManager.getUser().getIdUtilisateur();

        HashMap <String, String> values = new HashMap<>();
        values.put(ClesCommunicationServeur.ID_UTILISATEUR, user);

        AppelServiceWeb serviceWeb = new AppelServiceWeb() {
            @Override
            public void postExecute(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0 ; i<array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        CommamdeCustom commamdeCustom = new CommamdeCustom();
                        commamdeCustom.setDatePassationCommande(object.getString(ClesCommunicationServeur.DATE_COMMANDE));
//                        commamdeCustom.setEvolutionCommande(object.getInt());
                        commamdeCustom.setIdCommande(object.getString(ClesCommunicationServeur.ID_COMMANDE));
                        commamdeCustom.setPrixTotal(object.getDouble(ClesCommunicationServeur.PRIX_TOTAL_ARTICLE));

                        JSONArray array1 = object.getJSONArray(ClesCommunicationServeur.ARTICLES_COMMANDE);
                        ArrayList<ArticleCommandeCustom> articleCommandeCustoms = new ArrayList<>();
                        for (int j = 0; j < array1.length(); j++){

                            JSONObject object1 = array1.getJSONObject(j);

                            ArticleCommandeCustom articleCommandeCustom = new ArticleCommandeCustom();
                            articleCommandeCustom.setIdAgent(object1.getString(ClesCommunicationServeur.ID_AGENT));
                            articleCommandeCustom.setIdFournisseurArticle(object1.getString(ClesCommunicationServeur.ID_FOURNISSEUR));
                            articleCommandeCustom.setLibelleArticleCommandeCustom(object1.getString(ClesCommunicationServeur.LIB_ARTICLE_COMMANDE));
                            articleCommandeCustom.setRefArticleCommandeCustom(object1.getString(ClesCommunicationServeur.REF_ARTICLE_COMMANDE));
                            articleCommandeCustom.setRefArticle(object1.getString(ClesCommunicationServeur.REF_ARTICLE));
                            articleCommandeCustom.setRegionArticle(object1.getString(ClesCommunicationServeur.REGION_ARTICLE));
                            articleCommandeCustom.setPrixUnitaireArticleCommandeCustom(object1.getDouble(ClesCommunicationServeur.PRIX_UNITAIRE_ARTICLE));
                            articleCommandeCustom.setQuantiteArticleCommandeCustom(object1.getDouble(ClesCommunicationServeur.QTE_ARTICLE_COMMANDE));

                            articleCommandeCustoms.add(articleCommandeCustom);
                        }

                        commamdeCustom.setArticlePaniers(articleCommandeCustoms);

                        commamdeCustoms.add(commamdeCustom);
                    }
                    ListCommandeAdapter adapter = new ListCommandeAdapter(context, commamdeCustoms);
                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

        };
        serviceWeb.appelService(context, ActionsRequettes.ACTION_LISTER_COMMANDE_RECU, values, Request.Method.POST, true);
        return commamdeCustoms;
    }

    public ArrayList<Agence> listerAgence (final ListView listView, final String region){

        final ArrayList<Agence> agences = new ArrayList<>();
        final ArrayList<String> nomAgences = new ArrayList<>();

        HashMap<String, String> values = new HashMap<>();
        values.put(ClesCommunicationServeur.REGION_ARTICLE, region);

        AppelServiceWeb appelServiceWeb = new AppelServiceWeb() {
            @Override
            public void postExecute(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0 ;i<array.length(); i++){
                        JSONObject object = array.getJSONObject(i);

                        Agence agence = new Agence();
                        agence.setIdAgence(object.getString(ClesCommunicationServeur.ID_AGENCE));
                        agence.setNomAgence(object.getString(ClesCommunicationServeur.NOM_AGENCE));
                        agence.setTelephoneAgence(object.getString(ClesCommunicationServeur.TELEPHONE_AGENCE));
                        agence.setEmailAgence(object.getString(ClesCommunicationServeur.EMAIL_AGENCE));
                        agences.add(agence);
                        nomAgences.add(agence.getNomAgence());
                    }
                    ListeAgencesAdapter adapter = new ListeAgencesAdapter(context, agences);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, res.getString(R.string.erreur_convertion) +
                            e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        };
        appelServiceWeb.appelService(context, ActionsRequettes.ACTION_CHOISIR_AGENCE, values, Request.Method.POST, true);
        return agences;
    }

    public ArrayList<Agent> listerAgent (final Spinner spinner, final String region){

        final ArrayList<Agent> agents = new ArrayList<>();
        final ArrayList<String> nomAgents = new ArrayList<>();
        HashMap<String, String> values = new HashMap<>();
        values.put(ClesCommunicationServeur.REGION_ARTICLE, region);

        AppelServiceWeb appelServiceWeb = new AppelServiceWeb() {
            @Override
            public void postExecute(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i<array.length(); i++){
                        JSONObject object = array.getJSONObject(i);

                        Agent agent = new Agent();
                        agent.setIdAgent(object.getString(ClesCommunicationServeur.ID_AGENT));
                        agent.setNomAgent(object.getString(ClesCommunicationServeur.NOM_AGENT));
                        agent.setTelephoneAgent(object.getString(ClesCommunicationServeur.TELEPHONE_AGENT));
                        nomAgents.add(object.getString(ClesCommunicationServeur.NOM_AGENT));
                        agents.add(agent);
                    }
                    ArrayAdapter adapter = new ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, nomAgents);
                    spinner.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, res.getString(R.string.erreur_convertion) + e , Toast.LENGTH_SHORT).show();
                }
            }


        };
        appelServiceWeb.appelService(context, ActionsRequettes.ACTION_CHOISIR_AGENT, values, Request.Method.POST, true);

        return agents;
    }

    public ArrayList<AgenceBancaire> listerAgencesBancaire(final ListView listView, final View view){
        HashMap<String, String> values = new HashMap<>();

        final ArrayList<AgenceBancaire> agenceBancaires = new ArrayList<>();

        final ArrayList<String> noms = new ArrayList<>();

        AppelServiceWeb appelServiceWeb = new AppelServiceWeb() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceAsColor")
            @Override
            public void postExecute(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        AgenceBancaire agenceBancaire = new AgenceBancaire();
                        agenceBancaire.setLoginAgenceBancaire(object.getString(ClesCommunicationServeur.LOGIN_AGENCE_BANCAIRE));
                        agenceBancaire.setNomAgenceBancaire(object.getString(ClesCommunicationServeur.NOM_AGENCE_BANCAIRE));

                        noms.add(agenceBancaire.getNomAgenceBancaire());
                        agenceBancaires.add(agenceBancaire);
                        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_checked, noms);
                        listView.setAdapter(adapter);
                    }

                    AlertDialog alertDialog = new AlertDialog.Builder(context)
                            .setTitle(res.getString(R.string.agence_bancaire))
                            .setView(view)
                            .setNegativeButton(res.getString(R.string.annuler), null)
                            .create();
                    alertDialog.show();
                } catch (JSONException e) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }


        };
        appelServiceWeb.appelService(context, ActionsRequettes.ACTION_LISTER_AGENCE_BANCAIRE, values, Request.Method.POST, true);

        return agenceBancaires;
    }

}
