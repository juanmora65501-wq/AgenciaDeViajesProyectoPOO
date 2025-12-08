
package DataAccess;

import Models.HabitacionItinerario;
import java.util.List;
import Models.Habitacion;
import java.util.ArrayList;

public class HabitacionItinerarioRepository {

    private IDataAccess<HabitacionItinerario> dataAccess;

    public HabitacionItinerarioRepository() {
        this.dataAccess = new JsonRepository<>("habitacion_itinerario.json", HabitacionItinerario.class);
    }

    public HabitacionItinerarioRepository(IDataAccess<HabitacionItinerario> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<HabitacionItinerario> getAllHabitacionItinerario() {
        return dataAccess.findAll();
    }

    public HabitacionItinerario findHabitacionItinerarioById(String id) {
        return dataAccess.findById(id);
    }

    public void saveHabitacionItinerario(HabitacionItinerario item) {
        dataAccess.save(item);
    }

    public void deleteHabitacionItinerario(String id) {
        dataAccess.delete(id);
    }
    
    public List<Habitacion> findHabitacionesByItinerarioId(String itinerarioId) {
        List<HabitacionItinerario> ihList = getAllHabitacionItinerario();
        List<Habitacion> habitaciones = new HabitacionRepository().getAllHabitaciones();
        List<Habitacion> result = new ArrayList<>();

        for (Habitacion h : habitaciones) {
            for (HabitacionItinerario ih : ihList) {
                if (ih.getIdItinerario() != null &&
                    ih.getIdItinerario().equals(itinerarioId) &&
                    ih.getIdHabitacion().equals(h.getId())) {

                    result.add(h);
                }
            }
        }

        return result;
    }
    
    

}
