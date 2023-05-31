package CaminhosMinimos;

import Grafos.GrafoLAdj;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeMap;

public class FloydWarshall
{
    public final GrafoLAdj G;
    private final TreeMap<Integer, Integer> indices;
    private final int[][] dist;
    private final int[] prev;

    public FloydWarshall(GrafoLAdj G) {
        final int nVertices = G.totalVertices();
        dist = new int[nVertices][nVertices];
        prev = new int[nVertices];
        indices = G.gerarIndices();
        this.G = G;
    }
    // Programa assume que os vértices iniciam em 0
    public void executar() {
        final int nVertices = G.totalVertices();
        final Set<Integer> vList = G.vertices();
        int i;
        // Inicializa as matrizes com valores padrão.
        for(i = 0; i < nVertices; i++) {
            for(int j = 0; j < nVertices; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
            prev[i] = -1;
        }
        i = 0;
        // Seta os valores iniciais dos caminhos mínimos e dos antecessores
        for(var v : vList) {
            var arestas = G.adj(v);
            for(var aresta : arestas) {
                final int destIndex = indices.get(aresta.destino);
                dist[i][destIndex] = aresta.peso;
            }
            dist[i][i] = 0;
            i++;
        }
        // Determina os caminhos mínimos e os antecessores
        for(var k : vList) {
            final int indexK = indices.get(k);
            for(var u : vList) {
                final int indexU = indices.get(u);
                for(var v : vList) {
                    final int indexV = indices.get(v);
                    if( dist[indexU][indexK] == Integer.MAX_VALUE || dist[indexK][indexV] == Integer.MAX_VALUE) continue;
                    int camIntermediario = dist[indexU][indexK] + dist[indexK][indexV];
//                    dist[u][v] = Integer.min(dist[u][v], camIntermediario);
                    if(camIntermediario < dist[indexU][indexV]) {
                        dist[indexU][indexV] = camIntermediario;
                        prev[indexV] = k;
                    }
                }
            }
        }
    }

    public int[][] getDistanciasMinimas() { return this.dist; }
    public int[] getAntecessores() { return this.prev; }

    public void print() {
        System.out.println("------FLOYD-WARSHALL------");
        System.out.println("## Matriz dos caminhos mínimos:");
        for (int[] ints : dist) {
            for (int j = 0; j < ints.length; j++) {
                if (j == 0) System.out.print("|");
                System.out.print(ints[j] + "|");
            }
            System.out.println();
        }
        System.out.println("## Vetor dos antecessores:");
        for(var v : G.vertices()) {
            final int index = indices.get(v);
            System.out.println("Menor antecessor de " + v + ": " + prev[index]);
        }
    }

    public boolean printCaminho(int u, int v) {
        final int nLoops = G.totalArestas();
        final int dest = v;
        final int srcIndex = indices.get(u);
        int destIndex = indices.get(v);
        int i = 0;
        var fila = new LinkedList<Integer>();
        System.out.println("## Caminho " + u + "->" + v + ":");
        while(true) {
            if(i++ == nLoops) {
                System.out.println("\nNão há caminho de " + u + " para" + v);
                return false;
            }
            if(u == v || u == prev[destIndex]) break;
            fila.add(prev[destIndex]);
            v = prev[destIndex];
            destIndex = indices.get(v);
        }
        fila.add(dest);
        fila.forEach(e -> System.out.print(e + "-"));
        System.out.println();
        return true;
    }
}
