
package com.supermarket;

/**
 *
 * @author USUARIO
 */

public class Producto {
    private String nombre;
    private double precio;
    private int tiempoProcesamiento; 

    public Producto(String nombre, double precio, int tiempoProcesamiento) {
        this.nombre = nombre;
        this.precio = precio;
        this.tiempoProcesamiento = tiempoProcesamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getTiempoProcesamiento() {
        return tiempoProcesamiento;
    }
}