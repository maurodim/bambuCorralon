/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reparto.interfaces;

import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public interface AbmHdr {
    public void agregarItem(Object item,Integer numero,String comprobante);
    public void anularHdr(Integer numeroHdr,String motivo);
    public ArrayList cargarItemsHdr(Integer numero);
    public ArrayList cargarUltimaHdr();
}
