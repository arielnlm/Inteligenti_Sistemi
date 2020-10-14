import java.util.*;

public class MainAStar {

    static Tacka CILJ = new Tacka(3,3,5);
    static int smerx[] = {0,1,0,-1};
    static int smery[] = {1,0,-1,0};
    static boolean poseceni[][] = new boolean[20][20];


    public static void main(String[] args){

        int[][] planina = new int[][] {
                { 0, 2, 1, 0, 0 },
                { 2, 4, 2, 1, 0 },
                { 3, 2, 2, 3, 2 },
                { 1, 1, 3, 5, 4 },
                { 0, 1, 2, 4, 3 } };

        PriorityQueue<List<Tacka>> red = new PriorityQueue<>(new Comparator<List<Tacka>>() {
            @Override
            public int compare(List<Tacka> o1, List<Tacka> o2) {
                Tacka ol1 = o1.get(o1.size() - 1);
                Tacka ol2 = o2.get(o2.size() - 1);

                return Integer.compare(o1.size() - 1 + ol1.visinskaRazlika(CILJ) * ol1.manhattan(CILJ),
                        (o2.size() - 1 + ol2.visinskaRazlika(CILJ) * ol2.manhattan(CILJ)));
            }
        });
        red.add(Collections.singletonList(new Tacka(0,0,0)));
        poseceni[0][0] = true;

        while(!red.isEmpty()){
            List<Tacka> list = red.poll();
            Tacka tt = list.get(list.size() - 1);

            if(tt.equals(CILJ)){
                System.out.println("Cilj dostignut, put:");
                for(Tacka tz : list){
                    System.out.println(tz.toString());
                }
                return;
            }

            for(int i=0; i<4; i++){
                int tx,ty;
                tx = tt.getX() + smerx[i];
                ty = tt.getY() + smery[i];
                if(bezbedno(tx, ty) && !poseceni[ty][tx]){
                    List<Tacka> novaL = new ArrayList<Tacka>(list);
                    novaL.add(new Tacka(tx, ty, planina[ty][tx]));
                    red.add(novaL);
                }
            }
        }

    }
    static boolean bezbedno(int x, int y){
        return x>=0 && x<5 && y>=0 && y <5;
    }
}
