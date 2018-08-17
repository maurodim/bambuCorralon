/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reparto;


import Clientes.Objectos.Clientes;
import Reparto.interfaces.Editables;
import Reparto.interfaces.ExportacionDePedidos;
import Reparto.interfaces.Procesos;
import interfaces.Transaccionable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import objetos.Conecciones;
import tablas.miTablaModificacion;

/**
 *
 * @author USUARIO
 */
public class PedidosParaReparto implements Editables,ExportacionDePedidos,Procesos{
	/*
	 * ACA SOLAMENTE GENERO EL OBJETO PEDIDO CON LOS DATOS ESENCIALES PARA TRABAJAR LAS 
	 * PRIMERAS PANTALLAS SIN CARGA DE CALCULOS
	 * EL IDPEDIDO DEBE SER UNICO PARA CADA PEDIDO ENVIADO, EL CALCULO DEL PESO Y EL DESDOBLAMIENTO 
	 * DE LA INFORMACION EN LAS DISTINTAS BASES QUE LO HAGA EL SISTEMA DE EXPORTACION DE PEDIDOS
	 * 
	 * TENER EN CUENTA QUE LA GRILLA INICIAL VA A LISTAR ESTOS POR FECHA Y LOS VA A TRABAJAR MODIFICANDO SU CARGA
	 * LA VARIABLE ENTREGACOMPELTADA ES PARA DETERMINAR SI TIENE MATERIALES PENDIENTES
	 * ESE PEDIDO ( PARA REENVIARLO SE GENERA UN NUEVO IDPEDIDO PERO SE 
	 * MANTIENE EL NUMERO DE PEDIDO DE TANGO
     * PARA GENERAR LA INDEPENDENCIA DE ESTE SISTEMA A TANGO Y MANEJARLO  EN FORMA DISTINTA
	 */
	private Integer iDPedido;
	private String codigoTangoDePedido;
	private String razonSocial;
        private String codigoCliente;
	private Double pesoTotal;
	private String fechaEnvio;
	private Integer vehiculoAsignado;
	private Boolean entregaCompletada;
	private Connection coneccionPedidos=null;
	private Integer numeroDeProceso;
        private Boolean confirmacionPorceso;
        private Integer codigoDeposito;
        private String codigoArticulo;
        private String descripcionArticulo;
        private Double cantidadArticulo;
        private Double cantidadArticuloPendiente;
        private Double cantidadArticulosTotales;
        private Double pesoItems;
        private Double saldoCliente;
        private Integer condicionDeVenta;
        private Integer condicionEstadoDelPedido;
        private int estado;
        private Integer numeroDeListadoDeMateriales;
        private Integer numeroDeHojaDeRuta;
        private String fechaPedidosTango;
        private String observaciones;
        private String observaciones1;
        private String observaciones2;
        private Date fechaActualizacionSaldoCliente;
        private String numeroComprobante;
        private String saldoACobrar;
        private String vuelto;
        private String empresa;
        private Integer numeroDeFletero;
        private Boolean verificacion;
        private int zonaAsignada;
        private int alertaAsignada;
        private String zonaDescripcion;
        private String alertaDescripcion;
        private Integer numeroDeRevisionDeListado;
        private Integer numeroVendedor;
        private String nombreVendedor;
        private int verificadorRevision;
        private int vehiculoAnterior;
        private Integer idPedidoEnTango;
        private int notificado;

    public int getNotificado() {
        return notificado;
    }

    public void setNotificado(int notificado) {
        this.notificado = notificado;
    }
        
        

    public Integer getIdPedidoEnTango() {
        return idPedidoEnTango;
    }

    public void setIdPedidoEnTango(Integer idPedidoEnTango) {
        this.idPedidoEnTango = idPedidoEnTango;
    }
        

    public int getVehiculoAnterior() {
        return vehiculoAnterior;
    }

    public void setVehiculoAnterior(int vehiculoAnterior) {
        this.vehiculoAnterior = vehiculoAnterior;
    }
        

    public int getVerificadorRevision() {
        return verificadorRevision;
    }

    public void setVerificadorRevision(int verificadorRevision) {
        this.verificadorRevision = verificadorRevision;
    }
        

    public Integer getNumeroVendedor() {
        return numeroVendedor;
    }

    public void setNumeroVendedor(Integer numeroVendedor) {
        this.numeroVendedor = numeroVendedor;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public Integer getNumeroDeRevisionDeListado() {
        return numeroDeRevisionDeListado;
    }

    public void setNumeroDeRevisionDeListado(Integer numeroDeRevisionDeListado) {
        this.numeroDeRevisionDeListado = numeroDeRevisionDeListado;
    }

        
    public String getZonaDescripcion() {
        return zonaDescripcion;
    }

    public void setZonaDescripcion(String zonaDescripcion) {
        this.zonaDescripcion = zonaDescripcion;
    }

    public String getAlertaDescripcion() {
        return alertaDescripcion;
    }

    public void setAlertaDescripcion(String alertaDescripcion) {
        this.alertaDescripcion = alertaDescripcion;
    }
        

    public int getZonaAsignada() {
        return zonaAsignada;
    }

    public void setZonaAsignada(int zonaAsignada) {
        this.zonaAsignada = zonaAsignada;
    }

    public int getAlertaAsignada() {
        return alertaAsignada;
    }

    public void setAlertaAsignada(int alertaAsignada) {
        this.alertaAsignada = alertaAsignada;
    }
        

    public Boolean getVerificacion() {
        return verificacion;
    }

    public void setVerificacion(Boolean verificacion) {
        this.verificacion = verificacion;
    }
        

    public Integer getNumeroDeFletero() {
        return numeroDeFletero;
    }

    public void setNumeroDeFletero(Integer numeroDeFletero) {
        this.numeroDeFletero = numeroDeFletero;
    }
        
    public String getObservaciones1() {
        return observaciones1;
    }

    public void setObservaciones1(String observaciones1) {
        this.observaciones1 = observaciones1;
    }

    public String getObservaciones2() {
        return observaciones2;
    }

    public void setObservaciones2(String observaciones2) {
        this.observaciones2 = observaciones2;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
        

    public String getSaldoACobrar() {
        return saldoACobrar;
    }

    public void setSaldoACobrar(String saldoACobrar) {
        this.saldoACobrar = saldoACobrar;
    }

    public String getVuelto() {
        return vuelto;
    }

    public void setVuelto(String vuelto) {
        this.vuelto = vuelto;
    }
        

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

        
    public Date getFechaActualizacionSaldoCliente() {
        return fechaActualizacionSaldoCliente;
    }

    public void setFechaActualizacionSaldoCliente(Date fechaActualizacionSaldoCliente) {
        this.fechaActualizacionSaldoCliente = fechaActualizacionSaldoCliente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
        

        
    public String getFechaPedidosTango() {
        return fechaPedidosTango;
    }

    public void setFechaPedidosTango(String fechaPedidosTango) {
        this.fechaPedidosTango = fechaPedidosTango;
    }

        
        
    public Integer getNumeroDeHojaDeRuta() {
        return numeroDeHojaDeRuta;
    }

    public void setNumeroDeHojaDeRuta(Integer numeroDeHojaDeRuta) {
        this.numeroDeHojaDeRuta = numeroDeHojaDeRuta;
    }

    public Integer getNumeroDeListadoDeMateriales() {
        return numeroDeListadoDeMateriales;
    }

    public void setNumeroDeListadoDeMateriales(Integer numeroDeListadoDeMateriales) {
        this.numeroDeListadoDeMateriales = numeroDeListadoDeMateriales;
    }
        
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
        
    public Integer getCondicionEstadoDelPedido() {
        return condicionEstadoDelPedido;
    }

    public void setCondicionEstadoDelPedido(Integer condicionEstadoDelPedido) {
        this.condicionEstadoDelPedido = condicionEstadoDelPedido;
    }

    public Double getCantidadArticulosTotales() {
        return cantidadArticulosTotales;
    }

    public void setCantidadArticulosTotales(Double cantidadArticulosEntregados) {
        this.cantidadArticulosTotales = cantidadArticulosEntregados;
    }

        
    public Double getCantidadArticuloPendiente() {
        return cantidadArticuloPendiente;
    }

    public void setCantidadArticuloPendiente(Double cantidadArticuloPendiente) {
        this.cantidadArticuloPendiente = cantidadArticuloPendiente;
    }
   
    public Integer getCondicionDeVenta() {
        return condicionDeVenta;
    }

    public void setCondicionDeVenta(Integer condicionDeVenta) {
        this.condicionDeVenta = condicionDeVenta;
    }
        
    public Double getSaldoCliente() {
        return saldoCliente;
    }

    public void setSaldoCliente(Double saldoCliente) {
        this.saldoCliente = saldoCliente;
    }
        

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

        
    public Double getCantidadArticulo() {
        return cantidadArticulo;
    }

    public void setCantidadArticulo(Double cantidadArticulo) {
        this.cantidadArticulo = cantidadArticulo;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public Double getPesoItems() {
        return pesoItems;
    }

    public void setPesoItems(Double pesoItems) {
        this.pesoItems = pesoItems;
    }
        

    public Integer getCodigoDeposito() {
        return codigoDeposito;
    }

    public void setCodigoDeposito(Integer codigoDeposito) {
        this.codigoDeposito = codigoDeposito;
    }

    public Connection getConeccionPedidos() {
        return coneccionPedidos;
    }

    public void setConeccionPedidos(Connection coneccionPedidos) {
        this.coneccionPedidos = coneccionPedidos;
    }

    public Boolean getConfirmacionPorceso() {
        return confirmacionPorceso;
    }

    public void setConfirmacionPorceso(Boolean confirmacionPorceso) {
        this.confirmacionPorceso = confirmacionPorceso;
    }

    public Integer getNumeroDeProceso() {
        return numeroDeProceso;
    }

    public void setNumeroDeProceso(Integer numeroDeProceso) {
        this.numeroDeProceso = numeroDeProceso;
    }
        
	public Boolean getEntregaCompletada() {
		return entregaCompletada;
	}

	public void setEntregaCompletada(Boolean entregaCompletada) {
		this.entregaCompletada = entregaCompletada;
	}
	
	public String getCodigoTangoDePedido() {
		return codigoTangoDePedido;
	}

	public void setCodigoTangoDePedido(String codigoTangoDePedido) {
		this.codigoTangoDePedido = codigoTangoDePedido;
	}

	public String getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Integer getiDPedido() {
		return iDPedido;
	}

	public void setiDPedido(Integer iDPedido) {
		this.iDPedido = iDPedido;
	}

	public Double getPesoTotal() {
		return pesoTotal;
	}

	public void setPesoTotal(Double pesoTotal) {
            //DecimalFormat dc=new DecimalFormat("####.#");
            //String peso=dc.format(pesoTotal);
            //this.pesoTotal=Double.parseDouble(peso);
            this.pesoTotal = pesoTotal;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public Integer getVehiculoAsignado() {
		return vehiculoAsignado;
	}

	public void setVehiculoAsignado(Integer vehiculoAsignado) {
		this.vehiculoAsignado = vehiculoAsignado;
	}

    public PedidosParaReparto() {
         
       // coneccionPedidos=Coneccion.ObtenerConeccion();
        this.empresa = "";
        this.saldoCliente=0.00;
    }
    private Boolean enviarPedido(ArrayList lista){
        Boolean verificador=false; 
        
            
            PedidosParaReparto ped=new PedidosParaReparto();
            Iterator il=lista.listIterator();
            Transaccionable conR=new Conecciones();
            ArrayList numeroParaEliminar=new ArrayList();
            
            String sql="";
            int i=0;
            while(il.hasNext()){
               
                    ped=(PedidosParaReparto)il.next();
                    
                    //System.err.println(" proceso "+ped.getMarcadoParaProceso()+" reparto "+ped.getMarcadoParaReparto()+" pend "+ped.getCantidadArticuloPendiente());
                        
                        
                         
                        
                            
                        sql="insert into pedidos_carga1 (NRO_PEDIDO,FEC_PEDIDO,COD_CLIENT,RAZON_SOC,COND_VENTA,LEYENDA_1,LEYENDA_2,LEYENDA_3,COD_ARTIC,DESC_ARTIC,CANT_PEDID,CANT_FACT,CANT_DESC,entrega,reparto,peso,TALON_PEDI,numeroOriginal,zona,alerta,COD_VENDED,ID_GVA03,notificado) values ('"+ped.getCodigoTangoDePedido()+"','"+ped.getFechaPedidosTango()+"','"+ped.getCodigoCliente()+"','"+ped.getRazonSocial()+"',"+ped.getCondicionDeVenta()+",'"+ped.getObservaciones()+"','"+ped.getObservaciones1()+"','"+ped.getObservaciones2()+"','"+ped.getCodigoArticulo()+"','"+ped.getDescripcionArticulo()+"',"+ped.getCantidadArticulo()+","+ped.getCantidadArticuloPendiente()+","+ped.getCantidadArticulosTotales()+",'"+ped.getFechaEnvio()+"',1,0.00"+",'BU',1,"+ped.getZonaAsignada()+","+ped.getAlertaAsignada()+","+ped.getNumeroVendedor()+","+ped.getIdPedidoEnTango()+",0)";
                        System.out.println(sql);
                        conR.guardarRegistro(sql);
                        //System.out.println(sql);
                            //idPedido=ped.getiDPedido();
                        
                        
                        
                        

                        
                    
                 
                   // EditorDePedidos.jProgressBar1.setValue(i);
                
                verificador=true;
            }
            //eliminarItems(numeroParaEliminar);            
            return verificador;
        
    }
    private ArrayList ListarPedidosPorFecha(String fechEnt) throws SQLException, ClassNotFoundException{
	/*
         * ACA SE GENERA UN ARRAY CON TODOS LOS OBJETOS PEDIDOS QUE CORRESPONDEN SER ENTREGADOS
         * EN LA FECHA PUNTUALIZADA. POR LO TANTO SE PUEDE VOLCAR A LA TABLA PARA SU SELECCION
         * 
         */
          //      Connection cp=cn.ObtenerConeccion();
          ArrayList <PedidosParaReparto> listaPed=new ArrayList();
		String sql="select *,sum(pedidos_carga1.peso * pedidos_carga1.CANT_PEDID) as total,(select zonas.descripcion from zonas where zonas.numero=pedidos_carga1.zona)as zonasDescripcion,(select vendedores.nombre from vendedores where vendedores.id=pedidos_carga1.COD_VENDED)as vendedor from pedidos_carga1 where entrega like '"+fechEnt+"%'and reparto=1 group by NRO_PEDIDO order by RAZON_SOC";
		System.out.println(sql);
                Transaccionable tra=new Conecciones();
                //PreparedStatement st=cp.prepareStatement(sql);
                //st.execute(sql);
		ResultSet rs=tra.leerConjuntoDeRegistros(sql);
		//synchronized rs;
                while(rs.next()){
			PedidosParaReparto pedidos=new PedidosParaReparto();
                        Clientes clie=new Clientes();
			pedidos.setiDPedido(rs.getInt("numero"));
			pedidos.setRazonSocial(rs.getString("RAZON_SOC"));
			pedidos.setCodigoTangoDePedido(rs.getString("NRO_PEDIDO"));
			pedidos.setVehiculoAsignado(rs.getInt("vehiculo"));
			pedidos.setPesoTotal(rs.getDouble("total"));
                        pedidos.setCodigoArticulo(rs.getString("COD_ARTIC"));
                        pedidos.setDescripcionArticulo(rs.getString("DESC_ARTIC")+" "+rs.getString("DESC_ADIC"));
                        pedidos.setPesoItems(rs.getDouble("peso")* rs.getDouble("CANT_PEDID"));
                        //pedidos.setPesoTotal(rs.getDouble("peso")* rs.getDouble("CANT_PEDID"));
                        pedidos.setCantidadArticulo(rs.getDouble("CANT_PEDID"));
			pedidos.setCodigoCliente(rs.getString("COD_CLIENT"));
                        pedidos.setFechaEnvio(rs.getString("entrega"));
                        //pedidos.setFechaActualizacionSaldoCliente(rs.getDate("act"));
                        pedidos.setNumeroDeListadoDeMateriales(rs.getInt("listado"));
                        pedidos.setNumeroDeRevisionDeListado(rs.getInt("revision"));
                        pedidos.setNumeroDeHojaDeRuta(rs.getInt("hdr1"));
                        pedidos.setNumeroDeProceso(rs.getInt("orden_num"));
                        pedidos.setNumeroDeFletero(rs.getInt("fletero"));
                        pedidos.setNumeroComprobante(rs.getString("N_REMITO"));
                        pedidos.setEmpresa(rs.getString("TALON_PEDI"));
                        pedidos.setVerificadorRevision(rs.getInt("revisionado"));
                        pedidos.setVehiculoAnterior(rs.getInt("vehiculoAnterior"));
                        pedidos.setIdPedidoEnTango(rs.getInt("ID_GVA03"));
                        pedidos.setObservaciones(rs.getString("LEYENDA_1"));
                        pedidos.setObservaciones1(rs.getString("LEYENDA_2"));
                        pedidos.setObservaciones2(rs.getString("LEYENDA_3"));
                        pedidos.setFechaPedidosTango(rs.getString("FEC_PEDIDO"));
                        //pedidos.setSaldoACobrar(rs.getDouble("saldo"));
                        clie.setCodigoCliente(pedidos.getCodigoCliente());
                        clie.setRazonSocial(pedidos.getRazonSocial());
                        clie.setEmpresa(pedidos.getEmpresa());
                        //ACA TENDRIA QUE HACER UNA INTERFAZ PAR QUE ME BUSQUE Y ACTUALICE LOS SALDOS
                        
                        //Iterator iSc=SiderconCapaatos.saldoCliente.listIterator();
                        Double sald=0.00;
                        //Clientes cli=new Clientes();
                        //Actualizable actCli=new Clientes();
                        
                        //while(iSc.hasNext()){
                          //  cli=(Clientes)iSc.next();
                        
                        pedidos.setSaldoCliente(sald);
                        sald=0.00;
                        pedidos.setNumeroVendedor(rs.getInt("COD_VENDED"));
                        pedidos.setNombreVendedor(rs.getString("vendedor"));
                        System.err.println(" numero v"+pedidos.getNumeroVendedor()+" nombre v "+pedidos.getNombreVendedor()+" cliente "+pedidos.getRazonSocial()+" saldo "+pedidos.getSaldoCliente());
                        String pendiente=String.valueOf(rs.getDouble("CANT_FACT"));
                        
                        Double articulosPendientes=0.00;
                        if(pendiente==null){
                            
                        }else{
                            articulosPendientes=Double.parseDouble(pendiente);
                        }
                        //pedidos.setCantidadArticulosEntregados(articulosPendientes);
                        //articulosPendientes=pedidos.getCantidadArticulo()- pedidos.getCantidadArticulosEntregados();
                        pedidos.setCantidadArticuloPendiente(articulosPendientes);
                        pedidos.setZonaAsignada(rs.getInt("zona"));
                        pedidos.setAlertaAsignada(0);
                        pedidos.setNotificado(rs.getInt("notificado"));
                        if(pedidos.getZonaAsignada() <=1){
                            pedidos.setZonaDescripcion("SANTA FE");
                        }else{
                             pedidos.setZonaDescripcion(rs.getString("zonasDescripcion"));
                        }
                        if(pedidos.getAlertaAsignada() > 0){
                         pedidos.setAlertaDescripcion(rs.getString("alertasDescripcion"));
                        }
                        
                        listaPed.add(pedidos);
		}
                rs.close();
                //ActualizarDatosPedidos act=new ActualizarDatosPedidos();
                //act.setPedidos(listaPed);
                //act.start();
            //    cn.CerrarConneccion(cp);
		return listaPed;
		
	}
    private ArrayList GuardarPedidosPorVehiculo(int vehiculo,ArrayList listaPed){
        ArrayList<PedidosParaReparto> listaUnidad=new ArrayList();
		//listaUnidad=pedido;
		Iterator iterador=listaPed.iterator();
                String sql;
                String saldo;
                
                
                
                
                
		while(iterador.hasNext()){
			PedidosParaReparto ped=(PedidosParaReparto)iterador.next();
			if(vehiculo==ped.getVehiculoAsignado()){
                            System.out.println("NOMBRE PEDIDO LISTADO "+ped.getRazonSocial());
                            if(ped.getSaldoCliente()==0.00){
                                saldo="PS";
                            }else{
                                saldo=String.valueOf(ped.getSaldoCliente());
                            }
                            
                            sql="update pedidos_carga1 set saldoCliente='"+saldo+"' where NRO_PEDIDO='%"+ped.getCodigoTangoDePedido()+"' and entrega='"+ped.getFechaEnvio()+"'";
                            /*
                            try {
                                st.executeUpdate(sql);
                                System.out.println("Actualizacion de saldos "+sql);
                            } catch (SQLException ex) {
                                Logger.getLogger(Procesos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            */
                            System.out.println(ped.getObservaciones()+"-"+ped.getObservaciones1());
				listaUnidad.add(ped);
			}
		}
		return listaUnidad;
    }
    private ArrayList listadoPedidosPorZona(String fechEnt,int zonaNumero){
            	/*
         * ACA SE GENERA UN ARRAY CON TODOS LOS OBJETOS PEDIDOS QUE CORRESPONDEN SER ENTREGADOS
         * EN LA FECHA PUNTUALIZADA. POR LO TANTO SE PUEDE VOLCAR A LA TABLA PARA SU SELECCION
         * 
         */
          //      Connection cp=cn.ObtenerConeccion();
		String sql=null;
                if(zonaNumero > 1)sql="select *,(select TABLA1.actualizacion from TABLA1 where TABLA1.COD_CLI=pedidos_carga1.COD_CLIENT group by TABLA1.COD_CLI)as act,sum(pedidos_carga1.peso * pedidos_carga1.CANT_PEDID) as total,(select zonas.descripcion from zonas where zonas.numero=pedidos_carga1.zona)as zonasDescripcion,(select alertas.descripcion from alertas where alertas.numero=pedidos_carga1.alerta)as alertasDescripcion from pedidos_carga1 where entrega like '"+fechEnt+"%'and reparto=1 and zona="+zonaNumero+" group by NRO_PEDIDO order by RAZON_SOC";
                if(zonaNumero ==1)sql="select *,(select TABLA1.actualizacion from TABLA1 where TABLA1.COD_CLI=pedidos_carga1.COD_CLIENT group by TABLA1.COD_CLI)as act,sum(pedidos_carga1.peso * pedidos_carga1.CANT_PEDID) as total,(select zonas.descripcion from zonas where zonas.numero=pedidos_carga1.zona)as zonasDescripcion,(select alertas.descripcion from alertas where alertas.numero=pedidos_carga1.alerta)as alertasDescripcion from pedidos_carga1 where entrega like '"+fechEnt+"%'and reparto=1 and zona=0 group by NRO_PEDIDO order by RAZON_SOC";
		System.out.println(sql);
                ArrayList <PedidosParaReparto> listaPed=new ArrayList();
               Transaccionable tra=new Conecciones();
                //st.execute(sql);
		ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            try {
                //synchronized rs;
                while(rs.next()){
                    PedidosParaReparto pedidos=new PedidosParaReparto();
                    pedidos.setiDPedido(rs.getInt("numero"));
                    pedidos.setRazonSocial(rs.getString("RAZON_SOC"));
                    pedidos.setCodigoTangoDePedido(rs.getString("NRO_PEDIDO"));
                    pedidos.setVehiculoAsignado(rs.getInt("vehiculo"));
                    pedidos.setPesoTotal(rs.getDouble("peso")* rs.getDouble("CANT_PEDID"));
                    pedidos.setCodigoArticulo(rs.getString("COD_ARTIC"));
                    pedidos.setDescripcionArticulo(rs.getString("DESC_ARTIC")+" "+rs.getString("DESC_ADIC"));
                    pedidos.setPesoItems(rs.getDouble("peso")* rs.getDouble("CANT_PEDID"));
                    pedidos.setCantidadArticulo(rs.getDouble("CANT_PEDID"));
                    pedidos.setCodigoCliente(rs.getString("COD_CLIENT"));
                    pedidos.setFechaEnvio(rs.getString("entrega"));
                    pedidos.setFechaActualizacionSaldoCliente(rs.getDate("act"));
                    pedidos.setNumeroDeListadoDeMateriales(rs.getInt("listado"));
                    pedidos.setNumeroDeHojaDeRuta(rs.getInt("hdr1"));
                    pedidos.setNumeroDeProceso(rs.getInt("orden_num"));
                    pedidos.setNumeroDeFletero(rs.getInt("fletero"));
                    pedidos.setNumeroComprobante(rs.getString("N_REMITO"));
                    
                    String pendiente=String.valueOf(rs.getDouble("CANT_FACT"));
                    
                    Double articulosPendientes=0.00;
                    if(pendiente==null){
                        
                    }else{
                        articulosPendientes=Double.parseDouble(pendiente);
                    }
                    //pedidos.setCantidadArticulosEntregados(articulosPendientes);
                    //articulosPendientes=pedidos.getCantidadArticulo()- pedidos.getCantidadArticulosEntregados();
                    pedidos.setCantidadArticuloPendiente(articulosPendientes);
                    pedidos.setZonaAsignada(rs.getInt("zona"));
                    pedidos.setAlertaAsignada(rs.getInt("alerta"));
                    if(pedidos.getZonaAsignada() <=1){
                        pedidos.setZonaDescripcion("SANTA FE");
                    }else{
                        pedidos.setZonaDescripcion(rs.getString("zonasDescripcion"));
                    }
                    if(pedidos.getAlertaAsignada() > 0){
                        pedidos.setAlertaDescripcion(rs.getString("alertasDescripcion"));
                    }
                    listaPed.add(pedidos);
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PedidosParaReparto.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                //ActualizarDatosPedidos act=new ActualizarDatosPedidos();
                //act.setPedidos(listaPed);
                //act.start();
            //    cn.CerrarConneccion(cp);
		return listaPed;
		

        }
    private void guardadoDeAsignacionDeVehiculos(ArrayList pedidosTrabajados){
            PedidosParaReparto pedidos=new PedidosParaReparto();
            
            //String fechaMod=String.valueOf(ultimoNumeroDeListado);
            
            
            String ttx="-- guardarAsignacionDeVehiculos - function - Clase Procesos -\r\n";
           // Connection cp=cn.ObtenerConeccion();
            Iterator ii=pedidosTrabajados.listIterator();
            String sql="";
            Transaccionable tra=new Conecciones();
            while(ii.hasNext()){
                pedidos=(PedidosParaReparto) ii.next();
                if(pedidos.getVehiculoAnterior()==0){
                sql="update pedidos_carga1 set vehiculo="+pedidos.getVehiculoAsignado()+",vehiculoAnterior="+pedidos.getVehiculoAsignado()+" where NRO_PEDIDO like '%"+pedidos.getCodigoTangoDePedido()+"%' and entrega like '"+pedidos.getFechaEnvio()+"%'";
                }else{
                    
                sql="update pedidos_carga1 set vehiculo="+pedidos.getVehiculoAsignado()+",vehiculoAnterior="+pedidos.getVehiculoAnterior()+" where NRO_PEDIDO like '%"+pedidos.getCodigoTangoDePedido()+"%' and entrega like '"+pedidos.getFechaEnvio()+"%'";                    
                }
                ttx+=sql+"\r\n";
                tra.guardarRegistro(sql);                
                
            }
            System.err.println(ttx);
            
            //gArch.registrarMovimiento(ttx, "bdMv.txt","130506");
          //  cn.CerrarConneccion(cp);
        }
    private Integer leerHdrVehiculo(int idUnidad,String fecha){
            String sql=null;
            Integer uni=0;
                sql="select hdr.numero from hdr where numeroVehiculo="+idUnidad+" and fechaEntrega like '"+fecha+"%'";
                Transaccionable tra=new Conecciones();
                ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            try {
                while(rs.next()){
                    uni=rs.getInt(1);
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PedidosParaReparto.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            
            
            return uni;
        }
    private ArrayList leerDetallePedidoParaCorreccion(String numeroPedido,String fecha){
            //System.out.println(numeroPedido+" fecha "+fecha);
          //  Connection cp=cn.ObtenerConeccion();
            ArrayList <PedidosParaReparto> detalles=new ArrayList();
            String sql="select *,(SELECT vendedores.nombre FROM vendedores WHERE vendedores.id = pedidos_carga1.COD_VENDED) AS vendedor from pedidos_carga1 where NRO_PEDIDO like '%"+numeroPedido+"%' and entrega like '"+fecha+"%' and reparto=1";
            PreparedStatement st=null;
            Transaccionable tra=new Conecciones();
            ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            String fechaT=null;
            try {
                while(rs.next()){
                    PedidosParaReparto pedido=new PedidosParaReparto();
                    pedido.setCodigoTangoDePedido(numeroPedido);
                    pedido.setCodigoArticulo(rs.getString("COD_ARTIC"));
                    pedido.setDescripcionArticulo(rs.getString("DESC_ARTIC"));
                    pedido.setCantidadArticulo(rs.getDouble("CANT_PEDID"));
                    pedido.setCantidadArticuloPendiente(rs.getDouble("CANT_FACT"));
                    if(pedido.getCantidadArticuloPendiente()==null){
                        pedido.setCantidadArticuloPendiente(0.00);
                    }
                    pedido.setNumeroDeListadoDeMateriales(rs.getInt("listado"));
                    pedido.setNumeroDeRevisionDeListado(rs.getInt("revision"));
                    pedido.setNombreVendedor(rs.getString("vendedor"));
                    pedido.setFechaEnvio(rs.getString("entrega"));
                    pedido.setiDPedido(rs.getInt("numero"));
                    pedido.setEmpresa(rs.getString("TALON_PEDI"));
                    pedido.setVehiculoAsignado(rs.getInt("vehiculo"));
                    pedido.setVehiculoAnterior(rs.getInt("vehiculoAnterior"));
                    pedido.setPesoTotal(rs.getDouble("peso"));
                    pedido.setIdPedidoEnTango(rs.getInt("ID_GVA03"));
                    pedido.setObservaciones(rs.getString("LEYENDA_1"));
                    pedido.setObservaciones1(rs.getString("LEYENDA_2"));
                    pedido.setObservaciones2(rs.getString("LEYENDA_3"));
                    fechaT=rs.getString("FEC_PEDIDO").substring(0,10);
                    pedido.setNotificado(rs.getInt("notificado"));
                    pedido.setFechaPedidosTango(fechaT);
                    System.out.println("pedido :"+numeroPedido+" fecha"+fecha+" articulo: "+pedido.getDescripcionArticulo());
                    detalles.add(pedido);
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PedidosParaReparto.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          //  cn.CerrarConneccion(cp);
            return detalles;
        }
    
    
    
    
    
    @Override
    public ArrayList ListarDetalleDePedidos(String codigoPedido) {
            
                Transaccionable conR=new Conecciones();
                ArrayList listaPed=new ArrayList();
                try {
                String sql="select *,(select zonas.descripcion from zonas where zonas.numero=pedidos_carga1.zona)as zonasDescripcion,(select alertas.descripcion from alertas where alertas.numero=pedidos_carga1.alerta)as alertasDescripcion,(select saldosclientesact.saldo from saldosclientesact where saldosclientesact.RAZON_SOC like 'pedidos_carga1.RAZON_SOC%' and saldosclientesact.COD_CLI like 'pedidos_carga1.COD_CLIENT%')as saldo,(select vendedores.nombre from vendedores where vendedores.id=pedidos_carga1.COD_VENDED)as vendedor from pedidos_carga1 where NRO_PEDIDO like '%"+codigoPedido+"'and reparto=1";
                System.out.println(sql);
                
               
                //st.execute(sql);
                ResultSet rs=conR.leerConjuntoDeRegistros(sql);
                //ChequearCantidadesPedidos chp=new Clientes();
                //synchronized rs;
                while(rs.next()){
                    PedidosParaReparto pedidos=new PedidosParaReparto();
                    Clientes clie=new Clientes();
                    pedidos.setiDPedido(rs.getInt("numero"));
                    pedidos.setRazonSocial(rs.getString("RAZON_SOC"));
                    pedidos.setCodigoTangoDePedido(rs.getString("NRO_PEDIDO"));
                    pedidos.setVehiculoAsignado(rs.getInt("vehiculo"));
                    //pedidos.setPesoTotal(rs.getDouble("total"));
                    pedidos.setCodigoArticulo(rs.getString("COD_ARTIC"));
                    pedidos.setDescripcionArticulo(rs.getString("DESC_ARTIC")+" "+rs.getString("DESC_ADIC"));
                    pedidos.setPesoItems(rs.getDouble("peso")* rs.getDouble("CANT_PEDID"));
                    pedidos.setPesoTotal(rs.getDouble("peso")* rs.getDouble("CANT_PEDID"));
                    pedidos.setCantidadArticulo(rs.getDouble("CANT_PEDID"));
                    pedidos.setCodigoCliente(rs.getString("COD_CLIENT"));
                    pedidos.setFechaEnvio(rs.getString("entrega"));
                    //pedidos.setFechaActualizacionSaldoCliente(rs.getDate("act"));
                    pedidos.setNumeroDeListadoDeMateriales(rs.getInt("listado"));
                    pedidos.setNumeroDeRevisionDeListado(rs.getInt("revision"));
                    pedidos.setNumeroDeHojaDeRuta(rs.getInt("hdr1"));
                    pedidos.setNumeroDeProceso(rs.getInt("orden_num"));
                    pedidos.setNumeroDeFletero(rs.getInt("fletero"));
                    pedidos.setNumeroComprobante(rs.getString("N_REMITO"));
                    pedidos.setEmpresa(rs.getString("TALON_PEDI"));
                    pedidos.setVerificadorRevision(rs.getInt("revisionado"));
                    pedidos.setVehiculoAnterior(rs.getInt("vehiculoAnterior"));
                    pedidos.setIdPedidoEnTango(rs.getInt("ID_GVA03"));
                    //pedidos.setSaldoACobrar(rs.getDouble("saldo"));
                    clie.setCodigoCliente(pedidos.getCodigoCliente());
                    clie.setRazonSocial(pedidos.getRazonSocial());
                    clie.setEmpresa(pedidos.getEmpresa());
                    //ACA TENDRIA QUE HACER UNA INTERFAZ PAR QUE ME BUSQUE Y ACTUALICE LOS SALDOS
                    
                    //Iterator iSc=SiderconCapaatos.saldoCliente.listIterator();
                    Double sald=0.00;
                    //Clientes cli=new Clientes();
                    //Actualizable actCli=new Clientes();
                    
                    //while(iSc.hasNext()){
                    //  cli=(Clientes)iSc.next();
                    String empresa=pedidos.getEmpresa();
                    int numeroConeccion=0;
                    
                    pedidos.setSaldoCliente(clie.getSaldoActual());
                    sald=0.00;
                    pedidos.setNumeroVendedor(rs.getInt("COD_VENDED"));
                    pedidos.setNombreVendedor(rs.getString("vendedor"));
                    System.err.println(" numero v"+pedidos.getNumeroVendedor()+" nombre v "+pedidos.getNombreVendedor()+" cliente "+pedidos.getRazonSocial()+" saldo "+pedidos.getSaldoCliente());
                    String pendiente=String.valueOf(rs.getDouble("CANT_FACT"));
                    
                    Double articulosPendientes=0.00;
                    if(pendiente==null){
                        
                    }else{
                        articulosPendientes=Double.parseDouble(pendiente);
                    }
                    //pedidos.setCantidadArticulosEntregados(articulosPendientes);
                    //articulosPendientes=pedidos.getCantidadArticulo()- pedidos.getCantidadArticulosEntregados();
                    pedidos.setCantidadArticuloPendiente(articulosPendientes);
                    pedidos.setZonaAsignada(rs.getInt("zona"));
                    pedidos.setAlertaAsignada(rs.getInt("alerta"));
                    if(pedidos.getZonaAsignada() <=1){
                        pedidos.setZonaDescripcion("SANTA FE");
                    }else{
                        pedidos.setZonaDescripcion(rs.getString("zonasDescripcion"));
                    }
                    if(pedidos.getAlertaAsignada() > 0){
                        pedidos.setAlertaDescripcion(rs.getString("alertasDescripcion"));
                    }
                    
                    listaPed.add(pedidos);
                }
                rs.close();
                //ActualizarDatosPedidos act=new ActualizarDatosPedidos();
                //act.setPedidos(listaPed);
                //act.start();
                //    cn.CerrarConneccion(cp);
                
            } catch (SQLException ex) {
                Logger.getLogger(PedidosParaReparto.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listaPed;
    }

    @Override
    public ArrayList ListadoPedidosPorFecha(String fecha) {
            try {
                return this.ListarPedidosPorFecha(fecha);
            } catch (SQLException ex) {
                Logger.getLogger(PedidosParaReparto.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PedidosParaReparto.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
    }

    @Override
    public Boolean EliminarItems(Integer id) {
        Transaccionable conR=new Conecciones();
        Boolean verif=false;
                
        String sql="delete from pedidos_carga1 where numero="+id;
        System.out.println(sql);
        
            
                conR.guardarRegistro(sql);
                verif=true;
            
        return verif;
    }

    @Override
    public DefaultTableModel MostrarEnTabla(ArrayList listado) {
        miTablaModificacion mod=new miTablaModificacion();
        PedidosParaReparto pedid=new PedidosParaReparto();
        //Procesos pro=new Procesos();
        
        Iterator ii=listado.listIterator();
        //jTable1 = new javax.swing.JTable();

        //jTable1.setModel(mod);
        mod.addColumn("Nro Pedido");
        mod.addColumn("Cod Articulo");
        mod.addColumn("descrip Articulo");
        mod.addColumn("cant a enviar");
        mod.addColumn("cant s/fecha");
        mod.addColumn("fecha de entrega");
        mod.addColumn("eliminar Item");
        mod.addColumn("Vendedor");
        Object[] fila=new Object[8];
        while(ii.hasNext()){
        pedid=(PedidosParaReparto)ii.next();
        fila[0]=pedid.getCodigoTangoDePedido();
        fila[1]=pedid.getCodigoArticulo();
        fila[2]=pedid.getDescripcionArticulo();
        fila[3]=pedid.getCantidadArticulo();
        fila[4]=pedid.getCantidadArticuloPendiente();
        fila[5]=pedid.getFechaEnvio();
        fila[6]=true;
        fila[7]=pedid.getNombreVendedor();
        mod.addRow(fila);
        }
        return mod;
    }

    @Override
    public Boolean guardar(ArrayList list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList leer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList leerTango(int vendedor, String fecha, Connection bd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean modificar(Object ob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean enviar(ArrayList listado) {
        return this.enviarPedido(listado);
    }

    @Override
    public Boolean enviarRemotaAcces(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void agregar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList validarEnvio(ArrayList listadoP) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean validarCliente(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean actualizarDatosClientes(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notificar(String mensaje) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void marcarParaReparto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void marcarParaProceso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fijarFecha() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean eliminarItems(ArrayList num) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList validarBaseLocal(int vend, String emp, String fecc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel mostrarEnTabla(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList leerDetalleTango(int vendedor, String fecha, Connection bd, String nroPedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String validarEnviadoHdr(Object pedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList detallePedidosParaCorreccion(String numeroPedido, String fecha) {
        return this.leerDetallePedidoParaCorreccion(numeroPedido, fecha);
    }

    @Override
    public ArrayList ListarVehiculos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer cargarHdrVehiculo(int lst, String fecha) {
        return this.leerHdrVehiculo(lst, fecha);
    }

    @Override
    public void guardarAsignacionDeVehiculos(ArrayList lista) {
        this.guardadoDeAsignacionDeVehiculos(lista);
    }

    @Override
    public ArrayList ListarPedidosPorVehiculo(int idUnidad, ArrayList lstPedidos) {
        return this.GuardarPedidosPorVehiculo(idUnidad, lstPedidos);
    }

    @Override
    public ArrayList ListarPedidosPorZona(String fecha, int zonaSeleccionada) {
        return this.listadoPedidosPorZona(fecha, zonaSeleccionada);
    }
  	
}
