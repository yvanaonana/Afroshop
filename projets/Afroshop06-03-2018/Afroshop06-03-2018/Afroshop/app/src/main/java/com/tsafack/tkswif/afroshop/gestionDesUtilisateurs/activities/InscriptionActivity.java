package com.tsafack.tkswif.afroshop.gestionDesUtilisateurs.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.User;
import com.tsafack.tkswif.afroshop.serveur.ActionsRequettes;
import com.tsafack.tkswif.afroshop.serveur.AppelServiceWeb;
import com.tsafack.tkswif.afroshop.serveur.ClesCommunicationServeur;
//import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.R;

import java.util.HashMap;

public class InscriptionActivity extends AppCompatActivity {

    TextView connexion;

    EditText inputNom;
    EditText inputPrenom;
//    EditText inputDateNaissance;
    EditText inputEmail;
    EditText inputTelephone;
    EditText inputemail;
    EditText inputLocalite;
    EditText inputVille;
    EditText inputPassword;
    EditText inputPasswordConfirm;

    Spinner inputRegion;
    Spinner inputPays;
    Spinner inputGroupeUtilisateur;
    Spinner inputTypeUtilisateur;

    Button sinscrire;

    String nom;
    String prenom;
//    String dateNaissance;
    String email;
    String telephone;
    String password;
    String passwordConfirm;
    String pays;
    String region;
    String localite;
    String ville;
    String groupeUtilisateur;
    String typeUtilisateur;

    //Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mUsersDBref;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        mAuth = FirebaseAuth.getInstance();

        mDialog = new ProgressDialog(this);

        connexion = (TextView)findViewById(R.id.inscription_linkTo_connexion);
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });

        inputNom = (EditText) findViewById(R.id.inscription_input_nom);
//        inputDateNaissance = (EditText) findViewById(R.id.inscription_input_dateNaissance);
        inputEmail = (EditText) findViewById(R.id.inscription_input_email);
        inputPrenom = (EditText) findViewById(R.id.inscription_input_prenom);
        inputTelephone = (EditText) findViewById(R.id.inscription_input_telephone);
        inputVille = (EditText) findViewById(R.id.inscription_input_ville);
        inputLocalite = (EditText) findViewById(R.id.inscription_input_localite);
        inputPassword = (EditText) findViewById(R.id.inscription_input_password);
        inputPasswordConfirm = (EditText) findViewById(R.id.inscription_input_confirmPassword);

        inputRegion = (Spinner) findViewById(R.id.inscription_input_region);
        inputPays = (Spinner) findViewById(R.id.inscription_input_pays);
        inputGroupeUtilisateur = (Spinner) findViewById(R.id.inscription_input_groupe_utilisateur);
        inputTypeUtilisateur = (Spinner) findViewById(R.id.inscription_input_type_utilisateur);

        initialisationRegion();
        initialisationPays();
        initialisationGroupeUtilisateur();
        initialisationTypeUtilisateur();

        sinscrire = (Button) findViewById(R.id.inscription_input_submit);

        sinscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void initialisationTypeUtilisateur() {
        Resources res = getResources();
        String[] regions = {"client", "fournisseur"};

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, regions);
        inputTypeUtilisateur.setAdapter(adapter);
    }

    private void initialisationGroupeUtilisateur() {
        Resources res = getResources();
        String[] regions = {"organisation", "particulier"};

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, regions);
        inputGroupeUtilisateur.setAdapter(adapter);
    }

    void registerUser(){

        initVariables();
        Resources res = getResources();

        if (testEmpty()){
            if (passwordConfirm.equals(password)){
                HashMap<String, String> values = new HashMap<>();
                values.put(ClesCommunicationServeur.NOM_UTILISATEUR, nom + " " + prenom);
//                values.put(ClesCommunicationServeur.DATE_NAISSANCE_UTILISATEUR, dateNaissance);
                values.put(ClesCommunicationServeur.ID_UTILISATEUR, email);
                values.put(ClesCommunicationServeur.TELEPHONE_UTILISATEUR, telephone);
                values.put(ClesCommunicationServeur.PAYS_UTILISATEUR, pays);
                values.put(ClesCommunicationServeur.LOCALITE_UTILISATEUR, localite);
                values.put(ClesCommunicationServeur.REGION_UTILISATEUR, region);
                values.put(ClesCommunicationServeur.VILLE_UTILISATEUR, ville);
                values.put(ClesCommunicationServeur.PASSWORD_UTILISATEUR, password);
                values.put(ClesCommunicationServeur.GROUPE_UTILISATEUR, groupeUtilisateur);
                values.put(ClesCommunicationServeur.TYPE_UTILISATEUR, typeUtilisateur);
                AppelServiceWeb serviceWeb = new AppelServiceWeb() {
                    @Override
                    public void postExecute(String response) {

//                        signUpUserWithFirebase(nom, email, password);
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }

                };
                serviceWeb.appelService(InscriptionActivity.this, ActionsRequettes.ACTION_INSCRIPTION_UTILISATEUR, values, Request.Method.POST, false);
            }
            else {
                Toast.makeText(this, res.getString(R.string.erreur_confirm_password), Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, res.getString(R.string.erreur_champ_non_remplis), Toast.LENGTH_LONG).show();

        }

    }

    private void initVariables() {
        nom = inputNom.getText().toString().trim();
        prenom = inputPrenom.getText().toString().trim();
//        dateNaissance = inputDateNaissance.getText().toString().trim();
        email = inputEmail.getText().toString().trim();
        telephone = inputTelephone.getText().toString().trim();
        pays = inputPays.getSelectedItem().toString().trim();
        localite = inputLocalite.getText().toString().trim();
        region = inputRegion.getSelectedItem().toString().trim();
        ville = inputVille.getText().toString().trim();
        password = inputPassword.getText().toString().trim();
        passwordConfirm = inputPasswordConfirm.getText().toString().trim();
        groupeUtilisateur = inputGroupeUtilisateur.getSelectedItem().toString().trim();
        typeUtilisateur = inputTypeUtilisateur.getSelectedItem().toString().trim();
    }

    private void initialisationRegion() {
        Resources res = getResources();
        String[] regions = {res.getString(R.string.extreme_nord), res.getString(R.string.nord), res.getString(R.string.adamaoua),
                res.getString(R.string.centre), res.getString(R.string.est), res.getString(R.string.nord_ouest),
                res.getString(R.string.ouest), res.getString(R.string.sud_ouest), res.getString(R.string.littoral), res.getString(R.string.sud)};

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, regions);
        inputRegion.setAdapter(adapter);
    }

    private void initialisationPays() {
        Resources res = getResources();
        String[] pays = {res.getString(R.string.pays_cameroun)};
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, pays);
        inputPays.setAdapter(adapter);
    }

    private boolean testEmpty (){
        return !nom.isEmpty() && !nom.equalsIgnoreCase("") && !prenom.isEmpty() && !prenom.equalsIgnoreCase("") &&
//                !dateNaissance.isEmpty() && !dateNaissance.equalsIgnoreCase("") &&
                !email.isEmpty() &&
                !email.equalsIgnoreCase("") && !telephone.isEmpty() && !telephone.equalsIgnoreCase("") &&
                !pays.isEmpty() && !pays.equalsIgnoreCase("") && !localite.isEmpty() && !localite.equalsIgnoreCase("") &&
                !region.isEmpty() && !region.equalsIgnoreCase("") && !ville.isEmpty() && !ville.equalsIgnoreCase("") &&
                !password.isEmpty() && !password.equalsIgnoreCase("") && !passwordConfirm.isEmpty() && !passwordConfirm.equalsIgnoreCase("");
    }

    private void signUpUserWithFirebase(final String name, String email, String password){
        mDialog.setMessage("Please wait...");
        mDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    //there was an error
                    Toast.makeText(InscriptionActivity.this, "Error " + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    final FirebaseUser newUser = task.getResult().getUser();
                    //success creating user, now set display name as name
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build();

                    newUser.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    mDialog.dismiss();
                                    if (task.isSuccessful()) {
                                        Log.d(InscriptionActivity.class.getName(), "User profile updated.");
                                        /***CREATE USER IN FIREBASE DB AND REDIRECT ON SUCCESS**/
                                        createUserInDb(newUser.getUid(), newUser.getDisplayName(), newUser.getEmail());

                                    }else{
                                        //error
                                        Toast.makeText(InscriptionActivity.this, "Error " + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });
    }

    private void createUserInDb(String userId, String displayName, String email){
        mUsersDBref = FirebaseDatabase.getInstance().getReference().child("Users");
        User user = new User(userId, displayName, email);
        mUsersDBref.child(userId).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(!task.isSuccessful()){
                    //error
                    Toast.makeText(InscriptionActivity.this, "Error " + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    //success adding user to db as well
                    //go to users chat list
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
