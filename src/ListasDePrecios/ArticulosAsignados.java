
package ListasDePrecios;

import Articulos.Articulos;
import Articulos.Rubros;
import Articulos.SubRubros;
import Conversores.Numeros;
import Proveedores.Proveedores;
import facturacion.clientes.Clientes;
import facturacion.clientes.Facturable;
import facturacion.clientes.ListasDePrecios;
import interfaceGraficas.BarraDeProgreso;
import interfaces.Comparables;
import interfaces.Editables;
import interfaces.Personalizable;
import interfaces.Transaccionable;
import interfacesPrograma.Facturar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.table.DefaultTableModel;
import objetos.Conecciones;
import tablas.MiModeloTablaContacto;

/**
 *
 * @author mauro di
 */
public class ArticulosAsignados implements Articulable{
    private Integer id;
    private String descripcion;
    private Double precioUnitario;
    private Double precioDeCosto;
    private Integer idCliente;
    private Integer idRubro;
    private Integer idSubRubro;
    private Integer idLista;
    private Double coeficiente;
    private String observaciones;
    private Integer origen;
    private String descLista;
    private static Transaccionable tra=new Conecciones();
    private static ResultSet rs;

    public String getDescLista() {
        return descLista;
    }

    public void setDescLista(String descLista) {
        this.descLista = descLista;
    }

    
    public Integer getOrigen() {
        return origen;
    }

    public void setOrigen(Integer origen) {
        this.origen = origen;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getPrecioDeCosto() {
        return precioDeCosto;
    }

    public void setPrecioDeCosto(Double precioDeCosto) {
        this.precioDeCosto = precioDeCosto;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Integer idRubro) {
        this.idRubro = idRubro;
    }

    public Integer getIdSubRubro() {
        return idSubRubro;
    }

    public void setIdSubRubro(Integer idSubRubro) {
        this.idSubRubro = idSubRubro;
    }

    public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }

    public Double getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(Double coeficiente) {
        this.coeficiente = coeficiente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
    
    @Override
    public ArrayList filtrador(ArrayList rubro1, ArrayList subRubro,Object cli) {
        ArrayList listado=new ArrayList();
        String sql="";
        SubRubros rubro=new SubRubros();
        Integer idC=0;
        Integer idLista=1;
        Double coeficiente1=1.00;
        try{
        Clientes cliente;
        cliente=(Clientes)cli;
        idC=cliente.getCodigoId();
        idLista=cliente.getListaDePrecios();
        coeficiente1=cliente.getCoeficienteListaDeprecios();
        }catch(java.lang.ClassCastException ex){
            Proveedores cliente;
            cliente=(Proveedores)cli;
        }
        ArticulosAsignados articulo;
        
        Iterator it=rubro1.listIterator();
        while(it.hasNext()){
            rubro=(SubRubros)it.next();
            sql="select articulos.id,articulos.nombre,articulos.costo,articulos.precio,articulos.idrubro,articulos.idsubrubro,(select aplicacion.coeficiente from aplicacion where aplicacion.idarticulo=articulos.id and aplicacion.idcliente="+idC+" limit 0,1)as coeficienteA,(select aplicacion.observaciones from aplicacion where aplicacion.idarticulo=articulos.id and aplicacion.idcliente="+idC+" limit 0,1)as observaciones,(select aplicacion.idlista from aplicacion where aplicacion.idarticulo=articulos.id and aplicacion.idcliente="+idC+" limit 0,1)as idlista,(select articulosMov.cantidad from articulosMov where articulosMov.idArticulo=articulos.ID)as sst from articulos where idsubrubro="+rubro.getId()+" order by nombre";
            rs=tra.leerConjuntoDeRegistros(sql);
            //System.out.println(sql);
        Double precio=0.00;
        Double coeficiente=1.00;
        Personalizable per=new ListasDePrecios();
        ListasDePrecios lst=new ListasDePrecios();
        try {
            while(rs.next()){
                articulo=new ArticulosAsignados();
                
                articulo.setId(rs.getInt("id"));
                
                articulo.setDescripcion(rs.getString("nombre"));
                articulo.setIdCliente(idC);
                if(rs.getInt("idlista")==0){
                    articulo.setIdLista(idLista);
                    coeficiente=coeficiente1;
                    articulo.setCoeficiente(coeficiente1);
                    articulo.setOrigen(0);
                }else{
                    articulo.setIdLista(rs.getInt("idlista"));
                    articulo.setCoeficiente(rs.getDouble("coeficienteA"));
                    coeficiente=rs.getDouble("coeficienteA");
                    articulo.setOrigen(1);
                }
                
                lst=(ListasDePrecios) per.buscarPorNumero(articulo.getIdLista());
                articulo.setDescLista(lst.getDescripcionLista());
                articulo.setIdRubro(rs.getInt("idrubro"));
                articulo.setIdSubRubro(rs.getInt("idsubrubro"));
                articulo.setObservaciones(rs.getString("observaciones"));
                articulo.setPrecioDeCosto(rs.getDouble("costo"));
                precio=rs.getDouble("precio") * coeficiente;
                articulo.setPrecioUnitario(precio);
                
                listado.add(articulo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosAsignados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // fin iterator
        }
        return listado;
    }

    @Override
    public void guardar(ArrayList listado) {
        ArticulosAsignados arti;
        Iterator itA=listado.listIterator();
        String sql="";
        Transaccionable tra=new Conecciones();
        ResultSet rs;
        //BarraDeProgreso progreso=new BarraDeProgreso(listado.size());
        //progreso.setTitle("Progreso de Modificacion de Lista de Precio");
        
        //JProgressBar barra=new JProgressBar(0,listado.size());
        //barra.setMaximum(listado.size());
        
        //progreso.setVisible(true);
        //progreso.toFront();
        //barra.setStringPainted(true);
        
        int aaa=0;
        while(itA.hasNext()){
            arti=(ArticulosAsignados)itA.next();
            sql="select * from aplicacion where idcliente="+arti.getIdCliente()+" and idarticulo="+arti.getId();
            rs=tra.leerConjuntoDeRegistros(sql);
            arti.setOrigen(0);
            try {
                while(rs.next()){
                    arti.setOrigen(1);
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticulosAsignados.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(arti.getOrigen()==0){
                //nuevo
                sql="insert into aplicacion (idcliente,idarticulo,idlista,coeficiente,observaciones) values ("+arti.getIdCliente()+","+arti.getId()+","+arti.getIdLista()+","+arti.getCoeficiente()+",'"+arti.getObservaciones()+"')";
                tra.guardarRegistro(sql);
            }else{
                //modificar
                sql="update aplicacion set idlista="+arti.getIdLista()+",coeficiente="+arti.getCoeficiente()+",observaciones='"+arti.getObservaciones()+"' where idcliente="+arti.getIdCliente()+" and idarticulo="+arti.getId();
                tra.guardarRegistro(sql);
            }
            aaa++;
            //BarraDeProgreso.agregar(aaa);
            System.out.println(aaa+" "+sql);
        }
        //progreso.dispose();
    }

    @Override
    public DefaultTableModel mostrarListado(ArrayList lista) {
        MiModeloTablaContacto modelo=new MiModeloTablaContacto();
        Iterator it=lista.listIterator();
        ArticulosAsignados articulos=new ArticulosAsignados();
        modelo.addColumn("Listar");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Precio");
        modelo.addColumn("Costo");
        modelo.addColumn("Lista Asig.");
        modelo.addColumn("Observaciones");
        Object [] fila=new Object[6];
        while(it.hasNext()){
            articulos=(ArticulosAsignados)it.next();
            fila[0]=true;
            fila[1]=articulos.getDescripcion();
            fila[2]=Numeros.ConvertirNumero(articulos.getPrecioUnitario());
            fila[3]=articulos.getPrecioDeCosto();
            fila[4]=articulos.getDescLista();
            fila[5]=articulos.getObservaciones();
            modelo.addRow(fila);
        }
        return modelo;
        
    }

    @Override
    public ArrayList listarTodos(Object idCliente) {
        ArticulosAsignados articulo;
        Clientes cliente=new Clientes();
        cliente=(Clientes)idCliente;
        ArrayList listado=new ArrayList();
        String sql="select articulos.id,articulos.nombre,articulos.costo,articulos.precio,articulos.idrubro,articulos.idsubrubro,(select aplicacion.coeficiente from aplicacion where aplicacion.idarticulo=articulos.id and aplicacion.idcliente="+cliente.getCodigoId()+" limit 0,1)as coeficienteA,(select aplicacion.observaciones from aplicacion where aplicacion.idarticulo=articulos.id and aplicacion.idcliente="+cliente.getCodigoId()+" limit 0,1)as observaciones,(select aplicacion.idlista from aplicacion where aplicacion.idarticulo=articulos.id and aplicacion.idcliente="+cliente.getCodigoId()+" limit 0,1)as idlista,(select articulosMov.cantidad from articulosMov where articulosMov.idArticulo=articulos.ID)as sst from articulos order by nombre";
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        Double precio=0.00;
        Double coeficiente=1.00;
        Personalizable per=new ListasDePrecios();
        ListasDePrecios lst=new ListasDePrecios();
        try {
            while(rs.next()){
                articulo=new ArticulosAsignados();
                
                articulo.setId(rs.getInt("id"));
                
                articulo.setDescripcion(rs.getString("nombre"));
                articulo.setIdCliente(cliente.getCodigoId());
                if(rs.getInt("idlista")==0){
                    articulo.setIdLista(cliente.getListaDePrecios());
                    coeficiente=cliente.getCoeficienteListaDeprecios();
                    articulo.setCoeficiente(cliente.getCoeficienteListaDeprecios());
                }else{
                    articulo.setIdLista(rs.getInt("idlista"));
                    articulo.setCoeficiente(rs.getDouble("coeficienteA"));
                    coeficiente=rs.getDouble("coeficienteA");
                }
                lst=(ListasDePrecios) per.buscarPorNumero(articulo.getIdLista());
                articulo.setDescLista(lst.getDescripcionLista());
                articulo.setIdRubro(rs.getInt("idrubro"));
                articulo.setIdSubRubro(rs.getInt("idsubrubro"));
                articulo.setObservaciones(rs.getString("observaciones"));
                articulo.setPrecioDeCosto(rs.getDouble("costo"));
                precio=rs.getDouble("precio") * coeficiente;
                articulo.setPrecioUnitario(precio);
                listado.add(articulo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosAsignados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listado;
        
    }

    @Override
    public ArrayList convertirListadoEnArticulos(ArrayList listado) {
        ArrayList listadoArticulos=new ArrayList();
        Iterator it=listado.listIterator();
        Articulos articulo;
        Facturar comp=new Articulos();
        ArticulosAsignados art=new ArticulosAsignados();
        while(it.hasNext()){
            art=(ArticulosAsignados)it.next();
            articulo=new Articulos();
            
            articulo=(Articulos) comp.cargarPorCodigoAsignado(art.getId());
            if(articulo !=null){
                System.out.println(art.getId()+" // "+art.getDescripcion()+" // "+art.getPrecioDeCosto()+" // "+articulo.getPrecioDeCosto());
                articulo.setPrecioDeCosto(art.getPrecioDeCosto());
                
            
            articulo.setPrecioUnitario(Numeros.ConvertirStringADouble(String.valueOf(art.getPrecioUnitario())));
            articulo.setPrecioUnitarioNeto(Numeros.ConvertirStringADouble(String.valueOf(art.getPrecioUnitario())));
            listadoArticulos.add(articulo);
            }
        }
        
        return listadoArticulos;
    }

    @Override
    public ArrayList filtradorDeFormularios(ArrayList rubro1, ArrayList subRubro, Object cli, String ttx) {
        ArrayList listado=new ArrayList();
        String sql="";
        SubRubros rubro=new SubRubros();
       Integer idC=0;
        Integer idLista=1;
        Double coeficiente1=1.00;
        try{
        Clientes cliente;
        cliente=(Clientes)cli;
        idC=cliente.getCodigoId();
        idLista=cliente.getListaDePrecios();
        coeficiente1=cliente.getCoeficienteListaDeprecios();
        }catch(java.lang.ClassCastException ex){
            Proveedores cliente;
            cliente=(Proveedores)cli;
        }
        ArticulosAsignados articulo;
        
        Iterator it=rubro1.listIterator();
        while(it.hasNext()){
            rubro=(SubRubros)it.next();
            sql="select articulos.id,articulos.nombre,articulos.costo,articulos.precio,articulos.idrubro,articulos.idsubrubro,(select aplicacion.coeficiente from aplicacion where aplicacion.idarticulo=articulos.id and aplicacion.idcliente="+idC+" limit 0,1)as coeficienteA,(select aplicacion.observaciones from aplicacion where aplicacion.idarticulo=articulos.id and aplicacion.idcliente="+idC+" limit 0,1)as observaciones,(select aplicacion.idlista from aplicacion where aplicacion.idarticulo=articulos.id and aplicacion.idcliente="+idC+" limit 0,1)as idlista,(select articulosMov.cantidad from articulosMov where articulosMov.idArticulo=articulos.ID)as sst from articulos where idsubrubro="+rubro.getId()+" and nombre like '%"+ttx+"%' order by nombre";
            rs=tra.leerConjuntoDeRegistros(sql);
            //System.out.println(sql);
        Double precio=0.00;
        Double coeficiente=1.00;
        Personalizable per=new ListasDePrecios();
        ListasDePrecios lst=new ListasDePrecios();
        try {
            while(rs.next()){
                articulo=new ArticulosAsignados();
                
                articulo.setId(rs.getInt("id"));
                
                articulo.setDescripcion(rs.getString("nombre"));
                articulo.setIdCliente(idC);
                if(rs.getInt("idlista")==0){
                    articulo.setIdLista(idLista);
                    coeficiente=coeficiente1;
                    articulo.setCoeficiente(coeficiente1);
                    articulo.setOrigen(0);
                }else{
                    articulo.setIdLista(rs.getInt("idlista"));
                    articulo.setCoeficiente(rs.getDouble("coeficienteA"));
                    coeficiente=rs.getDouble("coeficienteA");
                    articulo.setOrigen(1);
                }
                lst=(ListasDePrecios) per.buscarPorNumero(articulo.getIdLista());
                articulo.setDescLista(lst.getDescripcionLista());
                articulo.setIdRubro(rs.getInt("idrubro"));
                articulo.setIdSubRubro(rs.getInt("idsubrubro"));
                articulo.setObservaciones(rs.getString("observaciones"));
                articulo.setPrecioDeCosto(rs.getDouble("costo"));
                precio=rs.getDouble("precio") * coeficiente;
                articulo.setPrecioUnitario(precio);
                listado.add(articulo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosAsignados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // fin iterator
        }
        return listado;
    }
    
}
