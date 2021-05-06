package Ficha3;

public class Triangulo {
    private int x1;
    private int x2;
    private int x3;
    private int y1;
    private int y2;
    private int y3;

    public Triangulo(int x1,int y1,int x2, int y2, int x3, int y3){
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    public int haltura(){
        int temp = Math.max(Math.abs(y1-y2),Math.abs(y1-y3));
        return Math.max(temp,Math.abs(y2-y3));
    }

    public double calculaPerimetroTriangulo(){
        double l1 = Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
        double l2 = Math.sqrt(Math.pow(x1-x3,2)+Math.pow(y1-y3,2));
        double l3 = Math.sqrt(Math.pow(x3-x2,2)+Math.pow(y3-y2,2));
        return l1 +l2 +l3;
    }
}
