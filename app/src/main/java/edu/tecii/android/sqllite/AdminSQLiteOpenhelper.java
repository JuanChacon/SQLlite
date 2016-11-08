package edu.tecii.android.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juan Chacon Holguin on 01/11/2016.
 */

public class AdminSQLiteOpenhelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenhelper(Context context,String name, SQLiteDatabase.CursorFactory factory,int version){//controlador o auxiliar para la base de datos
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {// sentencia para crear  una tabla
        db.execSQL("create table articulos(codigo int primary key,description" +// exec
                "text,precio real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
