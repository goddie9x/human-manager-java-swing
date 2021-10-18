package View;

import Util.Message;
import Util.DefaultEvent;
import Util.Conversion;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import Controller.LoginController;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class Login extends javax.swing.JFrame {

    private ManagerHuman managerHumanFrame;
    private LoginController curentLoginController;

    public Login(LoginController curentLoginController) {
        initComponents();
        this.curentLoginController = curentLoginController;
        DefaultEvent.addEventOnclickClearTextField(accountField);
        DefaultEvent.addEventOnclickClearTextField(passwordField);
        DefaultEvent.forcusHideComponent(accountField, errorAccountLabel);
        DefaultEvent.forcusHideComponent(passwordField, errorPasswordLabel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcomeLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        accountField = new javax.swing.JTextField();
        labelAccount = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        errorAccountLabel = new javax.swing.JLabel();
        errorPasswordLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");

        welcomeLabel.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        welcomeLabel.setText("Welcome to Human Manager");
        welcomeLabel.setToolTipText("");

        passwordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordField.setToolTipText("Enter your password");
        passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        passwordField.setMargin(new java.awt.Insets(0, 10, 0, 0));
        passwordField.setNextFocusableComponent(loginButton);
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFieldFocusGained(evt);
            }
        });
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        accountField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        accountField.setText("Account");
        accountField.setToolTipText("Enter your user account");
        accountField.setActionCommand("<Not Set>");
        accountField.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        accountField.setInheritsPopupMenu(true);
        accountField.setMargin(new java.awt.Insets(10, 10, 10, 10));
        accountField.setNextFocusableComponent(passwordField);
        accountField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                accountFieldFocusGained(evt);
            }
        });

        labelAccount.setLabelFor(accountField);
        labelAccount.setText("Enter Account");

        labelPassword.setLabelFor(passwordField);
        labelPassword.setText("Enter password");

        loginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medias/Icon/icons8-confirm-35.png"))); // NOI18N
        loginButton.setText("Login");
        loginButton.setNextFocusableComponent(accountField);
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginButtonMouseClicked(evt);
            }
        });

        errorAccountLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorAccountLabel.setLabelFor(accountField);
        errorAccountLabel.setText("This field is required");
        errorAccountLabel.setVisible(false);

        errorPasswordLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorPasswordLabel.setLabelFor(passwordField);
        errorPasswordLabel.setText("This field is required");
        errorPasswordLabel.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(accountField, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(errorAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(errorPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(welcomeLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accountField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(errorAccountLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorPasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed

    }//GEN-LAST:event_passwordFieldActionPerformed

    private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseClicked
        String accountName = accountField.getText();
        String accountPassword = Conversion.passwordToString(passwordField.getPassword());
        boolean accountFieldEmpty = accountName.isEmpty();
        boolean passwordFieldEmpty = accountPassword.isEmpty();

        if (accountFieldEmpty || passwordFieldEmpty) {
            Border border = BorderFactory.createLineBorder(Color.RED, 3);

            Message.showError(this, "All field must not empty!");
            if (accountFieldEmpty) {
                accountField.setBorder(border);
                errorAccountLabel.setVisible(true);
            }
            if (passwordFieldEmpty) {
                passwordField.setBorder(border);
                errorPasswordLabel.setVisible(true);
            }
        } else {

            boolean isSetedLoginAccount
                    = curentLoginController.setUser(accountName, accountPassword);

            if (isSetedLoginAccount) {
                this.setVisible(false);
            } else {
                Message.showError(this, "Account or password is incorrect!");
            }
        }
    }//GEN-LAST:event_loginButtonMouseClicked

    private void accountFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_accountFieldFocusGained
        accountField.setBorder(null);
    }//GEN-LAST:event_accountFieldFocusGained

    private void passwordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusGained
        passwordField.setBorder(null);
    }//GEN-LAST:event_passwordFieldFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accountField;
    private javax.swing.JLabel errorAccountLabel;
    private javax.swing.JLabel errorPasswordLabel;
    private javax.swing.JLabel labelAccount;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
