/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaprogramaciondinamica;

import static java.lang.Thread.sleep;
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
        MenuPrincipal();      
    }
    
    private void MenuPrincipal(){
        Scanner s = new Scanner(System.in);
        String op = "";
        
        while(!op.equals("1") && !op.equals("2")){        
            System.out.println("Por favor, seleccione una de las siguientes opciones: ");
            System.out.println("1. Maquina Vs Maquina");
            System.out.println("2. Maquina Vs Persona");
            op = s.next();
        }
        if(op.equals("1")) Menu(false);
        if(op.equals("2")) Menu(true);
        
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

    private void Menu(boolean persona) {
        Scanner s = new Scanner(System.in);
        String op = "";
        while(!op.equals("3") && !op.equals("S")){
            System.out.println("Por favor, seleccione una de las siguientes opciones:\n"
                    + "\t1. Algoritmo Fordward (F)\n"
                    + "\t2. Algoritmo Backward (B)\n"
                    + "\t3. Salir (S)");
            op = s.next();
            if(persona){
                if(op.equals("1") || op.equals("F"))
                    JuegoPersona(true);
                else if(op.equals("2") || op.equals("B"))
                    JuegoPersona(false);
                else if(op.equals("3") || op.equals("S"))
                    System.out.println("Fin del programa.");
                else
                    System.out.println("Por favor, elija una opcion correcta");
            }else{               
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
    }
    
    private void JuegoPersona(boolean fordward){        
        int turno = 0;
        Scanner sc = new Scanner(System.in);
        int opcion=0;
        int eliminar = 0;
        ArrayList<Estado> listaEstados;
        ArrayList<Integer> nuevaLista;
        while (estadoInicial.cantidadPotencias() > 0) {
            listaEstados = new ArrayList<Estado>();
            estadoFinal = null;
            turno++;
            System.out.println("___________________________");
            System.out.print("Turno:  " + turno);
            if (turno % 2 == 0) {
                System.out.println(" Jugador 2:");
                nuevaLista = estadoInicial.getLista();
                while(opcion<1 || opcion>nuevaLista.size()){
                    System.out.println("Selecciona la posicion de la que quieres eliminar una potencia:\n"
                            + "El rango para elegir es: 1(Representa pos 0 del array) - " + (nuevaLista.size()));
                    opcion = sc.nextInt();
                }
                while(eliminar<1 || eliminar>nuevaLista.get(opcion-1)){           
                System.out.println("Selecciona el numero de potencias que quieres eliminar.\n"
                        + "El rango para elegir es: 1 - " + nuevaLista.get(opcion-1));
                    eliminar = sc.nextInt();
                }
                nuevaLista.set(opcion-1, nuevaLista.get(opcion-1)-eliminar);
                estadoFinal = new Estado(nuevaLista);
                estadoFinal.setPosicion(tiposSituacion.Ganadora);
            } else {
                System.out.println(" Jugador 1:");
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){
                    e.printStackTrace();
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

    private void Juego(boolean fordward) {
        int turno = 0;
        ArrayList<Estado> listaEstados;
        while (estadoInicial.cantidadPotencias() > 0) {
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
    
    
    private void Fordward(ArrayList<Estado> listaEstados) {
        int count = 0;
        while (count < listaEstados.size() && estadoFinal == null) {
            Estado situacionActual = listaEstados.get(count);
            for (Estado previo : previosEstadosPosibles(situacionActual)) {
                // If we find a similar state we try to retrieve it, otherwise we add the new state
                if (listaEstados.contains(previo)) {
                    previo = listaEstados.get(listaEstados.indexOf(previo));
                } else {
                    listaEstados.add(previo);
                }
                //if the first isn't a winner position then it becomes in a Looser position
                if (situacionActual.getPosicion() == tiposSituacion.Perdedora) {
                    previo.setPosicion(tiposSituacion.Ganadora);
                    // base case, we finish with a winner position
                    if (previo.equals(estadoInicial)) {
                        estadoFinal = situacionActual;
                    }
                }
            }
            count++;
        }
    }

    /**
     * @param actual
     * @return
     */
    public ArrayList<Estado> previosEstadosPosibles(Estado actual) {
        ArrayList<Estado> estadosPrevios = new ArrayList<Estado>();
        ArrayList<Integer> previousstack;
        // we are adding toothpick sticks to reach the first state
        for (int stack = 0; stack < actual.getLista().size(); stack++) {
            for (int potencia = actual.getLista().get(stack) + 1; potencia <= Collections.max(estadoInicial.getLista()); potencia++) {
                previousstack = new ArrayList<Integer>(actual.getLista());
                previousstack.set(stack, potencia);
                Estado previos = new Estado(previousstack);
                // We should control not to get over the amount of the stacks
                if (!estadosPrevios.contains(previos) && !previos.fin(estadoInicial)) {
                    previos.setPosicion(tiposSituacion.Perdedora);
                    estadosPrevios.add(previos);
                }
            }
        }
        // new stacks of toothpick sticks is created 
        for (int potencia = 1; potencia <= Collections.max(estadoInicial.getLista()); potencia++) {
            previousstack = new ArrayList<Integer>(actual.getLista());
            previousstack.add(potencia);
            Estado anterior = new Estado(previousstack);
            //// We should control not to get over the amount of the stacks
            if (!estadosPrevios.contains(anterior) && !anterior.fin(estadoInicial)) {
                anterior.setPosicion(tiposSituacion.Perdedora);
                estadosPrevios.add(anterior);
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
        return estadosPrevios;
    }

    private void Backward(Estado primerEstado, ArrayList<Estado> listaEstados) {
        // for each posible states
        for (Estado next : posiblesSiguientesEstados(primerEstado)) {
            // when we finish, don't generate any another state
            if (estadoFinal == null) {
                // if it doesn't exist we add a new state among the posible
                if (listaEstados.contains(next)) {
                    next = listaEstados.get(listaEstados.indexOf(next));
                } else {
                    listaEstados.add(next);
                }
                // we compute the next position when it isn't evaluated using the recursion
                if (next.getPosicion() == tiposSituacion.Indecidible) {
                    Backward(next, listaEstados);
                }
                // base case, we finish with a winner position
                if (next.getPosicion() == tiposSituacion.Perdedora) {
                    primerEstado.setPosicion(tiposSituacion.Ganadora);
                    if (primerEstado.equals(this.estadoInicial)) {
                        estadoFinal = next;
                    }
                }
                // if the first isn't a winner position then it becomes in a Looser position
                if (primerEstado.getPosicion() != tiposSituacion.Ganadora) {
                    primerEstado.setPosicion(tiposSituacion.Perdedora);
                }
            }
        }
    }

    private ArrayList<Estado> posiblesSiguientesEstados(Estado actual) {
        // ArrayList Variables
        ArrayList<Estado> siguientesEstados = new ArrayList<Estado>();
        ArrayList<Integer> nextstack;
        // For each stack of toothpick sticks we generate from 0 to he maximun value
        for (int stack = 0; stack < actual.getLista().size(); stack++) {
            for (int potencias = 0; potencias < actual.getLista().get(stack); potencias++) {
                nextstack = new ArrayList<Integer>(actual.getLista());
                nextstack.set(stack, potencias);
                Estado next = new Estado(nextstack);
                // We control not to generate the same state
                if (!siguientesEstados.contains(next)) {
                    siguientesEstados.add(next);
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
        return siguientesEstados;
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
