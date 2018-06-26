/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vendedores;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author mauro
 */
public interface Vendable {
    public Integer nuevo(Object vend);
    public Object cargar(Integer id);
    public ArrayList listar();
    public DefaultComboBoxModel mostrarEnCombo(ArrayList listado);
    
}
