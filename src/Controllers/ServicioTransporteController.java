package Controllers;

import DataAccess.ServicioTransporteRepository;
import DataAccess.TrayectoRepository;
import DataAccess.VehiculoRepository;
import Models.ServicioTransporte;
import Models.Trayecto;
import Models.Vehiculo;
import java.util.List;

public class ServicioTransporteController {
    private ServicioTransporteRepository servicioData;
    private TrayectoRepository trayectoData;
    private VehiculoRepository vehiculoData;

    public ServicioTransporteController() {
        this.servicioData = new ServicioTransporteRepository();
        this.trayectoData = new TrayectoRepository();
        this.vehiculoData = new VehiculoRepository();
    }

    public ServicioTransporteController(ServicioTransporteRepository servicioData, TrayectoRepository trayectoData, VehiculoRepository vehiculoData) {
        this.servicioData = servicioData;
        this.trayectoData = trayectoData;
        this.vehiculoData = vehiculoData;
    }

    public List<ServicioTransporte> getAllServicios() { return servicioData.getAllServiciosTransporte(); }
    public ServicioTransporte getServicioById(String id) { return servicioData.findServicioTransporteById(id); }

    public boolean addServicio(String trayectoId, String vehiculoId, String fechaInicio, String fechaFin, double costo) {
        if (trayectoId == null || vehiculoId == null) return false;
        if (trayectoData.findTrayectoById(trayectoId) == null) return false;
        if (vehiculoData.findVehiculoById(vehiculoId) == null) return false;

        ServicioTransporte s = new ServicioTransporte();
        s.setIdTrayecto(trayectoId);
        s.setIdVehiculo(vehiculoId);
        s.setFechaInicio(fechaInicio);
        s.setFechaFin(fechaFin);
        s.setCosto(costo);

        servicioData.saveServicioTransporte(s);
        return true;
    }

    public boolean updateServicio(String id, String trayectoId, String vehiculoId, String fechaInicio, String fechaFin, Double costo) {
        ServicioTransporte s = servicioData.findServicioTransporteById(id);
        if (s == null) return false;

        if (trayectoId != null) {
            if (trayectoData.findTrayectoById(trayectoId) == null) return false;
            s.setIdTrayecto(trayectoId);
        }
        if (vehiculoId != null) {
            if (vehiculoData.findVehiculoById(vehiculoId) == null) return false;
            s.setIdVehiculo(vehiculoId);
        }
        if (fechaInicio != null) s.setFechaInicio(fechaInicio);
        if (fechaFin != null) s.setFechaFin(fechaFin);
        if (costo != null) s.setCosto(costo);

        servicioData.saveServicioTransporte(s);
        return true;
    }

    public boolean deleteServicio(String id) {
        servicioData.deleteServicioTransporte(id);
        return true;
    }

    public List<Trayecto> findTrayectosByVehiculoId(String vehiculoId) { return servicioData.findTrayectosByVehiculoId(vehiculoId); }
    public List<Vehiculo> findVehiculosByTrayectoId(String trayectoId) { return servicioData.findVehiculosByTrayectoId(trayectoId); }
}

