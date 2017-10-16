package studentManager;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AssessmentTaskTest {
    private AssessmentTask Task1 = new AssessmentTask("Midterm Test", "50% of total", 50, 50);

    @Test
    public void AssessmentTaskGetterTest()
    {
        assertTrue(Task1.getTaskName() == "Midterm Test");
        assertTrue(Task1.getTaskDescribtion() == "50% of total");
        assertTrue(Task1.getTotalMark() == 50);
    }


    @Test
    public void AssessmentArrayAddTasks()
    {
        ArrayList<Assessment> assessments = new ArrayList<>();
        Assessment assignment = new Assessment("Assignment 1", 15, 100, "Testing");
        assessments.add(assignment);

        int index = -1;
        for(Assessment temp : assessments) {
            if (temp.getTitle().compareTo("Assignment 1") == 0) {
                index = assessments.indexOf(temp);
                System.out.println("Found");
                break;
            }
        }

        assessments.get(index).addTask("Task1", "Add Task", 30, 30);
        System.out.println(assessments.get(index).Tasks.toString());

    }

    @Test
    public void AssessmentTaskSetterTest()
    {
        AssessmentTask TaskTemp = new AssessmentTask("Task", "10%", 10,10);

        TaskTemp.setTaskName("Task2");
        TaskTemp.setTaskDescribtion("20%");
        TaskTemp.setTotalMark(20);

        assertTrue(TaskTemp.getTaskName() == "Task2");
        assertTrue(TaskTemp.getTaskDescribtion() == "20%");
        assertTrue(TaskTemp.getTotalMark() == 20);
    }

    @Test
    public void AddMarkTest()
    {
        AssessmentTask Temp = new AssessmentTask("Task", "30%", 30,30);

        Temp.addMark("12345", 30);
        assertTrue(Temp.searchMarks("12345") == 30);
    }

    @Test
    public void RemoveMarkTest()
    {
        AssessmentTask Temp = new AssessmentTask("Task", "30%", 30, 30);

        Temp.addMark("12345", 30);
        Temp.removeMark("12345");
        assertTrue(Temp.searchMarks("12345") == -1);
    }
}
