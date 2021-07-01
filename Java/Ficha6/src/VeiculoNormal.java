import java.util.ArrayList;
import java.util.List;

public class VeiculoNormal extends Veiculo{
    public VeiculoNormal() {
        super();
    }

    public VeiculoNormal(String id, String marca, String modelo, int ano, double velMediaPorKm, double precoTeoricoPorKm, List<Integer> classificacoes) {
        super(id, marca, modelo, ano, velMediaPorKm, precoTeoricoPorKm, classificacoes);
    }

    @Override
    public double custoRealKM() {
        return this.getPrecokm() * 1.1;
    }

    public VeiculoNormal(String id, String marca, String modelo, int ano, double velMediaPorKm,
                         double precoTeoricoPorKm, List<Integer> classificacoes, int kms) {
        super(id, marca, modelo, ano, velMediaPorKm, precoTeoricoPorKm, classificacoes, kms);
    }

    public VeiculoNormal(String id, String marca, String modelo, int ano, double velMediaPorKm,
                         double precoTeoricoPorKm, ArrayList<Integer> classificacoes, int kms, int kmsUltimo) {
        super(id, marca, modelo, ano, velMediaPorKm, precoTeoricoPorKm, classificacoes, kms, kmsUltimo);
    }

    public VeiculoNormal(VeiculoNormal veiculo) {
        super(veiculo);
    }

    public double getCustoPorKm() {
        return custoRealKM();
    }

    public VeiculoNormal clone() {
        return new VeiculoNormal(this);
    }

    public String toString() {
        String sb = "Veículo Normal {\n" + super.toString() +
                "}";
        return sb;
    }
}