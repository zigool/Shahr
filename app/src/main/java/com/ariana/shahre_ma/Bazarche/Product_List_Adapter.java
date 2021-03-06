package com.ariana.shahre_ma.Bazarche;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ariana.shahre_ma.Fields.FieldClass;
import com.ariana.shahre_ma.Fields.FieldDataBase;
import com.ariana.shahre_ma.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ariana2 on 8/23/2015...
 */
public class Product_List_Adapter extends RecyclerView.Adapter<Product_List_Adapter.ViewHolder> {

    List<Product_List_Item> mItems;
    Product_List_Item nature;
    FieldClass fc=new FieldClass();
    FieldDataBase fieldDataBase=new FieldDataBase();
    Context context;
    public Product_List_Adapter(Context context) {
        super();
        this.context=context;
        mItems = new ArrayList<Product_List_Item>();


        Log.i("size",String.valueOf(fieldDataBase.getName_Product().size()));
             for(int i=0;i<fieldDataBase.getName_Product().size();i++)
             {
                 nature = new Product_List_Item();
                 nature.setName(fieldDataBase.getName_Product().get(i));

                 nature.setmPrice(fieldDataBase.getprice_Product().get(i));
                 nature.setId(fieldDataBase.getId_Product().get(i));
                 nature.setImageName(fieldDataBase.getImage_Product().get(i));
                 nature.setThumbnail(R.drawable.img_not_found);
                 mItems.add(nature);
             }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.prudcut_list_card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Product_List_Item nature = mItems.get(i);
        viewHolder.tvNature.setText(nature.getName());
        viewHolder.tvDesNature.setText(nature.getmPrice().toString());
        viewHolder.tvDesNature.setTag(nature.getId());
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
        String image_url_1;
        image_url_1 = "http://www.shahrma.com/image/business/" + nature.getImageName();
        Picasso.with(context).load(image_url_1).placeholder(R.drawable.img_not_found).into(viewHolder.imgThumbnail);
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView tvNature;
        public TextView tvDesNature;

        public ViewHolder(View itemView) {
            super(itemView);

            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_product);
            tvNature = (TextView)itemView.findViewById(R.id.tv_title);
            tvDesNature = (TextView)itemView.findViewById(R.id.tv_price);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    fc.SetProductId(Integer.valueOf(String.valueOf(tvDesNature.getTag())));
                    Intent i=new Intent(context, product_Details.class);
                    context.startActivity(i);

                }
            });
            imgThumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fc.SetProductId(Integer.valueOf(String.valueOf(tvDesNature.getTag())));
                    Intent i=new Intent(context, product_Details.class);
                    context.startActivity(i);

                }
            });

        }
    }
}
