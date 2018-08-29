/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pedidos;

import interfaces.Transaccionable;
import interfacesPrograma.Facturar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import Articulos.Articulos;
import Clientes.Objectos.Clientes;
import Reparto.PedidosParaReparto;
import Reparto.interfaces.ExportacionDePedidos;
import interfaceGraficas.Inicio;
import objetos.Conecciones;

/**
 *
 * @author mauro di
 */
public class DetallePedidos implements Pedable{
    private Integer id;
    private Integer idPedido;
    private Integer idArticulo;
    private String descripcionArticulo;
    private Integer idCliente;
    private Double cantidad;
    private Double precioUnitario;
    private String observaciones;
    private Double cantidadFacturada;
    private Double cantidadRemitida;
    private int descuento;

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Double getCantidadFacturada() {
        return cantidadFacturada;
    }

    public void setCantidadFacturada(Double cantidadFacturada) {
        this.cantidadFacturada = cantidadFacturada;
    }

    public Double getCantidadRemitida() {
        return cantidadRemitida;
    }

    public void setCantidadRemitida(Double cantidadRemitida) {
        this.cantidadRemitida = cantidadRemitida;
    }
    private Boolean EnviarAReparto(String entrega,ArrayList listado,String guardarF){
        DetallePedidos detalle;
        Iterator it=listado.listIterator();
        PedidosParaReparto pedido;
        Boolean confirmado=false;
        Clientes cliente;
        Articulos articulo;
        ArrayList enviados=new ArrayList();
        Integer idPedido=0;
        Transaccionable tra=new Conecciones();
        String sql=null;
        while(it.hasNext()){
            detalle=(DetallePedidos) it.next();
            pedido=new PedidosParaReparto();
            cliente=new Clientes();
            articulo=new Articulos();
            articulo=(Articulos) articulo.cargarPorCodigoAsignado(detalle.getIdArticulo());
            cliente=(Clientes) cliente.cargarPorCodigoAsignado(detalle.getIdCliente());
            pedido.setCodigoTangoDePedido(String.valueOf(detalle.getIdPedido()));
            pedido.setFechaPedidosTango(Inicio.fechaDia);
            pedido.setCodigoCliente(String.valueOf(detalle.getIdCliente()));
            pedido.setRazonSocial(cliente.getRazonSocial());
            if(cliente.getCupoDeCredito() > cliente.getSaldo()){
                pedido.setCondicionDeVenta(1);
            }else{
                pedido.setCondicionDeVenta(0);
            }
            pedido.setObservaciones(detalle.getObservaciones());
            pedido.setObservaciones1("");
            pedido.setObservaciones2("");
            pedido.setCodigoArticulo(articulo.getCodigoDeBarra());
            pedido.setDescripcionArticulo(detalle.getDescripcionArticulo());
            pedido.setCantidadArticulo(detalle.getCantidad());
            pedido.setCantidadArticuloPendiente(detalle.getCantidad());
            pedido.setCantidadArticulosTotales(detalle.getCantidadRemitida());
            pedido.setFechaEnvio(entrega);
            pedido.setZonaAsignada(1);
            pedido.setAlertaAsignada(0);
            pedido.setNumeroVendedor(Inicio.usuario.getNumeroId());
            idPedido=detalle.getIdPedido();
            pedido.setIdPedidoEnTango(detalle.getId());
            sql="update detallepedidos set entrega='"+guardarF+"' where id="+detalle.getId();
            tra.guardarRegistro(sql);
            enviados.add(pedido);
        }
        
        
        sql="update pedidos set entrega='"+guardarF+"' where id="+idPedido;
        tra.guardarRegistro(sql);
        if(enviados.size() > 0){
            ExportacionDePedidos expor=new PedidosParaReparto();
            expor.enviar(enviados);
            confirmado=true;
        }else{
            confirmado=false;
        }
        return confirmado;
    }
    @Override
    public Integer nuevoPedido(Object ped) {
        DetallePedidos detalle=new DetallePedidos();
        detalle=(DetallePedidos)ped;
        Integer numero=0;
        String sql="insert into detallepedidos (idpedido,idarticulo,descripcionarticulo,idcliente,cantidad,preciounitario,descuento,cantidadpendiente) values ("+detalle.getIdPedido()+","+detalle.getIdArticulo()+",'"+detalle.getDescripcionArticulo()+"',"+detalle.getIdCliente()+","+detalle.getCantidad()+","+detalle.getPrecioUnitario()+","+detalle.getDescuento()+","+detalle.getCantidad()+")";
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        
        return numero;
    }

    @Override
    public ArrayList cargarDetallePedido(Integer idPed) {
        ArrayList <DetallePedidos> listadoP=new ArrayList();
        DetallePedidos detalle;
        String sql="select * from detallepedidos where idpedido="+idPed;
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                detalle=new DetallePedidos();
                detalle.setId(rs.getInt("id"));
                detalle.setCantidad(rs.getDouble("cantidadpendiente"));
                detalle.setCantidadFacturada(0.00);
                detalle.setCantidadRemitida(rs.getDouble("cantidad") - rs.getDouble("cantidadpendiente"));
                detalle.setDescripcionArticulo(rs.getString("descripcionarticulo"));
                detalle.setDescuento(rs.getInt("descuento"));
                detalle.setIdArticulo(rs.getInt("idarticulo"));
                detalle.setIdCliente(rs.getInt("idcliente"));
                detalle.setIdPedido(rs.getInt("idpedido"));
                detalle.setObservaciones(rs.getString("observaciones"));
                detalle.setPrecioUnitario(rs.getDouble("preciounitario"));
                listadoP.add(detalle);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetallePedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listadoP;
    }

    @Override
    public Object cargarEncabezadoPedido(Integer idPed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorCliente(Integer idClient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorEstado(Integer idClient, int estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean modificarPedido(Object ped) {
        DetallePedidos detalle=new DetallePedidos();
        detalle=(DetallePedidos)ped;
        String sql="update detallepedidos set descripcionarticulo='"+detalle.getDescripcionArticulo()+"',cantidad="+detalle.getCantidad()+",preciounitario="+detalle.getPrecioUnitario()+", descuento="+detalle.getDescuento()+" where id="+detalle.getId();
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        return true;
        
    }

    @Override
    public void eliminarPedido(Integer idPed) {
        String sql="delete from detallepedidos where id="+idPed;
        Transaccionable tra=new Conecciones();
       tra.guardarRegistro(sql);
    }

    @Override
    public DefaultTableModel mostrarListado(ArrayList lista) {
        Iterator it=lista.listIterator();
        DetallePedidos detalle=new DetallePedidos();
        DefaultTableModel modelo=new DefaultTableModel();
        modelo.addColumn("CODIGO");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("CANTIDAD");
        Object[] fila=new Object[3];
        while(it.hasNext()){
            detalle=(DetallePedidos) it.next();
            fila[0]=detalle.getIdArticulo();
            fila[1]=detalle.getDescripcionArticulo();
            fila[2]=detalle.getCantidad();
            modelo.addRow(fila);
        }
        return modelo;
    }

    @Override
    public Integer transformarEnFactura(Object ped, ArrayList detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public ArrayList convertirAArticulos(ArrayList listado) {
        Articulos articulo;
        Facturar fact=new Articulos(); 
        DetallePedidos detalle=new DetallePedidos();
        ArrayList listadoA=new ArrayList();
        Iterator it=listado.listIterator();
        while(it.hasNext()){
            detalle=(DetallePedidos)it.next();
            articulo=new Articulos();
            if(detalle.getIdArticulo()==0){
             articulo.setNumeroId(detalle.getIdArticulo());
             articulo.setDescripcionArticulo(detalle.getDescripcionArticulo());
             articulo.setIdCombo(0);
             articulo.setPrecioDeCosto(0.00);
            }else{
            articulo=(Articulos) fact.cargarPorCodigoAsignado(detalle.getIdArticulo());
            }
            articulo.setPrecioUnitarioNeto(detalle.getPrecioUnitario());
            articulo.setCantidad(detalle.getCantidad());
            articulo.setIdRenglon(detalle.getId());
            listadoA.add(articulo);
        }
        
        return listadoA;
    }

    @Override
    public Object ActualizarPedido(Object ped) {
        Pedidos cotizac=(Pedidos) ped;
        
        String sql="select *,articulos.PRECIO as PRECIO from detallepedidos left join articulos on articulos.id=detallepedidos.idArticulo where idpedido="+cotizac.getId();
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        DetallePedidos cotizacionD;
        ArrayList listado=new ArrayList();
        try {
            while(rs.next()){
                cotizacionD=new DetallePedidos();
                cotizacionD.setId(rs.getInt("id"));
                cotizacionD.setIdArticulo(rs.getInt("idArticulo"));
                cotizacionD.setDescripcionArticulo(rs.getString("descripcionarticulo"));
                cotizacionD.setCantidad(rs.getDouble("cantidad"));
                cotizacionD.setIdCliente(rs.getInt("idcliente"));
                cotizacionD.setIdPedido(rs.getInt("idpedido"));
                cotizacionD.setPrecioUnitario(rs.getDouble("PRECIO"));
                cotizacionD.setDescuento(rs.getInt("descuento"));
                //cotizacionD.setMontoDescuento(rs.getDouble("montoDescuento"));
                listado.add(cotizacionD);
            }
            rs.close();
            Iterator it=listado.listIterator();
            Double total=0.00;
            Double items=0.00;
            Double subTotal=0.00;
            Double descuento=0.00;
            while(it.hasNext()){
                cotizacionD=(DetallePedidos) it.next();
                sql="update detallepedidos set preciounitario="+cotizacionD.getPrecioUnitario()+" where id="+cotizacionD.getId();
                tra.guardarRegistro(sql);
                items=cotizacionD.getPrecioUnitario() * cotizacionD.getCantidad();
                total=total + items;
            }
            total=total * 1.21;
            if(cotizac.getPorcentajeDescuento() > 0){
                descuento=total * cotizac.getPorcentajeDescuento();
                subTotal=total;
                total=total - descuento;
                //cotizac.setDescuento(descuento);
                
            }else{
                subTotal=total;
            }
            cotizac.setSubTotal(subTotal);
            cotizac.setTotal(total);
            cotizac.setDescuento(descuento);
        } catch (SQLException ex) {
            Logger.getLogger(DetallePedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cotizac;
    }

    @Override
    public Object AplicarRecargo(Double tasa,Object ped) {
        Pedidos cotizac=(Pedidos) ped;
        
        String sql;
        Transaccionable tra=new Conecciones();
        //ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        DetallePedidos cotizacionD;
        
                cotizacionD=(DetallePedidos) ped;
                sql="update detallepedidos set preciounitario=round(preciounitario * "+tasa+",2) where id="+cotizacionD.getId();
                tra.guardarRegistro(sql);
                
            
        return cotizac;
    }

    @Override
    public Boolean EnviarReparto(String entrega, ArrayList lista,String fechaG) {
        return this.EnviarAReparto(entrega, lista,fechaG);
    }
    
    
}
