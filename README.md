# 🧪 Automatización E2E con Serenity BDD, Cucumber y Saucedemo

Este proyecto implementa un flujo de pruebas automatizadas end-to-end sobre el sitio [Saucedemo](https://www.saucedemo.com), utilizando **Serenity BDD**, **Cucumber** y el **patrón Screenplay**. Incluye la gestión de datos externos, captura de evidencias automáticas y configuración para evitar interferencias del navegador.

---

## 🚀 Tecnologías utilizadas

- **Java (JDK 16+)**
- **Gradle 7.6.1**
- **Serenity BDD 4.2.13**
- **Cucumber 7.20.1**
- **WebDriverManager**

---

## ⚙️ Configuración del entorno

### Prerrequisitos

- Java JDK 16 o superior
- Gradle instalado 
- Navegador Chrome o Firefox

---

## 📦 Instalación y ejecución

Clonar el repositorio:

```bash
git clone https://github.com/Xaler01/e2e-saucedemo-screnplay.git
cd e2e-saucedemo-screnplay
```

### Ejecutar pruebas con Gradle

```bash
./gradlew clean test
```

### Ejecutar pruebas en Firefox

```bash
./gradlew clean test -Pdriver=firefox
```

---

## 📋 Escenario automatizado

El test principal cubre el siguiente flujo completo de compra:

1. Iniciar sesión con usuario y contraseña.
2. Agregar los productos al carrito.
3. Ingresar al carrito y proceder al checkout.
4. Cargar los datos del usuario desde `purchase-data.json`.
5. Finalizar la compra y validar el mensaje de confirmación.

---

## 📁 Estructura de carpetas

```
src
├── test
│   ├── java                      # Código de pruebas y lógica Screenplay
│   └── resources
│       ├── features             # Escenarios Gherkin
│       └── test-data            # Archivo purchase-data.json
build.gradle
serenity.conf
```

---

## 📄 Datos externos (JSON)

El archivo `purchase-data.json` permite parametrizar los datos de checkout e ingresar tantos productos como se requiera en la prueba. Este archivo se encuentra en la carpeta `src/test/resources/test-data`.

```json
{
  "standard_user": {
    "username": "standard_user",
    "password": "secret_sauce",
    "products": [ 
      "Sauce Labs Backpack",
      "Sauce Labs Bike Light",
      "Sauce Labs Bolt T-Shirt",
      "Sauce Labs Fleece Jacket",
      "Sauce Labs Onesie"
    ],
    "confirmationMessage": "Thank you for your order!",
    "checkoutData": [
      {
        "firstName": "Alexander",
        "lastName": "Jacome",
        "zipCode": "17035"
      }
    ]
  }
}
```

---

## 📸 Reportes

Después de ejecutar las pruebas, se generan reportes visuales en:

```
target/site/serenity/index.html
```

Para abrir el reporte:

```bash
open target/site/serenity/index.html
```

---

## 🧑‍💻 Patrón Screenplay

Las pruebas siguen el patrón **Screenplay**, en el que un "actor" realiza tareas definidas como objetos. Ejemplo de tarea:


Esto permite una alta legibilidad, reutilización de código y mantenimiento sencillo.

---

## ✅ Resultado esperado

Al completar el flujo de compra, se espera visualizar el mensaje:

> **"Thank you for your order!"**

---

##  Reportes Generados

Después de ejecutar las pruebas, Serenity BDD genera reportes detallados que incluyen información sobre los pasos ejecutados, capturas de pantalla de la interfaz de usuario y cualquier fallo en las afirmaciones.

Aquí se presentan algunos ejemplos de los reportes generados:

![Reporte de prueba](images/Reporte1.png)
*Ejemplo de un reporte que muestra los pasos ejecutados.*

![Reporte de pruebas con errores](images/Reporte2.png)
![Reporte de pruebas con errores](images/Reporte3.png)
*Reporte que muestra un escenario de error en la prueba.*

---

## 📬 Contacto

Desarrollado por [@Xaler01](https://github.com/Xaler01)  
Proyecto de automatización E2E con Serenity & Cucumber
