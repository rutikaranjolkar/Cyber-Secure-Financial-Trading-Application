/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.aedproject.rranjolkar.ui;

import edu.neu.aedproject.rranjolkar.AuditItem;
import edu.neu.aedproject.rranjolkar.WorkRequest;
import edu.neu.aedproject.rranjolkar.ai.MachineLearningUtil;
import edu.neu.aedproject.rranjolkar.services.LoginService;
import edu.neu.aedproject.rranjolkar.trading.IOIWorkRequest;
import edu.neu.aedproject.rranjolkar.trading.TradingOrganization;
import edu.neu.aedproject.rranjolkar.trading.util.CommonUtils;
import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rutika Ranjolkar
 */
public class TraderIOIPanel extends javax.swing.JPanel {

    /**
     * Creates new form PortfolioManagerClientsPanel
     */
    public TraderIOIPanel() {
        initComponents();

        populateIOIs();

        /**
         * Add change listener to table
         */
        tblIOIs.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tblIOIs.getSelectedRow();
                if (selectedRow >= 0) {
                    IOIWorkRequest ioiRequest = CommonUtils.getSelection(tblIOIs, 1);
                    String message = ioiRequest.getMessage();
                    txtIOIContent.setText(message);
                    if (ioiRequest.getRiskPercent() > 50) {
                        lblRating.setText("Possible spam: Proceed with caution");
                        lblRating.setForeground(Color.RED);
                    } else {
                        lblRating.setText("No issues detected with this IOI");
                        lblRating.setForeground(Color.GREEN);
                    }
                } else {
                    txtIOIContent.setText("");
                    lblRating.setText("Click on an IOI to see details");
                    lblRating.setForeground(Color.GREEN);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnsBuySell = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblIOIs = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblRating = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtIOIContent = new javax.swing.JTextArea();
        btnDeleteIOI = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        tblIOIs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Sender", "Message", "Submitted At"
            }
        ));
        jScrollPane1.setViewportView(tblIOIs);
        if (tblIOIs.getColumnModel().getColumnCount() > 0) {
            tblIOIs.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Received Indications of Interest");

        lblRating.setBackground(new java.awt.Color(255, 255, 255));
        lblRating.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRating.setForeground(new java.awt.Color(0, 102, 0));
        lblRating.setText("Click on an IOI to see details");

        txtIOIContent.setColumns(20);
        txtIOIContent.setRows(5);
        jScrollPane2.setViewportView(txtIOIContent);

        btnDeleteIOI.setText("Archive this Indication of Interest");
        btnDeleteIOI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteIOIActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblRating, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(btnDeleteIOI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 11, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblRating, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteIOI)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteIOIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteIOIActionPerformed
        IOIWorkRequest req = CommonUtils.getSelection(tblIOIs, 1);
        if (req == null) {
            CommonUtils.showMessage("Please select an IOI");
        } else {
            req.receive(LoginService.getInstance().getCurrentAccount());
            req.resolve();
            populateIOIs();
            LoginService.getInstance().getCurrentAccount().getAuditTrail().add(
                    new AuditItem(new Date(), "Archived IOI: " + req.getMessage())
            );
        }
    }//GEN-LAST:event_btnDeleteIOIActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteIOI;
    private javax.swing.ButtonGroup btnsBuySell;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblRating;
    private javax.swing.JTable tblIOIs;
    private javax.swing.JTextArea txtIOIContent;
    // End of variables declaration//GEN-END:variables

    private void populateIOIs() {
        DefaultTableModel model = (DefaultTableModel) tblIOIs.getModel();
        model.setRowCount(0);
        TradingOrganization organization = (TradingOrganization) LoginService.getInstance().getCurrentOrganization();
        List<IOIWorkRequest> requests = organization.getIOIWorkRequests();
        MachineLearningUtil.classifyIOIs(requests);
        if (CommonUtils.isNotEmpty(requests)) {
            Collections.sort(requests, new Comparator<IOIWorkRequest>() {
                @Override
                public int compare(IOIWorkRequest o1, IOIWorkRequest o2) {
                    return o2.getSubmittedAt().compareTo(o1.getSubmittedAt());
                }
            });
            for (IOIWorkRequest request : requests) {
                if (!request.getStatus().equals(WorkRequest.WORK_STATUS_RESOLVED)) {
                    Object[] row = {
                        request.getBroker(),
                        request,
                        request.getSubmittedAt(),};
                    model.addRow(row);
                }
            }
        }
    }

}