
package com.supermarket;

/**
 *
 * @author USUARIO
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupermarketCheckout {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creamos la lista para cajeras y clientes
        List<Cajera> cajeras = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();

        System.out.print("Ingrese el numero de clientes: ");
        int numeroClientes = scanner.nextInt();
        scanner.nextLine(); 

        // Le pedimos los datos a cada cliente
        for (int i = 0; i < numeroClientes; i++) {
            System.out.print("Ingrese el nombre del cliente " + (i + 1) + ": ");
            String nombreCliente = scanner.nextLine();

            System.out.print("Ingrese el numero de productos para " + nombreCliente + ": ");
            int numeroProductos = scanner.nextInt();
            scanner.nextLine(); 

            List<Producto> productos = new ArrayList<>();
            double costoTotalCompra = 0;
            int tiempoTotalCompra = 0;

            for (int j = 0; j < numeroProductos; j++) {
                System.out.print("Ingrese el nombre del producto " + (j + 1) + ": ");
                String nombreProducto = scanner.nextLine();

                System.out.print("Ingrese el precio del producto " + nombreProducto + ": ");
                double precioProducto = scanner.nextDouble();
                scanner.nextLine();

                // Calculamos automáticamente el tiempo de procesamiento
                int tiempoProcesamiento = (int) (precioProducto * 300);

                costoTotalCompra += precioProducto;
                tiempoTotalCompra += tiempoProcesamiento;

                productos.add(new Producto(nombreProducto, precioProducto, tiempoProcesamiento));
            }

            Cliente cliente = new Cliente(nombreCliente, productos);
            clientes.add(cliente);

            System.out.println("Productos comprados por " + nombreCliente + ":");
            for (Producto producto : productos) {
                System.out.println("- " + producto.getNombre() + ": $" + producto.getPrecio() + ", Tiempo de procesamiento: " + (producto.getTiempoProcesamiento() / 600) + " segundos");
            }
            System.out.println("Costo total de la compra: $" + costoTotalCompra);
            System.out.println("Tiempo total de procesamiento de la compra: " + (tiempoTotalCompra / 600) + " segundos\n");
            System.out.println("--------------------------------------------------------------------");
        }

        // Pedimos los nombres de las cajeras y le asignamos su respectivo cliente
        for (int i = 0; i < numeroClientes; i++) {
            System.out.print("Ingrese el nombre de la cajera para el cliente " + clientes.get(i).getNombre() + ": ");
            System.out.println("--------------------------------------------------------------------");
            String nombreCajera = scanner.nextLine();
            
            Cajera cajera = new Cajera(nombreCajera, clientes.get(i));
            cajeras.add(cajera);
        }

        // Iniciamos las cajeras con los hilos
        for (Cajera cajera : cajeras) {
            cajera.start();
        }

        // Esperamos a que las cajeras terminen de procesar
        for (Cajera cajera : cajeras) {
            try {
                cajera.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Calculamos y mostramos el tiempo total de cobro para una compra (el máximo entre todas las cajeras)
        long tiempoMaximo = 0;
        for (Cajera cajera : cajeras) {
            if (cajera.getTotalTiempoCobro() > tiempoMaximo) {
                tiempoMaximo = cajera.getTotalTiempoCobro();
            }
        }
        System.out.println("Tiempo total de cobro para una compra: " + (tiempoMaximo / 600) + " segundos.");
        System.out.println("--------------------------------------------------------------------");
        
        // Calculamos y mostramos el tiempo total de cobro para todas las compras
        long tiempoTotal = 0;
        for (Cajera cajera : cajeras) {
            tiempoTotal += cajera.getTotalTiempoCobro();
        }
        System.out.println("Tiempo total de cobro para todas las compras: " + (tiempoTotal / 600) + " segundos.");
        System.out.println("--------------------------------------------------------------------");
        scanner.close();
    }
}