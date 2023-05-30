package Grafos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Discente: Gabriel Shimada Rabello    Docente: Roberto Samarone dos Santos Araújo
 * Matrícula: 202204940039              Disciplina: Grafos
 * Data: 01/05/2023                     Atividades 3 e 4
 */

/**
 * Melhoria importante a ser feita: Os vértices são representados pelo próprio índice. Isso causa confusão entre o uso e implementação. Tentar abstrair a representação dos vértices em outra classe.
 */
public class GrafoLMatriz {
    private List<List<Boolean>> grafo;

    public GrafoLMatriz(int nVertices) {
        grafo = new LinkedList<>();
        for (int i = 0; i < nVertices; i++) {
            var newRow = new LinkedList<Boolean>();
            for (int j = 0; j < nVertices; j++) {
                newRow.add(false);
            }
            grafo.add(newRow);
        }
    }

    public void addVertice() {
        int size = grafo.size() + 1;
        var newRow = new LinkedList<Boolean>();
        for (int i = 0; i < size; i++) {
            if (i != size - 1) { // Novas colunas
                grafo.get(i).add(false);
            }
            newRow.add(false);
        }
        grafo.add(newRow); // Nova linha
    }

    public void remVertice(int v) {
        for (var linha : grafo) {
            linha.remove(v);
        }
        grafo.remove(v);
    }

    public void addAresta(int src, int dest) {
        grafo.get(src).set(dest, true);
        grafo.get(dest).set(src, true);
    }

    public void remAresta(int src, int dest) {
        grafo.get(src).set(dest, false);
        grafo.get(dest).set(src, false);
    }

    public List<Byte> adj(int v) {
        var res = new ArrayList<Byte>();
        for(var val : grafo.get(v)) {
            res.add((byte) (val ? 1 : 0));
        }
        return res;
    }

    public int adjCount(int v) {
        int n = 0;
        for (var el : grafo.get(v)) {
            if (el) n++;
        }
        return n;
    }

    public int totalVertices() {
        return grafo.size();
    }

    public int totalArestas() {
        final int size = grafo.size();
        int n = 0;
        int selfCount = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boolean el = grafo.get(i).get(j);
                if (el) {
                    // Caso haja uma laço
                    if (i != j) n++;
                    else selfCount++;
                }
            }
        }
        return (n / 2) + selfCount;
    }

    public void print() {
        System.out.print("V/A ");
        final int size = grafo.size();
        for (int i = 0; i < size; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < size; j++) {
                int val = grafo.get(i).get(j) ? 1 : 0;
                System.out.print(val + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
