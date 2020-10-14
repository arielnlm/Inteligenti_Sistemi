using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Hanojske_Kule : MonoBehaviour
{
    Vector2[] sviCvorovi = new Vector2[50];
    int[] red = new int[50];
    int[] roditelji = new int[50];
    int[] poseceni = new int[50];
    int brojNapravljenih = 1;

    // Start is called before the first frame update
    void Start()
    {
        int prvi = 0, poslednji = 0, trenutni = 0, brojCvorova = 1;

        sviCvorovi[0] = new Vector2(0, 0);
        red[0] = 0;
        poseceni[0] = 1;
        roditelji[0] = -1;

        bool manja = true, ft = true;

        /* Ako ocemo DFS
            DFS(sviCvorovi[0], true, 0);
            ispis(brojNapravljenih - 1);
        */

        while (prvi <= poslednji) {
            trenutni = red[prvi];
            if (sviCvorovi[trenutni] == new Vector2(2, 2)) {
                Debug.Log("Zavrseno!");
                break;
            }
            if (manja) {
                for (int m = 0; m < 3; m++) {
                    bool posecen = false;
                    for (int k = 0; k <= poslednji; k++) {
                        if (sviCvorovi[trenutni].x == m || (sviCvorovi[k].x == m && sviCvorovi[k].y ==
                            sviCvorovi[trenutni].y)) {
                            posecen = true;
                            break;
                        }
                    }
                    if (!posecen) {
                        sviCvorovi[++poslednji] = new Vector2(m, sviCvorovi[trenutni].y);
                        red[poslednji] = poslednji;
                        roditelji[poslednji] = trenutni;
                    }
                }
            }
            else {
                for (int m = 0; m < 3; m++) {
                    bool posecen = false;
                    for (int k = 0; k <= poslednji; k++) {
                        if ((sviCvorovi[k].y == m && sviCvorovi[k].x ==
                            sviCvorovi[trenutni].x) ||
                            sviCvorovi[trenutni].y == m ||
                            sviCvorovi[trenutni].x == m
                            ) {
                            posecen = true;
                            break;
                        }
                    }
                    if (!posecen) {
                        sviCvorovi[++poslednji] = new Vector2(sviCvorovi[trenutni].x, m);
                        red[poslednji] = poslednji;
                        roditelji[poslednji] = trenutni;
                    }
                }
            }

            manja = !manja;
            prvi++;
        }
        ispis(trenutni);
        
    }

    void ispis(int index) {
        if(roditelji[index] != -1) {
            ispis(roditelji[index]);
        }
        Debug.Log(sviCvorovi[index]);
    }


    bool DFS(Vector2 Cvor, bool manja, int roditelj) {
        if(Cvor == new Vector2(2, 2)) {
            Debug.Log("Zavreno");
            //Debug.Log(Cvor);
            return true;
        }
        if (manja) {
            for (int m = 0; m <= 2; m++) {
                bool postoji = false;
                //Debug.Log("U manjem proveravam: " )
                for (int k=0; k<brojNapravljenih; k++) {
                    
                    if (Cvor.x == m || sviCvorovi[k] == new Vector2(m, Cvor.y)) 
                    {
                        postoji = true;
                        break;
                    }
                }
                if (!postoji) {
                    sviCvorovi[brojNapravljenih++] = new Vector2(m, Cvor.y);
                    roditelji[brojNapravljenih - 1] = roditelj;
                    if (DFS(sviCvorovi[brojNapravljenih - 1], false, brojNapravljenih - 1)) {
                        //Debug.Log(Cvor);
                        return true;
                    }
                }
            }
        }
        else {
            for (int m = 0; m <= 2; m++) {
                bool postoji = false;
                for (int k = 0; k < brojNapravljenih; k++) {

                    if (Cvor.y == m || sviCvorovi[k] == new Vector2(Cvor.x, m) || Cvor.x == m) {
                        postoji = true;
                        break;
                    }
                }
                if (!postoji) {
                    sviCvorovi[brojNapravljenih++] = new Vector2(Cvor.x, m);
                    roditelji[brojNapravljenih - 1] = roditelj;
                    if(DFS(sviCvorovi[brojNapravljenih - 1], true, brojNapravljenih - 1)) {
                        //Debug.Log(Cvor);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
