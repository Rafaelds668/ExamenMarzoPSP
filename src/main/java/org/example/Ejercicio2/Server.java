package org.example.Ejercicio2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            CifradoCesar cesarService = new ImplementacionCifradoCesar();
            registry.rebind("CifradoCesar", cesarService);
            System.out.println("Servidor listo");
        } catch (Exception e) {
            System.err.println("Server exception: " + e);
            e.printStackTrace();
        }
    }

}
