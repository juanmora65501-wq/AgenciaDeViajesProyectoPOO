package DataAccess;

import Models.Plan;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class PlanRepository extends JsonRepository<Plan> {

    private static final String FILE = "data/planes.json";

    public PlanRepository() {
        super(FILE, getListType());
    }

    private static Type getListType() {
        return new TypeToken<java.util.List<Plan>>() {}.getType();
    }
}
