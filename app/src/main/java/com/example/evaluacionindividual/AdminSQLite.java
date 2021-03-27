package com.example.evaluacionindividual;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.ArrayList;


//Esta Clase como su nombre lo dice, permite administrar/gestionar la data.

public class AdminSQLite extends SQLiteOpenHelper {

    public AdminSQLite(Context context) {
        super(context, "db_eval_individual", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table productos (" +
                "nombre varchar(100)" +
                ",precio varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //Abrir base de datos.
    public SQLiteDatabase abrirBaseDeDatos() {
        return getReadableDatabase();
    }

    //CRUD// Solo hacer metodos Insertar - Seleccionar

    public void insertarDatos(String nombre, String precio) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = String.format(
                "insert into productos (nombre, precio) " +
                        "values ('%s','%s');", nombre, precio);
        db.execSQL(sql);
        db.close();
    }

    //Para guardar todos los productos en un arraylist
    public ArrayList<Producto> seleccionDatos() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c;
        ArrayList<Producto> productos = new ArrayList<Producto>();
        c = db.rawQuery("select * from productos", null);

        if (c.moveToFirst()) {
            do {
                productos.add(new Producto(c.getString(0), c.getString(1)));
            } while (c.moveToNext());
        }
        db.close();
        return productos;
    }

}
