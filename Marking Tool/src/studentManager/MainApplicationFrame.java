package studentManager;     

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Contains the tabbed layout for the main application window.
 * @author David Wright
 */
public class MainApplicationFrame extends JFrame {
    public MainApplicationFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Students", new GroupPanel());
        tabbedPane.addTab("Assignments", AssessmentListPanel.getPanel());

        // sample data
        Assessment sampleAssessment = new Assessment("Assignment A", 20, 100, "A sample assignment.");
        sampleAssessment.addTask("Task 1", "The first task, in which students are expected to design an application interface.", 30, 20);
        sampleAssessment.addTask("Task 2", "The second task, during which students should review each others' interface designs.", 30, 15);
        sampleAssessment.addTask("Task 3", "The third task, in which students implement their interface designs.", 30, 25);
        sampleAssessment.addTask("Task 4", "The last task, students will present their interface to their fellow students.\n\nThe marks for this task contribute only 10% of the final mark for this assignment, so no stress", 10, 10);

        AssessmentListPanel.addAssessment(sampleAssessment);
        AssessmentListPanel.addAssessment(new Assessment("Assignment 2", 20, 100, "A second assignment."));
        AssessmentListPanel.addAssessment(new Assessment("Assignment 3", 20, 100, "A third assignment."));
        AssessmentListPanel.addAssessment(new Assessment("Assignment 4", 20, 100, "A fourth assignment."));
        AssessmentListPanel.addAssessment(new Assessment("Assignment 5", 10, 100, "A fifth assignment."));
        AssessmentListPanel.addAssessment(new Assessment("Assignment 6", 10, 100, "The final assignment."));

        ArrayList<String> studentID = new ArrayList<>();
        studentID.add("27871509");
        studentID.add("12345678");
        JScrollPane assignmentScrollPane = new JScrollPane(new MarkingPanel(sampleAssessment, studentID),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        assignmentScrollPane.getVerticalScrollBar().setUnitIncrement(16); // increase scrolling speed as it is quite slow by default
        tabbedPane.addTab("Marking", assignmentScrollPane);

        add(tabbedPane);
        setSize(new Dimension(800, 600));
        setResizable(false);
    }
}

