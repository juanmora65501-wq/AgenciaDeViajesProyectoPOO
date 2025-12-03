/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

public class ItinerarioTransporte {
    private String id;
    private String viajeId;              
    private String trayectoId;         
    private String servicioTransporteId; 
    private int orden;                   

    public ItinerarioTransporte() {}

    public ItinerarioTransporte(String id, String viajeId, String trayectoId, String servicioTransporteId, int orden) {
        this.id = id;
        this.viajeId = viajeId;
        this.trayectoId = trayectoId;
        this.servicioTransporteId = servicioTransporteId;
        this.orden = orden;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getViajeId() { return viajeId; }
    public void setViajeId(String viajeId) { this.viajeId = viajeId; }

    public String getTrayectoId() { return trayectoId; }
    public void setTrayectoId(String trayectoId) { this.trayectoId = trayectoId; }

    public String getServicioTransporteId() { return servicioTransporteId; }
    public void setServicioTransporteId(String servicioTransporteId) { this.servicioTransporteId = servicioTransporteId; }

    public int getOrden() { return orden; }
    public void setOrden(int orden) { this.orden = orden; }

}
