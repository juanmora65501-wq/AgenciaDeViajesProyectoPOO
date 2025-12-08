package Controllers;

import DataAccess.HabitacionRepository;
import DataAccess.HotelRepository;
import Models.Habitacion;
import java.util.List;

public class HabitacionController {
    private HabitacionRepository habitacionData;
    private HotelRepository hotelData;

    public HabitacionController() {
        this.habitacionData = new HabitacionRepository();
        this.hotelData = new HotelRepository();
    }

    public HabitacionController(HabitacionRepository habitacionData, HotelRepository hotelData) {
        this.habitacionData = habitacionData;
        this.hotelData = hotelData;
    }

    public List<Habitacion> getAllHabitaciones() { return habitacionData.getAllHabitaciones(); }
    public Habitacion getHabitacionById(String id) { return habitacionData.findHabitacionById(id); }

    public boolean addHabitacion(String hotelId, String tipo, double precio, int capacidad) {
        if (hotelId == null || tipo == null || tipo.trim().isEmpty()) return false;
        if (precio <= 0 || capacidad <= 0) return false;
        if (hotelData.findHotelById(hotelId) == null) return false;

        Habitacion h = new Habitacion();
        h.setHotelId(hotelId);
        h.setTipo(tipo.trim());
        h.setPrecio(precio);
        h.setCapacidad(capacidad);

        habitacionData.saveHabitacion(h);
        return true;
    }

    public boolean updateHabitacion(String id, String tipo, Double precio, Integer capacidad) {
        Habitacion h = habitacionData.findHabitacionById(id);
        if (h == null) return false;

        if (tipo != null && !tipo.trim().isEmpty()) h.setTipo(tipo.trim());
        if (precio != null && precio > 0) h.setPrecio(precio);
        if (capacidad != null && capacidad > 0) h.setCapacidad(capacidad);

        habitacionData.saveHabitacion(h);
        return true;
    }

    public boolean deleteHabitacion(String id) {
        habitacionData.deleteHabitacion(id);
        return true;
    }

    public List<Habitacion> findByHotelId(String hotelId) { return habitacionData.findHabitacionesByHotelId(hotelId); }
}
