/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Articulos;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro
 */
public interface Articulable {
    public void NuevoMasivo(ArrayList listado);
    public void ModificadoMasivo(ArrayList listado);
    public DefaultTableModel mostrarListadoParaSeleccionRubro(ArrayList listado,Integer idRubro);
    public void asignarMasivoRubros(ArrayList listado,Integer idRubro);
    public ArrayList listadoPorRubro(Integer idR);
    public void desAsignarMasivoRubros(ArrayList listado);
    public DefaultTableModel mostrarListadoParaSeleccionDeProveedor(ArrayList listado,Integer idRubro);
    public void asignarMasivoMonedas(ArrayList listado,Integer idRubro,Double nueva);
    public ArrayList listadoPorMonedas(Integer idR);
    public void desAsignarMasivoMonedas(ArrayList listado);
    public Integer actualizarPreciosPorMoneda(Integer idMoneda,Double nuevoValor);
}
