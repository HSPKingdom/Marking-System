package studentManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class deleteAssessment extends JFrame {
    private JPanel initialFormContainer;
    private JTextField assessmentName;
    private static ArrayList<Assessment> assessmentList = new ArrayList<>();


    public deleteAssessment(ArrayList<Assessment> assessmentList) {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(800, 80));
        setResizable(false);

        this.assessmentList = assessmentList;
        initialFormContainer = deleteForm();
        add(initialFormContainer);
    }
    public JPanel deleteForm()
    {
        JPanel formContainer = new JPanel();

        JPanel headerPanel = new JPanel(new GridLayout(1, 3));
        JLabel deleteText = new JLabel("Enter AssessmentName to delete");
        assessmentName = new JTextField();

        formContainer.add(headerPanel, BorderLayout.NORTH);
        headerPanel.add(deleteText);
        headerPanel.add(assessmentName);
        JButton deleteButton = new JButton("Delete Assessment");

        deleteButton.addActionListener(e -> {
            for (Assessment assessment : assessmentList)
            {
                if(assessment.getTitle().equals(assessmentName.getText()))
                {
                    System.out.println("Deleted");
                    assessmentList.remove(assessment);
                    break;
                }
            }
            this.dispose();
        });
        headerPanel.add(deleteButton);

        return formContainer;

    }
}
