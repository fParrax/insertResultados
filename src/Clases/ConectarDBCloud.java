package Clases;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp2.BasicDataSource;


public class ConectarDBCloud {
     static Connection con = null;
                                              
   // private static  String url = "jdbc:mysql://c1046.gconex.com/";
     private static  String url = "jdbc:mysql://capsperu.dyndns.org/";
    String user2 ="sp" ;
    String pass2 ="devSoft23#" ;
     
   
    
    static BasicDataSource ds = new BasicDataSource();
    Properties propiedades = new Properties();
    static DataSource dataSource;

        public ConectarDBCloud() {
          // Propiedades p = new Propiedades();
          // p.openPropiedades();
          //  this.url = new AES256().decrypt(p.getPropiedad("url"))+"/";
          //  this.user2 = new AES256().decrypt(p.getPropiedad("user"));
          //  this.pass2 = new AES256().decrypt(p.getPropiedad("password"));
           
    }

    public  ConectarDBCloud(String dataBase) {
            try {
                ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
                ds.setUsername(user2);
                ds.setPassword(pass2);
                ds.setUrl(url+dataBase);
                ds.setInitialSize(1);
                ds.setMaxTotal(2);
                ds.setTestWhileIdle(true);
                ds.setTimeBetweenEvictionRunsMillis(10000);
                ds.setValidationQuery("select 1");
            } catch (Exception ex) {
                Logger.getLogger(ConectarDBCloud.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error al Conectar con la base de Datos\nPor favor verifique su conexión a internet o contacte al administrador." + ex);
            }
        
    }

    
    
    public BasicDataSource getDataSource() {
        return ds;
    }

    public static DataSource getDataSourcePROPERTIES() throws FileNotFoundException, IOException, Exception {
        return ds;

    }

    public Connection getCon() {
         try {
             //con = getDataSourcePROPERTIES().getConnection();
             con = getDataSource().getConnection();
             return con;
         } catch (SQLException ex) {
             Logger.getLogger(ConectarDBCloud.class.getName()).log(Level.SEVERE, null, ex);
             return null;
         }
    }

    public static Connection getCon2() throws SQLException, Exception {
        con = getDataSourcePROPERTIES().getConnection();
        return con;
    }


    public  String tomarFecha() {
        String fecha = "";
        try (Connection conex = new ConectarDBCloud("ag").getCon()) {
                
            String sql = "select replace(CURDATE(),'/','-') as a";
            PreparedStatement s = conex.prepareStatement(sql);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                fecha = rs.getString("a");
            }
            try {
                if (s != null) {
                    s.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConectarDBCloud.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            Logger.getLogger(ConectarDBCloud.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, e);
        }

        return fecha;
    }
 public  String tomarFechaCompleta() {
        String fecha = "";
        try (Connection conex = new ConectarDBCloud("ag").getCon()) {
                
            String sql = "select replace(NOW(),'/','-') as a";
            PreparedStatement s = conex.prepareStatement(sql);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                fecha = rs.getString("a");
            }
            try {
                if (s != null) {
                    s.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConectarDBCloud.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            Logger.getLogger(ConectarDBCloud.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, e);
        }

        return fecha;
    }
    public int Desconectar() {
        int a = 0;
        try {
            con.close();
            a = 1;
        } catch (SQLException ex) {
            Logger.getLogger(ConectarDBCloud.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, null, 0);
            a = 0;
        }
        return a;
    }
    
}
