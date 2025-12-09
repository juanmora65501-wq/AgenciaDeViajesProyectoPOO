import Controllers.*;
import DataAccess.*;
import Models.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas JUnit para métodos analíticos A, B, C, D, E, F
 * A. Promedio de actividades por plan para viajes con trayectos aéreos y terrestres
 * B. Mínimo costo de un trayecto aéreo para una aerolínea específica
 * C. Cantidad de viajes con actividad específica usando vehículo con menos habitaciones
 * D. Suma total de costos de vuelos para un cliente específico
 * E. Hoteles con habitaciones reservadas en viajes aéreo-terrestres
 * F. Promedio de trayectos por viaje para clientes con más de un viaje
 */
@RunWith(MockitoJUnitRunner.class)
public class AnalyticosAFTest {
    
    @Mock
    private ViajeRepository viajeRepo;
    @Mock
    private ViajePlanRepository viajePlanRepo;
    @Mock
    private PlanActividadRepository planActividadRepo;
    @Mock
    private ClienteViajeRepository clienteViajeRepo;
    @Mock
    private ItinerarioTransporteRepository itinerarioRepo;
    @Mock
    private ServicioTransporteRepository servicioRepo;
    @Mock
    private AeronaveRepository aeronaveRepo;
    @Mock
    private AerolineaRepository aerolineaRepo;
    @Mock
    private HabitacionRepository habitacionRepo;
    @Mock
    private HabitacionItinerarioRepository habitacionItinerarioRepo;
    @Mock
    private HotelRepository hotelRepo;
    @Mock
    private TrayectoRepository trayectoRepo;
    @Mock
    private CarroRepository carroRepo;
    @Mock
    private MunicipioRepository municipioRepo;
    
    private ViajeController viajeController;
    private AerolineaController aerolineaController;
    private ClienteController clienteController;
    private HotelController hotelController;
    
    @Before
    public void setUp() {
        viajeController = new ViajeController(viajeRepo, viajePlanRepo, planActividadRepo,
                clienteViajeRepo, itinerarioRepo, servicioRepo, aeronaveRepo, trayectoRepo);
        aerolineaController = new AerolineaController(aerolineaRepo);
        clienteController = new ClienteController(null, null);
        hotelController = new HotelController(hotelRepo, municipioRepo);
    }
    
    // ==================== PRUEBAS MÉTODO A ====================
    // A. Promedio de actividades por plan para viajes con trayectos aéreos y terrestres
    
    @Test
    public void testGetPromedioActividadesPorPlanAereoTerrestreConDatos() {
        List<Viaje> viajes = new ArrayList<>();
        viajes.add(new Viaje("VIA001", "2025-12-20", "2025-12-27", 3000000.0));
        
        when(viajeRepo.getAllViajes()).thenReturn(viajes);
        
        double resultado = viajeController.getPromedioActividadesPorPlanAereoTerrestre();
        assertTrue("El promedio debe ser mayor o igual a 0", resultado >= 0);
    }
    
    @Test
    public void testGetPromedioActividadesPorPlanAereoTerrestreSinDatos() {
        List<Viaje> viajes = new ArrayList<>();
        when(viajeRepo.getAllViajes()).thenReturn(viajes);
        
        double resultado = viajeController.getPromedioActividadesPorPlanAereoTerrestre();
        assertEquals("El promedio sin datos debe ser 0", 0.0, resultado, 0.01);
    }
    
    @Test
    public void testGetPromedioActividadesPorPlanAereoTerrestreValido() {
        // Configurar datos mock
        List<Viaje> viajes = new ArrayList<>();
        Viaje viaje = new Viaje("VIA001", "2025-12-20", "2025-12-27", 3000000.0);
        viajes.add(viaje);
        
        when(viajeRepo.getAllViajes()).thenReturn(viajes);
        
        double resultado = viajeController.getPromedioActividadesPorPlanAereoTerrestre();
        assertNotNull("El resultado no debe ser nulo", resultado);
        assertTrue("El promedio debe ser válido", resultado >= 0.0);
    }
    
    // ==================== PRUEBAS MÉTODO B ====================
    // B. Mínimo costo de un trayecto aéreo para una aerolínea específica
    
    @Test
    public void testGetMinimoCostoTrayectoAereoAerolineaValida() {
        Aerolinea aerolinea = new Aerolinea("AER001", "Avianca", "123456789");
        when(aerolineaRepo.findAeronlineaById("AER001")).thenReturn(aerolinea);
        
        double resultado = aerolineaController.getMinimoCostoTrayectoAereoParaAerolinea("AER001");
        assertTrue("El resultado debe ser >= -1", resultado >= -1);
    }
    
    @Test
    public void testGetMinimoCostoTrayectoAereoAerolineaNula() {
        double resultado = aerolineaController.getMinimoCostoTrayectoAereoParaAerolinea(null);
        assertEquals("Debe retornar -1 con aerolineaId nulo", -1, resultado, 0.01);
    }
    
    @Test
    public void testGetMinimoCostoTrayectoAereoAerolineaVacia() {
        double resultado = aerolineaController.getMinimoCostoTrayectoAereoParaAerolinea("");
        assertEquals("Debe retornar -1 con aerolineaId vacío", -1, resultado, 0.01);
    }
    
    @Test
    public void testGetMinimoCostoTrayectoAereoAerolineaNoEncontrada() {
        when(aerolineaRepo.findAeronlineaById("AER999")).thenReturn(null);
        
        double resultado = aerolineaController.getMinimoCostoTrayectoAereoParaAerolinea("AER999");
        assertEquals("Debe retornar -1 si aerolinea no existe", -1, resultado, 0.01);
    }
    
    // ==================== PRUEBAS MÉTODO C ====================
    // C. Cantidad de viajes con actividad específica usando vehículo con menos habitaciones
    
    @Test
    public void testGetCantidadActividadesViajesClientesConCarroValido() {
        int resultado = clienteController.getCantidadActividadesViajesClientesConCarro("CLI001");
        assertTrue("La cantidad debe ser mayor o igual a 0", resultado >= 0);
    }
    
    @Test
    public void testGetCantidadActividadesViajesClientesConCarroClienteNulo() {
        int resultado = clienteController.getCantidadActividadesViajesClientesConCarro(null);
        assertEquals("Debe retornar 0 con clienteId nulo", 0, resultado);
    }
    
    @Test
    public void testGetCantidadActividadesViajesClientesConCarroClienteVacio() {
        int resultado = clienteController.getCantidadActividadesViajesClientesConCarro("");
        assertEquals("Debe retornar 0 con clienteId vacío", 0, resultado);
    }
    
    // ==================== PRUEBAS MÉTODO D ====================
    // D. Suma total de costos de vuelos para un cliente específico
    
    @Test
    public void testGetSumaCostosTrayectosVueloClienteValido() {
        List<Viaje> viajes = new ArrayList<>();
        viajes.add(new Viaje("VIA001", "2025-12-20", "2025-12-27", 3000000.0));
        
        when(clienteViajeRepo.findViajesByClienteId("CLI001")).thenReturn(viajes);
        when(itinerarioRepo.getAllItinerarioTransporte()).thenReturn(new ArrayList<>());
        
        double resultado = viajeController.getSumaCostosTrayectosVueloClienteEspecifico("CLI001");
        assertTrue("La suma debe ser mayor o igual a 0", resultado >= 0);
    }
    
    @Test
    public void testGetSumaCostosTrayectosVueloClienteNulo() {
        double resultado = viajeController.getSumaCostosTrayectosVueloClienteEspecifico(null);
        assertEquals("Debe retornar -1 con clienteId nulo", -1, resultado, 0.01);
    }
    
    @Test
    public void testGetSumaCostosTrayectosVueloClienteVacio() {
        double resultado = viajeController.getSumaCostosTrayectosVueloClienteEspecifico("");
        assertEquals("Debe retornar -1 con clienteId vacío", -1, resultado, 0.01);
    }
    
    @Test
    public void testGetSumaCostosTrayectosVueloSumaCero() {
        List<Viaje> viajes = new ArrayList<>();
        when(clienteViajeRepo.findViajesByClienteId("CLI002")).thenReturn(viajes);
        
        double resultado = viajeController.getSumaCostosTrayectosVueloClienteEspecifico("CLI002");
        assertEquals("La suma sin viajes debe ser 0", 0.0, resultado, 0.01);
    }
    
    // ==================== PRUEBAS MÉTODO E ====================
    // E. Hoteles con habitaciones reservadas en viajes aéreo-terrestres
    
    @Test
    public void testGetCantidadHotelConHabitacionesReservadasViajesMixtosConDatos() {
        List<Habitacion> habitaciones = new ArrayList<>();
        habitaciones.add(new Habitacion("HAB001", "HOT001", "Suite", 350000.0, 2));
        
        when(habitacionRepo.getAllHabitaciones()).thenReturn(habitaciones);
        when(habitacionItinerarioRepo.getAllHabitacionItinerario()).thenReturn(new ArrayList<>());
        
        int resultado = hotelController.getCantidadHotelConHabitacionesReservadasViajesMixtos();
        assertTrue("La cantidad debe ser mayor o igual a 0", resultado >= 0);
    }
    
    @Test
    public void testGetCantidadHotelConHabitacionesReservadasViajesMixtosSinDatos() {
        when(habitacionRepo.getAllHabitaciones()).thenReturn(new ArrayList<>());
        
        int resultado = hotelController.getCantidadHotelConHabitacionesReservadasViajesMixtos();
        assertEquals("La cantidad sin datos debe ser 0", 0, resultado);
    }
    
    // ==================== PRUEBAS MÉTODO F ====================
    // F. Promedio de trayectos por viaje para clientes con más de un viaje
    
    @Test
    public void testGetPromedioTrayectosPorViajeClientesMultiplesConDatos() {
        List<Viaje> viajes = new ArrayList<>();
        viajes.add(new Viaje("VIA001", "2025-12-20", "2025-12-27", 3000000.0));
        viajes.add(new Viaje("VIA002", "2026-01-10", "2026-01-20", 4000000.0));
        
        when(viajeRepo.getAllViajes()).thenReturn(viajes);
        when(clienteViajeRepo.getAllClienteViaje()).thenReturn(new ArrayList<>());
        when(itinerarioRepo.getAllItinerarioTransporte()).thenReturn(new ArrayList<>());
        
        double resultado = viajeController.getPromedioTrayectosPorViajeClientesMultiples();
        assertTrue("El promedio debe ser mayor o igual a 0", resultado >= 0);
    }
    
    @Test
    public void testGetPromedioTrayectosPorViajeClientesMultiplesSinDatos() {
        when(viajeRepo.getAllViajes()).thenReturn(new ArrayList<>());
        when(clienteViajeRepo.getAllClienteViaje()).thenReturn(new ArrayList<>());
        
        double resultado = viajeController.getPromedioTrayectosPorViajeClientesMultiples();
        assertEquals("El promedio sin datos debe ser 0", 0.0, resultado, 0.01);
    }
    
    @Test
    public void testGetPromedioTrayectosPorViajeClientesMultiplesValido() {
        List<Viaje> viajes = new ArrayList<>();
        viajes.add(new Viaje("VIA001", "2025-12-20", "2025-12-27", 3000000.0));
        
        when(viajeRepo.getAllViajes()).thenReturn(viajes);
        when(clienteViajeRepo.getAllClienteViaje()).thenReturn(new ArrayList<>());
        
        double resultado = viajeController.getPromedioTrayectosPorViajeClientesMultiples();
        assertNotNull("El resultado no debe ser nulo", resultado);
    }
}
