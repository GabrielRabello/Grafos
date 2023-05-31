package Grafos;

public class Aresta
{
    public final int destino;
    public final int peso;
    public static final int PESO_PADRAO = 1;

    public Aresta(int dest, int peso)
    {
        this.destino = dest;
        this.peso = peso;
    }

    public Aresta(int dest) {
        this(dest, PESO_PADRAO);
    }
}
