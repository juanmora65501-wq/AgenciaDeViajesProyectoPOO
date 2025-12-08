package Controllers;

import DataAccess.HabitacionItinerarioRepository;
import DataAccess.HabitacionRepository;
import DataAccess.HotelRepository;
import DataAccess.ItinerarioTransporteRepository;
import DataAccess.MunicipioRepository;
import DataAccess.PlanActividadRepository;
import Models.Habitacion;
import Models.HabitacionItinerario;
import Models.Hotel;
import Models.ItinerarioTransporte;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelController {
    private HotelRepository hotelData;
    private MunicipioRepository municipioData;
    private HabitacionRepository habitacionData;
    private HabitacionItinerarioRepository habitacionItinerarioData;
    private ItinerarioTransporteRepository itinerarioData;
    private PlanActividadRepository planActividadData;

    public HotelController() {
        this.hotelData = new HotelRepository();
        this.municipioData = new MunicipioRepository();
        this.habitacionData = new HabitacionRepository();
        this.habitacionItinerarioData = new HabitacionItinerarioRepository();
        this.itinerarioData = new ItinerarioTransporteRepository();
        this.planActividadData = new PlanActividadRepository();
    }

    public HotelController(HotelRepository hotelData, MunicipioRepository municipioData) {
        this.hotelData = hotelData;
        this.municipioData = municipioData;
    }

    public List<Hotel> getAllHoteles() { return hotelData.getAllHoteles(); }
    public Hotel getHotelById(String id) { return hotelData.findHotelById(id); }

    public boolean addHotel(String nombre, String municipioId, String direccion, String contacto, int totalHabitaciones) {
        if (nombre == null || nombre.trim().isEmpty()) return false;
        if (municipioId == null || municipioId.trim().isEmpty()) return false;
        if (municipioData.findMunicipioById(municipioId) == null) return false;

        Hotel h = new Hotel();
        h.setNombre(nombre.trim());
        h.setMunicipioId(municipioId);
        h.setDireccion(direccion != null ? direccion.trim() : "");
        h.setContacto(contacto != null ? contacto.trim() : "");
        h.setTotalHabitaciones(totalHabitaciones);

        hotelData.saveHotel(h);
        return true;
    }

    public boolean updateHotel(String id, String nombre, String direccion, String contacto, Integer totalHabitaciones) {
        Hotel h = hotelData.findHotelById(id);
        if (h == null) return false;

        if (nombre != null && !nombre.trim().isEmpty()) h.setNombre(nombre.trim());
        if (direccion != null) h.setDireccion(direccion.trim());
        if (contacto != null) h.setContacto(contacto.trim());
        if (totalHabitaciones != null) h.setTotalHabitaciones(totalHabitaciones);

        hotelData.saveHotel(h);
        return true;
    }

    public boolean deleteHotel(String id) {
        hotelData.deleteHotel(id);
        return true;
    }

    public List<Hotel> findByMunicipioId(String municipioId) { return hotelData.findHotelesByMunicipioId(municipioId); }

    // ==================== MÉTODOS ANALÍTICOS ====================

    // E. Hoteles con habitaciones reservadas en planes de viajes con trayectos aéreos y terrestres
    public int getCantidadHotelConHabitacionesReservadasViajesMixtos() {
        Map<String, Integer> hoteles = new HashMap<>();

        List<Habitacion> habitaciones = habitacionData.getAllHabitaciones();
        for (Habitacion hab : habitaciones) {
            List<HabitacionItinerario> habitacionesIt = habitacionItinerarioData.getAllHabitacionItinerario();
            for (HabitacionItinerario hi : habitacionesIt) {
                if (hi.getIdHabitacion().equals(hab.getId())) {
                    ItinerarioTransporte it = itinerarioData.findItinerarioTransporteById(hi.getIdItinerario());
                    if (it != null) {
                        // Contamos habitaciones por hotel
                        hoteles.put(hab.getHotelId(), hoteles.getOrDefault(hab.getHotelId(), 0) + 1);
                    }
                }
            }
        }

        return hoteles.size();
    }
}
