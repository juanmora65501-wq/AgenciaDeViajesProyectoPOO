package Controllers;

import DataAccess.PlanActividadRepository;
import DataAccess.PlanRepository;
import DataAccess.ActividadTuristicaRepository;
import Models.ActividadTuristica;
import Models.Plan;
import Models.PlanActividad;
import java.util.List;

public class PlanActividadController {
    private PlanActividadRepository planActividadData;
    private PlanRepository planData;
    private ActividadTuristicaRepository actividadData;

    public PlanActividadController() {
        this.planActividadData = new PlanActividadRepository();
        this.planData = new PlanRepository();
        this.actividadData = new ActividadTuristicaRepository();
    }

    public PlanActividadController(PlanActividadRepository planActividadData, PlanRepository planData, ActividadTuristicaRepository actividadData) {
        this.planActividadData = planActividadData;
        this.planData = planData;
        this.actividadData = actividadData;
    }

    public List<PlanActividad> getAllPlanActividad() { return planActividadData.getAllPlanActividad(); }
    public PlanActividad getPlanActividadById(String id) { return planActividadData.findPlanActividadById(id); }

    public boolean addPlanActividad(String planId, String actividadId) {
        if (planId == null || actividadId == null) return false;
        if (planData.findPlanById(planId) == null) return false;
        if (actividadData.findActividadTuristicaById(actividadId) == null) return false;

        PlanActividad pa = new PlanActividad();
        pa.setIdPlan(planId);
        pa.setIdActividad(actividadId);
        planActividadData.savePlanActividad(pa);
        return true;
    }

    public boolean updatePlanActividad(String id, String planId, String actividadId) {
        PlanActividad pa = planActividadData.findPlanActividadById(id);
        if (pa == null) return false;

        if (planId != null) {
            if (planData.findPlanById(planId) == null) return false;
            pa.setIdPlan(planId);
        }
        if (actividadId != null) {
            if (actividadData.findActividadTuristicaById(actividadId) == null) return false;
            pa.setIdActividad(actividadId);
        }

        planActividadData.savePlanActividad(pa);
        return true;
    }

    public boolean deletePlanActividad(String id) {
        planActividadData.deletePlanActividad(id);
        return true;
    }

    public List<ActividadTuristica> findActividadesByPlanId(String planId) { return planActividadData.findActividadesByPlanId(planId); }
    public List<Plan> findPlanesByActividadId(String actividadId) { return planActividadData.findPlanesByActividadId(actividadId); }
}

