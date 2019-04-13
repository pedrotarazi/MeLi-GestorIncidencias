import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import java.util.Collection;
import java.util.HashMap;

public class UsuarioServiceMapImplementation implements UsuarioService {

    private HashMap<Integer, Usuario> usuarioHashMap;

    public UsuarioServiceMapImplementation() {
        usuarioHashMap = new HashMap<>();
    }

    @Override
    public void addUsuario(Usuario usuario) throws Exception{
        if (usuario.getId() == null || usuario.getId() < 0 || usuarioExist(usuario.getId())){
            throw new Exception("Id invalido o ya existente");
        }
        else if (usuario.getNombre().equals("") || usuario.getNombre() == null){
            throw new Exception("Nombre invalido");
        }
        else if (usuario.getApellido().equals("") || usuario.getApellido() == null){
            throw new Exception("Apellido invalido");
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
    public Usuario getUsuario(int id) {
        return usuarioHashMap.get(id);
    }

    @Override
    public Usuario editUsuario(Usuario usuario) throws Exception {
        if (usuario.getId() == null || usuario.getId() < 0 || usuarioExist(usuario.getId())){
            throw new Exception("No existe el usuario que desea editar");
        }
        else{
            Usuario usuarioEditar = usuarioHashMap.get(usuario.getId());
            if (usuario.getNombre() == null || usuario.getApellido() == null){
                throw new Exception("Debe especificar un nombre y/o apellido");
            }
            else{
                usuarioEditar.setNombre(usuario.getNombre());
                usuarioEditar.setApellido(usuario.getApellido());
                return usuarioEditar;
            }
        }
    }

    @Override
    public boolean deleteUsuario(int id) {
        return usuarioHashMap.remove(id) != null;
    }

    @Override
    public boolean usuarioExist(int id) {
        return usuarioHashMap.containsKey(id);
    }
}
