package DataAccess;

import Models.Usuario;
import java.util.List;

public class UsuarioRepository {

    private IDataAccess<Usuario> dataAccess;

    public UsuarioRepository() {
        this.dataAccess = new JsonRepository<>("usuarios.json", Usuario.class);
    }

    public UsuarioRepository(IDataAccess<Usuario> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<Usuario> getAllUsuarios() {
        return dataAccess.findAll();
    }

    public Usuario findUsuarioById(String id) {
        return dataAccess.findById(id);
    }

    public void saveUsuario(Usuario usuario) {
        dataAccess.save(usuario);
    }

    public void deleteUsuario(String id) {
        dataAccess.delete(id);
    }
}
