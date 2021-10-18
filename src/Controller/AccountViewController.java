package Controller;

import View.AccountView;
import View.ManagerUsers;
import Util.Account;
import Util.Account;
import Controller.ManagerUsersController;
import Controller.ManagerHumanController;
import Model.AccountModel;

public class AccountViewController {

    private Account currentUser;
    private Account currentAccountView;
    private ManagerHumanController lastManagerHumanController;
    private ManagerUsersController lastManagerUsersController;
    private AccountViewController accountViewController;
    private AccountView AccountViewFrame;
    private final AccountModel accountModel = new AccountModel();

    public AccountViewController(
            ManagerUsersController lastManagerUsersController,
            Account currentUser
    ) {
        this.currentUser = currentUser;
        this.lastManagerUsersController = lastManagerUsersController;
        loadAccountViewAdd();
    }

    public AccountViewController(
            ManagerUsersController lastManagerUsersController,
            Account currentUser,
            String thatAccountName
    ) {
        this.currentUser = currentUser;
        this.lastManagerUsersController = lastManagerUsersController;
        currentAccountView
                = accountModel.getAnAccountByAccountName(thatAccountName);

        loadAccountView(currentAccountView);
    }

    public AccountViewController(
            ManagerHumanController lastManagerHumanController,
            Account currentUser,
            String thatAccountName
    ) {
        this.currentUser = currentUser;
        this.lastManagerHumanController = lastManagerHumanController;
        currentAccountView
                = accountModel.getAnAccountByAccountName(thatAccountName);

        loadAccountView(currentAccountView);
    }

    public AccountViewController(
            ManagerHumanController lastManagerUsersController,
            Account currentUser
    ) {
        this.currentUser = currentUser;
        loadAccountView(currentAccountView);
    }

    private void loadAccountViewAdd() {
        AccountViewFrame = new AccountView(this, currentAccountView);
        AccountViewFrame.setLocationRelativeTo(null);
        AccountViewFrame.setVisible(true);
    }

    public void loadAccountView(Account account) {
        AccountViewFrame = new AccountView(this, currentUser, account);
        AccountViewFrame.setLocationRelativeTo(null);
        AccountViewFrame.setVisible(true);
    }

    public boolean deleteAccountByAccountName(String accountName) {
        return accountModel.deleteAnAccountByAccountName(accountName);
    }

    public boolean addAnAccount(Account account) {
        return accountModel.addAnAccount(account);
    }

    public boolean saveCurrentAccount(Account account) {
        return accountModel.updateAnAccount(account);
    }

    public void loadManagerUsersView(int page) {
        lastManagerUsersController.loadDataPageAccounts(page);
    }

    public void showManagerView() {
        if (lastManagerHumanController == null) {
            lastManagerUsersController.showThisUserManagerView();
        } else {
            lastManagerHumanController.showThisHumanManagerView();
        }
        disposeThisAccountView();
    }

    public void showThisAccountView() {
        AccountViewFrame.setVisible(true);
    }

    private void disposeThisAccountView() {
        AccountViewFrame.dispose();
    }
}
