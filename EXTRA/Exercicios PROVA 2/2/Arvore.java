class No {
    public int elemento;
    public int quantidade;
    public No esq, dir;

    public No(int x){
        this.elemento = x;
        this.quantidade = 0;
        this.esq = this.dir = null;
    }
}

public class Arvore {
    private No raiz;
    public void inserir(int x) {
        raiz = inserir(x, raiz);
    }
    private No inserir(int x, No aux){
        if(aux == null) {
            aux = new No(x);
        }else if(aux.elemento == x) {
            aux.quantidade++;
        }else if(x > aux.elemento) {
            inserir(x, aux.dir);
        }else if(x < aux.elemento) {
            inserir(x, aux.esq);
        }
        return aux;
    }
}