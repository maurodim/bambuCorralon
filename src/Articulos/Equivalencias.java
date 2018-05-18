/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Articulos;

import interfaces.Componable;
import interfaces.Personalizable;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import objetos.Conecciones;

/**
 *
 * @author mauro
 */
public class Equivalencias implements Personalizable,Componable{
    private Integer id;
    private String descripcion;
    private Double coeficiente;
    private String simbolo;

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(Double coeficiente) {
        this.coeficiente = coeficiente;
    }

    @Override
    public Integer agregar(Object objeto) {
        int id=0;
        Equivalencias equivalencia=(Equivalencias) objeto;
        String sql="insert into equivalencias (descripcion,coeficiente,simbolo) values ('"+equivalencia.getDescripcion()+"',"+equivalencia.getCoeficiente()+",'"+equivalencia.getSimbolo()+"')";
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        sql="select last_insert_id()";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                id=rs.getInt(1);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Equivalencias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public Boolean modificar(Object objeto) {
        Boolean id=true;
        Equivalencias equivalencia=(Equivalencias) objeto;
        String sql="update equivalencias set descripcion='"+equivalencia.getDescripcion()+"', coeficiente="+equivalencia.getCoeficiente()+",simbolo='"+equivalencia.getSimbolo()+"' where id="+equivalencia.getId();
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        return id;
    }

    @Override
    public Boolean eliminar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscarPorNumero(Integer id) {
        Equivalencias equivalencia = null;
        String sql="select * from equivalencias where id="+id;
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                equivalencia=new Equivalencias();
                equivalencia.id=rs.getInt("id");
                equivalencia.descripcion=rs.getString("descripcion");
                equivalencia.coeficiente=rs.getDouble("coeficiente");
                equivalencia.simbolo=rs.getString("simbolo");
                //listado.add(equivalencia);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Equivalencias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equivalencia;
    }

    @Override
    public Object buscarPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscarPorCuit(String cuit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listar() {
        ArrayList<Equivalencias> listado=new ArrayList();
        Equivalencias equivalencia;
        String sql="select * from equivalencias order by descripcion";
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                equivalencia=new Equivalencias();
                equivalencia.id=rs.getInt("id");
                equivalencia.descripcion=rs.getString("descripcion");
                equivalencia.coeficiente=rs.getDouble("coeficiente");
                equivalencia.simbolo=rs.getString("simbolo");
                listado.add(equivalencia);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Equivalencias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public ArrayList listarPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorCuit(String cuit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultListModel LlenarList(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel LlenarTabla(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComboBoxModel LlenarCombo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultListModel LlenarListConArray(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel LlenarTablaConArray(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultComboBoxModel LlenarComboConArray(ArrayList listado) {
        Iterator it=listado.listIterator();
        Equivalencias equivalencia;
        DefaultComboBoxModel modelo=new DefaultComboBoxModel();
        while(it.hasNext()){
            equivalencia=(Equivalencias) it.next();
            modelo.addElement(equivalencia.descripcion);
        }
        return modelo;
    }
    
    
}
