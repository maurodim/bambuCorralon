package Pedidos;

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
import interfaces.Personalizable;
import javax.print.attribute.standard.Copies;
import objetos.Transportes;




/**
 *
 * @author hernan
 */
public class ImprimirPedido {

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
	public ImprimirPedido()
	{
		pj = Toolkit.getDefaultToolkit().getPrintJob(new Frame(), "SCAT",null);
               
	}
			
	/********************************************************************
	*	A continuación el método "imprimir(String)", el encargado de 	*
	*	colocar en el objeto gráfico la cadena que se le pasa como 		*
	*	parámetro y se imprime.											*
	********************************************************************/
        

    
    public void ImprimirOrdenDeTrabajo(Integer idCotizacion) throws IOException{
        Pedable cotizable=new Pedidos();
        Pedidos cotizacion=new Pedidos();
        cotizacion=(Pedidos)cotizable.cargarEncabezadoPedido(idCotizacion);
        ArrayList listadoDetalle=new ArrayList();
        DetallePedidos detalleDeCotizacion=new DetallePedidos();
        Pedable cotiz=new DetallePedidos();
        listadoDetalle=cotiz.cargarDetallePedido(cotizacion.getId());
        Clientes cliente;
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
        //new Copies(2);
        pagina = pj.getGraphics();
        
        try{
        //BufferedImage imagen= ImageIO.read(new File("C://Gestion//imagen//logo.png"));
        //pagina.drawImage(imagen,63,20,174,93,null);
        pagina.setFont(fuente6);
        pagina.setColor(Color.black);
        pagina.drawString("PEDIDO N° 00"+Inicio.sucursal.getNumero()+"-000"+cotizacion.getId(),20,20);
        pagina.setFont(fuente);
        pagina.drawString("FECHA IMPRESION:"+fec, 20,30);
        //pagina.drawString(" :"+Inicio.sucursal.getDescripcion(),20,150);
        pagina.drawString("USUARIO :"+Inicio.usuario.getNombre(),320,20);
        pagina.setFont(fuente6);
        Double monto=0.00; //caja.getMontoMovimiento()* -1;
        pagina.drawLine(20, 40, 600, 40);
        pagina.drawString("RAZON SOCIAL: "+cliente.getRazonSocial(),30,55);
        pagina.drawString("C.U.I.T.: "+cliente.getNumeroDeCuit(), 350,55);
        pagina.drawString("NOMBRE FANTASIA: "+cliente.getFantasia(),30,65);
        pagina.drawString("TELEFONO: "+cliente.getTelefono(),350,65);
        pagina.drawString("DIRECCION: "+cliente.getDireccion(),30,75);
        
        pagina.drawString("LOCALIDAD: ("+cliente.getCodigoPostal()+") "+cliente.getLocalidad(),350,75);
        
        pagina.drawString("COND IVA: "+cliente.getCondicionIva(),30,85);
        pagina.drawString("MAIL: "+cliente.getEmail(),350, 85);
        pagina.drawLine(20, 95, 600, 95);
        pagina.setFont(fuente);
        pagina.drawString("CODIGO",20,105);
        pagina.drawString("DESCRIPCION",100,105);
        pagina.drawString("CANTIDAD", 450,105);
        int renglon=115;
        Iterator it=listadoDetalle.listIterator();
        String descripcionArt=null;
        while(it.hasNext()){
            detalleDeCotizacion=(DetallePedidos)it.next();
            pagina.drawString(String.valueOf(detalleDeCotizacion.getIdArticulo()),20,renglon);
            if(detalleDeCotizacion.getDescripcionArticulo().length() > 50){
            descripcionArt=detalleDeCotizacion.getDescripcionArticulo().substring(0, 50);
            }else{
                descripcionArt=detalleDeCotizacion.getDescripcionArticulo();
            }
            pagina.drawString(descripcionArt,60,renglon);
            pagina.drawString(String.valueOf(detalleDeCotizacion.getCantidad()),470,renglon);
            renglon=renglon + 10;
        }
        //formulario derecho
        pagina.drawLine(20, renglon, 600,renglon);
        renglon=renglon + 30;
        pagina.drawString("ENVIAR POR: "+transporte.getDescripcion(), 30,renglon);
        pagina.drawString("ENCARGADO: "+transporte.getEncargado(),350,renglon);
        renglon=renglon + 10;
        pagina.drawString("DIRECCION: "+transporte.getDireccion()+"- ("+transporte.getCodigoPostal()+") "+transporte.getLocalidad(), 30, renglon);
        renglon=renglon + 10;
        pagina.drawString("TELEFONO: "+transporte.getTelefono(),30,renglon);
        pagina.drawString("CUIT: "+transporte.getCuit(),350,renglon);
        
        renglon=renglon + 10;
        pagina.drawLine(20, renglon, 600,renglon);
        renglon=renglon + 30;
        pagina.setFont(fuente11);
        if(cliente.getDireccionDeEntrega()!=null){
            pagina.drawString("ENTREGAR EN: "+cliente.getDireccionDeEntrega()+" - ("+cliente.getCodigoPostal()+") "+cliente.getLocalidad(), 30,renglon);
        }else{
            pagina.drawString("ENTREGAR EN: "+cliente.getDireccion()+" - ("+cliente.getCodigoPostal()+") "+cliente.getLocalidad(), 30,renglon);
        }
        //pagina.drawImage(imagen,363,20,174,93,null);
        
        
        pagina.dispose();
        pj.end();
        }catch(Exception e)
	{
		System.out.println("LA IMPRESION HA SIDO CANCELADA..."+e);
	}

    }
    public void ImprimirOrdenDetallada(Pedidos idCotizacion) throws IOException{
        Pedable cotizable=new Pedidos();
        Pedidos cotizacion=new Pedidos();
        cotizacion=(Pedidos)idCotizacion;
        ArrayList listadoDetalle=new ArrayList();
        DetallePedidos detalleDeCotizacion=new DetallePedidos();
        Pedable cotiz=new DetallePedidos();
        listadoDetalle=cotiz.cargarDetallePedido(cotizacion.getId());
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
        //BufferedImage imagen= ImageIO.read(new File("C://Gestion//imagen//logo.png"));
        //pagina.drawImage(imagen,63,20,174,93,null);
        pagina.setFont(fuente6);
        pagina.setColor(Color.black);
        pagina.drawString("PEDIDO N° 00"+Inicio.sucursal.getNumero()+"-000"+cotizacion.getId(),20,20);
        pagina.setFont(fuente);
        pagina.drawString("FECHA IMPRESION:"+fec, 20,30);
        //pagina.drawString(" :"+Inicio.sucursal.getDescripcion(),20,150);
        pagina.drawString("USUARIO :"+Inicio.usuario.getNombre(),320,20);
        pagina.setFont(fuente6);
        Double monto=0.00; //caja.getMontoMovimiento()* -1;
        pagina.drawLine(20, 35, 600, 35);
        pagina.drawString("RAZON SOCIAL: "+cliente.getRazonSocial(),30,45);
        pagina.drawString("C.U.I.T.: "+cliente.getNumeroDeCuit(), 350,45);
        pagina.drawString("NOMBRE FANTASIA: "+cliente.getFantasia(),30,55);
        pagina.drawString("TELEFONO: "+cliente.getTelefono(),350,55);
        pagina.drawString("DIRECCION: "+cliente.getDireccion(),30,65);
        
        pagina.drawString("LOCALIDAD: ("+cliente.getCodigoPostal()+") "+cliente.getLocalidad(),350,65);
        
        pagina.drawString("COND IVA: "+cliente.getCondicionIva(),30,75);
        pagina.drawString("MAIL: "+cliente.getEmail(),350, 75);
        pagina.drawLine(20, 85, 600, 85);
        pagina.setFont(fuente);
        pagina.drawString("CODIGO",20,95);
        pagina.drawString("DESCRIPCION",100,95);
        pagina.drawString("CANTIDAD", 400,95);
        pagina.drawString("PRECIO U",450,95);
        pagina.drawString("PRECIO",500,95);
        int renglon=105;
        Iterator it=listadoDetalle.listIterator();
        Double generalT=0.00;
        String descripcionArt=null;
        while(it.hasNext()){
            detalleDeCotizacion=(DetallePedidos)it.next();
            pagina.drawString(String.valueOf(detalleDeCotizacion.getIdArticulo()),20,renglon);
            
            if(detalleDeCotizacion.getDescripcionArticulo().length() > 50){
            descripcionArt=detalleDeCotizacion.getDescripcionArticulo().substring(0, 50);
            }else{
                descripcionArt=detalleDeCotizacion.getDescripcionArticulo();
            }
            
            //descripcionArt=detalleDeCotizacion.getDescripcionArticulo().substring(0, 50);
            
            pagina.drawString(descripcionArt,60,renglon);
            pagina.drawString(String.valueOf(detalleDeCotizacion.getCantidad()),420,renglon);
            pagina.drawString(Numeros.ConvertirNumero(detalleDeCotizacion.getPrecioUnitario() * 1.21),470,renglon);
            Double total=detalleDeCotizacion.getCantidad() * (detalleDeCotizacion.getPrecioUnitario() * 1.21);
            generalT=generalT + total;
            pagina.drawString(Numeros.ConvertirNumero(total),510,renglon);
            renglon=renglon + 10;
        }
        renglon=renglon + 10;
        pagina.setFont(fuente1);
        pagina.drawString("TOTAL: "+Numeros.ConvertirNumero(generalT),40,renglon);
        //formulario derecho
        renglon=renglon + 30;
        //pagina.drawImage(imagen,363,20,174,93,null);
        pagina.setFont(fuente);
        pagina.drawLine(20, renglon, 600,renglon);
        renglon=renglon + 10;
        pagina.drawString("ENVIAR POR: "+transporte.getDescripcion(), 30,renglon);
        pagina.drawString("ENCARGADO: "+transporte.getEncargado(),350,renglon);
        renglon=renglon + 10;
        pagina.drawString("DIRECCION: "+transporte.getDireccion()+"- ("+transporte.getCodigoPostal()+") "+transporte.getLocalidad(), 30, renglon);
        renglon=renglon + 10;
        pagina.drawString("TELEFONO: "+transporte.getTelefono(),30,renglon);
        pagina.drawString("CUIT: "+transporte.getCuit(),350,renglon);
        
        renglon=renglon + 10;
        pagina.drawLine(20, renglon, 600,renglon);
        renglon=renglon + 30;
        pagina.setFont(fuente11);
        if(cliente.getDireccionDeEntrega()!=null){
            pagina.drawString("ENTREGAR EN: "+cliente.getDireccionDeEntrega()+" - ("+cliente.getCodigoPostal()+") "+cliente.getLocalidad(), 30,renglon);
        }else{
            pagina.drawString("ENTREGAR EN: "+cliente.getDireccion()+" - ("+cliente.getCodigoPostal()+") "+cliente.getLocalidad(), 30,renglon);
        }
        
        pagina.dispose();
        pj.end();
        }catch(Exception e)
	{
		System.out.println("LA IMPRESION HA SIDO CANCELADA..."+e);
	}

    }					
}//FIN DE LA CLASE Impresora

 

