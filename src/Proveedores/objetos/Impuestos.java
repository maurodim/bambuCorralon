/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores.objetos;

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
public class Impuestos implements Personalizable,Componable,Proveer{
    private int id;
    private Double tasa;
    private String descripcion;
    private Integer idFactura;
    private Double monto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTasa() {
        return tasa;
    }

    public void setTasa(Double tasa) {
        this.tasa = tasa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    @Override
    public Integer agregar(Object objeto) {
        Impuestos impu=(Impuestos) objeto;
        int id=0;
        String sql="insert into impuestos (descripcion,tasa) values ('"+impu.getDescripcion()+"',"+impu.getTasa()+")";
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        ResultSet rs=tra.leerConjuntoDeRegistros("SELECT LAST_INSERT_ID()");
        try {
            while(rs.next()){
                id=rs.getInt(1);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Impuestos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public Boolean modificar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        Impuestos impu;
        ArrayList listado=new ArrayList();
        String sql="select id,descripcion,round(tasa,4) as tasa from impuestos";
        Transaccionable tra=new Conecciones();
        
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                impu=new Impuestos();
                impu.setId(rs.getInt("id"));
                impu.setDescripcion(rs.getString("descripcion"));
                impu.setTasa(rs.getDouble("tasa"));
                listado.add(impu);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Impuestos.class.getName()).log(Level.SEVERE, null, ex);
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
        DefaultTableModel modelo=new DefaultTableModel();
        Iterator it=listado.listIterator();
        Impuestos imp;
        modelo.addColumn("ID");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("TASA");
        Object[] fila=new Object[3];
        Double porciento=0.00;
        while(it.hasNext()){
            imp=(Impuestos) it.next();
            fila[0]=String.valueOf(imp.getId());
            fila[1]=imp.getDescripcion();
            porciento=imp.getTasa() * 100;
            porciento=Math.round(porciento * 10000.0) / 10000.0;
            fila[2]=String.valueOf(porciento);
            modelo.addRow(fila);
        }
        return modelo;
        
        
    }

    @Override
    public DefaultComboBoxModel LlenarComboConArray(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean GuardarImpuestos(ArrayList lista,Integer idFactura) {
        Iterator it=lista.listIterator();
        Impuestos impuesto;
        String sql;
        Transaccionable tra=new Conecciones();
        while(it.hasNext()){
            impuesto=(Impuestos) it.next();
            sql="insert into movimientosimpuestos (idfactura,idimpuesto,monto) values ("+idFactura+","+impuesto.id+","+impuesto.monto+")";
            tra.guardarRegistro(sql);
        }
        return true;
    }

    @Override
    public ArrayList LeerImpuestos(Integer idFactura) {
        Impuestos impu;
        ArrayList listado=new ArrayList();
        String sql="select movimientosimpuestos.idfactura,movimientosimpuestos.monto, impuestos.id,impuestos.descripcion,impuestos.tasa from movimientosimpuestos left join impuestos on impuestos.id=movimientosimpuestos.idimpuesto where movimientosimpuestos.idfactura="+idFactura;
        System.out.println(sql);
        Transaccionable tra=new Conecciones();
        
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                impu=new Impuestos();
                impu.setId(rs.getInt("id"));
                impu.setDescripcion(rs.getString("descripcion"));
                impu.setTasa(rs.getDouble("tasa"));
                impu.setIdFactura(rs.getInt("idfactura"));
                impu.setMonto(rs.getDouble("monto"));
                
                listado.add(impu);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Impuestos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public ArrayList ListarDetalleFactura(Integer idFactura, Integer tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
