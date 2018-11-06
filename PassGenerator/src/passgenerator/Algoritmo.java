/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passgenerator;

import java.util.Hashtable;

/**
 *
 * @author Teresa
 */
public class Algoritmo {
    
    Hashtable<Integer, String> validos = new Hashtable<Integer, String>();
    String [] sol;
    String [] solOpt;
    
    public Algoritmo(){
        validos.put(1, "1"); validos.put(10, "a"); validos.put(19, "j"); validos.put(28, "s");
        validos.put(2, "2"); validos.put(11, "b"); validos.put(20,"k"); validos.put(29, "t");
        validos.put(3, "3"); validos.put(12, "c"); validos.put(21, "l"); validos.put(30, "u");
        validos.put(4, "4"); validos.put(13, "d"); validos.put(22, "m"); validos.put(31, "v");
        validos.put(5, "5"); validos.put(14, "e"); validos.put(23, "n"); validos.put(32, "w"); 
        validos.put(6, "6"); validos.put(15, "f"); validos.put(24, "o"); validos.put(33, "x");
        validos.put(7, "7"); validos.put(16, "g"); validos.put(25, "p"); validos.put(34, "y");
        validos.put(8, "8"); validos.put(17, "h"); validos.put(26, "q"); validos.put(35, "z");
        validos.put(9, "9"); validos.put(18, "i"); validos.put(27, "r"); 
        validos.put(0, "0");
        sol = new String[5];
        solOpt = new String[5];
        sol = InicializarVector(sol);
        solOpt = InicializarVector(solOpt);
        Backward(0, sol);
       
    }

    private String[] InicializarVector(String[] sol) {
        for(int i =0;i<sol.length;i++){
            sol[i] = "";
        }
        
        return sol;
    }

    private void Backward(int etapa, String[] sol) {
        if(etapa == sol.length){
           if(esSolucion(sol))
            ImprimirVector(sol);
        }else{
            for(int i = 0; i<validos.size();i++){
               if(esPosible(sol, i, etapa)){
                    sol[etapa] = "" + validos.get(i);
                    etapa++;
                    Backward(etapa,sol);
                    etapa--;
                    sol[etapa]="";
                }
            }
        }
    }
    
    private String[] Fordward(){
        String sol[] ={null,null,null,null,null};
        int etapa = 0;
		int index = 0;
		//validos.get(index)
        while(etapa<=sol.length && etapa >=0){
            if(esSolucion(sol)){
                etapa--;
				index++;
            }			
            if(esPosible(sol, index, etapa)){
                etapa++;
            }else{
                sol[etapa]= null;
				etapa--;
				if(etapa>=0){
					sol[etapa] = validos.get(index);
					index++;
				}
            }
        }
        
        return sol;
    }

    private boolean esPosible(String[] sol, int index, int etapa) {
        
        String aux = "";
        
        if(etapa == 0) return true;
        else{
            
                aux = sol[etapa-1];
                if(!aux.equals("c") && !aux.equals("l")) return true;
                else{
                    if(aux.equals("c") && validos.get(index).equals("h")) return false;
                    if(aux.equals("l") && validos.get(index).equals("l")) return false;
                }
            
        }
        return true;
    }

    private void ImprimirVector(String[] sol) {
        for(int i = 0; i<sol.length;i++){
            System.out.print(sol[i]);
        }
        System.out.println();
    }

    private boolean esSolucion(String[] sol) {
        boolean posible = true;
        for(int i = 0; i<sol.length;i++){
            if(sol[i].equals(null)){
                posible = false;
            }
        }
        return posible;
    }
}
