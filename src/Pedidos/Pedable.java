/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pedidos;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro di
 */
public interface Pedable {
    public Integer nuevoPedido(Object ped);
    public ArrayList cargarDetallePedido(Integer idPed);
    public Object cargarEncabezadoPedido(Integer idPed);
    public ArrayList listar();
    public ArrayList listarPorCliente(Integer idClient);
    public ArrayList listarPorEstado(Integer idClient,int estado);
    public Boolean modificarPedido(Object ped);
    public void eliminarPedido(Integer idPed);
    public DefaultTableModel mostrarListado(ArrayList lista);
    public void transformarEnFactura(Object ped,ArrayList detalle);
    public void transformarEnCotizacion(Object ped,ArrayList detalle);
    public void transformarEnRemito(Object ped,ArrayList detalle);
    public ArrayList convertirAArticulos(ArrayList detalle);
}
