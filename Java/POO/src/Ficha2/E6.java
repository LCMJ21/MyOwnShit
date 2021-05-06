package Ficha2;

public class E6 {

    int [][] somaMat(int [][] m1,int [][] m2){
        int [][] soma = new int[m1.length][m1[0].length];
        for (int i = 0;i < m1.length;i++){
            for (int k = 0; k < m1[0].length;k++) soma[i][k] = m1[i][k] + m2[i][k];
        }
        return soma;
    }

    boolean iguaisMat(int [][] m1,int [][] m2){
        int [][] soma = new int[m1.length][m1[0].length];
        for (int i = 0;i < m1.length;i++){
            for (int k = 0; k < m1[0].length;k++)  if(m1[i][k] != m2[i][k]) return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    int [][] inversaMat(int [][] m){
        int [][] inversa = new int[m.length][m[0].length];
        for (int i = 0;i < m.length;i++){
            for (int k = 0; k < m[0].length;k++) inversa[i][k] = -m[i][k];
        }
        return inversa;
    }


}
