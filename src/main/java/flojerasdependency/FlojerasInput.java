package flojerasdependency;

import java.util.Scanner;

public class FlojerasInput implements AutoCloseable{
    private Scanner sc;

    public FlojerasInput(){
        sc = new Scanner(System.in, "ISO-8859-1");
    }

    /**
     * Pide al usuario una cadena de texto. Si el parámetro de permitirContenidoVacio es falso, se preguntará al usuario
     * indefinidamente mientras no introduzca nada. Adicionalmente, se le puede pasar un String para que aparezca a la hora
     * de preguntar información al usuario, indicándole cuándo se requiere introducir información. Si se pasa un String vacío,
     * no se mostrará nada.
     * @param caracterInput Carácter
     * @param permitirContenidoVacio Permitir no intoducir nada
     * @return String
     */
    public String pedirTexto(String caracterInput, boolean permitirContenidoVacio){
        String respuesta;

        do{
            if(!caracterInput.isEmpty()) System.out.print(caracterInput);
            respuesta = sc.nextLine();
        }while(respuesta.isEmpty() && !permitirContenidoVacio);

        return respuesta;
    }

    /**
     * Pide un número entero al usuario. Si la opción de positivo es verdadera, el usuario tendrá que introducir
     * de forma obligatoria un número positivo. Adicionalmente, se le puede pasar un String para que aparezca a la hora
     * de preguntar información al usuario, indicándole cuándo se requiere introducir información. Si se pasa un String vacío,
     * no se mostrará nada.
     * @param caracterInput Carácter
     * @param positivo Indica si sólo se permiten números positivos
     * @return int
     */
    public int pedirInt(String caracterInput, boolean positivo){
        int x;

        while(true){
            if(!caracterInput.isEmpty()) System.out.print(caracterInput);
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
                System.out.println("No se ha introducido un número válido.");
            } finally {
                sc.nextLine();
            }
        }
        return x;
    }

    /**
     * Pide un float al usuario. Si la opción de positivo es verdadera, el usuario tendrá que introducir
     * de forma obligatoria un número positivo. Adicionalmente, se le puede pasar un String para que aparezca a la hora
     * de preguntar información al usuario, indicándole cuándo se requiere introducir información. Si se pasa un String vacío,
     * no se mostrará nada.
     * @param caracterInput Carácter
     * @param positivo Indica si sólo se permiten números positivos
     * @return float
     */
    public float pedirFloat(String caracterInput, boolean positivo){
        float x;

        while(true){
            if(!caracterInput.isEmpty()) System.out.print(caracterInput);
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
                System.out.println("No se ha introducido un número válido.");
            } finally {
                sc.nextLine();
            }
        }
        return x;
    }

    /**
     * Pide un double al usuario. Si la opción de positivo es verdadera, el usuario tendrá que introducir
     * de forma obligatoria un número positivo. Adicionalmente, se le puede pasar un String para que aparezca a la hora
     * de preguntar información al usuario, indicándole cuándo se requiere introducir información. Si se pasa un String vacío,
     * no se mostrará nada.
     * @param caracterInput Carácter
     * @param positivo Indica si sólo se permiten números positivos
     * @return double
     */
    public double pedirDouble(String caracterInput, boolean positivo){
        double x;

        while(true){
            if(!caracterInput.isEmpty()) System.out.print(caracterInput);
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
                System.out.println("No se ha introducido un número válido.");
            } finally {
                sc.nextLine();
            }
        }

        return x;
    }

    /**
     * Pide una letra al usuario. Si la opción de devolverMayus es verdadera, devolverá la letra
     * en mayúscula. En caso contrario, la devolverá en minúscula. Adicionalmente, se le puede pasar un String para que aparezca a la hora
     * de preguntar información al usuario, indicándole cuándo se requiere introducir información. Si se pasa un String vacío,
     * no se mostrará nada.
     * @param caracterInput Carácter
     * @param devolverMayus Devolver en mayúscula
     * @return char
     */
    public char pedirLetra(String caracterInput, boolean devolverMayus){
        String input;
        char c = '@'; //Valor basura porque sí

        do{
            try {
                input = pedirTexto(caracterInput, false);
                if(devolverMayus){
                    c = input.toUpperCase().charAt(0);
                }else{
                    c = input.toLowerCase().charAt(0);
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }while(c < 'A' || c > 'z');

        return c;
    }

    @Override
    public void close() {
        sc.close();
    }
}
