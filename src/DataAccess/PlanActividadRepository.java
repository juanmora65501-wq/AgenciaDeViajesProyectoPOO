package DataAccess;

import Models.PlanActividad;
import java.util.ArrayList;
import java.util.List;
import Models.ActividadTuristica;
import Models.Plan;

public class PlanActividadRepository {

    private IDataAccess<PlanActividad> dataAccess;

    public PlanActividadRepository() {
        this.dataAccess = new JsonRepository<>("plan_actividad.json", PlanActividad.class);
    }

    public PlanActividadRepository(IDataAccess<PlanActividad> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<PlanActividad> getAllPlanActividad() {
        return dataAccess.findAll();
    }

    public PlanActividad findPlanActividadById(String id) {
        return dataAccess.findById(id);
    }

    public void savePlanActividad(PlanActividad item) {
        dataAccess.save(item);
    }

    public void deletePlanActividad(String id) {
        dataAccess.delete(id);
    }


    public List<ActividadTuristica> findActividadesByPlanId(String planId) {
        List<PlanActividad> planActividades = getAllPlanActividad();
        List<ActividadTuristica> actividades = new ActividadTuristicaRepository().getAllActividadesTuristicas();
        List<ActividadTuristica> result = new ArrayList<>();

        for (ActividadTuristica actividad : actividades) {
            for (PlanActividad pa : planActividades) {
                if (pa.getIdPlan() != null &&
                    pa.getIdPlan().equals(planId) &&
                    pa.getIdActividad().equals(actividad.getId())) {

                    result.add(actividad);
                }
            }
        }

        return result;
    }

    public List<Plan> findPlanesByActividadId(String actividadId) {
        List<PlanActividad> planActividades = getAllPlanActividad();
        List<Plan> planes = new PlanRepository().getAllPlanes();
        List<Plan> result = new ArrayList<>();

        for (Plan plan : planes) {
            for (PlanActividad pa : planActividades) {
                if (pa.getIdActividad() != null &&
                    pa.getIdActividad().equals(actividadId) &&
                    pa.getIdPlan().equals(plan.getId())) {

                    result.add(plan);
                }
            }
        }

        return result;
    }

}
