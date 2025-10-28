package MainFolder.interfaz;

import MainFolder.dao.MaterialObjeto;
import MainFolder.modelos.DVD;
import MainFolder.modelos.Material;
import MainFolder.util.Validacion;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class CrudDVD extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CrudDVD.class.getName());
    private MaterialObjeto dao = new MaterialObjeto();
    private DefaultTableModel modelo;

    public CrudDVD() {
        initComponents();
        this.setLocationRelativeTo(null);//CENTRAR VENTANA
        modelo = (DefaultTableModel) tabla.getModel();
        listar();
        tabla.setRowSelectionAllowed(true);//SELECCIONAR TODA UNA FILA

        //VALIDACIONES
        Validacion.permitirSolo(txtGenero, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáéíóúÁÉÍÓÚñÑ0123456789 ", 255);
        Validacion.permitirSolo(txtUD, "0123456789", 5);
        Validacion.permitirSolo(txtTitulo, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáéíóúÁÉÍÓÚñÑ0123456789 ", 255);
        Validacion.permitirSolo(txtDirector, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáéíóúÁÉÍÓÚñÑ0123456789 ", 100);
        //OCULTAR COLUMNAS
        TableColumnModel tcm = tabla.getColumnModel();
        TableColumn columna = tcm.getColumn(0);
        tcm.removeColumn(columna);

        tabla.setRowSelectionAllowed(true);
        tabla.setColumnSelectionAllowed(false);
        txtId.setVisible(false);

        try {
            javax.swing.text.MaskFormatter timeMask = new javax.swing.text.MaskFormatter("##:##:##");
            timeMask.setPlaceholderCharacter('0');
            txtDuracion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(timeMask));
        } catch (ParseException ex) {
            System.getLogger(CrudCD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        Validacion.permitirSolo(txtDuracion, "0123456789", 10);
    }

    private void filtrarTabla(String texto) {
        modelo.setRowCount(0);
        List<Material> lista = dao.listarMateriales();
        for (Material m : lista) {
            if ("dvd".equals(m.getTipo())) {
                DVD l = (DVD) m;
                if (l.getCodigo().toLowerCase().contains(texto.toLowerCase())
                        || l.getTitulo().toLowerCase().contains(texto.toLowerCase())
                        || l.getDirector().toLowerCase().contains(texto.toLowerCase())) {
                    modelo.addRow(new Object[]{
                        l.getId(),
                        l.getCodigo(),
                        l.getTitulo(),
                        l.getUnidadesDisponibles(),
                        l.getDirector(),
                        l.getGenero(),
                        l.getDuracion()
                    });
                }
            }
        }
    }

    private boolean validarCampos() {
        if (txtTitulo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El título es obligatorio.");
            return false;
        }
        if ("00:00:00".equals(txtDuracion.getText().trim())) {
            JOptionPane.showMessageDialog(this, "La duracion es obligatoria.");
            return false;
        }
        if (txtGenero.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El género es obligatorio.");
            return false;
        }
        if (txtDirector.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El artista es obligatorio.");
            return false;
        }
        if (txtUD.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Las unidades disponibles es obligatorio.");
            return false;
        }
        return true;
    }

    private void listar() {
        modelo.setRowCount(0); // Limpiar tabla
        List<Material> lista = dao.listarMateriales();
        for (Material m : lista) {
            if ("dvd".equals(m.getTipo())) {
                DVD l = (DVD) m;

                modelo.addRow(new Object[]{
                    l.getId(),
                    l.getCodigo(),
                    l.getTitulo(),
                    l.getUnidadesDisponibles(),
                    l.getDirector(),
                    l.getGenero(),
                    l.getDuracion()
                });

            }
        }
    }

    public static String inglesAespanol(String fecha) {
        return LocalDate.parse(fecha).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtCodigo.setText("");
        txtTitulo.setText("");
        txtDirector.setText("");
        txtUD.setText("");
        txtGenero.setText("");
        txtDuracion.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        txtGenero = new javax.swing.JTextField();
        txtId = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigo1 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        txtTitulo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtUD = new javax.swing.JTextField();
        lb = new javax.swing.JLabel();
        txtDirector = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDuracion = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setText("Género");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        txtId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtId.setText("Id");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Buscar por título, código.");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("GESTIÓN DE DVD");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtCodigo1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCodigo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCodigo1.setText("Código");

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jLabel9.setText("Título");

        lb.setText("Unidades disponibles");

        jLabel2.setText("Director");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "", "Código", "Título", "Disponibles", "Director", "Género", "Duración"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setColumnSelectionAllowed(true);
        tabla.setRowHeight(40);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);
        tabla.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel5.setText("Duración");

        txtCodigo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCodigo.setText("DVD00001");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAgregar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(188, 188, 188)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(46, 46, 46))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(75, 75, 75)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUD, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(263, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(lb)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtUD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(txtCodigo1)
                            .addGap(3, 3, 3)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDirector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(28, 28, 28))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        filtrarTabla(txtBuscar.getText().trim());
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        ME ventana = new ME();
        ventana.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            int id = (Integer) modelo.getValueAt(fila, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este dvd?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (dao.eliminarMaterial(id)) {
                    JOptionPane.showMessageDialog(this, "CD eliminado correctamente.");
                    limpiarCampos();
                    listar();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el dvd.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un dvd de la tabla para eliminar.");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            if (validarCampos()) {
                DVD dvd = new DVD();
                dvd.setId(id);
                dvd.setCodigo(txtCodigo.getText().trim());
                dvd.setTitulo(txtTitulo.getText().trim());
                dvd.setDirector(txtDirector.getText().trim());
                dvd.setUnidadesDisponibles((Integer) Integer.parseInt(txtUD.getText().trim()));
                dvd.setGenero(txtGenero.getText().trim());

                String duracionStr = txtDuracion.getText().trim();
                java.sql.Time duracion = java.sql.Time.valueOf(duracionStr);
                dvd.setDuracion(duracion);

                dvd.setTipo("dvd");

                if (dao.modificarMaterial(dvd)) {
                    JOptionPane.showMessageDialog(this, "DVD modificada correctamente.");
                    listar();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al modificar la dvd.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Seleccione una dvd de la tabla para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {

            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                txtId.setText(modelo.getValueAt(fila, 0).toString());
                txtCodigo.setText(modelo.getValueAt(fila, 1).toString());
                txtTitulo.setText(modelo.getValueAt(fila, 2).toString());
                txtUD.setText(modelo.getValueAt(fila, 3).toString());
                txtDirector.setText(modelo.getValueAt(fila, 4).toString());
                txtGenero.setText(modelo.getValueAt(fila, 5).toString());
                txtDuracion.setText(modelo.getValueAt(fila, 6).toString());
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        if (validarCampos()) {
            DVD dvd = new DVD();
            dvd.setCodigo(txtCodigo.getText().trim());
            dvd.setTitulo(txtTitulo.getText().trim());
            dvd.setDirector(txtDirector.getText().trim());
            dvd.setUnidadesDisponibles((Integer) Integer.parseInt(txtUD.getText().trim()));
            dvd.setGenero(txtGenero.getText().trim());

            String duracionStr = txtDuracion.getText().trim();
            java.sql.Time duracion = java.sql.Time.valueOf(duracionStr);
            dvd.setDuracion(duracion);

            dvd.setTipo("dvd");

            if (dao.agregarMaterial(dvd)) {
                JOptionPane.showMessageDialog(this, "DVD agregado correctamente.");
                limpiarCampos();
                listar();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar el dvd.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new CrudDVD().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JLabel txtCodigo;
    private javax.swing.JLabel txtCodigo1;
    private javax.swing.JTextField txtDirector;
    private javax.swing.JFormattedTextField txtDuracion;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JLabel txtId;
    private javax.swing.JTextField txtTitulo;
    private javax.swing.JTextField txtUD;
    // End of variables declaration//GEN-END:variables
}
