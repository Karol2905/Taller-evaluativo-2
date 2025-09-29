# Taller Evaluativo #2

- **Rama:** `develop`

## 👥 Desarrolladores
- KAROL ESTEFANY ESTUPIÑAN VIANCHA
- SERGIO ALEJANDRO IDARRAGA TORRES
- JULIAN DAVID CASTIBLANCO REAL
- SANTIAGO CARMONA PINEDA
- JUAN CARLOS LEAL CRUZ

## 📋 Descripción
El Gestor de Tareas Colaborativo es un sistema de reportes financieros diseñado para empresas fintech que buscan ofrecer a sus clientes informes dinámicos, personalizables y fáciles de generar. Este sistema permite a los usuarios crear reportes detallados que incluyen información básica como título, fecha de generación, autor, lista de transacciones y contenido.

Gracias a la implementación de patrones de diseño como Builder y Decorator, los reportes pueden construirse paso a paso y extenderse dinámicamente con funcionalidades adicionales, tales como:
- Inclusión de gráficas.
- Aplicación de marcas de agua de seguridad.
- Generación de resúmenes estadísticos.
- Exportación a formatos PDF o Excel.

El sistema también permite listar todos los reportes generados y filtrarlos por fecha mediante Streams, garantizando eficiencia y flexibilidad en la gestión de la información. Todos los reportes se persisten en MongoDB, asegurando almacenamiento confiable y escalable.

## Funcionamiento del Proyecto con Postman 
A continuación un breve video para mostrar el funcionamiento del proyecto utilizando la herramienta de Postman
**https://pruebacorreoescuelaingeduco-my.sharepoint.com/:v:/g/personal/julian_castiblanco-r_mail_escuelaing_edu_co/Ebq4ibbMroBKlx1Jjx67KkcBpj8VcFRTbzNgxOG-GXIe1w?e=UEfn9M**



## 📐 Principios SOLID:
1. **Single Responsibility Principle (SRP)**  
   Cada clase tiene una única responsabilidad. Por ejemplo, el `ReporteRepository` solo gestiona la persistencia de reportes, el `Service` la lógica de negocio, y los `Builders/Decorators` la construcción y decoración de los reportes que genera el cliente.

2. **Open/Closed Principle (OCP)**  
   Los decoradores de reportes nos permite delegar funcionalidades (como agregar gráficas o marcas de agua) sin modificar las clases principales de neustro proyecto. Ya que se pueden agregar nuevos decoradores para los reportes sin tener que modificar el código. 

3. **Liskov Substitution Principle (LSP)**  
   Los decoradores y el builder implementan interfaces o heredan de una clase abstracta como `ReporteDecorador` , lo que nos permite hacer que cualquier decorador pueda sustituir a otro sin comprometer el funcionamiento de lo que ya está implementado.

4. **Interface Segregation Principle (ISP)**  
   Las interfaces como `ReporteRepository`, `TransaccionRepository` y las propias de los decoradores/servicios están bien definidas y no obligan a implementar métodos innecesarios.

5. **Dependency Inversion Principle (DIP)**  
   El `Service` depende de abstracciones (`ReporteRepository`), no de implementaciones concretas. Spring inyecta las dependencias, facilitando el desacoplamiento y la prueba.

Esto mejora la mantenibilidad, escalabilidad y testabilidad del sistema.


## 🌳 Estrategia de Ramas (Git Flow)

> Utilizamos **GitFlow**, un modelo de ramificación robusto que nos permite mantener un desarrollo organizado y controlado.

<img width="1200" height="588" alt="image" src="https://github.com/user-attachments/assets/487c8205-9c40-402a-a7b1-ce46b63d7f25" />

### ¿Por qué GitFlow?
- ✅ **Desarrollo paralelo:** Múltiples funcionalidades simultáneamente
- ✅ **Releases controlados:** Versiones estables y predecibles  
- ✅ **Hotfixes rápidos:** Corrección inmediata de bugs críticos
- ✅ **Historial limpio:** Trazabilidad completa del código

---

## 🏷️ Tipos de Ramas

### 🎯 `main`
> **Rama de producción** - Código estable y listo para despliegue

| **Aspecto** | **Descripción** |
|-------------|-----------------|
| **🎯 Propósito** | Rama estable con versión final (producción/demo) |
| **📥 Recibe de** | `release/*` y `hotfix/*` únicamente |
| **🏷️ Tags** | Cada merge crea un tag SemVer (`vX.Y.Z`) |
| **🔒 Protección** | PR obligatorio + 1-2 aprobaciones + CI verde |

### 🔧 `develop`  
> **Rama de integración** - Base para nuevas funcionalidades

| **Aspecto** | **Descripción** |
|-------------|-----------------|
| **🎯 Propósito** | Integración continua de trabajo en desarrollo |
| **📥 Recibe de** | `feature/*` y merges de `release/*` |
| **🔒 Protección** | Mismas reglas que `main` |

### ⚡ `feature/*`
> **Ramas de funcionalidad** - Desarrollo de nuevas características

| **Aspecto** | **Descripción** |
|-------------|-----------------|
| **🎯 Propósito** | Desarrollo de funcionalidades, refactors o spikes |
| **🌱 Base** | `develop` |
| **🔄 Cierre** | Merge a `develop` mediante PR |

### 🚀 `release/*`
> **Ramas de preparación** - Estabilización pre-despliegue

| **Aspecto** | **Descripción** |
|-------------|-----------------|
| **🎯 Propósito** | Congelar cambios, testing final, correcciones menores |
| **🌱 Base** | `develop` |
| **🔄 Cierre** | Merge a `main` (con tag) **Y** merge a `develop` |
| **📝 Ejemplo** | `release/1.3.0` |

### 🔥 `hotfix/*`
> **Ramas de emergencia** - Corrección urgente de bugs críticos

| **Aspecto** | **Descripción** |
|-------------|-----------------|
| **🎯 Propósito** | Corregir bugs **críticos** en producción |
| **🌱 Base** | `main` |
| **🔄 Cierre** | Merge a `main` (con tag PATCH) **Y** merge a `develop` |
| **📝 Ejemplos** | `hotfix/fix-login-crash` |

---

## Diagramas

- **Diagrama de clases:**
  ![Diagrama de Clases](docs/images/DiagramaClases.png)

- **Diagrama de Componentes General**
  <p align="center">
  <img width="360" height="567" alt="image" src="https://github.com/user-attachments/assets/086d86f3-c911-45c0-990e-e43c63b11663" />
  </p>

  * **Backend SRF (Sistema de Reportes de Componentes):**

    A grandes rasgos este va a ser el núcleo de nuestro sistema el cual está desarrollado en Java con SpringBoot. Donde se encarga de toda la lógica del negocio, la gestión de los reportes financieros y todo sobre la exposición de servicios REST. 

    Dentro de nuestro Backend tenemos las tecnologías clave como:
     - SpringBoot el cual es el framework principal del desarrollo del Back.
     - Maven que es la herramienta de gestión de dependencias y compilación del proyecto.
     - Swagger la cual nos ayuda a la documentación automática y la integración de las pruebas interactivas del API REST.
     - Java el lenguaje que estamos utilizando para desarrollar todo el proyecto en el Back.

  * **Conexión Backend con la base de datos:**

    El Backend se conecta a la base de datos a través del driver de MongoDB, la cual usa la configuración definida en application.properties.

- **Diagrama de Componentes Específico**
  <p align='center'>
 <img width="1030" height="689" alt="image" src="https://github.com/user-attachments/assets/1404925d-1942-4ad3-a12d-f2d3122d5273" />

  </p>


  En este diagrama se muestra como se conecta el back con los distintos componentes utilizando MVC (Modelo - Vista - Controlador) que está adaptado a Spring Boot.

  1. **ReporteController:** Este componente representa la capa de entrada a nuestro sistema, se encaraga de exponer la API REST para que el cliente pueda interactuar con el backend mediante protocolos HTTP (GET, POST, PUT, DELETE). Para así poderlo pasar al servicio para que maneje las solicitudes.
     
  2. **ReporteService:**  Este componente se encarga en implementar toda la lógica del negocio, es decir, aquí es donde se definen las reglas de operación y de como se transforman las solicitudes en acciones sobre los datos. 

  3. **ReporteRepository:** Este componente gestiona la interacción directa con la base de datos MongoDB, permitiendo guardar, actualizar, eliminar o consultar información de los reportes.
 
  4. **TransaccionController:** Al igual que el controlador de reportes, expone la API REST pero para las operaciones relacionadas con las transacciones, funcionando como la puerta de entrada para este módulo.
  
  5. **TransaccionService:** Encargado de la lógica de negocio de las transacciones, procesando las solicitudes recibidas desde el controlador y aplicando las reglas correspondientes.
 
  6. **TransaccionRepository:** Gestiona el acceso a la base de datos para almacenar o consultar las transacciones, asegurando que la información esté disponible de manera persistente.

##  📊 Análisis de pruebas con JaCoCo y Sonarqube

<img width="1364" height="423" alt="image" src="https://github.com/user-attachments/assets/1a7d5ed0-b281-420b-843a-86a3f5c00b55" />

Para este proyecto se utilizó JaCoCo con el fin de medir la cobertura de pruebas unitarias e identificar qué tan bien están cubiertos los diferentes componentes del sistema.

Cobertura total de instrucciones: 84% (123 de 806 instrucciones no cubiertas).
Clases cubiertas: 13 clases analizadas en total.

Se ejecutaron pruebas unitarias y de integración para este proyecto de Sistema de Reportes Financieros, lo cual obtuvimos los siguentes resultados: Cobertura de instrucciones del **84%** lo que quiere decir que de 806 instrucciones del código tan solo 123 no fueron ejecutadas durante las pruebas que realizamos. A su vez, esto nos garantiza que la mayor parte de la lógica del proyecto está suficientemente probada.  


<img width="862" height="163" alt="image" src="https://github.com/user-attachments/assets/63447c7d-38d3-4005-b440-568b9f3b22b1" />

Además hemos hecho un análisis de calidad de código con SonarQube, y el resultado es que nos muestra es aceptable. Esto significa que nuestro proyecto cumple con altos estándares, con más del 81% de cobertura de código gracias a las pruebas de unidad que fueron implementadas, lo que garantiza su estabilidad. Aunque la calidad es alta (cero código duplicado), hemos identificado unas pocas áreas que necesitan un poco de atención porque tenemos 3 problemas de seguridad y 5 bugs que debemos corregir de inmediato para que sea totalmente seguro y confiable.
