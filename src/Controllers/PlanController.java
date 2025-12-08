package Controllers;

import DataAccess.PlanRepository;
import Models.Plan;
import java.util.List;

public class PlanController {
    private PlanRepository planData;

    public PlanController() { this.planData = new PlanRepository(); }
    public PlanController(PlanRepository planData) { this.planData = planData; }

    public List<Plan> getAllPlanes() { return planData.getAllPlanes(); }
    public Plan getPlanById(String id) { return planData.findPlanById(id); }

    public boolean addPlan(String nombre, String descripcion, boolean preestablecido, double costoBase) {
        if (nombre == null || nombre.trim().isEmpty()) return false;
        if (costoBase < 0) return false;

        Plan p = new Plan();
        p.setNombre(nombre.trim());
        p.setDescripcion(descripcion != null ? descripcion.trim() : "");
        p.setPreestablecido(preestablecido);
        p.setCostoBase(costoBase);

        planData.savePlan(p);
        return true;
    }

    public boolean updatePlan(String id, String nombre, String descripcion, Boolean preestablecido, Double costoBase) {
        Plan p = planData.findPlanById(id);
        if (p == null) return false;

        if (nombre != null && !nombre.trim().isEmpty()) p.setNombre(nombre.trim());
        if (descripcion != null) p.setDescripcion(descripcion.trim());
        if (preestablecido != null) p.setPreestablecido(preestablecido);
        if (costoBase != null && costoBase >= 0) p.setCostoBase(costoBase);

        planData.savePlan(p);
        return true;
    }

    public boolean deletePlan(String id) {
        planData.deletePlan(id);
        return true;
    }
}
