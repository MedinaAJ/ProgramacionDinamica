/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menor_distancia_dinamica;

/**
 *
 * @author 9alej
 */
public class Nodo {
    private int nombre;
    private int valor_acumulado;
    private Nodo succ;

    public Nodo(int vertex, int value, Nodo succ) {
        this.nombre = vertex;
        this.valor_acumulado = value;
        this.succ = succ;
    }
    
    public Nodo(int vertex, int value) {
        this.nombre = vertex;
        this.valor_acumulado = value;
        this.succ = null;
    }
    
    public Nodo(int vertex) {
        this.nombre = vertex;
        this.valor_acumulado = Integer.MAX_VALUE;
        this.succ = null;
    }
    
    public boolean equals(Object o) {
	return o instanceof Nodo && ((Nodo)o).nombre == this.nombre;
    }

    public int getVertex() {
        return nombre;
    }

    public void setVertex(int vertex) {
        this.nombre = vertex;
    }

    public int getValue() {
        return valor_acumulado;
    }

    public void setValue(int value) {
        this.valor_acumulado = value;
    }

    public Nodo getSucc() {
        return succ;
    }

    public void setSucc(Nodo succ) {
        this.succ = succ;
    }

    @Override
    public String toString() {
        return "Nodo{" + "vertex=" + nombre + ", value=" + valor_acumulado + ", succ=" + succ + '}';
    }
}
