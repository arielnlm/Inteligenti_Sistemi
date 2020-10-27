package konj.bfs;

import konj.Par;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static int[][] tabla = new int[8][8];
    public static boolean[][] poseceni = new boolean[8][8];
    public static int[] smerX = {1, 1, 2, 2, -1, -1, -2, -2};
    public static int[] smerY = {2, -2, 1, -1, 2, -2, 1, -1};

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        System.out.println("Unesite pocetnu poziciju konja (x,y)");
        int fX = scanner.nextInt();
        int fY = scanner.nextInt();
        System.out.println("Unesite finalnu poziciju konja (x,y)");
        int pX = scanner.nextInt();
        int pY = scanner.nextInt();

        Queue<Par> red = new LinkedList<>();
        red.add(new Par(pX, pY, null));
        poseceni[pX][pY] = true;

        while(!red.isEmpty()){
            Par current = red.poll();
            poseceni[current.getX()][current.getY()] = true;

            if(current.getX() == fX && current.getY() == fY){
                System.out.println("Moze stici!");
                while(current != null){
                    System.out.println(current);
                    current = current.getRoditelj();
                }
                break;
            }
            for(int i=0; i<8; i++){
                int newX = current.getX() + smerX[i];
                int newY = current.getY() + smerY[i];
                Par test = new Par(newX, newY, current);
                if(jelValidan(newX, newY) && !poseceni[newX][newY] && !red.contains(test)){
                    red.add(test);
                }
            }
        }
    }

    public static boolean jelValidan(int x, int y){
        return x >= 0 && x<8 && y >= 0 && y<8;
    }

}
