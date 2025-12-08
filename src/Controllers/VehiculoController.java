package Controllers;

import DataAccess.VehiculoRepository;
import Models.Vehiculo;
import java.util.List;

public class VehiculoController {
    private VehiculoRepository vehiculoData;

    public VehiculoController() { this.vehiculoData = new VehiculoRepository(); }
    public VehiculoController(VehiculoRepository vehiculoData) { this.vehiculoData = vehiculoData; }

    public List<Vehiculo> getAllVehiculos() { return vehiculoData.getAllVehiculos(); }
    public Vehiculo getVehiculoById(String id) { return vehiculoData.findVehiculoById(id); }

    public boolean addVehiculo(String tipo, String marca, String modelo, int capacidad, String placa) {
        if (tipo == null || tipo.trim().isEmpty()) return false;
        if (marca == null || marca.trim().isEmpty()) return false;
        if (modelo == null || modelo.trim().isEmpty()) return false;
        if (capacidad <= 0) return false;

        Vehiculo v = new Vehiculo();
        v.setTipo(tipo.trim());
        v.setMarca(marca.trim());
        v.setModelo(modelo.trim());
        v.setCapacidad(capacidad);
        v.setPlaca(placa != null ? placa.trim() : null);

        vehiculoData.saveVehiculo(v);
        return true;
    }

    public boolean updateVehiculo(String id, String tipo, String marca, String modelo, Integer capacidad, String placa) {
        Vehiculo v = vehiculoData.findVehiculoById(id);
        if (v == null) return false;

        if (tipo != null && !tipo.trim().isEmpty()) v.setTipo(tipo.trim());
        if (marca != null && !marca.trim().isEmpty()) v.setMarca(marca.trim());
        if (modelo != null && !modelo.trim().isEmpty()) v.setModelo(modelo.trim());
        if (capacidad != null && capacidad > 0) v.setCapacidad(capacidad);
        if (placa != null) v.setPlaca(placa.trim());

        vehiculoData.saveVehiculo(v);
        return true;
    }

    public boolean deleteVehiculo(String id) {
        vehiculoData.deleteVehiculo(id);
        return true;
    }
}
