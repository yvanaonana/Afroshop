package com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.Article;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.CategorieArticle;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.traitement.GestionArticle;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.traitement.GestionCategorie;
import com.tsafack.tkswif.afroshop.serveur.gestionImage.FilePath;
import com.tsafack.tkswif.afroshop.sqLite.GestionCategorieArtice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AjouterProduitActivity extends AppCompatActivity {

    String selectedPath = "";
    private String fileName = null;
    String selectedFilePath = "";
    int serverResponseCode = 0;

    Bitmap bitmap;

    EditText inputLibelle, inputDescription, inputQuantite, inputPrix, inputRegion, inputVille,
            inputQuartier, inputPays, inputNumeroNormalisation;
    String libelle, categorie, description, quantite_chaine, prix_chiane, region, ville, quartier, pays,
            unite, numeroNormalisation;
    double prix, quantite;

    Spinner inputCategorie, inputUnite;
    CheckBox normalisation;

    LinearLayout linearLayout;

    Button enregistrer;

    GestionCategorieArtice gestionCategorieArtice;

    TextView click;
    Button addImage;
    List<String> filePaths = new ArrayList<>();
//    CircleImageView circleImageView1, circleImageView2, circleImageView3, circleImageView4, circleImageView5;
    private int resId = 0;


    GestionCategorie gestion;
    ArrayList<CategorieArticle> categorieArticles;

    private static int SELECT_IMAGE = 3;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_produit);

        initView();
        gestion = new GestionCategorie(this);

        gestionCategorieArtice = new GestionCategorieArtice(this);

        initCategorie();

        initUnite();

        normalisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (normalisation.isChecked()){
                    inputNumeroNormalisation.setVisibility(View.VISIBLE);
                }
                else
                    inputNumeroNormalisation.setVisibility(View.GONE);
            }
        });

        click = (TextView) findViewById(R.id.ajoutproduit_image_add_clicktext);
        addImage = (Button) findViewById(R.id.ajoutproduit_image_add);
//        circleImageView1 = (CircleImageView) findViewById(R.id.image1);
//        circleImageView2 = (CircleImageView) findViewById(R.id.image2);
//        circleImageView3 = (CircleImageView) findViewById(R.id.image3);
//        circleImageView4 = (CircleImageView) findViewById(R.id.image4);
//        circleImageView5 = (CircleImageView) findViewById(R.id.image5);

//        clickImageViews();

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
                click.setVisibility(View.GONE);
                addImage.setVisibility(View.VISIBLE);
            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enregistrerArticle();
            }
        });
    }

//    private void clickImageViews() {
//
//        circleImageView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                resId = 0;
//                openGallery();
//            }
//        });
//
//        circleImageView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                resId = 1;
//                openGallery();
//            }
//        });
//
//        circleImageView3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                resId = 2;
//                openGallery();
//            }
//        });
//
//        circleImageView4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                resId = 3;
//                openGallery();
//            }
//        });
//
//        circleImageView5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                resId = 4;
//                openGallery();
//            }
//        });
//    }

    private void enregistrerArticle() {

        Resources res = getResources();

        initVariable();

        if (verifierChamps()){
            Article article = new Article();
            article.setLibArticle(libelle);
            article.setIdCategorieArticle(categorie);
            article.setDescriptionArticle(description);
            article.setPrixUnitaireArticle(prix);
            article.setQteInitialeArticle(quantite);
            article.setRegionArticle(region);
            article.setQuartierArticle(quartier);
            article.setVilleArticle(ville);
            article.setNumeroNormalisationArticle(numeroNormalisation);
            article.setMentionArticle(false);
            article.setUniteArticle(unite);

            GestionArticle gestionArticle = new GestionArticle(this);
            gestionArticle.enregistrerArticle(article, (ArrayList<String>) filePaths);
        }else {
            Toast.makeText(this, res.getString(R.string.erreur_champ_non_remplis), Toast.LENGTH_LONG).show();
        }

    }

    private boolean verifierChamps() {

        if (normalisation.isChecked()){
            return !libelle.isEmpty() && !libelle.equalsIgnoreCase("") &&
                    !description.isEmpty() && !description.equalsIgnoreCase("") &&
                    !prix_chiane.isEmpty() && !prix_chiane.equalsIgnoreCase("") &&
                    !quantite_chaine.isEmpty() && !quantite_chaine.equalsIgnoreCase("") &&
                    !ville.isEmpty() && !ville.equalsIgnoreCase("") && !unite.isEmpty() &&
                    !unite.equalsIgnoreCase("") && !pays.isEmpty() && !pays.equalsIgnoreCase("")
                    && !numeroNormalisation.isEmpty() && !numeroNormalisation.equalsIgnoreCase("");
        }
        return !libelle.isEmpty() && !libelle.equalsIgnoreCase("") &&
                !description.isEmpty() && !description.equalsIgnoreCase("") &&
                !prix_chiane.isEmpty() && !prix_chiane.equalsIgnoreCase("") &&
                !quantite_chaine.isEmpty() && !quantite_chaine.equalsIgnoreCase("") &&
                !ville.isEmpty() && !ville.equalsIgnoreCase("") && !unite.isEmpty() &&
                !unite.equalsIgnoreCase("") && !pays.isEmpty() && !pays.equalsIgnoreCase("");

    }

    private void initVariable() {

        Resources res = getResources();

        libelle = inputLibelle.getText().toString().trim();
        description = inputDescription.getText().toString().trim();
        prix_chiane = inputPrix.getText().toString().trim();
        quantite_chaine = inputQuantite.getText().toString().trim();
        quartier = inputQuartier.getText().toString().trim();
        region = inputRegion.getText().toString().trim();
        ville = inputVille.getText().toString().trim();
        pays = inputPays.getText().toString().trim();
        unite = inputUnite.getSelectedItem().toString().trim();

        if (normalisation.isChecked()){
            numeroNormalisation = inputNumeroNormalisation.getText().toString().trim();
        }

        int position = inputCategorie.getSelectedItemPosition();
        CategorieArticle article = categorieArticles.get(position);
        categorie = article.getIdCategorieArticle();

        try{
            prix = Double.valueOf(prix_chiane);
            quantite = Double.valueOf(quantite_chaine);
        }catch (Exception e){
            Toast.makeText(AjouterProduitActivity.this, res.getString(R.string.erreur_convertion), Toast.LENGTH_SHORT).show();
        }

    }

    //ceci c'est pour initialiser les elements de l'interface
    //je n'ai pas initialiser la categorie car elle doit etre prise depuis la base de donnees et
    //affiche dans un spinner.

    private void initView() {

        inputLibelle = (EditText) findViewById(R.id.ajoutproduit_input_nom);
        inputDescription = (EditText) findViewById(R.id.ajoutproduit_input_description);
        inputPrix = (EditText) findViewById(R.id.ajoutproduit_input_prix);
        inputQuantite = (EditText) findViewById(R.id.ajoutproduit_input_quantite);
        inputQuartier = (EditText) findViewById(R.id.ajoutproduit_input_quartier);
        inputRegion = (EditText) findViewById(R.id.ajoutproduit_input_region);
        inputVille = (EditText) findViewById(R.id.ajoutproduit_input_ville);
        inputPays = (EditText) findViewById(R.id.ajoutproduit_input_pays);
        inputUnite = (Spinner) findViewById(R.id.ajoutproduit_input_unite);
        inputNumeroNormalisation = (EditText) findViewById(R.id.ajoutproduit_input_numeronormalisation);
        enregistrer = (Button) findViewById(R.id.ajoutproduit_input_submit);
        inputCategorie = (Spinner) findViewById(R.id.ajoutproduit_input_categorie);
        normalisation = (CheckBox) findViewById(R.id.ajoutproduit_checkboox_produitnormalise);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout_ajouterproduit);

    }

    public void openGallery (){
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("image/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent, "Choose image to Upload.."), SELECT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_IMAGE) {
                if (data == null) {
                    //no data present
                    return;
                }

                Uri selectedFileUri = data.getData();
                Toast.makeText(this, selectedFileUri.toString(), Toast.LENGTH_LONG).show();
                selectedFilePath = FilePath.getPath(this, selectedFileUri);

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedFileUri);

                    CircleImageView circleImageView = new CircleImageView(getApplicationContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(120, 120);
                    circleImageView.setLayoutParams(layoutParams);
//                    circleImageView.setScaleType(ImageView.ScaleType.CENTER);
                    circleImageView.setPadding(0, 0, 10, 0);
                    circleImageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            resId--;
                            openGallery();
                        }
                    });
//                    circleImageView.setAdjustViewBounds(true);
                    circleImageView.setImageBitmap(bitmap);
                    linearLayout.addView(circleImageView);
                    filePaths.add(selectedFilePath);

                    resId++;

                    if (resId == 5){
                        addImage.setClickable(false);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (selectedFilePath != null && !selectedFilePath.equals("")) {
//                    image = getStringImage(bitmap);
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            //creating new thread to handle Http Operations
//                            doFileUpload(selectedFilePath);
//                        }
//                    }).start();
                } else {
                    Toast.makeText(this, "impossible de recuperer le fichier", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == SELECT_IMAGE) {
//                if (data == null) {
//                    //no data present
//                    return;
//                }
//
//                Uri selectedFileUri = data.getData();
//                Toast.makeText(this, selectedFileUri.toString(), Toast.LENGTH_LONG).show();
//                selectedFilePath = FilePath.getPath(this, selectedFileUri);
//
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedFileUri);
//                    switch (resId){
//                        case 0:
//                            circleImageView1.setImageBitmap(bitmap);
//                            circleImageView1.setVisibility(View.VISIBLE);
//                            click.setVisibility(View.GONE);
//                            addImage.setVisibility(View.VISIBLE);
//                            filePaths[0] = selectedFilePath;
//                            resId = 1;
//                            break;
//                        case 1:
//                            circleImageView2.setImageBitmap(bitmap);
//                            circleImageView2.setVisibility(View.VISIBLE);
//                            filePaths[1] = selectedFilePath;
//                            resId=2;
//                            break;
//                        case 2:
//                            circleImageView3.setImageBitmap(bitmap);
//                            circleImageView3.setVisibility(View.VISIBLE);
//                            filePaths[2] = selectedFilePath;
//                            resId=3;
//                            break;
//                        case 3:
//                            circleImageView4.setImageBitmap(bitmap);
//                            circleImageView4.setVisibility(View.VISIBLE);
//                            filePaths[3] = selectedFilePath;
//                            resId = 4;
//                            break;
//                        case 4:
//                            circleImageView5.setImageBitmap(bitmap);
//                            circleImageView5.setVisibility(View.VISIBLE);
//                            filePaths[4] = selectedFilePath;
//                            addImage.setBackgroundColor(Color.parseColor("#777777"));
//                            addImage.setClickable(false);
//                        default:
//                                Toast.makeText(this, "ah bon", Toast.LENGTH_LONG).show();
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                if (selectedFilePath != null && !selectedFilePath.equals("")) {
////                    image = getStringImage(bitmap);
////                    new Thread(new Runnable() {
////                        @Override
////                        public void run() {
////                            //creating new thread to handle Http Operations
////                            doFileUpload(selectedFilePath);
////                        }
////                    }).start();
//                } else {
//                    Toast.makeText(this, "impossible de recuperer le fichier", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//
//    }

    public void initUnite (){
        ArrayList<String> values = new ArrayList<>();
        values.add("KG");
        values.add("sacs 25 KG");
        values.add("sacs 50 KG");
        values.add("sacs 100 KG");
        values.add("cajots");
        values.add("tonne");

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, values);
        inputUnite.setAdapter(adapter);
    }

    public void initCategorie(){

        ArrayList<String> categories = new ArrayList<>();
        categorieArticles = gestionCategorieArtice.getCategorieArticles();

        for (CategorieArticle categorie : categorieArticles){
            categories.add(categorie.getLibelleCategorieArticle());
        }

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, categories);
        inputCategorie.setAdapter(adapter);
    }
}
