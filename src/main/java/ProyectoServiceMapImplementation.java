import java.util.Collection;
import java.util.HashMap;

public class ProyectoServiceMapImplementation implements ProyectoService{

    private HashMap<Integer, Proyecto> proyectoHashMap;

    public ProyectoServiceMapImplementation() {
        this.proyectoHashMap = new HashMap<>();
    }

    @Override
    public void addProyecto(Proyecto proyecto) throws Exception{
        if (proyecto.getId() < 0){
            throw new Exception("Id invalido");
        }
        else if (proyecto.getTitulo().equals("") || proyecto.getTitulo() == null){
            throw new Exception("Titulo invalido");
        }
        else if (proyecto.getPropietario() == null){
            throw new Exception("Usuario invalido");
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
    public Proyecto getProyecto(int id) {
        return proyectoHashMap.get(id);
    }

    @Override
    public Proyecto editProyecto(Proyecto proyecto) throws Exception {
        if (proyecto.getId() < 0){
            throw new Exception("No existe el proyecto que desea editar");
        }
        else{
            Proyecto proyectoEditar = proyectoHashMap.get(proyecto.getId());
            if (proyecto.getTitulo().equals("") || proyecto.getPropietario() == null){
                throw new Exception("Titulo o Propieta invalido");
            }
            else{
                proyectoEditar.setTitulo(proyecto.getTitulo());
                proyectoEditar.setPropietario(proyecto.getPropietario());
                return proyectoEditar;
            }
        }
    }

    @Override
    public void deleteProyecto(int id) {
        proyectoHashMap.remove(id);
    }

    @Override
    public boolean proyectoExist(int id) {
        return proyectoHashMap.containsKey(id);
    }
}
