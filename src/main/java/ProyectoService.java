import java.util.Collection;

public interface ProyectoService {

    void addProyecto(Proyecto proyecto) throws Exception;
    Collection<Proyecto> getProyectos();
    Proyecto getProyecto(int id);
    Proyecto editProyecto(Proyecto proyecto) throws Exception;
    void deleteProyecto(int id);
    boolean proyectoExist(int id);
}
