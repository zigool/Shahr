package com.ariana.shahre_ma.WebServiceGet;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ariana.shahre_ma.Cards.Business_Card_Adapter;
import com.ariana.shahre_ma.DateBaseSqlite.DataBaseSqlite;
import com.ariana.shahre_ma.DateBaseSqlite.Query;
import com.ariana.shahre_ma.Fields.FieldClass;
import com.ariana.shahre_ma.MyBusiness.My_Business;
import com.ariana.shahre_ma.Settings.KeySettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ariana2 on 6/6/2015.
 */
public class HTTPGetBusinessMemberJson extends AsyncTask<String,Void, Integer>
{

    private static Context context;
    FieldClass fc=new FieldClass();
    Query query;
    private  String url_Business;

    Integer Id[];
    String market[];
    String code[];
    String phone[];
    String mobile[];
    String fax[];
    String email[];
    String businessowner[];
    String address[];
    String description[];
    String startdate[];
    String expirationdate[];
    String inactive[];
    String subset[];
    Integer subsetid[];
    Double longitude[];
    Double latitude[];
    Integer areaid[];
    String area1[];
    String user[];
    Integer userid[];
    Integer field1[];
    Integer field2[];
    Integer field3[];
    Integer field4[];
    Integer field5[];
    Integer field6[];
    Integer field7[];
    Integer ratecount[];
    Double ratevalue[];

    Integer len=0;
    Integer i=0;


    /**
     *
     * @param member
     */
    public   void SetUrl_businessMember(Integer member)
    {

        url_Business="http://test.shahrma.com/api/ApiGiveMemberBusiness?memberId="+member;
        Log.i("url_Business",url_Business);

    }

    /**
     *
     * @return
     */
    private String GetUrl_business()
    {
        return  url_Business;
    }

    /**
     *
     * @param c
     */
    public HTTPGetBusinessMemberJson(Context c) {
        context = c;
    }

    /**
     *
     * @param args
     * @return
     */
    protected Integer doInBackground(String... args) {
        Integer result=0;
        try {

            InputStream jsonStream = getStreamFromURL(GetUrl_business(), "GET");
            String jsonString = streamToString(jsonStream);
            parseJSON(jsonString);

            result=1;
        } catch (Exception e) {
            // Toast.makeText(getApplicationContext(),"do in background", Toast.LENGTH_LONG).show();
            result=0;
        }
        return result;


    }

    /**
     *
     */
    @Override
    protected void onPostExecute(Integer result) {
        //try {

            Integer count=0;
            Integer cityid=0;
            Integer idsubset=0;
            //  Toast.makeText(context,market[0], Toast.LENGTH_LONG).show();
            DataBaseSqlite dbs = new DataBaseSqlite(context);
            KeySettings setting=new KeySettings(context);
            query=new Query(context);
            cityid=query.getCityId(setting.getCityName());
            idsubset=fc.GetSubsetId();
            if(len>0){
                    dbs.delete_Business(cityid,idsubset);

                    //  dbs.delete_Business();
                    for (int i = 0; i <len; i++)
                    {
                        Cursor rows=dbs.select_CountBusinessId(Id[i]);
                        rows.moveToFirst();
                        count=rows.getInt(0);
                        rows.close();
                        Log.i("ID", String.valueOf(Id[i]));
                        Log.i("count", String.valueOf(count));



                        if(count==0)
                            dbs.Add_business(Id[i], market[i], code[i], phone[i], mobile[i], fax[i], email[i], businessowner[i], address[i], description[i], startdate[i], expirationdate[i], inactive[i], subset[i], subsetid[i], longitude[i], latitude[i], areaid[i], area1[i], user[i],cityid, userid[i], field1[i], field2[i], field3[i], field4[i], field5[i], field6[i], field7[i], ratecount[i], ratevalue[i]);

                    }

                final My_Business business=new My_Business();
                business.mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        Business_Card_Adapter adapter = new Business_Card_Adapter(context);
                        business.mRecyclerView.setAdapter(adapter);
                        business.job_list_Adapter.notifyDataSetChanged();
                    }
                });

            }
            else {
                final My_Business business=new My_Business();
                business.mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        Business_Card_Adapter adapter = new Business_Card_Adapter(context);
                        business.mRecyclerView.setAdapter(adapter);
                        business.job_list_Adapter.notifyDataSetChanged();
                    }
                });
                        Log.i("Count Business : ","دریافت ثبت شده ها");
            }
/*
        } catch (Exception e) {
            Toast.makeText(context, "در پایگاه داده ذخیره نشد", Toast.LENGTH_LONG).show();
        }*/
    }

    /**
     *
     * @param JSONString
     */
    void parseJSON(String JSONString) {

        try {

            Log.i("json",JSONString);
            JSONArray areas = new JSONArray(JSONString);

            Id=new Integer[areas.length()];
            market=new String[areas.length()];
            code=new String[areas.length()];
            phone=new String[areas.length()];
            mobile=new String[areas.length()];
            fax=new String[areas.length()];
            email=new String[areas.length()];
            businessowner=new String[areas.length()];
            address=new String[areas.length()];
            description=new String[areas.length()];
            startdate=new String[areas.length()];
            expirationdate=new String[areas.length()];
            inactive=new String[areas.length()];
            subset=new String[areas.length()];
            subsetid=new Integer[areas.length()];
            longitude=new Double[areas.length()];
            latitude=new Double[areas.length()];
            areaid=new Integer[areas.length()];
            area1=new String[areas.length()];
            user=new String[areas.length()];
            userid=new Integer[areas.length()];
            field1=new Integer[areas.length()];
            field2=new Integer[areas.length()];
            field3=new Integer[areas.length()];
            field4=new Integer[areas.length()];
            field5=new Integer[areas.length()];
            field6=new Integer[areas.length()];
            field7=new Integer[areas.length()];
            ratecount=new Integer[areas.length()];
            ratevalue=new Double[areas.length()];
            len=areas.length();
            Log.i("len",String.valueOf(areas.length()));
            for (int i = 0; i < areas.length(); i++) {
                Log.i("for","1");
                JSONObject area = areas.getJSONObject(i);
                Log.i("for+1", "0");
                Id[i]=area.getInt("Id");
                Log.i("BusinessOwner1",area.getString("BusinessOwner"));
                inactive[i]=area.getString("Inactive");
                Log.i("BusinessOwner2",area.getString("BusinessOwner"));

                if(area.getString("Latitude").equals(null))
                {
                    latitude[i] = 0.0;
                    longitude[i] = 0.0;
                }
                else
                {
                    latitude[i] = Double.valueOf(area.getString("Latitude"));
                    longitude[i] = Double.valueOf(area.getString("Longitude"));
                }



                market[i]=area.getString("Market");
                mobile[i]=area.getString("Mobile");
                phone[i]=area.getString("Phone");
                startdate[i]=area.getString("Startdate");
                subset[i]=area.getString("Subset");
                subsetid[i]=area.getInt("SubsetId");
                user[i]=area.getString("User");
                userid[i]=area.getInt("UserId");


                ratecount[i]=area.getInt("RateCount");
                ratevalue[i]=area.getDouble("RateAverage");

                Log.i("Market", area.getString("Market"));
                Log.i("Mobile",area.getString("Mobile"));
                Log.i("Phone", area.getString("Phone"));
                Log.i("SubsetId",String.valueOf(area.getInt("SubsetId")));
                Log.i("SubsetId",area.getString("Address"));



                address[i]=area.getString("Address");
                area1[i]=area.getString("Area");
                areaid[i]=area.getInt("AreaId");
                businessowner[i]=area.getString("BusinessOwner");
                code[i]=area.getString("Code");
                description[i]=area.getString("Description");
                email[i]=area.getString("Email");
                expirationdate[i]=area.getString("ExpirationDate");
                fax[i]=area.getString("Fax");
                field1[i]=area.getInt("Field1");
                field2[i]=area.getInt("Field2");
                field3[i]=area.getInt("Field3");
                field4[i]=area.getInt("Field4");
                field5[i]=area.getInt("Field5");
                field6[i] = area.getInt("Field6");
                field7[i] = area.getInt("Field7");
                Log.i("BusinessOwner",area.getString("BusinessOwner"));
                fc.SetBusiness_Id(area.getInt("Id"));


            }

        } catch (JSONException e) {
            // Toast.makeText(getApplicationContext()," parse Json", Toast.LENGTH_LONG).show();
            Log.i("JSONException",e.toString());
        }
    }


    /**
     *
     * @param urlString
     * @param method
     * @return
     */
    InputStream getStreamFromURL(String urlString, String method) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setReadTimeout(10000);
            huc.setConnectTimeout(15000);
            huc.setRequestMethod(method);
            huc.setDoInput(true);

            huc.connect();

            return huc.getInputStream();
        } catch (Exception e) {
            return null;
        }

    }

    /**
     *
     * @param is
     * @return
     */
    String streamToString(InputStream is) {
        String result = "";
        String line = null;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                result = line + "\n";
            }
        } catch (Exception e) {

        }


        return result;
    }

}
