package studentManager;



import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
/**
 * Contains the assessment marking logic
 * @author David Wright
 */
public class MarkingPanel extends JPanel {
    private JPanel headerPanel;
    private JPanel mainPanel;
    private JPanel footerPanel;
    private Assessment thisAssessment;
    private ArrayList<JTextArea> commentArea    = new ArrayList<JTextArea>();
    private ArrayList<JTextField> markField     = new ArrayList<JTextField>();
    private JTextArea TotalMarks = new JTextArea();
    private ArrayList<String> studentID         = new ArrayList<>();


    public MarkingPanel(Assessment assessment, ArrayList<String> tempStudentID) {
        thisAssessment = assessment;
        studentID = tempStudentID;
        headerPanel = new JPanel(new GridLayout(1, 4));
        mainPanel = new JPanel(new GridLayout(0, 4, 4, 4));
        footerPanel = new JPanel(new GridLayout(1, 5, 48, 12));

        List<AssessmentTask> taskList = thisAssessment.getTasks();

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 160*taskList.size()+32));
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        JLabel[] headerLabels = {
                new JLabel("Task"),
                new JLabel("Criteria"),
                new JLabel("Comment"),
                new JLabel("Mark") };

        // add header labels
        for (JLabel label : headerLabels) {
            label.setFont(label.getFont().deriveFont(28.0f));
            label.setVerticalAlignment(SwingConstants.BOTTOM);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            headerPanel.add(label);
        }



        //Pre ForLoop
        int counter = 0;
        SimpleListener ourListener = new SimpleListener();
        MarkListener markListener = new MarkListener();

        // add controls for each task
        for (AssessmentTask task : taskList) {
            JLabel taskNameLabel = new JLabel(task.getTaskName());
            taskNameLabel.setFont(taskNameLabel.getFont().deriveFont(16.0f));
            taskNameLabel.setVerticalAlignment(SwingConstants.TOP);
            taskNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            mainPanel.add(taskNameLabel);

            JTextArea taskDescriptionArea = new JTextArea(task.getTaskDescribtion());
            taskDescriptionArea.setLineWrap(true);
            taskDescriptionArea.setWrapStyleWord(true);
            taskDescriptionArea.setEditable(false);
            taskDescriptionArea.setBackground(this.getBackground());
            mainPanel.add(new JScrollPane(taskDescriptionArea));

            JTextArea commentTemp = new JTextArea();
            commentTemp.setLineWrap(true);
            commentTemp.setWrapStyleWord(true);
            commentArea.add(commentTemp);
            mainPanel.add(new JScrollPane(commentArea.get(counter)));

            JTextField markFieldTemp = new JTextField();
            markFieldTemp.setPreferredSize(new Dimension(32, 24));
            markField.add(markFieldTemp);
            markField.get(counter).addActionListener(markListener);
            JPanel markContainer = new JPanel();
            markContainer.add(markField.get(counter));
            markContainer.add(new JLabel("/ "+task.getTotalMark()));
            mainPanel.add(markContainer);


            counter++;
        }

        // add controls to save
        JButton saveButton  = new JButton("Save mark");
        saveButton.addActionListener(ourListener);
        JButton sendButton  = new JButton("Send mark to student");


        footerPanel.add(new JPanel()); // empty as a spacer
        footerPanel.add(saveButton);
        footerPanel.add(sendButton);
        footerPanel.add(new JPanel()); // empty as a spacer
        footerPanel.add(TotalMarks);

    }
    private class MarkListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            float sum = 0, total = 0;
            for(int i=0; i<= thisAssessment.Tasks.size()-1; i++)
            {
                total += thisAssessment.Tasks.get(i).getTotalMark();

                if(!markField.get(i).getText().equals("")) {
                    sum += Float.parseFloat(markField.get(i).getText());
                }
            }
            TotalMarks.setText("Total Mark: " + String.valueOf(sum) + "/" + total);

        }
    }
    private class SimpleListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String description[] = new String[thisAssessment.Tasks.size()];
            float marks[] = new float[thisAssessment.Tasks.size()];

            for(int i=0; i<= thisAssessment.Tasks.size()-1; i++)
            {
                if(!commentArea.get(i).getText().equals(""))
                    description[i] = commentArea.get(i).getText();
                if(!markField.get(i).getText().equals("")&&!markField.get(i).getText().equals(null))
                    marks[i] = Float.parseFloat(markField.get(i).getText());
            }
            for(String student: studentID)
            {
                for(int i=0; i<= thisAssessment.Tasks.size()-1; i++)
                {

                    thisAssessment.Tasks.get(i).addMark(student, marks[i]);
                    if(description[i]!=null)
                        thisAssessment.Tasks.get(i).addComment(student, description[i]);
                }
            }
            //TEST Need to be deleted

                for(int i=0; i<= thisAssessment.Tasks.size()-1; i++)
                {
                    System.out.print(thisAssessment.Tasks.get(i).printDetail());
                }

        }

    }

}
