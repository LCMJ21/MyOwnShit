package Ficha1.Exercicios2;

public class Ficha {
    public double celsiusParaFarenheit(double graus){
        return graus * 9 / 5 + 32;
    }

    public int maximoNumeros(int a, int b){
        return Math.max(a, b);
    }

    public String criaDescricaoConta(String nome, int saldo){
        return "Nome: " + nome + " Saldo: " + saldo;
    }

    public double eurosParaLibras(double valor, double taxaConversao){
        return  valor * taxaConversao;
    }

    public String media(int a, int b){
        if (a > b) return "A media de " + a + " e " + b + " é " + (double) (a+b)/2 ;
        else return "A media de " + b + " e " + a + " é " + (double) (a+b)/2 ;
    }

    public long factorial(int num){
        long f = 1;
        for(int i = 1;i <= num ; i++) f *= i;
        return f;
    }

    public long tempoGasto(){
        long start = System.currentTimeMillis();
        factorial(5000);
        long end = System.currentTimeMillis();
        return end - start;

        /*
        LocalDateTime st = LocalDateTime.now();
        LocalDateTime ed = LocalDateTime.now();
        return (long)(ed.minus(st).getNano()/1000000);
         */

    }
}
