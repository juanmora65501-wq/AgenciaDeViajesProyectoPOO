package DataAccess;

import Models.Hotel;
import java.util.List;
import java.util.ArrayList;

public class HotelRepository {
    private IDataAccess<Hotel> dataAccess;

    public HotelRepository() {
        this.dataAccess = new JsonRepository<>("DataAccess/hotel.json", Hotel.class);
    }

    public HotelRepository(IDataAccess<Hotel> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<Hotel> getAllHoteles() {
        return dataAccess.findAll();
    }

    public Hotel findHotelById(String id) {
        return dataAccess.findById(id);
    }

    public void saveHotel(Hotel item) {
        dataAccess.save(item);
    }

    public void deleteHotel(String id) {
        dataAccess.delete(id);
    }
    
    public List<Hotel> findHotelesByMunicipioId(String municipioId) {
        List<Hotel> hoteles = getAllHoteles();
        List<Hotel> result = new ArrayList<>();

        for (Hotel h : hoteles) {
            if (h.getMunicipioId() != null && h.getMunicipioId().equals(municipioId)) {
                result.add(h);
            }
        }

        return result;
    }
}
