package casainteligente;

public class SmartDevice {
    private final String ID;
    boolean On;

    /* Os quatros construtores da classe*/
    public SmartDevice(){
        this.ID = "";
        this.On = false;
    }

    public SmartDevice(String ID){
        this.ID = ID;
        this.On = false;
    }

    public SmartDevice(String ID, boolean On){
        this.ID = ID;
        this.On = On;
    }
    public SmartDevice(SmartDevice dev){
        this.ID = dev.ID;
        this.On = dev.On;
    }

    /* Funções clone e equals */
    public SmartDevice clone(){
        return new  SmartDevice(this);
    }

    public boolean equals(SmartDevice device){
        return this.ID.equals(device.getID());
    }

    /* Getters e Setters */
    public String getID(){ return this.ID; }
    public boolean getOn(){ return this.On; }
    public void setOn(boolean On) { this.On = On; }

}
