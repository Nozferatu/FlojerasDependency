# FlojerasDependency, una librería para dominarlos a todos

Ésta es una librería de Java que me he hecho a modo de facilitarme la vida a la hora de programar, aparte de aportar algo a gente que todavía le cueste Java. 

<br>
<h2>FLOJERAS UTILITY</h2>

Es una clase que incorpora métodos a modo de atajos, bien siendo el println() a modo de reemplazar el System.out.println(), el recorrerArray() y recorrerMatriz() para no tener que crear los bucles para ver el contenido, y varias cosas más. Entre ellas:

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


<h2>FLOJERAS CLASS GENERATOR ASSISTANT</h2>

Es una clase que te permite crear clases de Java mediante un asistente sencillo.
Para poder usarlo, se ha de crear una instancia de la clase, y llamar al método iniciar().
Permite establecer el nombre de la clase, y añadir atributos, además de poder modificar estos parámetros cuando el usuario quiera.


<h2>FLOJERAS PASSWORD GENERATOR</h2>

Es una clase que genera contraseñas de forma aleatoria según la pida el usuario, además de validar contraseñas. Se permite especificar la longitud, si quiere mayúsculas, números y símbolos.
Para poder usarlo, se puede extender de esta clase, o bien se puede crear un objeto.

<h2>CÓMO AÑADIR LA LIBRERÍA A TU PROYECTO</h2>

Para añadir esta librería, hay dos formas:

- USANDO JITPACK
  - Se puede importar en un proyecto Maven (o cualquier tecnología que tenga el archivo pom) añadiendo lo siguiente al pom.xml:
```
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```
  - Después, en el tag de dependencies, se añade la librería:
```
<dependency>
	<groupId>com.github.Nozferatu</groupId>
	<artifactId>FlojerasDependency</artifactId>
	<version>X.X.X</version>
</dependency>
```
- IMPORTANDO EL JAR
  - Se puede importar directamente el JAR de la sección de releases en el proyecto.
<h2>FAQ</h2>

- No aparece el javadoc
  - El javadoc se ha de incluir aparte en la configuración de la librería. Viene en un zip junto al JAR.

[![](https://jitpack.io/v/Nozferatu/FlojerasDependency.svg)](https://jitpack.io/#Nozferatu/FlojerasDependency)
