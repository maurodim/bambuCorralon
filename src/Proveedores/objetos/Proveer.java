/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores.objetos;

import java.util.ArrayList;

/**
 *
 * @author mauro
 */
public interface Proveer {
    public Boolean GuardarImpuestos(ArrayList lista,Integer idFactura);
    public ArrayList LeerImpuestos(Integer idFactura);
    public ArrayList ListarDetalleFactura(Integer idFactura,Integer tipo);
    
}
