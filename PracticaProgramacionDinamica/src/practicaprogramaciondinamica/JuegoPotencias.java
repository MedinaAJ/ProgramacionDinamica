/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaprogramaciondinamica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author 9alej
 */
public class JuegoPotencias implements InterfazSituacion{
    
    Estado estadoInicial;
    Estado estadoFinal;
    
    public JuegoPotencias(int n){
        ArrayList<Integer> cantidadDivisores;
        cantidadDivisores = GenerarDivisores(n);
        estadoInicial = new Estado(cantidadDivisores);
        estadoFinal = null;
        Menu();        
    }

    private ArrayList<Integer> GenerarDivisores(int n) {
        ArrayList<Integer> stacks = new ArrayList<Integer>();
        int aux = n;
        int cont = 0;
        if (n == 1)
            stacks.add(1);
        else
            for (int i = 2; i <= n; i++) {
                cont = 0;
                while (aux % i == 0) {
                    cont++;
                    aux = aux / i;
                }
                if (cont != 0)
                    stacks.add(cont);
            }
        return stacks;
    }

    private void Menu() {
        Scanner s = new Scanner(System.in);
        String op = "";
        while(!op.equals("3") && !op.equals("S")){
            System.out.println("Por favor, seleccione una de las siguientes opciones:\n"
                    + "\t1. Algoritmo Fordward (F)\n"
                    + "\t2. Algoritmo Backward (B)\n"
                    + "\t3. Salir (S)");
            op = s.next();
            if(op.equals("1") || op.equals("F"))
                Juego(true);
            else if(op.equals("2") || op.equals("B"))
                Juego(false);
            else if(op.equals("3") || op.equals("S"))
                System.out.println("Fin del programa.");
            else
                System.out.println("Por favor, elija una opcion correcta");
        }
    }

    private void Juego(boolean fordward) {
        int turno = 0;
        ArrayList<Estado> listaEstados;
        while (estadoInicial.cantidaddepalillos() > 0) {
            listaEstados = new ArrayList<Estado>();
            estadoFinal = null;
            turno++;
            System.out.println("___________________________");
            System.out.print("Turno:  " + turno);
            if (turno % 2 == 0) {
                System.out.println(" Jugador 2:");
            } else {
                System.out.println(" Jugador 1:");
            }
            System.out.println("    Sticks:" + estadoInicial.getLista());
            if (fordward) {
                listaEstados.add(new Estado(new ArrayList<Integer>()));
                Fordward(listaEstados);
            } else {
                listaEstados.add(estadoInicial);
                Backward(estadoInicial, listaEstados);
            }

            if (estadoFinal == null) {
                System.out.println("Elimina una potencia");
                // Se selecciona la fila con mas palos para quitarle 1, alargando al
                // maximo la partida y esperar error del oponente.
                eliminarPotencia(estadoInicial);
            } else {
                System.out.println("Gana el juego");
            }

            estadoInicial = estadoFinal;
            System.out.println("\n\nEmpezamos el juego en:  " + estadoInicial);
        }

        
        if (turno % 2 == 0) {
            System.out.println("Gana el segundo jugador!");
        } else {
            System.out.println("Gana el primer jugador!");
        }
    }
    
    private void eliminarPotencia(Estado estadoInicial) {
		ArrayList<Integer> Stacksone = new ArrayList<Integer>(estadoInicial.getLista());
		// Remove one toothpick from the bigger stack
		int biggerrow = Stacksone.indexOf(Collections.max(estadoInicial.getLista()));
		Stacksone.set(biggerrow, Stacksone.get(biggerrow) - 1);
		estadoFinal = new Estado(Stacksone);
		estadoFinal.setPosicion(tiposSituacion.Ganadora);
	}
    
    
    private void Fordward(ArrayList<Estado> statelist) {
        int count = 0;
        while (count < statelist.size() && estadoFinal == null) {
            Estado CurrentSituation = statelist.get(count);
            for (Estado previous : previosEstadosPosibles(CurrentSituation)) {
                // If we find a similar state we try to retrieve it, otherwise we add the new state
                if (statelist.contains(previous)) {
                    previous = statelist.get(statelist.indexOf(previous));
                } else {
                    statelist.add(previous);
                }
                //if the first isn't a winner position then it becomes in a Looser position
                if (CurrentSituation.getPosicion() == tiposSituacion.Perdedora) {
                    previous.setPosicion(tiposSituacion.Ganadora);
                    // base case, we finish with a winner position
                    if (previous.equals(estadoInicial)) {
                        estadoFinal = CurrentSituation;
                    }
                }
            }
            count++;
        }
    }

    /**
     * @param current
     * @return
     */
    public ArrayList<Estado> previosEstadosPosibles(Estado current) {
        ArrayList<Estado> previousstates = new ArrayList<Estado>();
        ArrayList<Integer> previousstack;
        // we are adding toothpick sticks to reach the first state
        for (int stack = 0; stack < current.getLista().size(); stack++) {
            for (int toothpick = current.getLista().get(stack) + 1; toothpick <= Collections.max(estadoInicial.getLista()); toothpick++) {
                previousstack = new ArrayList<Integer>(current.getLista());
                previousstack.set(stack, toothpick);
                Estado previous = new Estado(previousstack);
                // We should control not to get over the amount of the stacks
                if (!previousstates.contains(previous) && !previous.fin(estadoInicial)) {
                    previous.setPosicion(tiposSituacion.Perdedora);
                    previousstates.add(previous);
                }
            }
        }
        // new stacks of toothpick sticks is created 
        for (int toothpick = 1; toothpick <= Collections.max(estadoInicial.getLista()); toothpick++) {
            previousstack = new ArrayList<Integer>(current.getLista());
            previousstack.add(toothpick);
            Estado anterior = new Estado(previousstack);
            //// We should control not to get over the amount of the stacks
            if (!previousstates.contains(anterior) && !anterior.fin(estadoInicial)) {
                anterior.setPosicion(tiposSituacion.Perdedora);
                previousstates.add(anterior);
            }
        }

        /*// MOSTRAR LA GENERACION DE ANTERIORES
		System.out.print("Anteriores de ");
		System.out.println(current);
		for (int i = 0; i < previousstates.size(); i++) {
			System.out.println(previousstates.get(i).toString());
		}
		System.out.println("----------------");
         */
        return previousstates;
    }

    private void Backward(Estado first, ArrayList<Estado> stateslist) {
        // for each posible states
        for (Estado next : posiblesSiguientesEstados(first)) {
            // when we finish, don't generate any another state
            if (estadoFinal == null) {
                // if it doesn't exist we add a new state among the posible
                if (stateslist.contains(next)) {
                    next = stateslist.get(stateslist.indexOf(next));
                } else {
                    stateslist.add(next);
                }
                // we compute the next position when it isn't evaluated using the recursion
                if (next.getPosicion() == tiposSituacion.Indecidible) {
                    Backward(next, stateslist);
                }
                // base case, we finish with a winner position
                if (next.getPosicion() == tiposSituacion.Perdedora) {
                    first.setPosicion(tiposSituacion.Ganadora);
                    if (first.equals(this.estadoInicial)) {
                        estadoFinal = next;
                    }
                }
                // if the first isn't a winner position then it becomes in a Looser position
                if (first.getPosicion() != tiposSituacion.Ganadora) {
                    first.setPosicion(tiposSituacion.Perdedora);
                }
            }
        }
    }

    private ArrayList<Estado> posiblesSiguientesEstados(Estado current) {
        // ArrayList Variables
        ArrayList<Estado> nextstates = new ArrayList<Estado>();
        ArrayList<Integer> nextstack;
        // For each stack of toothpick sticks we generate from 0 to he maximun value
        for (int stack = 0; stack < current.getLista().size(); stack++) {
            for (int toothpick = 0; toothpick < current.getLista().get(stack); toothpick++) {
                nextstack = new ArrayList<Integer>(current.getLista());
                nextstack.set(stack, toothpick);
                Estado next = new Estado(nextstack);
                // We control not to generate the same state
                if (!nextstates.contains(next)) {
                    nextstates.add(next);
                }
            }
        }

        /*//		// MOSTRAR LA GENERACION DE SIGUIENTES
	System.out.print("Siguientes de ");
		System.out.println(current);
		for (int i = 0; i < nextstates.size(); i++) {
			System.out.println(nextstates.get(i));
		}
		System.out.println("----------------");

         */
        return nextstates;
    }

    public boolean isNumero(String valor) {
        try {
            int n = Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
