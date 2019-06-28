package com.tsafack.tkswif.afroshop.gestionDesCommandes.suiviDesCommandes.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.Agence;

import java.util.ArrayList;

/**
 * Created by tsamo ulrich on 12/04/2018.
 */

public class ListeAgencesAdapter extends ArrayAdapter {

    ArrayList<Agence> agences = new ArrayList<>();
    Context context;
    Holder holder = new Holder();

    public ListeAgencesAdapter(@NonNull Context context, @NonNull ArrayList<Agence> agences) {
        super(context, R.layout.item_agences, agences);
        this.context = context;
        this.agences = agences;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // engrais. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = null;
        rowView = inflater.inflate(R.layout.item_agences, parent, false);

        // 3. Get icon,title & counter views from the rowView
        holder.nom= (TextView) rowView.findViewById(R.id.item_listeagences_nom);
        holder.email = (TextView) rowView.findViewById(R.id.item_listeagences_email);
        holder.telephone = (TextView) rowView.findViewById(R.id.item_listeagences_telephone);

        // 4. Set the text for textView

        holder.nom.setText(agences.get(position).getNomAgence());
        holder.telephone.setText(agences.get(position).getTelephoneAgence());
        holder.email.setText(agences.get(position).getEmailAgence());

        // 5. retrn rowView
        return rowView;
    }

    class Holder {
        TextView nom;
        TextView telephone;
        TextView email;
    }
}
