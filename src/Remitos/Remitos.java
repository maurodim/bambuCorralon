/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Remitos;

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
public class Remitos implements Remitable{
    private Integer idCliente;
    private Date fecha;
    private Integer idComprobante;
    private Integer tipoComprobantte;
    private String observaciones;
    private Integer numeroDeRemito;
    private Integer id;
    private String domicilioDeEntrega;
    private String localidad;
    private Integer cantidadBultos;
    private Integer tipoBulto;
    
    private String sql;
    private static Transaccionable tra=new Conecciones();
    private static ResultSet rs;

    public String getDomicilioDeEntrega() {
        return domicilioDeEntrega;
    }

    public void setDomicilioDeEntrega(String domicilioDeEntrega) {
        this.domicilioDeEntrega = domicilioDeEntrega;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Integer getCantidadBultos() {
        return cantidadBultos;
    }

    public void setCantidadBultos(Integer cantidadBultos) {
        this.cantidadBultos = cantidadBultos;
    }

    public Integer getTipoBulto() {
        return tipoBulto;
    }

    public void setTipoBulto(Integer tipoBulto) {
        this.tipoBulto = tipoBulto;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(Integer idComprobante) {
        this.idComprobante = idComprobante;
    }

    public Integer getTipoComprobantte() {
        return tipoComprobantte;
    }

    public void setTipoComprobantte(Integer tipoComprobantte) {
        this.tipoComprobantte = tipoComprobantte;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getNumeroDeRemito() {
        return numeroDeRemito;
    }

    public void setNumeroDeRemito(Integer numeroDeRemito) {
        this.numeroDeRemito = numeroDeRemito;
    }


    @Override
    public Integer nuevo(Object remi) {
        Remitos remito=new Remitos();
        remito=(Remitos)remi;
        //System.out.println("es una prueba de carga :"+this.getIdCliente()+" observaciones: "+this.getObservaciones());
        
        sql="insert into remitos (idcliente,tipocomprobante,observaciones,numeroremito,idcomprobante,domicilio,localidad,cantidad,tipoBulto) values ("+remito.getIdCliente()+","+remito.getTipoComprobantte()+",'"+remito.getObservaciones()+"',(select tipocomprobantes.numeroActivo + 1 from tipocomprobantes where id=7),"+remito.getIdComprobante()+",'"+remito.getDomicilioDeEntrega()+"','"+remito.getLocalidad()+"',"+remito.getCantidadBultos()+","+remito.getTipoBulto()+")";
        tra.guardarRegistro(sql);
        sql="select LAST_INSERT_ID()";
        rs=tra.leerConjuntoDeRegistros(sql);
        int numeroId=0;
        try{
        while(rs.next()){
            numeroId=rs.getInt(1);
        }
        }catch(SQLException ex) {
            Logger.getLogger(Remitos.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql="update tipocomprobantes set numeroactivo=numeroactivo +1 where id=7";
        tra.guardarRegistro(sql);
        if(remito.getTipoComprobantte()==5){
            sql="update pedidos set estado=2, idremito="+numeroId+" where id="+remito.getIdComprobante();
        }else{
            sql="update facturas set estado=2,idremito="+numeroId+" where id="+remito.getIdComprobante();
        }
        tra.guardarRegistro(sql);
        return numeroId;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

    @Override
    public Object carga(Integer id) {
        Remitos remito=new Remitos();
        sql="select * from remitos where id="+id;
        rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                remito.setId(rs.getInt("id"));
                remito.setIdCliente(rs.getInt("idcliente"));
                remito.setNumeroDeRemito(rs.getInt("numeroremito"));
                remito.setObservaciones(rs.getString("observaciones"));
                remito.setIdComprobante(rs.getInt("idcomprobante"));
                remito.setDomicilioDeEntrega(rs.getString("domicilio"));
                remito.setLocalidad(rs.getString("localidad"));
                remito.setCantidadBultos(rs.getInt("cantidad"));
                remito.setTipoBulto(rs.getInt("tipoBulto"));
            
            }   
        } catch (SQLException ex) {
            Logger.getLogger(Remitos.class.getName()).log(Level.SEVERE, null, ex);
        }
    return remito;
    
    }
}
