package ui;

import model.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Puts a JPanel representing a menu from which student can add courses onto current JFrame.

public class AddCourseGUI extends JPanel implements ActionListener {
    private static ArrayList<JButton> courseButtons = new ArrayList<>();
    private static String BACK = "back";

    private static JFrame controllingFrame; //needed for dialogs

    // MODIFIES: controllingFrame
    // EFFECTS: Sets frame & adds button pane
    public AddCourseGUI() {
        //Use the default FlowLayout.
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

        for (Course c : SchoolUI.getCurrentSchool().getExistingCourses()) {
            String courseName = c.getCourseID();
            String slotsAvailable = c.getSlotsAvailable().toString();
            String totalSlots = c.getTotalSlots().toString();
            JButton courseButton = new JButton(courseName + ", Slots available: " + slotsAvailable + "/" + totalSlots);
            if (LogInGUI.currentStudent.getCourses().getCourses().contains(c)) {
                courseButton.setBackground(Color.red);
            }
            courseButtons.add(courseButton);
            courseButton.setActionCommand(courseName);
            courseButton.addActionListener(this);
            p.add(courseButton);
        }

        p.add(backButton);

        return p;
    }

    // EFFECTS: Sets the actions to be performed when buttons are clicked
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (SchoolUI.staticOnInputAdd(LogInGUI.currentStudent, cmd)) {
            JOptionPane.showMessageDialog(controllingFrame,
                    "Course added successfully!");
            startup();
        } else if (cmd.equals(BACK)) {
            AccountMenuGUI.startup();
        } else {
            JOptionPane.showMessageDialog(controllingFrame,
                    "Course couldn't be added. Please retry.");
        }
    }

    // MODIFIES: controllingFrame
    // EFFECTS: starts the course adding menu up after user specifies that they want to add a course
    public static void startup() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final AddCourseGUI newContentPane = new AddCourseGUI();
                controllingFrame.getContentPane().removeAll();
                controllingFrame.getContentPane().add(newContentPane);
                controllingFrame.revalidate();
                controllingFrame.repaint();
                controllingFrame.setTitle("Add a course");
            }
        });
    }
}
