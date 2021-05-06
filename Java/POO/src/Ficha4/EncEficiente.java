package Ficha4;

import java.util.ArrayList;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toCollection;

public class EncEficiente {

    private String nome_cliente;
    private String numero_fiscal_cliente;
    private String morada_cliente;
    private String numero_encomenda;
    private String data_encomenda;
    private ArrayList<LinhaEncomenda> linhas_encomenda;

    /** Constructor */
    public EncEficiente(String nome_cliente, String numero_fiscal_cliente, String morada_cliente
            , String numero_encomenda, String data_encomenda, ArrayList<LinhaEncomenda> linhas_encomenda) {
        this.nome_cliente = nome_cliente;
        this.numero_fiscal_cliente = numero_fiscal_cliente;
        this.morada_cliente = morada_cliente;
        this.numero_encomenda = numero_encomenda;
        this.data_encomenda = data_encomenda;
        this.linhas_encomenda = linhas_encomenda.stream().map(LinhaEncomenda ::clone).collect(toCollection(ArrayList :: new));
    }

    public EncEficiente(EncEficiente cop){
        this.nome_cliente = cop.nome_cliente;
        this.numero_fiscal_cliente = cop.numero_fiscal_cliente;
        this.morada_cliente = cop.morada_cliente;
        this.numero_encomenda = cop.numero_encomenda;
        this.data_encomenda = cop.data_encomenda;
        this.linhas_encomenda = cop.linhas_encomenda.stream().map(LinhaEncomenda ::clone).collect(toCollection(ArrayList :: new));
    }

    /** a) */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome do clinete: ").append(this.nome_cliente).append('\n');
        sb.append("Número fiscal do cliente: ").append(this.numero_fiscal_cliente).append('\n');
        sb.append("Morada do cliente: ").append(this.morada_cliente).append('\n');
        sb.append("Número da encomenda: ").append(this.numero_encomenda).append('\n');
        sb.append("Data da encomenda: ").append(this.data_encomenda).append('\n');
        sb.append("Linhas da encomenda: ").append('\n');
        this.linhas_encomenda.forEach(linha -> sb.append("--->").append(linha.toString()));
        return sb.toString();
    }


    public boolean equals(EncEficiente outro) {
        return this.linhas_encomenda.size() == outro.linhas_encomenda.size() &&
                this.linhas_encomenda.equals(outro.linhas_encomenda) &&
                this.nome_cliente.equals(outro.nome_cliente) &&
                this.numero_fiscal_cliente.equals(outro.numero_fiscal_cliente) &&
                this.morada_cliente.equals(outro.morada_cliente) &&
                this.numero_encomenda.equals(outro.numero_encomenda) &&
                this.data_encomenda.equals(outro.data_encomenda);
    }

    public EncEficiente clone(){
        return new EncEficiente(this);
    }



    /** I getters e setters */
    public String getNome_cliente() { return nome_cliente; }
    public void setNome_cliente(String nome_cliente) { this.nome_cliente = nome_cliente; }
    public String getNumero_fiscal_cliente() { return numero_fiscal_cliente; }
    public void setNumero_fiscal_cliente(String numero_fiscal_cliente) { this.numero_fiscal_cliente = numero_fiscal_cliente; }
    public String getMorada_cliente() { return morada_cliente; }
    public void setMorada_cliente(String morada_cliente) { this.morada_cliente = morada_cliente; }
    public String getNumero_encomenda() { return numero_encomenda; }
    public void setNumero_encomenda(String numero_encomenda) { this.numero_encomenda = numero_encomenda; }
    public String getData_encomenda() { return data_encomenda; }
    public void setData_encomenda(String data_encomenda) { this.data_encomenda = data_encomenda; }
    public ArrayList<LinhaEncomenda> getLinhas_encomenda() {
        return this.linhas_encomenda.stream().map(LinhaEncomenda ::clone).collect(toCollection(ArrayList :: new));
    }
    public void setLinhas_encomenda(ArrayList<LinhaEncomenda> linhas_encomenda) {
        this.linhas_encomenda = linhas_encomenda.stream().map(LinhaEncomenda ::clone).collect(toCollection(ArrayList :: new));
    }

    /** II */
    public double calculaValorTotal(){
        return linhas_encomenda.stream().mapToDouble(LinhaEncomenda :: calculaValorLinhaEnc).sum();
    }

    /** III */
    public double calculaValorDesconto(){
        return linhas_encomenda.stream().mapToDouble(LinhaEncomenda :: calculaValorDesconto).sum();
    }

    /** IV */
    public int numeroTotalProdutos(){
        return linhas_encomenda.stream().mapToInt(LinhaEncomenda :: getQuantidade).sum();
    }

    /** V */
    public boolean existeProdutoEncomenda(String refProduto){
        return linhas_encomenda.stream().anyMatch(ref -> ref.getReferencia().equals(refProduto));
    }

    /** VI */
    public void adicionaLinha(LinhaEncomenda linha){
        this.linhas_encomenda.add(new LinhaEncomenda(linha));
    }

    /** VII */
    public void removeProduto(String codProd){
        linhas_encomenda = linhas_encomenda.stream().filter(list -> !list.getReferencia().equals(codProd)).collect((toCollection(ArrayList :: new)));
    }
}
