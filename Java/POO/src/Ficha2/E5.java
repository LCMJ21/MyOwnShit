package Ficha2;

import java.util.Arrays;

public class E5 {


    public String maiorString(String [] array){
        String nova = array[0];
        for (String palavra:array) if (nova.length() < palavra.length()) nova = palavra;
        return  nova;
    }

    public String [] maisComuns(String [] array){
        String [] nova = new String[array.length];
        int i = 0;

        for (int k = 0;k < array.length;k++) {
            for (int j = k+1;j < array.length;j++) if (array[k].equals(array[j])) nova[i++] = array[k];
        }

        String [] res = new String[i];
        System.arraycopy(nova,0,res,0,i);
        return  res;
    }

    public int quantasVezes(String [] array, String th){
        int cc = 0;

        for (String nova:array) if (th.equals(nova)) cc++;

        return  cc;
    }


}
