package flojerasdependency;

//FLOJERAS PASSWORD GENERATOR V0.1

/**
 *
 * @author Carlos Madrid
 */
public class FlojerasPasswordGenerator extends FlojerasUtility{
    public static String generarContrasena(int longitud, boolean tieneMayus, boolean tieneNumeros, boolean tieneSimbolos){
        String passwd = "";
        String lista = introducirMinusculas();
        if(tieneMayus) lista += introducirMayusculas();
        if(tieneNumeros) lista += introducirNumeros();
        if(tieneSimbolos) lista += introducirSimbolos();
        
        for(int i = 0; i < longitud; i++){
            passwd += "" + lista.charAt(randomInt(1, lista.length()) - 1);
        }
        
        return passwd;
    }
    
    private static String introducirMinusculas(){
        String lista = "";
        for(int i = 97; i <= 122; i++){
            lista += "" + (char) i;
        }
        
        return lista;
    }
    
    private static String introducirMayusculas(){
        String lista = "";
        for(int i = 65; i <= 90; i++){
            lista += "" + (char) i;
        }
        
        return lista;
    }
    
    private static String introducirNumeros(){
        String lista = "";
        for(int i = 48; i <= 57; i++){
            lista += "" + (char) i;
        }
        
        return lista;
    }
    
    private static String introducirSimbolos(){
        String lista = "";
        for(int i = 33; i <= 47; i++){
            lista += "" + (char) i;
        }
        
        return lista;
    }
}
