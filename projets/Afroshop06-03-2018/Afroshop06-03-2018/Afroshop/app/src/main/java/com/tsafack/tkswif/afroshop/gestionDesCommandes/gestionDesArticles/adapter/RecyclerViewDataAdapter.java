package com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.SectionDataModel;

import java.util.ArrayList;

/**
 * Created by Stephane on 7/22/2017.
 */

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.ItemRowHolder>{

    //Constructeur
    private ArrayList<SectionDataModel> dataList;
    private Context mContext;

    public RecyclerViewDataAdapter(ArrayList<SectionDataModel> dataList, Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_acceuil, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }
    @Override
    public void onBindViewHolder(ItemRowHolder holder, int position) {
        final String sectionName = dataList.get(position).getHeaderTitle();

        ArrayList singleSectionItems = dataList.get(position).getAllItemsInSection();

        holder.itemTitle.setText(sectionName);

        SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(singleSectionItems,mContext);
        holder.recycler_view_list.setHasFixedSize(true);
        holder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.recycler_view_list.setAdapter(itemListDataAdapter);
//        holder.btnMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (sectionName.equalsIgnoreCase("intrants")){
//                    Context context = v.getContext();
//                    Intent intent = new Intent (context, intrant.class);
//
//                }
//                if(sectionName.equalsIgnoreCase("produits")){
//                    Context context = v.getContext();
//                    Intent intent = new Intent(context, MesProduits.class);
//
//                }
//                else{
//
//                }
//             }
//        });

    }
    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView itemTitle;

        protected RecyclerView recycler_view_list;

        protected Button btnMore;



        public ItemRowHolder(View view) {
            super(view);

            this.itemTitle = (TextView) view.findViewById(R.id.itemTitle);
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.recycler_view_list);
//            this.btnMore= (Button) view.findViewById(R.id.btnMore);


        }

    }


}
