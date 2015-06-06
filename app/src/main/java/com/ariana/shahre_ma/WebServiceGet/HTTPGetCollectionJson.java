package com.ariana.shahre_ma.WebServiceGet;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ariana.shahre_ma.DateBaseSqlite.CollectionSqlite;

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
public class HTTPGetCollectionJson extends AsyncTask<String, String, String>
    {

        private static Context context;
        public HTTPGetCollectionJson(Context c) {
        context = c;
    }
        private static final String url_collection="http://test.shahrma.com/api/apigivecollection";

        Integer Id[];
        String collectionname[];
        Integer len;

    protected String doInBackground(String... args) {
        try {


            InputStream jsonStream = getStreamFromURL(url_collection, "GET");
            String jsonString = streamToString(jsonStream);
            parseJSON(jsonString);
            onPostExecute();
        } catch (Exception e) {
            // Toast.makeText(getApplicationContext(),"do in background", Toast.LENGTH_LONG).show();
        }
        return null;


    }

    protected void onPostExecute() {
        try {

            CollectionSqlite collSql = new CollectionSqlite(context);


            for (int i = 0; i <len; i++)
            {
                collSql.Add(Id[i],collectionname[i]);

            }
        } catch (Exception e) {
            Toast.makeText(context, "در پایگاه داده ذخیره نشد", Toast.LENGTH_LONG).show();
        }
    }


    void parseJSON(String JSONString) {

        Integer ii = 0;
        try {

            JSONArray areas = new JSONArray(JSONString);

            Id=new Integer[areas.length()];
            collectionname=new String[areas.length()];
            len=areas.length();
            for (int i = 0; i < areas.length(); i++) {

                JSONObject area = areas.getJSONObject(i);
                Id[i]=area.getInt("Id");
                collectionname[i]=area.getString("Name");


            }

        } catch (JSONException e) {
            // Toast.makeText(getApplicationContext()," parse Json", Toast.LENGTH_LONG).show();
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

            return huc.getInputStream();
        } catch (Exception e) {
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
        } catch (Exception e) {
        }


        return result;
    }

}
