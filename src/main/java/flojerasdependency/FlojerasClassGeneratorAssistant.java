package flojerasdependency;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

//FLOJERAS CLASS GENERATOR ASSISTANT V0.3

/**
 *
 * @author Carlos Madrid
 */
public class FlojerasClassGeneratorAssistant extends FlojerasUtility{
    /* Posible optimización para agrupar las clases de ejemplo. A lo mejor le saco uso, a lo mejor no, no lo sé todavía
    private class ClaseEjemplo{
        private String nombre;
        private Atributo[] atributos;
        private boolean tieneToString;

        public ClaseEjemplo(String nombre, Atributo[] atributos, boolean tieneToString) {
            this.nombre = nombre;
            this.atributos = atributos;
            this.tieneToString = tieneToString;
        }
    }*/
    
    private class Atributo{
        private String visibilidad;
        private String tipo;
        private String nombre;
        private HashMap<String, Boolean> modificadores;
        private boolean tieneSetter;
        private boolean tieneGetter;
        
        public Atributo(){ 
            modificadores = new HashMap();
        }

        public Atributo(String visibilidad, String tipo, String nombre, HashMap<String, Boolean> modificadores, boolean tieneSetter, boolean tieneGetter) {
            this.visibilidad = visibilidad;
            this.tipo = tipo;
            this.nombre = nombre;
            this.modificadores = modificadores;
            this.tieneSetter = tieneSetter;
            this.tieneGetter = tieneGetter;
        }
        
        public void pedirDatos(){
            System.out.println("\nIntroduce el nombre:");
            do{
                System.out.print(">");
                nombre = pedirTexto();
            }while(nombre.isEmpty());
                    
            System.out.println("\nIntroduce el tipo de dato (int):");
            System.out.print(">");
            tipo = pedirTexto();
                    
            if(tipo.isEmpty()){
                this.tipo = "int";
            }
            
            System.out.println("\nIntroduce la visibilidad (private):");
            System.out.print(">");
            visibilidad = pedirTexto();
            
            if(visibilidad.isEmpty()) visibilidad = "private";
            
            System.out.println("\n¿Será static? (S/N)");
            do{
                System.out.print(">");
                char res = pedirLetra(true);
                if(res == 'S'){
                    modificadores.put("static", true);
                    break;
                }else if(res == 'N'){
                    modificadores.put("static", false);
                    break;
                }
            }while(true);
            
            System.out.println("\n¿Será final? (S/N)");
            do{
                System.out.print(">");
                char res = pedirLetra(true);
                if(res == 'S'){
                    modificadores.put("final", true);
                    break;
                }else if(res == 'N'){
                    modificadores.put("final", false);
                    break;
                }
            }while(true);
            
            System.out.println("\n¿Tendrá getter? (S/N)");
            do{
                System.out.print(">");
                char res = pedirLetra(true);
                if(res == 'S'){
                    tieneGetter = true;
                    break;
                }else if(res == 'N'){
                    tieneGetter = false;
                    break;
                }
            }while(true);
            
            //En caso de que el atributo sea final, se omite y se pone en false
            if(!modificadores.get("final")){
                System.out.println("\n¿Tendrá setter? (S/N)");
                do{
                    System.out.print(">");
                    char res = pedirLetra(true);
                    if(res == 'S'){
                        tieneSetter = true;
                        break;
                    }else if(res == 'N'){
                        tieneSetter = false;
                        break;
                    }
                }while(true);
            }else{
                tieneSetter = false;
            }
        }
        
        @Override
        public String toString() {
            String mods = "";
            for(String mod : modificadores.keySet()){
                if(modificadores.get(mod)) mods += '[' + mod + ']';
            }
            return '{' + nombre + "} -> " + "Visibilidad: " + visibilidad + ", Tipo: " + tipo + ", Modificadores: " + mods + ", Tiene setter: " + tieneSetter + ", Tiene getter: " + tieneGetter;
        }
    }
    
    public FlojerasClassGeneratorAssistant(){}
    
    public void iniciar(){
        int opcion;
        boolean terminado = false;
        
        //Variables para almacenar los datos de la clase
        String nombre = "ClaseDeEjemplo";
        boolean tieneToString = false;
        Atributo[] atributos = new Atributo[5];
        
        System.out.println("//----- FLOJERAS CLASS GENERATOR ASSISTANT V0.2.5 -----//\n");
        do{
            System.out.println("\nElija una opción:");
            System.out.println("[1] Declarar nombre clase");
            System.out.println("[2] Introducir atributo");
            System.out.println("[3] Modificar atributo");
            System.out.println("[4] Mostrar atributos");
            if(!tieneToString) System.out.println("[5] Añadir método toString");
            else System.out.println("[5] Quitar método toString");
            System.out.println("[6] Generar clase");
            System.out.println("[7] Generar clase de prueba");
            System.out.println("[8] Detener\n");
            
            do{
                System.out.print(">");
                opcion = pedirInt(false);
            }while(opcion < 1 || opcion > 8);
            
            switch(opcion){
                case 1:
                    System.out.println("\nIntroduce el nombre de la clase:");
                    do{
                        System.out.print(">");
                        nombre = pedirTexto();
                    }while(nombre.isEmpty());
                    
                    break;
                case 2:
                    int pos = devolverNumEspaciosOcupados(atributos);
                    atributos[pos] = new Atributo();
                    atributos[pos].pedirDatos();
                    break;
                case 3:
                    if(devolverNumEspaciosOcupados(atributos) != 0){
                        System.out.println("Elige el atributo a modificar:");
                        for (int i = 0; i < atributos.length; i++) {
                            if(atributos[i] != null){
                                System.out.println((i+1) + "º: " + atributos[i].nombre);
                            }
                        }

                        do{
                            System.out.print(">");
                            opcion = pedirInt(false);
                            opcion--;
                            if(opcion >= 0){
                                if(atributos[opcion] != null){
                                    Atributo atrAux = atributos[opcion];
                                    //Menú de edición del atributo
                                    int opcionEdit;
                                    boolean continuar = true;
                                    String input;
                                    System.out.println("\n//----- MENÚ DE EDICIÓN DEL ATRIBUTO -----//");
                                    do{
                                        System.out.printf("[1] Cambiar visibilidad -> %s", atrAux.visibilidad);
                                        System.out.printf("\n[2] Cambiar tipo -> %s", atrAux.tipo);
                                        System.out.printf("\n[3] Cambiar nombre -> %s", atrAux.nombre);
                                        System.out.printf("\n[4] Alternar modificador static -> %s", atrAux.modificadores.get("static"));
                                        System.out.printf("\n[5] Alternar modificador final -> %s", atrAux.modificadores.get("final"));
                                        System.out.printf("\n[6] Alternar setter -> %s", atrAux.tieneSetter);
                                        System.out.printf("\n[7] Alternar getter -> %s", atrAux.tieneGetter);
                                        System.out.println("\n[8] Guardar cambios");
                                        System.out.println("[9] Deshacer cambios");
                                        System.out.print(">");
                                        opcionEdit = pedirInt(true);
                                        switch(opcionEdit){
                                            case 1:
                                                System.out.println("Introduce la nueva visibilidad: ");
                                                do{
                                                    System.out.print(">");
                                                    input = pedirTexto();
                                                }while(input.isEmpty());
                                                
                                                atrAux.visibilidad = input;
                                                break;
                                            case 2:
                                                System.out.println("Introduce el nuevo tipo: ");
                                                do{
                                                    System.out.print(">");
                                                    input = pedirTexto();
                                                }while(input.isEmpty());
                                                
                                                atrAux.tipo = input;
                                                break;
                                            case 3:
                                                System.out.println("Introduce el nuevo nombre: ");
                                                do{
                                                    System.out.print(">");
                                                    input = pedirTexto();
                                                }while(input.isEmpty());
                                                
                                                atrAux.nombre = input;
                                                break;
                                            case 4:
                                                atrAux.modificadores.replace("static", !atrAux.modificadores.get("static"));
                                                break;
                                            case 5:
                                                atrAux.modificadores.replace("final", !atrAux.modificadores.get("final"));
                                                break;
                                            case 6:
                                                atrAux.tieneSetter = !atrAux.tieneSetter;
                                                break;
                                            case 7:
                                                atrAux.tieneGetter = !atrAux.tieneGetter;
                                                break;
                                            case 8:
                                                atributos[opcion] = atrAux;
                                            case 9:
                                                continuar = false;
                                                break;
                                            default:
                                                System.out.println("Opción inválida");
                                        }
                                    }while(continuar);
                                    break;
                                }
                            }
                        }while(true);
                    }else{
                        System.out.println("No se ha añadido ningún atributo.");
                    }
                    break;
                case 4:
                    for(Atributo atr: atributos){
                        if(atr != null){
                            System.out.println(atr);
                        }
                    }
                    break;
                case 5:
                    tieneToString = !tieneToString;
                    break;
                case 6:
                    generarClase(nombre, atributos, tieneToString);
                    break;
                case 7:
                    System.out.println("\nElige una de las siguientes opciones:");
                    System.out.println("[1] Persona");
                    System.out.println("[2] Animal");
                    do{
                        System.out.print(">");
                        opcion = pedirInt(false);
                    }while(opcion < 1 || opcion > 2);
                    
                    HashMap<String, Boolean> modsDef = new HashMap();
                    modsDef.put("static", false);
                    modsDef.put("final", false);
                    switch(opcion){
                        case 1:
                            Atributo[] atrPersona = new Atributo[4];
                            atrPersona[0] = new Atributo("private", "String", "nombre", modsDef, true, true);
                            atrPersona[1] = new Atributo("private", "String", "apellidos", modsDef, true, true);
                            atrPersona[2] = new Atributo("private", "int", "edad", modsDef, true, true);
                            atrPersona[3] = new Atributo("private", "int", "telefono", modsDef, true, true);
                            generarClase("Persona", atrPersona, true);
                            break;
                        case 2:
                            Atributo[] atrAnimal = new Atributo[4];
                            atrAnimal[0] = new Atributo("private", "String", "nombre", modsDef, true, true);
                            atrAnimal[0] = new Atributo("private", "String", "especie", modsDef, true, true);
                            atrAnimal[1] = new Atributo("private", "String", "nombreCientifico", modsDef, true, true);
                            atrAnimal[2] = new Atributo("private", "String", "alimentacion", modsDef, true, true);
                            atrAnimal[3] = new Atributo("private", "String", "reproduccion", modsDef, true, true);
                            generarClase("Animal", atrAnimal, true);
                    }
                    break;
                case 8:
                    terminado = true;
            }
        }while(!terminado);
        
        System.out.println(TEXTO_VERDE + "Fin de la ejecución." + RESET_COLORES);
    }
    
    private void generarClase(String nombre, Atributo[] atributos, boolean tieneToString){
        File clase = new File(nombre + ".java");
        try {
            if(clase.createNewFile()){
                System.out.println(TEXTO_VERDE + "Archivo " + clase.getName() + " creado en la ruta " + clase.getAbsolutePath() + RESET_COLORES);

                FileWriter output = new FileWriter(clase);

                //Nombre de la clase
                output.write("public class " + nombre + " {\n");
                //Atributos
                output.write("    //Atributos\n");
                int numAtr = devolverNumEspaciosOcupados(atributos);
                for(int i = 0; i < numAtr; i++){
                    //Modificadores de los atributos
                    String mods = " ";
                    HashMap<String, Boolean> modAux = atributos[i].modificadores;
                    for(String mod : modAux.keySet()){
                        if(modAux.get(mod)) mods += mod + ' ';
                    }
                    output.write("    " + atributos[i].visibilidad
                                        + mods
                                        + atributos[i].tipo + " "
                                        + atributos[i].nombre + ";\n");
                }
                //Constructor
                output.write("\n    //Constructor");
                output.write("\n    public " + nombre + "(");
                for(int i = 0; i < numAtr; i++){
                    output.write(atributos[i].tipo + " " + atributos[i].nombre);
                    if(i+1 != numAtr){
                        output.write(", ");
                    }
                }
                output.write("){\n");
                String aux;
                for(int i = 0; i < numAtr; i++){
                    aux = atributos[i].nombre;
                    output.write("        this." + aux + " = " + aux + ";\n");
                }
                output.write("    }\n");
                //Getters y setters
                output.write("\n    //Getters y setters");
                for(int i = 0; i < numAtr; i++){
                    aux = atributos[i].nombre; //Facilita la vida cuando tienes que escribir mucho para referenciar algo xd
                    //Getter
                    if(atributos[i].tieneGetter){
                        output.write("\n    public " + atributos[i].tipo + " get" 
                                                   + aux.substring(0, 1).toUpperCase() + aux.substring(1, aux.length())+ "(){\n");
                        output.write("        return " + aux + ";\n    }\n");
                    }
                    //Setter
                    if(atributos[i].tieneSetter){
                        output.write("\n    public void set" + aux.substring(0, 1).toUpperCase() + aux.substring(1, aux.length()) + "(" + atributos[i].tipo + " " + aux + "){\n");
                        output.write("        this." + aux + " = " + aux + ";\n    }\n");
                    }
                }
                //toString
                if(tieneToString){
                    output.write("\n    @Override\n    public String toString(){\n");
                    output.write("        return \"" + nombre + "{\" + ");
                    for(int i = 0; i < numAtr; i++){
                        aux = atributos[i].nombre;
                        if(i+1 != numAtr){
                            output.write("\"");
                        }
                        output.write(aux + "=\" + " + aux);
                        if(i+1 != numAtr){
                            output.write( " + \", ");
                        }
                    }
                    output.write(" + '}';\n    }");
                }
                output.write("\n}"); //Final del archivo
                output.close();
                System.out.println(TEXTO_VERDE + "Archivo generado con éxito." + RESET_COLORES);
            }else{
                System.out.println(TEXTO_ROJO + "El archivo ya existe" + RESET_COLORES);
            }
        } catch (IOException ex) {
            System.out.println(TEXTO_ROJO + "Ha ocurrido un error." + RESET_COLORES);
        }
    }
}