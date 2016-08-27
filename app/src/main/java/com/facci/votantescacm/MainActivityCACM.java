package com.facci.votantescacm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityCACM extends AppCompatActivity {

    DBHelperCACM dbSQLITE;

    EditText txtID, txtNombre,txtApellido,txtRecinto,txtAno;

    Button btnInsertar, btnModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_cacm);

        dbSQLITE = new DBHelperCACM(this);

    }

    public void insertarClick (View v){
        txtID = (EditText) findViewById(R.id.txtID);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
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
            mostrarMensaje("Error", "No se encontraron datos ingresados");
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

        mostrarMensaje("Registros", buffer.toString());
    }

    public void mostrarMensaje(String titulo, String Mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();
    }

    public void modificarRegistroClick(View v){

        txtID = (EditText) findViewById(R.id.txtID);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtRecinto = (EditText) findViewById(R.id.txtRecintoElectoral);
        txtAno = (EditText) findViewById(R.id.txtAnoNacimiento);

        boolean estaActualizado = dbSQLITE.modificarRegistro(txtID.getText().toString(),txtNombre.getText().toString(), txtApellido.getText().toString(), txtRecinto.getText().toString(), txtAno.getText().toString());

        if (estaActualizado == true){
            Toast.makeText(MainActivityCACM.this,"Registro Actualizado",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivityCACM.this,"No se pudo actualizar el registro",Toast.LENGTH_SHORT).show();
        }
    }
    public void eliminarRegistroClick(View v) {

        txtID = (EditText) findViewById(R.id.txtID);

        Integer registrosEliminados = dbSQLITE.eliminarRegistro(txtID.getText().toString());

        if(registrosEliminados > 0){
            Toast.makeText(MainActivityCACM.this,"Registro(s) Eliminados(s)",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivityCACM.this,"Error: Registro no eliminado",Toast.LENGTH_SHORT).show();
        }
    }
}
