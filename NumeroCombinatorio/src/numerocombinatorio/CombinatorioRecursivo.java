/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numerocombinatorio;

/**
 *
 * @author 9alej
 */
public class CombinatorioRecursivo {    
    public CombinatorioRecursivo(int n, int k){
        double c;
        c = CalcularCombinatorio(n,k);
        System.out.println(c);
    }
    private int CalcularCombinatorio(int n, int k){
        int c;
        if(k==0 || k==n){
            c = 1;
        }else{
            c = CalcularCombinatorio((n-1),(k-1)) + CalcularCombinatorio((n-1),k);
        }
        return c;
    }
}
