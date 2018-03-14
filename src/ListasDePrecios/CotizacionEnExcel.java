/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListasDePrecios;

import Articulos.Articulos;
import Excel.InformeMensual;
import ListasDePrecios.ArticulosAsignados;
import facturacion.clientes.Clientes;
import interfaceGraficas.Inicio;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author mauro
 */
public class CotizacionEnExcel {
     private ArrayList doc=new ArrayList();
    private Clientes cliente=new Clientes();

    public void setDoc(ArrayList doc) {
        this.doc = doc;
    }

    

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    
    public void GenerarInforme(){
                      HSSFWorkbook libro=new HSSFWorkbook();
        HSSFSheet hoja=libro.createSheet("Lista de Precios");
        
        
        /*
         * GENERAR LAS SIGUIENTES HOJAS
         * 1- DETALLE DE MOVIMIENTOS DE CAJA - LEE EN MOVIMIENTOS CAJA INDENTIFICANDO EL TIPO DE MOVIMIENTO, USUARIOS Y 
         * NUMERO DE CAJA
         * 2- DETALLE DE ARTICULOS VENDIDOS: LISTADO DE MOVIEMIENTOS DE ARTICULOS, CON USUARIOS Y CAJA
         * 3- DETALLE DE GASTOS : MOVIMIENTOS DE CAJA DETALLANDO LOS GASTOS
         * 
         */
        
        String ttx="celda numero :";
        HSSFRow fila=null;
        HSSFCell celda;
        HSSFCell celda1;
        HSSFCell celda2;
        HSSFCell celda3;
        HSSFCell celda4;
        HSSFCell celda5;
        HSSFCell celda6;
        HSSFCell celda7;
        HSSFCell celda8;
        HSSFFont fuente=libro.createFont();
        //fuente.setFontHeight((short)21);
        fuente.setFontName(fuente.FONT_ARIAL);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        String form=null;
        
        HSSFCellStyle titulo=libro.createCellStyle();
        ArticulosAsignados saldo=new ArticulosAsignados();
            
            ArrayList listado=new ArrayList();
            listado=doc;
        Iterator iCli=listado.listIterator();
        //Articulos cliente=new Articulos();
        titulo.setFont(fuente);
        //titulo.setFillBackgroundColor((short)22);
        titulo.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        titulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //for(int a=0;a < 100;a++){
        int col=0;
        int a=0;
            if(a==0){
                fila=hoja.createRow(a);
            celda=fila.createCell(0);
            celda.setCellStyle(titulo);
            celda.setCellValue("Codigo");
            celda1=fila.createCell(1);
            celda1.setCellStyle(titulo);
            celda1.setCellValue("Descripcion");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Precio Unitario");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Observaciones");
            }
            while(iCli.hasNext()){
                saldo= (ArticulosAsignados) iCli.next();
            a++;
            //col=rs.getInt("tipoMovimiento");
            switch(col){
                case 1:
                    
                    break;
                default:
                    
                    break;
            }
            fila=hoja.createRow(a);
            celda=fila.createCell(0);
            ttx=ttx;
            celda.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda.setCellValue(saldo.getId());
            celda1=fila.createCell(1);
            ttx=ttx;
            celda1.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda1.setCellValue(saldo.getDescripcion());
            celda2=fila.createCell(2);
            Double montoCIva=saldo.getPrecioUnitario() * 1.21;
            celda2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda2.setCellValue(montoCIva);
            celda3=fila.createCell(3);
            celda3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda3.setCellValue(saldo.getObservaciones());
            
        }
          
            
        
        //texto+="\r\n";
        String ruta=Inicio.fechaDia+"_"+cliente.getRazonSocial()+"_Lista de Precios.xls";
        try {
            FileOutputStream elFichero=new FileOutputStream(ruta);
            try {
                libro.write(elFichero);
                elFichero.close();
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ruta);
            } catch (IOException ex) {
                Logger.getLogger(InformeMensual.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InformeMensual.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
