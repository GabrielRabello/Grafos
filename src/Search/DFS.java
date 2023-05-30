package Search;

import Auxiliar.Cor;
import Grafos.GrafoLAdj;
import java.util.HashMap;
import java.util.Map;

/**
 * Discente: Gabriel Shimada Rabello    Docente: Roberto Samarone dos Santos Araújo
 * Matrícula: 202204940039              Disciplina: Grafos
 * Data: 09/05/2023                     Atividades 5 e 6
 */

public class DFS {
    private final GrafoLAdj G;
    private final Map<Integer, Cor> cor;
    private final Map<Integer, Integer> antecessor;
    private final Map<Integer, Integer> tDescoberta;
    private final Map<Integer, Integer> tFinal;
    private int tempoTot;

    public DFS(final GrafoLAdj G) {
        this.G = G;
        cor = new HashMap<>();
        antecessor = new HashMap<>();
        tDescoberta = new HashMap<>();
        tFinal = new HashMap<>();
        tempoTot = 0;
        for (var x : this.G.vertices()) {
            cor.put(x, Cor.Branco);
            antecessor.put(x, null);
        }
        for (var x : this.G.vertices()) {
            if(cor.get(x) == Cor.Branco)
                this.DFSVisit(x);
        }
    }
    public void DFSVisit(final int v) {
        cor.put(v, Cor.Cinza);
        tempoTot++;
        tDescoberta.putIfAbsent(v, tempoTot);
        for (var x : this.G.adj(v)) {
            if (cor.get(x.destino) == Cor.Branco) {
                antecessor.putIfAbsent(x.destino, v);
                DFSVisit(x.destino);
            }
        }
        cor.put(v, Cor.Preto);
        tempoTot++;
        tFinal.putIfAbsent(v, tempoTot);
    }

    @Override
    public String toString() {
        var s = new StringBuilder();
        for (var x : this.G.vertices()) {
            s.append("Vértice ").append(x).append(": ");
            s.append("Cor: ").append(cor.get(x));
            s.append(", antecessor: ").append(antecessor.getOrDefault(x, null));
            s.append(", tDescoberta: ").append(tDescoberta.getOrDefault(x, null));
            s.append(", tFinal: ").append(tFinal.getOrDefault(x, null));
            s.append('\n');
        }
        return s.toString();
    }
}
