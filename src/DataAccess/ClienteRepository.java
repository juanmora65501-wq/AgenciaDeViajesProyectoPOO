package DataAccess;

import Models.Cliente;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class ClienteRepository extends JsonRepository<Cliente> {

    private static final String FILE = "data/clientes.json";

    public ClienteRepository() {
        super(FILE, getListType());
    }

    private static Type getListType() {
        return new TypeToken<java.util.List<Cliente>>() {}.getType();
    }
}
