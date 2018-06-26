/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vendedores;

import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import objetos.Conecciones;

/**
 *
 * @author mauro
 */
public class Vendedores implements Vendable{
    private Integer id;
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Integer nuevo(Object vend) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object cargar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listar() {
        Transaccionable tra=new Conecciones();
        ArrayList <Vendedores> listado=new ArrayList();
        String sql="select * from vendedores order by nombre";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        Vendedores vendedor;
        try {
            while(rs.next()){
                vendedor=new Vendedores();
                vendedor.id=rs.getInt("id");
                vendedor.nombre=rs.getString("nombre");
                listado.add(vendedor);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Vendedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public DefaultComboBoxModel mostrarEnCombo(ArrayList listado) {
        DefaultComboBoxModel combo=new DefaultComboBoxModel();
        Iterator it=listado.listIterator();
        Vendedores vendedor;
        while(it.hasNext()){
            vendedor=(Vendedores) it.next();
            combo.addElement(vendedor.nombre);
        }
        return combo;
    }
    
    
}
