package DataAccess;

import Models.ActividadTuristica;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class ActividadRepository extends JsonRepository<ActividadTuristica> {

    private static final String FILE = "data/actividades.json";

    public ActividadRepository() {
        super(FILE, getListType());
    }

    private static Type getListType() {
        return new TypeToken<java.util.List<ActividadTuristica>>() {}.getType();
    }
}
