package DataAccess;

import Models.ServicioTransporte;
import Models.Vehiculo;
import Models.Trayecto;
import java.util.List;
import java.util.ArrayList;

public class ServicioTransporteRepository {

    private IDataAccess<ServicioTransporte> dataAccess;

    public ServicioTransporteRepository() {
        this.dataAccess = new JsonRepository<>("servicio_transporte.json", ServicioTransporte.class);
    }

    public ServicioTransporteRepository(IDataAccess<ServicioTransporte> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<ServicioTransporte> getAllServiciosTransporte() {
        return dataAccess.findAll();
    }

    public ServicioTransporte findServicioTransporteById(String id) {
        return dataAccess.findById(id);
    }

    public void saveServicioTransporte(ServicioTransporte item) {
        dataAccess.save(item);
    }

    public void deleteServicioTransporte(String id) {
        dataAccess.delete(id);
    }


  
    public List<Trayecto> findTrayectosByVehiculoId(String vehiculoId) {
        List<ServicioTransporte> servicios = getAllServiciosTransporte();
        List<Trayecto> trayectos = new TrayectoRepository().getAllTrayectos();
        List<Trayecto> result = new ArrayList<>();

        for (Trayecto t : trayectos) {
            for (ServicioTransporte s : servicios) {
                if (s.getIdVehiculo() != null &&
                    s.getIdVehiculo().equals(vehiculoId) &&
                    s.getIdVehiculo().equals(t.getId())) {

                    result.add(t);
                }
            }
        }

        return result;
    }
    public List<Vehiculo> findVehiculosByTrayectoId(String trayectoId) {
        List<ServicioTransporte> servicios = getAllServiciosTransporte();
        List<Vehiculo> vehiculos = new VehiculoRepository().getAllVehiculos();
        List<Vehiculo> result = new ArrayList<>();

        for (Vehiculo v : vehiculos) {
            for (ServicioTransporte s : servicios) {
                if (s.getIdTrayecto() != null &&
                    s.getIdTrayecto().equals(trayectoId) &&
                    s.getIdTrayecto().equals(v.getId())) {

                    result.add(v);
                }
            }
        }

        return result;
    }
}
