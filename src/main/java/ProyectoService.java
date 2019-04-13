import java.util.Collection;

public interface ProyectoService {

    void addProyecto(Proyecto proyecto) throws Exception;
    Collection<Proyecto> getProyectos();
    Proyecto getProyecto(int id);
    Proyecto editProyecto(Proyecto proyecto) throws Exception;
    boolean deleteProyecto(int id) throws Exception;
    boolean proyectoExist(int id);
}
