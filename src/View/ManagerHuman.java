package View;

import Util.Message;
import Util.Employee;
import Controller.ManagerHumanController;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class ManagerHuman extends javax.swing.JFrame {

    private int currentAccountRole;
    private int currentPage = 1;
    private ManagerHumanController currentManagerHumanController;
    private DefaultTableModel table;
    private final int PERPAGE = 10;
    private ArrayList<Employee> employees;
    private boolean isAllSelected = false;
    private DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    private int quantityOfEmployees;
    private String searchText = "";

    public ManagerHuman(
            int currentAccountRole,
            ManagerHumanController currentManagerHumanController) {
        this.currentAccountRole = currentAccountRole;
        this.currentManagerHumanController = currentManagerHumanController;
        initComponents();
        this.setLocationRelativeTo(null);
        table = (DefaultTableModel) HumanTable.getModel();
        this.employees = currentManagerHumanController
                .loadDataPageEmployees(currentPage);
        quantityOfEmployees
                = currentManagerHumanController.quantityOfEmployees("");
        renderTable();

        if (currentAccountRole == 1) {
            userManu.setText("Admin");
        } else {
            userManu.setText("Nomal user");
            managerAllUserMenuItem.setVisible(false);
        }
    }

    public void reloadTable() {
        quantityOfEmployees
                = currentManagerHumanController.quantityOfEmployees(searchText);

        this.employees = currentManagerHumanController.searchEmployeesWithKey(
                searchText, currentPage);
        renderTable();
    }

    public void renderTable() {
        int quantityPage = quantityPageCalculator(quantityOfEmployees);
        int employeesLength = employees.size();
        
        if (currentPage > quantityPage) {
            currentPage = quantityPage;
        }
        
        if (quantityPage < 1) {
            disableAllPagination();
        } else {
            selectPageField.setVisible(true);
        }

        table.setRowCount(0);

        for (int i = 0; i < employeesLength; i++) {
            Object[] infoOfEmployee = getRowTableByEmployeeInfo(
                    employees.get(i),
                    i
            );

            table.addRow(
                    infoOfEmployee
            );
        }
        goToPageButton.setVisible(true);

        if (currentPage == 1 || quantityPage < 1) {
            backPageButton.setVisible(false);
            firstPageButton.setVisible(false);
        } else {
            backPageButton.setVisible(true);
            firstPageButton.setVisible(true);
        }
        if (currentPage == quantityPage || quantityPage < 1) {
            nextPageButton.setVisible(false);
            endPageButton.setVisible(false);
        } else {
            nextPageButton.setVisible(true);
            endPageButton.setVisible(true);
        }
        if (quantityPage == 1 || quantityPage < 1) {
            selectPageField.setVisible(false);
            goToPageButton.setVisible(false);
        }
        selectPageField.setText(String.valueOf(currentPage));
        setTableCellAlignment(JLabel.CENTER);
    }

    private void setTableCellAlignment(int alignment) {
        renderer.setHorizontalAlignment(alignment);
        for (int i = 1; i < table.getColumnCount(); i++) {
            HumanTable.setDefaultRenderer(table.getColumnClass(i), renderer);
        }
        HumanTable.updateUI();
    }

    private int quantityPageCalculator(int quantityOfEmployees) {
        return (quantityOfEmployees % PERPAGE == 0)
                ? (quantityOfEmployees / PERPAGE)
                : (quantityOfEmployees / PERPAGE + 1);
    }

    private Object[] getRowTableByEmployeeInfo(
            Employee employee,
            int index) {

        Object[] infoOfEmployee = new Object[]{
            false,
            (currentPage - 1) * PERPAGE + index + 1, //we need current index user in our list
            employee.getAttibuteByStringExceptsalaryLevel("staffCode"),
            employee.getAttibuteByString("fullname"),
            employee.getAttibuteByStringExceptsalaryLevel("positionCode"),
            employee.getAttibuteByString("country"),
            employee.getAttibuteByString("phoneNumber"),
            employee.getAttibuteByString("sex"),
            employee.getsalaryLevel(),};

        return infoOfEmployee;
    }

    private void disableAllPagination() {
        selectPageField.setVisible(false);
    }

    public void selectAllRow() {
        int quantityRows = HumanTable.getRowCount();
        for (int i = 0; i < quantityRows; i++) {
            table.setValueAt(true, i, 0);
        }
        isAllSelected = true;
        buttonSelectAll.setText("Un select all");
    }

    public void unSelectAllRow() {
        int quantityRows = HumanTable.getRowCount();
        for (int i = 0; i < quantityRows; i++) {
            table.setValueAt(false, i, 0);
        }
        isAllSelected = false;
        buttonSelectAll.setText("Select all");
    }

    public ArrayList<String> getStaffCodeInRowChecked() {
        ArrayList<String> listStaffCodes = new ArrayList<String>();

        int quantityRows = HumanTable.getRowCount();
        for (int i = 0; i < quantityRows; i++) {
            boolean isCurrentRowChecked = table.getValueAt(i, 0).equals(true);

            if (isCurrentRowChecked) {
                String staffCode = table.getValueAt(i, 2).toString().trim();

                listStaffCodes.add(staffCode);
            }
        }

        return listStaffCodes;
    }

    private void deleteMultipleEmployeesByStaffCode(ArrayList<String> EmployeeCodes) {
        currentManagerHumanController.deleteMultipleEmployeesByCode(EmployeeCodes);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        HumanTable = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        multipleDeleteButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        backPageButton = new javax.swing.JButton();
        nextPageButton = new javax.swing.JButton();
        firstPageButton = new javax.swing.JButton();
        endPageButton = new javax.swing.JButton();
        goToPageButton = new javax.swing.JButton();
        selectPageField = new javax.swing.JTextField();
        buttonSelectAll = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        userManu = new javax.swing.JMenu();
        managerCurrentUserMenuItem = new javax.swing.JMenuItem();
        managerAllUserMenuItem = new javax.swing.JMenuItem();
        logoutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Manager human");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        HumanTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Select", "Index", "Employee code", "Name", "Position", "Country", "Phone number", "Sex", "Salary level"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        HumanTable.setToolTipText("Double click to row that you want to view more detail");
        HumanTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        HumanTable.setRowHeight(22);
        HumanTable.setShowGrid(true);
        HumanTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HumanTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(HumanTable);
        if (HumanTable.getColumnModel().getColumnCount() > 0) {
            HumanTable.getColumnModel().getColumn(0).setMinWidth(30);
            HumanTable.getColumnModel().getColumn(1).setPreferredWidth(30);
            HumanTable.getColumnModel().getColumn(2).setMinWidth(70);
            HumanTable.getColumnModel().getColumn(3).setMinWidth(150);
            HumanTable.getColumnModel().getColumn(4).setMinWidth(40);
            HumanTable.getColumnModel().getColumn(5).setMinWidth(150);
            HumanTable.getColumnModel().getColumn(6).setMinWidth(100);
            HumanTable.getColumnModel().getColumn(7).setMinWidth(50);
            HumanTable.getColumnModel().getColumn(8).setMinWidth(50);
        }

        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medias/Icon/icons8-add-30.png"))); // NOI18N
        addButton.setText("Add");
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addButtonMouseClicked(evt);
            }
        });

        multipleDeleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medias/Icon/icons8-bin-30.png"))); // NOI18N
        multipleDeleteButton.setText("Multiple delete");
        multipleDeleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                multipleDeleteButtonMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel1.setText("Employees table");
        jLabel1.setToolTipText("");

        searchField.setText("Search for employee");
        searchField.setToolTipText("Find Employee by Employee Code, Name, etc ");
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFieldFocusGained(evt);
            }
        });

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Icon/icons8-search-client-30.png"))); // NOI18N
        searchButton.setText("Search");
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchButtonMouseClicked(evt);
            }
        });

        backPageButton.setText("<");
        backPageButton.setToolTipText(" Move to previous page");
        backPageButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backPageButtonMouseClicked(evt);
            }
        });

        nextPageButton.setText(">");
        nextPageButton.setToolTipText("Move to next page");
        nextPageButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextPageButtonMouseClicked(evt);
            }
        });

        firstPageButton.setText("First");
        firstPageButton.setToolTipText("Move to first page");
        firstPageButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                firstPageButtonMouseClicked(evt);
            }
        });

        endPageButton.setText("End");
        endPageButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                endPageButtonMouseClicked(evt);
            }
        });

        goToPageButton.setText("Go");
        goToPageButton.setToolTipText("Click to move that page");
        goToPageButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goToPageButtonMouseClicked(evt);
            }
        });

        selectPageField.setText("Enter page");
        selectPageField.setInheritsPopupMenu(true);
        selectPageField.setMargin(new java.awt.Insets(10, 10, 10, 10));
        selectPageField.setMinimumSize(new java.awt.Dimension(100, 20));
        selectPageField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectPageFieldFocusGained(evt);
            }
        });

        buttonSelectAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medias/Icon/icons8-checked-30.png"))); // NOI18N
        buttonSelectAll.setText("Select all");
        buttonSelectAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSelectAllMouseClicked(evt);
            }
        });

        userManu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        userManu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medias/Icon/icons8-confirm-30.png"))); // NOI18N
        userManu.setText("User");
        userManu.setToolTipText("Manager your account");

        managerCurrentUserMenuItem.setText("Your account");
        managerCurrentUserMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managerCurrentUserMenuItemActionPerformed(evt);
            }
        });
        userManu.add(managerCurrentUserMenuItem);

        managerAllUserMenuItem.setText("Users manager");
        managerAllUserMenuItem.setToolTipText("Manager all accont");
        managerAllUserMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managerAllUserMenuItemActionPerformed(evt);
            }
        });
        userManu.add(managerAllUserMenuItem);

        logoutMenuItem.setText("Logout");
        logoutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutMenuItemActionPerformed(evt);
            }
        });
        userManu.add(logoutMenuItem);

        jMenuBar1.add(userManu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(firstPageButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(backPageButton)
                        .addGap(19, 19, 19)
                        .addComponent(selectPageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(goToPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nextPageButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(endPageButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(buttonSelectAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(multipleDeleteButton)
                        .addGap(136, 136, 136)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton)
                        .addGap(19, 19, 19)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(246, 246, 246))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(multipleDeleteButton)
                    .addComponent(addButton)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton)
                    .addComponent(buttonSelectAll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(endPageButton)
                    .addComponent(nextPageButton)
                    .addComponent(backPageButton)
                    .addComponent(firstPageButton)
                    .addComponent(goToPageButton)
                    .addComponent(selectPageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
        currentManagerHumanController.loadAddNewEmployeeView();

        currentManagerHumanController.hideThisHumanManagerView();
    }//GEN-LAST:event_addButtonMouseClicked

    private void managerAllUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managerAllUserMenuItemActionPerformed
        currentManagerHumanController.loadManagerUsersViews();
        currentManagerHumanController.hideThisHumanManagerView();
    }//GEN-LAST:event_managerAllUserMenuItemActionPerformed

    private void firstPageButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_firstPageButtonMouseClicked
        currentPage = 1;
        this.employees = currentManagerHumanController.loadDataPageEmployees(currentPage);
        this.renderTable();
    }//GEN-LAST:event_firstPageButtonMouseClicked

    private void backPageButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backPageButtonMouseClicked
        currentPage--;
        if (currentPage < 1) {
            currentPage = 1;
        } else {
            reloadTable();
        }
    }//GEN-LAST:event_backPageButtonMouseClicked

    private void nextPageButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextPageButtonMouseClicked
        int quantityPage = quantityPageCalculator(quantityOfEmployees);

        currentPage++;
        if (currentPage > quantityPage) {
            currentPage = quantityPage;
        } else {
            reloadTable();
        }
    }//GEN-LAST:event_nextPageButtonMouseClicked

    private void HumanTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HumanTableMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            int index = HumanTable.getSelectedRow();
            String staffCode = table.getValueAt(index, 2).toString().trim();

            currentManagerHumanController.loadEmployeeViewByStaffCode(staffCode);
            currentManagerHumanController.hideThisHumanManagerView();
        }
    }//GEN-LAST:event_HumanTableMouseClicked

    private void logoutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutMenuItemActionPerformed
        currentManagerHumanController.logout();
    }//GEN-LAST:event_logoutMenuItemActionPerformed

    private void searchFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldFocusGained
        searchField.setText("");
    }//GEN-LAST:event_searchFieldFocusGained

    private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseClicked
        currentPage = 1;
        searchText = searchField.getText();
        reloadTable();
    }//GEN-LAST:event_searchButtonMouseClicked

    private void endPageButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_endPageButtonMouseClicked
        currentPage = quantityPageCalculator(quantityOfEmployees);
        reloadTable();
    }//GEN-LAST:event_endPageButtonMouseClicked

    private void goToPageButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goToPageButtonMouseClicked
        try {
            int page = Integer.parseInt(selectPageField.getText());

            currentPage = page;
            if (page > quantityOfEmployees) {
                Message.showError(this, "You are enter page more than our total page");
                currentPage = quantityOfEmployees;
            }
            reloadTable();
        } catch (NumberFormatException ex) {
            Message.showError(this, "Please enter a number!");
        }
    }//GEN-LAST:event_goToPageButtonMouseClicked

    private void selectPageFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_selectPageFieldFocusGained
        selectPageField.setText("");
    }//GEN-LAST:event_selectPageFieldFocusGained

    private void multipleDeleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_multipleDeleteButtonMouseClicked
        ArrayList<String> listStaffCodeNeedToDelete = getStaffCodeInRowChecked();

        if (listStaffCodeNeedToDelete.size() == 0) {
            Message.showError(null, "No row has been selected");
        } else {
            boolean userSelected = Message.showYesNoQuestion(null,
                    "Do you really want to delete those? That action cannot undo");
            if (userSelected) {
                deleteMultipleEmployeesByStaffCode(listStaffCodeNeedToDelete);
                reloadTable();
            }
        }
    }//GEN-LAST:event_multipleDeleteButtonMouseClicked

    private void buttonSelectAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSelectAllMouseClicked
        if (isAllSelected) {
            unSelectAllRow();
        } else {
            selectAllRow();
        }
    }//GEN-LAST:event_buttonSelectAllMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        boolean userSelected = Message.showYesNoQuestion(null,
                "Do you wanna close?");

        if (userSelected) {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void managerCurrentUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managerCurrentUserMenuItemActionPerformed
        currentManagerHumanController.loadCurrentAccount();
    }//GEN-LAST:event_managerCurrentUserMenuItemActionPerformed
    private void viewAnEmployeeButtonMouseClicked(java.awt.event.MouseEvent evt, String staffCode) {
        currentManagerHumanController.loadViewAnEmployee(staffCode);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable HumanTable;
    private javax.swing.JButton addButton;
    private javax.swing.JButton backPageButton;
    private javax.swing.JButton buttonSelectAll;
    private javax.swing.JButton endPageButton;
    private javax.swing.JButton firstPageButton;
    private javax.swing.JButton goToPageButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem logoutMenuItem;
    private javax.swing.JMenuItem managerAllUserMenuItem;
    private javax.swing.JMenuItem managerCurrentUserMenuItem;
    private javax.swing.JButton multipleDeleteButton;
    private javax.swing.JButton nextPageButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JTextField selectPageField;
    private javax.swing.JMenu userManu;
    // End of variables declaration//GEN-END:variables
}
