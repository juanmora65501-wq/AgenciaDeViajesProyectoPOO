package Models;

public class ServicioTransporte {
    private String id;             // PK
    private String idTrayecto;     // FK a Trayecto
    private String idVehiculo;     // FK a Vehiculo

    private String fechaInicio;    // atributos adicionales
    private String fechaFin;
    private double costo;

    public ServicioTransporte() {}

    public ServicioTransporte(String id, String idTrayecto, String idVehiculo,
                              String fechaInicio, String fechaFin, double costo) {
        this.id = id;
        this.idTrayecto = idTrayecto;
        this.idVehiculo = idVehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costo = costo;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getIdTrayecto() { return idTrayecto; }
    public void setIdTrayecto(String idTrayecto) { this.idTrayecto = idTrayecto; }

    public String getIdVehiculo() { return idVehiculo; }
    public void setIdVehiculo(String idVehiculo) { this.idVehiculo = idVehiculo; }

    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }

    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }
}
