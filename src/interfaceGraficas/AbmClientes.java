/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGraficas;

import Articulos.Rubrable;
import Conversores.Numeros;
import Cotizaciones.IngresoDeCotizacion;
import Excel.InformesClientes;
import Excel.LeerExcelClientes;
import facturacion.clientes.Clientes;
import facturacion.pantallas.IngresoDeFacturas;
import Pedidos.IngresoDePedidos;
import interfaces.Adeudable;
import interfaces.Personalizable;
import interfacesPrograma.Busquedas;
import interfacesPrograma.Facturar;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import objetos.Localidades;
import tablas.MiModeloTablaArticulos;
import tablas.MiModeloTablaBuscarCliente;

/**
 *
 * @author mauro
 */
public class AbmClientes extends javax.swing.JInternalFrame {
    private ArrayList listadoClientes=new ArrayList();
    private TableColumn columnaCodigo;
    private ArrayList listadoLoc;
    /**
     * Creates new form AbmClientes
     */
    public AbmClientes() {
        initComponents();
        listadoLoc=new ArrayList();
        columnaCodigo=this.jTable1.getColumn("COD CLIENTE");
        columnaCodigo.setPreferredWidth(30);
        Personalizable per=new Localidades();
        Rubrable rub=new Localidades();
        
        listadoLoc=per.listar();
        this.jComboBox1.setModel(rub.mostrarEnBox(listadoLoc));
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
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();

        setClosable(true);
        setMaximizable(true);
        setTitle("Clientes");
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

        MiModeloTablaArticulos miTabla=new MiModeloTablaArticulos();
        Busquedas bus=new facturacion.clientes.Clientes();
        listadoClientes=bus.listar("");
        Iterator listC=listadoClientes.listIterator();
        miTabla.addColumn("COD CLIENTE");
        miTabla.addColumn("RAZON SOCIAL");
        miTabla.addColumn("DIRECCION");
        miTabla.addColumn("TELEFONO");
        miTabla.addColumn("LOCALIDAD");
        miTabla.addColumn("CONTACTO");
        miTabla.addColumn("CUIT");
        miTabla.addColumn("ID IVA");
        miTabla.addColumn("COND IVA");
        Object[] fila=new Object[9];
        facturacion.clientes.Clientes cliente=new facturacion.clientes.Clientes();
        while(listC.hasNext()){
            cliente=(facturacion.clientes.Clientes)listC.next();
            fila[0]=cliente.getCodigoId();
            fila[1]=cliente.getRazonSocial();
            fila[2]=cliente.getDireccion();
            fila[3]=cliente.getTelefono();
            fila[4]=cliente.getLocalidad();
            fila[5]=cliente.getResponsable();
            fila[6]=cliente.getNumeroDeCuit();
            fila[7]=cliente.getTipoIva();
            fila[8]=cliente.getCondicionIva();
            miTabla.addRow(fila);
        }
        jTable1.setModel(miTabla);
        columnaCodigo=this.jTable1.getColumn("COD CLIENTE");
        columnaCodigo.setPreferredWidth(30);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Buscar por Nombre - Contacto - Nombre de Fantasía");

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

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/black_folder_search.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/excel_icone.png"))); // NOI18N
        jButton3.setText("Listado de Clientes");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setText("Filtrar por Localidad");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/folder_new.png"))); // NOI18N
        jMenu1.setText("Nuevo Cliente");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/currency_black_dollar.png"))); // NOI18N
        jMenu2.setText("Saldo");
        jMenuBar1.add(jMenu2);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/metacontact_offline.png"))); // NOI18N
        jMenu5.setText("Perfil");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/sub_black_delete.png"))); // NOI18N
        jMenu6.setText("Eliminar Cliente");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DecimalFormat fr=new DecimalFormat("00");
        DecimalFormat formato=new DecimalFormat("####.####");
        //SiderconCapaatos.listaPedidos.clear();
        SimpleDateFormat dia=new SimpleDateFormat("dd/mm/yyyy");
        //Date mes=Calendar.getInstance().getTime();
        //dateChooserCombo1.setDateFormat(dia);
        //Calendar fechaNueva=dateChooserCombo3.getSelectedDate();
        //Calendar fechaHasta=dateChooserCombo4.getSelectedDate();
        //mes=dia.format(fechaNueva,null,null);
        
        
        InformesClientes informes=new InformesClientes();
        try {
            informes.GenerarInforme(listadoClientes);
        } catch (SQLException ex) {
            Logger.getLogger(AbmClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        NuevoCliente clienteNuevo=new NuevoCliente();
        Inicio.jDesktopPane1.add(clienteNuevo);
        try {
            clienteNuevo.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AbmClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        clienteNuevo.setVisible(true);
        clienteNuevo.toFront();
    }//GEN-LAST:event_jMenu1MouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
       
    }//GEN-LAST:event_formComponentShown

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

    }//GEN-LAST:event_formFocusGained

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
                 this.jTable1.removeAll();
        MiModeloTablaArticulos miTabla=new MiModeloTablaArticulos();
Busquedas bus=new Clientes();
listadoClientes=bus.listar("");
Iterator listC=listadoClientes.listIterator();
miTabla.addColumn("COD CLIENTE");
miTabla.addColumn("RAZON SOCIAL");
miTabla.addColumn("DIRECCION");
miTabla.addColumn("TELEFONO");
miTabla.addColumn("LOCALIDAD");
miTabla.addColumn("CONTACTO");
miTabla.addColumn("NOM. FANTASIA");
miTabla.addColumn("CELULAR");
miTabla.addColumn("COND IVA");

Object[] fila=new Object[9];
Clientes cliente=new Clientes();
while(listC.hasNext()){
    cliente=(Clientes)listC.next();
    fila[0]=cliente.getCodigoId();
fila[1]=cliente.getRazonSocial();
fila[2]=cliente.getDireccion();
fila[3]=cliente.getTelefono();
fila[4]=cliente.getLocalidad();
fila[5]=cliente.getResponsable();
fila[6]=cliente.getFantasia();
fila[7]=cliente.getCelular();
fila[8]=cliente.getCondicionIva();
miTabla.addRow(fila);
}

jTable1.setModel(miTabla);
columnaCodigo=this.jTable1.getColumn("COD CLIENTE");
        columnaCodigo.setPreferredWidth(30);
    }//GEN-LAST:event_formInternalFrameActivated

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        /*
        ACA VAN A IR LOS DISTINTOS EVENTOS SI SE PRESIONAN LAS F
        
        */
    }//GEN-LAST:event_formKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                String nombre=jTextField1.getText();
        Clientes resCli=new Clientes();
        Busquedas mcli=new Clientes();
        listadoClientes.clear();
        //ArrayList resultado=new ArrayList();
        listadoClientes=mcli.listar(nombre.toUpperCase());
        int cant=listadoClientes.size();
        //Iterator ir=resultado.listIterator();
        //this.jPanel2.setVisible(true);
        cargarTabla();
        //this.jList1.setModel(modelo);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        Clientes clienteTango=new Clientes();
        clienteTango=(Clientes)listadoClientes.get(this.jTable1.getSelectedRow());
        NuevoCliente clienteNuevo=new NuevoCliente(clienteTango);
        Inicio.jDesktopPane1.add(clienteNuevo);
        clienteNuevo.setTitle(clienteTango.getRazonSocial());
        try {
            clienteNuevo.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AbmClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        clienteNuevo.setVisible(true);
        clienteNuevo.toFront();
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    String nombre=jTextField1.getText();
        Clientes resCli=new Clientes();
        Busquedas mcli=new Clientes();
        listadoClientes.clear();
        //ArrayList resultado=new ArrayList();
        listadoClientes=mcli.listar(nombre.toUpperCase());
        int cant=listadoClientes.size();
        //Iterator ir=resultado.listIterator();
        //this.jPanel2.setVisible(true);
        cargarTabla();
        //this.jList1.setModel(modelo);
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        if(JOptionPane.showConfirmDialog(this,"Seguro de Eliminar este cliente?","Eliminar Cliente",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==1){
            
        }else{
            Clientes clienteTango=new Clientes();
            clienteTango=(Clientes)listadoClientes.get(this.jTable1.getSelectedRow());
            Busquedas bus=new Clientes();
            bus.eliminar(clienteTango.getCodigoId());
            this.dispose();
        }
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Integer posic=this.jComboBox1.getSelectedIndex();
        Localidades localidad;
        localidad=(Localidades) listadoLoc.get(posic);
        Clientes resCli=new Clientes();
        Adeudable mcli=new Clientes();
        listadoClientes.clear();
        //ArrayList resultado=new ArrayList();
        listadoClientes=mcli.ListarPorLocalidad(localidad.getId());
        int cant=listadoClientes.size();
        //Iterator ir=resultado.listIterator();
        //this.jPanel2.setVisible(true);
        cargarTabla();
    }//GEN-LAST:event_jButton1ActionPerformed
private void cargarTabla(){
        MiModeloTablaBuscarCliente busC=new MiModeloTablaBuscarCliente();
        this.jTable1.removeAll();
        //ArrayList listadoPedidos=new ArrayList();
        
        Clientes pedidos=new Clientes();
        busC.addColumn("COD CLIENTE");
busC.addColumn("RAZON SOCIAL");
busC.addColumn("DIRECCION");
busC.addColumn("TELEFONO");
busC.addColumn("LOCALIDAD");
busC.addColumn("CONTACTO");
busC.addColumn("NOM. FANTASIA");
busC.addColumn("CELULAR");
busC.addColumn("COND IVA");
Object[] fila=new Object[9];
Iterator irP=listadoClientes.listIterator();
while(irP.hasNext()){
    pedidos=(Clientes) irP.next();
    fila[0]=pedidos.getCodigoId();
fila[1]=pedidos.getRazonSocial();
fila[2]=pedidos.getDireccion();
fila[3]=pedidos.getTelefono();
fila[4]=pedidos.getLocalidad();
fila[5]=pedidos.getResponsable();
fila[6]=pedidos.getFantasia();
fila[7]=pedidos.getCelular();
fila[8]=pedidos.getCondicionIva();
busC.addRow(fila);
}
this.jTable1.setModel(busC);
columnaCodigo=this.jTable1.getColumn("COD CLIENTE");
        columnaCodigo.setPreferredWidth(30);
      /*  
        Clientes pedidos=new Clientes();
        busC.addColumn("CODIGO CLIENTE");
        busC.addColumn("RAZON SOCIAL");
        busC.addColumn("DIRECCION");
        busC.addColumn("RESPONSABLE");
        busC.addColumn("TELEFONO");
        busC.addColumn("LOCALIDAD");
        busC.addColumn("LISTA DE PRECIOS");
        busC.addColumn("FORMA DE PAGO");
        Object[] fila=new Object[8];
        Iterator irP=listadoClientes.listIterator();
        while(irP.hasNext()){
            pedidos=(Clientes) irP.next();
            fila[0]=pedidos.getCodigoCliente();
            fila[1]=pedidos.getRazonSocial();
            fila[2]=pedidos.getDireccion();
            fila[3]=pedidos.getResponsable();
            fila[4]=pedidos.getTelefono();
            fila[5]=pedidos.getLocalidad();
            fila[6]=pedidos.getListaDePrecios();
            fila[7]=pedidos.getCondicionDeVenta();
            busC.addRow(fila);
        }
        */

}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
