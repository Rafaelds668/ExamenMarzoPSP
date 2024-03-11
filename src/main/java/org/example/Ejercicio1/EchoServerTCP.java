package org.example.Ejercicio1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServerTCP {
    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        Socket client = null;
        PrintWriter out = null;
        Scanner in = null;
        String line;
        int port = 12345; // Puerto del servidor

        /*
         * Crear e inicializar el socket del servidor
         */
        try {
            server = new ServerSocket(port);
            System.out.println("Server listening on port " + port);
        } catch (IOException e) {
            System.out.println("Could not listen on port " + port);
            System.exit(-1);
        }

        while (true) {
            try {
                // Esperar conexiones entrantes
                client = server.accept();
                System.out.println("Accepted connection from " + client.getInetAddress());

                // Inicializar flujos de entrada/salida del socket conectado
                out = new PrintWriter(client.getOutputStream(), true);
                in = new Scanner(client.getInputStream());

                boolean salir = false;

                while (!salir) {
                    try {
                        // Leer datos del cliente en String line
                        line = in.nextLine();
                        System.out.println("Received from client: " + line);

                        // Comprobar si es fin de conexiÃ³n
                        if (!line.equals("CLOSE")) {
                            // Enviar eco al cliente
                            out.println("Server Echo: " + line);
                            System.out.println("Sending to client: " + line);
                        } else {
                            // El cliente quiere cerrar la conexiÃ³n, ha enviado CLOSE
                            // Enviar OK al cliente
                            out.println("OK");
                            salir = true;
                        }
                    } catch (Exception e) {
                        System.out.println("Read failed");
                        System.exit(-1);
                    }
                }

                // Cerrar flujos y socket de la conexiÃ³n con el cliente
                in.close();
                out.close();
                client.close();

                System.out.println("Closing connection with the client");
                System.out.println("Waiting for a new client");
            } catch (IOException e) {
                System.out.println("Accept failed: " + port);
                System.exit(-1);
            }
        }
        //server.close();
    }

}
