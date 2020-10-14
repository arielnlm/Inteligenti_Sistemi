using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BFS : MonoBehaviour
{
    Vector3[] sviCvorovi = new Vector3[100];
    int[] roditelji = new int[100];
    int[] red = new int[50];
    int broj_napravljenih_cvorova = 1;

    // Vraca da li smo vec bili u identicnoj poziciji
    private bool uporediCvorove(Vector3 cvor, float m, float l, float c) {
        return cvor.x == m && cvor.y == l && cvor.z == c;
    }
    // Start is called before the first frame update
    void Start()
    {
        int m, l, j=0, k, prvi = 0, poslednji = 0;

        sviCvorovi[0] = new Vector3(3, 3, 1);

        red[prvi] = 0;
        roditelji[0] = -1;

        while(prvi <= poslednji) {
            j = red[prvi];
            // U slucaju da smo prebacili sve misionare i ljudozdere na desnu obalu
            if(sviCvorovi[j].x == 0 && sviCvorovi[j].y == 0 && sviCvorovi[j].z == 0) {
                Debug.Log("Zavrseno!");
                break;
            }
            // Ako je camac na levoj obali
            if(sviCvorovi[j].z == 1) {
                for(m=0; m<=2 && m <= sviCvorovi[j].x; m++) {
                    for(l=0; l<=2 && l<= sviCvorovi[j].y; l++) {
                        bool posecen = false;
                        if(m + l <= 2 && m + l > 0 &&
                            (sviCvorovi[j].x - m == sviCvorovi[j].y - l ||
                            sviCvorovi[j].x - m == 0 ||
                            sviCvorovi[j].x - m == 3)) {
                            for (k = 0; k < broj_napravljenih_cvorova; k++) {
                                if (uporediCvorove(sviCvorovi[k], sviCvorovi[j].x - m, sviCvorovi[j].y - l, 0)) {
                                    posecen = true;
                                    break;
                                }
                            }
                            if (!posecen) {
                                sviCvorovi[broj_napravljenih_cvorova++] = new Vector3(sviCvorovi[j].x - m,
                                    sviCvorovi[j].y - l, 0);
                                red[++poslednji] = broj_napravljenih_cvorova - 1;
                                roditelji[broj_napravljenih_cvorova - 1] = j;
                            }
                        }
                        
                    }

                }
            }
            else {
                // 3 - sviCvorovi[j].x ustvari znaci da mogu da uzmem samo onliko misionara
                // Kolko ih ima na desnoj obali, ne mogu vise od toga
                for (m = 0; m <= 2 && m <= 3 - sviCvorovi[j].x; m++) {
                    for (l = 0; l <= 2 && l <= 3 - sviCvorovi[j].y; l++) {
                        bool posecen = false;
                        if (m + l <= 2 && m + l > 0 &&
                            (sviCvorovi[j].x + m == sviCvorovi[j].y + l ||
                            sviCvorovi[j].x + m == 0 ||
                            sviCvorovi[j].x + m == 3)) {
                            for (k = 0; k < broj_napravljenih_cvorova; k++) {
                                if (uporediCvorove(sviCvorovi[k], sviCvorovi[j].x + m, sviCvorovi[j].y + l, 1)) {
                                    posecen = true;
                                    break;
                                }
                            }
                            if (!posecen) {
                                sviCvorovi[broj_napravljenih_cvorova++] = new Vector3(sviCvorovi[j].x + m,
                                    sviCvorovi[j].y + l, 1);
                                red[++poslednji] = broj_napravljenih_cvorova - 1;
                                roditelji[broj_napravljenih_cvorova - 1] = j;
                                
                            }
                        }
                        
                    }

                }
            }
            prvi++;
        }

        while(roditelji[j] != -1) {
            Debug.Log(sviCvorovi[j] + " ----- " + (new Vector3(3,3,1) - sviCvorovi[j]));
            j = roditelji[j];
        }
        Debug.Log(sviCvorovi[j] + " ----- " + (new Vector3(3, 3, 1) - sviCvorovi[j]));
    }

    

    // Update is called once per frame
    void Update()
    {
        
    }
}
