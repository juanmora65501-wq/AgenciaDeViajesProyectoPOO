/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;


import Models.ActividadTuristica;
import java.util.ArrayList;
import java.util.List;

public class ActividadTuristicaRepository {
    private IDataAccess<ActividadTuristica> dataAccess;

    public ActividadTuristicaRepository() {
        this.dataAccess = new JsonRepository<>("actividadTuristica.json", ActividadTuristica.class);
    }

    public ActividadTuristicaRepository(IDataAccess<ActividadTuristica> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<ActividadTuristica> getAllActividadesTuristicas() {
        return dataAccess.findAll();
    }

    public ActividadTuristica findActividadTuristicaById(String id) {
        return dataAccess.findById(id);
    }

    public void saveActividadTuristica(ActividadTuristica item) {
        dataAccess.save(item);
    }

    public void deleteActividadTuristica(String id) {
        dataAccess.delete(id);
    }

    public List<ActividadTuristica> findByMunicipioId(String municipioId) {
        List<ActividadTuristica> actividades = getAllActividadesTuristicas();
        List<ActividadTuristica> result = new ArrayList<>();

        for (ActividadTuristica actividad : actividades) {
            if (actividad.getMunicipioId() != null && actividad.getMunicipioId().equals(municipioId)) {
                result.add(actividad);
            }
        }

        return result;
    }
}

    

