/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cotizaciones;

import java.sql.Date;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro di
 */
public interface Cotizable {
    public Object cargarEncabezado(Integer id);
    public ArrayList cargarDetalle(Integer idCotizacion);
    public ArrayList listar();
    public ArrayList listarPorCliente(Integer idCliente);
    public ArrayList listarPorEstado(Integer idCliente,int estado);
    public ArrayList listarPorFecha(Date fechaDesde,Date fechaHasta);
    public ArrayList listarPorVencimiento(Date fechaDesde,Date fechaHasta);
    public ArrayList listarPorArticulo(Integer idArticulo,Integer idCliente);
    public ArrayList listarPorPedido(Integer idPedido,Integer idCliente);
    public Integer nuevaCotizacion(Object coti);
    public Object modificarCotizacion(Object coti);
    public void eliminarCotizacion(Integer id);
    public DefaultTableModel mostrarListado(ArrayList listadoC);
    public void transformarEnPedido(Object coti,ArrayList detalle);
    public void transformarEnFactura(Object coti,ArrayList detalle);
    public ArrayList convertirAArticulos(ArrayList listado);
    
}
