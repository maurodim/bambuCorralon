/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actualizaciones;

import Proveedores.Proveedores;
import Depositos.Depositos;
import Sucursales.Cajas;
import Sucursales.ListasDePrecios;
import Sucursales.Sucursales;
import Sucursales.Usuarios;
import facturacion.clientes.Clientes;
import interfaceGraficas.Inicio;
import interfaces.Transaccionable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import Articulos.Articulos;
import objetos.Conecciones;

/**
 *
 * @author mauro
 */
public class Actualiza1 extends Thread{
     @Override
    public void run(){
        Timer timer=new Timer(300000,new ActionListener(){ 
            @Override
    public void actionPerformed(ActionEvent e) 
    { 
        Inicio.actualizable=0;
        //System.err.println("COMIENZO DEL CICLO DE RELOJ *******************************");
        //ActOt at=new ActOt();
        //at.start();
        
            //Inicio.coneccionRemota=true;
            //VerificarErrores();
        
            //carga la lista remota
            //Proveedores.cargarListadoProv1();
        /*
        Integer idDep=Inicio.usuario.getEquipo() / 1000000;
        String sql="select * from actualizaciones where iddeposito="+idDep+" and estado < 4 and idobjeto=1 order by estado";
        Transaccionable tra=new Conecciones();
        Integer estado=0;
        ResultSet rx=tra.leerConjuntoDeRegistros(sql);
                try {
                    while(rx.next()){
                        Inicio.actualizable=1;
                        estado=rx.getInt("estado");
                    }
                    //if(estado > 0)estado++;
                    rx.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Actualiza1.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        if(Inicio.actualizable==1){
            Articulos.RecargarMap(estado);
        
        Articulos.BackapearMap(estado);
        Inicio.actualizable=0;
        tra=new Conecciones();
        sql="update actualizaciones set estado=4 where iddeposito="+idDep+" and idobjeto=1 and estado="+estado;
        tra.guardarRegistro(sql);
        System.out.println(sql);
        estado=0;
        }
        sql="select * from actualizaciones where estado < 4 and idobjeto=1 order by estado";
        Transaccionable tat=new Conecciones();
        int ver=0;
        rx=tat.leerConjuntoDeRegistros(sql);
                try {
                    while(rx.next()){
                        //Inicio.actualizable=1;
                        ver=1;
                    }
                    //if(estado > 0)estado++;
                    rx.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Actualiza1.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(ver==0){
                    sql="update articulos set actualizacion=1 where actualizacion=0";
                    tat.guardarRegistro(sql);
                }
        
        
         * Usuarios
         * Sucursales
         * Depositos
         * Comprobante
         * ACTUALIZAR EL NUMERO DE CAJA ADMINISTRADORA
         */
      
     } 
}); 
        timer.start();
        
        
        
    }  
}
