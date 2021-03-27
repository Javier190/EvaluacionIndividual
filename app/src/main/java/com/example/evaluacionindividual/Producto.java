package com.example.evaluacionindividual;

//Los datos a desplegar en el listview seran obtenidos a partir de las instancias de esta Clase

public class Producto {
    private String nombre;
    private String precio;


    //Constructor
    public Producto(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    //Metodos

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
