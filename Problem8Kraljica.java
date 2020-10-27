import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Problem8Kraljica {

    static List<Tacka> dame = new ArrayList<>();
    static int[][] tabla = {
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0}
    };

    public static int heruistika(Tacka t){
        int counter = -1;
        for(Tacka d : dame){
            if( t.getX() == d.getX() ||
                 t.getY() == d.getY() ||
                 Math.abs(d.getY() - t.getY()) == Math.abs(d.getX() - t.getX())){
                //System.out.println("Overlap " + t + " and " + d);
                counter++;
            }

        }
        return counter;
    }
    public static int ukupnaHeuristika(){
        int ukupno = 0;
        for(Tacka t : dame){
            ukupno+= heruistika(t);
        }
        return  ukupno;
    }
    static double e(double temperatura, int hNovo, int hStaro){
        return Math.exp(-(hNovo - hStaro) / temperatura);
    }
    static boolean jelZauzeto(int x, int y){
        if(tabla[y][x] == 0)
            return false;
        return  true;

    }

    public static void main(String[] args){
        int trenutniBrojPreklapanja = 0;
        int brojac = 0;
        double T = 100;
        Random random = new Random();
        do{
            int x = random.nextInt(8);
            int y = random.nextInt(8);
            if(tabla[y][x] == 0){
                dame.add(new Tacka(x,y,1));
                tabla[y][x] = 1;
                brojac++;
            }
        }while(brojac < 8);

        for(Tacka t : dame){
            System.out.println(t);
            trenutniBrojPreklapanja += heruistika(t);
        }
        System.out.println("Trenutni br. preklapanja: " + trenutniBrojPreklapanja);

        while(brojac < 4000){

            T *= 0.95;
            if(ukupnaHeuristika() == 0){
                System.out.println("Reseno posle " + brojac + " iteracija, pozicije dama:");
                for(Tacka d : dame){
                    System.out.println(d);
                }
                return;
            }

            int rx,ry;
            do
            {
                rx = random.nextInt(8);
                ry = random.nextInt(8);
            }while(jelZauzeto(rx, ry));

            int indexDame = random.nextInt(8);


            Tacka tn = new Tacka(rx, ry, tabla[ry][rx]);

          //  System.out.println(heruistika(tn) + " - " + heruistika(dame.get(indexDame)));
            if(heruistika(tn) < heruistika(dame.get(indexDame))){
                tabla[tn.getY()][tn.getX()] = 1;
                tabla[dame.get(indexDame).getY()][dame.get(indexDame).getX()] = 0;
                dame.set(indexDame, tn);
            }else{
                double p = e(T, heruistika(tn), heruistika(dame.get(indexDame)));
                double ran = random.nextDouble();
                if(ran < p){
                    tabla[tn.getY()][tn.getX()] = 1;
                    tabla[dame.get(indexDame).getY()][dame.get(indexDame).getX()] = 0;
                    dame.set(indexDame, tn);
                }
            }

            brojac++;
        }

        System.out.println("Novi broj preklapanja je " + ukupnaHeuristika());

    }

}
