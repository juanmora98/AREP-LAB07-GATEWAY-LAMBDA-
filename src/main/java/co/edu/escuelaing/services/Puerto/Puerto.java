package co.edu.escuelaing.services.Puerto;


public class Puerto
{
    /**
     * Metodo realizado para la asignacion de un puerto.
     * @return
     */
    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
        
    }
}