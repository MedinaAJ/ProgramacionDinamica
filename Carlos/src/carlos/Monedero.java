/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carlos;

/**
 *
 * @author 9alej
 */
public class Monedero {
    
    private int tarjetas;
    private double dinero;
    
    
    public Monedero (int tarjetas, double dinero){
        this.dinero = dinero;
        this.tarjetas = tarjetas;
    }

    public int getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(int tarjetas) {
        this.tarjetas = tarjetas;
    }

    public double getDinero() {
        return dinero;
    }

    private void setDinero(double dinero) {
        this.dinero = dinero;
    }    
    
    public void ganarDinero(double dinero){
        setDinero(this.dinero + dinero);
    }
    
    public void perderDinero(double dinero){
        setDinero(this.dinero - dinero);
    }
    
}
