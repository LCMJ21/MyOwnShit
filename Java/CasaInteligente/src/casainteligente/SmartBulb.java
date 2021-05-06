package casainteligente;

public class SmartBulb extends SmartDevice{

    public static final int COLD = 0;
    public static final int NEUTRAL = 1;
    public static final int WARM = 2;

    private int Tone;

    /* Os quatro construtores da classe*/
    public SmartBulb(){
        super();
        Tone = NEUTRAL;
    }

    public SmartBulb(String ID){
        super(ID);
        Tone = NEUTRAL;
    }

    public SmartBulb(String ID, int Tone){
        super(ID);
        this.Tone = Tone;
    }

    public SmartBulb(SmartBulb sb){
        super(sb);
        this.Tone = sb.Tone;
    }

    /* Funções clone e equals */
    public SmartBulb clone(){
        return new SmartBulb(this);
    }

    public boolean equals(SmartBulb device){
        return super.equals(device);
    }

    /* Getters e Setters */
    public int getTone() { return this.Tone; }
    public void setTone(int Tone){
        if (Tone > 0) this.Tone = WARM;
        else if (Tone < 0) this.Tone = COLD;
        else this.Tone = NEUTRAL;
    }

}
