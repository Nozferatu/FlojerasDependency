# FlojerasDependency, una librería para dominarlos a todos

Ésta es una librería de Java que me he hecho a modo de facilitarme la vida a la hora de programar, aparte de aportar algo a gente que todavía le cueste Java. 

<br>
<h2>FLOJERAS UTILITY</h2>

Es una clase que incorpora métodos a modo de atajos, bien siendo el print() a modo de reemplazar el System.out.println(), el recorrerArray() y recorrerMatriz() para no tener que crear los bucles para ver el contenido, y varias cosas más. Entre ellas:

- Métodos matemáticos
  - esCapicua() -> Te comprueba si un número es capicúa o no.
  - devolverDivisores() -> Te devuelve en una lista los divisores de un número.
  - ecuacionSegundoGrado() -> Te muestra las soluciones de una ecuación de segundo grado en caso de que las tenga. Sino, te lo notificará.
- Métodos de modificación
  - rotarArray() -> Te rota un array las posiciones que tú le indiques.
  - ordenarArrayAlfabeticamente -> Creo que el nombre ya lo dice todo.
  - sustituirArray() -> Reemplaza los valores de un array por los de otro.
- Generación de datos típicos de personas
  - devolverNombre() -> Devuelve un nombre al azar de una lista de nombres del propio paquete
  - devolverApellido() -> Lo mismo que devolverNombre, pero devuelve un apellido.
  - comprobarDNI -> Comprueba si el DNI proporcionado es válido o no.

<h2>FAQ</h2>

- ¿Cómo se añade la librería a mi proyecto?
  - En el proyecto en el que estés trabajando, te vas a Propiedades > Librerías > Classpath > Añadir JAR/Carpeta. Lo recomendable es depositar el JAR dentro de la carpeta principal del proyecto, así podrás trabajar en cualquier ordenador sin problemas.
- No aparece el javadoc
  - El javadoc se ha de incluir aparte en la configuración de la librería. Viene en un zip junto al JAR.
