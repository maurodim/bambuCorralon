/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Articulos;

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
import tablas.MiModeloTablaContacto;

/**
 *
 * @author mauro di
 */
public class Rubros implements Personalizable,Rubrable{
    private Integer id;
    private String descripcion;
    private static String sql;
    private static Transaccionable tra=new Conecciones();
    private static ResultSet rs;
    

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

    @Override
    public Boolean agregar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean modificar(Object objeto) {
        Rubros rubro=new Rubros();
        rubro=(Rubros)objeto;
        String sql="update rubros set descripcion='"+rubro.getDescripcion()+"' where id="+rubro.getId();
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        return true;
    }

    @Override
    public Boolean eliminar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscarPorNumero(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        ArrayList listado=new ArrayList();
        String sql="select * from rubros order by descripcion";
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Rubros rubro=new Rubros();
                rubro.setId(rs.getInt("id"));
                rubro.setDescripcion(rs.getString("descripcion"));
                listado.add(rubro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Rubros.class.getName()).log(Level.SEVERE, null, ex);
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
    public Integer nuevo(Object rubro) {
        Rubros rubros=new Rubros();
        rubros=(Rubros)rubro;
        String sql="insert into rubros (descripcion) values ('"+rubros.getDescripcion()+"')";
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        int ultimo=0;
        sql="select LAST_INSERT_ID()";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                ultimo=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Rubros.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ultimo;
    }

    @Override
    public void modificarPrecioRubro(Integer idRubro, Double precio) {
        Double coe=precio / 100;
        coe=coe + 1;
        System.out.println("resultado :"+coe);
        sql="update articulos set precio=round((precio * "+coe+"),4) where idrubro="+idRubro;
        
        tra.guardarRegistro(sql);
        
    }

    @Override
    public ArrayList listarPorRubro(Integer idRubro) {
ArrayList listado=new ArrayList();
        Rubros subRubro;
        String sql="select * from rubros order by descripcion";
        
        rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                subRubro=new Rubros();
                subRubro.setDescripcion(rs.getString("descripcion"));
                subRubro.setId(rs.getInt("id"));
                listado.add(subRubro);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SubRubros.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public ArrayList listarPorSubRubro(ArrayList idSubRubro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Integer idRubro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarCostoPorRubro(Integer idRubro, Double precio) {
        Double coe=precio / 100;
        coe=coe + 1;
        System.out.println("resultado :"+coe);
        String sql="update articulos set costo=round((costo * "+coe+"),4) where idrubro="+idRubro;
        
        tra.guardarRegistro(sql);
    }

    @Override
    public DefaultTableModel mostrarListado(ArrayList listado) {
        MiModeloTablaContacto mod=new MiModeloTablaContacto();
        Iterator it=listado.listIterator();
        Rubros rubro;
        mod.addColumn("Seleccion");
        mod.addColumn("Rubros");
        Object[] fila=new Object[2];
        while(it.hasNext()){
            rubro=(Rubros)it.next();
            fila[0]=false;
            fila[1]=rubro.getDescripcion();
            mod.addRow(fila);
        }
        return mod;
    }

    @Override
    public DefaultListModel mostrarEnCombo(ArrayList listado) {
        DefaultListModel modelo=new DefaultListModel();
        Rubros rubro=new Rubros();
        Iterator it=listado.listIterator();
        while(it.hasNext()){
            rubro=(Rubros)it.next();
            modelo.addElement(rubro.getId()+" "+rubro.getDescripcion());
        }
        return modelo;
    }

    @Override
    public ArrayList buscar(String texto) {
        ArrayList resultado=new ArrayList();
        Rubros rubro=null;
        sql="select * from rubros where descripcion like '%"+texto+"%'";
        rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
               rubro=new Rubros();
               rubro.setId(rs.getInt("id"));
               rubro.setDescripcion(rs.getString("descripcion"));
               resultado.add(rubro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Rubros.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public DefaultComboBoxModel mostrarEnBox(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
