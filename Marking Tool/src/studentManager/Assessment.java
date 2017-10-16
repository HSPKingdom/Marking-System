package studentManager;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class Assessment {

    private String title;       // Title of the assessment
    private int percentage;     // Percentage weight to the whole subject
    private int totalMark;      // Total marks of the assessment
    private String description; // Description of the assessment
    ArrayList<AssessmentTask> Tasks = new ArrayList<>();    // Each individual Tasks of the assessment

    /**
     * All the constructor getter and setter can be delete if not used after the model is built
     * @author Adrian Bin
     */
    // =========================================Constructor============================================

    /**
     * Constructor for the Assessment
     * @param title
     * @param percentage
     * @param describtion
     * @author Adrian Bin
     */
    public Assessment(String title, int percentage, int totalMark, String describtion) {
        this.title = title;
        this.percentage = percentage;
        this.totalMark = totalMark;
        this.description = describtion;
    }

    // ===========================================Setter===============================================

    /**
     *
     * Set all parameter from the assessment
     * @param title
     * @param percentage
     * @param totalMark
     * @param describtion
     */
    public void setAssessment(String title, int percentage, int totalMark, String describtion) {
        this.title = title;
        this.percentage = percentage;
        this.totalMark = totalMark;
        this.description = describtion;
    }

    /**
     * Setter for Title
     * @param title
     * @author Adrian Bin
     */
    public void setTitle(String title) {

        this.title = title;
    }

    /**
     * Setter for total Mark of the Assessment
     * @param totalMark
     * @author Adrian Bin
     */
    public void setTotalMark(int totalMark)
    {
        this.totalMark = totalMark;
    }

    /**
     * Setter for the total Percentage of mark count in the Unit
     * @param percentage
     * @author Adrian Bin
     */
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    /**
     * Setter for the description of the Assessment
     * @param description
     * @author Adrian Bin
     */
    public void setDescription(String description) {
        this.description = description;
    }


    // ===========================================Getter===============================================

    /**
     * Get the title of the Assessment
     * @return title (String)
     * @author Adrian Bin
     */
    public String getTitle() {

        return title;
    }

    /**
     * Get the total mark of the Assessment
     * @return totalMark (int)
     * @author Adrian Bin
     */
    public int getTotalMark()
    {
        return totalMark;
    }

    /**
     * Get the percentage of the Assessment to the Unit
     * @return percentage(int)
     * @author Adrian Bin
     */
    public int getPercentage() {
        return percentage;
    }

    /**
     * Get the description of the Assessment
     * @return description
     * @author Adrian Bin
     */
    public String getDescription() {
        return description;
    }

    public String getAssessmentTaskMarks(String taskName)
    {
        return Tasks.get(searchTask(taskName)).printMarks();
    }

    public List<AssessmentTask> getTasks() {return new ArrayList<>(Tasks);}


    // =========================================Functions==============================================

    /**
     * Search and return the array index if the taskName exist in the Assessment Task
     * @param taskName
     * @return Arraylist index of <parm>AssessmentTask</parm> if Exist
     * @return -1 if not exist in the Arraylist of <parm>AssessmentTask</parm>
     * @author Adrian Bin
     */
    public int searchTask(String taskName)
    {
        for(AssessmentTask target : Tasks)                      // Scan through the whole Tasks and return its array Index
            if(target.getTaskName().compareTo(taskName) == 0)
                return Tasks.indexOf(target);
        return -1;
    }

    /**
     * Add a new Task into the Assessment
     * @param taskName
     * @param taskDescription
     * @return true if success
     * @return false if task is already exist
     * @author Adrian Bin
     */
    public boolean addTask(String taskName, String taskDescription, float percentage, int taskMark)
    {
        if(searchTask(taskName) == -1)
        {
            AssessmentTask Temp = new AssessmentTask(taskName, taskDescription, percentage, taskMark);
            Tasks.add(Temp);
            return true;
        }

        return false;
    }

    /**
     * Delete a task from Assessment Tasks
     * @param taskName      Task Title
     * @return true if success
     * @return false if item does not exist in Arraylist of Tasks
     * @author Adrian Bin
     */
    public boolean deleteTask(String taskName)
    {
        int index = searchTask(taskName);

        if(index != -1)
        {
            Tasks.remove(index);
            return true;
        }
        else
            return false;
    }

    /**
     * Add marks into the task
     * @param taskName      Task Title
     * @param marks         Marks combination of Student and their marks
     * @return True if success
     * @return False if Task is not found
     */
    public boolean addTaskMark(String taskName, Hashtable<String, Float> marks)
    {
        int indexArray = searchTask(taskName);  // Search for array index
        if(indexArray == -1)                    // Task is not exist
            return false;

        // Scan through the whole Hashtable
        Enumeration keys = marks.keys();
        while (keys.hasMoreElements())
        {
            Object key = keys.nextElement();
            float value = marks.get(key);

            Tasks.get(indexArray).addMark(key.toString(), value);       // Add marks individually in task
        }
        return true;
    }


    /**
     * Search and record the studentID if the students marks are not given in the Tasks
     * @param taskName      tasks in the assessment
     * @param studentList   List of all the student enrolled in the subject
     * @return  Empty list if assessment is fully marked
     * @return  List of students which are not marked
     */
    public ArrayList<String> getStudentNotMarkedByTask(String taskName, ArrayList<String> studentList)
    {
        ArrayList<String> studentNotMarked = new ArrayList<>();

        for(String student: studentList)                                    // Scan through students enroll in the subject
        {
            if(Tasks.get(searchTask(taskName)).searchMarks(student) == -1)          // If their marks doesn't exist
                studentNotMarked.add(student);                                         // Add into the list
        }
        return studentNotMarked;
    }



    //==================================ToString==========================================

    @Override
    public String toString()
    {
        String output = "Title:\t" + getTitle() + "\nPercentage:\t" + getPercentage() + "\nTotal Mark:\t" + getTotalMark() + "\n" + getDescription() + "\n{";
        for(AssessmentTask target : Tasks)
        {
            output += target.toString() + "\n";
        }
        output += "}";
        return output;
    }



}
