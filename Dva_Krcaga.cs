using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Dva_Krcaga : MonoBehaviour
{
    Vector2[] sviCvorovi = new Vector2[50];
    int[] redIndexa = new int[50];
    int[] roditelji = new int[50];
    int brojNapravljenih = 1;
    Vector2 Prespi(Vector2 cvor, bool izManjeg) {
        Vector2 tmp = new Vector2();
        float kolicina;

        if (izManjeg) {
            kolicina = cvor.y + cvor.x;
            tmp.x = kolicina > 3 ? kolicina-3 : 0;
            tmp.y = kolicina > 3 ? 3 : kolicina;
        }
        else {
            kolicina = cvor.y + cvor.x;
            tmp.x = kolicina > 2 ? 2 : kolicina;
            tmp.y = kolicina > 2 ? kolicina-2 : 0;
        }

        return tmp;
    }
    Vector2 Dopuni(Vector2 v, bool manju) {
        Vector2 novaKolicina = v;
        if (manju) {
            novaKolicina.x = 2;
        }
        else novaKolicina.y = 3;
        return novaKolicina;
    }

    // Start is called before the first frame update
    void Start()
    {
        int prviIndex = 0, poslednjiIndex = 0, trenutniIndex = 0, brCvorova = 1;

        sviCvorovi[0] = new Vector2(0, 0);
        redIndexa[0] = 0;
        roditelji[0] = -1;

        // DFS varijanta
        // DFS(sviCvorovi[0], 0);
        // ispis(brojNapravljenih - 1);

        while (prviIndex <= poslednjiIndex) {

            trenutniIndex = redIndexa[prviIndex];

            if(sviCvorovi[trenutniIndex].x == 1) {
                Debug.Log("Zavresno!");
                break;
            }
            bool f1 = false, f2 = false, f3 = false, f4 = false;
            for(int k=0; k <= poslednjiIndex; k++) {
                if (!f1 && sviCvorovi[k] == Dopuni(sviCvorovi[trenutniIndex], true)) 
                    f1 = true;
                if (!f2 && sviCvorovi[k] == Dopuni(sviCvorovi[trenutniIndex], false)) {
                    f2 = true;
                }
                if (!f3 && sviCvorovi[k] == Prespi(sviCvorovi[trenutniIndex], true)) {
                    f3 = true;
                }
                if (!f4 && sviCvorovi[k] == Prespi(sviCvorovi[trenutniIndex], false)) {
                    f4 = true;
                }
            }
            if (!f1) {
                sviCvorovi[brCvorova++] = Dopuni(sviCvorovi[trenutniIndex], true);
                redIndexa[++poslednjiIndex] = poslednjiIndex;
                roditelji[poslednjiIndex] = trenutniIndex;
            }
            if (!f2) {
                sviCvorovi[brCvorova++] = Dopuni(sviCvorovi[trenutniIndex], false);
                redIndexa[++poslednjiIndex] = poslednjiIndex;
                roditelji[poslednjiIndex] = trenutniIndex;
            }
            if (!f3) {
                sviCvorovi[brCvorova++] = Prespi(sviCvorovi[trenutniIndex], true);
                redIndexa[++poslednjiIndex] = poslednjiIndex;
                roditelji[poslednjiIndex] = trenutniIndex;
            }
            if (!f4) {
                sviCvorovi[brCvorova++] = Prespi(sviCvorovi[trenutniIndex], false);
                redIndexa[++poslednjiIndex] = poslednjiIndex;
                roditelji[poslednjiIndex] = trenutniIndex;
            }
    
            prviIndex++;
        }
        ispis(trenutniIndex);
        
    }

    void ispis(int index) {
        if (roditelji[index] != -1) {
            ispis(roditelji[index]);
        }
        Debug.Log(sviCvorovi[index]);
    }

    bool DFS(Vector2 Cvor, int trenutniIndex) {

        if(Cvor.x == 1) {
            Debug.Log("Zavrseno!");
            //Debug.Log(Cvor);
            return true;
        }
        bool f1 = false , f2= false, f3 = false, f4 = false;
        for(int k=0; k< brojNapravljenih; k++) {
            if (!f1 && sviCvorovi[k] == Dopuni(Cvor, true))
                f1 = true;
            if (!f2 && sviCvorovi[k] == Dopuni(Cvor, false))
                f2 = true;
            if (!f3 && sviCvorovi[k] == Prespi(Cvor, true))
                f3 = true;
            if (!f4 && sviCvorovi[k] == Prespi(Cvor, false))
                f4 = true;
        }
        if (!f1) {
            sviCvorovi[brojNapravljenih++] = Dopuni(Cvor, true);
            roditelji[brojNapravljenih - 1] = trenutniIndex;
            if (DFS(sviCvorovi[brojNapravljenih - 1], trenutniIndex + 1)) {
                //Debug.Log(Cvor);
                return true;
            }
        }
        if (!f2) {
            sviCvorovi[brojNapravljenih++] = Dopuni(Cvor, false);
            roditelji[brojNapravljenih - 1] = trenutniIndex;
            if(DFS(sviCvorovi[brojNapravljenih - 1], trenutniIndex + 1)){
                //Debug.Log(Cvor);
                return true;
            }
        }
        if (!f3) {
            sviCvorovi[brojNapravljenih++] = Prespi(Cvor, true);
            roditelji[brojNapravljenih - 1] = trenutniIndex;
            if(DFS(sviCvorovi[brojNapravljenih - 1], brojNapravljenih - 1)){
                //Debug.Log(Cvor);
                return true;
            }
        }
        if (!f4) {
            sviCvorovi[brojNapravljenih++] = Prespi(Cvor, false);
            roditelji[brojNapravljenih - 1] = trenutniIndex;
            if(DFS(sviCvorovi[brojNapravljenih - 1], trenutniIndex + 1)){
                //Debug.Log(Cvor);
                return true;
            }
        }

        return false;
    }

}
