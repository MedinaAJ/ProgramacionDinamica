/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menor_distancia_dinamica;

import java.util.ArrayList;

/**
 *
 * GRAFOS NO VALORADOS
 */
public class GrafoNV<X> extends Grafo<X,Boolean>{
    public GrafoNV(int n,boolean dirigido){
        super(n,dirigido);
    }
    public GrafoNV(int n){
        super(n);
    }
    public void nuevoArco(X origen,X destino){
        super.nuevoArco(origen,destino,true);
    }
    public Boolean peso(X origen,X destino){
       return perteneceArco(origen,destino);
    }
   
    
}