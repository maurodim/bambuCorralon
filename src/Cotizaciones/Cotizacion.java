/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cotizaciones;

import Pedidos.DetallePedidos;
import Pedidos.Pedable;
import Pedidos.Pedidos;
import interfaceGraficas.Inicio;
import interfaces.Transaccionable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import objetos.Conecciones;

/**
 *
 * @author mauro di
 */
public class Cotizacion implements Cotizable{
    private Integer id;
    private Integer idCliente;
    private Date fecha;
    private Date vencimiento;
    private Double total;
    private Integer idUsuario;
    private int estado;
    private Integer idPedido;
    private String archivo;
    private Double subTotal;
    private Double descuento;
    private Double porcentajeDescuento;

    public Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
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

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public Object cargarEncabezado(Integer id) {
        
        String sql="select * from cotizaciones where id="+id;
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        Cotizacion cotizacion=new Cotizacion();
        try {
            while(rs.next()){
                
                cotizacion.setId(rs.getInt("id"));
                cotizacion.setIdCliente(rs.getInt("idcliente"));
                cotizacion.setIdPedido(rs.getInt("idpedido"));
                cotizacion.setArchivo(rs.getString("archivo"));
                cotizacion.setEstado(rs.getInt("estado"));
                cotizacion.setFecha(rs.getDate("fecha"));
                cotizacion.setIdUsuario(rs.getInt("idusuario"));
                cotizacion.setTotal(rs.getDouble("total"));
                cotizacion.setVencimiento(rs.getDate("vencimiento"));
                cotizacion.setSubTotal(rs.getDouble("subtotal"));
                cotizacion.setDescuento(rs.getDouble("descuento"));
                cotizacion.setPorcentajeDescuento(rs.getDouble("porcentajed"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cotizacion;
    }

    @Override
    public ArrayList cargarDetalle(Integer idCotizacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listar() {
        ArrayList listado=new ArrayList();
        String sql="select * from cotizaciones order by id desc";
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        Cotizacion cotizacion;
        try {
            while(rs.next()){
                cotizacion=new Cotizacion();
                cotizacion.setId(rs.getInt("id"));
                cotizacion.setIdCliente(rs.getInt("idcliente"));
                cotizacion.setIdPedido(rs.getInt("idpedido"));
                cotizacion.setArchivo(rs.getString("archivo"));
                cotizacion.setEstado(rs.getInt("estado"));
                cotizacion.setFecha(rs.getDate("fecha"));
                cotizacion.setIdUsuario(rs.getInt("idusuario"));
                cotizacion.setTotal(rs.getDouble("total"));
                cotizacion.setVencimiento(rs.getDate("vencimiento"));
                cotizacion.setSubTotal(rs.getDouble("subtotal"));
                cotizacion.setDescuento(rs.getDouble("descuento"));
                cotizacion.setPorcentajeDescuento(rs.getDouble("porcentajed"));
                listado.add(cotizacion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public ArrayList listarPorCliente(Integer idCliente) {
        ArrayList listado=new ArrayList();
        String sql="select * from cotizaciones where idcliente="+idCliente+" and estado =0 order by id desc";
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        Cotizacion cotizacion;
        try {
            while(rs.next()){
                cotizacion=new Cotizacion();
                cotizacion.setId(rs.getInt("id"));
                cotizacion.setIdCliente(rs.getInt("idcliente"));
                cotizacion.setIdPedido(rs.getInt("idpedido"));
                cotizacion.setArchivo(rs.getString("archivo"));
                cotizacion.setEstado(rs.getInt("estado"));
                cotizacion.setFecha(rs.getDate("fecha"));
                cotizacion.setIdUsuario(rs.getInt("idusuario"));
                cotizacion.setTotal(rs.getDouble("total"));
                cotizacion.setVencimiento(rs.getDate("vencimiento"));
                cotizacion.setSubTotal(rs.getDouble("subtotal"));
                cotizacion.setDescuento(rs.getDouble("descuento"));
                cotizacion.setPorcentajeDescuento(rs.getDouble("porcentajed"));
                listado.add(cotizacion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public ArrayList listarPorEstado(Integer idCliente, int estado) {
       ArrayList listado=new ArrayList();
        String sql="select * from cotizaciones where idcliente="+idCliente+" and estado="+estado+" order by id desc";
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        Cotizacion cotizacion;
        try {
            while(rs.next()){
                cotizacion=new Cotizacion();
                cotizacion.setId(rs.getInt("id"));
                cotizacion.setIdCliente(rs.getInt("idcliente"));
                cotizacion.setIdPedido(rs.getInt("idpedido"));
                cotizacion.setArchivo(rs.getString("archivo"));
                cotizacion.setEstado(rs.getInt("estado"));
                cotizacion.setFecha(rs.getDate("fecha"));
                cotizacion.setIdUsuario(rs.getInt("idusuario"));
                cotizacion.setTotal(rs.getDouble("total"));
                cotizacion.setVencimiento(rs.getDate("vencimiento"));
                cotizacion.setSubTotal(rs.getDouble("subtotal"));
                cotizacion.setDescuento(rs.getDouble("descuento"));
                cotizacion.setPorcentajeDescuento(rs.getDouble("porcentajed"));
                listado.add(cotizacion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public ArrayList listarPorFecha(Date fechaDesde, Date fechaHasta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorVencimiento(Date fechaDesde, Date fechaHasta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorArticulo(Integer idArticulo, Integer idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorPedido(Integer idPedido, Integer idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer nuevaCotizacion(Object coti) {
        Cotizacion cotizacion=new Cotizacion();
        cotizacion=(Cotizacion)coti;
        String sql="insert into cotizaciones (idcliente,fecha,vencimiento,total,idusuario,subtotal,descuento,porcentajed) values ("+cotizacion.getIdCliente()+",'"+cotizacion.getFecha()+"','"+cotizacion.getVencimiento()+"',round("+cotizacion.getTotal()+",4),"+cotizacion.getIdUsuario()+",round("+cotizacion.getSubTotal()+",4),round("+cotizacion.getDescuento()+",4),"+cotizacion.getPorcentajeDescuento()+")";
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        int ultimo=0;
        sql="select LAST_INSERT_ID()";
        
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                ultimo=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ultimo;
    }

    @Override
    public Object modificarCotizacion(Object coti) {
        Cotizacion cotizacion=new Cotizacion();
        cotizacion=(Cotizacion)coti;
        String sql="update cotizaciones set total=round("+cotizacion.getTotal()+",4),subtotal=round("+cotizacion.getSubTotal()+",4),descuento=round("+cotizacion.getDescuento()+",4),porcentajed="+cotizacion.getPorcentajeDescuento()+", estado="+cotizacion.getEstado()+",idpedido="+cotizacion.getIdPedido()+" where id="+cotizacion.getId();
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        
        return cotizacion;
    }

    @Override
    public void eliminarCotizacion(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel mostrarListado(ArrayList listadoC) {
        MiModeloTablaArticulos listado=new MiModeloTablaArticulos();
        Cotizacion cotizacion;
        Iterator iL=listadoC.listIterator();
        listado.addColumn("Numero");
        listado.addColumn("Fecha");
        listado.addColumn("Vencimiento");
        listado.addColumn("Monto");
        listado.addColumn("Estado");
        Object[] fila=new Object[5];
        while(iL.hasNext()){
            cotizacion=(Cotizacion)iL.next();
            fila[0]=String.valueOf(cotizacion.getId());
            fila[1]=String.valueOf(cotizacion.getFecha());
            fila[2]=String.valueOf(cotizacion.getVencimiento());
            fila[3]=String.valueOf(cotizacion.getTotal());
            if(cotizacion.getEstado()==0){
                fila[4]="Pendiente";
            }else{
                fila[4]="Finalizada";
            }
            listado.addRow(fila);
        }
        
        return listado;
    }

    @Override
    public void transformarEnPedido(Object coti, ArrayList detalle) {
        Pedidos pedido=new Pedidos();
        DetallePedidos detallePedido;
        DetalleCotizacion detalleCotizacion=new DetalleCotizacion();
        Cotizacion cotizacion=new Cotizacion();
        Iterator it=detalle.listIterator();
        Pedable pedPedido=new Pedidos();
        Pedable detPedido=new DetallePedidos();
        cotizacion=(Cotizacion)coti;
        Cotizable cotiza=new Cotizacion();
        
        pedido.setFecha(cotizacion.getFecha());
        pedido.setIdCliente(cotizacion.getIdCliente());
        pedido.setIdCotizacion(cotizacion.getId());
        pedido.setIdFactura(0);
        pedido.setIdRemito(0);
        pedido.setIdUsuario(Inicio.usuario.getNumeroId());
        pedido.setTotal(cotizacion.getTotal());
        pedido.setSubTotal(cotizacion.getSubTotal());
        pedido.setDescuento(cotizacion.getDescuento());
        pedido.setPorcentajeDescuento(cotizacion.getPorcentajeDescuento());
        pedido.setId(pedPedido.nuevoPedido(pedido));
        cotizacion.setEstado(1);
        cotiza.modificarCotizacion(cotizacion);
        
        while(it.hasNext()){
            detallePedido=new DetallePedidos();
            detalleCotizacion=(DetalleCotizacion)it.next();
            detallePedido.setCantidad(detalleCotizacion.getCantidad());
            detallePedido.setCantidadFacturada(0.00);
            detallePedido.setCantidadRemitida(0.00);
            detallePedido.setDescripcionArticulo(detalleCotizacion.getDescripcionArticulo());
            detallePedido.setDescuento(detalleCotizacion.getDescuento());
            detallePedido.setIdArticulo(detalleCotizacion.getIdArticulo());
            detallePedido.setIdCliente(detalleCotizacion.getIdCliente());
            detallePedido.setObservaciones(detalleCotizacion.getObservaciones());
            detallePedido.setPrecioUnitario(detalleCotizacion.getPrecioUnitario());
            detallePedido.setIdPedido(pedido.getId());
            detPedido.nuevoPedido(detallePedido);
            
            
        }
        
        
    }

    @Override
    public void transformarEnFactura(Object coti, ArrayList detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList convertirAArticulos(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
