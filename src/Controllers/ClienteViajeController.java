package Controllers;

import DataAccess.ClienteViajeRepository;
import DataAccess.ClienteRepository;
import DataAccess.ViajeRepository;
import Models.ClienteViaje;
import Models.Cliente;
import Models.Viaje;
import java.util.List;

public class ClienteViajeController {
    private ClienteViajeRepository clienteViajeData;
    private ClienteRepository clienteData;
    private ViajeRepository viajeData;

    public ClienteViajeController() {
        this.clienteViajeData = new ClienteViajeRepository();
        this.clienteData = new ClienteRepository();
        this.viajeData = new ViajeRepository();
    }

    public ClienteViajeController(ClienteViajeRepository clienteViajeData, ClienteRepository clienteData, ViajeRepository viajeData) {
        this.clienteViajeData = clienteViajeData;
        this.clienteData = clienteData;
        this.viajeData = viajeData;
    }

    public List<ClienteViaje> getAllClienteViaje() { return clienteViajeData.getAllClienteViaje(); }
    public ClienteViaje getClienteViajeById(String id) { return clienteViajeData.findClienteViajeById(id); }

    public boolean addClienteViaje(String clienteId, String viajeId) {
        if (clienteId == null || viajeId == null) return false;
        if (clienteData.findClientesById(clienteId) == null) return false;
        if (viajeData.findViajeById(viajeId) == null) return false;

        ClienteViaje cv = new ClienteViaje();
        cv.setIdCliente(clienteId);
        cv.setIdViaje(viajeId);
        clienteViajeData.saveClienteViaje(cv);
        return true;
    }

    public boolean updateClienteViaje(String id, String clienteId, String viajeId) {
        ClienteViaje cv = clienteViajeData.findClienteViajeById(id);
        if (cv == null) return false;

        if (clienteId != null) {
            if (clienteData.findClientesById(clienteId) == null) return false;
            cv.setIdCliente(clienteId);
        }
        if (viajeId != null) {
            if (viajeData.findViajeById(viajeId) == null) return false;
            cv.setIdViaje(viajeId);
        }

        clienteViajeData.saveClienteViaje(cv);
        return true;
    }

    public boolean deleteClienteViaje(String id) {
        clienteViajeData.deleteClienteViaje(id);
        return true;
    }

    public List<Cliente> findClientesByViajeId(String viajeId) { return clienteViajeData.findClientesByViajeId(viajeId); }
    public List<Viaje> findViajesByClienteId(String clienteId) { return clienteViajeData.findViajesByClienteId(clienteId); }
}
