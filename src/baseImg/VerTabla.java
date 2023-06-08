package baseImg;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Daniel CAVS
 */
public class VerTabla {

    public void visualizarTabla(JTable tabla) {
        Conexion conn = new Conexion();
        Connection con = conn.conectar();
        ResultSet rs = conn.visualizar();
        tabla.setDefaultRenderer(Object.class, new TablaImg());
        DefaultTableModel dt = new DefaultTableModel();
        dt.addColumn("Nombre");
        dt.addColumn("Foto");

        try {
            while (rs.next()) {
                Object[] fila = new Object[2];
                fila[0] = rs.getObject(2);
                Blob blob = rs.getBlob(3);
                if (blob != null) {
                    try {

                        byte[] data = blob.getBytes(1, (int) blob.length());
                        BufferedImage img = null;

                        try {
                            img = ImageIO.read(new ByteArrayInputStream(data));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        ImageIcon icon = new ImageIcon(img);
                        fila[1] = new JLabel(icon);
                    } catch (Exception e) {
                        fila[1]="no imagen";
                    }
                }
                else{
                fila[1]="no imagen";
                }
                    dt.addRow(fila);

                
            }
            tabla.setModel(dt);
            tabla.setRowHeight(64);

        } catch (Exception e) {
            System.out.println("error al visualizar en la tabla");
        }
    }
}
