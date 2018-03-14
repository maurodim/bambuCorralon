/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recibos;

import interfaces.Transaccionable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import objetos.Conecciones;

/**
 *
 * @author mauro di
 */
public class Recibo implements Recidable{
    private Integer id;
    private Integer idCliente;
    private Double monto;
    private Date fecha;
    private static Transaccionable tra=new Conecciones();
    private static ResultSet rs;
    private String sql;

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
        Recibo recibo=new Recibo();
        recibo=(Recibo)rec;
        int numero=0;
        sql="insert into recibos (idcliente,monto) values ("+recibo.getIdCliente()+","+recibo.getMonto()+")";
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
        return numero;
    }

    @Override
    public ArrayList listar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    
    
}
