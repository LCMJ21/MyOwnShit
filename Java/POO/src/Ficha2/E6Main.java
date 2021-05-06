package Ficha2;

import java.util.Arrays;
import java.util.Scanner;

public class E6Main {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        E6 f = new E6();
        System.out.println("Intruduza a alinea:");
        int p = sc.nextInt();
        int [][] mat1 = {{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5}};
        int [][] mat2 = {{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5}};

        switch (p) {
            case 1 -> {
                System.out.println("Intruduza numero de linhas:");
                int l = sc.nextInt();
                System.out.println("Intruduza numero de colunas:");
                int c = sc.nextInt();
                int [][] matriz = new int[l][c];
                for(int i = 0;i < l;i++){
                    for(int k = 0;k < c;k++){
                        matriz[i][k] = sc.nextInt();
                    }
                }
                for(int [] numeros:matriz){
                    System.out.print('\n');
                    System.out.print(Arrays.toString(numeros) + ' ');
                }
            }
            case 2 -> {
                int [][] mat = f.somaMat(mat1,mat2);

                for(int [] numeros:mat){
                    System.out.print('\n');
                    System.out.print(Arrays.toString(numeros) + ' ');
                }
            }
            case 3 -> {
                System.out.print(f.iguaisMat(mat1,mat2));
            }
            case 4 -> {
                int [][] inv = f.inversaMat(mat1);
                int [][] mat = f.somaMat(mat1, inv);


                for(int [] numeros:mat){
                    System.out.print('\n');
                    System.out.print(Arrays.toString(numeros) + ' ');
                }
            }

            default -> System.out.println("Não existe");
        }
    }
}
