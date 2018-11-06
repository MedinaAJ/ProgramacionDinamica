/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backsparrow;

import java.util.Hashtable;

/**
 *
 * @author 9alej
 */
public class OptimizarViajes {
    
    private Hashtable<String, Integer> TesorosyPesos;
    private int peso_saco;
    private int n_viajes;
    private int n_viajesOpt;
    private int sol[];
    private int solOpt[];
    private int aux;
    private String key;
    private int peso_acum;
    
    OptimizarViajes(int peso_saco, Hashtable<String, Integer> TesorosyPesos) {
        /*
            Forma de la solucion:
        
            Sol es un array de las mismas dimensiones que los diferentes tesoros que pasamos
            por parametro al constructor, en el que cada numero almacenado representa el viaje
            en el que se llevó el objeto, y cada posicion + 1 el objeto que se lleva.
            
        */
        
        this.peso_acum = 0;
        this.n_viajesOpt = Integer.MAX_VALUE;
        this.n_viajes = 1;
        this.peso_saco = peso_saco;
        this.TesorosyPesos = TesorosyPesos;
        this.sol = InicializarVector(this.sol);
        this.solOpt = InicializarVector(this.solOpt);
        
        Backward(this.n_viajes, this.sol, this.n_viajesOpt, this.solOpt);
        
    }

    private int[] InicializarVector(int[] sol) {
        sol = new int[this.TesorosyPesos.size()];
        for(int i = 0; i< sol.length; i++)
            sol[i]=0;
        return sol;
    }

    private void Backward(int n_viajes, int[] sol, int n_viajesOpt, int[] solOpt) {
        if(esSolucion(sol)){
            if(esMejor(sol, solOpt))
                System.arraycopy(sol, 0, solOpt, 0, sol.length);
        }else{
            for (int i = 0; i < this.TesorosyPesos.size(); i++){
                aux = i + 1; 
                key = "O" + aux;
                if(esPosible(sol, TesorosyPesos.get(key), i, n_viajes)){
                    sol[i] = n_viajes; 
                }else{
                    n_viajes ++;
                }
                Backward(n_viajes, sol, n_viajesOpt, solOpt);
            }                
        }
    }

    private boolean esSolucion(int[] sol) {
        /*
            Tendremos una solucion cuando no quede ningun objeto en tierra, para ello el vector
            debe tener todos los numeros asignados, es decir, distintos a 0.
        */
        
        for(int i = 0; i < sol.length; i++)
            if(sol[i] == 0) return false;
        return true;
    }

    private boolean esMejor(int[] sol, int[] solOpt) {
        
        int viajesSol = 0;
        int viajesOpt = 0;
        
        for(int i = 0; i < sol.length; i++){
            if(sol[i] >= viajesSol) viajesSol = sol[i];
            if(solOpt[i] >= viajesOpt) viajesOpt = solOpt[i];
        }
        
        return viajesSol <= viajesOpt;
    }

    private boolean esPosible(int[] sol, int pesoObj, int i, int n_viajes) {
        int acum = 0;
        String clave;
        int index;
        
        if(sol[i]!=0) return false;
        else{
            for(int j = 0; j < sol.length ; j++){
                if(sol[i] == n_viajes){
                    index = i + 1;
                    clave = "O" + index;
                    acum += this.TesorosyPesos.get(clave);
                }
            }
            
            acum += pesoObj;
            return this.peso_saco <= acum;
        }
    }
    
}
