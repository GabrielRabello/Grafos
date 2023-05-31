package CaminhosMinimos;

import Grafos.Aresta;
import Grafos.GrafoLAdj;
import java.util.HashMap;
import java.util.Map;

public class CaminhoMinimoBase
{
    protected final GrafoLAdj G;
    protected final Map<Integer, Integer> antecessor;
    protected final Map<Integer, Integer> dEstimada;
    protected static final String VERTICE_ORIGEM_NAO_ESTA_NO_GRAFO = "Vértice de Origem Não Está no Grafo";
    protected static final String VERTICE_FORA_ATRIBUTO_ALGORITMO = "Vértice Fornecido Não Está Dentro de um Dos Atributos";

    public CaminhoMinimoBase(GrafoLAdj G)
    {
        this.G = G;
        this.antecessor = new HashMap<>();
        this.dEstimada = new HashMap<>();
        for(var v : this.G.vertices()) {
            dEstimada.put(v, Integer.MAX_VALUE);
            antecessor.put(v, null);
        }
    }

    public void inicializarFonteUnica(int verticeOrigem)
    {
        if(!G.hasVertice(verticeOrigem)) throw new IllegalArgumentException(VERTICE_ORIGEM_NAO_ESTA_NO_GRAFO);
        if(!dEstimada.containsKey(verticeOrigem)) throw new IllegalArgumentException(VERTICE_FORA_ATRIBUTO_ALGORITMO);
        dEstimada.put(verticeOrigem, 0);
    }

    public boolean relaxar(int origem, Aresta aresta)
    {
        if(!dEstimada.containsKey(origem)) throw new IllegalArgumentException(VERTICE_FORA_ATRIBUTO_ALGORITMO);
        if(!dEstimada.containsKey(aresta.destino)) throw new IllegalArgumentException(VERTICE_FORA_ATRIBUTO_ALGORITMO);
        if(!antecessor.containsKey(aresta.destino)) throw new IllegalArgumentException(VERTICE_FORA_ATRIBUTO_ALGORITMO);
        if(!this.temMenorCaminhoDisponivel(origem, aresta)) return false;

        dEstimada.put(aresta.destino, dEstimada.get(origem) + aresta.peso);
        antecessor.put(aresta.destino, origem);
        return true;
    }

    public boolean temMenorCaminhoDisponivel(int origem, Aresta aresta)
    {
        int dOrig = dEstimada.get(origem);
        int dDest = dEstimada.get(aresta.destino);
        return dOrig != Integer.MAX_VALUE && dDest > dOrig + aresta.peso;
    }

    public void printEstadoAtual()
    {
        for(var u : this.G.vertices()) {
            System.out.println(u + ": " + this.dEstimada.get(u));
        }
        System.out.println();
    }
}
