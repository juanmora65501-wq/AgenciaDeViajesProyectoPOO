package Controllers;

import DataAccess.CarroRepository;
import DataAccess.HotelRepository;
import Models.Carro;
import java.util.List;

public class CarroController {
    private CarroRepository carroData;
    private HotelRepository hotelData;

    public CarroController() {
        this.carroData = new CarroRepository();
        this.hotelData = new HotelRepository();
    }

    public CarroController(CarroRepository carroData, HotelRepository hotelData) {
        this.carroData = carroData;
        this.hotelData = hotelData;
    }

    public List<Carro> getAllCarros() { return carroData.getAllCarros(); }
    public Carro getCarroById(String id) { return carroData.findCarroById(id); }

    public boolean addCarro(String hotelId, String marca, String modelo, String placa) {
        if (hotelId == null || hotelId.trim().isEmpty()) return false;
        if (marca == null || marca.trim().isEmpty()) return false;
        if (hotelData.findHotelById(hotelId) == null) return false;

        Carro c = new Carro();
        c.setHotelId(hotelId);
        c.setMarca(marca.trim());
        c.setModelo(modelo != null ? modelo.trim() : "");
        c.setPlaca(placa != null ? placa.trim() : "");

        carroData.saveCarro(c);
        return true;
    }

    public boolean updateCarro(String id, String marca, String modelo, String placa) {
        Carro c = carroData.findCarroById(id);
        if (c == null) return false;

        if (marca != null && !marca.trim().isEmpty()) c.setMarca(marca.trim());
        if (modelo != null) c.setModelo(modelo.trim());
        if (placa != null) c.setPlaca(placa.trim());

        carroData.saveCarro(c);
        return true;
    }

    public boolean deleteCarro(String id) {
        carroData.deleteCarro(id);
        return true;
    }

    public List<Carro> findByHotelId(String hotelId) { return carroData.findCarrosByHotelId(hotelId); }
}
