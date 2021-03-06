/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.aedproject.rranjolkar.ui;

/**
 *
 * @author Rutika Ranjolkar
 */
public class TraderTopPanel extends javax.swing.JPanel {

    /**
     * Creates new form PortfolioManagerTopPanel
     */
    public TraderTopPanel() {
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

        btnTradeMonitor = new javax.swing.JButton();
        btnPendingOrders = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnIOIs = new javax.swing.JButton();
        btnVacation = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 102));

        btnTradeMonitor.setText("Trade Monitor");
        btnTradeMonitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTradeMonitorActionPerformed(evt);
            }
        });

        btnPendingOrders.setText("My Orders");
        btnPendingOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPendingOrdersActionPerformed(evt);
            }
        });

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnIOIs.setText("IOI Management");
        btnIOIs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIOIsActionPerformed(evt);
            }
        });

        btnVacation.setText("Vacation Setup");
        btnVacation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVacationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnTradeMonitor, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPendingOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnIOIs, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVacation, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTradeMonitor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPendingOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIOIs, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVacation, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTradeMonitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTradeMonitorActionPerformed
        GenericJFrame.getInstance().populate(this, new TraderTradeMonitorPanel());
    }//GEN-LAST:event_btnTradeMonitorActionPerformed

    private void btnPendingOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPendingOrdersActionPerformed
        GenericJFrame.getInstance().populate(this, new TraderMyOrdersPanel());
    }//GEN-LAST:event_btnPendingOrdersActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        GenericJFrame.getInstance().logout();
        GenericJFrame.getInstance().populate(new WelcomeJPanel(), new LoginJPanel());
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnIOIsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIOIsActionPerformed
        GenericJFrame.getInstance().populate(this, new TraderIOIPanel());
    }//GEN-LAST:event_btnIOIsActionPerformed

    private void btnVacationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVacationActionPerformed
        GenericJFrame.getInstance().populate(this, new UserVacationPanel());
    }//GEN-LAST:event_btnVacationActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIOIs;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPendingOrders;
    private javax.swing.JButton btnTradeMonitor;
    private javax.swing.JButton btnVacation;
    // End of variables declaration//GEN-END:variables
}
