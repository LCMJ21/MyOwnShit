package Ficha3;

enum MODE {
    ON,
    ECO,
    OFF
}

public class Lampada {

    private MODE m;
    private double consumo;
    private int gasto;
    private int gasto_total;

    public Lampada(int consumo){
        this.m = MODE.ON;
        this.consumo = consumo;
        this.gasto = this.gasto_total = 0;
    }

    public void time(int t){
        if (this.m == MODE.ON) gasto+=(consumo*t);
        else if(this.m == MODE.ECO) gasto+=((consumo/2)*t);
    }

    public void lampOFF(){
        this.m = MODE.OFF;
        gasto_total+=gasto;
        gasto = 0;
    }

    public void lampECO(){
        this.m = MODE.ECO;
        gasto_total+=gasto;
        gasto = 0;
    }

    public double totalConsumo(){
        return gasto +gasto_total;
    }

    public double periodoConsumo(){
        return gasto;
    }



}
