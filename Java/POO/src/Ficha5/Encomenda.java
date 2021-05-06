package Ficha5;

public class Encomenda {

    private static final int MAXECOMENDAS = 10;

    private String nome;
    private int encomenda;
    private int nEncomendas;
    private LinhaEncomenda [] lista;

    public int getEncomenda() {
        return encomenda;
    }

    public void setEncomenda(int encomenda) {
        this.encomenda = encomenda;
    }

    public Encomenda(String nome, int encomenda){
        this.nome = nome;
        this.encomenda = encomenda;
        this.nEncomendas = 0;
        this.lista = new LinhaEncomenda[MAXECOMENDAS];
    }

    public Encomenda(Encomenda outra){
        this.nome = outra.nome;
        this.encomenda = outra.encomenda;
        this.nEncomendas = outra.nEncomendas;
        this.lista = outra.lista;
    }

    public Encomenda clone() {
        return new Encomenda(this);
    }

    public double calculaValorTotal(){
        int i = 0;
        double total = 0;
        for (;i<nEncomendas ;i++) total+=lista[i].calculaValorLinhaEnc();
        return total;
    }

    public double calculaValorDesconto(){
        int i = 0;
        double total = 0;
        for (;i<nEncomendas ;i++) total+=lista[i].calculaValorDesconto();
        return total;
    }

    public int numeroTotalProdutos(){
        int i = 0;
        int total = 0;
        for (;i<nEncomendas ;i++) total+=lista[i].getQuantidade();
        return total;
    }

    public boolean existeProdutoEncomenda(String refProduto) {
        int i = 0;
        for (;i<nEncomendas ;i++) if (refProduto.equals(lista[i].getReferencia())) return true;
        return false;
    }

    public void adicionaLinha(LinhaEncomenda linha) {
        if (nEncomendas < MAXECOMENDAS){
            lista[nEncomendas++] = linha;
        }
    }

    public void removeProduto(String codProd){
        int i;
        for (i = 0;i < MAXECOMENDAS;i++) if (codProd.equals(this.lista[i].getReferencia())) break;

        if (i != MAXECOMENDAS) {
            System.arraycopy(this.lista, i + 1, this.lista, i, nEncomendas - i);
            nEncomendas--;
        }
    }


    public Integer getEnc() {
        return encomenda;
    }
}
