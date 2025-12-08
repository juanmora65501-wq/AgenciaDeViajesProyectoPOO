package Controllers;

import DataAccess.TrayectoRepository;
import DataAccess.MunicipioRepository;
import Models.Trayecto;
import java.util.List;

public class TrayectoController {
    private TrayectoRepository trayectoData;
    private MunicipioRepository municipioData;

    public TrayectoController() {
        this.trayectoData = new TrayectoRepository();
        this.municipioData = new MunicipioRepository();
    }

    public TrayectoController(TrayectoRepository trayectoData, MunicipioRepository municipioData) {
        this.trayectoData = trayectoData;
        this.municipioData = municipioData;
    }

    public List<Trayecto> getAllTrayectos() { return trayectoData.getAllTrayectos(); }
    public Trayecto getTrayectoById(String id) { return trayectoData.findTrayectoById(id); }

    public boolean addTrayecto(String origenId, String destinoId) {
        if (origenId == null || destinoId == null) return false;
        if (municipioData.findMunicipioById(origenId) == null) return false;
        if (municipioData.findMunicipioById(destinoId) == null) return false;

        Trayecto t = new Trayecto();
        t.setIdMunicipioOrigen(origenId);
        t.setIdMunicipioDestino(destinoId);
        trayectoData.saveTrayecto(t);
        return true;
    }

    public boolean updateTrayecto(String id, String origenId, String destinoId) {
        Trayecto t = trayectoData.findTrayectoById(id);
        if (t == null) return false;

        if (origenId != null) {
            if (municipioData.findMunicipioById(origenId) == null) return false;
            t.setIdMunicipioOrigen(origenId);
        }
        if (destinoId != null) {
            if (municipioData.findMunicipioById(destinoId) == null) return false;
            t.setIdMunicipioDestino(destinoId);
        }

        trayectoData.saveTrayecto(t);
        return true;
    }

    public boolean deleteTrayecto(String id) {
        trayectoData.deleteTrayecto(id);
        return true;
    }

    public List<Trayecto> findDestinosByMunicipioOrigen(String origenId) { return trayectoData.findDestinosByMunicipioOrigen(origenId); }
    public List<Trayecto> findOrigenesByMunicipioDestino(String destinoId) { return trayectoData.findOrigenesByMunicipioDestino(destinoId); }
}
