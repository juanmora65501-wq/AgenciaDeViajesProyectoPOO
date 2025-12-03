/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
public class PlanActividad {
    private String id;
    private String idPlan;
    private String idActividad;

    public PlanActividad() {}

    public PlanActividad(String id, String idPlan, String idActividad) {
        this.id = id;
        this.idPlan = idPlan;
        this.idActividad = idActividad;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getIdPlan() { return idPlan; }
    public void setIdPlan(String idPlan) { this.idPlan = idPlan; }

    public String getIdActividad() { return idActividad; }
    public void setIdActividad(String idActividad) { this.idActividad = idActividad; }
}
