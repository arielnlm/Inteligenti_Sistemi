package mice;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    // [y][x], 1 ==  igrac, -1 == PC
    static Stanje tabla = new Stanje(new int[3][3], 1);
    static int igrac = 1;

    public static void main(String[] args){

        tabla.ispis();
        while(true){
            if(tabla.getIgracNaPotezu() == 1){
                int[][] tmp = tabla.getTabla();
                if(tabla.punaTabla()){
                    int a = scanner.nextInt();
                    int b = scanner.nextInt();
                    // Ako smo izabrali nase polje i ciljano polje je prazno
                    if(tmp[a/3][a%3] == 1 && tmp[b/3][b%3] == 0){
                        tmp[a/3][a%3] = 0;
                        tmp[b/3][b%3] = 1;
                        tabla = new Stanje(tmp, -tabla.getIgracNaPotezu());
                    }else{
                        System.out.println("Nevalidan potez!");
                    }
                }else{
                    int a = scanner.nextInt();
                    if(tmp[a/3][a%3] == 0){
                        tmp[a/3][a%3] = 1;
                        tabla = new Stanje(tmp, -tabla.getIgracNaPotezu());
                    }else{
                        System.out.println("Nevalidan potez!");
                    }
                }
            }else{
                tabla = nadjiNajboljeSledeceStanje(tabla, 6);
            }
            tabla.ispis();
        }


    }

    private static boolean validanPotez(int y, int x){
        if(tabla.getTabla()[y][x] != 2 && y >= 0 && y < 3 && x >= 0 && x<3)
            return true;
        else return false;
    }

    public static Stanje nadjiNajboljeSledeceStanje(Stanje trenutnoStanje, int maxDubina){
        Stanje najboljeStanje = null;
        int najboljiPotez = -trenutnoStanje.getIgracNaPotezu();
        List<Stanje> naredna = trenutnoStanje.sledecaStanja();
        for(Stanje st : naredna){
            int trenutni = minimnax(st, 1, maxDubina);
            if(trenutnoStanje.getIgracNaPotezu() == -1){
                if(najboljeStanje == null || trenutni<najboljiPotez)
                {
                    najboljiPotez = trenutni;
                    najboljeStanje = st;
                }
            }else{
                if(najboljeStanje == null || trenutni > najboljiPotez){
                    najboljeStanje = st;
                    najboljiPotez = trenutni;
                }
            }
        }
        return najboljeStanje;

    }

    private static int minimnax(Stanje tabla, int trenutnaDubina, int maxDubina){
        if(trenutnaDubina == maxDubina || tabla.proveriPobednika() != 0){
            return tabla.proveriPobednika();
        }
        int najboljiPotez = -tabla.getIgracNaPotezu();

        for(Stanje st : tabla.sledecaStanja()){
            int novi = minimnax(st, trenutnaDubina + 1, maxDubina);
            // Ako igra PC
            if(tabla.getIgracNaPotezu() == -1){
                if(novi < najboljiPotez)
                    najboljiPotez = novi;
            }else{
                if(novi > najboljiPotez)
                    najboljiPotez = novi;
            }
            if(najboljiPotez == tabla.getIgracNaPotezu())
                return najboljiPotez;
        }
        return najboljiPotez;
    }

}
