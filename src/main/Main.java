package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<Pelicula> arrayList = null;
    private static String path = "videoteca.dat";

    public static void main(String[] args) /* Eliminamos el throws de IOException en el método main para poder controlar manualmente todas sus excepciones */{

        int opcion = 0;
        while (opcion != 4) {
            System.out.println("MENU VIDEOTECA");
            System.out.println("1. Cargar datos de inicio");
            System.out.println("2. Listar película ordenadas por num votos");
            System.out.println("3. Lista películas ordenadas por genero");
            System.out.println("4. Salir");
            
            /**
             * Comienzo del bloque Try Catch a partir de la inserción del input del usuario ya que es la parte del código que nos puede dar excepciones explícitas.
             * 1 - NumberFormatException -> Controlamos que el usuario no se equivoque y escriba una opción del menú que no sea un valor numérico.
             * 2 - NullPointerException -> Controlamos que no haya instancias nulas en el programa que puedan afectar a su desarrollo
             * Ejemplos de excepción: No instanciar el ArrayList y dejarlo null tal como está globalmente, dejar la variable line a null en el método cargarDatos
             * 3 - FileNotFoundException -> Controlamos que el fichero no se haya movido de sitio o se introduzca su nombre incorrectamente en la variable path
             * 4 - IOException -> Controlamos una excepción de Input / Output que se escape de las anteriores más concretas.
             */
            
            try {
                String entrada = reader.readLine();
                opcion = Integer.parseInt(entrada);

                switch (opcion) {
                    case 1:
                        cargarDatos();
                        break;
                    case 2:
                        listarPorVotos();
                        break;
                    case 3:
                        listarPorGenero();
                        break;
                    case 4:
                        System.out.println("Saliendo...");
                        break;
                }
            } catch (NumberFormatException n)  {
                System.err.println("Introduce un valor válido");
            } catch (NullPointerException np) {
                /**
                 * En los próximos bloques catch se realiza un break para que termine el programa ya que queremos que el usuario vuelva al código para revisarlo y saber qué está fallando.
                 * No tendría mucho sentido continuar con la ejecución si el código va a seguir siendo erróneo hasta que no lo cambie.
                 */
                System.err.println("Revisa si hay algún elemento " + np.getMessage() + ", revisa su instanciación y vuelve a ejecutar el programa.");
                break;
            } catch (FileNotFoundException fnf)  {
                System.err.println("El fichero no existe o está mal escrito, revisa su nombre o directorio y vuelve a ejecutar el programa.");
                System.err.println(fnf.getMessage());
                break;
            } catch (IOException io) {
                System.err.println("Error Input / Output, revisa que todo esté bien y vuelve a ejecutar el programa");
                System.err.println(io.getMessage());
                break;
            }
        }
    }

    public static void cargarDatos() throws FileNotFoundException, IOException, NullPointerException {
        /**
         * Este método lanza a su método pariente (en este caso main) todas las posibles excepciones que pueda devolver este código
         * Tal como hemos visto anteriormente el main captura correctamente las tres excepciones que lanzamos en cargarDatos
         * El principal objetivo es la centralización y el orden de las excepciones, por esta razón, he decidido hacer que el main se encargue de las excepciones del método y tenerlas todas en un lugar
         */
        FileReader reader = new FileReader(path);
        BufferedReader br = new BufferedReader(reader);

        String line = null;
        arrayList = new ArrayList<Pelicula>();
        
        while ((line = br.readLine()) != null) {
        String[] movie = line.split(";");
            arrayList.add(new Pelicula(movie[0], movie[1], Integer.parseInt(movie[2])));
        }
        
        br.close();
    }

    public static void listarPorVotos() {
        System.out.println("Lista peliculas ordenadas por orden descendente nº de votos");
    }

    public static void listarPorGenero() {

        System.out.println("Lista peliculas ordenadas por género y orden descendente nº de votos");

    }

}
