package org.example.Ejercicio2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            CifradoCesar cesarService = (CifradoCesar) registry.lookup("CifradoCesar");

            int desplazamiento = 6;
            String mensajeOriginal = "CESUR MALAGA ESTE";

            String mensajeCifrado = cesarService.cesar(desplazamiento, mensajeOriginal);
            System.out.println("Mensaje cifrado: " + mensajeCifrado);

            String mensajeReves = cesarService.reves(mensajeOriginal);
            System.out.println("Mensaje del reves: " + mensajeReves);

            String mensajeDescifrado = cesarService.descifrar(desplazamiento, mensajeCifrado);
            System.out.println("Mensaje descifrado: " + mensajeDescifrado);
        } catch (Exception e) {
            System.out.println("Client exception: " + e);
            e.printStackTrace();
        }
    }

}
