import java.util.Collection;

public interface IncidenteService {

    void addIncidente(Incidente incidente) throws IncidenteException;
    Collection<Incidente> getIncidentes();
    Incidente getIncidente(Integer id) throws IncidenteException;
    Incidente changeEstado(Incidente incidente) throws IncidenteException;
    void editDescripcion(Incidente incidente) throws IncidenteException;
    boolean incidenteExist(Integer id);
    Collection<Incidente> getIncidentesResponsable(Integer id) throws IncidenteException;
    Collection<Incidente> getIncidentesReportador(Integer id) throws IncidenteException;
    Collection<Incidente> getIncidentesAsignado(String estado) throws IncidenteException;
}
