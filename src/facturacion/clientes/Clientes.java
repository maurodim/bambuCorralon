/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.clientes;


import Conversores.Numeros;
import interfaceGraficas.Inicio;
import interfaces.Adeudable;
import interfaces.Transaccionable;
import interfacesPrograma.Busquedas;
import interfacesPrograma.Facturar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Comprobantes;
import objetos.ConeccionLocal;
import objetos.Conecciones;


/**
 *
 * @author Administrador
 */
public class Clientes implements Busquedas,Facturar,Adeudable{
        private String codigoCliente;
        private String razonSocial;
        private Double saldo;
        private Integer condicionDePago;
        private Date fechaActualizacion;
        private String direccion;
        private String localidad;
        private String telefono;
        private String observaciones;
        private Boolean contactado;
        private Date fechaPedido;
        private String numeroPedido;
        private Date fechaListado;
        private Integer numeroListado;
        private Date fechaHdr;
        private Integer numeroHdr;
        private String numeroDeCuit;
        private String empresa;
        private Integer condicionDeVenta;
        private Integer listaDePrecios;
        private Double descuento;
        private String condicionIva;
        private Double coeficienteListaDeprecios;
        private Integer codigoId;
        private Double cupoDeCredito;
        private Double saldoActual;
        private Integer tipoIva;
        private String celular;
        private String fax;
        private String codigoPostal;
        private static Integer numeroRecibo;
        private String fantasia;
        private static ConcurrentHashMap listadoClientes=new ConcurrentHashMap();
        private static ConcurrentHashMap listadoPorNom=new ConcurrentHashMap();
        private static ConcurrentHashMap listadoPorContacto=new ConcurrentHashMap();
        private static ConcurrentHashMap listadoPorFantasia=new ConcurrentHashMap();
        private static int signal=0;
        private String responsable;
        private String direccionFantasia;
        private String email;
        private Integer tipoComprobante;
        private String direccionDeEntrega;
        private static Transaccionable tra=new Conecciones();
        private static ResultSet rs;
        private Integer idTransporte;

    public Integer getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(Integer idTransporte) {
        this.idTransporte = idTransporte;
    }
        

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

        
    public String getDireccionDeEntrega() {
        return direccionDeEntrega;
    }

    public void setDireccionDeEntrega(String direccionDeEntrega) {
        this.direccionDeEntrega = direccionDeEntrega;
    }
        
        

    public Integer getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(Integer tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }
        

    public String getDireccionFantasia() {
        return direccionFantasia;
    }

    public void setDireccionFantasia(String direccionFantasia) {
        this.direccionFantasia = direccionFantasia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
        
        

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

        
    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

        
        
    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
        
        

    public Integer getTipoIva() {
        return tipoIva;
    }

    public void setTipoIva(Integer tipoIva) {
        this.tipoIva = tipoIva;
    }
        
        
        

    public Double getCupoDeCredito() {
        return cupoDeCredito;
    }

    public void setCupoDeCredito(Double cupoDeCredito) {
        this.cupoDeCredito = cupoDeCredito;
    }

    public Double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(Double saldoActual) {
        this.saldoActual = saldoActual;
    }
        

    public Integer getCodigoId() {
        return codigoId;
    }

    public void setCodigoId(Integer codigoId) {
        this.codigoId = codigoId;
    }
    public static void cargarMap(){
              
            
            
            String sql=null;
            
            if(signal==1){
                tra=new Conecciones();
                sql="select *,(select coeficienteslistas.coeficiente from coeficienteslistas where coeficienteslistas.id=clientes.LISTADEPRECIO)as coeficiente,(select sum(movimientosclientes.monto) from movimientosclientes where pagado=0 and movimientosclientes.numeroProveedor=clientes.id)as saldo from clientes";    
                //System.err.println("LEER CLIENTES - "+sql);
                //signal=0;
                Inicio.coneccionRemota=true;
            }else{
             
                tra=new Conecciones();
                sql="select id,idtransporte,clientes.fax,clientes.celular,clientes.COD_CLIENT,clientes.fantasia,clientes.RAZON_SOCI,clientes.DOMICILIO,clientes.COND_VTA,(clientes.LISTADEPRECIO)as NRO_LISTA,(select coeficienteslistas.coeficiente from coeficienteslistas where coeficienteslistas.id=clientes.listadeprecio)as descuento,(clientes.NUMERODECUIT)as IDENTIFTRI,clientes.empresa,clientes.TELEFONO_1,clientes.coeficiente,(clientes.CUPODECREDITO) AS CUPO_CREDI,clientes.saldo,clientes.TIPO_IVA,(select condicionesiva.descripcion from condicionesiva where id=clientes.tipo_iva)as tipo_iva2,(select condicionesiva.tipocomprobante from condicionesiva where condicionesiva.id=clientes.tipo_iva)as tipocomprobante,(select localidades.localidad from localidades where id=clientes.localidad)as localidad1,clientes.responsable,(select localidades.codigo_postal from localidades where id=clientes.localidad)as postal from clientes order by RAZON_SOCI";
            }
            //sql="select *,(select coeficienteslistas.coeficiente from coeficienteslistas where coeficienteslistas.id=clientes.NRO_LISTA)as coeficiente,(select sum(movimientosclientes.monto) from movimientosclientes where pagado=0 and movimientosclientes.numeroProveedor=clientes.id)as saldo from clientes";
            //System.out.println("CLIENTES "+sql);
            //String sql="select pedidos_carga1.COD_CLIENT,pedidos_carga1.RAZON_SOC,pedidos_carga1.NRO_PEDIDO,pedidos_carga1.numero,pedidos_carga1.LEYENDA_2 from pedidos_carga1 where RAZON_SOC like '"+cliente+"%' group by COD_CLIENT order by RAZON_SOC";
            rs=tra.leerConjuntoDeRegistros(sql);
            try{
                listadoClientes.clear();
                listadoPorNom.clear();
                listadoPorContacto.clear();
                listadoPorFantasia.clear();
                String codigo="";
                String nombre="";
                String sql1="";
                String responsable="";
                String fantasia="";
                Transaccionable rat=new Conecciones();
                
                ResultSet rr=null;
            while(rs.next()){               
                Clientes cli=new Clientes();
                cli.setCodigoId(rs.getInt("id"));
                cli.setCodigoCliente(rs.getString("id"));
                cli.setRazonSocial(rs.getString("RAZON_SOCI"));
                cli.setDireccion(rs.getString("DOMICILIO"));
                cli.setCondicionDeVenta(rs.getInt("COND_VTA"));
                cli.setListaDePrecios(rs.getInt("NRO_LISTA"));
                //Double descuento=Double.parseDouble(rs.getString("PORC_DESC"));
                cli.setDescuento(rs.getDouble("descuento"));
                //cli.setDescuento(descuento);
                cli.setNumeroDeCuit(rs.getString("IDENTIFTRI"));
                cli.setEmpresa(rs.getString("empresa"));
                cli.setCondicionIva(rs.getString("TIPO_IVA2"));
                cli.setTipoIva(rs.getInt("tipo_iva"));
                cli.setTelefono(rs.getString("TELEFONO_1"));
                cli.setLocalidad(rs.getString("localidad1"));
                cli.setCoeficienteListaDeprecios(rs.getDouble("coeficiente"));
                cli.setCupoDeCredito(rs.getDouble("CUPO_CREDI"));
                cli.setResponsable(rs.getString("responsable"));
                cli.setFantasia(rs.getString("fantasia"));
                cli.setCelular(rs.getString("celular"));
                cli.setFax(rs.getString("fax"));
                cli.setCodigoPostal(rs.getString("postal"));
                cli.setTipoComprobante(rs.getInt("tipocomprobante"));
                cli.setIdTransporte(rs.getInt("idtransporte"));
               // if(Inicio.usuario.getNivelDeAutorizacion()==1){
                System.out.println("ACTUALIZACION :"+Inicio.actualizacionesClientes);
                
                
            /*    
            }else{
                cli.setSaldo(rs.getDouble("saldo"));
                cli.setSaldoActual(rs.getDouble("saldo"));    
                }
                    */
                //cli.setNumeroPedido(rs.getString(3));
                //cli.setObservaciones(rs.getString(5));
                //System.out.println("CLIENTE "+cli.getRazonSocial() +"COMENTARIO "+cli.getCodigoCliente());
                codigo=cli.getCodigoCliente();
                nombre=cli.getRazonSocial();
                responsable=cli.getResponsable();
                fantasia=cli.getFantasia();
                listadoClientes.putIfAbsent(codigo,cli);
                listadoPorNom.putIfAbsent(nombre,cli);
                listadoPorContacto.putIfAbsent(responsable,cli);
                listadoPorFantasia.putIfAbsent(fantasia, cli);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
            if(signal==1){
            //if(Inicio.coneccionRemota)BackapearClientes();
            signal=0;
            Inicio.coneccionRemota=false;
            }else{
            
            }
    }   

    public Clientes(String codigoCliente) {
        Clientes clientesTango=(Clientes)listadoClientes.get(codigoCliente);
                    
                    this.codigoId=clientesTango.getCodigoId();
                    this.codigoCliente=clientesTango.getCodigoCliente();
                    this.razonSocial=clientesTango.getRazonSocial();
                    this.direccion=clientesTango.getDireccion();
                    this.condicionDeVenta=clientesTango.getCondicionDeVenta();
                    this.listaDePrecios=clientesTango.getListaDePrecios();
                    Double descuent=clientesTango.getDescuento();                
                    this.descuento=descuent;
                    this.numeroDeCuit=clientesTango.getNumeroDeCuit();
                    this.empresa=clientesTango.getEmpresa();
                    this.condicionIva=clientesTango.getCondicionIva();
                    this.telefono=clientesTango.getTelefono();
                    this.localidad=clientesTango.getLocalidad();
                    this.coeficienteListaDeprecios=clientesTango.getCoeficienteListaDeprecios();
                    this.cupoDeCredito=clientesTango.getCupoDeCredito();
                    this.saldoActual=clientesTango.getSaldoActual();
                    this.saldo=clientesTango.getSaldo();
                    this.direccionDeEntrega=clientesTango.getDireccionDeEntrega();
                    //cli.setNumeroPedido(rs.getString(3));
                    //cli.setObservaciones(rs.getString(5));
                    //System.out.println("CLIENTE "+cli.getRazonSocial() +"COMENTARIO "+cli.getCodigoCliente());
                    //ped.add(cli);
            
    }

    public Clientes() {
    }
    

    public Double getCoeficienteListaDeprecios() {
        return coeficienteListaDeprecios;
    }

    public void setCoeficienteListaDeprecios(Double coeficienteListaDeprecios) {
        this.coeficienteListaDeprecios = coeficienteListaDeprecios;
    }

    public String getCondicionIva() {
        return condicionIva;
    }

    public void setCondicionIva(String condicionIva) {
        this.condicionIva = condicionIva;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }
        

    public Integer getCondicionDeVenta() {
        return condicionDeVenta;
    }

    public void setCondicionDeVenta(Integer condicionDeVenta) {
        this.condicionDeVenta = condicionDeVenta;
    }

    public Integer getListaDePrecios() {
        return listaDePrecios;
    }

    public void setListaDePrecios(Integer listaDePrecios) {
        
            this.listaDePrecios = listaDePrecios;
        
    }
        
        

    public String getNumeroDeCuit() {
        return numeroDeCuit;
    }

    public void setNumeroDeCuit(String numeroDeCuit) {
        this.numeroDeCuit = numeroDeCuit;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Boolean getContactado() {
        return contactado;
    }

    public void setContactado(Boolean contactado) {
        this.contactado = contactado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaHdr() {
        return fechaHdr;
    }

    public void setFechaHdr(Date fechaHdr) {
        this.fechaHdr = fechaHdr;
    }

    public Date getFechaListado() {
        return fechaListado;
    }

    public void setFechaListado(Date fechaListado) {
        this.fechaListado = fechaListado;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Integer getNumeroHdr() {
        return numeroHdr;
    }

    public void setNumeroHdr(Integer numeroHdr) {
        this.numeroHdr = numeroHdr;
    }

    public Integer getNumeroListado() {
        return numeroListado;
    }

    public void setNumeroListado(Integer numeroListado) {
        this.numeroListado = numeroListado;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
        

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
        


    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Integer getCondicionDePago() {
        return condicionDePago;
    }

    public void setCondicionDePago(Integer condicionDePago) {
        this.condicionDePago = condicionDePago;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    public void agregarNuevo(Clientes cli) throws SQLException{
        
        String sql="insert into clientes (COD_CLIENT,RAZON_SOCI,DOMICILIO,LOCALIDAD,TELEFONO_1,TIPO_IVA,IDENTIFTRI,COND_VTA,NRO_LISTA,empresa,dentrega,idtransporte) values ('"+cli.getCodigoCliente()+"','"+cli.getRazonSocial()+"','"+cli.getDireccion()+"','SANTA FE','"+cli.getTelefono()+"',"+cli.getCondicionIva()+",'"+cli.getNumeroDeCuit()+"',1,1,'"+cli.getEmpresa()+"','"+cli.getDireccionDeEntrega()+"',"+cli.getIdTransporte()+")";
        if(tra.guardarRegistro(sql)){
            
        }else{
            
        }
        
    }
    public void ajustarSaldo(Clientes cli,Double ajuste){
        numeroActualRecibo();
       numeroRecibo++;
        
        String sql="insert into movimientosclientes (numeroProveedor,monto,numeroComprobante,idUsuario,tipoComprobante,idSucursal,idRemito) values ("+cli.getCodigoId()+","+ajuste+","+numeroRecibo+","+Inicio.usuario.getNumeroId()+",15,"+Inicio.sucursal.getNumero()+",0)";
        tra.guardarRegistro(sql);
        
    }
    public ArrayList listarPorVehiculo(int numeroVehiculo,String fecha) throws SQLException{
        ArrayList lista=new ArrayList();
        //String sql="select * from clientes where RAZON_SOCI like";
        String sql="select pedidos_carga1.COD_CLIENT,pedidos_carga1.RAZON_SOC,pedidos_carga1.listado,pedidos_carga1.LEYENDA_2,pedidos_carga1.NRO_PEDIDO,(select clientes.DOMICILIO from clientes where clientes.COD_CLIENT like pedidos_carga1.COD_CLIENT and clientes.RAZON_SOCI like pedidos_carga1.RAZON_SOC group by RAZON_SOC)as domicilio,(select clientes.TELEFONO_1 from clientes where clientes.COD_CLIENT like pedidos_carga1.COD_CLIENT and clientes.RAZON_SOCI like pedidos_carga1.RAZON_SOC group by RAZON_SOC)as telefono,(select clientes.LOCALIDAD from clientes where clientes.COD_CLIENT like pedidos_carga1.COD_CLIENT and clientes.RAZON_SOCI like pedidos_carga1.RAZON_SOC group by RAZON_SOC)as localidad,if((select contactos.contactado from contactos where contactos.numerocliente like pedidos_carga1.COD_CLIENT and contactos.numeroListado=pedidos_carga1.listado)=1,true,false)as contactado from pedidos_carga1 where vehiculo="+numeroVehiculo+" and entrega like '"+fecha+"%' group by RAZON_SOC";
                // and entregaConv like '"+fecha+"'";
        
        return lista;
    }
    public static void BackapearClientes(){
        //if(listadoPorNom.size()==0){
        if(signal==0){
            signal=1;
            cargarMap();
            //signal=0;
        }
        //}
        ArrayList listado=new ArrayList();
        Busquedas bus=new Clientes();
        Clientes cli=new Clientes();
        listado=bus.listar("");
        Iterator ilC=listado.listIterator();
        Transaccionable tt=new Conecciones();
        String sql="delete from clientes";
        tt.guardarRegistro(sql);
        while(ilC.hasNext()){
            cli=(Clientes)ilC.next();
            
            sql="insert into clientes (id,cod_client,razon_soci,domicilio,telefono_1,cond_vta,listadeprecio,numerodecuit,empresa,coeficiente,cupodecredito,saldo,saldoactual,tipo_iva) values ("+cli.getCodigoId()+",'"+cli.getCodigoCliente()+"','"+cli.getRazonSocial()+"','"+cli.getDireccion()+"','"+cli.getTelefono()+"',"+cli.getCondicionDeVenta()+","+cli.getListaDePrecios()+",'"+cli.getNumeroDeCuit()+"','"+cli.getEmpresa()+"',"+cli.getCoeficienteListaDeprecios()+","+cli.getCupoDeCredito()+","+cli.getSaldo()+","+cli.getSaldoActual()+",1)";
            //System.out.println("CLIENTES TANGO - "+sql);
            tt.guardarRegistro(sql);
        }
    }
    private static void numeroActualRecibo(){
        
        String sql="select * from tipocomprobantes where numero=11";
        rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
            numeroRecibo=rs.getInt("numeroActivo");
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void GuardarNumeroRecibo(){
        
        String sql="update tipocomprobantes set numeroActivo="+numeroRecibo+" where numero=11";
        tra.guardarRegistro(sql);
    }
    @Override
    public ArrayList listar(String cliente) {
        ArrayList ped=new ArrayList();
            Clientes cli=null;
            
            String sql="select id,idtransporte,clientes.fax,dentrega,clientes.direccionfantasia,(select condicionesiva.tipocomprobante from condicionesiva where condicionesiva.id=clientes.tipo_iva)as tipocomprobante,clientes.email,clientes.celular,clientes.COD_CLIENT,clientes.fantasia,clientes.RAZON_SOCI,clientes.DOMICILIO,clientes.COND_VTA,(clientes.LISTADEPRECIO)as NRO_LISTA,(select coeficienteslistas.coeficiente from coeficienteslistas where coeficienteslistas.id=clientes.listadeprecio)as descuento,(clientes.NUMERODECUIT)as IDENTIFTRI,clientes.empresa,clientes.TELEFONO_1,clientes.coeficiente,(clientes.CUPODECREDITO) AS CUPO_CREDI,clientes.saldo,clientes.TIPO_IVA,(select condicionesiva.descripcion from condicionesiva where id=clientes.tipo_iva)as tipo_iva2,(select localidades.localidad from localidades where id=clientes.localidad)as localidad1,clientes.responsable,(select localidades.codigo_postal from localidades where id=clientes.localidad)as postal from clientes where razon_soci like '%"+cliente+"%' or responsable like '%"+cliente+"%' or fantasia like '%"+cliente+"%' order by razon_soci";
            rs=tra.leerConjuntoDeRegistros(sql);
            try {
                while(rs.next()){
                  cli=new Clientes();
                cli.setCodigoId(rs.getInt("id"));
                cli.setCodigoCliente(rs.getString("id"));
                cli.setRazonSocial(rs.getString("RAZON_SOCI"));
                cli.setDireccion(rs.getString("DOMICILIO"));
                cli.setCondicionDeVenta(rs.getInt("COND_VTA"));
                cli.setListaDePrecios(rs.getInt("NRO_LISTA"));
                //Double descuento=Double.parseDouble(rs.getString("PORC_DESC"));
                cli.setDescuento(rs.getDouble("descuento"));
                //cli.setDescuento(descuento);
                cli.setNumeroDeCuit(rs.getString("IDENTIFTRI"));
                cli.setEmpresa(rs.getString("empresa"));
                cli.setCondicionIva(rs.getString("TIPO_IVA2"));
                cli.setTipoIva(rs.getInt("tipo_iva"));
                cli.setTelefono(rs.getString("TELEFONO_1"));
                cli.setLocalidad(rs.getString("localidad1"));
                cli.setCoeficienteListaDeprecios(rs.getDouble("coeficiente"));
                cli.setCupoDeCredito(rs.getDouble("CUPO_CREDI"));
                cli.setResponsable(rs.getString("responsable"));
                cli.setFantasia(rs.getString("fantasia"));
                cli.setCelular(rs.getString("celular"));
                cli.setFax(rs.getString("fax"));
                cli.setDireccionFantasia(rs.getString("direccionfantasia"));
                cli.setEmail(rs.getString("email"));
                cli.setTipoComprobante(rs.getInt("tipocomprobante"));
                cli.setDireccionDeEntrega(rs.getString("dentrega"));
                cli.setCodigoPostal(rs.getString("postal"));
                cli.setIdTransporte(rs.getInt("idtransporte"));
               // if(Inicio.usuario.getNivelDeAutorizacion()==1){
                System.out.println("ACTUALIZACION :"+Inicio.actualizacionesClientes); 
                ped.add(cli);
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ped;
    } 
        @Override
    public ArrayList listarPorContacto(String cliente) {
        ArrayList ped=new ArrayList();
            Clientes cli=null;
            
            String sql="select id,idtransporte,clientes.fax,clientes.direccionfantasia,(select condicionesiva.tipocomprobante from condicionesiva where condicionesiva.id=clientes.tipo_iva)as tipocomprobante,clientes.email,clientes.celular,clientes.COD_CLIENT,clientes.fantasia,clientes.RAZON_SOCI,clientes.DOMICILIO,clientes.COND_VTA,(clientes.LISTADEPRECIO)as NRO_LISTA,(select coeficienteslistas.coeficiente from coeficienteslistas where coeficienteslistas.id=clientes.listadeprecio)as descuento,(clientes.NUMERODECUIT)as IDENTIFTRI,clientes.empresa,clientes.TELEFONO_1,clientes.coeficiente,(clientes.CUPODECREDITO) AS CUPO_CREDI,clientes.saldo,clientes.TIPO_IVA,(select condicionesiva.descripcion from condicionesiva where id=clientes.tipo_iva)as tipo_iva2,(select localidades.localidad from localidades where id=clientes.localidad)as localidad1,clientes.responsable from clientes where responsable like '%"+cliente+"%' order by razon_soci";
            rs=tra.leerConjuntoDeRegistros(sql);
            try {
                while(rs.next()){
                  cli=new Clientes();
                cli.setCodigoId(rs.getInt("id"));
                cli.setCodigoCliente(rs.getString("id"));
                cli.setRazonSocial(rs.getString("RAZON_SOCI"));
                cli.setDireccion(rs.getString("DOMICILIO"));
                cli.setCondicionDeVenta(rs.getInt("COND_VTA"));
                cli.setListaDePrecios(rs.getInt("NRO_LISTA"));
                //Double descuento=Double.parseDouble(rs.getString("PORC_DESC"));
                cli.setDescuento(rs.getDouble("descuento"));
                //cli.setDescuento(descuento);
                cli.setNumeroDeCuit(rs.getString("IDENTIFTRI"));
                cli.setEmpresa(rs.getString("empresa"));
                cli.setCondicionIva(rs.getString("TIPO_IVA2"));
                cli.setTipoIva(rs.getInt("tipo_iva"));
                cli.setTelefono(rs.getString("TELEFONO_1"));
                cli.setLocalidad(rs.getString("localidad1"));
                cli.setCoeficienteListaDeprecios(rs.getDouble("coeficiente"));
                cli.setCupoDeCredito(rs.getDouble("CUPO_CREDI"));
                cli.setResponsable(rs.getString("responsable"));
                cli.setFantasia(rs.getString("fantasia"));
                cli.setCelular(rs.getString("celular"));
                cli.setFax(rs.getString("fax"));
                cli.setDireccionFantasia(rs.getString("direccionfantasia"));
                cli.setEmail(rs.getString("email"));
                cli.setTipoComprobante(rs.getInt("tipocomprobante"));
                cli.setIdTransporte(rs.getInt("idtransporte"));
               // if(Inicio.usuario.getNivelDeAutorizacion()==1){
                System.out.println("ACTUALIZACION :"+Inicio.actualizacionesClientes); 
                ped.add(cli);
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ped;
    }
        @Override
    public ArrayList listarPorFantasia(String cliente) {
        ArrayList ped=new ArrayList();
            Clientes cli=null;
            
            String sql="select id,idtransporte,clientes.fax,clientes.direccionfantasia,(select condicionesiva.tipocomprobante from condicionesiva where condicionesiva.id=clientes.tipo_iva)as tipocomprobante,clientes.email,clientes.celular,clientes.COD_CLIENT,clientes.fantasia,clientes.RAZON_SOCI,clientes.DOMICILIO,clientes.COND_VTA,(clientes.LISTADEPRECIO)as NRO_LISTA,(select coeficienteslistas.coeficiente from coeficienteslistas where coeficienteslistas.id=clientes.listadeprecio)as descuento,(clientes.NUMERODECUIT)as IDENTIFTRI,clientes.empresa,clientes.TELEFONO_1,clientes.coeficiente,(clientes.CUPODECREDITO) AS CUPO_CREDI,clientes.saldo,clientes.TIPO_IVA,(select condicionesiva.descripcion from condicionesiva where id=clientes.tipo_iva)as tipo_iva2,(select localidades.localidad from localidades where id=clientes.localidad)as localidad1,clientes.responsable from clientes where fantasia like '%"+cliente+"%' order by razon_soci";
            rs=tra.leerConjuntoDeRegistros(sql);
            try {
                while(rs.next()){
                  cli=new Clientes();
                cli.setCodigoId(rs.getInt("id"));
                cli.setCodigoCliente(rs.getString("id"));
                cli.setRazonSocial(rs.getString("RAZON_SOCI"));
                cli.setDireccion(rs.getString("DOMICILIO"));
                cli.setCondicionDeVenta(rs.getInt("COND_VTA"));
                cli.setListaDePrecios(rs.getInt("NRO_LISTA"));
                //Double descuento=Double.parseDouble(rs.getString("PORC_DESC"));
                cli.setDescuento(rs.getDouble("descuento"));
                //cli.setDescuento(descuento);
                cli.setNumeroDeCuit(rs.getString("IDENTIFTRI"));
                cli.setEmpresa(rs.getString("empresa"));
                cli.setCondicionIva(rs.getString("TIPO_IVA2"));
                cli.setTipoIva(rs.getInt("tipo_iva"));
                cli.setTelefono(rs.getString("TELEFONO_1"));
                cli.setLocalidad(rs.getString("localidad1"));
                cli.setCoeficienteListaDeprecios(rs.getDouble("coeficiente"));
                cli.setCupoDeCredito(rs.getDouble("CUPO_CREDI"));
                cli.setResponsable(rs.getString("responsable"));
                cli.setFantasia(rs.getString("fantasia"));
                cli.setCelular(rs.getString("celular"));
                cli.setFax(rs.getString("fax"));
                cli.setDireccionFantasia(rs.getString("direccionfantasia"));
                cli.setEmail(rs.getString("email"));
                cli.setTipoComprobante(rs.getInt("tipocomprobante"));
                cli.setIdTransporte(rs.getInt("idtransporte"));
               // if(Inicio.usuario.getNivelDeAutorizacion()==1){
                System.out.println("ACTUALIZACION :"+Inicio.actualizacionesClientes); 
                ped.add(cli);
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ped;
    }

    @Override
    public void marcarContactado(Integer item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void modificarDatosCliente(Object cliente) {
        Clientes cli=(Clientes)cliente;
        Boolean resultado=false;
        
        String sql="insert into clientes (COD_CLIENT,RAZON_SOCI,DOMICILIO,LOCALIDAD,TELEFONO_1,TIPO_IVA,IDENTIFTRI,COND_VTA,NRO_LISTA,empresa,coeficiente,idtransporte) values ('"+cli.getCodigoCliente()+"','"+cli.getRazonSocial()+"','"+cli.getDireccion()+"','SANTA FE','"+cli.getTelefono()+"',"+cli.getCondicionIva()+",'"+cli.getNumeroDeCuit()+"',1,2,'"+cli.getEmpresa()+"',"+cli.getCoeficienteListaDeprecios()+","+cli.getIdTransporte()+")";
        
        resultado=tra.guardarRegistro(sql);
        //return resultado;
    }

    @Override
    public ArrayList buscar(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList filtrar(String numeroCliente, String nombreCliente) {
        throw new UnsupportedOperationException("Not supported yet.");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean guardarNuevoCliente(Object cliente) {
        Clientes cli=(Clientes)cliente;
        Boolean resultado=false;
        
        String sql="insert into clientes (COD_CLIENT,RAZON_SOCI,DOMICILIO,TELEFONO_1,TIPO_IVA,NUMERODECUIT,COND_VTA,LISTADEPRECIO,empresa,cupodecredito,coeficiente,responsable,fantasia,celular,localidad,fax,direccionfantasia,email,dentrega,idtransporte) values ('"+cli.getCodigoCliente()+"','"+cli.getRazonSocial()+"','"+cli.getDireccion()+"','"+cli.getTelefono()+"','"+cli.getTipoIva()+"','"+cli.getNumeroDeCuit()+"',1,"+cli.getListaDePrecios()+",'"+cli.getEmpresa()+"',"+cli.getCupoDeCredito()+","+cli.getCoeficienteListaDeprecios()+",'"+cli.getResponsable()+"','"+cli.getFantasia()+"','"+cli.getCelular()+"','"+cli.getLocalidad()+"','"+cli.getFax()+"','"+cli.getDireccionFantasia()+"','"+cli.getEmail()+"','"+cli.getDireccionDeEntrega()+"',"+cli.getIdTransporte()+")";
        System.out.println(sql);
        resultado=tra.guardarRegistro(sql);
        cargarMap();
        return resultado;
    }

    @Override
    public Boolean modificarDatosDelCliente(Object cliente) {
        Clientes cli=(Clientes)cliente;
        Boolean resultado=false;
        
        
        //String sql="insert into clientes (COD_CLIENT,RAZON_SOCI,DOMICILIO,LOCALIDAD,TELEFONO_1,TIPO_IVA,IDENTIFTRI,COND_VTA,NRO_LISTA,empresa) values ('"+cli.getCodigoCliente()+"','"+cli.getRazonSocial()+"','"+cli.getDireccion()+"','SANTA FE','"+cli.getTelefono()+"',"+cli.getCondicionIva()+",'"+cli.getNumeroDeCuit()+"',1,1,'"+cli.getEmpresa()+"')";
        String sql="update clientes set RAZON_SOCI='"+cli.getRazonSocial()+"',idtransporte="+cli.getIdTransporte()+",listadeprecio="+cli.getListaDePrecios()+",DOMICILIO='"+cli.getDireccion()+"',TELEFONO_1='"+cli.getTelefono()+"',localidad='"+cli.getLocalidad()+"',responsable='"+cli.getResponsable()+"',numerodecuit='"+cli.getNumeroDeCuit()+"',tipo_iva="+cli.getTipoIva()+",cupodecredito="+cli.getCupoDeCredito()+",coeficiente="+cli.getCoeficienteListaDeprecios()+",fantasia='"+cli.getFantasia()+"',celular='"+cli.getCelular()+"',fax='"+cli.getFax()+"',direccionfantasia='"+cli.getDireccionFantasia()+"',email='"+cli.getEmail()+"',dentrega='"+cli.getDireccionDeEntrega()+"' where id ="+cli.getCodigoId();
        resultado=tra.guardarRegistro(sql);
        cargarMap();
        return resultado;
    }

    @Override
    public ArrayList listarClientes(String nombre) {
             ArrayList ped=new ArrayList();
            Clientes rs=null;
            
            nombre=nombre.toUpperCase();
            Enumeration<Clientes> elementos=listadoPorNom.elements();
            while(elementos.hasMoreElements()){
                rs=(Clientes)elementos.nextElement();
                Clientes cli=new Clientes();
                 int pos=rs.getRazonSocial().indexOf(nombre);
                if(pos==-1){
                    
                }else{
                cli=rs;
                //cli.setNumeroPedido(rs.getString(3));
                //cli.setObservaciones(rs.getString(5));
                //System.out.println("CLIENTE "+cli.getRazonSocial() +"COMENTARIO "+cli.getCodigoCliente());
                ped.add(cli);
                }
            }
            return ped;

    }

    @Override
    public Object cargarPorCodigoDeBarra(String codigoDeBarra) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer leerNumeroDeComprobanteSiguiente(Integer numeroComprobante) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object cargarPorCodigoAsignado(Integer id) {
        String sql="select *,(select condicionesiva.tipocomprobante from condicionesiva where condicionesiva.id=clientes.tipo_iva)as tipocomprobante,(select localidades.localidad from localidades where localidades.id=clientes.localidad)as localidadD,(select localidades.codigo_postal from localidades where localidades.id=clientes.localidad)as postal,(select condicionesiva.descripcion from condicionesiva where condicionesiva.id=clientes.tipo_iva)as tipocondicion from clientes where id="+id;
        String sql1="";
        Clientes cli=new Clientes();
        
        rs=tra.leerConjuntoDeRegistros(sql);
            try {
                while(rs.next()){
                    
                    cli.setCodigoId(rs.getInt("id"));
                    cli.setCodigoCliente(rs.getString("id"));
                    cli.setRazonSocial(rs.getString("RAZON_SOCI"));
                    cli.setDireccion(rs.getString("DOMICILIO"));
                    cli.setCondicionDeVenta(rs.getInt("COND_VTA"));
                    cli.setListaDePrecios(rs.getInt("listadeprecio"));
                    //Double descuento=Double.parseDouble(rs.getString("PORC_DESC"));
                    cli.setDescuento(rs.getDouble("coeficiente"));
                    //cli.setDescuento(descuento);
                    cli.setNumeroDeCuit(rs.getString("numerodecuit"));
                    cli.setEmpresa(rs.getString("empresa"));
                    cli.setCondicionIva(rs.getString("tipocondicion"));
                    cli.setTipoIva(rs.getInt("tipo_iva"));
                    cli.setTelefono(rs.getString("TELEFONO_1"));
                    cli.setLocalidad(rs.getString("localidadD"));
                    cli.setCodigoPostal(rs.getString("postal"));
                    cli.setCoeficienteListaDeprecios(rs.getDouble("coeficiente"));
                    cli.setCupoDeCredito(rs.getDouble("cupodecredito"));
                    cli.setResponsable(rs.getString("responsable"));
                    cli.setFantasia(rs.getString("fantasia"));
                    cli.setCelular(rs.getString("celular"));
                    cli.setFax(rs.getString("fax"));
                    cli.setTipoComprobante(rs.getInt("tipocomprobante"));
                    cli.setIdTransporte(rs.getInt("idtransporte"));
                    // if(Inicio.usuario.getNivelDeAutorizacion()==1){
                    System.out.println("ACTUALIZACION :"+Inicio.actualizacionesClientes);
                    
                    /*
                    }else{
                    cli.setSaldo(rs.getDouble("saldo"));
                    cli.setSaldoActual(rs.getDouble("saldo"));
                    }
                    */
                    //cli.setNumeroPedido(rs.getString(3));
                    //cli.setObservaciones(rs.getString(5));
                    //System.out.println("CLIENTE "+cli.getRazonSocial() +"COMENTARIO "+cli.getCodigoCliente());
                
                }
            } catch (SQLException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            return cli;
    }

    @Override
    public ArrayList ListarAPagar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList ListarACobrar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object ActualizarComprobante(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object PagarComprobante(Object objeto) {
       Comprobantes factProv=(Comprobantes)objeto;
       numeroActualRecibo();
       numeroRecibo++;
       String fech=Numeros.ConvertirFecha(Inicio.fechaVal);
       
       Double montt=factProv.getMontoTotal() * -1;
       String sql="insert into movimientosclientes (numeroProveedor,monto,numeroComprobante,idUsuario,tipoComprobante,idSucursal,idRemito) values ("+factProv.getCliente().getCodigoId()+","+montt+","+numeroRecibo+","+factProv.getUsuarioGenerador()+",11,"+factProv.getIdSucursal()+",0)";
       //String sql="update movimientosproveedores set pagado=1,numeroComprobante="+numeroRecibo+",idCaja="+Inicio.caja.getNumero()+",fechaPago='"+fech+"',idSucursal="+Inicio.sucursal.getNumero()+" where id="+factProv.getId();
       //System.out.println("VEAMOS "+sql);
       tra.guardarRegistro(sql);
       //String ttx="PAGO A PROVEEDOR "+factProv.getNombreProveedor();
       Double monto=factProv.getMontoTotal();
       sql="insert into movimientoscaja (numeroUsuario,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,idCliente,tipoCliente,pagado) value ("+Inicio.usuario.getNumeroId()+","+Inicio.sucursal.getNumero()+","+numeroRecibo+",6,"+monto+",13,"+Inicio.caja.getNumero()+","+factProv.getCliente().getCodigoId()+",1,1)";
       tra.guardarRegistro(sql);
       GuardarNumeroRecibo();
       return factProv;
    }

    @Override
    public void eliminar(Integer id) {
        String sql="delete from clientes where id="+id;
        tra.guardarRegistro(sql);
    }

    @Override
    public ArrayList ListarPorLocalidad(Integer idLocalidad) {
        ArrayList listadoP=new ArrayList();
        String sql="select *,(select condicionesiva.tipocomprobante from condicionesiva where condicionesiva.id=clientes.tipo_iva)as tipocomprobante,(select localidades.localidad from localidades where localidades.id=clientes.localidad)as localidadD,(select localidades.codigo_postal from localidades where localidades.id=clientes.localidad)as postal,(select condicionesiva.descripcion from condicionesiva where condicionesiva.id=clientes.tipo_iva)as tipocondicion from clientes where localidad="+idLocalidad;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        Clientes cli;
            try {
                while(rs.next()){
                    cli=new Clientes();
                    cli.setCodigoId(rs.getInt("id"));
                    cli.setCodigoCliente(rs.getString("id"));
                    cli.setRazonSocial(rs.getString("RAZON_SOCI"));
                    cli.setDireccion(rs.getString("DOMICILIO"));
                    cli.setCondicionDeVenta(rs.getInt("COND_VTA"));
                    cli.setListaDePrecios(rs.getInt("listadeprecio"));
                    //Double descuento=Double.parseDouble(rs.getString("PORC_DESC"));
                    cli.setDescuento(rs.getDouble("coeficiente"));
                    //cli.setDescuento(descuento);
                    cli.setNumeroDeCuit(rs.getString("numerodecuit"));
                    cli.setEmpresa(rs.getString("empresa"));
                    cli.setCondicionIva(rs.getString("tipocondicion"));
                    cli.setTipoIva(rs.getInt("tipo_iva"));
                    cli.setTelefono(rs.getString("TELEFONO_1"));
                    cli.setLocalidad(rs.getString("localidadD"));
                    cli.setCodigoPostal(rs.getString("postal"));
                    cli.setCoeficienteListaDeprecios(rs.getDouble("coeficiente"));
                    cli.setCupoDeCredito(rs.getDouble("cupodecredito"));
                    cli.setResponsable(rs.getString("responsable"));
                    cli.setFantasia(rs.getString("fantasia"));
                    cli.setCelular(rs.getString("celular"));
                    cli.setFax(rs.getString("fax"));
                    cli.setTipoComprobante(rs.getInt("tipocomprobante"));
                    cli.setIdTransporte(rs.getInt("idtransporte"));
                    listadoP.add(cli);
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listadoP;
    }
        
}
