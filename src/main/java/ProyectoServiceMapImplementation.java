import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ProyectoServiceMapImplementation implements ProyectoService{

    private HashMap<Integer, Proyecto> proyectoHashMap;

    public ProyectoServiceMapImplementation() {
        this.proyectoHashMap = new HashMap<>();
    }

    @Override
    public void addProyecto(Proyecto proyecto) throws ProyectoException{
        if (proyecto.getId() == null || proyecto.getId() < 0){
            throw new ProyectoException("ID_ERROR");
        }
        else if(proyectoExist(proyecto.getId())){
            throw new ProyectoException("EXIST");
        }
        else if (proyecto.getTitulo() == null || proyecto.getTitulo().equals("")){
            throw new ProyectoException("NAME_ERROR");
        }
        else if (proyecto.getPropietario() == null || proyecto.getPropietario().equals("") ||
                proyecto.getPropietario().getId() == null || proyecto.getPropietario().getId() < 0 ||
                proyecto.getPropietario().getNombre() == null || proyecto.getPropietario().getNombre().equals("") ||
                proyecto.getPropietario().getApellido() == null || proyecto.getPropietario().getApellido().equals("")){
            throw new ProyectoException("PROPIETARIO_ERROR");
        }
        else{
            proyectoHashMap.put(proyecto.getId(), proyecto);
        }
    }

    @Override
    public Collection<Proyecto> getProyectos() {
        return proyectoHashMap.values();
    }

    @Override
    public Proyecto getProyecto(Integer id) throws ProyectoException {
        if (id == null || id < 0){
            throw new ProyectoException("ID_ERROR");
        }
        else if (proyectoExist(id)) {
            return proyectoHashMap.get(id);
        }
        else{
            throw new ProyectoException("NOT_EXIST");
        }
    }

    @Override
    public Proyecto editProyecto(Proyecto proyecto) throws ProyectoException {
        if (proyecto.getId() == null || proyecto.getId() < 0){
            throw new ProyectoException("ID_ERROR");
        }
        else if (!proyectoExist(proyecto.getId())){
            throw new ProyectoException("NOT_EXIST");
        }
        else{
            Proyecto proyectoEditar = proyectoHashMap.get(proyecto.getId());
            if (proyecto.getPropietario() == null || proyecto.getPropietario().equals("")){
                throw new ProyectoException("PROPIETARIO_ERROR");
            }
            else if (proyecto.getTitulo() == null || proyecto.getTitulo().equals("")){
                throw new ProyectoException("NAME_ERROR");
            }
            else{
                proyectoEditar.setTitulo(proyecto.getTitulo());
                proyectoEditar.setPropietario(proyecto.getPropietario());
                return proyectoEditar;
            }
        }
    }

    @Override
    public boolean deleteProyecto(Integer id) throws ProyectoException {
        if (proyectoExist(id)){
            if (proyectoHashMap.get(id).getIncidentes().isEmpty()){
                return proyectoHashMap.remove(id) != null;
            }
            else{
                throw new ProyectoException("NOT_REMOVE");
            }
        }
        else{
            throw new ProyectoException("NOT_EXIST");
        }
    }

    @Override
    public boolean proyectoExist(Integer id) {
        return proyectoHashMap.containsKey(id);
    }

    @Override
    public Collection<Proyecto> getProyectoUsuarioPropietario(Integer id) throws ProyectoException{
        HashMap<Integer, Proyecto> proyectos = new HashMap<>();
        for(int i=0; i < proyectoHashMap.size(); i++){
            if (proyectoHashMap.get(i).getPropietario().getId().intValue() == id){
                System.out.println(i);
                proyectos.put(i, proyectoHashMap.get(i));
            }
        }
        if (proyectos.isEmpty()){
            throw new ProyectoException("NO_USER");
        }
        return proyectos.values();
    }

    @Override
    public Collection<Incidente> getIncidentesProyecto(Integer id) throws ProyectoException{
        if (proyectoExist(id)) {
            if (proyectoHashMap.get(id).getIncidentes().isEmpty()) {
                throw new ProyectoException("NO_INCIDENT");
            }
            return proyectoHashMap.get(id).getIncidentes();
        }
        else{
            throw new ProyectoException("NO_PROJECT");
        }
    }

}
