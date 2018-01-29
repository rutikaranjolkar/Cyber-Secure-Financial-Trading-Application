/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.aedproject.rranjolkar.trading.util;

import edu.neu.aedproject.rranjolkar.WorkRequest;
import edu.neu.aedproject.rranjolkar.trading.Broker;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rutika Ranjolkar
 */
public class CommonUtils {

    public static void clear(JComboBox cmb) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cmb.getModel();
        model.removeAllElements();
    }

    public static void populate(JComboBox cmb, List items) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cmb.getModel();
        for (Object item : items) {
            model.addElement(item);
        }
    }

    public static Date parseDate(int yyyy, int dd, int month, int hh, int min) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            String dateString = yyyy + "-" + dd + "-" + month + " " + hh + ":" + min;
            return sdf.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getFieldFromDate(Date date, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }

    public static boolean isNotEmpty(Collection coll) {
        return coll != null && !coll.isEmpty();
    }

    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static boolean isDouble(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static <T> T getSelection(JTable table, int column) {
        if (table.getSelectedRow() < 0) {
            return null;
        } else {
            int selectedRow = table.getSelectedRow();
            return (T) table.getValueAt(selectedRow, column);
        }
    }

    public static <T> T getSelection(JComboBox cmbBrokers) {
        return (T) cmbBrokers.getSelectedItem();
    }

    public static void clear(JTable tbl) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
    }

    public static void addRow(JTable tbl, Object[] row) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.addRow(row);
    }

    public static void clear(JList lst) {
        DefaultListModel model = new DefaultListModel();
        model.removeAllElements();
    }

    public static void addItem(JList lst, Object item) {
        DefaultListModel model = (DefaultListModel) lst.getModel();
        model.addElement(item);
    }

}
