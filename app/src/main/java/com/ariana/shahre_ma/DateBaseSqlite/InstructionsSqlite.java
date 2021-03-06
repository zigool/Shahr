package com.ariana.shahre_ma.DateBaseSqlite;

/**
 * Created by ariana on 7/11/2015.
 */
public class  InstructionsSqlite {


    // Database table name
    public static final String TABLE_NAME_SUBSET= "subset";
    public static final String TABLE_NAME_SUBSET_PRODUCT= "subset_Product";
    public static final String TABLE_NAME_COLLECTION_PRODUCT = "collection_Product";
    public static final String TABLE_NAME_PROPERTY_PRODUCT = "Property_Product";
    public static final String TABLE_NAME_VALUE_PRODUCT = "Value_Product";
    public static final String TABLE_NAME_SubsetProperty_PRODUCT = "SubsetProperty_Product";
    public static final String TABLE_NAME_COLLECTION = "collection";
    public static final String TABLE_NAME_MEMBER = "member";
    public static final String TABLE_NAME_OPINION = "opinion";
    public static final String TABLE_NAME_BUSINESS_TOPS = "business_Tops";
    public static final String TABLE_NAME_BUSINESS_DISCOUNT = "business_DisCount";
    public static final String TABLE_NAME_BUSINESS_IMAGE = "BusinessImage";
    public static final String TABLE_NAME_BUSINESS = "business";
    public static final String TABLE_NAME_City   = "city";
    public static final String TABLE_NAME_Bookmark = "bookmark";
    public static final String TABLE_NAME_Area = "area";
    public static final String TABLE_NAME_UpdateTime = "UpdateTime";
    public static final String TABLE_NAME_Search = "Search";
    public static final String TABLE_NAME_Like = "Like";
    public static final String TABLE_NAME_Interest = "Interest";
    public static final String TABLE_NAME_NOTIFICATION="Notification";
    public static final String TABLE_NAME_SHOWNOTIFICATION="ShowNotification";
    public static final String TABLE_NAME_FieldActivity="FieldActivity";
    public static final String TABLE_NAME_DisCount="DisCount";
    public static final String TABLE_NAME_DisCountMember="DisCountMember";
    public static final String TABLE_NAME_LikeDisCount="LikeDisCount";
    public static final String TABLE_NAME_Advertisment="Advertisment";
    public static final String TABLE_NAME_ProductMember="ProductMember";
    




    //Notification Table Columns names
    public static final String ID_Notification="Id";
    public static final String OpinionType_Notification="OpinionType";
    public static final String ErJa_Notification="Erja";
    public static final String ExecutionTime_Notification="ExecutionTime";
    public static final String Description_Notification="Description";
    public static final String ExpirationDate_Notification="ExpirationDate";
    public static final String City_Notification="City";
    public static final String CityId_Notification="CityId";
    public static final String Subset_Notification="Subset";
    public static final String SubsetId_Notification="SubsetId";
    public static final String TITEL_Notification="Title";


    //ProductMember Table Columns names
    public static final String ID_ProductMember="Id";
    public static final String MEMBERID__ProductMember="MemberId";
    public static final String NAME_ProductMember="Name";
    public static final String PROPERTY_ProductMember="Property";
    public static final String PRICE_ProductMember="Price";
    public static final String LATITUDE_ProductMember="Latutude";
    public static final String ADAPTIVE_ProductMember="Adaptive";
    public static final String DESCRIPTION_ProductMember="Description";
    public static final String IMAGE_ProductMember="Image";
    public static final String PHONE_ProductMember="Phone";
    public static final String MOBILE_ProductMembe="Adaptive";
    public static final String ADDRESS_ProductMembe="Description";
    public static final String EMAIL_ProductMembe="Image";
    public static final String SUBSETID_ProductMembe="Phone";
    public static final String AREAID_ProductMembe="Phone";
    public static final String CITYID_ProductMembe="Phone";


    //Value Table Columns names
    public static final String ID_VALUE_PRODUCT="Id";
    public static final String NAME_VALUE_PRODUCT="Name";
    public static final String IDPROPERTY_VALUE_PRODUCT="IdProperty";

    // SubsetProperty_Product  Table Columns names
    public static final String ID_SubsetProperty_PRODUCT="Id";
    public static final String PRODUCTSUBSETID_SubsetProperty_PRODUCT="ProductSubsetId";
    public static final String PROPERTYID_SubsetProperty_PRODUCT="PropertyId";

    //Propertiy Table Columns names
    public static final String ID_PROPERTY_PRODUCT="Id";
    public static final String NAME_PROPERTY_PRODUCT="Name";
    public static final String Type_PROPERTY_PRODUCT="Type";



    //FieldActivity Table Columns names
    public static final String ID_FIELDACTIVITY="Id";
    public static final String ACTIVITY_FIELDACTIVITY="Activity";


    //UpdateTime Table Columns names
    public static final String ID_Update = "Id";
    public static final String DATE_Update = "Date";

    //Area Table Columns names
    public static final String ID_area = "Id";
    public static final String NAME_area = "AreaName";
    public static final String CITYID_area = "CityId";


    //Bookmark Table Columns names
    public static final String ID_bookmark = "Id";
    public static final String BUSINESSID_bookmark = "BusinessId";
    public static final String MEMBERID_bookmark = "MemberId";

    //Advertisment Table Columns names
    public static final String ID_Advertisment= "Id";
    public static final String IMAGE_Advertisment = "Image";
    public static final String LINK_Advertisment = "Link";
    public static final String TYPE_Advertisment = "Type";

    //Business Image Table Columns names
    public static final String ID_BusinessImage= "Id";
    public static final String BUSINESSID_BusinessImage = "BusinessId";
    public static final String SRC_BusinessImage = "Src";



    //ShowNotification Table Columns names
    public static final String ID_SHOWNOTIFICATION = "Id";
    public static final String BUSINESSID_SHOWNOTIFICATION = "BusinessId";
    public static final String SHOW_SHOWNOTIFICATION = "Show";



    //City Table Columns names
    public static final String ID_city = "Id";
    public static final String NAME_city = "Name";
    public static final String PROVINCEID_city = "ProvinceId";

    //Like Table Columns names
    public static final String ID_Like = "Id";
    public static final String LIKE_Like = "Like";
    public static final String MEMBERID_Like = "MemberId";
    public static final String OPINION_Like = "OpinionId";

    //Interest Table Columns names

    public static final String SUBSETID_Interest = "SubsetId";
    public static final String MEMBERID_Interest = "MemberId";


    //LikeDisCount Table Columns names
    public static final String ID_LIKEDISCOUNT = "Id";
    public static final String LIKECOUNT_LIKEDISCOUNT = "Like";
    public static final String MEMBERID_LIKEDISCOUNT = "MemberId";
    public static final String DISCOUNTID_LIKEDISCOUNT = "DisCountId";
    public static final String BUSINESSID_LIKEDISCOUNT = "BusinessId";

    //DisCoutn Table Columns names
    public static final String ID_DISCOUNT = "Id";
    public static final String TEXT_DISCOUNT = "Text";
    public static final String IMAGE_DISCOUNT = "Image";
    public static final String STARTDATE_DISCOUNT = "StartDate";
    public static final String EXPIRATIONDATE_DISCOUNT = "ExpirationDate";
    public static final String DESCRIPTION_DISCOUNT= "Description";
    public static final String PERCENT_DISCOUNT = "Percent";
    public static final String BUSINESSID_DISCOUNT = "BusinessId";
    public static final String LIKE_DISCOUNT = "Like";
    public static final String DISLIKE_DISCOUNT = "DisLike";

    //DisCoutnMember Table Columns names
    public static final String ID_DISCOUNTMEMBER = "Id";
    public static final String TEXT_DISCOUNTMEMBER = "Text";
    public static final String IMAGE_DISCOUNTMEMBER = "Image";
    public static final String STARTDATE_DISCOUNTMEMBER = "StartDate";
    public static final String EXPIRATIONDATE_DISCOUNTMEMBER = "ExpirationDate";
    public static final String DESCRIPTION_DISCOUNTMEMBER= "Description";
    public static final String PERCENT_DISCOUNTMEMBER = "Percent";
    public static final String BUSINESSID_DISCOUNTMEMBER= "BusinessId";

    //Opinion Table Columns names
    public static final String ID_opinion = "Id";
    public static final String DESCRIPTION_opinion = "Description";
    public static final String DATE_opinion = "Date";
    public static final String MEMBERNAME_opinion = "MemberName";
    public static final String ERJA_opinion = "Erja";
    public static final String COUNTLIKE_opinion = "CountLike";
    public static final String COUNTDISLIKE_opinion = "CountDisLike";


    //subset Table Columns names
    public static final String ID_subset = "Id";
    public static final String NAME_subset = "SubsetName";
    public static final String COLLECTIONID_subset = "CollectionId";

    //subset_Product Table Columns names
    public static final String ID_subset_Product = "Id";
    public static final String NAME_subset_Product = "SubsetName";
    public static final String COLLECTIONID_subset_Product = "CollectionId";


    //collection Table Columns names
    public static final String ID_colection = "Id";
    public static final String NAME_collection = "CollectionName";


    //collection_product Table Columns names
    public static final String ID_colection_Product = "Id";
    public static final String NAME_collection_Product = "CollectionName";


    //Member Table Columns names
    public static final String ID_member = "id";
    public static final String NAME_member = "Name";
    public static final String EMAIL_member = "Email";
    public static final String MOBILE_member = "Mobile";
    public static final String AGE_member = "Age";
    public static final String SEX_member = "Sex";
    public static final String USERNAME_member = "UserName";
    public static final String PASSWORD_member = "Password";
    public static final String CITYID_member = "CityId";


    //business Table Columns names
    public static final String ID_business = "Id";
    public static final String MARKET_business = "Market";
    public static final String CODE_business = "Code";
    public static final String PHONE_business = "Phone";
    public static final String MOBILE_business = "Mobile";
    public static final String FAX_business = "Fax";
    public static final String EMAIL_business = "Email";
    public static final String BUSINESSOWNER_business = "BusinessOwner";
    public static final String ADDRESS_business = "Address";
    public static final String DESCRIPTION_business = "Description";
    public static final String STARTDATE_business = "Startdate";
    public static final String EXPIRATIONDATE_business = "ExpirationDate";
    public static final String INACTIVE_business = "Inactive";
    public static final String SUBSET_business = "Subset";
    public static final String SUBSETID_business = "SubsetId";
    public static final String LONGITUDE_business = "Longitude";
    public static final String LATITUDE_business = "Latitude";
    public static final String SRC_business = "Src";
    public static final String AREAID_business = "AreaId";
    public static final String AREA_business = "Area";
    public static final String USER_business = "User";
    public static final String CITYID_business = "CityId";
    public static final String USERID_business = "UserId";
    public static final String FIELD1_business = "Field1";
    public static final String FIELD2_business = "Field2";
    public static final String FIELD3_business = "Field3";
    public static final String FIELD4_business = "Field4";
    public static final String FIELD5_business = "Field5";
    public static final String FIELD6_business = "Field6";
    public static final String FIELD7_business = "Field7";
    public static final String RATECOUNT_business = "RateCount";
    public static final String RATEVALUE_business = "RateValue";


    // SQL statement to create area table
    public static final String CREATE_TABLE_Area = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_Area + " (" +
            "Id INTEGER PRIMARY KEY ," +
            "AreaName TEXT," +
            "CityId INTEGER" +
            ");";


    // SQL statement to create Value_Product table
    public static final String CREATE_TABLE_Value_Product = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_VALUE_PRODUCT + " (" +
            "Id INTEGER PRIMARY KEY ," +
            "Name TEXT," +
            "IdProperty INTEGER" +
            ");";

    // SQL statement to create SubsetProperty_Product table
    public static final String CREATE_TABLE_SubsetProperty_Product = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_SubsetProperty_PRODUCT + " (" +
            "Id INTEGER PRIMARY KEY ," +
            "ProductSubsetId INTEGER," +
            "PropertyId INTEGER" +
            ");";


    // SQL statement to create Property_Product table
    public static final String CREATE_TABLE_Property_Product = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_PROPERTY_PRODUCT + " (" +
            "Id INTEGER PRIMARY KEY ," +
            "Name TEXT," +
            "Type INTEGER" +
            ");";


    // SQL statement to create area table
    public static final String CREATE_TABLE_BusinessImage = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_BUSINESS_IMAGE + " (" +
            "Id INTEGER PRIMARY KEY ," +
            "BusinessId INTEGER," +
            "Src TEXT" +
            ");";


    //SQL statement to create notification table
    public static final String CREATE_TABLE_Notification = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_NOTIFICATION + " (" +
            "Id INTEGER PRIMARY KEY ," +
            "OpinionType INTEGER," +
            "ErJa INTEGER," +
            "ExecutionTime BOOLEAN ," +
            "Description TEXT ," +
            "ExpirationDate TEXT ," +
            "City TEXT," +
            "CityId INTEGER," +
            "Subset TEXT ," +
            "SubsetId INTEGER, " +
            "Title TEXT " +
            ");";

    //SQL statement to create DisCoutn table
    public static final String CREATE_TABLE_DisCount = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_DisCount + " (" +
            "Id INTEGER PRIMARY KEY ," +
            "Text TEXT," +
            "Image TEXT," +
            "StartDate TEXT ," +
            "ExpirationDate TEXT ," +
            "Description TEXT ," +
            "Percent TEXT," +
            "BusinessId INTEGER," +
            "Like INTEGER," +
            "DisLike INTEGER" +
            "Src TEXT" +
            ");";

    //SQL statement to create DisCoutnMember table
    public static final String CREATE_TABLE_DisCountMember = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_DisCountMember + " (" +
            "Id INTEGER PRIMARY KEY  ," +
            "Text TEXT," +
            "Image TEXT," +
            "StartDate TEXT ," +
            "ExpirationDate TEXT ," +
            "Description TEXT ," +
            "Percent TEXT," +
            "BusinessId INTEGER" +
            "Src TEXT" +
            ");";

    //SQL statement to create LikeDisCoutn table
    public static final String CREATE_TABLE_LikeDisCount = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_LikeDisCount + " (" +
            "Id INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "Like BOOLEAN, " +
            "MemberId INTEGER," +
            "DisCountId INTEGER," +
            "BusinessId INTEGER" +
            ");";
    // SQL statement to create fieldactivity table
    public static final String CREATE_TABLE_FieldActivity = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_FieldActivity + " (" +
            " Id INTEGER PRIMARY KEY ," +
            " Activity TEXT" +
            ");";

    // SQL statement to create city table
    public static final String CREATE_TABLE_City  = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_City + " (" +
            "Id INTEGER PRIMARY KEY ," +
            "Name TEXT," +
            "ProvinceId INTEGER" +
            ");";

    // SQL statement to create Advertisment table
    public static final String CREATE_TABLE_Advertisment  = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_Advertisment + " (" +
            "Id INTEGER PRIMARY KEY ," +
            "Image TEXT," +
            "Link TEXT," +
            "Type INTEGER" +
            ");";

    // SQL statement to create ZamanSanj table
    public static final String CREATE_TABLE_Update = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_UpdateTime + " (" +
            "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Date TEXT" +
            ");";



    // SQL statement to create ProductMember table
    public static final String CREATE_TABLE_ProductMember  = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_ProductMember + " (" +
            " Id INTEGER PRIMARY KEY ," +
            " MemberId INTEGER ," +
            " Name TEXT ," +
            " Property TEXT ," +
            " Price TEXT ," +
            " Latitude TEXT ," +
            " Longtiude TEXT ," +
            " Adaptive Boolean ," +
            " Description TEXT ," +
            " Image TEXT ," +
            " Phone TEXT ," +
            " Mobile TEXT ," +
            " Address TEXT ," +
            " Email TEXT ," +
            " SubsetId INTEGER ," +
            " AreaId INTEGER," +
            " CityId INTEGER" +
            ");";


    // SQL statement to create Search table
    public static final String CREATE_TABLE_Search  = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_Search+ " (" +
            " Id INTEGER PRIMARY KEY ," +
            " Market TEXT ," +
            " Code TEXT ," +
            " Phone TEXT ," +
            " Mobile TEXT ," +
            " Fax TEXT ," +
            " Email TEXT ," +
            " BusinessOwner TEXT ," +
            " Address TEXT ," +
            " Description TEXT ," +
            " Startdate TEXT ," +
            " ExpirationDate TEXT ," +
            " Inactive TEXT ," +
            " Subset TEXT ," +
            " SubsetId INTEGER ," +
            " Latitude REAL," +
            " Longitude REAL," +
            " AreaId INTEGER ," +
            " Area TEXT ," +
            " User TEXT ," +
            " CityId INTEGER ," +
            " UserId INTEGER," +
            " Field1 INTEGER," +
            " Field2 INTEGER," +
            " Field3 INTEGER," +
            " Field4 INTEGER," +
            " Field5 INTEGER," +
            " Field6 INTEGER," +
            " Field7 INTEGER, " +
            " RateCount INTEGER ," +
            " RateValue DOUBLE ," +
            " Src TEXT " +
            ");";


    // SQL statement to create business_disCount table
    public static final String CREATE_TABLE_Business_DisCount  = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_BUSINESS_DISCOUNT + " (" +
            " Id INTEGER PRIMARY KEY ," +
            " Market TEXT ," +
            " Code TEXT ," +
            " Phone TEXT ," +
            " Mobile TEXT ," +
            " Fax TEXT ," +
            " Email TEXT ," +
            " BusinessOwner TEXT ," +
            " Address TEXT ," +
            " Description TEXT ," +
            " Startdate TEXT ," +
            " ExpirationDate TEXT ," +
            " Inactive TEXT ," +
            " Subset TEXT ," +
            " SubsetId INTEGER ," +
            " Latitude REAL," +
            " Longitude REAL," +
            " AreaId INTEGER ," +
            " Area TEXT ," +
            " User TEXT ," +
            " CityId INTEGER ," +
            " UserId INTEGER," +
            " Field1 INTEGER," +
            " Field2 INTEGER," +
            " Field3 INTEGER," +
            " Field4 INTEGER," +
            " Field5 INTEGER," +
            " Field6 INTEGER," +
            " Field7 INTEGER, " +
            " RateCount INTEGER ," +
            " RateValue DOUBLE ," +
            " Src TEXT " +
            ");";

    // SQL statement to create business_tops table
    public static final String CREATE_TABLE_Business_Tops  = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_BUSINESS_TOPS + " (" +
            " Id INTEGER PRIMARY KEY ," +
            " Market TEXT ," +
            " Code TEXT ," +
            " Phone TEXT ," +
            " Mobile TEXT ," +
            " Fax TEXT ," +
            " Email TEXT ," +
            " BusinessOwner TEXT ," +
            " Address TEXT ," +
            " Description TEXT ," +
            " Startdate TEXT ," +
            " ExpirationDate TEXT ," +
            " Inactive TEXT ," +
            " Subset TEXT ," +
            " SubsetId INTEGER ," +
            " Latitude REAL," +
            " Longitude REAL," +
            " AreaId INTEGER ," +
            " Area TEXT ," +
            " User TEXT ," +
            " CityId INTEGER ," +
            " UserId INTEGER," +
            " Field1 INTEGER," +
            " Field2 INTEGER," +
            " Field3 INTEGER," +
            " Field4 INTEGER," +
            " Field5 INTEGER," +
            " Field6 INTEGER," +
            " Field7 INTEGER, " +
            " RateCount INTEGER ," +
            " RateValue DOUBLE ," +
            " Src TEXT " +
            ");";

    // SQL statement to create business table
    public static final String CREATE_TABLE_Business  = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_BUSINESS + " (" +
            " Id INTEGER PRIMARY KEY ," +
            " Market TEXT ," +
            " Code TEXT ," +
            " Phone TEXT ," +
            " Mobile TEXT ," +
            " Fax TEXT ," +
            " Email TEXT ," +
            " BusinessOwner TEXT ," +
            " Address TEXT ," +
            " Description TEXT ," +
            " Startdate TEXT ," +
            " ExpirationDate TEXT ," +
            " Inactive TEXT ," +
            " Subset TEXT ," +
            " SubsetId INTEGER ," +
            " Latitude REAL," +
            " Longitude REAL," +
            " AreaId INTEGER ," +
            " Area TEXT ," +
            " User TEXT ," +
            " CityId INTEGER ," +
            " UserId INTEGER," +
            " Field1 INTEGER," +
            " Field2 INTEGER," +
            " Field3 INTEGER," +
            " Field4 INTEGER," +
            " Field5 INTEGER," +
            " Field6 INTEGER," +
            " Field7 INTEGER, " +
            " RateCount INTEGER ," +
            " RateValue DOUBLE ," +
            " Src TEXT " +
            ");";

    // SQL statement to create Like table
    public static final String CREATE_TABLE_Like  = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_Like + " (" +
            "Id INTEGER PRIMARY KEY ," +
            "Like BOOLEAN," +
            "MemberId TEXT," +
            "OpinionID INTEGER" +

            ");";

    // SQL statement to create Interest table
    public static final String CREATE_TABLE_Interest = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_Interest + " (" +
            "Id INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "SubsetId INTEGER," +
            "MemberId INTEGER" +
            ");";

    // SQL statement to create Opinion table
    public static final String CREATE_TABLE_Opinion  = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_OPINION + " (" +
            "Id INTEGER PRIMARY KEY ," +
            "Description TEXT," +
            "Date TEXT," +
            "Erja INTEGER," +
            "CountLike INTEGER," +
            "CountDisLike INTEGER," +
            "MemberName TEXT" +
            ");";


    // SQL statement to create Member table
    public static final String CREATE_TABLE_Member  = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_MEMBER + " (" +
            " Id INTEGER PRIMARY KEY," +
            " Name TEXT ," +
            " Email TEXT ," +
            " Mobile TEXT ," +
            " Age INTEGER ," +
            " Sex BOOLEAN ," +
            " UserName TEXT ," +
            " Password TEXT ," +
            " CityId INTEGER " +
            ");";


    // SQL statement to create Collection table
    public static final String CREATE_TABLE_Collection  = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_COLLECTION + " (" +
            " Id INTEGER PRIMARY KEY ," +
            " CollectionName TEXT" +
            ");";

    // SQL statement to create Collection table
    public static final String CREATE_TABLE_Collection_Product  = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_COLLECTION_PRODUCT + " (" +
            " Id INTEGER PRIMARY KEY ," +
            " CollectionName TEXT" +
            ");";


    // SQL statement to create Subset table
    public static final String CREATE_TABLE_Subset  = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_SUBSET + " (" +
            "Id INTEGER PRIMARY KEY ," +
            "SubsetName TEXT," +
            "CollectionId INTEGER" +
            ");";

    // SQL statement to create Subset_Product table
    public static final String CREATE_TABLE_Subset_Prodcut  = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_SUBSET_PRODUCT + " (" +
            "Id INTEGER PRIMARY KEY ," +
            "SubsetName TEXT," +
            "CollectionId INTEGER" +
            ");";



    // SQL statement to create bookmark table
    public static final String CREATE_TABLE_Bookmark = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_Bookmark + " (" +
            "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "BusinessId INTEGER," +
            "MemberId INTEGER" +
            ");";

    // SQL statement to create ShowNotification table
    public static final String CREATE_TABLE_ShowNotification = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_SHOWNOTIFICATION + " (" +
            "Id INTEGER PRIMARY KEY, " +
            "BusinessId INTEGER , " +
            "Show BOOLEAN " +
            ");";
}
