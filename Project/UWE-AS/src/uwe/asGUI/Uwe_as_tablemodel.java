package uwe.asGUI;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import uwe.as.Hall;
import uwe.as.Lease;
import uwe.as.Room;
import uwe.as.User;

/**
 *
 * @author adw8
 */
public class Uwe_as_tablemodel extends AbstractTableModel {

    private static final int LEASE_NUMBER_COL = 0;
    private static final int HALL_NAME_COL = 1;
    private static final int HALL_NUMBER_COL = 2;
    private static final int ROOM_NUMBER_COL = 3;
    private static final int STUDENT_NAME_COL = 4;
    private static final int OCCUPANCY_STATUS_COL = 5;
    private static final int CLEANING_STATUS_COL = 6;

    private String[] columnNames = {"Lease Number", "Hall Name", "Hall Number", "Room Number", "Student Name", "Occupancy Status", "Cleaning Status"};

    private List<Hall> halls;
    private List<Lease> leases;
    private List<Room> rooms;
    private List<User> users;

    public Uwe_as_tablemodel(List<Hall> theHalls, List<Lease> theLease, List<Room> theRoom, List<User> theUser) {
        halls = theHalls;
        leases = theLease;
        rooms = theRoom;
        users = theUser;
    }

    Uwe_as_tablemodel(List<Hall> halls) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRowCount() {
        return halls.size();

    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Hall tempHall = halls.get(row);
        Lease tempLease = leases.get(row);
        Room tempRoom = rooms.get(row);
        User tempUser = users.get(row);

        switch (col) {
            case LEASE_NUMBER_COL:
                return tempLease.getLeaseNumber();
            case HALL_NAME_COL:
                return tempHall.getName();
            case HALL_NUMBER_COL:
                return tempHall.getNumber();
            case ROOM_NUMBER_COL:
                return tempRoom.getNumber();
            case STUDENT_NAME_COL:
                return tempUser.getName();

//            case OCCUPANCY_STATUS_COL:
//                return tempRoom.();
//            case HALL_NUMBER_COL:
//                return tempHall.getNumber();
//            case HALL_NUMBER_COL:
//                return tempHall.getNumber();
            default:
                return tempHall.getName();

        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

}
