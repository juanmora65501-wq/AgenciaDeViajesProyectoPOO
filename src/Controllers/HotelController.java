package Controllers;

import DataAccess.HotelRepository;
import DataAccess.MunicipioRepository;
import Models.Hotel;
import java.util.List;

public class HotelController {
    private HotelRepository hotelData;
    private MunicipioRepository municipioData;

    public HotelController() {
        this.hotelData = new HotelRepository();
        this.municipioData = new MunicipioRepository();
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
}
