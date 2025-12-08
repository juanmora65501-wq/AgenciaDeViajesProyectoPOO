package Controllers;

import DataAccess.ViajePlanRepository;
import DataAccess.ViajeRepository;
import DataAccess.PlanRepository;
import Models.Plan;
import Models.Viaje;
import Models.ViajePlan;
import java.util.List;

public class ViajePlanController {
    private ViajePlanRepository viajePlanData;
    private ViajeRepository viajeData;
    private PlanRepository planData;

    public ViajePlanController() {
        this.viajePlanData = new ViajePlanRepository();
        this.viajeData = new ViajeRepository();
        this.planData = new PlanRepository();
    }

    public ViajePlanController(ViajePlanRepository viajePlanData, ViajeRepository viajeData, PlanRepository planData) {
        this.viajePlanData = viajePlanData;
        this.viajeData = viajeData;
        this.planData = planData;
    }

    public List<ViajePlan> getAllViajePlan() { return viajePlanData.getAllViajePlan(); }
    public ViajePlan getViajePlanById(String id) { return viajePlanData.findViajePlanById(id); }

    public boolean addViajePlan(String viajeId, String planId) {
        if (viajeId == null || planId == null) return false;
        if (viajeData.findViajeById(viajeId) == null) return false;
        if (planData.findPlanById(planId) == null) return false;

        ViajePlan vp = new ViajePlan();
        vp.setIdViaje(viajeId);
        vp.setIdPlan(planId);
        viajePlanData.saveViajePlan(vp);
        return true;
    }

    public boolean updateViajePlan(String id, String viajeId, String planId) {
        ViajePlan vp = viajePlanData.findViajePlanById(id);
        if (vp == null) return false;

        if (viajeId != null) {
            if (viajeData.findViajeById(viajeId) == null) return false;
            vp.setIdViaje(viajeId);
        }
        if (planId != null) {
            if (planData.findPlanById(planId) == null) return false;
            vp.setIdPlan(planId);
        }

        viajePlanData.saveViajePlan(vp);
        return true;
    }

    public boolean deleteViajePlan(String id) {
        viajePlanData.deleteViajePlan(id);
        return true;
    }

    public List<Viaje> findViajesByPlanId(String planId) { return viajePlanData.findViajesByPlanId(planId); }
    public List<Plan> findPlanesByViajeId(String viajeId) { return viajePlanData.findPlanesByViajeId(viajeId); }
}

