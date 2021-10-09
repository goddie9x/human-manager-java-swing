package Model;

import Config.ConnectDbSql;
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

        String queryThatAccount = "select top 1 * from Accounts as Acc "
                + "where Acc.accountName = ? AND "
                + "Acc.accountPassword = ?";

        try {
            PreparedStatement queryThatAccountStatement
                    = DB.prepareStatement(queryThatAccount);

            queryThatAccountStatement.setString(1, accountName);
            queryThatAccountStatement.setString(2, accountPassword);

            ResultSet accountResult
                    = queryThatAccountStatement.executeQuery();

            accountResult.next();
            Account account = new Account(
                    accountResult.getString("userName"),
                    accountResult.getString("accountPassword"),
                    accountResult.getString("accountName"),
                    accountResult.getInt("accountRole")
            );

            return account;
        } catch (SQLException e) {
        }

        return new Account();
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
                        resultSet.getString("userName"),
                        resultSet.getString("accountPassword"),
                        resultSet.getString("accountName"),
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

    public int getAmountAccounts() {
        int quantityOfAccounts = 0;
        String countAccountsQuery = "SELECT COUNT(*) FROM Accounts";

        try {
            Statement statementQueryAllAccounts = DB.createStatement();
            ResultSet resultSet
                    = statementQueryAllAccounts.executeQuery(countAccountsQuery);
            resultSet.next();

            return resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quantityOfAccounts;
    }

    public ArrayList<Account> getAccountsWithKey(String key,
            int page) {
        int postitionStartSelectAccount = (page - 1) * PERPAGE;
        ArrayList<Account> Accounts = new ArrayList<>();

        String queyAllAccountsWithKey = "SELECT * FROM Accounts "
                + "WHERE AccountCode = ? or fullname = ? or country = ?"
                + " or phoneNumber = ?"
                + " ORDER BY AccountCode "
                + "OFFSET " + postitionStartSelectAccount + " ROWS "
                + "FETCH NEXT " + PERPAGE + " ROWS ONLY";

        try {
            PreparedStatement queryThatAccountStatement
                    = DB.prepareStatement(queyAllAccountsWithKey);

            queryThatAccountStatement.setString(1, key);
            queryThatAccountStatement.setString(2, key);
            queryThatAccountStatement.setString(3, key);
            queryThatAccountStatement.setString(4, key);

            ResultSet resultSet
                    = queryThatAccountStatement.executeQuery();

            while (resultSet.next()) {
                Account currentAccount = new Account(
                        resultSet.getString("userName"),
                        resultSet.getString("accountPassword"),
                        resultSet.getString("accountName"),
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

    public boolean updateAnAccount(
            Account newAccountResult) {
        String userName = newAccountResult.getUserName();
        boolean result = false;

        String updateEmeployeeStatement = "UPDATE Accounts "
                + "Set "
                + "accountName = N'"
                + newAccountResult.getAccountName()
                + "'," + "accountPassword = '"
                + newAccountResult.getPassword()
                + "'," + "accountRole = '"
                + newAccountResult.getAccountRole()
                + "' WHERE AccountCode = " + "'" + userName + "'";
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
        String insertEmeployeeStatement = "INSERT INTO Accounts "
                + "VALUES("
                + "'" + newAccountResult.getUserName() + ","
                + newAccountResult.getAccountName() + "','"
                + newAccountResult.getPassword() + "','"
                + newAccountResult.getAccountRole()
                + ");";
        System.out.print(insertEmeployeeStatement);
        try {
            Statement deleteAccount = DB.createStatement();
            deleteAccount.executeUpdate(insertEmeployeeStatement);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int[] deleteMultipleAccountsByAccountCode(String[] AccountCodes) {
        int AccountCodesLength = AccountCodes.length;
        //some time we need to checkout what employee has been deleted or not
        int[] statusDeleteMultipleAccount = new int[AccountCodesLength];

        for (int i = 0; i < AccountCodesLength; i++) {
            if (deleteAnAccountByAccountCode(AccountCodes[i])) {
                statusDeleteMultipleAccount[i] = 1;
            } else {
                statusDeleteMultipleAccount[i] = 0;
            }
        }

        return statusDeleteMultipleAccount;
    }

    public boolean deleteAnAccountByAccountCode(String AccountCode) {
        boolean result = false;
        String deleteAccountStatement = "DELETE FROM contractLabors WHERE "
                + "AccountCode = " + "'" + AccountCode + "';"
                + "DELETE FROM Accounts WHERE "
                + "AccountCode = " + "'" + AccountCode + "';";

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
