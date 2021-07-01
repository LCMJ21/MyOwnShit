import java.util.ArrayList;

public class VeiculoOcasiao extends Veiculo {
    private boolean isprom;

    public VeiculoOcasiao() {
        super();
        isprom = false;
    }

    public VeiculoOcasiao(String marca, String modelo, String matricula,
                   int ano, double velociademedia, double precokm,
                   ArrayList<Integer> classificacao,
                   int kms, int kmsUltimo) {
        super( marca, modelo, matricula, ano, velociademedia, precokm, classificacao, kms, kmsUltimo);
        isprom = false;
    }

    public VeiculoOcasiao(VeiculoOcasiao veiculoOcasiao){
        super(veiculoOcasiao);
        isprom = veiculoOcasiao.isprom;
    }

    @Override
    public double custoRealKM(){
        if (isprom) return this.getPrecokm() * 1.1 * 0.75;
        return this.getPrecokm() * 1.1;
    }

    public VeiculoOcasiao clone() {
        return new VeiculoOcasiao(this);
    }

    public boolean isIsprom() {
        return isprom;
    }

    public void setIsprom(boolean isprom) {
        this.isprom = isprom;
    }

    @Override
    public String toString() {
        return "VeiculoOcasiao{" +
                "isprom=" + isprom +
                '}';
    }
}
