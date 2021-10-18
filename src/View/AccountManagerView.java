package View;

import Controller.AccountManager;
import Util.Account;

public class AccountManagerView extends javax.swing.JFrame {

    private AccountManager currentAccountManagerController;
    private Account currentAccount;

    public AccountManagerView(Account currentAccount) {
        initComponents();
        this.currentAccount = currentAccount;

        userNameValueLabel.setText(currentAccount.getUserName());

    }

    private void setRole() {
        switch (currentAccount.getAccountRole()) {
            case 1: {
                RoleValueLabel.setText("Manager");
                break;
            }
            case 2: {
                RoleValueLabel.setText("Genarel user");
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userNameValueLabel = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JLabel();
        RoleValueLabel = new javax.swing.JLabel();
        roleLabel = new javax.swing.JLabel();
        changeInfoButton = new javax.swing.JButton();
        userInforLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        userNameValueLabel.setText("User name:");

        userNameLabel.setText("User name:");

        RoleValueLabel.setText("User name:");

        roleLabel.setText("Role:");

        changeInfoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Icon/icons8-edit-30.png"))); // NOI18N
        changeInfoButton.setText("Change info");

        userInforLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        userInforLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Icon/icons8-profile-48_1.png"))); // NOI18N
        userInforLabel.setText("User Info");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(roleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(RoleValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userNameValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(changeInfoButton)))
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(userInforLabel)
                .addGap(137, 137, 137))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userInforLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userNameValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RoleValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(changeInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel RoleValueLabel;
    private javax.swing.JButton changeInfoButton;
    private javax.swing.JLabel roleLabel;
    private javax.swing.JLabel userInforLabel;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JLabel userNameValueLabel;
    // End of variables declaration//GEN-END:variables
}
