
# Empezando con Serenity, Cucumber y Saucedemo

Este proyecto demuestra cómo usar **Serenity BDD** en combinación con **Cucumber** para automatizar las pruebas end-to-end de una aplicación de comercio electrónico real. 
Este repositorio contiene un conjunto de pruebas automatizadas para simular el flujo de compra en el sitio web de Saucedemo, permitiéndo explorar el proceso de automatización de pruebas.

## Tecnologías y Versiones Requeridas

- **JDK**: 16 o superior
- **Gradle**: 7.6.1.
- **Serenity BDD**: Versión 4.2.13
- **Cucumber**: Versión 7.20.1
- **WebDriverManager**: Para manejar los drivers automáticamente

## Requisitos Previos

Antes de ejecutar las pruebas, se requiere tener instalado lo siguiente:

- **Java** (JDK 16 o superior)
- **Gradle**
- **Serenity BDD** (integrado en el proyecto)
- **Cucumber** (integrado con Serenity)

## Configuración del Proyecto

1. Clona este repositorio o descarga el proyecto. https://github.com/Xaler01/e2e-saucedemo-screnplay.git
2. Navega al directorio del proyecto.
3. Usa los siguientes comandos para construir y ejecutar las pruebas:

Para **Gradle**:
```bash
./gradlew clean build
```

Para **Maven**:
```bash
mvn clean verify
```

Por defecto, las pruebas se ejecutarán en **Chrome**, pero puedes especificar el navegador que deseas usar sobrescribiendo la propiedad del sistema del driver. Por ejemplo, para ejecutar las pruebas en Firefox, usa:

Para **Gradle**:
```bash
./gradlew clean test -Pdriver=firefox
```

Para **Maven**:
```bash
mvn clean verify -Ddriver=firefox
```

## Estructura del Proyecto

El proyecto sigue una estructura típica de directorios de **Serenity BDD** y **Cucumber**, que separa los componentes principales del marco de automatización de pruebas:

```
src
  ├── main
  ├── test
  │   ├── java                       # Runners de prueba y código de soporte
  │   └── resources
  │       └── features               # Archivos de características de Cucumber
  │       └── test-data              # Datos de prueba (ej., users.json)
  └── build.gradle / pom.xml         # Configuración de Gradle o Maven
```

## El Escenario de Prueba

El escenario de prueba automatiza el proceso de compra en **Saucedemo**, una plataforma de comercio electrónico para pruebas. El escenario simula los siguientes pasos:

1. **Iniciar sesión** como un usuario.
2. **Añadir productos** al carrito.
3. **Proceder a la compra**.
4. **Ingresar la información de pago** usando datos de un archivo (`users.json`).
5. **Completar la compra** y verificar el mensaje de confirmación.

#### Feature: Flujo de Compra en Saucedemo

```gherkin
Feature: Flujo de Compra en Saucedemo
  Como un usuario estándar
  Quiero completar una compra
  Para verificar el flujo de comercio electrónico

  Scenario: Completar la compra con dos productos
    Given Ingreso como "standard_user" con contraseña "secret_sauce"
    When Agrego los dos primeros productos al carrito
    And Veo mi carrito
    And Procedo al checkout
    And Ingreso mi información de checkout desde el archivo "users.json" para el usuario <userIndex>
    Then Debería ver la confirmación del pedido "¡Gracias por tu pedido!"
```

## Implementación con Serenity y Cucumber

Este proyecto sigue el **Patrón Screenplay**, que es un enfoque flexible y componible que permite escribir las pruebas en términos de "actores" y las tareas que realizan.

Por ejemplo, una de las tareas para iniciar sesión es:

```java
@Given("Ingreso como {string} con contraseña {string}")
public void ingreso_como_con_contraseña(String username, String password) {
    theActorCalled(username).attemptsTo(
            Open.url("https://www.saucedemo.com"),
            WaitUntil.the(SaucedemoPage.USERNAME_FIELD, isVisible()).forNoMoreThan(10).seconds(),
            Login.withCredentials(username, password)
    );
}
```

## El Patrón Screenplay

El **Patrón Screenplay** describe las pruebas en términos de "actores" y las tareas que realizan. Las tareas están representadas como objetos (por ejemplo, `Login.withCredentials`, `AddToCart.twoProducts()`) en lugar de métodos. Esto proporciona un enfoque más flexible y reutilizable para escribir pruebas.

Código de ejemplo para agregar productos al carrito:

```java
@When("Agrego los dos primeros productos al carrito")
public void agrego_los_dos_primeros_productos_al_carrito() {
    theActorInTheSpotlight().attemptsTo(
            AddToCart.twoProducts()
    );
}
```

Cada tarea es una **acción ejecutable** que el actor lleva a cabo. Las tareas se componen para formar un flujo completo, y cada tarea es responsable de una pequeña parte de la acción.

## Ejecución de Pruebas y Generación de Reportes

Una vez que las pruebas se completan, **Serenity BDD** generará automáticamente reportes detallados. Estos reportes incluyen información sobre los pasos ejecutados, capturas de pantalla de la interfaz de usuario y cualquier fallo en las afirmaciones. Los reportes se encuentran en el directorio `target/site/serenity`.

Para generar los reportes después de ejecutar las pruebas, puedes usar:

Para **Gradle**:
```bash
./gradlew serenityReport
```

Para **Maven**:
```bash
mvn serenity:aggregate
```

Los reportes se encontrarán en la siguiente ruta:
```
target/site/serenity/index.html
```

También se generarán los reportes de Cucumber en formato HTML. Estos reportes se encuentran en la siguiente ruta:
```
target/cucumber-reports/cucumber-html-report.html
```

Puedes abrir estos archivos en un navegador para ver los resultados de las pruebas.

## Datos de Prueba

Los datos de prueba se almacenan en un archivo JSON ubicado en la carpeta `src/test/resources/test-data`. Cada entrada en el archivo JSON representa un usuario con su información para completar el checkout. Esto permite probar múltiples escenarios con diferentes datos de usuario de manera fluida.

Ejemplo de contenido de `users.json`:

```json
[
  {
    "firstName": "John",
    "lastName": "Doe",
    "zipCode": "12345"
  },
  {
    "firstName": "Jane",
    "lastName": "Smith",
    "zipCode": "67890"
  }
]
```
## Reportes Generados

Después de ejecutar las pruebas, Serenity BDD genera reportes detallados que incluyen información sobre los pasos ejecutados, capturas de pantalla de la interfaz de usuario y cualquier fallo en las afirmaciones.

Aquí se presentan algunos ejemplos de los reportes generados:

![Reporte de prueba](images/Reporte1.png)
*Ejemplo de un reporte que muestra los pasos ejecutados.*

![Reporte de pruebas con errores](images/Reporte2.png)
![Reporte de pruebas con errores](images/Reporte3.png)
*Reporte que muestra un escenario de error en la prueba.*

...