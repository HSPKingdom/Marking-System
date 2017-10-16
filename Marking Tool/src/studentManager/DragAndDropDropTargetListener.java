package studentManager;   
  
import java.awt.Component;   
import java.awt.datatransfer.Transferable;   
import java.awt.dnd.*;   
  
import javax.swing.JTree;   
import javax.swing.tree.*;   
  
//* Drop Target Listener */   
public class DragAndDropDropTargetListener implements DropTargetListener {   
  
  
    public void dragEnter(DropTargetDragEvent e) {   
    }   
  
   
    public void dragOver(DropTargetDragEvent e) {   
    }   
  
     
    public void dropActionChanged(DropTargetDragEvent e) {}   
  
    
    public void dragExit(DropTargetEvent e) {}   
  
  
   
    public void drop(DropTargetDropEvent e) {   
           
        Transferable transfer = e.getTransferable();   
        DefaultMutableTreeNode dragSource = null;   
           
        if (transfer.isDataFlavorSupported(DragAndDropTransferable.TREENODE_FLAVOR)) {   
            try {   
                 
                e.acceptDrop(DnDConstants.ACTION_MOVE);   
                  
                dragSource = (DefaultMutableTreeNode) transfer.getTransferData(   
                        DragAndDropTransferable.TREENODE_FLAVOR);   
            } catch (Exception ex) {   
                ex.printStackTrace();   
            }   
        }   
        if (dragSource == null) {    
              
            e.dropComplete(false);   
            return;   
        }   
           
        DropTarget dt = (DropTarget) e.getSource();   
        Component comp = dt.getComponent();   
        if (!(comp instanceof JTree)) {   
              
            e.dropComplete(false);   
            return;   
        }   
        JTree jtr = (JTree) comp;   
        TreePath treePath = jtr.getPathForLocation(e.getLocation().x,e.getLocation().y);   
        if (treePath==null) {   
              
            e.dropComplete(false);   
            return;   
        }   
        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();   
        if (!isCanDrop(dragSource,treeNode,jtr)) {   
            
            e.dropComplete(false);   
            return;   
        }   
  
      
        treeNode.add(dragSource);   
        
        ((DefaultTreeModel)jtr.getModel()).reload(treeNode);   
  
          
        e.dropComplete(true);   
    }   
  
   
    public boolean isCanDrop(DefaultMutableTreeNode dragTreeNode,   
                             DefaultMutableTreeNode dropTreeNode, JTree jtr) {   
        if (dragTreeNode == null) {  
            return false;   
        }   
        
        if (dropTreeNode == null ) {   
            return false;   
        }   
          
        if (dragTreeNode==dropTreeNode) {   
         return false;   
        }   
       TreePath dragPath = new TreePath (((DefaultTreeModel )jtr.getModel()).getPathToRoot(dragTreeNode));   
       TreePath dropPath = new TreePath (((DefaultTreeModel )jtr.getModel()).getPathToRoot(dropTreeNode));   
       String strDragPath = dragPath.toString();   
       String strDropPath = dropPath.toString();   
       String subDragPath = strDragPath.substring(0,strDragPath.length()-1);   
  
       if (strDropPath.startsWith(subDragPath)) {  
           return false;   
       }   
       if ( dragPath.getParentPath().toString().equals(strDropPath)) {   
           return false;   
       }   
  
         
       if (dragTreeNode.getParent().equals(dropTreeNode)) {   
           return false;   
       }   
  
       return true;   
    }   
}  