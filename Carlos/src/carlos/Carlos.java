/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carlos;
import java.util.Scanner;
/**
 *
 * @author 9alej
 */
public class Carlos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String HolaMundo="Hola mundo.";
        System.out.println(HolaMundo);
        int n=5;
        int Suma;
        Suma=n+26;
        if (Suma==26){
            System.out.println(Suma);
        } else{
            System.out.println("Hola mundo: "+ Suma);
        }
        double it=0.5;
        while (it<25.3){
            it+=1.3;
        }
        for (int i=0; i<5;i++){
            System.out.println("El valor de i es: "+i);
        }
       
       int [] v= new int [5];
       int pos = 6;
       
       if (EsPosible(pos,v)){
           // 0 0 0 0 20
           v[pos]=20;
           
       }
       
       Menu();
       Scanner sc=new Scanner (System.in);
       
       Monedero cartera=new Monedero (3,15.75);
       
       double dineroganado;
       double dineroperdido;
        System.out.println("Introduce el dinero que vas a ganar.");
        dineroganado=sc.nextDouble();
        cartera.ganarDinero(dineroganado);
        System.out.println("Introduce el dinero que vas a perder.");
        dineroperdido=sc.nextDouble();
        cartera.perderDinero(dineroperdido);
        
        
    }
    private static boolean EsPosible (int i, int [] v){
        boolean posible; 
        
        if (i>=v.length)
            posible=false;
        else{
           posible=true;
        }
        return posible;
    }
    
    private static void Menu (){
        Scanner sc = new Scanner(System.in);
        double a,b;
        int op;
        System.out.println("Por favor, introduzca el primer numero.");
        a=sc.nextDouble();
        System.out.println("Por favor, introduzca el segundo numero.");
        b=sc.nextDouble();
        ImprimirMenu(true);
        op=sc.nextInt();
        double res;
        while(op!=5){
            switch(op){
                case 1:
                    res=Sumar(a,b);
                    break;
                case 2:
                    res=Restar(a,b);
                    break;
                case 3:
                    res=Multiplicar(a,b);
                    break;
                case 4:
                    res=Dividir(a,b);
                    if(res == 0){
                        System.out.println("Resultado erroneo");
                    }
                    break;
                case 5:
                    System.out.println("Adios");
                    res = 0;
                    break;
                default:
                    System.out.println("Error al introducir la opcion, vuelve a elegirla");
                    res = 0;
                    break;
            }
            if(res != 0)
                System.out.println("El resultado es: " + res);
            System.out.println("-----------------------------");
            ImprimirMenu(false);
            op = sc.nextInt();
        }
    }
    private static void ImprimirMenu(boolean primera_vez) {
        String pres="Por favor, elija una de las siguientes opciones: ";
        
        if (primera_vez){
            pres="Bienvenido al fantastico tutorial de MediWeed. " + pres;
        System.out.println(pres);
        }
        else{
            System.out.println(pres);
        }
        System.out.println("1. Sumar");
        System.out.println("2. Restar");
        System.out.println("3. Multiplicar");
        System.out.println("4. Dividir");
        System.out.println("5. Salir");
    }

    private static double Sumar(double a, double b) {
        double resultado;
        resultado=a+b;
        return resultado;
    }

    private static double Restar(double a, double b) {
        double resultado = a - b;
        return resultado;
    }

    private static double Multiplicar(double a, double b) {
        return a*b;
    }

    private static double Dividir(double a, double b) {
        double res;
        try{
            res = a/b;
        }catch(ArithmeticException e){
            System.out.println("No se puede dividir por cero" + e.getMessage());
            res = 0;
        }
        return res;
    }

}
