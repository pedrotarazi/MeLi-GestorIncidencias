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
            response.type("application/json");
            Proyecto proyecto = new Gson().fromJson(request.body(), Proyecto.class);
            try {
                proyectoService.addProyecto(proyecto);
            }
            catch (Exception e){
                System.out.println(e);
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
            }
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        // Obtener todos los proyectos
        get("/proyecto", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(proyectoService.getProyectos())));
        });

        // Obtener un proyecto
        get("/proyecto/:id", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(proyectoService.getProyecto(new Integer(request.params(":id"))))));
        });

        // Modificar un proyecto
        put("/proyecto", (request, response) -> {
            response.type("application/json");
            Proyecto proyecto = new Gson().fromJson(request.body(), Proyecto.class);
            try{
                Proyecto proyectoEdit = proyectoService.editProyecto(proyecto);
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                        new Gson().toJson(proyectoEdit)));
            }
            catch (Exception e){
                System.out.println(e);
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
            }
        });

        // Eliminar un proyecto
        delete("/proyecto/:id", (request, response) -> {
            response.type("application/json");
            try {
                if (proyectoService.deleteProyecto(new Integer(request.params(":id")))) {
                    return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
                }
            } catch (Exception e){
                System.out.println(e);
            }
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
        });

        // INCIDENTE
        // Crear incidente
        post("/incidente", (request, response) -> {
            response.type("application/json");
            Incidente incidente = new Gson().fromJson(response.body(), Incidente.class);
            try{
                incidenteService.addIncidente(incidente);
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
            }
            catch (Exception e){
                System.out.println(e);
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
            }
        });

        // Obtener todos los incidentes
        get("/incidente", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                    new Gson().toJsonTree(incidenteService.getIncidentes())));
        });

        //Obtener un incidente
        get("/incidente/:id", (request, response) -> {
            response.type("application/json");
            try {
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                        new Gson().toJsonTree(incidenteService.getIncidente(new Integer(request.params(":id"))))));
            }
            catch (Exception e){
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
            }
        });

        // Cambiar estado
        put("/incidente/:id", (request, response) -> {
            response.type("application/json");
            Incidente incidenteEdit = new Gson().fromJson(response.body(), Incidente.class);
            try {
                incidenteService.changeEstado(incidenteEdit);
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                        new Gson().toJsonTree(incidenteService.getIncidente(new Integer(request.params(":id"))))));
            }
            catch (Exception e){
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
            }
        });

        // Cambiar descripcion
        put("/incidente/:id", (request, response) -> {
            response.type("application/json");
            Incidente incidenteEdit = new Gson().fromJson(response.body(), Incidente.class);
            try {
                incidenteService.editDescripcion(incidenteEdit);
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                        new Gson().toJsonTree(incidenteService.getIncidente(new Integer(request.params(":id"))))));
            }
            catch (Exception e){
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
            }
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
