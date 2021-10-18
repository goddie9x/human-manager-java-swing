package View;

import Util.Employee;
import Util.Message;
import Util.Conversion;
import Util.DefaultEvent;
import Util.Validatetion;
import Controller.EmployeeViewController;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class EmployeeView extends javax.swing.JFrame {

    private Employee currentEmployee;
    private EmployeeViewController currentEmployeeViewController;
    private final Border border = BorderFactory.createLineBorder(Color.RED, 3);

    public EmployeeView(EmployeeViewController currentEmployeeViewController) {
        this.currentEmployeeViewController = currentEmployeeViewController;
        createEmployeeMode();
        hideRedAlertLabel();
        setAutoHideWarningOnForcusField();
    }

    public EmployeeView(
            EmployeeViewController currentEmployeeViewController,
            Employee currentEmployee) {
        this.currentEmployeeViewController = currentEmployeeViewController;
        this.currentEmployee = currentEmployee;
        viewEmployeeMode();
        hideRedAlertLabel();
    }

    private void setAutoHideWarningOnForcusField() {
        DefaultEvent.forcusHideWarning(staffCodeField, staffCodeWarningLabel);
        DefaultEvent.forcusHideWarning(phoneNumberField, phoneNumberWarningLabel);
        DefaultEvent.forcusHideWarning(nationField, nationCodeWarningLabel);
        DefaultEvent.forcusHideWarning(fullnameField, fullnameCodeWarningLabel);
        DefaultEvent.forcusHideWarning(dateOfBirthField, dateOfBirthWarningLabel);
        DefaultEvent.forcusHideWarning(countryField, countryWarningLabel);
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

        Employee newEmployee = new Employee(
                staffCodeField.getText(),
                fullnameField.getText(),
                departmentCodeBox.getSelectedItem().toString(),
                Conversion.stringToDateForSql(dateOfBirthField.getText()),
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
        setAutoHideWarningOnForcusField();
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

        String staffCode = staffCodeField.getText().trim();
        String fullname = fullnameField.getText().trim();
        String dateOfBirth = dateOfBirthField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();
        String nation = nationField.getText().trim();
        String country = countryField.getText().trim();
        String departmentCode = departmentCodeBox.getSelectedItem().toString().trim();
        String positionCode = positionCodeBox.getSelectedItem().toString().trim();
        String educationalBackgroundCode
                = educationalBackgroundCodeBox.getSelectedItem().toString();
        int salaryLevel = salaryLevelBox.getSelectedIndex();
        int sex = sexRadioGroup.getButtonCount();

        if (!Validatetion.checkLengthInRange(staffCode, 5, 30)) {
            staffCodeField.setBorder(border);
            staffCodeWarningLabel.setVisible(true);
            isValidated = false;
        }
        if (!Validatetion.checkLengthInRange(fullname, 5, 100)) {
            fullnameField.setBorder(border);
            fullnameCodeWarningLabel.setVisible(true);
            isValidated = false;
        }
        if (dateOfBirth.isEmpty() || Conversion.stringToDateForSql(dateOfBirth).equals('1')) {
            dateOfBirthField.setBorder(border);
            dateOfBirthWarningLabel.setVisible(true);
            isValidated = false;
        }
        if (!Validatetion.isPhoneNumber(phoneNumber)) {
            phoneNumberField.setBorder(border);
            phoneNumberWarningLabel.setVisible(true);
            isValidated = false;
        }
        if (!Validatetion.checkLengthInRange(nation, 2, 30)) {
            nationField.setBorder(border);
            nationCodeWarningLabel.setVisible(true);
            isValidated = false;
        }
        if (!Validatetion.checkLengthInRange(country, 5, 30)) {
            countryField.setBorder(border);
            countryWarningLabel.setVisible(true);
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
                Message.showSuccess(this, "Add data success");
            } else {
                alertWhenGotErrorFromSever();
            }
        } else {
            if (updateCurrentEmoloyee()) {
                Message.showSuccess(this, "Save data success");
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
        staffCodeWarningLabel.setVisible(false);
        phoneNumberWarningLabel.setVisible(false);
        nationCodeWarningLabel.setVisible(false);
        fullnameCodeWarningLabel.setVisible(false);
        dateOfBirthWarningLabel.setVisible(false);
        countryWarningLabel.setVisible(false);
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
        Message.showError(this, "Some error have been occured, please check your data");
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
        dateOfBirthField = new javax.swing.JFormattedTextField();
        nationCodeWarningLabel = new javax.swing.JLabel();
        dateOfBirthWarningLabel = new javax.swing.JLabel();
        phoneNumberWarningLabel = new javax.swing.JLabel();
        countryWarningLabel = new javax.swing.JLabel();
        fullnameCodeWarningLabel = new javax.swing.JLabel();
        staffCodeWarningLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Staff info");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        staffCodeLabel.setLabelFor(staffCodeField);
        staffCodeLabel.setText("Staff code");

        staffCodeField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        staffCodeField.setToolTipText("Staff code");

        stafInforLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        stafInforLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medias/Icon/icons8-confirm-35.png"))); // NOI18N
        stafInforLabel.setText("Staff info");

        departmentCodeLabel.setLabelFor(departmentCodeBox);
        departmentCodeLabel.setText("Department code");

        positionCodeLabel.setLabelFor(positionCodeBox);
        positionCodeLabel.setText("Position code");

        fullname.setLabelFor(fullnameField);
        fullname.setText("Full name");

        fullnameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fullnameField.setToolTipText("Full name");

        dateOfBirthLabel.setLabelFor(dateOfBirthField);
        dateOfBirthLabel.setText("Date of birth");

        countryLabel.setLabelFor(countryField);
        countryLabel.setText("Country");

        countryField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        countryField.setToolTipText("Country");

        nationLabel.setLabelFor(nationField);
        nationLabel.setText("Nation");

        nationField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nationField.setToolTipText("Nation");

        salaryLevelLabel.setLabelFor(salaryLevelBox);
        salaryLevelLabel.setText("Salary level:");

        educationalBackgroundCodeLabel.setLabelFor(educationalBackgroundCodeBox);
        educationalBackgroundCodeLabel.setText("Educational background code");

        confirmButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medias/Icon/icons8-verified-account-30.png"))); // NOI18N
        confirmButton.setText("Confirm");
        confirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmButtonMouseClicked(evt);
            }
        });

        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Icon/icons8-delete-30.png"))); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelButtonMouseClicked(evt);
            }
        });

        phoneNumberLabel.setLabelFor(phoneNumberField);
        phoneNumberLabel.setText("PhoneNumber");

        phoneNumberField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        phoneNumberField.setToolTipText("PhoneNumber");

        sexLabel.setLabelFor(maleRadio);
        sexLabel.setText("Sex");
        sexLabel.setToolTipText("Select gender");
        sexLabel.setAutoscrolls(true);

        sexRadioGroup.add(maleRadio);
        maleRadio.setText("Male");

        sexRadioGroup.add(femaleRadio);
        femaleRadio.setText("Female");

        sexRadioGroup.add(otherSexRadio);
        otherSexRadio.setText("Other");

        deteteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Icon/icons8-bin-30.png"))); // NOI18N
        deteteButton.setText("Delete");
        deteteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deteteButtonMouseClicked(evt);
            }
        });

        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medias/Icon/icons8-edit-30.png"))); // NOI18N
        editButton.setText("Edit");
        editButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editButtonMouseClicked(evt);
            }
        });

        clearButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Icon/icons8-eraser-30.png"))); // NOI18N
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

        dateOfBirthField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        dateOfBirthField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dateOfBirthField.setToolTipText("Enter your date of birth");

        nationCodeWarningLabel.setForeground(new java.awt.Color(255, 0, 0));
        nationCodeWarningLabel.setLabelFor(nationField);
        nationCodeWarningLabel.setText("Please enter at least 2 characters");

        dateOfBirthWarningLabel.setForeground(new java.awt.Color(255, 0, 0));
        dateOfBirthWarningLabel.setLabelFor(dateOfBirthField);
        dateOfBirthWarningLabel.setText("Please enter date format");

        phoneNumberWarningLabel.setForeground(new java.awt.Color(255, 0, 0));
        phoneNumberWarningLabel.setLabelFor(phoneNumberField);
        phoneNumberWarningLabel.setText("Please enter phone number");

        countryWarningLabel.setForeground(new java.awt.Color(255, 0, 0));
        countryWarningLabel.setLabelFor(countryField);
        countryWarningLabel.setText("Please enter at least 5 characters");

        fullnameCodeWarningLabel.setForeground(new java.awt.Color(255, 0, 0));
        fullnameCodeWarningLabel.setLabelFor(fullnameField);
        fullnameCodeWarningLabel.setText("Please enter at least 5 characters");

        staffCodeWarningLabel.setForeground(new java.awt.Color(255, 0, 0));
        staffCodeWarningLabel.setLabelFor(staffCodeField);
        staffCodeWarningLabel.setText("Please enter at least 5 characters");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(staffCodeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(nationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(41, 41, 41))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(staffCodeWarningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dateOfBirthField, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(nationField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(38, 38, 38)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(countryField, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addComponent(countryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addComponent(fullname, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addComponent(fullnameCodeWarningLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addComponent(fullnameField)
                                    .addComponent(phoneNumberField)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dateOfBirthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(phoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(staffCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(nationCodeWarningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(dateOfBirthWarningLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(38, 38, 38)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(countryWarningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(phoneNumberWarningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(salaryLevelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(77, 77, 77)
                                .addComponent(salaryLevelBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(positionCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(positionCodeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(educationalBackgroundCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                .addComponent(educationalBackgroundCodeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sexLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(maleRadio)
                                    .addComponent(femaleRadio)
                                    .addComponent(otherSexRadio))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(departmentCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(departmentCodeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addComponent(stafInforLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(confirmButton)
                                .addGap(18, 18, 18)
                                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(deteteButton)
                                .addGap(18, 18, 18)
                                .addComponent(cancelButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(alertLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(stafInforLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(alertLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sexLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maleRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(femaleRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(otherSexRadio)
                            .addComponent(fullnameCodeWarningLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(salaryLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salaryLevelBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(positionCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(positionCodeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(educationalBackgroundCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(educationalBackgroundCodeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(departmentCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(departmentCodeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(fullnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(staffCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(staffCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(staffCodeWarningLabel)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(countryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(countryField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(nationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nationField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(2, 2, 2)
                                .addComponent(nationCodeWarningLabel))
                            .addComponent(countryWarningLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateOfBirthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateOfBirthField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateOfBirthWarningLabel)
                            .addComponent(phoneNumberWarningLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmButton)
                    .addComponent(cancelButton)
                    .addComponent(editButton)
                    .addComponent(clearButton)
                    .addComponent(deteteButton))
                .addGap(21, 21, 21))
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
            Message.showError(this, "All field need to be fill");
        }
    }//GEN-LAST:event_confirmButtonMouseClicked

    private void deteteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deteteButtonMouseClicked
        boolean userSelected = Message.showYesNoQuestion(null,
                "Do you really want to delete this? That action cannot undo");
        if (userSelected) {
            boolean isDeleted = currentEmployeeViewController.deleteEmployeeByStaffCode(
                    currentEmployee.getAttibuteByStringExceptsalaryLevel("staffCode"));
            if (isDeleted) {
                Message.showSuccess(this, "Delete success");

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
    private javax.swing.JLabel countryWarningLabel;
    private javax.swing.JFormattedTextField dateOfBirthField;
    private javax.swing.JLabel dateOfBirthLabel;
    private javax.swing.JLabel dateOfBirthWarningLabel;
    private javax.swing.JComboBox<String> departmentCodeBox;
    private javax.swing.JLabel departmentCodeLabel;
    private javax.swing.JButton deteteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JComboBox<String> educationalBackgroundCodeBox;
    private javax.swing.JLabel educationalBackgroundCodeLabel;
    private javax.swing.JRadioButton femaleRadio;
    private javax.swing.JLabel fullname;
    private javax.swing.JLabel fullnameCodeWarningLabel;
    private javax.swing.JTextField fullnameField;
    private javax.swing.JRadioButton maleRadio;
    private javax.swing.JLabel nationCodeWarningLabel;
    private javax.swing.JTextField nationField;
    private javax.swing.JLabel nationLabel;
    private javax.swing.JRadioButton otherSexRadio;
    private javax.swing.JTextField phoneNumberField;
    private javax.swing.JLabel phoneNumberLabel;
    private javax.swing.JLabel phoneNumberWarningLabel;
    private javax.swing.JComboBox<String> positionCodeBox;
    private javax.swing.JLabel positionCodeLabel;
    private javax.swing.JComboBox<String> salaryLevelBox;
    private javax.swing.JLabel salaryLevelLabel;
    private javax.swing.JLabel sexLabel;
    private javax.swing.ButtonGroup sexRadioGroup;
    private javax.swing.JLabel stafInforLabel;
    private javax.swing.JTextField staffCodeField;
    private javax.swing.JLabel staffCodeLabel;
    private javax.swing.JLabel staffCodeWarningLabel;
    // End of variables declaration//GEN-END:variables
}
