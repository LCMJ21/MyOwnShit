package Ficha1.Exercicios2;

import java.util.Scanner;

public class Teste {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        Ficha f = new Ficha();
        System.out.println("Intruduza a pergunta:");
        int p = scanner.nextInt();

        switch (p){
            case 1:
                System.out.println("Intruduza o valor de graus para converter a farenheit:");
                double celsius = scanner.nextDouble();
                double farenheit = f.celsiusParaFarenheit(celsius);
                System.out.println(celsius + " graus celsius corresponde a " + farenheit + " graus farenheit");
                break;
            case 2:
                System.out.println("Intruduza os dois valores :");
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int maior = f.maximoNumeros(a,b);
                System.out.println("Entre os numeros " + a +" e " + b + " o maior é " + maior);
                break;
            case 3:
                System.out.println("Intruduza o nome e o saldo :");
                String nome = scanner.nextLine();
                int saldo = scanner.nextInt();
                String stat = f.criaDescricaoConta(nome,saldo);
                System.out.println(stat);
                break;
            case 4:
                System.out.println("Intruduza o dinheiro em euros e a taxa de conversão :");
                double euros = scanner.nextDouble();
                double tc = scanner.nextDouble();
                double libras = f.eurosParaLibras(euros,tc);
                System.out.println(euros + "€ são " + libras + "£");
                break;
            case 5:
                System.out.println("Intruduza os dois valores :");
                int c = scanner.nextInt();
                int d = scanner.nextInt();
                String resp = f.media(c,d);
                System.out.println(resp);
                break;
            case 6:
                if (args.length >= 1){
                    System.out.println(f.factorial(Integer.parseInt(args[0])));
                }
                break;
            case 7:
                System.out.println("O tempo gasto foi:" + f.tempoGasto());
                break;
            default:
                System.out.println("Não existe");
        }
    }
}
