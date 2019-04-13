import com.google.gson.Gson;
import java.util.Date;
import static spark.Spark.*;

public class GestorIncidencias {

    public static void main(String[] args) {

        final UsuarioService usuarioService = new UsuarioServiceMapImplementation();
        final ProyectoService proyectoService = new ProyectoServiceMapImplementation();
        final IncidenteService incidenteService = new IncidenteServiceMapImplementation();

        try{
            init(usuarioService, proyectoService, incidenteService);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        // **************************************************************************************
        // USUARIO
        // Crear usuario
        post("/usuario", (request, response) -> {
            response.type("application/json");
            Usuario usuario = new Gson().fromJson(request.body(), Usuario.class);
            try {
                usuarioService.addUsuario(usuario);
            }
            catch (UsuarioException e){
                if (e.getMessage().equals("ID")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.ID_ERROR,
                            "ID invalido"));
                }
                else if (e.getMessage().equals("EXIST")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.EXIST,
                            "El ID ya existe"));
                }
                else if (e.getMessage().equals("NAME_ERROR")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.NAME_ERROR,
                            "Nombre/Apellido inválido"));
                }
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
            }
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Usuario agregado"));
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
            try {
                Usuario usuario = usuarioService.getUsuario(new Integer(request.params(":id")));
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                        new Gson().toJsonTree(usuario)));
            }
            catch (UsuarioException e){
                if (e.getMessage().equals("NOT_EXIST")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.NOT_EXIST, "El usuario no existe"));
                }
                else if (e.getMessage().equals("ID_ERROR")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.ID_ERROR, "ID invalido"));
                }
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
            }
        });

        // Modificar un usuario
        put("/usuario", (request, response) -> {
            response.type("application/json");
            Usuario usuario = new Gson().fromJson(request.body(), Usuario.class);
            try{
                Usuario usuarioEdit = usuarioService.editUsuario(usuario);
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                            new Gson().toJsonTree(usuarioEdit)));
            }
            catch (UsuarioException e){
                if (e.getMessage().equals("ID_ERROR")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.ID_ERROR,
                            "ID invalido"));
                }
                else if(e.getMessage().equals("NOT_EXIST")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.NOT_EXIST,
                            "No existe el usuario"));
                }
                else if(e.getMessage().equals("NAME_ERROR")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.NAME_ERROR,
                            "Nombre/Apellido invalido"));
                }
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
            }
        });

        // Eliminar un usuario
        delete("/usuario/:id", (request, response) -> {
            response.type("application/json");
            try{
                usuarioService.deleteUsuario(new Integer(request.params(":id")));
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
            }
            catch (UsuarioException e){
                if(e.getMessage().equals("NOT_EXIST")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.NOT_EXIST));
                }
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
            }
        });

        // **************************************************************************************
        // PROYECTO
        // Crear proyecto
        post("/proyecto", (request, response) -> {
            response.type("application/json");
            Proyecto proyecto = new Gson().fromJson(request.body(), Proyecto.class);
            try {
                proyectoService.addProyecto(proyecto);
            }
            catch (ProyectoException e){
                if (e.getMessage().equals("ID_ERROR")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.ID_ERROR, "ID invalido"));
                }
                else if (e.getMessage().equals("EXIST")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.EXIST, "El ID ya existe"));
                }
                else if (e.getMessage().equals("NAME_ERROR")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.NAME_ERROR,
                            "Titulo invalido"));
                }
                else if (e.getMessage().equals("PROPIETARIO_ERROR")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.PROPIETARIO_ERROR,
                            "Propietario con parametros invalidos"));
                }
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
            }
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Proyecto agregado"));
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
            try{
                Proyecto proyecto = proyectoService.getProyecto(new Integer(request.params(":id")));
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                        new Gson().toJsonTree(proyecto)));
            }
            catch(ProyectoException e){
                if (e.getMessage().equals("ID_ERROR")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.ID_ERROR, "ID invalido"));
                }
                else if (e.getMessage().equals("NO_EXIST")) {
                    return new Gson().toJson(new StandardResponse(StatusResponse.NAME_ERROR,
                            "EL proyecto no existe"));
                }
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
            }
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
            catch(ProyectoException e){
                if (e.getMessage().equals("ID_ERROR")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.ID_ERROR, "ID invalido"));
                }
                else if (e.getMessage().equals("NAME_ERROR")) {
                    return new Gson().toJson(new StandardResponse(StatusResponse.NAME_ERROR,
                            "Titulo invalido"));
                }
                else if (e.getMessage().equals("PROPIETARIO_ERROR")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.PROPIETARIO_ERROR,
                            "Propietario con parametros invalidos"));
                }
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
            } catch (ProyectoException e){
                if (e.getMessage().equals("NOT_EXIST")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.NOT_EXIST,
                            "El proyecto no existe"));
                }
                else if (e.getMessage().equals("NOT_REMOVE")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.NOT_REMOVE,
                            "El proyecto no se puede eliminar."));
                }
            }
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
        });

        // **************************************************************************************
        // INCIDENTE
        // Crear incidente
        post("/incidente", (request, response) -> {
            response.type("application/json");
            Incidente incidente = new Gson().fromJson(response.body(), Incidente.class);
            try{
                incidenteService.addIncidente(incidente);
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
            }
            catch (IncidenteException e){
                if (e.getMessage().equals("ID_ERROR")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.ID_ERROR, "ID no valido"));
                }
                else if(e.getMessage().equals("EXIST")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.EXIST, "El ID ya existe"));
                }
                else if(e.getMessage().equals("CLASIFICACION_ERROR")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.CLASIFICACION_ERROR,
                            "Clasificacion invalida"));
                }
                else if(e.getMessage().equals("NAME_ERROR")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.NAME_ERROR,
                            "Falta descripcion"));
                }
                else if(e.getMessage().equals("ESTADO_ERROR")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.ESTADO_ERROR,
                            "Estado invalido"));
                }
                else if(e.getMessage().equals("USUARIO_ERROR")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.USUARIO_ERROR,
                            "Reportador o Responsable con parametros no validos"));
                }
                else if(e.getMessage().equals("DATE_ERROR")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.DATE_ERROR, "Fecha invalida"));
                }
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
            catch (IncidenteException e){
                if (e.getMessage().equals("ID_ERROR"))
                    return new Gson().toJson(new StandardResponse(StatusResponse.ID_ERROR, "ID invalido"));
                else if (e.getMessage().equals("NOT_EXIST")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.NOT_EXIST,
                            "No existe el incidente"));
                }
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
            catch (IncidenteException e){
                if(e.getMessage().equals("ESTADO_ERROR")) {
                    return new Gson().toJson(new StandardResponse(StatusResponse.ESTADO_ERROR,
                            "Estado invalido"));
                }
                else if (e.getMessage().equals("NOT_EXIST")){
                    return new Gson().toJson(new StandardResponse(StatusResponse.NOT_EXIST,
                            "No existe el incidente que desea modificar"));
                }
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
            catch (IncidenteException e){
                if (e.getMessage().equals("NAME_ERROR")) {
                    return new Gson().toJson(new StandardResponse(StatusResponse.NAME_ERROR,
                            "Descripcion invalida"));
                }
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
            }
        });

        // **************************************************************************************
        // GENERALES
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

    private static void init(UsuarioService usuarioService, ProyectoService proyectoService,
                            IncidenteService incidenteService) throws Exception {

        String nombres[] = {"Pedro", "Lionel", "Diego"};
        String apellidos[] = {"Tarazi", "Messi", "Maradona"};

        // 3 usuarios
        for (int i = 0; i < 3; i++) {
            Usuario user = new Usuario();
            user.setId(i);
            user.setNombre(nombres[i]);
            user.setApellido(apellidos[i]);
            usuarioService.addUsuario(user);
        }
        System.out.println("Usuarios creados");

        // 2 incidentes
        for (int i = 0; i < 2; i++){
            Incidente incidente = new Incidente();
            incidente.setId(i);
            incidente.setClasificacion(Clasificacion.NORMAL);
            incidente.setDescripcion("Incidente normal " + i);
            incidente.setReportador(usuarioService.getUsuario(2));
            incidente.setResponsable(usuarioService.getUsuario(i));
            if (i == 0){ incidente.setEstado(Estado.ASIGNADO); }
            else { incidente.setEstado(Estado.RESUELTO); }
            incidente.setFecha_creacion(new Date(2019-1900, 0, 2+i));
            incidente.setFecha_solucion(new Date());
            incidenteService.addIncidente(incidente);
        }
        System.out.println("Incidentes creados");
        // 2 proyectos
        for (int i = 0; i < 2; i++){
            Proyecto proyecto = new Proyecto();
            proyecto.setId(i);
            proyecto.setTitulo("Proyecto " + i);
            proyecto.setPropietario(usuarioService.getUsuario(i));
            proyecto.setIncidentes(incidenteService.getIncidente(i));
            proyectoService.addProyecto(proyecto);
        }
        System.out.println("Proyectos creados");
    }

}


