/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diana_disenoalgoritmos;


/**
 *
 * ENUNCIADO:
 * 
 * Se tiene una diana con una serie de zonas de distinto valor (por ejemplo, 10,25,50,75), y se quiere conseguir P puntos(siguiendo con el ejemplo, 
 * que sea P = 105 puntos) con el menor numero de dardos (en este caso 75+10+10+10 = 105 4 dardos). Si la puntuacion exacta no se pudiera alcanzar 
 * se devuelven el menor numero de dardos que mas se acerquen a dicha puntuacion sin pasarse(por ejemplo, si P=65 la solucion seria 50+10).
 * 
 */
public class Diana_DisenoAlgoritmos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] zonas = {10,25,50,75};
        int puntos = 105;
        Juego j = new Juego(zonas,puntos);
    }
    
}
