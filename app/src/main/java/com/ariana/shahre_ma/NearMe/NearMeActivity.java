package com.ariana.shahre_ma.NearMe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ariana.shahre_ma.DateBaseSqlite.DataBaseSqlite;
import com.ariana.shahre_ma.Fields.FieldDataBusiness;
import com.ariana.shahre_ma.R;
import com.ariana.shahre_ma.WebServiceSend.HTTPSendNearMeURL;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class NearMeActivity extends ActionBarActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    FieldDataBusiness fdb=new FieldDataBusiness();
    String Latitude[];
    String Longtitude[];
    String Market[];
    Double Rate[];
    Integer len=0;
    Integer id[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_me);
        setUpMapIfNeeded();

      /*  Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=35.688951,51.018301"));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);*/
        /*

        Location locationA = new Location("point A");
        locationA.setLatitude(35.687543);
        locationA.setLongitude(51.019641);

        Location locationB = new Location("point B");
        locationB.setLatitude(35.688951);
        locationB.setLongitude(51.018301);

       float distance = locationA.distanceTo(locationB) ;
        Log.i("distance",String.valueOf(distance)+"M");*/
       // latitude=51.0096686&longitude=35.8357895&distance=0.01

    /*    try {
            Locale loc = new Locale("IR");
            Geocoder gcd = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            addresses = gcd.getFromLocation(35.8357895, 51.0096686, 1);
            if (addresses.size() > 0)
              Log.i("Addresss",addresses.get(0).getLocality());

        } catch (IOException e) {
            e.printStackTrace();
        }*/



        HTTPSendNearMeURL nearMeURL=new HTTPSendNearMeURL(this);
        nearMeURL.SetNearMe("35.8357895","51.0096686",0.01);
        nearMeURL.execute();



        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter("near-me"));

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
               // setUpMap();
            }
        }
    }


    private void setUpMap() {

        DataBaseSqlite db=new DataBaseSqlite(this);
        Cursor rows=db.select_AllBusiness();

        if(rows.moveToFirst())
        {
            do
            {
                mMap.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(rows.getString(16)), Double.valueOf(rows.getString(15)))).title("\u200e" + rows.getString(1)));
                len++;
            }while (rows.moveToNext());
        }





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_near_me, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
              FilterDialog dialog=new FilterDialog(this);
                dialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("message");
            Log.i("receiver", "Got message: " + message);
            setUpMap();
        }

    };
}
