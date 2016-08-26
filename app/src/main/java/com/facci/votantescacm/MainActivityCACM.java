package com.facci.votantescacm;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivityCACM extends AppCompatActivity {

    DBHelperCACM dbSQLITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_cacm);

        dbSQLITE = new DBHelperCACM(this);

    }
}
