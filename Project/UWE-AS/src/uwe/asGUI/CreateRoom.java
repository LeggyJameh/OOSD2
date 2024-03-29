package uwe.asGUI;

import java.util.List;
import java.util.stream.Collectors;
import uwe.as.Hall;
import uwe.as.Room;

/**
 *
 * @author Jamie Mills (16004255)
 */
public class CreateRoom extends javax.swing.JFrame {

    public static uwe.as.Data_Cache data_cache;
    private List<Hall> halls;
    MainScreen mainScreen;

    /**
     * Creates new form CreateRoom
     */
    public CreateRoom(MainScreen mainScreen) {
        initComponents();
        importFromCache();
        populateHallBox();
        this.mainScreen = mainScreen;
        this.setVisible(true);
    }

    private void importFromCache() {
        this.halls = data_cache.getHalls();
    }

    private void populateHallBox() {
        comboBox_Hall.removeAllItems();
        for (Hall h : halls) {
            if (!h.getRooms().isEmpty()) {
                comboBox_Hall.addItem(h.getName());
            }
        }
    }

    private Hall getCurrentHall() {
        String currentHallName = comboBox_Hall.getItemAt(comboBox_Hall.getSelectedIndex());
        List<Hall> filteredHalls = halls.stream().filter(h -> h.getName().equals(currentHallName)).collect(Collectors.toList());
        if (!filteredHalls.isEmpty()) {
            return filteredHalls.get(0);
        }
        return null;
    }

    private void getAllFieldsAndSubmit() {
        String number = textfield_Number.getText();
        int rate = Integer.parseInt(textfield_Rate.getText());
        Hall currentHall = getCurrentHall();

        if (currentHall != null) {
            new Room(number, rate, currentHall.getUID());
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

        labelHall = new javax.swing.JLabel();
        labelRoom = new javax.swing.JLabel();
        labelStudent = new javax.swing.JLabel();
        buttonCancel = new javax.swing.JButton();
        buttonAdd = new javax.swing.JButton();
        labelTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        textfield_Rate = new javax.swing.JFormattedTextField();
        comboBox_Hall = new javax.swing.JComboBox<>();
        textfield_Number = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create Room");
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        labelHall.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelHall.setText("Number");

        labelRoom.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelRoom.setText("Rate");

        labelStudent.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelStudent.setText("Hall");
        labelStudent.setToolTipText("");

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        buttonAdd.setText("Add");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        labelTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitle.setText("Add New Room");

        textfield_Rate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        textfield_Rate.setMaximumSize(new java.awt.Dimension(100, 20));
        textfield_Rate.setMinimumSize(new java.awt.Dimension(100, 20));
        textfield_Rate.setPreferredSize(new java.awt.Dimension(100, 20));

        comboBox_Hall.setMaximumSize(new java.awt.Dimension(100, 20));
        comboBox_Hall.setMinimumSize(new java.awt.Dimension(100, 20));
        comboBox_Hall.setPreferredSize(new java.awt.Dimension(100, 20));

        textfield_Number.setMaximumSize(new java.awt.Dimension(100, 20));
        textfield_Number.setMinimumSize(new java.awt.Dimension(100, 20));
        textfield_Number.setPreferredSize(new java.awt.Dimension(100, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(buttonAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonCancel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelTitle)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelHall)
                            .addComponent(labelRoom)
                            .addComponent(labelStudent))
                        .addGap(103, 103, 103)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textfield_Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBox_Hall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textfield_Number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 31, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(labelTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHall)
                    .addComponent(textfield_Number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRoom)
                    .addComponent(textfield_Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelStudent)
                    .addComponent(comboBox_Hall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancel)
                    .addComponent(buttonAdd))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        mainScreen.createRoomClosing();
        this.dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        getAllFieldsAndSubmit();
        mainScreen.createRoomClosing();
        this.dispose();
    }//GEN-LAST:event_buttonAddActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        mainScreen.createRoomClosing();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(CreateLease.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateLease.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateLease.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateLease.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JComboBox<String> comboBox_Hall;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelHall;
    private javax.swing.JLabel labelRoom;
    private javax.swing.JLabel labelStudent;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JTextField textfield_Number;
    private javax.swing.JFormattedTextField textfield_Rate;
    // End of variables declaration//GEN-END:variables
}
