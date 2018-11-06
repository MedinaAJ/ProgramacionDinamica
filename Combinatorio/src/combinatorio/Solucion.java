/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combinatorio;

import static java.lang.Double.max;
import static java.lang.Double.min;

/**
 *
 * @author 9alej
 */
public class Solucion {

    public Solucion(boolean fordward) {
        int sol;
        if (fordward) {
            sol = solFord(4, 2);
        } else {
            sol = solBack(4, 2);
        }
    }

    private int solFord(int m, int n) {

        int sol = 0;
        if (n >= 0 && m >= 0 && n >= m) {
            int[][] triangulo = new int[n + 1][m + 1];
            numCombForw(n, m, triangulo);
            sol = triangulo[n][m];
        }
        return sol;
    }

    private int solBack(int m, int n) {
        int sol = 0;
        if (n >= 0 && m >= 0 && n >= m) {
            int[][] triangulo = new int[n + 1][m + 1];
            numCombBack(n, m, triangulo);
            sol = triangulo[n][m];
        }
        return sol;
    }

    private static void numCombForw(int n, int m, int[][] triangulo) {
        triangulo[0][0] = 1;
        for (int f = 1; f <= n; f++) {
            for (int c = (int) max(0, f - m); c <= min(f, m); c++) {//max es el máximo; min es el minimo
                if (c == 0) {
                    triangulo[f][c] = 1;
                } else {
                    triangulo[f][c] = triangulo[f - 1][c - 1] + triangulo[f - 1][c];
                }
            }
        }
    }

    private void numCombBack(int n, int m, int[][] triangulo) {
        if (triangulo[n][m] == 0) {
            if (m == 0 || n == m) {
                triangulo[n][m] = 1;
            } else {
                numCombBack(n - 1, m- 1, triangulo);
                numCombBack(n - 1, m, triangulo);
                triangulo[n][m] = triangulo[n - 1][m - 1] + triangulo[n - 1][m];
            }
        }
    }
}
