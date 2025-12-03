package DataAccess;

import Models.Hotel;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class HotelRepository extends JsonRepository<Hotel> {

    private static final String FILE = "data/hoteles.json";

    public HotelRepository() {
        super(FILE, getListType());
    }

    private static Type getListType() {
        return new TypeToken<java.util.List<Hotel>>() {}.getType();
    }
}
