package co.edu.escuelaing.services.Iniciador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import co.edu.escuelaing.services.Puerto.Puerto;
import spark.Request;
import spark.Response;
import spark.Route;
import static spark.Spark.*;


public class Iniciador
{

    public static void main(String[] args) {
        port(Puerto.getPort());
        get("/", (req, res) -> PaginaInicial(req, res));
        get("/results", (req, res) -> PaginaResultado(req, res));
    }

     private static String PaginaInicial(Request req, Response res) {
        String pageContent = "<!DOCTYPE html>"
                + "<html>"
                +"<head>"
                +"<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" rel=\"stylesheet\"" 
                +"integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">" 
                +"</head>"
                + "<body>"
                + "<h2>Calcular el cuadrado de un numero</h2>"
                + "<form action=\"/results\">"
                + "  Hola, porfavor en el siguiente espacio ingresa el numero que deseas conocer su cuadrado:<br>"
                + "  <br>"
                + "  <textarea class=\"form-control\" name=\"data\" placeholder=\"5\">5</textarea>"
                + "  <br>"
                + "  <button class= \"btn btn-outline-primary\" type=\"submit\">Calcular</button>"
                + "</form>"
                +" <br>"
                + "</body>"
                + "</html>";
        return pageContent;
    }
    private static String PaginaResultado(Request req, Response res) throws Exception {
        String number=(req.queryParams("data"));
         URL pagina=new URL("https://i119l4omrg.execute-api.us-east-1.amazonaws.com/alfa?value="+number);
        String contenido="";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(pagina.openStream()))) {
			String inputLine = null;
			while ((inputLine = reader.readLine()) != null) {
				contenido+=inputLine;
			}
		} catch (IOException io) {
			System.err.println(io);

		}
        return contenido;
    }


}