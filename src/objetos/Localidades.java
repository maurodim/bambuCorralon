/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import Articulos.Rubrable;
import interfaces.Componable;
import interfaces.Personalizable;
import interfaces.Transaccionable;
import interfacesPrograma.Busquedas;
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

/**
 *
 * @author mauro di
 */
public class Localidades implements Busquedas,Personalizable,Componable,Rubrable{
    private Integer id;
    private String descripcion;
    private Integer provincia;
    private String descripcionProvincia;
    private String codigoPostal;
    private static Transaccionable tra=new Conecciones();
    private static String sql;
    private static ResultSet rs;

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
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

    public Integer getProvincia() {
        return provincia;
    }

    public void setProvincia(Integer provincia) {
        this.provincia = provincia;
    }

    public String getDescripcionProvincia() {
        return descripcionProvincia;
    }

    public void setDescripcionProvincia(String descripcionProvincia) {
        this.descripcionProvincia = descripcionProvincia;
    }

    @Override
    public ArrayList buscar(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList filtrar(String numeroCliente, String nombreCliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listar(String cliente) {
        ArrayList listado=new ArrayList();
        
        sql="select * from localidades order by localidad";
        rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Localidades localidad=new Localidades();
                localidad.setId(rs.getInt("id"));
                localidad.setDescripcion(rs.getString("localidad"));
                localidad.setProvincia(rs.getInt("id_provincia"));
                localidad.setCodigoPostal(rs.getString("codigo_postal"));
                listado.add(localidad);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Localidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listado;
    }

    @Override
    public ArrayList listarPorContacto(String cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorFantasia(String cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void marcarContactado(Integer item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarDatosCliente(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean agregar(Object objeto) {
        Localidades localidad;
        localidad=(Localidades) objeto;
        sql="insert into localidades (localidad,codigo_postal,id_provincia,codigo_interno) values ('"+localidad.getDescripcion()+"','"+localidad.getCodigoPostal()+"',"+localidad.getProvincia()+",(select last_insert_id()))";
        tra.guardarRegistro(sql);
        return true;
        
    }

    @Override
    public Boolean modificar(Object objeto) {
        Localidades localidad;
        localidad=(Localidades) objeto;
        sql="update localidades set localidad='"+localidad.getDescripcion()+"',codigo_postal='"+localidad.getCodigoPostal()+"',id_provincia="+localidad.getProvincia()+" where id="+localidad.getId();
        tra.guardarRegistro(sql);
        return true;
    }

    @Override
    public Boolean eliminar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscarPorNumero(Integer id) {
        Localidades localidad=new Localidades();
        sql="select * from localidades where id="+id;
        rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                localidad.setDescripcion(rs.getString("localidad"));
                localidad.setId(rs.getInt("id"));
                localidad.setCodigoPostal(rs.getString("codigo_postal"));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Localidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return localidad;
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
        
        sql="select * from localidades order by localidad";
        rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Localidades localidad=new Localidades();
                localidad.setId(rs.getInt("id"));
                localidad.setDescripcion(rs.getString("localidad"));
                localidad.setProvincia(rs.getInt("id_provincia"));
                localidad.setCodigoPostal(rs.getString("codigo_postal"));
                listado.add(localidad);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Localidades.class.getName()).log(Level.SEVERE, null, ex);
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
    public Integer nuevo(Object rubro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarPrecioRubro(Integer idRubro, Double precio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorRubro(Integer idRubro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel mostrarListado(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultListModel mostrarEnCombo(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultComboBoxModel mostrarEnBox(ArrayList listado) {
        Iterator it=listado.listIterator();
        DefaultComboBoxModel modelo=new DefaultComboBoxModel();
        Localidades localidad;
        while(it.hasNext()){
            localidad=(Localidades) it.next();
            modelo.addElement(localidad.getDescripcion());
        }
        return modelo;
    }

    @Override
    public DefaultComboBoxModel LlenarComboConArray(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
