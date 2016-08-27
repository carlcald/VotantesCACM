package com.facci.votantescacm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityCACM extends AppCompatActivity {

    DBHelperCACM dbSQLITE;

    EditText txtID, txtNombre,txtApellido,txtRecinto,txtAno;

    Button btnInsertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_cacm);

        dbSQLITE = new DBHelperCACM(this);

    }

    public void insertarClick (View v){
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtID = (EditText) findViewById(R.id.txtID);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtRecinto = (EditText) findViewById(R.id.txtRecintoElectoral);
        txtAno = (EditText) findViewById(R.id.txtAnoNacimiento);

        boolean estaInsertado = dbSQLITE.IngresarDatos(txtNombre.getText().toString(), txtApellido.getText().toString(), txtRecinto.getText().toString(), txtAno.getText().toString());

        if (estaInsertado)
            Toast.makeText(MainActivityCACM.this,"Datos Ingresados correctamente",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivityCACM.this,"Error de Ingreso",Toast.LENGTH_SHORT).show();
    }

    public void verTodosClick(View v)
    {
        Cursor re = dbSQLITE.MostarTodos();
        if (re.getCount()==0){
            return;
        }
        StringBuffer buffer = new StringBuffer();

        while(re.moveToNext()){
            buffer.append("Id : " + re.getString(0) + "\n");
            buffer.append("Nombre : " + re.getString(1) + "\n");
            buffer.append("Apellido : " + re.getString(2) + "\n");
            buffer.append("Recinto : " + re.getString(3) + "\n");
            buffer.append("Año nacimiento : " + re.getInt(4) + "\n\n");
        }
    }
}
