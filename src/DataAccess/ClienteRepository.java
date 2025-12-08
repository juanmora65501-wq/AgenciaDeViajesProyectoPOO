package DataAccess;

import Models.Cliente;
import java.util.List;

public class ClienteRepository {
    private IDataAccess<Cliente> dataAccess;

    public ClienteRepository() {
        this.dataAccess = new JsonRepository<>("DataAccess/cliente.json", Cliente.class);
    }

    public ClienteRepository(IDataAccess<Cliente> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<Cliente> getAllClientes() {
        return dataAccess.findAll();
    }

    public Cliente findClientesById(String id) {
        return dataAccess.findById(id);
    }

    public void saveCliente(Cliente item) {
        dataAccess.save(item);
    }

    public void deleteCliente(String id) {
        dataAccess.delete(id);
    }
}
