/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGraficas;

import Articulos.Articulos;
import Articulos.Modificable;
import Articulos.Rubrable;
import Articulos.Rubros;
import Articulos.SubRubros;
import Conversores.Numeros;
import ListasDePrecios.Articulable;
import ListasDePrecios.ArticulosAsignados;
import facturacion.clientes.Clientes;
import static facturacion.pantallas.IngresoDeFacturas.cliT;
import static facturacion.pantallas.IngresoDeFacturas.jTextField1;
import interfacesPrograma.Facturar;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author mauro
 */
public class StockAbm extends javax.swing.JInternalFrame {
    private TableColumn columnaCodigo;
    private static ArrayList listadoDeBusqueda;
    private ArrayList listadoB;
    private Rubros rubro=new Rubros();
    private SubRubros subRubro;
    private ArrayList listadoSubRubros;
    private Rubrable ruble=new Rubros();
    private ArrayList listadoR=new ArrayList();
    private DefaultComboBoxModel combox=new DefaultComboBoxModel();
    private String valorCargado;
    private Articulos articuloPadre;
    private Articulos articuloHijo;
    private Clientes cliT;
    private DefaultTableModel modelo;
    private Modificable modiA;
    
    /**
     * Creates new form StockAbm
     */
    public StockAbm() {
        initComponents();
        listadoB=new ArrayList();
        cliT=new Clientes();
        cliT=(Clientes) cliT.cargarPorCodigoAsignado(448);
        modelo=new DefaultTableModel();
        modiA=new Articulos();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Ajuste de Stock");

        jLabel1.setText("Articulo a descontar");

        jLabel2.setText("Cantidad");

        jLabel3.setText("Articulo Resultante");

        jLabel4.setText("Cantidad");

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jButton1.setText("Procesar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable3);

        jLabel7.setText("Rubro");

        jTextField5.setText("jTextField5");
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
        });

        jLabel8.setText("Rubro");

        jTextField6.setText("jTextField6");
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addContainerGap())
        );

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
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            //listadoDeBusqueda.clear();
            rubro=new Rubros();
            Rubrable subRuble=new SubRubros();
            Iterator iR=listadoR.listIterator();
            while(iR.hasNext()){
                rubro=(Rubros)iR.next();
                listadoSubRubros=subRuble.listarPorRubro(rubro.getId());
            }
            Modificable modiA=new Articulos();
            Articulable modi=new ArticulosAsignados();
            listadoDeBusqueda=modi.convertirListadoEnArticulos(modi.filtrador(listadoSubRubros, listadoR, cliT));
            //listadoDeBusqueda=modi.filtrador(listadoSubRubros,listadoR);
            ListadoDeArticulos2 listadoArt=new ListadoDeArticulos2(listadoSubRubros,listadoR,listadoDeBusqueda,cliT);
            ListadoDeArticulos2.jTable1.setModel(modiA.mostrarListadoBusqueda(listadoDeBusqueda));
            //this.jTable3.setModel(modiA.mostrarListadoBusqueda(listadoDeBusqueda));
            listadoArt.setVisible(true);
            ListadoDeArticulos2.jTextField1.setText(null);
            ListadoDeArticulos2.jTextField1.requestFocus();
            articuloPadre=ListadoDeArticulos2.articulo;
            //listadoB.add(ListadoDeArticulos2.articulo);
            this.jLabel1.setText(articuloPadre.getDescripcionArticulo());
            this.jTextField2.setText(null);
            this.jTextField2.requestFocus();
            
        
        }else{
            String rub=this.jTextField5.getText();

            listadoR=ruble.buscar(rub);

            //this.jTable2.setModel(ruble.mostrarEnCombo(listadoR));
        }
    }//GEN-LAST:event_jTextField5KeyPressed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            listadoDeBusqueda.clear();
            rubro=new Rubros();
            Rubrable subRuble=new SubRubros();
            Iterator iR=listadoR.listIterator();
            while(iR.hasNext()){
                rubro=(Rubros)iR.next();
                listadoSubRubros=subRuble.listarPorRubro(rubro.getId());
            }
            Modificable modiA=new Articulos();
            Articulable modi=new ArticulosAsignados();
            listadoDeBusqueda=modi.convertirListadoEnArticulos(modi.filtrador(listadoSubRubros, listadoR, cliT));
            //listadoDeBusqueda=modi.filtrador(listadoSubRubros,listadoR);
            
            ListadoDeArticulos2 listadoArt=new ListadoDeArticulos2(listadoSubRubros,listadoR,listadoDeBusqueda,cliT);
            ListadoDeArticulos2.jTable1.setModel(modiA.mostrarListadoBusqueda(listadoDeBusqueda));
            //this.jTable3.setModel(modiA.mostrarListadoBusqueda(listadoDeBusqueda));
            listadoArt.setVisible(true);
            ListadoDeArticulos2.jTextField1.setText(null);
            ListadoDeArticulos2.jTextField1.requestFocus();
            articuloHijo=ListadoDeArticulos2.articulo;
            //listadoB.add(ListadoDeArticulos2.articulo);
            this.jLabel3.setText(articuloHijo.getDescripcionArticulo());
            this.jTextField4.setText(null);
            this.jTextField4.requestFocus();
            
           
        }else{
            String rub=this.jTextField6.getText();

            listadoR=ruble.buscar(rub);

            //this.jTable2.setModel(ruble.mostrarEnCombo(listadoR));
        }
    }//GEN-LAST:event_jTextField6KeyPressed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if(KeyEvent.VK_ENTER==evt.getKeyCode()){
            //listadoB.add(articuloPadre);
            articuloPadre.setCantidad(Numeros.ConvertirStringADouble(this.jTextField2.getText()));
            articuloPadre.setCantidad(articuloPadre.getCantidad() * -1);
            listadoB.add(articuloPadre);
            
            this.jTable3.setModel(modiA.mostrarModificacionStock(listadoB));
            columnaCodigo=this.jTable3.getColumn("Descripcion");
        columnaCodigo.setPreferredWidth(580);
        columnaCodigo.setMaxWidth(580);
                columnaCodigo=this.jTable3.getColumn("Cantidad");
        columnaCodigo.setPreferredWidth(70);
        columnaCodigo.setMaxWidth(70);
            this.jTextField6.setText(null);
            this.jTextField6.requestFocus();
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if(KeyEvent.VK_ENTER==evt.getKeyCode()){
            
            articuloHijo.setCantidad(Numeros.ConvertirStringADouble(this.jTextField4.getText()));
            listadoB.add(articuloHijo);
            this.jTable3.setModel(modiA.mostrarModificacionStock(listadoB));
            columnaCodigo=this.jTable3.getColumn("Descripcion");
        columnaCodigo.setPreferredWidth(580);
        columnaCodigo.setMaxWidth(580);
                columnaCodigo=this.jTable3.getColumn("Cantidad");
        columnaCodigo.setPreferredWidth(70);
        columnaCodigo.setMaxWidth(70);
            this.jTextField5.selectAll();
            this.jTextField5.requestFocus();
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Iterator it=listadoB.listIterator();
        Articulos artic=new Articulos();
        
        while(it.hasNext()){
            artic=(Articulos) it.next();
            modiA.cargarMovimientoDeAjuste(artic);
        }
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
