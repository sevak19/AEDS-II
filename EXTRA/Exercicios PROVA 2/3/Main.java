class Matriz{
    CelulaMatriz inicio;
    int linha, coluna;
    CelulaDupla diagUnificada(){
        CelulaMatriz aux = inicio;
        CelulaDupla primeiro = new CelulaDupla();
        CelulaDupla fim = primeiro;

        while(aux.inf != null) {
            Celula tmp = aux.inicio;
            while(tmp.prox != null) {
                fim.prox = new CelulaDupla(tmp.elemento);
                fim.prox.ant = fim;
                fim = fim.prox;
                tmp = tmp.prox;
            }
            aux = aux.inf.dir;
        }

        return primeiro;
    }
}
class CelulaMatriz{
    CelulaMatriz esq, dir, inf, sup;
    Celula inicio, fim;
}
class Celula{
    int elemento;
    Celula prox;
}
class CelulaDupla{
    int elemento;
    CelulaDupla prox, ant;

    public CelulaDupla() {
        this.elemento = 0;
        this.prox = this.ant = null;
    }

    public CelulaDupla(int x) {
        this.elemento = x;
        this.prox = this.ant = null;
    }
}