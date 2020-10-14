public class Planinarenje {

    static int[][] planina = new int[][] {
            { 0, 2, 1, 0, 0 },
            { 2, 4, 2, 1, 0 },
            { 3, 2, 2, 3, 2 },
            { 1, 1, 3, 5, 4 },
            { 0, 1, 2, 4, 3 } };
    static boolean poseceni[][] = new boolean[20][20];
    static int smerx[] = {0,1,0,-1};
    static int smery[] = {1,0,-1,0};
    public static void main(String[] args){

        planinarenje_rek(0,0);


    }

    static boolean bezbedno(int x,int y){
        return x>=0 && x<5 && y>=0 && y<5;
    }

    private static int maks(){
        int max = 0;
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(max < planina[i][j])
                    max = planina[i][j];
            }
        }
        return max;

    }
    private static boolean planinarenje_rek(int x, int y){
        int tx, ty, maxx, maxy;
        boolean ima_jos = false;
        poseceni[y][x] = true;
        System.out.println("(" + x + "," + y + ")");
        if(planina[y][x] == maks()) {
            System.out.println("Cilj dostignut!");
            return true;
        }
        do{
            maxx = -1;
            maxy = -1;
            for(int i=0; i<4; i++){
                tx = x + smerx[i];
                ty = y + smery[i];
                if(bezbedno(tx, ty) && !poseceni[ty][tx]){
                    if(maxx  < 0 || planina[ty][tx] > planina[maxy][maxx]){
                        maxx = tx;
                        maxy = ty;
                        ima_jos = true;
                    }
                }
            }
            if(maxx >= 0){
                if(planinarenje_rek(maxx, maxy)){
                    return true;
                }
            }

        }while(ima_jos);

        return false;
    }

}



























