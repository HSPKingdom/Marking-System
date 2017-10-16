package studentManager;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

public class AssessmentForm {
    private JButton AllUnmarkedStudent;
    private JPanel panelMain;

    //Example
    private ArrayList<Assessment> assessment = new ArrayList<>();           // A list of assessment in the subject
    private ArrayList<String> students = new ArrayList<>();                 // List of Students studies in this unit


    public AssessmentForm()
    {
        AllUnmarkedStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, showAllUnmardedStudent());
            }
        });
    }

    public static void main(String[] args)  {
        JFrame frame = new JFrame("AssessmentForm");
        frame.setContentPane(new AssessmentForm().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }




    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>EXAMPLE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public String showAllUnmardedStudent()
    {
        String output = "";

        addAssessmentExample();               // <<< PUT TEMPLATE DATA INTO IT
        addMarkExample();

        // Show all the example marks
        //output += showExample();

        //User Story 2
        // Show the marking state of each Assessment
        ArrayList<String> studentNotMarked = new ArrayList<>();
        studentNotMarked.clear();

        for(Assessment assignments : assessment)
        {
            output += "\nFor " + assignments.getTitle() + "\n";
            for(AssessmentTask tasks : assignments.Tasks)
            {
                studentNotMarked.clear();
                studentNotMarked = assignments.getStudentNotMarkedByTask(tasks.getTaskName(), students);

                if(studentNotMarked.isEmpty())
                    output += tasks.getTaskName() + " is fully Marked\n";
                else
                    output += tasks.getTaskName() + " is not fully Marked, These are the students which has not been marked\n" + studentNotMarked + "\n";
            }
        }

        return output;
    }



    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>EXAMPLE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void addAssessmentExample()
    {

        // Create Assignment
        // Assignment1
        Assessment assignment = new Assessment("Assignment1", 15, 100, "Testing");
        assessment.add(assignment);
        // Assignment2
        Assessment assignment2 = new Assessment("Assignment2", 85, 100, "Final");
        assessment.add(assignment2);


        // Create Task in Assignment
        // Add a Task in Assignment1   // !!!!!!!!!!!!!!SEARCH INDEX CAN BE REPLACE BY SCAN THROUGH THE WHOLE ARRAYLIST
        int index = searchIndex("Assignment1");                 // Search for index in the arrayList
        assessment.get(index).addTask("Task1", "Add Task", 30, 30);
        assessment.get(index).addTask("Task2", "Done Task", 70, 70);
        // Add a Task in Assignment2
        index = searchIndex("Assignment2");                     // Search for index in the arrayList
        assessment.get(index).addTask("Question1", "Add Task", 30, 30);
        assessment.get(index).addTask("Question2", "Done Task", 70, 70);

        // Create Students
        // Lists of students
        students.add("Adrian");
        students.add("Ben");
        students.add("Chloe");



    }

    public void addMarkExample()
    {
        // Create marks
        // Creating marks for Assignment1   // Task1    // Missing >>Chloe<<
        Hashtable<String, Float> task1Marks = new Hashtable<>();
        float score = 30;
        task1Marks.put("Adrian", score);
        task1Marks.put("Ben", score);
        score = 20;
        task1Marks.put("Chloe", score);
        // Task2    //Missing Chloe
        Hashtable<String, Float> task2Marks = new Hashtable<>();
        float score2 = 40;
        task2Marks.put("Adrian", score2);
        score = 10;
        task2Marks.put("Ben", score2);

        //Creating marks for Assignment2    // Question1//Missing Ben
        Hashtable<String, Float> question1Marks = new Hashtable<>();
        score = 25;
        question1Marks.put("Adrian", score);
        score = 20;
        question1Marks.put("Chloe", score);
        // Question2//Missing Ben
        Hashtable<String, Float> question2Marks = new Hashtable<>();
        score = 50;
        question2Marks.put("Adrian", score);
        score = 70;
        question2Marks.put("Chloe", score);

        // Add Tasks marks to Assignment
        // Assignment1 : Add Tasks Marks
        int index = searchIndex("Assignment1");     // Get the index of Assignment1 is belongs to arrayList
        assessment.get(index).addTaskMark("Task1", task1Marks);
        assessment.get(index).addTaskMark("Task2", task2Marks);
        //Assignment2 : Add Tasks Marks
        index = searchIndex("Assignment2");     // Get the index of Assignment2 is belongs to arrayList
        assessment.get(index).addTaskMark("Question1", question1Marks);
        assessment.get(index).addTaskMark("Question2", question2Marks);
    }

    public String showExample()
    {
        String output = "";
        // Show all the tasks
        for(Assessment assignments : assessment)
        {
            output += assignments.getTitle() + ":\n";
            for(AssessmentTask tasks : assignments.Tasks)
                output += "\t" + tasks.getTaskName() + ":\n" + tasks.printMarks();
        }
        return output;

    }

    public int searchIndex(String assignmentTitle)
    {
        for(Assessment temp : assessment)
        {
            if(temp.getTitle().compareTo(assignmentTitle) == 0)
                return assessment.indexOf(temp);
        }
        return -1;
    }



}
