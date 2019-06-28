package com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.gridViewModel;

import java.util.ArrayList;

/**
 * Created by Stephane on 7/22/2017.
 */

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    int[][] images = {{R.drawable.outils7, R.drawable.outils4, R.drawable.engin3, R.drawable.engrais},
            {R.drawable.produit3, R.drawable.produit8, R.drawable.produit9, R.drawable.semences2},
            {R.drawable.formation2, R.drawable.formation3, R.drawable.formation9, R.drawable.formation7},
            {R.drawable.formation1, R.drawable.formation4, R.drawable.formation5, R.drawable.formation7},
            {R.drawable.engin5, R.drawable.fomation8, R.drawable.produit4, R.drawable.semences1}};

    private ArrayList<gridViewModel> itemsList;
    private Context mContext;

    public SectionListDataAdapter(ArrayList<gridViewModel> itemsList, Context mContext) {
        this.itemsList = itemsList;
        this.mContext = mContext;
    }

    public SectionListDataAdapter() {
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_single_card, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int position) {
        gridViewModel singleItem = itemsList.get(position);
        holder.tvTitle.setText(singleItem.getName());
        holder.itemImage.setImageResource(singleItem.getUrl());

    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {
        protected TextView tvTitle;
        protected ImageView itemImage;

        public SingleItemRowHolder(final View view) {
            super(view);
            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.itemImage = (ImageView) view.findViewById(R.id.itemImage);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String titre = tvTitle.getText().toString().trim();

                   /** switch (titre){
                        case "Fruits":
                            Intent intentf = new Intent(v.getContext(), ProduitAll.class);
                            intentf.putExtra("categorie", titre);
                            v.getContext().startActivity(intentf);
                            break;
                        case "Graines":
                            Intent intentg = new Intent(v.getContext(), ProduitAll.class);
                            intentg.putExtra("categorie", titre);
                            v.getContext().startActivity(intentg);
                            break;
                        case "Tubercules":
                            Intent intentt = new Intent(v.getContext(), ProduitAll.class);
                            intentt.putExtra("categorie", titre);
                            v.getContext().startActivity(intentt);
                            break;
                        case "Legumes":
                            Intent intentl = new Intent(v.getContext(), ProduitAll.class);
                            intentl.putExtra("categorie", titre);
                            v.getContext().startActivity(intentl);
                            break;
                        case "Pesticides":
                            Intent intentp = new Intent(v.getContext(), IntrantAll.class);
                            intentp.putExtra("categorie", titre);
                            v.getContext().startActivity(intentp);
                            break;
                        case "Materiels":
                            Intent intentm = new Intent(v.getContext(), IntrantAll.class);
                            intentm.putExtra("categorie", titre);
                            v.getContext().startActivity(intentm);
                            break;
                        case "Machines":
                            Intent intentma = new Intent(v.getContext(), IntrantAll.class);
                            intentma.putExtra("categorie", titre);
                            v.getContext().startActivity(intentma);
                            break;
                        case "Engrais":
                            Intent intente = new Intent(v.getContext(), IntrantAll.class);
                            intente.putExtra("categorie", titre);
                            v.getContext().startActivity(intente);
                            break;
                        default:
                            Toast.makeText(v.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();
                            break;

                    }**/

                }
            });

//            for (int i = 0; i<5 ; i++){
//                for (int j = 0 ; j<4 ; j++){
//                    itemImage.setImageResource(images[i][j]);
//                }
//            }
        }

    }
}
