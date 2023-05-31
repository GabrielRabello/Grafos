package CaminhosMinimos;

import Grafos.GrafoLAdj;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra extends CaminhoMinimoBase
{
    public Dijkstra(GrafoLAdj G) {
        super(G);
    }

    public void executar(int verticeOrigem)
    {
        System.out.println("------DIJKSTRA-------\nEstado inicial:");
        System.out.println("{Vértice}: {Custo}");
        this.printEstadoAtual();

        super.inicializarFonteUnica(verticeOrigem);
        int loop = 1;
        // Não inicializei a fila de prioridade totalmente porque ela não é reordenada automaticamente ao longo do relaxamento (alterações nos valores).
        // Então ela é preenchida ao longo do loop. Fora isso o algoritmo é semanticamente idêntico ao do Cormen.
        var fila = new PriorityQueue<Map.Entry<Integer, Integer>>(Comparator.comparingInt(Map.Entry::getValue));
        fila.add(Map.entry(verticeOrigem, 0));
        while(!fila.isEmpty()) {
            int u = fila.remove().getKey();
            for(var aresta : super.G.adj(u)) {
                if(super.relaxar(u, aresta)) {
                    fila.add(Map.entry(aresta.destino, dEstimada.get(aresta.destino)));
                }
                System.out.println("Loop " + loop++ + " (u = " + u + ")" + ":");
                super.printEstadoAtual();
            }
        }
    }
}
