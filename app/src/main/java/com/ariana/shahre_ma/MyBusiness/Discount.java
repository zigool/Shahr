package com.ariana.shahre_ma.MyBusiness;

import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ariana.shahre_ma.DateBaseSqlite.DataBaseSqlite;
import com.ariana.shahre_ma.R;

import java.util.ArrayList;

public class Discount extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);

        discount_Adapter adapter = new discount_Adapter(this, generateData());
        final ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        final int count = adapter.getCount();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == count - 1) {
                    Discount_Dialog dialog = new Discount_Dialog(Discount.this);
                    dialog.show();
                } else {
                    Toast.makeText(getApplicationContext(), "فقط مجاز به ویرایش آخرین تخفیف هستید", Toast.LENGTH_LONG).show();
                    Snackbar.make(listView, "test", Snackbar.LENGTH_LONG);

                }
            }
        });

    }

    private ArrayList<discount_item> generateData(){
        ArrayList<discount_item> items = new ArrayList<discount_item>();
        DataBaseSqlite db=new DataBaseSqlite(this);
        Cursor rows=db.select_AllDisCountMember();
        if(rows.moveToFirst()) {
            do {


                items.add(new discount_item(" % تخفیف "+rows.getString(6),rows.getString(5),rows.getString(3)));
                //  items.add(new discount_item("20% تخفیف", "به مناسبت روز مرد", "1394/5/8"));
            }while (rows.moveToNext());
        }

        return items;
    }

    public void add_discount(View v){

    Discount_Dialog dialog=new Discount_Dialog(this);
        dialog.show();


    }

}


