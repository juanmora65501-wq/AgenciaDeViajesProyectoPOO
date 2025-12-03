package DataAccess;

import Models.Viaje;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class ViajeRepository extends JsonRepository<Viaje> {

    private static final String FILE = "data/viajes.json";

    public ViajeRepository() {
        super(FILE, getListType());
    }

    private static Type getListType() {
        return new TypeToken<java.util.List<Viaje>>() {}.getType();
    }
}
