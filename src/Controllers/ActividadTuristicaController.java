package Controllers;

import DataAccess.ActividadTuristicaRepository;
import DataAccess.MunicipioRepository;
import Models.ActividadTuristica;
import java.util.List;

public class ActividadTuristicaController {
    private ActividadTuristicaRepository actividadData;
    private MunicipioRepository municipioData;

    public ActividadTuristicaController() {
        this.actividadData = new ActividadTuristicaRepository();
        this.municipioData = new MunicipioRepository();
    }

    public ActividadTuristicaController(ActividadTuristicaRepository actividadData, MunicipioRepository municipioData) {
        this.actividadData = actividadData;
        this.municipioData = municipioData;
    }

    public List<ActividadTuristica> getAllActividades() { return actividadData.getAllActividadesTuristicas(); }
    public ActividadTuristica getActividadById(String id) { return actividadData.findActividadTuristicaById(id); }

    public boolean addActividad(String nombre, String descripcion, String municipioId, String duracion, double costo) {
        if (nombre == null || nombre.trim().isEmpty()) return false;
        if (municipioId == null || municipioId.trim().isEmpty()) return false;
        if (municipioData.findMunicipioById(municipioId) == null) return false;
        if (costo < 0) return false;

        ActividadTuristica a = new ActividadTuristica();
        a.setNombre(nombre.trim());
        a.setDescripcion(descripcion != null ? descripcion.trim() : "");
        a.setMunicipioId(municipioId);
        a.setDuracion(duracion != null ? duracion.trim() : "");
        a.setCosto(costo);

        actividadData.saveActividadTuristica(a);
        return true;
    }

    public boolean updateActividad(String id, String nombre, String descripcion, String duracion, Double costo) {
        ActividadTuristica a = actividadData.findActividadTuristicaById(id);
        if (a == null) return false;

        if (nombre != null && !nombre.trim().isEmpty()) a.setNombre(nombre.trim());
        if (descripcion != null) a.setDescripcion(descripcion.trim());
        if (duracion != null) a.setDuracion(duracion.trim());
        if (costo != null && costo >= 0) a.setCosto(costo);

        actividadData.saveActividadTuristica(a);
        return true;
    }

    public boolean deleteActividad(String id) {
        actividadData.deleteActividadTuristica(id);
        return true;
    }

    public List<ActividadTuristica> findByMunicipioId(String municipioId) { return actividadData.findByMunicipioId(municipioId); }
}
