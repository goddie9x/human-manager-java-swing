package Controller;

import Util.Account;
import View.ManagerHuman;
import java.util.ArrayList;
import Model.EmployeeModel;
import Controller.EmployeeViewController;
import Controller.LoginController;
import Util.Employee;

public class ManagerHumanController {

    private ManagerHuman managerHumanFrame;
    private final EmployeeModel employeeModel = new EmployeeModel();
    private EmployeeViewController employeeViewController;
    private Account currentAccount;
    private LoginController currentLoginController;

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

    public ArrayList<Employee> loadDataPageEmployees(int page) {
        ArrayList<Employee> listEmployees = employeeModel.getEmployeesOnPage(page);

        return listEmployees;
    }

    public int[] deleteMultipleEmployeesByCode(ArrayList<String> EmployeeCodes) {
        return employeeModel.deleteMultipleEmployeesByEmployeeCode(EmployeeCodes);
    }

    public Employee getEmployeeByStaffCode(String staffCode) {
        Employee employee = employeeModel.getAnEmployeeByEmployeeCode(staffCode);
        return employee;
    }

    public int quantityOfEmployees() {
        return employeeModel.countEmployees();
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

    public void logout() {
        managerHumanFrame.dispose();

        currentLoginController.logout();
    }

    public void showThisHumanManagerView() {
        managerHumanFrame.setVisible(true);
        managerHumanFrame.renderTable();
    }

    public void hideThisHumanManagerView() {
        managerHumanFrame.setVisible(false);
    }
}
