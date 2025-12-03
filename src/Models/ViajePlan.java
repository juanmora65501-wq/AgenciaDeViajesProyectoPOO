/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
public class ViajePlan {
    private String id;
    private String idViaje;
    private String idPlan;

    public ViajePlan() {}

    public ViajePlan(String id, String idViaje, String idPlan) {
        this.id = id;
        this.idViaje = idViaje;
        this.idPlan = idPlan;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getIdViaje() { return idViaje; }
    public void setIdViaje(String idViaje) { this.idViaje = idViaje; }

    public String getIdPlan() { return idPlan; }
    public void setIdPlan(String idPlan) { this.idPlan = idPlan; }
}

