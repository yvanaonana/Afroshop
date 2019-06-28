package com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import com.tsafack.tkswif.afroshop.R;

/**
 * Created by Stephane on 28/02/2018.
 */

class VueArticles extends RecyclerView.ViewHolder {
    public TextView title,categories,prix,nombredecommandes,etatsDuproduits;
    public TextSwitcher NombredeJaime;
    public ImageView urlImage, optionMenu,likeBt;

     VueArticles(View view) {
        super(view);
        title = (TextView) view.findViewById(R.id.Listview_titreDuProduits);
        categories = (TextView) view.findViewById(R.id.Listview_CategoriesProduits);
        etatsDuproduits = (TextView) view.findViewById(R.id.Listview_etat_produits);
        prix = (Button) view.findViewById(R.id.Listview_ButtonAcheterProduits);
        NombredeJaime= (TextSwitcher) view.findViewById(R.id.Listview_tsLikesCounter);
        urlImage = (ImageView) view.findViewById(R.id.Listview_itemImageProduits);
        optionMenu = (ImageView) view.findViewById(R.id.Listview_OptionMenu);
        likeBt=(ImageView) view.findViewById(R.id.LikeButtonImageItemProduitd);
        nombredecommandes=(TextView)view.findViewById(R.id.AfficherNombreDeCommandesAffectue);
    }


}

