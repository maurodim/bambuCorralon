/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarjetas;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author mauro
 */
public class Tarjetas implements Tarjetable{
    private Integer id;
    private String descripcion;
    private Double tasaDescuento;
    private Double tasaResumen;
    private Double tasaGanancia;
    private Double montoGanancia;
    private int diasParaLaAcreditacion;
    private Double recargo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getTasaDescuento() {
        return tasaDescuento;
    }

    public void setTasaDescuento(Double tasaDescuento) {
        this.tasaDescuento = tasaDescuento;
    }

    public Double getTasaResumen() {
        return tasaResumen;
    }

    public void setTasaResumen(Double tasaResumen) {
        this.tasaResumen = tasaResumen;
    }

    public Double getTasaGanancia() {
        return tasaGanancia;
    }

    public void setTasaGanancia(Double tasaGanancia) {
        this.tasaGanancia = tasaGanancia;
    }

    public Double getMontoGanancia() {
        return montoGanancia;
    }

    public void setMontoGanancia(Double montoGanancia) {
        this.montoGanancia = montoGanancia;
    }

    public int getDiasParaLaAcreditacion() {
        return diasParaLaAcreditacion;
    }

    public void setDiasParaLaAcreditacion(int diasParaLaAcreditacion) {
        this.diasParaLaAcreditacion = diasParaLaAcreditacion;
    }

    public Double getRecargo() {
        return recargo;
    }

    public void setRecargo(Double recargo) {
        this.recargo = recargo;
    }
    
    private ArrayList listar(){
        ArrayList <Tarjetas> listado=new ArrayList();
        Tarjetas tarjeta;
        for(int a=0;a < 11;a++){
            tarjeta=new Tarjetas();
            tarjeta.id=a;
            tarjeta.descripcion="Tarjeta n° "+a;
            listado.add(tarjeta);
        }
        
        return listado;
    }

    @Override
    public ArrayList ListarTodas() {
        return this.listar();
    }

    @Override
    public DefaultComboBoxModel MostrarArrayEnCombo(ArrayList lst) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
