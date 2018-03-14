/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pedidos;

import interfaces.Transaccionable;
import interfacesPrograma.Facturar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import Articulos.Articulos;
import objetos.Conecciones;

/**
 *
 * @author mauro di
 */
public class DetallePedidos implements Pedable{
    private Integer id;
    private Integer idPedido;
    private Integer idArticulo;
    private String descripcionArticulo;
    private Integer idCliente;
    private Double cantidad;
    private Double precioUnitario;
    private String observaciones;
    private Double cantidadFacturada;
    private Double cantidadRemitida;
    private int descuento;

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Double getCantidadFacturada() {
        return cantidadFacturada;
    }

    public void setCantidadFacturada(Double cantidadFacturada) {
        this.cantidadFacturada = cantidadFacturada;
    }

    public Double getCantidadRemitida() {
        return cantidadRemitida;
    }

    public void setCantidadRemitida(Double cantidadRemitida) {
        this.cantidadRemitida = cantidadRemitida;
    }

    @Override
    public Integer nuevoPedido(Object ped) {
        DetallePedidos detalle=new DetallePedidos();
        detalle=(DetallePedidos)ped;
        Integer numero=0;
        String sql="insert into detallepedidos (idpedido,idarticulo,descripcionarticulo,idcliente,cantidad,preciounitario,descuento) values ("+detalle.getIdPedido()+","+detalle.getIdArticulo()+",'"+detalle.getDescripcionArticulo()+"',"+detalle.getIdCliente()+","+detalle.getCantidad()+","+detalle.getPrecioUnitario()+","+detalle.getDescuento()+")";
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        
        return numero;
    }

    @Override
    public ArrayList cargarDetallePedido(Integer idPed) {
        ArrayList listadoP=new ArrayList();
        DetallePedidos detalle;
        String sql="select * from detallepedidos where idpedido="+idPed;
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                detalle=new DetallePedidos();
                detalle.setId(rs.getInt("id"));
                detalle.setCantidad(rs.getDouble("cantidad"));
                detalle.setCantidadFacturada(0.00);
                detalle.setCantidadRemitida(0.00);
                detalle.setDescripcionArticulo(rs.getString("descripcionarticulo"));
                detalle.setDescuento(rs.getInt("descuento"));
                detalle.setIdArticulo(rs.getInt("idarticulo"));
                detalle.setIdCliente(rs.getInt("idcliente"));
                detalle.setIdPedido(rs.getInt("idpedido"));
                detalle.setObservaciones(rs.getString("observaciones"));
                detalle.setPrecioUnitario(rs.getDouble("preciounitario"));
                listadoP.add(detalle);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetallePedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listadoP;
    }

    @Override
    public Object cargarEncabezadoPedido(Integer idPed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorCliente(Integer idClient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorEstado(Integer idClient, int estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean modificarPedido(Object ped) {
        DetallePedidos detalle=new DetallePedidos();
        detalle=(DetallePedidos)ped;
        String sql="update detallepedidos set descripcionarticulo='"+detalle.getDescripcionArticulo()+"',cantidad="+detalle.getCantidad()+",preciounitario="+detalle.getPrecioUnitario()+", descuento="+detalle.getDescuento()+" where id="+detalle.getId();
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        return true;
        
    }

    @Override
    public void eliminarPedido(Integer idPed) {
        String sql="delete from detallepedidos where id="+idPed;
        Transaccionable tra=new Conecciones();
       tra.guardarRegistro(sql);
    }

    @Override
    public DefaultTableModel mostrarListado(ArrayList lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void transformarEnFactura(Object ped, ArrayList detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void transformarEnCotizacion(Object ped, ArrayList detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void transformarEnRemito(Object ped, ArrayList detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList convertirAArticulos(ArrayList listado) {
        Articulos articulo;
        Facturar fact=new Articulos(); 
        DetallePedidos detalle=new DetallePedidos();
        ArrayList listadoA=new ArrayList();
        Iterator it=listado.listIterator();
        while(it.hasNext()){
            detalle=(DetallePedidos)it.next();
            articulo=new Articulos();
            if(detalle.getIdArticulo()==0){
             articulo.setNumeroId(detalle.getIdArticulo());
             articulo.setDescripcionArticulo(detalle.getDescripcionArticulo());
             articulo.setIdCombo(0);
             articulo.setPrecioDeCosto(0.00);
            }else{
            articulo=(Articulos) fact.cargarPorCodigoAsignado(detalle.getIdArticulo());
            }
            articulo.setPrecioUnitarioNeto(detalle.getPrecioUnitario());
            articulo.setCantidad(detalle.getCantidad());
            articulo.setIdRenglon(detalle.getId());
            listadoA.add(articulo);
        }
        
        return listadoA;
    }
    
    
}
