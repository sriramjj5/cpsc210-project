package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Puts a JPanel representing an account menu onto current JFrame. From here, student can add/delete/view/save/logout.

public class AccountMenuGUI extends JPanel implements ActionListener {
    private static String ADD = "ok";
    private static String DROP = "drop";
    private static String VIEW = "view";
    private static String LOGOUT = "logout";
    private static String SAVE = "save";
    JLabel label1 = new JLabel("Your details were successfully loaded from " + SchoolUI.JSON_STORE + "!");
    JPanel jpanel = new JPanel(new GridLayout(0, 1));

    protected static JFrame controllingFrame;

    // MODIFIES: controllingFrame
    // EFFECTS: Sets frame & adds button pane
    public AccountMenuGUI() {
        controllingFrame = LogInGUI.controllingFrame;

        JComponent buttonPane = createButtonPanel();

        add(buttonPane);
    }

    // MODIFIES: jpanel
    // EFFECTS: Adds & initializes buttons onto current panel
    protected JComponent createButtonPanel() {
        JButton addButton = new JButton("Add a course");
        JButton dropButton = new JButton("Drop a course");
        JButton viewButton = new JButton("View your courses");
        JButton saveButton = new JButton("Save");
        JButton logoutButton = new JButton("LOG OUT");
        logoutButton.setBackground(Color.yellow);

        addButton.setActionCommand(ADD);
        dropButton.setActionCommand(DROP);
        viewButton.setActionCommand(VIEW);
        saveButton.setActionCommand(SAVE);
        logoutButton.setActionCommand(LOGOUT);
        addButton.addActionListener(this);
        dropButton.addActionListener(this);
        viewButton.addActionListener(this);
        saveButton.addActionListener(this);
        logoutButton.addActionListener(this);

        jpanel.add(label1);
        jpanel.add(addButton);
        jpanel.add(dropButton);
        jpanel.add(viewButton);
        jpanel.add(saveButton);
        jpanel.add(logoutButton);

        return jpanel;
    }

    // EFFECTS: Sets the actions to be performed when buttons are clicked
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (ADD.equals(cmd)) {
            AddCourseGUI.startup();
        } else if (DROP.equals(cmd)) {
            DropCourseGUI.startup();
        } else if (VIEW.equals(cmd)) {
            ViewCourseGUI.startup();
        } else if (SAVE.equals(cmd)) {
            SchoolUI.staticSaveSchool();
            JOptionPane.showMessageDialog(controllingFrame,
                    "Account info has been saved!");
        } else if (LOGOUT.equals(cmd)) {
            LogInGUI.currentStudent = null;
            controllingFrame.dispose();
            LogInGUI.startup();
        }
    }

    // MODIFIES: controllingFrame
    // EFFECTS: starts the account menu up after user logs in or creates their account
    public static void startup() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final AccountMenuGUI newContentPane = new AccountMenuGUI();
                controllingFrame.getContentPane().removeAll();
                controllingFrame.getContentPane().add(newContentPane);
                controllingFrame.revalidate();
                controllingFrame.repaint();
                controllingFrame.setTitle("Account Menu");
            }
        });
    }
}
