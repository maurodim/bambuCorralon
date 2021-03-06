/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cotizaciones;

import Conversores.Numeros;
import Clientes.Objectos.Clientes;
import Clientes.Pantallas.NuevoCliente;
import facturacion.pantallas.SeleccionDeClientes;
import interfaceGraficas.Inicio;
import interfaces.Comparables;
import interfacesPrograma.Facturar;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Articulos.Articulos;
import Articulos.Modificable;
import Articulos.Rubrable;
import Articulos.Rubros;
import Articulos.SubRubros;
import ListasDePrecios.Articulable;
import ListasDePrecios.ArticulosAsignados;
import Sucursales.ListasDePrecios;
import Vendedores.Vendable;
import Vendedores.Vendedores;
import interfaceGraficas.LstVendedores;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import objetos.Comprobantes;
import tablas.MiModeloTablaFacturacion;


/**
 *
 * @author hernan
 */
public class IngresoDeCotizacion extends javax.swing.JInternalFrame {

    /**
     * Creates new form IngresoDePedidos
     */
    public static Clientes cliT;
    private ArrayList detalleDelPedido=new ArrayList();
    private Articulos arti;
    private static ArrayList listadoDeBusqueda=new ArrayList();
    private static Double montoTotal=0.00;
    private static Comprobantes comp=new Comprobantes();
    private ListasDePrecios lista;
    private Rubros rubro=new Rubros();
    private SubRubros subRubro;
    private ArrayList listadoSubRubros;
    private Rubrable ruble=new Rubros();
    private ArrayList listadoR=new ArrayList();
    private DefaultComboBoxModel combox=new DefaultComboBoxModel();
    private TableColumn columnaCodigo;
    private String valorCargado;
    private Double porcentajeDescuento;
    private Double subTotal;
    private String rub;
    private Vendedores vendedor;
    private ArrayList listadoVendedores;
    
    
    public IngresoDeCotizacion() {
        //Articulos.CargarMap();
        cliT=new Clientes();
        Facturar fa=new Clientes();
        cliT=(Clientes) fa.cargarPorCodigoAsignado(1);
        lista=new ListasDePrecios(cliT.getListaDePrecios());
        //cliT=(ClientesTango)oob;
        //comp.setCliente(cliT);
        initComponents();
        vendedor=new Vendedores();
        listadoVendedores=new ArrayList();
        Vendable vende=new Vendedores();
        LstVendedores lVen=new LstVendedores(null,true);
        listadoVendedores=vende.listar();
        
        lVen.jComboBox1.setModel(vende.mostrarEnCombo(listadoVendedores));
        lVen.setVisible(true);
        vendedor=(Vendedores) listadoVendedores.get(lVen.jComboBox1.getSelectedIndex());
       
        this.setTitle("Ingreso de Cotizaciones - Vendedor "+vendedor.getNombre());
        rub="";
        porcentajeDescuento=0.00;
        subTotal=0.00;
        this.jLabel6.setText(cliT.getRazonSocial());
        //this.jLabel7.setVisible(false);
        this.jTextField4.setVisible(false);
        this.jCheckBox1.setVisible(false);
        this.jCheckBox2.setEnabled(false);
        this.jCheckBox2.setVisible(false);
        //this.jLabel10.setVisible(false);
        //this.jComboBox2.setVisible(false);
        this.jTextField1.requestFocus();
        
        //this.jPanel2.requestFocus();
        
    }

    public IngresoDeCotizacion(Clientes clienteTango) {
        cliT=new Clientes();
        cliT=(Clientes)clienteTango;
        lista=new ListasDePrecios(cliT.getListaDePrecios());
//cliT=(ClientesTango)oob;
        //comp.setCliente(cliT);
        initComponents();
        vendedor=new Vendedores();
        listadoVendedores=new ArrayList();
        Vendable vende=new Vendedores();
        LstVendedores lVen=new LstVendedores(null,true);
        listadoVendedores=vende.listar();
        
        lVen.jComboBox1.setModel(vende.mostrarEnCombo(listadoVendedores));
        lVen.setVisible(true);
        vendedor=(Vendedores) listadoVendedores.get(lVen.jComboBox1.getSelectedIndex());
        
        rub="";
        porcentajeDescuento=0.00;
        subTotal=0.00;
        this.jButton3.setVisible(false);
        this.jButton5.setVisible(false);
        this.jLabel6.setText(cliT.getRazonSocial());
       // this.jLabel7.setVisible(false);
        this.jTextField4.setVisible(false);
        this.jCheckBox1.setVisible(false);
        this.jCheckBox2.setEnabled(false);
        this.jCheckBox2.setVisible(false);
        this.jTextField1.requestFocus();
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        MiModeloTablaFacturacion facturas=new MiModeloTablaFacturacion();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ingreso de Cotizacion");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
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
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jTable1.setModel(facturas);
        jScrollPane1.setViewportView(jTable1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/mac_trashcan_full_new.png"))); // NOI18N
        jButton2.setText("Eliminar Item");
        jButton2.setMaximumSize(new java.awt.Dimension(185, 41));
        jButton2.setMinimumSize(new java.awt.Dimension(185, 41));
        jButton2.setPreferredSize(new java.awt.Dimension(185, 41));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/currency_black_dollar.png"))); // NOI18N
        jButton6.setText("Modificar Precio");
        jButton6.setMaximumSize(new java.awt.Dimension(185, 41));
        jButton6.setMinimumSize(new java.awt.Dimension(185, 41));
        jButton6.setPreferredSize(new java.awt.Dimension(185, 41));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Tools.png"))); // NOI18N
        jButton7.setText("Agregar Comentario");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton7);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/pdf.png"))); // NOI18N
        jButton1.setText("IMPRIMIR");
        jButton1.setMaximumSize(new java.awt.Dimension(185, 41));
        jButton1.setMinimumSize(new java.awt.Dimension(185, 41));
        jButton1.setPreferredSize(new java.awt.Dimension(185, 41));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jCheckBox2.setSelected(true);
        jCheckBox2.setText("PAGADO");
        jCheckBox2.setMaximumSize(new java.awt.Dimension(185, 25));
        jCheckBox2.setMinimumSize(new java.awt.Dimension(185, 25));
        jCheckBox2.setPreferredSize(new java.awt.Dimension(185, 25));
        jPanel4.add(jCheckBox2);

        jLabel1.setText("TOTAL FACTURA :");
        jLabel1.setMaximumSize(new java.awt.Dimension(185, 16));
        jLabel1.setMinimumSize(new java.awt.Dimension(185, 16));
        jLabel1.setPreferredSize(new java.awt.Dimension(185, 16));
        jPanel4.add(jLabel1);

        jTextField3.setText("0");
        jTextField3.setToolTipText("Presione enter para aplicar descuento general");
        jTextField3.setMinimumSize(new java.awt.Dimension(30, 22));
        jTextField3.setPreferredSize(new java.awt.Dimension(30, 22));
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });
        jPanel4.add(jTextField3);

        jLabel5.setText("% DESCUENTO");
        jPanel4.add(jLabel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setMaximumSize(new java.awt.Dimension(521, 202));
        jPanel2.setLayout(new java.awt.GridLayout(4, 5, 2, 2));

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/groups_black.png"))); // NOI18N
        jButton3.setText("Ingresar Cliente");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/folder_new.png"))); // NOI18N
        jButton5.setText("Nuevo Cliente");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5);

        jPanel2.add(jPanel5);

        jLabel6.setText("<HTML><strong>jLabel6</strong></html>");
        jPanel2.add(jLabel6);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 685, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel8);

        jLabel3.setText("Descripcion (F1 Busca)");
        jPanel2.add(jLabel3);

        jTextField1.requestFocus();
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        jPanel2.add(jTextField1);

        jLabel9.setText("<html>PRESIONE F1 PARA CONSULTAR POR DESCRIPCION<br>\nPRESIONE F3 PARA SELECCIONAR CLIENTE<br>\n</html>");
        jPanel2.add(jLabel9);

        jLabel4.setText("CANTIDAD :");
        jPanel2.add(jLabel4);

        jTextField2.setPreferredSize(new java.awt.Dimension(40, 20));
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });
        jPanel2.add(jTextField2);

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("iNCLUYE SERVICIO ?");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        jPanel2.add(jCheckBox1);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel7);

        jLabel8.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel6.add(jLabel8);

        jPanel2.add(jPanel6);

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });
        jPanel2.add(jTextField4);

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        DefaultTableModel modelo=new DefaultTableModel();
        jTable2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jTable2.setModel(modelo);
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable2KeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jPanel3.add(jScrollPane3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            //System.out.println("ENTRO CON EL ENTER¡¡¡¡¡¡");
            listadoDeBusqueda.clear();
            Facturar fart=new Articulos();
            arti=new Articulos();
            arti=(Articulos)fart.cargarPorCodigoDeBarra(jTextField1.getText());
            if(arti.getCodigoDeBarra().equals("")){
                
             jTextField1.setText("");   
            }else{
            listadoDeBusqueda.add(arti);
            //jTextField1.setText(arti.getCodigoAsignado());
            jTextField2.setText("1");
            this.jLabel8.setText(arti.getDescripcionArticulo());
            
            
                //this.jLabel7.setVisible(false);
                this.jTextField4.setVisible(false);
                

                if(arti.getPrecioServicio() > 0){
                  //  this.jLabel7.setVisible(true);
                    this.jTextField4.setVisible(true);
                    
                    this.jTextField4.setText(String.valueOf(arti.getPrecioUnitarioNeto()));
                    //this.jTextField4.setEnabled(false);
                    this.jCheckBox1.setVisible(true);
                    Calendar calendario=new GregorianCalendar();
                    int hora=calendario.get(Calendar.HOUR_OF_DAY);
                    //System.out.println("LA HORA ACTUAL ES :"+hora);
                    
                }
            
            
            if(cliT.getCondicionDeVenta()==2)this.jCheckBox2.setEnabled(true);
            this.jTextField2.selectAll();
            this.jTextField2.requestFocus();
            }
        }
        if(evt.getKeyCode()==KeyEvent.VK_F1){
            //System.out.println("ENTRO CON F1¡¡¡¡¡");
            valorCargado=jTextField1.getText();
        Facturar fart=new Articulos();
        this.jTable2.removeAll();
            Modificable modiA=new Articulos();
            Articulable modi=new ArticulosAsignados();
            listadoDeBusqueda.clear();
            listadoDeBusqueda=fart.listadoBusqueda(jTextField1.getText());
            //listadoDeBusqueda=modi.convertirListadoEnArticulos(modi.filtradorDeFormularios(listadoSubRubros, listadoR, cliT,this.jTextField1.getText()));
            //listadoDeBusqueda=modi.filtrador(listadoSubRubros,listadoR);
            this.jTable2.setModel(modiA.mostrarListadoBusqueda(listadoDeBusqueda));
                columnaCodigo=this.jTable2.getColumn("Descripcion");
        columnaCodigo.setPreferredWidth(600);
        columnaCodigo.setMaxWidth(600);
                columnaCodigo=this.jTable2.getColumn("Stock");
        columnaCodigo.setPreferredWidth(60);
        columnaCodigo.setMaxWidth(60);
            this.jTable2.requestFocus();
        }else{
            /*
            Facturar fart=new Articulos();
            this.jTable2.removeAll();
            Modificable modiA=new Articulos();
            Articulable modi=new ArticulosAsignados();
            listadoDeBusqueda.clear();
            listadoDeBusqueda=modi.convertirListadoEnArticulos(modi.filtradorDeFormularios(listadoSubRubros, listadoR, cliT,this.jTextField1.getText()));
            //listadoDeBusqueda=modi.filtrador(listadoSubRubros,listadoR);
            this.jTable2.setModel(modiA.mostrarListadoBusqueda(listadoDeBusqueda));
                columnaCodigo=this.jTable2.getColumn("Descripcion");
        columnaCodigo.setPreferredWidth(600);
        columnaCodigo.setMaxWidth(600);
                columnaCodigo=this.jTable2.getColumn("Stock");
        columnaCodigo.setPreferredWidth(60);
        columnaCodigo.setMaxWidth(60);
            */
        }
        if(evt.getKeyCode()==KeyEvent.VK_F4){
                    //verificar();
        //Impresora imp=new Impresora();        
        String cadena=cliT.getCodigoCliente()+" - "+cliT.getRazonSocial()+"\n"+cliT.getDireccion();
        //comp.setCliente(cliT);
        //VisorDeHojaDeRuta
        
        //comp.setVendedor(VisorDeHojaDeRuta.tG.getOperador());
        if(this.jCheckBox1.isSelected()){
        //    comp.setReparto(1);
        //    comp.setEntrega(String.valueOf(this.jTextField3.getText()));
        }
        
        //comp.setArticulos(detalleDelPedido);
        DecimalFormat fr=new DecimalFormat("00");
        Calendar c1=Calendar.getInstance();
	Calendar c2=new GregorianCalendar();
	String dia=Integer.toString(c2.get(Calendar.DAY_OF_MONTH));
	String mes=Integer.toString(c2.get(Calendar.MONTH));
	String ano=Integer.toString(c2.get(Calendar.YEAR));
	
        int da=Integer.parseInt(dia);
        int me=Integer.parseInt(mes);
        me++;
        dia=fr.format(da);
        mes=fr.format(me);
        String fecha=dia+"/"+mes+"/"+ano;
        String fecha2=ano+"-"+mes+"-"+dia;
        //comp.setFechaComprobante(fecha2);
        //comp.setFechaComprobante(fecha);
        int comprobanteTipo=(int) Inicio.sucursal.getTipoComprobantes().get(0);
        //System.out.println("COMPROBANTEEEEEEE "+comprobanteTipo);
        if(cliT.getCondicionIva().equals("RI "))comprobanteTipo=(int)Inicio.sucursal.getTipoComprobantes().get(1);
        Comprobantes comprobante=new Comprobantes();
        comprobante.setCliente(cliT);
        comprobante.setTipoMovimiento(1);
        comprobante.setTipoComprobante(comprobanteTipo);
        comprobante.setFechaEmision((Date.valueOf(fecha2)));
        comprobante.setListadoDeArticulos(detalleDelPedido);
        comprobante.setUsuarioGenerador(Inicio.usuario.getNumero());
        comprobante.setIdSucursal(Inicio.sucursal.getNumero());
        comprobante.setIdDeposito(Inicio.deposito.getNumero());
        comprobante.setIdCaja(Inicio.caja.getNumero());
        if(montoTotal == 0.00){
            String sqM="usuario :"+Inicio.usuario.getNombre()+" sucursal "+Inicio.sucursal.getNumero()+" idcaja "+Inicio.caja.getNumero();
            JOptionPane.showMessageDialog(this,"OJO EL MONTO DE ESTE COMPROBANTE ES $ 0, AVISE PARA DETECTAR EL ERROR");
            FileWriter fichero=null;
            PrintWriter pw=null;
            try {
                fichero = new FileWriter("C:\\Gestion\\"+Inicio.fechaDia+" - errores en comprobantes.txt",true);
                pw=new PrintWriter(fichero);
                pw.println(sqM);
            } catch (IOException ex1) {
                Logger.getLogger(IngresoDeCotizacion.class.getName()).log(Level.SEVERE, null, ex1);
            }finally{
                         try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
            }
        }
        comprobante.setMontoTotal(montoTotal);
        int noFacturar=0;
        if(this.jCheckBox2.isSelected()){
            comprobante.setPagado(1);
        }else{
            comprobante.setPagado(0);
            /*
             * ACA DEBO COMPROBAR EL LIMITE DEL CLIENTE Y SI LO SUPERA LA COMPRA RECHAZAR LA VENTA
             * 
             */
            Double limite=cliT.getCupoDeCredito();
            Double saldo=cliT.getSaldo();
            Double totalGral=montoTotal + saldo;
            if(limite < totalGral)noFacturar=1;
            
        }
        if(noFacturar==0){
        Facturar fat=new Comprobantes();
        fat.guardar(comprobante);
        /*
         * ACA DEVO LIMPIAR TODOS LOS CAMPOS Y VARIABLES DE LA PANTALLA
         * 
         */
        //comp.setTipoComprobante(comprobanteTipo);
        //comp.setMontoTotal(montoTotal);
        detalleDelPedido.clear();
        agregarRenglonTabla();
        this.jCheckBox2.setSelected(true);
        this.jCheckBox2.setEnabled(false);
        this.jTable2.removeAll();
        listadoDeBusqueda.clear();
        cargarLista(listadoDeBusqueda);
        cliT=new Clientes("999999");
        this.jLabel6.setText(cliT.getRazonSocial());
        this.jTextField2.setText("");
        jTextField1.setText("");
        jTextField1.requestFocus();
        
        }else{
            JOptionPane.showMessageDialog(this,"El cliente supera el límite de crédito, debe abonar la venta");
            noFacturar=0;
        }
        }
        if(evt.getKeyCode()==KeyEvent.VK_F3){
            SeleccionDeClientes selCli=new SeleccionDeClientes(1);
        Inicio.jDesktopPane1.add(selCli);
        selCli.setVisible(true);
        selCli.toFront();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Double cantt=Double.parseDouble(this.jTextField2.getText());
            
            if(arti.getModificaPrecio()){
                this.jTextField4.setText(String.valueOf(arti.getPrecioUnitarioNeto()));
                this.jTextField4.requestFocus();
            }else{
                if(arti.getPrecioServicio()>0){
                 this.jTextField4.setText(String.valueOf(arti.getPrecioUnitarioNeto()));
                    this.jTextField4.requestFocus();   
                }else{
                    Articulos articul=new Articulos();
                    Comparables comparar=new Articulos();
                    
                    articul.setCantidad(cantt);
                    articul.setCodigoAsignado(arti.getCodigoAsignado());
                    articul.setDescuento(0);
                    articul.setCodigoDeBarra(arti.getCodigoDeBarra());
                    articul.setDescripcionArticulo(arti.getDescripcionArticulo());
                    articul.setNumeroId(arti.getNumeroId());
                    Double precio=comparar.comparaConCotizaciones(cliT.getCodigoId(),arti.getNumeroId(),cliT.getCoeficienteListaDeprecios());
                    String precio2=comparar.comparaConPedidos(cliT.getCodigoId(),arti.getNumeroId());
                    articul.setPrecioUnitarioNeto(arti.getPrecioUnitarioNeto());
                    // aca tengo que modificar el precio unitario segun el coeficiente del cliente y la lista
                    //Double precioU=arti.getPrecioUnitarioNeto();// * lista.getCoeficiente();
                    
                    
                    if(precio != cliT.getCoeficienteListaDeprecios()){
                        precio=articul.getPrecioUnitarioNeto()* precio;
                        String cartel="precio asignado: "+precio+" "+precio2;
                        if(JOptionPane.showConfirmDialog(this, cartel)==0){
                            articul.setPrecioUnitarioNeto(precio);
                            
                        }else{
                            Double precioU= arti.getPrecioUnitarioNeto() * cliT.getCoeficienteListaDeprecios();
                            articul.setPrecioUnitarioNeto(precioU);
                        }
                    }else{
                        Double precioU= arti.getPrecioUnitarioNeto() * cliT.getCoeficienteListaDeprecios();
                        articul.setPrecioUnitarioNeto(precioU);
                    }
                    
                    
                    articul.setPrecioDeCosto(arti.getPrecioDeCosto());
                    
                    articul.setPrecioUnitario(arti.getPrecioUnitario());
                    
                    articul.setIdCombo(arti.getIdCombo());
                    articul.setCombo(arti.getCombo());
            detalleDelPedido.add(articul);
            agregarRenglonTabla();
//                Double montoTotalX=(arti.getPrecioUnitario() * arti.getCantidad());
//                montoTotal=montoTotal + montoTotalX;
                 montrarMonto();
                 //System.err.println("MONTO TOTAL "+montoTotal);
                 this.jLabel8.setText("");
                 this.jTable2.removeAll();
                this.jButton1.setVisible(true);
            
            this.jTextField2.setText("");
            jTextField1.setText(valorCargado);
            this.jTextField1.selectAll();
            this.jTextField1.requestFocus();
                }
                }
            
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        detalleDelPedido.clear();
        listadoDeBusqueda.clear();
        montoTotal=0.00;
    }//GEN-LAST:event_formComponentHidden

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        detalleDelPedido.clear();
        listadoDeBusqueda.clear();
        montoTotal=0.00;
    }//GEN-LAST:event_formInternalFrameClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //verificar();
        //Impresora imp=new Impresora();        
        String cadena=cliT.getCodigoCliente()+" - "+cliT.getRazonSocial()+"\n"+cliT.getDireccion();
        //comp.setCliente(cliT);
        //VisorDeHojaDeRuta
        
        //comp.setVendedor(VisorDeHojaDeRuta.tG.getOperador());
        
        
        //comp.setArticulos(detalleDelPedido);
        DecimalFormat fr=new DecimalFormat("00");
        Calendar c1=Calendar.getInstance();
	Calendar c2=new GregorianCalendar();
	String dia=Integer.toString(c2.get(Calendar.DAY_OF_MONTH));
	String mes=Integer.toString(c2.get(Calendar.MONTH));
	String ano=Integer.toString(c2.get(Calendar.YEAR));
	
        int da=Integer.parseInt(dia);
        int me=Integer.parseInt(mes);
        me++;
        dia=fr.format(da);
        mes=fr.format(me);
        String fecha=dia+"/"+mes+"/"+ano;
        String fecha2=ano+"-"+mes+"-"+dia;
        me++;
        if(me > 12){
            me=1;
            Integer anio=Integer.parseInt(ano);
            anio++;
            ano=String.valueOf(anio);
        }    
        mes=fr.format(me);
        
        String vencimiento=ano+"-"+mes+"-"+dia;
        //comp.setFechaComprobante(fecha2);
        //comp.setFechaComprobante(fecha);
        //int comprobanteTipo=4;
        //if(cliT.getCondicionIva().equals("RI "))comprobanteTipo=(int)Inicio.sucursal.getTipoComprobantes().get(1);
        
        Cotizacion comprobante1=new Cotizacion();
        comprobante1.setIdCliente(cliT.getCodigoId());
        comprobante1.setFecha(Date.valueOf(fecha2));
        comprobante1.setVencimiento(Date.valueOf(vencimiento));
        comprobante1.setIdUsuario(Inicio.usuario.getNumero());
        
        
        
        subTotal=montoTotal;
        Double ivv=subTotal * 0.21;
        Double sub=0.00;
        Double tot=montoTotal + ivv;
        if(porcentajeDescuento > 0.00){
            sub = subTotal * porcentajeDescuento;
            sub= montoTotal - sub;
        }else{
            sub=montoTotal;
        }
        
        comprobante1.setTotal(sub);
        comprobante1.setSubTotal(montoTotal);
        Double descuen=montoTotal - sub;
        comprobante1.setDescuento(descuen);
        comprobante1.setPorcentajeDescuento(porcentajeDescuento);
        comprobante1.setIdUsuario(vendedor.getId());
        System.out.println("subtotal "+montoTotal+" descuento "+descuen+" total "+subTotal);
        Cotizable cCoti=new Cotizacion();
        Cotizable det=new DetalleCotizacion();
        DetalleCotizacion detalle;
        Integer nuevaCotizacion=cCoti.nuevaCotizacion(comprobante1);
        comprobante1.setId(nuevaCotizacion);
        Iterator iArt=detalleDelPedido.listIterator();
        Articulos articulo=new Articulos();
        
        while(iArt.hasNext()){
            articulo=(Articulos)iArt.next();
            detalle=new DetalleCotizacion();
            detalle.setIdArticulo(articulo.getNumeroId());
            detalle.setDescripcionArticulo(articulo.getDescripcionArticulo());
            detalle.setIdCliente(cliT.getCodigoId());
            detalle.setIdCotizacion(nuevaCotizacion);
            detalle.setCantidad(articulo.getCantidad());
            
            detalle.setPrecioUnitario(articulo.getPrecioUnitarioNeto());
            detalle.setPrecioUnitarioNeto(articulo.getPrecioUnitario());
            
            detalle.setDescuento(articulo.getDescuento());
            if(articulo.getMontoDescuento()!=null){
            detalle.setMontoDescuento(articulo.getMontoDescuento());
            }else{
                detalle.setMontoDescuento(0.00);
            }
            det.nuevaCotizacion(detalle);
            
        }
        // A PARTIR DE ACA DEBO CARGAR LA IMPRESION LO ANTERIOR ES PARA GUARDAR EL MOVIMIENTO
        
        int comprobanteTipo=4;
        
        Comprobantes comprobante=new Comprobantes();
        
        //comprobante.setCliente(cliT);
        //comprobante.setTipoMovimiento(1);
        comprobante.setTipoComprobante(comprobanteTipo);
        comprobante.setFechaEmision((Date.valueOf(fecha2)));
        comprobante.setVencimiento(Date.valueOf(vencimiento));
        comprobante.setListadoDeArticulos(detalleDelPedido);
        comprobante.setUsuarioGenerador(Inicio.usuario.getNumero());
        comprobante.setIdSucursal(1);
        comprobante.setIdDeposito(1);
        Integer numeroCaja=Inicio.caja.getNumero();
        //System.out.println("EL NUMERO DE CAJA ESSSSSSSS "+numeroCaja);
        //comprobante.setIdCaja(numeroCaja);
        
        comprobante.setMontoTotal(montoTotal);
        int noFacturar=0;
        
        pdfsJavaGenerador pdf=new pdfsJavaGenerador();
        pdf.setDoc(comprobante1);
        pdf.setCliente(cliT);
        pdf.run();
        /*
        Facturar fat=new Comprobantes();
        fat.guardar(comprobante);
        */
         // ACA DEVO LIMPIAR TODOS LOS CAMPOS Y VARIABLES DE LA PANTALLA
         
        //comp.setTipoComprobante(comprobanteTipo);
        //comp.setMontoTotal(montoTotal);
        
            Facturar fac=new Clientes();
            cliT=(Clientes) fac.cargarPorCodigoAsignado(1);
            detalleDelPedido.clear();
                agregarRenglonTabla();
                this.jCheckBox2.setSelected(true);
                //this.jCheckBox2.setEnabled(false);
                this.jTable2.removeAll();
                listadoDeBusqueda.clear();
                cargarLista(listadoDeBusqueda);
                //cliT=new Clientes("99");
                this.jLabel6.setText(cliT.getRazonSocial());
                this.jTextField2.setText("");
                jTextField1.setText("");
                jTextField1.requestFocus();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int posicion=this.jTable1.getSelectedRow();
        detalleDelPedido.remove(posicion);
        //detalleDelPedido.clear();
        agregarRenglonTabla();
        jTextField1.setText("");
        jTextField1.requestFocus();
        //listadoDeBusqueda.clear();
        //montoTotal=0.00;        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        SeleccionDeClientes selCli=new SeleccionDeClientes(1);
        Inicio.jDesktopPane1.add(selCli);
        selCli.setVisible(true);
        selCli.toFront();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        jTextField1.requestFocus();
    }//GEN-LAST:event_formComponentShown

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        NuevoCliente nCli=new NuevoCliente(1);
        Inicio.jDesktopPane1.add(nCli);
        nCli.setVisible(true);
        nCli.toFront();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Double servicio;
            //Articulos articuloss=new Articulos();
            if(this.jCheckBox1.isSelected()){
                servicio=arti.getPrecioUnitarioNeto();
            }else{
                servicio=0.00;
            }
            if(arti.getModificaPrecio())servicio=Numeros.ConvertirStringADouble(String.valueOf(this.jTextField4.getText()));
            Double tota=servicio;
            //arti.setPrecioUnitarioNeto(tota);
            //arti.setPrecioServicio(servicio);
            Double cantt=Double.parseDouble(this.jTextField2.getText());
            Articulos articul=new Articulos();
                    articul.setCantidad(cantt);
                    articul.setCodigoAsignado(arti.getCodigoAsignado());
                    articul.setPrecioServicio(servicio);
                    articul.setCodigoDeBarra(arti.getCodigoDeBarra());
                    articul.setDescripcionArticulo(arti.getDescripcionArticulo());
                    articul.setNumeroId(arti.getNumeroId());
                    articul.setPrecioDeCosto(arti.getPrecioDeCosto());
                    articul.setPrecioUnitario(arti.getPrecioUnitarioNeto());
                    articul.setPrecioUnitarioNeto(tota);
                    articul.setModificaPrecio(arti.getModificaPrecio());
                    articul.setIdCombo(arti.getIdCombo());
                    articul.setCombo(arti.getCombo());
                        detalleDelPedido.add(articul);
            agregarRenglonTabla();
//                Double montoTotalX=(arti.getPrecioUnitario() * arti.getCantidad());
//                montoTotal=montoTotal + montoTotalX;
                 montrarMonto();
                 //System.err.println("MONTO TOTAL "+montoTotal);
                 this.jLabel8.setText("");
                 this.jTable2.removeAll();
                this.jButton1.setVisible(true);
            this.jTextField1.setText("");
            this.jTextField2.setText("");
            this.jTextField1.requestFocus();
            //this.jLabel7.setVisible(false);
            this.jTextField4.setVisible(false);
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
       this.jTextField4.requestFocus();
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //MODIFICAR PRECIO
        int posicion=this.jTable1.getSelectedRow();
        Articulos pedidos;
        pedidos=(Articulos)detalleDelPedido.get(posicion);
        Double precioU=pedidos.getPrecioUnitarioNeto();
        Double precio=Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo valor unitario s/iva",precioU));
        Double cantidad=Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad",pedidos.getCantidad()));
        pedidos.setCantidad(cantidad);

        Double descuento=pedidos.getPrecioUnitarioNeto() - precio;
        pedidos.setPrecioUnitarioNeto(precio);
        if(descuento > 0){
            pedidos.setMontoDescuento(descuento * cantidad);
        }
        pedidos.setDescuento(1);
        //detalleDelPedido.clear();
        agregarRenglonTabla();
        System.out.println("total "+montoTotal);
        montrarMonto();
        jTextField1.setText("");
        jTextField1.requestFocus();

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int cod=0;
        String descripcion=JOptionPane.showInputDialog("Ingrese aclaracion del articulo ","");
        descripcion=descripcion.trim();
        if(descripcion.equals("")){
            
        }else{
            Articulos pedidos=new Articulos();
            pedidos.setNumeroId(0);
            pedidos.setCantidad(0.00);
            pedidos.setPrecioUnitarioNeto(0.00);
            pedidos.setPrecioDeCosto(0.00);
            pedidos.setDescripcionArticulo(descripcion);
            pedidos.setCodigoAsignado(String.valueOf(cod));
            detalleDelPedido.add(pedidos);
            agregarRenglonTabla();
            montrarMonto();
        }
        jTextField1.setText("");
        jTextField1.requestFocus();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    int posicion=this.jTable2.getSelectedRow();
        arti=(Articulos)listadoDeBusqueda.get(posicion);
        //System.err.println("ARTICULO SELECCIONADO :"+arti.getDescripcionArticulo()+" "+arti.getCodigoDeBarra());
        String codBar=arti.getCodigoDeBarra();
        jTextField1.setText(codBar.trim());
      
        this.jLabel8.setText(arti.getDescripcionArticulo());
        
        this.jTextField1.requestFocus();
        

        }

    }//GEN-LAST:event_jTable2KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if(KeyEvent.VK_ENTER==evt.getKeyCode()){
            Double descuentoGral=Numeros.ConvertirStringADouble(this.jTextField3.getText());
            descuentoGral=descuentoGral / 100;
            porcentajeDescuento=descuentoGral;
            /*
            Iterator it=detalleDelPedido.listIterator();
            Articulos art;
            Double precio=0.00;
            montoTotal=0.00;
            Double monto=0.00;
            
            while(it.hasNext()){
                art=(Articulos)it.next();
                art.setDescuento(1);
                
                precio=art.getPrecioUnitarioNeto() * descuentoGral;
                art.setMontoDescuento(precio * art.getCantidad());
                precio=art.getPrecioUnitarioNeto() - precio;
                art.setPrecioUnitarioNeto(precio);
                //art.setPrecioUnitario(precio);
                monto=art.getPrecioUnitarioNeto() * art.getCantidad();
                
                montoTotal=montoTotal + monto;
            }
                    */
            //cargarLista(detalleDelPedido);
            montrarMonto();
            agregarRenglonTabla();
            
        }
    }//GEN-LAST:event_jTextField3KeyPressed
private void cargarLista(ArrayList lista){
    DefaultTableModel modelo=new DefaultTableModel();
    Iterator il=lista.listIterator();
    Articulos art=new Articulos();
    modelo.addColumn("Descripcion");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        Object [] fila=new Object[3];
        while(il.hasNext()){
            art=(Articulos)il.next();
            fila[0]=art.getDescripcionArticulo();
            fila[1]=" $"+Numeros.ConvertirNumero(art.getPrecioUnitarioNeto());
            fila[2]=String.valueOf(art.getStockActual());
            //modelo.addElement(articulo.getDescripcionArticulo()+" $"+Numeros.ConvertirNumero(articulo.getPrecioUnitarioNeto()));
            modelo.addRow(fila);
        }
    
    
    this.jTable2.setModel(modelo);
            columnaCodigo=this.jTable2.getColumn("Descripcion");
        columnaCodigo.setPreferredWidth(600);
        columnaCodigo.setMaxWidth(600);
                columnaCodigo=this.jTable2.getColumn("Stock");
        columnaCodigo.setPreferredWidth(60);
        columnaCodigo.setMaxWidth(60);
}
private void agregarRenglonTabla(){
        MiModeloTablaFacturacion busC=new MiModeloTablaFacturacion();
        this.jTable1.removeAll();
        montoTotal=0.00;
        //ArrayList listadoPedidos=new ArrayList();
        this.jTable1.setModel(busC);
        Articulos pedidos;
        busC.addColumn("CODIGO");
        busC.addColumn("DESCRIPCION");
        busC.addColumn("COSTO");
        busC.addColumn("PRECIO UNITARIO S/IVA");
        busC.addColumn("CANTIDAD");
        busC.addColumn("PRECIO TOTAL");
        busC.addColumn("IVA");
        busC.addColumn("PRECIO FINAL");
        Object[] fila=new Object[8];
        Iterator irP=detalleDelPedido.listIterator();
        while(irP.hasNext()){
            pedidos=new Articulos();
            pedidos=(Articulos) irP.next();
            //fila[0]=pedidos.getCodigo();
            String codig=pedidos.getCodigoAsignado();
            String desc=pedidos.getDescripcionArticulo();
            String cant=String.valueOf(pedidos.getCantidad());
            
            fila[0]=codig;
            fila[1]=desc;
            Double precioUnitario=pedidos.getPrecioUnitarioNeto();
            
            //precioUnitario=precioUnitario * cliT.getCoeficienteListaDeprecios();
            
            Double valor=precioUnitario * pedidos.getCantidad();
            //precioUnitario= pedidos.getPrecioUnitario() * cliT.getCoeficienteListaDeprecios();
            //Double valor=(pedidos.getCantidad() * precioUnitario);
            //valor=valor * cliT.getCoeficienteListaDeprecios();
            pedidos.setPrecioUnitario(valor);
            String val=Numeros.ConvertirNumero(valor);
            montoTotal=montoTotal + valor;
            //precioUnitario=precioUnitario * cliT.getCoeficienteListaDeprecios();
            //fila[2]=cant;
            Double iva=0.00;
            Double pFinal=0.00;
            precioUnitario=precioUnitario / 1.21;
            pFinal=valor /1.21;
            iva=valor - pFinal;
            
            fila[5]=Numeros.ConvertirNumero(pFinal);//pFinal=valor / 1.21
            fila[3]=Numeros.ConvertirNumero(precioUnitario);//subtotal
            fila[2]=Numeros.ConvertirNumero(pedidos.getPrecioDeCosto());
            //Double iva=valor * 0.21;//valor - pFinal
            fila[6]=Numeros.ConvertirNumero(iva);
            fila[4]=cant;
            //Double pFinal=valor + iva;//valor - iva
            fila[7]=Numeros.ConvertirNumero(valor);//valor
            busC.addRow(fila);
        }
        subTotal=montoTotal / 1.21;
        Double ivv=subTotal *0.21;
        Double sub=subTotal;
        Double tot=subTotal + ivv;
        if(porcentajeDescuento > 0.00){
            sub = sub * porcentajeDescuento;
            sub= tot - sub;
        }
        fila[0]="";
        fila[1]="<html><strong>SUBTOTAL</strong></html>";
        fila[2]="";
        fila[3]="";
        fila[4]="";
        fila[5]="";
        fila[6]="";
        fila[7]="<html><strong>"+Numeros.ConvertirNumero(sub)+"</strong></html>";
        Double descuen=tot - (sub + ivv);
        busC.addRow(fila);
        fila[0]="";
        fila[1]="<html><strong>DESCUENTO </strong></html>";
        fila[2]="";
        fila[3]="";
        fila[4]="";
        fila[5]="";
        fila[6]="";
        fila[7]="<html><strong>"+Numeros.ConvertirNumero(descuen)+"</strong></html>";
        busC.addRow(fila);
        fila[0]="";
        fila[1]="<html><strong>TOTAL</strong></html>";
        fila[2]="";
        fila[3]="";
        fila[4]="";
        fila[5]="";
        fila[6]="";
        fila[7]="<html><strong>"+Numeros.ConvertirNumero(tot)+"</strong></html>";
        busC.addRow(fila);
        columnaCodigo=this.jTable1.getColumn("CODIGO");
        columnaCodigo.setPreferredWidth(80);
        columnaCodigo.setMaxWidth(80);
        columnaCodigo=this.jTable1.getColumn("DESCRIPCION");
        columnaCodigo.setPreferredWidth(350);
        //columnaCodigo.setMaxWidth(400);
        columnaCodigo.setMinWidth(300);
        columnaCodigo=this.jTable1.getColumn("CANTIDAD");
        columnaCodigo.setPreferredWidth(80);
        columnaCodigo.setMaxWidth(80);
        //montoTotal=montoTotal * 1.21;
        String total=String.valueOf(montoTotal);
        this.jLabel1.setText("TOTAL COTIZACION:  "+total);
        listadoDeBusqueda.clear();
        cargarLista(listadoDeBusqueda);
        this.jCheckBox1.setSelected(true);
        this.jCheckBox1.setVisible(false);
        if(detalleDelPedido.size()==0){
            this.jButton1.setEnabled(false);
        }else{
            this.jButton1.setEnabled(true);
        }
}
private void montrarMonto(){
    //System.err.println("DESCUENTO :"+cliT.getDescuento());
    Double total=montoTotal;
    subTotal = montoTotal;
    //Double total=montoTotal * cliT.getDescuento();
    //comp.setMontoTotal(total);
    this.jLabel1.setText("<html>TOTAL COTIZACION:  "+Numeros.ConvertirNumero(total)+"</html>");
}
private void verificar(){
    int cantidad=this.jTable1.getRowCount();
    Articulos art=new Articulos();
    cantidad=cantidad - 1;
    for(int ah=0;ah < cantidad;ah++){
        
        art=(Articulos)detalleDelPedido.get(ah);
        //ah++;
        String descripcion=(String) this.jTable1.getValueAt(ah,1);
        art.setDescripcionArticulo(descripcion);
        String cant=String.valueOf(this.jTable1.getValueAt(ah, 2));
        Double cantida=Double.valueOf(cant).doubleValue();
        art.setCantidad(cantida);
        Double precioUni=(Double) this.jTable1.getValueAt(ah,3);
        Double tot=precioUni;
        art.setPrecioUnitario(tot);
        //montoTotal=montoTotal + tot;
        //System.err.println("nimero "+ah+" decripcion "+descripcion+" limite "+cantidad);
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    public static javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    public static javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
