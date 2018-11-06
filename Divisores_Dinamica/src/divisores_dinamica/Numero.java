/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package divisores_dinamica;

/**
 *
 * @author 9alej
 */
public class Numero {
    private int numero;
    private Numero viene_de;

    public Numero(int numero, Numero viene_de) {
        this.numero = numero;
        this.viene_de = viene_de;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Numero getViene_de() {
        return viene_de;
    }

    public void setViene_de(Numero viene_de) {
        this.viene_de = viene_de;
    }

    @Override
    public String toString() {
        return "Numero{" + "numero=" + numero + ", viene_de=" + viene_de + '}';
    }
    
}
