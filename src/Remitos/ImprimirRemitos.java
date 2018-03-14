package Remitos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




import Conversores.Numeros;
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
import interfaces.Personalizable;
import objetos.Transportes;




/**
 *
 * @author hernan
 */
public class ImprimirRemitos {

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
	public ImprimirRemitos()
	{
		pj = Toolkit.getDefaultToolkit().getPrintJob(new Frame(), "SCAT", null);
               
	}
			
	/********************************************************************
	*	A continuación el método "imprimir(String)", el encargado de 	*
	*	colocar en el objeto gráfico la cadena que se le pasa como 		*
	*	parámetro y se imprime.											*
	********************************************************************/
        

    
    public void ImprimirRemito(Integer idCotizacion) throws IOException{
        Remitable cotizable=new Remitos();
        Remitos cotizacion=new Remitos();
        cotizacion=(Remitos)cotizable.carga(idCotizacion);
        ArrayList listadoDetalle=new ArrayList();
        DetalleRemitos detalleDeCotizacion=new DetalleRemitos();
        Remitable cotiz=new DetalleRemitos();
        listadoDetalle=cotiz.cargarDetalle(cotizacion.getId());
        Clientes cliente=new Clientes();
        Facturar factu=new Clientes();
        cliente=(Clientes)factu.cargarPorCodigoAsignado(cotizacion.getIdCliente());
        Transportes transporte=new Transportes();
        Personalizable perT=new Transportes();
        transporte=(Transportes) perT.buscarPorNumero(cliente.getIdTransporte());
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
        //BufferedImage imagen= ImageIO.read(new File("logo.png"));
        //pagina.drawImage(imagen,63,20,174,93,null);
        //pagina.drawImage(imagen,30,20,232,144,null);
        pagina.setFont(fuente6);
        pagina.setColor(Color.black);
        Double monto=0.00; //caja.getMontoMovimiento()* -1;
        
        pagina.setFont(fuente6);
       // pagina.drawString("N° "+cotizacion.getDescripcionTipo()+"-0000000"+cotizacion.getNumeroFactura(), 420,80);
        pagina.drawString("FECHA: "+fec, 420,95);
        String len=String.valueOf(cotizacion.getNumeroDeRemito());
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
        //pagina.drawString("N° 0001-"+numero, 420,105);
        pagina.drawString("RAZON SOCIAL: "+cliente.getRazonSocial(),30,180);
        pagina.drawString("C.U.I.T.: "+cliente.getNumeroDeCuit(), 350,180);
        pagina.drawString("NOMBRE FANTASIA: "+cliente.getFantasia(),30,190);
        pagina.drawString("TELEFONO: "+cliente.getTelefono(),350,190);
        pagina.drawString("DIRECCION: "+cliente.getDireccion(),30,200);
        pagina.drawString("LOCALIDAD: ("+cliente.getCodigoPostal()+") "+cliente.getLocalidad(),350,200);
        pagina.drawString("COND IVA: "+cliente.getCondicionIva(),30,210);
        pagina.drawString("MAIL: "+cliente.getEmail(),350,210);
        
        len=String.valueOf(cotizacion.getIdComprobante());
        cantiL=len.length();
        cero="0";
        reemplazo= 8 - cantiL;
        finall=reemplazo + 1;
        reemplazo=reemplazo -1;
        numero = "0";
        for(int a=1;a < finall;a++){
            numero+=cero;
            if(a == reemplazo){
                a=finall;
                numero+=len;
            }
            
        }

        
        //pagina.drawString("FC: 0003-"+cotizacion.getIdComprobante(),350,220);
        
        pagina.drawString("CODIGO",20,245);
        pagina.drawString("DESCRIPCION",160,245);
        pagina.drawString("CANTIDAD", 350,245);
        Double cantidadTotal=0.00;
        int renglon=255;
        Iterator it=listadoDetalle.listIterator();
        String unitario="";
        while(it.hasNext()){
            detalleDeCotizacion=(DetalleRemitos)it.next();
            pagina.drawString(String.valueOf(detalleDeCotizacion.getIdArticulo()),40,renglon);
            pagina.drawString(detalleDeCotizacion.getDescripcionArticulo(),80,renglon);
            pagina.drawString(String.valueOf(detalleDeCotizacion.getCantidadRemitida()),370,renglon);
            cantidadTotal=cantidadTotal + detalleDeCotizacion.getCantidadRemitida();
            renglon=renglon + 10;
        }
        //formulario derecho
        
        //pagina.drawImage(imagen,363,20,174,93,null);
        pagina.drawString("DOMICILIO: "+cotizacion.getDomicilioDeEntrega(),40,700);
        pagina.drawString("LOCALIDAD: "+cotizacion.getLocalidad(), 40,710);
        String tipo="";
        switch (cotizacion.getTipoBulto()){
            case 1:
                tipo="BULTO";
                break;
            case 2:
                tipo="CAJA";
                break;
            case 3:
                tipo="PALET";   
                break;
        }
        pagina.drawString("CANTIDAD "+tipo+": "+cotizacion.getCantidadBultos(),40,720);
        pagina.drawString("OBSERVACIONES: "+cotizacion.getObservaciones(),40,730);
        pagina.drawLine(20, 740, 600,740);
        pagina.drawString("TRANSPORTE: "+transporte.getDescripcion(),40,750);
        pagina.drawString("ENCARGADO: "+transporte.getEncargado(),350,750);
        pagina.drawString("DIRECCION: "+transporte.getDireccion()+"- ("+transporte.getCodigoPostal()+") "+transporte.getLocalidad(),40,760);
        pagina.drawString("TELEFONO: "+transporte.getTelefono(), 40,770);
        pagina.drawString("CUIT: "+transporte.getCuit(),350,770);
        
        //pagina.drawString("CANT. TOTAL", 450, 750);
        //pagina.drawString(String.valueOf(cantidadTotal),440,760);
        
        pagina.dispose();
        //duplicado
        
        
        pj.end();
        }catch(Exception e)
	{
		System.out.println("LA IMPRESION HA SIDO CANCELADA..."+e);
	}

    }
    					
}//FIN DE LA CLASE Impresora

 

