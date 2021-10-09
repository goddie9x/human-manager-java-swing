package View;

import Util.Employee;
import Controller.EmployeeViewController;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class EmployeeView extends javax.swing.JFrame {

    private Employee currentEmployee;
    private EmployeeViewController currentEmployeeViewController;
    private final Border border = BorderFactory.createLineBorder(Color.RED, 3);

    public EmployeeView(EmployeeViewController currentEmployeeViewController) {
        this.currentEmployeeViewController = currentEmployeeViewController;
        createEmployeeMode();
        hideRedAlertLabel();
    }

    public EmployeeView(
            EmployeeViewController currentEmployeeViewController,
            Employee currentEmployee) {
        this.currentEmployeeViewController = currentEmployeeViewController;
        this.currentEmployee = currentEmployee;
        viewEmployeeMode();
        hideRedAlertLabel();
    }

    private void setDataAllTextField(Employee employee) {
        String staffCode
                = employee.getAttibuteByStringExceptsalaryLevel("staffCode");
        String sex
                = employee.getAttibuteByStringExceptsalaryLevel("sex");
        String fullname
                = employee.getAttibuteByStringExceptsalaryLevel("fullname");
        String dateOfBirth
                = employee.getAttibuteByStringExceptsalaryLevel("dateOfBirth");
        String phoneNumber
                = employee.getAttibuteByStringExceptsalaryLevel("phoneNumber");
        String departmentCode
                = employee.getAttibuteByStringExceptsalaryLevel("departmentCode");
        String nation
                = employee.getAttibuteByStringExceptsalaryLevel("nation");
        String positionCode
                = employee.getAttibuteByStringExceptsalaryLevel("positionCode");
        String educationalBackgroundCode
                = employee.getAttibuteByStringExceptsalaryLevel("educationalBackgroundCode");
        String country
                = employee.getAttibuteByStringExceptsalaryLevel("country");
        int salaryLevel = employee.getsalaryLevel();

        switch (sex) {
            case "": {
                maleRadio.setSelected(false);
                femaleRadio.setSelected(false);
                otherSexRadio.setSelected(false);
                break;
            }
            case "Nam": {
                maleRadio.setSelected(true);
                break;
            }
            case "Nữ": {
                femaleRadio.setSelected(true);
                break;
            }
            default: {
                otherSexRadio.setSelected(true);
                break;
            }
        }

        departmentCodeBox.setSelectedItem(departmentCode);
        positionCodeBox.setSelectedItem(positionCode);
        salaryLevelBox.setSelectedItem(salaryLevel);
        educationalBackgroundCodeBox.setSelectedItem(educationalBackgroundCode);

        staffCodeField.setText(staffCode);
        fullnameField.setText(fullname);
        dateOfBirthField.setText(dateOfBirth);
        phoneNumberField.setText(phoneNumber);
        nationField.setText(nation);
        countryField.setText(country);
    }

    private Employee getDataAllTextField() {
        int sexSelected = sexRadioGroup.getButtonCount();
        String sex;

        switch (sexSelected) {
            case 1: {
                sex = "Nam";
                break;
            }
            case 2: {
                sex = "Nữ";
                break;
            }
            default: {
                sex = "Khác";
                break;
            }
        }

        System.err.println(salaryLevelBox.getSelectedIndex() + 1);

        Employee newEmployee = new Employee(
                staffCodeField.getText(),
                fullnameField.getText(),
                departmentCodeBox.getSelectedItem().toString(),
                dateOfBirthField.getText(),
                countryField.getText(),
                sex,
                nationField.getText(),
                phoneNumberField.getText(),
                educationalBackgroundCodeBox.getSelectedItem().toString(),
                positionCodeBox.getSelectedItem().toString(),
                salaryLevelBox.getSelectedIndex() + 1
        );

        return newEmployee;
    }

    private void disableEditableAllTextField() {
        staffCodeField.setEditable(false);
        fullnameField.setEditable(false);
        dateOfBirthField.setEditable(false);
        phoneNumberField.setEditable(false);
        countryField.setEditable(false);
        nationField.setEditable(false);
        departmentCodeBox.setEnabled(false);
        positionCodeBox.setEnabled(false);
        salaryLevelBox.setEnabled(false);
        educationalBackgroundCodeBox.setEnabled(false);
        maleRadio.setEnabled(false);
        femaleRadio.setEnabled(false);
        otherSexRadio.setEnabled(false);
    }

    private void enableEditableAllTextField() {
        staffCodeField.setEditable(true);
        fullnameField.setEditable(true);
        dateOfBirthField.setEditable(true);
        phoneNumberField.setEditable(true);
        countryField.setEditable(true);
        nationField.setEditable(true);
        departmentCodeBox.setEnabled(true);
        positionCodeBox.setEnabled(true);
        salaryLevelBox.setEnabled(true);
        educationalBackgroundCodeBox.setEnabled(true);
        maleRadio.setEnabled(true);
        femaleRadio.setEnabled(true);
        otherSexRadio.setEnabled(true);
    }

    private void createEmployeeMode() {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        deteteButton.setVisible(false);
        editButton.setVisible(false);
        clearAllTexField();
    }

    private void editEmployeeMode() {
        confirmButton.setVisible(true);
        deteteButton.setVisible(true);
        clearButton.setVisible(true);
        editButton.setVisible(false);
        enableEditableAllTextField();
    }

    private void viewEmployeeMode() {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        confirmButton.setVisible(false);
        deteteButton.setVisible(false);
        clearButton.setVisible(false);
        setDataAllTextField(currentEmployee);
        disableEditableAllTextField();
    }

    private void clearAllTexField() {
        setDataAllTextField(new Employee());
    }

    private boolean addAnEmployee(Employee employee) {
        return currentEmployeeViewController.addAnEmployee(employee);
    }

    private boolean updateCurrentEmoloyee() {
        currentEmployee = getDataAllTextField();

        return currentEmployeeViewController.saveCurrentEmployee(currentEmployee);
    }

    private boolean deleteCurrentEmployee() {
        return currentEmployeeViewController.deleteEmployeeByStaffCode(
                currentEmployee.getAttibuteByStringExceptsalaryLevel("staffCode"));
    }

    private boolean validateEmployeeForm() {
        boolean isValidated = true;

        String staffCode = staffCodeField.getText();
        String fullname = fullnameField.getText();
        String dateOfBirth = dateOfBirthField.getText();
        String phoneNumber = phoneNumberField.getText();
        String nation = nationField.getText();
        String country = countryField.getText();
        String departmentCode = departmentCodeBox.getSelectedItem().toString();
        String positionCode = positionCodeBox.getSelectedItem().toString();
        String educationalBackgroundCode
                = educationalBackgroundCodeBox.getSelectedItem().toString();
        int salaryLevel = salaryLevelBox.getSelectedIndex();
        int sex = sexRadioGroup.getButtonCount();

        if (staffCode.isEmpty()) {
            staffCodeField.setBorder(border);
            isValidated = false;
        }
        if (fullname.isEmpty()) {
            fullnameField.setBorder(border);
            isValidated = false;
        }
        if (dateOfBirth.isEmpty()) {
            dateOfBirthField.setBorder(border);
            isValidated = false;
        }
        if (phoneNumber.isEmpty()) {
            phoneNumberField.setBorder(border);
            isValidated = false;
        }
        if (nation.isEmpty()) {
            nationField.setBorder(border);
            isValidated = false;
        }
        if (country.isEmpty()) {
            countryField.setBorder(border);
            isValidated = false;
        }
        if (departmentCode.isEmpty()) {
            departmentCodeBox.setBorder(border);
            isValidated = false;
        }
        if (positionCode.isEmpty()) {
            positionCodeBox.setBorder(border);
            isValidated = false;
        }
        if (educationalBackgroundCode.isEmpty()) {
            educationalBackgroundCodeBox.setBorder(border);
            isValidated = false;
        }
        if (salaryLevel < 0) {
            salaryLevelBox.setBorder(border);
            isValidated = false;
        }
        if (sex < 1) {
            sexLabel.setForeground(Color.RED);
            isValidated = false;
        }
        return isValidated;
    }

    private void handleConfirmOnclick() {
        if (currentEmployee == null) {
            Employee employee = getDataAllTextField();
            if (addAnEmployee(employee)) {
                JOptionPane.showMessageDialog(this,
                        "Add data success", "Successfull", INFORMATION_MESSAGE);
            } else {
                alertWhenGotErrorFromSever();
            }
        } else {
            if (updateCurrentEmoloyee()) {
                JOptionPane.showMessageDialog(this,
                        "Save data success", "Successfull", INFORMATION_MESSAGE);
            } else {
                alertWhenGotErrorFromSever();
            }
        }
    }

    private void showRedAlertLabel(String every_box_need_to_be_fill) {
        alertLabel.setVisible(true);
    }

    private void hideRedAlertLabel() {
        alertLabel.setVisible(false);
    }

    private void hideRedAlertBorders() {
        staffCodeField.setBorder(null);
        fullnameField.setBorder(null);
        dateOfBirthField.setBorder(null);
        phoneNumberField.setBorder(null);
        nationField.setBorder(null);
        countryField.setBorder(null);
        departmentCodeBox.setBorder(null);
        positionCodeBox.setBorder(null);
        educationalBackgroundCodeBox.setBorder(null);
        salaryLevelBox.setBorder(null);
        sexLabel.setBorder(null);
    }

    private boolean saveChangeCurrentEmployee() {

        return currentEmployeeViewController.saveCurrentEmployee(currentEmployee);
    }

    private void alertWhenGotErrorFromSever() {
        JOptionPane.showMessageDialog(
                this, "Some error have been occured, please check your data", "faltal error", ERROR_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sexRadioGroup = new javax.swing.ButtonGroup();
        staffCodeLabel = new javax.swing.JLabel();
        staffCodeField = new javax.swing.JTextField();
        stafInforLabel = new javax.swing.JLabel();
        departmentCodeLabel = new javax.swing.JLabel();
        positionCodeLabel = new javax.swing.JLabel();
        fullname = new javax.swing.JLabel();
        fullnameField = new javax.swing.JTextField();
        dateOfBirthLabel = new javax.swing.JLabel();
        dateOfBirthField = new javax.swing.JTextField();
        countryLabel = new javax.swing.JLabel();
        countryField = new javax.swing.JTextField();
        nationLabel = new javax.swing.JLabel();
        nationField = new javax.swing.JTextField();
        salaryLevelLabel = new javax.swing.JLabel();
        educationalBackgroundCodeLabel = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        phoneNumberLabel = new javax.swing.JLabel();
        phoneNumberField = new javax.swing.JTextField();
        sexLabel = new javax.swing.JLabel();
        maleRadio = new javax.swing.JRadioButton();
        femaleRadio = new javax.swing.JRadioButton();
        otherSexRadio = new javax.swing.JRadioButton();
        deteteButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        salaryLevelBox = new javax.swing.JComboBox<>();
        educationalBackgroundCodeBox = new javax.swing.JComboBox<>();
        positionCodeBox = new javax.swing.JComboBox<>();
        departmentCodeBox = new javax.swing.JComboBox<>();
        alertLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Staff info");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        staffCodeLabel.setLabelFor(staffCodeField);
        staffCodeLabel.setText("Staff code");

        staffCodeField.setToolTipText("Staff code");

        stafInforLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        stafInforLabel.setText("Staff info");

        departmentCodeLabel.setLabelFor(staffCodeField);
        departmentCodeLabel.setText("Department code");

        positionCodeLabel.setLabelFor(staffCodeField);
        positionCodeLabel.setText("Position code");

        fullname.setLabelFor(staffCodeField);
        fullname.setText("Full name");

        fullnameField.setToolTipText("Full name");

        dateOfBirthLabel.setLabelFor(staffCodeField);
        dateOfBirthLabel.setText("Date of birth");

        dateOfBirthField.setToolTipText("Date of birth");

        countryLabel.setLabelFor(staffCodeField);
        countryLabel.setText("Country");

        countryField.setToolTipText("Country");

        nationLabel.setLabelFor(staffCodeField);
        nationLabel.setText("Nation");

        nationField.setToolTipText("Nation");

        salaryLevelLabel.setLabelFor(staffCodeField);
        salaryLevelLabel.setText("Salary level:");

        educationalBackgroundCodeLabel.setLabelFor(staffCodeField);
        educationalBackgroundCodeLabel.setText("Educational background code");

        confirmButton.setText("Confirm");
        confirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmButtonMouseClicked(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelButtonMouseClicked(evt);
            }
        });

        phoneNumberLabel.setLabelFor(staffCodeField);
        phoneNumberLabel.setText("PhoneNumber");

        phoneNumberField.setToolTipText("PhoneNumber");

        sexLabel.setLabelFor(staffCodeField);
        sexLabel.setText("Sex");
        sexLabel.setToolTipText("Select gender");
        sexLabel.setAutoscrolls(true);

        sexRadioGroup.add(maleRadio);
        maleRadio.setText("Male");

        sexRadioGroup.add(femaleRadio);
        femaleRadio.setText("Female");

        sexRadioGroup.add(otherSexRadio);
        otherSexRadio.setText("Other");

        deteteButton.setText("Delete");
        deteteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deteteButtonMouseClicked(evt);
            }
        });

        editButton.setText("Edit");
        editButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editButtonMouseClicked(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearButtonMouseClicked(evt);
            }
        });

        salaryLevelBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "level 1", "level 2", "level 3" }));
        salaryLevelBox.setToolTipText("Salary level:");

        educationalBackgroundCodeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CD", "DH" }));
        educationalBackgroundCodeBox.setToolTipText("Educational background code");

        positionCodeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TP", "PP", "NV" }));
        positionCodeBox.setToolTipText("Position code");

        departmentCodeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KD", "HC", "KT" }));
        departmentCodeBox.setToolTipText("Department code");

        alertLabel.setForeground(new java.awt.Color(255, 0, 0));
        alertLabel.setText("Every box has red alert need to be fill");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nationField)
                                    .addComponent(nationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(countryField)
                                    .addComponent(countryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateOfBirthField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateOfBirthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(phoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(phoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(staffCodeField, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addComponent(staffCodeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fullnameField)
                                    .addComponent(fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(alertLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stafInforLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(salaryLevelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                .addGap(77, 77, 77)
                                .addComponent(salaryLevelBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(educationalBackgroundCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(educationalBackgroundCodeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(departmentCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(departmentCodeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sexLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(maleRadio)
                                    .addComponent(femaleRadio)
                                    .addComponent(otherSexRadio))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(positionCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(positionCodeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(confirmButton)
                        .addGap(40, 40, 40)
                        .addComponent(editButton)
                        .addGap(49, 49, 49)
                        .addComponent(clearButton)
                        .addGap(45, 45, 45)
                        .addComponent(deteteButton)
                        .addGap(46, 46, 46)
                        .addComponent(cancelButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(stafInforLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(alertLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(staffCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(staffCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fullnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(countryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(countryField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(nationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nationField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(phoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(educationalBackgroundCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(educationalBackgroundCodeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(phoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(departmentCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(departmentCodeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dateOfBirthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateOfBirthField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sexLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maleRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(femaleRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(otherSexRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(salaryLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salaryLevelBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(positionCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(positionCodeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(81, 81, 81)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmButton)
                    .addComponent(cancelButton)
                    .addComponent(editButton)
                    .addComponent(clearButton)
                    .addComponent(deteteButton))
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseClicked
        currentEmployeeViewController.showManagerHumanView();
    }//GEN-LAST:event_cancelButtonMouseClicked

    private void clearButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearButtonMouseClicked
        clearAllTexField();
    }//GEN-LAST:event_clearButtonMouseClicked

    private void editButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMouseClicked
        editEmployeeMode();
    }//GEN-LAST:event_editButtonMouseClicked

    private void confirmButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseClicked
        if (validateEmployeeForm()) {
            handleConfirmOnclick();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Delete", "Successfull", ERROR_MESSAGE);
        }
    }//GEN-LAST:event_confirmButtonMouseClicked

    private void deteteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deteteButtonMouseClicked
        int userSelected = JOptionPane.showConfirmDialog(null,
                "Do you really want to delete this? That action cannot undo",
                "Yes",
                JOptionPane.YES_NO_OPTION);
        if (userSelected == 0) {
            boolean isDeleted = currentEmployeeViewController.deleteEmployeeByStaffCode(
                    currentEmployee.getAttibuteByStringExceptsalaryLevel("staffCode"));
            if (isDeleted) {
                JOptionPane.showMessageDialog(
                        this, "Delete success", "SUCCESS", INFORMATION_MESSAGE);

                currentEmployeeViewController.showManagerHumanView();
            } else {
                alertWhenGotErrorFromSever();
            }
        }
    }//GEN-LAST:event_deteteButtonMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       currentEmployeeViewController.showManagerHumanView();
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JTextField countryField;
    private javax.swing.JLabel countryLabel;
    private javax.swing.JTextField dateOfBirthField;
    private javax.swing.JLabel dateOfBirthLabel;
    private javax.swing.JComboBox<String> departmentCodeBox;
    private javax.swing.JLabel departmentCodeLabel;
    private javax.swing.JButton deteteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JComboBox<String> educationalBackgroundCodeBox;
    private javax.swing.JLabel educationalBackgroundCodeLabel;
    private javax.swing.JRadioButton femaleRadio;
    private javax.swing.JLabel fullname;
    private javax.swing.JTextField fullnameField;
    private javax.swing.JRadioButton maleRadio;
    private javax.swing.JTextField nationField;
    private javax.swing.JLabel nationLabel;
    private javax.swing.JRadioButton otherSexRadio;
    private javax.swing.JTextField phoneNumberField;
    private javax.swing.JLabel phoneNumberLabel;
    private javax.swing.JComboBox<String> positionCodeBox;
    private javax.swing.JLabel positionCodeLabel;
    private javax.swing.JComboBox<String> salaryLevelBox;
    private javax.swing.JLabel salaryLevelLabel;
    private javax.swing.JLabel sexLabel;
    private javax.swing.ButtonGroup sexRadioGroup;
    private javax.swing.JLabel stafInforLabel;
    private javax.swing.JTextField staffCodeField;
    private javax.swing.JLabel staffCodeLabel;
    // End of variables declaration//GEN-END:variables
}
