import java.util.Collection;

public interface IncidenteService {

    void addIncidente(Incidente incidente) throws IncidenteException;
    Collection<Incidente> getIncidentes();
    Incidente getIncidente(Integer id) throws IncidenteException;
    Incidente changeEstado(Integer integer, Incidente incidente) throws IncidenteException;
    void editDescripcion(Integer id, Incidente incidente) throws IncidenteException;
    boolean incidenteExist(Integer id);
    Collection<Incidente> getIncidentesResponsable(Integer id) throws IncidenteException;
    Collection<Incidente> getIncidentesReportador(Integer id) throws IncidenteException;
    Collection<Incidente> getIncidentesAsignado(String estado) throws IncidenteException;
}
