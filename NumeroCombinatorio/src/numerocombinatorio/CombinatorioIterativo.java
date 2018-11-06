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
public class CombinatorioIterativo {
    public CombinatorioIterativo(int n, int k){
        CalcularCombinatorio(n,k);
    }

    private void CalcularCombinatorio(int n, int k) {
        double c;
        
        int factn = 1;
        int factk = 1;
        int factnk = 1;
        
        if(n==0) factn = 1;
        else{
            for(int i = n; i>0; i--){
                factn *= i;
            }
        }
        if(k==0) factk = 1;
        else{
            for(int j = k ; j>0; j--){
                factk *= j;
            }  
        }
        if((n-k)==0) factnk = 1;
        else{
            for(int z = (n-k); z>0; z--){
                factnk *= z; 
            }  
        }
        c = factn / (factk * factnk);
        System.out.println(c);
    }
}
