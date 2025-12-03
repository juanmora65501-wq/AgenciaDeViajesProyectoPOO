/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Acer
 */
public class HabitacionItinerario {
    private String id;
    private String idHabitacion;
    private String idItinerario;

    public HabitacionItinerario() {
    }
    

    public HabitacionItinerario(String id, String idHabitacion, String idItinerario) {
        this.id = id;
        this.idHabitacion = idHabitacion;
        this.idItinerario = idItinerario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(String idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getIdItinerario() {
        return idItinerario;
    }

    public void setIdItinerario(String idItinerario) {
        this.idItinerario = idItinerario;
    }
    
    
    
}
