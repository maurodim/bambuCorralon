/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes.Objectos;

import Clientes.Interfaces.Ajustable;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Conecciones;

/**
 *
 * @author mauro
 */
public class Ajustes implements Ajustable{
    private Integer id;
    private Integer idCliente;
    private Double montoMovimiento;
    private String observaciones;

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

    public Double getMontoMovimiento() {
        return montoMovimiento;
    }

    public void setMontoMovimiento(Double montoMovimiento) {
        this.montoMovimiento = montoMovimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    private void nuevoAjuste(){
        Transaccionable tra=new Conecciones();
        String sql="insert into ajustes (idcliente,monto,observaciones) values ("+this.idCliente+","+this.montoMovimiento+",'"+this.observaciones+"')";
        System.out.println(sql);
        tra.guardarRegistro(sql);
        ResultSet rs=tra.leerConjuntoDeRegistros("select last_insert_id()");
        //Integer numero=0;
        try {
            while(rs.next()){
                this.id=rs.getInt(1);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Ajustes.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return numero;
    }

    @Override
    public Integer NuevoAjuste(Object ajuste) {
       // Ajustes aju=new Ajustes();
        Ajustes aju=(Ajustes) ajuste;
        aju.nuevoAjuste();
        return aju.id;
    }
}
