package studentManager;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a set of groups within the application, with some basic group and student management. Also manages the
 * JTree rendered to the user, as a visual representation of the data it holds.
 */
public class GroupSet {
    private JTree tree;
    private DefaultMutableTreeNode root;
    private TreeSet<Group> groups;
    private TreeSet<Student> unassignedStudents;
    ArrayList allTreeNodeNameList = new ArrayList();

    public GroupSet() {
        groups = new TreeSet<>();
        unassignedStudents = new TreeSet<>();
        root = new DefaultMutableTreeNode(null);
        tree = new JTree(root);

        // Set tree properties
        tree.setRootVisible(true);
        DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer) tree
                .getCellRenderer();
        ImageIcon leaf = new ImageIcon(this.getClass().getResource("/icons/student.png"));
        ImageIcon closed = new ImageIcon(this.getClass().getResource("/icons/group.png"));
        closed.setImage(closed.getImage().getScaledInstance(24,24, Image.SCALE_SMOOTH));
        leaf.setImage(leaf.getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH));

        cellRenderer.setLeafIcon(leaf);
        cellRenderer.setClosedIcon(closed);
        cellRenderer.setOpenIcon(closed);
    }

    // returns whether the call changed anything
    public boolean addGroup(Group group) {
        if (!groups.contains(group)) {
            groups.add(group);
            refreshTree();
            return true;
        } else {
            return false;
        }
    }

    // returns whether the call changed anything
    public boolean removeGroup(Group group) {
        if (groups.contains(group)) {
            groups.remove(group);
            refreshTree();
            return true;
        } else {
            return false;
        }
    }

    // returns whether the call changed anything
    public boolean addStudent(Student student) {
        if (!unassignedStudents.contains(student)) {
            unassignedStudents.add(student);
            refreshTree();
            return true;
        } else {
            return false;
        }
    }

    // returns whether the call changed anything
    public boolean removeStudent(Student student) {
        if (unassignedStudents.contains(student)) {
            unassignedStudents.remove(student);
            refreshTree();
            return true;
        } else {
            return false;
        }
    }

    /**
     * This must be called whenever a change to a student or group has been made.
     */
    public void refreshTree() {
        // discards old nodes for every change. Wasteful, but works.
        // TODO: Refactor to edit existing nodes instead of building new ones
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Group List");

        // build nodes
        for (Group group : groups) {
            // add node for this group
            DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(group);

            root.add(groupNode);
            for (Student student : group.getStudents()) {
                DefaultMutableTreeNode studentNode = new DefaultMutableTreeNode(student);
                groupNode.add(studentNode);
            }
        }

        for (Student student : unassignedStudents) {
            DefaultMutableTreeNode studentNode = new DefaultMutableTreeNode(student);
            root.add(studentNode);
        }

        DefaultTreeModel model = (DefaultTreeModel) tree.getModel(); // get model of tree
        model.setRoot(root);
        model.reload();
    }

    public Set<Group> getGroups() {
        return new TreeSet<>(groups);
    }

    public Set<Student> getUnassignedStudents() {
        return new TreeSet<>(unassignedStudents);
    }

    public JTree getTree() {
        return tree;
    }
    public DefaultTreeModel getModel(){
    	return (DefaultTreeModel) tree.getModel();
    }
}
