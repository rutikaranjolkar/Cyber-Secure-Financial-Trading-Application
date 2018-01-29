/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.aedproject.rranjolkar.ui;

import edu.neu.aedproject.rranjolkar.UserAccount;
import edu.neu.aedproject.rranjolkar.WorkRequest;
import edu.neu.aedproject.rranjolkar.security.VacationLoginBlockedWorkRequest;
import edu.neu.aedproject.rranjolkar.services.LoginService;
import edu.neu.aedproject.rranjolkar.trading.util.CommonUtils;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Rutika Ranjolkar
 */
public class SecurityAnalystWorkQueuePanel extends javax.swing.JPanel implements ResettablePanel {

    /**
     * Creates new form SecurityAnalystWorkQueuePanel
     */
    public SecurityAnalystWorkQueuePanel() {
        initComponents();

        reset();

        /**
         * Add change listener to table
         */
        tblWorkQueue.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                WorkRequest request = (WorkRequest) CommonUtils.getSelection(tblWorkQueue, 2);
                pnlWorkRequest.removeAll();
                if (request != null) {
                    if (request instanceof VacationLoginBlockedWorkRequest) {
                        VacationLoginBlockedWorkRequest blockedWorkRequest = (VacationLoginBlockedWorkRequest) request;
                        pnlWorkRequest.add(new VacationLoginBlockedPanel(blockedWorkRequest, SecurityAnalystWorkQueuePanel.this));
                    }
                }
                pnlWorkRequest.revalidate();
                pnlWorkRequest.repaint();
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblWorkQueue = new javax.swing.JTable();
        pnlWorkRequest = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("List of Work Requests assigned to you:");

        tblWorkQueue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Submitted At", "Affected User", "Root Cause"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblWorkQueue);
        if (tblWorkQueue.getColumnModel().getColumnCount() > 0) {
            tblWorkQueue.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        pnlWorkRequest.setBackground(new java.awt.Color(255, 255, 255));
        pnlWorkRequest.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlWorkRequest.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlWorkRequest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlWorkRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlWorkRequest;
    private javax.swing.JTable tblWorkQueue;
    // End of variables declaration//GEN-END:variables

    private void populateTable() {
        UserAccount securityAnalyst = LoginService.getInstance().getCurrentAccount();
        List<WorkRequest> workRequests = LoginService.getInstance().getCurrentOrganization().findAssignedWorkRequests(securityAnalyst);
        CommonUtils.clear(tblWorkQueue);
        for (WorkRequest request : workRequests) {
            if (!request.getStatus().equals(WorkRequest.WORK_STATUS_RESOLVED)) {
                Object[] row = {
                    request.getSubmittedAt(),
                    request.getSender(),
                    request
                };
                CommonUtils.addRow(tblWorkQueue, row);
            }
        }
    }

    @Override
    public void reset() {
        populateTable();

    }
}