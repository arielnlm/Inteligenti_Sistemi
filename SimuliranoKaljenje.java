import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SimuliranoKaljenje {


    static Tacka CILJ = new Tacka(3,3,5);
    static int[] smerx = {0,1,0,-1};
    static int[] smery = {1,0,-1,0};
    static int[][] planina = new int[][] {
            { 0, 2, 1, 0, 0 },
            { 2, 4, 2, 1, 0 },
            { 3, 2, 2, 3, 2 },
            { 1, 1, 3, 5, 4 },
            { 0, 1, 2, 4, 3 }
    };

    static double e(double tNovo, int hNovo, int hStaro){
        return Math.exp((hNovo - hStaro) / tNovo);
    }
    static int heuristika(Tacka t){
        return t.manhattan(CILJ)* t.visinskaRazlika(CILJ);
    }
    static boolean bezbedno(int x, int y){
        return x>=0 && x<5 && y>=0 && y<5;
    }
    public static void main(String[] args){

        double T = 100; // Pocetna temperatura
        Random r = new Random();

        Tacka t = new Tacka(3,0, planina[0][3]);// tacka polaska
        while(T > 1e-6){

            System.out.println(t);
            int tx,ty,i;
            if(t.equals(CILJ)){
                System.out.println("Cilj dostignut, put:");
                return;
            }

            do{
                i = r.nextInt(4);
                tx = t.getX() + smerx[i];
                ty = t.getY() + smery[i];
            }while(!bezbedno(tx,ty));
            T *= 0.95;
            Tacka tn = new Tacka(tx, ty, planina[ty][tx]);
            if(heuristika(tn) < heuristika(t)){
                t = tn;
            }else {
                double p  = e(T, heuristika(tn), heuristika(t));
                double ran = r.nextDouble();
                if(ran <= p){
                    t = tn;
                }
            }
        }
    System.out.println("Cilj nije dostignut");
    }


}


