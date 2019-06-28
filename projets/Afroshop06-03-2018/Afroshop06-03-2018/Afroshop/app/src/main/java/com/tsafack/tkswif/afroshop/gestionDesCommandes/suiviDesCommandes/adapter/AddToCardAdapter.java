package com.tsafack.tkswif.afroshop.gestionDesCommandes.suiviDesCommandes.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.Agent;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.ArticleCommande;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.ArticlePanier;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.SharePreferenceCart;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.traitement.GestionCommande;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.Extra;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.activities.Page_de_detaille_du_produit;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.suiviDesCommandes.activities.CardActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsamo ulrich on 05/04/2018.
 */

public class AddToCardAdapter extends ArrayAdapter {

    ArrayList<ArticlePanier> articleCommandes = new ArrayList<>();
    Context context;
    List<Agent> agents = new ArrayList<>();
    Spinner inputAgent;
    EditText inputQuantite;

    public AddToCardAdapter(@NonNull Context context, @NonNull ArrayList<ArticlePanier> articleCommandes) {
        super(context, R.layout.list_item_addtocardactivity, articleCommandes);
        this.articleCommandes = articleCommandes;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // engrais. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = null;
        rowView = inflater.inflate(R.layout.list_item_addtocardactivity, parent, false);

        // 3. Get icon,title & counter views from the rowView
        Holder.titre= (TextView) rowView.findViewById(R.id.item_addtocard_libelle);
        Holder.prix = (TextView) rowView.findViewById(R.id.item_addtocard_prixunitaire);
        Holder.quantite = (TextView) rowView.findViewById(R.id.item_addtocard_quantite);
        Holder.supprimer = (ImageButton) rowView.findViewById(R.id.item_addtocard_supprimer);
        Holder.modifier = (ImageButton) rowView.findViewById(R.id.item_addtocard_modifier);

        // 4. Set the text for textView

        Holder.titre.setText(articleCommandes.get(position).getLibelleArticlePanier());
        Holder.quantite.setText(articleCommandes.get(position).getQuantiteArticlePanier()+"");
        Holder.prix.setText(articleCommandes.get(position).getPrixUnitaireArticlePanier()+"");

        Holder.supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharePreferenceCart preferenceCart = new SharePreferenceCart(context);
                preferenceCart.deleteArticleOfCart(articleCommandes.get(position).getRefArticle());
                articleCommandes.remove(position);
                notifyDataSetChanged();
            }
        });

        Holder.modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifierProduit(position);
            }
        });

        // 5. retrn rowView
        return rowView;
    }

    private void modifierProduit(final int position) {

        Resources res = context.getResources();

        LayoutInflater factory = LayoutInflater.from(context);
        final View alertDialog = factory.inflate(R.layout.activity_add_product_to_card, null);

        inputQuantite = (EditText) alertDialog.findViewById(R.id.addproducttocard_input_quantite);
        inputAgent = (Spinner) alertDialog.findViewById(R.id.addproduittocard_input_agent);

        initialisationAgent(position);

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(res.getString(R.string.modifer_un_produit_du_panier))
                .setMessage(res.getString(R.string.alert_dialog_modifier_produit_message))
                .setView(alertDialog)
                .setIcon(R.drawable.acheter)
                .setPositiveButton(res.getString(R.string.modifer_un_produit_du_panier), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        int k = inputAgent.getSelectedItemPosition();

                        SharePreferenceCart preferenceCart = new SharePreferenceCart(context);
                        preferenceCart.updateArticleOfCart(Double.parseDouble(inputQuantite.getText().toString().trim()),
                                agents.get(k).getIdAgent(), position);
                        articleCommandes.get(position).setIdAgent(agents.get(k).getIdAgent());
                        articleCommandes.get(position).setQuantiteArticlePanier(Double.parseDouble(inputQuantite.getText().toString().trim()));

                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton(res.getString(R.string.annuler), null)
                .create();
        dialog.show();

    }

    private void initialisationAgent(int position) {

        GestionCommande gestionCommande = new GestionCommande(context);
        agents = gestionCommande.listerAgent(inputAgent, articleCommandes.get(position).getRegionArticle());

    }

    static class Holder {
        static TextView titre;
        static TextView quantite;
        static TextView prix;
        static ImageButton supprimer;
        static ImageButton modifier;
    }
}
