# MeLi-GestorIncidencias
API RestFul para un Gestor de Incidencias (simplificado)

La API permite:
* Crear un usuario, mostrar todos los usuarios creados, un usuario por
id, modificar un usuario existente y eliminar un usuario (que no sea
propietario de un proyecto, responsable o reportador).
* Crear un proyecto, mostrar (uno o todos), modificar y eliminar (si no
tiene incidentes reportados).
* Crear un incidente (no se puede modificar o eliminar, sólo se podrá
añadir texto a la descripción y cambiar su estado a RESUELTO)
* La API deberá mostrar todos los proyectos de los que un usuario es
propietario, todos los incidentes asignados a un usuario, todos
incidentes creados por un usuario, todos los incidentes asociados a
un proyecto, todos los incidentes abiertos (que estarían pendientes)
y, finalmente, todos los incidentes resueltos (los que estarían
cerrados).

## Pruebas con Postman
El archivo "GestorIncidentes.postman_collection.json" es una coleccion de Postman. 
El archivo se debe importar con Postman para poder realizar las pruebas.
