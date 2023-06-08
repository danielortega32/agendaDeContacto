
package baseImg;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Daniel CAVS
 */
public class TablaImg extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
        if (o instanceof JLabel) {
            JLabel lb1 =(JLabel)o;
            return lb1;
        }
        return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1); 
    }
  
}
