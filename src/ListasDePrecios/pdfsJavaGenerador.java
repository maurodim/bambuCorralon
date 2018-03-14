/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListasDePrecios;


import Conversores.Numeros;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import facturacion.clientes.Clientes;
import interfaceGraficas.Inicio;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mauro di
 */
public class pdfsJavaGenerador {
    private ArrayList doc=new ArrayList();
    private Clientes cliente=new Clientes();

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    
    
    public void setDoc(ArrayList doc) {
        this.doc = doc;
    }
    
    
    
    public void run(){
        Document documento=new Document();
        int i=1;
        String arch=Inicio.fechaDia+"_"+cliente.getRazonSocial()+"_Lista de Precios.pdf";
        
        
        File fich=new File(arch);
        while(fich.exists()){
            i++;
            arch=Inicio.fechaDia+"_"+cliente.getRazonSocial()+i+"_Lista de Precios.pdf";
            fich=new File(arch);
        }
        FileOutputStream fichero;
        try {
            ArticulosAsignados saldo=new ArticulosAsignados();
            //Cotizable cotizable=new DetalleCotizacion();
            ArrayList listado=new ArrayList();
            listado=doc;
            fichero=new FileOutputStream(arch);
            PdfWriter writer=PdfWriter.getInstance(documento, fichero);
            documento.open();
            PdfContentByte cb=writer.getDirectContent();
            BaseFont bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,16);
            cb.beginText();
            cb.setTextMatrix(100,750);
            cb.showText("eR&Re");
            
            cb.setFontAndSize(bf,10);
            cb.setTextMatrix(100, 740);
            cb.showText("PAPELES");
            bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,8);
            cb.setTextMatrix(40,720);
            cb.showText("de Rivadeneira Enrique y Rivadeneira Jorge S.H.");
            bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,14);
            cb.setTextMatrix(300,750);
            cb.showText("LISTA DE PRECIOS: "+cliente.getRazonSocial());
            bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,10);
            cb.setTextMatrix(40,690);
            cb.showText("Razon Social :"+cliente.getRazonSocial());
            cb.setTextMatrix(410,690);
            cb.showText("Fecha "+Inicio.fechaDia);
            cb.setTextMatrix(40,670);
            cb.showText("Direccion: "+cliente.getDireccion());
            cb.setTextMatrix(380,670);
            cb.showText("Mail :"+cliente.getCelular());
            cb.setTextMatrix(40,650);
            cb.showText("Telefono: "+cliente.getTelefono());
            cb.setTextMatrix(380,650);
            cb.showText("Cuit: "+cliente.getNumeroDeCuit());
            
            int renglon=610;
            String vencimiento;
            String descripcion;
            String monto;
            String recargo;
            String total;
            String totalFinal;
            Double tot=0.00;
            //aca empieza la iteracion
            
            //encabezados
            bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,10);
            cb.setTextMatrix(40,renglon);
                cb.showText("COD");
                cb.setTextMatrix(70,renglon);
                cb.showText("DESCRIPCION");
                cb.setTextMatrix(450,renglon);
                cb.showText("P. UNIT.");
                cb.setTextMatrix(500,renglon);
                cb.showText("OBS");
                renglon=renglon - 20;
            
            //fin encabezados
            bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,10);
            Iterator itl=listado.listIterator();
            vencimiento="Esta cotización tendrá vigencia 30 días ";
            while(itl.hasNext()){
                saldo=(ArticulosAsignados)itl.next();
                //vencimiento=saldo.getVencimientoString();
                
                descripcion="Numero Resumen de cta ";
                monto=Numeros.ConvertirNumero(saldo.getPrecioUnitario() * 1.21);
                recargo="10%";
                total="nada";
                //recargo=String.valueOf(saldo.getRecargo());
                //tot=tot + saldo.getTotal();
                //total=String.valueOf(saldo.getTotal());
                cb.setTextMatrix(40,renglon);
                cb.showText(String.valueOf(saldo.getId()));
                cb.setTextMatrix(70,renglon);
                String descA;
                if(saldo.getDescripcion().length() > 50){
                    descA=saldo.getDescripcion().substring(0,50);
                }else{
                    descA=saldo.getDescripcion();
                }
                cb.showText(descA);
                cb.setTextMatrix(450,renglon);
                cb.showText(monto);
                cb.setTextMatrix(500,renglon);
                String obs="";
                if(saldo.getObservaciones()!=null){
                if(saldo.getObservaciones().length()> 30){
                    obs=saldo.getObservaciones().substring(0,30);
                }else{
                    obs=saldo.getObservaciones();
                }
                }
                cb.showText(obs);
                
                renglon=renglon - 20;
                if(renglon < 30){
                            renglon=760;
                            cb.endText();
                            documento.newPage();
                            cb.beginText();
                          bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                    cb.setFontAndSize(bf,10);
                    cb.setTextMatrix(40,renglon);
                        cb.showText("COD");
                        cb.setTextMatrix(70,renglon);
                        cb.showText("DESCRIPCION");
                        cb.setTextMatrix(380,renglon);
                        cb.showText("P. UNIT.");
                        cb.setTextMatrix(430,renglon);
                        cb.showText("OBS");
                        renglon=renglon - 20;
                      bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                    cb.setFontAndSize(bf,10);
                }
            }
            cb.endText();
            documento.close();
            
            File f=new File(arch);
            if(f.exists()){
            
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+arch);
            }
            int confirmacion=0;
            /*
            if(doc.getArchivo().isEmpty()){
                
            }else{
                confirmacion=JOptionPane.showConfirmDialog(null, "DESEA NOTIFICAR POR MAIL?");
            if(confirmacion==0){
                //JOptionPane.showMessageDialog(null,"acepto");
                
            }
            }
                    */
            System.out.println("eligio "+confirmacion);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (DocumentException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
