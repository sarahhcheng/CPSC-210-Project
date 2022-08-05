package ui;

import javax.swing.*;
import java.awt.event.*;

public class GUI extends JDialog {
    private JPanel contentPane;
    private JButton xButton;
    private JTextField textField1;
    private JTextArea recipesTextArea;
    private JTextArea makeYourRecipeTextArea;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;

    public GUI() {
        setContentPane(contentPane);
        setModal(true);
//        makeYourRecipeTextArea.setEnabled(false);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        GUI dialog = new GUI();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
