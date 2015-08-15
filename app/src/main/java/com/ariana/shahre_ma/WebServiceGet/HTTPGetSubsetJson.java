package com.ariana.shahre_ma.WebServiceGet;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.ariana.shahre_ma.DateBaseSqlite.DataBaseSqlite;
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
public class HTTPGetSubsetJson extends AsyncTask<String,Void,Integer>
{

    private static Context context;

    private static final String url_subset="http://test.shahrma.com/api/apigivesubset";

    Integer Id[];
    String subsetname[];
    Integer collectionId[];
    Integer len;

    /**
     *
     * @param c
     */
    public HTTPGetSubsetJson(Context c) {
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


            InputStream jsonStream = getStreamFromURL(url_subset, "GET");
            String jsonString = streamToString(jsonStream);
            parseJSON(jsonString);
            result=1;
        } catch (Exception e) {
            result=0;
            // Toast.makeText(getApplicationContext(),"do in background", Toast.LENGTH_LONG).show();
        }
        return result;


    }

    @Override
    protected void onPostExecute(Integer result) {
//        onPostExecute(result);
        if(result==1) {
            try {
                KeySettings setting=new KeySettings(context);
                if(len>0) {

                    DataBaseSqlite dbs = new DataBaseSqlite(context);
                    dbs.delete_Subset();

                    for (int i = 0; i < len; i++) {
                        dbs.Add_subset(Id[i], subsetname[i], collectionId[i]);

                    }
                }


                setting.saveSubset(true);
                Intent intent = new Intent("Collection");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                if(setting.getAllUpdate()) {
                    HTTPGetAreaJosn httpGetAreaJosn = new HTTPGetAreaJosn(context);
                    httpGetAreaJosn.execute();
                }

            } catch (Exception e) {
                //Toast.makeText(context, "در پایگاه داده ذخیره نشد", Toast.LENGTH_LONG).show();
            }
        }
        else
        {

        }
    }


    void parseJSON(String JSONString) {

        Integer ii = 0;
        try {

            JSONArray areas = new JSONArray(JSONString);
            Log.i("JSONsubset",JSONString);
            Id=new Integer[areas.length()];
            subsetname=new String[areas.length()];
            collectionId=new Integer[areas.length()];
            len=areas.length();
            for (int i = 0; i < areas.length(); i++) {

                JSONObject area = areas.getJSONObject(i);
                collectionId[i]=area.getInt("CollectionId");
                Id[i]=area.getInt("Id");
                subsetname[i]=area.getString("Name");


            }

        } catch (JSONException e) {
           // Toast.makeText(getApplicationContext()," parse Json", Toast.LENGTH_LONG).show();
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