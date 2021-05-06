package Ficha2;

import java.util.Arrays;
import java.util.Scanner;

public class E5Main {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        E5 f = new E5();
        System.out.println("Intruduza a alinea:");
        int p = sc.nextInt();
        String [] arrayStrings = {"edgar","zé dos cromos marcio","zé dos bigodes","zé dos bigodes","cabeça de azeite","edgar"};

        switch (p) {
            case 1 -> {
                System.out.println("Intruduza o numero de strings:");
                int len = sc.nextInt();
                String [] arrayS = new String[len];
                System.out.println("Intruduza as Strings:");
                sc.nextLine();
                for(int i = 0;i < len;i++){
                    arrayS[i] = sc.nextLine();
                }
                System.out.println(Arrays.toString(arrayS));

            }
            case 2 -> {
                System.out.println(f.maiorString(arrayStrings));
            }
            case 3 -> {
                String [] res = f.maisComuns(arrayStrings);
                System.out.println(Arrays.toString(res));
            }
            case 4 -> {
                sc.nextLine();
                String nova = sc.nextLine();
                System.out.println((f.quantasVezes(arrayStrings,nova)));
            }
            default -> System.out.println("Não existe");
        }
    }
}
