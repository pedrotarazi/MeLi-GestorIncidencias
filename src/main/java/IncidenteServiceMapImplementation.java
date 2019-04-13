import sun.misc.ExtensionInstallationException;

import java.util.Collection;
import java.util.HashMap;

public class IncidenteServiceMapImplementation implements IncidenteService{

    private HashMap<Integer, Incidente> incidenteHashMap;

    public IncidenteServiceMapImplementation(){
        incidenteHashMap = new HashMap<>();
    }

    @Override
    public void addIncidente(Incidente incidente) throws Exception{
        if (incidenteExist(incidente) || incidente.getId() < 0 || incidente.getId() == null){
            throw new Exception("Id invalido");
        }
        else if (incidente.getClasificacion() == null || incidente.getClasificacion() != Clasificacion.CRITICO ||
            incidente.getClasificacion() != Clasificacion.NORMAL ||
                incidente.getClasificacion() != Clasificacion.MENOR){
            throw new Exception("Falta clasificacion o es invalida (debe ser CRITICO, NORMAL o MENOR");
        }
        else if (incidente.getDescripcion() == null){
            throw new Exception("Falta campo descripcion");
        }
        else if (incidente.getReportador().getId() < 0 || incidente.getReportador().getId() == null){
            throw new Exception("Id de Reportador invalido");
        }
        else if (incidente.getResponsable().getId() < 0 || incidente.getResponsable().getId() == null){
            throw new Exception("Id de Responsable invalido");
        }
        else if (incidente.getEstado() == null || incidente.getEstado() != Estado.ASIGNADO ||
            incidente.getEstado() != Estado.RESUELTO){
            throw new Exception("Falta estado o es invalido (ASIGADO o RESUELTO");
        }
        else if (incidente.getFecha_creacion() == null || incidente.getFecha_solucion() == null){
            throw new Exception("Fecha incorrecta");
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
    public Incidente getIncidente(int id) {
        return incidenteHashMap.get(id);
    }

    @Override
    public void changeEstado(Incidente incidente) throws Exception {
        if (incidente.getEstado() == null || incidente.getEstado() != Estado.ASIGNADO ||
                incidente.getEstado() != Estado.RESUELTO) {
            throw new Exception("Estado invalido");
        }
        else if (incidenteExist(incidente)){
            throw new Exception("Incidente inexistente");
        }
        else{
            Incidente incidenteEdit = incidenteHashMap.get(incidente.getId());
            incidenteEdit.setEstado(incidente.getEstado());
        }
    }

    @Override
    public void editDescripcion(Incidente incidente) throws Exception{
        if (incidente.getDescripcion() == null){
            throw new Exception("Descripcion invalida");
        }
        incidenteHashMap.get(incidente.getId()).setDescripcion(incidente.getDescripcion());
    }

    @Override
    public boolean incidenteExist(Incidente incidente){
        return incidenteHashMap.get(incidente.getId()) != null;
    }
}
