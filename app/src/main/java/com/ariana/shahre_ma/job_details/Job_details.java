package com.ariana.shahre_ma.job_details;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.ariana.shahre_ma.Cards.Comment_Card_Adapter;
import com.ariana.shahre_ma.Cards.Job_lists_card_item;
import com.ariana.shahre_ma.Cards.job_list_cards_adapter;
import com.ariana.shahre_ma.DateBaseSqlite.DataBaseSqlite;
import com.ariana.shahre_ma.Fields.FieldClass;
import com.ariana.shahre_ma.R;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.Locale;

public class Job_details extends ActionBarActivity implements ActionBar.TabListener {




    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter Comment_adapter;



    SectionsPagerAdapter mSectionsPagerAdapter;



    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);




        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }



    }



    public void onClick(View v) {

        Toast.makeText(getBaseContext(), "clicked", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());

        if (tab.getPosition()==2){
       map();
       route();

        }else if(tab.getPosition()==3){

          setCards();

        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){

                case 0:
                    return new job_details_1.PlaceholderFragment();
               case 2:
                    return new Job_details_map.PlaceholderFragment();
                case 3:
                    return new Job_details_comment.PlaceholderFragment();

            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.det1).toUpperCase(l);
                case 1:
                    return getString(R.string.det2).toUpperCase(l);
                case 2:
                    return getString(R.string.det3).toUpperCase(l);
                case 3:
                    return getString(R.string.det4).toUpperCase(l);
            }
            return null;
        }
    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_job_details, container, false);
            return rootView;
        }
    }



   public void map(){
        ResourceProxy resourceProxy;
        MapView map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        map.getController().setZoom(15);
        map.getController().animateTo(new GeoPoint(35.6877315,51.0240054));

        ArrayList<OverlayItem> overlays = new ArrayList<OverlayItem>();
        overlays.add(new OverlayItem("اندیشه", "داده پردازان آریانا", new GeoPoint(35.6876661, 51.0195912)));
        Drawable marker=this.getResources().getDrawable(R.drawable.marker);

        resourceProxy = new DefaultResourceProxyImpl(getApplicationContext());
        ItemizedIconOverlay myLocationOverlay;
        myLocationOverlay = new ItemizedIconOverlay<OverlayItem>(overlays,marker,null, resourceProxy);
        map.getOverlays().add(myLocationOverlay);




    }
   public void route(){

        MapView map = (MapView) findViewById(R.id.map);
        RoadManager roadManager = new OSRMRoadManager();
        GeoPoint startPoint= new GeoPoint(35.6876661, 51.0195912);
        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
        waypoints.add(startPoint);
        GeoPoint endPoint = new GeoPoint(35.683703,51.1139485);
        waypoints.add(endPoint);

        Road road = roadManager.getRoad(waypoints);

        Polyline roadOverlay = RoadManager.buildRoadOverlay(road, this);
        roadOverlay.setColor(this.getResources().getColor(R.color.material_blue_grey_900));
        map.getOverlays().add(roadOverlay);
        map.invalidate();

    }




    private void setCards(){
        try {
            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_comments);
            mRecyclerView.setHasFixedSize(true);

            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            Comment_adapter = new Comment_Card_Adapter();
            mRecyclerView.setAdapter(Comment_adapter);
        }
        catch (Exception e){}


    }


}