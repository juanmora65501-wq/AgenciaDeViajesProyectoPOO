package DataAccess;

import Models.Cuota;
import java.util.List;
import java.util.ArrayList;

public class CuotaRepository {

    private IDataAccess<Cuota> dataAccess;

    public CuotaRepository() {
        this.dataAccess = new JsonRepository<>("cuota.json", Cuota.class);
    }

    public CuotaRepository(IDataAccess<Cuota> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<Cuota> getAllCuotas() {
        return dataAccess.findAll();
    }

    public Cuota findCuotaById(String id) {
        return dataAccess.findById(id);
    }

    public void saveCuota(Cuota item) {
        dataAccess.save(item);
    }

    public void deleteCuota(String id) {
        dataAccess.delete(id);
    }

    public List<Cuota> findCuotasByViajeId(String viajeId) {
        List<Cuota> cuotas = getAllCuotas();
        List<Cuota> result = new ArrayList<>();

        for (Cuota c : cuotas) {
            if (c.getViajeId() != null && c.getViajeId().equals(viajeId)) {
                result.add(c);
            }
        }

        return result;
    }
}
