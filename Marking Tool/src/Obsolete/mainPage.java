package Obsolete;

import java.awt.*;
import javax.swing.*;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import studentManager.Group;
import studentManager.GroupSet;
import studentManager.Student;

import java.awt.event.ActionEvent;

/**
 * 
 * @author Geng Feng	
 * @since 06/09/2017
 * The class represents the main page of the application
 * 
 *
 */

public class mainPage {

	public JFrame frame;
	private JPanel bottomPanel;
	private GroupSet treeContainer;
	
	/**
	 * Create the application.
	 */
	public mainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		treeContainer = new GroupSet();

		// add sample data
		Group g1 = new Group("Group A");
		g1.addStudent(new Student("12345678", "Adrian Bin"));
		g1.addStudent(new Student("29475619", "Joe Wong"));
		g1.addStudent(new Student("40971247", "Smith Ken"));
		Group g2 = new Group("Group B");
		g2.addStudent(new Student("12093817", "John Smith"));
		treeContainer.addGroup(g1);
		treeContainer.addGroup(g2);
		treeContainer.addStudent(new Student("09127361", "Joey jo"));

		// root frame
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setMinimumSize(new Dimension(640, 480));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(8, 8));

		// bottom panel
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.setMinimumSize(new Dimension(192, 48));
		bottomPanel.setMaximumSize(bottomPanel.getMinimumSize());
		frame.add(bottomPanel, BorderLayout.SOUTH);

		// create and set the position of the tree
		JTree tree = treeContainer.getTree();
		frame.add(tree, BorderLayout.LINE_START);

		// create group dropdown
		JComboBox<Group> groupList = new JComboBox<>();
		for (Group group : treeContainer.getGroups()) {
			groupList.addItem(group);
		}
		groupList.setMinimumSize(new Dimension(192, 24));
		groupList.setMaximumSize(groupList.getMinimumSize());
		groupList.setAlignmentX(Component.LEFT_ALIGNMENT);
		bottomPanel.add(groupList);

		// create button to add student to group
		JButton studentGroupButton = new JButton("Add Selected Student");
		studentGroupButton.setMinimumSize(new Dimension(192, 24));
		studentGroupButton.setMaximumSize(studentGroupButton.getMinimumSize());
		studentGroupButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		bottomPanel.add(studentGroupButton);
		studentGroupButton.addActionListener( (ActionEvent e) -> {

			DefaultMutableTreeNode selected = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (selected != null && selected.getUserObject() instanceof Student) {
				Student student = (Student) selected.getUserObject();
				if (treeContainer.getUnassignedStudents().contains(student) && groupList.getSelectedItem() instanceof Group) {
					// unassigned student selected, and valid group selected; assign this student and remove from unassigned
					Group group = (Group) groupList.getSelectedItem();
					group.addStudent(student);
					treeContainer.removeStudent(student); // will also refresh the tree
				}
			}

		});
	}
}
