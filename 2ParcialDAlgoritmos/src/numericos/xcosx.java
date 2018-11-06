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
public class xcosx extends Funciones {

    public xcosx(double inf, double sup) {
        super(inf, sup);
    }

    public double f(double x) {
        if (vale(x)) {
            return x * Math.cos(x);
        } else {
            return 0;
        }
    }

    public double areaRieman(int k) {
        return 0.0;
    }

    public double areaNumericoVM(int k) {//area probabilista según el teoreama del valor medio. k ell nuemro de medidas
        double[] valores = new double[k];//para calcular medias, cuasi varianzas e intervalos de confianza
        double suma = 0.0;
        for (int i = 0; i < k; i++) {
            double x = Math.random() * amplitud() + linf();//valor aleatorio de x entre [0,r]
            double y = Math.abs(f(x));//valor de y para esa x
            valores[i] = amplitud() * y;//Area=base amplitud por altura y
            suma = suma + valores[i];
        }
        intervaloConfianza(valores);
        return suma / k;
    }

    public double areaNumericoP(int k) {
//area probabilista lanzando k puntos A=casos favorables/casos posibles
        int buenos = 0;
        for (int n = 0; n < k; n++) {
            double x = Math.random() * amplitud() + linf();
            double y = Math.random() * lsup();
//la funcion x>=xcox por lo que lo que tomo el rectangulo como amplitud()*lsup
            if (y <= Math.abs(f(x))) {
                buenos++;
            }
        }
        IntervaloConfProporciones((double) buenos / k, k);
        return amplitud() * lsup() * (double) buenos / k;//buenos/k proporcion; amplitud()*f(lsup()) total del area
    }

    public double area() {
        //hacerla
        return 0.0;
    }

    public String toString() {
        return "funcion=xCosx" + super.toString();
    }

    public double intervaloInfP() {
        return amplitud() * lsup() * super.intervaloInfP();
    }

    public double intervaloSupP() {
        return amplitud() * lsup() * super.intervaloSupP();
    }

}
