package practice2nim;

import java.io.IOException;
import java.util.Scanner;
/**
 * @authors Carlos Cordoba - Alvaro Moreno
 */
public class Practice2Nim {

    public static void main(String[] args) throws IOException {
        System.out.println("Elija el numero con el que se va a jugar");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        new NimGame(n);
    }
    
}
