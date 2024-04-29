# Contenedores de aplicaciones potenciadas con lenguajes de programación invitados: 
### Módulo "app_gestinterna"
***

### Rafael Rojas Crespo
#####  Junio/2024

***
## Tabla de contenidos
1. [Información general](#info-general)
2. [app_gestinterna](#app_gestinterna)
3. [Tecnologías](#tecnologías)
4. [Instalación](#instalación)


## Información general
***
Este trabajo forma parte de un Trabajo Fin de Grado desarrollado para la obtención del título de Grado en Ingeniería informática, especialidad Computación.
Se trata de un proyecto en el que se muestra cómo se trabaja con los lenguajes Java y Python dentro de un mismo proyecto.
El ejemplo que se ha diseñado puede verse en su totalidad como un sistema de información que emula cómo interactuarían varias empresas
para obtener información estratégica de cara al crecimiento de sus negocios, siendo la información el eje principal que daría sentido a un caso de uso
que podría darse en un entorno real.
El sistema desarrollado es un conjunto de contenedores que contendrían los subsistemas software virtualizados y que forman una red tipo C entre ellos.
Este subsistema tendría por ip la 192.168.0.6.
Esta configuración de red es la que permite que desde este módulo se puedan establecer conexiones a los sistemas de gestión de bases de datos
de los módulos app_gestpacientes y app_gestlaboratorios que forma el sistema en su conjunto, concretamente a las IP's 192.168.0.4 y 192.168.0.2 
respectivamente que se corresponden con las ip's de los servidores de bases de datos MySQL, del módulo app_gestpacientes, y PostgreSQL,
del módulo app_gestlaboratorios.

## app_gestinterna
***
Se trata de una aplicación que tomando las bases de datos de las aplicaciones
app_pacientes y app_laboratorios, relaciona los laboratorios con los clientes
que consumen sus fármacos. Esta relación se hace a través de un proceso escrito en
Python que forma parte del proyecto Java y que se ejecuta desde GraalVM

## Tecnologías usadas
***
* [Spring Boot](https://example.com): Versión 3.2.5
* [Java+GraalVM](https://www.graalvm.org/): Versión 21.0.2+13.1
* [Maven](https://maven.apache.org/): Versión 3.8.8
* [Graalpy](https://github.com/oracle/graalpython): Versión 23.1.2
* [Docker](https://www.docker.com/): Versión 4.29.0
* [Thymeleaf](www.thymeleaf.org): Versión 2.34
* [MySQL](https://www.mysql.com/): Versión 8.0.36
* [PostgreSQL](https://www.mysql.com/): Version 7.8


## Instalación
***
```
$ git clone https://github.com/i42rocrr/app_gestinterna.git
$ cd app_gestinterna
$ doker-compose up

$ http://localhost:8087
```
