package com.ariana.shahre_ma.Bazarche.WebServiceGet;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;

import com.ariana.shahre_ma.Bazarche.Product_List;
import com.ariana.shahre_ma.Fields.FieldDataBase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ariana on 8/18/2015.
 */
public class HTTPGetProductJson extends AsyncTask<String,Void,Integer>
    {
        private static Context context;
        FieldDataBase fdb=new FieldDataBase();
        private String url_product="";
        Integer errorCode=0;

        List<Integer> selectId =new ArrayList<>();
        List<String>  selectName =new ArrayList<>();
        List<String>  selectPrice =new ArrayList<>();
        List<Boolean> selectAdaptive =new ArrayList<>();
        List<String>  selectImage =new ArrayList<>();



        public  void setUrl_product(Integer cityid,Integer pagesize,Integer page,Integer sort)
        {
            url_product="http://test.shahrma.com/api/ApiGiveProductList?page="+page+"&pageSize="+pagesize+"&cityId="+cityid+"&sort="+sort;
            Log.i("url",url_product);
        }

        private String getUrl_product()
        {
            return  url_product;
        }
        Integer len;

        /**
         *
         * @param c
         */
        public HTTPGetProductJson(Context c) {
            context = c;
        }

        /**
         *
         * @param args
         * @return
         */
        protected Integer doInBackground(String... args) {
            Integer result=0;
            Product_List.Loading=true;
            try {
                InputStream jsonStream = getStreamFromURL(getUrl_product(), "GET");
                String jsonString = streamToString(jsonStream);
                parseJSON(jsonString);
                result=1;
            } catch (Exception e) {
                result=0;

            }
            return result;


        }

        @Override
        protected void onPostExecute(Integer result) {
            try
            {
                if(result==1)
                {
                    Log.i("get", "Products");

                /*    Product_List.mRecyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            Product_List_Adapter adapter = new Product_List_Adapter(context);
                            Product_List.mRecyclerView.setAdapter(adapter);
                            Product_List.Product_Adapter.notifyDataSetChanged();
                            Product_List.pg.setVisibility(View.GONE);

                        }
                    });*/
                    Intent intent = new Intent("productList");
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }
                else
                {
                    Product_List.retry.post(new Runnable() {
                        @Override
                        public void run() {
                            if (Product_List.retry.getVisibility()==View.INVISIBLE){
                                Product_List.retry.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
            } catch (Exception e) {
                Log.i("onPostExecuteProdutjson", e.toString());
            }
        }


        void parseJSON(String JSONString) {
            try {


                Log.i("JSONProduct", JSONString);

                JSONObject json=new JSONObject(JSONString);
                fdb.setCountProduct(json.getInt("Count"));
                JSONArray areas = json.getJSONArray("Prooducts");
                len=areas.length();
                for (int i = 0; i < areas.length(); i++) {

                    JSONObject area = areas.getJSONObject(i);

                    selectId.add(area.getInt("Id"));
                    selectName.add(area.getString("Name"));
                    Log.i("price",area.getString("Price"));
                    selectPrice.add(area.getString("Price"));
                    selectAdaptive.add(area.getBoolean("Adaptive"));
                    selectImage.add(area.getString("Image"));

                }

                fdb.setId_Product(selectId);
                fdb.setName_Product(selectName);
                fdb.setPrice_Product(selectPrice);
                fdb.setAdaptive__Product(selectAdaptive);
                fdb.setImage_Product(selectImage);

            } catch (JSONException e) {
                Log.i("JSONExceptionProduct", e.toString());
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
                Log.i("TimeOutProdutjson", e.toString());
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
                Log.i("streamToString", e.toString());
            }


            return result;
        }

    }
