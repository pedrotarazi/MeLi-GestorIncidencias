import java.util.Collection;
import java.util.HashMap;

public class IncidenteServiceMapImplementation implements IncidenteService{

    private HashMap<Integer, Incidente> incidenteHashMap;

    public IncidenteServiceMapImplementation(){
        incidenteHashMap = new HashMap<>();
    }

    @Override
    public void addIncidente(Incidente incidente) throws IncidenteException{
        if (incidente.getId() == null || incidente.getId() < 0 ){
            throw new IncidenteException("ID_ERROR");
        }
        else if (incidenteExist(incidente.getId())){
            throw new IncidenteException("EXIST");
        }
        else if (incidente.getClasificacion() == null || ( incidente.getClasificacion() != Clasificacion.CRITICO &&
            incidente.getClasificacion() != Clasificacion.NORMAL && incidente.getClasificacion() != Clasificacion.MENOR)){
            throw new IncidenteException("CLASIFICACION_ERROR");
        }
        else if (incidente.getDescripcion() == null){
            throw new IncidenteException("NAME_ERROR");
        }
        else if (incidente.getReportador().getId() == null || incidente.getReportador().getId() < 0 ||
                incidente.getResponsable().getId() == null || incidente.getResponsable().getId() < 0){
            throw new IncidenteException("USUARIO_ERROR");
        }
        else if (incidente.getEstado() == null || (incidente.getEstado() != Estado.ASIGNADO &&
            incidente.getEstado() != Estado.RESUELTO)){
            throw new IncidenteException("ESTADO_ERROR");
        }
        else if (incidente.getFecha_creacion() == null || incidente.getFecha_solucion() == null){
            throw new IncidenteException("DATE_ERROR");
        }
        else{
            incidenteHashMap.put(incidente.getId(), incidente);
        }
    }

    @Override
    public Collection<Incidente> getIncidentes() {
        return incidenteHashMap.values();
    }

    @Override
    public Incidente getIncidente(Integer id) throws IncidenteException {
        if (id == null || id < 0){
            throw new IncidenteException("ID_ERROR");
        }
        if (!incidenteExist(id)){
            throw new IncidenteException("NOT_EXIST");
        }
        else{
            return incidenteHashMap.get(id);
        }
    }

    @Override
    public Incidente changeEstado(Incidente incidente) throws IncidenteException {
        if (incidente.getEstado() == null || (incidente.getEstado() != Estado.RESUELTO)) {
            throw new IncidenteException("ESTADO_ERROR");
        }
        else if (!incidenteExist(incidente.getId())){
            throw new IncidenteException("NOT_EXIST");
        }
        else{
            Incidente incidenteEdit = incidenteHashMap.get(incidente.getId());
            incidenteEdit.setEstado(incidente.getEstado());
            return incidenteEdit;
        }
    }

    @Override
    public void editDescripcion(Incidente incidente) throws IncidenteException{
        if (incidente.getDescripcion() == null){
            throw new IncidenteException("NAME_ERROR");
        }
        incidenteHashMap.get(incidente.getId()).setDescripcion(incidente.getDescripcion());
    }

    @Override
    public boolean incidenteExist(Integer id){
        return incidenteHashMap.get(id) != null;
    }

    @Override
    public Collection<Incidente> getIncidentesResponsable(Integer id) throws IncidenteException{
        HashMap<Integer, Incidente> incidentes = new HashMap<>();
        for(int i=0; i < incidenteHashMap.size(); i++){
            if(incidenteHashMap.get(i).getResponsable().getId().intValue() == id){
                incidentes.put(i, incidenteHashMap.get(i));
            }
        }

        if(incidentes.isEmpty()){
            throw new IncidenteException("USUARIO_ERROR");
        }
        else{
            return incidentes.values();
        }
    }

    @Override
    public Collection<Incidente> getIncidentesReportador(Integer id) throws IncidenteException{
        HashMap<Integer, Incidente> incidentes = new HashMap<>();
        for(int i=0; i < incidenteHashMap.size(); i++){
            if(incidenteHashMap.get(i).getReportador().getId().intValue() == id){
                incidentes.put(i, incidenteHashMap.get(i));
            }
        }

        if(incidentes.isEmpty()){
            throw new IncidenteException("USUARIO_ERROR");
        }
        else{
            return incidentes.values();
        }
    }

    @Override
    public Collection<Incidente> getIncidentesAsignado(String estado) throws IncidenteException{
        HashMap<Integer, Incidente> incidentes = new HashMap<>();
        for(int i=0; i < incidenteHashMap.size(); i++){
            if(incidenteHashMap.get(i).getEstado().getEstado().equals(estado)){
                incidentes.put(i, incidenteHashMap.get(i));
            }
        }

        if(incidentes.isEmpty()){
            throw new IncidenteException("ESTADO_ERROR");
        }
        return incidentes.values();
    }
}
