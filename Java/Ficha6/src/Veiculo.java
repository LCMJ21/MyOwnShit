/*********************************************************************************/
/** DISCLAIMER: Este cÃ³digo foi criado e alterado durante as aulas prÃ¡ticas      */
/** de POO. Representa uma soluÃ§Ã£o em construÃ§Ã£o, com base na matÃ©ria leccionada */
/** atÃ© ao momento da sua elaboraÃ§Ã£o, e resulta da discussÃ£o e experimentaÃ§Ã£o    */
/** durante as aulas. Como tal, nÃ£o deverÃ¡ ser visto como uma soluÃ§Ã£o canÃ³nica,  */
/** ou mesmo acabada. Ã‰ disponibilizado para auxiliar o processo de estudo.      */
/** Os alunos sÃ£o encorajados a testar adequadamente o cÃ³digo fornecido e a      */
/** procurar soluÃ§Ãµes alternativas, Ã  medida que forem adquirindo mais           */
/** conhecimentos de POO.                                                        */
/** *******************************************************************************/

import java.util.ArrayList;
import java.util.List;

/**
 * Classe VeÃ­culo, para utilizaÃ§Ã£o na Ficha 6.
 *
 * @author MaterialPOO
 * @version 20210420
 */

public abstract class Veiculo implements Comparable<Veiculo> {
    private String marca;
    private String modelo;
    private String matricula;
    private int ano;
    private double velociademedia;
    private double precokm;
    private ArrayList<Integer> classificacao;
    private int kms;
    private int kmsUltimo; // kms da Ãºltima viagem??

    /**
     * Construtores
     */


    public Veiculo() {
        this.marca = "";
        this.modelo = "";
        this.matricula = "";
        this.ano = 0;
        this.velociademedia = 0;
        this.precokm = 0;
        this.classificacao = new ArrayList<>();
        this.kms = 0;
        this.kmsUltimo = 0;
    }

    public Veiculo(String marca, String modelo, String matricula,
                   int ano, double velociademedia, double precokm,
                   ArrayList<Integer> classificacao,
                   int kms, int kmsUltimo) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.ano = ano;
        this.velociademedia = velociademedia;
        this.precokm = precokm;
        this.classificacao = new ArrayList<>(classificacao);
        this.kms = kms;
        this.kmsUltimo = kmsUltimo;
    }



    public Veiculo(Veiculo v){
        this.marca = v.getMarca();
        this.modelo = v.getModelo();
        this.matricula = v.getMatricula();
        this.ano = v.getAno();
        this.velociademedia = v.getVelociademedia();
        this.precokm = v.getPrecokm();
        this.classificacao = v.getClassificacao();
        this.kms = v.getKms();
        this.kmsUltimo = v.getKmsUltimo();

    }

    public Veiculo(String id, String marca, String modelo, int ano, double velMediaPorKm, double precoTeoricoPorKm, List<Integer> classificacoes) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.velociademedia = velMediaPorKm;
        this.precokm = precoTeoricoPorKm;
        this.classificacao = new ArrayList<>(classificacoes);
    }

    public Veiculo(String id, String marca, String modelo, int ano, double velMediaPorKm, double precoTeoricoPorKm, List<Integer> classificacoes, int kms) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.velociademedia = velMediaPorKm;
        this.precokm = precoTeoricoPorKm;
        this.classificacao = new ArrayList<>(classificacoes);
        this.kms = kms;
    }


    public String getMarca() {
        return marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public double getVelociademedia() {
        return velociademedia;
    }

    public double getPrecokm() {
        return precokm;
    }


    /**
     * Se a implementaÃ§Ã£o fosse:
     *   return this.classificaÃ§Ã£o;
     *
     *
     * No programa principal:
     *   List<Integer> l = v.getClassificacao();
     *
     *   l.clear(); --> acontecia que internamente o arraylist de classificaÃ§Ãµes
     *                  tb era "apagado". Isso Ã© uma quebra de encapsulamento.
     */

    public ArrayList<Integer> getClassificacao() {
        return new ArrayList<>(classificacao);
    }

    public int getKms() {
        return kms;
    }

    public int getKmsUltimo() {
        return kmsUltimo;
    }

    public void setVelociademedia(double velociademedia) {
        this.velociademedia = velociademedia;
    }

    public void setPrecokm(double precokm) {
        this.precokm = precokm;
    }

    public void addViagem(int nkms){
        this.kms += nkms;
        this.kmsUltimo = nkms;
    }


    /**
     * Esta implementaÃ§Ã£o poderÃ¡ ser alterada.
     * Neste momento considera-se que o custo Ã© 10% acima do custo
     * teÃ³rico. Poderia ser tambÃ©m funÃ§Ã£o dos kms realizados.
     */

    public abstract double custoRealKM();


    public int classificacao(){
        return (int) this.classificacao.stream().mapToInt(k->k.intValue()).average().getAsDouble();
    }

    public void addClassificacao(int v){
        this.classificacao.add(v);
    }

    public abstract Veiculo clone();



    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || ! o.getClass().equals(this.getClass())) return false;
        Veiculo v = (Veiculo) o;
        return  this.marca.equals(v.getMarca()) &&
                this.modelo.equals(v.getModelo())&&
                this.matricula.equals(v.getMatricula()) &&
                this.ano == v.getAno() &&
                this.velociademedia == v.getVelociademedia() &&
                this.precokm == v.getPrecokm() &&
                this.classificacao.equals(v.getClassificacao()) &&
                this.kms == v.getKms() &&
                this.kmsUltimo == v.getKmsUltimo() ;
    }

    /**
     * ImplementaÃ§Ã£o da ordem natural dos veÃ­culos
     */
    public int compareTo(Veiculo v) {
        if(this.marca.compareTo(v.getMarca()) == 0)
            return (this.modelo.compareTo(v.getModelo()));
        else
            return (this.marca.compareTo(v.getMarca()));
    }
}
