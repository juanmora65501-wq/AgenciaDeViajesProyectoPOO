package Models;

public class Carro extends Vehiculo {

    private String idCarro;       // PK del carro
    private String hotelId;    // FK -> Vehiculo
    private String tipoCombustible; // Ej: gasolina, diésel, eléctrico
    private String transmision;     

    public Carro() {}

    public Carro(String idCarro, String hotelId, String tipoCombustible,
                 String transmision,
                 String id, String tipo, String marca, String modelo,
                 int capacidad, String placa) {

        super(id, tipo, marca, modelo, capacidad, placa);

        this.idCarro = idCarro;
        this.hotelId = hotelId;
        this.tipoCombustible = tipoCombustible;
        this.transmision = transmision;
    }

    public String getIdCarro() { return idCarro; }
    public void setIdCarro(String idCarro) { this.idCarro = idCarro; }

    public String getHotelId() { return hotelId; }
    public void setHotelId(String hotelId) { this.hotelId = hotelId; }

    public String getTipoCombustible() { return tipoCombustible; }
    public void setTipoCombustible(String tipoCombustible) { this.tipoCombustible = tipoCombustible; }

    public String getTransmision() { return transmision; }
    public void setTransmision(String transmision) { this.transmision = transmision; }

}
