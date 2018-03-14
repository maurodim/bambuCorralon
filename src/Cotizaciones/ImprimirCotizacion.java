package Cotizaciones;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Impresiones.*;
import Depositos.RemitosInternos;
import Sucursales.Cajas;
import interfaceGraficas.Inicio;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import javax.imageio.ImageIO;
import Articulos.Articulos;
import javax.print.attribute.standard.Copies;




/**
 *
 * @author hernan
 */
public class ImprimirCotizacion {

    Font fuente = new Font("Arial", Font.PLAIN, 9);
    Font fuente1=new Font("Arial",Font.BOLD,16);
    Font fuente3 = new Font("Arial", Font.PLAIN, 7);
    Font fuente4 = new Font("Arial", Font.BOLD,7);
    Font fuente5=new Font("Arial",Font.PLAIN,16);
    Font fuente6 = new Font("Arial", Font.BOLD, 9);
    Font fuente7=new Font("Sans Serif", Font.BOLD,7);
    Font fuente8=new Font("Arial",Font.PLAIN,8);
    Font fuente9 = new Font("Arial", Font.BOLD, 5);
    Font fuente10 = new Font("Arial", Font.PLAIN, 6);
    Font fuente11=new Font("Arial",Font.BOLD,11);
	PrintJob pj;	
	Graphics pagina;
	

	/********************************************************************
	*	A continuación el constructor de la clase. Aquí lo único que	*
	*	hago es tomar un objeto de impresion.							*
	********************************************************************/
	public ImprimirCotizacion()
	{
	
            pj = Toolkit.getDefaultToolkit().getPrintJob(new Frame(), "SCAT",null);
            new Copies(2);
            
                
               
	}
			
	/********************************************************************
	*	A continuación el método "imprimir(String)", el encargado de 	*
	*	colocar en el objeto gráfico la cadena que se le pasa como 		*
	*	parámetro y se imprime.											*
	********************************************************************/
        

    
    public void ImprimirCotizacion(Integer idCotizacion) throws IOException{
        Cotizable cotizable=new Cotizacion();
        Cotizacion cotizacion=new Cotizacion();
        cotizacion=(Cotizacion)cotizable.cargarEncabezado(idCotizacion);
        ArrayList listadoDetalle=new ArrayList();
        DetalleCotizacion detalleDeCotizacion=new DetalleCotizacion();
        Cotizable cotiz=new DetalleCotizacion();
        listadoDetalle=cotiz.cargarDetalle(cotizacion.getId());
        
        Calendar fecha=new GregorianCalendar();
        int dia=fecha.get(Calendar.DAY_OF_MONTH);
        int mes=fecha.get(Calendar.MONTH);
        mes++;
        int ano=fecha.get(Calendar.YEAR);
        int hora=fecha.get(Calendar.HOUR_OF_DAY);
        int minuto=fecha.get(Calendar.MINUTE);
        int segundo=fecha.get(Calendar.SECOND);
        String fec=dia+"/"+mes+"/"+ano;
        String hrs=hora+","+minuto+":"+segundo;
        // formulario izquierdo
        
        pagina = pj.getGraphics();
        try{
        BufferedImage imagen= ImageIO.read(new File("C://Gestion//imagen//logo.png"));
        pagina.drawImage(imagen,63,20,174,93,null);
        pagina.setFont(fuente6);
        pagina.setColor(Color.black);
        pagina.drawString("COMPROBANTE N° 00"+Inicio.sucursal.getNumero()+"-000"+cotizacion.getId(),20,130);
        pagina.setFont(fuente);
        pagina.drawString("FECHA IMPRESION:"+fec, 20,140);
        pagina.drawString("SUCURSAL :"+Inicio.sucursal.getDescripcion(),20,150);
        pagina.drawString("CAJERO :"+Inicio.usuario.getNombre(),20,160);
        pagina.setFont(fuente6);
        Double monto=0.00; //caja.getMontoMovimiento()* -1;
        pagina.drawString("MONTO : $ "+monto,20,190);
        pagina.setFont(fuente);
        pagina.drawString("CAJA N°: "+Inicio.caja.getNumero(),20,200);
        pagina.drawString("HORA :"+hrs,20,210);
        pagina.setFont(fuente1);
        pagina.drawString("RETIRO DE EFECTIVO ", 50,280);
        //formulario derecho
        
        pagina.drawImage(imagen,363,20,174,93,null);
        pagina.setFont(fuente6);
        pagina.setColor(Color.black);
        pagina.drawString("COMPROBANTE N° 00"+Inicio.sucursal.getNumero()+"-00010",320,130);
        pagina.setFont(fuente);
        pagina.drawString("FECHA :"+fec, 320,140);
        pagina.drawString("SUCURSAL :"+Inicio.sucursal.getDescripcion(),320,150);
        pagina.drawString("CAJERO :"+Inicio.usuario.getNombre(),320,160);
        pagina.setFont(fuente6);
        pagina.drawString("MONTO : $ "+monto,320,190);
        pagina.setFont(fuente);
        pagina.drawString("CAJA N°: "+Inicio.caja.getNumero(),320,200);
        pagina.drawString("HORA :"+hrs,320,210);
        pagina.setFont(fuente1);
        pagina.drawString("RETIRO DE EFECTIVO ", 350,280);
        
        
        pagina.dispose();
        pj.end();
        }catch(Exception e)
	{
		System.out.println("LA IMPRESION HA SIDO CANCELADA..."+e);
	}

    }
    					
}//FIN DE LA CLASE Impresora

 

