package com.ariana.shahre_ma.WebServiceGet;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ariana.shahre_ma.DateBaseSqlite.DataBaseSqlite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ariana on 7/12/2015.
 */
public class HTTPGetLikeDisCount extends AsyncTask<String, String, String>
{
    ProgressDialog pd;
    Integer errorCode=0;
    private static Context context;

    private static final String url_LikeDisCount="http://test.shahrma.com/api/apigivecollection";

    Integer Id[];
    Boolean like[];
    Integer discountid[];
    Integer memberid[];
    Integer likecount[];
    Integer dislikecount[];
    Integer len;


    /**
     *
     * @param c
     */
    public HTTPGetLikeDisCount(Context c) {
        context = c;
    }

    /**
     *
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("loading");
        pd.show();
    }

    /**
     *
     * @param args
     * @return
     */
    protected String doInBackground(String... args) {
        try {


            InputStream jsonStream = getStreamFromURL(url_LikeDisCount, "GET");
            String jsonString = streamToString(jsonStream);
            parseJSON(jsonString);
            onPostExecute();
        } catch (Exception e) {
        }
        return null;


    }

    /**
     *
     */
    protected void onPostExecute() {
        try {
            pd.dismiss();
        } catch (Exception e) {
            pd.dismiss();
        }
    }


    /**
     *
     * @param JSONString
     */
    void parseJSON(String JSONString) {
        try {

            JSONArray areas = new JSONArray(JSONString);

            Id=new Integer[areas.length()];
            like=new Boolean[areas.length()];
            memberid=new Integer[areas.length()];
            discountid=new Integer[areas.length()];
            likecount=new Integer[areas.length()];
            dislikecount=new Integer[areas.length()];
            len=areas.length();
            for (int i = 0; i < areas.length(); i++) {

                JSONObject area = areas.getJSONObject(i);
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
