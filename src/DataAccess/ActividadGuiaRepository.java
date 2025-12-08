package DataAccess;

import Models.ActividadGuia;
import java.util.ArrayList;
import java.util.List;
import Models.ActividadTuristica;
import Models.Guia;

public class ActividadGuiaRepository {

    private IDataAccess<ActividadGuia> dataAccess;

    public ActividadGuiaRepository() {
        this.dataAccess = new JsonRepository<>("actividad_guia.json", ActividadGuia.class);
    }

    public ActividadGuiaRepository(IDataAccess<ActividadGuia> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<ActividadGuia> getAllActividadGuia() {
        return dataAccess.findAll();
    }

    public ActividadGuia findActividadGuiaById(String id) {
        return dataAccess.findById(id);
    }

    public void saveActividadGuia(ActividadGuia item) {
        dataAccess.save(item);
    }

    public void deleteActividadGuia(String id) {
        dataAccess.delete(id);
    }

    public List<ActividadTuristica> findActividadesByGuiaId(String guiaId) {
        List<ActividadGuia> relaciones = getAllActividadGuia();
        List<ActividadTuristica> actividades = new ActividadTuristicaRepository().getAllActividadesTuristicas();
        List<ActividadTuristica> result = new ArrayList<>();

        for (ActividadTuristica actividad : actividades) {
            for (ActividadGuia ag : relaciones) {
                if (ag.getIdGuia() != null &&
                    ag.getIdGuia().equals(guiaId) &&
                    ag.getIdActividad().equals(actividad.getId())) {

                    result.add(actividad);
                }
            }
        }

        return result;
    }


    public List<Guia> findGuiasByActividadId(String actividadId) {
        List<ActividadGuia> relaciones = getAllActividadGuia();
        List<Guia> guias = new GuiaRepository().getAllGuias();
        List<Guia> result = new ArrayList<>();

        for (Guia guia : guias) {
            for (ActividadGuia ag : relaciones) {
                if (ag.getIdActividad() != null &&
                    ag.getIdActividad().equals(actividadId) &&
                    ag.getIdGuia().equals(guia.getId())) {

                    result.add(guia);
                }
            }
        }

        return result;
    }

}
