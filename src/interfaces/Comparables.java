/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author mauro di
 */
public interface Comparables {
    public Double comparaConCotizaciones(Integer idCliente,Integer idArticulo,Double coeficienteCliente);
    public String comparaConPedidos(Integer idCliente,Integer idArticulo);
}
