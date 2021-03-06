package com.ariana.shahre_ma.WebServiceGet;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ariana.shahre_ma.DateBaseSqlite.AddDataBaseSqlite;
import com.ariana.shahre_ma.DateBaseSqlite.DataBaseSqlite;
import com.ariana.shahre_ma.DateBaseSqlite.DeleteDataBaseSqlite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ariana on 6/15/2015.
 */
public class HTTPGetInterestJson  extends AsyncTask<String,Void, Integer>
{
    ProgressDialog pd;

    private static Context context;
    public HTTPGetInterestJson(Context c) {
        context = c;
    }
    private static  String url_Interest;
    Integer errorCode=0;

    Integer subsetid[];
    Integer memberid[];
    Integer len;

    public void SetUrl_Interest(Integer memberid)
    {
        url_Interest ="http://test.shahrma.com/api/ApiGiveInterest?memberId="+memberid;

    }

    private String getUrl_Interest()
    {
        return url_Interest;
    }
    /**
     *
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("دریافت...");
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
            InputStream jsonStream = getStreamFromURL(getUrl_Interest(), "GET");
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
     */

    @Override
    protected void onPostExecute(Integer result) {
        onPostExecute(result);
        try {
            if (result == 1) {

                try {
                    AddDataBaseSqlite adb = new AddDataBaseSqlite(context);
                    DeleteDataBaseSqlite ddb=new DeleteDataBaseSqlite(context);
                    ddb.delete_Interest();
                    for (int i = 0; i < len; i++) {

                        adb.Add_Interest(subsetid[i], memberid[i]);
                        Log.i("onPostExecuteInterest", "strt1");
                    }
                    pd.dismiss();
                } catch (Exception e) {
                    Log.e("ExceptionInterest", e.toString());
                }
            } else {

            }
        }
        catch (Exception e){}
    }

    /**
     *
     * @param JSONString
     */
    void parseJSON(String JSONString) {
        Log.i("JsonInterest",JSONString);
        try {

            JSONArray areas = new JSONArray(JSONString);

            subsetid=new Integer[areas.length()];
            memberid=new Integer[areas.length()];
            len=areas.length();
            for (int i = 0; i < areas.length(); i++) {

                JSONObject area = areas.getJSONObject(i);

                subsetid[i]=area.getInt("SubsetId");
                memberid[i]=area.getInt("MemberId");
            }

        } catch (JSONException e) {

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



