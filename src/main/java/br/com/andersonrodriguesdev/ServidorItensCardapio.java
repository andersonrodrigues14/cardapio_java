package br.com.andersonrodriguesdev;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServidorItensCardapio {

    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8000);
        HttpServer httpServer = HttpServer.create(inetSocketAddress, 0);

        httpServer.createContext("/itensCardapio.json", exchange -> {
            Path path = Paths.get("itensCardapio.json");
            String json = Files.readString(path);
            byte[] bytes = json.getBytes();

            Headers responseHeaders = exchange.getResponseHeaders();
            responseHeaders.add("Content-Type", "application/json, charset=UTF-8");

            exchange.sendResponseHeaders(200, bytes.length);

            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(bytes);
        });

        System.out.println("Subiu servidor HTTP!");
        httpServer.start();
    }

}
