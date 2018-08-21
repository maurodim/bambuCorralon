/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reparto;

import Objetos.PdfConsolidado;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Conecciones;


/**
 *
 * @author mauro
 */
public class EmisionDeListadosDeMaterialesConsolidadosR extends Thread{
    private static Connection cc1;
    private String fechaEnvio;
    private Integer numVehiculo;
    private String descVehiculo;
    private Double total;
    private Integer numeroListado;
    private Integer revision;

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public void setDescVehiculo(String descVehiculo) {
        this.descVehiculo = descVehiculo;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public void setNumVehiculo(Integer numVehiculo) {
        this.numVehiculo = numVehiculo;
    }

    public void setNumeroListado(Integer numeroListado) {
        this.numeroListado = numeroListado;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    
    public synchronized void run(){
        Map listConsolidado=new HashMap();
         System.out.println("OJOOOOOOOOO  lpm "+this.numeroListado+" / total "+total+" revision "+this.revision);
        Conecciones con=new Conecciones();
         cc1=con.obtenerConeccion();
        listConsolidado.put("numListado",this.numeroListado);
        if(total !=null){
            
        }else{
            total=0.00;
        }
        listConsolidado.put("totalKg",total);
        listConsolidado.put("revision",this.revision);
        
        System.out.println(fechaEnvio+"uni "+numVehiculo+" desc "+descVehiculo+" kg "+total+"LISTADO NUM"+this.numeroListado+" rev "+this.revision);
        String master="Repartos//Listados//";
        String destino="Repartos//Listados//"+numeroListado+" R "+this.revision+" Listado consolidado General de materiales.pdf";
        String destino2="Repartos//Listados//"+numeroListado+" R "+this.revision+" Listado consolidado General de materiales.pdf";
        System.err.println(master);
        String kg=String.valueOf(total);
        destino=null;
        PdfConsolidado pdf=new PdfConsolidado(cc1,this.numeroListado,this.revision,descVehiculo,kg,fechaEnvio,destino2,destino);
        pdf.start();
               
                 File f=new File(destino2);
                 if(f.exists()){
            try {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+destino2);
                
            } catch (IOException ex) {
                Logger.getLogger(EmisionDeListadosDeMaterialesConsolidados.class.getName()).log(Level.SEVERE, null, ex);
            }
}
                 
        
    }    
}
