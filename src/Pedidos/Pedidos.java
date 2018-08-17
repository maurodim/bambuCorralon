/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pedidos;

import Articulos.Articulos;
import Clientes.Objectos.Clientes;
import Conversores.Numeros;
import Clientes.Objectos.MovimientosClientes;
import Configuracion.Propiedades;
import objetos.FacturaElectronica;
import Recibos.DetalleRecibo;
import Recibos.Recidable;
import interfaceGraficas.Inicio;
import interfaces.Editables;
import interfaces.FacturableE;
import interfaces.Transaccionable;
import interfacesPrograma.Facturar;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import objetos.Comprobantes;
import objetos.Conecciones;
import objetos.DetalleFacturas;
import objetos.TiposIva;

/**
 *
 * @author mauro di
 */
public class Pedidos implements Pedable,Recidable{
    private Integer id;
    private Integer idCliente;
    private Date fecha;
    private Double total;
    private Integer idUsuario;
    private Integer idCotizacion;
    private Integer idFactura;
    private Integer idRemito;
    private String archivo;
    private int estado;
    private static Transaccionable tra=new Conecciones();
    private static ResultSet rs;
    private static String sql;
    private Double subTotal;
    private Double descuento;
    private Double porcentajeDescuento;
    private int pagado;
    private Double saldo;
    private String fechaEntrega;

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    

    public int getPagado() {
        return pagado;
    }

    public void setPagado(int pagado) {
        this.pagado = pagado;
    }
    

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Integer idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    private void ImpactarPago(DetalleRecibo det){
        DetalleRecibo detalle=det;
        Double montoP=Numeros.ConvertirStringADouble(det.getMontoFcatura());
        if(det.getMonto() < montoP){
            sql="update pedidos set saldo=round(total - "+det.getMonto()+",2) where id="+det.getIdFactura();
        }else{
            sql="update pedidos set saldo=round(total - "+det.getMonto()+",2),pagado=1 where id="+det.getIdFactura();
        }
        tra.guardarRegistro(sql);
    }
    private Integer ConvertirEnFacturaElectronica(Object ped){
        Pedidos pedido=(Pedidos) ped;
        Facturar fact=new Clientes();
        Pedable peda=new Pedidos();
        DetallePedidos detallePedido=new DetallePedidos();
        Pedable detP=new DetallePedidos();
        Clientes cliT=new Clientes();
        
        cliT=(Clientes)fact.cargarPorCodigoAsignado(pedido.getIdCliente());
        //lista=new ListasDePrecios(cliT.getListaDePrecios());
        ArrayList detalleDelPedido=detP.cargarDetallePedido(pedido.getId());
        //detalleDelPedido=detP.convertirAArticulos(listadoPed);
        //porcentajeDescuento=pedido.getPorcentajeDescuento();
        //subTotal=0.00;
        
        
        
        String cadena=cliT.getCodigoCliente()+" - "+cliT.getRazonSocial()+"\n"+cliT.getDireccion();
        //comp.setCliente(cliT);
        //VisorDeHojaDeRuta

        //comp.setVendedor(VisorDeHojaDeRuta.tG.getOperador());
        

        //comp.setArticulos(detalleDelPedido);
        DecimalFormat fr=new DecimalFormat("00");
        Calendar c1=Calendar.getInstance();
        Calendar c2=new GregorianCalendar();
        String dia=Integer.toString(c2.get(Calendar.DAY_OF_MONTH));
        String mes=Integer.toString(c2.get(Calendar.MONTH));
        String ano=Integer.toString(c2.get(Calendar.YEAR));

        int da=Integer.parseInt(dia);
        int me=Integer.parseInt(mes);
        me++;
        dia=fr.format(da);
        mes=fr.format(me);
        String fecha=dia+"/"+mes+"/"+ano;
        String fecha2=ano+"-"+mes+"-"+dia;
        //comp.setFechaComprobante(fecha2);
        //comp.setFechaComprobante(fecha);
        
        int comprobanteTipo=1;
        //cliT.setCondicionIva("1");
        if(cliT.getCondicionIva().equals("1"))comprobanteTipo=1;
        if(cliT.getCondicionIva().equals("2"))comprobanteTipo=2;
        if(cliT.getCondicionIva().equals("3"))comprobanteTipo=3;
        
        Comprobantes comprobante=new Comprobantes();
        comprobante.setFe(true);
        comprobante.setCliente(cliT);
        comprobante.setTipoMovimiento(1);
        comprobante.setIdPedido(pedido.getId());
        comprobante.setTipoComprobante(comprobanteTipo);
        comprobante.setFechaEmision((Date.valueOf(fecha2)));
        comprobante.setListadoDeArticulos(detalleDelPedido);
        comprobante.setUsuarioGenerador(Inicio.usuario.getNumero());
        comprobante.setIdSucursal(Inicio.sucursal.getNumero());
        comprobante.setIdDeposito(Inicio.deposito.getNumero());
        Integer numeroCaja=Inicio.caja.getNumero();
        //System.out.println("EL NUMERO DE CAJA ESSSSSSSS "+numeroCaja);
        comprobante.setIdCaja(numeroCaja);
        /*
           double montoTotal=Math.round(montoTotal * 100.0) / 100.0;
            subTotal=montoTotal / 1.21;
            //Double ivv=subTotal / 1.21;
            subTotal=Math.round(subTotal * 100.0) / 100.0;
            //Double ivv=Math.round(ivv * 100.0) / 100.0;
            Double sub=0.00;
            Double tot=montoTotal - subTotal;
            tot=Math.round(tot * 100.0) /100.0;
            porcentajeDescuento=0.00;
            if(porcentajeDescuento > 0.00){
                sub = subTotal * porcentajeDescuento;
                sub= montoTotal - sub;
            }else{
                sub=montoTotal;
            }
            */
        
            comprobante.setMontoTotal(pedido.getTotal());
            comprobante.setSubTotal(pedido.getSubTotal());
            double iva=pedido.getTotal() - pedido.getSubTotal();
            comprobante.setMontoIva(iva);
            comprobante.setMontoBruto(pedido.getSubTotal());
            Double descuen=pedido.getDescuento();
            comprobante.setDescuento(descuen);
            comprobante.setPorcentajeDescuento(pedido.getPorcentajeDescuento());

            int noFacturar=0;
            
                comprobante.setPagado(1);
            
                //comprobante.setFiscal(1);
                Facturar fat=new Comprobantes();
                comprobante=(Comprobantes) fat.guardar(comprobante);
                // aqui hago el envio a factura  electronica, si aprueba no imprime
                
                FacturaElectronica fe=new FacturaElectronica();
                FacturableE factuE=new FacturaElectronica();
                ArrayList listadoIva=new ArrayList();
                Double montoIva=0.00;
                Double total1=pedido.total;
                Double subTotal1=pedido.subTotal;
                if(Propiedades.getCONDICIONIVA().equals("2")){
                    subTotal1=Math.round((total1 / 1.21) *100.0) / 100.0;
                    Double iiv=Math.round((total1 - subTotal1) * 100.0) / 100.0;
                    float subT=Float.parseFloat(String.valueOf(subTotal1));
                    float totT=Float.parseFloat(String.valueOf(iiv));
                    TiposIva iva1=new TiposIva(5,subT,totT,21);
                    listadoIva.add(iva1);
                    montoIva=iva;
                }else{
                    listadoIva=null;
                }
                ArrayList listadoTrib=null;
                ArrayList <DetalleFacturas> listadoDetalle=new ArrayList();
                Pedable ppd=new DetallePedidos();
                ArrayList lst=ppd.convertirAArticulos(detalleDelPedido);
                
                Iterator itD=lst.listIterator();
                Articulos artic;
                DetalleFacturas detalle;
                double precio=0.00;
                while(itD.hasNext()){
                    artic=(Articulos) itD.next();
                    detalle=new DetalleFacturas();
                    detalle.setCodigo(artic.getCodigoAsignado());
                    detalle.setDescripcion(artic.getDescripcionArticulo());
                    detalle.setCantidadS(String.valueOf(artic.getCantidad()));
                    
                    precio=Math.round(artic.getPrecioUnitarioNeto() * 100.0) / 100.0;
                    detalle.setPrecioUnitarioS(String.valueOf(precio));
                    listadoDetalle.add(detalle);
                }
                //montoIva=tot;
                System.out.println(Propiedades.getARCHIVOCRT()+" // condicion iva "+Propiedades.getCONDICIONIVA()+" // punto de venta "+Propiedades.getPUNTODEVENTA()+" // tipo de venta "+Propiedades.getTIPODEVENTA());
                int condicion=Integer.parseInt(Propiedades.getCONDICIONIVA());
                int ptoVta=Integer.parseInt(Propiedades.getPUNTODEVENTA());
                int tipoVta=Integer.parseInt(Propiedades.getTIPODEVENTA());
                if(cliT.getNumeroDeCuit() != null){
                    
                }else{
                    if(cliT.getNumeroDeCuit().length() == 1){
                        if(cliT.getTipoIva()==4){
                            cliT.setNumeroDeCuit("0");
                        }else{
                            cliT.setNumeroDeCuit("000");
                        }
                    }
                }
                Integer idPed=0;
                if(pedido.getId() != null)idPed=pedido.getId();
                Integer nro=factuE.generar(null, condicion, Propiedades.getARCHIVOKEY(),Propiedades.getARCHIVOCRT(),cliT.getCodigoId(), cliT.getNumeroDeCuit(), comprobante.getTipoComprobante(), total1, subTotal1, montoIva, ptoVta, Propiedades.getCUIT(), tipoVta, listadoIva, listadoTrib, cliT.getRazonSocial(), cliT.getDireccion(), cliT.getCondicionIva(), listadoDetalle,idPed);
                System.out.println("COMPROBANTE FISCAL N° "+nro);
                sql="update pedidos set idfactura="+comprobante.getIdFactura()+" where id="+pedido.getId();
                tra.guardarRegistro(sql);
                sql="update facturas set numerofactura="+nro+" where id="+comprobante.getIdFactura();
                tra.guardarRegistro(sql);
                return nro;
                
    }
    private void EnviarAReparto(Object ped){
        Pedidos pedido=(Pedidos) ped;
        //ACA DEBO HACER UN UPDATE DE PEDIDOS ASIGNANDO LA FECHA DE ENTREGA Y ENVIAR LOS DATOS DEL PEDIDO A PEDIDOS_CARGA1 ASI LO VA A LEER EL MODULO DE REPARTO
        
    }
    @Override
    public Integer nuevoPedido(Object ped) {
        Pedidos pedido=new Pedidos();
        pedido=(Pedidos)ped;
        Integer verif=0;
        sql="insert into pedidos (idcliente,fecha,total,idusuario,idcotizacion,estado,subtotal,descuento,porcentajed,saldo) values ("+pedido.getIdCliente()+",'"+pedido.getFecha()+"',round("+pedido.getTotal()+",2),"+pedido.getIdUsuario()+","+pedido.getIdCotizacion()+",0,round("+pedido.getSubTotal()+",2),round("+pedido.getDescuento()+",2),"+pedido.getPorcentajeDescuento()+",round("+pedido.getTotal()+",2))";
        //Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        sql="select LAST_INSERT_ID()";
        
        rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                verif=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        MovimientosClientes movimiento=new MovimientosClientes();
        movimiento.setIdCliente(pedido.getIdCliente());
        movimiento.setMonto(pedido.getTotal());
        movimiento.setPagado(pedido.getPagado());
        movimiento.setNumeroComprobante(verif);
        movimiento.setIdRemito(pedido.getIdRemito());
        movimiento.setIdUsuario(Inicio.usuario.getNumeroId());
        movimiento.setIdCaja(Inicio.caja.getNumero());
        movimiento.setTipoComprobante(5);
        movimiento.setIdSucursal(1);
        movimiento.setEstado(0);
        movimiento.setIdPedido(verif);
        Editables edita=new MovimientosClientes();
        edita.AltaObjeto(movimiento);
        return verif;
    }

    @Override
    public ArrayList cargarDetallePedido(Integer idPed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object cargarEncabezadoPedido(Integer idPed) {
        Pedidos pedido=new Pedidos();
        //ArrayList listado=new ArrayList();
        sql="select * from pedidos where id="+idPed;
        
        rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                pedido.setId(rs.getInt("id"));
                pedido.setEstado(rs.getInt("estado"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setIdCliente(rs.getInt("idcliente"));
                pedido.setIdCotizacion(rs.getInt("idcotizacion"));
                pedido.setIdFactura(rs.getInt("idfactura"));
                pedido.setIdRemito(rs.getInt("idremito"));
                pedido.setIdUsuario(rs.getInt("idusuario"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setSubTotal(rs.getDouble("subtotal"));
                pedido.setDescuento(rs.getDouble("descuento"));
                pedido.setPorcentajeDescuento(rs.getDouble("porcentajed"));
                pedido.setSaldo(rs.getDouble("saldo"));
                pedido.setFechaEntrega(rs.getString("entrega"));
                //listado.add(pedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pedido;
    }

    @Override
    public ArrayList listar() {
        Pedidos pedido;
        ArrayList listado=new ArrayList();
        sql="select * from pedidos order by id desc";
        
        rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                pedido=new Pedidos();
                pedido.setId(rs.getInt("id"));
                pedido.setEstado(rs.getInt("estado"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setIdCliente(rs.getInt("idcliente"));
                pedido.setIdCotizacion(rs.getInt("idcotizacion"));
                pedido.setIdFactura(rs.getInt("idfactura"));
                pedido.setIdRemito(rs.getInt("idremito"));
                pedido.setIdUsuario(rs.getInt("idusuario"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setSubTotal(rs.getDouble("subtotal"));
                pedido.setDescuento(rs.getDouble("descuento"));
                pedido.setPorcentajeDescuento(rs.getDouble("porcentajed"));
                pedido.setSaldo(rs.getDouble("saldo"));
                pedido.setFechaEntrega(rs.getString("entrega"));
                listado.add(pedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listado;
    }

    @Override
    public ArrayList listarPorCliente(Integer idClient) {
        Pedidos pedido;
        ArrayList listado=new ArrayList();
        String sql="select * from pedidos where idcliente="+idClient+" order by id desc";
        
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                pedido=new Pedidos();
                pedido.setId(rs.getInt("id"));
                pedido.setEstado(rs.getInt("estado"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setIdCliente(rs.getInt("idcliente"));
                pedido.setIdCotizacion(rs.getInt("idcotizacion"));
                pedido.setIdFactura(rs.getInt("idfactura"));
                pedido.setIdRemito(rs.getInt("idremito"));
                pedido.setIdUsuario(rs.getInt("idusuario"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setSubTotal(rs.getDouble("subtotal"));
                pedido.setDescuento(rs.getDouble("descuento"));
                pedido.setPorcentajeDescuento(rs.getDouble("porcentajed"));
                pedido.setSaldo(rs.getDouble("saldo"));
                pedido.setFechaEntrega(rs.getString("entrega"));
                listado.add(pedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listado;
    }

    @Override
    public ArrayList listarPorEstado(Integer idClient, int estado) {
        Pedidos pedido;
        ArrayList listado=new ArrayList();
        //String sql="select *,(select facturas.numerofactura from facturas where facturas.id=pedidos.idfactura)as factura from pedidos where idcliente="+idClient+" and idfactura=0 and pagado=0 order by id desc";
        String sql="select *,(select facturas.numerofactura from facturas where facturas.id=pedidos.idfactura)as factura from pedidos where idcliente="+idClient+" and idfactura=0 and saldo > 0 order by id desc";
        System.out.println(sql);
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                pedido=new Pedidos();
                pedido.setId(rs.getInt("id"));
                pedido.setEstado(rs.getInt("estado"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setIdCliente(rs.getInt("idcliente"));
                pedido.setIdCotizacion(rs.getInt("idcotizacion"));
                pedido.setIdFactura(rs.getInt("factura"));
                pedido.setIdRemito(rs.getInt("idremito"));
                pedido.setIdUsuario(rs.getInt("idusuario"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setSubTotal(rs.getDouble("subtotal"));
                pedido.setDescuento(rs.getDouble("descuento"));
                pedido.setPorcentajeDescuento(rs.getDouble("porcentajed"));
                pedido.setSaldo(rs.getDouble("saldo"));
                pedido.setFechaEntrega(rs.getString("entrega"));
                listado.add(pedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listado;
    }

    @Override
    public Boolean modificarPedido(Object ped) {
        Boolean verif=true;
        Pedidos pedido=new Pedidos();
        pedido=(Pedidos)ped;
        String sql="update pedidos set total=round("+pedido.getTotal()+",2),subtotal=round("+pedido.getSubTotal()+",2),saldo=round("+pedido.getSaldo()+",2),descuento=round("+pedido.getDescuento()+",2),porcentajed="+pedido.getPorcentajeDescuento()+",idcotizacion="+pedido.getIdCotizacion()+",idfactura="+pedido.getIdFactura()+",idremito="+pedido.getIdRemito()+",idremito="+pedido.getIdRemito()+",estado="+pedido.getEstado()+" where id="+pedido.getId();
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        
        return true;
    }

    @Override
    public void eliminarPedido(Integer idPed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel mostrarListado(ArrayList lista) {
        MiModeloTablaArticulos listado1=new MiModeloTablaArticulos();
        Pedidos cotizacion;
        Iterator iL=lista.listIterator();
        listado1.addColumn("Numero");
        listado1.addColumn("Fecha");
        listado1.addColumn("Factura");
        listado1.addColumn("Remito");
        listado1.addColumn("Monto");
        listado1.addColumn("Saldo");
        listado1.addColumn("Entrega");
        Object[] fila=new Object[7];
        while(iL.hasNext()){
            cotizacion=(Pedidos)iL.next();
            fila[0]=String.valueOf(cotizacion.getId());
            fila[1]=String.valueOf(cotizacion.getFecha());
            fila[2]=String.valueOf(cotizacion.getIdFactura());
            fila[3]=String.valueOf(cotizacion.getIdRemito());
            fila[4]=Numeros.ConvertirNumero(cotizacion.getTotal());
            fila[5]=Numeros.ConvertirNumero(cotizacion.getSaldo());
            if(cotizacion.getFechaEntrega() != null){
                fila[6]=cotizacion.getFechaEntrega();
            }else{
                fila[6]="S/ENTREGA";
            }
            listado1.addRow(fila);
        }
        
        return listado1;
    }

    @Override
    public Integer transformarEnFactura(Object ped, ArrayList detalle) {
        return ConvertirEnFacturaElectronica(ped);
        /*
        Pedidos pedido=new Pedidos();
        pedido=(Pedidos)ped;
        sql="update pedidos set estado=1 where id="+pedido.getId();
        tra.guardarRegistro(sql);
*/
    }

    @Override
    public void transformarEnCotizacion(Object ped, ArrayList detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void transformarEnRemito(Object ped, ArrayList detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList convertirAArticulos(ArrayList detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object ActualizarPedido(Object ped) {
        Pedidos pedido=(Pedidos) ped;
        Pedable peda=new DetallePedidos();
        //pedido=(Pedidos) peda.ActualizarPedido(pedido);
        String sql="update pedidos set total=round("+pedido.getTotal()+",2),subtotal=round("+pedido.getSubTotal()+",2),saldo=round("+pedido.getTotal()+",2),descuento="+pedido.getDescuento()+" where id="+pedido.getId();
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        return pedido;
    }

    @Override
    public Object AplicarRecargo(Double tasa,Object ped) {
       Pedidos pedido=(Pedidos) ped;
        Pedable peda=new DetallePedidos();
        //pedido=(Pedidos) peda.ActualizarPedido(pedido);
        String sql="update pedidos set total=round(total * "+tasa+",2),subtotal=round(subtotal * "+tasa+",2),saldo=round(saldo * "+tasa+",2),descuento=round(descuento * "+tasa+",2) where id="+pedido.getId();
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        sql="update movimientosclientes set monto=round(monto * "+tasa+",2) where idpedido="+pedido.id;
        tra.guardarRegistro(sql);
        sql="update detallepedidos set preciounitario=round(preciounitario * "+tasa+",2) where idpedido="+pedido.getId();
        tra.guardarRegistro(sql);
        pedido.subTotal=Math.round((pedido.subTotal * tasa) * 100.0) / 100.0;
        pedido.total=Math.round((pedido.total * tasa) * 100.0) / 100.0;
        return pedido;
    }

    @Override
    public Integer nuevo(Object rec) {
        this.ImpactarPago((DetalleRecibo) rec);
        return 0;
    }

    @Override
    public ArrayList listar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double imputarAFactura(Object rec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel mostrarARecibir(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel mostrarARecibirSuma(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object cargar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean EnviarReparto(String entrega, ArrayList lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
