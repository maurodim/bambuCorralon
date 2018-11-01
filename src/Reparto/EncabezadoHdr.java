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
public class EncabezadoHdr implements AbmHdr{
    private Integer numero;
    private String fechaImpresion;
    private String fechaReparto;
    private String descripcionVehiculo;
    private Integer numeroVehiculo;
    private Integer numeroListado;
    private Double pesoListado;
    private Double pesoEntregado;
    private Integer numeroOperador;
    private String nombreOperador;
    private Integer kmIniciales;
    private Integer kmFinales;
    private Integer kmRecorridos;
    private Integer horaSalida;
    private Integer minutosSalida;
    private Integer horaLlegada;
    private Integer minutosLlegada;
    private Double totalCobranza;
    private Double totalVuelto;
    private int estadoHdr;
    private String motivoAnulacion;

    public int getEstadoHdr() {
        return estadoHdr;
    }

    public void setEstadoHdr(int estadoHdr) {
        this.estadoHdr = estadoHdr;
    }

    public String getMotivoAnulacion() {
        return motivoAnulacion;
    }

    public void setMotivoAnulacion(String motivoAnulacion) {
        this.motivoAnulacion = motivoAnulacion;
    }
    

    
    public Double getTotalCobranza() {
        return totalCobranza;
    }

    public void setTotalCobranza(Double totalCobranza) {
        this.totalCobranza = totalCobranza;
    }

    public Double getTotalVuelto() {
        return totalVuelto;
    }

    public void setTotalVuelto(Double totalVuelto) {
        this.totalVuelto = totalVuelto;
    }
    public String getDescripcionVehiculo() {
        return descripcionVehiculo;
    }

    public void setDescripcionVehiculo(String descripcionVehiculo) {
        this.descripcionVehiculo = descripcionVehiculo;
    }

    public String getFechaImpresion() {
        return fechaImpresion;
    }

    public void setFechaImpresion(String fechaImpresion) {
        this.fechaImpresion = fechaImpresion;
    }

    public String getFechaReparto() {
        return fechaReparto;
    }

    public void setFechaReparto(String fechaReparto) {
        this.fechaReparto = fechaReparto;
    }

    public Integer getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Integer horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public Integer getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Integer horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Integer getKmFinales() {
        return kmFinales;
    }

    public void setKmFinales(Integer kmFinales) {
        this.kmFinales = kmFinales;
    }

    public Integer getKmIniciales() {
        return kmIniciales;
    }

    public void setKmIniciales(Integer kmIniciales) {
        this.kmIniciales = kmIniciales;
    }

    public Integer getKmRecorridos() {
        return kmRecorridos;
    }

    public void setKmRecorridos(Integer kmRecorridos) {
        this.kmRecorridos = kmRecorridos;
    }

    public Integer getMinutosLlegada() {
        return minutosLlegada;
    }

    public void setMinutosLlegada(Integer minutosLlegada) {
        this.minutosLlegada = minutosLlegada;
    }

    public Integer getMinutosSalida() {
        return minutosSalida;
    }

    public void setMinutosSalida(Integer minutosSalida) {
        this.minutosSalida = minutosSalida;
    }

    public String getNombreOperador() {
        return nombreOperador;
    }

    public void setNombreOperador(String nombreOperador) {
        this.nombreOperador = nombreOperador;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumeroListado() {
        return numeroListado;
    }

    public void setNumeroListado(Integer numeroListado) {
        this.numeroListado = numeroListado;
    }

    public Integer getNumeroOperador() {
        return numeroOperador;
    }

    public void setNumeroOperador(Integer numeroOperador) {
        this.numeroOperador = numeroOperador;
    }

    public Integer getNumeroVehiculo() {
        return numeroVehiculo;
    }

    public void setNumeroVehiculo(Integer numeroVehiculo) {
        this.numeroVehiculo = numeroVehiculo;
    }

    public Double getPesoEntregado() {
        return pesoEntregado;
    }

    public void setPesoEntregado(Double pesoEntregado) {
        this.pesoEntregado = pesoEntregado;
    }

    public Double getPesoListado() {
        return pesoListado;
    }

    public void setPesoListado(Double pesoListado) {
        this.pesoListado = pesoListado;
    }
    private ArrayList UltimaHdr(){
            ArrayList listadoEnc=new ArrayList();
            EncabezadoHdr enc=new EncabezadoHdr();
            String sql="select hdr.numero,hdr.kmInicio,hdr.kmFinal,hdr.numeroFletero,hdr.numeroVehiculo,hdr.pesoCarga,hdr.listadoNumero,hdr.fechaEntrega,hdr.horaInicio,hdr.horaFinal,(select unidades.descripcion from unidades where unidades.numero=hdr.numeroVehiculo) as unid,(select fleteros.nombre from fleteros where fleteros.numero=hdr.numeroFletero) as flete,hdr.fechaImpresion,hdr.estado,hdr.motivoAnulacion from hdr order by numero";
            Transaccionable tra=new Conecciones();
            ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                enc=new EncabezadoHdr();
                enc.setNumero(rs.getInt("numero"));
                enc.setKmIniciales(rs.getInt("kmInicio"));
                enc.setKmFinales(rs.getInt("kmFinal"));
                enc.setNumeroOperador(rs.getInt("numeroFletero"));
                enc.setNumeroVehiculo(rs.getInt("numeroVehiculo"));
                enc.setPesoEntregado(rs.getDouble("pesoCarga"));
                enc.setNumeroListado(rs.getInt("listadoNumero"));
                enc.setFechaReparto(rs.getString("fechaEntrega"));
                String horaS=rs.getString("horaInicio");
                String horaF=rs.getString("horaFinal");
                enc.setPesoListado(0.00);
                enc.setDescripcionVehiculo(rs.getString("unid"));
                enc.setNombreOperador(rs.getString("flete"));
                enc.setFechaImpresion(rs.getString("fechaImpresion"));
                enc.setEstadoHdr(rs.getInt("estado"));
                enc.setMotivoAnulacion(rs.getString("motivoAnulacion"));
                System.err.println(" horas "+horaS+" "+horaF);
                Integer hrS=Integer.parseInt(horaS.substring(0,2));
                Integer minS=Integer.parseInt(horaS.substring(3,4));
                Integer hrF=Integer.parseInt(horaF.substring(0, 2));
                Integer minF=Integer.parseInt(horaF.substring(3,4));
                enc.setHoraSalida(hrS);
                enc.setMinutosSalida(minS);
                enc.setHoraLlegada(hrF);
                enc.setMinutosLlegada(minF);
                listadoEnc.add(enc);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(EncabezadoHdr.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            return listadoEnc;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList cargarUltimaHdr() {
        return this.UltimaHdr();
    }
    
}
