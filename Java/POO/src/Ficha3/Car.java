package Ficha3;

enum CAR_MODE {
    ON,
    OFF
}

public class Car {
    private CAR_MODE m;
    private String marca;
    private String modelo;
    private int ano;
    private double csm_100KM;
    private double totaisKM;
    private double media_csm;
    private double KMS_ult;
    private double media_csm_ult;
    private double reg_eng_1km;

    public Car(String marca, String modelo, int ano, int csm_100KM,int reg_eng_1km){
        this.m = CAR_MODE.OFF;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.csm_100KM = csm_100KM;
        this.reg_eng_1km = reg_eng_1km;
        this.totaisKM = 0;
        this.media_csm = 0;
        this.KMS_ult = 0;
        this.media_csm_ult = 0;
    }

    public void ligaCarro(){
        m = CAR_MODE.ON;
    }

    public void desligaCarro(){
        m = CAR_MODE.OFF;
        totaisKM += KMS_ult;
        media_csm += media_csm_ult;
        KMS_ult = 0;
        media_csm_ult = 0;
    }

    public void resetUltimaViagem(){
        this.desligaCarro();
        this.ligaCarro();
    }

    void avancaCarro(double metros, double velocidade){
        if (m==CAR_MODE.ON){
            KMS_ult += metros * 0.001;
            media_csm += ((velocidade * csm_100KM)/100);
        }
    }

    public void travaCarro(double metros){
        media_csm_ult -= (metros * 0.001* reg_eng_1km);
    }

    public String toSring(){
        return marca + " " + modelo + " " + ano + " KMS:" + totaisKM + " MEDIA CONSUMO:" + media_csm;
    }
}

