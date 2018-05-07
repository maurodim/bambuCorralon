/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Articulos;

import Conversores.Numeros;
import Proveedores.objetos.Proveer;
import com.mysql.jdbc.CommunicationsException;
import interfaceGraficas.Inicio;
import interfaces.Comparables;
import interfaces.Editables;
import interfaces.Transaccionable;
import interfacesPrograma.Facturar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import objetos.Conecciones;
import tablas.MiModeloTablaContacto;

/**
 *
 * @author mauro
 */
public class Articulos implements Facturar,Editables,Comparables,Modificable,Proveer{
    private String codigoDeBarra;
    private String codigoAsignado;
    private Integer rubro;
    private String nrubro;
    private Integer numeroId;
    private String descripcionArticulo;
    private Double cantidad;
    private Double precioUnitario;
    private Double precioIva;
    private Double precioUnitarioNeto;
    private Double equivalencia;
    private Double precioDeCosto;
    private Double stockMinimo;
    private Double stockActual;
    private Double precioServicio=0.00;
    private Boolean confirmado;
    private Double recargo;
    private Integer proveedorId;
    private Boolean modificaPrecio;
    private static ConcurrentHashMap listadoBarr=new ConcurrentHashMap();
    private static ConcurrentHashMap listadoNom=new ConcurrentHashMap();
    private static ConcurrentHashMap listadoCodigo=new ConcurrentHashMap();
    private Double diferenciaRemitida;
    private Boolean modificaServicio;
    private Double precioServicio1=0.00;
    private static ConcurrentHashMap listadoBarr1=new ConcurrentHashMap();
    private static ConcurrentHashMap listadoNom1=new ConcurrentHashMap();
    private static ConcurrentHashMap listadoCodigo1=new ConcurrentHashMap();
    private ArrayList combo;
    private Integer idCombo;
    private static ArrayList listCombo=new ArrayList();
    private Integer idDeposito;
    private Integer rubroId;
    private int descuento;
    private int idRenglon;
    private Integer idSubRubro;
    private static Transaccionable tra=new Conecciones();
    private String sql;
    private static ResultSet rs;
    private static Double totalVenta;
    private static Double totalCosto;
    private Double montoDescuento;

    public Double getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(Double montoDescuento) {
        this.montoDescuento = montoDescuento;
    }
    

    public static Double getTotalVenta() {
        return totalVenta;
    }

    public static Double getTotalCosto() {
        return totalCosto;
    }
    

    public Integer getIdSubRubro() {
        return idSubRubro;
    }

    public void setIdSubRubro(Integer idSubRubro) {
        this.idSubRubro = idSubRubro;
    }
    

    public int getIdRenglon() {
        return idRenglon;
    }

    public void setIdRenglon(int idRenglon) {
        this.idRenglon = idRenglon;
    }
    
    

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
    
    

    public Integer getRubroId() {
        return rubroId;
    }

    public void setRubroId(Integer rubroId) {
        this.rubroId = rubroId;
    }
    
    
    public Integer getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    
    public Integer getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(Integer idDeposito) {
        this.idDeposito = idDeposito;
    }
    

    public Integer getIdCombo() {
        return idCombo;
    }

    public void setIdCombo(Integer idCombo) {
        this.idCombo = idCombo;
    }

    
    public ArrayList getCombo() {
        return combo;
    }

    public void setCombo(ArrayList combo) {
        this.combo = combo;
    }
    

    public Double getPrecioServicio1() {
        return precioServicio1;
    }

    public void setPrecioServicio1(Double precioServicio1) {
        this.precioServicio1 = precioServicio1;
    }
    

    public Boolean getModificaServicio() {
        return modificaServicio;
    }

    public void setModificaServicio(Boolean modificaServicio) {
        this.modificaServicio = modificaServicio;
    }

    
    public Double getDiferenciaRemitida() {
        return diferenciaRemitida;
    }

    public void setDiferenciaRemitida(Double diferenciaRemitida) {
        this.diferenciaRemitida = diferenciaRemitida;
    }
    
    

    public Boolean getModificaPrecio() {
        return modificaPrecio;
    }

    public void setModificaPrecio(Boolean modificaPrecio) {
        this.modificaPrecio = modificaPrecio;
    }
    
    public Double getRecargo() {
        return recargo;
    }

    public void setRecargo(Double recargo) {
        this.recargo = recargo;
    }
    
    

    public Boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }
    

    public Double getPrecioServicio() {
        return precioServicio;
    }

    public void setPrecioServicio(Double precioServicio) {
        if(precioServicio==null){
            precioServicio=0.00;
        }
        this.precioServicio = precioServicio;
    }
    

    public Double getStockActual() {
        return stockActual;
    }

    public void setStockActual(Double stockActual) {
        this.stockActual = stockActual;
        /*
         * codigo vista stockart
         * select articulos.id,(select sum(movimientosarticulos.cantidad) from movimientosarticulos where movimientosarticulos.idArticulo=articulos.id)as stock from articulos order by id
         */
    }
    
    public Double getPrecioDeCosto() {
        return precioDeCosto;
    }

    public void setPrecioDeCosto(Double precioDeCosto) {
        this.precioDeCosto = precioDeCosto;
    }

    public Double getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Double stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
    

    public Double getEquivalencia() {
        return equivalencia;
    }

    public void setEquivalencia(Double equivalencia) {
        this.equivalencia = equivalencia;
    }
    

    public String getNrubro() {
        return nrubro;
    }

    public void setNrubro(String nrubro) {
        this.nrubro = nrubro;
    }

    public Articulos() {
    }

    public String getCodigoDeBarra() {
        return codigoDeBarra;
    }

    public void setCodigoDeBarra(String codigoDeBarra) {
        this.codigoDeBarra = codigoDeBarra;
    }

    public String getCodigoAsignado() {
        return codigoAsignado;
    }

    public void setCodigoAsignado(String codigoAsignado) {
        this.codigoAsignado = codigoAsignado;
    }

    public Integer getRubro() {
        return rubro;
    }

    public void setRubro(Integer rubro) {
        this.rubro = rubro;
    }

    public Integer getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(Integer numeroId) {
        this.numeroId = numeroId;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        
        this.precioUnitario = precioUnitario;
    }

    public Double getPrecioIva() {
        return precioIva;
    }

    public void setPrecioIva(Double precioIva) {
        this.precioIva = precioIva;
    }

    public Double getPrecioUnitarioNeto() {
        
        return precioUnitarioNeto;
    }

    public void setPrecioUnitarioNeto(Double precioUnitarioNeto) {
      
        this.precioUnitarioNeto = precioUnitarioNeto;
        //System.err.println("RECIBIDO "+precioUnitarioNeto+" resultado "+this.precioUnitarioNeto+" recargo "+this.recargo+"\n");
    }
    
    public static void CargarMap(){
        // ACA SE CARGA EL MAP PARA TENER ACCESO A LOS ARTICULOS SIN ESTAR CONECTADO , LA CLAVE EL CODIGO DE BARRA
        listadoBarr.clear();
             listadoNom.clear();
             listadoCodigo.clear();
        Transaccionable tra;
        //ArrayList resultado=new ArrayList();
        String sql="";
        Articulos articulo=null;
        /*
        if(listadoBarr.size()==0){
            tra=new Conecciones();
            sql="select *,(select stockart.stock from stockart where stockart.id=articulos.ID)as stock,(select rubros.recargo from rubros where rubros.id=articulos.idRubro)as recargo from articulos where INHABILITADO=0";
            
        }else{
         */
            tra=new Conecciones();
            
            sql="select articulos.ID,articulos.idsubrubro,articulos.idrubro,articulos.NOMBRE,articulos.BARRAS,articulos.recargo,articulos.PRECIO,articulos.equivalencia,articulos.COSTO,articulos.MINIMO,(select articulosMov.cantidad from articulosMov where articulosMov.idArticulo=articulos.ID)as sst,articulos.SERVICIO, articulos.modificaPrecio,articulos.modificaServicio,articulos.SERVICIO1,articulos.idcombo from articulos where INHABILITADO=0";
            
        //}
        
        
        ResultSet rr=tra.leerConjuntoDeRegistros(sql);
        String codA="";
        try {
            while(rr.next()){
                articulo=new Articulos();
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("sst"));
                articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rr.getBoolean("modificaServicio"));
                articulo.setPrecioServicio1(rr.getDouble("servicio1"));
                articulo.setIdCombo(rr.getInt("idcombo"));
                articulo.setRubroId(rr.getInt("idrubro"));
                articulo.setIdSubRubro(rr.getInt("idsubrubro"));
                listadoBarr.putIfAbsent(articulo.getCodigoDeBarra(),articulo);
                codA=articulo.getCodigoAsignado();
                listadoCodigo.putIfAbsent(codA,articulo);
                
                //resultado.add(articulo);
            }
                  } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("ACA DEBE LEER EN LE ARCHIVO");
            
        }
        /*
        if(Inicio.coneccionRemota){
        sql="select *,(select stockart.stock from stockart where stockart.id=articulos.ID)as stock,(select rubros.recargo from rubros where rubros.id=articulos.idRubro)as recargo from articulos where INHABILITADO=0 order by NOMBRE";
        }else{
        */ 
            sql="select articulos.ID,articulos.NOMBRE,articulos.BARRAS,articulos.recargo,articulos.PRECIO,articulos.equivalencia,articulos.COSTO,articulos.MINIMO,(select articulosMov.cantidad from articulosMov where articulosMov.idArticulo=articulos.ID)as sst,articulos.SERVICIO, articulos.modificaPrecio,articulos.modificaServicio,articulos.servicio1 from articulos where INHABILITADO=0 order by NOMBRE";
        //}
        rr=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rr.next()){
                articulo=new Articulos();
                
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("sst"));
                articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rr.getBoolean("modificaServicio"));
                articulo.setPrecioServicio1(rr.getDouble("SERVICIO1"));
                String nom=rr.getString("NOMBRE");
                listadoNom.putIfAbsent(nom,articulo);
                //resultado.add(articulo);
            }
            rr.close();
            //System.out.println(" CANTIDAD MAP inicial "+listadoBarr.size());
           
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        //if(Inicio.coneccionRemota)BackapearMap();
    }
    public static synchronized void RecargarMap(Integer funcion){
        

        //System.out.println(" CANTIDAD MAP 1 - "+listadoBarr1.size());
        // ACA SE CARGA EL MAP PARA TENER ACCESO A LOS ARTICULOS SIN ESTAR CONECTADO , LA CLAVE EL CODIGO DE BARRA
        Transaccionable tra=new Conecciones();
        //ArrayList resultado=new ArrayList();
        Articulos articulo=null;
        
        /*
        String sql="select *,(select stockart.stock from stockart where stockart.id=articulos.ID)as stock,(select rubros.recargo from rubros where rubros.id=articulos.idRubro)as recargo from articulos where INHABILITADO=0 and actualizacion=0";
        ResultSet rr=tra.leerConjuntoDeRegistros(sql);
        try {
            listadoBarr1.clear();
             listadoNom1.clear();
             listadoCodigo1.clear();
            while(rr.next()){
                articulo=new Articulos();
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("stock"));
//                System.out.println(Inicio.sucursal.getDireccion());
                try{
                if(Inicio.sucursal.getDireccion().equals("1")){
                articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                }else{
                    articulo.setPrecioServicio(rr.getDouble("SERVICIO1"));
                }
                }catch(NullPointerException nEx){
                    articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                    articulo.setPrecioServicio1(rr.getDouble("SERVICIO1"));
                }
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rr.getBoolean("modificaServicio"));
                listadoBarr1.putIfAbsent(articulo.getCodigoDeBarra(),articulo);
               listadoCodigo1.putIfAbsent(articulo.getCodigoAsignado(),articulo);
                //resultado.add(articulo);
            }
                  } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        Transaccionable tta=new Conecciones();
        String sql1="";
        Boolean completo=false;
        String sql="";
        listadoNom1.clear();
        int contador=0;
        ArrayList comboD;
        ResultSet rr;
        switch (funcion){
            case 1:
                sql="select *,(select sum(cantidad) from movimientosarticulos where movimientosarticulos.numerodeposito="+Inicio.deposito.getNumero()+" and movimientosarticulos.idArticulo=articulos.ID)as stock,(select articulosMov.cantidad from articulosMov where articulosMov.idArticulo=articulos.ID)as sst from articulos where INHABILITADO=0 order by ID";
                break;
            case 2:
                sql="select *,(select sum(cantidad) from movimientosarticulos where movimientosarticulos.numerodeposito="+Inicio.deposito.getNumero()+" and movimientosarticulos.idArticulo=articulosmodificacion.ID)as stock,(select articulosMov.cantidad from articulosMov where articulosMov.idArticulo=articulos.ID)as sst from articulosmodificacion";
                break;
            case 3:
                sql="select *,(select sum(cantidad) from movimientosarticulos where movimientosarticulos.numerodeposito="+Inicio.deposito.getNumero()+" and movimientosarticulos.idArticulo=articulosnuevos.ID)as stock,(select articulosMov.cantidad from articulosMov where articulosMov.idArticulo=articulos.ID)as sst from articulosnuevos";
                break;
            case 4:
                sql="select * from articuloseliminados";
                break;
            default:
                System.out.println("OJO QUE NO ENTRO EN NINGUNA OPCION");
                break;
        }
        System.out.println(sql);
        rr=tra.leerConjuntoDeRegistros(sql);
        try {
            
            while(rr.next()){
                articulo=new Articulos();
                
                articulo.setCodigoAsignado(rr.getString("ID"));
                
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                if(articulo.getNumeroId()==321){
                  //  JOptionPane.showMessageDialog(null,"coca");
                }
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("sst"));
                try{
                if(Inicio.sucursal.getDireccion().equals("1")){
                articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                }else{
                    articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                    articulo.setPrecioServicio1(rr.getDouble("SERVICIO"));
                }
                }catch(NullPointerException nEx){
                    articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                    articulo.setPrecioServicio1(rr.getDouble("SERVICIO"));
                }
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rr.getBoolean("modificaServicio"));
                articulo.setIdCombo(rr.getInt("idcombo"));
                /*
                if(articulo.getIdCombo() > 0){
                   sql1="select * from combo where articuloPadre ="+articulo.getNumeroId();
                   comboD=new ArrayList();
                   Articulos arti;
                   ResultSet rs=tta.leerConjuntoDeRegistros(sql1);
                   while(rs.next()){
                       arti=new Articulos();
                       arti.setCantidad(rs.getDouble("cantidad"));
                       arti.setNumeroId(rs.getInt("idarticulo"));
                       comboD.add(arti);
                   }
                   rs.close();
                   articulo.setCombo(comboD);
                }
                */
                String nom=rr.getString("NOMBRE");
                listadoNom1.putIfAbsent(nom,articulo);
                //resultado.add(articulo);
            }
            
            contador=contador + 100;
            System.out.println(sql);
            
//            sql="update actualizaciones set estado=1 where iddeposito="+Inicio.deposito.getNumero()+" and idobjeto=1";
//            tra.guardarRegistro(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
            
            completo=true;
        } catch(NullPointerException ey){
            System.err.println(" error de punto nulo en tabla combo :"+ey);
        }
            try {
                String sent="";
            sql="select * from combo";
            rr=tra.leerConjuntoDeRegistros(sql);
            listCombo=new ArrayList();
            while(rr.next()){
                sent="insert into combo (idarticulo,cantidad,articulopadre) values ("+rr.getInt("idarticulo")+","+rr.getDouble("cantidad")+","+rr.getInt("articuloPadre")+")";
                listCombo.add(sent);
            }
                rr.close();
            } catch (SQLException ex) {
                Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Comunicacion interrumpida por superar el maximo de tiempo ");
            } catch(NullPointerException ey){
            System.err.println(" error de punto nulo en tabla combo :"+ey);
        }
        

    }
    public static synchronized void BackapearMap(Integer funcion){
        Transaccionable tt=new Conecciones();
        System.out.println(" CANTIDAD MAP back "+listadoBarr1.size());
        String sql="";
    //String sql="delete from articulos";
      //  tt.guardarRegistro(sql);
        String criterio="";
        Integer canttt=listadoNom1.size();
        Integer contador=0;
        Articulos articulo=null;
        criterio=criterio.toUpperCase();
        int comboOk=0;
        Enumeration<Articulos> elementos=listadoNom1.elements();
        if(listCombo.size() > 0){
        sql="truncate table combo";
                tt.guardarRegistro(sql);
                comboOk=1;
        }
        if(funcion==1){
            sql="truncate table articulos";
                tt.guardarRegistro(sql);
                
        }
        while(elementos.hasMoreElements()){
            articulo=(Articulos)elementos.nextElement();
            int pos=articulo.getDescripcionArticulo().indexOf(criterio);
            int mod=0;
            int serv=0;
            if(articulo.getModificaPrecio())mod=1;
            if(articulo.getModificaServicio())serv=1;
            if(articulo.getDescripcionArticulo().equals(""))articulo.setDescripcionArticulo("--");
            
            switch (funcion){
            case 1:
                
                sql="insert into articulos (id,nombre,barras,servicio,costo,precio,minimo,stock,equivalencia,modificaprecio,modificaservicio,recargo,inhabilitado,idrubro,servicio1,idcombo) values ("+articulo.getNumeroId()+",'"+articulo.getDescripcionArticulo()+"','"+articulo.getCodigoDeBarra()+"',"+articulo.getPrecioServicio()+","+articulo.getPrecioDeCosto()+","+articulo.getPrecioUnitarioNeto()+","+articulo.getStockMinimo()+","+articulo.getStockActual()+","+articulo.getEquivalencia()+","+mod+","+serv+","+articulo.getRecargo()+",0,0,"+articulo.getPrecioServicio1()+","+articulo.getIdCombo()+")";
                break;
            case 2:
                
                sql="update articulos set nombre='"+articulo.getDescripcionArticulo()+"',barras='"+articulo.getCodigoDeBarra()+"',servicio="+articulo.getPrecioServicio()+",costo="+articulo.getPrecioDeCosto()+",precio="+articulo.getPrecioUnitarioNeto()+",minimo="+articulo.getStockMinimo()+",stock="+articulo.getStockActual()+",equivalencia="+articulo.getEquivalencia()+",modificaprecio="+mod+",modificaservicio="+serv+",recargo="+articulo.getRecargo()+",servicio1="+articulo.getPrecioServicio1()+",idcombo="+articulo.getIdCombo()+" where id="+articulo.getNumeroId();
                break;
            case 3:
               
                sql="insert into articulos (id,nombre,barras,servicio,costo,precio,minimo,stock,equivalencia,modificaprecio,modificaservicio,recargo,inhabilitado,idrubro,servicio1,idcombo) values ("+articulo.getNumeroId()+",'"+articulo.getDescripcionArticulo()+"','"+articulo.getCodigoDeBarra()+"',"+articulo.getPrecioServicio()+","+articulo.getPrecioDeCosto()+","+articulo.getPrecioUnitarioNeto()+","+articulo.getStockMinimo()+","+articulo.getStockActual()+","+articulo.getEquivalencia()+","+mod+","+serv+","+articulo.getRecargo()+",0,0,"+articulo.getPrecioServicio1()+","+articulo.getIdCombo()+")";
                break;
            case 4:
                sql="delete from articulos wher id="+articulo.getNumeroId();
                break;
            default:
                System.out.println("OJO QUE NO ENTRO EN NINGUNA OPCION");
                break;
        }
            //sql="insert into articulos (id,nombre,barras,servicio,costo,precio,minimo,stock,equivalencia,modificaprecio,modificaservicio,recargo,inhabilitado,idrubro,servicio1) values ("+articulo.getNumeroId()+",'"+articulo.getDescripcionArticulo()+"','"+articulo.getCodigoDeBarra()+"',"+articulo.getPrecioServicio()+","+articulo.getPrecioDeCosto()+","+articulo.getPrecioUnitarioNeto()+","+articulo.getStockMinimo()+","+articulo.getStockActual()+","+articulo.getEquivalencia()+","+mod+","+serv+","+articulo.getRecargo()+",0,0,"+articulo.getPrecioServicio1()+")";
            //sql="update articulos set nombre='"+articulo.getDescripcionArticulo()+"',barras='"+articulo.getCodigoDeBarra()+"',servicio="+articulo.getPrecioServicio()+",costo="+articulo.getPrecioDeCosto()+",precio="+articulo.getPrecioUnitarioNeto()+",minimo="+articulo.getStockMinimo()+",stock="+articulo.getStockActual()+",equivalencia="+articulo.getEquivalencia()+",modificaprecio="+mod+",modificaservicio="+serv+",recargo="+articulo.getRecargo()+",inhabilitado=0,idrubro=0,servicio1="+articulo.getPrecioServicio1()+" where id="+articulo.getNumeroId();
            contador++;
            System.out.println("van "+contador+" de "+canttt);
            System.out.println("hash "+sql);
            tt.guardarRegistro(sql);
           
            
        }
         if(listCombo.size() > 0){
                String art="";
                Iterator ic=listCombo.listIterator();
                while(ic.hasNext()){
                    art=(String)ic.next();
                    sql=art;
                    tt.guardarRegistro(sql);
                }
            }
        //CargarMap();
    }
    public static synchronized void BackapearMap1(){
        Transaccionable tt=new Conecciones();
        System.out.println(" CANTIDAD MAP back "+listadoNom1.size());
        //String sql="";
    String sql="truncate table articulos";
        tt.guardarRegistro(sql);
        sql="truncate table combo";
        tt.guardarRegistro(sql);
        String criterio="";
        Articulos articulo=null;
        criterio=criterio.toUpperCase();
        Enumeration<Articulos> elementos=listadoNom1.elements();
        while(elementos.hasMoreElements()){
            articulo=(Articulos)elementos.nextElement();
            int pos=articulo.getDescripcionArticulo().indexOf(criterio);
            int mod=0;
            int serv=0;
            if(articulo.getModificaPrecio())mod=1;
            if(articulo.getModificaServicio())serv=1;
            if(articulo.getDescripcionArticulo().equals(""))articulo.setDescripcionArticulo("--");
            
            sql="insert into articulos (id,nombre,barras,servicio,costo,precio,minimo,stock,equivalencia,modificaprecio,modificaservicio,recargo,inhabilitado,idrubro,servicio1,idcombo) values ("+articulo.getNumeroId()+",'"+articulo.getDescripcionArticulo()+"','"+articulo.getCodigoDeBarra()+"',"+articulo.getPrecioServicio()+","+articulo.getPrecioDeCosto()+","+articulo.getPrecioUnitarioNeto()+","+articulo.getStockMinimo()+","+articulo.getStockActual()+","+articulo.getEquivalencia()+","+mod+","+serv+","+articulo.getRecargo()+",0,0,"+articulo.getPrecioServicio1()+","+articulo.getIdCombo()+")";
            //sql="update articulos set nombre='"+articulo.getDescripcionArticulo()+"',barras='"+articulo.getCodigoDeBarra()+"',servicio="+articulo.getPrecioServicio()+",costo="+articulo.getPrecioDeCosto()+",precio="+articulo.getPrecioUnitarioNeto()+",minimo="+articulo.getStockMinimo()+",stock="+articulo.getStockActual()+",equivalencia="+articulo.getEquivalencia()+",modificaprecio="+mod+",modificaservicio="+serv+",recargo="+articulo.getRecargo()+",inhabilitado=0,idrubro=0,servicio1="+articulo.getPrecioServicio1()+" where id="+articulo.getNumeroId();
            System.out.println("hash "+sql);
            tt.guardarRegistro(sql);
            
            
        }
        if(listCombo.size() > 0){
                String art="";
                Iterator ic=listCombo.listIterator();
                while(ic.hasNext()){
                    art=(String)ic.next();
                    sql=art;
                    tt.guardarRegistro(sql);
                }
            }
        //CargarMap();
    }
    private ArrayList CargarCombo(Integer id){
        Transaccionable tt=new Conecciones();
        String sql="select * from combo where articuloPadre="+id;
        ArrayList listadoA=new ArrayList();
        ResultSet rr=tt.leerConjuntoDeRegistros(sql);
        Articulos artic;
        try {
            while(rr.next()){
                artic=new Articulos();
                artic.setNumeroId(rr.getInt("idarticulo"));
                artic.setCantidad(rr.getDouble("cantidad"));
                listadoA.add(artic);
            }
            rr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoA;
    }
    @Override
    public Boolean guardar(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificar(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean nuevo(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object leer(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void imprimirComprobante(int tipoComprobante, Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listadoBusqueda(String criterio) {
        ArrayList resultado=new ArrayList();
        Articulos articulo=null;
        criterio=criterio.toUpperCase();
        String sql="select *,(select articulosMov.cantidad from articulosMov where articulosMov.idArticulo=articulos.ID)as sst from articulos where nombre like '%"+criterio+"%'";
        Transaccionable tra=new Conecciones();
        ResultSet rr=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rr.next()){
                articulo=new Articulos();
                
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("sst"));
                articulo.setRubroId(rr.getInt("idrubro"));
                articulo.setIdSubRubro(rr.getInt("idsubrubro"));
                try{
                    if(Inicio.sucursal.getDireccion().equals("1")){
                        articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                    }else{
                        articulo.setPrecioServicio(rr.getDouble("SERVICIO1"));
                    }
                }catch(NullPointerException nEx){
                    articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                    articulo.setPrecioServicio1(rr.getDouble("SERVICIO1"));
                }
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rr.getBoolean("modificaServicio"));
                String nom=rr.getString("NOMBRE");
                articulo.setIdCombo(rr.getInt("idcombo"));
                if(articulo.getIdCombo() > 0)articulo.setCombo(CargarCombo(articulo.getNumeroId()));
                resultado.add(articulo);
            }
            rr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        Transaccionable tra=new Conecciones();
        ArrayList resultado=new ArrayList();
        Articulos articulo=null;
        String sql="select *,(select stockart.stock from stockart where stockart.id=articulos.ID)as stock,(select rubros.recargo from rubros where rubros.id=articulos.idRubro)as recargo from articulos where NOMBRE like '"+criterio+"%' and INHABILITADO=0";
        ResultSet rr=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rr.next()){
                articulo=new Articulos();
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("stock"));
                articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                resultado.add(articulo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        return resultado;
    }

    @Override
    public Boolean guardarNuevoCliente(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificarDatosDelCliente(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listarClientes(String nombre) {
         ArrayList listado=new ArrayList();
        String sql="select id,nombre,barras,idsubrubro,idrubro,precio,equivalencia,costo,minimo,stock,servicio,servicio1,modificaprecio,modificaservicio,stock,recargo,idcombo,(select articulosMov.cantidad from articulosMov where articulosMov.idArticulo=articulos.ID)as sst from articulos where BARRAS like '"+nombre+"' and INHABILITADO=0";
        Transaccionable tra=new Conecciones();
        ResultSet rr=tra.leerConjuntoDeRegistros(sql);
        Articulos articulo=new Articulos();
        
        try {
            while(rr.next()){
                
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("sst"));
                articulo.setRubroId(rr.getInt("idrubro"));
                articulo.setIdSubRubro(rr.getInt("idsubrubro"));
                        articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rr.getBoolean("modificaServicio"));
                String nom=rr.getString("NOMBRE");
                articulo.setIdCombo(rr.getInt("idcombo"));
                if(articulo.getIdCombo() > 0)articulo.setCombo(CargarCombo(articulo.getNumeroId()));
                listado.add(articulo);
            }
            rr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listado;
    }

    @Override
    public Object cargarPorCodigoDeBarra(String codigoDeBarra) {
        //Articulos articulo;
        //articulo=(Articulos)listadoBarr.get(codigoDeBarra);
        
        String sql="select id,nombre,idrubro,idsubrubro,barras,precio,equivalencia,costo,minimo,stock,servicio,servicio1,modificaprecio,modificaservicio,stock,recargo,idcombo,(select articulosMov.cantidad from articulosMov where articulosMov.idArticulo=articulos.ID)as sst from articulos where BARRAS like '"+codigoDeBarra+"' and INHABILITADO=0";
        System.out.println(sql);
        Transaccionable tra=new Conecciones();
        ResultSet rr=tra.leerConjuntoDeRegistros(sql);
        Articulos articulo=new Articulos();
        
        try {
            while(rr.next()){
                
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("sst"));
                articulo.setRubroId(rr.getInt("idrubro"));
                articulo.setIdSubRubro(rr.getInt("idsubrubro"));
                        articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rr.getBoolean("modificaServicio"));
                String nom=rr.getString("NOMBRE");
                articulo.setIdCombo(rr.getInt("idcombo"));
                if(articulo.getIdCombo() > 0)articulo.setCombo(CargarCombo(articulo.getNumeroId()));
            }
            rr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return articulo;
    }

    @Override
    public Boolean AltaObjeto(Object objeto) {
        Articulos articulo=(Articulos)objeto;
        Boolean ch=false;
        //String sql="insert into articulos (NOMBRE='"+articulo.getDescripcionArticulo()+"',SERVICIO="+articulo.getPrecioServicio()+",COSTO="+articulo.getPrecioDeCosto()+",PRECIO="+articulo.getPrecioUnitarioNeto()+",MINIMO="+articulo.getStockMinimo()+",BARRAS ='"+articulo.getCodigoDeBarra()+"',modificaPrecio="+articulo.getModificaPrecio()+" where ID="+articulo.getNumeroId();
        String sql="insert into articulos (NOMBRE,SERVICIO,COSTO,PRECIO,MINIMO,BARRAS,SERVICIO1,idcombo,actualizacion,idrubro,idsubrubro) values ('"+articulo.getDescripcionArticulo()+"',"+articulo.getPrecioServicio()+","+articulo.getPrecioDeCosto()+","+articulo.getPrecioUnitarioNeto()+","+articulo.getStockMinimo()+",'"+articulo.getCodigoDeBarra()+"',"+articulo.getPrecioServicio1()+","+articulo.getIdCombo()+",3,"+articulo.getRubroId()+","+articulo.getIdSubRubro()+")";
        System.out.println(sql);
        Transaccionable tra=new Conecciones();
        ch=tra.guardarRegistro(sql);
        sql="select last_insert_id()";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        Integer ultimoArt=0;
        try {
            while(rs.next()){
                ultimoArt=rs.getInt(1);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql="update articulos set BARRAS="+ultimoArt+" where id="+ultimoArt;
        tra.guardarRegistro(sql);
         //sql="insert into actualizaciones (iddeposito,idobjeto,estado) values (1,1,3),(2,1,3),(3,1,3),(4,1,3),(5,1,3),(6,1,3),(7,1,3)";
        //tra.guardarRegistro(sql);
        if(articulo.getIdCombo() > 0){
            Articulos art=new Articulos();
            Iterator ic=articulo.getCombo().listIterator();
            while(ic.hasNext()){
                art=(Articulos)ic.next();
                sql="insert into combo (idarticulo,cantidad,articuloPadre) values ("+art.getNumeroId()+","+art.getCantidad()+","+ultimoArt+")";
                tra.guardarRegistro(sql);
            }
        }
        
        return ch;
    }

    @Override
    public Boolean ModificaionObjeto(Object objeto) {
        Articulos articulo=(Articulos)objeto;
        Boolean ch=false;
        sql="update articulos set NOMBRE='"+articulo.getDescripcionArticulo()+"',COSTO="+articulo.getPrecioDeCosto()+",PRECIO="+articulo.getPrecioUnitarioNeto()+",MINIMO="+articulo.getStockMinimo()+",BARRAS ='"+articulo.getCodigoDeBarra()+"',modificaPrecio="+articulo.getModificaPrecio()+",modificaServicio="+articulo.getModificaServicio()+",actualizacion=2,idcombo="+articulo.getIdCombo()+",idrubro="+articulo.getRubroId()+",idsubrubro="+articulo.getIdSubRubro()+" where ID="+articulo.getNumeroId();
        System.out.println(sql);
        tra=new Conecciones();
        ch=tra.guardarRegistro(sql);
        //sql="insert into actualizaciones (iddeposito,idobjeto,estado) values (1,1,2),(2,1,2),(3,1,2),(4,1,2),(5,1,2),(6,1,2),(7,1,2)";
        //tra.guardarRegistro(sql);
         if(articulo.getIdCombo() > 0){
            Articulos art=new Articulos();
            Iterator ic=articulo.getCombo().listIterator();
            while(ic.hasNext()){
                art=(Articulos)ic.next();
                sql="insert into combo (idarticulo,cantidad,articuloPadre) values ("+art.getNumeroId()+","+art.getCantidad()+","+articulo.getNumeroId()+")";
                tra.guardarRegistro(sql);
            }
        }
        return ch;
    }

    @Override
    public Boolean EliminacionDeObjeto(Object objeto) {
        Articulos articulo=(Articulos)objeto;
        Boolean verif=false;
        //String sql="update articulos set INHABILITADO=1, actualizacion=4 where ID="+articulo.getNumeroId();
        String sql="delete from articulos where ID="+articulo.getNumeroId();
        Transaccionable tra=new Conecciones();
        verif=tra.guardarRegistro(sql);
        sql="insert into actualizaciones (iddeposito,idobjeto,estado) values (1,1,4),(2,1,4),(3,1,4),(4,1,4),(5,1,4),(6,1,4),(7,1,4)";
        tra.guardarRegistro(sql);
        
        return verif;
    }

    @Override
    public Integer leerNumeroDeComprobanteSiguiente(Integer numeroComprobante) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object cargarPorCodigoAsignado(Integer id) {
        //Articulos articulo;
        //String idd=String.valueOf(id);
        //articulo=(Articulos)listadoCodigo.get(idd);
        
        
        //ArrayList resultado=new ArrayList();
        sql="";
        Articulos articulo=null;
        /*
        if(listadoBarr.size()==0){
            tra=new Conecciones();
            sql="select *,(select stockart.stock from stockart where stockart.id=articulos.ID)as stock,(select rubros.recargo from rubros where rubros.id=articulos.idRubro)as recargo from articulos where INHABILITADO=0";
            
        }else{
         */
            
            
            sql="select articulos.ID,articulos.idrubro,articulos.idsubrubro,articulos.NOMBRE,articulos.BARRAS,articulos.recargo,articulos.PRECIO,articulos.equivalencia,articulos.COSTO,articulos.MINIMO,(select articulosMov.cantidad from articulosMov where articulosMov.idArticulo=articulos.ID)as sst,articulos.SERVICIO, articulos.modificaPrecio,articulos.modificaServicio,articulos.SERVICIO1,articulos.idcombo from articulos where INHABILITADO=0 and id="+id;
            
        //}
        
        tra=new Conecciones();
        ResultSet rr=tra.leerConjuntoDeRegistros(sql);
        String codA="";
        try {
            while(rr.next()){
                articulo=new Articulos();
                
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("sst"));
                articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rr.getBoolean("modificaServicio"));
                articulo.setPrecioServicio1(rr.getDouble("servicio1"));
                articulo.setIdCombo(rr.getInt("idcombo"));
                articulo.setRubroId(rr.getInt("idrubro"));
                articulo.setIdSubRubro(rr.getInt("idsubrubro"));
                
                //resultado.add(articulo);
            }
                  } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("ACA DEBE LEER EN LE ARCHIVO");
            
        }
        
        return articulo;
    }

    @Override
    public Boolean MovimientoDeAjusteDeCantidades(Object objeto, Double cantidadMovimiento, String observaciones) {
        Articulos articulo=(Articulos)objeto;
        Boolean verif=false;
        
        sql="insert into movimientosarticulos (tipoMovimiento,idArticulo,cantidad,numeroDeposito,tipoComprobante,numeroComprobante,numeroCliente,fechaComprobante,numerousuario,precioDeCosto,precioDeVenta,precioServicio,observaciones,idcaja) values (14,"+articulo.getNumeroId()+","+cantidadMovimiento+","+articulo.getIdDeposito()+",18,(select tipocomprobantes.numeroActivo + 1 from tipocomprobantes where tipocomprobantes.numero=18),1,'"+Inicio.fechaDia+"',"+Inicio.usuario.getNumeroId()+","+articulo.getPrecioDeCosto()+","+articulo.getPrecioUnitarioNeto()+","+articulo.getPrecioServicio()+",'"+observaciones+"',"+Inicio.caja.getNumero()+")";
        
        verif=tra.guardarRegistro(sql);
        sql="update tipocomprobantes set numeroActivo=numeroActivo + 1 where numero=18";
        tra.guardarRegistro(sql);
        return verif;
    }

    @Override
    public ArrayList ListarPorSucursal(Object objeto) {
        Articulos articulo=(Articulos)objeto;
        Articulos articuloI;
        ArrayList listado=new ArrayList();
        String sql="select sum(cantidad),idArticulo,numeroDeposito FROM movimientosarticulos where idArticulo="+String.valueOf(articulo.getCodigoAsignado())+" group by idArticulo,numeroDeposito";
        
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                articuloI=new Articulos();
                articuloI.setCantidad(rs.getDouble("sum(cantidad)"));
                articuloI.setNumeroId(rs.getInt("idArticulo"));
                articuloI.setIdDeposito(rs.getInt("numeroDeposito"));
                System.out.println(sql);
                listado.add(articuloI);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listado;
    }

    

    @Override
    public Double comparaConCotizaciones(Integer idCliente, Integer idArticulo,Double coeficienteCliente) {
        Double precio=0.00;
        String sql="select aplicacion.coeficiente from aplicacion where idcliente="+idCliente+" and idarticulo="+idArticulo+" limit 0,1";
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                precio=rs.getDouble("coeficiente");
            }
            if(precio==0.00)precio=coeficienteCliente;
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return precio;
    }

    @Override
    public String comparaConPedidos(Integer idCliente, Integer idArticulo) {
        String precio="";
        String sql="select aplicacion.observaciones,aplicacion.idlista,(select coeficienteslistas.descripcion from coeficienteslistas where coeficienteslistas.id=aplicacion.idlista)as descLista from aplicacion where idcliente="+idCliente+" and idarticulo="+idArticulo+" limit 0,1";
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                precio=" lista asignada: "+rs.getString("descLista")+" Motivo: "+rs.getString("observaciones");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return precio;
    }

    @Override
    public ArrayList buscar(Integer rubro,Integer subRubro, String criterio) {
        ArrayList resultado=new ArrayList();
        Articulos articulo=null;
        criterio=criterio.toUpperCase();
        
        
        sql="select *,(select articulosMov.cantidad from articulosMov where articulosMov.idArticulo=articulos.ID)as sst from articulos where nombre like '%"+criterio+"%' or idrubro="+rubro+" or idsubrubro="+subRubro+" order by nombre";
        
        rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                articulo=new Articulos();
                
                articulo.setCodigoAsignado(rs.getString("ID"));
                articulo.setDescripcionArticulo(rs.getString("NOMBRE"));
                articulo.setNumeroId(rs.getInt("ID"));
                articulo.setCodigoDeBarra(rs.getString("BARRAS"));
                articulo.setRecargo(rs.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rs.getDouble("PRECIO"));
                articulo.setEquivalencia(rs.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rs.getDouble("COSTO"));
                articulo.setStockMinimo(rs.getDouble("MINIMO"));
                articulo.setStockActual(rs.getDouble("sst"));
                articulo.setRubroId(rs.getInt("idrubro"));
                articulo.setIdSubRubro(rs.getInt("idsubrubro"));
                
                articulo.setModificaPrecio(rs.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rs.getBoolean("modificaServicio"));
                String nom=rs.getString("NOMBRE");
                articulo.setIdCombo(rs.getInt("idcombo"));
                if(articulo.getIdCombo() > 0)articulo.setCombo(CargarCombo(articulo.getNumeroId()));
                resultado.add(articulo);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        Transaccionable tra=new Conecciones();
        ArrayList resultado=new ArrayList();
        Articulos articulo=null;
        String sql="select *,(select stockart.stock from stockart where stockart.id=articulos.ID)as stock,(select rubros.recargo from rubros where rubros.id=articulos.idRubro)as recargo from articulos where NOMBRE like '"+criterio+"%' and INHABILITADO=0";
        ResultSet rr=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rr.next()){
                articulo=new Articulos();
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("stock"));
                articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                resultado.add(articulo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        return resultado;
    }

    @Override
    public Integer nuevoArticulo(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean modificarArticulo(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList modificarPrecios(ArrayList listado, Double porcPrecio, Double porcCosto) {
        Double porc1=porcPrecio;
        porc1= porc1/100;
        porc1=porc1 + 1;
        Double porc2=porcCosto;
        porc2=porc2/100;
        porc2=porc2 + 1;
        Iterator itL=listado.listIterator();
        Articulos articulo=new Articulos();
        while(itL.hasNext()){
            articulo=(Articulos)itL.next();
            articulo.setPrecioUnitarioNeto(articulo.getPrecioUnitarioNeto() * porc1);
            articulo.setPrecioDeCosto(articulo.getPrecioDeCosto() * porc2);
            sql="update articulos set precio=round(precio * "+porc1+",4),costo=round(costo * "+porc2+",4) where id="+articulo.getNumeroId();
            tra.guardarRegistro(sql);
        }
        return listado;
        
    }

    @Override
    public DefaultTableModel mostrarListado(ArrayList listado) {
        MiModeloTablaContacto modelo=new MiModeloTablaContacto();
        Iterator it=listado.listIterator();
        Articulos articulos=new Articulos();
        Double porcentajeGanancia=0.00;
        modelo.addColumn("Listar");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Precio");
        modelo.addColumn("Costo");
        modelo.addColumn("% de Ganancia");
        
        Object [] fila=new Object[5];
        while(it.hasNext()){
            articulos=(Articulos)it.next();
            articulos.setConfirmado(false);
            fila[0]=true;
            fila[1]=articulos.getDescripcionArticulo();
            fila[2]=articulos.getPrecioUnitarioNeto();
            fila[3]=articulos.getPrecioDeCosto();
            
        porcentajeGanancia=articulos.getPrecioUnitarioNeto() / articulos.getPrecioDeCosto();
        //System.err.println("vta "+Articulos.getTotalVenta()+" costo "+Articulos.getTotalCosto()+" total "+porcentajeGanancia);
        porcentajeGanancia=(porcentajeGanancia -1) * 100;
            fila[4]=String.valueOf(Math.round(porcentajeGanancia));
            
            modelo.addRow(fila);
        }
        return modelo;
    }

    @Override
    public DefaultTableModel mostrarListadoBusqueda(ArrayList listado) {
        DefaultTableModel modelo=new DefaultTableModel();
        Iterator it=listado.listIterator();
        Articulos articulo=new Articulos();
        modelo.addColumn("Descripcion");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        Object [] fila=new Object[3];
        while(it.hasNext()){
            articulo=(Articulos)it.next();
            fila[0]=articulo.getDescripcionArticulo();
            fila[1]=" $"+Numeros.ConvertirNumero(articulo.getPrecioUnitarioNeto());
            fila[2]=String.valueOf(articulo.getStockActual());
            //modelo.addElement(articulo.getDescripcionArticulo()+" $"+Numeros.ConvertirNumero(articulo.getPrecioUnitarioNeto()));
            modelo.addRow(fila);
        }
        return modelo;
    }

    @Override
    public ArrayList buscarParaFacturar(Integer rubro, Integer subRubro, String criterio, Integer idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList filtrador(ArrayList rubro1, ArrayList subRubro) {
        ArrayList listado=new ArrayList();
        String sql="";
        SubRubros rubro=new SubRubros();
        totalVenta=0.00;
        totalCosto=0.00;
        Articulos articulo;
        
        Iterator it=rubro1.listIterator();
        while(it.hasNext()){
            rubro=(SubRubros)it.next();
            sql="select *,(select articulosMov.cantidad from articulosMov where articulosMov.idArticulo=articulos.ID)as sst from articulos where idsubrubro="+rubro.getId()+" and inhabilitado=0 order by nombre";
            ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            System.out.println(sql);
        Double precio=0.00;
        Double coeficiente=1.00;
        
        try {
            while(rs.next()){
                articulo=new Articulos();
                
                articulo.setCodigoAsignado(rs.getString("ID"));
                articulo.setDescripcionArticulo(rs.getString("NOMBRE"));
                articulo.setNumeroId(rs.getInt("ID"));
                articulo.setCodigoDeBarra(rs.getString("BARRAS"));
                articulo.setRecargo(rs.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rs.getDouble("PRECIO"));
                articulo.setEquivalencia(rs.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rs.getDouble("COSTO"));
                articulo.setStockMinimo(rs.getDouble("MINIMO"));
                articulo.setStockActual(rs.getDouble("sst"));
                articulo.setRubroId(rs.getInt("idrubro"));
                articulo.setIdSubRubro(rs.getInt("idsubrubro"));
                totalVenta=totalVenta + rs.getDouble("PRECIO");
                totalCosto=totalCosto + rs.getDouble("COSTO");
                articulo.setModificaPrecio(rs.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rs.getBoolean("modificaServicio"));
                String nom=rs.getString("NOMBRE");
                articulo.setIdCombo(rs.getInt("idcombo"));
                if(articulo.getIdCombo() > 0)articulo.setCombo(CargarCombo(articulo.getNumeroId()));
                listado.add(articulo);
                System.out.println("cantiadad cargada "+listado.size());
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // fin iterator
        }
        return listado;
    }

    @Override
    public ArrayList aplicarGanancia(ArrayList listado, Double ganancia) {
        Double porc1=ganancia / 100;
        porc1=porc1 +1;
        Iterator itL=listado.listIterator();
        Articulos articulo=new Articulos();
        while(itL.hasNext()){
            articulo=(Articulos)itL.next();
            articulo.setPrecioUnitarioNeto(articulo.getPrecioDeCosto() * porc1);
            sql="update articulos set precio=round("+articulo.getPrecioUnitarioNeto()+",4) where id="+articulo.getNumeroId();
            tra.guardarRegistro(sql);
        }
        return listado;
    }

    @Override
    public void depurarFiltrador(ArrayList rubro1) {
       ArrayList listado=new ArrayList();
        String sql="";
        Articulos rubro=new Articulos();
        totalVenta=0.00;
        totalCosto=0.00;
        Articulos articulo;
        
        Iterator it=rubro1.listIterator();
        while(it.hasNext()){
            articulo=(Articulos)it.next();
                //articulo=new Articulos();
                
                totalVenta=totalVenta + articulo.getPrecioUnitarioNeto();
                totalCosto=totalCosto + articulo.getPrecioDeCosto();
            }
        //return listado;
    }

    @Override
    public ArrayList modificarPreciosValor(ArrayList listado, Double porcPrecio, Double porcCosto) {
        
        Double porc1=porcPrecio;
        //porc1=porc1 + 1;
        Double porc2=porcCosto;
        //porc2=porc2 + 1;
        Iterator itL=listado.listIterator();
        Articulos articulo=new Articulos();
        while(itL.hasNext()){
            articulo=(Articulos)itL.next();
            if(porc1 > 0){
            articulo.setPrecioUnitarioNeto(porc1);
            }
            if(porc2 > 0){
            articulo.setPrecioDeCosto(porc2);
            }
            sql="update articulos set precio=round("+articulo.getPrecioUnitarioNeto()+",4),costo=round("+articulo.getPrecioDeCosto()+",4) where id="+articulo.getNumeroId();
            tra.guardarRegistro(sql);
        }
        return listado;
        
    }

    @Override
    public DefaultTableModel actualizarListado(ArrayList listado) {
        MiModeloTablaContacto modelo=new MiModeloTablaContacto();
        Iterator it=listado.listIterator();
        Articulos articulos=new Articulos();
        modelo.addColumn("Listar");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Precio");
        modelo.addColumn("Costo");
        modelo.addColumn("Rubro");
        modelo.addColumn("SubRubro");
        Object [] fila=new Object[6];
        while(it.hasNext()){
            articulos=(Articulos)it.next();
            fila[0]=articulos.getConfirmado();
            fila[1]=articulos.getDescripcionArticulo();
            fila[2]=articulos.getPrecioUnitarioNeto();
            fila[3]=articulos.getPrecioDeCosto();
            fila[4]=articulos.getNrubro();
            fila[5]=articulos.getIdSubRubro();
            modelo.addRow(fila);
        }
        return modelo;
    }

    @Override
    public DefaultTableModel mostrarModificacionStock(ArrayList listado) {
         DefaultTableModel modelo=new DefaultTableModel();
        Iterator it=listado.listIterator();
        Articulos articulos=new Articulos();
        Double porcentajeGanancia=0.00;
        modelo.addColumn("Id");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Cantidad");
        
        Object [] fila=new Object[3];
        while(it.hasNext()){
            articulos=(Articulos)it.next();
            //articulos.setConfirmado(false);
            
            fila[0]=articulos.getNumeroId();
            fila[1]=articulos.getDescripcionArticulo();
            fila[2]=articulos.getCantidad();
            
            
         
            modelo.addRow(fila);
        }
        return modelo;
    }

    @Override
    public void cargarMovimientoDeAjuste(Object articulo) {
        Articulos arti=(Articulos) articulo;
        String sql="insert into movimientosarticulos (tipoMovimiento,idArticulo,cantidad,numeroDeposito,tipoComprobante,numeroComprobante,numeroCliente,numeroUsuario,precioDeCosto,precioDeVenta,precioServicio,idcaja,fechaComprobante) values (7,"+arti.getNumeroId()+","+arti.getCantidad()+",1,0,0,0,"+Inicio.usuario.getNumeroId()+",0.00,0.00,0.00,"+Inicio.caja.getNumero()+",'"+Inicio.fechaDia+"')";
        tra.guardarRegistro(sql);
    }

    @Override
    public Boolean GuardarImpuestos(ArrayList lista, Integer idFactura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList LeerImpuestos(Integer idFactura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList ListarDetalleFactura(Integer idFactura, Integer tipo) {
         ArrayList listado = new ArrayList();
       String sql="select movimientosarticulos.cantidad,movimientosarticulos.idarticulo,movimientosarticulos.numeroComprobante,movimientosarticulos.numerocliente,movimientosarticulos.preciodecosto,movimientosarticulos.preciodeventa,articulos.barras,articulos.servicio,articulos.id,articulos.nombre,articulos.modificaprecio,articulos.idcombo from movimientosarticulos left join articulos on articulos.id=movimientosarticulos.idarticulo where tipoMovimiento="+tipo+" and numeroComprobante="+idFactura;
       Transaccionable tra=new Conecciones();
       ResultSet rs=tra.leerConjuntoDeRegistros(sql);
       Articulos articul;
        try {
            while(rs.next()){
                    articul=new Articulos();
                    articul.setCantidad(rs.getDouble("cantidad"));
                    
                    articul.setCodigoAsignado(rs.getString("id"));
                    articul.setPrecioServicio(rs.getDouble("servicio"));
                    articul.setCodigoDeBarra(rs.getString("barras"));
                    articul.setDescripcionArticulo(rs.getString("nombre"));
                    articul.setNumeroId(rs.getInt("idarticulo"));//idArticulo
                    articul.setPrecioDeCosto(rs.getDouble("preciodecosto"));
                    articul.setPrecioUnitario(rs.getDouble("preciodeventa"));
                    articul.setPrecioUnitarioNeto(rs.getDouble("preciodecosto"));
                    articul.setModificaPrecio(rs.getBoolean("modificaprecio"));
                    articul.setIdCombo(rs.getInt("idcombo"));
                    //articul.setCombo(arti.getCombo());
                    listado.add(articul);
                
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }
    
    
}
