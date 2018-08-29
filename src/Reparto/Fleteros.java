/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reparto;

import Reparto.interfaces.Procesos;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Conecciones;

/**
 *
 * @author Administrador
 */
public class Fleteros implements Procesos{
    private Integer numeroFletero;
    private String nombreFletero;
    private String celularFletero;
    private Integer condicion;

    public Integer getCondicion() {
        return condicion;
    }

    public void setCondicion(Integer condicion) {
        this.condicion = condicion;
    }

    public String getCelularFletero() {
        return celularFletero;
    }

    public void setCelularFletero(String celularFletero) {
        this.celularFletero = celularFletero;
    }

    public String getNombreFletero() {
        return nombreFletero;
    }

    public void setNombreFletero(String nombreFletero) {
        this.nombreFletero = nombreFletero;
    }

    public Integer getNumeroFletero() {
        return numeroFletero;
    }

    public void setNumeroFletero(Integer numeroFletero) {
        this.numeroFletero = numeroFletero;
    }
    
    private ArrayList ListadoDeFleteros(){
            //Fleteros conductor=new Fleteros();
       //     Connection cp=cn.ObtenerConeccion();
            ArrayList lista=new ArrayList();
            String sql="select * from fleteros order by numero";
            Transaccionable tra=new Conecciones();
            ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Fleteros conductor=new Fleteros();
                conductor.setNumeroFletero(rs.getInt("numero"));
                conductor.setNombreFletero(rs.getString("nombre"));
                conductor.setCelularFletero(rs.getString("celular"));
                lista.add(conductor);
            }
             rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Fleteros.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       //     cn.CerrarConneccion(cp);
            return lista;
        }
    @Override
    public ArrayList detallePedidosParaCorreccion(String numeroPedido, String fecha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList ListarVehiculos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer cargarHdrVehiculo(int lst, String fecha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardarAsignacionDeVehiculos(ArrayList lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList ListarPedidosPorVehiculo(int idUnidad, ArrayList lstPedidos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList ListarPedidosPorZona(String fecha, int zonaSeleccionada) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList ListarPedidosParaCargaEnVehiculo(int vehiculo, String fecha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList ListarFleteros() {
        return this.ListadoDeFleteros();
    }

    @Override
    public ArrayList actualizarVistaHdr(int vehiculo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
