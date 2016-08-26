package com.facci.votantescacm;

import android.database.sqlite.SQLiteDatabase;
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

        boolean estado = dbSQLITE.IngresarDatos(txtNombre.getText().toString(), txtApellido.getText().toString(), txtRecinto.getText().toString(), txtAno.getText().toString());

        if (estaInsertado == true)
            Toast.makeText(MainActivityCACM.this,"Datos Ingresados correctamente",Toast.LENGTH_SHORT).show();
    }
}
