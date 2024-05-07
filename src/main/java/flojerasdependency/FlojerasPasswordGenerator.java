package flojerasdependency;

//FLOJERAS PASSWORD GENERATOR V0.2

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Carlos Madrid
 */
public class FlojerasPasswordGenerator extends FlojerasUtility{
    /**
     * Genera una contraseña de forma aleatoria según le especifique el usuario.
     * @param longitud Longitud
     * @param tieneMayus Tendrá mayúsculas
     * @param tieneNumeros Tendrá números
     * @param tieneSimbolos Tendrá símbolos
     * @return String
     */
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
    
    /**
     * Cifra con el algoritmo SHA-256 la contraseña proporcionada. Dicho algoritmo es irreversible, haciendo que sea útil
     * a la hora de almacenar contraseñas de usuarios para una página web.
     * @param contrasena Contraseña
     * @return String
     */
    public static String cifrarContrasena(String contrasena){
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(contrasena.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {}
        
        return null;
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
