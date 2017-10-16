package studentManager;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton that contains and manages the list of assessments, and is used for assessment management and an entry to
 * marking.
 * @author David Wright
 */
public class AssessmentListPanel extends JPanel {
    private static JPanel mainPanel;
    private static JPanel footerPanel;
    private static ArrayList<Assessment> assessmentList = new ArrayList<>();
    private static AssessmentListPanel instance;

    public static AssessmentListPanel getPanel() {
        if (instance == null) {
            instance = new AssessmentListPanel();
        }

        return instance;
    }

    private AssessmentListPanel() {
        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setPreferredSize(new Dimension(768, 88*assessmentList.size()));
        JScrollPane listScrollPane = new JScrollPane(mainPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        listScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(listScrollPane, BorderLayout.CENTER);

        footerPanel = new JPanel();
        add(footerPanel, BorderLayout.PAGE_END);

        // add controls to for assessment management
        JButton createButton = new JButton("Create Assessment");
        JButton deleteButton = new JButton("Delete Assessment");

        createButton.addActionListener(e -> {
            (new AssessmentCreatorPanel()).setVisible(true);
        });

        deleteButton.addActionListener(e -> {
            // delete assessment
            (new deleteAssessment(assessmentList)).setVisible(true);
        });

        footerPanel.add(createButton);
        footerPanel.add(deleteButton);

        for (Assessment assessment : assessmentList) {
            mainPanel.add(new AssessmentListCard(assessment));
        }
    }

    private static class AssessmentListCard extends JPanel {
        public final Assessment associatedAssessment;

        public AssessmentListCard(Assessment assessment) {
            associatedAssessment = assessment;
            setLayout(new BorderLayout());
            setMaximumSize(new Dimension(800, 88));

            JPanel contextButtonsContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton editAssessmentButton = new JButton("Edit");
            JButton markSummaryButton = new JButton("View Details");
            contextButtonsContainer.add(editAssessmentButton);
            contextButtonsContainer.add(markSummaryButton);
            add(contextButtonsContainer, BorderLayout.PAGE_END);

            // define assessment specific behaviour
            editAssessmentButton.addActionListener(e -> {
                // called when edit button is clicked
            	EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        				
        					EditAssessmentPage frame = new EditAssessmentPage(associatedAssessment);
        					//frame.setExtendedState(JFrame.NORMAL);
        					frame.setSize(800, 600);
        					
        					frame.setVisible(true);
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		});
            });

            markSummaryButton.addActionListener(e -> {
                // called when summary button is clicked
                System.out.println(associatedAssessment.getDescription());
            });

            JLabel assessmentTitle = new JLabel(associatedAssessment.getTitle());
            add(assessmentTitle, BorderLayout.PAGE_START);

            JTextArea descriptionArea = new JTextArea(associatedAssessment.getDescription());
            descriptionArea.setLineWrap(true);
            descriptionArea.setWrapStyleWord(true);
            descriptionArea.setEditable(false);
            descriptionArea.setBackground(this.getBackground());
            add(descriptionArea, BorderLayout.CENTER);

            setBorder(new EmptyBorder(new Insets(4, 4, 4, 4)));
        }
    }

    public static List<Assessment> getAssessments() {
        return new ArrayList<>(assessmentList);
    }

    public static boolean addAssessment(Assessment a) {
        if (assessmentList.contains(a)) {
            return false;
        } else {
            assessmentList.add(a);
            mainPanel.add(new AssessmentListCard(a));
            mainPanel.setPreferredSize(new Dimension(768, 88*assessmentList.size()));
            mainPanel.repaint();
            return true;
        }
    }

    public static boolean removeAssessment(Assessment a) {
        if (!assessmentList.contains(a)) {
            return false;
        } else {
            assessmentList.remove(a);

            for (Component assessmentCard : mainPanel.getComponents()) {
                if (!(assessmentCard instanceof AssessmentListCard)) {continue;}
                if (((AssessmentListCard) assessmentCard).associatedAssessment == a) {
                    mainPanel.remove(assessmentCard);
                    break;
                }
            }

            mainPanel.setPreferredSize(new Dimension(768, 88*assessmentList.size()));
            mainPanel.repaint();
            return true;
        }
    }
}
