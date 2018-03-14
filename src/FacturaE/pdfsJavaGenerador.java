/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacturaE;

import Conversores.NumberToLetterConverter;
import Conversores.Numeros;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import facturacion.clientes.Clientes;
import facturacion.clientes.DetalleFacturas;
import facturacion.clientes.Facturable;
import facturacion.clientes.Facturas;
import interfaceGraficas.Inicio;
import interfaces.Personalizable;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import objetos.Localidades;

/**
 *
 * @author mauro di
 */
public class pdfsJavaGenerador {
    private FEl doc=new FEl();
    private Clientes cliente=new Clientes();

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    
    
    public void setDoc(FEl doc) {
        this.doc = doc;
    }
    
    
    
    public void run(){
        Document documento=new Document();
        int i=1;
        //String arch="\\\\Diseno\\c\\Users\\RyR\\Documents\\Facturas Electronicas\\"+doc.getIdCliente()+"_"+doc.getAfipPlastCbte()+"_factura.pdf";
        String arch="Facturas Electronicas\\"+doc.getIdCliente()+"_"+doc.getAfipPlastCbte()+"_factura.pdf";
        
        File fich=new File(arch);
        while(fich.exists()){
            i++;
            arch="\\\\Diseno\\c\\Users\\RyR\\Documents\\Facturas Electronicas\\"+doc.getIdCliente()+"_"+doc.getAfipPlastCbte()+i+"_factura.pdf";
            fich=new File(arch);
        }
        FileOutputStream fichero;
        Facturas factura=new Facturas();
        Facturable fac=new Facturas();
        factura=(Facturas)fac.cargarIdFactura(doc.getIdFactura());
        try {
            DetalleFacturas saldo=new DetalleFacturas();
            Facturable cotizable=new DetalleFacturas();
            ArrayList listado=new ArrayList();
            listado=cotizable.cargarDetallefactura(doc.getIdFactura());
            fichero=new FileOutputStream(arch);
            PdfWriter writer=PdfWriter.getInstance(documento, fichero);
            documento.open();
            PdfContentByte cb=writer.getDirectContent();
            Image imagen= Image.getInstance("logo.png");
            imagen.scaleAbsolute(190, 110);
            documento.add(imagen);
            BaseFont bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,16);
            cb.beginText();
            cb.setTextMatrix(100,790);
            //cb.showText("eR&Re");
            //cb.add(imagen);
            cb.setFontAndSize(bf,10);
            cb.setTextMatrix(100, 740);
            //cb.showText("PAPELES");
            bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,8);
            cb.setTextMatrix(40,720);
            //cb.showText("de Rivadeneira Enrique y Rivadeneira Jorge S.H.");
            bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,14);
            cb.setTextMatrix(370,810);
            Integer comF=Integer.parseInt(doc.getTipoComprobante());
            
            String len=doc.getAfipPlastCbte();
        int cantiL=len.length();
        String cero="0";
        int reemplazo= 8 - cantiL;
        int finall=reemplazo + 1;
        reemplazo=reemplazo -1;
        String numero = "0";
        for(int a=1;a < finall;a++){
            numero+=cero;
            if(a == reemplazo){
                a=finall;
                numero+=len;
            }
            
        }
            
            switch (comF){
                case 1:
                    cb.showText("FACTURA A");
                    break;
                case 2:
                    cb.showText("NTA DE DEBITO A");
                    break;
                case 3:
                    cb.showText("NTA DE CREDITO A");
                    break;
                case 6:
                    cb.showText("FACTURA B");
                    break;
                case 7:
                    cb.showText("NTA DE DEBITO B");
                    break;
                case 8:
                    cb.showText("NTA DE CREDITO B");
                    break;
                default:
                    cb.showText("FACTURA B");
                    break;
            }
            cb.setTextMatrix(370,790);
            cb.showText("N°: 0003-"+numero);
            bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,8);
            cb.setTextMatrix(370,770);
            cb.showText("Fecha "+doc.getFechaCae());
            cb.setTextMatrix(370,760);
            cb.showText("CUIT: 33-71235462-9");
            cb.setTextMatrix(370,750);
            cb.showText("Ing. Brutos / Conv. Multilateral: 921-454951-6");
            cb.setTextMatrix(370,740);
            cb.showText("Inicio Activ.: 01/07/2012");
            //cb.setTextMatrix(380,740);
            //cb.showText("Fecha "+doc.getFechaCae());
            bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,10);
            cb.setTextMatrix(40,680);
            cb.showText("Razon Social :"+cliente.getRazonSocial());
            cb.setTextMatrix(380,680);
            String condV="";
            if(doc.getEstado()==1){
                condV="CONTADO";
            }else{
                condV="CTA CTE";
            }
            cb.showText("Cond. Vta: "+condV);
            cb.setTextMatrix(380,670);
            cb.showText("Cond. Iva: "+cliente.getCondicionIva());
            cb.setTextMatrix(40,670);
            cb.showText("Direccion: "+cliente.getDireccion());
            cb.setTextMatrix(40,660);
            Localidades localidad=new Localidades();
            Personalizable per=new Localidades();
            //localidad=per.buscarPorNumero(cliente.getLocalidad())
            cb.showText("Localidad :("+cliente.getCodigoPostal()+") - "+cliente.getLocalidad());
            //cb.setTextMatrix(40,650);
            //cb.showText("Telefono: "+cliente.getTelefono());
            cb.setTextMatrix(380,660);
            Integer tipo=Integer.parseInt(String.valueOf(doc.getCustomerTypeDoc()));
            switch (tipo){
                case 80:
                    cb.showText("Cuit: "+doc.getCustomerId());
                    break;
                case 86:
                    cb.showText("Cuil: "+doc.getCustomerId());
                    break;
                case 96:
                    cb.showText("Dni: "+doc.getCustomerId());
                    break;
            }
            
            
            int renglon=610;
            String vencimiento;
            String descripcion;
            String monto;
            String recargo;
            String total;
            String totalFinal;
            Double tot=0.00;
            Double totalD=0.00;
            Double grav=0.00;
            Double totalS=0.00;
            //aca empieza la iteracion
            
            //encabezados
            bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,10);
            cb.setTextMatrix(40,renglon);
                cb.showText("COD");
                cb.setTextMatrix(70,renglon);
                cb.showText("DESCRIPCION");
                cb.setTextMatrix(330,renglon);
                cb.showText("CANT.");
                cb.setTextMatrix(370,renglon);
                cb.showText("P. UNIT.");
                cb.setTextMatrix(450,renglon);
                cb.showText("DESC.");
                cb.setTextMatrix(500,renglon);
                cb.showText("P. TOTAL.");
                renglon=renglon - 20;
            
            //fin encabezados
            bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,10);
            Iterator itl=listado.listIterator();
            vencimiento="C.A.E.: "+doc.getCae()+"    C.A.E. vencimiento: "+doc.getCaeVto();
            String descripcionArt=null;
            while(itl.hasNext()){
                saldo=(DetalleFacturas)itl.next();
                //vencimiento=saldo.getVencimientoString();
                
                descripcion="Numero Resumen de cta ";
                monto=Numeros.ConvertirNumero(saldo.getPrecioUnitario());
                recargo="10%";
                total="nada";
                //recargo=String.valueOf(saldo.getRecargo());
                //tot=tot + saldo.getTotal();
                //total=String.valueOf(saldo.getTotal());
                cb.setTextMatrix(40,renglon);
                cb.showText(String.valueOf(saldo.getIdArticulo()));
                cb.setTextMatrix(70,renglon);
                if(saldo.getDescripcionArticulo().length() > 40){
            descripcionArt=saldo.getDescripcionArticulo().substring(0, 40);
            }else{
                descripcionArt=saldo.getDescripcionArticulo();
            }
                cb.showText(descripcionArt);
                cb.setTextMatrix(330,renglon);
                cb.showText(String.valueOf(saldo.getCantidad()));
                tot=saldo.getCantidad() * saldo.getPrecioUnitario();
                //tot=tot * 1.21;
                if(comF==1 || comF==2 || comF==3){
                    
                cb.setTextMatrix(370,renglon);
                cb.showText(Numeros.ConvertirNumero(saldo.getPrecioUnitario()));
                cb.setTextMatrix(450,renglon);
                if(saldo.getDescuento()!=null){
                    cb.showText(String.valueOf(saldo.getDescuento()));
                    totalD=totalD + saldo.getDescuento();
                }else{
                    cb.showText("0.00");    
                }
                cb.setTextMatrix(500,renglon);
                cb.showText(Numeros.ConvertirNumero(tot));
                grav=grav + tot;
                totalS=totalS + (tot * 1.21);
                //cb.setTextMatrix(440,renglon);
                
                //cb.showText(Numeros.ConvertirNumero(tot));
                renglon=renglon - 20;
                System.out.println("renglon "+renglon);

                    
                }else{
                    tot=tot * 1.21;
                cb.setTextMatrix(370,renglon);
                cb.showText(Numeros.ConvertirNumero(saldo.getPrecioUnitario() * 1.21));
                cb.setTextMatrix(450,renglon);
                if(saldo.getDescuento()!=null){
                    cb.showText(String.valueOf(saldo.getDescuento()));
                    totalD=totalD + saldo.getDescuento();
                }else{
                    cb.showText("0.00");    
                }
                cb.setTextMatrix(500,renglon);
                cb.showText(Numeros.ConvertirNumero(tot));
                grav=grav + tot;
                totalS=totalS + (tot * 1.21);
                //cb.setTextMatrix(440,renglon);
                
                //cb.showText(Numeros.ConvertirNumero(tot));
                renglon=renglon - 20;
                System.out.println("renglon "+renglon);
                }
                
            }
            
            renglon=60;
            String totalF=Numeros.ConvertirNumero(factura.getTotal());
            String letras=NumberToLetterConverter.convertNumberToLetter(factura.getTotal());
            cb.setTextMatrix(40,renglon);
            cb.showText("SON PESOS: "+letras);
            renglon=renglon -20;
            bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,10);
            cb.setTextMatrix(40,renglon);
            cb.showText("TOTAL BRUTO");
            cb.setTextMatrix(180,renglon);
            cb.showText("T. DESC");
            cb.setTextMatrix(250,renglon);
            cb.showText("MONTO GRAVADO");
            cb.setTextMatrix(380,renglon);
            cb.showText("IVA 21 %");
            cb.setTextMatrix(480,renglon);
            cb.showText("TOTAL");
            renglon=renglon - 10;
            bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,10);
            if(comF==1 || comF==2 || comF==3){
                cb.setTextMatrix(40,renglon);
            Double sub=factura.getTotal() / 1.21;
            Double iva=factura.getTotal() - sub;
            cb.showText(Numeros.ConvertirNumero(sub));
            cb.setTextMatrix(180,renglon);
            cb.showText(Numeros.ConvertirNumero(totalD));
            cb.setTextMatrix(250,renglon);
            cb.showText(Numeros.ConvertirNumero(sub));
            cb.setTextMatrix(380,renglon);
            cb.showText(Numeros.ConvertirNumero(iva));
            }else{
                cb.setTextMatrix(40,renglon);
            Double sub=factura.getTotal() / 1.21;
            Double iva=factura.getTotal() - sub;
            cb.showText(totalF);
            cb.setTextMatrix(180,renglon);
            cb.showText(Numeros.ConvertirNumero(totalD));
            cb.setTextMatrix(250,renglon);
            cb.showText(totalF);
            }
            cb.setTextMatrix(480,renglon);
            cb.showText(totalF);
            
            /*
            totalFinal=doc.getImporteTotal();
            renglon=renglon - 20;
            cb.setTextMatrix(380,renglon);
            cb.showText("TOTAL NETO"+totalFinal);
            */
            
            //pie de documento
            renglon=renglon - 20;
            cb.setTextMatrix(40,renglon);
            cb.showText(vencimiento);
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
