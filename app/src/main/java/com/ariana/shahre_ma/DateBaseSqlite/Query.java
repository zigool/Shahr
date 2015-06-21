package com.ariana.shahre_ma.DateBaseSqlite;

import android.content.Context;
import android.database.Cursor;

import com.ariana.shahre_ma.Fields.FieldClass;

/**
 * Created by ariana2 on 6/17/2015.
 */
public class Query {

    Context context;


    FieldClass fc=new FieldClass();
   public Query(Context context)
   {
       this.context=context;

   }




    public Integer getsubsetID(String subsetname) {



        Integer Result = 0;

        try {
            DataBaseSqlite dbs = new DataBaseSqlite(context);
            Cursor allrows = dbs.select_SubsetId(subsetname);
            allrows.moveToFirst();
            Result = allrows.getInt(0);
            allrows.close();


            fc.SetBusiness_SubsetId(Result);
        }
        catch (Exception e) {
            // Toast.makeText(activity, e.toString(), Toast.LENGTH_LONG).show();
        }
        return Result;
    }

    public Integer getCountBusiness(Integer subsetid) {
        Integer Result = 0;

        try {
            DataBaseSqlite dbs = new DataBaseSqlite(context);
            Cursor allrows = dbs.select_business_SubsetId(subsetid);
            allrows.moveToFirst();
            Result = allrows.getInt(0);
            allrows.close();
        }
        catch (Exception e)
        {}

        return Result;
    }


    public String getTime_ZamanSanj() {
        String Result ="";

        try {
            DataBaseSqlite dbs = new DataBaseSqlite(context);
            Cursor allrows = dbs.select_UpdateTime(fc.GetTableNameUpdateTime());
            allrows.moveToFirst();
            Result = allrows.getString(1);
            allrows.close();
        }
        catch (Exception e)
        {}

        return Result;
    }

    public String getDate_ZamanSanj() {
        String Result ="";

        try {
            DataBaseSqlite dbs = new DataBaseSqlite(context);
            Cursor allrows = dbs.select_UpdateTime(fc.GetTableNameUpdateTime());
            allrows.moveToFirst();
            Result = allrows.getString(2);
            allrows.close();
        }
        catch (Exception e)
        {}

        return Result;
    }

    public Integer getMemberId() {
        Integer Result =0;

        try {
            DataBaseSqlite dbs = new DataBaseSqlite(context);
            Cursor allrows = dbs.select_Member();
            allrows.moveToFirst();
            Result = allrows.getInt(0);
            allrows.close();
        }
        catch (Exception e)
        {}

        return Result;
    }

    public Integer getOpinionId() {
        Integer Result =0;

        try {
            DataBaseSqlite dbs = new DataBaseSqlite(context);
            Cursor allrows = dbs.select_opinion();
            allrows.moveToFirst();
            Result = allrows.getInt(0);
            allrows.close();
        }
        catch (Exception e)
        {}

        return Result;
    }

    public Integer getBusinessId() {
        Integer Result =0;

        try {
            DataBaseSqlite dbs = new DataBaseSqlite(context);
            Cursor allrows = dbs.select_business(14);
            allrows.moveToFirst();
            Result = allrows.getInt(0);
            allrows.close();
        }
        catch (Exception e)
        {}

        return Result;
    }



/*
    if (ns.checkInternetConnection() == false) {

        if (count == 0)
        {
            Toast.makeText(getApplicationContext(), "فروشگاه ثبت نشده", Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent i = new Intent(getApplicationContext(), Jobs_List.class);
            startActivity(i);

        }

    }
    else
    {


        if (count == 0 ) {
            //  Toast.makeText(getApplicationContext(),"فروشگاه ثبت نشده",Toast.LENGTH_LONG).show();
            httpbusin = new HTTPGetBusinessJson(Jobs.this);
            httpbusin.SetUrl_business(query.getsubsetID(selected));
            httpbusin.execute();

            Log.i("Count ==0 ", " ok");
        }
        else
        {
            if (time == "")
            {
                Toast.makeText(getApplicationContext(), "Time: " + time, Toast.LENGTH_LONG).show();
                DataBaseSqlite dbs = new DataBaseSqlite(Jobs.this);
                dbs.Add_UpdateTime(fc.GetTableNameUpdateTime(), dt.Hours(), dt.Now());

                httpbusin = new HTTPGetBusinessJson(Jobs.this);
                httpbusin.SetUrl_business(query.getsubsetID(selected));
                httpbusin.execute();
                Log.i("Time == 0", " ok");
            }
            else
            {
                if (Integer.parseInt(dt.Hours()) >= Integer.parseInt(time + 3) || date != dt.Now()) {
                    DataBaseSqlite dbs = new DataBaseSqlite(Jobs.this);
                    dbs.delete_UpdateTime(fc.GetTableNameUpdateTime());
                    dbs.Add_UpdateTime(fc.GetTableNameUpdateTime(), dt.Hours(), dt.Now());

                    httpbusin = new HTTPGetBusinessJson(Jobs.this);
                    httpbusin.SetUrl_business(query.getsubsetID(selected));
                    httpbusin.execute();
                    Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_LONG).show();
                    Log.i("Time > Time+3 ", "ok");
                }
                else
                {
                    Intent i = new Intent(getApplicationContext(), Jobs_List.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "0", Toast.LENGTH_LONG).show();
                    Log.i("NOT IF And Eles ", "Ok");
                }
            }
        }
    }*/
}