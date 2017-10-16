package studentManager;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class StudentTest {

    private Student student1 = new Student("12345678", "FName LName");
    private Student student2 = new Student("39471946", "First Last");

    @Test
    public void testIDStudent()
    {
        assertTrue("12345678".compareTo(student1.getsID())==0);
        student1.setsID("23456789");
        assertTrue("23456789".compareTo(student1.getsID())==0);

    }
    @Test
    public void testNameStudent()
    {
        assertTrue("First Last".compareTo(student2.getName())==0);
        student2.setName("Hello");
        assertTrue("Hello".compareTo(student2.getName())==0);
    }
    @Test
    public void testEqual()
    {
        assertTrue(student1.equals(student1));
        assertFalse(student2.equals(student1));
    }

    @Test
    public void testCompareTo()
    {
        assertTrue(student1.compareTo(student2)<0);
        assertTrue(student1.compareTo(student1) == 0);
    }


}
