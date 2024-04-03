package cmj;

import java.util.Scanner;

/*
    Flojeras Dependency es una librería que me he hecho a modo de hacer el trabajo más fácil, 
    agilizando el código y facilitando las pruebas del programa. Para usarla, se ha de extender
    de esta clase.
*/

//Sugerencias anotadas
//Ordenar alfabéticamente y ordenar números
//Desplazar números x posiciones
//Eliminar duplicados

/**
 *
 * @author Carlos Madrid
 */

public class FlojerasDependency {
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
    public static void print(int a){
        System.out.println(a);
    }
    public static void print(float a){
        System.out.println(a);
    }
    public static void print(double a){
        System.out.println(a);
    }
    public static void print(boolean x){
        System.out.println(x);
    }
    public static void print(char c){
        System.out.println(c);
    }
    public static void print(Object o){
        System.out.println(o);
    }
    public static void print(String msg){
        System.out.println(msg);
    }
    
    //Imprimir por consola con colores
    public static void print(int a, String colorTexto, String colorFondo){
        System.out.println(colorTexto + colorFondo + a);
    }
    public static void print(float a, String colorTexto, String colorFondo){
        System.out.println(colorTexto + colorFondo + a);
    }
    public static void print(double a, String colorTexto, String colorFondo){
        System.out.println(colorTexto + colorFondo + a);
    }
    public static void print(boolean x, String colorTexto, String colorFondo){
        System.out.println(colorTexto + colorFondo + x);
    }
    public static void print(char c, String colorTexto, String colorFondo){
        System.out.println(colorTexto + colorFondo + c);
    }
    public static void print(Object o, String colorTexto, String colorFondo){
        System.out.println(colorTexto + colorFondo + o);
    }
    public static void print(String msg, String colorTexto, String colorFondo){
        System.out.println(colorTexto + colorFondo + msg);
    }
    
    //Métodos de input
    /**
     * Pide un número entero al usuario. Si la opción de positivo es verdadera, el usuario tendrá que introducir
     * de forma obligatoria un número positivo
     * @param positivo
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
        
        return x;
    }
    /**
     * Pide un float al usuario. Si la opción de positivo es verdadera, el usuario tendrá que introducir
     * de forma obligatoria un número positivo
     * @param positivo
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
        
        return x;
    }
    /**
     * Pide un double al usuario. Si la opción de positivo es verdadera, el usuario tendrá que introducir
     * de forma obligatoria un número positivo
     * @param positivo
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
        
        return x;
    }
    /**
     * Pide un char al usuario. Si la opción de devolverMayus es verdadera, devolverá el char
     * en mayúscula. En caso contrario, lo devolverá en minúscula.
     * @param devolverMayus
     * @return
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
        
        return c;
    }
    
    //Métodos matemáticos
    /**
     * Devuelve un número x elevado a un exponente y. Es el Math.pow(), pero sin nombres raros.
     * @param base
     * @param exp
     * @return Número elevado al exponente proporcionado
     */
    public static int elevar(int base, int exp){
        return (int) Math.pow(base, exp);
    }
    public static double raizCuadrada(int a){
        return Math.sqrt(a);
    }
    /**
     *
     * @param max
     * @param min
     * @return Número entero al azar entre un mínmo y máximo proporcionado
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
     *
     * @param max
     * @param min
     * @return Número con decimales al azar entre un mínmo y máximo proporcionado
     */
    public static double randomDouble(float min, float max){
        return (Math.random() * (max - min + 1)) + min;
    }
    public static char randomChar(boolean mayus){
        char c;
        
        if(mayus) c = (char) randomInt('A', 'Z');
        else c = (char) randomInt('a', 'z');
        
        return c;
    }
    public static boolean randomBoolean(){
        int random = randomInt(0, 1);
        
        return random == 0;
    }
    public static double[] randomDoubleArr(int min, int max, int cantidad){
        double[] arr = new double[cantidad];
        
        for(int i = 0; i < arr.length; i++){
            arr[i] = randomInt(min, max);
        }
        
        return arr;
    }
    public static double truncar(double num, int decimales){
        int dec = elevar(10, decimales);
        double truncado = (int) (num * dec);
        return truncado /= dec;
    }
    public static void ecuacionSegundoGrado(int a, int b, int c){
        double[] soluciones = new double[2];
            double parteRaiz = Math.sqrt(Math.pow(b, 2) - (4*a*c));
            soluciones[0] = (-b + parteRaiz) / (2*a);
            soluciones[1] = (-b - parteRaiz) / (2*a);
            print("a = " + a + " b = " + b + " c = " + c);
            print("Solución 1 = " + soluciones[0]);
            print("Solución 2 = " + soluciones[1]);
    }
    
    //Métodos de iteración (principalmente para solo ver el contenido)
    //Recorrer array
    public static void recorrerArray(int[] arr){
        for(int i = 0; i < arr.length; i++){
            print(arr[i]);
        }
    }
    public static void recorrerArray(float[] arr){
        for(int i = 0; i < arr.length; i++){
            print(arr[i]);
        }
    }
    public static void recorrerArray(double[] arr){
        for(int i = 0; i < arr.length; i++){
            print(arr[i]);
        }
    }
    public static void recorrerArray(boolean[] arr){
        for(int i = 0; i < arr.length; i++){
            print(arr[i]);
        }
    }
    /**
     * En el caso de los objetos, se supone que los objetos del array a recorrer, tienen un método toString,
     * para que en vez de que salga la dirección de memoria, salgan los datos del objeto almacenado.
     * @param arr
     */
    public static void recorrerArray(Object[] arr){
        for(int i = 0; i < arr.length; i++){
            print(arr[i]);
        }
    }
    public static void recorrerArray(String[] arr){
        for(int i = 0; i < arr.length; i++){
            print(arr[i]);
        }
    }
    
    //Recorrer matrices
    public static void recorrerMatriz(int[][] maiz){
        for(int i = 0; i < maiz.length; i++){
            for(int j = 0; j < maiz[i].length; j++){
                print(maiz[i][j]);
            }
        }
    }
    public static void recorrerMatriz(float[][] maiz){
        for(int i = 0; i < maiz.length; i++){
            for(int j = 0; j < maiz[i].length; j++){
                print(maiz[i][j]);
            }
        }
    }
    public static void recorrerMatriz(double[][] maiz){
        for(int i = 0; i < maiz.length; i++){
            for(int j = 0; j < maiz[i].length; j++){
                print(maiz[i][j]);
            }
        }
    }
    public static void recorrerMatriz(boolean[][] maiz){
        for(int i = 0; i < maiz.length; i++){
            for(int j = 0; j < maiz[i].length; j++){
                print(maiz[i][j]);
            }
        }
    }
    /**
     * En el caso de los objetos, se supone que los objetos de la matriz a recorrer, tienen un método toString,
     * para que en vez de que salga la dirección de memoria, salgan los datos del objeto almacenado.
     * @param maiz
     */
    public static void recorrerMatriz(Object[][] maiz){
        for(int i = 0; i < maiz.length; i++){
            for(int j = 0; j < maiz[i].length; j++){
                print(maiz[i][j]);
            }
        }
    }
    public static void recorrerMatriz(String[][] maiz){
        for(int i = 0; i < maiz.length; i++){
            for(int j = 0; j < maiz[i].length; j++){
                print(maiz[i][j]);
            }
        }
    }
    
    //Relleno de datos al azar
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
            int random = randomInt(0, 1);
            if(random == 0) arr[i] = false;
            else arr[i] = true;
        }
    }
    public static void rellenarArray(Object[] arr){
        for(int i = 0; i < arr.length; i++){
            arr[i] = new Object();
        }
    }
    
    //Generación de datos típicos de personas
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
    //Generar DNI y comprobrar que es válido
    public static String generaDNI(){
        String dni = "" + (int) (Math.random()*89999999 + 10000000);
        
        int suma = 0;
        char letra = ' ';
        
        for(int i = 0; i < dni.length(); i++){
            suma += dni.charAt(i);
        }
        
        suma %= 23;
        
        switch(suma){
            case 0 -> letra = 'T';
            case 1 -> letra = 'R';
            case 2 -> letra = 'W';
            case 3 -> letra = 'A';
            case 4 -> letra = 'G';
            case 5 -> letra = 'M';
            case 6 -> letra = 'Y';
            case 7 -> letra = 'F';
            case 8 -> letra = 'P';
            case 9 -> letra = 'D';
            case 10 -> letra = 'X';
            case 11 -> letra = 'B';
            case 12 -> letra = 'N';
            case 13 -> letra = 'J';
            case 14 -> letra = 'Z';
            case 15 -> letra = 'S';
            case 16 -> letra = 'Q';
            case 17 -> letra = 'V';
            case 18 -> letra = 'H';
            case 19 -> letra = 'L';
            case 20 -> letra = 'C';
            case 21 -> letra = 'K';
            case 22 -> letra = 'E';
        }
        
        dni += letra;
        
        return dni;
    }
    public static boolean comprobarDNI(String dni){
        if(dni.length() == 9){
            String listaN = "0123456789";
            int len = dni.length();
            for(int i = 0; i < len - 1; i++){
                if(!listaN.contains("" + dni.charAt(i))) return false;
            }

            char ultima = dni.charAt(len - 1);
            return ultima >= 'A' && ultima <= 'Z';
        }else{
            return false;
        }
    }
}
