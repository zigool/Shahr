package com.ariana.shahre_ma.DateBaseSqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ariana on 6/1/2015.
 */
public class RateSqlite extends SQLiteOpenHelper

    {


        //Database Version
        private static final int DATABASE_VERSION = 1;

        // Database Name
        private static final String DATABASE_NAME = "DBshahrma.db";

        // Rate table name
        private static final String TABLE_NAME_RATE = "rate";

        //Rate Table Columns names
        private static final String ID = "Id";
        private static final String RATE = "Rate";
        private static final String BUSINESSID = "BusinessId";
        private static final String MEMBERID = "MemberId";

        // SQL statement to create Rate table
        private static final String CREATE_TABLE_Rate  = "CREATE TABLE  IF  NOT EXISTS " + TABLE_NAME_RATE + " (" +
                "Id INTEGER PRIMARY KEY ," +
                "Rate DOUBLE," +
                "BusinessId INTEGER," +
                "MemberId INTEGER" +
                ");";


        public  RateSqlite(Context context)
        {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public  void onCreate(SQLiteDatabase db)
        {

            // create books table
            db.execSQL(CREATE_TABLE_Rate);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
        {
            // Drop older books table if existed
            db.execSQL("DROP TABLE IF EXISTS books");

            // create fresh books table
            this.onCreate(db);
        }

    public void Add(Integer id,Double rate,Integer businessid,Integer memberid)
    {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(ID,id);
        values.put(RATE,rate);
        values.put(BUSINESSID,businessid);
        values.put(MEMBERID, memberid);



        // 3. insert
        db.insert(TABLE_NAME_RATE, // table
                null, //nullColumnHack
                values); // key/value

        // 4. close
        db.close();
    }

    // Get All Books
    public List<String> getAllBooks() {
        List<String> books = new LinkedList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_NAME_RATE;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
       // Book book = null;
        if (cursor.moveToFirst()) {
            do {
             /*  book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));*/

                // Add book to books
              //  books.add(book);
            } while (cursor.moveToNext());
        }

        Log.d("getAllBooks()", books.toString());

        // return books
        return books;
    }

    // Deleting single book
    public void delete(Integer id) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        // db.delete(TABLE_BOOKS_MEMBER,ID," =",id);

        // 3. close
        db.close();

        // Log.d("deleteBook", book.toString());

    }

    // Updating single book
    public int update(Integer id) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        //values.put("title", book.getTitle()); // get title
        //values.put("author", book.getAuthor()); // get author

        // 3. updating row
        int i=0 ;
     /* = db.update(TABLE_BOOKS_MEMBER, //table
                values, // column/value
                ID+" = ?", // selections
                new String[] { String.valueOf(book.getId()) }); //selection args*/

        // 4. close
        db.close();

        return i;

    }
}
