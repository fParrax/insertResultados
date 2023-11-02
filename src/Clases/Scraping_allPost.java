
package Clases;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.regex.Pattern;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraping_allPost {
	
    public static final String url = "https://www.tuazar.com/loteria/animalitos/resultados/";
    public static final int maxPages = 20;
	
    
    public JsonArray getResultados(){
        JsonArray resultados = new JsonArray();
        
          // Compruebo si me da un 200 al hacer la petición
            if (getStatusConnectionCode(url) == 200) {
				
                // Obtengo el HTML de la web en un objeto Document2
                Document document = getHtmlDocument(url);
				
                // Busco todas las historias de meneame que estan dentro de: 
                Elements entradas = document.select("div.col-md-8.col-sm-12.resultados");
               // Elements entradas = document.select(".row.resultado");
				            
                // Paseo cada una de las entradas
                for (Element elem : entradas) {
                    String titulo = elem.getElementsByClass("col-xs-6 col-sm-3").text();
                    JsonObject myResultado = new JsonObject();
                    if(titulo.contains("Lotto Activo") ){//|| titulo.contains("La Granjita")
                        String separador = Pattern.quote("Animalito Lotto Activo ");
                        String[] arreglo = titulo.split(separador);
                        for(String valor : arreglo){
                            if(!valor.contains("- - -")){
                                /*
                                    Valor Imprime: 20 - CERDO 09:00
                                        #Animal - NombreAnimal - HoraSorteo
                                
                                    Resultado Imprime: 
                                    [0]=Animal=20
                                    [1]=Separador=-
                                    [2]=TextoAnimal=CERDO
                                    [3]=HoraSorteo=09:00
                                */
                                
                                String separador2 = Pattern.quote(" ");
                                String[] resultado = valor.split(separador2);
                                if(resultado.length > 1){
                                    String numAnimal = resultado[0];
                                    String horaSorteo = resultado[3];
                                    System.out.println("Lotto Activo Salió el "+numAnimal+" para las "+horaSorteo);
                                    myResultado.addProperty("animal", numAnimal);
                                    myResultado.addProperty("horaSorteo", horaSorteo);
                                    resultados.add(myResultado);
                                }
                            }
                        }
                    }else if(titulo.contains("La Granjita") ){
                        String separador = Pattern.quote("Animalito La Granjita ");
                        String[] arreglo = titulo.split(separador);
                        for(String valor : arreglo){
                            if(!valor.contains("- - -")){
                                /*
                                    Valor Imprime: 20 - CERDO 09:00
                                        #Animal - NombreAnimal - HoraSorteo
                                
                                    Resultado Imprime: 
                                    [0]=Animal=20
                                    [1]=Separador=-
                                    [2]=TextoAnimal=CERDO
                                    [3]=HoraSorteo=09:00
                                */
                                
                                String separador2 = Pattern.quote(" ");
                                String[] resultado = valor.split(separador2);
                                if(resultado.length > 1){
                                    String numAnimal = resultado[0];
                                    String horaSorteo = resultado[3];
                                    System.out.println("Granjita Salió el "+numAnimal+" para las "+horaSorteo);
                                     myResultado.addProperty("animal", numAnimal);
                                    myResultado.addProperty("horaSorteo", horaSorteo);
                                    resultados.add(myResultado);
                                }
                            }
                        }
                    }
                    //System.out.println("titulo: "+titulo);
					
					
                }
		
            }else{
                System.out.println("El Status Code no es OK es: "+getStatusConnectionCode(url));
            }
        
        return resultados;
    }
    
    
	
    /**
     * Con esta método compruebo el Status code de la respuesta que recibo al hacer la petición
     * EJM:
     * 		200 OK			300 Multiple Choices
     * 		301 Moved Permanently	305 Use Proxy
     * 		400 Bad Request		403 Forbidden
     * 		404 Not Found		500 Internal Server Error
     * 		502 Bad Gateway		503 Service Unavailable
     * @param url
     * @return Status Code
     */
    public static int getStatusConnectionCode(String url) {
		
        Response response = null;
		
        try {
            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
        }
        return response.statusCode();
    }
	
	
    /**
     * Con este método devuelvo un objeto de la clase Document con el contenido del
     * HTML de la web que me permitirá parsearlo con los métodos de la librelia JSoup
     * @param url
     * @return Documento con el HTML
     */
    public static Document getHtmlDocument(String url) {

        Document doc = null;

        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
        }

        return doc;

    }
}