package br.com.andersonrodriguesdev;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorItensCardapioComSocket {

    private static final Database database = new Database();

    public static void main(String[] args) throws Exception {

        try (ExecutorService executorService = Executors.newFixedThreadPool(50)) {

            try (ServerSocket serverSocket = new ServerSocket(8000)) {
                System.out.println("Servidor Iniciado");

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    executorService.execute(() -> trataRequisicao(clientSocket));
                }
            }
        }
    }

    private static void trataRequisicao(Socket clientSocket) {
        try (clientSocket) {

            System.out.println("Cliente conectado");

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream clientOS = clientSocket.getOutputStream();
            PrintStream clientOut = new PrintStream(clientOS);

            // Lê headers
            StringBuilder headersBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                headersBuilder.append(line).append("\r\n");
            }

            String headers = headersBuilder.toString();
            System.out.println("HEADERS:\n" + headers);

            // Extrai a primeira linha da requisição (ex: POST /itens-cardapio HTTP/1.1)
            String[] headerLines = headers.split("\r\n");
            String requestLine = headerLines[0];
            String[] requestParts = requestLine.split(" ");
            if (requestParts.length < 2) {
                clientOut.println("HTTP/1.1 400 Bad Request");
                clientOut.println();
                return;
            }

            String method = requestParts[0];
            String requestURI = requestParts[1];

            // Extrai Content-Length (se houver)
            int contentLength = 0;
            for (String header : headerLines) {
                if (header.toLowerCase().startsWith("content-length:")) {
                    contentLength = Integer.parseInt(header.split(":")[1].trim());
                    break;
                }
            }

            // Lê o corpo (se houver)
            String body = "";
            if (contentLength > 0) {
                char[] bodyChars = new char[contentLength];
                int read = reader.read(bodyChars, 0, contentLength);
                body = new String(bodyChars, 0, read);
                System.out.println("BODY:\n" + body);
            }

            System.out.println("#####################################");
            System.out.println(method + " " + requestURI);

            Gson gson = new Gson();

            if (method.equals("GET") && requestURI.equals("/itensCardapio.json")) {
                Path path = Paths.get("itensCardapio.json");
                String json = Files.readString(path);

                clientOut.println("HTTP/1.1 200 OK");
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                clientOut.println(json);

            } else if (method.equals("GET") && requestURI.equals("/itens-cardapio")) {
                List<ItemCardapio> listaItensCardapio = database.listaDeItensCardapio();
                String json = gson.toJson(listaItensCardapio);

                clientOut.println("HTTP/1.1 200 OK");
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                clientOut.println(json);

            } else if (method.equals("GET") && requestURI.equals("/itens-cardapio/total")) {
                List<ItemCardapio> listaItensCardapio = database.listaDeItensCardapio();

                clientOut.println("HTTP/1.1 200 OK");
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                clientOut.println(listaItensCardapio.size());

            } else if (method.equals("POST") && requestURI.equals("/itens-cardapio")) {
                if (body.isEmpty()) {
                    clientOut.println("HTTP/1.1 400 Bad Request");
                    clientOut.println();
                    return;
                }

                try {
                    ItemCardapio novoItem = gson.fromJson(body, ItemCardapio.class);
                    database.adicionaItemCardapio(novoItem);

                    clientOut.println("HTTP/1.1 201 Created");
                    clientOut.println();
                } catch (Exception e) {
                    clientOut.println("HTTP/1.1 400 Bad Request");
                    clientOut.println("Content-Type: text/plain; charset=UTF-8");
                    clientOut.println();
                    clientOut.println("Erro ao processar JSON: " + e.getMessage());
                }

            } else {
                clientOut.println("HTTP/1.1 404 Not Found");
                clientOut.println();
            }

        } catch (Exception e) {
            System.err.println("Erro ao tratar requisição: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
