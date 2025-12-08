package Controllers;

import DataAccess.ActividadGuiaRepository;
import DataAccess.ActividadTuristicaRepository;
import DataAccess.GuiaRepository;
import Models.ActividadGuia;
import Models.ActividadTuristica;
import Models.Guia;
import java.util.List;

public class ActividadGuiaController {
    private ActividadGuiaRepository actividadGuiaData;
    private ActividadTuristicaRepository actividadData;
    private GuiaRepository guiaData;

    public ActividadGuiaController() {
        this.actividadGuiaData = new ActividadGuiaRepository();
        this.actividadData = new ActividadTuristicaRepository();
        this.guiaData = new GuiaRepository();
    }
    public ActividadGuiaController(ActividadGuiaRepository actividadGuiaData, ActividadTuristicaRepository actividadData, GuiaRepository guiaData) {
        this.actividadGuiaData = actividadGuiaData;
        this.actividadData = actividadData;
        this.guiaData = guiaData;
    }

    public List<ActividadGuia> getAllActividadGuia() { return actividadGuiaData.getAllActividadGuia(); }
    public ActividadGuia getActividadGuiaById(String id) { return actividadGuiaData.findActividadGuiaById(id); }

    public boolean addActividadGuia(String actividadId, String guiaId) {
        if (actividadId == null || actividadId.trim().isEmpty()) return false;
        if (guiaId == null || guiaId.trim().isEmpty()) return false;
        if (actividadData.findActividadTuristicaById(actividadId) == null) return false;
        if (guiaData.findGuiaById(guiaId) == null) return false;

        ActividadGuia ag = new ActividadGuia();
        ag.setIdActividad(actividadId.trim());
        ag.setIdGuia(guiaId.trim());

        actividadGuiaData.saveActividadGuia(ag);
        return true;
    }

    public boolean updateActividadGuia(String id, String actividadId, String guiaId) {
        ActividadGuia ag = actividadGuiaData.findActividadGuiaById(id);
        if (ag == null) return false;

        if (actividadId != null && !actividadId.trim().isEmpty()) {
            if (actividadData.findActividadTuristicaById(actividadId) == null) return false;
            ag.setIdActividad(actividadId.trim());
        }
        if (guiaId != null && !guiaId.trim().isEmpty()) {
            if (guiaData.findGuiaById(guiaId) == null) return false;
            ag.setIdGuia(guiaId.trim());
        }

        actividadGuiaData.saveActividadGuia(ag);
        return true;
    }

    public boolean deleteActividadGuia(String id) {
        actividadGuiaData.deleteActividadGuia(id);
        return true;
    }

    public List<ActividadTuristica> findActividadesByGuiaId(String guiaId) { return actividadGuiaData.findActividadesByGuiaId(guiaId); }
    public List<Guia> findGuiasByActividadId(String actividadId) { return actividadGuiaData.findGuiasByActividadId(actividadId); }
}
