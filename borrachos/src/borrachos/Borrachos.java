/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borrachos;

import java.util.Random;

/**
 *
 * @author 9alej
 */
public class Borrachos {

    /**
     * @param args the command line arguments
     */
    public static final int dimensiones = 4;
    public static void main(String[] args) {
        // TODO code application logic here
        /*  Eje y
         N  |      B2
            |
            |
            |
            | B1
          0 |_ _ _ _ _ _ Eje x 
        
        1 = posicion del borracho 1
        2 = posicion del borracho 2
        el resto = 0
        
        */
        Random r = new Random();
        
        int B1x, B1y, B2x, B2y;
        int[][] tablero = new int[dimensiones][dimensiones];
        tablero = inicializarTablero(tablero);
        
        boolean horizontal1, sentido1, horizontal2, sentido2;
        /*
        Si horizontal es true caminamos para la derecha o izquierda, si es false arriba o abajo
        Si sentido es true, incrementamos, si es false, decrementamos
        */
        B1x = r.nextInt(dimensiones);
        B1y = r.nextInt(dimensiones);
        int[] borracho1 = {B1x, B1y};
        
        B2x = r.nextInt(dimensiones);
        B2y = r.nextInt(dimensiones);
        int[] borracho2 = {B2x, B2y};
        
        System.out.println("Posicion inicial Borracho1 : " + borracho1[0] + ", " + borracho1[1]);
        System.out.println("Posicion inicial Borracho2 : " + borracho2[0] + ", " + borracho2[1]);
        tablero[B1x][B1y] = 1; tablero[B2x][B2y] = 2;
        int iteraciones = 0;
        int cota = 2000;
        
        while((borracho1[0] != borracho2[0] || borracho1[1] != borracho2[1]) && iteraciones < cota){
            ImprimirMatriz(tablero);
            
            tablero[borracho1[0]][borracho1[1]] = 0; 
            tablero[borracho2[0]][borracho2[1]] = 0;
            horizontal1 = r.nextBoolean();
            sentido1 = r.nextBoolean();
            horizontal2 = r.nextBoolean();
            sentido2 = r.nextBoolean();
            borracho1 = moverBorracho(borracho1, horizontal1, sentido1);
            borracho2 = moverBorracho(borracho2, horizontal2, sentido2);
            tablero[borracho1[0]][borracho1[1]] = 1;
            tablero[borracho2[0]][borracho2[1]] = 2; 
            iteraciones++;
        }
        
        System.out.println("N_Iteraciones : " + iteraciones);
        System.out.println("Posicion Borracho1 : " + borracho1[0] + ", " + borracho1[1]);
        System.out.println("Posicion Borracho2 : " + borracho2[0] + ", " + borracho2[1]);
    }

    private static int[][] inicializarTablero(int[][] tablero) {
        for(int i = 0; i<tablero.length; i++)
            for(int j = 0; j<tablero[0].length; j++){
                tablero[i][j] = 0;
            }
        return tablero;
    }

    private static int[] moverBorracho(int[] borracho, boolean horizontal, boolean sentido) {
        if(horizontal && sentido) borracho[0]++;
        else if(horizontal && !sentido) borracho[0]--;
        else if(!horizontal && sentido) borracho[1]++;
        else if(!horizontal && !sentido) borracho[1]--;
        
        if(borracho[0] < 0) borracho[0] = 0;
        if(borracho[0] >= dimensiones) borracho[0] = dimensiones-1;
        if(borracho[1] < 0) borracho[1] = 0;
        if(borracho[1] >= dimensiones) borracho[1] = dimensiones-1;
        return borracho;
    }

    private static void ImprimirMatriz(int[][] tablero) {
        for(int i = 0; i<tablero.length; i++){
            for(int j = 0; j<tablero[0].length; j++){
                System.out.print(tablero[i][j]);
            }
            System.out.println();
        }    
        System.out.println("");
    }
    
}
