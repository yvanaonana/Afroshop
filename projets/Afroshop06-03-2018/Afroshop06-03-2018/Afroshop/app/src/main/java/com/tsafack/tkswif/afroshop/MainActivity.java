package com.tsafack.tkswif.afroshop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.tsafack.tkswif.afroshop.entities.SectionDataModel;
import com.tsafack.tkswif.afroshop.entities.gridViewModel;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.adapter.RecyclerViewDataAdapter;
import com.tsafack.tkswif.afroshop.gestionDesUtilisateurs.activities.InscriptionActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView inscription;
    ArrayList<SectionDataModel> allSampleData;
    private MenuItem mSearchItem;
    private Toolbar mToolbar;

    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";

    View rootLayout;

    private int revealX;
    private int revealY;
    private DrawerLayout mDrawerLayout;
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

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allSampleData = new ArrayList<SectionDataModel>();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // ce code perùmet d'initialiser le la pge d'acceuil
        createDummyData();
        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);
        my_recycler_view.setHasFixedSize(true);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(allSampleData,this);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);

        inscription = (TextView) findViewById(R.id.login_text_signup);
        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),InscriptionActivity.class);
                startActivity(i);
            }
        });

        final Intent intent = getIntent();

        rootLayout = findViewById(R.id.drawer_layout);

        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {
            rootLayout.setVisibility(View.INVISIBLE);

            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0);
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0);


            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        revealActivity(revealX, revealY);
                        rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        } else {
            rootLayout.setVisibility(View.VISIBLE);
        }
    }


    protected void revealActivity(int x, int y) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);

            // create the animator for this view (the start radius is zero)
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0, finalRadius);
            circularReveal.setDuration(700);
            circularReveal.setInterpolator(new AccelerateInterpolator());
            // make the view visible and start the animation
            rootLayout.setVisibility(View.VISIBLE);
            circularReveal.start();
        } else {
            finish();
        }
    }

    protected void unRevealActivity() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            finish();
        } else {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                    rootLayout, revealX, revealY, finalRadius, 0);

            circularReveal.setDuration(700);
            circularReveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    rootLayout.setVisibility(View.INVISIBLE);
                    finish();
                }
            });
            circularReveal.start();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }
    public void createDummyData() {
        for (int i = 1; i <= 3; i++) {
            SectionDataModel dm;
            dm = new SectionDataModel();
            if(i==1) {
                dm.setHeaderTitle("Rayon intrants ");

            }
            if(i==2) {
                dm.setHeaderTitle("Rayon produits ");
            }
//            if(i==3) {
//                dm.setHeaderTitle("Formations");
//            }
//            if(i==4) {
//                dm.setHeaderTitle("Financements");
//            }
            if(i==3) {
                dm.setHeaderTitle("Ce qui pourait vous interresser");
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