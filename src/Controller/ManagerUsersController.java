package Controller;

import Util.Account;
import View.ManagerUsers;
import java.util.ArrayList;
import Model.AccountModel;
import Controller.ManagerHumanController;
import Controller.ManagerUsersController;
import Controller.LoginController;

public class ManagerUsersController {

    private ManagerUsers userManagerFrame;
    private final AccountModel accountModel = new AccountModel();
    private AccountViewController accountViewController;
    private Account currentAccount;
    private Account currentAdminAccount;
    private LoginController currentLoginController;
    private ManagerHumanController currentManagerHumanController;

    ManagerUsersController(
            ManagerHumanController currentManagerHumanController,
            Account currentAdminAccount
    ) {
        this.currentManagerHumanController = currentManagerHumanController;
        this.currentAdminAccount = currentAdminAccount;
        loadManagerUsers();
    }

    public void loadManagerUsers() {
        userManagerFrame = new ManagerUsers(currentAccount, this);
        userManagerFrame.setLocationRelativeTo(null);
        userManagerFrame.setVisible(true);
    }

    public void loadAccountViewByAccountName(String accountName) {
        accountViewController = new AccountViewController(
                this,
                currentAdminAccount,
                accountName
        );
    }

    public void loadAddNewAccountView() {
        accountViewController = new AccountViewController(
                this,
                currentAccount
        );
    }

    public void loadCurrentAccountView() {
        accountViewController = new AccountViewController(
                this,
                currentAdminAccount,
                currentAdminAccount.getAccountName()
        );
    }

    public void loadViewAnAccount(String thatAccountName) {
        accountViewController = new AccountViewController(
                this,
                currentAccount,
                thatAccountName
        );
    }

    public ArrayList<Account> loadDataPageAccounts(int page) {
        ArrayList<Account> listAccounts
                = accountModel.getAllAccountsInfoFromPageExceptCurrentAdminAccount(
                        page, currentAdminAccount.getAccountName());

        return listAccounts;
    }

    public int[] deleteMultipleAccountsByCode(ArrayList<String> AccountNames) {
        return accountModel.deleteMultipleAccountsByAccountName(AccountNames);
    }

    public Account getAccountByAccountName(String accountName) {
        Account account = accountModel.getAnAccountByAccountName(accountName);
        return account;
    }

    public int quantityOfAccounts(String searchTex) {
        if (searchTex.isEmpty()) {
            return accountModel.getAmountAccountsWithCondition(
                    currentAdminAccount.getAccountName());
        }
        return accountModel.getAmountAccountsWithCondition(
                searchTex, currentAdminAccount.getAccountName());
    }

    public ArrayList<Account> searchAccountsWithKey(String key, int page) {
        ArrayList<Account> accounts;
        if (key.isEmpty()) {
            accounts = accountModel
                    .getAllAccountsInfoFromPageExceptCurrentAdminAccount(
                            page, currentAdminAccount.getAccountName());

        } else {
            accounts = accountModel.getAccountsWithKeyExceptCurrentAdminAccount(
                    key, page, currentAdminAccount.getAccountName());
        }

        return accounts;
    }

    public void logout() {
        currentManagerHumanController.logout();
        disposeThisUserManagerView();
    }

    public void backToHumanManager() {
        currentManagerHumanController.showThisHumanManagerView();
        disposeThisUserManagerView();
    }

    public void disposeThisUserManagerView() {
        userManagerFrame.dispose();
    }

    public void showThisUserManagerView() {
        userManagerFrame.setVisible(true);
        userManagerFrame.reloadTable();
    }

    public void hideThisUserManagerView() {
        userManagerFrame.setVisible(false);
    }
}
