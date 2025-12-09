/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.Aerolinea;
import java.util.List;

public class AerolineaRepository {
    private IDataAccess<Aerolinea> dataAccess;

    public AerolineaRepository() {
        this.dataAccess = new JsonRepository<>("aerolinea.json", Aerolinea.class);
    }

    public AerolineaRepository(IDataAccess<Aerolinea> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<Aerolinea> getAllAerolineas() {
        return dataAccess.findAll();
    }

    public Aerolinea findAeronlineaById(String id) {
        return dataAccess.findById(id);
    }

    public void saveAerolinea(Aerolinea item) {
        dataAccess.save(item);
    }

    public void deleteAerolinea(String id) {
        dataAccess.delete(id);
    }
}
