package br.com.andersonrodriguesdev;

import java.math.BigDecimal;

public record ItemCardapio(Long id, String nome, String descricao, CategoriaCardapio categoria, BigDecimal preco,
                           BigDecimal precoComDesconto) {

    public enum CategoriaCardapio {
        ENTRADAS,
        PRATOS_PRINCIPAIS,
        BEBIDAS,
        SOBREMESAS
    }

}
