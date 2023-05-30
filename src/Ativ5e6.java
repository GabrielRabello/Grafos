import Grafos.GrafoLAdj;
import Search.BFS;
import Search.DFS;

public class Ativ5e6 {
    private static void intro() {
        System.out.println("Discente: Gabriel Shimada Rabello\t\tDocente: Roberto Samarone dos Santos Araújo");
        System.out.println("Matrícula: 202204940039\t\tDisciplina: Grafos");
        System.out.println("Data: 09/05/2023\tAtividades 5 e 6");
        System.out.println("-------------------------------------");
    }
    public static void DFS() {
        intro();
        // Caso se queira carregar o grafo a partir de um arquivo
        //        String os = System.getProperty("os.name");
        //        String caminhoArquivo = System.getProperty("user.dir");
        //        if (os.contains("Windows")) {
        //            caminhoArquivo += "\\src\\grafoBellmanFord.txt";
        //        } else {
        //            caminhoArquivo += "/src/grafoBellmanFord.txt";
        //        }
        //        var grafo1 = new GrafoLAdj(caminhoArquivo, true);
        System.out.println("Exemplos dos grafos no livro do Cormen et. al. (3ªed. brasileira, p.434 para o BFS e p.440 para o DFS)");
        System.out.println("DFS no Grafo direcionado");
        var grafo1 = new GrafoLAdj(6, true);
        grafo1.addAresta(1, 2);
        grafo1.addAresta(1, 4);
        grafo1.addAresta(2, 3);
        grafo1.addAresta(3, 4);
        grafo1.addAresta(4, 2);
        grafo1.addAresta(5, 3);
        grafo1.addAresta(5, 6);
        grafo1.addAresta(6, 6);
        var dfs = new DFS(grafo1);
        System.out.println(dfs);
    }

    public static void BFS() {
        System.out.println("BFS no Grafo não-drecionado");
        var grafo2 = new GrafoLAdj(8);
        grafo2.addAresta(1, 2);
        grafo2.addAresta(2, 3);
        grafo2.addAresta(3, 4);
        grafo2.addAresta(4, 5);
        grafo2.addAresta(4, 6);
        grafo2.addAresta(5, 6);
        grafo2.addAresta(5, 7);
        grafo2.addAresta(6, 7);
        grafo2.addAresta(6, 8);
        grafo2.addAresta(7, 8);
        var bfs = new BFS(grafo2, 3);
        System.out.println(bfs);
    }






}
