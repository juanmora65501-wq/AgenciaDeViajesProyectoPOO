package DataAccess;

import Models.Vehiculo;
import java.util.List;

public class VehiculoRepository {

    private IDataAccess<Vehiculo> dataAccess;

    public VehiculoRepository() {
        this.dataAccess = new JsonRepository<>("vehiculos.json", Vehiculo.class);
    }

    public VehiculoRepository(IDataAccess<Vehiculo> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<Vehiculo> getAllVehiculos() {
        return dataAccess.findAll();
    }

    public Vehiculo findVehiculoById(String id) {
        return dataAccess.findById(id);
    }

    public void saveVehiculo(Vehiculo vehiculo) {
        dataAccess.save(vehiculo);
    }

    public void deleteVehiculo(String id) {
        dataAccess.delete(id);
    }
}
