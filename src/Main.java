import java.io.IOException;
import CaminhosMinimos.BellmanFord;
import CaminhosMinimos.Dijkstra;
import CaminhosMinimos.FloydWarshall;
import Grafos.GrafoLAdj;
import Grafos.GrafoLMatriz;

/**
 * Discente: Gabriel Shimada Rabello    Docente: Roberto Samarone dos Santos Araújo
 * Matrícula: 202204940039              Disciplina: Grafos
 * Data: 09/05/2023                     Atividades 5 e 6
 */

public class Main {
    public static void main(String[] args) throws IOException {

//        var grafo1 = new GrafoLAdj("grafoBellmanFord.txt", true, true);
//        new BellmanFord(grafo1).executar(1, true);

//        var grafo2 = new GrafoLAdj("grafoDijkstra.txt", true, true);
//        new Dijkstra(grafo2).executar(1);
        var grafo3 = new GrafoLAdj(3, true);
        grafo3.addAresta(0,1,8);
        grafo3.addAresta(0,0,2);
        grafo3.addAresta(0,2,5);
        grafo3.addAresta(1,0,3);
        grafo3.addAresta(2,1,2);
        var res3 = new FloydWarshall(grafo3);
        res3.executar();
        res3.print();
        res3.printCaminho(1,2);
    }
}