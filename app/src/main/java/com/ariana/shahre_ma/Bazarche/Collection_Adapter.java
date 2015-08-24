package com.ariana.shahre_ma.Bazarche;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ariana.shahre_ma.Fields.FieldClass;
import com.ariana.shahre_ma.ListExpand.Continent;
import com.ariana.shahre_ma.ListExpand.Country;
import com.ariana.shahre_ma.R;
import com.ariana.shahre_ma.Settings.KeySettings;

import java.util.ArrayList;

/**
 * Created by ariana2 on 6/28/2015.
 */
public class Collection_Adapter extends BaseExpandableListAdapter {

    KeySettings setting;
    Integer count=0;
    private Context context;
    private ArrayList<Continent> continentList;
    private ArrayList<Continent> originalList;
    private FieldClass fc=new FieldClass();
    public static ImageView headimage;
    public Collection_Adapter(Context context, ArrayList<Continent> continentList) {
        this.context = context;
        this.continentList = new ArrayList<Continent>();
        this.continentList.addAll(continentList);
        this.originalList = new ArrayList<Continent>();
        this.originalList.addAll(continentList);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Country> countryList = continentList.get(groupPosition).getCountryList();
        return countryList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        final Country country = (Country) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_item, null);
        }

        final TextView name = (TextView) view.findViewById(R.id.laptop);
        name.setText(country.getName().trim());

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,name.getText().toString(),Toast.LENGTH_LONG).show();
                Intent i=new Intent(context,Product_List.class);
                context.startActivity(i);
            }
        });


        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<Country> countryList = continentList.get(groupPosition).getCountryList();
        return countryList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return continentList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return continentList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view,
                             ViewGroup parent) {

        final Continent continent = (Continent) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.group_item, null);
        }

        try {


            headimage = (ImageView) view.findViewById(R.id.head_image);
         /*   imgLoader = new ImageLoader(context);*/

            TextView heading = (TextView) view.findViewById(R.id.laptop1);
            heading.setText(continent.getName().trim());

            if (isExpanded) {
                view.setBackgroundColor(context.getResources().getColor(R.color.blue_focused));
            } else {
                view.setBackgroundColor(context.getResources().getColor(R.color.white));
            }
        }
        catch (Exception e)
        {

        }
        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query){

        query = query.toLowerCase();
        Log.v("MyListAdapter", String.valueOf(continentList.size()));
        continentList.clear();
        //originalList.clear();

        if(query.isEmpty()){
            continentList.addAll(originalList);
        }
        else {

            for(Continent continent: originalList){

                ArrayList<Country> countryList = continent.getCountryList();
                ArrayList<Country> newList = new ArrayList<Country>();
                for(Country country: countryList){
                    if(country.getName().toLowerCase().contains(query)){
                        newList.add(country);
                        notifyDataSetChanged();
                    }
                }
                if(newList.size() > 0){
                    Continent nContinent = new Continent(continent.getName(),newList,31);
                    continentList.add(nContinent);
                }
            }
        }

        Log.v("MyListAdapter", String.valueOf(continentList.size()));
        notifyDataSetChanged();

    }
}