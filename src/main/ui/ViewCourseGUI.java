package ui;

import model.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Puts a JPanel representing a menu from which student can view their registered courses onto current JFrame.

public class ViewCourseGUI extends JPanel implements ActionListener {
    private static ArrayList<JButton> courseButtons = new ArrayList<>();
    private static String BACK = "back";

    private static JFrame controllingFrame;

    // MODIFIES: controllingFrame
    // EFFECTS: Sets frame & adds button pane
    public ViewCourseGUI() {
        controllingFrame = LogInGUI.controllingFrame;

        JComponent buttonPane = createButtonPanel();

        add(buttonPane);
    }

    // MODIFIES: p, initialized in method code
    // EFFECTS: Adds & initializes buttons onto current panel
    protected JComponent createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(0, 1));
        JButton backButton = new JButton("<- Back");
        backButton.setBackground(Color.yellow);
        backButton.setActionCommand(BACK);
        backButton.addActionListener(this);

        for (Course c : LogInGUI.currentStudent.getCourses().getCourses()) {
            String courseName = c.getCourseID();
            String slotsAvailable = c.getSlotsAvailable().toString();
            String totalSlots = c.getTotalSlots().toString();
            JButton courseButton = new JButton(courseName + ", Slots available: " + slotsAvailable + "/" + totalSlots);
            courseButtons.add(courseButton);
            p.add(courseButton);
        }

        p.add(backButton);

        return p;
    }

    // EFFECTS: Sets the actions to be performed when buttons are clicked
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals(BACK)) {
            AccountMenuGUI.startup();
        }
    }

    // MODIFIES: controllingFrame
    // EFFECTS: starts the course viewing menu up after user specifies that they want to view their registered courses
    public static void startup() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final ViewCourseGUI newContentPane = new ViewCourseGUI();
                controllingFrame.getContentPane().removeAll();
                controllingFrame.getContentPane().add(newContentPane);
                controllingFrame.revalidate();
                controllingFrame.repaint();
                controllingFrame.setTitle("View your courses");
            }
        });
    }
}
