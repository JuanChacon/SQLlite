package edu.tecii.android.sqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private EditText e1,e2,e3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1=(EditText)findViewById(R.id.et1);
        e2=(EditText)findViewById(R.id.et2);
        e3=(EditText)findViewById(R.id.et3);
    }

    public void alta(View v){
        AdminSQLiteOpenhelper admin = new  AdminSQLiteOpenhelper(this,"administracion",null,1);
        SQLiteDatabase bd= admin.getWritableDatabase();
        String cod=e1.getText().toString();
        String des=e2.getText().toString();
        String pre=e3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo",cod);
        registro.put("descripcion",des);
        registro.put("codigo",pre);

        bd.insert("articulos",null,registro);
        bd.close();
        e1.setText("");
        e2.setText("");
        e3.setText("");
        Toast.makeText(this, "Se cargaron los datos del artículo",
                Toast.LENGTH_SHORT).show();
    }

    public void consultaporcodigo(View v){
        AdminSQLiteOpenhelper admin = new AdminSQLiteOpenhelper(this,"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod=e1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select description,precio from articulos where código=" + cod,null
        );
        if (fila.moveToFirst()){
            e2.setText(fila.getString(0));
            e3.setText(fila.getString(1));
        }else{
            Toast.makeText(this,"No existe un articulo con dicho código",Toast.LENGTH_SHORT).show();
            bd.close();
        }
    }
    public void consultapordescripcion(View v) {
        AdminSQLiteOpenhelper admin = new AdminSQLiteOpenhelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String descri = e2.getText().toString();
        Cursor fila = bd.rawQuery(
                "select codigo,precio from articulos where descripcion='" + descri +"'", null);
        if (fila.moveToFirst()) {
            e1.setText(fila.getString(0));
            e3.setText(fila.getString(1));
        } else
            Toast.makeText(this, "No existe un artículo con dicha descripción",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void bajaporcodigo(View v) {
        AdminSQLiteOpenhelper admin = new AdminSQLiteOpenhelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod= e1.getText().toString();
        int cant = bd.delete("articulos", "codigo=" + cod, null);
        bd.close();
        e1.setText("");
        e2.setText("");
        e3.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borró el artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe un artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
    }
    public void modificacion(View v) {
        AdminSQLiteOpenhelper admin = new AdminSQLiteOpenhelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = e1.getText().toString();
        String descri = e2.getText().toString();
        String pre = e3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("descripcion", descri);
        registro.put("precio", pre);
        int cant = bd.update("articulos", registro, "codigo=" + cod, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe un artículo con el código ingresado",
                    Toast.LENGTH_SHORT).show();
    }
}
