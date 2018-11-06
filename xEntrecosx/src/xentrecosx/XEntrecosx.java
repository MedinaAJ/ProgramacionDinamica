/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xentrecosx;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author 9alej
 */
public class XEntrecosx {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList valores = new ArrayList();
        /*
        
            APARTADO A
        
        */
        double Linf = 0.0;
        double Lsup = Math.PI;
        
        double intervalo = Lsup - Linf;
        double resultado,y,yaux,x=Linf;
        double dentro = 0, fuera = 0;
        Random r = new Random();
        
        while(x <= Lsup){
            y = f(x);
            yaux = r.nextDouble();
            valores.add(yaux * intervalo);
            if(yaux<=y) dentro++;
            else fuera++;
            x+=0.001;
        }
        resultado = dentro/(dentro + fuera);
		System.out.println("Area :" + (f(Lsup)*Lsup)*resultado);
        
        System.out.println(" El area de la funcion es : " + resultado );
    
        /*
        
            APARTADO B
        
        */
        
        double[] intervaloConfianza = intervaloConfianza(valores);
        
        System.out.println("El intervalo de confianza es : [" + intervaloConfianza[0] + " , " + intervaloConfianza[1] + " ]");
    }
    public static double[] intervaloConfianza(ArrayList valores) {//valores.length tamaño de la muestra
        double[] intervaloConfianza = new double[2];
        double media = media(valores);
        double S = cuasiV(valores, media);
        intervaloConfianza[0] = media - 1.96 * S / Math.sqrt(valores.size());
        intervaloConfianza[1] = media + 1.96 * S / Math.sqrt(valores.size());
        return intervaloConfianza;
    }
    
    public static double media(ArrayList valores) {
        double media = 0;
        for (int x = 0; x < valores.size(); x++) {
            media = media + (double) valores.get(x);
        }
        return media / valores.size();
    }
    
    public static double cuasiV(ArrayList valores, double media) {
        double S = 0;
        for (int x = 0; x < valores.size(); x++) {
            S = S + Math.pow((double) valores.get(x) - media, 2);
        }
        return Math.sqrt(S / (valores.size() - 1));
    }
    
    public static double f(double x){
        return x / Math.cos(x);
    }
}
