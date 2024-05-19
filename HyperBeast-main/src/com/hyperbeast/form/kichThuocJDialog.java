/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.hyperbeast.form;

import com.hyperbeast.model.sanPhamModel;
import com.hyperbeast.utils.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class kichThuocJDialog extends javax.swing.JDialog {

    /**
     * Creates new form kichThuocJDialog
     */
    int pageNumber;
    int page = 1;
    int pageSelect;
    sanPhamModel spmodel = new sanPhamModel();
    ArrayList listkt;
    public kichThuocJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        trangThaiCB.setSelectedIndex(0);
        getPage();
        statusPage();
        loadData(pageSelect);
    }

    void statusPage() {
        listkt = spmodel.getMaKT();
        pageNumber = (int) Math.ceil((listkt.size()/5.0));
        pageLbl.setText(page + "/" + pageNumber);
    }
    
    void getPage() {
        pageSelect = (page - 1) * 5;
    }
    
    void loadData(int pageSelect) {
        DefaultTableModel model = (DefaultTableModel) kichThuocTbl.getModel();
        model.setRowCount(0);
        String trangThai = null;
        String query = "select * from SIZE\n" +
                        "order by MaSIZE\n" +
                        "offset ? row\n" +
                        "fetch next 5 ROWS ONLY";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, pageSelect);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {  
                if(rs.getInt("TrangThai") == 1) {
                    trangThai = "Hoạt động";
                } else {
                    trangThai = "Không hoạt động";
                }
                Object[] data = {
                  rs.getInt("MaSize"),
                  rs.getString("KichThuoc"), 
                  trangThai
                };
                model.addRow(data);
            }
        } catch (SQLException e) {
        }
    }
    
    void validateData( int choice) {
        String kichThuoc = kichThuocTxt.getText();
        if (kichThuoc.isEmpty() || kichThuoc.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập kích thước hoặc chỉ chứa dấu cách");
            return;
        } else {
            
            try {
                int test = Integer.parseInt(kichThuocTxt.getText());
                if(test <= 0) {
                    JOptionPane.showMessageDialog(this, "Kích thước phải lớn hơn 0");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Kích thước phải nhập số");
                return;
            }
        }
        
        if(choice == 1) {
            insertKichThuoc();
        } 
        if(choice == 2) {
            updateKichThuoc();
        }    
         
    }
    
    void insertKichThuoc() {
        String query = "INSERT INTO SIZE(KichThuoc,TrangThai)\n" +
                        "VALUES(?,?)";
        int trangThai;
        if(trangThaiCB.getSelectedItem().equals("Hoạt động")) {
            trangThai = 1;
        } else {
            trangThai =0;
        }
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, kichThuocTxt.getText());
            pstmt.setInt(2, trangThai);
            pstmt.execute();
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            statusPage();
            loadData(pageSelect);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }
    
    void updateKichThuoc() {
        String query = "UPDATE SIZE\n" +
                        "set KichThuoc = ?, TrangThai = ?\n" +
                        "where MaSize = ?";
        int trangThai;
        int maKT;
        int selectedRow = kichThuocTbl.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Chưa chọn dòng cập nhật");
            return;
        } else {
            maKT = (int) kichThuocTbl.getValueAt(selectedRow, 0);
        }
        if(trangThaiCB.getSelectedItem().equals("Hoạt động")) {
            trangThai = 1;
        } else {
            trangThai =0;
        }
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, kichThuocTxt.getText());
            pstmt.setInt(2, trangThai);
            pstmt.setInt(3, maKT);
            pstmt.execute();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
            statusPage();
            loadData(pageSelect);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
        }
    }
    
    void getDataRow() {
        int selectedRow = kichThuocTbl.getSelectedRow();
        kichThuocTxt.setText((String) kichThuocTbl.getValueAt(selectedRow, 1));
        trangThaiCB.setSelectedItem(kichThuocTbl.getValueAt(selectedRow, 2));
    }
    
    void clearForm() {
        kichThuocTxt.setText("");
        trangThaiCB.setSelectedIndex(0);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        kichThuocTbl = new javax.swing.JTable();
        kichThuocTxt = new com.hyperbeast.swing.TextField();
        trangThaiCB = new com.hyperbeast.swing.Combobox();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pageLbl = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        kichThuocTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã size", "Kích Thước", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        kichThuocTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kichThuocTblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(kichThuocTbl);

        kichThuocTxt.setLabelText("Kích thước");

        trangThaiCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hoạt động", "Không hoạt động" }));
        trangThaiCB.setSelectedIndex(-1);
        trangThaiCB.setLabeText("Trạng thái");

        jButton3.setBackground(new java.awt.Color(0, 102, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Mới");
        jButton3.setBorderPainted(false);
        jButton3.setRequestFocusEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Thêm");
        jButton1.setBorderPainted(false);
        jButton1.setRequestFocusEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cập nhật");
        jButton2.setBorderPainted(false);
        jButton2.setRequestFocusEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Kích Thước");

        pageLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pageLbl.setText("Trang");

        jButton5.setBackground(new java.awt.Color(0, 102, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("<<");
        jButton5.setBorderPainted(false);
        jButton5.setRequestFocusEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText(">>");
        jButton4.setBorderPainted(false);
        jButton4.setRequestFocusEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pageLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kichThuocTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(trangThaiCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addGap(0, 6, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(kichThuocTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(trangThaiCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(pageLbl)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kichThuocTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kichThuocTblMouseClicked
        // TODO add your handling code here:
        getDataRow();
    }//GEN-LAST:event_kichThuocTblMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        validateData(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        validateData(2);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if(page <= pageNumber) {
            if(page == 1) {
                return;
            }
            page--;
            getPage();
            statusPage();
            loadData(pageSelect);

        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if(page >= 1 && page < pageNumber) {
            page++;
            getPage();
            statusPage();
            loadData(pageSelect);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(kichThuocJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kichThuocJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kichThuocJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kichThuocJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                kichThuocJDialog dialog = new kichThuocJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable kichThuocTbl;
    private com.hyperbeast.swing.TextField kichThuocTxt;
    private javax.swing.JLabel pageLbl;
    private com.hyperbeast.swing.Combobox trangThaiCB;
    // End of variables declaration//GEN-END:variables
}
