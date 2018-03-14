/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import Conversores.Numeros;
import interfaces.Transaccionable;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Articulos.Articulos;
import objetos.Conecciones;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


/**
 *
 * @author mauro di
 */
public class LeerExcelProveedores {
   public void leerExcel1(String fileName) throws SQLException {
       List cellDataList = new ArrayList();
try
{
/**
* Create a new instance for FileInputStream class
*/
FileInputStream fileInputStream = new FileInputStream(fileName);
/**
* Create a new instance for POIFSFileSystem class
*/
POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
/*
* Create a new instance for HSSFWorkBook Class
*/
HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
HSSFSheet hssfSheet = workBook.getSheetAt(0);
/**
* Iterate the rows and cells of the spreadsheet
* to get all the datas.
*/
Iterator rowIterator = hssfSheet.rowIterator();
while (rowIterator.hasNext())
{
HSSFRow hssfRow = (HSSFRow) rowIterator.next();
Iterator iterator = hssfRow.cellIterator();
List cellTempList = new ArrayList();
while (iterator.hasNext())
{
HSSFCell hssfCell = (HSSFCell) iterator.next();
cellTempList.add(hssfCell);
}
cellDataList.add(cellTempList);
}
}
catch (Exception e)
{
e.printStackTrace();
}
/**
* Call the printToConsole method to print the cell data in the
* console.
*/
printToConsole(cellDataList);
}
/**
* This method is used to print the cell data to the console.
* @param cellDataList - List of the data's in the spreadsheet.
*/
private void printToConsole(List cellDataList)
{
    String error=""; 
    int fila=0;
    String mail="";
    String razon="";
    String fantasia="";
    String responsable="";
    String direccion="";
    String telefono="";
    int tipoIva=1;
    String celular="";
    int cuit2;
    String sql="";
    Transaccionable tra=new Conecciones();
    HashMap listadoArticulos=new HashMap();
    Boolean verif=false;
    ArrayList lstArt=new ArrayList();
    String unidadDeMedida="";
    Double peso=0.00;
    System.out.println("cantidad de celdas "+cellDataList.size()+" cantidad map "+listadoArticulos.size());
    for (int i = 0; i < cellDataList.size(); i++)
    {
        List cellTempList = (List) cellDataList.get(i);
        //articulo=new Articulos();
        String descrip="";
        for (int j = 0; j < cellTempList.size(); j++)
        {
            HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
            String stringCellValue = hssfCell.toString();
            descrip="";
            if(i > 0){
                switch (j){
                    case 0:
                        stringCellValue=stringCellValue.trim();
                        //cuit2=Long.decode(stringCellValue).intValue();
                        razon=stringCellValue;
                        //cuit=String.valueOf(cuit2);
                        
                        
                        break;
                    case 1:
                        if(stringCellValue.isEmpty()){
                            stringCellValue=" ";
                        }
                        responsable=stringCellValue;
                        //fantasia=stringCellValue;
                        break;
                    case 2:
                        if(stringCellValue.isEmpty()){
                            stringCellValue=" ";
                        }
                        direccion=stringCellValue;
                        break;
                    case 3:
                        telefono=stringCellValue;
                        
                        break;
                    case 4:
                        celular=stringCellValue;
                        break;
                    case 5:
                        //celular=stringCellValue;
                        break;
                    case 6:
                        mail=stringCellValue.toLowerCase();
                        break;
                    case 7:
                        break;
                }
                
                System.out.print(stringCellValue +" "+ j+" fila "+i+ "\t");
                
            }
            
        }
        if(i > 0){
            
        }
        //int rub=idRubro.intValue();
        sql="insert into proveedores (nombre,domicilio,telefono,mail,celular,responsable) values ('"+razon+"','"+direccion+"','"+telefono+"','"+mail+"','"+celular+"','"+responsable+"')";
                tra.guardarRegistro(sql);
        verif=false;
        lstArt.clear();
        
        System.out.println();
    }
    JOptionPane.showMessageDialog(null,"PROCESO EXITOSO \n CANTIDAD DE FILAS PROCESADAS "+fila);
   }
}
