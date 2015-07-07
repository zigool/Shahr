package com.ariana.shahre_ma.WebServicePost;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.ariana.shahre_ma.DateBaseSqlite.DataBaseSqlite;
import com.ariana.shahre_ma.Fields.FieldClass;
import com.ariana.shahre_ma.MainActivity;
import com.ariana.shahre_ma.MyProfile.Log_In;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ariana on 7/5/2015.
 */
public class HTTPPostMemberEditJson extends AsyncTask<String, Long, Integer>
{

    private static  final  String url_Member="http://test.shahrma.com/api/ApiUpdateMembers";

    private ProgressDialog mProgressDialog;
    // variable get json
    private static String data_json;
    // variable response
    private  static String response_message;
    FieldClass fc=new FieldClass();

    ProgressDialog pd;
    // get/set

    // Set json Member
    public void  SetMember_Json(String json_member)
    {
        data_json=json_member;
    }

    // Get Josn Member
    public String GetMember_json()
    {
        return data_json;
    }


    // Set response
    private void  SetResponse(String json_member)
    {
        response_message=json_member;
    }
    // Get response
    public String GetResponse()
    {
        return response_message;
    }

    private static Context context;
    public HTTPPostMemberEditJson(Context c) {
        context = c;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("منتظر بمانید...");
        pd.setCancelable(false);
        pd.show();
    }
    @Override
    protected Integer doInBackground(String... params) {
        Integer i=0;
        try {


            JSONObject json = new JSONObject(GetMember_json()); //your array;
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext httpContext = new BasicHttpContext();
            HttpPost httpPost = new HttpPost(url_Member);

            StringEntity se = new StringEntity(json.toString(),"UTF-8");

            httpPost.setEntity(se);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");


            HttpResponse httpresponse = httpClient.execute(httpPost, httpContext); //execute your request and parse response


            //  String json_String = EntityUtils.toString(entity); //if response in JSON format

            HttpEntity entity = httpresponse.getEntity();
            InputStream webs = entity.getContent();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(webs, "UTF-8"), 8);
                response_message = (reader.readLine());
                webs.close();
                i=1;
            } catch (Exception e) {
                Log.e("Error in conversion: ", e.toString());
            }

            //SetResponse(json_String);

        } catch (Exception e) {
            i=0;
            e.printStackTrace();
            // Toast.makeText(, e.toString(), Toast.LENGTH_LONG).show();
        }

        return i;
    }

    @Override
    protected void onPostExecute(Integer result) {
            /* Download complete. Lets update UI */

        if(result==1) {
            Log.i("onPostExecute", "onPostExecute");
            DataBaseSqlite dbs = new DataBaseSqlite(context);
            Integer ID = Integer.parseInt(GetResponse());
            dbs.delete_Member();
            if (ID >= 0) {
                Log.i("fc.GetMember_Name()", fc.GetMember_Name());

                dbs.Add_member(ID, fc.GetMember_Name(), fc.GetMember_Email(), fc.GetMember_Mobile(), fc.GetMember_Age(), fc.GetMember_Sex(), fc.GetMember_UserName(), fc.GetMember_Password(), fc.GetMember_CityId());
                pd.dismiss();
                Intent i = new Intent(this.context, MainActivity.class);
                this.context.startActivity(i);
                // Toast.makeText(context, "کاربر ثبت شد!", Toast.LENGTH_LONG).show();
            } else {
                pd.dismiss();
                //  Toast.makeText(context, "کاربر ساخته نشد دوباره امتحان کنید", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            pd.dismiss();
        }




    }

}