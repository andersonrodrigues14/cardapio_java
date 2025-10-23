package br.com.andersonrodriguesdev;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static br.com.andersonrodriguesdev.ItemCardapio.CategoriaCardapio.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Banco de dados
        Database database = new SQLDatabase();
        List<ItemCardapio> listaItensCardapio = database.listaDeItensCardapio();
        listaItensCardapio.forEach(System.out::println);

        int total = database.totalItensCardapio();
        System.out.println("Total de itens cardapio: " + total);

//        ItemCardapio novoItemCardapio = new ItemCardapio(1l, "Tacos de Carnitas", "Tacos recheados com carne tenra",
//                PRATOS_PRINCIPAIS, new BigDecimal("29.10"), null);
//
//        database.adicionaItemCardapio(novoItemCardapio);






        // Execução em Memória
//        InMemoryDatabase database = new InMemoryDatabase();
//        List<ItemCardapio> itens = database.listaDeItensCardapio();
//        itens.forEach(System.out::println);
//        System.out.println("-----------------------");
//       // saber quais categorias realmente tenho no cardapio
//
//        Comparator<ItemCardapio.CategoriaCardapio> comparadorPorNome = Comparator.comparing(ItemCardapio.CategoriaCardapio::name);
//        Set<ItemCardapio.CategoriaCardapio> categorias = new TreeSet<>(comparadorPorNome);
//        for (ItemCardapio item: itens) {
//            ItemCardapio.CategoriaCardapio categoria = item.categoria();
//            categorias.add(categoria);
//        }
//        //for (ItemCardapio.CategoriaCardapio categoria : categorias) {
//        //    System.out.println(categoria);
//        //}
//
//        itens.stream()
//                .map(ItemCardapio::categoria)
//                .collect(Collectors.toCollection(() -> new TreeSet<>(comparadorPorNome)))
//                .forEach(System.out::println);
//
//        // QUANTOS ITENS DE CADA CATEGORIA REALMENTE TEM NO CARDAPIO
//        //Map<ItemCardapio.CategoriaCardapio, Integer> itensPorCategoria= new HashMap<>();
//        //for (ItemCardapio item : itens) {
//        //    ItemCardapio.CategoriaCardapio categoria = item.categoria();
//        //    if(!itensPorCategoria.containsKey(categoria)) {
//        //        itensPorCategoria.put(categoria, 1);
//        //    } else {
//        //        Integer quantidadeAnterior = itensPorCategoria.get(categoria);
//        //        itensPorCategoria.put(categoria, quantidadeAnterior+1);
//        //    }
//        //}
//        //System.out.println(itensPorCategoria);
//        System.out.println("-----------------------");
//
//        itens.stream()
//                .collect(Collectors.groupingBy(
//                        ItemCardapio::categoria,
//                        TreeMap::new,
//                        Collectors.counting()
//                ))
//                .forEach((chave, valor) -> System.out.println(chave + " => " + valor));
//
//        System.out.println("-----------------------");
//        // Item por ID
//        Optional<ItemCardapio> optionalItem = database.itemCardapioPorId(1L);
//        //if (optionalItem.isPresent()){
//        //    ItemCardapio item = optionalItem.get();
//        //    System.out.println(item);
//        //} else {
//        //    System.out.println("Item não encontrado");
//        //}
//
//        String mensagem = optionalItem.map(ItemCardapio::toString).orElse("Item não encontrado");
//        System.out.println(mensagem);
//
//        System.out.println("-----------------------");
//
//        // PRECISA MANTER AS CATEGORIAS QUE ESTÃO EM PROMOCAO
//        Set<ItemCardapio.CategoriaCardapio> categoriaPromocao = new TreeSet<>();
//        categoriaPromocao.add(SOBREMESAS);
//        categoriaPromocao.add(ENTRADAS);
//        categoriaPromocao.forEach(System.out::println);
//
//        System.out.println("-----------------------");
//
//        Set<ItemCardapio.CategoriaCardapio> categoriaCardapios2 = Set.of(SOBREMESAS, ENTRADAS);
//        categoriaCardapios2.forEach(System.out::println);
//
//        System.out.println("-----------------------");
//
//        Set<ItemCardapio.CategoriaCardapio> categoriaCardapios3 = EnumSet.of(SOBREMESAS, ENTRADAS);
//        categoriaCardapios3.forEach(System.out::println);
//
//        System.out.println("-----------------------");
//
//        // PRECISO DAS DESCRIÇÕES ASSOCIADAS AS CATEGORIAS EM PROMOÇÃO
//        Map<ItemCardapio.CategoriaCardapio, String> promocoes = new EnumMap<>(ItemCardapio.CategoriaCardapio.class);
//        promocoes.put(SOBREMESAS, "O doce perfeito para você!");
//        promocoes.put(ENTRADAS, "Comece sua refeição com um toque de sabor!");
//        System.out.println(promocoes);
//
//        System.out.println("-----------------------");
//
//
//        // PRECISO DE UM HISTÓRICO DE VISUALIZAÇÃO DO CARDAPIO
//        HistoricoVisualizacao historico = new HistoricoVisualizacao(database);
//        historico.registrarVisualizacao(1L);
//        historico.registrarVisualizacao(2L);
//        historico.registrarVisualizacao(4L);
//        historico.registrarVisualizacao(6L);
//
//        historico.mostrarTotalItensVisualizados();
//        historico.listaVisualizacoes();
//
//
//        System.out.println("-----------------------");
//
//        // PRECISO REMOVER UM ITEM DE CARDAPIO
//        Long idParaRemover = 2L;
//        boolean removido = database.removerItemCardapio(idParaRemover);
//        if (removido) {
//            System.out.println("Removido com sucesso! ID: " +  idParaRemover);
//        }else{
//            System.out.println("Item não encontrado! ID: " +  idParaRemover);
//        }
//
//        database.listaDeItensCardapio().forEach(System.out::println);
//
//        System.out.println("-----------------------");
//
//        // PRECISA ALTERAR O PRECO DE UM ITEM DO CARDAPIO
//        boolean alterado = database.alterarPrecoItemCardapio(1L, new BigDecimal("10.99"));
//        database.listaDeItensCardapio().forEach(System.out::println);
//
//        System.out.println("-----------------------");
//
//        // PRECISO AUDITAR AS MUDANCAS DE PRECO DOS ITENS DO CARDAPIO
//        database.imprimirRastroAuditoriaPrecos();
    }
}