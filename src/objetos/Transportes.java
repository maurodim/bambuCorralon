
package objetos;

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

/**
 *
 * @author mauro
 */
public class Transportes implements Personalizable,Componable{
   private Integer id;
   private String descripcion;
   private String direccion;
   private String telefono;
    private String cuit;
    private String localidad;
    private Integer idLocalidad;
    private String provincia;
    private String codigoPostal;
    private String encargado;
    private Integer idProvincia;

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }
    

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Integer getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(Integer idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }
    

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    @Override
    public Boolean agregar(Object objeto) {
        Transportes transporte=(Transportes) objeto;
        Boolean verif=false;
        String sql="insert into transportes (descripcion,direccion,telefono,cuit,idlocalidad,codigopostal,encargado,idprovincia) values ('"+transporte.getDescripcion()+"','"+transporte.getDireccion()+"','"+transporte.getTelefono()+"','"+transporte.getCuit()+"',"+transporte.getIdLocalidad()+",'"+transporte.getCodigoPostal()+"','"+transporte.getEncargado()+"',"+transporte.getIdProvincia()+")";
        Transaccionable tra=new Conecciones();
        verif=tra.guardarRegistro(sql);
        
        return verif;
    }

    @Override
    public Boolean modificar(Object objeto) {
        Transportes transporte=(Transportes) objeto;
        Boolean verif=false;
        String sql="update transportes set descripcion='"+transporte.getDescripcion()+"',direccion='"+transporte.getDireccion()+"',cuit='"+transporte.getCuit()+"',telefono='"+transporte.getTelefono()+"',idlocalidad="+transporte.getIdLocalidad()+",codigopostal='"+transporte.getCodigoPostal()+"',encargado='"+transporte.getEncargado()+"',idprovincia="+transporte.getIdProvincia()+" where id="+transporte.getId();
        Transaccionable tra=new Conecciones();
        verif=tra.guardarRegistro(sql);
        
        return verif;
    }

    @Override
    public Boolean eliminar(Object objeto) {
        Transportes transporte=(Transportes) objeto;
        Boolean verif=false;
        String sql="delete from transportes where id="+transporte.getId();
        Transaccionable tra=new Conecciones();
        verif=tra.guardarRegistro(sql);
        return verif;
    }

    @Override
    public Object buscarPorNumero(Integer id) {
        Transportes transporte=new Transportes();
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros("select *,(select localidades.localidad from localidades where localidades.id=transportes.idlocalidad)as localidad from transportes where id="+id);
       try {
           while(rs.next()){
               transporte.setId(rs.getInt("id"));
               transporte.setDescripcion(rs.getString("descripcion"));
               transporte.setDireccion(rs.getString("direccion"));
               transporte.setTelefono(rs.getString("telefono"));
               transporte.setCuit(rs.getString("cuit"));
               transporte.setIdLocalidad(rs.getInt("idlocalidad"));
               transporte.setLocalidad(rs.getString("localidad"));
               //transporte.setProvincia(rs.getString("provincia"));
               transporte.setEncargado(rs.getString("encargado"));
               transporte.setCodigoPostal(rs.getString("codigopostal"));
               //transporte
               
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(Transportes.class.getName()).log(Level.SEVERE, null, ex);
           return transporte;
       }
       
       return transporte;
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
        ArrayList lisatdo=new ArrayList();
        String sql="select *,(select localidades.localidad from localidades where localidades.id=transportes.idlocalidad)as localidad from transportes order by descripcion";
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        Transportes transporte;
       try {
           while(rs.next()){
               transporte=new Transportes();
               transporte.setId(rs.getInt("id"));
               transporte.setDescripcion(rs.getString("descripcion"));
               transporte.setDireccion(rs.getString("direccion"));
               transporte.setTelefono(rs.getString("telefono"));
               transporte.setCuit(rs.getString("cuit"));
               transporte.setIdLocalidad(rs.getInt("idlocalidad"));
               transporte.setLocalidad(rs.getString("localidad"));
               //transporte.setProvincia(rs.getString("provincia"));
               transporte.setEncargado(rs.getString("encargado"));
               transporte.setCodigoPostal(rs.getString("codigopostal"));
               lisatdo.add(transporte);
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(Transportes.class.getName()).log(Level.SEVERE, null, ex);
       }
       return lisatdo;
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
        DefaultComboBoxModel combo=new DefaultComboBoxModel();
        Iterator it=listado.listIterator();
        Transportes transporte;
        while(it.hasNext()){
            transporte=(Transportes)it.next();
            combo.addElement(transporte.getDescripcion());
        }
        return combo;
    }
   
   
}
