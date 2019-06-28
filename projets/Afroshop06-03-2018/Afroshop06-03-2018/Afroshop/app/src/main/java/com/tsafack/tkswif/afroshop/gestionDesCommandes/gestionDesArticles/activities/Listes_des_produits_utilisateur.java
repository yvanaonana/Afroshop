package com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.CategorieArticle;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.ArticleCustom;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.traitement.GestionArticle;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.Extra;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.adapter.MyArticlesAdapter;

import java.util.ArrayList;
import java.util.List;

public class Listes_des_produits_utilisateur extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyArticlesAdapter adapter;
    private RecyclerView.LayoutManager LayoutManager;
    private List<ArticleCustom> ListeProduits= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listes_desproduits);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_affichageDesProduits);
        recyclerView.setHasFixedSize(true);
        LayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LayoutManager);


        prepareAlbums();

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArticleCustom articleCustom = adapter.getItem(recyclerView.getChildAdapterPosition(view));
                Intent intent = new Intent(Listes_des_produits_utilisateur.this, Page_de_detaille_du_produit.class);
                intent.putExtra(Extra.ARTICLE_CHOISI, articleCustom);
                // ici nous avons une liste de produits semblables ou de la mm categorie nous allons decider apres
                intent.putParcelableArrayListExtra(Extra.ARTICLES_SEMBLABLES, (ArrayList<? extends Parcelable>) ListeProduits);
                startActivity(intent);
            }
        });

    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        GestionArticle gestionArticle = new GestionArticle(this);
        adapter = gestionArticle.listerArticlesUtilisateur(recyclerView);
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
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




    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
