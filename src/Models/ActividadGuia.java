/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
public class ActividadGuia {
    private String id;
    private String idActividad;
    private String idGuia;

    public ActividadGuia() {}

    public ActividadGuia(String id, String idActividad, String idGuia) {
        this.id = id;
        this.idActividad = idActividad;
        this.idGuia = idGuia;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getIdActividad() { return idActividad; }
    public void setIdActividad(String idActividad) { this.idActividad = idActividad; }

    public String getIdGuia() { return idGuia; }
    public void setIdGuia(String idGuia) { this.idGuia = idGuia; }
}
