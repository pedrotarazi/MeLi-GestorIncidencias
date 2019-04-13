import java.util.Collection;

public interface UsuarioService {

    void addUsuario(Usuario usuario) throws Exception;
    Collection<Usuario> getUsuarios();
    Usuario getUsuario(Integer id) throws UsuarioException;
    Usuario editUsuario(Usuario usuario) throws Exception;
    void deleteUsuario(Integer id) throws UsuarioException;
    boolean usuarioExist(int id);
}
