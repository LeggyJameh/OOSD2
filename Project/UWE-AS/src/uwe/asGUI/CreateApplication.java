package uwe.asGUI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import uwe.as.Hall;
import uwe.as.Room;
import uwe.as.Data_Cache;
import uwe.as.UWEAS;

/**
 *
 * @author Jamie Mills (16004255)
 */
public class CreateApplication extends javax.swing.JFrame {

    public static Data_Cache data_cache;
    private List<Hall> halls;
    private List<Room> rooms;
    MainScreen mainScreen;
    boolean setup = true;

    /**
     * Creates new form CreateApplication
     */
    public CreateApplication(MainScreen mainScreen) {
        initComponents();
        importFromCache();
        populateDateField();
        populateHallBox();
        populateRooms();
        this.mainScreen = mainScreen;
        this.setVisible(true);
        setup = false;
    }

    private void importFromCache() {
        this.rooms = data_cache.getRooms();
        this.halls = data_cache.getHalls();
    }

    private void getAllFieldsAndSubmit() {
        int currentRoomUID = -1;
        int duration = Integer.parseInt(comboBox_Duration.getItemAt(comboBox_Duration.getSelectedIndex()));
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            date = df.parse(textbox_StartDate.getText());
        } catch (ParseException ex) {
            System.out.println("CreateLease.getAllFieldsAndSubmit() produced the following error:");
            System.out.println(ex);
        }

        Room currentRoom = getCurrentRoom();
        if (currentRoom != null) {
            currentRoomUID = currentRoom.getUID();
        }

        new uwe.as.RoomApplication(currentRoomUID, date, duration, UWEAS.currentUser.getUID());
    }

    private void populateHallBox() {
        comboBox_Hall.removeAllItems();
        for (Hall h : halls) {
            if (!h.getRooms().isEmpty()) {
                comboBox_Hall.addItem(h.getName());
            }
        }
    }

    private void populateDateField() {
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        Date now = Calendar.getInstance().getTime();
        String date = df.format(now);
        this.textbox_StartDate.setText(date);
    }

    private Hall getCurrentHall() {
        String currentHallName = comboBox_Hall.getItemAt(comboBox_Hall.getSelectedIndex());
        List<Hall> filteredHalls = halls.stream().filter(h -> h.getName().equals(currentHallName)).collect(Collectors.toList());
        if (!filteredHalls.isEmpty()) {
            return filteredHalls.get(0);
        }
        return null;
    }

    private Room getCurrentRoom() {
        String currentRoomNumber = comboBox_Room.getItemAt(comboBox_Room.getSelectedIndex());
        List<Room> filteredRooms = rooms.stream().filter(r -> r.getNumber().equals(currentRoomNumber)).collect(Collectors.toList());
        if (!filteredRooms.isEmpty()) {
            return filteredRooms.get(0);
        }
        return null;
    }

    private void populateRooms() {
        setup = true;
        Hall currentHall = getCurrentHall();
        comboBox_Room.removeAllItems();
        if (currentHall != null) {
            List<Room> roomsInHall = rooms.stream().filter(r -> currentHall.getRooms().contains(r.getUID())).collect(Collectors.toList());
            for (Room r : roomsInHall) {
                comboBox_Room.addItem(r.getNumber());
            }
        }
        setup = false;
    }

    private void updateDetailsForRoom() {
        if (setup == false)
        {
            Room currentRoom = getCurrentRoom();
            textbox_RoomDesc.setText(currentRoom.getDescription());
            textbox_Price.setValue(currentRoom.getRate());
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

        label_Hall = new javax.swing.JLabel();
        label_Room = new javax.swing.JLabel();
        label_StartDate = new javax.swing.JLabel();
        label_Duration = new javax.swing.JLabel();
        button_Apply = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textbox_RoomDesc = new javax.swing.JTextPane();
        label_Description = new javax.swing.JLabel();
        label_Price = new javax.swing.JLabel();
        textbox_Price = new javax.swing.JFormattedTextField();
        comboBox_Hall = new javax.swing.JComboBox<>();
        comboBox_Room = new javax.swing.JComboBox<>();
        textbox_StartDate = new javax.swing.JFormattedTextField();
        button_Cancel = new javax.swing.JButton();
        comboBox_Duration = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label_Hall.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Hall.setText("Hall");

        label_Room.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Room.setText("Room");

        label_StartDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_StartDate.setText("Start Date");

        label_Duration.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Duration.setText("Duration");

        button_Apply.setText("Apply");
        button_Apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ApplyActionPerformed(evt);
            }
        });

        textbox_RoomDesc.setEditable(false);
        jScrollPane1.setViewportView(textbox_RoomDesc);

        label_Description.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Description.setText("Description");

        label_Price.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Price.setText("Price (PCM)");

        textbox_Price.setEditable(false);
        textbox_Price.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Price.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Price.setPreferredSize(new java.awt.Dimension(100, 20));

        comboBox_Hall.setMaximumSize(new java.awt.Dimension(100, 20));
        comboBox_Hall.setMinimumSize(new java.awt.Dimension(100, 20));
        comboBox_Hall.setPreferredSize(new java.awt.Dimension(100, 20));
        comboBox_Hall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox_HallActionPerformed(evt);
            }
        });

        comboBox_Room.setMaximumSize(new java.awt.Dimension(100, 20));
        comboBox_Room.setMinimumSize(new java.awt.Dimension(100, 20));
        comboBox_Room.setPreferredSize(new java.awt.Dimension(100, 20));
        comboBox_Room.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox_RoomActionPerformed(evt);
            }
        });

        textbox_StartDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        textbox_StartDate.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_StartDate.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_StartDate.setPreferredSize(new java.awt.Dimension(100, 20));

        button_Cancel.setText("Cancel");
        button_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_CancelActionPerformed(evt);
            }
        });

        comboBox_Duration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "6", "12", "18", "24" }));
        comboBox_Duration.setMaximumSize(new java.awt.Dimension(100, 20));
        comboBox_Duration.setMinimumSize(new java.awt.Dimension(100, 20));
        comboBox_Duration.setPreferredSize(new java.awt.Dimension(100, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label_Hall)
                                    .addComponent(label_Room))
                                .addGap(85, 85, 85)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(comboBox_Hall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBox_Room, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(label_Description))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label_Duration)
                                    .addComponent(label_StartDate))
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label_Price)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textbox_Price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textbox_StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBox_Duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(72, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button_Apply)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button_Cancel)
                        .addGap(9, 9, 9))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_StartDate)
                    .addComponent(textbox_StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBox_Hall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_Hall))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_Duration)
                    .addComponent(comboBox_Room, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_Room)
                    .addComponent(comboBox_Duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textbox_Price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_Price)
                    .addComponent(label_Description))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button_Apply)
                            .addComponent(button_Cancel))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_ApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ApplyActionPerformed
        getAllFieldsAndSubmit();
        mainScreen.createApplicationClosing();
        this.dispose();
    }//GEN-LAST:event_button_ApplyActionPerformed

    private void button_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_CancelActionPerformed
        mainScreen.createApplicationClosing();
        this.dispose();
    }//GEN-LAST:event_button_CancelActionPerformed

    private void comboBox_HallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox_HallActionPerformed
        populateRooms();
    }//GEN-LAST:event_comboBox_HallActionPerformed

    private void comboBox_RoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox_RoomActionPerformed
        updateDetailsForRoom();
    }//GEN-LAST:event_comboBox_RoomActionPerformed

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
    private javax.swing.JButton button_Apply;
    private javax.swing.JButton button_Cancel;
    private javax.swing.JComboBox<String> comboBox_Duration;
    private javax.swing.JComboBox<String> comboBox_Hall;
    private javax.swing.JComboBox<String> comboBox_Room;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_Description;
    private javax.swing.JLabel label_Duration;
    private javax.swing.JLabel label_Hall;
    private javax.swing.JLabel label_Price;
    private javax.swing.JLabel label_Room;
    private javax.swing.JLabel label_StartDate;
    private javax.swing.JFormattedTextField textbox_Price;
    private javax.swing.JTextPane textbox_RoomDesc;
    private javax.swing.JFormattedTextField textbox_StartDate;
    // End of variables declaration//GEN-END:variables
}
