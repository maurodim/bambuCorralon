/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reparto.interfaces;

import java.util.ArrayList;

/**
 *
 * @author mauro
 */
public interface Procesos {
    public ArrayList detallePedidosParaCorreccion(String numeroPedido,String fecha);
    public ArrayList ListarVehiculos();
    public Integer cargarHdrVehiculo(int lst,String fecha);
    public void guardarAsignacionDeVehiculos(ArrayList lista);
    public ArrayList ListarPedidosPorVehiculo(int idUnidad,ArrayList lstPedidos);
    public ArrayList ListarPedidosPorZona(String fecha,int zonaSeleccionada);
}
