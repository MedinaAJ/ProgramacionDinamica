/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package divisores_dinamica;

import java.util.ArrayList;

/**
 *
 * @author 9alej
 */
public class Solucion {
    
    ArrayList<Numero> solucion;
    
    public Solucion(){
        solucion = fordward();
        boolean encontrada = false;
        for(int i = 0; i < solucion.size(); i++){
            if(solucion.get(i).getNumero() == 10){
                encontrada = true;
                System.out.println(solucion.get(i).toString());
            }
        }
        if(!encontrada) System.out.println("No ha sido encontrada ninguna solucion");
    }

    private ArrayList<Numero> fordward() {
        ArrayList<Numero> anchura = new ArrayList<Numero>();
        anchura.add(new Numero(1,null));
        int pos = 0;
        while(pos < anchura.size() && anchura.get(pos).getNumero()!=10){
            Numero actual = anchura.get(pos);
            ArrayList<Numero> ady = adyacentes(actual);
            for(int i = 0; i < ady.size(); i++){
                Numero nuevo = ady.get(i);
                if(!anchura.contains(nuevo))
                    anchura.add(nuevo);
            }
            pos++;
        }
        
        return anchura;
    }

    private ArrayList<Numero> adyacentes(Numero actual) {
        ArrayList<Numero> ady = new ArrayList<Numero>();
        for(int i = 2; i<=3; i++){
            if(i==2) ady.add(new Numero(actual.getNumero()*2, actual));
            else ady.add(new Numero(actual.getNumero()/3, actual));
        }
        return ady;
    }
}
