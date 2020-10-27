package triPosude.bfs;

import triPosude.Presipanje;
import triPosude.Stanje;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static List<Stanje> proverena = new ArrayList<>();

    public static void main(String[] args){
        int brojIteracija = 0;
        Queue<Stanje> red = new LinkedList<>();
        red.add(new Stanje(14, 0, 0, null));

        while(!red.isEmpty()){
            Stanje current = red.poll();
            proverena.add(current);
            brojIteracija++;
            //System.out.println("G" + current);
            if(current.getPrva() == 7 || current.getTreca() == 7){
                System.out.println("Uspesno! " + brojIteracija + " put:");
                while(current != null){
                    System.out.println(current);
                    current = current.getRoditelj();
                }
                break;
            }
            Stanje tmp = new Stanje(current.getPrva(), current.getDruga(),current.getTreca(),current);
            //System.out.println("----------------------------");
            for(int i=0; i<6; i++){
                Stanje tmp2 = tmp;
                tmp2 = i%2 == 0 ? tmp2.dopuni(i/2) : tmp;
                if(!proverena.contains(tmp2)){
                    red.add(tmp2);
                    proverena.add(tmp2);
                }

                tmp2 = tmp;
                tmp2 = i%2 == 0 ? tmp2.isprazni(i/2) : tmp;
                if(!proverena.contains(tmp2)){
                    red.add(tmp2);
                    proverena.add(tmp2);
                }

                tmp2 = tmp;
                tmp2 = tmp2.prespi(Presipanje.values()[i]);
                if(!proverena.contains(tmp2)){
                    proverena.add(tmp2);
                    red.add(tmp2);
                }

            }

        }
    }

}
