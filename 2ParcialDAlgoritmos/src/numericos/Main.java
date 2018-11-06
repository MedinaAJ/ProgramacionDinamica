/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numericos;

/**
 *
 * @author 9alej
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Funciones c = new xElevadoy(2, 1, 3);
        int intervalos = 1000;
        System.out.println(""
                + "\nArea Rieman : " + c.areaRieman(intervalos)
                + "\nArea NumericoVM : " + c.areaNumericoVM(intervalos)
                + "\n\tIntervalo de confianza [" + c.intervaloInf() + ", " + c.intervaloSup() + "]"
                + "\nArea NumericoP : " + c.areaNumericoP(intervalos)
                + "\n\tIntervalo de confianza [" + c.intervaloInfP() + ", " + c.intervaloSupP() + "]"
                + "\nArea : " + c.area()
        );
    }
    
}
