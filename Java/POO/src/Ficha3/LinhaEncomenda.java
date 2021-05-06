package Ficha3;

import java.util.Queue;

public class LinhaEncomenda {

    private String referencia;
    private String descricao;
    private double preco;
    private int quantidade;
    private double imposto;
    private double desconto;

    public LinhaEncomenda(String referencia, String descricao, double preco, int quantidade, double imposto, double desconto){
        this.referencia = referencia;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.imposto = imposto;
        this.desconto = desconto;
    }

    public String getReferencia(){
        return referencia;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public double calculaValorLinhaEnc(){
        return (preco + preco*imposto/100 - preco*desconto/100) * quantidade;
    }

    public double calculaValorDesconto(){
        return quantidade*preco*desconto/100;
    }

}
