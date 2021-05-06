package Ficha1.Exercicios1;

import java.util.Scanner;

public class Teste {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        Ficha f = new Ficha();
        System.out.println("Intruduza a pergunta:");
        int p = scanner.nextInt();

        switch (p) {
            case 1 -> {
                System.out.println("Intruduza a data");
                System.out.print("dia:");
                int dia = scanner.nextInt();
                System.out.print("mes:");
                int mes = scanner.nextInt();
                System.out.print("ano:");
                int ano = scanner.nextInt();
                System.out.print(f.diadaSemana(ano, mes, dia));
            }
            case 2 -> {
                int[] d1 = {0, 0, 0, 0};
                int[] d2 = {0, 0, 0, 0};
                System.out.println("Intruduza a primeira data");
                System.out.print("dias:");
                d1[0] = scanner.nextInt();
                System.out.print("horas:");
                d1[1] = scanner.nextInt();
                System.out.print("minutos:");
                d1[2] = scanner.nextInt();
                System.out.print("segundos:");
                d1[3] = scanner.nextInt();
                System.out.println("Intruduza a segunda data");
                System.out.print("dias:");
                d2[0] = scanner.nextInt();
                System.out.print("horas:");
                d2[1] = scanner.nextInt();
                System.out.print("minutos:");
                d2[2] = scanner.nextInt();
                System.out.print("segundos:");
                d2[3] = scanner.nextInt();
                System.out.println(f.somaDatas(d1, d2));
            }
            case 3 -> {
                int[] cc = {0, 0, 0, 0};
                System.out.println("Intruduza o numero de classificações");
                int n = scanner.nextInt();
                while (n > 0) {
                    cc = f.somas(cc, scanner.nextInt());
                    n--;
                }
                System.out.println("O numero de classificações entre 0 e 5 é " + cc[0]);
                System.out.println("O numero de classificações entre 5 e 10 é " + cc[1]);
                System.out.println("O numero de classificações entre 10 e 15 é " + cc[2]);
                System.out.println("O numero de classificações entre 15 e 20 é " + cc[3]);
            }
            case 4 -> {


            }

            default -> System.out.println("Não existe");
        }
    }
}
