package FacturaE;

import Configuracion.Propiedades;
import Conversores.Numeros;
import Objetos.FacturaElectronica;
import facturacion.clientes.Clientes;
import facturacion.clientes.Facturable;
import facturacion.clientes.Facturas;
import interfaces.Transaccionable;
import interfacesPrograma.Busquedas;
import interfacesPrograma.Facturar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import objetos.Comprobantes;
import objetos.Conecciones;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import Interface.Electronicable;
import java.io.File;
import static java.lang.Thread.sleep;






public class FEl implements FacturableE{
    private String valor;
    private String resultado;
    private String respuesta;
    private String cae;
    private String caeVto;
    private String fechaCae;
    private String afipQty;
    private String afipPlastId;
    private String afipPlastCbte;
    private Integer id;
    private Integer idFactura;
    private Integer idCliente;
    private String fecha;
    private String customerId;
    private String customerTypeDoc;
    private String tipoComprobante;
    private String importeTotal;
    private String importeNeto;
    private String impuestoLiquido;
    private String condVta;
    private Integer estado;

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    
    public String getCondVta() {
        return condVta;
    }

    public void setCondVta(String condVta) {
        this.condVta = condVta;
    }

    
    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerTypeDoc() {
        return customerTypeDoc;
    }

    public void setCustomerTypeDoc(String customerTypeDoc) {
        this.customerTypeDoc = customerTypeDoc;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(String importeTotal) {
        this.importeTotal = importeTotal;
    }

    public String getImporteNeto() {
        return importeNeto;
    }

    public void setImporteNeto(String importeNeto) {
        this.importeNeto = importeNeto;
    }

    public String getImpuestoLiquido() {
        return impuestoLiquido;
    }

    public void setImpuestoLiquido(String impuestoLiquido) {
        this.impuestoLiquido = impuestoLiquido;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getCae() {
        return cae;
    }

    public void setCae(String cae) {
        this.cae = cae;
    }

    public String getCaeVto() {
        return caeVto;
    }

    public void setCaeVto(String caeVto) {
        this.caeVto = caeVto;
    }

    public String getFechaCae() {
        return fechaCae;
    }

    public void setFechaCae(String fechaCae) {
        this.fechaCae = fechaCae;
    }

    public String getAfipQty() {
        return afipQty;
    }

    public void setAfipQty(String afipQty) {
        this.afipQty = afipQty;
    }

    public String getAfipPlastId() {
        return afipPlastId;
    }

    public void setAfipPlastId(String afipPlastId) {
        this.afipPlastId = afipPlastId;
    }

    public String getAfipPlastCbte() {
        return afipPlastCbte;
    }

    public void setAfipPlastCbte(String afipPlastCbte) {
        this.afipPlastCbte = afipPlastCbte;
    }
    
    
    
    public Object leer(Object comp) throws MalformedURLException, IOException, ParserConfigurationException, SAXException, InterruptedException{
        Comprobantes compro=new Comprobantes();
        compro=(Comprobantes)comp;
        Runtime jpfBatch=Runtime.getRuntime();
        String resultadoA="Error";
        
        
        String cuit=compro.getCliente().getNumeroDeCuit().trim();
        Integer tipDocumento=0;
        Integer tipComprobante=0;
        //FEl fE=new FEl();
        FacturaElectronica fact=new FacturaElectronica();
        Electronicable fBle=new FacturaElectronica();
        FEl fE=new FEl();
        String idCliente=compro.getCliente().getNumeroDeCuit();
        fE.setEstado(compro.getPagado());
        if(idCliente.length() == 8 || idCliente.length()==11){
            
        }else{
            idCliente=JOptionPane.showInputDialog(null,"Ingrese numero de CUIT/CUIL o DNI Sin puntos ni guiones ",idCliente);
        }
        idCliente=idCliente.replace("-","");
        idCliente=idCliente.trim();
        Integer cantCuit=idCliente.length();
        switch(cantCuit){
            case 11:
                if(compro.getTipoComprobante()==2)tipDocumento=80;
                if(compro.getTipoComprobante()==1)tipDocumento=86;
                break;
            case 8:
                tipDocumento=96;
                break;
            
        }
        String tipoDocumento=String.valueOf(tipDocumento);
        if(compro.getTipoComprobante()==1)tipComprobante=6;
        if(compro.getTipoComprobante()==2)tipComprobante=1;
        if(compro.getTipoComprobante()==9)tipComprobante=2;
        if(compro.getTipoComprobante()==10)tipComprobante=3;
        if(compro.getTipoComprobante()==11)tipComprobante=7;
        if(compro.getTipoComprobante()==12)tipComprobante=8;
        
        String tipoComprobante=String.valueOf(tipComprobante);
        String importeTotal=Numeros.ConvertirNumeroAfip(compro.getMontoTotal());
        String importeNeto=Numeros.ConvertirNumeroAfip(compro.getSubTotal());
        String importeTotalConcepto="0.00";
        String importeEx="0.00";
        String impuestoLiq=Numeros.ConvertirNumeroAfip(compro.getMontoIva());
        String importeTributo="0.00";
        String idIva="5";
         /*   
        String sentencia="java -jar FacturaElectronica.jar "+tipoComprobante+" "+tipoDocumento+" "+idCliente+" "+importeTotal+" "+importeTotalConcepto+" "+importeNeto+" "+impuestoLiq+" "+importeTributo+" "+importeEx+" "+idIva;
        System.out.println(sentencia);
        jpfBatch.exec("java -jar FacturaElectronica.jar "+tipoComprobante+" "+tipoDocumento+" "+idCliente+" "+importeTotal+" "+importeTotalConcepto+" "+importeNeto+" "+impuestoLiq+" "+importeTributo+" "+importeEx+" "+idIva);
        //jpfBatch.wait(1000);
        
//wait(1000);
        //sleep(10000);
        File salida=new File("fe.out");
        if(salida.exists()){
            resultadoA="OK";
            JOptionPane.showMessageDialog(null,"POR FIN");
            
        }
        */
         
        fact.setPuntoDeVenta(Propiedades.getPUNTODEVENTA());
        fact.setTipoComprobante(tipoComprobante);//11
        fact.setArchivoCrt(Propiedades.getARCHIVOBCRT());
        fact.setArchivoKey(Propiedades.getARCHIVOKEY());
        fact.setCuit(Propiedades.getCUIT());
        fact.setConcepto("1");
        fact.setTipoDocumento(tipoDocumento);
        fact.setNumeroDocumento(idCliente);
        if(fact.getTipoComprobante().equals("11")){
            fact.setImporteTotal(importeTotal);
            fact.setImporteTotalConcepto("0.00");
            fact.setImporteNeto(importeTotal);
            fact.setImporteIva("0.00");
            fact.setImporteTributo("0.00");
            fact.setImporteOperacionesExp("0.00");
        }else{
            fact.setImporteTotal(importeTotal);
            fact.setImporteTotalConcepto(importeTotalConcepto);
            fact.setImporteNeto(importeNeto);
            fact.setImporteIva(impuestoLiq);
            fact.setImporteTributo(importeTributo);
            fact.setImporteOperacionesExp(importeEx);
        }
        fact.setIvaId(idIva);
        fact.setIdFactura(compro.getIdFactura());
        fact.setIdCliente(compro.getCliente().getCodigoId());
        fact=(FacturaElectronica) fBle.Solicitar(fact);
        if(fact.getCae()!=null){
            
        
            fE.setAfipPlastCbte(fact.getAfipPlastCbte());
            fE.setAfipPlastId(fact.getAfipPlastId());
            fE.setAfipQty(fact.getAfipQty());
            fE.setCae(fact.getCae());
            fE.setCaeVto(fact.getCaeVto());
            fE.setCustomerTypeDoc(fact.getTipoComprobante());
            fE.setCustomerTypeDoc(fact.getTipoDocumento());
            fE.setFecha(fact.getFechaCae());
            fE.setFechaCae(fact.getFechaCae());
            //fE.setIdCliente(idCliente);
            fE.setImporteNeto(fact.getImporteNeto());
            fE.setImporteTotal(fact.getImporteTotal());
            fE.setImpuestoLiquido(fact.getImporteIva());
            fE.setResultado(fact.getResultado());
            fE.setTipoComprobante(fact.getTipoComprobante());
            fE.setIdFactura(fact.getIdFactura());
            fE.setIdCliente(fact.getIdCliente());
            fE.setId(guardar(fE));
            fE.setRespuesta("OK");
            

        }else{
               JOptionPane.showMessageDialog(null,fact.getRespuesta());
                }
      return fE;
      
        //fE.setRespuesta(resultadoA);
      //return fE;
    }

    @Override
    public Object recuperar(Object Fe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer guardar(Object Fe) {
        Transaccionable tra=new Conecciones();
        FEl ffE=new FEl();
        ffE=(FEl)Fe;
        Integer estado=0;
        Integer id=0;
        if(ffE.getResultado().equals("R"))estado=1;
        String sql="insert into facturaelectronica (cae,cae_vto,fecha_cae,afipqty,afipplastid,afipplastcbte,idfactura,idcliente,estado,customerid,customertypedoc,tipo_comprobante,importe_total,importe_neto,impto_liq) values ('"+ffE.getCae()+"','"+ffE.getCaeVto()+"','"+ffE.getFechaCae()+"','"+ffE.getAfipQty()+"','"+ffE.getAfipPlastId()+"','"+ffE.getAfipPlastCbte()+"',"+ffE.getIdFactura()+","+ffE.getIdCliente()+","+estado+",'"+ffE.getCustomerId()+"','"+ffE.getCustomerTypeDoc()+"','"+ffE.getTipoComprobante()+"','"+ffE.getImporteTotal()+"','"+ffE.getImporteNeto()+"','"+ffE.getImpuestoLiquido()+"')";
        System.out.println(sql);
        tra.guardarRegistro(sql);
        sql="select LAST_INSERT_ID()";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                id=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FEl.class.getName()).log(Level.SEVERE, null, ex);
        }
                return id;
    }

    @Override
    public Object modificar(Object Fe) {
        FEl fE=new FEl();
        fE=(FEl)Fe;
        String sql="update facturaelectronica set cae='"+fE.getCae()+"',cae_vto='"+fE.getCaeVto()+"',fecha_cae='"+fE.getFechaCae()+"',afipqty='"+fE.getAfipQty()+"',afipplastid='"+fE.getAfipPlastId()+"',afipplastcbte='"+fE.getAfipPlastCbte()+"', estado=1 where id="+fE.getId();
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        return fE;
    }

    @Override
    public ArrayList listarPorEstado(Integer estado) {
        ArrayList listado=new ArrayList();
        String sql="select * from facturaelectronica where estado="+estado;
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        FEl factE;
        try {
            while(rs.next()){
                factE=new FEl();
                factE.setId(rs.getInt("id"));
                factE.setCae(rs.getString("cae"));
                factE.setCaeVto(rs.getString("cae_vto"));
                factE.setFechaCae(rs.getString("fecha_cae"));
                factE.setAfipQty(rs.getString("afipqty"));
                factE.setAfipPlastId(rs.getString("afipplastid"));
                factE.setAfipPlastCbte(rs.getString("afipplastcbte"));
                factE.setIdFactura(rs.getInt("idfactura"));
                factE.setIdCliente(rs.getInt("idcliente"));
                factE.setFecha(rs.getString("fecha"));
                factE.setCustomerId(rs.getString("customerid"));
                factE.setCustomerTypeDoc(rs.getString("customertypedoc"));
                factE.setTipoComprobante(rs.getString("tipo_comprobante"));
                factE.setImporteTotal(rs.getString("importe_total"));
                factE.setImporteNeto(rs.getString("importe_neto"));
                factE.setImpuestoLiquido(rs.getString("impto_liq"));
                if(rs.getInt("estado")==1){
                    factE.setRespuesta("OK");
                }else{
                    factE.setRespuesta("ERROR");
                }
                listado.add(factE);
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FEl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listado;
    }

    @Override
    public Object cargar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel mostrarListado(ArrayList listadoC) {
        MiModeloTablaArticulos listado=new MiModeloTablaArticulos();
        FEl cotizacion;
        Clientes cliente;
        Facturar bus=new Clientes();
        Facturable ff=new Facturas();
        Facturas factura;
        Iterator iL=listadoC.listIterator();
        listado.addColumn("Fecha");
        listado.addColumn("Cliente");
        listado.addColumn("dni/cuit/cuil");
        listado.addColumn("Monto");
        listado.addColumn("Estado");
        Object[] fila=new Object[5];
        while(iL.hasNext()){
            cotizacion=(FEl)iL.next();
            cliente=new Clientes();
            factura=new Facturas();
            fila[0]=String.valueOf(cotizacion.getFecha());
            cliente=(Clientes)bus.cargarPorCodigoAsignado(cotizacion.getIdCliente());
            fila[1]=cliente.getRazonSocial();
            fila[2]=cliente.getNumeroDeCuit();
            //factura=(Facturas)ff.
            fila[3]=String.valueOf(cotizacion.getImporteTotal());
            if(cotizacion.getRespuesta().equals("OK")){
                fila[4]="Aprobada";
            }else{
                fila[4]="Pendiente";
            }
            listado.addRow(fila);
        }
        
        return listado;
    }

    @Override
    public Object reEnviar(Object fe) {
        FEl fE=new FEl();
            fE=(FEl)fe;
        try {
            
            URL url = new URL("https://tufacturaelectronica.net/api/v1/SANDBOX");
            String charSet="UTF-8";
            String tipo="xml";
            String key="NTYyNjI0ODI1OTUwMy0xNTEwMjAwODI0NTA=";
            //String cuit=compro;
            Integer tipDocumento=0;
            Integer tipComprobante=0;
            
            String idCliente=fE.getCustomerId();
            
            String tipoDocumento=fE.getCustomerTypeDoc();
            
            String tipoComprobante=fE.getTipoComprobante();
            String importeTotal=fE.getImporteTotal();
            String importeNeto=fE.getImporteNeto();
            String importeEx="0.0";
            String impuestoLiq=fE.getImpuestoLiquido();
            
            
            HttpURLConnection con;
            
                con = (HttpURLConnection)url.openConnection();
            
            Authenticator au = new Authenticator() {
                @Override
                protected PasswordAuthentication
                    getPasswordAuthentication() {
                        return new PasswordAuthentication
                       ("mauro@bambusoft.com.ar", "SUtter001".toCharArray());
                    }
            };
            Authenticator.setDefault(au);
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            try{
                OutputStreamWriter out=new OutputStreamWriter(
                        con.getOutputStream());
                
                out.write("TYPE="+tipo);
                out.write("&PUBLIC_KEY="+key);
                out.write("&CUSTOMERID="+idCliente);
                out.write("&CUSTOMERTYPEDOC="+tipoDocumento);
                out.write("&TIPO_COMPROBANTE="+tipoComprobante);
                out.write("&IMPORTE_TOTAL="+importeTotal);
                out.write("&IMPORTE_NETO="+importeNeto);
                out.write("&IMP_OP_EX="+importeEx);
                out.write("&IMPTO_LIQ="+impuestoLiq);
                out.close();
                
                BufferedReader in=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String response;
                String cadena="";
                while((response=in.readLine())!=null){
                    System.out.println(response);
                    cadena=response;
                }
                
                
                //String cadena=response;
                //in.close();
                
                DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
                DocumentBuilder db=dbf.newDocumentBuilder();
                //System.err.println(cadena);
                InputSource archivo=new InputSource();
                
                archivo.setCharacterStream(new StringReader(cadena));
                Document documento=db.parse(archivo);
                //Document documento=db.parse(response);
                documento.getDocumentElement().normalize();
                org.w3c.dom.NodeList nodeLista=documento.getElementsByTagName("AFIP");
                int cantidad=nodeLista.getLength();
                System.out.println("Informacion de conecciones");
                  
                for (int s = 0; s < cantidad; s++) {
                    
                    Node primerNodo = nodeLista.item(s);
                    String titulo;
                    String autor;
                    String hits;
                    System.err.println("numero nodo "+s);
                    
                    if (primerNodo.getNodeType() == Node.ELEMENT_NODE) {
                        
                        Element primerElemento = (Element) primerNodo;
                        //Configuracion conf=new Configuracion();
                        
                        org.w3c.dom.NodeList primerNombreElementoLista =primerElemento.getElementsByTagName("RESPONSE");
                        Element primerNombreElemento =(Element) primerNombreElementoLista.item(0);
                        org.w3c.dom.NodeList primerNombre = primerNombreElemento.getChildNodes();
                        fE.setRespuesta(((Node) primerNombre.item(0)).getNodeValue().toString());
                        System.out.println("respuesta : "  + fE.getRespuesta());
                        //conf.setNombreConeccion(nombreConeccion);
                        org.w3c.dom.NodeList segundoNombreElementoLista =primerElemento.getElementsByTagName("CAE");
                        Element segundoNombreElemento =(Element) segundoNombreElementoLista.item(0);
                        org.w3c.dom.NodeList segundoNombre = segundoNombreElemento.getChildNodes();
                        
                        fE.setCae(((Node) segundoNombre.item(0)).getNodeValue().toString());
                        System.out.println("cae : "  + fE.getCae());
                        //conf.setStringDeUrl(stringDeUrl);
                        org.w3c.dom.NodeList tercerNombreElementoLista =primerElemento.getElementsByTagName("CAE_VTO");
                        Element tercerNombreElemento =(Element) tercerNombreElementoLista.item(0);
                        org.w3c.dom.NodeList tercerNombre = tercerNombreElemento.getChildNodes();
                        fE.setCaeVto(((Node) tercerNombre.item(0)).getNodeValue().toString());
                        System.out.println("cae vencimiento : "  + fE.getCaeVto());
                        //conf.setUsuario(usuario);
                        org.w3c.dom.NodeList cuartoNombreElementoLista =primerElemento.getElementsByTagName("FECHA_CAE");
                        Element cuartoNombreElemento =(Element) cuartoNombreElementoLista.item(0);
                        org.w3c.dom.NodeList cuartoNombre = cuartoNombreElemento.getChildNodes();
                        fE.setFechaCae(((Node) cuartoNombre.item(0)).getNodeValue().toString());
                        System.out.println("fecha cae : "  + fE.getFechaCae());
                        //conf.setUsuario(usuario);
                        org.w3c.dom.NodeList quintoNombreElementoLista =primerElemento.getElementsByTagName("AFIPQTY");
                        Element quintoNombreElemento =(Element) quintoNombreElementoLista.item(0);
                        org.w3c.dom.NodeList quintoNombre = quintoNombreElemento.getChildNodes();
                        fE.setAfipQty(((Node) quintoNombre.item(0)).getNodeValue().toString());
                        System.out.println("afipqty : "  + fE.getAfipQty());
                        //conf.setUsuario(usuario);
                        org.w3c.dom.NodeList sextoNombreElementoLista =primerElemento.getElementsByTagName("AFIPLASTID");
                        Element sextoNombreElemento =(Element) sextoNombreElementoLista.item(0);
                        org.w3c.dom.NodeList sextoNombre = sextoNombreElemento.getChildNodes();
                        fE.setAfipPlastId(((Node) sextoNombre.item(0)).getNodeValue().toString());
                        System.out.println("afipplastid : "  + fE.getAfipPlastId());
                        //conf.setUsuario(usuario);
                        org.w3c.dom.NodeList septimoNombreElementoLista =primerElemento.getElementsByTagName("AFIPLASTCBTE");
                        Element septimoNombreElemento =(Element) septimoNombreElementoLista.item(0);
                        org.w3c.dom.NodeList septimoNombre = septimoNombreElemento.getChildNodes();
                        fE.setAfipPlastCbte(((Node) septimoNombre.item(0)).getNodeValue().toString());
                        System.out.println("afipplastcbte : "  + fE.getAfipPlastCbte());
                        //conf.setClave(clave);
                        //listadoConecciones.add(conf);
                    }
                }
                in.close();
            }catch(java.net.UnknownHostException ex){
                Logger.getLogger(FEl.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("En factura electronica: "+ex);
                fE.setRespuesta("ERROR");
            }catch(java.lang.NullPointerException ey){
                Logger.getLogger(FEl.class.getName()).log(Level.SEVERE, null, ey);
                System.err.println("Parametros invalidos: "+ey);
                fE.setRespuesta("PARAMETROS");
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(FEl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(FEl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(FEl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FEl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fE;
        
    }

    @Override
    public void eliminar(Object fe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String reimprimir(Object fe) {
        FEl fE=new FEl();
        fE=(FEl)fe;
        pdfsJavaGenerador pdf=new pdfsJavaGenerador();
        Clientes cliente=new Clientes();
        Facturar fac=new Clientes();
        cliente=(Clientes)fac.cargarPorCodigoAsignado(fE.getIdCliente());
        pdf.setCliente(cliente);
        pdf.setDoc(fE);
        pdf.run();
        return null;
        
    }

    @Override
    public String imprimir(Object fe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
