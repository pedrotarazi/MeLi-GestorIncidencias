import java.util.ArrayList;
import java.util.Collection;

public interface ProyectoService {

    void addProyecto(Proyecto proyecto) throws ProyectoException;
    Collection<Proyecto> getProyectos();
    Proyecto getProyecto(Integer id) throws ProyectoException;
    Proyecto editProyecto(Proyecto proyecto) throws ProyectoException;
    boolean deleteProyecto(Integer id) throws ProyectoException;
    boolean proyectoExist(Integer id);
    Collection<Proyecto> getProyectoUsuarioPropietario(Integer id) throws ProyectoException;

    Collection<Incidente> getIncidentesProyecto(Integer id) throws ProyectoException;
}
