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
public class Menu {
    public Menu(){
        /*
            DATOS ENTRADA
        */
        
        Hashtable<String, Integer> TesorosyPesos = new Hashtable<String, Integer>();
        TesorosyPesos.put("O1",3);  TesorosyPesos.put("O2",2);  TesorosyPesos.put("O3",5);
        TesorosyPesos.put("O4",7);  TesorosyPesos.put("O5",11); TesorosyPesos.put("O6",12);
        TesorosyPesos.put("O7",4);  TesorosyPesos.put("O8",3);  TesorosyPesos.put("O9",2);
        TesorosyPesos.put("O10",5); TesorosyPesos.put("O11",6); TesorosyPesos.put("O12",8);
        TesorosyPesos.put("O13",9); TesorosyPesos.put("O14",15);
        
        int peso_saco = 20;
        
        OptimizarViajes op = new OptimizarViajes(peso_saco, TesorosyPesos);
    
    }
}
