/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pedidos;

import Conversores.Numeros;
import interfaces.Transaccionable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import objetos.Conecciones;

/**
 *
 * @author mauro di
 */
public class Pedidos implements Pedable{
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

    @Override
    public Integer nuevoPedido(Object ped) {
        Pedidos pedido=new Pedidos();
        pedido=(Pedidos)ped;
        Integer verif=0;
        sql="insert into pedidos (idcliente,fecha,total,idusuario,idcotizacion,estado,subtotal,descuento,porcentajed) values ("+pedido.getIdCliente()+",'"+pedido.getFecha()+"',"+pedido.getTotal()+","+pedido.getIdUsuario()+","+pedido.getIdCotizacion()+",0,round("+pedido.getSubTotal()+",4),round("+pedido.getDescuento()+",4),"+pedido.getPorcentajeDescuento()+")";
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
        String sql="select *,(select facturas.numerofactura from facturas where facturas.id=pedidos.idfactura)as factura from pedidos where idcliente="+idClient+" and estado="+estado+" order by id desc";
        
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
        String sql="update pedidos set total=round("+pedido.getTotal()+",4),subtotal=round("+pedido.getSubTotal()+",4),descuento=round("+pedido.getDescuento()+",4),porcentajed="+pedido.getPorcentajeDescuento()+",idcotizacion="+pedido.getIdCotizacion()+",idfactura="+pedido.getIdFactura()+",idremito="+pedido.getIdRemito()+",idremito="+pedido.getIdRemito()+",estado="+pedido.getEstado()+" where id="+pedido.getId();
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
        listado1.addColumn("Estado");
        Object[] fila=new Object[6];
        while(iL.hasNext()){
            cotizacion=(Pedidos)iL.next();
            fila[0]=String.valueOf(cotizacion.getId());
            fila[1]=String.valueOf(cotizacion.getFecha());
            fila[2]=String.valueOf(cotizacion.getIdFactura());
            fila[3]=String.valueOf(cotizacion.getIdRemito());
            fila[4]=Numeros.ConvertirNumero(cotizacion.getTotal());
            if(cotizacion.getEstado()==0){
                fila[5]="Pendiente";
            }else{
                fila[5]="Finalizada";
            }
            listado1.addRow(fila);
        }
        
        return listado1;
    }

    @Override
    public void transformarEnFactura(Object ped, ArrayList detalle) {
        Pedidos pedido=new Pedidos();
        pedido=(Pedidos)ped;
        sql="update pedidos set estado=1 where id="+pedido.getId();
        tra.guardarRegistro(sql);
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
    
    
}
