package studentManager;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class GroupTest {
    private Student student1 = new Student("12345678", "FName LName");
    private Student student2 = new Student("39471946", "First Last");
    private Student student3 = new Student("10394715", "Fore Surname");

    // Add student
    @Test
    public void addStudent() throws Exception {
        Group group = new Group("GroupName");
        group.addStudent(student2);

        assertTrue(group.getStudents().contains(student2));
    }


    @Test
    public void removeStudent() throws Exception {
        Group group = new Group("GroupName");
        group.addStudent(student3);
        group.removeStudent(student3);
        assertFalse(group.getStudents().contains(student2));
    }

    @Test
    public void hasStudent() throws Exception {
        Group group = new Group("GroupName");
        group.addStudent(student1);
        group.addStudent(student2);

        assertTrue(group.hasStudent(student1));
        assertTrue(group.hasStudent(student2));
        assertFalse(group.hasStudent(student3));
    }


    @Test
    public void getName() throws Exception {
        Group group = new Group("GroupName");
        assertTrue("GroupName".equals(group.getName()));
    }

    @Test
    public void getStudents() throws Exception {
        Group group = new Group("GroupName");
        group.addStudent(student1);
        group.addStudent(student3);

        assertTrue(group.getStudents().containsAll(Arrays.asList(student1, student3)));
    }

    @Test
    public void testToString() throws Exception {
        Group group = new Group("GroupName");
        group.addStudent(student1);
        group.addStudent(student3);
        assertTrue(group.toString().equals("GroupName"));

        Group group2 = new Group("GroupTwo");
        assertTrue(group2.toString().equals("GroupTwo"));
    }

}