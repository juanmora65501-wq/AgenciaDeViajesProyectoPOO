package DataAccess;

import Models.ViajePlan;
import Models.Plan;
import Models.Viaje;
import java.util.List;
import java.util.ArrayList;

public class ViajePlanRepository {

    private IDataAccess<ViajePlan> dataAccess;

    public ViajePlanRepository() {
        this.dataAccess = new JsonRepository<>("viaje_plan.json", ViajePlan.class);
    }

    public ViajePlanRepository(IDataAccess<ViajePlan> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<ViajePlan> getAllViajePlan() {
        return dataAccess.findAll();
    }

    public ViajePlan findViajePlanById(String id) {
        return dataAccess.findById(id);
    }

    public void saveViajePlan(ViajePlan item) {
        dataAccess.save(item);
    }

    public void deleteViajePlan(String id) {
        dataAccess.delete(id);
    }
        
    public List<Viaje> findViajesByPlanId(String planId) {
        List<ViajePlan> viajePlanes = getAllViajePlan();
        List<Viaje> viajes = new ViajeRepository().getAllViajes();
        List<Viaje> result = new ArrayList<>();

        for (Viaje viaje : viajes) {
            for (ViajePlan vp : viajePlanes) {
                if (vp.getIdPlan() != null &&
                    vp.getIdPlan().equals(planId) &&
                    vp.getIdViaje().equals(viaje.getId())) {

                    result.add(viaje);
                }
            }
        }

        return result;
    }


    public List<Plan> findPlanesByViajeId(String viajeId) {
        List<ViajePlan> viajePlanes = getAllViajePlan();
        List<Plan> planes = new PlanRepository().getAllPlanes();
        List<Plan> result = new ArrayList<>();

        for (Plan plan : planes) {
            for (ViajePlan vp : viajePlanes) {
                if (vp.getIdViaje() != null &&
                    vp.getIdViaje().equals(viajeId) &&
                    vp.getIdPlan().equals(plan.getId())) {

                    result.add(plan);
                }
            }
        }

        return result;
    }
}
