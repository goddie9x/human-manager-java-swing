package Model;

import Config.ConnectDbSql;
import Util.Employee;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeModel {

    private final Connection DB = ConnectDbSql.getInstance().getConnection();
    private final int PERPAGE = 10;

    public Employee getAnEmployeeByEmployeeCode(String EmployeeCode) {
        String queryThatEmployee = "select top 1 * from Employees "
                + "where EmployeeCode = '" + EmployeeCode + "'";

        try {
            Statement queryThatEmployeeStatement = DB.createStatement();
            ResultSet employeeResult
                    = queryThatEmployeeStatement.executeQuery(queryThatEmployee);
            employeeResult.next();

            Employee thatEmployee = new Employee(
                    employeeResult.getString("EmployeeCode"),
                    employeeResult.getString("fullname"),
                    employeeResult.getString("departmentCode"),
                    employeeResult.getString("dateOfBirth"),
                    employeeResult.getString("country"),
                    employeeResult.getString("sex"),
                    employeeResult.getString("nation"),
                    employeeResult.getString("phoneNumber"),
                    employeeResult.getString("educationalBackgroundCode"),
                    employeeResult.getString("positionCode"),
                    employeeResult.getInt("salaryLevel")
            );
            return thatEmployee;
        } catch (SQLException exception) {
        }

        return null;
    }

    public ArrayList<Employee> getEmployeesOnPage(int page) {
        ArrayList<Employee> Employees = getEmployeesWithConditonInPage(
                "1=1",
                page);

        return Employees;
    }

    public int countEmployees() {
        int quantityOfEmployees = 0;
        String countEmployeesQuery = "SELECT COUNT(*) FROM Employees";

        try {
            Statement statementQueryAllEmployees = DB.createStatement();
            ResultSet resultSet
                    = statementQueryAllEmployees.executeQuery(countEmployeesQuery);
            resultSet.next();

            quantityOfEmployees = resultSet.getInt(1);
            return quantityOfEmployees;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quantityOfEmployees;
    }

    public int countEmployeesWithKey(String key) {
        int quantityOfEmployees = 0;
        String countEmployeesQuery = "SELECT COUNT(*) FROM Employees"
                + " WHERE EmployeeCode = ? or fullname = ? or country = ?"
                + " or phoneNumber = ?";

        try {
            PreparedStatement statementQueryAllEmployees = DB.prepareStatement(
                    countEmployeesQuery);
            statementQueryAllEmployees.setString(1, key);
            statementQueryAllEmployees.setString(2, key);
            statementQueryAllEmployees.setString(3, key);
            statementQueryAllEmployees.setString(4, key);

            ResultSet resultSet
                    = statementQueryAllEmployees.executeQuery();
            resultSet.next();

            return resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quantityOfEmployees;
    }

    public ArrayList<Employee> getEmployeesWithConditonInPage(
            String condition,
            int page) {
        if (page < 1) {
            page = 1;
        }

        int postitionStartSelectEmployee = (page - 1) * PERPAGE;
        ArrayList<Employee> Employees = new ArrayList<>();

        String queyAllEmployees = "SELECT * FROM Employees "
                + "WHERE " + condition
                + " ORDER BY EmployeeCode "
                + "OFFSET " + postitionStartSelectEmployee + " ROWS "
                + "FETCH NEXT " + PERPAGE + " ROWS ONLY";

        try {
            Statement statementQueryAllEmployees = DB.createStatement();
            ResultSet resultSet
                    = statementQueryAllEmployees.executeQuery(queyAllEmployees);

            while (resultSet.next()) {
                Employee currentEmployee = new Employee(
                        resultSet.getString("EmployeeCode"),
                        resultSet.getString("fullname"),
                        resultSet.getString("DepartmentCode"),
                        resultSet.getString("dateOfBirth"),
                        resultSet.getString("country"),
                        resultSet.getString("sex"),
                        resultSet.getString("nation"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("educationalBackgroundCode"),
                        resultSet.getString("PositionCode"),
                        resultSet.getInt("salaryLevel")
                );
                Employees.add(currentEmployee);
            }
            return Employees;
        } catch (SQLException exception) {
        }

        return Employees;
    }

    public ArrayList<Employee> getEmployeesWithKey(String key,
            int page) {
        if (page < 1) {
            page = 1;
        }

        int postitionStartSelectEmployee = (page - 1) * PERPAGE;
        ArrayList<Employee> Employees = new ArrayList<>();

        String queyAllEmployeesWithKey = "SELECT * FROM Employees "
                + "WHERE EmployeeCode = ? or fullname = ? or country = ?"
                + " or phoneNumber = ?"
                + " ORDER BY EmployeeCode "
                + "OFFSET " + postitionStartSelectEmployee + " ROWS "
                + "FETCH NEXT " + PERPAGE + " ROWS ONLY";

        try {
            PreparedStatement queryThatEmployeeStatement
                    = DB.prepareStatement(queyAllEmployeesWithKey);

            queryThatEmployeeStatement.setString(1, key);
            queryThatEmployeeStatement.setString(2, key);
            queryThatEmployeeStatement.setString(3, key);
            queryThatEmployeeStatement.setString(4, key);

            ResultSet resultSet
                    = queryThatEmployeeStatement.executeQuery();

            while (resultSet.next()) {
                Employee currentEmployee = new Employee(
                        resultSet.getString("EmployeeCode"),
                        resultSet.getString("fullname"),
                        resultSet.getString("DepartmentCode"),
                        resultSet.getString("dateOfBirth"),
                        resultSet.getString("country"),
                        resultSet.getString("sex"),
                        resultSet.getString("nation"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("educationalBackgroundCode"),
                        resultSet.getString("PositionCode"),
                        resultSet.getInt("salaryLevel")
                );

                Employees.add(currentEmployee);
            }
            return Employees;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return Employees;
    }

    public boolean updateAnEmployee(
            Employee newEmployeeResult) {
        String EmployeeCode = newEmployeeResult.getAttibuteByStringExceptsalaryLevel("staffCode");
        boolean result = false;

        String updateEmeployeeStatement = "UPDATE Employees "
                + "Set "
                + "fullname = N'"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("fullname")
                + "'," + "DepartmentCode = '"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("departmentCode")
                + "'," + "dateOfBirth = '"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("dateOfBirth")
                + "'," + "country = N'"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("country")
                + "'," + "sex = N'"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("sex")
                + "'," + "nation = '"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("nation")
                + "'," + "phoneNumber = N'"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("phoneNumber")
                + "'," + "educationalBackgroundCode = '"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("educationalBackgroundCode")
                + "'," + "positionCode = '"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("positionCode")
                + "'," + "salaryLevel = '"
                + newEmployeeResult.getsalaryLevel()
                + "' WHERE EmployeeCode = " + "'" + EmployeeCode + "'";
        try {
            Statement deleteEmployee = DB.createStatement();
            deleteEmployee.executeUpdate(updateEmeployeeStatement);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean addAnEmployee(Employee newEmployeeResult) {
        boolean result = false;
        String insertEmeployeeStatement = "INSERT INTO Employees "
                + "VALUES("
                + "'" + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("staffCode") + "','"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("fullname") + "','"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("dateOfBirth") + "','"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("country") + "','"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("sex") + "','"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("nation") + "','"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("phoneNumber") + "','"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("departmentCode") + "','"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("positionCode") + "','"
                + newEmployeeResult.getAttibuteByStringExceptsalaryLevel("educationalBackgroundCode") + "',"
                + newEmployeeResult.getsalaryLevel()
                + ");";
        try {
            Statement addEmployee = DB.createStatement();
            addEmployee.executeUpdate(insertEmeployeeStatement);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int[] deleteMultipleEmployeesByEmployeeCode(ArrayList<String> EmployeeCodes) {
        int EmployeeCodesLength = EmployeeCodes.size();
        //some time we need to checkout what employee has been deleted or not
        int[] statusDeleteMultipleEmployee = new int[EmployeeCodesLength];

        for (int i = 0; i < EmployeeCodesLength; i++) {
            String currentEmployeeCode = EmployeeCodes.get(i);

            if (deleteAnEmployeeByEmployeeCode(currentEmployeeCode)) {
                statusDeleteMultipleEmployee[i] = 1;
            } else {
                statusDeleteMultipleEmployee[i] = 0;
            }
        }

        return statusDeleteMultipleEmployee;
    }

    public boolean deleteAnEmployeeByEmployeeCode(String EmployeeCode) {
        boolean result = false;
        String deleteEmployeeStatement = "DELETE FROM contractLabors WHERE "
                + "EmployeeCode = " + "'" + EmployeeCode + "';"
                + "DELETE FROM Employees WHERE "
                + "EmployeeCode = " + "'" + EmployeeCode + "';";

        try {
            Statement deleteEmployee = DB.createStatement();
            deleteEmployee.executeUpdate(deleteEmployeeStatement);
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

}
