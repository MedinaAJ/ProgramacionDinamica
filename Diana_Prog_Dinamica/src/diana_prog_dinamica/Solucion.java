/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diana_prog_dinamica;

import java.util.ArrayList;

/**
 *
 * @author 9alej
 */
public class Solucion {
    
    private ArrayList<Objeto> solucion;
    private int[] zonas = {10, 25, 50, 75};
    private int puntos = 105;
    private Objeto mejorSol = null;
    
    public Solucion(boolean fordward){
        if(fordward){
            solucion = fordward();
            //for(int i = 0; i < solucion.size(); i++){ System.out.println(solucion.get(i).toStringSinCamino());}
            Objeto mejor = Mejor(solucion);
            System.out.println(mejor.toString());
        }
        else{
            this.solucion = new ArrayList<Objeto>();
            Objeto inicial = new Objeto(puntos, -1, 0, 0, null);
            this.solucion.add(inicial);
            Objeto fin = backward(inicial, this.solucion);
            System.out.println(fin.toString());
        }
        
    }

    private ArrayList<Objeto> fordward() {
        ArrayList<Objeto> anchura = new ArrayList<Objeto>();
        anchura.add(new Objeto(puntos, -1, 0, 0, null));
        int pos = 0;
        
        while(pos < anchura.size()){
            Objeto actual = anchura.get(pos);
            ArrayList<Objeto> ady = adyacentes(actual);
            for(int i = 0; i < ady.size(); i++){
                Objeto nuevo = ady.get(i);
                if(!anchura.contains(nuevo))
                    anchura.add(nuevo);
                else{
                    Objeto existente = anchura.get(anchura.indexOf(nuevo));
                    if(existente.getDardos_acum() > nuevo.getDardos_acum()){
                        existente.setDardos_tipo(nuevo.getDardos_tipo());
                        existente.setDardos_tipo_cantidad(nuevo.getDardos_tipo_cantidad());
                        existente.setDardos_acum(nuevo.getDardos_acum());
                        existente.setViene_de(nuevo.getViene_de());
                    }
                }
            }
            pos++;
        }
        
        return anchura;
    }

    private ArrayList<Objeto> adyacentes(Objeto actual) {
        ArrayList<Objeto> ady = new ArrayList<Objeto>();
        int tipo_dardo = actual.getDardos_tipo() + 1;
        if(tipo_dardo < zonas.length){
            int maximo = puntos/zonas[tipo_dardo];
            for(int i = 0; i <= maximo; i++){
                int nuevo_ptos_restantes = actual.getPtos_porHacer() - (zonas[tipo_dardo]*i);
                if(nuevo_ptos_restantes >= 0){//no se pasa
                    Objeto nuevo = new Objeto(nuevo_ptos_restantes, tipo_dardo, i, actual.getDardos_acum() + i, actual);
                    ady.add(nuevo);
                }
            }
        }
        
        return ady;
    }

    private Objeto Mejor(ArrayList<Objeto> solucion) {
        Objeto mejor = solucion.get(solucion.size()-1);
        for(int i = 0; i < solucion.size(); i++){
            if(solucion.get(i).getDardos_acum() <= mejor.getDardos_acum() && 
                    solucion.get(i).getPtos_porHacer() <= mejor.getPtos_porHacer())
                mejor = solucion.get(i);
        }
        
        return mejor;
    }

    private Objeto backward(Objeto actual, ArrayList<Objeto> calculados) {
        Objeto valor = null;
        if(calculados.contains(actual)){
            valor = calculados.get(calculados.indexOf(actual));
        }else{
            int tipo_dardo = actual.getDardos_tipo() + 1;
            if(tipo_dardo < zonas.length){
                int maximo = puntos/zonas[tipo_dardo];
                for(int i = 0; i <= maximo; i++){
                    int nuevos_ptos_restantes = actual.getPtos_porHacer() - (zonas[tipo_dardo]*i);
                    if(nuevos_ptos_restantes >= 0){
                        Objeto nuevo = new Objeto(nuevos_ptos_restantes, tipo_dardo, i, actual.getDardos_acum(), actual);
                        valor = backward(nuevo,calculados);
                        if(valor.getDardos_acum()<actual.getDardos_acum() || actual.getDardos_acum() == 0){
                            
                            
                            actual.setDardos_acum(valor.getDardos_acum()+i);
                            actual.setViene_de(valor.getViene_de());
                        }
                    }
                }//for
            }//System.out.println(actual.toStringSinCamino()); // ESTE PRINT MUESTRA LOS ESTADOS
            valor = actual;
            calculados.add(actual);
        }
        return valor;
    }
}
