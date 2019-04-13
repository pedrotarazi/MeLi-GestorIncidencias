import java.util.Collection;

public interface UsuarioService {

    void addUsuario(Usuario usuario) throws Exception;
    Collection<Usuario> getUsuarios();
    Usuario getUsuario(int id);
    Usuario editUsuario(Usuario usuario) throws Exception;
    boolean deleteUsuario(int id);
    boolean usuarioExist(int id);
}
