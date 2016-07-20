# BashExecuteScript

Programa Java que permite la ejecución de servicios bash, para un posterior tratado de los resultados. Los servicios son entidades que deberan ser desarrolladas incluyendo unas caracteristicas comunes entre las que se incluye un script.

El nucleo de la aplicación ejecutara el script, analizando las instrucciones y devolvera la ejecución de los mismos.

# Master version

Versión 1.0

## Versión 1.0 (deprecated)

La primera versión ejecuta comandos tanto en operativos windows como en operativos linux. Esta versión incluye un archivo properties que se incluye los diferentes comandos que se pueden realizar. Esta versión es muy insegura y primitiva. Solo sirve como esqueleto para realizar versiones mejores.

La versión incluye
- Nucleo de ejecución
- Ejecución basica de comandos.
- Diferenciación entre diferentes operativos.

## Versión 2.0 (on deploy)

La segunda versión de esta aplicación ejecuta diversos servicios. Los diversos servicios contendran diversos script que seran ejecutados por el nucleo de la aplicación y devueltos a ellos mismos los datos procesados. 

Notas: Para la ejecución en el entorno windows es necesario el cambiar las politicas utilizando el siguiente comando
	Set-ExecutionPolicy RemoteSigned -Scope CurrentUser