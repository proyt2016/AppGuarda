package com.fedoraapps.www.appguarda.Model;

/**
 * Created by maxi on 06/06/2016.
 */
public class Pasaje {
   // private int id;
    private int idViaje;
    private int idOrigen;
    private int idDestino;
    private int idPrecio;
    private int idEmpleado;
    private int monto;

    public Pasaje( int idViaje, int idOrigen, int idDestino, int idPrecio, int idEmpleado, int monto){

        this.idViaje = idViaje;
        this.idOrigen = idOrigen;
        this.idDestino = idDestino;
        this.idPrecio = idPrecio;
        this.idEmpleado = idEmpleado;
        this.monto = monto;
    }



    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public int getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(int idOrigen) {
        this.idOrigen = idOrigen;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public int getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(int idPrecio) {
        this.idPrecio = idPrecio;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }
}
