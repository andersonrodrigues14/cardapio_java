package br.com.andersonrodriguesdev;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface Database {
    List<ItemCardapio> listaDeItensCardapio();

    Optional<ItemCardapio> itemCardapioPorId(Long itemId);

    boolean removerItemCardapio(Long itemId);

    boolean alterarPrecoItemCardapio(Long itemId, BigDecimal novoPreco);

    void adicionaItemCardapio(ItemCardapio itemCardapio);

    int totalItensCardapio();
}
