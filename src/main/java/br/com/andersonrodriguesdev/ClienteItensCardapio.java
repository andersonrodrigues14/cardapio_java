package br.com.andersonrodriguesdev;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ClienteItensCardapio {

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://localhost:8000/itensCardapio.json");

        try (Scanner scanner = new Scanner(url.openStream())){

            //URLConnection urlConnection = url.openConnection();

            //InputStream inputStream = urlConnection.getInputStream();
            //Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        URI uri = URI.create("http://localhost:8000/itensCardapio.json");

        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder(uri).build();
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            String body = httpResponse.body();
            System.out.println(body);
        }

    }
}
