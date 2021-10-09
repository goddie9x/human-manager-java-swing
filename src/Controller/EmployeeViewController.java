package Controller;

import View.EmployeeView;
import View.ManagerHuman;
import Util.Employee;
import Util.Account;
import Controller.ManagerHumanController;
import Model.EmployeeModel;

public class EmployeeViewController {

    private int currentAccountRole;
    private ManagerHumanController lastManagerHumanController;
    private EmployeeView EmployeeViewFrame;
    private final EmployeeModel employeeModel = new EmployeeModel();

    public EmployeeViewController(
            int currentAccountRole,
            ManagerHumanController lastManagerHumanController
    ) {
        this.currentAccountRole = currentAccountRole;
        this.lastManagerHumanController = lastManagerHumanController;
        loadEmployeeViewAdd();
    }

    public EmployeeViewController(
            int currentAccountRole,
            ManagerHumanController lastManagerHumanController,
            String thatStaffCode
    ) {

        this.currentAccountRole = currentAccountRole;
        this.lastManagerHumanController = lastManagerHumanController;
        Employee currentEmploylee
                = employeeModel.getAnEmployeeByEmployeeCode(thatStaffCode);

        loadEmployeeView(currentEmploylee);
    }

    private void loadEmployeeViewAdd() {
        EmployeeViewFrame = new EmployeeView(this);
        EmployeeViewFrame.setLocationRelativeTo(null);
        EmployeeViewFrame.setVisible(true);
    }

    private void loadEmployeeView(Employee currentEmploylee) {
        EmployeeViewFrame = new EmployeeView(this, currentEmploylee);
        EmployeeViewFrame.setLocationRelativeTo(null);
        EmployeeViewFrame.setVisible(true);
    }

    public boolean deleteEmployeeByStaffCode(String staffCode) {
        return employeeModel.deleteAnEmployeeByEmployeeCode(staffCode);
    }

    public boolean addAnEmployee(Employee employee) {
        return employeeModel.addAnEmployee(employee);
    }

    public boolean saveCurrentEmployee(Employee employee) {
        return employeeModel.updateAnEmployee(employee);
    }

    public void loadManagerHumanView(int page) {
        lastManagerHumanController.loadDataPageEmployees(page);
    }

    public void showManagerHumanView() {
        lastManagerHumanController.showThisHumanManagerView();
        disposeThisEmployeeView();
    }

    public void showThisEmployeeView() {
        EmployeeViewFrame.setVisible(true);
    }

    private void disposeThisEmployeeView() {
        EmployeeViewFrame.dispose();
    }
}
