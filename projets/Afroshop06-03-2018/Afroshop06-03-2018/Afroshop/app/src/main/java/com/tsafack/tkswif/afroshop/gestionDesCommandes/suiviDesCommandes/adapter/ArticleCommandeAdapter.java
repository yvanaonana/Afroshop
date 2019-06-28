package com.tsafack.tkswif.afroshop.gestionDesCommandes.suiviDesCommandes.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.ArticleCommandeCustom;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsamo ulrich on 04/05/2018.
 */

public class ArticleCommandeAdapter extends RecyclerView.Adapter<ArticleCommandeAdapter.Holder>{

    private FirebaseAuth mAuth;
    private DatabaseReference mUsersDBRef;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<User> mUsersList = new ArrayList<>();
    Context context;
    ArrayList<ArticleCommandeCustom> articleCommandeCustoms = new ArrayList<>();

    public ArticleCommandeAdapter(@NonNull Context context, @NonNull ArrayList<ArticleCommandeCustom> articleCommandeCustoms) {
        this.articleCommandeCustoms = articleCommandeCustoms;
        this.context = context;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_commande, null);
        Holder mh = new Holder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.libelle.setText(articleCommandeCustoms.get(position).getLibelleArticleCommandeCustom());
        holder.description.setText("Article dans la region " + articleCommandeCustoms.get(position).getRegionArticle() + " la quantite est " +
                articleCommandeCustoms.get(position).getQuantiteArticleCommandeCustom() + " de "+
                articleCommandeCustoms.get(position).getPrixUnitaireArticleCommandeCustom() + " XAF") ;
        holder.mention.setText("en cours");
    }

    @Override
    public int getItemCount() {
        return (null != articleCommandeCustoms ? articleCommandeCustoms.size() : 0);
    }



    class Holder extends RecyclerView.ViewHolder  {
        ImageView image;
        TextView libelle;
        TextView description;
        ImageView option;
        TextView mention;


        public Holder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.item_articlecommande_image);
            libelle = (TextView) itemView.findViewById(R.id.item_articlecommande_libelle);
            description = (TextView) itemView.findViewById(R.id.item_articlecommande_description);
            option = (ImageView) itemView.findViewById(R.id.item_articlecommande_option);
            mention = (TextView) itemView.findViewById(R.id.item_articlecommande_mentioncommande);

            option.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(context, option);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_commande_item, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            if (item.getItemId() == R.id.annuler){
                                return true;
                            }
                            if (item.getItemId() == R.id.tchater){
//                                connecterTchat(finalRowView);
                                return true;
                            }
                            else
                                return true;
                        }
                    });
                    popupMenu.show();
                }
            });

        }


    }

    private void queryUsersAndAddthemToList() {
        mUsersDBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0) {
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        User user = snap.getValue(User.class);
                        //if not current user, as we do not want to show ourselves then chat with ourselves lol
                        try {
                            if (!user.getUserId().equals(mAuth.getCurrentUser().getUid())) {
                                if(user.getEmail().equalsIgnoreCase(articleCommandeCustoms.get(0).getEmailAgent()))
                                    mUsersList.add(user);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                /**populate listview**/
                //apres avoir trouve l'agent pou le connecter au chat


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
