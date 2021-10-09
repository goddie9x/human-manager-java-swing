package Controller;

import Util.Account;
import View.AccountManagerView;

public class AccountManager {

    private Account currentAccount;
    private AccountManagerView currentAccountManagerView;
    
    public AccountManager(Account currentAccount) {
        this.currentAccount = currentAccount;
        
        currentAccountManagerView = new AccountManagerView(currentAccount);
    }
    
    private void loadAccountManagerView(){
        
    }
    
    public boolean changePassword(){
        boolean result = false;
        
        return result;
    }
}
