package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Puts a JPanel representing a menu from which student can create an account onto current JFrame.
// CITATION: Much of code is modeled from PasswordDemo.
//           PasswordDemo code at https://docs.oracle.com/javase/tutorial/uiswing/components/passwordfield.html

public class CreateAccountGUI extends JPanel implements ActionListener {

    private static String OK = "ok";

    protected static JFrame controllingFrame = LogInGUI.controllingFrame;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField nameField;

    JLabel label0 = new JLabel("Enter your name: ");
    JLabel label1 = new JLabel("Enter your desired username: ");
    JLabel label2 = new JLabel("Enter your desired password: ");

    // MODIFIES: controllingFrame, nameField, usernameField, passwordField
    // EFFECTS: Sets frame, adds button pane & user-inputted fields (text) for their desired name, username & password
    public CreateAccountGUI() {

        usernameField = new JTextField(10);
        usernameField.setActionCommand(OK);
        usernameField.addActionListener(this);

        passwordField = new JTextField(10);
        passwordField.setActionCommand(OK);
        passwordField.addActionListener(this);

        nameField = new JTextField(10);
        nameField.setActionCommand(OK);
        nameField.addActionListener(this);

        label0.setLabelFor(nameField);
        label1.setLabelFor(usernameField);
        label2.setLabelFor(passwordField);

        JComponent buttonPane = createButtonPanel();

        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        textPane.add(label0);
        textPane.add(nameField);
        textPane.add(label1);
        textPane.add(usernameField);
        textPane.add(label2);
        textPane.add(passwordField);

        add(textPane);
        add(buttonPane);
    }

    // MODIFIES: p, initialized in method code
    // EFFECTS: Adds & initializes buttons onto current panel
    protected JComponent createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(0,1));
        JButton okButton = new JButton("OK");

        okButton.setActionCommand(OK);
        okButton.addActionListener(this);

        p.add(okButton);

        return p;
    }

    // EFFECTS: Sets the actions to be performed when buttons are clicked
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (OK.equals(cmd)) {
            String name = nameField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (SchoolUI.addAccount(name, username, password)) {
                JOptionPane.showMessageDialog(controllingFrame,
                        "Account created successfully! Welcome, " + name);
                AccountMenuGUI.startup();
            } else {
                JOptionPane.showMessageDialog(controllingFrame,
                        "This username already exists! Please try logging in.");
            }
        }
    }

    // MODIFIES: controllingFrame
    // EFFECTS: starts the account creation menu up when user specifies that they want to create an account
    public static void startup() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final CreateAccountGUI newContentPane = new CreateAccountGUI();
                controllingFrame.getContentPane().removeAll();
                controllingFrame.getContentPane().add(newContentPane);
                controllingFrame.revalidate();
                controllingFrame.repaint();
                controllingFrame.setTitle("Create an account");
            }
        });
    }

}
