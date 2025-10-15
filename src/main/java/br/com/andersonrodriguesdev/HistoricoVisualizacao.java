package br.com.andersonrodriguesdev;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HistoricoVisualizacao {

    private final Database database;

    //ItemCardapio => Data e Hora
    private final Map<ItemCardapio, LocalDateTime> visualizacoes = new HashMap<>();

    public HistoricoVisualizacao(Database database) {
        this.database = database;
    }

    public void registrarVisualizacao(Long itemId){
        Optional<ItemCardapio> optionalItemCardapio = database.itemCardapioPorId(itemId);
        if(optionalItemCardapio.isEmpty()){
            System.out.println("Item não encontrado: "+itemId);
            return;
        }
        ItemCardapio itemCardapio = optionalItemCardapio.get();
        LocalDateTime now = LocalDateTime.now();
        visualizacoes.put(itemCardapio, now);
        System.out.printf("'%s' visualizado em '%s'\n", itemCardapio.nome(), now);
    }

    public void mostrarTotalItensVisualizados(){
        System.out.println("\nTotal de itens visualizados: "+ visualizacoes.size());
    }

    public void listaVisualizacoes(){
        if (visualizacoes.isEmpty()) {
            System.out.println("\nNenhum item foi visualizado ainda.");
            return;
        }

        System.out.println("\nHistórico de Visualizações");
        visualizacoes.forEach((item, hora) ->
                        System.out.printf("- %s em %s\n", item.nome(), hora));
        System.out.println();
    }

}
