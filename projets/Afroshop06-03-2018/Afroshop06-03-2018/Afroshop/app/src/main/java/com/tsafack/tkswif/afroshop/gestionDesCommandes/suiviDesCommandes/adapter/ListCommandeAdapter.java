package com.tsafack.tkswif.afroshop.gestionDesCommandes.suiviDesCommandes.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.CommamdeCustom;

import java.util.ArrayList;

/**
 * Created by tsamo ulrich on 04/05/2018.
 */

public class ListCommandeAdapter extends ArrayAdapter {

    ArrayList<CommamdeCustom> commamdeCustoms = new ArrayList<>();
    Context context;


    public ListCommandeAdapter(@NonNull Context context, @NonNull ArrayList<CommamdeCustom> commamdeCustoms) {
        super(context, R.layout.item_commande, commamdeCustoms);
        this.context = context;
        this.commamdeCustoms = commamdeCustoms;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = null;
        rowView = inflater.inflate(R.layout.item_commande, parent, false);

        Holder holder = new Holder();

        holder.nomCommande = (TextView) rowView.findViewById(R.id.item_commande_nomcommande);
        holder.datePubication = (TextView) rowView.findViewById(R.id.item_commande_datecommande);
        holder.prixTotal = (TextView) rowView.findViewById(R.id.item_commande_prixtotal);
        holder.articleCommandes = (RecyclerView) rowView.findViewById(R.id.item_commande_list_articlecommande);
        holder.articleCommandes.setHasFixedSize(true);

        holder.nomCommande.setText(commamdeCustoms.get(position).getLibelleCommande());
        holder.datePubication.setText(commamdeCustoms.get(position).getDatePassationCommande());
        holder.prixTotal.setText(commamdeCustoms.get(position).getPrixTotal() + " XAF");

        ArticleCommandeAdapter adapter = new ArticleCommandeAdapter(context, commamdeCustoms.get(position).getArticlePaniers());
        holder.articleCommandes.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.articleCommandes.setAdapter(adapter);

        return rowView;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return commamdeCustoms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class Holder {
        TextView nomCommande;
        TextView datePubication;
        TextView prixTotal;
        RecyclerView articleCommandes;
    }
}
