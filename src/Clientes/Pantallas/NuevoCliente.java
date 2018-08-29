/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes.Pantallas;

import Articulos.Rubrable;
import Clientes.Pantallas.AbmSaldosClientes;
import Conversores.Numeros;
import Cotizaciones.Cotizable;
import Cotizaciones.Cotizacion;
import Cotizaciones.DetalleCotizacion;
import Cotizaciones.IngresoDeCotizacion;
import Cotizaciones.ModificacionDeCotizacion;
import ListasDePrecios.ListasPorCliente;
import Pedidos.ImprimirPedido;
import Pedidos.IngresoDePedidos;
import Pedidos.ModificacionDePedidos;
import Pedidos.Pedable;
import Pedidos.Pedidos;
import Recibos.AbmRecibos;
import Recibos.DetalleRecibo;
import Recibos.Recidable;
import Remitos.IngresoDeRemitos;
import Clientes.Objectos.Clientes;
import facturacion.clientes.Facturable;
import facturacion.clientes.MovimientoProveedores;
import facturacion.clientes.ImprimirFactura;
import facturacion.clientes.ListasDePrecios;
import facturacion.pantallas.IngresoDeFacturas;
import facturacion.pantallas.ModificacionDeFacturas;
import interfaceGraficas.AbmClientes;
import interfaceGraficas.Inicio;
import interfaceGraficas.LocalidadesAbm;
import interfaceGraficas.TransporteAbm;
import interfaces.Componable;
import interfaces.Personalizable;
import interfacesPrograma.Busquedas;
import interfacesPrograma.Facturar;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import objetos.CondicionesIva;
import objetos.Localidades;
import objetos.Transportes;

/**
 *
 * @author hernan
 */
public class NuevoCliente extends javax.swing.JInternalFrame implements InternalFrameListener {
    private JInternalFrame clientes;
    private int modificacion;
    private Clientes cliTa=new Clientes();
    private ArrayList listadoL=new ArrayList();
    private ArrayList listadoIva=new ArrayList();
    private ArrayList listadoLoc=new ArrayList();
    private Cotizacion cotizacionT=new Cotizacion();
    private ArrayList listadoCot=new ArrayList();
    private ArrayList listadoPed=new ArrayList();
    DefaultTableModel modelo=new DefaultTableModel();
    DefaultTableModel modelo1=new DefaultTableModel();
    DefaultTableModel modelo2=new DefaultTableModel();        
    private CondicionesIva condicion=new CondicionesIva();
    private ListasDePrecios listaPrecio=new ListasDePrecios();
    private Localidades localidad=new Localidades();
    private ArrayList listadoFac=new ArrayList();
    private TableColumn columnaNumero;
    private ArrayList listadoTransporte;
    private Transportes transp;
    private int pantallaOrigen;
    
    
    /**
     * Creates new form NuevoCliente
     */
    public NuevoCliente() {
        initComponents();
        this.jPanel2.setVisible(false);
    }
    public NuevoCliente(int origen) {
        initComponents();
        this.jPanel2.setVisible(false);
        pantallaOrigen=origen;
    }
    public NuevoCliente(Object client) {
        initComponents();
        cliTa=(Clientes)client;
        this.jTextField1.setText(cliTa.getRazonSocial());
        //this.setTitle("MODIFICACION DATOS DEL CLIENTE - "+cliTa.getRazonSocial());
        this.jTextField2.setText(cliTa.getDireccion());
        Iterator iIva=listadoIva.listIterator();
        int tipoIvaC=cliTa.getTipoIva();
        int rengl=0;
        int posicion=0;
        while(iIva.hasNext()){
            condicion=(CondicionesIva)iIva.next();
            if(tipoIvaC==condicion.getId()){
                posicion=rengl;
            }
            rengl++;
        }
        this.jComboBox1.setSelectedIndex(posicion);
        
        this.jTextField3.setText(cliTa.getNumeroDeCuit());
        this.jTextField4.setText(cliTa.getTelefono());
        tipoIvaC=cliTa.getListaDePrecios();
        rengl=0;
        posicion=0;
        Iterator iLst=listadoL.listIterator();
        while(iLst.hasNext()){
            listaPrecio=(ListasDePrecios)iLst.next();
            if(tipoIvaC==listaPrecio.getNumeroLista()){
                posicion=rengl;
            }
            rengl++;
        }
        this.jComboBox2.setSelectedIndex(posicion);
        
        
        listadoTransporte=new ArrayList();
        /*
        Componable comp=new Transportes();
        Personalizable per=new Transportes();
        listadoTransporte=per.listar();
        this.jComboBox4.setModel(comp.LlenarComboConArray(listadoTransporte));
        */
        
        String loc=cliTa.getLocalidad();
        String loc2="";
        rengl=0;
        posicion=0;
        Iterator itLoc=listadoLoc.listIterator();
        while(itLoc.hasNext()){
            localidad=(Localidades)itLoc.next();
            loc2=localidad.getDescripcion();
            if(loc.equals(loc2)){
                posicion=rengl;
            }
            rengl++;
        }
        
        this.jComboBox3.setSelectedIndex(posicion);
        this.jTextField5.setText(String.valueOf(cliTa.getCupoDeCredito()));
        Double coef=0.00;
        coef=cliTa.getCoeficienteListaDeprecios() - 1;
        if(coef==0.00)coef=1.00;
        /*
        if(cliTa.getCoeficienteListaDeprecios() < 1){
            
            coef=coef * 100;
        }else{
            coef=0.00;
        }
        */
        this.jTextField6.setText(cliTa.getDireccionDeEntrega());
        this.jTextField7.setText(cliTa.getResponsable());
        this.jTextField8.setText(cliTa.getFantasia());
        this.jTextField9.setText(cliTa.getCelular());
        this.jTextField10.setText(cliTa.getDireccionFantasia());
        this.jTextField11.setText(cliTa.getFax());
        this.jTextField12.setText(cliTa.getEmail());
        
        this.jTextArea1.setText(cliTa.getObservaciones());
        modificacion=1;
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jButton21 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("FACTURACION - CARGA DE NUEVO CLIENTE");
        setAutoscrolls(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Razon Social");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, 123, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 13, 310, -1));

        jLabel2.setText("Domicilio :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 54, 123, -1));
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 48, 310, -1));

        jLabel3.setText("Cond Iva:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 86, 123, -1));

        CondicionesIva condicion=new CondicionesIva();
        Busquedas bus=new CondicionesIva();
        listadoIva=bus.listar("");
        Iterator iIva=listadoIva.listIterator();
        while(iIva.hasNext()){
            condicion=(CondicionesIva)iIva.next();
            jComboBox1.addItem(condicion.getDescripcion());
        }
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 83, 310, -1));

        jLabel4.setText("N° de CUIT:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 124, 123, -1));
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 118, 310, -1));

        jLabel5.setText("Telefono :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 156, 123, -1));
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 153, 310, -1));

        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 530, 113, -1));

        jLabel6.setText("Lista de precios :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 191, 123, -1));

        Personalizable per=new ListasDePrecios();
        ListasDePrecios listaP=new ListasDePrecios();
        listadoL=per.listar();
        Iterator ilL=listadoL.listIterator();
        while(ilL.hasNext()){
            listaP=(ListasDePrecios)ilL.next();
            jComboBox2.addItem(listaP.getDescripcionLista());
        }
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 188, 310, -1));

        jLabel9.setText("Cupo de Credito :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 226, 123, -1));

        jTextField5.setText("0.00");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 223, 310, -1));

        jLabel10.setText("Dirección de Entrega:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 261, -1, -1));
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 258, 310, -1));

        jLabel11.setText("Responsable :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 293, 123, -1));
        jPanel1.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 293, 310, -1));

        jLabel12.setText("Nombre de Fantasía :");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 331, 123, -1));
        jPanel1.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 328, 310, -1));

        jLabel13.setText("Celular :");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 366, 123, -1));
        jPanel1.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 363, 310, -1));

        jLabel14.setText("Dirección Fantasía :");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 670, 123, -1));
        jPanel1.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 670, 310, -1));

        jLabel15.setText("Fax :");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 700, 123, -1));
        jPanel1.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 700, 310, -1));

        jLabel16.setText("email  :");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 123, -1));
        jPanel1.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 310, -1));

        jLabel17.setText("Localidad :");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 123, -1));

        Localidades localidad1=new Localidades();
        Busquedas busca=new Localidades();
        listadoLoc=busca.listar("");
        Iterator iLoc=listadoLoc.listIterator();
        while(iLoc.hasNext()){
            localidad1=(Localidades)iLoc.next();
            jComboBox3.addItem(localidad1.getDescripcion());
        }
        jPanel1.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 157, -1));

        jButton11.setText("LISTAS DE PRECIOS");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 530, -1, -1));

        jButton12.setText("VER REMITOS");
        jPanel1.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 550, -1, -1));

        jButton13.setText("DETALLE DE SALDO");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 550, 149, -1));

        jButton20.setText("Nueva Localidad");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 420, 135, -1));

        jLabel19.setText("Transporte:");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, 123, -1));

        jPanel1.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 630, 157, -1));

        jButton21.setText("Nuevo Transporte");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 630, -1, -1));

        jLabel20.setText("Observaciones");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 123, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 460, 310, 60));

        jTable1.setModel(modelo);
        jScrollPane1.setViewportView(jTable1);

        jLabel7.setText("Cotizaciones ");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/pdf.png"))); // NOI18N
        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Cart.png"))); // NOI18N
        jButton3.setText("Pasar Pedido");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel18.setText("Facturas ");

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/tractorunitblack.png"))); // NOI18N
        jButton9.setText("Remitir");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jTable3.setModel(modelo2);
        jScrollPane3.setViewportView(jTable3);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Currency- Dollar.png"))); // NOI18N
        jButton10.setText("Emitir Recibo");
        jButton10.setEnabled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/List.png"))); // NOI18N
        jButton14.setText("Reimprimir");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/black_folder_search.png"))); // NOI18N
        jButton15.setText("Visualizar");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/folder_new.png"))); // NOI18N
        jButton16.setText("Nuevo Presupuesto");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/folder_new.png"))); // NOI18N
        jButton18.setText("Nueva Factura");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Mostrar / Ocultar Panel");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Repeat.png"))); // NOI18N
        jButton22.setText("Actualizar");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton24.setText("Eliminar Presup.");
        jButton24.setEnabled(false);

        jLabel8.setText("Pedidos");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Print.png"))); // NOI18N
        jButton4.setText("OTrabajo");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Print.png"))); // NOI18N
        jButton5.setText("Detallado");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Document 2.png"))); // NOI18N
        jButton6.setText("Factura");
        jButton6.setMaximumSize(new java.awt.Dimension(121, 41));
        jButton6.setMinimumSize(new java.awt.Dimension(121, 41));
        jButton6.setPreferredSize(new java.awt.Dimension(121, 41));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/comment_edit.png"))); // NOI18N
        jButton7.setText("Modificar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Repeat.png"))); // NOI18N
        jButton23.setText("Actualizar");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/tractorunitblack.png"))); // NOI18N
        jButton8.setText("Remitir");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/folder_new.png"))); // NOI18N
        jButton17.setText("Nuevo Pedido");
        jButton17.setMaximumSize(new java.awt.Dimension(101, 41));
        jButton17.setMinimumSize(new java.awt.Dimension(101, 41));
        jButton17.setPreferredSize(new java.awt.Dimension(101, 41));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jTable2.setModel(modelo1);
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButton4)
                        .addGap(12, 12, 12)
                        .addComponent(jButton5)
                        .addGap(12, 12, 12)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButton7)
                        .addGap(12, 12, 12)
                        .addComponent(jButton23)
                        .addGap(7, 7, 7)
                        .addComponent(jButton8)
                        .addGap(12, 12, 12)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1058, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7)
                    .addComponent(jButton23)
                    .addComponent(jButton8)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1032, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jButton9)
                        .addGap(12, 12, 12)
                        .addComponent(jButton10)
                        .addGap(18, 18, 18)
                        .addComponent(jButton14)
                        .addGap(18, 18, 18)
                        .addComponent(jButton15)
                        .addGap(12, 12, 12)
                        .addComponent(jButton18))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1068, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton16)
                                .addGap(12, 12, 12)
                                .addComponent(jButton24)))))
                .addGap(936, 936, 936))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jButton19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7))
                    .addComponent(jButton2)
                    .addComponent(jButton22)
                    .addComponent(jButton3)
                    .addComponent(jButton16)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jButton24)))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9)
                    .addComponent(jButton10)
                    .addComponent(jButton14)
                    .addComponent(jButton15)
                    .addComponent(jButton18))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       Clientes cli=new Clientes();
       //cli.setCodigoCliente(title);
       if(modificacion==1){
           cli=cliTa;
       }
       cli.setRazonSocial(this.jTextField1.getText());
       cli.setDireccion(this.jTextField2.getText());
       String condicion1=null;
       Integer tipo=0;
       condicion=(CondicionesIva)listadoIva.get(this.jComboBox1.getSelectedIndex());
       tipo=condicion.getId();
       
       if(this.jComboBox1.getSelectedIndex() < 4){
           cli.setEmpresa("sd");
       }else{
           cli.setEmpresa("bu");
       }
       ListasDePrecios lista=new ListasDePrecios();
       lista=(ListasDePrecios)listadoL.get(this.jComboBox2.getSelectedIndex());
       
       cli.setListaDePrecios(lista.getNumeroLista());
       cli.setCoeficienteListaDeprecios(lista.getPorcentaje());
       cli.setTipoIva(tipo);
       cli.setCondicionIva(condicion1);
       cli.setNumeroDeCuit(this.jTextField3.getText());
       cli.setTelefono(this.jTextField4.getText());
       cli.setCupoDeCredito(Numeros.ConvertirStringADouble(this.jTextField5.getText()));
       cli.setDireccionDeEntrega(this.jTextField6.getText());
       //Double coef=Numeros.ConvertirStringADouble(this.jTextField6.getText()) / 100;
       //coef=coef + 1;
       //cli.setCoeficienteListaDeprecios(coef);
       cli.setResponsable(this.jTextField7.getText());
       cli.setFantasia(this.jTextField8.getText());
       cli.setCelular(this.jTextField9.getText());
       cli.setFax(this.jTextField11.getText());
       cli.setDireccionFantasia(this.jTextField10.getText());
       cli.setEmail(this.jTextField12.getText());
       localidad=(Localidades)listadoLoc.get(this.jComboBox3.getSelectedIndex());
       cli.setLocalidad(String.valueOf(localidad.getId()));
       transp=(Transportes) listadoTransporte.get(this.jComboBox4.getSelectedIndex());
       cli.setIdTransporte(transp.getId());
       cli.setObservaciones(this.jTextArea1.getText());
       if(cli.getCupoDeCredito() > 0){
               cli.setCondicionDeVenta(2);
           }else{
            cli.setCondicionDeVenta(1);
       }
       Facturar fact=new Clientes();
       if(modificacion==1){
          
        fact.modificarDatosDelCliente(cli); 
       }else{
            
            cli.setCodigoId(fact.guardarNuevoCliente(cli));
            switch(pantallaOrigen){
            case 1:
        
                IngresoDeCotizacion.jCheckBox2.setSelected(true);
                IngresoDeCotizacion.jCheckBox2.setEnabled(false);
                IngresoDeCotizacion.cliT=cli;
                IngresoDeCotizacion.jLabel6.setText(cli.getRazonSocial());
            break;
            case 2:
                IngresoDePedidos.jCheckBox2.setSelected(true);
                IngresoDePedidos.jCheckBox2.setEnabled(false);
                IngresoDePedidos.cliT=cli;
                IngresoDePedidos.jLabel6.setText(cli.getRazonSocial());
                IngresoDePedidos.jTextField1.requestFocus();
            break;
            case 3:
                IngresoDeFacturas.jCheckBox2.setSelected(true);
                IngresoDeFacturas.jCheckBox2.setEnabled(false);
                IngresoDeFacturas.cliT=cli;
                IngresoDeFacturas.jLabel6.setText(cli.getRazonSocial());
                IngresoDeFacturas.jTextField1.requestFocus();
            break;
            default:
                
            break;
        
        }
       }
      
       this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Pedidos pedido=new Pedidos();
        pedido=(Pedidos)listadoPed.get(this.jTable2.getSelectedRow());
        ModificacionDePedidos modificar=new ModificacionDePedidos(pedido);
        Inicio.jDesktopPane1.add(modificar);
        try {
            modificar.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        modificar.setVisible(true);
        modificar.toFront();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Pedidos pedido=new Pedidos();
        pedido=(Pedidos)listadoPed.get(this.jTable2.getSelectedRow());
        ImprimirPedido imprimir=new ImprimirPedido();
        try {
            imprimir.ImprimirOrdenDetallada(pedido);
        } catch (IOException ex) {
            Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Pedidos pedido=new Pedidos();
        pedido=(Pedidos)listadoPed.get(this.jTable2.getSelectedRow());
        ImprimirPedido imprimir=new ImprimirPedido();
        try {
            imprimir.ImprimirOrdenDeTrabajo(pedido.getId());
        } catch (IOException ex) {
            Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Cotizacion cotizacion=new Cotizacion();
        cotizacion=(Cotizacion)listadoCot.get(this.jTable1.getSelectedRow());
        Cotizable coti=new Cotizacion();
        ArrayList detalleC=new ArrayList();
        Cotizable detC=new DetalleCotizacion();
        detalleC=detC.cargarDetalle(cotizacion.getId());
        coti.transformarEnPedido(cotizacion, detalleC);
        this.jTable2.removeAll();
        Pedable pedable=new Pedidos();
        listadoPed.clear();
        listadoPed=pedable.listarPorEstado(cliTa.getCodigoId(),0);
        modelo1=pedable.mostrarListado(listadoPed);
        this.jTable2.setModel(modelo1);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Cotizable cotizable=new Cotizacion();
        Cotizacion cotizacion=new Cotizacion();
        cotizacion=(Cotizacion)listadoCot.get(this.jTable1.getSelectedRow());
        ModificacionDeCotizacion modificar=new ModificacionDeCotizacion(cliTa,cotizacion);
        Inicio.jDesktopPane1.add(modificar);
        try {
            modificar.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        modificar.setVisible(true);
        modificar.toFront();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Pedidos pedido=new Pedidos();
        pedido=(Pedidos)listadoPed.get(this.jTable2.getSelectedRow());
        Pedable peda=new Pedidos();
        peda.transformarEnFactura(pedido, listadoL);
        ModificacionDeFacturas factu=new ModificacionDeFacturas(pedido);
        Inicio.jDesktopPane1.add(factu);
        try {
            factu.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        factu.setVisible(true);
        factu.toFront();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        ListasPorCliente listas=new ListasPorCliente(cliTa);
        Inicio.jDesktopPane1.add(listas);
        try {
            listas.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        listas.setVisible(true);
        listas.toFront();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Integer numeroF=0;
        MovimientoProveedores factura=new MovimientoProveedores();
        factura=(MovimientoProveedores)listadoFac.get(this.jTable3.getSelectedRow());
        IngresoDeRemitos remi=new IngresoDeRemitos(cliTa,factura);
        Inicio.jDesktopPane1.add(remi);
        try {
            remi.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        remi.setVisible(true);
        remi.toFront();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        ArrayList listadoParaRecibo=new ArrayList();
        int cantidad=this.jTable2.getRowCount();
        Double montt=0.00;
        Pedidos factura;
        for(int a=0;a < cantidad;a++){
            if(Numeros.ConvertirStringADouble((String)this.jTable2.getValueAt(a, 4)) > 0){
                factura=new Pedidos();
                factura=(Pedidos)listadoPed.get(a);
                listadoParaRecibo.add(factura);
                montt=montt + factura.getTotal();
            }
        }
        System.out.println("cantidad a recibir "+listadoParaRecibo.size()+" monto total: "+montt);
        Recidable reci=new DetalleRecibo();
        AbmRecibos abm=new AbmRecibos(null,true,listadoParaRecibo,montt,cliTa);
        //abm.jTable1.setModel(reci.mostrarARecibir(listadoParaRecibo));
        //AbmRecibos.listadoFc=listadoParaRecibo;
        //AbmRecibos.montoTotal=montt;
//        abm.setListadoFc(listadoParaRecibo);
  //      abm.setMontoTotal(montt);
    //    abm.setSaldo(montt);
        
        //AbmRecibos.jLabel2.setText(" $"+montt);
        abm.setVisible(true);
        abm.toFront();
        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        MovimientoProveedores comprobante=new MovimientoProveedores();
        int posicion=this.jTable3.getSelectedRow();
        comprobante=(MovimientoProveedores)listadoFac.get(posicion);
        ImprimirFactura imprimir=new ImprimirFactura();
            try {
                imprimir.ImprimirFactura(comprobante.getId(),comprobante.getTipo());
            } catch (IOException ex) {
                Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
                MovimientoProveedores comprobante=new MovimientoProveedores();
        int posicion=this.jTable3.getSelectedRow();
        comprobante=(MovimientoProveedores)listadoFac.get(posicion);
        ModificacionDeFacturas factu=new ModificacionDeFacturas(comprobante);
        Inicio.jDesktopPane1.add(factu);
        try {
            factu.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        factu.setVisible(true);
        factu.toFront();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        //
    }//GEN-LAST:event_formComponentShown

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
    //        System.out.println(" debería recargar los datos acá");
    }//GEN-LAST:event_formFocusGained

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // ACA DEBO MANEJAR LOS EVENTOS DE LAS TECLAS
        System.out.println(" acá se presiono una tecla");
    }//GEN-LAST:event_formKeyPressed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
                
        IngresoDeCotizacion pedidos=new IngresoDeCotizacion(cliTa);
        Inicio.jDesktopPane1.add(pedidos);
        try {
            pedidos.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AbmClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        pedidos.setVisible(true);
        pedidos.toFront();

    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
                
        IngresoDePedidos pedidos=new IngresoDePedidos(cliTa);
        Inicio.jDesktopPane1.add(pedidos);
        try {
            pedidos.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AbmClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        pedidos.setVisible(true);
        pedidos.toFront();

    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        
        IngresoDeFacturas pedidos=new IngresoDeFacturas(cliTa);
        Inicio.jDesktopPane1.add(pedidos);
        try {
            pedidos.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AbmClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        pedidos.setVisible(true);
        pedidos.toFront();

    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        if(this.jPanel1.isVisible()){
            this.jPanel1.setVisible(false);
        }else{
            this.jPanel1.setVisible(true);
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Integer numeroF=0;
        Pedidos pedido=new Pedidos();
        pedido=(Pedidos)listadoPed.get(this.jTable2.getSelectedRow());
        
        IngresoDeRemitos remi=new IngresoDeRemitos(cliTa,pedido);
        Inicio.jDesktopPane1.add(remi);
        try {
            remi.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        remi.setVisible(true);
        remi.toFront();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
       //this.setTitle("MODIFICACION DATOS DEL CLIENTE");
        Personalizable per=new Localidades();
        Rubrable rub=new Localidades();
        listadoLoc.clear();
        listadoCot.clear();
        listadoPed.clear();
        listadoFac.clear();
        listadoLoc=per.listar();
        this.jComboBox3.setModel(rub.mostrarEnBox(listadoLoc));
        
        String loc=cliTa.getLocalidad();
        String loc2="";
        Integer rengl=0;
        Integer posicion=0;
        Iterator itLoc=listadoLoc.listIterator();
        if(loc !=null){
        while(itLoc.hasNext()){
            localidad=(Localidades)itLoc.next();
            loc2=localidad.getDescripcion();
            if(loc.equals(loc2)){
                posicion=rengl;
            }
            rengl++;
        }
        this.jComboBox3.setSelectedIndex(posicion);
        }
        
        Cotizable cotizable=new Cotizacion();
        Cotizacion cotizacion=new Cotizacion();
        listadoCot=cotizable.listarPorEstado(cliTa.getCodigoId(),0);
        if(listadoCot.size() > 0){
            this.jTable1.setVisible(true);
            modelo=cotizable.mostrarListado(listadoCot);
        
            if(Inicio.usuario.getNumeroId()==2){
                this.jButton5.setVisible(false);
                this.jButton8.setVisible(false);
            }
            this.jTable1.setModel(modelo);
            columnaNumero=this.jTable1.getColumn("Numero");
            columnaNumero.setPreferredWidth(60);
            columnaNumero.setMaxWidth(60);
        }else{
            this.jTable1.setVisible(false);
            this.jScrollPane1.setVisible(false);
        }
        Pedable pedable=new Pedidos();
        Pedidos pedidos=new Pedidos();
        listadoPed=pedable.listarPorEstado(cliTa.getCodigoId(),0);
        if(listadoPed.size() > 0){
            this.jTable2.setVisible(true);
            modelo1=pedable.mostrarListado(listadoPed);
            this.jTable2.setModel(modelo1);
            columnaNumero=this.jTable2.getColumn("Numero");
            columnaNumero.setPreferredWidth(60);
            columnaNumero.setMaxWidth(60);
            }else{
            this.jTable2.setVisible(false);
            this.jScrollPane2.setVisible(false);
        }
            Facturable ff=new MovimientoProveedores();
            MovimientoProveedores factura=new MovimientoProveedores();
        
            listadoFac=ff.listarPorClienteNoRemitidas(cliTa.getCodigoId());
        if(listadoFac.size() > 0){
            this.jTable3.setVisible(true);
            modelo2=ff.mostrarListado(listadoFac);
            this.jTable3.setModel(modelo2);
            columnaNumero=this.jTable3.getColumn("Recibo");
            columnaNumero.setPreferredWidth(80);
            columnaNumero.setMaxWidth(80);
        }else{
            this.jTable3.setVisible(false);
            this.jScrollPane3.setVisible(false);
        }
        /*columnaNumero=this.jTable3.getColumn("Monto");
        columnaNumero.setPreferredWidth(80);
        columnaNumero.setMaxWidth(80);
        */
        
        Componable comp=new Transportes();
        Personalizable perT=new Transportes();
        listadoTransporte=perT.listar();
        this.jComboBox4.setModel(comp.LlenarComboConArray(listadoTransporte));
        
        
        Iterator itTra=listadoTransporte.listIterator();
        Integer idTranp=cliTa.getIdTransporte();
        Integer posicion1=0;
        Transportes transp1;
        rengl=0;
        while(itTra.hasNext()){
            transp1=(Transportes) itTra.next();
            if(idTranp == transp1.getId()){
                posicion1=rengl;
            }
            rengl++;
        }
        if(posicion1 > 0){
            this.jComboBox4.setSelectedIndex(posicion1);
        }
        
    }//GEN-LAST:event_formInternalFrameActivated

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        LocalidadesAbm loca=new LocalidadesAbm();
        Inicio.jDesktopPane1.add(loca);
        loca.setVisible(true);
        loca.toFront();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        TransporteAbm trans=new TransporteAbm();
        Inicio.jDesktopPane1.add(trans);
        trans.setVisible(true);
        trans.toFront();
        
        
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        Cotizable cotizable=new Cotizacion();
        Cotizacion cotizacion=new Cotizacion();
        cotizacion=(Cotizacion)listadoCot.get(this.jTable1.getSelectedRow());
        cotizacion=(Cotizacion) cotizable.ActualizarCotizacion(cotizacion);
        ModificacionDeCotizacion modificar=new ModificacionDeCotizacion(cliTa,cotizacion);
        Inicio.jDesktopPane1.add(modificar);
        try {
            modificar.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        modificar.setVisible(true);
        modificar.toFront();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        Pedidos pedido=new Pedidos();
        pedido=(Pedidos)listadoPed.get(this.jTable2.getSelectedRow());
        Pedable pedab=new Pedidos();
        pedido=(Pedidos) pedab.ActualizarPedido(pedido);
        
        ModificacionDePedidos modificar=new ModificacionDePedidos(pedido);
        Inicio.jDesktopPane1.add(modificar);
        try {
            modificar.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        modificar.setVisible(true);
        modificar.toFront();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        AbmSaldosClientes abm=new AbmSaldosClientes(cliTa);
        Inicio.jDesktopPane1.add(abm);
        abm.setVisible(true);
        abm.toFront();
    }//GEN-LAST:event_jButton13ActionPerformed
    private void ControlaInstancia(JInternalFrame inter){
        /*
        boolean mostrar=true;
        //String nombre=inter.getTitle();
        for(int a =0;a < Inicio.jDesktopPane1.getComponentCount();a++){
            if(inter.getClass().isInstance(Inicio.jDesktopPane1.getComponent(a))){
                inter.toFront();
                Inicio.jDesktopPane1.moveToFront(inter);
            }
        }
        */
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

    @Override
    public void internalFrameOpened(InternalFrameEvent ife) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent ife) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent ife) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameIconified(InternalFrameEvent ife) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent ife) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent ife) {
        System.out.println(" debería recargar los datos acá");
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent ife) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
