package Search;

import Auxiliar.Cor;
import Grafos.GrafoLAdj;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.ArrayDeque;

/**
 * Discente: Gabriel Shimada Rabello    Docente: Roberto Samarone dos Santos Araújo
 * Matrícula: 202204940039              Disciplina: Grafos
 * Data: 09/05/2023                     Atividades 5 e 6
 */

public class BFS {
    private final GrafoLAdj G;
    private final Map<Integer, Cor> cor;
    private final Map<Integer, Integer> antecessor;
    private final Map<Integer, Integer> dOrigem;

    public BFS(final GrafoLAdj G, final int vInicial) {
        if (!G.hasVertice(vInicial)) throw new IllegalArgumentException("Vértice inicial não existe no grafo");
        this.G = G;
        cor = new HashMap<>();
        antecessor = new HashMap<>();
        dOrigem = new HashMap<>();

        for (var x : G.vertices()) {
            if (x == vInicial) continue;
            cor.put(x, Cor.Branco);
            dOrigem.put(x, Integer.MAX_VALUE);
            antecessor.put(x, null);
        }
        cor.put(vInicial, Cor.Cinza);
        dOrigem.put(vInicial, 0);
        antecessor.put(vInicial, null);
        Queue<Integer> fila = new ArrayDeque<>();
        fila.add(vInicial);
        while (!fila.isEmpty()) {
            var v = fila.remove();
            for (var x : G.adj(v)) {
                if (cor.get(x.destino) == Cor.Branco) {
                    cor.put(x.destino, Cor.Cinza);
                    dOrigem.put(x.destino, dOrigem.get(v) + 1);
                    antecessor.put(x.destino, v);
                    fila.add(x.destino);
                }
            }
            cor.put(v, Cor.Preto);
        }
    }

    @Override
    public String toString() {
        var s = new StringBuilder();
        for (var x : this.G.vertices()) {
            s.append("Vértice ").append(x).append(": ");
            s.append("Cor: ").append(cor.get(x));
            s.append(", antecessor: ").append(antecessor.getOrDefault(x, null));
            s.append(", distância-origem: ").append(dOrigem.getOrDefault(x, null));
            s.append('\n');
        }
        return s.toString();
    }
}
