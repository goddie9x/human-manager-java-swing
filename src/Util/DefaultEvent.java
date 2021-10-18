package Util;

import javax.swing.*;
import java.awt.event.*;

public class DefaultEvent {

    public static void addEventOnclickClearTextField(JTextField currentTextField) {
        currentTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                clearText(currentTextField);
            }
        });
    }

    private static void clearText(JTextField currentTextField) {
        currentTextField.setText("");
    }

    public static void forcusHideComponent(
            JComponent componentActive, JComponent componentTarget) {
        componentActive.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                componentTarget.setVisible(false);
            }
        });
    }

    public static void forcusHideWarning(
            JComponent componentActive, JComponent labelWarning) {
        componentActive.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                componentActive.setBorder(null);
                labelWarning.setVisible(false);
            }
        });
    }

    public static void forcusHideComponents(
            JComponent componentActive, JComponent[] componentTargets) {
        componentActive.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                for (JComponent componentTarget : componentTargets) {
                    componentTarget.setVisible(false);
                }
            }
        });
    }
}
