package flojerasdependency;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

//FLOJERAS CLASS GENERATOR ASSISTANT V0.4.1

/**
 *
 * @author Carlos Madrid
 */
public class FlojerasClassGeneratorAssistant extends FlojerasUtility{
    
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

        public Atributo(String nombre) {
            this.nombre = nombre;
        }
        
        public Atributo(String visibilidad, String tipo, String nombre){
            this.visibilidad = visibilidad;
            this.tipo = tipo;
            this.nombre = nombre;
            modificadores = new HashMap();
            modificadores.put("final", false);
            modificadores.put("static", false);
            this.tieneSetter = true;
            this.tieneGetter = true;
        }
        
        public Atributo(String visibilidad, String tipo, String nombre, boolean seraFinal, boolean seraStatic, boolean tieneSetter, boolean tieneGetter) {
            this.visibilidad = visibilidad;
            this.tipo = tipo;
            this.nombre = nombre;
            modificadores = new HashMap();
            modificadores.put("final", seraFinal);
            modificadores.put("static", seraStatic);
            this.tieneSetter = tieneSetter;
            this.tieneGetter = tieneGetter;
        }
        
        public void pedirDatos(){
            System.out.println("\nIntroduce el nombre:");
            nombre = fInput.pedirTexto(">", false);

            System.out.println("\nIntroduce el tipo de dato (int):");
            tipo = fInput.pedirTexto(">", true);

            if(tipo.isEmpty()){
                this.tipo = "int";
            }

            System.out.println("\nIntroduce la visibilidad (private):");
            visibilidad = fInput.pedirTexto(">", true);

            if(visibilidad.isEmpty()) visibilidad = "private";

            System.out.println("\n¿Será static? (S/N)");
            do{
                char res = fInput.pedirLetra(">", true);
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
                char res = fInput.pedirLetra(">", true);
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
                char res = fInput.pedirLetra(">", true);
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
                    char res = fInput.pedirLetra(">", true);
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

        public String getVisibilidad() {
            return visibilidad;
        }

        public String getTipo() {
            return tipo;
        }

        public String getNombre() {
            return nombre;
        }

        public HashMap<String, Boolean> getModificadores() {
            return modificadores;
        }

        public boolean tieneSetter() {
            return tieneSetter;
        }

        public boolean tieneGetter() {
            return tieneGetter;
        }
        
        @Override
        public String toString() {
            String mods = "";
            for(String mod : modificadores.keySet()){
                if(modificadores.get(mod)) mods += '[' + mod + ']';
            }
            return '{' + nombre + "} -> " + "Visibilidad: " + visibilidad + ", Tipo: " + tipo + ", Modificadores: " + mods + ", Tiene setter: " + tieneSetter + ", Tiene getter: " + tieneGetter;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Atributo other = (Atributo) obj;
            return Objects.equals(this.nombre.toLowerCase(), other.nombre.toLowerCase());
        }
    }

    private FlojerasInput fInput;
    
    public FlojerasClassGeneratorAssistant(){
        fInput = new FlojerasInput();
    }
    
    public void iniciar(){
        int opcion;
        boolean terminado = false;
        
        //Variables para almacenar los datos de la clase
        String nombreClase = "ClaseDeEjemplo";
        boolean tieneToString = false;
        HashMap<String, Atributo> atributos = new HashMap<String, Atributo>();
        
        System.out.println("//----- FLOJERAS CLASS GENERATOR ASSISTANT V0.4.1 -----//\n");
        do{
            System.out.println("\nElija una opción:");
            System.out.printf("[1] Declarar nombre clase (%s)\n", nombreClase);
            System.out.println("[2] Introducir atributo");
            System.out.println("[3] Modificar atributo");
            System.out.println("[4] Mostrar atributos");
            if(!tieneToString) System.out.println("[5] Añadir método toString");
            else System.out.println("[5] Quitar método toString");
            System.out.println("[6] Generar clase");
            System.out.println("[7] Generar clase de prueba");
            System.out.println("[8] Guardar clase como XML");
            System.out.println("[9] Cargar clase");
            System.out.println("[10] Detener\n");
            
            do{
                opcion = fInput.pedirInt(">" ,false);
            }while(opcion < 1 || opcion > 10);
            
            switch(opcion){
                case 1:
                    System.out.println("\nIntroduce el nombre de la clase:");
                    nombreClase = fInput.pedirTexto(">", false);
                    
                    break;
                case 2:
                    Atributo nuevoAtributo = new Atributo();
                    nuevoAtributo.pedirDatos();
                    atributos.put(nuevoAtributo.nombre ,nuevoAtributo);
                    break;
                case 3:
                    if(!atributos.isEmpty()){
                        System.out.println("Elige el atributo a modificar:");
                        for (Atributo attr : atributos.values()) {
                            System.out.println(" - " + attr.nombre);
                        }

                        do{
                            String opcionAttr = fInput.pedirTexto(">", false);
                            Atributo atrAux = atributos.get(opcionAttr);
                            if(atrAux != null){
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
                                    opcionEdit = fInput.pedirInt(">" , false);
                                    switch(opcionEdit){
                                        case 1:
                                            System.out.println("Introduce la nueva visibilidad: ");
                                            input = fInput.pedirTexto(">", false);

                                            atrAux.visibilidad = input;
                                            break;
                                        case 2:
                                            System.out.println("Introduce el nuevo tipo: ");
                                            input = fInput.pedirTexto(">", false);

                                            atrAux.tipo = input;
                                            break;
                                        case 3:
                                            System.out.println("Introduce el nuevo nombre: ");
                                            input = fInput.pedirTexto(">", false);

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
                                            atributos.put(opcionAttr, atrAux);
                                        case 9:
                                            continuar = false;
                                            break;
                                        default:
                                            System.out.println("Opción inválida");
                                    }
                                }while(continuar);
                                break;
                            }
                        }while(true);
                    }else{
                        System.out.println("No se ha añadido ningún atributo.");
                    }
                    break;
                case 4:
                    for(Atributo atr: atributos.values()){
                        if(atr != null){
                            System.out.println(atr);
                        }
                    }
                    break;
                case 5:
                    tieneToString = !tieneToString;
                    break;
                case 6:
                    generarClase(nombreClase, atributos, tieneToString);
                    break;
                case 7:
                    System.out.println("\nElige una de las siguientes opciones:");
                    System.out.println("[1] Persona");
                    System.out.println("[2] Animal");
                    do{
                        opcion = fInput.pedirInt(">", false);
                    }while(opcion < 1 || opcion > 2);
                    
                    switch(opcion){
                        case 1:
                            HashMap<String, Atributo> atrPersona = new HashMap<>();
                            atrPersona.put("nombre", new Atributo("private", "String", "nombre", false, false, true, true));
                            atrPersona.put("apellidos", new Atributo("private", "String", "apellidos", false, false, true, true));
                            atrPersona.put("apellidos", new Atributo("private", "int", "apellidos", false, false, true, true));
                            atrPersona.put("telefono", new Atributo("private", "int", "telefono", false, false, true, true));
                            generarClase("Persona", atrPersona, true);
                            break;
                        case 2:
                            HashMap<String, Atributo> atrAnimal = new HashMap<>();
                            atrAnimal.put("nombre", new Atributo("private", "String", "nombre", false, false, true, true));
                            atrAnimal.put("especie", new Atributo("private", "String", "especie", false, false, true, true));
                            atrAnimal.put("nombreCientifico", new Atributo("private", "String", "nombreCientifico", false, false, true, true));
                            atrAnimal.put("alimentacion", new Atributo("private", "String", "alimentacion", false, false, true, true));
                            atrAnimal.put("reproduccion", new Atributo("private", "String", "reproduccion", false, false, true, true));
                            generarClase("Animal", atrAnimal, true);
                    }
                    break;
                case 8:
                    try {
                        //Creación del documento
                        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                        Document doc = dBuilder.newDocument();
                        
                        //Elemento raíz
                        Element raiz = doc.createElement("clase");
                        doc.appendChild(raiz);
                        
                        //Nombre
                        Element eNombreClase = doc.createElement("nombreClase");
                        eNombreClase.appendChild(doc.createTextNode(nombreClase));
                        raiz.appendChild(eNombreClase);
                        
                        //Atributos
                        Element eAtributos = doc.createElement("atributos");
                        for(Atributo attr : atributos.values()){
                            if(attr != null){
                                Element a = doc.createElement("atributo");
                                Element eNombreAttr = doc.createElement("nombre");
                                eNombreAttr.appendChild(doc.createTextNode(attr.getNombre()));
                                a.appendChild(eNombreAttr);
                                Element tipoAttr = doc.createElement("tipo");
                                tipoAttr.appendChild(doc.createTextNode(attr.getTipo()));
                                a.appendChild(tipoAttr);
                                Element scopeAttr = doc.createElement("visibilidad");
                                scopeAttr.appendChild(doc.createTextNode(attr.getVisibilidad()));
                                a.appendChild(scopeAttr);
                                //Modificadores si tiene
                                Element modAttr = doc.createElement("modificadores");
                                for(String valor : attr.getModificadores().keySet()){
                                    if(attr.getModificadores().get(valor)){
                                        Element eStatic = doc.createElement(valor);
                                        modAttr.appendChild(eStatic);
                                    }
                                }
                                a.appendChild(modAttr);
                                if(attr.tieneGetter){
                                    a.appendChild(doc.createElement("tieneGetter"));
                                }
                                if(attr.tieneSetter){
                                    a.appendChild(doc.createElement("tieneSetter"));
                                }
                                eAtributos.appendChild(a);
                            }
                        }
                        
                        raiz.appendChild(eAtributos);
                        
                        if(tieneToString){
                            raiz.appendChild(doc.createElement("tieneToString"));
                        }
                        
                        //Se escribe en el archivo XML
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        //Necesario para que salga con un formato
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                        DOMSource source = new DOMSource(doc);
                        StreamResult result = new StreamResult(new File(nombreClase + ".xml"));

                        transformer.transform(source, result);
                    } catch(Exception e) {
                        e.printStackTrace(System.out);
                    }
                    break;
                case 9:
                    //Se filtran los archivos del directorio actual
                    File directorioActual = new File(".");
                    File[] opciones;
                    File archivoSeleccionado;
                    FilenameFilter filter = (File f, String name) -> name.endsWith(".xml");
                    //Se listan los archivos
                    opciones = directorioActual.listFiles(filter);
                    for(int i = 0; i < opciones.length; i++){
                        File archivo = opciones[i];
                        System.out.printf("[%s] - " + archivo.getName() + "\n", i+1);
                    }
                    do{
                        opcion = fInput.pedirInt(">", false);
                    }while(opcion < 1 || opcion > opciones.length);
                    opcion--;
                    
                    archivoSeleccionado = opciones[opcion];

                    if(archivoSeleccionado != null){
                        //Se lee el archivo y carga los valores
                        try {
                            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                            Document doc = dBuilder.parse(archivoSeleccionado);

                            //Nombre de la clase
                            Element raiz = (Element) doc.getChildNodes().item(0);
                            nombreClase = raiz.getElementsByTagName("nombreClase").item(0).getTextContent();
                            
                            //Atributos
                            Element atributosAux = (Element) raiz.getElementsByTagName("atributos").item(0);
                            NodeList atributosList = atributosAux.getElementsByTagName("atributo");
                            for(int i = 0; i < atributosList.getLength(); i++){
                                Element atributo = (Element) atributosList.item(i);
                                String nombreAttr = atributo.getElementsByTagName("nombre").item(0).getTextContent();
                                String tipoAttr = atributo.getElementsByTagName("tipo").item(0).getTextContent();
                                String scopeAttr = atributo.getElementsByTagName("visibilidad").item(0).getTextContent();
                                NodeList modificadores = atributo.getElementsByTagName("modificadores");
                                boolean esStatic = false;
                                boolean esFinal = false;
                                if(modificadores.getLength() > 0){
                                    for(int j = 0; j < modificadores.getLength(); j++){
                                        Element mod = (Element) modificadores.item(j);
                                        if(mod.getElementsByTagName("static").item(0) != null) esStatic = true;
                                        if(mod.getElementsByTagName("final").item(0) != null) esFinal = true;
                                    }
                                }
                                boolean tieneSetter = atributo.getElementsByTagName("tieneSetter").item(0) != null;
                                boolean tieneGetter = atributo.getElementsByTagName("tieneGetter").item(0) != null;
                                atributos.put(nombreAttr, new Atributo(scopeAttr, tipoAttr, nombreAttr, esFinal, esStatic, tieneSetter, tieneGetter));
                            }
                            Element toString = (Element) raiz.getElementsByTagName("tieneToString").item(0);
                            tieneToString = toString != null;
                        } catch(Exception e) {
                            System.out.println("El archivo no es válido.");
                        }
                    }else{
                        System.out.println("Cancelado");
                    }
                    break;
                case 10:
                    terminado = true;
            }
        }while(!terminado);
        
        System.out.println(TEXTO_VERDE + "Fin de la ejecución." + RESET_COLORES);
        fInput.close();
    }
    
    private Atributo recuperarAtributo(List<Atributo> lista, Atributo proporcionado){
        for(Atributo a : lista){
            if(a.equals(proporcionado)) return a;
        }
        
        return null;
    }
    
    private void generarClase(String nombre, HashMap<String, Atributo> atributos, boolean tieneToString){
        File clase = new File(nombre + ".java");
        try {
            FileWriter output = new FileWriter(clase);
            boolean cancelado = false;
            if(clase.exists()){
                System.out.println("El archivo de la clase ya existe. ¿Desea sobreescribirlo? (S/N)");
                do{
                    char res = fInput.pedirLetra(">", true);
                    if(res == 'S'){
                        System.out.println("Sobreescribiendo...");
                        break;
                    }else if(res == 'N'){
                        System.out.println("Cancelado.");
                        cancelado = true;
                        break;
                    }
                }while(true);
                
            }
            if(clase.createNewFile()) System.out.println(TEXTO_VERDE + "Archivo " + clase.getName() + " creado en la ruta " + clase.getAbsolutePath() + RESET_COLORES);

            if(!cancelado){
                //Nombre de la clase
                output.write("public class " + nombre + " {\n");
                //Atributos
                output.write("    //Atributos\n");
                for(Atributo a : atributos.values()){
                    //Modificadores de los atributos
                    String mods = " ";
                    HashMap<String, Boolean> modAux = a.modificadores;
                    for(String mod : modAux.keySet()){
                        if(modAux.get(mod)) mods += mod + ' ';
                    }
                    output.write("    " + a.visibilidad
                                        + mods
                                        + a.tipo + " "
                                        + a.nombre + ";\n");
                }
                //Constructor
                output.write("\n    //Constructor");
                output.write("\n    public " + nombre + "(");
                for(Atributo a : atributos.values()){
                    output.write(a.tipo + " " + a.nombre);
                    if(!a.equals(atributos.get(atributos.size() - 1))){
                        output.write(", ");
                    }
                }
                output.write("){\n");
                String aux;
                for(Atributo a : atributos.values()){
                    aux = a.nombre;
                    output.write("        this." + aux + " = " + aux + ";\n");
                }
                output.write("    }\n");
                //Getters y setters
                output.write("\n    //Getters y setters");
                for(Atributo a : atributos.values()){
                    aux = a.nombre; //Facilita la vida cuando tienes que escribir mucho para referenciar algo xd
                    //Getter
                    if(a.tieneGetter){
                        output.write("\n    public " + a.tipo + " get" 
                                                   + aux.substring(0, 1).toUpperCase() + aux.substring(1, aux.length())+ "(){\n");
                        output.write("        return " + aux + ";\n    }\n");
                    }
                    //Setter
                    if(a.tieneSetter){
                        output.write("\n    public void set" + aux.substring(0, 1).toUpperCase() + aux.substring(1, aux.length()) + "(" + a.tipo + " " + aux + "){\n");
                        output.write("        this." + aux + " = " + aux + ";\n    }\n");
                    }
                }
                //toString
                if(tieneToString){
                    output.write("\n    @Override\n    public String toString(){\n");
                    output.write("        return \"" + nombre + "{\" + \"");
                    for(Atributo a : atributos.values()){
                        aux = a.nombre;
                        output.write(aux + "=\" + " + aux);

                        if(!a.equals(atributos.get(atributos.size() - 1))){
                            output.write( " + \", ");
                        }
                    }
                    output.write(" + '}';\n    }");
                }
                output.write("\n}"); //Final del archivo
                output.close();
                System.out.println(TEXTO_VERDE + "Archivo generado con éxito." + RESET_COLORES);
            }
        } catch (IOException ex) {
            System.out.println(TEXTO_ROJO + "Ha ocurrido un error." + RESET_COLORES);
        }
    }
}