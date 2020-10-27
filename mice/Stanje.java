package mice;

import java.util.ArrayList;
import java.util.List;

public class Stanje {
    private int[][] tabla = new int[3][3];
    private int igracNaPotezu;

    public Stanje(int[][] tabla, int igracNaPotezu){
        for (int i = 0; i < 3; i++) {
            System.arraycopy(tabla[i], 0, this.tabla[i], 0, 3);
        }
        this.igracNaPotezu = igracNaPotezu;
    }

    public int[][] getTabla(){
        return tabla;
    }

    public int getIgracNaPotezu(){return igracNaPotezu;}

    public void setPolje(int y, int x, int igrac){
        tabla[y][x] = igrac;
    }



    public int proveriPobednika(){
        int zbirovi[] = new int[8];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                zbirovi[i] += tabla[i][j];
                zbirovi[3 + j] += tabla[i][j];
                zbirovi[6] += (i == j) ? tabla[i][j] : 0;
                zbirovi[7] += (i + j == 2) ? tabla[i][j] : 0;
            }
        }
        for (int i = 0; i < 8; i++) {
            if (zbirovi[i] == 3) {
                return 1;
            }
            if (zbirovi[i] == -3) {
                return -1;
            }
        }
        return 0;
    }

    public List<Stanje> sledecaStanja(){
        List<Stanje> sledeca = new ArrayList<>();
        int[][] kopija = tabla.clone();
        int[] smerX = {-1, 0, 1, 0, 1, -1, -1, 1};
        int[] smerY = {0, 1, 0, -1, 1, -1, 1, -1};
        if(punaTabla()){
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    // Ako je mesto na koje pomerano prazno
                    if(kopija[i][j] == 0){
                        for(int p=0; p<8; p++){
                            int staroY = i + smerY[p];
                            int staroX = j + smerX[p];
                            // Ako potez nije dijagonalan, ako je dihagonalan i u okviru dijagonale
                            if(p < 4 || i+j == 2 || i-j == 0){
                                // Ako je u okviru granica i ako je do njega igrac koji je na potezu
                                if(staroY < 3 && staroX < 3 && staroY >= 0 && staroX >= 0 && kopija[staroY][staroX] == igracNaPotezu){
                                    kopija[staroY][staroX] = 0;
                                    kopija[i][j] = igracNaPotezu;
                                    sledeca.add(new Stanje(kopija, -igracNaPotezu));
                                    kopija[staroY][staroX] = igracNaPotezu;
                                    kopija[i][j] = 0;
                                }
                            }
                        }
                    }

                }
            }
        }else{
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(tabla[i][j] == 0){
                        kopija[i][j] = igracNaPotezu;
                        sledeca.add(new Stanje(kopija, -igracNaPotezu));
                        kopija[i][j] = 0;
                    }
                }
            }
        }

        return sledeca;

    }

    public boolean punaTabla(){
        int counter = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(tabla[i][j] != 0)
                    counter++;
            }
        }
        if(counter == 6) return true;
        else return false;
    }

    public void ispis(){
        char[] chtb = {'\u2665', '\u25cb', '\u2660'};
        char num1 = '\u2460';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabla[i][j] == 0) {
                    if (i == j && i == 0) {
                        System.out.printf("%2c ", '\u24ea');
                    } else {
                        System.out.printf("%2c ", num1 + 3 * i + j - 1);
                    }
                } else {
                    System.out.printf("%2c ", chtb[1 + tabla[i][j]]);
                }
                if (j < 2) {
                    System.out.print('\u2500');
                }
            }
            System.out.println();
            if (i == 0) {
                System.out.println(" \u2502 \\ \u2502 / \u2502");
            }
            if (i == 1) {
                System.out.println(" \u2502 / \u2502 \\ \u2502");
            }
        }
        System.out.println();
    }
}
