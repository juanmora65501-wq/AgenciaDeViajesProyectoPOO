package DataAccess;

import Models.Viaje;
import java.util.List;

public class ViajeRepository {

    private IDataAccess<Viaje> dataAccess;

    public ViajeRepository() {
        this.dataAccess = new JsonRepository<>("viajes.json", Viaje.class);
    }

    public ViajeRepository(IDataAccess<Viaje> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<Viaje> getAllViajes() {
        return dataAccess.findAll();
    }

    public Viaje findViajeById(String id) {
        return dataAccess.findById(id);
    }

    public void saveViaje(Viaje viaje) {
        dataAccess.save(viaje);
    }

    public void deleteViaje(String id) {
        dataAccess.delete(id);
    }
}
