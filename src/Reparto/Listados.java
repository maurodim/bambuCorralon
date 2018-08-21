/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reparto;

import Reparto.interfaces.Revisionar;
import interfaces.Transaccionable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Conecciones;


/**
 *
 * @author Administrador
 */
public class Listados implements Revisionar{
        //private String titulo;
        private Date fechaDeImpresion;
        private Integer numeroPedidoParaReparto;
        private Integer numeroDeVehiculo;
        //private String codigoArticulo;
        //private String descripcionArticulo;
        //private Double cantidadArticulo;
        //private String detalleFraccion;
        //private Double pesoArticulo;
        private String observacionesPedido;
        private static Integer numeroListado;
        private static Integer numeroRevision;
        private Date fechaDeEntrega;
        private Integer numListado;
       static Transaccionable cc=null;
       // static Connection con=null;

    public Integer getNumListado() {
        return numListado;
    }

    public void setNumListado(Integer numListado) {
        this.numListado = numListado;
    }

    public Integer getNumeroDeVehiculo() {
        return numeroDeVehiculo;
    }

    public void setNumeroDeVehiculo(Integer numeroDeVehiculo) {
        this.numeroDeVehiculo = numeroDeVehiculo;
    }

    public Date getFechaDeEntrega() {
        return fechaDeEntrega;
    }

    public void setFechaDeEntrega(Date fechaDeEntrega) {
        this.fechaDeEntrega = fechaDeEntrega;
    }

    public Date getFechaDeImpresion() {
        return fechaDeImpresion;
    }

    public void setFechaDeImpresion(Date fechaDeImpresion) {
        this.fechaDeImpresion = fechaDeImpresion;
    }

    public Integer getNumeroListado() {
        return numeroListado;
    }

    public void setNumeroListado(Integer numeroListado) {
        Listados.numeroListado = numeroListado;
    }

    public Integer getNumeroPedidoParaReparto() {
        return numeroPedidoParaReparto;
    }

    public void setNumeroPedidoParaReparto(Integer numeroPedidoParaReparto) {
        this.numeroPedidoParaReparto = numeroPedidoParaReparto;
    }

    public Integer getNumeroRevision() {
        return numeroRevision;
    }

    public void setNumeroRevision(Integer numeroRevision) {
        Listados.numeroRevision = numeroRevision;
    }

    public String getObservacionesPedido() {
        return observacionesPedido;
    }

    public void setObservacionesPedido(String observacionesPedido) {
        this.observacionesPedido = observacionesPedido;
    }

    public Listados() {
        cc=new Conecciones();
        //con=cc.getCn();
        Listados.numeroRevision=0;
        Listados.numeroListado=0;
    }
    private Listados GeneracionDeNuevoListado(int vehiculo,String fecha,Boolean listadoCh,Boolean listadoCH1){
            System.out.println(vehiculo+" "+fecha);
            Listados list=new Listados();
            Boolean chq = false;
            Integer ultimoNumeroDeListado=0;
            try {
                ultimoNumeroDeListado = ChequearListado(vehiculo,fecha);
                if(ultimoNumeroDeListado > 0)chq=true;
            } catch (SQLException ex) {
                Logger.getLogger(Listados.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(vehiculo+" "+fecha+" "+chq);
            Transaccionable tra=new Conecciones();
            String sql=null;
            ResultSet rs=null;
            
            int ultimaRevisionDeListado=0;
            if(chq){
                //ultimaRevisionDeListado;
                
                list.setNumeroListado(ultimoNumeroDeListado);
               
                // listadoCH1 debe ser true para incrementar numero de revision
                
                if(listadoCH1){
                ultimaRevisionDeListado++;
                }
                list.setNumeroRevision(ultimaRevisionDeListado);
                /*
                list.setNumeroListado(ultimoNumeroDeListado);
                //Listados.nuevaRevision();
                list.setNumeroListado(Listados.nuevaRevision());
                */
                System.out.println("NUMERO DE LISTADO Y REVISION ESTATICO :"+list.getNumeroListado()+" / "+list.getNumeroRevision());
                //return list;
            }else{
            sql="insert into listadosDeMateriales (fechaEntrega,vehiculo) values ('"+fecha+"',"+vehiculo+")";
            //st=cp.createStatement();
            tra.guardarRegistro(sql);
            
            //st.close();
            sql="select LAST_INSERT_ID()";
            //Statement ss=cp.createStatement();
            
            rs=tra.leerConjuntoDeRegistros(sql);
        
                try {
                    while(rs.next()){
                        ultimoNumeroDeListado=rs.getInt(1);
                        System.err.println(" LA FUNCION LAST_INSERT_ID() DA :"+ultimoNumeroDeListado);
                    }
                list.setNumeroListado(ultimoNumeroDeListado);
                rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Listados.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            //list.setNumeroRevision(0);
            }

            // listadoCH al ser true guarda los datos en las bases sobre los distintos listados y revisiones
            
            if(listadoCh){
            
            
            sql="update listadosDeMateriales set revision=revision + 1 where fechaEntrega like '"+fecha+"%' and vehiculo="+vehiculo;
            System.out.println("SQL = "+sql);
            tra.guardarRegistro(sql);
            //Listados.nuevoListado();
            System.err.println(sql+" "+chq);
            }
            return list;
            
        } 
    
    private Integer ChequearListado(int vehiculo,String fecha) throws SQLException{
            String sql="select * from listadosDeMateriales where fechaEntrega like '"+fecha+"%' and vehiculo="+vehiculo;
            Boolean ccch = false;
            try{
            Transaccionable tra=new Conecciones();
            ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            Integer ultimoNumeroDeListado=0;
            int ultimaRevisionDeListado=0;
             //ccch= false;   
            /*
             * ACA LEE EL LA BASE CUAL ES EL ULTIMO NUMERO DE LISTADO Y REVISION EMITIDO
             */
            while(rs.next()){
                ultimoNumeroDeListado=rs.getInt("numero");
                ultimaRevisionDeListado=rs.getInt("revision");
                
                ccch=true;
            
            }
            rs.close();
            
            return ultimoNumeroDeListado;
            }catch(Exception ex){
                
                System.err.println("ERROR DE CONEXION PARA SACAR NUMERO DE LISTADO");
                return 0;
            }
        }
    private void GuardadoNumeroListadoEnPedido(ArrayList pedidos,Integer seleccion,String fecha){
            Iterator iip=pedidos.listIterator();
            String sql=null;
            PedidosParaReparto pe=new PedidosParaReparto();
            PedidosParaReparto pedi=new PedidosParaReparto();
            //ChequearCantidadesPedidos ch=new Checking();
            //FileWriter revisiones=null;
            String fch=fecha.replaceAll("/","_");
            //String ruta="C://bases//STHDR//revHdr//"+fch+"testRevision.txt";
            //revisiones=new FileWriter(ruta);
            String txx="";
             //Runtime r=Runtime.getRuntime();
        //r.gc();
            Transaccionable tra=new Conecciones();
            while(iip.hasNext()){
                pe=(PedidosParaReparto)iip.next();
                int revision=pe.getNumeroDeRevisionDeListado();
                //pe=(PedidosParaReparto)ch.check(pe);
                //revision++;
                //sql="update pedidos_carga1 set revision="+revision+",listado="+pe.getNumeroDeListadoDeMateriales()+" where NRO_PEDIDO ='"+pe.getCodigoTangoDePedido()+"' and vehiculo="+seleccion+" and entrega like '"+fecha+"%'";
                txx+=revision+" - "+pe.getCodigoTangoDePedido()+"\r\n";
                
            
                sql="update pedidos_carga1 set revision="+revision+",revisionado=1,listado="+pe.getNumeroDeListadoDeMateriales()+",vehiculoAnterior="+pe.getVehiculoAsignado()+" where numero ="+pe.getiDPedido(); 
                tra.guardarRegistro(sql);
                
                //sql="insert into historicoListadoRevision (numeroListado,numeroRevision) values ("+pe.getNumeroDeListadoDeMateriales()+","+pe.getNumeroDeRevisionDeListado()+")";
                //st.executeUpdate(sql);
               
               
                
                
                System.err.println(sql+" NUMERO ID "+pe.getiDPedido()+" articulo "+pe.getCodigoArticulo()+"cantidad "+pe.getCantidadArticulo());
            }
            //revisiones.write(txx);
            //revisiones.close();
            
        }
    
    
    
public static void nuevoListado(){
     
    
    String sql="select * from listadosDeMateriales order by numero";
    
            try (ResultSet rs = cc.leerConjuntoDeRegistros(sql)) {
                int numeroL=0;
                try {
                    while(rs.next()){
                        numeroL=rs.getInt("numero");
                    }       } catch (SQLException ex) {
                        Logger.getLogger(Listados.class.getName()).log(Level.SEVERE, null, ex);
                    }
                Listados.numeroListado=numeroL++;
                Listados.numeroRevision=0;
                sql="insert into historicolistadorevision (numeroListado,numeroRevision) values ("+Listados.numeroListado+","+Listados.numeroRevision+")";
                cc.guardarRegistro(sql);
                //st.close();
                //Coneccion.CerrarConneccion(con);
 }          catch (SQLException ex) {
                Logger.getLogger(Listados.class.getName()).log(Level.SEVERE, null, ex);
            }}    
public static Integer nuevaRevision() throws SQLException{
    Integer rev=0;
     
    
    String sql="select * from historicolistadorevision where numeroListado ="+Listados.numeroListado+" order by numeroRevision";
    
    int numeroR=0;
    ResultSet rs=cc.leerConjuntoDeRegistros(sql);
    while(rs.next()){
        numeroR=rs.getInt("numeroRevision");
    }
    Listados.numeroRevision=numeroR++;
    //Listados.numeroRevision=0;
    sql="insert into historicolistadorevision (numeroListado,numeroRevision) values ("+Listados.numeroListado+","+Listados.numeroRevision+")";
    cc.guardarRegistro(sql);
    sql="update listadosDeMateriales set revision="+Listados.numeroRevision+" where numero="+Listados.numeroListado;
    cc.guardarRegistro(sql);
    
    rs.close();
    //Coneccion.CerrarConneccion(con);
    rev=Listados.numeroRevision;
    return rev;
}
public static ArrayList listarLpm(String fecha) throws SQLException{
    ArrayList listado=new ArrayList();
    String sql="select * from listadosdemateriales where fechaEntrega like '"+fecha+"%'";
    System.out.println(sql);
     
    
    
    ResultSet rs=cc.leerConjuntoDeRegistros(sql);
    while(rs.next()){
        Listados lista=new Listados();
        lista.setNumListado(rs.getInt("numero"));
        //lista.setFechaDeEntrega(rs.getDate("fechaEntrega"));
        lista.setNumeroDeVehiculo(rs.getInt("vehiculo"));
        System.out.println(" LISTADOS EMITIDOS "+lista.getNumeroListado()+" vehiculo "+lista.getNumeroDeVehiculo());
        listado.add(lista);
    }
    //Coneccion.CerrarConneccion(cn);
    return listado;
}
public static Boolean eliminarLpm(Object listado){
    Boolean confirmacion=true;    
    Listados lst=(Listados)listado;
    String sql="update listadosdemateriales set vehiculo=0,revision=0 where numero="+lst.getNumListado();
    cc.guardarRegistro(sql);
    sql="update pedidos_carga1 set listado=0,revision=0,vehiculo=0,repetidoEnListado=0,revisionado=0,vehiculoAnterior=0 where listado="+lst.getNumListado();
    cc.guardarRegistro(sql);
        return confirmacion;
}
public static Listados ultimoListado(Integer vehiculo,String fecha){
    Listados ch=new Listados();
    String sql="select * from listadosdemateriales where vehiculo="+vehiculo+" and fechaEntrega like '"+fecha+"'";
    System.err.println(" SENTENCIA "+sql);
        try {
            
            ResultSet rs=cc.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                ch.setNumeroDeVehiculo(vehiculo);
                ch.setNumeroListado(rs.getInt("numero"));
                ch.setNumeroRevision(rs.getInt("revision"));
                System.out.println(" ULTIMO LISTADO :"+ch.getNumeroListado()+" revision "+ch.getNumeroRevision()+" VEHICULO "+vehiculo+" fecha "+fecha);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Listados.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return ch;
}

    @Override
    public ArrayList listadoDeMovimientos(Integer numeroListado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean agregarRevision(ArrayList nuevos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object verificarCantidadAnterior(Object item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int ultimaRevisionDeListado(Integer numeroDeListado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList convertirARevision(ArrayList carga) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean chequearCambioDeListado(ArrayList carga) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean guardarDatosRevision(ArrayList carga) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer leerNumeroDeListadoAnterior() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object GenerarNuevoListado(int vehiculo, String fecha, Boolean listadoCh, Boolean listadoCH1) {
        return this.GeneracionDeNuevoListado(vehiculo, fecha, listadoCh, listadoCH1);
    }

    @Override
    public void GuardarNumeroListadoEnPedido(ArrayList pedidos, Integer seleccion, String fecha) {
        this.GuardadoNumeroListadoEnPedido(pedidos, seleccion, fecha);
    }
}
