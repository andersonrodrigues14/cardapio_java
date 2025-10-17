package br.com.andersonrodriguesdev;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServidorItensCardapioComSocket {

    public static void main(String[] args) throws Exception {

        try(ServerSocket serverSocket = new ServerSocket(8000)){
            System.out.println("Servidor Iniciado");

            while(true){

                try(Socket clientSocket = serverSocket.accept()){
                    System.out.println("Cliente conectado");
                    InputStream clientIS = clientSocket.getInputStream();

                    StringBuilder requestBuilder = new StringBuilder();

                    int data;

                    do {
                        data = clientIS.read();
                        requestBuilder.append((char)data);
                    } while (clientIS.available() > 0);

                    String request = requestBuilder.toString();
                    System.out.println(request);

                    Path path = Paths.get("itensCardapio.json");
                    String json = Files.readString(path);

                    OutputStream clientOS = clientSocket.getOutputStream();
                    PrintStream clientOut = new PrintStream(clientOS);

                    clientOut.println("HTTP/1.1 200 OK");
                    clientOut.println("Content-type: application/json; charset=UTF-8");
                    clientOut.println();
                    clientOut.println(json);

                }
            }

        }

    }

}
