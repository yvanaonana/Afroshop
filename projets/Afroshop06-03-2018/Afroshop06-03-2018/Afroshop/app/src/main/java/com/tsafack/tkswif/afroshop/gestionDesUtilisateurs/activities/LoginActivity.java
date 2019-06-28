package com.tsafack.tkswif.afroshop.gestionDesUtilisateurs.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.Utilisateur;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.activities.AjouterProduitActivity;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.activities.categories_articles;
import com.tsafack.tkswif.afroshop.gestionDesUtilisateurs.configuration.GestionUtilisateur;
import com.tsafack.tkswif.afroshop.gestionDesUtilisateurs.configuration.SessionManager;
import com.tsafack.tkswif.afroshop.gestionDesUtilisateurs.services.EnregistrementUser;
import com.tsafack.tkswif.afroshop.serveur.ActionsRequettes;
import com.tsafack.tkswif.afroshop.serveur.AppelServiceWeb;
import com.tsafack.tkswif.afroshop.serveur.ClesCommunicationServeur;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    SessionManager sessionManager;

    EditText inputPseudonime;
    EditText inputPassword;

    Button btnLoginUbmit;

    String pseudo;
    String password;

    TextView inscription;

    //firebase
    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;

    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";

    View rootLayout;

    private int revealX;
    private int revealY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
        mDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        inputPseudonime = (EditText) findViewById(R.id.login_input_pseudo);
        inputPassword = (EditText) findViewById(R.id.login_input_password);
        btnLoginUbmit = (Button) findViewById(R.id.login_input_submit);
        inscription = (TextView)findViewById(R.id.login_text_signup);

        if (sessionManager.isLoggedIn()){
            Resources res = getResources();
            Toast.makeText(this, res.getString(R.string.message_deja_logger), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, categories_articles.class);
            startActivity(intent);
            finish();
        }

        btnLoginUbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitLogin(view);
            }
        });
        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),InscriptionActivity.class);
                startActivity(i);
                finish();
            }
        });

        final Intent intent = getIntent();

        rootLayout = findViewById(R.id.layout_login);

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

    void submitLogin(View view){
        pseudo = inputPseudonime.getText().toString().trim();
        password= inputPassword.getText().toString().trim();

        if (!pseudo.isEmpty() && !pseudo.equalsIgnoreCase("") && !password.isEmpty() &&
                !password.equalsIgnoreCase("")){
            connecterUser(pseudo, password);
        }else {
            Toast.makeText(getApplicationContext(), getApplicationContext().getResources().
                    getString(R.string.erreur_champ_non_remplis), Toast.LENGTH_LONG).show();
        }

    }

    public void connecterUser(final String email, final String password){
        final Resources res = getResources();
        HashMap<String, String> values = new HashMap<>();
        values.put(ClesCommunicationServeur.EMAIL_UTILISATEUR, email);
        values.put(ClesCommunicationServeur.PASSWORD_UTILISATEUR, password);
        AppelServiceWeb serviceWeb = new AppelServiceWeb() {
            @Override
            public void postExecute(String response) {
                try {
                    JSONObject array = new JSONObject(response);
                    if (array.getInt("error") == 0) {

                        JSONObject object = array.getJSONObject("data");
                        Utilisateur user = new Utilisateur();
                        user.setIdUtilisateur(object.getString(ClesCommunicationServeur.ID_UTILISATEUR));
                        user.setNomUtilisateur(object.getString(ClesCommunicationServeur.NOM_UTILISATEUR));
                        user.setDateInscriptionUtilisateur(object.getString(ClesCommunicationServeur.DATE_INSCRIPTION_UTILISATEUR));
//                        user.setDateNaissanceUtilisateur(Date.valueOf(object.getString(ClesCommunicationServeur.DATE_NAISSANCE_UTILISATEUR)));
                        user.setEmailUtilisateur(object.getString(ClesCommunicationServeur.EMAIL_UTILISATEUR));
                        user.setLocaliteUtilisateur(object.getString(ClesCommunicationServeur.LOCALITE_UTILISATEUR));
                        user.setPaysUtilisateur(object.getString(ClesCommunicationServeur.PAYS_UTILISATEUR));
                        user.setRegionUtilisateeur(object.getString(ClesCommunicationServeur.REGION_UTILISATEUR));
                        user.setTelephoneUtilisateur(object.getString(ClesCommunicationServeur.TELEPHONE_UTILISATEUR));
                        user.setVilleUtilisateur(object.getString(ClesCommunicationServeur.VILLE_UTILISATEUR));
                        sessionManager.setUser(user);
                        sessionManager.setLogin(true);

//                        logInUsers(email, password);
                        Intent intent = new Intent(LoginActivity.this, categories_articles.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), res.getString(R.string.connexion_echoue), Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), res.getString(R.string.erreur_convertion) + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }

        };
        serviceWeb.appelService(LoginActivity.this, ActionsRequettes.ACTION_CONNECTER_UTILISATEUR, values, Request.Method.POST, false);
    }

    private void logInUsers(String email, String password) {
        mDialog.setMessage("Please wait...");
        mDialog.show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mDialog.dismiss();
                if (!task.isSuccessful()) {
                    //error loging
                    Toast.makeText(LoginActivity.this, "Error " + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(LoginActivity.this, categories_articles.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
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
}
