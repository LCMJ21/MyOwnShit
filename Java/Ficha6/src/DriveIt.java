
import java.util.*;
import java.util.stream.Collectors;

public class DriveIt {

    private  Map<String,Comparator<Veiculo>> comparators;
    private Map<String,Veiculo> deriveit_v;


    public DriveIt(){

        deriveit_v = new HashMap<>();
        comparators = new HashMap<>();
    }

    public Map<String, Veiculo> getDeriveit_v() { return new HashMap<>(deriveit_v) ; }

    public void setDeriveit_v(Map<String, Veiculo> deriveit_v) {
        this.deriveit_v = new HashMap<>(deriveit_v);
    }

    /**
     * Implementação dos exercicios da Ficha 1.
     */
    public boolean existeVeiculo(String cod){
        return this.deriveit_v.containsKey(cod);
    }

    public int quantos(){
        return this.deriveit_v.size();
    }

    public int quantos(String marca){
        return (int) this.deriveit_v.values().stream().filter(veiculo -> veiculo.getMarca().equals(marca)).count();
    }

    public Veiculo getVeiculo(String cod){
        return this.deriveit_v.values().stream().filter(veiculo -> veiculo.getMatricula().equals(cod)).collect(Collectors.toList()).get(0); }

    public void adiciona(Veiculo v){
            this.deriveit_v.put(v.getMatricula(), v.clone());
    }

    public List<Veiculo> getVeiculos(){
        return this.deriveit_v.values().stream().map(Veiculo::clone).collect(Collectors.toList());
    }

    public void adiciona(Set<Veiculo> vs){
        vs.forEach(this::adiciona);
    }

    public void registarAluguer(String codVeiculo, int numKms){
        this.getVeiculo(codVeiculo).addViagem(numKms);
    }

    public void classificarVeiculo(String cod, int classificacao){
        this.getVeiculo(cod).addClassificacao(classificacao);
    }

    public double custoRealKm(String cod){
        return this.getVeiculo(cod).custoRealKM();
    }

    public Set<Veiculo> ordenarVeiculosSet(){
        return deriveit_v.values().stream().map(Veiculo::clone).collect(Collectors.toCollection(TreeSet::new));
    }

    public List<Veiculo> ordenarVeiculosList(){
        return deriveit_v.values().stream().map(Veiculo::clone).sorted().collect(Collectors.toList());
    }

    public Set<Veiculo> ordenarVeiculosSetComp(Comparator<Veiculo> c){
        return deriveit_v.values().stream().map(Veiculo::clone).collect(Collectors.toCollection(() -> new TreeSet<>(c)));
    }

    public void guardarComparador(String name, Comparator<Veiculo> c){
        comparators.put(name,c);
    }

    public Iterator<Veiculo> ordenarVeiculo(String criterio){
        if (comparators.containsKey(criterio)){
            return ordenarVeiculosSetComp(comparators.get(criterio)).iterator();
        }
        else return null;
    }

    public List<BonificaKms> daoPontos(){
        return deriveit_v.values().stream()
                .filter(veiculo -> veiculo instanceof BonificaKms).map(veiculo -> (BonificaKms) veiculo.clone())
                .collect(Collectors.toList());
    }


}
