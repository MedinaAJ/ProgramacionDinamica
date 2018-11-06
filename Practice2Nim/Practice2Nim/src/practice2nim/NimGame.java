package practice2nim;

import java.io.IOException;
import java.util.*;
/**
 * @authors Carlos Cordoba - Alvaro Moreno
 */
public class NimGame implements Interfacetypes {
	State finalturn;
	State firststate;   

	public NimGame () throws IOException {
		finalturn = null;
		readFile();
		game();      
	}

	//read the stacks of toothpick stick from a txt file
	private void readFile () throws IOException {
		ArrayList<Integer> Stacks = ReadFile.readStacks("stacks.txt");
		this.firststate=new State(Stacks);
	}  

	//menu to choose one of the algorithms backward or fordward
	private void game() throws IOException {        
		Scanner sc=new Scanner(System.in);
		String option="";
		int intoption=0;
		boolean stroption;
		do {
			System.out.println("\n1. Backward");
			System.out.println("2. Fordward");
			System.out.println("3. Exit");
			do {
				System.out.println("\nChoose an option: ");
				option=sc.next();

				if (IsNumericInteger(option)) {
					intoption=Integer.parseInt(option);
					if (intoption>=1 && intoption<=3)
						stroption=true;
					else {
						System.out.println("Out of interval");
						stroption=false;
					}
				}
				else {
					System.out.println("Non numeric");
					stroption=false;

				}
			} while(!stroption);           
			switch (intoption) {
			case 1:
				System.out.println(toPlay('B'));
				new NimGame();
				break;
			case 2:
				System.out.println(toPlay('F'));
				new NimGame();
				break;
			case 3:
				System.out.println("END OF GAME"); 
				break;
			}
		} while (intoption!=3);
	}

	//game between two oponents
	private String toPlay(char algorithm) {
		int turn = 0;
		ArrayList<State> stateslist;
		while (firststate.amountoftoothpicksticks()> 0) {
			stateslist = new ArrayList<State>();
			finalturn=null;
			turn++;
			System.out.println("___________________________");
			System.out.print("Turn:" + turn );
			if (turn % 2 == 0) {
				System.out.println(" Player 2:");
			} else {
				System.out.println(" Player 1:");
			}
			System.out.println("    Sticks:" + firststate.getStacks());
			if (algorithm=='B') {                           
				stateslist.add(firststate);
				backwardSolution(firststate, stateslist);
			}
			else {
				stateslist.add(new State(new ArrayList<Integer>()));
				forwardSolution(stateslist);
			}

			if (finalturn == null) {
				System.out.println("Remove One");
				// Se selecciona la fila con mas palos para quitarle 1, alargando al
				// maximo la partida y esperar error del oponente.
				removeOne(firststate);
			} else {
				System.out.println("Winner play");
			}
			
			firststate = finalturn;
			System.out.println("We get in the game : "+firststate);
		}

		String resultado;
		if (turn % 2 == 0) {
			resultado = "Win el 2nd player";
		} else {
			resultado = "Win el 1st player";
		}
		return resultado;
	}

	//
	private void removeOne(State firststate) {
		ArrayList<Integer> Stacksone = new ArrayList<Integer>(firststate.getStacks());
		// Remove one toothpick from the bigger stack
		int biggerrow = Stacksone.indexOf(Collections.max(firststate.getStacks()));
		Stacksone.set(biggerrow, Stacksone.get(biggerrow) - 1);
		finalturn = new State(Stacksone);
		finalturn.setPosition(Positiontype.Winner);
	}


	private void forwardSolution(ArrayList<State> statelist) {
		int count = 0;
		while (count < statelist.size() && finalturn == null) {
			State CurrentSituation = statelist.get(count);
			for (State previous : previousPosiblestates(CurrentSituation)) {
				// If we find a similar state we try to retrieve it, otherwise we add the new state
				if (statelist.contains(previous)) {
					previous = statelist.get(statelist.indexOf(previous));
				} else {
					statelist.add(previous);
				}
				//if the first isn't a winner position then it becomes in a Looser position
				if (CurrentSituation.getPosition()==Positiontype.Looser) {
					previous.setPosition(Positiontype.Winner);
					// base case, we finish with a winner position
					if (previous.equals(firststate)) {
						finalturn = CurrentSituation;
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
	public ArrayList<State> previousPosiblestates(State current) {
		ArrayList<State> previousstates = new ArrayList<State>();
		ArrayList<Integer> previousstack;
		// we are adding toothpick sticks to reach the first state
		for (int stack = 0; stack < current.getStacks().size(); stack++) {
			for (int toothpick = current.getStacks().get(stack) + 1; toothpick <= Collections.max(firststate.getStacks()); toothpick++) {
				previousstack = new ArrayList<Integer>(current.getStacks());
				previousstack.set(stack, toothpick);
				State previous = new State(previousstack);
				// We should control not to get over the amount of the stacks
				if (!previousstates.contains(previous) && !previous.isOver(firststate)) {
					previous.setPosition(Positiontype.Looser);
					previousstates.add(previous);
				}
			}
		}
		// new stacks of toothpick sticks is created 
		for (int toothpick = 1; toothpick <= Collections.max(firststate.getStacks()); toothpick++) {
			previousstack = new ArrayList<Integer>(current.getStacks());
			previousstack.add(toothpick);
			State anterior = new State(previousstack);
			//// We should control not to get over the amount of the stacks
			if (!previousstates.contains(anterior) && !anterior.isOver(firststate)) {
				anterior.setPosition(Positiontype.Looser);
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

	private void backwardSolution(State first, ArrayList<State> stateslist) {
		// for each posible states
		for (State next : nextPosiblestates(first)) {
			// when we finish, don't generate any another state
			if (finalturn == null) {
				// if it doesn't exist we add a new state among the posible
				if (stateslist.contains(next)) {
					next = stateslist.get(stateslist.indexOf(next));
				} else {
					stateslist.add(next);
				}
				// we compute the next position when it isn't evaluated using the recursion
				if (next.getPosition()==Positiontype.NotEvaluated){                                      
					backwardSolution(next, stateslist);
				}
				// base case, we finish with a winner position
				if (next.getPosition()==Positiontype.Looser) {
					first.setPosition(Positiontype.Winner);
					if (first.equals(this.firststate)) {
						finalturn = next;
					}
				}
				// if the first isn't a winner position then it becomes in a Looser position
				if (first.getPosition()!=Positiontype.Winner) {
					first.setPosition(Positiontype.Looser);
				}
			}
		}
	}

	private ArrayList<State> nextPosiblestates(State current) {
		// ArrayList Variables
		ArrayList<State> nextstates = new ArrayList<State>();
		ArrayList<Integer> nextstack;
		// For each stack of toothpick sticks we generate from 0 to he maximun value
		for (int stack = 0; stack < current.getStacks().size(); stack++) {
			for (int toothpick = 0; toothpick < current.getStacks().get(stack); toothpick++) {
				nextstack = new ArrayList<Integer>(current.getStacks());
				nextstack.set(stack, toothpick);
				State next = new State(nextstack);
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

	public boolean IsNumericInteger (String valor) {
		try {
			int numero=Integer.parseInt(valor);
			return true;
		}
		catch (NumberFormatException nfe){
			return false;
		}
	}
}
