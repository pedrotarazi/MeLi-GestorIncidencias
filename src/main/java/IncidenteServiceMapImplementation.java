import java.util.Collection;
import java.util.HashMap;

public class IncidenteServiceMapImplementation implements IncidenteService{

    private HashMap<Integer, Incidente> incidenteHashMap;

    public IncidenteServiceMapImplementation(){
        incidenteHashMap = new HashMap<>();
    }

    @Override
    public void addIncidente(Incidente incidente){

    }

    @Override
    public Collection<Incidente> getIncidentes() {
        return null;
    }

    @Override
    public Incidente getIncidente(int id) {
        return null;
    }

    @Override
    public void changeEstado(Incidente incidente) {

    }

    @Override
    public void addDescripcion(Incidente incidente) {

    }

    @Override
    public void editDescripcion(Incidente incidente) {
        incidenteHashMap.get(incidente.getId()).setDescripcion(incidente.getDescripcion());
    }
}
