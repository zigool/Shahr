package com.ariana.shahre_ma.Fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ariana on 7/23/2015.
 */
public class FieldDataBusiness
{
    private static List<Integer> Id=new ArrayList<>();
    private static List<Double> Longtiude=new ArrayList<>();
    private static List<Double> Latitude=new ArrayList<>();
    private static List<Double> Rate=new ArrayList<>();
    private static List<String> Phone=new ArrayList<>();
    private static List<String> Mobile=new ArrayList<String>();
    private static List<String> Address=new ArrayList<>();
    private static List<String> MarketName=new ArrayList<String>();


    public void SetIdBusiness(List<Integer> id)
    {
        Id=id;
    }

    public List<Integer> GetIdBusiness()
    {
        return Id;
    }

    /**
     * Get rate Business
     * @param rate
     */
    public void SetRateBusiness(List<Double>  rate)
    {
        Rate=rate;
    }

    public List<Double>  GetRateBusiness()
    {
        return Rate;
    }

    /**
     *
     * @param longtiude
     */
    public void SetLongtiudeBusiness(List<Double>  longtiude)
    {
        Longtiude=longtiude;
    }

    public List<Double>  GetLongtiudeBusiness()
    {
        return Longtiude;
    }

    /**
     *
     * @param latitude
     */
    public void SetLatitudeBusiness(List<Double>  latitude)
    {
        Latitude=latitude;
    }

    public List<Double>  GetLatitudeBusiness()
    {
        return Latitude;
    }


    /**
     * Get phone business
     * @param phone
     */
    public void SetPhoneBusiness(List<String> phone)
    {
        Phone=phone;
    }

    public List<String> GetPhoneBusiness()
    {
        return Phone;
    }

    /**
     *
     * @param mobile
     */
    public void SetMobileBusiness(List<String> mobile)
    {
        Mobile=mobile;
    }

    public List<String> GetMobileBusiness()
    {
        return Mobile;
    }

    /**
     * Get address business
     * @param address
     */
    public void SetAddressBusiness(List<String> address)
    {
        Address=address;
    }

    public List<String> GetAddressBusiness()
    {
        return Address;
    }

    /**
     * Get market name business
     * @param marketName
     */
    public void SetMarketBusiness(List<String> marketName)
    {
        MarketName=marketName;
    }

    public List<String> GetMarketBusiness()
    {
        return MarketName;
    }

    public void ClearAll()
    {
         Id.clear();
        Longtiude.clear();
        Latitude.clear();
        Rate.clear();
        Phone.clear();
        Mobile.clear();
        Address.clear();
        MarketName.clear();

    }
}
