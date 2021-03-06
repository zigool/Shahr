package com.ariana.shahre_ma.WebServiceGet;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.ariana.shahre_ma.Date.CalendarTool;
import com.ariana.shahre_ma.DateBaseSqlite.AddDataBaseSqlite;
import com.ariana.shahre_ma.DateBaseSqlite.DataBaseSqlite;
import com.ariana.shahre_ma.DateBaseSqlite.DeleteDataBaseSqlite;
import com.ariana.shahre_ma.Fields.FieldClass;
import com.ariana.shahre_ma.MyBusiness.Discount;
import com.ariana.shahre_ma.MyBusiness.discount_Adapter;
import com.ariana.shahre_ma.MyBusiness.discount_item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ariana on 7/12/2015.
 */
public class HTTPGetDisCountJson extends AsyncTask<String,Void,Integer> {
    private static Context context;


    Integer errorCode=0;
    Integer Id[];
    String description[];
    String startdate[];
    String image[];
    String text[];
    String percent[];
    String expirationdate[];
    Integer businessid[];

    Integer len;
    Integer memberid;
    FieldClass fc=new FieldClass();
    ProgressDialog pd;



    /**
     *
     * @param memberid
     */
    public void seturl_DisCount(Integer memberid) {

        this.memberid=memberid;
    }

    /**
     *
     * @return
     */
    private String geturl_discount()
    {

        String Result="";
        Result="http://test.shahrma.com/api/apiGiveDisCount?memberid="+memberid;
        Log.i("URLdiscount",Result);
        return  Result;
    }

    /**
     *
     * @param c
     */
    public HTTPGetDisCountJson(Context c) {
        context = c;

    }

    /**
     *
     */
    @Override
    protected void onPreExecute() {
       super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("دریافت..." );
        pd.setCancelable(false);
        pd.show();
    }

    /**
     *
     * @param args
     * @return
     */
    protected Integer doInBackground(String... args) {
        Integer result=0;
        try {


            InputStream jsonStream = getStreamFromURL(geturl_discount(), "GET");
            String jsonString = streamToString(jsonStream);
            parseJSON(jsonString);
            result=1;

        } catch (Exception e) {
            result=0;

        }
        return result;


    }

    /**
     *
     * @param result
     */
    @Override
    protected void onPostExecute(Integer result) {
        try {
        if(result==1) {
            if(len==0 &&  errorCode==200){
                Discount.img_null.setVisibility(View.VISIBLE);
                Discount.tv_null.setVisibility(View.VISIBLE);
            }
                AddDataBaseSqlite adb = new AddDataBaseSqlite(context);
                DeleteDataBaseSqlite ddb = new DeleteDataBaseSqlite(context);

                ddb.delete_DisCountMember();
                for (int i = 0; i < len; i++) {
                    adb.Add_DisCountMember(Id[i], text[i], image[i], startdate[i], expirationdate[i], description[i], percent[i], businessid[i]);
                }
                pd.dismiss();
                Discount dis = new Discount();
                discount_Adapter adapter = new discount_Adapter(context, generateData());
                Discount.listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

        } else
            {
                pd.dismiss();
            }
        } catch (Exception e) {
                pd.dismiss();

            }

    }
    public ArrayList<discount_item> generateData()
    {
        CalendarTool ct=new CalendarTool();
        CalendarTool ct1=new CalendarTool();
        ArrayList<discount_item> items = new ArrayList<discount_item>();
        DataBaseSqlite db = new DataBaseSqlite(context);


        Cursor rows = db.select_DisCountMember(fc.GetBusiness_Id());
        if (rows.moveToFirst()) {
            do {
                ct.setGregorianDate(Integer.valueOf(rows.getString(3).substring(0, 4)),Integer.valueOf(rows.getString(3).substring(5, 7)),Integer.valueOf(rows.getString(3).substring(8, 10)));
                ct1.setGregorianDate(Integer.valueOf(rows.getString(4).substring(0, 4)),Integer.valueOf(rows.getString(4).substring(5, 7)),Integer.valueOf(rows.getString(4).substring(8, 10)));
                items.add(new discount_item(" % " + rows.getString(6), rows.getString(5),ct1.getIranianDate(), rows.getString(1),ct.getIranianDate(), rows.getInt(0)));

            } while (rows.moveToNext());
        }


        return items;
    }

    /**
     *
     * @param JSONString
     */
    void parseJSON(String JSONString) {
        try {
            Log.i("JSONdiscountMember", JSONString);
            JSONArray areas = new JSONArray(JSONString);

            Id=new Integer[areas.length()];
            text=new String[areas.length()];
            image=new String[areas.length()];
            startdate=new String[areas.length()];
            expirationdate=new String[areas.length()];
            description=new String[areas.length()];
            percent=new String[areas.length()];
            businessid=new Integer[areas.length()];
            len=areas.length();

            for (int i = 0; i < areas.length(); i++) {

                JSONObject area = areas.getJSONObject(i);
                Id[i]=area.getInt("Id");
                text[i]=area.getString("Text");
                image[i]=area.getString("Image");
                startdate[i]=area.getString("Startdate");
                expirationdate[i]=area.getString("ExpirationDate");
                description[i]=area.getString("Description");
                percent[i]=area.getString("Percent");
                businessid[i]=area.getInt("BusinessId");


            }

        } catch (JSONException e) {

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
            errorCode=huc.getResponseCode();
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
