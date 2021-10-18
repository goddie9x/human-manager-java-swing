package Util;

public class Account {

    private String userName;
    private String accountPassword;
    private String accountName;
    private String salt;
    private int accountRole;

    public Account() {

    }

    public Account(
            String userName,
            String accountPassword,
            String salt,
            String accountName,
            int accountRole
    ) {
        this.userName = userName;
        this.accountPassword = accountPassword;
        this.salt = salt;
        this.accountName = accountName;
        this.accountRole = accountRole;
    }

    public String getUserName() {
        return userName;
    }

    public int getAccountRole() {
        return accountRole;
    }

    public String getPassword() {
        return accountPassword;
    }

    public String getSalt() {
        return salt;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAccountRole(int accountRole) {
        this.accountRole = accountRole;
    }

    public void setPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
