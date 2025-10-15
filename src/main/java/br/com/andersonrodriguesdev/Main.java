package br.com.andersonrodriguesdev;

import com.google.gson.Gson;

import java.math.BigDecimal;

import static br.com.andersonrodriguesdev.ItemCardapio.CategoriaCardapio.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ItemCardapio refrescoDoChaves = new ItemCardapio(1L, "Refresco do Chaves", """
                Suco de limão, que parece tamarindo mas tem gosto de groselha
                """, BEBIDAS, new BigDecimal("2.99"), null);

        Gson gson = new Gson();
        String json = gson.toJson(refrescoDoChaves);
        System.out.println(json);
    }
}