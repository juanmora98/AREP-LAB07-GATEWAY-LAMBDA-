package co.edu.escuelaing.services.Iniciador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import co.edu.escuelaing.services.Puerto.Puerto;
import spark.Request;
import spark.Response;
import static spark.Spark.*;


public class Iniciador
{

    /**
     * Metodo inicial que se encargara de correr la asignacion del puerto y asi mismo los llamados get de las paginas especificas
     * @param args
     */
    public static void main(String[] args) {
        port(Puerto.getPort());
        get("/", (req, res) -> PaginaInicial(req, res));
        get("/results", (req, res) -> PaginaResultado(req, res));
    }

    /**
     * Metodo encargado de generar la pagina inicial la cual se encargara de solicitarle un numero al usuario
     * @param req
     * @param res
     * @return
     */
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

    /**
     * Metodo encargado de generar la pagina de respuesta al usuario esta esta conectada directamente con el lambda de AWS para realizar la operacion
     * @param req
     * @param res
     * @return
     * @throws Exception
     */
    private static String PaginaResultado(Request req, Response res) throws Exception {
        String number=(req.queryParams("data"));
         URL lambda=new URL("https://i119l4omrg.execute-api.us-east-1.amazonaws.com/alfa?value="+number);
        String contenido="";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(lambda.openStream()))) {
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