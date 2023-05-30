package CaminhosMinimos;

import Grafos.GrafoLAdj;

public class BellmanFord extends CaminhoMinimoBase {

    public BellmanFord(GrafoLAdj G) {
        super(G);
    }

    public boolean executar(int verticeOrigem, boolean printSteps) {
        super.inicializarFonteUnica(verticeOrigem);
        if(printSteps) {
            System.out.println("------BELLMAN-FORD-------\nEstado inicial:");
            System.out.println("{Vértice}: {Custo}");
            this.printEstadoAtual();
        }
        int loop = 1;
        int rodada = 1;
        for(int i = 0; i < super.G.totalVertices() - 1; i++) {
            if(printSteps) System.out.println("#RODADA " + rodada++);
            for(var u : super.G.vertices()) {
                var arestas = super.G.adj(u);
                arestas.forEach(aresta -> super.relaxar(u, aresta));
                if(printSteps) {
                    System.out.println("Loop " + loop++ + " (u = " + u + ")" + ":");
                    super.printEstadoAtual();
                }
            }
        }

        for(var u : super.G.vertices()) {
            var arestas = super.G.adj(u);
            for(var aresta : arestas) {
                if(super.temMenorCaminhoDisponivel(verticeOrigem, aresta)) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean executar(int verticeOrigem) {
        return this.executar(verticeOrigem, false);
    }
}
