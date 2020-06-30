package com.example.logica;

/**
 *
 * @author YO
 */
public class Posicion {
    
    private int numero;
    private boolean estado;

    public Posicion(int numero, boolean estado) {
        this.numero = numero;
        this.estado = estado;
    }
    
    public Posicion() {
        this.numero = 0;
        this.estado = false;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
}
