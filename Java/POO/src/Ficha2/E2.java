package Ficha2;

public class E2 {

    public int [] maisAltas(int [][] notasAlunos){
        int [] maiores = {0,0,0,0,0};
        for(int [] notas:notasAlunos) {
            for(int i = 0;i < notas.length;i++){
                if(notas[i] > maiores[i]) maiores[i] = notas[i];
            }
        }
        return maiores;
    }

    public String maisbaixa(int [][] notasAlunos){
        int aluno = -1, disciplina = -1,baixa = Integer.MAX_VALUE;
        for(int k = 0;k < notasAlunos.length;k++) {
            for(int i = 0;i < notasAlunos[k].length;i++){
                if(notasAlunos[k][i] < baixa) {
                    aluno = k;
                    disciplina = i;
                    baixa = notasAlunos[k][i];
                }
            }
        }
        return "A nota mais baixa é do aluno " + aluno + " na disciplina " + disciplina + " de " + baixa + " valores.";
    }

    public int [] maiorQue(int [][] notasAlunos, int valor){
        int edgar = 0;
        int [] nova = new int[25];

        for (int[] notasAluno : notasAlunos) {
            for (int j : notasAluno) {
                if (j > valor) {
                    nova[edgar++] = j;
                }
            }
        }

        int[] res = new int[edgar];
        System.arraycopy(nova, 0, res, 0, edgar);
        return res;
    }

    public String matriz(int [][] notasAlunos){
        String res = "";
        for(int k = 0;k < notasAlunos.length;k++) {
            res += ("O aluno " + (k+1) +" tirou ");
            for(int i = 0;i < notasAlunos[k].length;i++){
                res += (notasAlunos[k][i] + " a " + (i+1) +", ");
            }
            res += ("\n");
        }
        return res;
    }
}
