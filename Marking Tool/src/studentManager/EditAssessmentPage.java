package studentManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;

public class EditAssessmentPage extends JFrame {
	private JPanel headerPanel;
    private JPanel mainPanel;
    private JPanel footerPanel;
    private Assessment thisAssessment;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assessment sampleAssessment = new Assessment("Assignment 1", 20, 100, "A sample assignment.");
			        sampleAssessment.addTask("Task 1", "The first task, in which students are expected to design an application interface.", 30, 20);
			        sampleAssessment.addTask("Task 2", "The second task, during which students should review each others' interface designs.", 30, 15);
			        sampleAssessment.addTask("Task 3", "The third task, in which students implement their interface designs.", 30, 25);
			        sampleAssessment.addTask("Task 4", "The last task, students will present their interface to their fellow students.\n\nThe marks for this task contribute only 10% of the final mark for this assignment, so no stress", 10, 10);
					EditAssessmentPage frame = new EditAssessmentPage(sampleAssessment);
					//frame.setExtendedState(JFrame.NORMAL);
					frame.setSize(800, 600);
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public EditAssessmentPage(Assessment assessment) {
			//setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		 	thisAssessment = assessment;
	        headerPanel = new JPanel(new GridLayout(1, 3));
	        mainPanel = new JPanel(new GridLayout(0, 3, 4, 4));
	        footerPanel = new JPanel();

	        List<AssessmentTask> taskList = thisAssessment.getTasks();

	        getContentPane().setLayout(new BorderLayout());
	        //setPreferredSize(new Dimension(800, 160*taskList.size()+32));
	        getContentPane().add(headerPanel, BorderLayout.NORTH);
	        getContentPane().add(mainPanel, BorderLayout.CENTER);
	        getContentPane().add(footerPanel, BorderLayout.SOUTH);

	        JLabel[] headerLabels = {
	                new JLabel("Task"),
	                new JLabel("Criteria"),
	                new JLabel("Mark") };

	        // add header labels
	        for (JLabel label : headerLabels) {
	            label.setFont(label.getFont().deriveFont(28.0f));
	            label.setVerticalAlignment(SwingConstants.BOTTOM);
	            label.setHorizontalAlignment(SwingConstants.CENTER);
	            headerPanel.add(label);
	        }

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
	            taskDescriptionArea.setEditable(true);
	            //taskDescriptionArea.setBackground(this.getBackground());
	            mainPanel.add(new JScrollPane(taskDescriptionArea));

	            JTextField TotalmarkField = new JTextField(2);
	            TotalmarkField.setText(String.valueOf(task.getTotalMark()));
	            JPanel markContainer = new JPanel();
	            markContainer.add(TotalmarkField);
	            //markContainer.add(new JLabel("/ "+task.getTotalMark()));
	            mainPanel.add(markContainer);
	        }
	        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

	        // add controls to save
	        JButton saveButton = new JButton("Save Assignment");
	        footerPanel.add(saveButton);
	        footerPanel.add(new JPanel()); // empty as a spacer
	        footerPanel.add(new JPanel()); // empty as a spacer
	    }
	}


