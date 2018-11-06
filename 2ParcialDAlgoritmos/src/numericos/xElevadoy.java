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
public class xElevadoy extends Funciones {

    int potencia;

    public xElevadoy(int p, double inf, double sup) {
        super(inf, sup);
        if (p < 0) {
            p = 0;
        }
        potencia = p;
    }

    public double f(double x) {
        if (vale(x)) {
            return Math.pow(x, potencia);
        } else {
            return 0;
        }
    }

    public double areaRieman(int intervalos) {
        double suma = 0.0;
        double inc = amplitud() / intervalos;
        double x = linf();
        while (x < lsup()) {
            suma = suma + f(x) * inc;
            x = x + inc;
        }
        return suma;
    }

    public double areaNumericoVM(int k) {
//area probabilista según el teoreama del valor medio. k ell nuemro de medidas
        double[] valores = new double[k];
//para calcular medias, cuasi varianzas e intervalos de confianza
        double suma = 0.0;
        for (int i = 0; i < k; i++) {
            double x = Math.random() * amplitud() + linf();//valor aleatorio de x entre [0,r]
            double y = f(x);//valor de y para esa x
            valores[i] = amplitud() * y;//Area=base, amplitud, por altura, y
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
            double y = Math.random() * f(lsup());
            if (y <= f(x)) {
                buenos++;
            }
        }
        IntervaloConfProporciones((double) buenos / k, k);
        return amplitud() * f(lsup()) * (double) buenos / k;
//buenos/k proporcion; amplitud()*f(lsup()) total del area
    }

    public double intervaloInfP() {
        return amplitud() * f(lsup()) * super.intervaloInfP();
    }

    public double intervaloSupP() {
        return amplitud() * f(lsup()) * super.intervaloSupP();
    }

    public double area() {//Area integrando
        return (Math.pow(lsup(), potencia + 1) - Math.pow(linf(), potencia + 1)) / (potencia + 1);
    }

    public String toString() {
        return "funcion=x^" + potencia + super.toString();
    }
}
