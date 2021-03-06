package com.ariana.shahre_ma.MyProfile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import com.ariana.shahre_ma.Date.CalendarTool;
import com.ariana.shahre_ma.DateBaseSqlite.Query;
import com.ariana.shahre_ma.DateBaseSqlite.SelectDataBaseSqlite;
import com.ariana.shahre_ma.Fields.FieldClass;
import com.ariana.shahre_ma.R;
import com.ariana.shahre_ma.WebServiceGet.SqliteTOjson;
import com.ariana.shahre_ma.WebServicePost.HTTPPostMemberEditJson;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;
import java.util.List;

public class Edit_User extends ActionBarActivity {

    CalendarTool ct=new CalendarTool();
    FieldClass fc = new FieldClass();
    HTTPPostMemberEditJson sendPost;
    Query query=new Query(this);
    SqliteTOjson json = new SqliteTOjson(this);

    //Variable
    Boolean _sex = false;

    String Aname, Aemail, Acity, Aphone, Ausername, Apass;
    Boolean Asex=false;
    Integer Aage;
    String _json;

    EditText name;
    EditText email;
    AutoCompleteTextView city;
    EditText phone;
    EditText age;
    Spinner sex;
    EditText pass;
    EditText user;
    String change_pass="";
    String change_user="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__user);


            Intialize();

        SelectDataBaseSqlite sdb=new SelectDataBaseSqlite(this);
        Cursor allrows=sdb.select_Member();
        allrows.moveToFirst();

        name.setText(allrows.getString(1));
        email.setText(allrows.getString(2));
        city.setText(query.getCityName(allrows.getInt(8)));
        getNameCity();
        phone.setText(allrows.getString(3));
        age.setText(String.valueOf(allrows.getInt(4)));
        pass.setText(allrows.getString(7));
        change_pass=allrows.getString(7);
        user.setText(allrows.getString(6));
        change_user=allrows.getString(6);
        SpinnerSetUp();


        sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                switch (pos) {
                    case 0:

                        Asex = false;
                        break;
                    case 1:

                        Asex = true;
                        break;

                }
            }


            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

    }


    public void Intialize() {
        name = (EditText) findViewById(R.id.edit_name);
        email = (EditText) findViewById(R.id.edit_email);
        city = (AutoCompleteTextView) findViewById(R.id.edit_city);
        phone = (EditText) findViewById(R.id.edit_phone);
        age = (EditText) findViewById(R.id.edit_age);
        sex = (Spinner) findViewById(R.id.edit_sex);
        pass = (EditText) findViewById(R.id.edit_pass);
        user=(EditText)findViewById(R.id.edit_username);
    }

    public void edit_member(View v){

          try
          {
              Aname = name.getText().toString();
              Aemail = email.getText().toString();
              Acity = city.getText().toString();
              Aphone = phone.getText().toString();
              Aage = Integer.parseInt(age.getText().toString());
              Ausername = user.getText().toString();
              Apass = pass.getText().toString();

              Integer cityid = query.getCityId(Acity);

              if (cityid <= 0) {
                  city.requestFocus();
                  city.setError("شهر خود را انتخاب کنید");

              } else if(Aname.length()<3) {
                  name.setError("بیش از 3 حرف وارد کنید");
                  name.requestFocus();

              } else if(Aemail.length()>0 && !isValidEmail(Aemail)){

                  email.setError("ایمیل نا معتبر است.");
              } else if (Ausername.length()<5) {
                  user.setError("نام کاربری باید حد اقل 5 حرف باشد");
                  user.requestFocus();
              } else if (Apass.length()<6) {
                  pass.setError("رمز عبور باید حداقل 6 حرف باشد");
                  pass.requestFocus();
              }
              else
              {

                      _json = (json.getSqliteTOjson(Aname.trim(), Aemail.trim(), Aphone.trim(), Aage, Asex, Ausername.trim(), Apass.trim(), query.getCityId(Acity)));
                      fc.SetMember_Name(Aname);
                      fc.SetMember_Email(Aemail);
                      fc.SetMember_Mobile(Aphone);
                      fc.SetMember_Age(Aage);
                      fc.SetMember_Sex(Asex);
                      fc.SetMember_UserName(Ausername);
                      fc.SetMember_Password(Apass);
                      fc.SetMember_CityId(query.getCityId(Acity));

                      sendPost = new HTTPPostMemberEditJson(this);
                      sendPost.SetMember_Json(_json);
                      sendPost.execute();

              }
          }
          catch (Exception e)
          {
              AlertDialog alertDialog = new AlertDialog.Builder(Edit_User.this).create();
              alertDialog.setTitle("هشدار ");
              alertDialog.setMessage("ویرایش نشد،دوباره امتحان کنید");
              alertDialog.setButton("تایید", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int which) {


                  }
              });

              alertDialog.show();
          }
    }


    public List<String> getId() {

        SelectDataBaseSqlite sdb=new SelectDataBaseSqlite(this);

        List<String> studentList = new ArrayList<>();
        Cursor allrows  = sdb.select_AllCity();
        if (allrows.moveToFirst()) {
            do {

                studentList.add(allrows.getString(1));

            } while (allrows.moveToNext());
        }

        return studentList;
    }

    public void getNameCity()
    {
        try
        {
          //  AutoCompleteTextView et2=(AutoCompleteTextView) findViewById(R.id.autoCompletecCity);
            ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getId());
            city.setAdapter(adapter);


            city.setThreshold(1);
        }
        catch (Exception e) {
        }
    }


    void SpinnerSetUp(){

        sex.setPrompt("جنسیت:");
        List<String> list = new ArrayList<String>();
        list.add("زن");
        list.add("مرد");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex.setAdapter(dataAdapter);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
