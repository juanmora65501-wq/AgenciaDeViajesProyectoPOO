package DataAccess;

import Models.Plan;
import java.util.List;

public class PlanRepository {

    private IDataAccess<Plan> dataAccess;

    public PlanRepository() {
        this.dataAccess = new JsonRepository<>("planes.json", Plan.class);
    }

    public PlanRepository(IDataAccess<Plan> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<Plan> getAllPlanes() {
        return dataAccess.findAll();
    }

    public Plan findPlanById(String id) {
        return dataAccess.findById(id);
    }

    public void savePlan(Plan plan) {
        dataAccess.save(plan);
    }

    public void deletePlan(String id) {
        dataAccess.delete(id);
    }
}
