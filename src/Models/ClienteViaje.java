/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
public class ClienteViaje {
    private String id;
    private String idCliente;
    private String idViaje;

    public ClienteViaje() {}

    public ClienteViaje(String id, String idCliente, String idViaje) {
        this.id = id;
        this.idCliente = idCliente;
        this.idViaje = idViaje;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public String getIdViaje() { return idViaje; }
    public void setIdViaje(String idViaje) { this.idViaje = idViaje; }
}
