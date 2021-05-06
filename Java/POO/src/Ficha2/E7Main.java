package Ficha2;

import java.util.Arrays;
import java.util.Scanner;

public class E7Main {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        E7 f = new E7();
        int i,temp;
        int [] chave_pessoa = new int[7];
        int [] chave = f.geraChave();
        for(i = 0; i < 5; i++) {
            temp = 0;
            while (temp > 50 || temp < 1){
                System.out.println("Insira o " + (i+1) + "º numero:");
                temp = sc.nextInt();
            }
            chave_pessoa[i] = temp;
        }
        for(i = 0; i < 2; i++) {
            temp = 0;
            while (temp > 9 || temp < 1){
                System.out.println("Insira o " + (i+1) + "ª estrela:");
                temp = sc.nextInt();
            }
            chave_pessoa[i] = temp;
        }

        if (Arrays.equals(chave_pessoa,chave)){
            System.out.println("Vitoria");
            f.printWin(chave);
        }
        else {
            System.out.println("Chave errada ...");
            System.out.println(Arrays.toString(chave));
        }

    }
}
