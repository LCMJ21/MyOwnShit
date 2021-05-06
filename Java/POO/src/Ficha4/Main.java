package Ficha4;

import java.util.ArrayList;

public class Main {
    public static void main(String [] args){
        // criamos o ArrayList com 3 Linhas ecomendas para efeitos de testes
        ArrayList<LinhaEncomenda> teste = new ArrayList<>();
        teste.add(new LinhaEncomenda("first","produto",10,20,0.2,0.1));
        teste.add(new LinhaEncomenda("second","produto",20,20,0.2,0.1));
        teste.add(new LinhaEncomenda("third","produto",30,20,0.2,0.1));

        //criamos a encomenda e outra com o clone
        EncEficiente test = new EncEficiente("Zé", "12345","Rua ...","1","31 de fevereiro",teste);
        EncEficiente newtest = (EncEficiente) ((EncEficiente)test).clone();
        System.out.println("As instancias sao iguais ?:" + test.equals(newtest));

        //Imprimimos os resultados de alguns metodos para verficar se estao coretos
        System.out.println("O preço total é:" + test.calculaValorTotal());
        System.out.println("O total do desconto é:" + test.calculaValorDesconto());
        System.out.println("O numero total de produtos é:" + test.numeroTotalProdutos());
        System.out.println("Existe produto com referencia new ?:" + test.existeProdutoEncomenda("new"));

        LinhaEncomenda nova = new LinhaEncomenda("new","produto",10,20,0.2,0.1);
        test.adicionaLinha(nova);
        System.out.println("Existe produto com referencia new ?:" + test.existeProdutoEncomenda("new"));
        System.out.println("As instancias sao iguais ?:" + test.equals(newtest));
        test.removeProduto("new");
        System.out.println("Existe produto com referencia new ?:" + test.existeProdutoEncomenda("new"));
        System.out.println();
        System.out.println(test.toString());

    }
}
