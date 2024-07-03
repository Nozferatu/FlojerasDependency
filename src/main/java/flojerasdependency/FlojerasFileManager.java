package flojerasdependency;

//FLOJERAS FILE MANAGER V0.1.3

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

/**
 *
 * @author Carlos Madrid
 */
public class FlojerasFileManager {
    private File archivo;
    private boolean accesible;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Stack<String> queue;
    private boolean debugMode;
    
    public FlojerasFileManager(String ruta){
        this.archivo = new File(ruta);
        if(!archivo.exists()) {
            try {
                archivo.createNewFile();
                if(debugMode) System.out.println("Archivo nuevo creado");
            } catch (IOException ex) {
                if(debugMode) System.out.println("No se ha podido crear el archivo");
            }
        }
        if(!archivo.isFile()){
            System.out.printf("EL ARCHIVO/RUTA NO ES VÁLIDO (%s)\n", ruta);
            accesible = false;
        }else{
            accesible = true;
        }
        queue = new Stack();
        debugMode = false;
    }

    public FlojerasFileManager(String ruta, boolean debugMode){
        this(ruta);
        this.debugMode = debugMode;
    }

    /**
     * Devuelve la ruta absoluta donde se encuentra el archivo
     * @return String
     */
    public String rutaAbsoluta(){
        return archivo.getAbsolutePath();
    }
    
    /**
     * Modifica la ruta del archivo al que apunta el objeto. Si ya hay un flujo de datos abierto, no se podrá cambiar y no surtirá efecto.
     * Para cerrar el flujo de datos, se debe llamar a cerrarFlujo(). Por medio del parámetro de forzado, se puede especificar si se quiere
     * cerrar el flujo directamente o no.
     * @param nuevaRuta La ruta a la que se quiere cambiar
     * @param forzado Forzar el cambio
     */
    public void cambiarRuta(String nuevaRuta, boolean forzado){
        if(forzado) cerrarFlujo();
        
        if(writer == null && reader == null){
            archivo = new File(nuevaRuta);
            if(!archivo.isFile()){
                System.out.printf("EL ARCHIVO/RUTA NO ES VÁLIDO (%s)\n", nuevaRuta);
                accesible = false;
            }else{
                accesible = true;
            }
        }else{
            System.out.println("Ya hay un flujo de datos abierto. Se debe de cerrar primero antes de poder cambiar la ruta.");
        }
    }
    
    private static boolean borrarArchivo(File f){
        if(f.isFile()){
            return f.delete();
        }else return false;
    }
    
    /**
     * Borra el archivo al que esté apuntando el objeto. Devuelve un booleano indicando si ha podido eliminarlo o no.
     * Este método no funcionará mientras haya un flujo de datos abierto o la ruta proporcionada sea un directorio.
     * @param forzado Indica si se quiere que el borrado se haga de manera forzada.
     * @return Verdadero en caso de haberlo borrado con éxito. De lo contrario, será falso.
     */
    public boolean borrarArchivo(boolean forzado){
        if(accesible) {
            if(forzado) cerrarFlujo();
            return borrarArchivo(archivo);
        } else return false;
    }
    
    /**
     * Lo mismo que borrarArchivo(), pero pasando directamente la ruta del archivo.
     * @param ruta Ruta del archivo
     * @return Verdadero en caso de haberlo borrado con éxito. De lo contrario, será falso.
     */
    public static boolean borrarArchivo(String ruta){
        File tmp = new File(ruta);
        if(tmp.exists()){
            return borrarArchivo(tmp);
        }else{
            return false;
        }
    }
    
    /**
     * Devuelve una cadena de texto del archivo. En caso de que no haya más por leer, devolverá null.
     * @return String
     */
    public String leerLinea(){
        if(accesible){
            if(reader == null){
                try {
                    reader = new BufferedReader(new FileReader(archivo));
                } catch (FileNotFoundException ex) {}
            }
            try {
                return reader.readLine();
            } catch (IOException ex) {
                if(debugMode) {
                    System.out.println("No se ha podido leer el archivo");
                    ex.printStackTrace(System.out);
                }
                return null;
            }
        }else{
            return null;
        }
    }
    
    /**
     * Añade a una cola la cadena de texto que se proporcione. Si el parámetro de introducirNuevaLinea es verdadero, se añadirá un Enter. 
     * Hasta que no se llame el método prepararCambios(), no se pasará al flujo de datos.
     * @param linea String
     * @param introducirNuevaLinea Boolean
     */
    public void escribirLinea(String linea, boolean introducirNuevaLinea){
        if (introducirNuevaLinea){
            queue.push(linea + '\n');
        }else{
            queue.push(linea);
        }
    }
    
    /**
     * Añade todos los elementos de la cola al flujo de datos. Hasta que no se llame el método efectuarCambios(), no se realizará la escritura en el archivo.
     */
    public void prepararCambios(){
        if(accesible){
            if(!queue.isEmpty()){
                if(writer == null){
                    try {
                        writer = new BufferedWriter(new FileWriter(this.archivo, true));
                    } catch (IOException ex) {}
                }
                try {
                    for(String item : queue)
                        writer.write(item);
                } catch (IOException ex) {
                    if(debugMode){
                        System.out.println("No se ha podido escribir en el archivo.");
                        ex.printStackTrace(System.out);
                    }
                }
            }else{
                System.out.println("No hay ningún cambio añadido a la cola.");
            }
        }else{
            System.out.println("No se puede acceder al archivo especificado.");
        }
    }
    
    /**
     * Se realizan los cambios que hayan almacenados en el flujo de datos.
     */
    public void efectuarCambios(){
        if(writer != null){
            try {
                writer.close();
            } catch (IOException ex) {}
        }else{
            System.out.println("El flujo de datos no está abierto.");
        }
    }
    
    /**
     * Elimina el último elemento añadido a la cola.
     */
    public void deshacerCambiosCola(){
        if(!queue.isEmpty()) queue.pop();
    }
    
    /**
     * Elimina todos los cambios pendientes en la cola.
     */
    public void limpiarCola(){
        queue.clear();
    }
    
    /**
     * Elimina todos los cambios del flujo de datos.
     */
    public void limpiarFlujo(){
        if(writer != null){
            try {
                writer.flush();
            } catch (IOException ex) {}
        }else{
            System.out.println("El flujo de datos no está abierto.");
        }
    }
    
    /**
     * Cierra el flujo de datos, tanto el de lectura como el de escritura. Es recomendable cuando ya no se siga leyendo/modificando el archivo.
     */
    public void cerrarFlujo() {
        try {
            if (reader != null) reader.close();
            if (writer != null) writer.close();
        } catch (IOException e) {}
    }
}