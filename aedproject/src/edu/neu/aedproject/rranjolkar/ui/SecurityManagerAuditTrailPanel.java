/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.aedproject.rranjolkar.ui;

import edu.neu.aedproject.rranjolkar.AuditItem;
import edu.neu.aedproject.rranjolkar.Enterprise;
import edu.neu.aedproject.rranjolkar.Network;
import edu.neu.aedproject.rranjolkar.Organization;
import edu.neu.aedproject.rranjolkar.UserAccount;
import edu.neu.aedproject.rranjolkar.services.LoginService;
import edu.neu.aedproject.rranjolkar.trading.ClientAccount;
import edu.neu.aedproject.rranjolkar.trading.IOIWorkRequest;
import edu.neu.aedproject.rranjolkar.trading.Security;
import edu.neu.aedproject.rranjolkar.trading.TradingNetwork;
import edu.neu.aedproject.rranjolkar.trading.TradingOrganization;
import edu.neu.aedproject.rranjolkar.trading.TradingWorkRequest;
import edu.neu.aedproject.rranjolkar.trading.util.CommonUtils;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rutika Ranjolkar
 */
public class SecurityManagerAuditTrailPanel extends javax.swing.JPanel {

    private UserAccount selectedUser;

    /**
     * Creates new form PortfolioManagerClientsPanel
     */
    public SecurityManagerAuditTrailPanel() {
        initComponents();

        populateUsersList();

        /**
         * Add change listener to table
         */
        tblUsers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedUser = CommonUtils.getSelection(tblUsers, 1);
                populateAuditTable();
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
        txtUserSearchTerm = new javax.swing.JTextField();
        btnSearchUsers = new javax.swing.JButton();
        btnResetUsers = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAudit = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtAuditSearchTerm = new javax.swing.JTextField();
        btnSearchAudit = new javax.swing.JButton();
        btnResetAudit = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        btnSearchUsers.setText("Search");
        btnSearchUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchUsersActionPerformed(evt);
            }
        });

        btnResetUsers.setText("Reset");
        btnResetUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetUsersActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Select a user");

        tblAudit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Date", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblAudit);
        if (tblAudit.getColumnModel().getColumnCount() > 0) {
            tblAudit.getColumnModel().getColumn(1).setPreferredWidth(130);
        }

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Search for an action");

        txtAuditSearchTerm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAuditSearchTermActionPerformed(evt);
            }
        });

        btnSearchAudit.setText("Search");
        btnSearchAudit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchAuditActionPerformed(evt);
            }
        });

        btnResetAudit.setText("Reset");
        btnResetAudit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetAuditActionPerformed(evt);
            }
        });

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Username", "Employee", "Last Login"
            }
        ));
        jScrollPane3.setViewportView(tblUsers);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtUserSearchTerm, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearchUsers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnResetUsers))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtAuditSearchTerm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearchAudit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResetAudit))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUserSearchTerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchUsers)
                            .addComponent(btnResetUsers)
                            .addComponent(txtAuditSearchTerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSearchAudit)
                        .addComponent(btnResetAudit)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchUsersActionPerformed
        populateUsersList();
    }//GEN-LAST:event_btnSearchUsersActionPerformed

    private void btnResetUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetUsersActionPerformed
        txtUserSearchTerm.setText("");
        populateUsersList();
        populateAuditTable();
    }//GEN-LAST:event_btnResetUsersActionPerformed

    private void btnSearchAuditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchAuditActionPerformed
        populateAuditTable();
    }//GEN-LAST:event_btnSearchAuditActionPerformed

    private void btnResetAuditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetAuditActionPerformed
        txtAuditSearchTerm.setText("");
        populateAuditTable();
    }//GEN-LAST:event_btnResetAuditActionPerformed

    private void txtAuditSearchTermActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAuditSearchTermActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAuditSearchTermActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnResetAudit;
    private javax.swing.JButton btnResetUsers;
    private javax.swing.JButton btnSearchAudit;
    private javax.swing.JButton btnSearchUsers;
    private javax.swing.ButtonGroup btnsBuySell;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblAudit;
    private javax.swing.JTable tblUsers;
    private javax.swing.JTextField txtAuditSearchTerm;
    private javax.swing.JTextField txtUserSearchTerm;
    // End of variables declaration//GEN-END:variables

    private void populateUsersList() {
        CommonUtils.clear(tblUsers);
        String userSearchTerm = txtUserSearchTerm.getText().trim().toUpperCase();
        Enterprise enterprise = LoginService.getInstance().getCurrentEnterprise();
        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizations()) {
            for (UserAccount user : organization.getUserAccountDirectory().getUserAccounts()) {
                String username = user.getUsername();
                String name = user.getEmployee().getName();
                boolean usernameMatches = username.trim().toUpperCase().contains(userSearchTerm);
                boolean nameMatches = name.trim().toUpperCase().contains(userSearchTerm);
                if (usernameMatches || nameMatches) {
                    CommonUtils.addRow(tblUsers, new Object[]{user.getUsername(), user, user.getLastNormalLoginDate()});
                }
            }
        }
    }

    private void populateAuditTable() {
        CommonUtils.clear(tblAudit);
        String auditSearchTerm = txtAuditSearchTerm.getText().trim();
        if (selectedUser == null) {
            CommonUtils.showMessage("Please select a user");
        } else {
            List<AuditItem> auditTrail = selectedUser.getAuditTrail();
            for (AuditItem auditItem : auditTrail) {
                String action = auditItem.getAction() == null ? "" : auditItem.getAction();
                if (action.trim().toUpperCase().contains(auditSearchTerm.trim().toUpperCase())) {
                    CommonUtils.addRow(tblAudit, new Object[]{auditItem.getDate(), auditItem.getAction()});
                }
            }
        }
    }

}
