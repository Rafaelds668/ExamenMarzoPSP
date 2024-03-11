package org.example.Ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClientTCP {
    public static void main(String[] args) throws IOException {
        String serverName = "localhost";
        int portNumber = 12345;
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            // Crear socket y conectar con el servidor
            echoSocket = new Socket(serverName, portNumber);
            System.out.println("STATUS: Conectado al servidor");

            // Inicializar flujos de entrada/salida del socket conectado
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + serverName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + serverName);
            System.exit(1);
        }

        // Obtener texto por teclado
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        System.out.println("Introduzca un texto a enviar (FIN para acabar)");

        while ((userInput = stdIn.readLine()) != null) {
            // Enviar texto en userInput al servidor a travÃ©s del flujo de salida del socket conectado
            out.println(userInput);
            System.out.println("STATUS: Enviando " + userInput);

            // Si el usuario quiere terminar la interacciÃ³n, ha introducido FIN
            if (userInput.equals("FIN")) {
                System.out.println("STATUS: El cliente quiere terminar la interacciÃ³n");
                break;
            }

            // Recibir texto en echo enviado por el servidor a travÃ©s del flujo de entrada del socket conectado
            String echo = in.readLine();
            System.out.println("echo: " + echo); // Muestra por pantalla el eco recibido

            // Leer texto de usuario por teclado
            System.out.println("Introduzca un texto a enviar (FIN para acabar)");
        }

        // Enviar CLOSE al servidor a travÃ©s del flujo de salida del socket conectado para indicar el fin de la interacciÃ³n
        out.println("CLOSE");
        System.out.println("STATUS: Sending CLOSE");

        // Recibir OK enviado por el servidor confirmando el cierre a travÃ©s del flujo de entrada del socket conectado
        String ok = in.readLine();
        System.out.println("STATUS: Cerrando conexiÃ³n " + ok);

        // Cerrar flujos y sockets
        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
        System.out.println("STATUS: ConexiÃ³n cerrada");
    }

}
