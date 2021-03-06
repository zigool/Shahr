package com.ariana.shahre_ma.WebServiceGet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;

import com.ariana.shahre_ma.Date.CalendarTool;
import com.ariana.shahre_ma.Date.DateTime;
import com.ariana.shahre_ma.DateBaseSqlite.AddDataBaseSqlite;
import com.ariana.shahre_ma.DateBaseSqlite.DataBaseSqlite;
import com.ariana.shahre_ma.DateBaseSqlite.DeleteDataBaseSqlite;
import com.ariana.shahre_ma.MainActivity;
import com.ariana.shahre_ma.Settings.KeySettings;
import com.ariana.shahre_ma.Settings.UpdateActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ariana on 7/1/2015.
 */
public class HTTPGetFieldActivityJson extends AsyncTask<String,Void,Integer>
{

    private String url1_get_FieldActivity= "http://test.shahrma.com/api/ApiGiveFieldActivity";
    ProgressDialog pd;
    Integer id[];
    String activity[];
    Context context;
    Integer len;
    CalendarTool ct=new CalendarTool();
    Integer errorCode=0;


    /**
     *
     * @param context
     */
    public HTTPGetFieldActivityJson(Context context)
    {
        this.context=context;
    }

    /**
     *
     */
    @Override
    protected void onPreExecute() {

    }
    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Integer doInBackground(String... params) {
        Integer result=0;
        try {
            InputStream jsonStream = getStreamFromURL(url1_get_FieldActivity, "GET");
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
     * @param values
     */
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    /**
     *
     * @param JSONString
     */
    void parseJSON(String JSONString) {
        try {

            Log.i("jsonFieldActivity",JSONString);

            JSONArray areas = new JSONArray(JSONString);
            id=new Integer[areas.length()];
            activity=new String[areas.length()];
            len=areas.length();
            for (int i = 0; i < areas.length(); i++) {

                JSONObject area = areas.getJSONObject(i);
                id[i]=area.getInt("Id");
                activity[i]=area.getString("Activity").trim();

            }

        } catch (JSONException e) {
            Log.e("JSONException",e.toString());

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

    /**
     *
     * @param o
     */
    @Override
    protected void onPostExecute(Integer o) {

        KeySettings setting=new KeySettings(context);
        AddDataBaseSqlite adb = new AddDataBaseSqlite(context);
        DeleteDataBaseSqlite ddb=new DeleteDataBaseSqlite(context);

        try
        {
            Log.e("Integer",String.valueOf(o));
            if (o == 1)
            {

                if (len > 0)
                {

                    ddb.delete_Update();
                    ddb.delete_FiledActivity();
                    for (int i = 0; i < len; i++)
                        adb.Add_FieldActivity(id[i], activity[i]);

                    adb.Add_Update(ct.getIranianDate());


                    try
                    {
                        UpdateActivity.PgUpdate.post(new Runnable() {
                            @Override
                            public void run() {
                                UpdateActivity.PgUpdate.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                    catch (Exception e){}
                    //end All update
                    setting.setUpdateAll(false);
                    //Get FieldActivity
                    setting.saveFieldActivity(true);

                    Intent mainIntent = new Intent(context.getApplicationContext(),MainActivity.class);
                    context.startActivity(mainIntent);
                    ((Activity)context).finish();

                }
                else
                {
                    if (setting.getAllUpdate())
                    {
                        Intent intent = new Intent("GetCity");
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    }
                }

            }
            else
            {
                if (setting.getAllUpdate())
                {
                    Intent intent = new Intent("GetCity");
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }
            }
        }
        catch (Exception e)
        {
            Log.e("ExceptionFiledActivity",e.toString());
        }
    }


}
