package casainteligente;

public class SmartSpeaker extends SmartDevice{

    public static final int MAX = 20;
    public static final int MIN = 0;

    int Volume;
    String Canal;

    /* Os quatro construtores da classe*/
    public SmartSpeaker(){
        super();
        this.Volume = 0;
        this.Canal = "";
    }

    public SmartSpeaker(String ID){
        super(ID);
        this.Volume = 0;
        this.Canal = "";
    }

    public SmartSpeaker(String ID, String Canal, int Volume){
        super(ID);
        if (Volume >= MIN && Volume <= MAX) this.Volume = Volume;
        else this.Volume = 0;
        this.Canal = Canal;
    }

    public SmartSpeaker(SmartSpeaker dev){
        super(dev);
        this.Volume = dev.Volume;
        this.Canal = dev.Canal;
    }

    /* Funções clone e equals */
    public SmartSpeaker clone(){
        return new SmartSpeaker(this);
    }

    public boolean equals(SmartSpeaker device){
        return super.equals(device);
    }

    /* Getters e Setters */
    public int getVolume() { return this.Volume; }
    public void volumeUp(){ if (this.Volume >= MIN && this.Volume < MAX) this.Volume++; }
    public void volumeDown(){ if (this.Volume > MIN && this.Volume <= MAX) this.Volume-=1; }
    public String getChannel(){ return this.Canal; }
    public void setChannel(String Canal){ this.Canal = Canal; }
}
