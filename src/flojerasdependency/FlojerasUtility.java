package flojerasdependency;

import flojerasdependency.data.*;
import java.util.Scanner;

/*
    Flojeras Utility es un módulo que me he hecho a modo de hacer el trabajo más fácil, 
    agilizando el código y facilitando las pruebas del programa. Para usarla, se ha de extender
    de esta clase.
*/

//Sugerencias anotadas

/**
 *
 * @author Carlos Madrid
 */

public class FlojerasUtility {
    //Atributos varios
    public static final String RESET_COLORES = "\u001B[0m";
    
    //Colores para el texto de la consola
    public static final String TEXTO_NEGRO = "\u001B[30m";
    public static final String TEXTO_ROJO = "\u001B[31m";
    public static final String TEXTO_VERDE = "\u001B[32m";
    public static final String TEXTO_AMARILLO = "\u001B[33m";
    public static final String TEXTO_AZUL = "\u001B[34m";
    public static final String TEXTO_MORADO = "\u001B[35m";
    public static final String TEXTO_CYAN = "\u001B[36m";
    public static final String TEXTO_BLANCO = "\u001B[37m";
    
    //Colores para el fondo de la consola
    public static final String FONDO_NEGRO = "\u001B[40m";
    public static final String FONDO_ROJO = "\u001B[41m";
    public static final String FONDO_VERDE = "\u001B[42m";
    public static final String FONDO_AMARILLO = "\u001B[43m";
    public static final String FONDO_AZUL = "\u001B[44m";
    public static final String FONDO_MORADO = "\u001B[45m";
    public static final String FONDO_CYAN = "\u001B[46m";
    public static final String FONDO_BLANCO = "\u001B[47m";
    
    //Imprimir por consola
    /**
     * Imprime por la terminal. No tiene mucho misterio.
     * @param o Mensaje
     */
    public static void print(Object o){
        System.out.println(o);
    }
    
    //Imprimir por consola con colores
    /**
     * Imprime por la terminal. Al contrario del print normal, éste permite los parámetros para añadir
     * el color del texto, y el del fondo. La librería incluye unas constantes con los valores necesarios
     * para ésto. Como por ejemplo, TEXTO_ROJO, TEXTO_CYAN, FONDO_VERDE.
     * @param o Mensaje
     * @param colorTexto Color del texto
     * @param colorFondo Color del fondo
     */
    public static void print(Object o, String colorTexto, String colorFondo){
        System.out.println(colorTexto + colorFondo + o);
    }
    
    //MÉTODOS DE INPUT

    /**
     * Pide al usuario una cadena de texto.
     * @return String
     */
    public static String pedirTexto(){
        Scanner sc = new Scanner(System.in);
        
        String respuesta = sc.nextLine();
        
        sc.close();
        
        return respuesta;
    }
    /**
     * Pide un número entero al usuario. Si la opción de positivo es verdadera, el usuario tendrá que introducir
     * de forma obligatoria un número positivo
     * @param positivo Booleano
     * @return int
     */
    public static int pedirInt(boolean positivo){
        Scanner sc = new Scanner(System.in);
        int x;
        
        while(true){
            try {
                x = sc.nextInt();

                if(positivo){
                    if(x < 0){
                        System.out.println("El número debe de ser positivo");
                    }else{
                        break;
                    }
                }else{
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        sc.close();
        
        return x;
    }
    /**
     * Pide un float al usuario. Si la opción de positivo es verdadera, el usuario tendrá que introducir
     * de forma obligatoria un número positivo
     * @param positivo Booleano
     * @return float
     */
    public static float pedirFloat(boolean positivo){
        Scanner sc = new Scanner(System.in);
        float x;
        
        while(true){
            try {
                x = sc.nextFloat();

                if(positivo){
                    if(x < 0){
                        System.out.println("El número debe de ser positivo");
                    }else{
                        break;
                    }
                }else{
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        sc.close();
        
        return x;
    }
    /**
     * Pide un double al usuario. Si la opción de positivo es verdadera, el usuario tendrá que introducir
     * de forma obligatoria un número positivo
     * @param positivo Booleano
     * @return double
     */
    public static double pedirDouble(boolean positivo){
        Scanner sc = new Scanner(System.in);
        double x;
        
        while(true){
            try {
                x = sc.nextDouble();

                if(positivo){
                    if(x < 0){
                        System.out.println("El número debe de ser positivo");
                    }else{
                        break;
                    }
                }else{
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        sc.close();
        
        return x;
    }
    /**
     * Pide una letra al usuario. Si la opción de devolverMayus es verdadera, devolverá la letra
     * en mayúscula. En caso contrario, la devolverá en minúscula.
     * @param devolverMayus Booleano
     * @return char
     */
    public static char pedirLetra(boolean devolverMayus){
        Scanner sc = new Scanner(System.in);
        char c = '@'; //Valor basura porque sí
        
        do{
            try {
                if(devolverMayus){
                    c = sc.nextLine().toUpperCase().charAt(0);
                }else{
                    c = sc.nextLine().toLowerCase().charAt(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while(c < 'A' || c > 'z');
        
        sc.close();
        
        return c;
    }
    
    //MÉTODOS MATEMÁTICOS
    /**
     * Devuelve los divisores de un número proporcionado.
     * @param num Número
     * @return int[]
     */
    public static int[] devolverDivisores(int num){
        int cant = 0;
        
        for(int i = num; i > 0; i--){
            if(num % i == 0){
                cant++;
            }
        }
        
        int[] divisores = new int[cant];
        int contador = 0;
        
        for(int i = num; i > 0; i--){
            if(num % i == 0){
                divisores[contador] = i;
                contador++;
            }
        }
        
        return divisores;
    }
    /**
     * Devuelve un booleano indicando si un número proporcionado es capicúa o no.
     * @param num Número
     * @return boolean (True/False)
     */
    public static boolean esCapicua(int num){
        int palindromo = num;
        int reves = 0;
        
        while(palindromo != 0){
            int resto = palindromo % 10;
            reves = reves * 10 + resto;
            palindromo /= 10;
        }
        
        return num == reves;
    }
    /**
     * Devuelve un número x elevado a un exponente y. Es el Math.pow(), pero sin nombres raros.
     * @param base Base
     * @param exp Exponente
     * @return int
     */
    public static double elevar(int base, int exp){
        return Math.pow(base, exp);
    }
    /**
     * Hace la raíz cuadrada de un número proporcionado. Si el número es negativo y no se hace cast
     * a int, se devolverá un NaN (número imaginario).
     * @param a Número
     * @return double
     */
    public static double raizCuadrada(int a){
        return Math.sqrt(a);
    }
    /**
     * Calcula la raíz n de un número proporcionado. Si el número es negativo y no se hace cast
     * a int, se devolverá un NaN (número imaginario).
     * @param a Número
     * @param n Raíz
     * @return double
     */
    public static double raizN(int a, int n){
        return Math.pow(a, 1.0/n);
    }
    /**
     * Devuelve un booleano indicando si el número proporcionado es desgarrable o no. Se considera que
     * un número es desgarrable si al dividirlo en dos partes (izquierda y derecha), el cuadrado de la
     * suma de ambas partes es igual al número original.<br>
     * Ejemplo: 88209 = (88 + 209)<sup>2</sup>
     * @param num Número
     * @return boolean (True/False)
     */
    public static boolean esDesgarrable(int num){
        String numStr = "" + num;
        int longitud = numStr.length();
        String izda = "", dcha = "";
        
        for(int i = 0; i < longitud % 2 + 1; i++){
            izda += numStr.charAt(i);
        }
        for(int i = longitud / 2; i < longitud; i++){
            dcha += numStr.charAt(i);
        }
        
        int comprobacion = (int) Math.pow((Integer.valueOf(izda) + Integer.valueOf(dcha)), 2);
        
        return num == comprobacion;
    }
    /**
     * Devuelve un número entero al azar entre un mínmo y máximo proporcionado
     * @param max Intervalo máximo
     * @param min Intervalo mínimo
     * @return Int
     */
    public static int randomInt(int min, int max){
        return (int) (Math.random() * (max - min + 1)) + min;
    }
    public static int[] randomIntArr(int min, int max, int cantidad){
        int[] arr = new int[cantidad];
        
        for(int i = 0; i < arr.length; i++){
            arr[i] = randomInt(min, max);
        }
        
        return arr;
    }
    /**
     * Devuelve un número con decimales al azar entre un mínmo y máximo proporcionado
     * @param max Intervalo máximo
     * @param min Intervalo mínimo
     * @return double
     */
    public static double randomDouble(float min, float max){
        return (Math.random() * (max - min + 1)) + min;
    }
    public static double[] randomDoubleArr(int min, int max, int cantidad){
        double[] arr = new double[cantidad];
        
        for(int i = 0; i < arr.length; i++){
            arr[i] = randomDouble(min, max);
        }
        
        return arr;
    }
    /**
     * Devuelve una letra (char) al azar. Si la opción de mayus es true, devolverá la letra en mayúscula.
     * En caso contrario, será minúscula.
     * @param mayus Booleano
     * @return char
     */
    public static char randomChar(boolean mayus){
        char c;
        
        if(mayus) c = (char) randomInt('A', 'Z');
        else c = (char) randomInt('a', 'z');
        
        return c;
    }
    /**
     * Devuelve al azar o verdadero o falso
     * @return boolean (True/False)
     */
    public static boolean randomBoolean(){
        int random = randomInt(0, 1);
        
        return random == 0;
    }
    /**
     * Trunca un número con decimales al número de decimales especificado.
     * @param num Número
     * @param decimales Cantidad de decimales
     * @return double
     */
    public static double truncar(double num, int decimales){
        int dec = (int) elevar(10, decimales);
        double truncado = (int) (num * dec);
        return truncado /= dec;
    }
    /**
     * Se realiza la ecuación de segundo grado de los números proporcionados a, b y c. Si la ecuación
     * no tiene ninguna solución, se lanzará un mensaje indicándolo.
     * @param a A
     * @param b B
     * @param c C
     */
    public static void ecuacionSegundoGrado(int a, int b, int c){
        double[] soluciones = new double[2];
        double parteRaiz = Math.sqrt(Math.pow(b, 2) - (4*a*c));
        soluciones[0] = (-b + parteRaiz) / (2*a);
        soluciones[1] = (-b - parteRaiz) / (2*a);
            
        System.out.println("a = " + a + " b = " + b + " c = " + c);
            
        if(Double.isNaN(soluciones[0]) && Double.isNaN(soluciones[1])){
            System.out.println("La ecuación no tiene solución.");
        }else{
            int cont = 1;
            if(!Double.isNaN(soluciones[0])) {
                System.out.println("Solución " + cont + " = " + soluciones[0]);
                cont++;
            }
            if(!Double.isNaN(soluciones[1]))
                System.out.println("Solución " + cont + " = " + soluciones[1]);
            }
    }
    
    public static double[] ecuacionSegundoGradoNuevo(int a, int b, int c){
        double[] aux = new double[2]; //Array temporal para almacenar las posibles soluciones
        double parteRaiz = Math.sqrt(Math.pow(b, 2) - (4*a*c));
        aux[0] = (-b + parteRaiz) / (2*a);
        aux[1] = (-b - parteRaiz) / (2*a);
            
        System.out.println("a = " + a + " b = " + b + " c = " + c);
            
        if(Double.isNaN(aux[0]) && Double.isNaN(aux[1])){
            System.out.println("La ecuación no tiene solución.");
            return null;
        }else{
            int cont = 0;
            if(!Double.isNaN(aux[0])) cont++;
            if(!Double.isNaN(aux[1])) cont++;
            double[] soluciones = new double[cont];

            System.arraycopy(aux, 0, soluciones, 0, aux.length);
            
            return soluciones;
        }
    }
    
    //MÉTODOS DE ITERACIÓN (principalmente para solo ver el contenido)
    //Recorrer array
    public static void recorrerArray(int[] arr){
        for (Object n : arr) {
            System.out.println(n);
        }
    }
    public static void recorrerArray(float[] arr){
        for (Object n : arr) {
            System.out.println(n);
        }
    }
    public static void recorrerArray(double[] arr){
        for (Object n : arr) {
            System.out.println(n);
        }
    }
    /**
     * En el caso de los objetos, se supone que los objetos del array a recorrer, tienen un método toString,
     * para que en vez de que salga la dirección de memoria, salgan los datos del objeto almacenado.
     * @param arr Array
     */
    public static void recorrerArray(Object[] arr){
        for (Object n : arr) {
            System.out.println(n);
        }
    }
    
    //Recorrer matrices
    public static void recorrerMatriz(int[][] maiz){
        for(int i = 0; i < maiz.length; i++){
            for(int j = 0; j < maiz[i].length; j++){
                System.out.println(maiz[i][j]);
            }
        }
    }
    public static void recorrerMatriz(double[][] maiz){
        for(int i = 0; i < maiz.length; i++){
            for(int j = 0; j < maiz[i].length; j++){
                System.out.println(maiz[i][j]);
            }
        }
    }
    public static void recorrerMatriz(float[][] maiz){
        for(int i = 0; i < maiz.length; i++){
            for(int j = 0; j < maiz[i].length; j++){
                System.out.println(maiz[i][j]);
            }
        }
    }
    /**
     * En el caso de los objetos, se supone que los objetos de la matriz a recorrer, tienen un método toString,
     * para que en vez de que salga la dirección de memoria, salgan los datos del objeto almacenado.
     * @param maiz Matriz
     */
    public static void recorrerMatriz(Object[][] maiz){
        for(int i = 0; i < maiz.length; i++){
            for(int j = 0; j < maiz[i].length; j++){
                System.out.println(maiz[i][j]);
            }
        }
    }
    
    //Devolver espacios restantes
    public static int[] devolverEspaciosRestantes(Object[] arr){
        int espacios = 0;
        
        for(Object o : arr){
            if(o == null) espacios++;
        }
        
        int[] indices = new int[espacios];
        espacios = 0; //Reusar variable en vez de crear otra
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == null) {
                indices[espacios] = i;
                espacios++;
            }
            
        }
        
        return indices;
    }
    
    //RELLENO DE DATOS AL AZAR
    //Relleno de arrays
    public static void rellenarArray(int[] arr, int min, int max){
        for(int i = 0; i < arr.length; i++){
            arr[i] = randomInt(min, max);
        }
    }
    public static void rellenarArray(float[] arr, int min, int max){
        for(int i = 0; i < arr.length; i++){
            arr[i] = (float) randomDouble(min, max);
        }
    }
    public static void rellenarArray(double[] arr, int min, int max){
        for(int i = 0; i < arr.length; i++){
            arr[i] = randomDouble(min, max);
        }
    }
    public static void rellenarArray(boolean[] arr, int min, int max){
        for(int i = 0; i < arr.length; i++){
            arr[i] = randomBoolean();
        }
    }
    public static void rellenarArray(Object[] arr){
        for(int i = 0; i < arr.length; i++){
            arr[i] = new Object();
        }
    }
    
    //MÉTODOS DE MODIFICACIÓN
    /**
     * Ordena de forma ascendente un array de enteros.
     * @param arr Array
     * @return int[]
     */
    public static int[] ordenarArrayAsc(int[] arr){
        int longitud = arr.length;
        int ordenada[] = new int[longitud];
        int aux;
        
        for(int i = 0; i < longitud; i++){
            for(int j = i; j < longitud; j++){
                if(arr[i] > arr[j]){
                    aux = arr[i];
                    arr[i] = arr[j];
                    arr[j] = aux;
                }
            }
            
            ordenada[i] = arr[i];
        }
        
        return ordenada;
    }
    /**
     * Rota un número de posiciones x (pos) un array de enteros.
     * @param arr Array
     * @param pos Número de posiciones
     * @return int[]
     */
    public static int[] rotarArray(int[] arr, int pos){
        int longitud = arr.length;
        int[] arrRotado = new int[longitud];
        
        for(int i = 0; i < longitud; i++){
            if(i < pos){
                arrRotado[i] = arr[longitud+i-pos];
            }else{
                arrRotado[i] = arr[i-pos];
            }
        }
        
        return arrRotado;
    }
    /**
     * Ordena alfabéticamente un array de cadenas de texto (String).
     * @param arr Array
     */
    public static void ordenarArrayAlfabeticamente(String[] arr){
        int longitud = arr.length;
        
        for(int i = 0; i<longitud; i++){
            for(int j = i+1; j<longitud; j++){
                if(arr[i].compareTo(arr[j])>0){
                    String aux = arr[i];
                    arr[i] = arr[j];
                    arr[j] = aux;
                }
            }
        }
    }
    /**
     * Macacha los valores de un array (original) con los proporcionados (reemplazo). La longitud del original
     * debe ser igual o mayor a la del reemplazo. En caso contrario, el método lanzará un mensaje de error.
     * @param original Array original
     * @param reemplazo Array para reemplazar
     */
    public static void sustituirArray(int[] original, int[] reemplazo){
        if(original.length >= reemplazo.length){
            System.arraycopy(reemplazo, 0, original, 0, reemplazo.length);
        }else{
            System.out.println(TEXTO_ROJO + "El array original tiene una longitud menor a la del que lo va a reemplazar." + RESET_COLORES);
        }
    }
    /**
     * Elimina los elementos duplicados de un array de enteros.
     * @param arr Array
     */
    public static void eliminarDuplicados(int[] arr){
        int longitud = arr.length;
        int dupes = 0;
        
        for(int i = 0; i < longitud; i++){
            if(arr[i] == arr[i+1]){
                dupes++;
            }
        }
        
        int[] aux = new int[longitud - dupes];
        
        sustituirArray(arr, aux);
    }
    
    //GENERACIÓN DE DATOS TÍPICOS DE PERSONAS
    //Devolver nombres y apellidos al azar
    public static String devolverNombre(){
        return String.valueOf(Nombres.values()[randomInt(0,  Nombres.values().length)]);
    }
    public static String devolverApellido(){
        return String.valueOf(Apellidos.values()[randomInt(0,  Apellidos.values().length)]);
    }
    //Devolver sexo al azar
    public static char devolverSexo(){
        int random = randomInt(0, 1);
        if(random == 0) return 'H';
        else return 'M';
    }
    //Método auxiliar para devolver la letra correspondiente de un DNI
    private static char letraDNI(int dni){
        char[] lista = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        int resto = dni % 23;
        
        char letra = lista[resto];
        
        return letra;
    }
    /**
     * Genera un DNI de forma aleatoria
     * @return String
     */
    public static String generaDNI(){
        String dni = "" + (int) (Math.random()*89999999 + 10000000);
        
        char letra = ' ';
        
        letra = letraDNI(Integer.parseInt(dni));
        
        dni += letra;
        
        return dni;
    }
    /**
     * Comprueba si el DNI proporcionado es válido. Un DNI es válido si tiene una longitud de 9 caracteres,
     * los 8 primeros son números, y la letra es la correspondiente. Para determinar la letra, se hace el
     * módulo del número de 8 cifras del DNI entre 23. Dependiendo del resto, se asigna una letra. <br><br>
     * <table border="1">
     * <caption>Texto de ejemplo</caption>
     * <tr>
     * <th>RESTO</th>
       <td>0</td> <td>1</td> <td>2</td> <td>3</td> <td>4</td> <td>5</td> <td>6</td> <td>7</td> <td>8</td> <td>9</td> <td>10</td>
       <td>11</td> <td>12</td> <td>13</td> <td>14</td> <td>15</td> <td>16</td> <td>17</td> <td>18</td> <td>19</td> <td>20</td>
       <td>21</td> <td>22</td>
     * </tr>
     * <tr>
     * <th>LETRA</th>
     * <td>T</td> <td>R</td> <td>W</td> <td>A</td> <td>G</td> <td>M</td> <td>Y</td> <td>F</td> <td>P</td> <td>D</td> <td>X</td>
       <td>B</td> <td>N</td> <td>J</td> <td>Z</td> <td>S</td> <td>Q</td> <td>V</td> <td>H</td> <td>L</td> <td>C</td>
       <td>K</td> <td>E</td>
     * </tr>
     * </table>
     * @param dni DNI
     * @return True/False
     */
    public static boolean comprobarDNI(String dni){
        if(dni.length() == 9){
            String listaN = "0123456789";
            int len = dni.length();
            
            for(int i = 0; i < len - 1; i++){
                if(!listaN.contains("" + dni.charAt(i))) return false;
            }
            
            char ultima = dni.charAt(len - 1);
            String nDNI = dni.substring(0, 8);
            return ultima == letraDNI(Integer.parseInt(nDNI));
        }else{
            return false;
        }
    }
}