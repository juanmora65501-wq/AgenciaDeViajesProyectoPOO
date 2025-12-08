package Controllers;

import DataAccess.ViajeRepository;
import Models.Viaje;
import java.util.List;

public class ViajeController {
    private ViajeRepository viajeData;

    public ViajeController() { this.viajeData = new ViajeRepository(); }
    public ViajeController(ViajeRepository viajeData) { this.viajeData = viajeData; }

    public List<Viaje> getAllViajes() { return viajeData.getAllViajes(); }
    public Viaje getViajeById(String id) { return viajeData.findViajeById(id); }

    public boolean addViaje(String fechaInicio, String fechaFin, double costoTotal) {
        if (fechaInicio == null || fechaInicio.trim().isEmpty()) return false;
        if (fechaFin == null || fechaFin.trim().isEmpty()) return false;
        if (costoTotal < 0) return false;

        Viaje v = new Viaje();
        v.setFechaInicio(fechaInicio.trim());
        v.setFechaFin(fechaFin.trim());
        v.setCostoTotal(costoTotal);

        viajeData.saveViaje(v);
        return true;
    }

    public boolean updateViaje(String id, String fechaInicio, String fechaFin, Double costoTotal) {
        Viaje v = viajeData.findViajeById(id);
        if (v == null) return false;

        if (fechaInicio != null && !fechaInicio.trim().isEmpty()) v.setFechaInicio(fechaInicio.trim());
        if (fechaFin != null && !fechaFin.trim().isEmpty()) v.setFechaFin(fechaFin.trim());
        if (costoTotal != null && costoTotal >= 0) v.setCostoTotal(costoTotal);

        viajeData.saveViaje(v);
        return true;
    }

    public boolean deleteViaje(String id) {
        viajeData.deleteViaje(id);
        return true;
    }
}
