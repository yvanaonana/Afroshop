package com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.Agent;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.ArticleCustom;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.ArticlePanier;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.SharePreferenceCart;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.traitement.GestionArticle;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.traitement.GestionCommande;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.Extra;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.activities.ModifierProduitActivity;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.activities.Page_de_detaille_du_produit;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.services.LikeService;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.suiviDesCommandes.activities.CardActivity;

import java.util.ArrayList;
import java.util.List;

public class MyArticlesAdapter extends RecyclerView.Adapter<VueArticles> {
    private Context mContext;
    private List<ArticleCustom> articlesList;

    private List<Agent> agents = new ArrayList<>();

    Spinner inputAgent;
    EditText inputQuantite;
    int nombreArticlePanier = 0;

    public MyArticlesAdapter(Context mContext, List<ArticleCustom> articlesList) {
        this.mContext = mContext;
        this.articlesList = new ArrayList<ArticleCustom>();
        this.articlesList = articlesList;
    }


    @Override
    public VueArticles onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.listes_produits_items_page, parent, false);
        return new VueArticles(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final VueArticles holder, final int position) {
        holder.title.setText(articlesList.get(position).getLibArticle());
        holder.categories.setText(articlesList.get(position).getCategorieArticle());
        holder.etatsDuproduits.setText(articlesList.get(position).isMentionArticle() + "");
        holder.prix.setText(articlesList.get(position).getPrixUnitaireArticle()+"");
        holder.optionMenu.setImageResource(R.drawable.ic_more_grey);
        holder.NombredeJaime.setText(articlesList.get(position).getNombreAimeArticle()+"");
        // loading album cover using Glide library
        holder.urlImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Page_de_detaille_du_produit.class);
                intent.putExtra(Extra.ARTICLE_CHOISI, articlesList.get(position));
                mContext.startActivity(intent);
            }
        });
        holder.optionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mContext, holder.optionMenu);
                popupMenu.getMenuInflater().inflate(R.menu.menu_list, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new MyMenuItemClickListener(position));
                popupMenu.show();
            }
        });
        holder.nombredecommandes.setText(articlesList.get(position).getQteInitialeArticle() + " " + articlesList.get(position).getUniteArticle());

        if (articlesList.get(position).getImages() != null){
            Glide.with(mContext).load(articlesList.get(position).getImages().get(0).getUrlMedia())
                    .placeholder(R.drawable.empty_flag_64px).error(R.drawable.errorcloudgreen).into(holder.urlImage);
        }
        holder.prix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajouterUnProduitAuPanier(view, position);
            }
        });

        if (articlesList.get(position).isAimeArticle()){
            holder.likeBt.setImageResource(R.drawable.ic_love);
            holder.likeBt.setClickable(false);
        }else {
            holder.likeBt.setImageResource(R.drawable.ic_heart_outline_grey);
            holder.likeBt.setClickable(true);
            holder.likeBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, LikeService.class);
                    intent.putExtra(Extra.ID_PRODUIT, articlesList.get(position).getRefArticle());
                    mContext.startService(intent);
//                    GestionArticle gestionArticle = new GestionArticle(mContext);
//                    gestionArticle.ajouterJAime(articlesList.get(position).getRefArticle());
                }
            });
        }

    }


    private void ajouterUnProduitAuPanier(View view, int position) {
        final Resources res = mContext.getResources();

        LayoutInflater factory = LayoutInflater.from(mContext);
        final View alertDialog = factory.inflate(R.layout.activity_add_product_to_card, null);

        inputQuantite = (EditText) alertDialog.findViewById(R.id.addproducttocard_input_quantite);
        inputAgent = (Spinner) alertDialog.findViewById(R.id.addproduittocard_input_agent);

        initialisationAgent(position);

        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle(res.getString(R.string.text_ajouter_au_panier))
                .setMessage("Ajouter votre produit au panier en ajoutant ses parametres")
                .setView(alertDialog)
                .setIcon(R.drawable.acheter)
                .setPositiveButton(res.getString(R.string.text_ajouter_au_panier), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        double quantite = 0;
                        int position = 0;
                        try {
                            quantite = Double.parseDouble(inputQuantite.getText().toString().trim());
                            position = inputAgent.getSelectedItemPosition();
                        }catch (Exception e){
                            Toast.makeText(mContext, res.getString(R.string.erreur_convertion),
                                    Toast.LENGTH_SHORT).show();
                        }

                        addArticleToCart(new ArticlePanier("sjhdgds", articlesList.get(position).getLibArticle(),
                                agents.get(position).getIdAgent(), articlesList.get(position).getPrixUnitaireArticle(), quantite,
                                articlesList.get(position).getRefArticle(), articlesList.get(position).getIdFournisseurArticle(),
                                articlesList.get(position).getRegionArticle()));
                        Intent intent = new Intent(mContext, CardActivity.class);
                        intent.putExtra(Extra.ARTICLE_COMMANDE, articlesList.get(position));
                        mContext.startActivity(intent);
                    }
                })
                .setNegativeButton("Annuler", null)
                .create();
        dialog.show();
    }

    private void initialisationAgent(int position) {

        GestionCommande gestionCommande = new GestionCommande(mContext);
        agents = gestionCommande.listerAgent(inputAgent, articlesList.get(position).getRegionArticle());

    }


    public void addArticleToCart(ArticlePanier singletonArticle){
        SharePreferenceCart preferenceCart = new SharePreferenceCart(mContext);
        List<ArticlePanier> articlesInCart = preferenceCart.retrieveArticlesFromCart();

        if(articlesInCart.isEmpty()){
            List<ArticlePanier> cartArticleList = new ArrayList<>();
            cartArticleList.add(singletonArticle);
            preferenceCart.addArticlesToCart((ArrayList<ArticlePanier>) cartArticleList);
            nombreArticlePanier = cartArticleList.size();
        }else {
            List<ArticlePanier> articlesInCartToUpdate = preferenceCart.retrieveArticlesFromCart();
            articlesInCartToUpdate.add(singletonArticle);
            preferenceCart.addArticlesToCart((ArrayList<ArticlePanier>) articlesInCartToUpdate);
            nombreArticlePanier = articlesInCartToUpdate.size();
        }
        preferenceCart.addArticleCount(nombreArticlePanier);
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        private int position;
        public MyMenuItemClickListener(int position) {
            this.position = position;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            GestionArticle gestionArticle = new GestionArticle(mContext);
            switch (menuItem.getItemId()) {
                case R.id.modifier:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, ModifierProduitActivity.class);
                    intent.putExtra(Extra.MODIFIER_ARTICLE, articlesList.get(position));
                    mContext.startActivity(intent);
                    return true;
                case R.id.supprimer:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    gestionArticle.supprimerArticle(articlesList.get(position).getRefArticle());
                    return true;
                default:
            }
            return false;
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {

        return articlesList.size();
    }

    public ArticleCustom getItem(int position){
        return articlesList.get(position);
    }
  
}
