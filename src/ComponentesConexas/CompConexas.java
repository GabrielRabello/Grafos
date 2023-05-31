package ComponentesConexas;

import Grafos.GrafoLAdj;
import java.util.TreeMap;

public class CompConexas
{
    private final GrafoLAdj G;
    private final TreeMap<Integer, Integer> indiceMap;

    public CompConexas(GrafoLAdj G)
    {
        System.out.println("\n------COMPONENTES CONEXAS-------");
        this.G = G;
        this.indiceMap = G.gerarIndices();
        var visitado = new boolean[G.totalVertices()];

        for (var u : G.vertices()) {
            int index = indiceMap.get(u);
            if (!visitado[index]) {
                System.out.print("Componente Conexa: ");
                dfs(u, visitado);
                System.out.println();
            }
        }
    }

    private void dfs(int u, boolean[] visitado)
    {
        int index = indiceMap.get(u);
        visitado[index] = true;
        System.out.print(u + " ");
        for (var aresta : this.G.adj(u)) {
            int v = aresta.destino;
            index = indiceMap.get(v);
            if (!visitado[index]) {
                dfs(v, visitado);
            }
        }
    }
}
