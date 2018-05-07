/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuracion;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro
 */
public class Propiedades {
    static String SERVER="localhost";
    static String BD="bbgestion";
    static String USUARIO="bambusoft";
    static String CLAVE="Bghjiit889210}>";
    static String CREADA;
    static String ARCHIVOBCRT;
    static String ARCHIVOKEY;
    static String PUNTODEVENTA;
    static String CUIT;

    public static String getSERVER() {
        return SERVER;
    }

    public static String getBD() {
        return BD;
    }

    public static String getUSUARIO() {
        return USUARIO;
    }

    public static String getCLAVE() {
        return CLAVE;
    }

    public static String getCREADA() {
        return CREADA;
    }

    public static String getARCHIVOBCRT() {
        return ARCHIVOBCRT;
    }

    public static String getARCHIVOKEY() {
        return ARCHIVOKEY;
    }

    public static String getPUNTODEVENTA() {
        return PUNTODEVENTA;
    }

    public static String getCUIT() {
        return CUIT;
    }
    
    
    

    
    
    public static void CargarPropiedades1() throws ParseException, IOException{
        File archivo = new File ("Configuracion\\bbsGestion.properties");
        Properties p=new Properties();
         if(archivo.exists()){
            try {
                //Process px=Runtime.getRuntime().exec("c:/xampp/xampp_start.exe");
                sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        int verificado=0;
        
            p.load(new FileReader(archivo));
            Enumeration<Object> keys = p.keys();

            while (keys.hasMoreElements()){
               Object key = keys.nextElement();
               System.out.println(key + " = "+ p.get(key));
            }   
         
            
         //FileReader fr = null;
            
                //fr = new FileReader (archivo);
                //BufferedReader br = new BufferedReader(fr);
                // Lectura del fichero
                String linea;
                int renglon=0;
                //Transaccionable tra=new Conecciones();
                //while((linea=br.readLine())!=null){
                    
                    
                    
                            CREADA=p.getProperty("CREADA");
                        
                            
                            
                            CUIT=p.getProperty("CUIT");
                            SERVER=p.getProperty("SERVER");
                            BD=p.getProperty("BD");
                            USUARIO=p.getProperty("USUARIO");
                            CLAVE=p.getProperty("CLAVE");
                            ARCHIVOBCRT=p.getProperty("ARCHIVOBCRT");
                            ARCHIVOKEY=p.getProperty("ARCHIVOKEY");
                            PUNTODEVENTA=p.getProperty("PUNTODEVENTA");
                            
                            
                        
            
            
            
        }else{
             
             ListadoConfiguracion confi=new ListadoConfiguracion();
             confi.setVisible(true);
             confi.toFront();
             CREADA="1";
             CUIT=confi.jTextField8.getText();
             SERVER=confi.jTextField1.getText();
             BD=confi.jTextField2.getText();
             USUARIO=confi.jTextField3.getText();
             CLAVE=confi.jTextField4.getText();
             ARCHIVOBCRT=confi.jTextField6.getText();
             ARCHIVOKEY=confi.jTextField7.getText();
             PUNTODEVENTA=confi.jTextField5.getText();
             
             
             p.setProperty("CREADA",CREADA);
             p.setProperty("CUIT",CUIT);
             p.setProperty("SERVER",SERVER);
             p.setProperty("BD",BD);
                    
             
             p.setProperty("USUARIO",USUARIO);
             p.setProperty("CLAVE",CLAVE);
             p.setProperty("ARCHIVOBCRT",ARCHIVOBCRT);
             p.setProperty("ARCHIVOKEY",ARCHIVOKEY);
             p.setProperty("PUNTODEVENTA",PUNTODEVENTA);
                        
                    
             
             p.store(new FileWriter("Configuracion\\bbsGestion.properties"),"");
             
               
            
         }
        //BD="siglox";
        
        
        
    }
    
}
