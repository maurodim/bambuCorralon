/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recibos;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro di
 */
public interface Recidable {
    public Integer nuevo(Object rec);
    public ArrayList listar(Integer id);
    public Double imputarAFactura(Object rec);//devuelve el saldo no imputado en la factura
    public DefaultTableModel mostrarARecibir(ArrayList listado);
    public DefaultTableModel mostrarARecibirSuma(ArrayList listado);
}
