/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGraficas;

import Articulos.Articulos;
import Articulos.Modificable;
import ListasDePrecios.Articulable;
import ListasDePrecios.ArticulosAsignados;
import Clientes.Objectos.Clientes;
import static facturacion.pantallas.IngresoDeFacturas.cliT;
import static facturacion.pantallas.IngresoDeFacturas.jTextField1;
import interfacesPrograma.Facturar;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.table.TableColumn;

/**
 *
 * @author mauro
 */
public class ListadoDeArticulos2 extends javax.swing.JDialog {
    public static Articulos articulo;
    private ArrayList listadoDeBusqueda;
    private ArrayList listadoSubRubros;
    private ArrayList listadoR;
    private TableColumn columnaCodigo;
    private Clientes cliT;
    /**
     * Creates new form ListadoDeArticulos
     */
    public ListadoDeArticulos2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public ListadoDeArticulos2() {
        initComponents();
    }
    
    ListadoDeArticulos2(ArrayList listado1,ArrayList listado2,ArrayList listado3,Clientes ccl) {
        initComponents();
        articulo=new Articulos();
        //listadoDeBusqueda=listado;
        listadoSubRubros=listado1;
        listadoR=listado2;
        listadoDeBusqueda=listado3;
        cliT=ccl;
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listado de Articulos");
        setModal(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel1.setText("Descripcion");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_F1){
            //System.out.println("ENTRO CON F1¡¡¡¡¡");
            String valorCargado=jTextField1.getText();
        Facturar fart=new Articulos();
        this.jTable1.removeAll();
            Modificable modiA=new Articulos();
            Articulable modi=new ArticulosAsignados();
            listadoDeBusqueda.clear();
            listadoDeBusqueda=modi.convertirListadoEnArticulos(modi.filtradorDeFormularios(listadoSubRubros, listadoR, cliT,this.jTextField1.getText()));
            //listadoDeBusqueda=modi.filtrador(listadoSubRubros,listadoR);
            this.jTable1.setModel(modiA.mostrarListadoBusqueda(listadoDeBusqueda));
            columnaCodigo=this.jTable1.getColumn("Descripcion");
        columnaCodigo.setPreferredWidth(600);
        columnaCodigo.setMaxWidth(600);
                columnaCodigo=this.jTable1.getColumn("Stock");
        columnaCodigo.setPreferredWidth(60);
        columnaCodigo.setMaxWidth(60);
            this.jTable1.requestFocus();
        }else{
            Facturar fart=new Articulos();
            this.jTable1.removeAll();
            Modificable modiA=new Articulos();
            Articulable modi=new ArticulosAsignados();
            listadoDeBusqueda.clear();
            listadoDeBusqueda=modi.convertirListadoEnArticulos(modi.filtradorDeFormularios(listadoSubRubros, listadoR, cliT,this.jTextField1.getText()));
            //listadoDeBusqueda=modi.filtrador(listadoSubRubros,listadoR);
            this.jTable1.setModel(modiA.mostrarListadoBusqueda(listadoDeBusqueda));
            columnaCodigo=this.jTable1.getColumn("Descripcion");
        columnaCodigo.setPreferredWidth(600);
        columnaCodigo.setMaxWidth(600);
                columnaCodigo=this.jTable1.getColumn("Stock");
        columnaCodigo.setPreferredWidth(60);
        columnaCodigo.setMaxWidth(60);
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
       if(KeyEvent.VK_ENTER==evt.getKeyCode()){
           int posicion=jTable1.getSelectedRow();
           articulo=(Articulos) listadoDeBusqueda.get(posicion);
           this.dispose();
       }
    }//GEN-LAST:event_jTable1KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListadoDeArticulos2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListadoDeArticulos2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListadoDeArticulos2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListadoDeArticulos2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListadoDeArticulos2 dialog = new ListadoDeArticulos2(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
