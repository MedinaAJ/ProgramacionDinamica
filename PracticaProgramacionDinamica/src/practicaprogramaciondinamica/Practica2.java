
package practicaprogramaciondinamica;

import java.util.Scanner;

/**
 *
 * @author 9alej
 */
public class Practica2 {
    
    private int n;
    private Scanner s;
    
    public Practica2(){
        System.out.println("Elija el numero con el que se va a jugar");
        s = new Scanner(System.in);
        n = s.nextInt();
        JuegoPotencias jp = new JuegoPotencias(n);
    }
}
