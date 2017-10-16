package studentManager;



import org.junit.Test;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;


public class AssessmentDemonstrate {


    private ArrayList<Assessment> assessment = new ArrayList<>();           // A list of assessment in the subject
    private ArrayList<String> students = new ArrayList<>();                 // List of Students studies in this unit


    @Test   // Demonstration of User Story 7
    public void demonstrationUserStory7()
    {
        Random random = new Random();

        addAssessmentExample();
        for(Assessment assignments : assessment)
        {
            System.out.println("\n\n" + assignments.getTitle() + ":");
            for(AssessmentTask tasks : assignments.Tasks)
            {
                System.out.println("\n>>>>>" + tasks.getTaskName() + "\nMarking scheme: " + tasks.getTaskDescribtion() + "\nTotal Mark of task" + tasks.getTotalMark() + "\nPercentage to assessment:" + tasks.getPercentage() + "%\n");
                for(String studentID : students)
                {
                    float marks = random.nextInt(tasks.getTotalMark()+1);
                    tasks.addMark(studentID, marks);
                    System.out.println("Student " + studentID + " added, score --> " + marks);
                }
            }
        }
    }


    @Test
    public void demonstrationUserStory2() {

        addAssessmentExample();               // <<< PUT TEMPLATE DATA INTO IT
        addMarkExample();
        // Show all the tasks
        for(Assessment assignments : assessment)
        {
            System.out.println(assignments.getTitle() + ":\n");
            for(AssessmentTask tasks : assignments.Tasks)
                System.out.println("\t" + tasks.getTaskName() + ":\n" + tasks.printMarks());
        }


        //User Story 2
        // Show the marking state of each Assessment
        ArrayList<String> studentNotMarked = new ArrayList<>();
        for(Assessment assignments : assessment)
        {
            System.out.println("\nFor " + assignments.getTitle());
            for(AssessmentTask tasks : assignments.Tasks)
            {
                studentNotMarked.clear();
                studentNotMarked = assignments.getStudentNotMarkedByTask(tasks.getTaskName(), students);

                if(studentNotMarked.isEmpty())
                    System.out.println(tasks.getTaskName() + " is fully Marked");
                else
                    System.out.println(tasks.getTaskName() + " is not fully Marked, These are the students which has not been marked\n" + studentNotMarked);
            }
        }

        assert(true);
    }

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
