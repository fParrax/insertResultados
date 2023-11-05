/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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


public class MainProceso extends Thread{
     JsonObject resultadosAgregados = new JsonObject();
     boolean tiempoCorto=true;
     String fechaHoy = new ConectarDBCloud().tomarFecha();
     ArrayList<Resultado> resultadosCloud = new ArrayList();
    int minutosDiferencia =0;
    public void run(){
        
        while(true){
            
            JsonArray resultados = new ScrapResultados().getResultados();
           
            
            for (JsonElement element : resultados) {
                JsonObject resultado = element.getAsJsonObject();
                String fechaServidor = horaActualServidor();
                minutosDiferencia = minutosPostSorteo(element,fechaHoy,fechaServidor);
                
                
                    
                System.out.println("");
                String programa = resultado.get("programa").getAsString();
                String animal = resultado.get("animal").getAsString();
                String animalResultado = animal.equals("00")
                        ? "-1Ballena"
                        : animal + getAnimal(animal);
                String sorteoUtilizar = sorteoUtilizar(programa,getHoradelSorteo(resultado.get("horaSorteo").getAsString()));
               
                System.out.println(programa+" "+animalResultado+" "+sorteoUtilizar);
                
                if(!resultadosAgregados.has(sorteoUtilizar)){//minutos>= 0 && minutos <=20 && 
                    System.out.println("Resultado no encontrado");
                    
                    resultadosCloud = (ArrayList) new Resultado().getResultados(fechaHoy, fechaHoy).clone();
                    Resultado rst = resultadosCloud.stream().filter(t-> 
                                        t.getPrograma().equalsIgnoreCase(programa) && 
                                        t.getAnimal().equalsIgnoreCase(animalResultado) &&
                                        t.getSorteo().equalsIgnoreCase(sorteoUtilizar)
                                    ).findFirst().orElse(new Resultado());
                    
                    if(rst.getId() > 0){//está en la base de datos pero aún no se agrega al registro Local
                        resultadosAgregados.addProperty(sorteoUtilizar, sorteoUtilizar);
                        System.out.println("Está en la BD pero no Local. Agregado Localmente");
                    }else{
                        rst.setFecha(fechaHoy);
                        rst.setPrograma(programa);
                        rst.setSorteo(sorteoUtilizar);
                        rst.setAnimal(animalResultado);
                        //if(rst.insert()>0){
                        if(true){
                            System.out.println("Agregado en la red");
                            resultadosAgregados.addProperty(sorteoUtilizar, sorteoUtilizar);
                            System.out.println("Agregado Local");
                        }else{
                            System.out.println("Error agregando resultado");
                        }
                    }
                    
                }else{
                    System.out.println("Ya se encontraba en el registro Local");
                }
                //System.out.println(programa+" "+animalResultado+" "+sorteoUtilizar);
            
                
                
                
            }
            
                
            if ((minutosDiferencia >= 0 && minutosDiferencia <= 20) || (minutosDiferencia >= 49 && minutosDiferencia <= 59)) {
                tiempoCorto = true;
            } else {
                tiempoCorto = false;
            }
                
          esperar();
        }
    }
    
    private void esperar(){
        try {
            int tiempoEspera = 1;
            if(tiempoCorto){
                tiempoEspera = 5;
            }else{
                if(minutosDiferencia < 60){
                   tiempoEspera =  53-minutosDiferencia;
                }else{
                    tiempoEspera = 5;
                }
            }
            
            
            
            System.out.println("Esperaremos "+tiempoEspera+" hasta el próximo posible sorteo");
            for(int i=0; i<tiempoEspera;i++){System.out.println("Esperando #"+i);
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
            Date dFecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(fecha);
            mFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dFecha);
        } catch (ParseException ex) {
            Logger.getLogger(MainProceso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
