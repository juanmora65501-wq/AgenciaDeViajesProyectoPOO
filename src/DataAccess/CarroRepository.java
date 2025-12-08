package DataAccess;

import Models.Carro;
import java.util.List;
import java.util.ArrayList;

public class CarroRepository {

    private IDataAccess<Carro> dataAccess;

    public CarroRepository() {
        this.dataAccess = new JsonRepository<>("carro.json", Carro.class);
    }

    public CarroRepository(IDataAccess<Carro> dataAccess) {
        this.dataAccess = dataAccess;
    }

    // --------------------------
    // MÉTODOS CRUD
    // --------------------------

    public List<Carro> getAllCarros() {
        return dataAccess.findAll();
    }

    public Carro findCarroById(String id) {
        return dataAccess.findById(id);
    }

    public void saveCarro(Carro item) {
        dataAccess.save(item);
    }

    public void deleteCarro(String id) {
        dataAccess.delete(id);
    }

    // --------------------------
    // MÉTODO N → 1
    // --------------------------
    // Devuelve todos los carros de un hotel
    public List<Carro> findCarrosByHotelId(String hotelId) {
        List<Carro> carros = getAllCarros();
        List<Carro> result = new ArrayList<>();

        for (Carro c : carros) {
            if (c.getHotelId() != null && c.getHotelId().equals(hotelId)) {
                result.add(c);
            }
        }

        return result;
    }
}
