package DataAccess;

import Models.Guia;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class GuiaRepository extends JsonRepository<Guia> {

    private static final String FILE = "data/guias.json";

    public GuiaRepository() {
        super(FILE, getListType());
    }

    private static Type getListType() {
        return new TypeToken<java.util.List<Guia>>() {}.getType();
    }
}
