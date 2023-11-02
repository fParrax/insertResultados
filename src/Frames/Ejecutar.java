/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frames;

import Clases.ConectarDBCloud;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Ejecutar {
    public static void main(String[] args) {
        
        
        try {
            String fecha = new ConectarDBCloud().tomarFechaCompleta();
            Date dFecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(fecha);
            
            String mFecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa").format(dFecha);
            
            
             System.out.println(mFecha);
        } catch (ParseException ex) {
            Logger.getLogger(Ejecutar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
       
    }
}
