package uwe.asGUI;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import uwe.as.Hall;
import uwe.as.Room;
import uwe.as.RoomApplication;
import uwe.as.User;

/**
 *
 * @author Jamie Mills (16004255)
 */
public class ViewApplications extends javax.swing.JFrame {
    public static uwe.as.Data_Cache data_cache;
    private MainScreen mainScreen;
    /**
     * Creates new form CreateApplication
     */
    public ViewApplications(MainScreen mainScreen) {
        initComponents();
        this.mainScreen = mainScreen;
        updateTable();
        this.setVisible(true);
    }
    
    private void updateTable()
    {
        List<RoomApplication> applications = data_cache.getApplications();
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[11];
        
        for (RoomApplication a : applications)
        {
            Room currentRoom = data_cache.getRoom(a.getRoomUID());
            User currentStudent = data_cache.getUser(a.getStudentUID());
            Hall currentHall = null;
            if (currentRoom != null && currentStudent != null)
            {
                currentHall = data_cache.getHall(currentRoom.getHallUID());
                if (currentHall != null)
                {
                    row[0] = currentHall.getName();
                    row[1] = currentRoom.getNumber();
                    row[2] = a.getDate();
                    row[3] = a.getDuration();
                    row[4] = currentRoom.getRate();
                    row[5] = currentStudent.getRealName();
                    row[6] = currentStudent.getStudentNumber();
                    row[7] = a.getUID();
                    row[8] = currentHall.getUID();
                    row[9] = currentRoom.getUID();
                    row[10] = currentStudent.getUID();
                    model.addRow(row);
                }
            }
        }
        
    }
    
    private RoomApplication getCurrentApplication()
    {
        int selectedRow = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        return data_cache.getApplication(Integer.parseInt(model.getValueAt(selectedRow, 7).toString()));
    }
    
    private Hall getCurrentHall()
    {
        int selectedRow = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        return data_cache.getHall(Integer.parseInt(model.getValueAt(selectedRow, 8).toString()));
    }
    
    private Room getCurrentRoom()
    {
        int selectedRow = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        return data_cache.getRoom(Integer.parseInt(model.getValueAt(selectedRow, 9).toString()));
    }
    
    private User getCurrentStudent()
    {
        int selectedRow = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        return data_cache.getUser(Integer.parseInt(model.getValueAt(selectedRow, 10).toString()));
    }
    
    private void updateDetails()
    {
        Hall currentHall = getCurrentHall();
        Room currentRoom = getCurrentRoom();
        User currentStudent = getCurrentStudent();
        RoomApplication currentApplication = getCurrentApplication();
        
        if (currentHall != null)
        {
            textbox_HallName.setText(currentHall.getName());
            textbox_HallNumber.setText(currentHall.getNumber());
        }
        if (currentRoom != null)
        {
            textbox_RoomNumber.setText(currentRoom.getNumber());
            textbox_RoomRate.setText(Integer.toString(currentRoom.getRate()));
        }
        if (currentStudent != null)
        {
            textbox_StudentName.setText(currentStudent.getRealName());
            textbox_StudentNumber.setText(currentStudent.getStudentNumber());
            textbox_StudentEmail.setText(currentStudent.getEmailAddress());
        }
        if (currentApplication != null)
        {
            DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            textbox_StartDate.setText(df.format(currentApplication.getDate()));
            textbox_Duration.setText(Integer.toString(currentApplication.getDuration()));
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

        label_HallName = new javax.swing.JLabel();
        button_Approve = new javax.swing.JButton();
        button_Close = new javax.swing.JButton();
        button_Deny = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        textbox_HallName = new javax.swing.JTextField();
        label_HallNumber = new javax.swing.JLabel();
        label_RoomNumber = new javax.swing.JLabel();
        label_RoomRate = new javax.swing.JLabel();
        label_StudentName = new javax.swing.JLabel();
        label_StudentNumber = new javax.swing.JLabel();
        label_StudentEmail = new javax.swing.JLabel();
        label_StartDate = new javax.swing.JLabel();
        label_Duration = new javax.swing.JLabel();
        textbox_HallNumber = new javax.swing.JTextField();
        textbox_RoomNumber = new javax.swing.JTextField();
        textbox_RoomRate = new javax.swing.JTextField();
        textbox_StudentName = new javax.swing.JTextField();
        textbox_StudentNumber = new javax.swing.JTextField();
        textbox_StudentEmail = new javax.swing.JTextField();
        textbox_StartDate = new javax.swing.JTextField();
        textbox_Duration = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label_HallName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_HallName.setText("Hall Name");

        button_Approve.setText("Approve");
        button_Approve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ApproveActionPerformed(evt);
            }
        });

        button_Close.setText("Close");
        button_Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_CloseActionPerformed(evt);
            }
        });

        button_Deny.setText("Deny");
        button_Deny.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_DenyActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hall", "Room", "Start Date", "Duration", "Rate", "Student", "Student Number", "Application UID", "Hall UID", "Room UID", "User UID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(7).setMinWidth(0);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(7).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(8).setMinWidth(0);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(9).setMinWidth(0);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(9).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(10).setMinWidth(0);
            jTable1.getColumnModel().getColumn(10).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(10).setMaxWidth(0);
        }

        textbox_HallName.setEditable(false);
        textbox_HallName.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_HallName.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_HallName.setPreferredSize(new java.awt.Dimension(100, 20));

        label_HallNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_HallNumber.setText("Hall Number");

        label_RoomNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_RoomNumber.setText("Room Number");

        label_RoomRate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_RoomRate.setText("Room Rate");

        label_StudentName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_StudentName.setText("Student Name");

        label_StudentNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_StudentNumber.setText("Student Number");

        label_StudentEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_StudentEmail.setText("Student Email");

        label_StartDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_StartDate.setText("Start Date");

        label_Duration.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Duration.setText("Duration");

        textbox_HallNumber.setEditable(false);
        textbox_HallNumber.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_HallNumber.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_HallNumber.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_RoomNumber.setEditable(false);
        textbox_RoomNumber.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_RoomNumber.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_RoomNumber.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_RoomRate.setEditable(false);
        textbox_RoomRate.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_RoomRate.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_RoomRate.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_StudentName.setEditable(false);
        textbox_StudentName.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_StudentName.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_StudentName.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_StudentNumber.setEditable(false);
        textbox_StudentNumber.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_StudentNumber.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_StudentNumber.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_StudentEmail.setEditable(false);
        textbox_StudentEmail.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_StudentEmail.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_StudentEmail.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_StartDate.setEditable(false);
        textbox_StartDate.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_StartDate.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_StartDate.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_Duration.setEditable(false);
        textbox_Duration.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Duration.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Duration.setPreferredSize(new java.awt.Dimension(100, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(label_HallName)
                            .addGap(57, 57, 57)
                            .addComponent(textbox_HallName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(label_HallNumber)
                                .addComponent(label_RoomNumber)
                                .addComponent(label_RoomRate)
                                .addComponent(label_StudentName)
                                .addComponent(label_StudentNumber)
                                .addComponent(label_StudentEmail)
                                .addComponent(label_StartDate)
                                .addComponent(label_Duration))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(textbox_HallNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textbox_RoomNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(textbox_RoomRate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(textbox_StudentName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(textbox_StudentNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(textbox_StudentEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(textbox_StartDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(textbox_Duration, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button_Approve)
                        .addGap(18, 18, 18)
                        .addComponent(button_Deny)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button_Close)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textbox_HallName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_HallName))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_HallNumber)
                            .addComponent(textbox_HallNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_RoomNumber)
                            .addComponent(textbox_RoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_RoomRate)
                            .addComponent(textbox_RoomRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_StudentName)
                            .addComponent(textbox_StudentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_StudentNumber)
                            .addComponent(textbox_StudentNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_StudentEmail)
                            .addComponent(textbox_StudentEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_StartDate)
                            .addComponent(textbox_StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Duration)
                            .addComponent(textbox_Duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button_Approve)
                            .addComponent(button_Deny)
                            .addComponent(button_Close))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_ApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ApproveActionPerformed
        RoomApplication currentApplication = getCurrentApplication();
        currentApplication.approveApplication();
        updateTable();
    }//GEN-LAST:event_button_ApproveActionPerformed

    private void button_DenyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_DenyActionPerformed
        RoomApplication currentApplication = getCurrentApplication();
        currentApplication.refuseApplication();
        updateTable();
    }//GEN-LAST:event_button_DenyActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        updateDetails();
    }//GEN-LAST:event_jTable1MouseClicked

    private void button_CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_CloseActionPerformed
        mainScreen.viewApplicationsClosing();
        this.dispose();
    }//GEN-LAST:event_button_CloseActionPerformed

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
            java.util.logging.Logger.getLogger(CreateApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Approve;
    private javax.swing.JButton button_Close;
    private javax.swing.JButton button_Deny;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel label_Duration;
    private javax.swing.JLabel label_HallName;
    private javax.swing.JLabel label_HallNumber;
    private javax.swing.JLabel label_RoomNumber;
    private javax.swing.JLabel label_RoomRate;
    private javax.swing.JLabel label_StartDate;
    private javax.swing.JLabel label_StudentEmail;
    private javax.swing.JLabel label_StudentName;
    private javax.swing.JLabel label_StudentNumber;
    private javax.swing.JTextField textbox_Duration;
    private javax.swing.JTextField textbox_HallName;
    private javax.swing.JTextField textbox_HallNumber;
    private javax.swing.JTextField textbox_RoomNumber;
    private javax.swing.JTextField textbox_RoomRate;
    private javax.swing.JTextField textbox_StartDate;
    private javax.swing.JTextField textbox_StudentEmail;
    private javax.swing.JTextField textbox_StudentName;
    private javax.swing.JTextField textbox_StudentNumber;
    // End of variables declaration//GEN-END:variables
}
