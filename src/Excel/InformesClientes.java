/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import facturacion.clientes.Clientes;
import interfaces.Transaccionable;
import interfacesPrograma.Busquedas;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Conecciones;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author mauro di
 */
public class InformesClientes {
   public void GenerarInforme(ArrayList listadoClientes) throws SQLException{
              HSSFWorkbook libro=new HSSFWorkbook();
        HSSFSheet hoja=libro.createSheet("Saldos de Clientes");
        //HSSFSheet hoja1=libro.createSheet("Detalle de Saldos");
        
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
        String sql="";
        System.out.println(sql);
        Transaccionable tra=new Conecciones();
        ResultSet rs=null;
        HSSFCellStyle titulo=libro.createCellStyle();
        Iterator iCli=listadoClientes.listIterator();
        Clientes cliente=new Clientes();
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
            celda.setCellValue("Cod. Cliente");
            celda1=fila.createCell(1);
            celda1.setCellStyle(titulo);
            celda1.setCellValue("Nombre");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Direccion");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Teléfono");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("Localidad");
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("Celular");
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("Contacto");
            celda7=fila.createCell(7);
            celda7.setCellStyle(titulo);
            celda7.setCellValue("Nombre de Fantasia");
            }
            while(iCli.hasNext()){
                cliente=(Clientes)iCli.next();
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
            celda.setCellValue(cliente.getCodigoCliente());
            celda1=fila.createCell(1);
            ttx=ttx;
            celda1.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda1.setCellValue(cliente.getRazonSocial());
            celda2=fila.createCell(2);
            celda2.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda2.setCellValue(cliente.getDireccion());
            celda3=fila.createCell(3);
            celda3.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda3.setCellValue(cliente.getTelefono());
            celda4=fila.createCell(4);
            celda4.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda4.setCellValue(cliente.getLocalidad());
            
           
            celda5=fila.createCell(5);
            //celda5.setCellFormula(rs.getString("observaciones"));
            celda5.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda5.setCellValue(cliente.getCelular());
            celda6=fila.createCell(6);
            celda6.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda6.setCellValue(cliente.getResponsable());
            celda7=fila.createCell(7);
            celda7.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda7.setCellValue(cliente.getFantasia());
            //celda5.setCellValue(rs.getDate("fecha"));
            
        }
          
        //texto+="\r\n";
        String ruta="C://Informes//informeDeClientes.xls";
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
