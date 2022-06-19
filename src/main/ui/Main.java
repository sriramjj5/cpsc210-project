package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

// Runs the application
// CITATIONS: Nimbus look and feel taken from https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/nimbus.html

public class Main {
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            new SchoolUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file to load from not found");
        } catch (Exception e) {
            System.out.println("Unable to run application: Nimbus Look And Feel encountered an error");
        }
    }
}
