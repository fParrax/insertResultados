/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frames;

import Clases.ConectarDBCloud;
import Clases.Resultado;
import Clases.ScrapResultados;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cletox
 */
public class ProcesarResultados extends javax.swing.JFrame {

    JsonObject resultadosAgregados = new JsonObject();
     boolean tiempoCorto=true;
     String fechaHoy = new ConectarDBCloud().tomarFecha();
     ArrayList<Resultado> resultadosCloud = new ArrayList();
     int minutosDiferencia =0;
    
    public ProcesarResultados() {
        initComponents();
        new Thread(this::runSystem).start();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCentral = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtIngresadosLottoActivo = new javax.swing.JTextArea();
        mensajeEspera = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtIngresadosGranjita = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ingreso Automático de Resultados");
        setResizable(false);

        panelCentral.setBackground(new java.awt.Color(255, 255, 255));

        txtIngresadosLottoActivo.setEditable(false);
        txtIngresadosLottoActivo.setColumns(10);
        txtIngresadosLottoActivo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtIngresadosLottoActivo.setRows(5);
        jScrollPane1.setViewportView(txtIngresadosLottoActivo);

        mensajeEspera.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        mensajeEspera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensajeEspera.setText("jLabel1");

        txtIngresadosGranjita.setEditable(false);
        txtIngresadosGranjita.setColumns(10);
        txtIngresadosGranjita.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtIngresadosGranjita.setRows(5);
        jScrollPane2.setViewportView(txtIngresadosGranjita);

        javax.swing.GroupLayout panelCentralLayout = new javax.swing.GroupLayout(panelCentral);
        panelCentral.setLayout(panelCentralLayout);
        panelCentralLayout.setHorizontalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mensajeEspera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelCentralLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelCentralLayout.setVerticalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mensajeEspera, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

   
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProcesarResultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProcesarResultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProcesarResultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProcesarResultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
                new ProcesarResultados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel mensajeEspera;
    private javax.swing.JPanel panelCentral;
    private javax.swing.JTextArea txtIngresadosGranjita;
    private javax.swing.JTextArea txtIngresadosLottoActivo;
    // End of variables declaration//GEN-END:variables

    
    public void runSystem() {
    while(true) {
            if (!new ScrapResultados().isSameDay()) {
                tiempoCorto = true;
                mensajeEspera.setText("Aún no actualizan el día en TuAzar.com");
            } else {

                JsonArray resultados = new ScrapResultados().getResultados();

                mensajeEspera.setText("Buscando resultados");
                for (JsonElement element : resultados) {
                    JsonObject resultado = element.getAsJsonObject();
                    String fechaServidor = horaActualServidor();
                    minutosDiferencia = minutosPostSorteo(element, fechaHoy, fechaServidor);

                    System.out.println("");
                    String programa = resultado.get("programa").getAsString();
                    String animal = resultado.get("animal").getAsString();
                    String animalResultado = animal.equals("00")
                            ? "-1Ballena"
                            : animal + getAnimal(animal);
                    String sorteoUtilizar = sorteoUtilizar(programa, getHoradelSorteo(resultado.get("horaSorteo").getAsString()));

                    System.out.println(programa + " " + animalResultado + " " + sorteoUtilizar);
                    mensajeEspera.setText("Leyendo resultados");
                    if (!resultadosAgregados.has(sorteoUtilizar)) {//minutos>= 0 && minutos <=20 && 
                        mensajeEspera.setText("Resultados encontrados");
                        System.out.println("Resultado no encontrado");

                        resultadosCloud = (ArrayList) new Resultado().getResultados(fechaHoy, fechaHoy).clone();
                        Resultado rst = resultadosCloud.stream().filter(t
                                -> t.getPrograma().equalsIgnoreCase(programa)
                                && t.getAnimal().equalsIgnoreCase(animalResultado)
                                && t.getSorteo().equalsIgnoreCase(sorteoUtilizar)
                        ).findFirst().orElse(new Resultado());

                        if (rst.getId() > 0) {//está en la base de datos pero aún no se agrega al registro Local

                            resultadosAgregados.addProperty(sorteoUtilizar, sorteoUtilizar);
                            System.out.println("Está en la BD pero no Local. Agregado Localmente");
                            if (programa.equalsIgnoreCase("granjita")) {
                                txtIngresadosGranjita.append(sorteoUtilizar + " -> " + animalResultado + "\n");
                            } else {
                                txtIngresadosLottoActivo.append(sorteoUtilizar + " -> " + animalResultado + "\n");
                            }
                        } else {
                            rst.setFecha(fechaHoy);
                            rst.setPrograma(programa);
                            rst.setSorteo(sorteoUtilizar);
                            rst.setAnimal(animalResultado);
                            if (rst.insert() > 0) {
                                //if(true){
                                mensajeEspera.setText("Resultado agregado");
                                System.out.println("Agregado en la red");
                                resultadosAgregados.addProperty(sorteoUtilizar, sorteoUtilizar);

                                if (programa.equalsIgnoreCase("granjita")) {
                                    txtIngresadosGranjita.append(sorteoUtilizar + " -> " + animalResultado + "\n");
                                } else {
                                    txtIngresadosLottoActivo.append(sorteoUtilizar + " -> " + animalResultado + "\n");
                                }

                                System.out.println("Agregado Local");
                            } else {
                                mensajeEspera.setText("Error agregando resultado");
                                System.out.println("Error agregando resultado");
                            }
                        }

                    } else {
                        System.out.println("Ya se encontraba en el registro Local");
                    }
                    //System.out.println(programa+" "+animalResultado+" "+sorteoUtilizar);

                }

                if ((minutosDiferencia >= 58 && minutosDiferencia <= 65) || (minutosDiferencia >= 0 && minutosDiferencia <= 20) || (minutosDiferencia >= 49 && minutosDiferencia <= 59)) {
                    tiempoCorto = true;
                } else {
                    tiempoCorto = false;
                }

                if (resultadosAgregados.size() % 2 != 0) {
                    tiempoCorto = false;
                }

            }
            esperar();
        }
    }
    
    private void esperar(){
        try {
            int tiempoEspera = 1;
            System.out.println("tiempoCorto: "+tiempoCorto+" minutosDiferencia: "+minutosDiferencia);
            if(tiempoCorto){
                tiempoEspera = 5;
            }else{
                if(minutosDiferencia <= 60){
                   tiempoEspera =  61-(minutosDiferencia+2);
                }else{
                    tiempoEspera = 5;
                }
            }
            
            
            
            System.out.println("Esperaremos "+tiempoEspera+" hasta el próximo posible sorteo");
            for(int i=0; i<tiempoEspera;i++){
                mensajeEspera.setText("Esperando "+(tiempoEspera-i)+" para próx. revisión");
                System.out.println("Esperando #"+i);
                Thread.sleep(60000);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(MainProceso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    private  String sorteoUtilizar(String programa, String horaSorteo){
        switch(horaSorteo){
            case "09:00:00": return programa+" 9 AM";
            case "10:00:00": return programa+" 10 AM";
            case "11:00:00": return programa+" 11 AM";
            case "12:00:00": return programa+" 12 PM";
            case "13:00:00": return programa+" 1 PM";
            case "14:00:00": return programa+" 2 PM";
            case "15:00:00": return programa+" 3 PM";
            case "16:00:00": return programa+" 4 PM";
            case "17:00:00": return programa+" 5 PM";
            case "18:00:00": return programa+" 6 PM";
            case "19:00:00": return programa+" 7 PM";
            
            default: return "Granjita 8 AM";
        }
        
    }
    
    private  int minutosPostSorteo(JsonElement element,String fechaHoy,String fechaServidor) {
        

        JsonObject resultado = element.getAsJsonObject();
        String horaSorteo = getHoradelSorteo(resultado.get("horaSorteo").getAsString());
        String horaSorteoCompleta = fechaHoy + " " + horaSorteo;

        int minutos = getDiferenciaMinutos(horaSorteoCompleta, fechaServidor);
        System.out.println("hSorteo: "+horaSorteoCompleta+" y fServidor: "+fechaServidor+" - Minutos: "+minutos);
        //if(incrementar){
          //  minutos-=60;
        //}
        //System.out.println("hSorteoModificado: "+horaSorteoCompleta+" y fServidor: "+fechaServidor+" - Minutos: "+minutos);
        
        return minutos;
    }
    
    private  String horaActualServidor(){
        String mFecha ="";
        try {
            String fecha = new ConectarDBCloud().tomarFechaCompleta();
            Date dFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fecha);
            mFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dFecha);
        } catch (ParseException ex) {
            Logger.getLogger(MainProceso.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("HORA TOMADA DEL SERVIDOR: "+mFecha);
        return mFecha;
    }
    
    private  String getAnimal(String numero) {
        String animal = "";

        if (true) {
            switch (numero) {
                case "0":
                    animal = "Delfin";
                    break;
                case "00":
                    animal = "Ballena";
                    break;
                case "1":
                    animal = "Carnero";
                    break;
                case "2":
                    animal = "Toro";
                    break;
                case "3":
                    animal = "Ciempies";
                    break;
                case "4":
                    animal = "Alacrán";
                    break;
                case "5":
                    animal = "Leon";
                    break;
                case "6":
                    animal = "Rana";
                    break;
                case "7":
                    animal = "Perico";
                    break;
                case "8":
                    animal = "Ratón";
                    break;
                case "9":
                    animal = "Aguila";
                    break;
                case "10":
                    animal = "Tigre";
                    break;
                case "11":
                    animal = "Gato";
                    break;
                case "12":
                    animal = "Caballo";
                    break;
                case "13":
                    animal = "Mono";
                    break;
                case "14":
                    animal = "Paloma";
                    break;
                case "15":
                    animal = "Zorro";
                    break;
                case "16":
                    animal = "Oso";
                    break;
                case "17":
                    animal = "Pavo";
                    break;
                case "18":
                    animal = "Burro";
                    break;
                case "19":
                    animal = "Chivo";
                    break;
                case "20":
                    animal = "Cochino";
                    break;
                case "21":
                    animal = "Gallo";
                    break;
                case "22":
                    animal = "Camello";
                    break;
                case "23":
                    animal = "Cebra";
                    break;
                case "24":
                    animal = "Iguana";
                    break;
                case "25":
                    animal = "Gallina";
                    break;
                case "26":
                    animal = "Vaca";
                    break;
                case "27":
                    animal = "Perro";
                    break;
                case "28":
                    animal = "Zamuro";
                    break;
                case "29":
                    animal = "Elefante";
                    break;
                case "30":
                    animal = "Caimán";
                    break;
                case "31":
                    animal = "Lapa";
                    break;
                case "32":
                    animal = "Ardilla";
                    break;
                case "33":
                    animal = "Pescado";
                    break;
                case "34":
                    animal = "Venado";
                    break;
                case "35":
                    animal = "Jirafa";
                    break;
                case "36":
                    animal = "Culebra";
                    break;
            }
        }
        return animal;
    }

    private  String getHoradelSorteo(String hora){
        String rsp="";
                rsp = hora.equalsIgnoreCase("01:00") //&& horaArray[2].equalsIgnoreCase("pm")
                    ?  "13:00"
                    : hora.equalsIgnoreCase("02:00") //&& horaArray[2].equalsIgnoreCase("pm")
                    ?  "14:00"
                    : hora.equalsIgnoreCase("03:00") //&& horaArray[2].equalsIgnoreCase("pm")
                    ?  "15:00"
                    : hora.equalsIgnoreCase("04:00") //&& horaArray[2].equalsIgnoreCase("pm")
                    ?  "16:00"
                    : hora.equalsIgnoreCase("05:00") //&& horaArray[2].equalsIgnoreCase("pm")
                    ?  "17:00"
                    : hora.equalsIgnoreCase("06:00") //&& horaArray[2].equalsIgnoreCase("pm")
                    ?  "18:00"
                    : hora.equalsIgnoreCase("07:00") //&& horaArray[2].equalsIgnoreCase("pm")
                    ?  "19:00"
                    : hora ;
        return rsp+":00";
    }
     
    private  int getDiferenciaMinutos(String xHora, String horaServidor) {

    LocalDateTime horaInicial = LocalDateTime.parse(xHora, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    LocalDateTime horaFinal = LocalDateTime.parse(horaServidor, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    long diferenciaEnMilisegundos = horaFinal.toInstant(ZoneOffset.of("-05:00")).toEpochMilli() - horaInicial.toInstant(ZoneOffset.of("-05:00")).toEpochMilli();
    int diferenciaEnMinutos = (int) (diferenciaEnMilisegundos / (1000 * 60));

    return diferenciaEnMinutos;
}
   
}
