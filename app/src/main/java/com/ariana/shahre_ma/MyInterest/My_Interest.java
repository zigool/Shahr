package com.ariana.shahre_ma.MyInterest;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.ariana.shahre_ma.DateBaseSqlite.Query;
import com.ariana.shahre_ma.MessageDialog;
import com.ariana.shahre_ma.MyCity.TotalListener;
import com.ariana.shahre_ma.NetWorkInternet.NetState;
import com.ariana.shahre_ma.R;
import com.ariana.shahre_ma.Service.MyReceiver;
import com.ariana.shahre_ma.WebServiceGet.HTTPGetBusinessJson;
import com.ariana.shahre_ma.WebServiceGet.SqliteTOjson;
import com.ariana.shahre_ma.WebServicePost.HTTPPostInterestJson;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.tsengvn.typekit.TypekitContextWrapper;

import jonathanfinerty.once.Once;

public class My_Interest extends ActionBarActivity implements TotalListener {
    Query query;
    ExpandableListView expListView;
    int lastExpandedPosition = -1;

    HTTPGetBusinessJson httpbusin;
    NetState ns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__interest);
        query = new Query(this);

        httpbusin = new HTTPGetBusinessJson(this);
        ns = new NetState(this);


        Intent myIntent = new Intent(this, MyReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);

        expListView = (ExpandableListView) findViewById(R.id.expand_my_interest);

        Interest_Adapter adapter = new Interest_Adapter(this);
        adapter.setmListener(this);
        expListView.setAdapter(adapter);



        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });
        String showWhatsNew = "showHelpInterest";

        if (!Once.beenDone(Once.THIS_APP_VERSION, showWhatsNew)) {
            help3();
            Once.markDone(showWhatsNew);
        }
    }



    public void SendPostInterest(View v) {
       SqliteTOjson json=new SqliteTOjson(this);

        if(ns.checkInternetConnection())
        {
            HTTPPostInterestJson httpinterest = new HTTPPostInterestJson(this);
            httpinterest.SetInterest_Json(json.getSqliteInterestTOjson());
            httpinterest.execute();
        }
        else
        {
            MessageDialog messageDialog=new MessageDialog(getApplicationContext());
            messageDialog.ShowMessage("هشدار","اینترنت قطع می باشد","باشه","false");
        }

    }


    @Override
    public void onTotalChanged(int sum) {

    }

    @Override
    public void expandGroupEvent(int groupPosition, boolean isExpanded) {
        if(isExpanded)
            expListView.collapseGroup(groupPosition);
        else
            expListView.expandGroup(groupPosition);
    }



    public void help3(){
        ViewTarget Hdiscount=new ViewTarget(R.id.expand_my_interest,this);

        RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lps.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lps.addRule(RelativeLayout.CENTER_IN_PARENT);
        int margin = ((Number) (getResources().getDisplayMetrics().density * 80)).intValue();
        lps.setMargins(margin, margin, 10, margin);

        ShowcaseView sv= new ShowcaseView.Builder(this)
                .setTarget(Hdiscount)
                .setContentTitle("علایق من")
                .setContentText("از این قسمت شما میتوانید زیر مجموعه ای را به علایق خودتان اضافه کنید تا اعلاناتی را دریافت کنید که نیاز دارید.")
                .hideOnTouchOutside()
                .setStyle(R.style.CustomShowcaseTheme)
                .build();
        sv.setButtonText("خب");
        sv.setButtonPosition(lps);

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}