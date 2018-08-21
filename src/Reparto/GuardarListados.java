/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reparto;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import Reparto.PedidosParaReparto;
import interfaces.Transaccionable;
import objetos.Conecciones;

/**
 *
 * @author Administrador
 */
public class GuardarListados extends Thread{
    static Transaccionable cg=null;
    private ArrayList list=new ArrayList();
    

    public void setList(ArrayList list) {
        this.list = list;
    }
    
    @Override
    public synchronized void run(){
        String sql=null;
        PedidosParaReparto pd=new PedidosParaReparto();
        Iterator ig=this.list.listIterator();
         
        cg=new Conecciones();
        //try {
        
        
        while(ig.hasNext()){
            pd=(PedidosParaReparto)ig.next();
            sql="update pedidos_carga1 set hdr1="+pd.getNumeroDeHojaDeRuta()+", listado="+pd.getNumeroDeListadoDeMateriales()+",revision="+pd.getNumeroDeRevisionDeListado()+" where NRO_PEDIDO='"+pd.getCodigoTangoDePedido()+"' and entrega='"+pd.getFechaEnvio()+"'";
            cg.guardarRegistro(sql);
            System.out.println(sql);
            
            
        }
        
    } 
}
