package Models;

public class Cuota {
    private String id;
    private String viajeId; // FK -> Viaje
    private double monto;
    private int numeroCuota;
    private String fechaVencimiento; // String fecha

    public Cuota() {}

    public Cuota(String id, String viajeId, double monto, int numeroCuota, String fechaVencimiento) {
        this.id = id; this.viajeId = viajeId; this.monto = monto;
        this.numeroCuota = numeroCuota; this.fechaVencimiento = fechaVencimiento;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getViajeId() { return viajeId; }
    public void setViajeId(String viajeId) { this.viajeId = viajeId; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public int getNumeroCuota() { return numeroCuota; }
    public void setNumeroCuota(int numeroCuota) { this.numeroCuota = numeroCuota; }
    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
}
