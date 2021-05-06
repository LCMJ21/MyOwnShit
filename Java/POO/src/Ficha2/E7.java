package Ficha2;

import java.util.Random;

public class E7 {
    Random r = new Random();

    public int [] geraChave(){
        int [] chave = new  int[7];
        int i;
        for(i = 0; i < 5; i++) chave[i] = r.nextInt(50);
        for(; i < 7; i++) chave[i] =  r.nextInt(9);
        return chave;
    }

    public void printWin(int [] chave){
        String esp = "";
        for(int i = 0;i < 50;i++){
            System.out.print(esp);
            for (int num:chave) System.out.print(num + " ");
            System.out.print("\n");
            esp += "  ";
        }
    }

}
