package DataAccess;

import Models.Usuario;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class UsuarioRepository extends JsonRepository<Usuario> {

    private static final String FILE = "data/usuarios.json";

    public UsuarioRepository() {
        super(FILE, getListType());
    }

    private static Type getListType() {
        return new TypeToken<java.util.List<Usuario>>() {}.getType();
    }
}
