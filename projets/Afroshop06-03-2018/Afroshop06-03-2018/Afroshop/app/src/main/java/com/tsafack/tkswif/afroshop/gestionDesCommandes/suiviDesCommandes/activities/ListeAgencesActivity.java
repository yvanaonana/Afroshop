package com.tsafack.tkswif.afroshop.gestionDesCommandes.suiviDesCommandes.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.Agence;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.traitement.GestionCommande;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.suiviDesCommandes.adapter.ListeAgencesAdapter;

import java.sql.Date;
import java.util.ArrayList;

public class ListeAgencesActivity extends AppCompatActivity {

    ListView listViewAgences;
    ArrayList<Agence> agences = new ArrayList<>();
    ListeAgencesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_agences);

        listViewAgences = (ListView) findViewById(R.id.listeagences_liste);

        initList();
    }

    private void initList() {

//        agences.add(new Agence("", "Buca Voyage", new Date(14, 5, 2018),
//                "+237 694 576 654 ", "yvanaonana@gmail.com", "",
//                "", "", "", new Date(00, 00, 00)));
//        agences.add(new Agence("", "princesse voyage", new Date(14, 5, 2018),
//                "+237 691 812 939", "nelino.takam@gmail.com", "",
//                "", "", "", new Date(00, 00, 00)));
//        agences.add(new Agence("", "Garanti Express", new Date(14, 5, 2018),
//                "+237 693 290 232", "tsamoulrich@gmail.com", "",
//                "", "", "", new Date(00, 00, 00)));
        adapter = new ListeAgencesAdapter(ListeAgencesActivity.this, agences);
        listViewAgences.setAdapter(adapter);
        GestionCommande gestionCommande = new GestionCommande(ListeAgencesActivity.this);
        agences = gestionCommande.listerAgence(listViewAgences, "EST");
    }
}
