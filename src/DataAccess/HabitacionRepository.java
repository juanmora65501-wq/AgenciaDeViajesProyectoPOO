package DataAccess;

import Models.Habitacion;
import java.util.List;
import java.util.ArrayList;

public class HabitacionRepository {

    private IDataAccess<Habitacion> dataAccess;

    public HabitacionRepository() {
        this.dataAccess = new JsonRepository<>("habitacion.json", Habitacion.class);
    }

    public HabitacionRepository(IDataAccess<Habitacion> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<Habitacion> getAllHabitaciones() {
        return dataAccess.findAll();
    }

    public Habitacion findHabitacionById(String id) {
        return dataAccess.findById(id);
    }

    public void saveHabitacion(Habitacion item) {
        dataAccess.save(item);
    }

    public void deleteHabitacion(String id) {
        dataAccess.delete(id);
    }

    public List<Habitacion> findHabitacionesByHotelId(String hotelId) {
        List<Habitacion> habitaciones = getAllHabitaciones();
        List<Habitacion> result = new ArrayList<>();

        for (Habitacion h : habitaciones) {
            if (h.getHotelId() != null && h.getHotelId ().equals(hotelId)) {
                result.add(h);
            }
        }

        return result;
    }
}
