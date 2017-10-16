package studentManager;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

// represents a group of students within the system.
public class Group implements Serializable, Comparable<Group> {
    private TreeSet<Student> members;
    private String name;

    public Group(String groupName) {
        this.members = new TreeSet<>();
        this.name = groupName;
    }

    public void addStudent(Student student) {
        members.add(student);
    }

    public void removeStudent(Student student) {
        members.remove(student);
    }

    public boolean hasStudent(Student student) {
        return members.contains(student);
    }

    public String getName() {
        return this.name;
    }

    public Set<Student> getStudents() {
        return new TreeSet<>(members);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Group other) {
        return this.name.compareTo(other.getName()); // sort based on name
    }
}