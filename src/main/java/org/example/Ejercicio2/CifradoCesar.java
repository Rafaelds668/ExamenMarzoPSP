package org.example.Ejercicio2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CifradoCesar extends Remote {
    String cesar(int desplazamiento, String mensaje) throws RemoteException;
    String reves(String mensaje) throws RemoteException;
    String descifrar(int desplazamiento, String mensaje) throws RemoteException;

}
