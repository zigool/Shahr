package com.ariana.shahre_ma.WebServiceGet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ariana.shahre_ma.DateBaseSqlite.DataBaseSqlite;
import com.ariana.shahre_ma.DateBaseSqlite.Query;
import com.ariana.shahre_ma.Fields.FieldClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ariana on 7/2/2015.
 */
public class HTTPGetAreaJosn extends AsyncTask<String, String, String>

{
    Query query;
    FieldClass fc=new FieldClass();
    Context context;
    Integer Id_area[];
    Integer CityId_area[];
    String Name_Area[];
    Integer len_Area=0;
    private String url1_get_Area = "http://test.shahrma.com/api/apigivearea";

    public HTTPGetAreaJosn(Context context)
    {
        this.context=context;
        query=new Query(context);
    }
        protected String doInBackground(String... args) {
            try {


                InputStream jsonStream = getStreamFromURL(url1_get_Area, "GET");
                String jsonString = streamToString(jsonStream);
                parseJSON(jsonString);

            } catch (Exception e) {
                // Toast.makeText(getApplicationContext(),"do in background", Toast.LENGTH_LONG).show();
            }
            return null;


        }

        protected void onPostExecute(String file_url) {
            if(len_Area>0)
            {
                try
                {
                    DataBaseSqlite db=new DataBaseSqlite(context);

                    for (int i = 0; i < len_Area; i++) {
                        db.Add_area(Id_area[i],Name_Area[i],CityId_area[i]);
                    }


                } catch (Exception e)
                {

                }
            }
            else
            {
                //pToast.makeText(getApplicationContext(),"همگام سازی انجام نشد دوباره امتحان کنید",Toast.LENGTH_LONG).show();
            }
        }


        void parseJSON(String JSONString) {

            Integer ii = 0;
            try {

                JSONArray areas = new JSONArray(JSONString);

                Id_area=new Integer[areas.length()];
                CityId_area=new Integer[areas.length()];
                Name_Area=new String[areas.length()];
                len_Area=areas.length();
                for (int i = 0; i < areas.length(); i++) {

                    JSONObject area = areas.getJSONObject(i);

                    Name_Area[i]=area.getString("AreaName");
                    CityId_area[i]=area.getInt("CityId");
                    Id_area[i]=area.getInt("Id");

                }

            } catch (JSONException e) {
                //  Toast.makeText(getApplicationContext()," parse Json", Toast.LENGTH_LONG).show();
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