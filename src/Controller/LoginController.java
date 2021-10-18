package Controller;

import Model.AccountModel;
import Controller.ManagerHumanController;
import View.Login;
import Util.Account;

public class LoginController {
    private Login loginFrame;
    private Account currentAccount;
    private final  AccountModel accountModel = new AccountModel();

    public void loadViewLogin() {
        loginFrame = new Login(this);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    public boolean setUser(String accountName, String accountPassword) {
        currentAccount = accountModel.getAccountInfo(accountName, accountPassword);

        if (currentAccount.getAccountRole() > 0) {
            startManagerHumanController(currentAccount);
            return true;
        }
        return false;
    }

    private void startManagerHumanController(Account account) {
        ManagerHumanController managerHumanController = new ManagerHumanController(account, this);
    }

    public void logout(){
        loginFrame = null;
        loadViewLogin();
    }
    
    public void hideThisLoginView() {
        loginFrame.dispose();
    }
}
