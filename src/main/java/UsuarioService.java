import java.util.Collection;

public interface UsuarioService {

    void addUsuario(Usuario usuario) throws Exception;
    Collection<Usuario> getUsuarios();
    Usuario getUsuario(Integer id) throws UsuarioException;
    Usuario editUsuario(Usuario usuario) throws Exception;
    void deleteUsuario(Integer id, IncidenteService incidentes, ProyectoService proyectos)
            throws UsuarioException, ProyectoException, IncidenteException;
    boolean usuarioExist(int id);
}
