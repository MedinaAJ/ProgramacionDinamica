/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diana_disenoalgoritmos;

/**
 *
 * @author 9alej
 */
public class Juego {
    private int puntos;
    private int[] zonas;
    private int[] sol;
    private int[] solOpt;
    
    public Juego(int[]zonas, int puntos){
        this.zonas = zonas;
        this.puntos = puntos;
        sol = new int[zonas.length];
        solOpt = new int[zonas.length];
        InicializarVectores(sol, solOpt);
        Backward(puntos, sol, solOpt);
        ImprimirSolucion(solOpt);
        solOpt = Fordward();
        ImprimirSolucion(solOpt);
        
    }

    private void InicializarVectores(int[] sol, int[] solOpt) {
        for(int i=0; i<sol.length;i++){
            sol[i]=0;
            solOpt[i]=0;
        }
    }

    private void Backward(int puntos, int[] sol, int[] solOpt) {
        if(puntos<=0){
            if(esMejor(sol,solOpt)){
                System.arraycopy(sol, 0, solOpt, 0, sol.length);
            }
        }else{
            for(int i = 0; i < zonas.length; i++){
                puntos -= zonas[i];
                sol[i]++;
                Backward(puntos,sol,solOpt);
                puntos += zonas[i];
                sol[i]--;
            }
        }
    }

    private boolean esMejor(int[] sol, int[] solOpt) {
        int n_dardos = 0;
        int n_dardosOpt = 0;
        int pts = 0;
        int ptsOpt = 0;
        
        for(int i = 0; i<sol.length; i++){
            n_dardos += sol[i];
            n_dardosOpt += solOpt[i];
            pts += sol[i]*zonas[i];
            ptsOpt += solOpt[i]*zonas[i];
        }
        
        if(pts == puntos)
            return true;
        else return false;     
    }

    private void ImprimirSolucion(int[] solOpt) {
        System.out.println("Vector:");
        for(int i=0; i<sol.length;i++){
            System.out.println(solOpt[i]);
        }
    }

    private int[] Fordward() {
        sol = new int[zonas.length];
        int etapa = 0;
        
        while(sumaPuntos(sol) != puntos && etapa >= 0 && etapa <= sol.length){
            if(etapa == sol.length){
                etapa--;
                sol[etapa]++;
            }
            if(sumaPuntos(sol) <= puntos)
                etapa++;
            else{
                sol[etapa] = 0;
                etapa--;
                if(etapa >= 0)
                    sol[etapa]++;
            }
            
        }
        return sol;
    }

    private int sumaPuntos(int[] sol) {
        int suma = 0;
        for(int i=0; i<zonas.length; i++){
            suma += sol[i]*zonas[i];
        }
        return suma;
    }
}
