/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reparto;

import Reparto.interfaces.ChequearCantidadesPedidos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfaces.Transaccionable;
import objetos.Conecciones;

/**
 *
 * @author mauro di
 */
public class Checking implements ChequearCantidadesPedidos{
    private Transaccionable con;
    private static Connection cnMy;
    private Integer numeroIdMysql;
    private Double cantidadMysql;
    private String codigoMy;
    private Integer idNumeroTango;
    private Double cantidadTango;
    private String codigoTango;
    private Statement sta;
    

    public Checking() {
        con=new Conecciones();
        
        
        cantidadMysql=0.00;
        numeroIdMysql=0;
    }
    
    @Override
    public Object check(Object pedido) {
        PedidosParaReparto ped=new PedidosParaReparto();
        ped=(PedidosParaReparto)pedido;
        String empresa=ped.getEmpresa();
        String codigoPedido=ped.getCodigoTangoDePedido();
        Double cantidad=(Double)ped.getCantidadArticulo();
        Double cantidadPendiente=(Double)ped.getCantidadArticuloPendiente();
        Double cantidadTotal=(Double)ped.getCantidadArticulosTotales();
        String codigoArticulo=ped.getCodigoArticulo();
        ArrayList idTango=new ArrayList();
        ArrayList cantidadesTango=new ArrayList();
        Integer idTT=0;
        String sql="select * from detallepedidos where ID ="+ped.getIdPedidoEnTango()+" and cantidadpendiente < "+ped.getCantidadArticulo();
        //System.out.println(sql+"  -- sentencia en tango");
        
        
        int numeroConeccion=0;
                        
                        try {
                            //xt=null;
                            
                            ResultSet rs=con.leerConjuntoDeRegistros(sql);
                            Double cantidadT=0.00;
                            Double cantidadTPendiente=0.00;
                            Double cantidadEq=0.00;
                            int cantidadesItemsTango=0;
                            System.out.println("CONEXION "+numeroConeccion+" empresa "+empresa);
                            while(rs.next()){
                                cantidadT=rs.getDouble("cantidadpendiente");
                                sql="update pedidos_carga1 set cant_pedid="+cantidadT+"where ID_GVA03="+ped.getiDPedido();
                                ped.setCantidadArticulo(cantidadT);
                                Transaccionable tra=new Conecciones();
                                tra.guardarRegistro(sql);
                            }
                            
                            rs.close();
                            
                        } catch (SQLException ex) {
                           
                            Logger.getLogger(Checking.class.getName()).log(Level.SEVERE, null, ex);
                            System.err.println(" OJO QUE SE CORTO LA CONEXION A TANGO ");
                            
                        }
        
        return ped;
    }
    public void emitirMensaje(){
        
    }
 
    @Override
    public void verificar() {
       
        
    }

    @Override
    public Object actualizar(Object pedido) {
        PedidosParaReparto ped=new PedidosParaReparto();
        ped=(PedidosParaReparto)pedido;
        
        
        return ped;

    }
    private Boolean verificarMatrices(ArrayList mys,ArrayList tang,PedidosParaReparto pd){
        
            Boolean vMat=false;
            int tamaMy=mys.size();
            int tamaTg=tang.size();
            int fTamano=0;
            int fcantidades=0;
            int fDetalle=0;
            int fTangoMenor=0;
            Checking ccK=null;
            String sql="";
            try {
            if(tamaMy==tamaTg){
                fTamano=1;
            }
            int bucle=0;
            if(fTamano==1){
                bucle=fTamano;
                //comparacionDeMatrices(mys,tang);
            }else{
            if(tamaMy > tamaTg){
                bucle=tamaMy;
                fTangoMenor=1;
                   Statement st=cnMy.createStatement();
                   int tamaTg1=tamaTg -1;
                   for(int i=0;i < bucle;i++){

                          if(i > tamaTg1){ 
                              int aa=i;
                              ccK=(Checking)mys.get(aa);
                              //tamaTg=tamaTg - 1;
                               sql = "delete from pedidos_carga1 where numero="+ccK.numeroIdMysql;
                               System.out.println(sql);
                               //st.executeUpdate(sql);
                              //mys.remove(i);

                          } 

                   }
                   mys=revisarMyPedidos(pd);
                   st.close();

            }else{
                bucle=tamaTg;
                fTangoMenor=0;
                //comparacionDeMatrices(mys,tang);
            }
            
            }
            comparacionDeMatrices(mys,tang);
        } catch (SQLException ex) {
            Logger.getLogger(Checking.class.getName()).log(Level.SEVERE, null, ex);
        }
            
           
            return vMat;
        
    }
    private Boolean verif(Object pedido,Statement conTango,int cantidadItemTango,Double cantidadesTango,ArrayList idT){
        Boolean resultado=false;
        int cantidadItemsMysql=0;
        int h=0;
        int cantidadItemsTango=cantidadItemTango;
        Double cantidadesMy=0.00;
        Double totalMy=0.00;
        Double cantidadTango=cantidadesTango;
        Double cantidadITango=0.00;
        ArrayList cantidad=new ArrayList();
        ArrayList numeroI=new ArrayList();
        ArrayList codigoH=new ArrayList();
        String codigoHd="";
        Integer idPedido=0;
        Integer idPedidoTango=0;
        PedidosParaReparto ped=(PedidosParaReparto)pedido;
        String codigoPedido=ped.getCodigoTangoDePedido().substring(2);
        String codigoArticulo=ped.getCodigoArticulo();
        String sql1="";
        String sql="select pedidos_carga1.CANT_PEDID,pedidos_carga1.numero,pedidos_carga1.COD_ARTIC from pedidos_carga1 where NRO_PEDIDO like '"+ped.getCodigoTangoDePedido()+"' and entrega like '"+ped.getFechaEnvio()+"%'";
        Statement st=null;
        try {
            st = cnMy.createStatement();
        
        
            st.execute(sql);
        
        ResultSet rs=st.getResultSet();
        
        while(rs.next()){
            cantidadesMy=rs.getDouble("CANT_PEDID");
            idPedido=rs.getInt("numero");
            codigoHd=rs.getString("COD_ARTIC");
            totalMy=totalMy + cantidadesMy;
            cantidad.add(cantidadesMy);
            numeroI.add(idPedido);
            cantidadItemsMysql++;
            codigoH.add(codigoHd);
        }
        rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Checking.class.getName()).log(Level.SEVERE, null, ex);
        }
        //st.close();
        int a=0;
        if(totalMy == cantidadesTango){
            resultado=true;
        }else{
           Iterator iCant=cantidad.listIterator();
           Statement stt=conTango;
           Double compTango=0.00;
           while(iCant.hasNext()){
               cantidadesMy=(Double)iCant.next();
               compTango=0.00;
              if(cantidadItemTango > a){
                   idPedidoTango=(Integer)idT.get(a);
                   codigoHd=(String)codigoH.get(a);
               
               sql="select GVA03.CAN_EQUI_V,GVA03.CANT_A_DES from GVA03 where ID_GVA03="+idPedidoTango;
               a++;
               System.err.println(sql);
                try {
                    stt.execute(sql);
               ResultSet rss=stt.getResultSet();
               Double cantEqui=0.00;
               while(rss.next()){
                   cantidadITango=rss.getDouble("CANT_A_DES");
                   cantEqui=rss.getDouble("CAN_EQUI_V");
               }
               rss.close();
               compTango=cantidadITango / cantEqui;
                } catch (SQLException ex) {
                    Logger.getLogger(Checking.class.getName()).log(Level.SEVERE, null, ex);
                    compTango=0.00;
                }

               //stt.close();
               
               h=a-1;
              }else{
                  h=a;
              }
               if(cantidadesMy==compTango){
                   
               }else{
                       int itemsMatrizHdr=numeroI.size();
                   /*
                       if(itemsMatrizHdr > cantidadItemTango){
                       
                   }else{
                       h=itemsMatrizHdr -1;
                   }
                   */ 
                   idPedido=(Integer) numeroI.get(h);
                   sql1="update pedidos_carga1 set CANT_PEDID ="+compTango+" where numero="+idPedido;
                   System.out.println(" SQL1 "+sql1);
                   try {
                       st.executeUpdate(sql1);
                   } catch (SQLException ex) {
                       Logger.getLogger(Checking.class.getName()).log(Level.SEVERE, null, ex);
                   }
                  int id_Pedido = ped.getiDPedido();
                  if(id_Pedido==idPedido){
                      ped.setCantidadArticulo(compTango);
                  }
               }
           
           }
            try {
                stt.close();
            } catch (SQLException ex) {
                Logger.getLogger(Checking.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        try {
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Checking.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return resultado;
    }
    private ArrayList revisarMyPedidos(PedidosParaReparto ped){
        ArrayList cantidadesMy=new ArrayList();
        try {
            Double cantidadM=0.00;
            String numeroPedido=ped.getCodigoTangoDePedido().substring(2);
            String sql="select pedidos_carga1.CANT_PEDID,pedidos_carga1.numero,pedidos_carga1.COD_ARTIC from pedidos_carga1 where NRO_PEDIDO like '%"+numeroPedido+"' and entrega like '"+ped.getFechaEnvio()+"%' order by numero";
            Statement st=cnMy.createStatement();
            st.executeQuery(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                Checking cK=new Checking();
                
                cantidadM=rs.getDouble("CANT_PEDID");
                cK.cantidadMysql=cantidadM;
                cK.numeroIdMysql=rs.getInt("numero");
                cK.codigoMy=rs.getString("COD_ARTIC");
                cantidadesMy.add(cK);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Checking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidadesMy;
    }
    /*
     * FUNCION QUE SE ENCARGA DE VERIFICAR LA RELACION ITEMS A ITEMS ENTRE LOS PEDIDOS MYSQL Y TANGO
     * AMBAS MATRICES YA ESTAN CARGADAS E IGUALADAS EN CANTIDADES O ES MENOR LA MY, AQUI TENGO QUE HACER LA COMPARACION
     */
    private Boolean comparacionDeMatrices(ArrayList my,ArrayList tg){
        Boolean comparacion=true;
        int cantidadItemsAComparar=my.size();
        int cantidadItemsCargadosTg=tg.size();
        String codigoArticuloMy="";
        String codigoArticuloTg="";
        Double cantidadMy=0.00;
        Double cantidadTg=0.00;
        Checking myC=new Checking();
        Checking tgC=new Checking();
        
        
        for(int i=0;i < cantidadItemsCargadosTg;i++){
            tgC=(Checking)tg.get(i);
            codigoArticuloTg=tgC.codigoTango;
            cantidadTg=tgC.cantidadTango;
            for(int aaa=0;aaa <cantidadItemsAComparar;aaa++){
                myC=(Checking)my.get(aaa);
                codigoArticuloMy=myC.codigoMy.trim();
                cantidadMy=myC.cantidadMysql;
                if(codigoArticuloMy.equals(codigoArticuloTg)){
                    Iterator tangI=tg.listIterator();
                    Double mayor=0.00;
                    Double mayor1=0.00;
                    Checking tgC1=new Checking();
                    while(tangI.hasNext()){
                        tgC1=(Checking)tangI.next();
                        
                        mayor1=tgC1.cantidadTango;
                        if(codigoArticuloMy.equals(tgC1.codigoTango)){
                        if(mayor1 >= mayor){
                            mayor=mayor1;
                            mayor1=0.00;
                        }
                    }
                    }
                    if(mayor > 0)mayor1=mayor;
                    if(cantidadMy <= mayor1){
                        
                    }else{
                        cantidadMy=cantidadTg;
                        myC.cantidadMysql=cantidadTg;
                        modificarItems(myC);
                    }
                }
                
            }
            
        }
        
        return comparacion;
    }
    private void modificarItems(Checking cch){
        try {
            Statement st=cnMy.createStatement();
            String sql="update pedidos_carga1 set CANT_PEDID ="+cch.cantidadMysql+" where numero = "+cch.numeroIdMysql;
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Checking.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
private void modificarPedidos(PedidosParaReparto cch){
        try {
            Statement st=cnMy.createStatement();
            String sql="update pedidos_carga1 set CANT_PEDID ="+cch.getCantidadArticulo()+" where ID_GVA03 = "+cch.getIdPedidoEnTango();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Checking.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    public Boolean guardarIdEnMysql(Integer idTango, Integer idMy) {
        Boolean veri=true;
        try {
            String sql="update pedidos_carga1 set ID_GVA03="+idTango+" where numero ="+idMy;
            //Statement sta=cnMy.createStatement();
            sta.executeUpdate(sql);
            //sta.close();
        } catch (SQLException ex) {
            Logger.getLogger(Checking.class.getName()).log(Level.SEVERE, null, ex);
            veri=false;
        }
        return veri;
        
    }

    public Boolean marcarComoLeido(Integer idMy) {
        Boolean veri=true;
        try {
            String sql="update pedidos_carga1 set idcheck=1 where numero ="+idMy;
            //Statement sta=cnMy.createStatement();
            sta.executeUpdate(sql);
            //sta.close();
        } catch (SQLException ex) {
            Logger.getLogger(Checking.class.getName()).log(Level.SEVERE, null, ex);
            veri=false;
        }
        return veri;
    }

    

    public Boolean regenerarCantidadesTango(ArrayList pedidoDetalladoTango) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
