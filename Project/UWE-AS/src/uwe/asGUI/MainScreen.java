package uwe.asGUI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import uwe.as.CleanState;
import uwe.as.Hall;
import uwe.as.Lease;
import uwe.as.Room;
import uwe.as.UWEAS;
import uwe.as.User;

/**
 *
 * @author Adwait Chhetri (15021047) - Main Jamie Mills (16004255) - Some tweaks
 */
public class MainScreen extends javax.swing.JFrame {

    public static uwe.as.Data_Cache data_cache;
    private CreateLease createLease = null;
    private CreateRoom createRoom = null;
    private CreateHall createHall = null;
    private CreateApplication createApplication = null;
    private ViewApplications viewApplications = null;

    /**
     * Creates new form MainScreen
     */
    public MainScreen() {
        initComponents();
        show_users_in_jtable();
        disableElementsForUserLevel();
        this.setVisible(true);
    }

    private void refresh_jtable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        show_users_in_jtable();
    }

    private void show_users_in_jtable() {
        // Pulling data lists from cache
        List<Room> rooms = data_cache.getRooms();
        List<Lease> leases = data_cache.getLeases();
        List<Hall> halls = data_cache.getHalls();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Object[] row = new Object[10];

        if (halls != null && rooms != null && leases != null) {
            for (int j = 0; j < halls.size(); j++) {
                Hall currentHall = halls.get(j);
                //System.out.println(currentHall);

                for (int i = 0; i < rooms.size(); i++) {
                    Room currentRoom = rooms.get(i);

                    if (currentRoom.getHallUID() == currentHall.getUID()) {

                        for (Lease l : leases) {

                            if (currentRoom.getLeases().contains(l.getUID())) {
                                User currentUser = data_cache.getUser(l.getStudentUID());

                                if (currentUser != null) {
                                    row[0] = l.getLeaseNumber();
                                    row[1] = currentHall.getName();
                                    row[2] = currentHall.getNumber();
                                    row[3] = currentRoom.getNumber();
                                    row[4] = currentUser.getName();
                                    row[5] = currentRoom.getCleanliness();
                                    row[6] = currentUser.getUID();
                                    row[7] = l.getUID();
                                    row[8] = currentRoom.getUID();
                                    row[9] = currentHall.getUID();
                                    model.addRow(row);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void createLeaseClosing() {
        this.createLease = null;
    }

    public void createRoomClosing() {
        this.createRoom = null;
    }

    public void createHallClosing() {
        this.createHall = null;
    }
    
    public void createApplicationClosing() {
        this.createApplication = null;
    }
    
    public void viewApplicationsClosing() {
        this.viewApplications = null;
    }
            

    private void disableElementsForUserLevel() {
        switch (UWEAS.currentUser.getAccountLevel()) {
            default: // Student
                textbox_Hall_Name.setEditable(false);
                textbox_Hall_Number.setEditable(false);
                textbox_Hall_Address.setEditable(false);
                textbox_Hall_Telephone.setEditable(false);
                comboBox_Hall_Warden.setEnabled(false);
                button_Hall_Update.setEnabled(false);
                button_Hall_Delete.setEnabled(false);
                button_Hall_Create.setEnabled(false);
                comboBox_Room_CleanState.setEnabled(false);
                textbox_Room_Rate.setEditable(false);
                button_Room_Update.setEnabled(false);
                button_Room_Delete.setEnabled(false);
                button_Room_Create.setEnabled(false);
                textbox_Lease_LeaseNumber.setEditable(false);
                textbox_Lease_StartDate.setEditable(false);
                comboBox_Lease_Duration.setEnabled(false);
                button_Lease_UpdateLease.setEnabled(false);
                button_Lease_DeleteLease.setEnabled(false);
                button_Lease_CreateLease.setEnabled(false);
                button_Nav_ViewApplications.setEnabled(false);
                break;
            case 1: // Warden
                textbox_Hall_Name.setEditable(false);
                textbox_Hall_Number.setEditable(false);
                textbox_Hall_Address.setEditable(false);
                textbox_Hall_Telephone.setEditable(false);
                comboBox_Hall_Warden.setEnabled(false);
                button_Hall_Update.setEnabled(false);
                button_Hall_Delete.setEnabled(false);
                button_Hall_Create.setEnabled(false);
                textbox_Room_Rate.setEditable(false);
                button_Room_Update.setEnabled(false);
                button_Room_Delete.setEnabled(false);
                button_Room_Create.setEnabled(false);
                textbox_Lease_LeaseNumber.setEditable(false);
                textbox_Lease_StartDate.setEditable(false);
                comboBox_Lease_Duration.setEnabled(false);
                button_Lease_UpdateLease.setEnabled(false);
                button_Lease_DeleteLease.setEnabled(false);
                button_Lease_CreateLease.setEnabled(false);
                button_Nav_ViewApplications.setEnabled(false);
                break;
            case 2: // manager
                break;
        }
    }

    private void navButtonClick(int button) {
        int selectedRow = jTable1.getSelectedRow();
        switch (button) {
            default:
                panelHallView.setVisible(true);
                panelRoomView.setVisible(false);
                panelLeaseView.setVisible(false);
                break;
            case 1:
                panelHallView.setVisible(false);
                panelRoomView.setVisible(true);
                panelLeaseView.setVisible(false);
                break;
            case 2:
                panelHallView.setVisible(false);
                panelRoomView.setVisible(false);
                panelLeaseView.setVisible(true);
                break;
            case 3:
                this.dispose();
                break;
        }
    }

    private void updateHallPanelContents() {
        int selectedRow = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        List<User> users = data_cache.getUsers();
        Hall currentHall = getCurrentHall();
        if (users != null && currentHall != null) {
            textbox_Hall_Name.setText(model.getValueAt(selectedRow, 1).toString());
            textbox_Hall_Number.setText(model.getValueAt(selectedRow, 2).toString());
            textbox_Hall_Telephone.setText(currentHall.getTelephoneNumber());
            textbox_Hall_Address.setText(currentHall.getAddress());
            comboBox_Hall_Warden.removeAllItems();

            for (User u : users) {
                if (u.getAccountLevel() == 1) {
                    comboBox_Hall_Warden.addItem(u.getRealName());
                }
            }

            User currentWarden = data_cache.getUser(currentHall.getWardenUID());
            if (currentWarden != null) {
                comboBox_Hall_Warden.setSelectedItem(currentWarden.getRealName());
            }
        } else {
            System.out.println("Couldn't find users and/or current hall!");
        }
    }

    private void updateRoomPanelContents() {
        int selectedRow = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        Room currentRoom = getCurrentRoom();
        if (currentRoom != null) {
            Lease currentLease = currentRoom.getLeaseForDate(Date.from(Instant.now()));

            textbox_Room_HallName.setText(model.getValueAt(selectedRow, 1).toString());
            textbox_Room_HallNumber.setText(model.getValueAt(selectedRow, 2).toString());
            textbox_Room_RoomNumber.setText(model.getValueAt(selectedRow, 3).toString());
            textbox_Room_Rate.setValue(currentRoom.getRate());
            if (currentLease != null) {
                textbox_Room_LeaseNumber.setText(Integer.toString(currentLease.getLeaseNumber()));
                User currentStudent = data_cache.getUser(currentLease.getStudentUID());
                if (currentStudent != null) {
                    textbox_Room_StudentName.setText(currentStudent.getRealName());
                    textbox_Room_StudentNumber.setText(currentStudent.getStudentNumber());
                }
            } else {
                System.out.println("Couldn't find current lease!");
            }

            uwe.as.CleanState state = currentRoom.getCleanliness();
            switch (state) {
                default:
                    comboBox_Room_CleanState.setSelectedIndex(0);
                    break;
                case DIRTY:
                    comboBox_Room_CleanState.setSelectedIndex(1);
                    break;
                case OFFLINE:
                    comboBox_Room_CleanState.setSelectedIndex(2);
                    break;
            }
        } else {
            System.out.println("Couldn't find current room!");
        }
    }

    private void updateLeasePanelContents() {
        int selectedRow = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        Lease currentLease = getCurrentLease();
        if (currentLease != null) {
            Calendar endDate = Calendar.getInstance();
            endDate.setTime(currentLease.getStartDate());
            endDate.add(Calendar.MONTH, currentLease.getDuration());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");

            textbox_Lease_HallName.setText(model.getValueAt(selectedRow, 1).toString());
            textbox_Lease_HallNumber.setText(model.getValueAt(selectedRow, 2).toString());
            textbox_Lease_RoomNumber.setText(model.getValueAt(selectedRow, 3).toString());
            textbox_Lease_StudentName.setText(model.getValueAt(selectedRow, 4).toString());
            textbox_Lease_StartDate.setText(df.format(currentLease.getStartDate()));
            textbox_Lease_EndDate.setText(df.format(endDate.getTime()));
            textbox_Lease_LeaseNumber.setText(Integer.toString(currentLease.getLeaseNumber()));
        } else {
            System.out.println("Couldn't find current lease!");
        }
    }

    private Hall getCurrentHall() {
        return data_cache.getHall(Integer.parseInt(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 9).toString()));
    }

    private Room getCurrentRoom() {
        return data_cache.getRoom(Integer.parseInt(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 8).toString()));
    }

    private Lease getCurrentLease() {
        return data_cache.getLease(Integer.parseInt(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 7).toString()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Navigation = new javax.swing.JPanel();
        label_Navigation_Title = new javax.swing.JLabel();
        seperator_Navigation = new javax.swing.JSeparator();
        button_Nav_Hall = new javax.swing.JButton();
        button_Nav_Room = new javax.swing.JButton();
        button_Nav_Lease = new javax.swing.JButton();
        button_Nav_Exit = new javax.swing.JButton();
        button_Nav_CreateApplication = new javax.swing.JButton();
        button_Nav_ViewApplications = new javax.swing.JButton();
        ms_database_table = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelControl = new javax.swing.JPanel();
        panelLeaseView = new javax.swing.JPanel();
        label_Lease_HallName = new javax.swing.JLabel();
        label_Lease_HallNumber = new javax.swing.JLabel();
        label_Lease_RoomNumber = new javax.swing.JLabel();
        label_Lease_LeaseNumber = new javax.swing.JLabel();
        label_Lease_StudentName = new javax.swing.JLabel();
        label_Lease_StartDate = new javax.swing.JLabel();
        label_Lease_EndDate = new javax.swing.JLabel();
        button_Lease_UpdateLease = new javax.swing.JButton();
        button_Lease_CreateLease = new javax.swing.JButton();
        button_Lease_DeleteLease = new javax.swing.JButton();
        textbox_Lease_HallName = new javax.swing.JTextField();
        textbox_Lease_HallNumber = new javax.swing.JTextField();
        textbox_Lease_RoomNumber = new javax.swing.JTextField();
        textbox_Lease_StartDate = new javax.swing.JFormattedTextField();
        textbox_Lease_EndDate = new javax.swing.JFormattedTextField();
        textbox_Lease_LeaseNumber = new javax.swing.JFormattedTextField();
        textbox_Lease_StudentName = new javax.swing.JTextField();
        label_Lease_Duration = new javax.swing.JLabel();
        comboBox_Lease_Duration = new javax.swing.JComboBox<>();
        panelRoomView = new javax.swing.JPanel();
        label_Room_HallName = new javax.swing.JLabel();
        label_Room_HallNumber = new javax.swing.JLabel();
        label_Room_RoomNumber = new javax.swing.JLabel();
        label_Room_CleanState = new javax.swing.JLabel();
        label_Room_Rate = new javax.swing.JLabel();
        button_Room_Update = new javax.swing.JButton();
        button_Room_Create = new javax.swing.JButton();
        button_Room_Delete = new javax.swing.JButton();
        textbox_Room_HallNumber = new javax.swing.JTextField();
        textbox_Room_RoomNumber = new javax.swing.JTextField();
        comboBox_Room_CleanState = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        label_Room_LeaseNumber = new javax.swing.JLabel();
        textbox_Room_LeaseNumber = new javax.swing.JFormattedTextField();
        textbox_Room_StudentName = new javax.swing.JTextField();
        label_Room_StudentName = new javax.swing.JLabel();
        label_Room_CurrentLeaseInfo = new javax.swing.JLabel();
        label_Room_StudentName1 = new javax.swing.JLabel();
        textbox_Room_StudentNumber = new javax.swing.JTextField();
        textbox_Room_Rate = new javax.swing.JFormattedTextField();
        textbox_Room_HallName = new javax.swing.JTextField();
        panelHallView = new javax.swing.JPanel();
        label_Hall_Name = new javax.swing.JLabel();
        label_Hall_Number = new javax.swing.JLabel();
        label_Hall_Warden = new javax.swing.JLabel();
        label_Hall_Address = new javax.swing.JLabel();
        label_Hall_Telephone = new javax.swing.JLabel();
        button_Hall_Update = new javax.swing.JButton();
        button_Hall_Create = new javax.swing.JButton();
        button_Hall_Delete = new javax.swing.JButton();
        textbox_Hall_Name = new javax.swing.JTextField();
        textbox_Hall_Number = new javax.swing.JTextField();
        textbox_Hall_Telephone = new javax.swing.JTextField();
        comboBox_Hall_Warden = new javax.swing.JComboBox<>();
        textbox_Hall_Address = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UWE Accomodation App");
        setBackground(new java.awt.Color(247, 245, 242));
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(849, 1000));

        panel_Navigation.setBackground(new java.awt.Color(247, 245, 242));

        label_Navigation_Title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_Navigation_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Navigation_Title.setText("<html><div style=\"text-align: center\">UWE Accomodation<br>System</div>");
        label_Navigation_Title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        seperator_Navigation.setBackground(new java.awt.Color(247, 245, 242));

        button_Nav_Hall.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        button_Nav_Hall.setText("<html><div style=\"text-align:center\">Hall View</div>");
        button_Nav_Hall.setBorder(null);
        button_Nav_Hall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Nav_HallActionPerformed(evt);
            }
        });

        button_Nav_Room.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        button_Nav_Room.setText("<html><div style=\"text-align:center\">Room View</div>");
        button_Nav_Room.setBorder(null);
        button_Nav_Room.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Nav_RoomActionPerformed(evt);
            }
        });

        button_Nav_Lease.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        button_Nav_Lease.setText("<html><div style=\"text-align:center\">Lease View</div>");
        button_Nav_Lease.setBorder(null);
        button_Nav_Lease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Nav_LeaseActionPerformed(evt);
            }
        });

        button_Nav_Exit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        button_Nav_Exit.setText("<html><div style=\"text-align:center\">Exit</div>");
        button_Nav_Exit.setBorder(null);
        button_Nav_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Nav_ExitActionPerformed(evt);
            }
        });

        button_Nav_CreateApplication.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        button_Nav_CreateApplication.setText("<html><div style=\"text-align:center\">Create Application</div>");
        button_Nav_CreateApplication.setBorder(null);
        button_Nav_CreateApplication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Nav_CreateApplicationActionPerformed(evt);
            }
        });

        button_Nav_ViewApplications.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        button_Nav_ViewApplications.setText("<html><div style=\"text-align:center\">View Applications</div>");
        button_Nav_ViewApplications.setBorder(null);
        button_Nav_ViewApplications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Nav_ViewApplicationsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_NavigationLayout = new javax.swing.GroupLayout(panel_Navigation);
        panel_Navigation.setLayout(panel_NavigationLayout);
        panel_NavigationLayout.setHorizontalGroup(
            panel_NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_NavigationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_Nav_Exit)
                    .addGroup(panel_NavigationLayout.createSequentialGroup()
                        .addGroup(panel_NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(button_Nav_Hall)
                            .addComponent(seperator_Navigation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(label_Navigation_Title, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(button_Nav_Room, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(button_Nav_Lease, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(button_Nav_CreateApplication, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(button_Nav_ViewApplications, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_NavigationLayout.setVerticalGroup(
            panel_NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_NavigationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_Navigation_Title, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seperator_Navigation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button_Nav_Hall, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_Nav_Room, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_Nav_Lease, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_Nav_CreateApplication, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(button_Nav_ViewApplications, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_Nav_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lease Number", "Hall Name", "Hall Number ", "Room Number", "Student Name", "Cleaning Status", "Student ID", "Lease ID", "RoomID", "HallID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setPreferredSize(new java.awt.Dimension(800, 500));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowHorizontalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        ms_database_table.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(6).setMinWidth(0);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(6).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(7).setMinWidth(0);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(7).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(8).setMinWidth(0);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(9).setMinWidth(0);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(9).setMaxWidth(0);
        }

        panelControl.setBackground(new java.awt.Color(247, 245, 242));
        panelControl.setLayout(new java.awt.CardLayout());

        panelLeaseView.setMaximumSize(new java.awt.Dimension(530, 250));
        panelLeaseView.setMinimumSize(new java.awt.Dimension(530, 250));
        panelLeaseView.setPreferredSize(new java.awt.Dimension(530, 250));

        label_Lease_HallName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Lease_HallName.setText("Hall Name");

        label_Lease_HallNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Lease_HallNumber.setText("Hall Number");

        label_Lease_RoomNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Lease_RoomNumber.setText("Room Number");

        label_Lease_LeaseNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Lease_LeaseNumber.setText("Lease Number");

        label_Lease_StudentName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Lease_StudentName.setText("Student Name");

        label_Lease_StartDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Lease_StartDate.setText("Start Date");

        label_Lease_EndDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Lease_EndDate.setText("End Date");

        button_Lease_UpdateLease.setText("Update Lease");
        button_Lease_UpdateLease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Lease_UpdateLeaseActionPerformed(evt);
            }
        });

        button_Lease_CreateLease.setText("Create New Lease");
        button_Lease_CreateLease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Lease_CreateLeaseActionPerformed(evt);
            }
        });

        button_Lease_DeleteLease.setText("Delete Lease");
        button_Lease_DeleteLease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Lease_DeleteLeaseActionPerformed(evt);
            }
        });

        textbox_Lease_HallName.setEditable(false);
        textbox_Lease_HallName.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Lease_HallName.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Lease_HallName.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_Lease_HallNumber.setEditable(false);
        textbox_Lease_HallNumber.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Lease_HallNumber.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Lease_HallNumber.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_Lease_RoomNumber.setEditable(false);
        textbox_Lease_RoomNumber.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Lease_RoomNumber.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Lease_RoomNumber.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_Lease_StartDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        textbox_Lease_StartDate.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Lease_StartDate.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Lease_StartDate.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_Lease_EndDate.setEditable(false);
        textbox_Lease_EndDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        textbox_Lease_EndDate.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Lease_EndDate.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Lease_EndDate.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_Lease_LeaseNumber.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Lease_LeaseNumber.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Lease_LeaseNumber.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_Lease_StudentName.setEditable(false);
        textbox_Lease_StudentName.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Lease_StudentName.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Lease_StudentName.setPreferredSize(new java.awt.Dimension(100, 20));

        label_Lease_Duration.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Lease_Duration.setText("Duration (Months)");

        comboBox_Lease_Duration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "6", "12", "18", "24" }));
        comboBox_Lease_Duration.setMaximumSize(new java.awt.Dimension(100, 20));
        comboBox_Lease_Duration.setMinimumSize(new java.awt.Dimension(100, 20));
        comboBox_Lease_Duration.setPreferredSize(new java.awt.Dimension(100, 20));

        javax.swing.GroupLayout panelLeaseViewLayout = new javax.swing.GroupLayout(panelLeaseView);
        panelLeaseView.setLayout(panelLeaseViewLayout);
        panelLeaseViewLayout.setHorizontalGroup(
            panelLeaseViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeaseViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLeaseViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_Lease_UpdateLease)
                    .addComponent(label_Lease_HallName)
                    .addComponent(label_Lease_HallNumber)
                    .addComponent(label_Lease_RoomNumber)
                    .addComponent(label_Lease_StartDate))
                .addGap(18, 18, 18)
                .addGroup(panelLeaseViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLeaseViewLayout.createSequentialGroup()
                        .addComponent(button_Lease_DeleteLease)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button_Lease_CreateLease))
                    .addGroup(panelLeaseViewLayout.createSequentialGroup()
                        .addGroup(panelLeaseViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textbox_Lease_HallName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textbox_Lease_HallNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textbox_Lease_StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textbox_Lease_RoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(panelLeaseViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_Lease_StudentName)
                            .addComponent(label_Lease_EndDate)
                            .addComponent(label_Lease_Duration)
                            .addComponent(label_Lease_LeaseNumber))
                        .addGap(18, 18, 18)
                        .addGroup(panelLeaseViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textbox_Lease_LeaseNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBox_Lease_Duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textbox_Lease_EndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textbox_Lease_StudentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 26, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLeaseViewLayout.setVerticalGroup(
            panelLeaseViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeaseViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLeaseViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLeaseViewLayout.createSequentialGroup()
                        .addGroup(panelLeaseViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Lease_HallName)
                            .addComponent(textbox_Lease_HallName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelLeaseViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Lease_HallNumber)
                            .addComponent(textbox_Lease_HallNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelLeaseViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Lease_RoomNumber)
                            .addComponent(textbox_Lease_RoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_Lease_Duration)
                            .addComponent(comboBox_Lease_Duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelLeaseViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Lease_StartDate)
                            .addComponent(label_Lease_EndDate)
                            .addComponent(textbox_Lease_StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textbox_Lease_EndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelLeaseViewLayout.createSequentialGroup()
                        .addGroup(panelLeaseViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Lease_LeaseNumber)
                            .addComponent(textbox_Lease_LeaseNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelLeaseViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Lease_StudentName)
                            .addComponent(textbox_Lease_StudentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(69, 69, 69)
                .addGroup(panelLeaseViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_Lease_UpdateLease)
                    .addComponent(button_Lease_DeleteLease)
                    .addComponent(button_Lease_CreateLease))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelControl.add(panelLeaseView, "card5");

        panelRoomView.setMaximumSize(new java.awt.Dimension(530, 250));
        panelRoomView.setMinimumSize(new java.awt.Dimension(530, 250));

        label_Room_HallName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Room_HallName.setText("Hall Name");

        label_Room_HallNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Room_HallNumber.setText("Hall Number");

        label_Room_RoomNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Room_RoomNumber.setText("Room Number");

        label_Room_CleanState.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Room_CleanState.setText("Cleanliness Status");

        label_Room_Rate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Room_Rate.setText("Rate");

        button_Room_Update.setText("Update Room");
        button_Room_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Room_UpdateActionPerformed(evt);
            }
        });

        button_Room_Create.setText("Create New Room");
        button_Room_Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Room_CreateActionPerformed(evt);
            }
        });

        button_Room_Delete.setText("Delete Room");
        button_Room_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Room_DeleteActionPerformed(evt);
            }
        });

        textbox_Room_HallNumber.setEditable(false);
        textbox_Room_HallNumber.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Room_HallNumber.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Room_HallNumber.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_Room_RoomNumber.setEditable(false);
        textbox_Room_RoomNumber.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Room_RoomNumber.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Room_RoomNumber.setPreferredSize(new java.awt.Dimension(100, 20));

        comboBox_Room_CleanState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clean", "Dirty", "Offline" }));
        comboBox_Room_CleanState.setMaximumSize(new java.awt.Dimension(100, 20));
        comboBox_Room_CleanState.setMinimumSize(new java.awt.Dimension(100, 20));
        comboBox_Room_CleanState.setPreferredSize(new java.awt.Dimension(100, 20));

        label_Room_LeaseNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Room_LeaseNumber.setText("Lease Number");

        textbox_Room_LeaseNumber.setEditable(false);
        textbox_Room_LeaseNumber.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Room_LeaseNumber.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Room_LeaseNumber.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_Room_StudentName.setEditable(false);
        textbox_Room_StudentName.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Room_StudentName.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Room_StudentName.setPreferredSize(new java.awt.Dimension(100, 20));

        label_Room_StudentName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Room_StudentName.setText("Student Name");

        label_Room_CurrentLeaseInfo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        label_Room_CurrentLeaseInfo.setText("Current Lease Info");

        label_Room_StudentName1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Room_StudentName1.setText("Student Number");

        textbox_Room_StudentNumber.setEditable(false);
        textbox_Room_StudentNumber.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Room_StudentNumber.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Room_StudentNumber.setPreferredSize(new java.awt.Dimension(100, 20));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label_Room_LeaseNumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textbox_Room_LeaseNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label_Room_StudentName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textbox_Room_StudentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label_Room_StudentName1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textbox_Room_StudentNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label_Room_CurrentLeaseInfo)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(label_Room_CurrentLeaseInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_Room_LeaseNumber)
                    .addComponent(textbox_Room_LeaseNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_Room_StudentName)
                    .addComponent(textbox_Room_StudentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_Room_StudentName1)
                    .addComponent(textbox_Room_StudentNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        textbox_Room_Rate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        textbox_Room_Rate.setText("0");
        textbox_Room_Rate.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Room_Rate.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Room_Rate.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_Room_HallName.setEditable(false);
        textbox_Room_HallName.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Room_HallName.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Room_HallName.setPreferredSize(new java.awt.Dimension(100, 20));

        javax.swing.GroupLayout panelRoomViewLayout = new javax.swing.GroupLayout(panelRoomView);
        panelRoomView.setLayout(panelRoomViewLayout);
        panelRoomViewLayout.setHorizontalGroup(
            panelRoomViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoomViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRoomViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_Room_Update)
                    .addComponent(label_Room_HallName)
                    .addComponent(label_Room_HallNumber)
                    .addComponent(label_Room_RoomNumber)
                    .addComponent(label_Room_CleanState)
                    .addComponent(label_Room_Rate))
                .addGap(18, 18, 18)
                .addGroup(panelRoomViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRoomViewLayout.createSequentialGroup()
                        .addComponent(button_Room_Delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                        .addComponent(button_Room_Create))
                    .addGroup(panelRoomViewLayout.createSequentialGroup()
                        .addGroup(panelRoomViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textbox_Room_HallNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textbox_Room_RoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBox_Room_CleanState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textbox_Room_Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textbox_Room_HallName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelRoomViewLayout.setVerticalGroup(
            panelRoomViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoomViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRoomViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRoomViewLayout.createSequentialGroup()
                        .addGroup(panelRoomViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Room_HallName)
                            .addComponent(textbox_Room_HallName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelRoomViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Room_HallNumber)
                            .addComponent(textbox_Room_HallNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelRoomViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Room_RoomNumber)
                            .addComponent(textbox_Room_RoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelRoomViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Room_CleanState)
                            .addComponent(comboBox_Room_CleanState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelRoomViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Room_Rate)
                            .addComponent(textbox_Room_Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRoomViewLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelRoomViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button_Room_Update)
                            .addComponent(button_Room_Delete)
                            .addComponent(button_Room_Create))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelControl.add(panelRoomView, "card6");

        panelHallView.setMaximumSize(new java.awt.Dimension(530, 250));
        panelHallView.setMinimumSize(new java.awt.Dimension(530, 250));

        label_Hall_Name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Hall_Name.setText("Name");

        label_Hall_Number.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Hall_Number.setText("Number");

        label_Hall_Warden.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Hall_Warden.setText("Warden");

        label_Hall_Address.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Hall_Address.setText("Address");

        label_Hall_Telephone.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Hall_Telephone.setText("Telephone");

        button_Hall_Update.setText("Update Hall");
        button_Hall_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Hall_UpdateActionPerformed(evt);
            }
        });

        button_Hall_Create.setText("Create New Hall");
        button_Hall_Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Hall_CreateActionPerformed(evt);
            }
        });

        button_Hall_Delete.setText("Delete Hall");
        button_Hall_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Hall_DeleteActionPerformed(evt);
            }
        });

        textbox_Hall_Name.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Hall_Name.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Hall_Name.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_Hall_Number.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Hall_Number.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Hall_Number.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_Hall_Telephone.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Hall_Telephone.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Hall_Telephone.setPreferredSize(new java.awt.Dimension(100, 20));

        comboBox_Hall_Warden.setMaximumSize(new java.awt.Dimension(100, 20));
        comboBox_Hall_Warden.setMinimumSize(new java.awt.Dimension(100, 20));
        comboBox_Hall_Warden.setPreferredSize(new java.awt.Dimension(100, 20));

        textbox_Hall_Address.setMaximumSize(new java.awt.Dimension(100, 20));
        textbox_Hall_Address.setMinimumSize(new java.awt.Dimension(100, 20));
        textbox_Hall_Address.setPreferredSize(new java.awt.Dimension(100, 20));

        javax.swing.GroupLayout panelHallViewLayout = new javax.swing.GroupLayout(panelHallView);
        panelHallView.setLayout(panelHallViewLayout);
        panelHallViewLayout.setHorizontalGroup(
            panelHallViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHallViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHallViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_Hall_Update)
                    .addComponent(label_Hall_Name)
                    .addComponent(label_Hall_Number)
                    .addComponent(label_Hall_Warden))
                .addGap(38, 38, 38)
                .addGroup(panelHallViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHallViewLayout.createSequentialGroup()
                        .addComponent(button_Hall_Delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button_Hall_Create))
                    .addGroup(panelHallViewLayout.createSequentialGroup()
                        .addGroup(panelHallViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelHallViewLayout.createSequentialGroup()
                                .addGroup(panelHallViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textbox_Hall_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textbox_Hall_Number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(panelHallViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label_Hall_Telephone)
                                    .addComponent(label_Hall_Address))
                                .addGap(18, 18, 18)
                                .addGroup(panelHallViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textbox_Hall_Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textbox_Hall_Telephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(comboBox_Hall_Warden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 69, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelHallViewLayout.setVerticalGroup(
            panelHallViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHallViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHallViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHallViewLayout.createSequentialGroup()
                        .addGroup(panelHallViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Hall_Name)
                            .addComponent(textbox_Hall_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelHallViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Hall_Number)
                            .addComponent(textbox_Hall_Number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelHallViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Hall_Warden)
                            .addComponent(comboBox_Hall_Warden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelHallViewLayout.createSequentialGroup()
                        .addGroup(panelHallViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Hall_Address)
                            .addComponent(textbox_Hall_Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelHallViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Hall_Telephone)
                            .addComponent(textbox_Hall_Telephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(107, 107, 107)
                .addGroup(panelHallViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_Hall_Update)
                    .addComponent(button_Hall_Delete)
                    .addComponent(button_Hall_Create))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        panelControl.add(panelHallView, "card7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_Navigation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ms_database_table, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(ms_database_table, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel_Navigation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        updateHallPanelContents();
        updateRoomPanelContents();
        updateLeasePanelContents();
    }//GEN-LAST:event_jTable1MouseClicked

    private void button_Nav_HallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Nav_HallActionPerformed
        navButtonClick(0);
    }//GEN-LAST:event_button_Nav_HallActionPerformed

    private void button_Nav_RoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Nav_RoomActionPerformed
        navButtonClick(1);
    }//GEN-LAST:event_button_Nav_RoomActionPerformed

    private void button_Nav_LeaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Nav_LeaseActionPerformed
        navButtonClick(2);
    }//GEN-LAST:event_button_Nav_LeaseActionPerformed

    private void button_Nav_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Nav_ExitActionPerformed
        navButtonClick(3);
    }//GEN-LAST:event_button_Nav_ExitActionPerformed

    private void button_Lease_CreateLeaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Lease_CreateLeaseActionPerformed
        if (createLease == null) {
            createLease = new CreateLease(this);
        }
        refresh_jtable();
    }//GEN-LAST:event_button_Lease_CreateLeaseActionPerformed

    private void button_Lease_UpdateLeaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Lease_UpdateLeaseActionPerformed
        Lease currentLease = getCurrentLease();
        if (currentLease != null) {
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            try {
                date = df.parse(textbox_Lease_StartDate.getText());
            } catch (ParseException ex) {
                System.out.println("MainScreen.button_Lease_UpdateLeaseActionPerformed() produced the following error:");
                System.out.println(ex);
            }

            currentLease.modifyDuration(Integer.parseInt(comboBox_Lease_Duration.getItemAt(comboBox_Lease_Duration.getSelectedIndex())));
            currentLease.modifyLeaseNumber(Integer.parseInt(textbox_Lease_LeaseNumber.getText()));
            currentLease.modifyStartDate(date);
        }
        refresh_jtable();
    }//GEN-LAST:event_button_Lease_UpdateLeaseActionPerformed

    private void button_Lease_DeleteLeaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Lease_DeleteLeaseActionPerformed
        Lease currentLease = getCurrentLease();
        if (currentLease != null) {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected lease? This action is permanent.", "Delete?", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                data_cache.removeLease(currentLease);
            }
        }
        refresh_jtable();
    }//GEN-LAST:event_button_Lease_DeleteLeaseActionPerformed

    private void button_Room_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Room_UpdateActionPerformed
        Room currentRoom = getCurrentRoom();
        if (currentRoom != null) {
            currentRoom.modifyRate(Integer.parseInt(textbox_Room_Rate.getText()));
            switch (comboBox_Room_CleanState.getItemAt(comboBox_Room_CleanState.getSelectedIndex())) {
                case "Dirty":
                    currentRoom.changeCleanlinessState(CleanState.DIRTY);
                    break;
                case "Offline":
                    currentRoom.changeCleanlinessState(CleanState.OFFLINE);
                    break;
                default:
                    currentRoom.changeCleanlinessState(CleanState.CLEAN);
                    break;
            }
        }
        refresh_jtable();
    }//GEN-LAST:event_button_Room_UpdateActionPerformed

    private void button_Room_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Room_DeleteActionPerformed
        Room currentRoom = getCurrentRoom();
        if (currentRoom != null) {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected room? This action is permanent.", "Delete?", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                data_cache.removeRoom(currentRoom);
            }
        }
        refresh_jtable();
    }//GEN-LAST:event_button_Room_DeleteActionPerformed

    private void button_Room_CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Room_CreateActionPerformed
        if (createRoom == null) {
            createRoom = new CreateRoom(this);
        }
        refresh_jtable();
    }//GEN-LAST:event_button_Room_CreateActionPerformed

    private void button_Hall_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Hall_UpdateActionPerformed
        Hall currentHall = getCurrentHall();

        currentHall.modifyAddress(textbox_Hall_Address.getText());
        currentHall.modifyName(textbox_Hall_Name.getText());
        currentHall.modifyNumber(textbox_Hall_Number.getText());
        currentHall.modifyTelephone(textbox_Hall_Telephone.getText());

        User currentWarden = data_cache.getUser(currentHall.getWardenUID());
        if (currentWarden != null) {
            currentHall.modifyWarden(currentWarden.getUID());
        }
        refresh_jtable();
    }//GEN-LAST:event_button_Hall_UpdateActionPerformed

    private void button_Hall_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Hall_DeleteActionPerformed
        Hall currentHall = getCurrentHall();
        if (currentHall != null) {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected hall? This action is permanent.", "Delete?", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                data_cache.removeHall(currentHall);
            }
        }
        refresh_jtable();
    }//GEN-LAST:event_button_Hall_DeleteActionPerformed

    private void button_Hall_CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Hall_CreateActionPerformed
        if (createHall == null) {
            createHall = new CreateHall(this);
        }
        refresh_jtable();
    }//GEN-LAST:event_button_Hall_CreateActionPerformed

    private void button_Nav_CreateApplicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Nav_CreateApplicationActionPerformed
        if (createApplication == null) {
            createApplication = new CreateApplication(this);
        }
    }//GEN-LAST:event_button_Nav_CreateApplicationActionPerformed

    private void button_Nav_ViewApplicationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Nav_ViewApplicationsActionPerformed
        if (viewApplications == null)
        {
            viewApplications = new ViewApplications(this);
        }
    }//GEN-LAST:event_button_Nav_ViewApplicationsActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        updateHallPanelContents();
        updateRoomPanelContents();
        updateLeasePanelContents();
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
    private javax.swing.JButton button_Hall_Create;
    private javax.swing.JButton button_Hall_Delete;
    private javax.swing.JButton button_Hall_Update;
    private javax.swing.JButton button_Lease_CreateLease;
    private javax.swing.JButton button_Lease_DeleteLease;
    private javax.swing.JButton button_Lease_UpdateLease;
    private javax.swing.JButton button_Nav_CreateApplication;
    private javax.swing.JButton button_Nav_Exit;
    private javax.swing.JButton button_Nav_Hall;
    private javax.swing.JButton button_Nav_Lease;
    private javax.swing.JButton button_Nav_Room;
    private javax.swing.JButton button_Nav_ViewApplications;
    private javax.swing.JButton button_Room_Create;
    private javax.swing.JButton button_Room_Delete;
    private javax.swing.JButton button_Room_Update;
    private javax.swing.JComboBox<String> comboBox_Hall_Warden;
    private javax.swing.JComboBox<String> comboBox_Lease_Duration;
    private javax.swing.JComboBox<String> comboBox_Room_CleanState;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel label_Hall_Address;
    private javax.swing.JLabel label_Hall_Name;
    private javax.swing.JLabel label_Hall_Number;
    private javax.swing.JLabel label_Hall_Telephone;
    private javax.swing.JLabel label_Hall_Warden;
    private javax.swing.JLabel label_Lease_Duration;
    private javax.swing.JLabel label_Lease_EndDate;
    private javax.swing.JLabel label_Lease_HallName;
    private javax.swing.JLabel label_Lease_HallNumber;
    private javax.swing.JLabel label_Lease_LeaseNumber;
    private javax.swing.JLabel label_Lease_RoomNumber;
    private javax.swing.JLabel label_Lease_StartDate;
    private javax.swing.JLabel label_Lease_StudentName;
    private javax.swing.JLabel label_Navigation_Title;
    private javax.swing.JLabel label_Room_CleanState;
    private javax.swing.JLabel label_Room_CurrentLeaseInfo;
    private javax.swing.JLabel label_Room_HallName;
    private javax.swing.JLabel label_Room_HallNumber;
    private javax.swing.JLabel label_Room_LeaseNumber;
    private javax.swing.JLabel label_Room_Rate;
    private javax.swing.JLabel label_Room_RoomNumber;
    private javax.swing.JLabel label_Room_StudentName;
    private javax.swing.JLabel label_Room_StudentName1;
    private javax.swing.JScrollPane ms_database_table;
    private javax.swing.JPanel panelControl;
    private javax.swing.JPanel panelHallView;
    private javax.swing.JPanel panelLeaseView;
    private javax.swing.JPanel panelRoomView;
    private javax.swing.JPanel panel_Navigation;
    private javax.swing.JSeparator seperator_Navigation;
    private javax.swing.JTextField textbox_Hall_Address;
    private javax.swing.JTextField textbox_Hall_Name;
    private javax.swing.JTextField textbox_Hall_Number;
    private javax.swing.JTextField textbox_Hall_Telephone;
    private javax.swing.JFormattedTextField textbox_Lease_EndDate;
    private javax.swing.JTextField textbox_Lease_HallName;
    private javax.swing.JTextField textbox_Lease_HallNumber;
    private javax.swing.JFormattedTextField textbox_Lease_LeaseNumber;
    private javax.swing.JTextField textbox_Lease_RoomNumber;
    private javax.swing.JFormattedTextField textbox_Lease_StartDate;
    private javax.swing.JTextField textbox_Lease_StudentName;
    private javax.swing.JTextField textbox_Room_HallName;
    private javax.swing.JTextField textbox_Room_HallNumber;
    private javax.swing.JFormattedTextField textbox_Room_LeaseNumber;
    private javax.swing.JFormattedTextField textbox_Room_Rate;
    private javax.swing.JTextField textbox_Room_RoomNumber;
    private javax.swing.JTextField textbox_Room_StudentName;
    private javax.swing.JTextField textbox_Room_StudentNumber;
    // End of variables declaration//GEN-END:variables
}
