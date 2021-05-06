package Ficha3;

import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Intruduza a alinea:");
        int p = sc.nextInt();

        switch (p) {
            case 1 -> {
                Circulo c1 = new Circulo();
                Circulo c2 = new Circulo(3,4,5);
                Circulo c3 = new Circulo(c2);

                c3.alteraCentro(5,9);
                c3.setRaio(8);
                c1.print();
                c2.print();
                c3.print();
                System.out.println("Perim:" + c1.calculaPerimetro() + " Area:" + c1.calculaArea());
                System.out.println("Perim:" + c2.calculaPerimetro() + " Area:" + c2.calculaArea());
                System.out.println("Perim:" + c3.calculaPerimetro() + " Area:" + c3.calculaArea());
            }
            case 2 -> {
                Telemovl tl = new Telemovl("Ifuit","3Black");

                System.out.println(tl.existeEspaco(50));

                tl.recebeMsg("aaaaaaaaaaaaaaaaaaaaaaaaaa");

                tl.instalaApp("tt",20);
                tl.instalaApp("ff",40);
                tl.instalaApp("hh",5);
                tl.removeApp("ff",40);
                tl.instalaApp("jj",10);
                tl.removeApp("jj",10);

                System.out.println(tl.toString() + "\nO tamanho medio de apps é " + tl.tamMedioApps());
            }
            case 5 -> {
                Game g = new Game("SLB","SCP");

                g.startGame();

                g.goloVisitante();
                g.goloVisitado();
                g.goloVisitado();

                g.endGame();

                System.out.println(g.resultadoActual());
            }
            case 4 -> {
                Lampada l = new Lampada(10);

                l.time(5);
                l.lampOFF();
                l.time(5);
                l.lampECO();
                l.time(5);

                System.out.println("Total:" + l.totalConsumo() + " Agr:" + l.periodoConsumo());
            }
            case 6 -> {
                Car c = new Car("Renaut","Clio",1904,10,5);

                c.ligaCarro();
                c.avancaCarro(5000,200);
                c.resetUltimaViagem();
                c.avancaCarro(2500,200);
                c.travaCarro(1000);
                c.desligaCarro();
                System.out.println(c.toSring());
            }
            case 7 -> {
                LinhaEncomenda e = new LinhaEncomenda("PC","bom",20,10,10,5);
                System.out.println("Preco final:" + e.calculaValorLinhaEnc() + "€ com " + e.calculaValorDesconto() + "€ de desconto");
            }
            case 8 -> {
                Encomenda enco = new Encomenda("Jorge",295786);
                enco.adicionaLinha(new LinhaEncomenda("PC","bom",40,10,10,5));
                enco.adicionaLinha(new LinhaEncomenda("PC2","bom",20,10,10,5));
                enco.adicionaLinha(new LinhaEncomenda("PC3","bom",60,10,10,5));

                System.out.println("O valor total é:" + enco.calculaValorTotal() + "€ e com "
                        + enco.calculaValorDesconto() + "€ total de desconto"
                + " com um total de " + enco.numeroTotalProdutos() + " produtos");

                System.out.println(enco.existeProdutoEncomenda("PC2"));
                enco.removeProduto("PC2");
                System.out.println(enco.existeProdutoEncomenda("PC2"));


            }
            case 9 -> {
                Triangulo t = new Triangulo(1,2,3,2,2,4);

                System.out.println("A altura é:" + t.haltura());
                System.out.println("O perimetro é:" + t.calculaPerimetroTriangulo());

            }

        }





    }
}
