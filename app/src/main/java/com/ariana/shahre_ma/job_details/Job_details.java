package com.ariana.shahre_ma.job_details;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ariana.shahre_ma.DateBaseSqlite.Query;
import com.ariana.shahre_ma.DateBaseSqlite.SelectDataBaseSqlite;
import com.ariana.shahre_ma.Fields.FieldClass;
import com.ariana.shahre_ma.NetWorkInternet.NetState;
import com.ariana.shahre_ma.R;
import com.ariana.shahre_ma.WebServiceSend.HTTPDeleteBookMarkURL;
import com.ariana.shahre_ma.WebServiceSend.HTTPSendBookMarkURL;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.Locale;

public class Job_details extends ActionBarActivity implements ActionBar.TabListener {


    Query query;
    FieldClass fc = new FieldClass();
    SectionsPagerAdapter mSectionsPagerAdapter;
    MenuItem fav;
    NetState ns=new NetState(this);
    public boolean selected=false;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        setTitle(fc.GetMarket_Business());
        query=new Query(this);
        SelectDataBaseSqlite db=new SelectDataBaseSqlite(this);
        if(fc.GetShowNotification())
        {
           db.Add_ShowNotification(fc.GetShowNotificationId(),fc.GetBusiness_Id(),true);

            fc.SetShowNotification(false);
        }
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

        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }



    }




    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());

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
                case 1:
                    return new job_details_discount.PlaceholderFragment();
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



  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.

      getMenuInflater().inflate(R.menu.menu_job_details, menu);
      fav=menu.findItem(R.id.action_Fav);
      if(query.getBookMarkId(fc.GetBusiness_Id())>0)
      {
          fav.setIcon(R.drawable.abc_btn_rating_star_on_mtrl_alpha);
          selected=true;
      }
      return true;
  }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_Fav) {
            if (ns.checkInternetConnection()) {
                if (selected) {
                    if (query.getMemberId() > 0) {
                        HTTPDeleteBookMarkURL delete = new HTTPDeleteBookMarkURL(this);
                        delete.SetBookMark(fc.GetBusiness_Id(), query.getMemberId());
                        delete.execute();
                        fav.setIcon(R.drawable.abc_btn_rating_star_off_mtrl_alpha);
                        selected = false;
                    } else {
                        Toast.makeText(getApplicationContext(), "کاربری وارد نشده است", Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (query.getMemberId() > 0) {
                        HTTPSendBookMarkURL httpbookmark = new HTTPSendBookMarkURL(this);
                        httpbookmark.SetBusinessid(fc.GetBusiness_Id());
                        httpbookmark.SetMemberid(query.getMemberId());
                        httpbookmark.execute();
                        fav.setIcon(R.drawable.abc_btn_rating_star_on_mtrl_alpha);
                        selected = true;
                    } else {
                        Toast.makeText(getApplicationContext(), "کاربری وارد نشده است", Toast.LENGTH_LONG).show();
                    }
                }
            }else{
                Toast.makeText(getApplicationContext(),"اینترنت قطع می باشد!",Toast.LENGTH_LONG).show();
            }
            return true;
        }else if(id==android.R.id.home){
            fc.SetBusinessDisCountTops(false);
            fc.SetBusinessTops(false);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity

        super.onBackPressed();
        fc.SetBusinessDisCountTops(false);
        fc.SetBusinessTops(false);
        this.finish();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}