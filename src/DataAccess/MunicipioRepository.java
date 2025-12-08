package DataAccess;

import Models.Municipio;
import java.util.List;

public class MunicipioRepository {

    private IDataAccess<Municipio> dataAccess;

    public MunicipioRepository() {
        this.dataAccess = new JsonRepository<>("municipios.json", Municipio.class);
    }

    public MunicipioRepository(IDataAccess<Municipio> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<Municipio> getAllMunicipios() {
        return dataAccess.findAll();
    }

    public Municipio findMunicipioById(String id) {
        return dataAccess.findById(id);
    }

    public void saveMunicipio(Municipio municipio) {
        dataAccess.save(municipio);
    }

    public void deleteMunicipio(String id) {
        dataAccess.delete(id);
    }
}
