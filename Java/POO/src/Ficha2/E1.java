package Ficha2;

public class E1 {

    public int min(int [] array) {
        int pos = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                pos = i;
            }
        }
        return pos;
    }

    public int [] entre(int [] array,int st,int end) {
        int [] novo = new int [end-st-1];
        System.arraycopy(array,st+1,novo,0,end-st-1);
        return novo;
    }

    public int [] comuns(int [] array1,int [] array2) {
        int[] novo = new int[array1.length];
        int n = 0;
        boolean stop;

        for (int i : array1) {
            stop = Boolean.TRUE;
            for (int k = 0; k < array2.length && stop; k++) {
                if (i == array2[k]) {
                    novo[n++] = i;
                    stop = Boolean.FALSE;
                }
            }
        }
        int[] res = new int[n];
        System.arraycopy(novo, 0, res, 0, n);
        return res;
    }
}
