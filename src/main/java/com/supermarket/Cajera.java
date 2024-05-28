
package com.supermarket;

/**
 *
 * @author USUARIO
 */
public class Cajera extends Thread {
    private String nombre;
    private Cliente cliente;
    private long totalTiempoCobro;

    public Cajera(String nombre, Cliente cliente) {
        this.nombre = nombre;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        System.out.println("La cajera " + nombre + " ha comenzado a procesar la compra del cliente " + cliente.getNombre());
        long initialTime = System.currentTimeMillis();

        for (Producto producto : cliente.getProductos()) {
            this.esperarXsegundos(producto.getTiempoProcesamiento());
            System.out.println("Procesado el producto " + producto.getNombre() + " -> Precio: " + producto.getPrecio() + " en " + producto.getTiempoProcesamiento() + " ms");
        }

        totalTiempoCobro = System.currentTimeMillis() - initialTime;
        System.out.println("La cajera " + nombre + " ha terminado de procesar la compra del cliente " + cliente.getNombre() + " en un tiempo total de " + (totalTiempoCobro / 1000) + " segundos.");
    }

    private void esperarXsegundos(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public long getTotalTiempoCobro() {
        return totalTiempoCobro;
    }
}