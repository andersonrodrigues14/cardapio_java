package br.com.andersonrodriguesdev;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GeradorItensCardapioJSON {

    public static void main(String[] args) throws IOException {
        Database database = new InMemoryDatabase();
        List<ItemCardapio> listaItensCardapio = database.listaDeItensCardapio();

        Gson gson = new Gson();
        String json = gson.toJson(listaItensCardapio);

        System.out.println(json);

        Path path = Path.of("itensCardapio.json");
        Files.writeString(path, json);

    }
}
