/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moneditas;

/**
 *
 * @author 9alej
 */
public class Juego {
    private int[]monedas = {5,3,1};
    private int [] sol;
    private int [] solOpt;
    private int cambio = 8;
    
    public Juego(){
        sol = new int[monedas.length];
        solOpt = new int[monedas.length];
        sol = Inicializar(sol);
        solOpt = Inicializar(solOpt);
        Backward(0,sol,solOpt);
        Imprimir(solOpt);
    }

    private int[] Inicializar(int[] sol) {
        for(int i = 0 ; i<sol.length; i++)
            sol[i]=0;
        return sol;
    }
    private void Imprimir(int[] sol) {
        for(int i = 0 ; i<sol.length; i++)
            System.out.print(sol[i]);
        System.out.println();
    }

    private void Backward(int etapa, int[] sol, int[] solOpt) {
        if(etapa == sol.length){
            if(esMejor(sol,solOpt)){
                System.arraycopy(sol, 0, solOpt, 0, sol.length);
                Imprimir(sol);
            }
        }else{
            for(int i = 0; i<monedas.length;i++){
                if(esPosible(sol, i, etapa)){
                    sol[etapa]++;
                    etapa++;
                    Backward(etapa, sol, solOpt);
                    Imprimir(sol);
                    etapa--;
                    sol[etapa]--;
                }
            }
        }
    }

    private boolean esMejor(int[] sol, int[] solOpt) {
        int n = 0;
        int n_opt = 0;
        for(int i = 0; i<sol.length;i++){
            n += sol[i];
            n_opt += solOpt[i];
        }
        return n>=n_opt;
    }

    private boolean esPosible(int[] sol, int index, int etapa) {
        int n = 0;

        for(int i = 0; i<etapa;i++){
            n += sol[i]*monedas[i];
        }
        n+=sol[etapa]*monedas[index];
        return n<=cambio;
    }
}
