package com.ariana.shahre_ma.MyProfile;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ariana.shahre_ma.DateBaseSqlite.DataBaseSqlite;
import com.ariana.shahre_ma.R;

/**
 * Created by ariana2 on 7/23/2015.
 */
public class Old_Password extends Dialog {

    Button btnPass;
    EditText etPass;
    Context context;
    String old_pass="";
    public Old_Password(Context context) {
        super(context);
        this.context=context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.old_password);

        btnPass=(Button)findViewById(R.id.btn_pass);
        etPass=(EditText)findViewById(R.id.et_password);
        btnPass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DataBaseSqlite db=new DataBaseSqlite(context);
                Cursor allrows=db.select_Member();
                allrows.moveToFirst();
                old_pass=allrows.getString(7);

                if(etPass.getText().equals(old_pass))
                {

                }

            }
        });

    }
}
