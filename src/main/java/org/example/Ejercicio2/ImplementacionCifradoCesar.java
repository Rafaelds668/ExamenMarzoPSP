package org.example.Ejercicio2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImplementacionCifradoCesar extends UnicastRemoteObject implements CifradoCesar {



    private static final String CARACTERES = "ABCDEFGHIJKLMNÃ‘OPQRSTUVWXYZ ";

    public ImplementacionCifradoCesar() throws RemoteException {
        super();
    }
    @Override
    public String cesar(int desplazamiento, String mensaje) throws RemoteException {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < mensaje.length(); i++) {
            char caracter = mensaje.charAt(i);
            int posicion = CARACTERES.indexOf(caracter);
            if (posicion != -1) {
                int nuevaPosicion = (posicion + desplazamiento) % CARACTERES.length();
                if (nuevaPosicion < 0) {
                    nuevaPosicion += CARACTERES.length(); // Ajuste para manejar Ã­ndices negativos
                }
                resultado.append(CARACTERES.charAt(nuevaPosicion));
            } else {
                resultado.append(caracter);
            }
        }
        return resultado.toString();
    }

    @Override
    public String reves(String mensaje) throws RemoteException {
        return new StringBuilder(mensaje).reverse().toString();
    }

    // Para descifrar usaremos el cifrado con el desplazamiento negativo
    @Override
    public String descifrar(int desplazamiento, String mensaje) throws RemoteException {
        return cesar(-desplazamiento, mensaje);
    }

}
