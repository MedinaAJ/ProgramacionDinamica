/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lasvegas;

/**
 *
 * @author 9alej
 */
public class AjusteDeCadenas {

    public static int ajuste(String X, String Y) {
        X = comprueba(X);//quita lo que no son 0 o 1
        Y = comprueba(Y);
        int pos = -1;
        if (X.length() >= Y.length() && Y.length() > 0) {
            int y = convierteAentero(Y); //pasa de binario a decimal
            int p = primo(y);//p primo entre 2..y-1
            System.out.println("X:" + X + " long=" + X.length() + "\nY:" + Y + " long=" + Y.length()
                    + "\ny=" + y + " p=" + p);
            pos = subcadenaMonteCarlo(X, Y, y, p);
            pinta("MonteCarlo", X, Y, pos);
            pos = subcadenaLasVegas(X, Y, y, p);
            pinta("LasVegas", X, Y, pos);
        }
        return pos;
    }

    private static String comprueba(String s) {//quita lo que no son 0 o 1
        String aux = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0' || s.charAt(i) == '1') {
                aux = aux + s.charAt(i);
            }
        }
        return aux;
    }

    public static int convierteAentero(String Y) {//pasa de binario a decimal
        int n = 0;
        for (int i = 0; i < Y.length(); i++) {
            if (Y.charAt(i) == '1') {
                n = n + (int) Math.pow(2, Y.length() - i - 1);
            }
        }
        return n;
    }

    public static int primo(int y) {
        int resul = 0;
        for (int i = y - 1; i >= 1 && resul == 0; i--) {
            if (esPrimo(i)) {
                resul = i;
            }
        }
        return resul;
    }

    public static int subcadenaMonteCarlo(String X, String Y, int y, int p) {
        int pos = -1;
        int resto = 0;
        if (p > 0) {
            resto = y % p;
        }
        for (int i = 0; i < X.length() - Y.length() + 1 && pos < 0; i++) {
            String subX = X.substring(i, i + Y.length());
            int x = convierteAentero(subX);
            System.out.println("subX" + i + "=" + subX + " Fx=" + x);
            if (p > 0) {
                if (x % p == resto) {
                    pos = i;
                }
            } else if (x == resto) {
                pos = i;
            }
        }
        return pos;
    }

    public static int subcadenaLasVegas(String X, String Y, int y, int p) {
        int pos = -1;
        int resto = 0;
        if (p > 0) {
            resto = y % p;
        }
        for (int i = 0; i < X.length() - Y.length() + 1 && pos < 0; i++) {
            String subX = X.substring(i, i + Y.length());
            int x = convierteAentero(subX);
            System.out.println("subX" + i + "=" + subX + " Fx=" + x);
            if (p > 0) {
                if (x % p == resto && subX.equals(Y)) {
                    pos = i;
                }
            } else if (x == resto) {
                pos = i;
            }
        }
        return pos;
    }

    private static boolean esPrimo(int numero) {
        int contador = 2;
        boolean primo = true;

        while ((primo) && (contador != numero)) {
            if (numero % contador == 0) {
                primo = false;
            }
            contador++;
        }
        return primo;
    }

    private static void pinta(String monteCarlo, String X, String Y, int pos) {
        System.out.println(monteCarlo);
        System.out.println("Cadena 1 : " + X);
        System.out.println("Cadena 2 : " + Y);
        System.out.println("Posicion : " + pos);
    }
}
