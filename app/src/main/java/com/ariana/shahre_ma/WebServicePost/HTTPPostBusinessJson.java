package com.ariana.shahre_ma.WebServicePost;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.ariana.shahre_ma.DateBaseSqlite.Query;
import com.ariana.shahre_ma.Fields.FieldClass;
import com.ariana.shahre_ma.MainActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.lang.reflect.Field;

/**
 * Created by ariana on 7/1/2015.
 */
public class HTTPPostBusinessJson extends AsyncTask<String,Long,Integer>
{

    String url_Business="http://test.shahrma.com/api/ApiGiveoneBusiness";
    String jsonstring="";
    Query query;
    FieldClass fc=new FieldClass();
    Context context;


    /**
     *
     * @param context
     */
    public HTTPPostBusinessJson(Context context)
    {
        this.context=context;
        query=new Query(context);

    }

    /**
     *
     * @param json
     */
    public void SetBusinessJson(String json)
    {
        jsonstring=json;

    }

    private String GetJson()
    {
        return jsonstring;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Integer doInBackground(String... params) {
        Integer jsonResult=0;
        try
        {
            JSONArray json=new JSONArray();//Json array
            HttpClient httpclient=new DefaultHttpClient();
            HttpContext httpcontext=new BasicHttpContext();
            HttpPost httppost=new HttpPost(url_Business);

            StringEntity se=new StringEntity(GetJson(),"UTF-8");

            httppost.setEntity(se);
            httppost.setHeader("Accept", "application/json");
            httppost.setHeader("Content-type", "application/json");

            HttpResponse response=httpclient.execute(httppost,httpcontext);
            HttpEntity entity=response.getEntity();

            String jsonstring= EntityUtils.toString(entity);

            jsonResult=Integer.parseInt(jsonstring);

        }
        catch (Exception e){

        }
        return jsonResult;
    }

    /**
     *
     * @param integer
     */
    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if(integer==1)
        {
            Intent i = new Intent(this.context, MainActivity.class);
            this.context.startActivity(i);
        }
        else
        {

        }
    }
}
