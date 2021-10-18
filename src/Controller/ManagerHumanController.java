package Controller;

import Util.Account;
import View.ManagerHuman;
import java.util.ArrayList;
import Model.EmployeeModel;
import Controller.EmployeeViewController;
import Controller.LoginController;
import Controller.ManagerUsersController;
import Controller.AccountViewController;
import Util.Employee;

public class ManagerHumanController {

    private ManagerHuman managerHumanFrame;
    private final EmployeeModel employeeModel = new EmployeeModel();
    private EmployeeViewController employeeViewController;
    private Account currentAccount;
    private LoginController currentLoginController;
    private ManagerUsersController nextManagerUsersController;
    private AccountViewController nextAccountViewController;

    ManagerHumanController(Account currentAccount, LoginController currentLoginController) {
        this.currentAccount = currentAccount;
        this.currentLoginController = currentLoginController;

        loadManagerHumanView();
    }

    public void loadManagerHumanView() {
        managerHumanFrame = new ManagerHuman(currentAccount.getAccountRole(), this);
        managerHumanFrame.setLocationRelativeTo(null);
        managerHumanFrame.setVisible(true);
    }

    public void loadAddNewEmployeeView() {
        employeeViewController = new EmployeeViewController(
                currentAccount.getAccountRole(),
                this
        );
    }

    public void loadViewAnEmployee(String staffCode) {
        employeeViewController = new EmployeeViewController(
                currentAccount.getAccountRole(),
                this,
                staffCode);
    }

    public ArrayList<Employee> loadDataPageEmployees(int page) {
        ArrayList<Employee> listEmployees = employeeModel.getEmployeesOnPage(page);

        return listEmployees;
    }

    public int[] deleteMultipleEmployeesByCode(ArrayList<String> EmployeeCodes) {
        return employeeModel.deleteMultipleEmployeesByEmployeeCode(EmployeeCodes);
    }

    public boolean checkPasswordCompareCurrentAccountPassword(String password) {
        return password.equals(currentAccount.getPassword());
    }

    public Employee getEmployeeByStaffCode(String staffCode) {
        Employee employee = employeeModel.getAnEmployeeByEmployeeCode(staffCode);
        return employee;
    }

    public int quantityOfEmployees(String searchTex) {
        if (searchTex.isEmpty()) {
            return employeeModel.countEmployees();
        }
        else{
            return employeeModel.countEmployeesWithKey(searchTex);
        }

    }

    public void loadEmployeeViewByStaffCode(String staffCode) {
        employeeViewController = new EmployeeViewController(
                currentAccount.getAccountRole(),
                this,
                staffCode
        );
    }

    public ArrayList<Employee> searchEmployeesWithKey(String key, int page) {
        ArrayList<Employee> employees;
        if (key.isEmpty()) {
            employees = employeeModel.getEmployeesOnPage(page);

        } else {
            employees = employeeModel.getEmployeesWithKey(key, page);
        }

        return employees;
    }

    public void loadCurrentAccount() {
        nextAccountViewController = new AccountViewController(
                this, currentAccount, currentAccount.getAccountName());
        this.hideThisHumanManagerView();
    }

    public void loadManagerUsersViews() {
        nextManagerUsersController = new ManagerUsersController(this, currentAccount);

        nextManagerUsersController.getAccountByAccountName(
                currentAccount.getAccountName());
        hideThisHumanManagerView();
    }

    public void logout() {
        disposeThisHumanManagerView();
        currentLoginController.logout();
    }

    public void disposeThisHumanManagerView() {
        managerHumanFrame.dispose();
    }

    public void showThisHumanManagerView() {
        managerHumanFrame.setVisible(true);
        managerHumanFrame.reloadTable();
    }

    public void hideThisHumanManagerView() {
        managerHumanFrame.setVisible(false);
    }
}
