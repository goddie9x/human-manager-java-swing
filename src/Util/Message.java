package Util;

import java.awt.Component;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;
import javax.swing.JPasswordField;

public class Message {

    public static void showSuccess(Component componentParent, String message) {
        JOptionPane.showMessageDialog(
                componentParent, message, "Success", INFORMATION_MESSAGE);
    }

    public static void showWarning(Component componentParent, String message) {
        JOptionPane.showMessageDialog(
                componentParent, message, "Warning", WARNING_MESSAGE);
    }

    public static void showError(Component componentParent, String message) {
        JOptionPane.showMessageDialog(
                componentParent, message, "Error", ERROR_MESSAGE);
    }

    public static boolean showYesNoQuestion(Component componentParent, String message) {
        int userSelected = JOptionPane.showConfirmDialog(
                componentParent, message, "Yes", YES_NO_OPTION);

        if (userSelected == 0) {
            return true;
        }
        return false;
    }

    public static String getConfirmPassWord() {
        JPasswordField pf = new JPasswordField();
        
        int okCxl = JOptionPane.showConfirmDialog(
                null, pf,
                "Enter Password",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (okCxl == JOptionPane.OK_OPTION) {
           String password = new String(pf.getPassword());
           
           return password;
        }
        
        return "";
    }
}
