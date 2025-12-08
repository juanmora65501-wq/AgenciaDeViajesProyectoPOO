package DataAccess;

import Models.ItinerarioTransporte;
import Models.Viaje;
import Models.Trayecto;
import java.util.List;
import java.util.ArrayList;

public class ItinerarioTransporteRepository {

    private IDataAccess<ItinerarioTransporte> dataAccess;

    public ItinerarioTransporteRepository() {
        this.dataAccess = new JsonRepository<>("itinerario_transporte.json", ItinerarioTransporte.class);
    }

    public ItinerarioTransporteRepository(IDataAccess<ItinerarioTransporte> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<ItinerarioTransporte> getAllItinerarioTransporte() {
        return dataAccess.findAll();
    }

    public ItinerarioTransporte findItinerarioTransporteById(String id) {
        return dataAccess.findById(id);
    }

    public void saveItinerarioTransporte(ItinerarioTransporte item) {
        dataAccess.save(item);
    }

    public void deleteItinerarioTransporte(String id) {
        dataAccess.delete(id);
    }
    public List<Viaje> findViajesByTrayectoId(String trayectoId) {
        List<ItinerarioTransporte> itinerarios = getAllItinerarioTransporte();
        List<Viaje> viajes = new ViajeRepository().getAllViajes();
        List<Viaje> result = new ArrayList<>();

        for (Viaje viaje : viajes) {
            for (ItinerarioTransporte it : itinerarios) {
                if (it.getTrayectoId() != null &&
                    it.getTrayectoId().equals(trayectoId) &&
                    it.getViajeId().equals(viaje.getId())) {

                    result.add(viaje);
                }
            }
        }

        return result;
    }

    public List<Trayecto> findTrayectosByViajeId(String viajeId) {
        List<ItinerarioTransporte> itinerarios = getAllItinerarioTransporte();
        List<Trayecto> trayectos = new TrayectoRepository().getAllTrayectos();
        List<Trayecto> result = new ArrayList<>();

        for (Trayecto t : trayectos) {
            for (ItinerarioTransporte it : itinerarios) {
                if (it.getViajeId() != null &&
                    it.getViajeId().equals(viajeId) &&
                    it.getTrayectoId().equals(t.getId())) {

                    result.add(t);
                }
            }
        }

        return result;
    }
    
    public List<ItinerarioTransporte> findItinerariosByServicioId(String servicioTransporteId) {
        List<ItinerarioTransporte> itinerarios = getAllItinerarioTransporte();
        List<ItinerarioTransporte> result = new ArrayList<>();

        for (ItinerarioTransporte it : itinerarios) {
            if (it.getServicioTransporteId() != null &&
                it.getServicioTransporteId().equals(servicioTransporteId)) {

                result.add(it);
            }
        }
        return result;
    }
}
