/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores.objetos;

import interfaceGraficas.Inicio;
import interfaces.Personalizable;
import interfaces.Transaccionable;
import java.util.ArrayList;
import objetos.Conecciones;

/**
 *
 * @author mauro
 */
public class MovimientoArticulos implements Personalizable{
    private Integer tipoMovimiento;
    private Integer idArticulo;
    private Double cantidad;
    private Integer numeroDeposito;
    private Integer tipoComprobante;
    private Integer numeroComprobante;//EN EL CASO DE PROVEEDORES ID MOVIMIENTO
    private Integer idCliente;//O ID PROVEEDOR
    private String fechaComprobante;
    private Double precioDeCosto;
    private Double precioDeVenta;
    private Double precioDeServicio;
    private Integer estado;
    private Integer id;
    private Integer idCaja;

    public Integer getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(Integer tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getNumeroDeposito() {
        return numeroDeposito;
    }

    public void setNumeroDeposito(Integer numeroDeposito) {
        this.numeroDeposito = numeroDeposito;
    }

    public Integer getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(Integer tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public Integer getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(Integer numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(String fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    public Double getPrecioDeCosto() {
        return precioDeCosto;
    }

    public void setPrecioDeCosto(Double precioDeCosto) {
        this.precioDeCosto = precioDeCosto;
    }

    public Double getPrecioDeVenta() {
        return precioDeVenta;
    }

    public void setPrecioDeVenta(Double precioDeVenta) {
        this.precioDeVenta = precioDeVenta;
    }

    public Double getPrecioDeServicio() {
        return precioDeServicio;
    }

    public void setPrecioDeServicio(Double precioDeServicio) {
        this.precioDeServicio = precioDeServicio;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Integer idCaja) {
        this.idCaja = idCaja;
    }

    @Override
    public Boolean agregar(Object objeto) {
        MovimientoArticulos mov=(MovimientoArticulos) objeto;
        String sql="insert into movimientosarticulos (tipoMovimiento,idArticulo,cantidad,numeroDeposito,tipoComprobante,numeroComprobante,numeroCliente,fechaComprobante,numeroUsuario,precioDeCosto,precioDeVenta,precioServicio,estado,idcaja) values ("+mov.getTipoMovimiento()+","+mov.getIdArticulo()+","+mov.getCantidad()+","+mov.getNumeroDeposito()+","+mov.getTipoComprobante()+",'"+mov.getNumeroComprobante()+"',"+mov.getIdCliente()+",'"+mov.getFechaComprobante()+"',"+Inicio.usuario.getNumeroId()+","+mov.getPrecioDeCosto()+","+mov.getPrecioDeVenta()+","+mov.getPrecioDeServicio()+","+mov.getEstado()+","+mov.getIdCaja()+")";
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        return true;
    }

    @Override
    public Boolean modificar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean eliminar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscarPorNumero(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscarPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscarPorCuit(String cuit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorCuit(String cuit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
