package com.ariana.shahre_ma;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.ariana.shahre_ma.DateBaseSqlite.Query;
import com.ariana.shahre_ma.DateBaseSqlite.SelectDataBaseSqlite;
import com.ariana.shahre_ma.Settings.KeySettings;
import com.ariana.shahre_ma.WebServiceGet.HTTPGetTopsBusinessJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ariana2 on 7/9/2015.
 */
public class CityDialog extends Dialog {

    KeySettings settings;
    Button btnchange;
    Spinner Sp_City;
    List<String> list;
    ArrayAdapter<String> dataAdapter;
    Query query = new Query(getContext());
    public CityDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.city_dialog_popup);
        super.onCreate(savedInstanceState);
        settings=new KeySettings(getContext());
        setTitle("شهر مورد نظر خود را انتخاب کنید:");
        String compareValue = settings.getCityName();
        sp();
        int spinnerPosition = dataAdapter.getPosition(compareValue);
        Sp_City.setSelection(spinnerPosition);

        setCanceledOnTouchOutside(false);
        btnchange = (Button)findViewById(R.id.btn_selected_city);
        btnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Log.i("Spinner", Sp_City.getSelectedItem().toString());
                    settings.saveCityName(Sp_City.getSelectedItem().toString());

                    Intent intent = new Intent("City");
                    intent.putExtra("received", "Cities");
                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);

                    HTTPGetTopsBusinessJson httpGetTopsBusinessJson=new HTTPGetTopsBusinessJson(getContext());
                    httpGetTopsBusinessJson.SetTopBusiness(query.getCityId(settings.getCityName()));
                    httpGetTopsBusinessJson.execute();
                    dismiss();
                }
                catch (Exception e)
                {

                }
            }
        });


    }

    public void sp(){

        SelectDataBaseSqlite sdb=new SelectDataBaseSqlite(getContext());
        Cursor allrows=sdb.select_AllCity();

        Sp_City = (Spinner) findViewById(R.id.select_city_dialog);
        Sp_City.setPrompt("انتخاب شهر:");
        list = new ArrayList<String>();
        if(allrows.moveToFirst())
        {
            do
            {
                list.add(allrows.getString(1));

            }while (allrows.moveToNext());
        }
        dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sp_City.setAdapter(dataAdapter);
    }
}
