package studentManager;     
  
import java.awt.*;   
import javax.swing.tree.DefaultMutableTreeNode;   
import javax.swing.tree.DefaultTreeModel;   
import javax.swing.tree.TreePath;   
import javax.swing.*;   
import java.awt.BorderLayout;   
import java.awt.event.ActionEvent;   
import java.awt.event.ActionListener;   
import java.awt.dnd.DragSource;   
import java.awt.dnd.DropTarget;   
import java.awt.dnd.DnDConstants;   
import javax.swing.event.TreeSelectionListener;   
import javax.swing.event.TreeSelectionEvent;   
import java.awt.dnd.DragGestureListener;   
import java.awt.dnd.DragGestureEvent;   
import java.awt.dnd.DragSourceContext;   
import java.awt.dnd.DragSourceListener;   
import java.awt.dnd.DragSourceEvent;   
import java.awt.dnd.DragSourceDragEvent;   
import java.awt.dnd.DragSourceDropEvent;   
import java.util.ArrayList;   
 /**
  *  This is first page of the program which contains groups of students. 
  * @author Geng Feng
  *
  */
public class GroupPanel extends JPanel implements TreeSelectionListener{
	public JFrame frame;
	private JPanel bottomPanel;
	private GroupSet treeContainer;
    JPanel contentPane;   
    BorderLayout borderLayout1 = new BorderLayout();   
    
    
   // node = new DefaultMutableTreeNode("根目录 ");   
    //DefaultTreeModel model = new DefaultTreeModel(node);   
    //JTree tree = new JTree(model);  
    DefaultTreeModel model; 
    JTree tree; 
    JButton btDel = new JButton();   
    JButton btAdd = new JButton();
    JButton btShowUnmarked = new JButton();
    JPanel jPanel1 = new JPanel();   
    GridBagLayout gridBagLayout1 = new GridBagLayout();   
    //draggable Node 
    DefaultMutableTreeNode dragTreeNode = null;   
    //树的所有树节点名称   
    ArrayList allTreeNodeNameList = new ArrayList();   
  
    public GroupPanel() {
        try {
            jbInit();   
        } catch (Exception exception) {   
            exception.printStackTrace();   
        }   
    }   
  
    /**  
     *   Component   initialization.  
     *  
     *   @throws   java.lang.Exception  
     */  
    private void jbInit() throws Exception { 
    	
    	
		
		
        contentPane = this;
        contentPane.setLayout(borderLayout1);
        btShowUnmarked.setText("Show Unmarked Assessment");
        btShowUnmarked.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, showAllUnmardedStudent());
            }
        });
        btDel.setText("Delete ");   
        btDel.addActionListener(new Frame1_jButton1_actionAdapter(this));   
        btAdd.setText("Add ");   
        btDel.setEnabled(false);   
        btAdd.setEnabled(false);   
        initalTree();   
        btAdd.addActionListener(new Frame1_jButton2_actionAdapter(this));   
        jPanel1.setLayout(gridBagLayout1);   
        contentPane.add(tree, java.awt.BorderLayout.CENTER);   
        contentPane.add(jPanel1, java.awt.BorderLayout.SOUTH);   
  
        jPanel1.add(btDel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0  
                                                  , GridBagConstraints.EAST,   
                                                  GridBagConstraints.NONE,   
                                                  new Insets(5, 5, 5, 5), 0, 0));
        jPanel1.add(btShowUnmarked, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
                , GridBagConstraints.EAST,
                GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0));
        jPanel1.add(btAdd, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0  
                                                  , GridBagConstraints.EAST,   
                                                  GridBagConstraints.NONE,   
                                                  new Insets(5, 5, 5, 5), 0, 0));   
        
        DragSource dragSource = DragSource.getDefaultDragSource();   
        //Set data source of drop data and also add listener   
        dragSource.createDefaultDragGestureRecognizer(tree,   
                DnDConstants.ACTION_MOVE, new DragAndDropDragGestureListener());   
        //set Objective jtr and add listener  
        DropTarget dropTarget = new DropTarget(tree,   
                                               new  
                                               DragAndDropDropTargetListener());   
  
    }   
  
    /**  
     * 初始化树  
     */  
    private void initalTree() {  
    	
    	
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
		tree = treeContainer.getTree();
		model = treeContainer.getModel();
		allTreeNodeNameList.add("Group A");
		allTreeNodeNameList.add("Group B");
		
        
        //Add Listener to the Tree  
        tree.addTreeSelectionListener(this);   
    }   
  
    public void jButton1_actionPerformed(ActionEvent e) {   
        DefaultMutableTreeNode selected = (DefaultMutableTreeNode) tree.   
                                          getLastSelectedPathComponent();   
        if (selected != null) {   
            model.removeNodeFromParent(selected);   
        }   
    }   
  
    public void jButton2_actionPerformed(ActionEvent e) {   
        String name = javax.swing.JOptionPane.showInputDialog(this, "input node name: ");   
        if (name == null || name.equals(" ")) {   
            return;   
        }   
        if (allTreeNodeNameList.contains(name.trim())) {// node name cannot repeat   
                 
         JOptionPane.showMessageDialog(this, "The name of node exits！","error",   
                                       JOptionPane.ERROR_MESSAGE);   
         return;   
  
        }   
        DefaultMutableTreeNode selected = (DefaultMutableTreeNode) tree.   
                                          getLastSelectedPathComponent();   
        if (selected == null) {   
            return;   
        }   
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(name);   
        // add to the node
        model.insertNodeInto(node, selected, 0);   
        TreePath path = new TreePath(node.getPath());   
        tree.makeVisible(path);   
    }

     // showUnmarkedStudent() btShowUnmarked
     public String showAllUnmardedStudent()
     {
         AssessmentForm template = new AssessmentForm();
         String output = template.showAllUnmardedStudent();
         return output;
     }
      
  
    public void valueChanged(TreeSelectionEvent e) {   
        TreePath path = e.getPath();   
        if (path == null) {   
            btAdd.setEnabled(false);   
            btDel.setEnabled(false);   
            return;   
        }   
        if (((DefaultMutableTreeNode) path.getLastPathComponent()).isRoot()) {   
            btAdd.setEnabled(false);   
            btDel.setEnabled(false);   
        }   
        btAdd.setEnabled(true);   
        btDel.setEnabled(true);   
  
    }   
  
    /**  
     * get dragged node. 
     * @return DefaultMutableTreeNode  
     */  
    public DefaultMutableTreeNode getDragTreeNode(){   
        return dragTreeNode;   
    }   
       
    /**  
     * set dragged node 
     */  
    public void setDragTreeNode(DefaultMutableTreeNode dragTreeNode){   
        this.dragTreeNode = dragTreeNode;   
    }   
       
    /**  
     * get tree  
     * @return JTree  
     */  
    public JTree getTree(){   
       return tree;    
    }   
  
    //since the Jtree is both the source of the dragging and the destination of the dragging, 
    //so we make a inner class DragGestureListener  
    class DragAndDropDragGestureListener implements DragGestureListener {   
        public void dragGestureRecognized(DragGestureEvent dge) {   
            JTree tree = (JTree) dge.getComponent();   
            TreePath path = tree.getSelectionPath();   
            if (path != null) {   
                dragTreeNode = (DefaultMutableTreeNode)   
                        path.getLastPathComponent();   
               if (dragTreeNode!= null) {   
                   DragAndDropTransferable dragAndDropTransferable = new  
                           DragAndDropTransferable(dragTreeNode);   
               
                   dge.startDrag(DragSource.DefaultMoveDrop,   
                                 dragAndDropTransferable,   
                                 new DragAndDropDragSourceListener());   
               }   
            }   
        }   
    }   
       
       
       
    class DragAndDropDragSourceListener implements DragSourceListener {   
  
   
    /**  
     * Being executed When the cursor goes in the component   
     * @param e DragSourceDragEvent  
     */  
    public void dragEnter(DragSourceDragEvent e) {   
        //set Cursor
        DragSourceContext context = e.getDragSourceContext();   
        int dropAction = e.getDropAction();   
        if ((dropAction & DnDConstants.ACTION_COPY) != 0) {   
            context.setCursor(DragSource.DefaultCopyDrop);   
        } else if ((dropAction & DnDConstants.ACTION_MOVE) != 0) {   
            context.setCursor(DragSource.DefaultMoveDrop);   
        } else {   
            context.setCursor(DragSource.DefaultCopyNoDrop);   
        }   
    }   
  
    /**  
     * event happens when the cursor exit from the component  
     * @param e DragSourceEvent  
     */  
    public void dragExit(DragSourceEvent e) {}   
  
    /**  
     * Executed when the cursor move after going into component.   
     * @param e DragSourceDragEvent  
     */  
    public void dragOver(DragSourceDragEvent e) {}   
  
    
    public void dropActionChanged(DragSourceDragEvent e) {}   
  
    
    public void dragDropEnd(DragSourceDropEvent e) {   
        
        if (!e.getDropSuccess()||e.getDropAction()!= DnDConstants.ACTION_MOVE) {   
            return;   
        }   
  
        DragSourceContext context = e.getDragSourceContext();   
        Object comp = context.getComponent();   
        if (comp==null||!(comp instanceof JTree)) {   
            return ;   
        }   
        DefaultMutableTreeNode dragTreeNode = getDragTreeNode();   
           
        if (dragTreeNode!=null) {   
            ((DefaultTreeModel) tree.getModel()).removeNodeFromParent(dragTreeNode);   
            //Set the dragged node Null
            setDragTreeNode(null);   
        }   
  
    }   
}   
  
  
  
    
}   
  
  
  
class Frame1_jButton2_actionAdapter implements ActionListener {   
    private GroupPanel adaptee;
    Frame1_jButton2_actionAdapter(GroupPanel adaptee) {
        this.adaptee = adaptee;   
    }   
  
    public void actionPerformed(ActionEvent e) {   
        adaptee.jButton2_actionPerformed(e);   
    }   
}   
  
  
class Frame1_jButton1_actionAdapter implements ActionListener {   
    private GroupPanel adaptee;
    Frame1_jButton1_actionAdapter(GroupPanel adaptee) {
        this.adaptee = adaptee;   
    }   
  
    public void actionPerformed(ActionEvent e) {   
        adaptee.jButton1_actionPerformed(e);   
    }   
}  