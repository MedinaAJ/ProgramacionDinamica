
package examenalgoritmos1p;

/**
 *
 * @author absit
 */
public class Principal {

    private int val;

    public Principal(int value) {
        this.val = value;
        fordward();
    }

    public boolean combinacion(int indice, int[] vector) {
        int i;
        boolean encontrado = false;
        if (indice == vector.length) {            
            if (comprobarVector(vector)) 
                encontrado = true;        
        } else {
            for (i = 2; i <= 3 && !encontrado; i++) {
                if (esPosible(vector, indice, i)) {
                    vector[indice] = i;
                    encontrado = combinacion(indice + 1, vector);
                }
            }
        }
        return encontrado;
    }

    public void fordward() {
        int indice = 1;
        int vector[] = null;
        boolean encontrado = false;
        while (!encontrado) {
            vector = new int[indice];
            encontrado = combinacion(0, vector);
            if (!encontrado) {
                indice++;
            }
        }
        for (int j=0;j<vector.length;j++)
             System.out.print(vector[j] + " ");
    }

    public boolean comprobarVector(int[] vector) {
        int total = 1;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] == 2) {
                total *= 2;
            } else {
                total = total / 3;
            }
        }
        return (total == val);
    }

    public boolean esPosible(int[] vector, int indice, int j) {
        int total = 1;
        int aux = 1;
        boolean posible = true;

        for (int i = 0; i < indice; i++) {
            if (vector[i] == 2) {
                total *= 2;
            } else {
                total /= 3;
            }
        }
        if (j == 2) {
            total *= 2;
        } else {
            total /= 3;
        }
        if (total == 0 || total == 1) {
            posible = false;
        }
        for (int z = 0; z < indice; z++) {
            if (total == aux) {
                posible = false;
            } else {
                if (vector[z] == 2) {
                    aux *= 2;
                } else {
                    aux /= 3;
                }
            }
        }
        return posible;
    }
}
