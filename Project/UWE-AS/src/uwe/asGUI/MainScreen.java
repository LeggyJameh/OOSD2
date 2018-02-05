/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uwe.asGUI;


import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author adw8
 */
public class MainScreen extends javax.swing.JFrame {

    /**
     * Creates new form MainScreen
     */
    public MainScreen() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        ms_seperator = new javax.swing.JPanel();
        ms_uwe_text = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        ms_btn_hallmanager = new javax.swing.JPanel();
        ms_ind_hallmanager = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ms_btn_warden = new javax.swing.JPanel();
        ms_ind_warden = new javax.swing.JPanel();
        ms_warden_text = new javax.swing.JLabel();
        ms_btn_all = new javax.swing.JPanel();
        ms_ind_all = new javax.swing.JPanel();
        ms_btn_all_text = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();

        jMenu1.setText("jMenu1");

        jMenu5.setText("jMenu5");

        jMenu6.setText("jMenu6");

        jMenu7.setText("jMenu7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(197, 196, 193));
        setLocationByPlatform(true);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");

        ms_seperator.setBackground(new java.awt.Color(247, 245, 242));

        ms_uwe_text.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        ms_uwe_text.setText("UWE Accomodation");

        jSeparator1.setBackground(new java.awt.Color(247, 245, 242));

        ms_btn_hallmanager.setBackground(new java.awt.Color(247, 245, 242));
        ms_btn_hallmanager.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ms_btn_hallmanagerMousePressed(evt);
            }
        });

        ms_ind_hallmanager.setBackground(new java.awt.Color(19, 19, 19));
        ms_ind_hallmanager.setOpaque(false);

        javax.swing.GroupLayout ms_ind_hallmanagerLayout = new javax.swing.GroupLayout(ms_ind_hallmanager);
        ms_ind_hallmanager.setLayout(ms_ind_hallmanagerLayout);
        ms_ind_hallmanagerLayout.setHorizontalGroup(
            ms_ind_hallmanagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        ms_ind_hallmanagerLayout.setVerticalGroup(
            ms_ind_hallmanagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel2.setText("Hall Manager");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout ms_btn_hallmanagerLayout = new javax.swing.GroupLayout(ms_btn_hallmanager);
        ms_btn_hallmanager.setLayout(ms_btn_hallmanagerLayout);
        ms_btn_hallmanagerLayout.setHorizontalGroup(
            ms_btn_hallmanagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ms_btn_hallmanagerLayout.createSequentialGroup()
                .addComponent(ms_ind_hallmanager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ms_btn_hallmanagerLayout.setVerticalGroup(
            ms_btn_hallmanagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ms_ind_hallmanager, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ms_btn_hallmanagerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        ms_btn_warden.setBackground(new java.awt.Color(247, 245, 242));
        ms_btn_warden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ms_btn_wardenMousePressed(evt);
            }
        });

        ms_ind_warden.setBackground(new java.awt.Color(19, 19, 19));
        ms_ind_warden.setOpaque(false);

        javax.swing.GroupLayout ms_ind_wardenLayout = new javax.swing.GroupLayout(ms_ind_warden);
        ms_ind_warden.setLayout(ms_ind_wardenLayout);
        ms_ind_wardenLayout.setHorizontalGroup(
            ms_ind_wardenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        ms_ind_wardenLayout.setVerticalGroup(
            ms_ind_wardenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        ms_warden_text.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        ms_warden_text.setText("Warden");

        javax.swing.GroupLayout ms_btn_wardenLayout = new javax.swing.GroupLayout(ms_btn_warden);
        ms_btn_warden.setLayout(ms_btn_wardenLayout);
        ms_btn_wardenLayout.setHorizontalGroup(
            ms_btn_wardenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ms_btn_wardenLayout.createSequentialGroup()
                .addComponent(ms_ind_warden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ms_warden_text, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ms_btn_wardenLayout.setVerticalGroup(
            ms_btn_wardenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ms_ind_warden, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ms_btn_wardenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ms_warden_text)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        ms_btn_all.setBackground(new java.awt.Color(247, 245, 242));
        ms_btn_all.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ms_btn_allMousePressed(evt);
            }
        });

        ms_ind_all.setBackground(new java.awt.Color(19, 19, 19));
        ms_ind_all.setOpaque(false);

        javax.swing.GroupLayout ms_ind_allLayout = new javax.swing.GroupLayout(ms_ind_all);
        ms_ind_all.setLayout(ms_ind_allLayout);
        ms_ind_allLayout.setHorizontalGroup(
            ms_ind_allLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        ms_ind_allLayout.setVerticalGroup(
            ms_ind_allLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        ms_btn_all_text.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        ms_btn_all_text.setText("All");

        javax.swing.GroupLayout ms_btn_allLayout = new javax.swing.GroupLayout(ms_btn_all);
        ms_btn_all.setLayout(ms_btn_allLayout);
        ms_btn_allLayout.setHorizontalGroup(
            ms_btn_allLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ms_btn_allLayout.createSequentialGroup()
                .addComponent(ms_ind_all, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ms_btn_all_text, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ms_btn_allLayout.setVerticalGroup(
            ms_btn_allLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ms_ind_all, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ms_btn_allLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ms_btn_all_text)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ms_seperatorLayout = new javax.swing.GroupLayout(ms_seperator);
        ms_seperator.setLayout(ms_seperatorLayout);
        ms_seperatorLayout.setHorizontalGroup(
            ms_seperatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ms_seperatorLayout.createSequentialGroup()
                .addGroup(ms_seperatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ms_seperatorLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(ms_uwe_text))
                    .addGroup(ms_seperatorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(ms_btn_warden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ms_btn_hallmanager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ms_btn_all, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ms_seperatorLayout.setVerticalGroup(
            ms_seperatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ms_seperatorLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(ms_uwe_text)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(ms_btn_hallmanager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(ms_btn_warden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(ms_btn_all, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );

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
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBackground(new java.awt.Color(247, 245, 242));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(ms_seperator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ms_seperator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MousePressed

    private void ms_btn_hallmanagerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ms_btn_hallmanagerMousePressed
        // TODO add your handling code here:
        setColor(ms_btn_hallmanager);
        resetColor(ms_btn_all);
            resetColor(ms_btn_warden);
            ms_ind_hallmanager.setOpaque(true);
            ms_ind_warden.setOpaque(false);
            ms_ind_all.setOpaque(false);
                
                    
    }//GEN-LAST:event_ms_btn_hallmanagerMousePressed

    private void ms_btn_wardenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ms_btn_wardenMousePressed
        // TODO add your handling code here:
         setColor(ms_btn_warden);
        resetColor(ms_btn_all);
            resetColor(ms_btn_hallmanager);
            ms_ind_warden.setOpaque(true);
            ms_ind_hallmanager.setOpaque(false);
            ms_ind_all.setOpaque(false);
    }//GEN-LAST:event_ms_btn_wardenMousePressed

    private void ms_btn_allMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ms_btn_allMousePressed
        // TODO add your handling code here:
         setColor(ms_btn_all);
        resetColor(ms_btn_hallmanager);
            resetColor(ms_btn_warden);
            ms_ind_hallmanager.setOpaque(false);
            ms_ind_warden.setOpaque(false);
            ms_ind_all.setOpaque(true);
    }//GEN-LAST:event_ms_btn_allMousePressed
    
    // loading smaple data
    void loadSampleData(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/keeptoo_all","root","");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select ' from students");
            
            
        
        
        
        
        } catch (Exception ex) {
   
        }
    }
    
    void setColor(JPanel panel){
        panel.setBackground(new Color(197,196,193));
    }
    
    void resetColor (JPanel panel){
        panel.setBackground(new Color(247,245,242));
    }
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
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel ms_btn_all;
    private javax.swing.JLabel ms_btn_all_text;
    private javax.swing.JPanel ms_btn_hallmanager;
    private javax.swing.JPanel ms_btn_warden;
    private javax.swing.JPanel ms_ind_all;
    private javax.swing.JPanel ms_ind_hallmanager;
    private javax.swing.JPanel ms_ind_warden;
    private javax.swing.JPanel ms_seperator;
    private javax.swing.JLabel ms_uwe_text;
    private javax.swing.JLabel ms_warden_text;
    // End of variables declaration//GEN-END:variables
}
