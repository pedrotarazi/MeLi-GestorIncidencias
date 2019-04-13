import com.google.gson.Gson;

import static spark.Spark.*;

public class GestorIncidencias {

    public static void main(String[] args) {

        final UsuarioService usuarioService = new UsuarioServiceMapImplementation();
        final ProyectoService proyectoService = new ProyectoServiceMapImplementation();
        final IncidenteService incidenteService = new IncidenteServiceMapImplementation();

        // USUARIO
        // Crear usuario
        post("/usuario", (request, response) -> {
            response.type("application/json");
            Usuario usuario = new Gson().fromJson(request.body(), Usuario.class);
            try {
                usuarioService.addUsuario(usuario);
            }
            catch (Exception e){
                System.out.println(e);
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
            }
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        // Obtener todos los usuarios
        get("/usuario", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(usuarioService.getUsuarios())));
        });

        // Obtener un usuario
        get("/usuario/:id", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(usuarioService.getUsuario(new Integer(request.params(":id"))))));
        });

        // Modificar un usuario
        put("/usuario", (request, response) -> {
            response.type("application/json");
            Usuario usuario = new Gson().fromJson(request.body(), Usuario.class);
            try{
                Usuario usuarioEdit = usuarioService.editUsuario(usuario);
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                            new Gson().toJson(usuarioEdit)));
            }
            catch (Exception e){
                System.out.println(e);
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
            }
        });

        // Eliminar un usuario
        delete("/usuario/:id", (request, response) -> {
            response.type("application/json");
            if (usuarioService.deleteUsuario(new Integer(request.params(":id")))){
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
            }
            else{
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
            }
        });

        // PROYECTO
        // Crear proyecto
        post("/proyecto", (request, response) -> {
            return null;
        });

        // Obtener todos los proyectos
        get("/proyecto", (request, response) -> {
            return null;
        });

        // Obtener un proyecto
        get("/proyecto/:id", (request, response) -> {
            return null;
        });

        // Modificar un proyecto
        put("/proyecto", (request, response) -> {
            return null;
        });

        // Eliminar un proyecto
        delete("/proyecto/:id", (request, response) -> {
            return null;
        });

        // INCIDENTE
        // Crear incidente
        post("/incidente", (request, response) -> {
            return null;
        });

        // Obtener todos los incidentes
        get("/incidente", (request, response) -> {
            return null;
        });

        //Obtener un incidente
        get("/incidente/:id", (request, response) -> {
            return null;
        });

        // Cambiar estado
        put("/incidente/:id", (request, response) -> {
            return null;
        });

        // Agregar descripcion
        post("/incidente/:id", (request, response) -> {
            return null;
        });

        // Cambiar descripcion
        put("/incidente/:id", (request, response) -> {
            return null;
        });

        //GENERALES
        // Proyectos con usuario como propietario
        get("/proyectos/propietario/:id", (request, response) -> {
            return null;
        });

        // Incidentes asignados a un usuario
        get("/incidentes/responsable/:id", (request, response) -> {
            return null;
        });

        // Incidentes creados por un usuario
        get("/incidentes/reportador/:id", (request, response) -> {
            return null;
        });

        // Incidentes abiertos y cerrados
        get("/incidentes/:estado", (request, response) -> {
            return null;
        });

        // Incidentes asociados a un proyecto

    }
}
