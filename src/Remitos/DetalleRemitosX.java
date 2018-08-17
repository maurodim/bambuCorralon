/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Remitos;

import Articulos.Articulos;
import interfaceGraficas.Inicio;
import interfaces.Transaccionable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import objetos.Conecciones;

/**
 *
 * @author mauro di
 */
public class DetalleRemitosX implements Remitable{
    private Integer idRemito;
    private Integer idArticulo;
    private Double cantidadRemitida;
    private String descripcionArticulo;
    private Double cantidadPedida;
    private Double cantidadFacturada;
    private Integer idFactura;
    private Integer idPedido;
    private Date fecha;
    private static Transaccionable tra=new Conecciones();
    private String sql;
    private static ResultSet rs;
    private Integer idCliente;
    private Integer idUsuario;
    private Double precioDeCosto;
    private Double precioDeVenta;
    private Integer idCaja;

    public Double getPrecioDeCosto() {
        return precioDeCosto;
    }

    public void setPrecioDeCosto(Double precioDeCosto) {
        this.precioDeCosto = precioDeCosto;
    }

    public Double getPrecioDeVenta() {
        return precioDeVenta;
    }

    public void setPrecioDeVenta(Double precioDeVenta) {
        this.precioDeVenta = precioDeVenta;
    }

    public Integer getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Integer idCaja) {
        this.idCaja = idCaja;
    }
    
    

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    
    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Double getCantidadRemitida() {
        return cantidadRemitida;
    }

    public void setCantidadRemitida(Double cantidadRemitida) {
        this.cantidadRemitida = cantidadRemitida;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public Double getCantidadPedida() {
        return cantidadPedida;
    }

    public void setCantidadPedida(Double cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public Double getCantidadFacturada() {
        return cantidadFacturada;
    }

    public void setCantidadFacturada(Double cantidadFacturada) {
        this.cantidadFacturada = cantidadFacturada;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
    

    @Override
    public Integer nuevo(Object remi) {
        DetalleRemitosX detalle=new DetalleRemitosX();
        detalle=(DetalleRemitosX)remi;
        int id=0;
        if(detalle.getIdFactura() == null){
            sql="insert into detalleremx (idremito,idarticulo,cantidadremitida,descrpcionarticulo,idpedido,idfactura) values ("+detalle.getIdRemito()+","+detalle.getIdArticulo()+","+detalle.getCantidadRemitida()+",'"+detalle.descripcionArticulo+"',"+detalle.getIdPedido()+",0)";
            
        }else{
            sql="insert into detalleremx (idremito,idarticulo,cantidadremitida,descrpcionarticulo,idfactura) values ("+detalle.getIdRemito()+","+detalle.getIdArticulo()+","+detalle.getCantidadRemitida()+",'"+detalle.descripcionArticulo+"',"+detalle.getIdFactura()+")";
        }
        tra.guardarRegistro(sql);
        sql="update detallepedidos set cantidadpendiente=cantidad - "+detalle.getCantidadRemitida()+" where idpedido="+detalle.getIdPedido()+" and idarticulo="+detalle.getIdArticulo();
        //sql="update detallefacturas set cantidadremitida="+detalle.getCantidadRemitida()+" where idfactura="+detalle.getIdFactura()+" and idarticulo="+detalle.getIdArticulo();
        tra.guardarRegistro(sql);
        sql="insert into movimientosarticulos (tipomovimiento,idarticulo,cantidad,numerodeposito,tipocomprobante,numerocomprobante,numerocliente,numerousuario,preciodecosto,preciodeventa,estado,idcaja,fechaComprobante) values (6,"+detalle.getIdArticulo()+",round("+detalle.getCantidadRemitida()+" * -1,2),1,14,"+detalle.getIdRemito()+","+detalle.getIdCliente()+","+detalle.getIdUsuario()+","+detalle.getPrecioDeCosto()+","+detalle.getPrecioDeVenta()+",1,"+detalle.getIdCaja()+",'"+Inicio.fechaDia+"')";
        System.out.println(sql);
        tra.guardarRegistro(sql);
        return id;
    }

    @Override
    public Boolean modificar(Object remi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean eliminar(Object remi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel mostrarListado(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean actualizarRegistros(Object remi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPendientesPorCliente(Integer idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList cargarDetalle(Integer remi) {
        ArrayList detalle=new ArrayList();
        DetalleRemitosX detRem;
        sql="select * from detalleremx where idremito="+remi;
        rs=tra.leerConjuntoDeRegistros(sql);
        try{
        while(rs.next()){
            detRem=new DetalleRemitosX();
            detRem.setCantidadFacturada(rs.getDouble("cantidadfacturada") - rs.getDouble("cantidadremitida"));
            detRem.setIdArticulo(rs.getInt("idarticulo"));
            detRem.setDescripcionArticulo(rs.getString("descrpcionarticulo"));
            detRem.setCantidadRemitida(rs.getDouble("cantidadremitida"));
            detalle.add(detRem);
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(DetalleRemitosX.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detalle;
 
    }

    @Override
    public Object carga(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
