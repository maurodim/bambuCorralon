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
public interface Tarjetable {
    public ArrayList ListarTodas();
    public DefaultComboBoxModel MostrarArrayEnCombo(ArrayList lst);
    
}
