package com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.SectionDataModel;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.Agent;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.ArticleCustom;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.ArticlePanier;
import com.tsafack.tkswif.afroshop.entities.gridViewModel;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.SharePreferenceCart;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.traitement.GestionCommande;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.Extra;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.adapter.RecyclerViewDataAdapter;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.suiviDesCommandes.activities.CardActivity;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.suiviDesCommandes.activities.ListeAgencesActivity;
import com.tsafack.tkswif.afroshop.gestionDesUtilisateurs.configuration.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class Page_de_detaille_du_produit extends AppCompatActivity {


    RoundedImageView imageArticleOutput;
    TextView libelleArticleOutput;
    TextView categorieArticleOutput;
    TextView qteCommanderArticleOutput;
    TextView nombreAimeArticleOutput;
    ImageView AimeArticle;
    TextView descriptionArticleOutput;
    Button addToCard;
    Spinner inputAgent;
    EditText inputQuantite;
    ImageView imageArticle1, imageArticle2, imageArticle3, imageArticle4, imageArticle5, imageArticle6, panier;

    private int nombreArticlePanier = 0;

    List<Agent> agents = new ArrayList<>();

    ArrayList<SectionDataModel> allSampleData;
    private MenuItem mSearchItem;
    private Toolbar mToolbar;
    private SessionManager session;
    public static int[][] images = {{R.drawable.outils7, R.drawable.outils4, R.drawable.engin3, R.drawable.engrais },
            {R.drawable.produit3, R.drawable.produit8, R.drawable.produit9, R.drawable.semences2},
            {R.drawable.formation2, R.drawable.formation3, R.drawable.formation9, R.drawable.fomation8},
            {R.drawable.formation1, R.drawable.formation4, R.drawable.formation5, R.drawable.formation7},
            {R.drawable.engin5, R.drawable.fomation8, R.drawable.produit4, R.drawable.semences1}};
    String[][] text = {{"Pesticides", "Materiels", "Machines", "Engrais" },
            {"Legumes", "Fruits", "Tubercules", "Graines"},
            {"potager", "irrigation", "apiculture", "formation disponible"},
            {"concours", "concours", "opportunite", "opportunite"},
            {"promotion", "financement disponible", "en vente", "intrans selectionnes"}};

    ArrayList<ArticleCustom> articleCustoms = new ArrayList<>();
    ArticleCustom articleCustom = new ArticleCustom();
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_de_detaille_du_produits);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarProduitsdetaille);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setDisplayShowHomeEnabled(true);


        initCollapsingToolbar();

        if (getIntent() != null) {
            articleCustom = getIntent().getParcelableExtra(Extra.ARTICLE_CHOISI);
            articleCustoms = getIntent().getParcelableArrayListExtra(Extra.ARTICLES_SEMBLABLES);
        }

        try {
            Glide.with(this).load(R.drawable.bg_img).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

        initViews();

        addToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajouterUnProduitAuPanier(view);
            }
        });

        allSampleData = new ArrayList<SectionDataModel>();
        // ce code perùmet d'initialiser le la pge d'acceuil
        createDummyData();
        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);
        my_recycler_view.setHasFixedSize(true);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(allSampleData,this);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);

    }

    private void ajouterUnProduitAuPanier(View view) {
        final Resources res = getApplicationContext().getResources();

        LayoutInflater factory = LayoutInflater.from(Page_de_detaille_du_produit.this);
        final View alertDialog = factory.inflate(R.layout.activity_add_product_to_card, null);

        inputQuantite = (EditText) alertDialog.findViewById(R.id.addproducttocard_input_quantite);
        inputAgent = (Spinner) alertDialog.findViewById(R.id.addproduittocard_input_agent);

        initialisationAgent();

        AlertDialog dialog = new AlertDialog.Builder(Page_de_detaille_du_produit.this)
                .setTitle(res.getString(R.string.text_ajouter_au_panier))
                .setMessage(res.getString(R.string.ajouter_panier_message))
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
                            Toast.makeText(Page_de_detaille_du_produit.this, res.getString(R.string.erreur_convertion),
                                    Toast.LENGTH_SHORT).show();
                        }

                        addArticleToCart(new ArticlePanier("sjhdgds", articleCustom.getLibArticle(),
                                agents.get(position).getIdAgent(), articleCustom.getPrixUnitaireArticle(), quantite,
                                articleCustom.getRefArticle(), articleCustom.getIdFournisseurArticle(),
                                articleCustom.getRegionArticle()));
                        Intent intent = new Intent(getApplicationContext(), CardActivity.class);
                        intent.putExtra(Extra.ARTICLE_COMMANDE, articleCustom);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(res.getString(R.string.annuler), null)
                .create();
        dialog.show();
    }

    private void initialisationAgent() {

        GestionCommande gestionCommande = new GestionCommande(Page_de_detaille_du_produit.this);
        agents = gestionCommande.listerAgent(inputAgent, articleCustom.getRegionArticle());

    }

    private void initViews() {

        libelleArticleOutput = (TextView) findViewById(R.id.titreDuProduits);
        descriptionArticleOutput = (TextView) findViewById(R.id.DescriptionDuProduits);
        qteCommanderArticleOutput = (TextView) findViewById(R.id.AfficherNombreDeCommandesAffectue);
        imageArticleOutput = (RoundedImageView) findViewById(R.id.itemImageProduits);
        categorieArticleOutput = (TextView) findViewById(R.id.categorieArticle);
        nombreAimeArticleOutput = (TextView) findViewById(R.id.nombreaimearticle);
        addToCard = (Button) findViewById(R.id.floatingActionButton2);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
//        imageArticle1 = (ImageView) findViewById(R.id.img);
//        imageArticle2 = (ImageView) findViewById(R.id.im);
//        imageArticle3 = (ImageView) findViewById(R.id.images2);
//        imageArticle4 = (ImageView) findViewById(R.id.images3);
//        imageArticle5 = (ImageView) findViewById(R.id.images4);
//        imageArticle6 = (ImageView) findViewById(R.id.images5);
        panier = (ImageView) findViewById(R.id.panier);

        panier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Page_de_detaille_du_produit.this, CardActivity.class);
                startActivity(intent);
            }
        });

        libelleArticleOutput.setText(articleCustom.getLibArticle());
        descriptionArticleOutput.setText(articleCustom.getDescriptionArticle());
        qteCommanderArticleOutput.setText(" Plus de " + (articleCustom.getQteInitialeArticle() -
                articleCustom.getQteRestanteArticle()) + " de KG commandé ");

        categorieArticleOutput.setText(articleCustom.getCategorieArticle());

//        nombreAimeArticleOutput.setText(articleCustom.getNombreAimeArticle());

        //je place sur l'image de l'article qui est affichee des le debut


        if (articleCustom.getImages() != null){
            Glide.with(this).load(articleCustom.getImages().get(0).getUrlMedia())
                    .placeholder(R.drawable.empty_flag_64px).error(R.drawable.errorcloudgreen).into(imageArticleOutput);
            // je recupere les images et je les affecte a des imageViews
//            ImageView[] imageViews = new ImageView[]{imageArticle1, imageArticle2, imageArticle3,
//                    imageArticle4, imageArticle5, imageArticle6};
//            List<ImageView> imageViews = new ArrayList<>();

            for (int i = 0 ; i<articleCustom.getImages().size(); i++){
                ImageView imageView = new ImageView(getApplicationContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(layoutParams);
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                imageView.setPadding(0, 0, 20, 0);
                imageView.setAdjustViewBounds(true);
                Glide.with(this).load(articleCustom.getImages().get(i).getUrlMedia())
                        .placeholder(R.drawable.empty_flag_64px).error(R.drawable.errorcloudgreen)
                        .into(imageView);
                linearLayout.addView(imageView);
            }

//            for (int i = 0 ; i < articleCustom.getImages().size(); i++){
//                Toast.makeText(this, articleCustom.getImages().get(i).getUrlMedia(), Toast.LENGTH_LONG).show();
//
//            }
//            for(int j = articleCustom.getImages().size(); j < 7 ; j++){
//                Glide.with(this).load(0).into(imageViews[j]);
//            }
        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    public void addArticleToCart(ArticlePanier singletonArticle){
        SharePreferenceCart preferenceCart = new SharePreferenceCart(Page_de_detaille_du_produit.this);
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

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbarProduitDetaille);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbarProduitsdetaille);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    //collapsingToolbar.setTitle("Produits");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setScrollbarFadingEnabled(true);
                    isShow = true;
                }
            }
        });
    }

    public void createDummyData() {
        for (int i = 1; i <= 3; i++) {
            SectionDataModel dm;
            dm = new SectionDataModel();
            if(i==1) {
                dm.setHeaderTitle("Dans la même categories ");

            }
            if(i==2) {
                dm.setHeaderTitle("Découvrez également");
            }
            if(i==3) {
                dm.setHeaderTitle(" Les Nouveautés");
            }


            ArrayList<gridViewModel> singleItem = new ArrayList<gridViewModel>();

            for (int j = 0; j < 4; j++ )  {
                singleItem.add(new gridViewModel(text[i-1][j], images[i-1][j]));
            }
            dm.setAllItemsInSection(singleItem);
            allSampleData.add(dm);
        }
    }

}
