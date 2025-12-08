package Controllers;

import DataAccess.ItinerarioTransporteRepository;
import DataAccess.ViajeRepository;
import DataAccess.TrayectoRepository;
import DataAccess.ServicioTransporteRepository;
import Models.ItinerarioTransporte;
import Models.Viaje;
import Models.Trayecto;
import java.util.List;

public class ItinerarioTransporteController {
    private ItinerarioTransporteRepository itinerarioData;
    private ViajeRepository viajeData;
    private TrayectoRepository trayectoData;
    private ServicioTransporteRepository servicioData;

    public ItinerarioTransporteController() {
        this.itinerarioData = new ItinerarioTransporteRepository();
        this.viajeData = new ViajeRepository();
        this.trayectoData = new TrayectoRepository();
        this.servicioData = new ServicioTransporteRepository();
    }

    public ItinerarioTransporteController(ItinerarioTransporteRepository itinerarioData, ViajeRepository viajeData, TrayectoRepository trayectoData, ServicioTransporteRepository servicioData) {
        this.itinerarioData = itinerarioData;
        this.viajeData = viajeData;
        this.trayectoData = trayectoData;
        this.servicioData = servicioData;
    }

    public List<ItinerarioTransporte> getAllItinerarios() { return itinerarioData.getAllItinerarioTransporte(); }
    public ItinerarioTransporte getItinerarioById(String id) { return itinerarioData.findItinerarioTransporteById(id); }

    public boolean addItinerario(String viajeId, String trayectoId, String servicioId, int orden) {
        if (viajeId == null || trayectoId == null || servicioId == null) return false;
        if (viajeData.findViajeById(viajeId) == null) return false;
        if (trayectoData.findTrayectoById(trayectoId) == null) return false;
        if (servicioData.findServicioTransporteById(servicioId) == null) return false;

        ItinerarioTransporte it = new ItinerarioTransporte();
        it.setViajeId(viajeId);
        it.setTrayectoId(trayectoId);
        it.setServicioTransporteId(servicioId);
        it.setOrden(orden);

        itinerarioData.saveItinerarioTransporte(it);
        return true;
    }

    public boolean updateItinerario(String id, String viajeId, String trayectoId, String servicioId, Integer orden) {
        ItinerarioTransporte it = itinerarioData.findItinerarioTransporteById(id);
        if (it == null) return false;

        if (viajeId != null) {
            if (viajeData.findViajeById(viajeId) == null) return false;
            it.setViajeId(viajeId);
        }
        if (trayectoId != null) {
            if (trayectoData.findTrayectoById(trayectoId) == null) return false;
            it.setTrayectoId(trayectoId);
        }
        if (servicioId != null) {
            if (servicioData.findServicioTransporteById(servicioId) == null) return false;
            it.setServicioTransporteId(servicioId);
        }
        if (orden != null) it.setOrden(orden);

        itinerarioData.saveItinerarioTransporte(it);
        return true;
    }

    public boolean deleteItinerario(String id) {
        itinerarioData.deleteItinerarioTransporte(id);
        return true;
    }

    public List<Viaje> findViajesByTrayectoId(String trayectoId) { return itinerarioData.findViajesByTrayectoId(trayectoId); }
    public List<Trayecto> findTrayectosByViajeId(String viajeId) { return itinerarioData.findTrayectosByViajeId(viajeId); }
    public List<ItinerarioTransporte> findItinerariosByServicioId(String servicioId) { return itinerarioData.findItinerariosByServicioId(servicioId); }
}
