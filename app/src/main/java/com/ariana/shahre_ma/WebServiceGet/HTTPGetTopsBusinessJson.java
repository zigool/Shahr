package com.ariana.shahre_ma.WebServiceGet;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.ariana.shahre_ma.Cards.TopBusiness_Card_Adapter;
import com.ariana.shahre_ma.Cards.TopDiscount_Card_Adapter;
import com.ariana.shahre_ma.DateBaseSqlite.AddDataBaseSqlite;
import com.ariana.shahre_ma.DateBaseSqlite.DeleteDataBaseSqlite;
import com.ariana.shahre_ma.DateBaseSqlite.Query;
import com.ariana.shahre_ma.Fields.FieldClass;
import com.ariana.shahre_ma.Frag_main_Top_Business;
import com.ariana.shahre_ma.Frag_main_Top_discount;
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
 * Created by ariana on 7/31/2015.
 */
public class HTTPGetTopsBusinessJson extends AsyncTask<String,Void,Integer>
{

    String urltopbusiness="";
    Context context;
    Integer errorCode=0;
    FieldClass fc=new FieldClass();
    Query query;


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
    String src[];
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

    Integer discountid[];
    String discounttext[];
    String discountimage[];
    String discountstartdate[];
    String discountexpirationdate[];
    String discountdescription[];
    String discountpercent[];
    Integer discountbusinessid[];

    Integer likediscount[];
    Integer dislikediscount[];


    Integer len=0;
    Integer i=0;


    public HTTPGetTopsBusinessJson(Context context)
    {
        this.context=context;
    }

    public void SetTopBusiness(Integer cityid)
    {
        urltopbusiness="http://test.shahrma.com/api/ApiGiveTop?cityId="+cityid;
    }

    private String getTopBusiness()
    {
        return urltopbusiness;
    }



    @Override
    protected Integer doInBackground(String... params) {
        Integer result=0;
        try
        {
            Log.i("url",getTopBusiness());
            InputStream inputStream=getStreamFromURL(getTopBusiness(),"GET");
            String json=streamToString(inputStream);
            ParsJSON(json);
            result=1;
        }
        catch (Exception e)
        {
            Log.e("TOpbusiness",e.toString());
            result=0;
        }
        return result;
    }


    void ParsJSON(String JSONString) {

        try {

            Log.i("JSONString",JSONString);
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
            src=new String[areas.length()];
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

            discountid = new Integer[areas.length()];
            discounttext = new String[areas.length()];
            discountimage=new String[areas.length()];
            discountstartdate=new String[areas.length()];
            discountexpirationdate=new String[areas.length()];
            discountdescription=new String[areas.length()];
            discountpercent=new String[areas.length()];
            discountbusinessid=new Integer[areas.length()];

            likediscount=new Integer[areas.length()];
            dislikediscount=new Integer[areas.length()];

            len=areas.length();
            for (int i = 0; i < areas.length(); i++) {


                JSONObject area = areas.getJSONObject(i);
                address[i]=area.getString("Address");
                area1[i]=area.getString("Area");
                areaid[i]=area.getInt("AreaId");
                businessowner[i]=area.getString("BusinessOwner");
                code[i]=area.getString("Code");
                description[i] = area.getString("Description");
                email[i] = area.getString("Email");
                expirationdate[i]=area.getString("ExpirationDate");
                fax[i]=area.getString("Fax");
                field1[i]=area.getInt("Field1");
                field2[i]=area.getInt("Field2");
                field3[i] = area.getInt("Field3");
                field4[i]=area.getInt("Field4");
                field5[i]=area.getInt("Field5");
                field6[i] = area.getInt("Field6");
                field7[i] = area.getInt("Field7");
                Id[i]=area.getInt("Id");
                inactive[i]=area.getString("Inactive");
                latitude[i]=Double.valueOf(area.getString("Latitude"));
                longitude[i]=Double.valueOf(area.getString("Longitude"));
                market[i]=area.getString("Market");
                mobile[i]=area.getString("Mobile");
                phone[i]=area.getString("Phone");
                startdate[i]=area.getString("Startdate");
                subset[i]=area.getString("Subset");
                subsetid[i]=area.getInt("SubsetId");
                user[i]=area.getString("User");
                userid[i]=area.getInt("UserId");
                src[i]=area.getString("Src");

                ratecount[i]=area.getInt("RateCount");
                ratevalue[i]=area.getDouble("RateAverage");


                discountid[i]=area.getInt("DiscountId");
                discounttext[i]=area.getString("DiscountText");
                discountimage[i]=area.getString("DiscountImage");
                discountstartdate[i]=area.getString("DiscountStartdate");
                discountexpirationdate[i]=area.getString("DiscountExpirationDate");
                discountpercent[i]=area.getString("DiscountPercent");
                discountdescription[i]=area.getString("DiscountDescription");
                discountbusinessid[i]=area.getInt("Id");

                likediscount[i]=area.getInt("DiscountLike");
                dislikediscount[i]=area.getInt("DiscountDislike");



            }




        } catch (JSONException e) {
            Log.i("ExceptionTopBusiness",e.toString());
        }
    }

    InputStream getStreamFromURL(String urlString, String method) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setReadTimeout(10000);
            huc.setConnectTimeout(15000);
            huc.setRequestMethod(method);
            huc.setDoInput(true);

            huc.connect();
            errorCode=huc.getResponseCode();
            return huc.getInputStream();
        } catch (Exception e) {
            Log.e("FromURLTopBusiness",e.toString());
            return null;
        }
    }

    String streamToString(InputStream is) {
        String result = "";
        String line = null;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                result = line + "\n";
            }
        } catch (Exception e)
        {
            Log.e("ToStringTopBusiness",e.toString());
        }

        return result;
    }


    @Override
    protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                try {

                    AddDataBaseSqlite adb = new AddDataBaseSqlite(context);
                    DeleteDataBaseSqlite ddb=new DeleteDataBaseSqlite(context);
                    KeySettings setting=new KeySettings(context);
                    query=new Query(context);

                    Integer cityid=0;
                    cityid=query.getCityId(setting.getCityName());

                    if(len==0) {

                        Log.i("CountTopsBusiness : ", "فروشگاه ثبت نشد");

                    }

                    else {


                        ddb.delete_BusinessTops();
                        ddb.delete_BusinessDisCount();

                        for (int i = 0; i <len; i++)
                        {
                            ddb.delete_DisCount(discountid[i]);


                            if(discountid[i]==0) {
                                Log.i("ifbusiness","0");
                            }
                            else
                            {
                                Log.i("elsebusiness", "i>0");
                                adb.Add_DisCount(discountid[i], discounttext[i], discountimage[i], discountstartdate[i], discountexpirationdate[i], discountdescription[i], discountpercent[i], discountbusinessid[i],likediscount[i],dislikediscount[i]);
                            }


                            if(i<=9) {
                                adb.Add_businessTops(Id[i], market[i], code[i], phone[i], mobile[i], fax[i], email[i], businessowner[i], address[i], description[i], startdate[i], expirationdate[i], inactive[i], subset[i], subsetid[i], latitude[i], longitude[i], areaid[i], area1[i], user[i], cityid, userid[i], field1[i], field2[i], field3[i], field4[i], field5[i], field6[i], field7[i], ratecount[i], ratevalue[i], src[i]);
                                Frag_main_Top_discount.tv_null.setVisibility(View.VISIBLE);
                                Frag_main_Top_discount.img_null.setVisibility(View.VISIBLE);
                            } else {
                                adb.Add_businessDisCount(Id[i], market[i], code[i], phone[i], mobile[i], fax[i], email[i], businessowner[i], address[i], description[i], startdate[i], expirationdate[i], inactive[i], subset[i], subsetid[i], latitude[i], longitude[i], areaid[i], area1[i], user[i], cityid, userid[i], field1[i], field2[i], field3[i], field4[i], field5[i], field6[i], field7[i], ratecount[i], ratevalue[i], src[i]);
                                Frag_main_Top_discount.tv_null.setVisibility(View.GONE);
                                Frag_main_Top_discount.img_null.setVisibility(View.GONE);
                            }
                        }

                        fc.SetCount_Business(query.getCountBusiness(query.getsubsetID(fc.GetSelected_job())));
                        TopBusiness_Card_Adapter mAdapter = new TopBusiness_Card_Adapter(context);
                        Frag_main_Top_Business.mRecyclerView.setAdapter(mAdapter);
                        TopDiscount_Card_Adapter dAdapter=new TopDiscount_Card_Adapter(context);
                        Frag_main_Top_discount.mRecyclerView.setAdapter(dAdapter);

                  }

                } catch (Exception e) {

                    Log.e("ExceptionBusinessJson",e.toString());
                }

    }
}
