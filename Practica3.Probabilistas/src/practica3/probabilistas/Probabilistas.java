/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3.probabilistas;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author 9alej
 */
public class Probabilistas {
    private int repeticiones;
    private double resultado;
    private Scanner sc = new Scanner(System.in);
    private Random r = new Random();
    
    public Probabilistas(){
        
    }
    
    public void apartadoA_ValorMedio(){
        
        /*
            Cuantas más veces caigan puntos en la esfera, mayor aproximacion del
            volumen de la esfera daré.
        */
        
        System.out.println("Por favor, introduzca el numero de repeticiones");
        repeticiones = sc.nextInt();
        int i = 0; double x,y,z;
        double valores = 0, dentro = 0, fuera = 0;
        
        /*
            Probabilidad de que caiga uno dentro o fuera
        */
        
        while(i <= repeticiones){
            x = r.nextDouble();
            y = r.nextDouble();
            z = (x*x) + (y*y);
            valores += z;
            i++;
        }
        double resultado = (valores * Math.PI/4)/repeticiones;
        System.out.println("El resultado obtenido es: " + resultado);
    }
    
    public void apartadoA_LanzamientoPuntos(){
       /*
            Cuantas más veces caigan puntos en la esfera, mayor aproximacion del
            volumen de la esfera daré.
        */
        
        System.out.println("Por favor, introduzca el numero de repeticiones");
        repeticiones = sc.nextInt();
        System.out.println("Por favor, introduzca el numero de valor");
        double resultado = sc.nextDouble();
        double fuera, dentro;
        fuera = dentro = 0.0;
        int i = 0;
        
        while (i<repeticiones){
            double x = Math.random();
            double y = Math.random();
            
            if (resultado <= Math.pow(x,2) + Math.pow(y,2)) dentro++;
            else fuera++;
            i++;
        }
        System.out.println("El resultado es : " + ((double)(dentro/i))*resultado);
       
    }
    
    public void apartadoB(){
        System.out.println("Por favor, introduzca el numero de repeticiones");
        double radio = sc.nextDouble();
        double fuera, dentro;
        fuera = dentro = 0.0;
        int i = 0;

        while (i < repeticiones) {
            double x = Math.random() * (radio);
            double y = Math.random() * (radio);
            double z = Math.random() * (radio);

            if (Math.sqrt((x*x) + (y*y) + (z*z)) <= radio) {
                dentro++;
            } else {
                fuera++;
            }
            i++;
        }
        
        double resultado = (dentro / (dentro + fuera));
        System.out.println("El resultado obtenido es: " + resultado*8);
    }
}
