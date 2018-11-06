package practicaprogramaciondinamica;

import java.util.ArrayList;
import java.util.Collections;


public class Estado implements InterfazSituacion {
    private tiposSituacion posicion;
    private ArrayList<Integer> lista;    
    
    public Estado (ArrayList<Integer> lista) {
        posicion=tiposSituacion.Indecidible;
        this.lista=new ArrayList<Integer>();
            for (int i=0;i<lista.size();i++) {
                if (lista.get(i)!=0)
                    this.lista.add(lista.get(i));
            }  
        if (this.lista.isEmpty() || lista.isEmpty()) {
            this.lista.add(0);
            posicion=tiposSituacion.Perdedora;
        }        
        
        Collections.sort(getLista());
    }
    public tiposSituacion getPosicion() {
        return posicion;
    }
     public boolean fin(Estado maximo) {        
        boolean fin=false;
        if(cantidadPotencias()>maximo.cantidadPotencias()){
                fin=true;
            }else if(getLista().size()>maximo.getLista().size()){
                fin=true;
            }
        return fin;        
    }     
    public void setPosicion(tiposSituacion posicion) {
        this.posicion = posicion;
    }    
    public ArrayList<Integer> getLista() {
        return this.lista;
    }  
    public void setLista(ArrayList<Integer> lista){
        this.lista = lista;
    }
    public int cantidadPotencias() {
        int contador=0;
        for (int i=0;i<getLista().size();i++) 
            contador=contador+getLista().get(i);
        return contador;
    }  
    @Override
    public boolean equals(Object nuevoEstado) {
            Estado aux = (Estado) nuevoEstado;
            return getLista().equals(aux.getLista());
    }

    @Override
    public String toString() {
            return getLista().toString() +" "+getPosicion();
    }
}
