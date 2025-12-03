package Models;

public class Viaje {

    private String fechaInicio;    // YYYY-MM-DD
    private String fechaFin;       // YYYY-MM-DD

    private double costoTotal;     

    public Viaje() {}

    public Viaje(String fechaInicio, String fechaFin, double costoTotal) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costoTotal = costoTotal;
    }

    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }

    public double getCostoTotal() { return costoTotal; }
    public void setCostoTotal(double costoTotal) { this.costoTotal = costoTotal; }

}
