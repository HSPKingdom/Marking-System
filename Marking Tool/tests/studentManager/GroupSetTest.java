package studentManager;

import org.junit.Test;

import static org.junit.Assert.*;

public class GroupSetTest {
    private Student student1 = new Student("12345678", "FName LName");
    private Student student2 = new Student("39471946", "First Last");

    @Test
    public void addGroup() throws Exception {
        GroupSet groupSet = new GroupSet();
        Group group = new Group("Test Group");
        group.addStudent(student1);


        assertFalse(groupSet.getGroups().contains(group));
        groupSet.addGroup(group);
        assertTrue(groupSet.getGroups().contains(group));

    }

    @Test
    public void removeGroup() throws Exception {
        GroupSet groupSet = new GroupSet();
        Group group = new Group("Test Group");
        Group group2 = new Group("Test Group 2");
        group.addStudent(student1);
        groupSet.addGroup(group);
        groupSet.addGroup(group2);

        groupSet.removeGroup(group);

        assertFalse(groupSet.getGroups().contains(group));
        assertTrue(groupSet.getGroups().contains(group2));
    }

    @Test
    public void addStudent() throws Exception {
        GroupSet groupSet = new GroupSet();

        assertFalse(groupSet.getUnassignedStudents().contains(student1));
        groupSet.addStudent(student1);
        assertTrue(groupSet.getUnassignedStudents().contains(student1));
    }

    @Test
    public void removeStudent() throws Exception {
        GroupSet groupSet = new GroupSet();
        groupSet.addStudent(student1);

        assertTrue(groupSet.getUnassignedStudents().contains(student1));
        groupSet.removeStudent(student1);
        assertFalse(groupSet.getUnassignedStudents().contains(student1));
    }

    @Test
    public void getGroups() throws Exception {
        GroupSet groupSet = new GroupSet();
        Group group = new Group("Test Group");
        Group group2 = new Group("Test Group 2");
        group.addStudent(student1);
        groupSet.addGroup(group);
        groupSet.addGroup(group2);

        assertTrue(groupSet.getGroups().contains(group));
        assertTrue(groupSet.getGroups().contains(group2));
        groupSet.removeGroup(group);
        assertFalse(groupSet.getGroups().contains(group));
        assertTrue(groupSet.getGroups().contains(group2));
    }

    @Test
    public void getUnassignedStudents() throws Exception {
        GroupSet groupSet = new GroupSet();
        groupSet.addStudent(student1);
        groupSet.addStudent(student2);

        assertTrue(groupSet.getUnassignedStudents().contains(student1));
        assertTrue(groupSet.getUnassignedStudents().contains(student2));
        groupSet.removeStudent(student1);
        assertFalse(groupSet.getUnassignedStudents().contains(student1));
        assertTrue(groupSet.getUnassignedStudents().contains(student2));
    }

}