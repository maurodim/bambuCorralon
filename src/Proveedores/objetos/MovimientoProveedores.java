
package Proveedores.objetos;

import Conversores.Numeros;
import FacturaE.FacturableE;
import interfaceGraficas.Inicio;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import objetos.Conecciones;
import tablas.MiModeloTablaContacto;

/**
 *
 * @author mauro
 */
public class MovimientoProveedores implements FacturableE{
    private Integer id;
    private Integer idProveedor;
    private String fecha;
    private Double monto;
    private Integer pagado;
    private String numeroComprobante;
    private Integer tipoComprobante;
    private String descripcionTipoComprobante;
    private Double subTotal;
    private Double saldo;
    private Integer idComprobante;

    public Integer getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(Integer idComprobante) {
        this.idComprobante = idComprobante;
    }
    

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getPagado() {
        return pagado;
    }

    public void setPagado(Integer pagado) {
        this.pagado = pagado;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public Integer getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(Integer tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getDescripcionTipoComprobante() {
        return descripcionTipoComprobante;
    }

    public void setDescripcionTipoComprobante(String descripcionTipoComprobante) {
        this.descripcionTipoComprobante = descripcionTipoComprobante;
    }
    public ArrayList listarFacturasProveedor(Integer estado){
        //LO VOY A UTILIZAR PARA LISTAR POR PROVEEDOR
        String sql="select * from movimientosproveedores where numeroProveedor="+estado+" and pagado=0 and tipoComprobante=1 order by fecha desc";
        Transaccionable tra=new Conecciones();
        ArrayList listado=new ArrayList();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        MovimientoProveedores mov;
        try {
            while(rs.next()){
                mov=new MovimientoProveedores();
                mov.setFecha(rs.getString("fecha"));
                mov.setId(rs.getInt("id"));
                mov.setIdProveedor(rs.getInt("numeroProveedor"));
                mov.setMonto(rs.getDouble("monto"));
                mov.setNumeroComprobante(rs.getString("numeroComprobante"));
                mov.setTipoComprobante(rs.getInt("tipoComprobante"));
                mov.setSaldo(rs.getDouble("saldo"));
                mov.setIdComprobante(rs.getInt("idcomprobante"));
                if(mov.getTipoComprobante()==1)mov.setDescripcionTipoComprobante("FACTURA PROVEEDOR");
                if(mov.getTipoComprobante()==2)mov.setDescripcionTipoComprobante("ORDEN DE PAGO");
                
                listado.add(mov);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MovimientoProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }
    public ArrayList lsitarFacturasProveedorOrdenadas(Integer estado){
        //LO VOY A UTILIZAR PARA LISTAR POR PROVEEDOR
        String sql="select * from movimientosproveedores where numeroProveedor="+estado+" and pagado=0 and tipoComprobante=1 order by fecha";
        Transaccionable tra=new Conecciones();
        ArrayList listado=new ArrayList();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        MovimientoProveedores mov;
        try {
            while(rs.next()){
                mov=new MovimientoProveedores();
                mov.setFecha(rs.getString("fecha"));
                mov.setId(rs.getInt("id"));
                mov.setIdProveedor(rs.getInt("numeroProveedor"));
                mov.setMonto(rs.getDouble("monto"));
                mov.setNumeroComprobante(rs.getString("numeroComprobante"));
                mov.setTipoComprobante(rs.getInt("tipoComprobante"));
                mov.setSaldo(rs.getDouble("saldo"));
                mov.setIdComprobante(rs.getInt("idcomprobante"));
                if(mov.getTipoComprobante()==1)mov.setDescripcionTipoComprobante("FACTURA PROVEEDOR");
                if(mov.getTipoComprobante()==2)mov.setDescripcionTipoComprobante("ORDEN DE PAGO");
                
                listado.add(mov);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MovimientoProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }
    public DefaultTableModel mostrarARecibir(ArrayList listado) {
        MiModeloTablaContacto listado1=new MiModeloTablaContacto();
        MovimientoProveedores cotizacion;
        Iterator iL=listado.listIterator();
        listado1.addColumn("Recibo");
        listado1.addColumn("Fecha");
        listado1.addColumn("Numero");
        listado1.addColumn("Monto");
        listado1.addColumn("Saldo");
        
        Object[] fila=new Object[5];
        while(iL.hasNext()){
            
            cotizacion=(MovimientoProveedores) iL.next();
            fila[0]=false;
            
            fila[1]=String.valueOf(cotizacion.getFecha());
            fila[2]=String.valueOf(cotizacion.getNumeroComprobante());
            fila[3]=Numeros.ConvertirNumero(cotizacion.getMonto());
            fila[4]=Numeros.ConvertirNumero(cotizacion.getSaldo());
            listado1.addRow(fila);
        }
        return listado1;
    }
    @Override
    public Object recuperar(Object Fe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer guardar(Object Fe) {
        Integer id=0;
        MovimientoProveedores mov=(MovimientoProveedores) Fe;
        Transaccionable tra=new Conecciones();
        String sql="insert into movimientosproveedores (numeroProveedor,monto,numeroComprobante,idUsuario,tipoComprobante,subtotal,saldo) values ("+mov.getIdProveedor()+",round("+mov.getMonto()+",4),'"+mov.getNumeroComprobante()+"',"+Inicio.usuario.getNumeroId()+","+mov.getTipoComprobante()+","+mov.getSubTotal()+","+mov.getSaldo()+")";
        tra.guardarRegistro(sql);
        sql="select LAST_INSERT_ID()";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                id=rs.getInt(1);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MovimientoProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public Object modificar(Object Fe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorEstado(Integer estado) {
        //LO VOY A UTILIZAR PARA LISTAR POR PROVEEDOR
        String sql="select * from movimientosproveedores where numeroProveedor="+estado+" order by fecha desc";
        Transaccionable tra=new Conecciones();
        ArrayList listado=new ArrayList();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        MovimientoProveedores mov;
        try {
            while(rs.next()){
                mov=new MovimientoProveedores();
                mov.setFecha(rs.getString("fecha"));
                mov.setId(rs.getInt("id"));
                mov.setIdProveedor(rs.getInt("numeroProveedor"));
                mov.setMonto(rs.getDouble("monto"));
                mov.setNumeroComprobante(rs.getString("numeroComprobante"));
                mov.setTipoComprobante(rs.getInt("tipoComprobante"));
                mov.setSaldo(rs.getDouble("saldo"));
                mov.setIdComprobante(rs.getInt("idcomprobante"));
                if(mov.getTipoComprobante()==1)mov.setDescripcionTipoComprobante("FACTURA PROVEEDOR");
                if(mov.getTipoComprobante()==2)mov.setDescripcionTipoComprobante("ORDEN DE PAGO");
                
                listado.add(mov);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MovimientoProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public Object cargar(Integer id) {
        //LO VOY A UTILIZAR PARA LISTAR POR PROVEEDOR
        String sql="select * from movimientosproveedores where id="+id;
        Transaccionable tra=new Conecciones();
        ArrayList listado=new ArrayList();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        MovimientoProveedores mov = null;
        try {
            while(rs.next()){
                mov=new MovimientoProveedores();
                mov.setFecha(rs.getString("fecha"));
                mov.setId(rs.getInt("id"));
                mov.setIdProveedor(rs.getInt("numeroProveedor"));
                mov.setMonto(rs.getDouble("monto"));
                mov.setNumeroComprobante(rs.getString("numeroComprobante"));
                mov.setTipoComprobante(rs.getInt("tipoComprobante"));
                mov.setSaldo(rs.getDouble("saldo"));
                mov.setIdComprobante(rs.getInt("idcomprobante"));
                if(mov.getTipoComprobante()==1)mov.setDescripcionTipoComprobante("FACTURA PROVEEDOR");
                if(mov.getTipoComprobante()==2)mov.setDescripcionTipoComprobante("ORDEN DE PAGO");
                
                //listado.add(mov);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MovimientoProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mov;
    }

    @Override
    public DefaultTableModel mostrarListado(ArrayList listado) {
        DefaultTableModel modelo=new DefaultTableModel();
        modelo.addColumn("FECHA");
        modelo.addColumn("TIPO");
        modelo.addColumn("NUMERO");
        modelo.addColumn("MONTO");
        modelo.addColumn("SALDO");
        Object [] fila=new Object[5];
        Iterator it=listado.listIterator();
        MovimientoProveedores mov;
        Double montt=0.00;
        while(it.hasNext()){
            mov=(MovimientoProveedores) it.next();
            fila[0]=mov.getFecha();
            fila[1]=mov.getDescripcionTipoComprobante();
            fila[2]=mov.getNumeroComprobante();
            if(mov.getMonto() < 0){
                montt=mov.getMonto() * (-1);
            }else{
                montt=mov.getMonto();
            }
            fila[3]=Numeros.ConvertirNumero(montt);
            if(mov.getTipoComprobante()==1){
                fila[4]=Numeros.ConvertirNumero(mov.getSaldo());
            }
            modelo.addRow(fila);
        }
        return modelo;
    }

    @Override
    public Object reEnviar(Object fe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Object fe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String reimprimir(Object fe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String imprimir(Object fe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
