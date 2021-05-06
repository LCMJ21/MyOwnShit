package Ficha1.Exercicios1;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Ficha {

    public String diadaSemana(int ano,int mes,int dia){
        int temp = (ano - 1900)*365;
        temp += ((ano-1900)/4);
        if ((mes == 1 || mes == 2) && ano%4 == 0) temp--;
        if (mes > 11) temp+= 334;
        else if (mes > 10) temp += 304;
        else if (mes > 9) temp += 273;
        else if (mes > 8) temp += 243;
        else if (mes > 7) temp += 212;
        else if (mes > 6) temp += 181;
        else if (mes > 5) temp += 151;
        else if (mes > 4) temp += 120;
        else if (mes > 3) temp += 90;
        else if (mes > 2) temp += 59;
        else if (mes > 1) temp += 31;
        temp += dia;

        String resposta = "O dia da semana em " + dia + "/" + mes + "/" + ano + " é ";

        temp = (temp % 7);

        switch (temp) {
            case 0 -> resposta += "Domingo";
            case 1 -> resposta += "Segunda";
            case 2 -> resposta += "Terça";
            case 3 -> resposta += "Quarta";
            case 4 -> resposta += "Quinta";
            case 5 -> resposta += "Sexta";
            case 6 -> resposta += "Sabado";
        }

        return resposta;
    }

    public String somaDatas(int [] dt1,int [] dt2){
        int d = dt1[0] + dt2[0];
        int h = dt1[1] + dt2[1];
        int m = dt1[2] + dt2[2];
        int s = dt1[3] + dt2[3];

        while (s >= 60) {
            s -= 60;
            m++;
        }
        while (m >= 60) {
            m -= 60;
            h++;
        }
        while (h >= 24) {
            h -= 24;
            d++;
        }
        return "A soma das datas é: " + d + " dias " + h + " horas " + m + " minutos " + s + " segundos";
    }

    public int[] somas(int [] cc, int c){
        if (c >= 15 && c <= 20) cc[3]++;
        else if (c >= 10 && c < 15) cc[2]++;
        else if (c >= 5 && c < 10) cc[1]++;
        else if (c >= 0 && c < 5) cc[0]++;
        return cc;
    }
}
