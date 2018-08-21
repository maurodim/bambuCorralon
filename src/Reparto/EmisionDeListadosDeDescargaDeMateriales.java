/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reparto;

import java.sql.Connection;
import java.util.ArrayList;
import Reparto.PedidosParaReparto;

/**
 *
 * @author mauro di
 */
public class EmisionDeListadosDeDescargaDeMateriales extends Thread{
    private Integer numeroListado;
    private Integer numeroDeRevision;
    private ArrayList detallePedidos1=new ArrayList();
    private Double pesoTotal;
    private Connection cc;
    private String codigoCliente;
    private String nombreCliente;
    private int numeroVehiculo;
    private String descripcionVehiculo;
    private String fechaEntrega;

    public void setDetallePedidos1(ArrayList detallePedidos1) {
        this.detallePedidos1 = detallePedidos1;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setNumeroVehiculo(int numeroVehiculo) {
        this.numeroVehiculo = numeroVehiculo;
    }

    public void setDescripcionVehiculo(String descripcionVehiculo) {
        this.descripcionVehiculo = descripcionVehiculo;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setNumeroListado(Integer numeroListado) {
        this.numeroListado = numeroListado;
    }

    public void setNumeroDeRevision(Integer numeroDeRevision) {
        this.numeroDeRevision = numeroDeRevision++;
    }

    public void setPesoTotal(Double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

public void addPedido(PedidosParaReparto ped){
    this.detallePedidos1.add(ped);
}
    
    
    

    @Override
    public synchronized void run(){
        //chequearListado(this.numeroListado);
        
}

}
