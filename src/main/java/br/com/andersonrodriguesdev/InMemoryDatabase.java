package br.com.andersonrodriguesdev;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDatabase implements Database {

    private final Map<Long, ItemCardapio> itensPorId = new ConcurrentHashMap<>();
    private final Map<ItemCardapio, BigDecimal> auditoriaPrecos = new IdentityHashMap<>();

    public InMemoryDatabase() {
//        var refrescoDoChaves = new ItemCardapio(1L, "Refresco do Chaves",
//                "Suco de limão que parece de tamarindo e tem gosto de groselha.",
//                BEBIDAS, new BigDecimal("2.99"), null);
//        itensPorId.put(refrescoDoChaves.id(), refrescoDoChaves);
//
//        var sanduicheDoChaves = new ItemCardapio(2L, "Sanduíche de Presunto do Chaves",
//                "Sanduíche de presunto simples, mas feito com muito amor.",
//                PRATOS_PRINCIPAIS, new BigDecimal("3.50"), new BigDecimal("2.99"));
//        itensPorId.put(sanduicheDoChaves.id(), sanduicheDoChaves);
//
//        var tortaDaDonaFlorinda = new ItemCardapio(5L, "Torta de Frango da Dona Florinda",
//                "Torta de frango com recheio cremoso e massa crocante.",
//                PRATOS_PRINCIPAIS, new BigDecimal("12.99"), new BigDecimal("10.99"));
//        itensPorId.put(tortaDaDonaFlorinda.id(), tortaDaDonaFlorinda);
//
//        var pipocaDoQuico = new ItemCardapio(6L, "Pipoca do Quico",
//                "Balde de pipoca preparado com carinho pelo Quico.",
//                PRATOS_PRINCIPAIS, new BigDecimal("4.99"), new BigDecimal("3.99"));
//        itensPorId.put(pipocaDoQuico.id(), pipocaDoQuico);
//
//        var aguaDeJamaica = new ItemCardapio(7L, "Água de Jamaica",
//                "Água aromatizada com hibisco e toque de açúcar.",
//                BEBIDAS, new BigDecimal("2.50"), new BigDecimal("2.00"));
//        itensPorId.put(aguaDeJamaica.id(), aguaDeJamaica);
//
//        var churrosDoChaves = new ItemCardapio(9L, "Churros do Chaves",
//                "Churros recheados com doce de leite, clássicos e irresistíveis.",
//                SOBREMESAS, new BigDecimal("4.99"), new BigDecimal("3.99"));
//        itensPorId.put(churrosDoChaves.id(), churrosDoChaves);
    }

    @Override
    public List<ItemCardapio> listaDeItensCardapio() {

        return new ArrayList<>(itensPorId.values());
    }

    @Override
    public Optional<ItemCardapio> itemCardapioPorId(Long itemId){
        ItemCardapio itemCardapio = itensPorId.get(itemId);
        return  Optional.ofNullable(itemCardapio);
    }

    @Override
    public boolean removerItemCardapio(Long itemId){
        ItemCardapio itemCardapioRemovido = itensPorId.remove(itemId);
        return  itemCardapioRemovido != null;
    }

    @Override
    public boolean alterarPrecoItemCardapio(Long itemId, BigDecimal novoPreco) {
        ItemCardapio itemCardapio = itensPorId.get(itemId);
        if (itemCardapio == null) {
            return false;
        }
        ItemCardapio itemComPrecoAlterado = itemCardapio.alterarPreco(novoPreco);
        itensPorId.put(itemId, itemComPrecoAlterado);
        auditoriaPrecos.put(itemCardapio, novoPreco);
        return true;
    }

    public void imprimirRastroAuditoriaPrecos(){
        System.out.println("\nAuditoria de preços:");
        auditoriaPrecos.forEach((itemAntigo, novoPreco) -> {
            System.out.printf("- %s: %s => %s\n", itemAntigo.nome(), itemAntigo.preco(), novoPreco);
        });
    }

    @Override
    public void adicionaItemCardapio(ItemCardapio itemCardapio){
        itensPorId.put(itemCardapio.id(), itemCardapio);
    }

    @Override
    public int totalItensCardapio() {
        return itensPorId.size();
    }


}
