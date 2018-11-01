/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reparto;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import Reparto.DetalleHdr;
import Reparto.EncabezadoHdr;
import objetos.Conecciones;


/**
 *
 * @author Administrador
 */
public class GuardarRecepcionHdr extends Thread{
    private ArrayList encabed=new ArrayList();
    private EncabezadoHdr en=new EncabezadoHdr();
    private Conecciones con;
    private Connection cgr;
    
    public void setEn(EncabezadoHdr en) {
        this.en = en;
    }
    
    
    public void setEncabed(ArrayList encabed) {
        this.encabed = encabed;
    }

    public GuardarRecepcionHdr() {
        con = new Conecciones();
        cgr = con.obtenerConeccion();
    }
    
    public synchronized void run(){
        DecimalFormat fr=new DecimalFormat("00");
        String sql="";
        EncabezadoHdr enc=new EncabezadoHdr();
        DetalleHdr detalle=new DetalleHdr();
        Statement st = null;
  
            try {
                enc=this.en;
                st=cgr.createStatement();
                String hI=fr.format(enc.getHoraSalida());
                String mI=fr.format(enc.getMinutosSalida());
                String hS=fr.format(enc.getHoraLlegada());
                String mS=fr.format(enc.getMinutosLlegada());
                String horaSalida=hI+":"+mI;
                String horaLlegada=hS+":"+mS;
                sql="update hdr set kmInicio="+enc.getKmIniciales()+",kmFinal="+enc.getKmFinales()+",horaInicio='"+horaSalida+"',horaFinal='"+horaLlegada+"' where numero="+enc.getNumero();
                System.err.println(sql);
                st.executeUpdate(sql);
                sql="update unidades set kilometrosActuales="+enc.getKmFinales()+" where numero="+enc.getNumeroVehiculo();
                st.executeUpdate(sql);
            Iterator ie=encabed.listIterator();
            while(ie.hasNext()){
                detalle=(DetalleHdr)ie.next();
                int ent=0;
                if(detalle.getEntregaCompletada()){
                    ent=0;
                }else{
                    ent=1;
                }
                sql="update detalle_hdr set entregado="+ent+",motivoFallido='"+detalle.getMotivoFaltaDeEntrega()+"',reenviar="+detalle.getReenviarPedido()+",comprobante='"+detalle.getNumeroDeComprobante()+"',importe='"+detalle.getSaldo()+"' where numero="+detalle.getNumero();
                st.executeUpdate(sql);
                System.err.println("motivos falta de entrega "+sql);
            }

            } catch (SQLException ex) {
                Logger.getLogger(GuardarRecepcionHdr.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(GuardarRecepcionHdr.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
}
