/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes.Pantallas;

import Conversores.Numeros;
import Excel.InformesClientes;
import facturacion.clientes.Clientes;
import interfaceGraficas.Inicio;
import interfacesPrograma.Busquedas;
import interfacesPrograma.Facturar;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tablas.MiModeloTablaArticulos;

/**
 *
 * @author mauro
 */
public class AbmClientes extends javax.swing.JInternalFrame {
    private ArrayList listadoClientes=new ArrayList();
    Clientes cliente;
    /**
     * Creates new form AbmClientes
     */
    public AbmClientes() {
        initComponents();
        cliente=new Clientes();
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
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateChooserCombo3 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo4 = new datechooser.beans.DateChooserCombo();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setTitle("MODIFICACION DE CLIENTES");

        MiModeloTablaArticulos miTabla=new MiModeloTablaArticulos();
        Busquedas bus=new Clientes();
        listadoClientes=bus.listar("");
        Iterator listC=listadoClientes.listIterator();
        miTabla.addColumn("COD CLIENTE");
        miTabla.addColumn("RAZON SOCIAL");
        miTabla.addColumn("DIRECCION");
        miTabla.addColumn("TELEFONO");
        miTabla.addColumn("CUPO DE CREDITO");
        miTabla.addColumn("SALDO");
        miTabla.addColumn("LISTA DE PRECIO");
        Object[] fila=new Object[7];
        Clientes cliente=new Clientes();
        while(listC.hasNext()){
            cliente=(Clientes)listC.next();
            fila[0]=cliente.getCodigoId();
            fila[1]=cliente.getRazonSocial();
            fila[2]=cliente.getDireccion();
            fila[3]=cliente.getTelefono();
            fila[4]=cliente.getCupoDeCredito();
            if(cliente.getSaldo() !=null){
                fila[5]=cliente.getSaldo();
            }else{
                fila[5]="0.00";
            }
            fila[6]=cliente.getListaDePrecios();
            miTabla.addRow(fila);
        }
        jTable1.setModel(miTabla);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Guardar");
        jButton1.setMaximumSize(new java.awt.Dimension(101, 39));
        jButton1.setMinimumSize(new java.awt.Dimension(101, 39));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Desde :");

        jLabel4.setText("Hasta :");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/excel_icone.png"))); // NOI18N
        jButton3.setText("Informe de Clientes");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateChooserCombo4, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(dateChooserCombo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jButton2.setText("Ver Detalle de Saldo");
        jButton2.setMaximumSize(new java.awt.Dimension(101, 39));
        jButton2.setMinimumSize(new java.awt.Dimension(101, 39));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int posicion=this.jTable1.getSelectedRow();
        Clientes cliente=new Clientes();
        cliente=(Clientes)listadoClientes.get(posicion);
        cliente.setRazonSocial(String.valueOf(this.jTable1.getValueAt(posicion,1)));
        cliente.setDireccion(String.valueOf(this.jTable1.getValueAt(posicion, 2)));
        cliente.setTelefono(String.valueOf(this.jTable1.getValueAt(posicion,3)));
        cliente.setCupoDeCredito(Numeros.ConvertirStringADouble(String.valueOf(this.jTable1.getValueAt(posicion,4))));
        Double sal=Numeros.ConvertirStringADouble(String.valueOf(this.jTable1.getValueAt(posicion,5)));
        Double saldoOriginal=0.00;
        Double ajuste=0.00;
        if(sal != cliente.getSaldo()){
            if(cliente.getSaldo() !=null){
                saldoOriginal=cliente.getSaldo();
            }
                ajuste=sal - saldoOriginal;
        }
        if(ajuste == 0){
            
        }else{
            if(JOptionPane.showConfirmDialog(this,"Genera Movimiento de Ajuste de Saldo?","Aplicar Ajuste de Saldo",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==1){
                
            }else{
                cliente.ajustarSaldo(cliente, ajuste);
            }
            
        }
        if(cliente.getCupoDeCredito()>0){
            cliente.setCondicionDeVenta(2);
        }else{
            cliente.setCondicionDeVenta(1);
        }
        //cliente.setSaldo(Numeros.ConvertirStringADouble(String.valueOf(this.jTable1.getValueAt(posicion,5))));
        cliente.setListaDePrecios((Integer.parseInt(String.valueOf(this.jTable1.getValueAt(posicion,6)))));
        Facturar fact=new Clientes();
        fact.modificarDatosDelCliente(cliente);
        //this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DecimalFormat fr=new DecimalFormat("00");
        DecimalFormat formato=new DecimalFormat("####.##");
        //SiderconCapaatos.listaPedidos.clear();
        SimpleDateFormat dia=new SimpleDateFormat("dd/mm/yyyy");
        //Date mes=Calendar.getInstance().getTime();
        //dateChooserCombo1.setDateFormat(dia);
        Calendar fechaNueva=dateChooserCombo3.getSelectedDate();
        Calendar fechaHasta=dateChooserCombo4.getSelectedDate();
        //mes=dia.format(fechaNueva,null,null);
        Double pesoDia=0.00;
        int ano=fechaNueva.get(Calendar.YEAR);
        int mes=fechaNueva.get(Calendar.MONTH);
        mes++;
        int dd=fechaNueva.get(Calendar.DAY_OF_MONTH);
        String fecha1=ano+"-"+mes+"-"+dd;
        ano=fechaHasta.get(Calendar.YEAR);
        mes=fechaHasta.get(Calendar.MONTH);
        mes++;
        dd=fechaHasta.get(Calendar.DAY_OF_MONTH);
        String fecha2=ano+"-"+mes+"-"+dd;

        InformesClientes informes=new InformesClientes();
        /*
        try {

            informes.GenerarInforme(listadoClientes,fecha1,fecha2);
        } catch (SQLException ex) {
            Logger.getLogger(AbmClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int posicion=this.jTable1.getSelectedRow();
        cliente=(Clientes) listadoClientes.get(posicion);
        AbmSaldosClientes abm=new AbmSaldosClientes(cliente);
        Inicio.jDesktopPane1.add(abm);
        abm.setVisible(true);
        abm.toFront();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo3;
    private datechooser.beans.DateChooserCombo dateChooserCombo4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
