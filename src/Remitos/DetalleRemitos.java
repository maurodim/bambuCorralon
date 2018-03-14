/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Remitos;

import Articulos.Articulos;
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
public class DetalleRemitos implements Remitable{
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
        DetalleRemitos detalle=new DetalleRemitos();
        detalle=(DetalleRemitos)remi;
        int id=0;
        if(detalle.getIdFactura() == null){
            sql="insert into detalleremitos (idremito,idarticulo,cantidadremitida,descrpcionarticulo,idpedido,idfactura) values ("+detalle.getIdRemito()+","+detalle.getIdArticulo()+","+detalle.getCantidadRemitida()+",'"+detalle.descripcionArticulo+"',"+detalle.getIdPedido()+",0)";
            
        }else{
            sql="insert into detalleremitos (idremito,idarticulo,cantidadremitida,descrpcionarticulo,idfactura) values ("+detalle.getIdRemito()+","+detalle.getIdArticulo()+","+detalle.getCantidadRemitida()+",'"+detalle.descripcionArticulo+"',"+detalle.getIdFactura()+")";
        }
        tra.guardarRegistro(sql);
        sql="update detallefacturas set cantidadremitida="+detalle.getCantidadRemitida()+" where idfactura="+detalle.getIdFactura()+" and idarticulo="+detalle.getIdArticulo();
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
        DetalleRemitos detRem;
        sql="select * from detalleremitos where idremito="+remi;
        rs=tra.leerConjuntoDeRegistros(sql);
        try{
        while(rs.next()){
            detRem=new DetalleRemitos();
            detRem.setCantidadFacturada(rs.getDouble("cantidadfacturada") - rs.getDouble("cantidadremitida"));
            detRem.setIdArticulo(rs.getInt("idarticulo"));
            detRem.setDescripcionArticulo(rs.getString("descrpcionarticulo"));
            detRem.setCantidadRemitida(rs.getDouble("cantidadremitida"));
            detalle.add(detRem);
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(DetalleRemitos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detalle;
 
    }

    @Override
    public Object carga(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
