import java.util.ArrayList;

public class Proyecto {
    private Integer id;
    private String titulo;
    private Usuario propietario;
    private ArrayList<Incidente> incidentes;

    public Proyecto(){ incidentes = new ArrayList<>(); }

    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public ArrayList<Incidente> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(Incidente incidente) {
        incidentes.add(incidente);
    }
}
