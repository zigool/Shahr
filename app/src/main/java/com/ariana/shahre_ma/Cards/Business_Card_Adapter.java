package com.ariana.shahre_ma.Cards;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ariana.shahre_ma.DateBaseSqlite.DataBaseSqlite;
import com.ariana.shahre_ma.Fields.FieldClass;
import com.ariana.shahre_ma.MyBusiness.Discount;
import com.ariana.shahre_ma.MyBusiness.Edit_business;
import com.ariana.shahre_ma.R;
import com.github.alexkolpa.fabtoolbar.FabToolbar;

import java.util.ArrayList;
import java.util.List;

public class Business_Card_Adapter extends RecyclerView.Adapter<Business_Card_Adapter.ViewHolder> {

    List<Business_Card_Items> mItems;
    FieldClass fc=new FieldClass();
    private  static Context context;
    Business_Card_Items nature;


    public Business_Card_Adapter(Context context) {
        super();
        this.context=context;

            DataBaseSqlite db = new DataBaseSqlite(context);
            Cursor rows = db.select_AllBusinessId(fc.GetBusiness_Id());
            mItems = new ArrayList<Business_Card_Items>();



        if(rows.moveToFirst())
        {
            do {

                nature = new Business_Card_Items();

                nature.setId(rows.getInt(0));
                nature.setName(rows.getString(1));
                nature.setmAddress(rows.getString(7));
                nature.setThumbnail(R.drawable.pooshak);
                nature.setRate(rows.getFloat(19));
                mItems.add(nature);

            }while (rows.moveToNext());
        }



    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.business_cards, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Business_Card_Items nature = mItems.get(i);
        viewHolder.tvNature.setText(nature.getName());
        viewHolder.tvNature.setTag(nature.getId());
       // viewHolder.tvDesNature.setText(nature.getmAddress());
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
        viewHolder.Rates.setRating((float)nature.getRate());
    }


    @Override
    public int getItemCount() {

        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public ImageView img_edit;
        public ImageView img_discount;
        public TextView tvNature;
        public RatingBar Rates;
        public FabToolbar fab;
        public ViewHolder(View itemView) {
            super(itemView);

            imgThumbnail = (ImageView)itemView.findViewById(R.id.my_business_image);
            tvNature = (TextView)itemView.findViewById(R.id.my_business_title);
            img_edit = (ImageView)itemView.findViewById(R.id.btn_edit_business);
            img_discount = (ImageView)itemView.findViewById(R.id.btn_discount);
            Rates = (RatingBar)itemView.findViewById(R.id.my_business_rate);
            fab=(FabToolbar)itemView.findViewById(R.id.fab_toolbar);
            fab.setButtonIcon(context.getResources().getDrawable(R.drawable.abc_ic_menu_moreoverflow_mtrl_alpha));
            img_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fab.hide();
                    Intent i = new Intent(context, Edit_business.class);
                    context.startActivity(i);
                    Log.i("clicked", tvNature.getText().toString());
                }
            });

            img_discount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fab.hide();
                    Intent i = new Intent(context,Discount.class);
                    context.startActivity(i);
                    Log.i("clicked",tvNature.getText().toString());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    Log.i("ON_______CLICK", tvNature.getText().toString());
                    fab.hide();

                }
            });
            imgThumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }
}