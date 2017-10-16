package studentManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Manages the GUI for the creation of a new assessment.
 * @author David Wright
 */
public class AssessmentCreatorPanel extends JFrame {
    private JPanel initialFormContainer;
    private JPanel assessmentFormContainer;
    private JTextField assessmentNameField;
    private JTextField taskCountField;
    private JTextField totalMarksField;
    private ArrayList<JTextField> taskNameFields;
    private ArrayList<JTextArea> taskDescriptionFields;
    private ArrayList<JTextField> taskMarkFields;
    private Assessment assessment;

    public AssessmentCreatorPanel() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setResizable(false);

        initialFormContainer = createInitialInformationForm();

        add(initialFormContainer);
    }

    private JPanel createAssessmentInfoForm(int taskCount) {
        JPanel formContainer = new JPanel();

        JPanel headerPanel = new JPanel(new GridLayout(1, 3));
        JPanel mainPanel = new JPanel(new GridLayout(taskCount, 3, 4, 4));
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        formContainer.setLayout(new BorderLayout());
        formContainer.setPreferredSize(new Dimension(768, 160*taskCount+32));
        mainPanel.setPreferredSize(new Dimension(768, 160*taskCount));
        formContainer.add(headerPanel, BorderLayout.NORTH);
        formContainer.add(mainPanel, BorderLayout.CENTER);
        formContainer.add(footerPanel, BorderLayout.SOUTH);

        JLabel[] headerLabels = {
                new JLabel("Task Name"),
                new JLabel("Description"),
                new JLabel("Total Mark") };

        // add header labels
        for (JLabel label : headerLabels) {
            label.setFont(label.getFont().deriveFont(28.0f));
            label.setVerticalAlignment(SwingConstants.BOTTOM);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            headerPanel.add(label);
        }

        taskNameFields = new ArrayList<>();
        taskDescriptionFields = new ArrayList<>();
        taskMarkFields = new ArrayList<>();

        for (int i = 0; i < taskCount; i++) {
            JTextField taskName = new JTextField();
            taskName.setPreferredSize(new Dimension(192, 24));
            taskNameFields.add(taskName);
            JPanel nameFieldContainer = new JPanel();
            nameFieldContainer.add(taskName);
            mainPanel.add(nameFieldContainer);

            JTextArea taskDescription = new JTextArea();
            taskDescription.setLineWrap(true);
            taskDescription.setWrapStyleWord(true);
            taskDescriptionFields.add(taskDescription);
            JScrollPane descScrollPane = new JScrollPane(taskDescription);
            descScrollPane.setPreferredSize(new Dimension(64, 156));
            mainPanel.add(descScrollPane);

            JTextField taskMark = new JTextField();
            taskMark.setPreferredSize(new Dimension(64, 24));
            taskMarkFields.add(taskMark);
            JPanel markFieldContainer = new JPanel();
            markFieldContainer.add(taskMark);
            mainPanel.add(markFieldContainer);
        }

        JButton saveButton = new JButton("Create Assessment");
        saveButton.addActionListener(e -> {
            // save assessment here
            for (int i = 0; i < taskCount; i++ )
            {
                assessment.addTask(taskNameFields.get(i).getText(), taskDescriptionFields.get(i).getText(), 100, Integer.parseInt(taskMarkFields.get(i).getText()));
                assessment.Tasks.toString();
            }
            // remove panel after assessment is added
            AssessmentListPanel.addAssessment(assessment);
            System.out.println(assessment.toString());
            this.dispose();
        });
        footerPanel.add(saveButton);

        return formContainer;
    }

    private JPanel createInitialInformationForm() {
        JPanel infoContainer = new JPanel();  // uses FlowLayout, which provides an acceptable control layout

        // create GUI controls for initial information gathering stage, and add them to the container defined here
        infoContainer.add(new JLabel("Assessment Name"));
        assessmentNameField = new JTextField();
        assessmentNameField.setPreferredSize(new Dimension(128, 24));
        infoContainer.add(assessmentNameField);

        infoContainer.add(Box.createHorizontalStrut(36));

        infoContainer.add(new JLabel("Task Count"));
        taskCountField = new JTextField();
        taskCountField.setPreferredSize(new Dimension(24, 24));
        infoContainer.add(taskCountField);

        infoContainer.add(Box.createHorizontalStrut(36));

        infoContainer.add(new JLabel("Total Marks"));
        totalMarksField = new JTextField();
        totalMarksField.setPreferredSize(new Dimension(36, 24));
        infoContainer.add(totalMarksField);

        infoContainer.add(Box.createHorizontalStrut(36));

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            assessment = new Assessment(assessmentNameField.getText(), 100, Integer.parseInt(totalMarksField.getText()), "No description provided.");
            assessmentFormContainer = createAssessmentInfoForm(Integer.parseInt(taskCountField.getText()));

            JScrollPane scrollPane = new JScrollPane(assessmentFormContainer,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);

            add(scrollPane);
            initialFormContainer.setVisible(false);

        });
        infoContainer.add(nextButton);

        return infoContainer;
    }
}
