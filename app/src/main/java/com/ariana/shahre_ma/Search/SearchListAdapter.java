package com.ariana.shahre_ma.Search;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ariana.shahre_ma.DateBaseSqlite.DataBaseSqlite;
import com.ariana.shahre_ma.DateBaseSqlite.Query;
import com.ariana.shahre_ma.Fields.FieldClass;
import com.ariana.shahre_ma.Fields.FieldDataBusiness;
import com.ariana.shahre_ma.R;
import com.ariana.shahre_ma.job_details.Job_details;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {

    List<SearchItems> mItems;
    SearchItems  nature;
    FieldClass fc=new FieldClass();

    Context context;
    Query query;
    public SearchListAdapter(Context context) {
        super();
        this.context=context;
        DataBaseSqlite db=new DataBaseSqlite(context);
        Query query=new Query(context);
        FieldDataBusiness fdb=new FieldDataBusiness();


            Log.i("SearchOffline", "strat");
            Log.i("SearchOffline", String.valueOf(fdb.GetMarketBusiness().size()));
            mItems = new ArrayList<SearchItems>();

        if(fc.GetSearchOffline())
        {
            for (int i = 0; i < fdb.GetMarketBusiness().size(); i++) {
                Log.i("SearchOffline", String.valueOf(fdb.GetMarketBusiness().size()));
                nature = new SearchItems();
                nature.setName(fdb.GetMarketBusiness().get(i));
                nature.setDes(fdb.GetAddressBusiness().get(i));
                nature.setSubsetId(fdb.GetSubsetId().get(i));
                // nature.setDisCount(fdb.GetDisCountId().get(i));
                nature.setRate(fdb.GetRateBusiness().get(i));
                nature.setmId(fdb.GetIdBusiness().get(i));
                nature.setNameImage(fdb.GetSrc().get(i));


                if (fdb.GetMobileBusiness().get(i).length() == 0 || fdb.GetMobileBusiness().get(i).equals("") || fdb.GetMobileBusiness().get(i).equals(null) || fdb.GetMobileBusiness().get(i).equals("null")) {
                    nature.setTell(fdb.GetPhoneBusiness().get(i));
                } else if (fdb.GetPhoneBusiness().get(i).equals("1")) {
                    nature.setTell("");
                } else {
                    nature.setTell(fdb.GetMobileBusiness().get(i));
                }

                mItems.add(nature);
                notifyDataSetChanged();


            }
            fc.SetSearchOffline(false);
        }
        else
        {
            Log.i("checkInternetConnection","strat");
            mItems = new ArrayList<SearchItems>();

            for (int i = 0; i < fdb.GetMarketBusiness().size(); i++)
            {
                nature = new SearchItems();
                nature.setName(fdb.GetMarketBusiness().get(i));
                nature.setDes(fdb.GetAddressBusiness().get(i));
                nature.setSubsetId(fdb.GetSubsetId().get(i));
                nature.setDisCount(fdb.GetDisCountId().get(i));
                nature.setRate(fdb.GetRateBusiness().get(i));
                nature.setmId(fdb.GetIdBusiness().get(i));
                nature.setNameImage(fdb.GetSrc().get(i));


                if(fdb.GetMobileBusiness().get(i).length()==0 || fdb.GetMobileBusiness().get(i).equals("") || fdb.GetMobileBusiness().get(i).equals(null) || fdb.GetMobileBusiness().get(i).equals("null"))
                {
                    nature.setTell(fdb.GetPhoneBusiness().get(i));
                }
                else if(fdb.GetPhoneBusiness().get(i).equals("1"))
                {
                    nature.setTell("");
                }
                else
                {
                    nature.setTell(fdb.GetMobileBusiness().get(i));
                }

                mItems.add(nature);
                notifyDataSetChanged();
            }
        }




    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.job_lists_card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        SearchItems nature = mItems.get(i);
        viewHolder.tvNature.setText(nature.getName());
        viewHolder.tvDesNature.setText(nature.getDes());
        viewHolder.tvDesNature.setTag(nature.getmId());
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
        viewHolder.rates.setRating((float) nature.getRate());
        viewHolder.rates.setTag(nature.getmId());
        viewHolder.tvTell.setText(nature.getTell());
        String image_url_1;
        if (nature.getNameImage().equals("null")||nature.getNameImage().equals("")||nature.getNameImage().equals(null)||nature.getNameImage()==null){
            Log.i("SubsetId",nature.getSubsetId().toString());
            image_url_1 = "http://www.shahrma.com/image/business/" +nature.getSubsetId()+".jpg";
            Picasso.with(context).load(image_url_1).placeholder(R.drawable.img_not_found).into(viewHolder.imgThumbnail);

        }
        else
        {
            image_url_1 = "http://www.shahrma.com/image/business/" + nature.getNameImage();
            Picasso.with(context).load(image_url_1).placeholder(R.drawable.img_not_found).into(viewHolder.imgThumbnail);
        }
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        public ImageView imgThumbnail;
        public TextView tvNature;
        public TextView tvDesNature;
        public RatingBar rates;
        public TextView tvTell;
        public CardView cards;

        public ViewHolder(View itemView) {
            super(itemView);

            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_market);
            tvNature = (TextView) itemView.findViewById(R.id.tv_title);
            tvDesNature = (TextView) itemView.findViewById(R.id.tv_address);
            rates = (RatingBar) itemView.findViewById(R.id.rates);
            tvTell = (TextView) itemView.findViewById(R.id.tv_tell);
            cards = (CardView) itemView.findViewById(R.id.cards);
            imgThumbnail.setOnClickListener(this);
            tvNature.setOnClickListener(this);
            cards.setOnClickListener(this);

            tvTell.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tvTell.getText().toString()));
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {
            fc.SetMarket_Business(tvNature.getText().toString());
            // fc.SetAddress_Business(tvDesNature.getText().toString());*/

            fc.SetBusiness_Id((Integer) rates.getTag());
            Intent i =new Intent(context,Job_details.class);
            context.startActivity(i);
        }
        }
    }
