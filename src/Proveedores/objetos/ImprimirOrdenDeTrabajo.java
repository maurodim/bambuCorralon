package Proveedores.objetos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import Conversores.NumberToLetterConverter;
import Conversores.Numeros;
import Proveedores.Proveedores;
import Recibos.Formable;
import Recibos.FormasDePago;
import Recibos.Recidable;
import interfaceGraficas.Inicio;
import interfaces.Personalizable;
import interfacesPrograma.Facturar;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;




/**
 *
 * @author hernan
 */
public class ImprimirOrdenDeTrabajo {

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
	public ImprimirOrdenDeTrabajo()
	{
		pj = Toolkit.getDefaultToolkit().getPrintJob(new Frame(), "SCAT", null);
                
               
	}
			
	/********************************************************************
	*	A continuación el método "imprimir(String)", el encargado de 	*
	*	colocar en el objeto gráfico la cadena que se le pasa como 		*
	*	parámetro y se imprime.											*
	********************************************************************/
        

    
    public void ImprimirOrdenDeTrabajo(Object recibo,ArrayList detalle,ArrayList pagos) throws IOException{
        OrdenDePago rec=new OrdenDePago();
        DetalleOrdenDePago det=new DetalleOrdenDePago();
        FormasDePago formas=new FormasDePago();
        /*
        cotizacion=(Pedidos)cotizable.cargarEncabezadoPedido(idCotizacion);
        ArrayList listadoDetalle=new ArrayList();
        DetallePedidos detalleDeCotizacion=new DetallePedidos();
        Pedable cotiz=new DetallePedidos();
        listadoDetalle=cotiz.cargarDetallePedido(cotizacion.getId());
        */
        rec=(OrdenDePago)recibo;
        Proveedores cliente=new Proveedores();
        Personalizable factu=new Proveedores();
        cliente=(Proveedores)factu.buscarPorNumero(rec.getIdCliente());
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
        pagina.drawString("ORDEN DE PAGO N° 0000"+Inicio.sucursal.getNumero()+"-000"+rec.getId(),20,110);
        pagina.setFont(fuente);
        pagina.drawString("FECHA IMPRESION:"+fec, 20,120);
        //pagina.drawString(" :"+Inicio.sucursal.getDescripcion(),20,150);
        pagina.drawString("USUARIO :"+Inicio.usuario.getNombre(),320,110);
        pagina.setFont(fuente6);
        Double monto=0.00; //caja.getMontoMovimiento()* -1;
        
        pagina.drawString("RAZON SOCIAL: "+cliente.getNombre(),30,185);
        pagina.drawString("C.U.I.T.: "+cliente.getCuit(), 350,185);
        pagina.drawString("DIRECCION: "+cliente.getDireccion(),30,200);
        
        pagina.drawString("LOCALIDAD: "+cliente.getDescripcionLocalidad(),350,200);
        pagina.drawString("REC PROVEEDOR: "+rec.getNumeroRecibo(),30,215);
        pagina.setFont(fuente);
        pagina.drawLine(20, 240, 600, 240);
        pagina.drawString("",20,250);
        pagina.drawString("DETALLE",100,250);
        pagina.drawString("MONTO FC", 300,250);
        pagina.drawString("MONTO", 400,250);
        pagina.drawString("SALDO", 500,250);
        int renglon=260;
        //pagina.drawLine(20, 260, 600, 260);
        Iterator it=detalle.listIterator();
        Double saldoI=0.00;
        while(it.hasNext()){
            det=(DetalleOrdenDePago)it.next();
            //pagina.drawString(String.valueOf(det.get),40,renglon);
            pagina.drawString("Factura: "+det.getNumeroStringFac()+" fecha "+Numeros.ConvertirFecha(det.getFecha()),30,renglon);
            pagina.drawString(det.getMontoFac(),300,renglon);
            pagina.drawString(Numeros.ConvertirNumero(det.getMonto()),400,renglon);
            saldoI=saldoI + det.getSaldoItem();
            pagina.drawString(Numeros.ConvertirNumero(det.getSaldoItem()),500,renglon);
            renglon=renglon + 10;
        }
        //formulario derecho
        renglon=renglon + 10;
        pagina.drawString("Saldo Total Proveedor: $ "+cliente.getSaldo(),30,renglon);
        renglon=renglon +10;
        pagina.drawLine(20, renglon, 600, renglon);
        renglon=renglon + 20;
        pagina.drawString("DETALLE PAGO",100,renglon);
        pagina.drawString("MONTO", 350,renglon);
        renglon=renglon + 20;
        it=pagos.listIterator();
        while(it.hasNext()){
            formas=(FormasDePago)it.next();
            //pagina.drawString(String.valueOf(det.get),40,renglon);
            if(formas.getDescripcion().equals("Cheque")){
            pagina.drawString(String.valueOf(formas.getDescripcion()+" "+formas.getBanco()+" "+formas.getNumero()+" "+formas.getVencimiento()),40,renglon);
            }else{
                pagina.drawString(formas.getDescripcion()+" ",40,renglon);
            }
            pagina.drawString(String.valueOf(formas.getMonto()),370,renglon);
            renglon=renglon + 10;
        }
        //pagina.drawImage(imagen,363,20,174,93,null);
        pagina.drawLine(20, renglon, 600, renglon);
        renglon=renglon + 20;
        String letras=NumberToLetterConverter.convertNumberToLetter(rec.getMonto());
        pagina.drawString("SON PESOS: "+letras,40,renglon);        
        
        pagina.dispose();
        pj.end();
        }catch(Exception e)
	{
		System.out.println("LA IMPRESION HA SIDO CANCELADA..."+e);
	}

    }
    public void ReImprimirOrdenDeTrabajo(Object recibo) throws IOException{
        OrdenDePago rec=new OrdenDePago();
        DetalleOrdenDePago det=new DetalleOrdenDePago();
        FormasDePago formas=new FormasDePago();
        rec=(OrdenDePago)recibo;
        ArrayList detalle=new ArrayList();
        ArrayList pagos=new ArrayList();
        Recidable reci=new DetalleOrdenDePago();
        Formable ff=new FormasDePago();
        detalle=reci.listar(rec.getId());
        pagos=ff.listarPagosProveedores(rec.getId());
        /*
        cotizacion=(Pedidos)cotizable.cargarEncabezadoPedido(idCotizacion);
        ArrayList listadoDetalle=new ArrayList();
        DetallePedidos detalleDeCotizacion=new DetallePedidos();
        Pedable cotiz=new DetallePedidos();
        listadoDetalle=cotiz.cargarDetallePedido(cotizacion.getId());
        */
        
        Proveedores cliente=new Proveedores();
        Personalizable factu=new Proveedores();
        cliente=(Proveedores)factu.buscarPorNumero(rec.getIdCliente());
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
        pagina.drawString("ORDEN DE PAGO N° 0000"+Inicio.sucursal.getNumero()+"-000"+rec.getId(),20,110);
        pagina.setFont(fuente);
        pagina.drawString("FECHA IMPRESION:"+fec, 20,120);
        //pagina.drawString(" :"+Inicio.sucursal.getDescripcion(),20,150);
        pagina.drawString("USUARIO :"+Inicio.usuario.getNombre(),320,110);
        pagina.setFont(fuente6);
        Double monto=0.00; //caja.getMontoMovimiento()* -1;
        
        pagina.drawString("RAZON SOCIAL: "+cliente.getNombre(),30,185);
        pagina.drawString("C.U.I.T.: "+cliente.getCuit(), 350,185);
        pagina.drawString("DIRECCION: "+cliente.getDireccion(),30,200);
        
        pagina.drawString("LOCALIDAD: "+cliente.getDescripcionLocalidad(),350,200);
        pagina.drawString("REC PROVEEDOR: "+rec.getNumeroRecibo(),30,215);
        pagina.setFont(fuente);
        pagina.drawLine(20, 240, 600, 240);
        pagina.drawString("",20,250);
        pagina.drawString("DETALLE",100,250);
        pagina.drawString("MONTO FC", 300,250);
        pagina.drawString("MONTO", 400,250);
        pagina.drawString("SALDO", 500,250);
        int renglon=260;
        //pagina.drawLine(20, 260, 600, 260);
        Iterator it=detalle.listIterator();
        Double saldoI=0.00;
        while(it.hasNext()){
            det=(DetalleOrdenDePago)it.next();
            //pagina.drawString(String.valueOf(det.get),40,renglon);
            pagina.drawString("Factura: "+det.getNumeroStringFac()+" fecha "+Numeros.ConvertirFecha(det.getFecha()),30,renglon);
            pagina.drawString(Numeros.ConvertirNumero(det.getMontoFacturaDouble()),300,renglon);
            pagina.drawString(Numeros.ConvertirNumero(det.getMonto()),400,renglon);
            saldoI=saldoI + det.getSaldoItem();
            pagina.drawString(Numeros.ConvertirNumero(det.getSaldoItem()),500,renglon);
            renglon=renglon + 10;
        }
        //formulario derecho
        renglon=renglon + 10;
        pagina.drawString("Saldo Total Proveedor: $ "+cliente.getSaldo(),30,renglon);
        renglon=renglon +10;
        pagina.drawLine(20, renglon, 600, renglon);
        renglon=renglon + 20;
        pagina.drawString("DETALLE PAGO",100,renglon);
        pagina.drawString("MONTO", 350,renglon);
        renglon=renglon + 20;
        it=pagos.listIterator();
        while(it.hasNext()){
            formas=(FormasDePago)it.next();
            //pagina.drawString(String.valueOf(det.get),40,renglon);
            if(formas.getDescripcion().equals("Cheque")){
            pagina.drawString(String.valueOf(formas.getDescripcion()+" "+formas.getBanco()+" "+formas.getNumero()+" "+formas.getVencimiento()),40,renglon);
            }else{
                pagina.drawString(formas.getDescripcion()+" ",40,renglon);
            }
            pagina.drawString(String.valueOf(formas.getMonto()),370,renglon);
            renglon=renglon + 10;
        }
        //pagina.drawImage(imagen,363,20,174,93,null);
        pagina.drawLine(20, renglon, 600, renglon);
        renglon=renglon + 20;
        String letras=NumberToLetterConverter.convertNumberToLetter(rec.getMonto());
        pagina.drawString("SON PESOS: "+letras,40,renglon);        
        
        pagina.dispose();
        pj.end();
        }catch(Exception e)
	{
		System.out.println("LA IMPRESION HA SIDO CANCELADA..."+e);
	}

    }
					
}//FIN DE LA CLASE Impresora

 

