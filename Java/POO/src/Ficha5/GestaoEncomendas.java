package Ficha5;

import Ficha5.Encomenda;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;

public class GestaoEncomendas {

    private Map<Integer, Encomenda> encomendas;

    public GestaoEncomendas(){
        this.encomendas = new HashMap<>();
    }

    public GestaoEncomendas(GestaoEncomendas encs){
        this.encomendas = encs.getEncomendas();
    }

    public Map<Integer,Encomenda> getEncomendas(){
        return this.encomendas.values().stream().collect(Collectors.toMap(Encomenda::getEnc, Encomenda::clone));
    }

    public Set<Integer> todosCodigosEnc() {
        return this.encomendas.keySet();
    }

    public void addEncomenda(Encomenda enc){
        encomendas.put(enc.getEncomenda(),enc.clone());
    }

    public Encomenda getEncomenda(String codEnc){
    }




}
