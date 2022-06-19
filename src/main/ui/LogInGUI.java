package ui;

import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

// Initializes the GUI! Also presents user with a page from which they can log into their account.
// CITATIONS: Much of code is modeled from PasswordDemo.
//            PasswordDemo code at https://docs.oracle.com/javase/tutorial/uiswing/components/passwordfield.html
//            Picture of waving dog taken from https://www.stocksy.com/1993531/old-cattle-dog-waving-paw

public class LogInGUI extends JPanel implements ActionListener {

    private static String OK = "ok";
    private static String NO_ACCOUNT = "help";
    private static String EXIT = "exit";

    protected static Student currentStudent;

    protected static JFrame controllingFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    // MODIFIES: controllingFrame, usernameField (declared in method code), passwordField (declared in method code)
    // EFFECTS: Sets frame, adds button pane & user-inputted fields (text) for their username and password
    public LogInGUI(JFrame f) {
        controllingFrame = f;

        controllingFrame.setPreferredSize(new Dimension(1500, 500));

        usernameField = new JTextField(10);
        usernameField.setActionCommand(OK);
        usernameField.addActionListener(this);

        passwordField = new JPasswordField(10);
        passwordField.setActionCommand(OK);
        passwordField.addActionListener(this);

        JLabel label1 = new JLabel("Enter your username: ");

        JLabel label2 = new JLabel("Enter your password: ");
        label1.setLabelFor(usernameField);
        label2.setLabelFor(passwordField);

        JComponent buttonPane = createButtonPanel();

        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
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
        JButton helpButton = new JButton("Don't have an account?");
        JButton exitButton = new JButton("Safely exit?");

        okButton.setActionCommand(OK);
        helpButton.setActionCommand(NO_ACCOUNT);
        exitButton.setActionCommand(EXIT);
        okButton.addActionListener(this);
        helpButton.addActionListener(this);
        exitButton.addActionListener(this);

        p.add(okButton);
        p.add(helpButton);
        p.add(exitButton);

        return p;
    }

    // EFFECTS: Sets the actions to be performed when buttons are clicked
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (OK.equals(cmd)) {
            String username = usernameField.getText();
            char[] input = passwordField.getPassword();
            if (isPasswordCorrect(username, input)) {
                JOptionPane.showMessageDialog(controllingFrame,
                        "Welcome back!");
                AccountMenuGUI.startup();
            } else {
                JOptionPane.showMessageDialog(controllingFrame,
                        "Invalid password. Try again.",
                        "Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }

            Arrays.fill(input, '0');

            passwordField.selectAll();
            resetFocus();
        } else if (NO_ACCOUNT.equals(cmd)) { //The user wants to create an account.
            CreateAccountGUI.startup();
        } else if (EXIT.equals(cmd)) {
            controllingFrame.dispose();
            displayThankYou();
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays a new JFrame thanking the user with a cute picture of a dog waving
    private static void displayThankYou() {
        JFrame thankYouFrame = new JFrame();
        thankYouFrame.setTitle("Thank you!");
        ImageIcon icon = new ImageIcon("./data/dog.jpg");
        JLabel label = new JLabel(icon);
        thankYouFrame.add(label);
        thankYouFrame.setDefaultCloseOperation(controllingFrame.EXIT_ON_CLOSE);
        thankYouFrame.pack();
        thankYouFrame.setVisible(true);
    }

    // EFFECTS: Checks whether user-inputted username and password correspond to a pre-existing account or not
    private static boolean isPasswordCorrect(String username, char[] input) {
        boolean isCorrect;

        isCorrect = SchoolUI.checkLogin(username, input);

        return isCorrect;
    }

    // EFFECTS: resets cursor to GUI's username text box
    protected void resetFocus() {
        usernameField.requestFocusInWindow();
    }

    // EFFECTS: initializes frame
    static void createAndShowGUI() {
        JFrame frame = new JFrame("Log In");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final LogInGUI newContentPane = new LogInGUI(frame);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                newContentPane.resetFocus();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: starts up the GUI
    public static void startup() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}
