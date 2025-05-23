/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package manager;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/**
 *
 * @author kacpe
 */
public class ShowPasswords extends javax.swing.JFrame {
    private int userId;

    /**
     * Creates new form ShowPasswords
     */
    public ShowPasswords(int userId) throws IOException, FileNotFoundException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableEntryException, CertificateException {
        this.userId = userId;
        initComponents();
        this.setResizable(false);
        loadData();
    }
    
    private void loadData() throws IOException, FileNotFoundException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableEntryException, CertificateException{
        ConnectionToDb conn = new ConnectionToDb();
        List<Password> passwords;
        
        try{
            passwords = conn.table(userId);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            for(Password pass : passwords){
                String alias = Integer.toString(userId);
                SecretKey sk = KeyStoreDb.loadKey(alias);
                String vectorString = pass.getVector();
                byte[] iv = Base64.getDecoder().decode(vectorString);
                GCMParameterSpec vector = new GCMParameterSpec(128, iv);
                
                String plainText = Hash.aesDecrypt(pass.getPassword(), sk, "AES/GCM/NoPadding" , vector);
                
                
                String dot = "*".repeat(plainText.length());
                model.addRow(new Object[]{pass.getWebsite(), pass.getLogin(), dot});
                
                System.out.println(plainText);
                
            }
        }
         catch (Exception e) {
                System.out.println(e);
        }
        
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(720, 480));
        setMinimumSize(new java.awt.Dimension(720, 480));
        setPreferredSize(new java.awt.Dimension(720, 480));

        jPanel1.setBackground(java.awt.Color.darkGray);
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jTable1.setBackground(java.awt.Color.darkGray);
        jTable1.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        jTable1.setForeground(java.awt.Color.lightGray);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Website", "Login", "Password", "Show"
            }
        ));
        jTable1.setRowHeight(30);
        jTable1.setSelectionForeground(java.awt.Color.lightGray);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(3).setMinWidth(20);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(20);
        }

        jButton1.setText("Back");
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
                .addContainerGap(145, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(314, 314, 314))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(jButton1)
                .addGap(74, 74, 74))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         ConnectionToDb conn = new ConnectionToDb();
         
        int column = jTable1.columnAtPoint(evt.getPoint());
        int row = jTable1.rowAtPoint(evt.getPoint());
        
        if(column == 2){
            String login = jTable1.getValueAt(row, 1).toString();
            String website = jTable1.getValueAt(row, 0).toString();
            
             try {
                 for(Password pass : conn.table(userId)){
                     if(pass.getLogin().equals(login) && pass.getWebsite().equals(website) ){
                         try{
                             String alias = Integer.toString(userId);
                             SecretKey sk = KeyStoreDb.loadKey(alias);
                             String vectorString = pass.getVector();
                             byte[] iv = Base64.getDecoder().decode(vectorString);
                             GCMParameterSpec vector = new GCMParameterSpec(128, iv);
                             
                             String plainText = Hash.aesDecrypt(pass.getPassword(), sk, "AES/GCM/NoPadding" , vector);
                             
                             StringSelection selection = new StringSelection(plainText);
                             Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                             clipboard.setContents(selection, selection);
                         } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException | BadPaddingException | IOException | KeyStoreException | UnrecoverableEntryException | CertificateException ex) {
                             Logger.getLogger(ShowPasswords.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     }
                 }} catch (ClassNotFoundException ex) {
                 Logger.getLogger(ShowPasswords.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        if(column == 3){
            String login = jTable1.getValueAt(row, 1).toString();
            String website = jTable1.getValueAt(row, 0).toString();
            String text = jTable1.getValueAt(row, 2).toString();
            
            try{
            for(Password pass : conn.table(userId)){
                if(pass.getLogin().equals(login)  && pass.getWebsite().equals(website)){
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    if(!text.contains("*")){
                        model.setValueAt("*".repeat(text.length()), row, 2);
                    }
                    else{
                    try{
                             String alias = Integer.toString(userId);
                             SecretKey sk = KeyStoreDb.loadKey(alias);
                             String vectorString = pass.getVector();
                             byte[] iv = Base64.getDecoder().decode(vectorString);
                             GCMParameterSpec vector = new GCMParameterSpec(128, iv);
                             
                             String plainText = Hash.aesDecrypt(pass.getPassword(), sk, "AES/GCM/NoPadding" , vector);
                             
                             
                             model.setValueAt(plainText, row, 2);
                         } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException | BadPaddingException | IOException | KeyStoreException | UnrecoverableEntryException | CertificateException ex) {
                             Logger.getLogger(ShowPasswords.class.getName()).log(Level.SEVERE, null, ex);
                         }
                }
              }
            }
        }    catch (ClassNotFoundException ex) {
                 Logger.getLogger(ShowPasswords.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        MainPanel mainpanel = new MainPanel(userId);            
        this.setVisible(false);
        mainpanel.setVisible(true);
        mainpanel.pack();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ShowPasswords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowPasswords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowPasswords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowPasswords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowPasswords().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
