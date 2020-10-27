package konj;

public class Par {

    private int x,y;
    private Par roditelj = null;

    public Par(int x, int y, Par roditelj){
        this.x = x;
        this.y = y;
        this.roditelj = roditelj;
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public Par getRoditelj(){return roditelj;}
    @Override
    public String toString() {
        return "Par{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
