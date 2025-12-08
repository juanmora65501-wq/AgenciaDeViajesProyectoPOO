package DataAccess;

import Models.Trayecto;
import java.util.List;
import java.util.ArrayList;

public class TrayectoRepository {

    private IDataAccess<Trayecto> dataAccess;

    public TrayectoRepository() {
        this.dataAccess = new JsonRepository<>("trayectos.json", Trayecto.class);
    }

    public TrayectoRepository(IDataAccess<Trayecto> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<Trayecto> getAllTrayectos() {
        return dataAccess.findAll();
    }

    public Trayecto findTrayectoById(String id) {
        return dataAccess.findById(id);
    }

    public void saveTrayecto(Trayecto trayecto) {
        dataAccess.save(trayecto);
    }

    public void deleteTrayecto(String id) {
        dataAccess.delete(id);
    }
    
    public List<Trayecto> findDestinosByMunicipioOrigen(String origenId) {
        List<Trayecto> items = getAllTrayectos();
        List<Trayecto> result = new ArrayList<>();

        for (Trayecto t : items) {
            if (t.getIdMunicipioOrigen() != null && t.getIdMunicipioOrigen().equals(origenId)) {
                result.add(t);
            }
        }

        return result;
    }

    // 🔹 Obtener trayectos origen por municipio de destino
    public List<Trayecto> findOrigenesByMunicipioDestino(String destinoId) {
        List<Trayecto> items = getAllTrayectos();
        List<Trayecto> result = new ArrayList<>();

        for (Trayecto t : items) {
            if (t.getIdMunicipioDestino() != null && t.getIdMunicipioDestino().equals(destinoId)) {
                result.add(t);
            }
        }

        return result;
    }
}
