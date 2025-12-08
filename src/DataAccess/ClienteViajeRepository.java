package DataAccess;

import Models.ClienteViaje;
import Models.Viaje;
import Models.Cliente;
import java.util.List;
import java.util.ArrayList;

public class ClienteViajeRepository {

    private IDataAccess<ClienteViaje> dataAccess;

    public ClienteViajeRepository() {
        this.dataAccess = new JsonRepository<>("cliente_viaje.json", ClienteViaje.class);
    }

    public ClienteViajeRepository(IDataAccess<ClienteViaje> dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<ClienteViaje> getAllClienteViaje() {
        return dataAccess.findAll();
    }

    public ClienteViaje findClienteViajeById(String id) {
        return dataAccess.findById(id);
    }

    public void saveClienteViaje(ClienteViaje item) {
        dataAccess.save(item);
    }

    public void deleteClienteViaje(String id) {
        dataAccess.delete(id);
    }


    public List<Cliente> findClientesByViajeId(String viajeId) {
        List<ClienteViaje> clienteViajes = getAllClienteViaje();
        List<Cliente> clientes = new ClienteRepository().getAllClientes();
        List<Cliente> result = new ArrayList<>();

        for (Cliente cliente : clientes) {
            for (ClienteViaje cv : clienteViajes) {
                if (cv.getIdViaje() != null &&
                    cv.getIdViaje().equals(viajeId) &&
                    cv.getIdCliente().equals(cliente.getId())) {

                    result.add(cliente);
                }
            }
        }

        return result;
    }

    public List<Viaje> findViajesByClienteId(String clienteId) {
        List<ClienteViaje> clienteViajes = getAllClienteViaje();
        List<Viaje> viajes = new ViajeRepository().getAllViajes();
        List<Viaje> result = new ArrayList<>();

        for (Viaje viaje : viajes) {
            for (ClienteViaje cv : clienteViajes) {
                if (cv.getIdCliente() != null &&
                    cv.getIdCliente().equals(clienteId) &&
                    cv.getIdViaje().equals(viaje.getId())) {

                    result.add(viaje);
                }
            }
        }

        return result;
    }
}
