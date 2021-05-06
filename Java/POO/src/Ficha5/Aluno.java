package Ficha5;

/*********************************************************************************/
/** DISCLAIMER: Este cÃ³digo foi criado e alterado durante as aulas prÃ¡ticas      */
/** de POO. Representa uma soluÃ§Ã£o em construÃ§Ã£o, com base na matÃ©ria leccionada */
/** atÃ© ao momento da sua elaboraÃ§Ã£o, e resulta da discussÃ£o e experimentaÃ§Ã£o    */
/** durante as aulas. Como tal, nÃ£o deverÃ¡ ser visto como uma soluÃ§Ã£o canÃ³nica,  */
/** ou mesmo acabada. Ã‰ disponibilizado para auxiliar o processo de estudo.      */
/** Os alunos sÃ£o encorajados a testar adequadamente o cÃ³digo fornecido e a      */
/** procurar soluÃ§Ãµes alternativas, Ã  medida que forem adquirindo mais           */
/** conhecimentos de POO.                                                        */
/*********************************************************************************/

/**
 * Classe Aluno.
 * Classe que modela de forma muito simples a
 * informaÃ§Ã£o e comportamento relevante de um aluno.
 *
 * @author MaterialPOO
 * @version 20200216
 * @version 20210406
 */

public class Aluno implements Comparable<Aluno>{

    private String numero;
    private int nota;
    private String nome;
    private String curso;

    /**
     * Constructores para a classe Aluno
     */
    public Aluno() {
        this.numero = "";
        this.nota = 0;
        this.nome = "";
        this.curso = "";
    }

    public Aluno(String numero, int nota, String nome, String curso) {
        this.numero = numero;
        this.nota = nota;
        this.nome = nome;
        this.curso = curso;
    }

    public Aluno(Aluno umAluno) {
        this.numero = umAluno.getNumero();
        this.nota = umAluno.getNota();
        this.nome = umAluno.getNome();
        this.curso = umAluno.getCurso();
    }

    /**
     * MÃ©todo que devolve o nÃºmero de um aluno.
     *
     * @return String com o nÃºmero do aluno
     */
    public String getNumero() {
        return this.numero;
    }

    /**
     * MÃ©todo que devolve a nota de um aluno.
     *
     * @return int com o nÃºmero do aluno
     */
    public int getNota() {
        return this.nota;
    }

    /**
     * MÃ©todo que devolve o nome de um aluno.
     *
     * @return String com o nome do aluno
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * MÃ©todo que devolve o curso de um aluno.
     *
     * @return String com o nÃºmero do aluno
     */
    public String getCurso() {
        return this.curso;
    }


    public void setNota(int novaNota) {
        this.nota = novaNota;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * ImplementaÃ§Ã£o do mÃ©todo toString
     * comum na maioria das classes Java.
     *
     * @return     uma string com a informaÃ§Ã£o textual do objecto aluno
     */
    public String toString() {
        StringBuffer sb= new StringBuffer();

        sb.append("Numero: ");
        sb.append(this.numero+"\n");
        sb.append("Nome: ");
        sb.append(this.nome+"\n");
        sb.append("Curso: ");
        sb.append(this.curso+"\n");
        sb.append("Nota: ");
        sb.append(this.nota+"\n");

        return sb.toString();

        //embora obrigue a maior esforÃ§o de escrita, esta implementaÃ§Ã£o
        //Ã© mais eficiente que a normal
        //return("Numero:" + this.numero + "Nome:"+ this.nome + "Nota:" + this.nota);

    }

    /**
     * ImplementaÃ§Ã£o do mÃ©todo de igualdade entre dois Aluno
     * RedefiniÃ§Ã£o do mÃ©todo equals de Object.
     *
     * @param  umAluno   aluno que Ã© comparado com o receptor
     * @return      booleano true ou false
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if((o == null) || (this.getClass() != o.getClass()))
            return false;

        Aluno umAluno = (Aluno) o;
        return(this.nome.equals(umAluno.getNome()) && this.nota == umAluno.getNota()
                && this.numero.equals(umAluno.getNumero())
                && this.curso.equals(umAluno.getCurso()));
    }

    /**
     * ImplementaÃ§Ã£o do mÃ©todo de clonagem de um Aluno
     *
     * @return  objecto do tipo Aluno
     */

    public Aluno clone() {
        return new Aluno(this);
    }


    /**
     * ImplementaÃ§Ã£o de hashCode.
     * Exemplo para ser modificado, neste caso recorre ao hashCode
     * das String.
     */

    public int hashCode() {
        return this.nome.hashCode() + this.numero.hashCode() +
                this.curso.hashCode() + this.nota*7;
    }

    /**
     * ImplementaÃ§Ã£o da ordem natural.
     * Neste caso a ordem natural Ã© alfabeticamente crescente.
     *
     */

    public int compareTo(Aluno outroAluno) {
        return this.nome.compareTo(outroAluno.getNome());
    }


}
