/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reparto;

import Reparto.interfaces.AbmHdr;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Conecciones;

/**
 *
 * @author Administrador
 */
public class DetalleHdr implements AbmHdr{
    private Integer numero;
    private Integer numeroHdr;
    private String numeroPedidoTango;
    private String codigoCliente;
    private String razonSocial;
    private String saldo;
    private String vuelto;
    private Boolean contactoTelefonico;
    private String horaContacto;
    private Boolean entregaCompletada;
    private String Observaciones;
    private String empresa;
    private String motivoFaltaDeEntrega;
    private Integer reenviarPedido;
    private String numeroDeComprobante;

    public String getNumeroDeComprobante() {
        return numeroDeComprobante;
    }

    public void setNumeroDeComprobante(String numeroDeComprobante) {
        this.numeroDeComprobante = numeroDeComprobante;
    }

    
    
    public Integer getReenviarPedido() {
        return reenviarPedido;
    }

    public void setReenviarPedido(Integer reenviarPedido) {
        this.reenviarPedido = reenviarPedido;
    }

    public String getMotivoFaltaDeEntrega() {
        return motivoFaltaDeEntrega;
    }

    public void setMotivoFaltaDeEntrega(String motivoFaltaDeEntrega) {
        this.motivoFaltaDeEntrega = motivoFaltaDeEntrega;
    }
    public String getVuelto() {
        return vuelto;
    }

    public void setVuelto(String vuelto) {
        this.vuelto = vuelto;
    }
 
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    
    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Boolean getContactoTelefonico() {
        return contactoTelefonico;
    }

    public void setContactoTelefonico(Boolean contactoTelefonico) {
        this.contactoTelefonico = contactoTelefonico;
    }

    public Boolean getEntregaCompletada() {
        return entregaCompletada;
    }

    public void setEntregaCompletada(Boolean entregaCompletada) {
        this.entregaCompletada = entregaCompletada;
    }

    public String getHoraContacto() {
        return horaContacto;
    }

    public void setHoraContacto(String horaContacto) {
        this.horaContacto = horaContacto;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumeroHdr() {
        return numeroHdr;
    }

    public void setNumeroHdr(Integer numeroHdr) {
        this.numeroHdr = numeroHdr;
    }

    public String getNumeroPedidoTango() {
        return numeroPedidoTango;
    }

    public void setNumeroPedidoTango(String numeroPedidoTango) {
        this.numeroPedidoTango = numeroPedidoTango;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
    private ArrayList cargarDetalleHdr(Integer numeroHdr){
            ArrayList detalle=new ArrayList();
            String sql="select detalle_hdr.cliente,detalle_hdr.numero_cli,detalle_hdr.comprobante,detalle_hdr.importe,detalle_hdr.entregado,detalle_hdr.motivoFallido,detalle_hdr.numero,detalle_hdr.reenviar,(select pedidos_carga1.NRO_PEDIDO from pedidos_carga1 where pedidos_carga1.hdr1=detalle_hdr.hdr and pedidos_carga1.COD_CLIENT=detalle_hdr.numero_cli group by pedidos_carga1.NRO_PEDIDO limit 0,1) from detalle_hdr where hdr="+numeroHdr;
            System.out.println(sql);
            Boolean entreg=true;
            Transaccionable tra=new Conecciones();
            ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                DetalleHdr det=new DetalleHdr();
                det.setRazonSocial(rs.getString(1));
                det.setCodigoCliente(rs.getString(2));
                det.setNumeroDeComprobante(rs.getString(3));
                det.setSaldo(rs.getString(4));
                if(rs.getInt(5)==1){
                    entreg=false;
                }else{
                    entreg=true;
                }
                det.setEntregaCompletada(entreg);
                det.setMotivoFaltaDeEntrega(rs.getString(6));
                det.setNumero(rs.getInt(7));
                det.setReenviarPedido(rs.getInt(8));
                det.setNumeroPedidoTango(rs.getString(9));
                System.out.println("numero de pedido"+det.getNumeroPedidoTango());
                detalle.add(det);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DetalleHdr.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            return detalle;
        }
    private ArrayList leerMotivosFallidos() throws SQLException{
            ArrayList listado=new ArrayList();
            String sql="select * from motivosentregasfallidas order by numero";
            Transaccionable tra=new Conecciones();
            
            ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                listado.add(rs.getString("descripcion"));
            }
            rs.close();
            
            return listado;
        }
     private int leerNumeroMotivoElegido(String seleccion) throws SQLException{
                      int elegido=0;
            String sql="select * from motivosentregasfallidas where descripcion like '"+seleccion+"%'";
            Transaccionable tra=new Conecciones();
            
            ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                elegido=rs.getInt("numero");
            }
            rs.close();
            
            return elegido;
  
    }

    @Override
    public void agregarItem(Object item, Integer numero, String comprobante) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void anularHdr(Integer numeroHdr, String motivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList cargarItemsHdr(Integer numero) {
        return this.cargarDetalleHdr(numero);
    }

    @Override
    public ArrayList cargarUltimaHdr() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
