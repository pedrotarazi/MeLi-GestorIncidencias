import java.util.Collection;

public interface IncidenteService {

    void addIncidente(Incidente incidente) throws IncidenteException;
    Collection<Incidente> getIncidentes();
    Incidente getIncidente(Integer id) throws IncidenteException;
    void changeEstado(Incidente incidente) throws IncidenteException;
    void editDescripcion(Incidente incidente) throws IncidenteException;
    boolean incidenteExist(Integer id);
}
