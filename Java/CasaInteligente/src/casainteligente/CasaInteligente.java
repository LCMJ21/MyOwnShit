package casainteligente;

import java.util.HashMap;
import java.util.Map;

public class CasaInteligente {

    private String morada;
    private Map<String,SmartDevice> smartDeviceMap;
    private Map<String,Map<String,SmartDevice>> roomMap;

    /* Os três construtores da classe*/
    public CasaInteligente() {
        this.morada = "";
        this.smartDeviceMap = new HashMap<>();
        this.roomMap = new HashMap<>();
    }

    public CasaInteligente(String morada) {
        this.morada = morada;
        this.smartDeviceMap = new HashMap<>();
        this.roomMap = new HashMap<>();
    }

    public CasaInteligente(CasaInteligente casa) {
        this.morada = casa.morada;
        this.smartDeviceMap = new HashMap<>(casa.smartDeviceMap);
        this.roomMap = new HashMap<>(casa.roomMap);
    }

    /* Funções necessarias para os testes*/
    public void addDevice(SmartDevice dev) {
        smartDeviceMap.put(dev.getID(),dev.clone());
    }

    public boolean existsDevice(String ID) {
        return smartDeviceMap.keySet().stream().anyMatch(ref -> ref.equals(ID));
    }

    public SmartDevice getDevice(String ID) {
        return smartDeviceMap.get(ID);
    }

    public void setAllOn(boolean bool) {
        smartDeviceMap.values().forEach(s -> s.setOn(bool));
    }

    public void addRoom(String name){
        roomMap.put(name,new HashMap<>());
    }

    public boolean hasRoom(String name){
        return roomMap.keySet().stream().anyMatch(ref -> ref.equals(name));
    }

    public void addToRoom(String roomName, String sm){
        roomMap.get(roomName).put(sm,new SmartDevice(sm));
    }

    public boolean roomHasDevice(String roomName, String sm){
        if (roomMap.containsKey(roomName)) return roomMap.get(roomName).containsKey(sm);
        else return false;
    }
}