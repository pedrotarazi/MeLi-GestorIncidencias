import java.util.Collection;

public interface IncidenteService {

    void addIncidente(Incidente incidente) throws Exception;
    Collection<Incidente> getIncidentes();
    Incidente getIncidente(int id);
    void changeEstado(Incidente incidente) throws Exception;
    void editDescripcion(Incidente incidente) throws Exception;
    boolean incidenteExist(Incidente incidente);
}
