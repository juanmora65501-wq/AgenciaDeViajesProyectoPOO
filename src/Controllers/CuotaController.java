package Controllers;

import DataAccess.CuotaRepository;
import DataAccess.ViajeRepository;
import Models.Cuota;
import java.util.List;

public class CuotaController {
    private CuotaRepository cuotaData;
    private ViajeRepository viajeData;

    public CuotaController() {
        this.cuotaData = new CuotaRepository();
        this.viajeData = new ViajeRepository();
    }

    public CuotaController(CuotaRepository cuotaData, ViajeRepository viajeData) {
        this.cuotaData = cuotaData;
        this.viajeData = viajeData;
    }

    public List<Cuota> getAllCuotas() { return cuotaData.getAllCuotas(); }
    public Cuota getCuotaById(String id) { return cuotaData.findCuotaById(id); }

    public boolean addCuota(String viajeId, double monto, int numeroCuota, String fechaVencimiento) {
        if (viajeId == null || viajeId.trim().isEmpty()) return false;
        if (viajeData.findViajeById(viajeId) == null) return false;
        if (monto <= 0 || numeroCuota <= 0) return false;

        Cuota c = new Cuota();
        c.setViajeId(viajeId);
        c.setMonto(monto);
        c.setNumeroCuota(numeroCuota);
        c.setFechaVencimiento(fechaVencimiento != null ? fechaVencimiento.trim() : "");

        cuotaData.saveCuota(c);
        return true;
    }

    public boolean updateCuota(String id, Double monto, Integer numeroCuota, String fechaVencimiento) {
        Cuota c = cuotaData.findCuotaById(id);
        if (c == null) return false;

        if (monto != null && monto > 0) c.setMonto(monto);
        if (numeroCuota != null && numeroCuota > 0) c.setNumeroCuota(numeroCuota);
        if (fechaVencimiento != null) c.setFechaVencimiento(fechaVencimiento.trim());

        cuotaData.saveCuota(c);
        return true;
    }

    public boolean deleteCuota(String id) {
        cuotaData.deleteCuota(id);
        return true;
    }

    public List<Cuota> findByViajeId(String viajeId) { return cuotaData.findCuotasByViajeId(viajeId); }
}
