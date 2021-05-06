package Ficha2;


import java.util.Scanner;
import java.util.Arrays;

public class E4Main {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        E4 f = new E4();
        System.out.println("Intruduza a alinea:");
        int p = sc.nextInt();
        int [] arrayTeste = {1,2,10,4,5,1,2,3,4,50,1,2,3,4,5,1,21,3,4,5,10,2,3,4,0};

        switch (p) {
            case 1 -> {
                f.ordena(arrayTeste);
                System.out.println(Arrays.toString(arrayTeste));
            }
            case 2 -> {
                System.out.println("Intruduza o elemento a procura:");
                int key = sc.nextInt();
                Arrays.sort(arrayTeste);
                System.out.println(Arrays.binarySearch(arrayTeste,key));
            }
            default -> System.out.println("Não existe");
        }
    }
}
