package com.example.evaluacionindividual;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private EditText et1;
    private EditText et2;
    private ListView listview;

    AdminSQLite AdminBD;
    SQLiteDatabase db;
    ArrayList<Producto> productos;
    ArrayList<String> str_productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Conectando la parte de diseño con la parte logica
        et1 = (EditText) findViewById(R.id.et_nombreprod);
        et2 = (EditText) findViewById(R.id.et_precioprod);
        listview = (ListView) findViewById(R.id.listviewprod);

        AdminBD = new AdminSQLite(getApplicationContext());               //Obtener el contexto es util mas que nada cuando queremos hacer algo en una activity que no es la de Inicio, pero de todas formas es bueno especificar donde esta
        AdminBD.abrirBaseDeDatos();
        AdminBD.seleccionDatos();

    }

    //Solo se pide metodo insert y seleccionar
    public void insertar(View view) {
        AdminBD.insertarDatos(et1.getText().toString(), et2.getText().toString());
        AdminBD.abrirBaseDeDatos();
        limpiar(view);
        seleccionar(view);
        Toast.makeText(this, "Datos Ingresados", Toast.LENGTH_SHORT).show();
    }

    //Para poder desplegar informacion en layouts y uso de adapter por defecto, hay que usar solamente listas de tipo String, por eso se
    // recorren la lista Producto y la info se almacena en lista String
    public void seleccionar(View view) {
        str_productos = new ArrayList<>();   //Esta Lista se crea dentro de este metodo, de lo contrario, al apretar el boton asociado al metodo insertar, los datos se duplicaban
        productos = AdminBD.seleccionDatos();
        for (int i = 0; i < productos.size(); i++) {
            str_productos.add(productos.get(i).getNombre() + '\n' + " $" + productos.get(i).getPrecio());
        }

        //Inicializacion de adapter y asociandolo a la listview.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str_productos);
        listview.setAdapter(adapter);
    }

    //Limpiar campos
    public void limpiar(View view) {
        et1.setText(null);
        et2.setText(null);
    }

}