import CaminhosMinimos.BellmanFord;
import CaminhosMinimos.Dijkstra;
import CaminhosMinimos.FloydWarshall;
import ComponentesConexas.CompConexas;
import Grafos.GrafoLAdj;

import java.io.IOException;

/**
 * Discente: Gabriel Shimada Rabello    Docente: Roberto Samarone dos Santos Araújo
 * Matrícula: 202204940039              Disciplina: Grafos
 * Data: 31/05/2023                     Atividades 7 a 10
 */

public class Main {
    public static void main(String[] args) throws IOException
    {
        // Grafo exemplo do livro do Cormen et. al. (3ªed. brasileira), p. 475
        var grafo1 = new GrafoLAdj("grafoBellmanFord.txt", true, true);
        new BellmanFord(grafo1).executar(1, true);

        // Grafo exemplo do livro Cormen et. al. (3ªed. Brasileira), p.480
        var grafo2 = new GrafoLAdj("grafoDijkstra.txt", true, true);
        new Dijkstra(grafo2).executar(1);

        // Grafo exemplo do material de aula, slide p.48.
        var grafo3 = new GrafoLAdj("grafoFloydWarshall.txt", true, true);
        var res3 = new FloydWarshall(grafo3);
        res3.executar();
        res3.print();
        res3.printCaminho(2,3);

        // Grafo exemplo do livro do Cormen et. al. (3ªed. brasileira), p. 409
        var grafo4 = new GrafoLAdj("grafoCompConexas.txt", false, false);
        new CompConexas(grafo4);
    }
}