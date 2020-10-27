package triPosude;

import java.util.Objects;

public class Stanje {

    // 14 L
    private int prva;
    // 5 L
    private int druga;
    // 9 L
    private int treca;

    private Stanje roditelj;
    public Stanje(int prva, int druga, int treca, Stanje roditelj){
        this.prva = prva;
        this.druga = druga;
        this.treca = treca;
        this.roditelj = roditelj;
    }

    public Stanje prespi(Presipanje presip){

        Stanje novo = new Stanje(getPrva(),getDruga(),getTreca(), getRoditelj());
        int iz, u;

        switch(presip){
            case PD:
                u = novo.getPrva() + novo.getDruga() > 5 ? 5 : novo.getPrva() + novo.getDruga();
                iz = novo.getPrva() - (u- novo.getDruga());
                //System.out.print(presip.toString() + ": " + novo + ", novo: ");
                novo.setPrva(iz);
                novo.setDruga(u);
                //System.out.println(novo);
            break;
            case PT:
                u = novo.getPrva() + novo.getTreca() > 9 ? 9 : novo.getPrva() + novo.getTreca();
                iz = novo.getPrva() - (u- novo.getTreca());
                //System.out.print(presip.toString() + ": " + novo + ", novo: ");
                novo.setPrva(iz);
                novo.setTreca(u);
                //System.out.println(novo);
                break;
            case DP:
                u = novo.getDruga() + novo.getPrva() > 14 ? 14 : novo.getDruga() + novo.getPrva();
                iz = novo.getDruga() - (u- novo.getPrva());
                //System.out.print(presip.toString() + ": " + novo + ", novo: ");
                novo.setDruga(iz);
                novo.setPrva(u);
                //System.out.println(novo);
                break;
            case DT:
                u = novo.getDruga() + novo.getTreca() > 9 ? 9 : novo.getDruga() + novo.getTreca();
                iz = novo.getDruga() - (u- novo.getTreca());
                //System.out.print(presip.toString() + ": " + novo + ", novo: ");
                novo.setDruga(iz);
                novo.setTreca(u);
                //System.out.println(novo);
                break;
            case TP:
                u = novo.getPrva() + novo.getTreca() > 14 ? 14 : novo.getPrva() + novo.getTreca();
                iz = novo.getTreca() - (u- novo.getPrva());
                //System.out.print(presip.toString() + ": " + novo + ", novo: ");
                novo.setTreca(iz);
                novo.setPrva(u);
                //System.out.println(novo);
                break;
            case TD:
                u = novo.getDruga() + novo.getTreca() > 5 ? 5 : novo.getDruga() + novo.getTreca();
                iz = novo.getTreca() - (u- novo.getDruga());
                //System.out.print(presip.toString() + ": " + novo + ", novo: ");
                novo.setTreca(iz);
                novo.setDruga(u);
                //System.out.println(novo);
                break;
        }
        return novo;
    }
    public Stanje dopuni(int index){
        Stanje novo = new Stanje(getPrva(), getDruga(),getTreca(),getRoditelj());
        //System.out.print("Dopuni za " + novo);
        switch(index){
            case 0: novo.setPrva(14); /*System.out.println(", novo: " + novo )*/; return novo;
            case 1: novo.setDruga(5); /*System.out.println(", novo: " + novo )*/; return novo;
            case 2: novo.setTreca(9); /*System.out.println(", novo: " + novo )*/; return novo;
            default: return novo;
        }
    }
    public Stanje isprazni(int index){
        Stanje novo = new Stanje(getPrva(), getDruga(),getTreca(),getRoditelj());
        //System.out.print("Prazni za " + novo);
        switch(index){
            case 0: novo.setPrva(0); /*System.out.println(", novo: " + novo );*/ return novo;
            case 1: novo.setDruga(0); /*System.out.println(", novo: " + novo);*/ return novo;
            case 2: novo.setTreca(0); /*System.out.println(", novo: " + novo);*/ return novo;
            default: return novo;
        }
    }

    public int getPrva() {
        return prva;
    }

    public int getDruga() {
        return druga;
    }

    public int getTreca() {
        return treca;
    }

    public Stanje getRoditelj() {
        return roditelj;
    }

    private void setPrva(int prva) {
        this.prva = prva;
    }

    private void setDruga(int druga) {
        this.druga = druga;
    }

    private void setTreca(int treca) {
        this.treca = treca;
    }

    @Override
    public String toString() {
        return "(" + prva +
                "," + druga +
                "," + treca +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        Stanje stanje = (Stanje) o;
        return prva == stanje.getPrva() &&
                druga == stanje.getDruga() &&
                treca == stanje.getTreca();
    }

    @Override
    public int hashCode() {
        return Objects.hash(prva, druga, treca);
    }
}
