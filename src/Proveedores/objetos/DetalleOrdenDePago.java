/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores.objetos;

import Recibos.Recidable;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import objetos.Conecciones;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import tablas.MiModeloTablaContacto;

/**
 *
 * @author mauro di
 */
public class DetalleOrdenDePago implements Recidable{
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
    private String numeroStringFac;
    private String montoFac;
    private Double saldoItem;
    private Double montoFacturaDouble;
    //private Double 

    public Double getMontoFacturaDouble() {
        return montoFacturaDouble;
    }

    public void setMontoFacturaDouble(Double montoFacturaDouble) {
        this.montoFacturaDouble = montoFacturaDouble;
    }

    public Double getSaldoItem() {
        return saldoItem;
    }

    public void setSaldoItem(Double saldoItem) {
        this.saldoItem = saldoItem;
    }
    
    

    public String getNumeroStringFac() {
        return numeroStringFac;
    }

    public void setNumeroStringFac(String numeroStringFac) {
        this.numeroStringFac = numeroStringFac;
    }

    public String getMontoFac() {
        return montoFac;
    }

    public void setMontoFac(String montoFac) {
        this.montoFac = montoFac;
    }
    

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
        DetalleOrdenDePago detalle=new DetalleOrdenDePago();
        detalle=(DetalleOrdenDePago)rec;
        sql="insert into detalleordendepagos (idorden,idcliente,monto,idfactura,saldo,montofactura) values ("+detalle.getIdRecibo()+","+detalle.getIdCliente()+","+detalle.getMonto()+","+detalle.getIdFactura()+","+detalle.getSaldoItem()+","+detalle.getMontoFacturaDouble()+")";
        tra.guardarRegistro(sql);
        return 0;
    }

    @Override
    public ArrayList listar(Integer id) {
        //DetalleOrdenDePago detalle=new DetalleOrdenDePago();
        ArrayList listado=new ArrayList();
        //detalle=(DetalleOrdenDePago)rec;
        sql="select *,(select movimientosproveedores.numerocomprobante from movimientosproveedores where movimientosproveedores.id=detalleordendepagos.idfactura)as numerocomprobante from detalleordendepagos where idorden="+id;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        DetalleOrdenDePago detalle=null;
        try {
            while(rs.next()){
                detalle=new DetalleOrdenDePago();
                detalle.setNumeroStringFac(rs.getString("numerocomprobante"));
                detalle.setFecha(rs.getDate("fecha"));
                detalle.setMontoFacturaDouble(rs.getDouble("montofactura"));
                detalle.setMonto(rs.getDouble("monto"));
                detalle.setSaldoItem(rs.getDouble("saldo"));
                listado.add(detalle);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetalleOrdenDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        //tra.guardarRegistro(sql);
        return listado;
    }

    @Override
    public Double imputarAFactura(Object rec) {
        MovimientoProveedores factura=new MovimientoProveedores();
        factura=(MovimientoProveedores)rec;
        if(factura.getSaldo() > 0){
            sql="update movimientosproveedores set saldo="+factura.getSaldo()+" where id="+factura.getId();
        }else{
            sql="update movimientosproveedores set saldo="+factura.getSaldo()+",pagado=1 where id="+factura.getId();
        }
        System.out.println(sql);
        tra.guardarRegistro(sql);
        return 0.00;
    }

    @Override
    public DefaultTableModel mostrarARecibir(ArrayList listado) {
        MiModeloTablaContacto listado1=new MiModeloTablaContacto();
        MovimientoProveedores cotizacion;
        Iterator iL=listado.listIterator();
        listado1.addColumn("Orden");
        listado1.addColumn("Fecha");
        listado1.addColumn("Numero");
        listado1.addColumn("Monto");
        listado1.addColumn("Saldo");
        
        Object[] fila=new Object[5];
        while(iL.hasNext()){
            
            cotizacion=(MovimientoProveedores)iL.next();
            fila[0]=false;
            
            fila[1]=String.valueOf(cotizacion.getFecha());
            fila[2]=String.valueOf(cotizacion.getNumeroComprobante());
            fila[3]=String.valueOf(cotizacion.getMonto());
            fila[4]=String.valueOf(cotizacion.getSaldo());
            listado1.addRow(fila);
        }
        return listado1;
    }

    @Override
    public DefaultTableModel mostrarARecibirSuma(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object cargar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
