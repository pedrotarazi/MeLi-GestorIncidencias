import java.util.Collection;

public interface IncidenteService {

    void addIncidente(Incidente incidente);
    Collection<Incidente> getIncidentes();
    Incidente getIncidente(int id);
    void changeEstado(Incidente incidente);
    void addDescripcion(Incidente incidente);
    void editDescripcion(Incidente incidente);
}
