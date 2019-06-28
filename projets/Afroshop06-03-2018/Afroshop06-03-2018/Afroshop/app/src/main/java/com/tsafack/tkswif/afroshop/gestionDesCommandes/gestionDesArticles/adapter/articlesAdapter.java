package com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;
import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.CategorieArticle;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.Extra;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.activities.AjouterProduitActivity;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.activities.Listes_des_produits_categorie;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.activities.Listes_des_produits_utilisateur;

import java.util.ArrayList;

/**
 * Created by Stephane on 14/02/2018.
 */

public class articlesAdapter extends RecyclerView.Adapter<articlesAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<CategorieArticle> articlesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.prix);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }

    public articlesAdapter(Context mContext, ArrayList<CategorieArticle> articlesList) {
        this.mContext = mContext;
        this.articlesList = articlesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_produits_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        CategorieArticle album = articlesList.get(position);
        holder.title.setText(album.getLibelleCategorieArticle());
        holder.count.setText(20 + " produits");
        // loading album cover using Glide library

        AnimationDrawable animationDrawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            animationDrawable=(AnimationDrawable)mContext.getDrawable(R.drawable.giphy);
        else
//            animationDrawable = (AnimationDrawable) mContext.getResources().getDrawable(R.drawable.giphy);
//        animationDrawable.start();

        Glide.with(mContext).load(album.getImageCategorieArticle())
//                .placeholder(animationDrawable)
//                .thumbnail(Glide.with(mContext).load(R.drawable.giphy))
//                .fitCenter()
//                .crossFade()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target,
                                               boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target, boolean isFromMemoryCache,
                                                   boolean isFirstResource) {
                        holder.thumbnail.setImageResource(0);
                        holder.thumbnail.setScaleType(ImageView.ScaleType.FIT_XY);
                        return false;
                    }
                })
                .error(R.drawable.errorcloudgreen)
                .into(holder.thumbnail);


        holder.overflow.setImageResource(0);
//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
//            }
//        });
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (mContext, Listes_des_produits_categorie.class);
                intent.putExtra(Extra.CATEGORIE, articlesList.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_list, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }


    public CategorieArticle getItem(int position) {
        return articlesList.get(position);
    }

    /**
 * Click listener for popup menu items
 */

class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

    public MyMenuItemClickListener() {
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.modifier:
                Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.supprimer:
                Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                return true;
            default:
        }
        return false;
    }
}

    @Override
    public int getItemCount() {
        return articlesList.size();
    }
}

