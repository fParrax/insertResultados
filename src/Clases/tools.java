package Clases;

import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import java.nio.charset.*;
import java.util.*;
public class tools {

//    Calendar c1 = Calendar.getInstance();

    
    public tools(){
        
    }
    //LISTADO DE JARS A IMPORTAR
    /*
        substance-4.3 - CAMBIAR LOOK AND FEEL
        commons-codec-1.13 - para Cifrar y Descifrar
     */
    
    public String getMesString() {
        return new SimpleDateFormat("MMMM").format(new Date());
    }
    public int getMesInteger() {
        
        String c = new SimpleDateFormat("MM").format(new Date());
        System.out.println(c);
        int v = Integer.valueOf(c);
        return v;
    }
    
    public  String getRandomInteger(int i){ 
    
        // bind the length 
         byte[] bytearray = new byte[256];         
        //byte[] bytearray;
        String mystring;
        StringBuffer thebuffer;
        String theAlphaNumericS;

        new Random().nextBytes(bytearray); 

        mystring 
            = new String(bytearray, Charset.forName("UTF-8")); 
            
        thebuffer = new StringBuffer();
        
        //remove all spacial char 
        theAlphaNumericS 
            = mystring 
                .replaceAll("[^0-9]", ""); // .replaceAll("[^A-Z0-9]", ""); 

        //random selection
        for (int m = 0; m < theAlphaNumericS.length(); m++) { 

            if (Character.isLetter(theAlphaNumericS.charAt(m)) 
                    && (i > 0) 
                || Character.isDigit(theAlphaNumericS.charAt(m)) 
                    && (i > 0)) { 

                thebuffer.append(theAlphaNumericS.charAt(m)); 
                i--; 
            } 
        } 

        // the resulting string 
        return thebuffer.toString(); 
    }
    public  String getRandomString(int i){ 
    
        // bind the length 
         byte[] bytearray = new byte[256];         
        //byte[] bytearray;
        String mystring;
        StringBuffer thebuffer;
        String theAlphaNumericS;

        new Random().nextBytes(bytearray); 

        mystring 
            = new String(bytearray, Charset.forName("UTF-8")); 
            
        thebuffer = new StringBuffer();
        
        //remove all spacial char 
        theAlphaNumericS 
            = mystring 
                .replaceAll("[^A-Z]", ""); // .replaceAll("[^A-Z0-9]", ""); 

        //random selection
        for (int m = 0; m < theAlphaNumericS.length(); m++) { 

            if (Character.isLetter(theAlphaNumericS.charAt(m)) 
                    && (i > 0) 
                || Character.isDigit(theAlphaNumericS.charAt(m)) 
                    && (i > 0)) { 

                thebuffer.append(theAlphaNumericS.charAt(m)); 
                i--; 
            } 
        } 

        // the resulting string 
        return thebuffer.toString(); 
    } 
    public  String getRandomMixString(int i){ 
    
        // bind the length 
         byte[] bytearray = new byte[256];         
        //byte[] bytearray;
        String mystring;
        StringBuffer thebuffer;
        String theAlphaNumericS;

        new Random().nextBytes(bytearray); 

        mystring 
            = new String(bytearray, Charset.forName("UTF-8")); 
            
        thebuffer = new StringBuffer();
        
        //remove all spacial char 
        theAlphaNumericS 
            = mystring 
                .replaceAll("[^A-Z0-9]", ""); // .replaceAll("[^A-Z0-9]", ""); 

        //random selection
        for (int m = 0; m < theAlphaNumericS.length(); m++) { 

            if (Character.isLetter(theAlphaNumericS.charAt(m)) 
                    && (i > 0) 
                || Character.isDigit(theAlphaNumericS.charAt(m)) 
                    && (i > 0)) { 

                thebuffer.append(theAlphaNumericS.charAt(m)); 
                i--; 
            } 
        } 

        // the resulting string 
        return thebuffer.toString(); 
    }
    
    
    public int obtenerEdad(String fnacx){
        int edad=0;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fnac;
        LocalDate ahora = LocalDate.now();
        
        fnac = LocalDate.parse(fnacx,fmt);
        Period periodo = Period.between(fnac, ahora);
        edad=periodo.getYears();
        
        return edad;
    }
    public int obtenerEdad2(String fnacx){
        int edad=0;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fnac;
        LocalDate ahora = LocalDate.now();
        
        fnac = LocalDate.parse(fnacx,fmt);
        Period periodo = Period.between(fnac, ahora);
        edad=periodo.getYears();
        
        return edad;
    }
    
    
    /**
	  * Obtenga la fecha del primer día de un año
	  * @param año
	 * @return Date
	 */
	public  Date getCurrYearFirst(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}
	
	/**
	  * Obtenga la fecha del último día de un año
	  * @param año
	 * @return Date
	 */
	public  Date getCurrYearLast(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		
		return currYearLast;
	}


    
    public static String toUTF8(String s) {
        if (s != null) {
            String ss;
            try {
                ss = new String(s.getBytes("ISO-8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace(System.out);
                ss = null;
            }
            return ss;
        } else {
            return "";
        }
    }

    public void getLlamado() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        String class2 = elements[2].getClassName();
        String method = elements[2].getMethodName();
        System.out.println("Clase: " + class2 + "   Metodo: " + method);
    }

    public void copiarAntiguo(String archivoOriginal, String nuevoArchivo) {
        File origen = new File(archivoOriginal);
        File destino = new File(nuevoArchivo);

        try {
            InputStream in = new FileInputStream(origen);
            OutputStream out = new FileOutputStream(destino);

            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            in.close();
            out.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public String Obtener_NombrePC() throws UnknownHostException {
        InetAddress datos = InetAddress.getLocalHost();
        String Hostname = null;
        try {
            Hostname = datos.getHostName();

        } catch (Exception e) {
        }
        return Hostname;
    }

    public String Obtener_ip() throws UnknownHostException {
        String ip = "";
        InetAddress datos = InetAddress.getLocalHost();
        ip = datos.getHostAddress().length() == 0 ? "0.0.0.0" : datos.getHostAddress();

        return ip;
    }

    public String sumarDiaHabilFECHA(String fechaInicial, int dias) {
        String resultado = "";

        String fechaCero = fechaInicial;
        int i = 0;
        do {
            fechaCero = sumarDiasFecha(fechaCero, 1) + "";
            if (getDiaHabil(fechaCero) == true) {
                i++;
                if (i == dias) {
                    resultado = fechaCero;
                }
            }
        } while (i < dias);

        return resultado;
    }

    public String sumarDiasFecha(String fechax, int dias) {
        Calendar calendar = Calendar.getInstance();
        try {

            Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechax);
            calendar.setTime(fecha);
            calendar.add(Calendar.DAY_OF_YEAR, dias);
        } catch (ParseException ex) {
            Logger.getLogger(tools.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }
public Date sumarDiasFechaDate(Date fechax, int dias) {
        Calendar calendar = Calendar.getInstance();
        //Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechax);
        calendar.setTime(fechax);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

public int restarMinutos(Date horaInicial, Date horaFinal) {
    int rsp=0;   
        long diff = horaFinal.getTime() - horaInicial.getTime();
        //System.out.println("dif: "+diff);
        long days = diff / (1000 * 60 );//Horas(1000 * 60*60 )//dias(1000 * 60*60*24 )
        rsp =(int)days;
        return rsp;
    }

    public boolean getDiaHabil(String fechax) {
        boolean rsp = false;

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        String separador = Pattern.quote("/");

        String[] separado = fechax.split(separador);

        int dia = Integer.parseInt(separado[0]);
        int mes = Integer.parseInt(separado[1]);
        int ano = Integer.parseInt(separado[2]);

        calendar.set(ano, mes - 1, dia);
        int nD = calendar.get(Calendar.DAY_OF_WEEK);

        if (nD >= 2 && nD <= 6) {
            rsp = true;
        }

        return rsp;
    }

    public String getMes(int i) {
        String mes = "";
        switch (i) {
            case 1:
                mes = "Enero";
                break;
            case 2:
                mes = "Febrero";
                break;
            case 3:
                mes = "Marzo";
                break;
            case 4:
                mes = "Abril";
                break;
            case 5:
                mes = "Mayo";
                break;
            case 6:
                mes = "Junio";
                break;
            case 7:
                mes = "Julio";
                break;
            case 8:
                mes = "Agosto";
                break;
            case 9:
                mes = "Septiembre";
                break;
            case 10:
                mes = "Octubre";
                break;
            case 11:
                mes = "Noviembre";
                break;
            case 12:
                mes = "Diciembre";
                break;

        }
        return mes;
    }
 public int getMes(String i) {
        int mes = 0;
        switch (i.toLowerCase()) {
            case "enero":
                mes=0;
                break;
            case "febrero":
                mes = 1;
                break;
            case "marzo":
                mes = 2;
                break;
            case "abril":
                mes = 3;
                break;
            case "mayo":
                mes = 4;
                break;
            case "junio":
                mes = 5;
                break;
            case "julio":
                mes = 6;
                break;
            case "agosto":
                mes = 7;
                break;
            case "septiembre":
                mes = 8;
                break;
            case "octubre":
                mes = 9;
                break;
            case "noviembre":
                mes = 10;
                break;
            case "diciembre":
                mes = 11;
                break;

        }
        return mes;
    }
    public String Obtener_mac() throws UnknownHostException {
        InetAddress datos = InetAddress.getLocalHost();
        StringBuilder sb = new StringBuilder();
        NetworkInterface result = null;
        byte[] mac_obten = null;
        String mac = null;
        try {
            result = NetworkInterface.getByInetAddress(datos);
            mac_obten = result.getHardwareAddress();
            for (int i = 0; i < mac_obten.length; i++) {
                sb.append(String.format("%02X%s", mac_obten[i], (i < mac_obten.length - 1) ? "-" : ""));
            }
            mac = String.valueOf(sb);
        } catch (Exception e) {
        }
        return mac;

    }


    public int crearCarpeta(String ruta) {
        int a = 0;
        File directorio = new File(ruta);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                a = 1;
            }
        }
        if (directorio.exists()) {
            a = 1;
        }
        return a;
    }

    public void abrirCarpeta(String ruta) {
        try {
            Desktop.getDesktop().open(new File(ruta));
        } catch (IOException ex) {
            Logger.getLogger(tools.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error Abriendo Carpeta!\n" + ex);
        }
    }

    public ArrayList listarDirectorio(String ruta) {
        ArrayList<String> temp = new ArrayList();
        File directorio = new File(ruta);
        if (directorio.exists()) {
            File[] ficheros = directorio.listFiles();
            for (int i = 0; i < ficheros.length; i++) {
                temp.add(ficheros[i].getName());
            }

        }
        return temp;
    }

    public String getNombreArchivo(String ruta) {
        String temp = "";
        String separador = Pattern.quote("\\");
        String[] lista = ruta.split(separador);
        for (int i = lista.length - 1; i < lista.length; i++) {
            temp = lista[i];
            String separador2 = Pattern.quote(".");
            String[] lista2 = temp.split(separador2);
            for (int i2 = 0; i2 < lista2.length; i2++) {
                temp = lista2[0];
            }
        }
        return temp;
    }

    public void abrirArchivo(String ruta) {

        try {
            File objetofile = new File(ruta);
            Desktop.getDesktop().open(objetofile);

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(null, "Error Abriendo Archivo!\n" + ex);

        }

    }

  

    public ArrayList separarFile(File a) {
        ArrayList<String> val = new ArrayList();
        String separador = Pattern.quote(".");
        String separado = a.getName().split("/.")[0].toString();
        String[] parts = separado.split(separador);
        val.add(parts[0]);
        val.add(parts[1]);

        return val;
    }
    public String getExtesion(String ruta){
        File archivoOriginal = new File(ruta);
        ArrayList<String> v1 = (ArrayList) separarFile(archivoOriginal).clone();
        return v1.get(1);
    }
    public void CopiarArchivo(String rutaArchivoOriginal, String CarpetaDestino) {

        try {

            File archivoOriginal = new File(rutaArchivoOriginal);
            ArrayList<String> v1 = (ArrayList) separarFile(archivoOriginal).clone();//getSepara es un metodo previo 

            String nameExtension = v1.get(1);//Toma la MismaExtension del Archivo Copiado
            String nameArchivo = getNombreArchivo(rutaArchivoOriginal);
            File archivoCopia = new File(CarpetaDestino + nameArchivo + "." + nameExtension + "");

            if (archivoOriginal.exists()) {
                Files.copy(Paths.get(archivoOriginal.getAbsolutePath()), Paths.get(archivoCopia.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);

            }

            /*//PRIMERO RUTA LUEGO EL NOMBRE DEL ARCHIVO
            inputStream = new FileInputStream(archivoOriginal);
            outputStream = new FileOutputStream(archivoCopia);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();*/
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void soloNumerosyCantidadDigitos(KeyEvent evt, JTextField a, int cantidad) {
        if (evt.getKeyChar() >= 32 && evt.getKeyChar() <= 45//Numeros parte1
                || evt.getKeyChar() >= 58 && evt.getKeyChar() <= 64//numerosparte2
                || evt.getKeyChar() >= 91 && evt.getKeyChar() <= 96
                || evt.getKeyChar() >= 97 && evt.getKeyChar() <= 122//Letras Minusculas
                || evt.getKeyChar() >= 65 && evt.getKeyChar() <= 90//Letras Mayusculas
                || evt.getKeyChar() >= 123 && evt.getKeyChar() <= 208
                || evt.getKeyChar() >= 210 && evt.getKeyChar() <= 240
                || evt.getKeyChar() >= 242 && evt.getKeyChar() <= 255
                || evt.getKeyChar() > 256) {

            evt.consume();

        } else if (a.getText().length() > cantidad) {
            evt.consume();
        }

    }

    public boolean soloDoubleyCantidadDigitos(KeyEvent evt, JTextField a, int cantidad) {
        boolean llave = false;
        if (evt.getKeyChar() >= 32 && evt.getKeyChar() <= 45//Numeros parte1
                || evt.getKeyChar() >= 58 && evt.getKeyChar() <= 64//numerosparte2
                || evt.getKeyChar() >= 91 && evt.getKeyChar() <= 96
                || evt.getKeyChar() >= 97 && evt.getKeyChar() <= 122//Letras Minusculas
                || evt.getKeyChar() >= 65 && evt.getKeyChar() <= 90//Letras Mayusculas
                || evt.getKeyChar() >= 123 && evt.getKeyChar() <= 208
                || evt.getKeyChar() >= 210 && evt.getKeyChar() <= 240
                || evt.getKeyChar() >= 242 && evt.getKeyChar() <= 255
                || evt.getKeyChar() > 256) {

            evt.consume();

        } else if (a.getText().length() > cantidad) {
            evt.consume();
        } else {
            llave = true;
        }
        return llave;
    }

    public String getVersion() {
        String temp = "";

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("\\\\atenea\\SGDS\\Sistema\\Version\\version.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                temp = linea;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return temp;
    }

    public boolean ComprobarNumeros(String clave) {
        String numeros[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        boolean correcto = false;
        int temp = 0;

        for (int j = 0; j < clave.length(); j++) {

            for (int i = 0; i < numeros.length; i++) {
                //if (clave.contains(numeros[i]))
                String tempx = String.valueOf(clave.charAt(j));
                if (tempx.contains((numeros[i]))) {
                    temp++;
                    break;
                }
            }
            if (temp == clave.length()) {
                correcto = true;
            }
        }

        if (correcto == false) {
            //System.out.println("No todos los valores son númericos");
        }

        return correcto;
    }
    
    public static double reducirDecimales(String x){
        double valor =0.0;
        double miValor = Double.parseDouble(x);
        String stringValor = new DecimalFormat("###.##").format(miValor);
        valor = Double.parseDouble(stringValor);
        return valor;
    }
    
    public long diferenciaFecha(String fecha01, String fecha02){
        long diffrence=0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date firstDate = sdf.parse(fecha01);
            Date secondDate = sdf.parse(fecha02);
            
            long diff = secondDate.getTime() - firstDate.getTime();
            
            TimeUnit time = TimeUnit.DAYS;
             diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
            
            
        } catch (ParseException ex) {
            Logger.getLogger(tools.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return diffrence; 
    }
}
