/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menor_distancia_dinamica;

import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author 9alej
 */
public class Solucion {

    private Grafo<Integer, Integer> grafo;

    public Solucion(boolean fordward) {
        Grafo<Integer, Integer> grafo = GenerarGrafo();
        this.grafo = grafo;
        if (fordward) {
            ArrayList<Nodo> distancias_respecto_1 = fordward(grafo);
            Nodo solucion = null;
            for (int i = 0; i < distancias_respecto_1.size(); i++) {
                if (distancias_respecto_1.get(i).getVertex() == 10) {
                    solucion = distancias_respecto_1.get(i);
                }
            }

            System.out.println("Camino: ");
            System.out.println(solucion.toString());
        } else {
            Nodo actual = new Nodo(1, 0, null);
            ArrayList<Nodo> calculados = new ArrayList<Nodo>();
            calculados.add(actual);
            Nodo fin = backward(actual, calculados);
            System.out.println(fin.toString());
        }
    }

    private Grafo<Integer, Integer> GenerarGrafo() {
        Grafo<Integer, Integer> g = new Grafo<Integer, Integer>(10, true);

        for (int i = 1; i <= 10; i++) {
            g.nuevoVertice(i);
        }

        g.nuevoArco(1, 2, 5);
        g.nuevoArco(1, 3, 7);
        g.nuevoArco(1, 4, 2);
        g.nuevoArco(2, 5, 3);
        g.nuevoArco(2, 6, 1);
        g.nuevoArco(3, 5, 4);
        g.nuevoArco(4, 5, 5);
        g.nuevoArco(4, 6, 9);
        g.nuevoArco(5, 7, 8);
        g.nuevoArco(5, 8, 11);
        g.nuevoArco(5, 9, 6);
        g.nuevoArco(6, 8, 4);
        g.nuevoArco(7, 10, 5);
        g.nuevoArco(8, 10, 9);
        g.nuevoArco(9, 10, 12);

        return g;
    }

    private Nodo backward(Nodo actual, ArrayList<Nodo> calculados) {
        Nodo valor = null;
        if (calculados.contains(actual)) {
            valor = calculados.get(calculados.indexOf(actual));
        } else {
            ArrayList<Integer> ady = grafo.adyacentes(actual.getVertex());
            for (int i = 0; i < ady.size(); i++) {
                int nueva_distancia = actual.getValue() + grafo.peso(actual.getVertex(), ady.get(i));
                Nodo nuevo = new Nodo(ady.get(i), nueva_distancia, actual);
                valor = backward(nuevo, calculados);
                if (actual.getValue() > valor.getValue()) {
                    actual.setValue(valor.getValue());
                    actual.setSucc(valor.getSucc());
                }

            }
            valor = actual;
            calculados.add(actual);
        }

        return valor;
    }

    private ArrayList<Nodo> fordward(Grafo<Integer, Integer> grafo) {
        ArrayList<Nodo> anchura = new ArrayList<Nodo>();
        anchura.add(new Nodo(1, 0, null));
        int pos = 0;
        while (pos < anchura.size()) {
            Nodo actual = anchura.get(pos);
            ArrayList<Integer> ady = grafo.adyacentes(actual.getVertex());
            for (int i = 0; i < ady.size(); i++) {
                Nodo nuevo = new Nodo(ady.get(i), actual.getValue() + grafo.peso(actual.getVertex(), ady.get(i)), actual);
                if (!anchura.contains(nuevo)) {
                    anchura.add(nuevo);
                } else {
                    Nodo existente = anchura.get(anchura.indexOf(nuevo));
                    if (nuevo.getValue() < existente.getValue()) {
                        existente.setValue(nuevo.getValue());
                        existente.setSucc(nuevo.getSucc());
                    }
                }
            }//for
            pos++;
        }//Fin while

        return anchura;
    }
}
