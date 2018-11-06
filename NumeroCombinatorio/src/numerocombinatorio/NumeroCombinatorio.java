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
public class NumeroCombinatorio {

    /**
     * @param args the command line arguments
     */
    /*
        C(n,k) = n!/(k!*(n-k)!)
    */
    public static void main(String[] args) {
        Menu();
    }

    private static void Menu() {
        CombinatorioIterativo c1 = new CombinatorioIterativo(5, 3);
        CombinatorioRecursivo c2 = new CombinatorioRecursivo(5, 3);
        CombinatorioPila c3 = new CombinatorioPila(5,3);
    }
    
}
