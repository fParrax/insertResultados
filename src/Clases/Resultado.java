
 
package Clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Resultado {

    int id=0;
    String fecha,programa,animal,sorteo,estado;
    String sql;
    PreparedStatement pst;
    ResultSet rs;
    public Resultado() {
    }

    public Resultado(int id, String fecha, String programa,  String sorteo,String animal,String estado) {
        this.id = id;
        this.fecha = fecha;
        this.programa = programa;
        this.animal = animal;
        this.sorteo = sorteo;
        this.estado=estado;
    }
    public Resultado(String fecha, String programa,  String sorteo,String animal) {
        this.fecha = fecha;
        this.programa = programa;
        this.animal = animal;
        this.sorteo = sorteo;
    }
    
    
    public int insert(){
        int rsp=0;
        
         try (java.sql.Connection con = new ConectarDBCloud("ag").getCon()) {
             con.setCatalog("ag");
             sql="call `sp.InsertSorteo` (?,?,?,?)";
             pst = con.prepareStatement(sql);
             pst.setString(1,this.fecha);
             pst.setString(2,this.programa);
             pst.setString(3,this.animal);
             pst.setString(4,this.sorteo);
             rsp = pst.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(Resultado.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Error con el manejo de base de datos, contacte con el adm.\n" + e);
        } finally {
            cerrar();
        }
        
        return rsp;
    }
    
    public ArrayList getResultados(String fecha01, String fecha02){
        ArrayList<Resultado> resuls = new ArrayList();
        try (java.sql.Connection con = new ConectarDBCloud("ag").getCon()) {
            con.setCatalog("ag");
             sql ="call `sp.getResultados` (?,?)";
             pst = con.prepareStatement(sql);
             pst.setString(1,fecha01);
             pst.setString(2,fecha02);
             rs = pst.executeQuery();
             while(rs.next()){
                 System.out.println("x");
                 resuls.add(new Resultado(rs.getInt("idresultado"),rs.getString("fechaSorteo"),rs.getString("programa"),
                 rs.getString("sorteo"),rs.getString("animal"),rs.getString("estado")));
             }
        } catch (Exception e) {
            Logger.getLogger(Resultado.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Error con el manejo de base de datos, contacte con el adm.\n" + e);
        } finally {
            cerrar();
        }
        return resuls;
    }
     
    
    
    @Override
    public int hashCode() {
        return Objects.hash(id, fecha,  programa,animal,sorteo,estado );
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Resultado t = (Resultado) obj;

        return Float.compare(id, t.id) == 0 && fecha.equals(t.fecha) && animal.equals(t.animal) &&
               programa.equals(t.programa) && sorteo.equals(t.sorteo) && estado.equals(t.estado);
    }
    private void cerrar() {
        try {
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Resultado.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return "Resultado{" + "id=" + id + ", fecha=" + fecha + ", programa=" + programa + ", animal=" + animal + ", sorteo=" + sorteo + ", estado=" + estado + '}';
    }

    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getSorteo() {
        return sorteo;
    }

    public void setSorteo(String sorteo) {
        this.sorteo = sorteo;
    }
    
    
    
    
    
    
    
}
