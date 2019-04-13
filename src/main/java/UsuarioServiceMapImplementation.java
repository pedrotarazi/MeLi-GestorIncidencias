import java.util.Collection;
import java.util.HashMap;

public class UsuarioServiceMapImplementation implements UsuarioService {

    private HashMap<Integer, Usuario> usuarioHashMap;

    public UsuarioServiceMapImplementation() {
        usuarioHashMap = new HashMap<>();
    }

    @Override
    public void addUsuario(Usuario usuario) throws UsuarioException{

        if (usuario.getId() == null || usuario.getId() < 0){
            throw new UsuarioException("ID");
        }
        else if (usuarioExist(usuario.getId())){
            throw new UsuarioException("EXIST");
        }
        else if (usuario.getNombre() == null || usuario.getNombre().equals("") ||
            usuario.getApellido() == null || usuario.getApellido().equals("")){
            throw new UsuarioException("NAME_ERROR");
        }
        else{
            usuarioHashMap.put(usuario.getId(), usuario);
        }
    }

    @Override
    public Collection<Usuario> getUsuarios() {
        return usuarioHashMap.values();
    }

    @Override
    public Usuario getUsuario(Integer id) throws UsuarioException {
        if (id == null || id < 0){
            throw new UsuarioException("ID_ERROR");
        }
        else if (usuarioExist(id)){
            return usuarioHashMap.get(id);
        }
        else{
            throw new UsuarioException("NOT_EXIST");
        }
    }

    @Override
    public Usuario editUsuario(Usuario usuario) throws UsuarioException {
        if (usuario.getId() == null || usuario.getId() < 0) {
            throw new UsuarioException("ID_ERROR");
        }
        else if (!usuarioExist(usuario.getId())){
            throw new UsuarioException("NOT_EXIST");
        }
        else{
            Usuario usuarioEditar = usuarioHashMap.get(usuario.getId());
            if (usuario.getNombre() == null || usuario.getApellido() == null ||
                usuario.getNombre().equals("") ||usuario.getApellido().equals("")){
                throw new UsuarioException("NAME_ERROR");
            }
            else{
                usuarioEditar.setNombre(usuario.getNombre());
                usuarioEditar.setApellido(usuario.getApellido());
                return usuarioEditar;
            }
        }
    }

    @Override
    public void deleteUsuario(Integer id) throws UsuarioException{
        if (usuarioExist(id)) {
            usuarioHashMap.remove(id);
        }
        else{
            throw new UsuarioException("NOT_EXIST");
        }
    }

    @Override
    public boolean usuarioExist(int id) {
        return usuarioHashMap.containsKey(id);
    }
}
