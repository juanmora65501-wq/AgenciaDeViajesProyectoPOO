package DataAccess;

import Models.Aeronave;
import Models.Aerolinea;
import java.util.List;
import java.util.ArrayList;

public class AeronaveRepository {

    private IDataAccess<Aeronave> dataAccess;

    public AeronaveRepository() {
        this.dataAccess = new JsonRepository<>("aeronave.json", Aeronave.class);
    }

    public AeronaveRepository(IDataAccess<Aeronave> dataAccess) {
        this.dataAccess = dataAccess;
    }

    // CRUD básico
    public List<Aeronave> getAllAeronaves() {
        return dataAccess.findAll();
    }

    public Aeronave findAeronaveById(String id) {
        return dataAccess.findById(id);
    }

    public void saveAeronave(Aeronave item) {
        dataAccess.save(item);
    }

    public void deleteAeronave(String id) {
        dataAccess.delete(id);
    }

    public List<Aeronave> findAeronavesByAerolineaId(String aerolineaId) {
        List<Aeronave> aeronaves = getAllAeronaves();
        List<Aeronave> result = new ArrayList<>();

        for (Aeronave a : aeronaves) {
            if (a.getAerolineaId() != null && a.getAerolineaId().equals(aerolineaId)) {
                result.add(a);
            }
        }

        return result;
    }
}
