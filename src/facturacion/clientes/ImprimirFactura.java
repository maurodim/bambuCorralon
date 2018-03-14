package facturacion.clientes;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import Conversores.Numeros;
import Impresiones.*;
import Depositos.RemitosInternos;
import Sucursales.Cajas;
import facturacion.clientes.Clientes;
import interfaceGraficas.Inicio;
import interfacesPrograma.Facturar;
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
import Conversores.NumberToLetterConverter;
import interfaces.Personalizable;
import objetos.Localidades;




/**
 *
 * @author hernan
 */
public class ImprimirFactura {

    Font fuente = new Font("Courier", Font.PLAIN, 9);
    Font fuente1=new Font("Courier",Font.BOLD,12);
    Font fuente3 = new Font("Courier", Font.PLAIN, 7);
    Font fuente4 = new Font("Courier", Font.BOLD,7);
    Font fuente5=new Font("Courier",Font.PLAIN,16);
    Font fuente6 = new Font("Courier", Font.BOLD, 9);
    Font fuente7=new Font("Courier", Font.BOLD,7);
    Font fuente8=new Font("Courier",Font.PLAIN,8);
    Font fuente9 = new Font("Courier", Font.BOLD, 5);
    Font fuente10 = new Font("Courier", Font.PLAIN, 6);
    Font fuente11=new Font("Courier",Font.BOLD,11);
	PrintJob pj;	
	Graphics pagina;
	

	/********************************************************************
	*	A continuación el constructor de la clase. Aquí lo único que	*
	*	hago es tomar un objeto de impresion.							*
	********************************************************************/
	public ImprimirFactura()
	{
            //PrintJob print=new PrintJob();
            pj = Toolkit.getDefaultToolkit().getPrintJob(new Frame(), "SCAT", null);
               
	}
			
	/********************************************************************
	*	A continuación el método "imprimir(String)", el encargado de 	*
	*	colocar en el objeto gráfico la cadena que se le pasa como 		*
	*	parámetro y se imprime.											*
	********************************************************************/
        

    
    public void ImprimirFactura(Integer idCotizacion,Integer tipo) throws IOException{
        Facturable cotizable=new Facturas();
        Facturas cotizacion=new Facturas();
        cotizacion=(Facturas)cotizable.cargarEncabezadoFactura(idCotizacion, tipo);
        ArrayList listadoDetalle=new ArrayList();
        DetalleFacturas detalleDeCotizacion=new DetalleFacturas();
        Facturable cotiz=new DetalleFacturas();
        listadoDetalle=cotiz.cargarDetallefactura(cotizacion.getId());
        Clientes cliente=new Clientes();
        Facturar factu=new Clientes();
        cliente=(Clientes)factu.cargarPorCodigoAsignado(cotizacion.getIdCliente());
        Localidades localidad=new Localidades();
        Personalizable pp=new Localidades();
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
        BufferedImage imagen= ImageIO.read(new File("logo.png"));
        //pagina.drawImage(imagen,63,20,174,93,null);
        pagina.drawImage(imagen,30,20,232,144,null);
        pagina.setFont(fuente6);
        pagina.setColor(Color.black);
        Double monto=0.00; //caja.getMontoMovimiento()* -1;
        
        pagina.setFont(fuente6);
       // pagina.drawString("N° "+cotizacion.getDescripcionTipo()+"-0000000"+cotizacion.getNumeroFactura(), 420,80);
        pagina.drawString("FECHA: "+fec, 420,80);
        String len=String.valueOf(cotizacion.getNumeroFactura());
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
        /*
        StringBuffer numero=new StringBuffer();
        numero.ensureCapacity(reemplazo);
        numero=numero.append(len);
        */
        pagina.drawString("N° 0001-"+numero, 420,100);
        //pagina.drawString("ORIGINAL", 420,110);
        pagina.drawString("RAZON SOCIAL: "+cliente.getRazonSocial(),30,185);
        pagina.drawString("C.U.I.T.: "+cliente.getNumeroDeCuit(), 350,185);
        pagina.drawString("DIRECCION: "+cliente.getDireccion(),30,200);
        
        pagina.drawString("LOCALIDAD: "+cliente.getLocalidad(),350,200);
        pagina.drawString("COND IVA: "+cliente.getCondicionIva(),30,215);
        String pago="";
        if(cotizacion.getEstado()==0){
            pago="CTA. CTE";
        }else{
            pago="CONTADO";
        }
        pagina.drawString("FORMA DE PAGO: "+pago,350,215);
        
        pagina.drawString("CODIGO",20,250);
        pagina.drawString("DESCRIPCION",160,250);
        pagina.drawString("DESCUENTO", 330,250);
        pagina.drawString("CANTIDAD", 400,250);
        pagina.drawString("P. UNITARIO",500,250);
        int renglon=260;
        Iterator it=listadoDetalle.listIterator();
        String unitario="";
        Double descuentoTotal=0.00;
        String descuento;
        String descripcionArt=null;
        while(it.hasNext()){
            detalleDeCotizacion=(DetalleFacturas)it.next();
            pagina.drawString(String.valueOf(detalleDeCotizacion.getIdArticulo()),40,renglon);
            if(detalleDeCotizacion.getDescripcionArticulo().length() > 40){
            descripcionArt=detalleDeCotizacion.getDescripcionArticulo().substring(0, 40);
            }else{
                descripcionArt=detalleDeCotizacion.getDescripcionArticulo();
            }
            pagina.drawString(descripcionArt,80,renglon);
            if(detalleDeCotizacion.getDescuento()!=null){
                descuentoTotal=descuentoTotal + detalleDeCotizacion.getDescuento();
                descuento=String.valueOf(detalleDeCotizacion.getDescuento());
            }else{
                descuento="0.00";
                descuentoTotal=descuentoTotal + 0;
            }
            pagina.drawString(descuento,350,renglon);
            
            
            pagina.drawString(String.valueOf(detalleDeCotizacion.getCantidad()),420,renglon);
            if(cotizacion.getTipo()==2){
                unitario=Numeros.ConvertirNumero(detalleDeCotizacion.getPrecioUnitario() * detalleDeCotizacion.getCantidad());
            }else{
                unitario=Numeros.ConvertirNumero((detalleDeCotizacion.getPrecioUnitario() * detalleDeCotizacion.getCantidad()) * 1.21);
            }
            pagina.drawString(unitario,520,renglon);
            renglon=renglon + 10;
        }
        //formulario derecho
        
        //pagina.drawImage(imagen,363,20,174,93,null);
        String letras=NumberToLetterConverter.convertNumberToLetter(cotizacion.getTotal());
        pagina.drawString("SON PESOS: "+letras, 30,735);
        if(cotizacion.getTipo()==2){
            Double sub=cotizacion.getTotal() / 1.21;
            Double iva=cotizacion.getTotal() - sub;
        pagina.drawString("MONTO BRUTO", 30,750);
        pagina.drawString(Numeros.ConvertirNumero(sub),40,760);
        pagina.drawString("DESCUENTO GRAL", 150,750);
        pagina.drawString(Numeros.ConvertirNumero(descuentoTotal),150,760);
        
        pagina.drawString("MTO GRAV.", 250,750);
        pagina.drawString(Numeros.ConvertirNumero(sub),250,760);
        pagina.drawString("IVA 21%", 350, 750);
        pagina.drawString(Numeros.ConvertirNumero(iva),350,760);            
        }else{

        }
        pagina.drawString("TOTAL", 450, 750);
        pagina.drawString(String.valueOf(cotizacion.getTotal()),450,760);
        
        pagina.dispose();
        //duplicado
        
                 
        pj.end();
        
        }catch(Exception e)
	{
		System.out.println("LA IMPRESION HA SIDO CANCELADA..."+e);
	}

    }
    					
}//FIN DE LA CLASE Impresora

 

