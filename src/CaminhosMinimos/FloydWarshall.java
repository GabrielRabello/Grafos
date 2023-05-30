package CaminhosMinimos;

import Grafos.GrafoLAdj;

import java.util.Arrays;
import java.util.Set;

public class FloydWarshall {
    public final GrafoLAdj G;
    private int[][] dist;
    private int[] prev;

    public FloydWarshall(GrafoLAdj G) {
        final int nVertices = G.totalVertices();
        dist = new int[nVertices][nVertices];
        prev = new int[nVertices];
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
                dist[i][aresta.destino] = aresta.peso;
            }
            dist[i][i] = 0;
            i++;
        }
        // Determina os caminhos mínimos e os antecessores
        for(var k : vList) {
            for(var u : vList) {
                for(var v : vList) {
                    if( dist[u][k] == Integer.MAX_VALUE || dist[k][v] == Integer.MAX_VALUE) continue;
                    int camIntermediario = dist[u][k] + dist[k][v];
//                    dist[u][v] = Integer.min(dist[u][v], camIntermediario);
                    if(camIntermediario < dist[u][v]) {
                        dist[u][v] = camIntermediario;
                        prev[v] = k;
                    }
                }
            }
        }
    }

    public int[][] getDistanciasMinimas() { return this.dist; }
    public int[] getAntecessores() { return this.prev; }

    public void print() {
        System.out.println("Matriz dos caminhos mínimos:");
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[i].length; j++) {
                if(j == 0) System.out.print("|");
                System.out.print(dist[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("Vetor dos antecessores:");
        for(int i = 0; i < dist.length; i++) {
            System.out.println("Menor antecessor de " + i + ": " + prev[i]);
        }
    }

    public boolean printCaminho(int u, int v) {
        final int nLoops = G.totalArestas();
        int i = 0;
        System.out.println("Caminho " + u + "->" + v + ":");
        while(u != v) {
            if(i++ == nLoops) {
                System.out.println("\nNão há caminho de " + u + " para" + v);
                return false;
            }
            System.out.print(prev[v]);
            v = prev[v];
            System.out.print("-");
        }
        System.out.print(prev[v]);
        return true;
    }
}
