/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escritores;

import java.util.ArrayList;

/**
 *
 * @author 9alej
 */
public class Solucion {
    private int[] ganancias_A = {10,8,5};
    private int[] ganancias_B = {8,7,6};
    private int[] ganancias_C = {12,6,2};

    private int[][] ganancias = {ganancias_A,ganancias_B,ganancias_C};
    
    private ArrayList<String[]> estados = new ArrayList<String[]>();
    private String[] sol = new String[3];
    private String[] escritores = {"A","B","C"};
    
    public Solucion(){
        generarEstados(0,sol);
        System.out.println("La mayor ganancia es colocando a los escritores en el orden: " + 
                 mayoresGanancias());
    }

    private void generarEstados(int x, String[] sol) {
        if(x == sol.length){
            String[] aux = new String[3];
            System.arraycopy(sol, 0, aux, 0, 3);
            ImprimirRes(sol);
            this.estados.add(aux);
        }else{
            for(int i = 0; i < this.escritores.length; i++){
                sol[x] = this.escritores[i];
                generarEstados(x+1, sol);
            }
        }
    }

    private String mayoresGanancias() {
        int ganancias = 0;
        int mayorGanancia = 0;
        String[] solucion = new String[3], mejor = new String[3];
        mejor = new String[3];
        int index = 0;
        while(index < this.estados.size()){
            ganancias = 0;
            solucion = estados.get(index);
            ImprimirRes(solucion);
            for(int i=0; i<solucion.length; i++){
                if(solucion[i].equals("A")) ganancias += ganancias_A[i];
                if(solucion[i].equals("B")) ganancias += ganancias_B[i];
                if(solucion[i].equals("C")) ganancias += ganancias_C[i];
            }
            if(ganancias > mayorGanancia){ mayorGanancia = ganancias;
            mejor = solucion;}
            index++;
        }
        
        
        String sol = "";
        for(int j = 0; j < mejor.length; j++){
            sol+= " " + mejor[j];
        }
        sol += " con una ganancia de " + mayorGanancia;
        
        return sol;
    }

    private void ImprimirRes(String[] sol) {
        for(int j = 0; j < sol.length; j++){
            System.out.print(sol[j]);
        }
        System.out.println("");
    }

    private void ImprimirArray() {
       for(int j = 0; j < estados.size(); j++){
            ImprimirRes(estados.get(j));
        }
    }
}
