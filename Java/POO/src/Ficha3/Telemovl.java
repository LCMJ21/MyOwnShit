package Ficha3;

import java.util.Arrays;

public class Telemovl {

    private static final int MAXAPPS = 10;
    private static final int ARM = 100;
    private static final int ARM_MSG = 20;

    private String marca;
    private String modelo;
    private int display_X;
    private int display_Y;
    private int armMens;
    private int armFotosApps;
    private int armFotos;
    private int armApps;
    private int ocupado;
    private int nFotos;
    private int nApps;
    private String [] appsInts;

    public Telemovl(String marca,String modelo){
        this.marca = marca;
        this.modelo = modelo;
        this.display_X = 5;
        this.display_Y = 5;
        this.armMens = ARM_MSG;
        this.armFotosApps = ARM;
        this.armFotos = ARM/2;
        this.armApps = ARM/2;
        this.ocupado = 0;
        this.nFotos = 0;
        this.nApps = 0;
        this.appsInts = new String[MAXAPPS];
    }

    public boolean existeEspaco(int numeroBytes){
        return this.armFotosApps - this.ocupado >= numeroBytes;
    }

    public void instalaApp(String nome, int tamanho) {
        if (this.nApps >= MAXAPPS || this.armApps < tamanho) System.out.println("Não existe mais espaço ...");
        else {
            this.appsInts[this.nApps++] = nome;
            this.ocupado += tamanho;
            this.armApps -= tamanho;
            this.armFotosApps -= tamanho;
        }
    }

    public void recebeMsg(String msg){
        if (this.armMens > msg.length()) this.armMens -= msg.length();
        else System.out.println("Não existe mais espaço para esta mensagem ...");
    }

    public double tamMedioApps(){
        return  ((double) (ARM/2 - this.armApps) / this.nApps);
    }

    public void removeApp(String nome, int tamanho){
        int i;

        for (i = 0;i < MAXAPPS;i++) if (nome.equals(this.appsInts[i])) break;

        if (i != MAXAPPS) {
            System.arraycopy(this.appsInts, i + 1, this.appsInts, i, nApps - i);

            this.nApps--;
            this.ocupado -= tamanho;
            this.armApps += tamanho;
            this.armFotosApps += tamanho;
        }
    }

    public String toString() {
        String r = "Marca:" + marca + " Modelo:" + modelo + " Display:" + this.display_X + "x" + this.display_Y + "\n" ;
        r += "As apps que tem instalas são:" + Arrays.toString(this.appsInts);
        return r;
    }

}
