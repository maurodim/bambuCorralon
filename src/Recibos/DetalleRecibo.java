/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recibos;

import facturacion.clientes.Facturas;
import interfaces.Transaccionable;

import java.sql.Date;
import java.sql.ResultSet;

import java.util.ArrayList;
import objetos.Conecciones;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;
import tablas.MiModeloTablaContacto;

/**
 *
 * @author mauro di
 */
public class DetalleRecibo implements Recidable{
    private Integer id;
    private Integer idRecibo;
    private Integer idCliente;
    private Integer idFormaDePago;
    private Double monto;
    private Date fecha;
    private Integer idFactura;
    private Integer idPedido;
    private Integer numeroFc;
    private static Transaccionable tra=new Conecciones();
    private static ResultSet rs;
    private String sql;
    private String montoFcatura;

    public String getMontoFcatura() {
        return montoFcatura;
    }

    public void setMontoFcatura(String montoFcatura) {
        this.montoFcatura = montoFcatura;
    }
    

    public Integer getNumeroFc() {
        return numeroFc;
    }

    public void setNumeroFc(Integer numeroFc) {
        this.numeroFc = numeroFc;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(Integer idRecibo) {
        this.idRecibo = idRecibo;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdFormaDePago() {
        return idFormaDePago;
    }

    public void setIdFormaDePago(Integer idFormaDePago) {
        this.idFormaDePago = idFormaDePago;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public Integer nuevo(Object rec) {
        DetalleRecibo detalle=new DetalleRecibo();
        detalle=(DetalleRecibo)rec;
        sql="insert into detallerecibos (idrecibo,idcliente,monto,idfactura) values ("+detalle.getIdRecibo()+","+detalle.getIdCliente()+","+detalle.getMonto()+","+detalle.getIdFactura()+")";
        tra.guardarRegistro(sql);
        return 0;
    }

    @Override
    public ArrayList listar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double imputarAFactura(Object rec) {
        Facturas factura=new Facturas();
        factura=(Facturas)rec;
        sql="update facturas set saldo=(total - "+factura.getTotal()+") where id="+factura.getId();
        System.out.println(sql);
        tra.guardarRegistro(sql);
        return 0.00;
    }

    @Override
    public DefaultTableModel mostrarARecibir(ArrayList listado) {
        MiModeloTablaContacto listado1=new MiModeloTablaContacto();
        Facturas cotizacion;
        Iterator iL=listado.listIterator();
        listado1.addColumn("Recibo");
        listado1.addColumn("Fecha");
        listado1.addColumn("Numero");
        listado1.addColumn("Monto");
        listado1.addColumn("Saldo");
        
        Object[] fila=new Object[5];
        while(iL.hasNext()){
            
            cotizacion=(Facturas)iL.next();
            fila[0]=false;
            
            fila[1]=String.valueOf(cotizacion.getFecha());
            fila[2]=String.valueOf(cotizacion.getNumeroFactura());
            fila[3]=String.valueOf(cotizacion.getMontoOriginal());
            fila[4]=String.valueOf(cotizacion.getTotal());
            listado1.addRow(fila);
        }
        return listado1;
    }

    @Override
    public DefaultTableModel mostrarARecibirSuma(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
