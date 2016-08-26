package com.facci.votantescacm;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CarlosAntonio on 26/08/2016.
 */
public class DBHelperCACM extends SQLiteOpenHelper {

    public static final String DB_NOMBRE = "CNE_CACM.db";
    public static final String TABLA_VOTANTES= "VOTANTES_CACM";
    public static final String COL_1 = "ID_CACM";
    public static final String COL_2 = "NOMBRE_CACM";
    public static final String COL_3 = "APELLIDOS_CACM";
    public static final String COL_4 = "RECINTO_ELECTORAL_CACM";
    public static final String COL_5 = "ANO_NACIMIENTO_CACM";


    public DBHelperCACM(Context context) {
        super(context, DB_NOMBRE, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(String.format("create table %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)",TABLA_VOTANTES, COL_2, COL_3, COL_4, COL_5));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLA_VOTANTES));
        onCreate(db);

    }

    public boolean IngresarDatos(String nombre,String apellido,String recinto, String ano ){

        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recinto);
        contentValues.put(COL_5,ano);
        long resultado =db.insert(TABLA_VOTANTES,null,contentValues);

        if(resultado == -1)
            return false;
        else
            return true;

    }
}
