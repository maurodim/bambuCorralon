/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores.objetos;

import Recibos.*;
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
public class OrdenDePago implements Recidable{
    private Integer id;
    private Integer idCliente;
    private Double monto;
    private Date fecha;
    private static Transaccionable tra=new Conecciones();
    private static ResultSet rs;
    private String sql;
    private String numeroRecibo;
    private Double saldoProveedor;

    public Double getSaldoProveedor() {
        return saldoProveedor;
    }

    public void setSaldoProveedor(Double saldoProveedor) {
        this.saldoProveedor = saldoProveedor;
    }
    

    public String getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(String numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
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

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
      }

    @Override
    public Integer nuevo(Object rec) {
        OrdenDePago recibo=new OrdenDePago();
        recibo=(OrdenDePago)rec;
        int numero=0;
        sql="insert into ordendepagos (idcliente,monto,numerorecibo,saldo) values ("+recibo.getIdCliente()+","+recibo.getMonto()+",'"+recibo.getNumeroRecibo()+"',"+recibo.getSaldoProveedor()+")";
        tra.guardarRegistro(sql);
        sql="select LAST_INSERT_ID()";
        rs=tra.leerConjuntoDeRegistros(sql);
        try{
        while(rs.next()){
            numero=rs.getInt(1);
        }
        }catch(SQLException ex){
            System.err.println("error "+ex);
        }
        Double monto=recibo.getMonto() * (-1);
        sql="insert into movimientosproveedores (numeroProveedor,monto,numeroComprobante,idUsuario,tipoComprobante,subtotal,saldo,idcomprobante) values ("+recibo.getIdCliente()+",round("+monto+",4),'"+recibo.getNumeroRecibo()+"',1,2,0.00,0.00,"+numero+")";
        tra.guardarRegistro(sql);
        return numero;
    }

    @Override
    public ArrayList listar(Integer id) {
        OrdenDePago recibo;
        //recibo=(OrdenDePago)rec;
        ArrayList numero=new ArrayList();
        sql="select * from ordendepagos where id="+id;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                recibo=new OrdenDePago();
                recibo.setId(rs.getInt("id"));
                recibo.setFecha(rs.getDate("fecha"));
                recibo.setIdCliente(rs.getInt("idcliente"));
                recibo.setMonto(rs.getDouble("monto"));
                recibo.setNumeroRecibo(rs.getString("numerorecibo"));
                numero.add(recibo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return numero;
    }

    @Override
    public Double imputarAFactura(Object rec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel mostrarARecibir(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public DefaultTableModel mostrarARecibirSuma(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object cargar(Integer id) {
        OrdenDePago recibo = null;
        //recibo=(OrdenDePago)rec;
        //ArrayList numero=new ArrayList();
        sql="select * from ordendepagos where id="+id;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                recibo=new OrdenDePago();
                recibo.setId(rs.getInt("id"));
                recibo.setFecha(rs.getDate("fecha"));
                recibo.setIdCliente(rs.getInt("idcliente"));
                recibo.setMonto(rs.getDouble("monto"));
                recibo.setNumeroRecibo(rs.getString("numerorecibo"));
                //numero.add(recibo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return recibo;
    }
    
    
    
}
