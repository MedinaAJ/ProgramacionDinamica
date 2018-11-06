/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diana_prog_dinamica;

/**
 *
 * @author 9alej
 */
public class Objeto {
    
    private int ptos_porHacer;
    private int dardos_tipo; //0,1,2,3 haciendo referencia al indice del vector tipos de zonas
    private int dardos_tipo_cantidad;
    private int dardos_acum;
    private Objeto viene_de;

    public Objeto(int ptos_acum, int dardos_tipo, int dardos_tipo_cantidad, int dardos_acum, Objeto viene_de) {
        this.ptos_porHacer = ptos_acum;
        this.dardos_tipo = dardos_tipo;
        this.dardos_acum = dardos_acum;
        this.dardos_tipo_cantidad = dardos_tipo_cantidad;
        this.viene_de = viene_de;
    }

    public int getPtos_porHacer() {
        return ptos_porHacer;
    }

    public void setPtos_porHacer(int ptos_porHacer) {
        this.ptos_porHacer = ptos_porHacer;
    }

    public int getDardos_tipo_cantidad() {
        return dardos_tipo_cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Objeto other = (Objeto) obj;
        if (this.ptos_porHacer != other.ptos_porHacer) {
            return false;
        }
        if (this.dardos_tipo != other.dardos_tipo) {
            return false;
        }
        return true;
    }

    public void setDardos_tipo_cantidad(int dardos_tipo_cantidad) {
        this.dardos_tipo_cantidad = dardos_tipo_cantidad;
    }

    public int getPtos_acum() {
        return ptos_porHacer;
    }

    public void setPtos_acum(int ptos_acum) {
        this.ptos_porHacer = ptos_acum;
    }

    public int getDardos_tipo() {
        return dardos_tipo;
    }

    public void setDardos_tipo(int dardos_tipo) {
        this.dardos_tipo = dardos_tipo;
    }

    public int getDardos_acum() {
        return dardos_acum;
    }

    public void setDardos_acum(int dardos_acum) {
        this.dardos_acum = dardos_acum;
    }

    public Objeto getViene_de() {
        return viene_de;
    }

    public void setViene_de(Objeto viene_de) {
        this.viene_de = viene_de;
    }

    @Override
    public String toString() {
        return "Objeto{" + "ptos_porHacer=" + ptos_porHacer + ", dardos_tipo=" + dardos_tipo + ", dardos_tipo_cantidad=" + dardos_tipo_cantidad + ", dardos_acum=" + dardos_acum + ", viene_de=" + viene_de + '}';
    }

   
    public String toStringSinCamino() {
        return "Objeto{" + "ptos_porHacer=" + ptos_porHacer + ", dardos_tipo=" + dardos_tipo + ", dardos_tipo_cantidad=" + dardos_tipo_cantidad + ", dardos_acum=" + dardos_acum + '}';
    }
    
    
}
