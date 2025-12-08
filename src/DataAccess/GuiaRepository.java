package DataAccess;

import Models.Guia;
import java.util.List;

public class GuiaRepository {
    private IDataAccess<Guia> dataAccess;

    public GuiaRepository() {
        this.dataAccess = new JsonRepository<>("DataAccess/guia.json", Guia.class);
    }

    public GuiaRepository(IDataAccess<Guia> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<Guia> getAllGuias() {
        return dataAccess.findAll();
    }

    public Guia findGuiaById(String id) {
        return dataAccess.findById(id);
    }

    public void saveGuia(Guia item) {
        dataAccess.save(item);
    }

    public void deleteGuia(String id) {
        dataAccess.delete(id);
    }
}
