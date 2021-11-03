package Model;

import Config.ConnectDbSql;
import Util.PassEncHashSalt;
import Util.Account;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

public class AccountModel {

    private final Connection DB = ConnectDbSql.getInstance().getConnection();
    private final int PERPAGE = 10;

    public Account getAccountInfo(String accountName, String accountPassword) {
        Account thatAccount = getAnAccountByAccountName(accountName);
        if (thatAccount == null) {
            return new Account();
        }

        String salt = thatAccount.getSalt().trim();
        String thatPassword = thatAccount.getPassword().trim();
        boolean isComparePassWord = PassEncHashSalt.verifyUserPassword(
                accountPassword, thatPassword, salt);

        if (isComparePassWord) {
            return thatAccount;
        }
        return new Account();
    }

    public ArrayList<Account> getAllAccountsInfoFromPageExceptCurrentAdminAccount(
            int page, String currentAdminAccountName) {
        ArrayList<Account> accounts = getAccountsWithConditonInPage(
                "*", " accountName <> '" + currentAdminAccountName + "'", 1);

        return accounts;
    }

    public ArrayList<Account> getAllAccountsInfoFromPage(int page) {
        ArrayList<Account> accounts = getAccountsWithConditonInPage(
                "*", "1=1", 1);

        return accounts;
    }

    public ArrayList<Account> getAccountsWithConditonInPage(String column,
            String condition,
            int page) {

        int postitionStartSelectAccount = (page - 1) * PERPAGE;
        ArrayList<Account> accounts = new ArrayList<Account>();

        String queyAllAccounts = "SELECT * FROM Accounts "
                + "WHERE " + condition
                + " ORDER BY accountID "
                + "OFFSET " + postitionStartSelectAccount + " ROWS "
                + "FETCH NEXT " + PERPAGE + " ROWS ONLY";

        try {
            Statement statementQueryAllAccounts = DB.createStatement();
            ResultSet resultSet
                    = statementQueryAllAccounts.executeQuery(queyAllAccounts);

            while (resultSet.next()) {
                Account currentAccount = new Account(
                        resultSet.getString("userName").trim(),
                        resultSet.getString("accountPassword").trim(),
                        "",
                        resultSet.getString("accountName").trim(),
                        resultSet.getInt("accountRole")
                );
                accounts.add(currentAccount);
            }
            return accounts;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return accounts;
    }

    public int getAmountAccountsWithCondition(String condition,
            String currentAdminAccountName) {
        int quantityOfAccounts = 1;
        String countAccountsQuery = "SELECT COUNT(*) FROM Accounts where"
                + "(userName = ? or accountName = ?) and "
                + " accountName <> '" + currentAdminAccountName + "'";

        try {
            PreparedStatement statementQueryAllAccounts = DB.prepareStatement(
                    countAccountsQuery);
            statementQueryAllAccounts.setString(1, condition);
            statementQueryAllAccounts.setString(2, condition);

            ResultSet resultSet
                    = statementQueryAllAccounts.executeQuery();
            resultSet.next();

            return resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quantityOfAccounts;
    }

    public int getAmountAccountsWithCondition(
            String currentAdminAccountName) {
        int quantityOfAccounts = 1;
        String countAccountsQuery = "SELECT COUNT(*) FROM Accounts";

        try {
            PreparedStatement statementQueryAllAccounts = DB.prepareStatement(
                    countAccountsQuery);

            ResultSet resultSet
                    = statementQueryAllAccounts.executeQuery();
            resultSet.next();

            return resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quantityOfAccounts;
    }

    public ArrayList<Account> getAccountsWithKeyExceptCurrentAdminAccount(String key,
            int page, String currentAdminAccountName) {
        if(page < 1){
            page = 1;
        }
        
        int postitionStartSelectAccount = (page - 1) * PERPAGE;
        ArrayList<Account> Accounts = new ArrayList<>();

        String queyAllAccountsWithKey = "SELECT * FROM Accounts "
                + "WHERE (userName = ? or accountName = ?) and "
                + " accountName <> '" + currentAdminAccountName
                + "' ORDER BY accountName "
                + "OFFSET " + postitionStartSelectAccount + " ROWS "
                + "FETCH NEXT " + PERPAGE + " ROWS ONLY";

        try {
            PreparedStatement queryThatAccountStatement
                    = DB.prepareStatement(queyAllAccountsWithKey);

            queryThatAccountStatement.setString(1, key);
            queryThatAccountStatement.setString(2, key);

            ResultSet resultSet
                    = queryThatAccountStatement.executeQuery();

            while (resultSet.next()) {
                Account currentAccount = new Account(
                        resultSet.getString("userName").trim(),
                        resultSet.getString("accountPassword").trim(),
                        "",
                        resultSet.getString("accountName").trim(),
                        resultSet.getInt("accountRole")
                );

                Accounts.add(currentAccount);
            }
            return Accounts;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return Accounts;
    }

    public ArrayList<Account> getAccountsWithKey(String key,
            int page) {
        int postitionStartSelectAccount = (page - 1) * PERPAGE;
        ArrayList<Account> Accounts = new ArrayList<>();

        String queyAllAccountsWithKey = "SELECT * FROM Accounts "
                + "WHERE userName = ? or accountName = ?"
                + " ORDER BY accountName "
                + "OFFSET " + postitionStartSelectAccount + " ROWS "
                + "FETCH NEXT " + PERPAGE + " ROWS ONLY";

        try {
            PreparedStatement queryThatAccountStatement
                    = DB.prepareStatement(queyAllAccountsWithKey);

            queryThatAccountStatement.setString(1, key);
            queryThatAccountStatement.setString(2, key);

            ResultSet resultSet
                    = queryThatAccountStatement.executeQuery();

            while (resultSet.next()) {
                Account currentAccount = new Account(
                        resultSet.getString("userName").trim(),
                        resultSet.getString("accountPassword").trim(),
                        "",
                        resultSet.getString("accountName").trim(),
                        resultSet.getInt("accountRole")
                );

                Accounts.add(currentAccount);
            }
            return Accounts;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return Accounts;
    }

    public Account getAnAccountByAccountName(String accountName) {
        String queryThatAccount = "select top 1 * from Accounts "
                + "where accountName = '" + accountName + "'";

        try {
            Statement queryThatAccountStatement = DB.createStatement();
            ResultSet employeeResult
                    = queryThatAccountStatement.executeQuery(queryThatAccount);
            employeeResult.next();

            Account thatAccount = new Account(
                    employeeResult.getString("userName").trim(),
                    employeeResult.getString("accountPassword").trim(),
                    employeeResult.getString("salt"),
                    employeeResult.getString("accountName").trim(),
                    employeeResult.getInt("accountRole")
            );
            return thatAccount;
        } catch (SQLException exception) {
            return null;
        }
    }

    public boolean updateAnAccount(
            Account newAccountResult) {
        String userName = newAccountResult.getUserName();
        boolean result = false;
        String saltvalue = PassEncHashSalt.getSaltvalue(30);
        String encryptedpassword
                = PassEncHashSalt.generateSecurePassword(
                        newAccountResult.getPassword(), saltvalue);

        String updateEmeployeeStatement = "UPDATE Accounts "
                + "Set "
                + "accountName = N'"
                + newAccountResult.getAccountName()
                + "'," + "accountPassword = '"
                + encryptedpassword
                + "'," + "salt = '"
                + saltvalue
                + "'," + "accountRole = '"
                + newAccountResult.getAccountRole()
                + "' WHERE accountName = " + "'" + userName + "'";
        try {
            Statement deleteAccount = DB.createStatement();
            deleteAccount.executeUpdate(updateEmeployeeStatement);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean addAnAccount(Account newAccountResult) {
        boolean result = false;
        String saltvalue = PassEncHashSalt.getSaltvalue(30);
        String encryptedpassword
                = PassEncHashSalt.generateSecurePassword(
                        newAccountResult.getPassword(), saltvalue);

        String insertEmeployeeStatement = "INSERT INTO Accounts "
                + "VALUES("
                + "N'" + newAccountResult.getUserName() + "','"
                + newAccountResult.getAccountName() + "','"
                + encryptedpassword + "','"
                + saltvalue + "',"
                + newAccountResult.getAccountRole()
                + ");";
        try {
            Statement insertAccount = DB.createStatement();
            insertAccount.executeUpdate(insertEmeployeeStatement);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int[] deleteMultipleAccountsByAccountName(ArrayList<String> AccountNames) {
        int AccountNamesLength = AccountNames.size();

        int[] statusDeleteMultipleAccount = new int[AccountNamesLength];

        for (int i = 0; i < AccountNamesLength; i++) {
            String currentAccountName = AccountNames.get(i);

            if (deleteAnAccountByAccountName(currentAccountName)) {
                statusDeleteMultipleAccount[i] = 1;
            } else {
                statusDeleteMultipleAccount[i] = 0;
            }
        }

        return statusDeleteMultipleAccount;
    }

    public boolean deleteAnAccountByAccountName(String accountName) {
        boolean result = false;
        String deleteAccountStatement = "DELETE FROM Accounts WHERE "
                + "accountName = " + "'" + accountName + "';";

        try {
            Statement deleteAccount = DB.createStatement();
            deleteAccount.executeUpdate(deleteAccountStatement);
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
