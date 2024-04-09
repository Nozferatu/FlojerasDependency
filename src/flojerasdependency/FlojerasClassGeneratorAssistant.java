package flojerasdependency;

import static flojerasdependency.FlojerasUtility.pedirLetra;
import static flojerasdependency.FlojerasUtility.pedirTexto;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//FLOJERAS CLASS GENERATOR ASSISTANT V0.1

/**
 *
 * @author Carlos Madrid
 */
public class FlojerasClassGeneratorAssistant extends FlojerasUtility{
    
    private class Atributo{
        private String visibilidad;
        private String tipo;
        private String nombre;
        private String modificadores;
        private boolean tieneSetter;
        private boolean tieneGetter;
        
        public Atributo(){ modificadores = ""; }
        
        public void pedirDatos(){
            System.out.println("Introduce el nombre:");
            do{
                nombre = pedirTexto();
            }while(nombre.isBlank());
                    
            System.out.println("Introduce el tipo de dato:");
            tipo = pedirTexto();
                    
            if(tipo.isBlank()){
                this.tipo = "int";
            }
            
            System.out.println("Introduce la visibilidad:");
            visibilidad = pedirTexto();
            
            if(visibilidad.isBlank()) visibilidad = "private";
            
            System.out.println("¿Será static? (S/N)");
            do{
                char res = pedirLetra(true);
                if(res == 'S'){
                    modificadores += " static";
                    break;
                }else if(res == 'N'){
                    break;
                }
            }while(true);
            
            System.out.println("¿Será final? (S/N)");
            do{
                char res = pedirLetra(true);
                if(res == 'S'){
                    modificadores += " final";
                    break;
                }else if(res == 'N'){
                    break;
                }
            }while(true);
            
            System.out.println("¿Tendrá getter? (S/N)");
            do{
                char res = pedirLetra(true);
                if(res == 'S'){
                    tieneGetter = true;
                    break;
                }else if(res == 'N'){
                    tieneGetter = false;
                    break;
                }
            }while(true);
                    
            System.out.println("¿Tendrá setter? (S/N)");
            do{
                char res = pedirLetra(true);
                if(res == 'S'){
                    tieneSetter = true;
                    break;
                }else if(res == 'N'){
                    tieneSetter = false;
                    break;
                }
            }while(true);
        }
    }
    
    public FlojerasClassGeneratorAssistant(){}
    
    public void iniciar(){
        int opcion;
        boolean terminado = false;
        
        //Variables para almacenar los datos de la clase
        String nombre = "TextoDeEjemplo";
        Atributo[] atributos = new Atributo[5];
        
        System.out.println("//---- FLOJERAS CLASS GENERATOR ASSISTANT V0.1 ----//\n");
        do{
            System.out.println("\nElija una opción:");
            System.out.println("[1] Declarar nombre clase [2] Introducir atributo [3] Modificar atributo");
            System.out.println("[4] Generar clase [5] Detener");
            
            do{
                opcion = pedirInt(false);
            }while(opcion < 1 || opcion > 5);
            
            switch(opcion){
                case 1:
                    System.out.println("Introduce el nombre de la clase:");
                    do{
                        nombre = pedirTexto();
                    }while(nombre.isBlank());
                    
                    break;
                case 2:
                    int pos = devolverNumEspaciosOcupados(atributos);
                    atributos[pos] = new Atributo();
                    atributos[pos].pedirDatos();
                    break;
                case 3:
                    System.out.println("Elige el atributo a modificar:");
                    for (int i = 0; i < atributos.length; i++) {
                        if(atributos[i] != null){
                            System.out.println((i+1) + "º: " + atributos[i].nombre);
                        }
                    }
                    
                    do{
                        opcion = pedirInt(true);
                        opcion--;
                        if(atributos[opcion] != null){
                            atributos[opcion] = null;
                            atributos[opcion] = new Atributo();
                            atributos[opcion].pedirDatos();
                            System.gc();
                            break;
                        }
                    }while(true);
                    break;
                case 4:
                    File clase = new File(nombre + ".java");
                    try {
                        if(clase.createNewFile()){
                            System.out.println(TEXTO_VERDE + "Archivo " + clase.getName() + " creado en la ruta " + clase.getAbsolutePath() + RESET_COLORES);
                            
                            FileWriter output = new FileWriter(clase);
                            
                            //Nombre de la clase
                            output.write("public " + nombre + " {\n");
                            //Atributos
                            int numAtr = devolverNumEspaciosOcupados(atributos);
                            for(int i = 0; i < numAtr; i++){
                                output.write("\t" + atributos[i].visibilidad 
                                                  + atributos[i].modificadores + " "
                                                  + atributos[i].tipo + " " 
                                                  + atributos[i].nombre + ";\n");
                            }
                            //Constructor
                            output.write("\n\tpublic " + nombre + "(");
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
                                output.write("\t\tthis." + aux + " = " + aux + ";\n");
                            }
                            output.write("\t}\n");
                            //Getters y setters
                            for(int i = 0; i < numAtr; i++){
                                aux = atributos[i].nombre; //Facilita la vida cuando tienes que escribir mucho para referenciar algo xd
                                //Getter
                                if(atributos[i].tieneGetter){
                                    output.write("\n\tpublic " + atributos[i].tipo + " get" 
                                                               + aux.substring(0, 1).toUpperCase() + aux.substring(1, aux.length())+ "(){\n");
                                    output.write("\t\treturn " + aux + ";\n\t}\n");
                                }
                                //Setter
                                if(atributos[i].tieneSetter){
                                    output.write("\n\tpublic void set" + aux.substring(0, 1).toUpperCase() + aux.substring(1, aux.length()) + "(" + atributos[i].tipo + " " + aux + "){\n");
                                    output.write("\t\tthis." + aux + " = " + aux + ";\n\t}\n");
                                }
                            }
                            output.write("}"); //Final del archivo
                            output.close();
                            System.out.println(TEXTO_VERDE + "Archivo generado con éxito." + RESET_COLORES);
                        }else{
                            System.out.println(TEXTO_ROJO + "El archivo ya existe" + RESET_COLORES);
                        }
                    } catch (IOException ex) {
                        System.out.println(TEXTO_ROJO + "Ha ocurrido un error." + RESET_COLORES);
                    }
                    break;
                case 5:
                    terminado = true;
            }
        }while(!terminado);
        
        System.out.println(TEXTO_VERDE + "Fin de la ejecución." + RESET_COLORES);
    }
}