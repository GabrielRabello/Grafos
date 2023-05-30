package Grafos;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

/**
 * (Atualizado Atividades 5 e 6)
 * Discente: Gabriel Shimada Rabello    Docente: Roberto Samarone dos Santos Araújo
 * Matrícula: 202204940039              Disciplina: Grafos
 * Data: 01/05/2023                     Atividades 3 e 4
 */

/**
 * Aceita grafos com as seguintes características: <br>
 * Peso/sem peso, direção/sem direção, laços/sem laços. <br>
 * Grafos com peso não funcionarão como esperado caso tenham arestas paralelas.
 */
public class GrafoLAdj {
    private final Map<Integer, LinkedList<Aresta>> grafo;
    private final boolean direcionado;
    private static final String NUMERO_VERTICES_NEGATIVO = "Número de vértices em um grafo não pode ser negativo";

    public GrafoLAdj(int vertices) {
        this(vertices, false);
    }
    public GrafoLAdj(int vertices, boolean direcionado) {
        if (vertices < 0) throw new IllegalArgumentException(NUMERO_VERTICES_NEGATIVO);
        grafo = new HashMap<>();
        this.direcionado = direcionado;
        for (int i = 0; i < vertices; i++) {
            grafo.put(i, new LinkedList<>());
        }
    }
    public GrafoLAdj(String nomeArquivo, boolean direcionado, boolean comPeso) throws IOException {
        final int nColunas = comPeso ? 3 : 2;
        String s = this.getCaminhoArquivo(nomeArquivo);
        Scanner in = new Scanner(Path.of(s), StandardCharsets.UTF_8);
        int v = in.nextInt();
        if (v < 0) throw new IllegalArgumentException(NUMERO_VERTICES_NEGATIVO);
        int[] vals = new int[nColunas];
        this.grafo = new HashMap<>();
        this.direcionado = direcionado;
        for (int i = 1; i <= v; i++) {
            this.grafo.put(i, new LinkedList<>());
        }

        while (in.hasNextInt()) {
            for(int i = 0; i < nColunas; i++) {
                if(in.hasNextInt()) {
                    vals[i] = in.nextInt();
                }
            }
            if (comPeso) this.addAresta(vals[0], vals[1], vals[2]);
            else this.addAresta(vals[0], vals[1]);
        }
        in.close();
    }

    public void addVertice(int v) {
        grafo.putIfAbsent(v, new LinkedList<>());
    }

    public boolean remVertice(int v) {
        if (!grafo.containsKey(v)) return false;
        // Remove toda aresta (v, x) e (x, v), onde x é qualquer vértice conectado a v.
        for(var lista : grafo.values()) {
            lista.removeIf(aresta -> aresta.destino == v);
        }
        // Remove o vertice v
        grafo.remove(v);
        return true;
    }

    public void addAresta(int src, int dest, int peso) {
        grafo.get(src).add(new Aresta(dest, peso));
        if (!direcionado && src != dest)
            grafo.get(dest).add(new Aresta(src, peso));
    }
    public void addAresta(int src, int dest) {
        this.addAresta(src, dest, Aresta.PESO_PADRAO);
    }

    public void remAresta(int src, int dest) {
        grafo.get(src).removeIf(aresta -> aresta.destino == dest);
        if (!direcionado)
            grafo.get(dest).removeIf(aresta -> aresta.destino == src);
    }

    public boolean hasVertice(int v) {
        return grafo.containsKey(v);
    }

    public Set<Integer> vertices() {
        return grafo.keySet();
    }

    /** Retorna lista de adjacências do vértice v */
    public List<Aresta> adj(int v) { return grafo.get(v); }

    /** Retorna a quantidade de vértices adjacentes do vértice v. */
    public int adjCount(int v) { return grafo.get(v).size(); }

    /** Retorna o total de vértices do grafo */
    public int totalVertices() { return grafo.size(); }

    /** Retorna o total de arestas do grafo */
    public int totalArestas() {
        int n = 0;
        int selfCount = 0;
        for (var e : grafo.entrySet()) {
            int k = e.getKey();
            n += e.getValue().size();
            // Caso haja uma laço
            if (e.getValue().contains(k)) {
                n--;
                selfCount++;
            }
        }
        return (n / 2) + selfCount;
    }

    /** Exibe a lista de adjacências de todo o grafo */
    public void print() {
        for (var map : grafo.entrySet()) {
            int v = map.getKey();
            LinkedList<Aresta> adjList = map.getValue();
            for (Aresta vizinho : adjList) {
                System.out.println(v + "-" + vizinho.destino + ": " + vizinho.peso);
            }
        }
    }

    private String getCaminhoArquivo(String arquivo) {
        //Caso se queira carregar o grafo a partir de um arquivo
        String os = System.getProperty("os.name");
        String caminhoArquivo = System.getProperty("user.dir");
        if (os.contains("Windows")) {
            caminhoArquivo += "\\src\\" + arquivo;
        } else {
            caminhoArquivo += "/src/" + arquivo;
        }
        return caminhoArquivo;
    }
}
