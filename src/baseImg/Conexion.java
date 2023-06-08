package baseImg;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Daniel CAVS
 */
public class Conexion {
  //Conexion a base de datos
    public Connection conectar() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/imagenes";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            System.out.println("en linea");
        } catch (Exception ex) {
            System.out.println("error");
           
        }
        return con;

    }

    public ResultSet visualizar() {
        Connection con = conectar();
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareStatement("select * from usuario ");
            rs = ps.executeQuery();
        } catch (Exception e) {
            System.out.println("error de consulta");
        }
        return rs;
    }

    public void guardarImg(String ruta, String nombre) {
        Connection con = conectar();
        String insert = "insert into usuario(nombre,foto)values(?,?)";
        FileInputStream fi = null;
        PreparedStatement ps = null;

        try {
            File file = new File(ruta);
            fi = new FileInputStream(file);

            ps = con.prepareStatement(insert);
            ps.setString(1, nombre);
            ps.setBinaryStream(2, fi);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error al guardar");
        }
    }
}
