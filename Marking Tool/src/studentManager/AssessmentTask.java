package studentManager;

import java.util.Enumeration;
import java.util.Hashtable;


/**
 * Assessment Task is individual Task in an assessment
 * A total Assessment mark is calculated by sum of the Assessment Task
 */
public class AssessmentTask {

    private String taskName;
    private String taskDescription;
    private int totalMark;
    private float percentage;
    private Hashtable<String, Float> marks = new Hashtable<String, Float>();
    private Hashtable<String, String> comments = new Hashtable<>();


    // ================================Constructor==================================

    /**
     * Constructor for Assessment Task
     * @param taskName
     * @param taskDescription
     * @author Adrian Bin
     */
    public AssessmentTask(String taskName, String taskDescription, float percentage, int totalMark)
    {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.percentage = percentage;
        this.totalMark = totalMark;
    }

    // ==================================Setter=====================================

    /**
     * Setter for marks(the whole Hash table)
     * @param marks
     * @author Adrian Bin
     */
    public void setMarks(Hashtable<String, Float> marks) {
        this.marks = marks;
    }

    /**
     * Set the Task Name
     * @param taskName
     * @author Adrian Bin
     */
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    /**
     * Set the Task Description
     * @param taskDescribtion
     * @author Adrian Bin
     */
    public void setTaskDescribtion(String taskDescribtion)
    {
        this.taskDescription = taskDescribtion;
    }

    /**
     * Setter for total mark
     * @param totalMark
     * @author Adrian Bin
     */
    public void setTotalMark(int totalMark)
    {
        this.totalMark = totalMark;
    }

    /**
     * Setter for percentage
     * @param percentage
     * @author Adrian Bin
     */
    public void setPercentage(float percentage)
    {
        this.percentage = percentage;
    }

    // ==================================Getter=====================================

    /**
     * Get the whole marks (HashTable)
     * @return HashTable of all the student's marks
     * @author Adrian Bin
     */
    public Hashtable<String, Float> getMarks() {
        return marks;
    }

    /**
     * Get the Tasks Name
     * @return taskName(String)
     * @author Adrian Bin
     */
    public String getTaskName()
    {
        return taskName;
    }

    /**
     * Get the task description
     * @return taskDescription (String)
     * @author Adrian Bin
     */
    public String getTaskDescribtion()
    {
        return taskDescription;
    }

    /**
     * Get the total Mark
     * @return totalMark(int)
     * @author Adrian Bin
     */
    public int getTotalMark()
    {
        return totalMark;
    }

    /**
     * Get the percentage of the assessment Task
     * @return percentage
     * @author Adrian Bin
     */
    public float getPercentage()
    {
        return percentage;
    }


    // ================================Functions====================================

    /**
     * Add Mark into the HashTable <parm>marks</parm>
     * @param studentID
     * @param score
     * @return true if success
     * @return false if <parm>studentID</parm> is already exist
     * @author Adrian Bin
     */
    public boolean addMark(String studentID, float score)
    {
        if(marks.containsKey(studentID)==false)
        {
            marks.put(studentID, score);
            return true;
        }
        else {
            marks.replace(studentID, score);
            return false;
        }
    }

    public boolean addComment(String studentID, String comment)
    {
        if(comments.containsKey(studentID)==false)
        {
            comments.put(studentID, comment);
            return true;
        }
        else {
            comments.replace(studentID, comment);
            return false;
        }
    }


    /**
     * Remove a student's mark in the HashTable <parm>marks</parm>
     * @param studentID
     * @return true if success
     * @return false if record does not exist
     * @author Adrian Bin
     */
    public boolean removeMark(String studentID)
    {
        if(marks.containsKey(studentID)==true)
        {
            marks.remove(studentID);
            return true;
        }
        else
            return false;
    }


    /**
     * Get the marks according to the student ID
     * @param studentID
     * @return marks if found
     * @return '-1' if record is not found
     * @author Adrian Bin
     */
    public float searchMarks(String studentID)
    {
        if(marks.containsKey(studentID))
        {
            return marks.get(studentID);
        }
        else
            return -1;
    }

//=========================================ToString=====================================


    public String printDetail()
    {
        String printLine = "Task " + taskName + ": \n";

        Enumeration keys = marks.keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            float value = marks.get(key);
            printLine += "StudentID: "+key + ":\tMarks: " + value + "\tComments: " + comments.get(key) + "\n";
        }
        return printLine;

    }
    /**
     * Print out all the marks from the Task
     * @return String of output
     * @author Adrian Bin
     */
    public String printMarks()
    {
        String printLine = "";

        Enumeration keys = marks.keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            float value = marks.get(key);
            printLine += key + ":\t" + value + "\n";
        }
        return printLine;
    }

    @Override
    public String toString()
    {
        String output = "\n\tTask Name:\t\t" + getTaskName() + "\n\tTotal Marks:\t" + getTotalMark() + "\n\tDescription:\t" + getTaskDescribtion();
        return output;
    }


}
