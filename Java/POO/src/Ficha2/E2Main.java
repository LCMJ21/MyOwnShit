package Ficha2;

import java.util.Scanner;

public class E2Main {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        E2 f = new E2();
        System.out.println("Intruduza a alinea:");
        int p = sc.nextInt();
        int [][] notasAlunos = {{1,2,10,4,5},{1,2,3,4,50},{1,2,3,4,5},{1,21,3,4,5},{10,2,3,4,0}};

        switch (p) {
            case 1 -> {
                for (int i = 0; i < notasAlunos.length; i++) {
                    System.out.print("Aluno " + (i+1) + ":");
                    for (int k = 0; k < notasAlunos[i].length; k++){
                        notasAlunos[i][k] = sc.nextInt();
                    }
                }
            }
            case 2 -> {
                System.out.println("Intruduza o numero da disciplina de 1 a 5:");
                int d = sc.nextInt();
                int total = 0;
                for (int [] aluno:notasAlunos) total += aluno[d-1];
                System.out.println("O numero total de valores da disciplina " + d + " é " + total + ".");
            }
            case 3 -> {
                System.out.println("Intruduza o numero do aluno 1 a 5:");
                int a = sc.nextInt();
                int total = 0;
                for (int nota:notasAlunos[a-1]) total += nota;
                System.out.println("O numero total de valores do aluno " + a + " é " + total + ".");
            }
            case 4 -> {
                System.out.println("Intruduza o numero da disciplina de 1 a 5:");
                int d = sc.nextInt();
                double total = 0;
                for (int [] aluno:notasAlunos) total += aluno[d-1];
                total /= 5;
                System.out.println("O media de valores da disciplina " + d + " é " + total + ".");
            }
            case 5 -> {
                int [] nova = f.maisAltas(notasAlunos);
                System.out.println("O notas mais altas de cada disciplina são respetivamene ");
                for(int nota:nova) System.out.print(nota + " ");
            }
            case 6 -> {
                String res = f.maisbaixa(notasAlunos);
                System.out.println(res);
            }
            case 7 -> {
                System.out.println("Intruduza o valor:");
                int valor = sc.nextInt();
                int [] nova = f.maiorQue(notasAlunos,valor);
                System.out.println("O notas mais altas que " + valor + " são:");
                for(int nota:nova) System.out.print(nota + " ");
            }
            case 8 -> {
                System.out.println(f.matriz(notasAlunos));
            }
            default -> System.out.println("Não existe");
        }
    }
}
