/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recibos;

import Conversores.Numeros;
import Pedidos.Pedidos;
import Proveedores.Proveedores;
import Clientes.Objectos.Clientes;
import Pedidos.Pedable;
import facturacion.clientes.MovimientoProveedores;
import interfaceGraficas.Inicio;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro di
 */
public class AbmRecibos extends javax.swing.JDialog {
    private DefaultTableModel modelo4=new DefaultTableModel();
    private ArrayList detallePagos=new ArrayList();
    FormasDePago pago;
    private Double montoTotal;
    private Double saldo;
    private ArrayList listadoFc=new ArrayList();
    private DefaultListModel modeloL=new DefaultListModel();
    private String banco;
    private String vencimiento;
    private Clientes cli;
    private Proveedores cliP;
    private int tipoFc;//para saber si son pedidos=1

    
    /**
     * Creates new form AbmRecibos
     */
    public AbmRecibos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Ingresar Pago Operación - Vendedor Asignado: "+Inicio.usuario.getNombre());
        Recidable reci=new DetalleRecibo();
        modelo4=reci.mostrarARecibir(listadoFc);
        this.jTable1.setModel(modelo4);
        this.jLabel2.setText(" $"+montoTotal);
        saldo=montoTotal;
        this.jLabel9.setText("Saldo: $"+saldo);
    }
    public AbmRecibos(java.awt.Frame parent, boolean modal,Clientes cliente) {
        super(parent, modal);
        initComponents();
        this.setTitle("Ingresar Pago Operación - Vendedor Asignado: "+Inicio.usuario.getNombre());
        cli=(Clientes)cliente;
        Pedable pedable=new Pedidos();
        Pedidos pedidos=new Pedidos();
        tipoFc=1;
        listadoFc=pedable.listarPorEstado(cli.getCodigoId(),0);
        montoTotal=cli.getSaldo();
        Recidable reci=new DetalleRecibo();
        modelo4=reci.mostrarARecibir(listadoFc);
        this.jTable1.setModel(modelo4);
        this.jLabel2.setText(" $"+montoTotal);
        saldo=montoTotal;
        this.jLabel9.setText("Saldo: $"+saldo);
    }

    public AbmRecibos(java.awt.Frame parent, boolean modal,ArrayList listado,Double monto,Clientes cliente) {
        super(parent, modal);
        initComponents();
        this.setTitle("Ingresar Pago Operación - Vendedor Asignado: "+Inicio.usuario.getNombre());
        cli=(Clientes)cliente;
        Recidable reci=new DetalleRecibo();
        listadoFc=listado;
        montoTotal=monto;
        modelo4=reci.mostrarARecibir(listadoFc);
        this.jTable1.setModel(modelo4);
        this.jLabel2.setText(" $"+montoTotal);
        saldo=montoTotal;
        this.jLabel9.setText("Saldo: $"+saldo);
    }
    public AbmRecibos(ArrayList listado,Double monto,Proveedores cliente) {
        initComponents();
        this.setTitle("Ingresar Pago Operación - Vendedor Asignado: "+Inicio.usuario.getNombre());
        cliP=(Proveedores)cliente;
        Recidable reci=new DetalleRecibo();
        listadoFc=listado;
        montoTotal=monto;
        modelo4=reci.mostrarARecibir(listadoFc);
        this.jTable1.setModel(modelo4);
        this.jLabel2.setText(" $"+montoTotal);
        saldo=montoTotal;
        this.jLabel9.setText("Saldo: $"+saldo);
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
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(modelo4);
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTable1FocusLost(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel10.setText("<html>F1 para eliminar de la lista los ítems desmarcados<br>F2 para que calcule el valor total</html>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setText("Se va a generar un Recibo por un total de :");

        jLabel2.setText("jLabel2");

        jLabel3.setText("Medio de Pago:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Efectivo", "Débito", "Crédito", "Cheque", "Transferencia Bancaria" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jLabel4.setText("Detalle:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosNuevos/Floopy.png"))); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Monto: ");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel6.setText("Banco: ");

        jLabel7.setText("Vencimiento: ");

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-mm-dd"))));
        jFormattedTextField1.setToolTipText("aaaa-mm-dd");
        jFormattedTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextField1KeyPressed(evt);
            }
        });

        jLabel8.setText("N° Ch");

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jLabel9.setText("Saldo :");

        jLabel11.setText("Formato de Vencimiento: aaaa-mm-dd (año-mes-dia)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jFormattedTextField1))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6))
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusLost
        
    }//GEN-LAST:event_jTable1FocusLost

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            pago=new FormasDePago();
            
            Double monto=0.00;
            monto=Numeros.ConvertirStringADouble(this.jTextField1.getText());
            pago.setMonto(monto);
            int fPago=this.jComboBox1.getSelectedIndex();
            pago.setId(fPago);
            switch(fPago){
                case 0:
                    //EFECTIVO
                    
                    pago.setDescripcion("Efectivo");
                pago.setMonto(monto);
                detallePagos.add(pago);
                modeloL.addElement(pago.getDescripcion()+" $"+pago.getMonto());
                this.jList1.setModel(modeloL);
                saldo=saldo - pago.getMonto();
                this.jLabel9.setText("Saldo: "+saldo);
                this.jTextField1.setText("");
                this.jComboBox1.requestFocus();
                    break;
                case 1:
                    //DEBITO
                     pago.setDescripcion("Débito");
                pago.setMonto(monto);
                pago.setDescripcion(JOptionPane.showInputDialog("Ingrese código de autorizacion. Gracias"));
                detallePagos.add(pago);
                modeloL.addElement(pago.getDescripcion()+" $"+pago.getMonto());
                this.jList1.setModel(modeloL);
                saldo=saldo - pago.getMonto();
                this.jLabel9.setText("Saldo: "+saldo);
                this.jTextField1.setText("");
                this.jComboBox1.requestFocus();
                    break;
                case 2:
                    //CREDITO
                     pago.setDescripcion("Crédito");
                pago.setMonto(monto);
                pago.setDescripcion(JOptionPane.showInputDialog("Ingrese código de autorizacion. Gracias"));
                detallePagos.add(pago);
                modeloL.addElement(pago.getDescripcion()+" $"+pago.getMonto());
                this.jList1.setModel(modeloL);
                saldo=saldo - pago.getMonto();
                this.jLabel9.setText("Saldo: "+saldo);
                this.jTextField1.setText("");
                this.jComboBox1.requestFocus();
                    break;
                case 3:
                    //CHEQUE
                    pago.setDescripcion("Cheque");
                this.jTextField2.selectAll();
                this.jTextField2.requestFocus();
                    break;
                case 4:
                    //TRANSFERENCIA
                     pago.setDescripcion("Transferencia");
                pago.setMonto(monto);
                detallePagos.add(pago);
                modeloL.addElement(pago.getDescripcion()+" $"+pago.getMonto());
                this.jList1.setModel(modeloL);
                saldo=saldo - pago.getMonto();
                this.jLabel9.setText("Saldo: "+saldo);
                this.jTextField1.setText("");
                this.jComboBox1.requestFocus();
                    break;
                default:
                     pago.setDescripcion("Efectivo");
                pago.setMonto(monto);
                detallePagos.add(pago);
                modeloL.addElement(pago.getDescripcion()+" $"+pago.getMonto());
                this.jList1.setModel(modeloL);
                saldo=saldo - pago.getMonto();
                this.jLabel9.setText("Saldo: "+saldo);
                this.jTextField1.setText("");
                this.jComboBox1.requestFocus();
                    break;
            }
            
            /*
            if(this.jComboBox1.getSelectedIndex()==1){
                pago.setDescripcion("Cheque");
                this.jTextField2.selectAll();
                this.jTextField2.requestFocus();
                
            }else{
                pago.setDescripcion("Efectivo");
                pago.setMonto(monto);
                detallePagos.add(pago);
                modeloL.addElement(pago.getDescripcion()+" $"+pago.getMonto());
                this.jList1.setModel(modeloL);
                saldo=saldo - pago.getMonto();
                this.jLabel9.setText("Saldo: "+saldo);
                this.jTextField1.setText("");
                this.jComboBox1.requestFocus();
            }
            */
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            pago.setBanco(this.jTextField2.getText());
            this.jTextField3.selectAll();
            this.jTextField3.requestFocus();
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jFormattedTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            pago.setVencimiento(this.jFormattedTextField1.getText());
            detallePagos.add(pago);
            modeloL.addElement(pago.getBanco()+" $"+pago.getMonto());
            this.jList1.setModel(modeloL);
            saldo=saldo - pago.getMonto();
            this.jLabel9.setText("Saldo: "+saldo);
            this.jTextField1.setText("");
            this.jTextField2.setText("");
            this.jTextField3.setText("");
            this.jComboBox1.requestFocus();
            
        }
    }//GEN-LAST:event_jFormattedTextField1KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            pago.setNumero(this.jTextField3.getText());
            this.jFormattedTextField1.selectAll();
            this.jFormattedTextField1.requestFocus();
        }
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        int seleccion=this.jList1.getSelectedIndex();
        detallePagos.remove(seleccion);
        Iterator itD=detallePagos.listIterator();
        modeloL.clear();
        while(itD.hasNext()){
            pago=new FormasDePago();
            pago=(FormasDePago)itD.next();
            modeloL.addElement(pago.getBanco()+" $"+pago.getMonto());
            
        }
        this.jList1.setModel(modeloL);
        this.jComboBox1.requestFocus();
    }//GEN-LAST:event_jList1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        OrdenDePago recibo=new OrdenDePago();
        DetalleRecibo detalle;
        Recidable det=new DetalleRecibo();
        recibo.setIdCliente(cli.getCodigoId());
        recibo.setMonto(montoTotal);
        Recidable recc=new OrdenDePago();
        int numero=0;
        numero=recc.nuevo(recibo);
        recibo.setId(numero);
        Iterator itF=listadoFc.listIterator();
        //int cantRecibos=listadoFc.size();
        ArrayList listadoDet=new ArrayList();
        Pedidos factura;
        int contador=0;
        Double saldoAImputar=montoTotal - saldo;
        while(itF.hasNext()){
            
            factura=(Pedidos)itF.next();
            if((Boolean)this.jTable1.getValueAt(contador,0)){
            detalle=new DetalleRecibo();
            detalle.setIdCliente(cli.getCodigoId());
            detalle.setIdFactura(factura.getId());
            
            detalle.setIdRecibo(recibo.getId());
            
            if(factura.getTotal() < saldoAImputar){
                detalle.setMonto(factura.getTotal());
            }else{
                //factura.setTotal(saldoAImputar);
                
                detalle.setMonto(saldoAImputar);
            }
            saldoAImputar=saldoAImputar - factura.getTotal();
            detalle.setFecha(factura.getFecha());
            if(factura.getIdFactura()!=null){
            detalle.setNumeroFc(factura.getIdFactura());
            }else{
                
                    
                    detalle.setNumeroFc(factura.getId());
                
            }
            detalle.setMontoFcatura(Numeros.ConvertirNumero(factura.getTotal()));
            det.nuevo(detalle);
            if(tipoFc==1){
                
            }else{
                det.imputarAFactura(factura);
            }
            listadoDet.add(detalle);
            }
            contador++;
        }
        //ACA CARGO LAS FORMAS DE PAGO SI ES EFECTIVO MOV DE CAJA, SINO CHEQUES
        Iterator itP=detallePagos.listIterator();
        Formable ff=new FormasDePago(); 
        Double montt=0.00;
        while(itP.hasNext()){
            pago=(FormasDePago)itP.next();
            pago.setIdCliente(cli.getCodigoId());
            pago.setIdRecibo(recibo.getId());
            pago.setIdUsuario(Inicio.usuario.getNumeroId());
            //pago.setNumero(banco);
            if(pago.getDescripcion().equals("Cheque")){
               // mando a formable
                ff.guardarCheques(pago);
            }else{
                // mando a movimientos caja
                switch(pago.getId()){
                    case 0:
                        ff.guardarEfectivo(pago);
                        break;
                    case 1:
                        ff.guardarDebito(pago);
                        break;
                    case 2:
                        ff.guardarCredito(pago);
                        break;
                }
                
            }
            montt=montt + pago.getMonto();
        }
        //if(montt >= recibo.getMonto()){
            recibo.setMonto(montt);
            
        //}
        ImprimirOrden imprimir=new ImprimirOrden();
        try {
            imprimir.ImprimirOrdenDeTrabajo(recibo, listadoDet, detallePagos);
        } catch (IOException ex) {
            Logger.getLogger(AbmRecibos.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_F1){
            int cantidad=this.jTable1.getRowCount();
        Double total=0.00;
        Double parte=0.00;
        Pedidos factu;
        Recidable reci=new DetalleRecibo();
        ArrayList aEliminar=new ArrayList();
        for(int a=0;a < cantidad;a++){
            if((Boolean)this.jTable1.getValueAt(a, 0)){
                parte=Numeros.ConvertirStringADouble((String) this.jTable1.getValueAt(a, 4));
                factu=(Pedidos)listadoFc.get(a);
                //factu.setEstado(1);
                factu.setTotal(parte);
                total=total + parte;
            }else{
                //listadoFc.remove(a);
                aEliminar.add(a);
            }
            
        }
        Iterator iEl=aEliminar.listIterator();
        int orden=0;
        while(iEl.hasNext()){
            orden=(int)iEl.next();
            listadoFc.remove(orden);
        }
        
        modelo4=reci.mostrarARecibir(listadoFc);
                this.jTable1.setModel(modelo4);
        
        }
        if(evt.getKeyCode()==KeyEvent.VK_F2){
            int cantidad=this.jTable1.getRowCount();
        Double total=0.00;
        Double parte=0.00;
        Pedidos factu;
        Recidable reci=new DetalleRecibo();
        ArrayList aEliminar=new ArrayList();
        for(int a=0;a < cantidad;a++){
            if((Boolean)this.jTable1.getValueAt(a, 0)){
                parte=Numeros.ConvertirStringADouble((String) this.jTable1.getValueAt(a, 4));
                factu=(Pedidos)listadoFc.get(a);
                //factu.setEstado(1);
                factu.setTotal(parte);
                total=total + parte;
            }
            
        }
        montoTotal=total;
        saldo=montoTotal;
        jLabel2.setText(" $"+total);
        jLabel9.setText("Saldo: "+saldo);
        this.jTextField1.setText(String.valueOf(total));
        this.jComboBox1.requestFocus();
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        int posicion=this.jComboBox1.getSelectedIndex();
        String mensaje;
        Double tasa=1.00;
        int cuotas=0;
        switch(posicion){
            case 1:
                mensaje="Ingrese por favor recargo por Pago con Débito";
                tasa=Numeros.ConvertirStringADouble(JOptionPane.showInputDialog(mensaje));
                cuotas=1;
                tasa=(tasa / 100) +1;
                saldo=saldo * tasa;
                break;
            case 2:
                mensaje="Ingrese por favor recargo por Pago con tarjeta de Crédito";
                tasa=Numeros.ConvertirStringADouble(JOptionPane.showInputDialog(mensaje));
                cuotas=Integer.parseInt(JOptionPane.showInputDialog("Cantidad de Cuotas?"));
                tasa=(tasa / 100) +1;
                saldo=saldo * tasa;
                break;
            default:
                break;
        }
        
        this.jTextField1.setText(String.valueOf(saldo));
        this.jTextField1.requestFocus();
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(AbmRecibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AbmRecibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AbmRecibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AbmRecibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AbmRecibos dialog = new AbmRecibos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
