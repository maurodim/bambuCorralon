/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Remitos;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro di
 */
public interface Remitable {
    public Integer nuevo(Object remi);
    public Boolean modificar(Object remi);
    public Boolean eliminar(Object remi);
    public DefaultTableModel mostrarListado(ArrayList listado);
    public Boolean actualizarRegistros(Object remi);
    public ArrayList listarPendientesPorCliente(Integer idCliente);
    public ArrayList cargarDetalle(Integer remi);
    public Object carga(Integer id);
}
