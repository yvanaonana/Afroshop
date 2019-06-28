package com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.Article;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.ArticleCustom;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.traitement.GestionArticle;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.Extra;
import com.tsafack.tkswif.afroshop.serveur.gestionImage.FilePath;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ModifierProduitActivity extends AppCompatActivity {

    String selectedPath = "";
    private String fileName = null;
    String selectedFilePath;
    int serverResponseCode = 0;

    Bitmap bitmap;

    EditText inputLibelle, inputDescription, inputQuantite, inputPrix, inputRegion, inputVille, inputQuartier;
    String libelle, description, quantite_chaine, prix_chiane, region, ville, quartier;
    double prix, quantite;

    ArticleCustom articleCustom = new ArticleCustom();

    Button enregistrer;

    TextView click;
    Button addImage;
    CircleImageView circleImageView1, circleImageView2, circleImageView3, circleImageView4;
    private int resId = 0;

    private static int SELECT_IMAGE = 3;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_produit);

        articleCustom = getIntent().getParcelableExtra(Extra.MODIFIER_ARTICLE);
        initView();

        click = (TextView) findViewById(R.id.ajoutproduit_image_add_clicktext);
        addImage = (Button) findViewById(R.id.ajoutproduit_image_add);
//        circleImageView1 = (CircleImageView) findViewById(R.id.image1);
//        circleImageView2 = (CircleImageView) findViewById(R.id.image2);
//        circleImageView3 = (CircleImageView) findViewById(R.id.image3);
//        circleImageView4 = (CircleImageView) findViewById(R.id.image4);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();

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

    private void enregistrerArticle() {

        Resources res = getResources();

        initVariable();

        if (verifierChamps()){
            Article article = new Article();
            article.setLibArticle(libelle);
            article.setDescriptionArticle(description);
            article.setPrixUnitaireArticle(prix);
            article.setQteInitialeArticle(quantite);

            GestionArticle gestionArticle = new GestionArticle(this);
            gestionArticle.modifierArticle(article);
        }else {
            Toast.makeText(this, res.getString(R.string.erreur_champ_non_remplis), Toast.LENGTH_LONG).show();
        }

    }

    private boolean verifierChamps() {

        return !libelle.isEmpty() && !libelle.equalsIgnoreCase("") &&
                !description.isEmpty() && !description.equalsIgnoreCase("") &&
                !prix_chiane.isEmpty() && !prix_chiane.equalsIgnoreCase("") &&
                !quantite_chaine.isEmpty() && !quantite_chaine.equalsIgnoreCase("") &&
                !ville.isEmpty() && !ville.equalsIgnoreCase("");

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

        try{
            prix = Double.valueOf(prix_chiane);
            quantite = Double.valueOf(quantite_chaine);
        }catch (Exception e){
            Toast.makeText(this, res.getString(R.string.erreur_convertion), Toast.LENGTH_SHORT).show();
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
        enregistrer = (Button) findViewById(R.id.ajoutproduit_input_submit);

        inputLibelle.setText(articleCustom.getLibArticle());
        inputDescription.setText(articleCustom.getDescriptionArticle());
        inputPrix.setText(articleCustom.getPrixUnitaireArticle()+"");
        inputQuantite.setText(articleCustom.getQteInitialeArticle()+"");
//        inputQuartier.setText(articleCustom.);

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
                selectedFilePath = FilePath.getPath(this, selectedFileUri);
                String[] parts = selectedFilePath.split("/");
                fileName = parts[parts.length - 1];
//                TextView text = (TextView) findViewById(R.id.titreimage);
//                nomDuFichier = fileName;
//                text.setText(fileName);

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedFileUri);
                    switch (resId){
                        case 0:
                            circleImageView1.setImageBitmap(bitmap);
                            circleImageView1.setVisibility(View.VISIBLE);
                            click.setVisibility(View.GONE);
                            addImage.setVisibility(View.VISIBLE);
                            resId = 1;
                            break;
                        case 1:
                            circleImageView2.setImageBitmap(bitmap);
                            circleImageView2.setVisibility(View.VISIBLE);
                            resId=2;
                            break;
                        case 2:
                            circleImageView3.setImageBitmap(bitmap);
                            circleImageView3.setVisibility(View.VISIBLE);
                            resId=3;
                            break;
                        case 3:
                            circleImageView4.setImageBitmap(bitmap);
                            circleImageView4.setVisibility(View.VISIBLE);
                            addImage.setBackgroundColor(Color.parseColor("#777777"));
                            break;
                        default:
                                Toast.makeText(this, "ah bon", Toast.LENGTH_LONG).show();
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

}
