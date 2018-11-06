package practice2nim;

import java.util.ArrayList;
import java.util.Collections;
/**
 * @authors Carlos Cordoba - Alvaro Moreno 
 */

public class State implements Interfacetypes {
    private Positiontype position;
    private ArrayList<Integer> stacks;    
    
    public State (ArrayList<Integer> stacks) {
        //We inicialize attributes
        this.stacks=new ArrayList<Integer>();
        
        
            position=Positiontype.NotEvaluated;
            for (int i=0;i<stacks.size();i++) {
                if (stacks.get(i).intValue()!=0)
                    this.stacks.add(stacks.get(i));
            }  
        if (this.stacks.isEmpty() || stacks.isEmpty()) {
            this.stacks.add(0);
            position=Positiontype.Looser;
        }        
        
        Collections.sort(getStacks());
    }
    public Positiontype getPosition() {
        return position;
    }
    public void setPosition(Positiontype position) {
        this.position = position;
    }    
    public ArrayList<Integer> getStacks() {
        return this.stacks;
    }  
    public int amountoftoothpicksticks() {
        int amount=0;
        for (int i=0;i<getStacks().size();i++) 
            amount+=getStacks().get(i);
        return amount;
    }
    public boolean isOver(State maximum) {        
        return ((amountoftoothpicksticks()>maximum.amountoftoothpicksticks()) 
                || (getStacks().size()>maximum.getStacks().size()));        
    }       
    @Override
    public boolean equals(Object newstate) {
            State aux = (State) newstate;
            return getStacks().equals(aux.getStacks());
    }

    @Override
    public String toString() {
            return getStacks().toString() +" "+getPosition();
    }
}
