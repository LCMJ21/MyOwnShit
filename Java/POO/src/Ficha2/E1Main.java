package Ficha2;

import Ficha1.Exercicios1.Ficha;

import java.util.Scanner;
import java.util.Arrays;

public class E1Main {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        E1 f = new E1();
        System.out.println("Intruduza a alinea:");
        int p = sc.nextInt();

        switch (p) {
            case 1 -> {
                System.out.print("Número de inteiros a ler: ");
                int tam = sc.nextInt();
                int [] array = new int [tam];
                for (int i = 0;i < array.length;i++) array[i] = sc.nextInt();
                int min = f.min(array);
                if (min == -1) System.out.println ("O array está vazio.");
                else System.out.println("Mínimo = " + array[min] + " na posição " + min ) ;
            }
            case 2 -> {
                System.out.print("Número de inteiros a ler: ");
                int tam = sc.nextInt();
                int [] array = new int [tam];
                for (int i = 0;i < array.length;i++) array[i] = sc.nextInt();
                System.out.print("Indice do limite inicial:");
                int incio = sc.nextInt();
                System.out.print("Indice do limite final:");
                int fim = sc.nextInt();
                int [] novo =  f.entre(array,incio,fim);
                for(int i:novo) System.out.print(i + " ");
            }
            case 3 -> {
                /*
                System.out.print("Número de inteiros a ler para o array1: ");
                int tam1 = sc.nextInt();
                int [] array1 = new int [tam1];
                for (int i = 0;i < array1.length;i++) array1[i] = sc.nextInt();
                System.out.print("Número de inteiros a ler para o array2: ");
                int tam2 = sc.nextInt();
                int [] array2 = new int [tam2];
                for (int i = 0;i < array2.length;i++) array2[i] = sc.nextInt();
                 */
                int [] array1 = {1,2,3,4,5,6,7,8,9,1,2};
                int [] array2 = {10,2,1,5,9,3};

                int [] novo =  f.comuns(array1,array2);
                for(int i:novo) System.out.print(i + " ");
            }
            default -> System.out.println("Não existe");
        }
    }
}
