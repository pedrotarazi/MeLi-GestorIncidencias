import java.util.Collection;

public interface ProyectoService {

    void addProyecto(Proyecto proyecto) throws ProyectoException;
    Collection<Proyecto> getProyectos();
    Proyecto getProyecto(Integer id) throws ProyectoException;
    Proyecto editProyecto(Proyecto proyecto) throws ProyectoException;
    boolean deleteProyecto(Integer id) throws ProyectoException;
    boolean proyectoExist(Integer id);
}
