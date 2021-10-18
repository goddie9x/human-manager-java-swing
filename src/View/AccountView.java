package View;

import Util.Account;
import Util.PassEncHashSalt;
import Util.Message;
import Util.Conversion;
import Util.DefaultEvent;
import Util.Validatetion;
import Controller.AccountViewController;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class AccountView extends javax.swing.JFrame {

    private Account currentManagerAccountViewing;
    private Account currentManagerAccount;
    private AccountViewController currentManagerAccountViewController;
    private final Border border = BorderFactory.createLineBorder(Color.RED, 3);

    public AccountView(
            AccountViewController currentManagerAccountViewController,
            Account currentManagerAccount
    ) {
        this.currentManagerAccountViewController = currentManagerAccountViewController;
        this.currentManagerAccount = currentManagerAccount;
        createAccountMode();
        hideRedAlertLabel();
        setAutoHideWarningOnForcusField();
    }

    public AccountView(
            AccountViewController currentManagerAccountViewController,
            Account currentManagerAccount,
            Account currentManagerAccountViewing
    ) {
        this.currentManagerAccountViewController = currentManagerAccountViewController;
        this.currentManagerAccount = currentManagerAccount;
        this.currentManagerAccountViewing = currentManagerAccountViewing;
        viewAccountMode();
        hideRedAlertLabel();
    }

    private void setAutoHideWarningOnForcusField() {
        DefaultEvent.forcusHideWarning(userNameField, userNameFieldWarningLabel);
        DefaultEvent.forcusHideWarning(PasswordField, PasswordWarningLabel);
        DefaultEvent.forcusHideWarning(
                reEnterPasswordField, reEnterPasswordWarningLabel);
        DefaultEvent.forcusHideWarning(
                accountNameField, accountNameFieldWarningLabel);
    }

    private void setDataAllTextField(Account account) {
        String accountName
                = account.getAccountName();
        String password
                = account.getPassword();
        String userName
                = account.getUserName();

        int accountRole
                = account.getAccountRole();

        switch (accountRole) {
            case 1: {
                managerRadio.setSelected(true);
                break;
            }
            case 2: {
                generalRadio.setSelected(true);
                break;
            }
        }
        userNameField.setText(userName);
        PasswordField.setText(password);
        reEnterPasswordField.setText(password);
        accountNameField.setText(accountName);
    }

    private Account getDataAllTextField() {
        int accountRole = roleGroup.getButtonCount();
        String password = Conversion.passwordToString(
                PasswordField.getPassword()
        );

        Account newAccount = new Account(
                userNameField.getText(),
                password,
                "",
                accountNameField.getText(),
                accountRole
        );

        return newAccount;
    }

    private void disableEditableAllTextField() {
        userNameField.setEditable(false);
        generalRadio.setEnabled(false);
        accountNameField.setEditable(false);
        if (currentManagerAccount.getAccountRole() == 1) {
            managerRadio.setEnabled(false);
        }
    }

    private void enableEditableAllTextField() {
        userNameField.setEditable(true);
        generalRadio.setEnabled(true);
        managerRadio.setEnabled(true);
    }

    private void createAccountMode() {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        deteteButton.setVisible(false);
        editButton.setVisible(false);
        clearAllTexField();
    }

    private void editAccountMode() {
        confirmButton.setVisible(true);
        deteteButton.setVisible(true);
        editButton.setVisible(false);
        reEnterPasswordLabel.setVisible(true);
        PasswordField.setVisible(true);
        reEnterPasswordField.setVisible(true);
        enableEditableAllTextField();
        setAutoHideWarningOnForcusField();
    }

    private void viewAccountMode() {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        passwordLabel.setVisible(false);
        reEnterPasswordWarningLabel.setVisible(false);
        confirmButton.setVisible(false);
        deteteButton.setVisible(false);
        PasswordField.setVisible(false);
        reEnterPasswordLabel.setVisible(false);
        reEnterPasswordField.setVisible(false);
        setDataAllTextField(currentManagerAccountViewing);
        disableEditableAllTextField();
    }

    private void clearAllTexField() {
        setDataAllTextField(new Account());
    }

    private boolean addAnAccount(Account account) {
        return currentManagerAccountViewController.addAnAccount(account);
    }

    private boolean updateCurrentEmoloyee() {
        currentManagerAccountViewing = getDataAllTextField();

        return currentManagerAccountViewController.saveCurrentAccount(currentManagerAccountViewing);
    }

    private boolean deleteCurrentAccount() {
        return currentManagerAccountViewController.deleteAccountByAccountName(
                currentManagerAccountViewing.getAccountName()
        );
    }

    private boolean validateAccountForm() {
        boolean isValidated = true;
        String userName = userNameField.getText();
        String accountName = accountNameField.getText();
        String accountPassword = Conversion.passwordToString(
                PasswordField.getPassword());
        String reAccountPassword = Conversion.passwordToString(
                reEnterPasswordField.getPassword());
        int role = roleGroup.getButtonCount();

        if (!Validatetion.checkLengthInRange(userName, 5, 100)) {
            userNameField.setBorder(border);
            userNameFieldWarningLabel.setVisible(true);
            isValidated = false;
        }

        if (!Validatetion.checkAccountName(accountName)) {
            accountNameField.setBorder(border);
            accountNameFieldWarningLabel.setVisible(true);
            isValidated = false;
        }

        if (!reAccountPassword.equals(accountPassword)) {
            reEnterPasswordWarningLabel.setText("Password are not same");
            reEnterPasswordWarningLabel.setVisible(true);
        }

        if (accountPassword.length() < 5) {
            PasswordWarningLabel.setVisible(true);
        }
        return isValidated;
    }

    private void handleConfirmOnclick() {
        if (currentManagerAccountViewing == null) {
            Account account = getDataAllTextField();
            if (addAnAccount(account)) {
                Message.showSuccess(this, "Add data success");
            } else {
                alertWhenGotErrorFromSever();
            }
        } else {
            if (updateCurrentEmoloyee()) {
                Message.showSuccess(this, "Save data success");
            } else {
                alertWhenGotErrorFromSever();
            }
        }
    }

    private void showRedAlertLabel(String every_box_need_to_be_fill) {
        alertLabel.setVisible(true);
    }

    private void hideRedAlertLabel() {
        alertLabel.setVisible(false);
        userNameFieldWarningLabel.setVisible(false);
        accountNameFieldWarningLabel.setVisible(false);
        reEnterPasswordWarningLabel.setVisible(false);
        PasswordWarningLabel.setVisible(false);
    }

    private void hideRedAlertBorders() {
        userNameField.setBorder(null);
        reEnterPasswordWarningLabel.setBorder(null);
        PasswordWarningLabel.setBorder(null);
    }

    private boolean saveChangeCurrentAccount() {
        return currentManagerAccountViewController.saveCurrentAccount(
                currentManagerAccountViewing);
    }

    private void alertWhenGotErrorFromSever() {
        Message.showError(this, "Some error have been occured, please check your data");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roleGroup = new javax.swing.ButtonGroup();
        userName = new javax.swing.JLabel();
        userNameField = new javax.swing.JTextField();
        stafInforLabel = new javax.swing.JLabel();
        accountNameLabel = new javax.swing.JLabel();
        reEnterPasswordLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        deteteButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        alertLabel = new javax.swing.JLabel();
        PasswordWarningLabel = new javax.swing.JLabel();
        reEnterPasswordWarningLabel = new javax.swing.JLabel();
        userNameFieldWarningLabel = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        reEnterPasswordField = new javax.swing.JPasswordField();
        managerRadio = new javax.swing.JRadioButton();
        generalRadio = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        accountNameField = new javax.swing.JTextField();
        accountNameFieldWarningLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Staff info");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        userName.setLabelFor(userNameField);
        userName.setText("User name");

        userNameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        userNameField.setToolTipText("Staff code");

        stafInforLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        stafInforLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medias/Icon/icons8-confirm-35.png"))); // NOI18N
        stafInforLabel.setText("User info");

        accountNameLabel.setText("Account name");

        reEnterPasswordLabel.setLabelFor(reEnterPasswordField);
        reEnterPasswordLabel.setText("Re enter password");

        passwordLabel.setLabelFor(PasswordField);
        passwordLabel.setText("Password");

        confirmButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medias/Icon/icons8-verified-account-30.png"))); // NOI18N
        confirmButton.setText("Confirm");
        confirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmButtonMouseClicked(evt);
            }
        });

        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Icon/icons8-delete-30.png"))); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelButtonMouseClicked(evt);
            }
        });

        deteteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Icon/icons8-bin-30.png"))); // NOI18N
        deteteButton.setText("Delete");
        deteteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deteteButtonMouseClicked(evt);
            }
        });

        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medias/Icon/icons8-edit-30.png"))); // NOI18N
        editButton.setText("Edit");
        editButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editButtonMouseClicked(evt);
            }
        });

        alertLabel.setForeground(new java.awt.Color(255, 0, 0));
        alertLabel.setText("Every box has red alert need to be fill");

        PasswordWarningLabel.setForeground(new java.awt.Color(255, 0, 0));
        PasswordWarningLabel.setText("Please enter at least 5 characters");

        reEnterPasswordWarningLabel.setForeground(new java.awt.Color(255, 0, 0));
        reEnterPasswordWarningLabel.setText("Password must be equals");

        userNameFieldWarningLabel.setForeground(new java.awt.Color(255, 0, 0));
        userNameFieldWarningLabel.setLabelFor(userNameField);
        userNameFieldWarningLabel.setText("Please enter at least 5 characters");

        PasswordField.setText("jPasswordField1");

        reEnterPasswordField.setText("jPasswordField1");

        roleGroup.add(managerRadio);
        managerRadio.setText("Manager");

        roleGroup.add(generalRadio);
        generalRadio.setSelected(true);
        generalRadio.setText("General");

        jLabel1.setLabelFor(generalRadio);
        jLabel1.setText("Role");

        accountNameFieldWarningLabel.setForeground(new java.awt.Color(255, 0, 0));
        accountNameFieldWarningLabel.setLabelFor(userNameField);
        accountNameFieldWarningLabel.setText("Do not enter strange characters here");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(PasswordWarningLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(userNameFieldWarningLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userName, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(PasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(userNameField))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(accountNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(accountNameFieldWarningLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(managerRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(accountNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(generalRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reEnterPasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(reEnterPasswordWarningLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(reEnterPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                        .addGap(0, 263, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(confirmButton)
                        .addGap(18, 18, 18)
                        .addComponent(editButton)
                        .addGap(18, 18, 18)
                        .addComponent(deteteButton)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(alertLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stafInforLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(stafInforLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alertLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(accountNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(accountNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNameFieldWarningLabel)
                    .addComponent(accountNameFieldWarningLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(PasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(managerRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(generalRadio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PasswordWarningLabel)
                .addGap(18, 18, 18)
                .addComponent(reEnterPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reEnterPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(reEnterPasswordWarningLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmButton)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton)
                    .addComponent(deteteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseClicked
        currentManagerAccountViewController.showManagerView();
        this.dispose();
    }//GEN-LAST:event_cancelButtonMouseClicked

    private void editButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMouseClicked
        boolean isViewCurrentAccount = currentManagerAccount.getAccountName()
                .equals(currentManagerAccountViewing.getAccountName());

        if (isViewCurrentAccount) {
            String passwordEntered = Message.getConfirmPassWord();
            String salt = currentManagerAccount.getSalt().trim();
            String thatPassword = currentManagerAccount.getPassword().trim();
            boolean isPasswordCompareCurrentUserPassword
                    = PassEncHashSalt.verifyUserPassword(
                            passwordEntered, thatPassword, salt);

            if (!isPasswordCompareCurrentUserPassword) {
                Message.showError(null, "Password not corrected");
                return;
            }
        }
        editAccountMode();
    }//GEN-LAST:event_editButtonMouseClicked

    private void confirmButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseClicked
        if (validateAccountForm()) {
            handleConfirmOnclick();
        } else {
            Message.showError(this, "All field need to be fill");
        }
    }//GEN-LAST:event_confirmButtonMouseClicked

    private void deteteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deteteButtonMouseClicked
        boolean userSelected = Message.showYesNoQuestion(null,
                "Do you really want to delete this? That action cannot undo");
        if (userSelected) {
            boolean isDeleted
                    = currentManagerAccountViewController.deleteAccountByAccountName(
                            currentManagerAccountViewing.getAccountName()
                    );
            if (isDeleted) {
                Message.showSuccess(this, "Delete success");
                currentManagerAccountViewController.showManagerView();
            } else {
                alertWhenGotErrorFromSever();
            }
        }
    }//GEN-LAST:event_deteteButtonMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        currentManagerAccountViewController.showManagerView();
        this.dispose();
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel PasswordWarningLabel;
    private javax.swing.JTextField accountNameField;
    private javax.swing.JLabel accountNameFieldWarningLabel;
    private javax.swing.JLabel accountNameLabel;
    private javax.swing.JLabel alertLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JButton deteteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JRadioButton generalRadio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton managerRadio;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField reEnterPasswordField;
    private javax.swing.JLabel reEnterPasswordLabel;
    private javax.swing.JLabel reEnterPasswordWarningLabel;
    private javax.swing.ButtonGroup roleGroup;
    private javax.swing.JLabel stafInforLabel;
    private javax.swing.JLabel userName;
    private javax.swing.JTextField userNameField;
    private javax.swing.JLabel userNameFieldWarningLabel;
    // End of variables declaration//GEN-END:variables
}
