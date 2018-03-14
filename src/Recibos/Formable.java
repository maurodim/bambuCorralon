/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recibos;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro di
 */
public interface Formable {
    public Boolean guardarCheques(Object listado);
    public Boolean guardarEfectivo(Object listado);
    public ArrayList listarCheques();
    public ArrayList listarChequesPorCliente(Integer idCliente);
    public ArrayList listarChequesPorEstado(Integer idEstado);
    public ArrayList listarChequesPorRecibo(Integer idRecibo);
    public ArrayList listarChequesPorProveedor(Integer idProveedor);
    public ArrayList listarChequesPorReciboDeProveedor(Integer idRecibo);
    public DefaultTableModel mostrarChequesEnTabla(ArrayList listado);
    public DefaultListModel mostrarChequesEnListado(ArrayList listado);
    
}
