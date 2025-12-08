package Controllers;

import DataAccess.HabitacionItinerarioRepository;
import DataAccess.HabitacionRepository;
import DataAccess.ItinerarioTransporteRepository;
import Models.HabitacionItinerario;
import Models.Habitacion;
import java.util.List;

public class HabitacionItinerarioController {
    private HabitacionItinerarioRepository habitacionItinerarioData;
    private HabitacionRepository habitacionData;
    private ItinerarioTransporteRepository itinerarioData;

    public HabitacionItinerarioController() {
        this.habitacionItinerarioData = new HabitacionItinerarioRepository();
        this.habitacionData = new HabitacionRepository();
        this.itinerarioData = new ItinerarioTransporteRepository();
    }

    public HabitacionItinerarioController(HabitacionItinerarioRepository habitacionItinerarioData, HabitacionRepository habitacionData, ItinerarioTransporteRepository itinerarioData) {
        this.habitacionItinerarioData = habitacionItinerarioData;
        this.habitacionData = habitacionData;
        this.itinerarioData = itinerarioData;
    }

    public List<HabitacionItinerario> getAllHabitacionItinerario() { return habitacionItinerarioData.getAllHabitacionItinerario(); }
    public HabitacionItinerario getHabitacionItinerarioById(String id) { return habitacionItinerarioData.findHabitacionItinerarioById(id); }

    public boolean addHabitacionItinerario(String habitacionId, String itinerarioId) {
        if (habitacionId == null || itinerarioId == null) return false;
        if (habitacionData.findHabitacionById(habitacionId) == null) return false;
        if (itinerarioData.findItinerarioTransporteById(itinerarioId) == null) return false;

        HabitacionItinerario hi = new HabitacionItinerario();
        hi.setIdHabitacion(habitacionId);
        hi.setIdItinerario(itinerarioId);

        habitacionItinerarioData.saveHabitacionItinerario(hi);
        return true;
    }

    public boolean updateHabitacionItinerario(String id, String habitacionId, String itinerarioId) {
        HabitacionItinerario hi = habitacionItinerarioData.findHabitacionItinerarioById(id);
        if (hi == null) return false;

        if (habitacionId != null) {
            if (habitacionData.findHabitacionById(habitacionId) == null) return false;
            hi.setIdHabitacion(habitacionId);
        }
        if (itinerarioId != null) {
            if (itinerarioData.findItinerarioTransporteById(itinerarioId) == null) return false;
            hi.setIdItinerario(itinerarioId);
        }

        habitacionItinerarioData.saveHabitacionItinerario(hi);
        return true;
    }

    public boolean deleteHabitacionItinerario(String id) {
        habitacionItinerarioData.deleteHabitacionItinerario(id);
        return true;
    }

    public List<Habitacion> findHabitacionesByItinerarioId(String itinerarioId) { return habitacionItinerarioData.findHabitacionesByItinerarioId(itinerarioId); }
}

