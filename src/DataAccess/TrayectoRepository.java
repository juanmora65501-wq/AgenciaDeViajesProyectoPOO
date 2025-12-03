package DataAccess;

import Models.Trayecto;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class TrayectoRepository extends JsonRepository<Trayecto> {

    private static final String FILE = "data/trayectos.json";

    public TrayectoRepository() {
        super(FILE, getListType());
    }

    private static Type getListType() {
        return new TypeToken<java.util.List<Trayecto>>() {}.getType();
    }
}
