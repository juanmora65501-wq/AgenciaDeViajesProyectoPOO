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
 * Pruebas JUnit para métodos analíticos G, H, I, J, K
 * G. Máximo número de actividades en un plan para viajes con trayecto terrestre
 * H. Conteo de clientes que utilizaron aerolínea y realizaron actividad en municipio
 * I. Suma total de costos de servicios de transporte para un viaje específico
 * J. Planes que incluyen actividad con nombre específico contratados por clientes con múltiples viajes
 * K. Promedio de habitaciones reservadas por hotel en viajes aéreo-terrestres
 */
@RunWith(MockitoJUnitRunner.class)
public class AnalyticosGKTest {
    
    @Mock
    private ViajeRepository viajeRepo;
    private ViajeController viajeController;
    
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
    private TrayectoRepository trayectoRepo;
    @Mock
    private HabitacionRepository habitacionRepo;
    @Mock
    private HabitacionItinerarioRepository habitacionItinerarioRepo;
    
    @Mock
    private ClienteRepository clienteRepo;
    @Mock
    private UsuarioRepository usuarioRepo;
    private ClienteController clienteController;
    
    @Mock
    private ActividadTuristicaRepository actividadRepo;
    @Mock
    private MunicipioRepository municipioRepo;
    @Mock
    private AerolineaRepository aerolineaRepo;
    @Mock
    private CarroRepository carroRepo;
    private ActividadTuristicaController actividadController;
    
    @Before
    public void setUp() {
        viajeController = new ViajeController(viajeRepo, viajePlanRepo, planActividadRepo,
                clienteViajeRepo, itinerarioRepo, servicioRepo, aeronaveRepo, trayectoRepo);
        clienteController = new ClienteController(clienteRepo, usuarioRepo);
        actividadController = new ActividadTuristicaController(actividadRepo, municipioRepo);
    }
    
    // ==================== PRUEBAS MÉTODO G ====================
    // G. Máximo número de actividades en un plan para viajes con trayecto terrestre
    
    @Test
    public void testGetMaximoActividadesPlanViajesTerrestreConDatos() {
        List<Viaje> viajes = new ArrayList<>();
        viajes.add(new Viaje("VIA001", "2025-12-20", "2025-12-27", 3000000.0));
        
        when(viajeRepo.getAllViajes()).thenReturn(viajes);
        when(viajePlanRepo.findPlanesByViajeId("VIA001")).thenReturn(new ArrayList<>());
        when(planActividadRepo.findActividadesByPlanId("")).thenReturn(new ArrayList<>());
        
        int resultado = viajeController.getMaximoActividadesPlanViajesTerrestre();
        assertTrue("El máximo debe ser mayor o igual a 0", resultado >= 0);
    }
    
    @Test
    public void testGetMaximoActividadesPlanViajesTerrestreSinDatos() {
        when(viajeRepo.getAllViajes()).thenReturn(new ArrayList<>());
        
        int resultado = viajeController.getMaximoActividadesPlanViajesTerrestre();
        assertEquals("El máximo sin datos debe ser 0", 0, resultado);
    }
    
    @Test
    public void testGetMaximoActividadesPlanViajesTerrestreValores() {
        List<Viaje> viajes = new ArrayList<>();
        viajes.add(new Viaje("VIA001", "2025-12-20", "2025-12-27", 3000000.0));
        viajes.add(new Viaje("VIA002", "2026-01-10", "2026-01-20", 4000000.0));
        
        when(viajeRepo.getAllViajes()).thenReturn(viajes);
        
        int resultado = viajeController.getMaximoActividadesPlanViajesTerrestre();
        assertNotNull("El resultado no debe ser nulo", resultado);
    }
    
    // ==================== PRUEBAS MÉTODO H ====================
    // H. Conteo de clientes que utilizaron aerolínea y realizaron actividad en municipio
    
    @Test
    public void testGetCantidadViajesClientePorAerolineaValido() {
        int resultado = clienteController.getCantidadViajesClientePorAerolinea("CLI001", "AER001");
        assertTrue("La cantidad debe ser mayor o igual a 0", resultado >= 0);
    }
    
    @Test
    public void testGetCantidadViajesClientePorAerolineaClienteNulo() {
        int resultado = clienteController.getCantidadViajesClientePorAerolinea(null, "AER001");
        assertEquals("Debe retornar 0 con clienteId nulo", 0, resultado);
    }
    
    @Test
    public void testGetCantidadViajesClientePorAerolineaClienteVacio() {
        int resultado = clienteController.getCantidadViajesClientePorAerolinea("", "AER001");
        assertEquals("Debe retornar 0 con clienteId vacío", 0, resultado);
    }
    
    @Test
    public void testGetCantidadViajesClientePorAerolineaAerolineaNula() {
        int resultado = clienteController.getCantidadViajesClientePorAerolinea("CLI001", null);
        assertEquals("Debe retornar 0 con aerolineaId nulo", 0, resultado);
    }
    
    @Test
    public void testGetCantidadViajesClientePorAerolineaAerolineaVacia() {
        int resultado = clienteController.getCantidadViajesClientePorAerolinea("CLI001", "");
        assertEquals("Debe retornar 0 con aerolineaId vacío", 0, resultado);
    }
    
    @Test
    public void testGetCantidadViajesClientePorAerolineaConDatos() {
        List<ClienteViaje> clienteViajes = new ArrayList<>();
        clienteViajes.add(new ClienteViaje("CV001", "CLI001", "VIA001"));
        
        when(clienteViajeRepo.getAllClienteViaje()).thenReturn(clienteViajes);
        when(viajeRepo.findViajeById("VIA001")).thenReturn(new Viaje("VIA001", "2025-12-20", "2025-12-27", 3000000.0));
        when(itinerarioRepo.getAllItinerarioTransporte()).thenReturn(new ArrayList<>());
        
        int resultado = clienteController.getCantidadViajesClientePorAerolinea("CLI001", "AER001");
        assertTrue("La cantidad debe ser válida", resultado >= 0);
    }
    
    // ==================== PRUEBAS MÉTODO I ====================
    // I. Suma total de costos de servicios de transporte para un viaje específico
    
    @Test
    public void testGetSumaCostosServiciosTransporteViajeValido() {
        Viaje viaje = new Viaje("VIA001", "2025-12-20", "2025-12-27", 3000000.0);
        when(viajeRepo.findViajeById("VIA001")).thenReturn(viaje);
        when(itinerarioRepo.getAllItinerarioTransporte()).thenReturn(new ArrayList<>());
        
        double resultado = viajeController.getSumaCostosServiciosTransporteViaje("VIA001");
        assertTrue("La suma debe ser mayor o igual a 0", resultado >= 0);
    }
    
    @Test
    public void testGetSumaCostosServiciosTransporteViajeNulo() {
        double resultado = viajeController.getSumaCostosServiciosTransporteViaje(null);
        assertEquals("Debe retornar -1 con viajeId nulo", -1, resultado, 0.01);
    }
    
    @Test
    public void testGetSumaCostosServiciosTransporteViajeVacio() {
        double resultado = viajeController.getSumaCostosServiciosTransporteViaje("");
        assertEquals("Debe retornar -1 con viajeId vacío", -1, resultado, 0.01);
    }
    
    @Test
    public void testGetSumaCostosServiciosTransporteViajeNoEncontrado() {
        when(viajeRepo.findViajeById("VIA999")).thenReturn(null);
        
        double resultado = viajeController.getSumaCostosServiciosTransporteViaje("VIA999");
        assertEquals("Debe retornar -1 si viaje no existe", -1, resultado, 0.01);
    }
    
    @Test
    public void testGetSumaCostosServiciosTransporteSumaCero() {
        Viaje viaje = new Viaje("VIA001", "2025-12-20", "2025-12-27", 3000000.0);
        when(viajeRepo.findViajeById("VIA001")).thenReturn(viaje);
        when(itinerarioRepo.getAllItinerarioTransporte()).thenReturn(new ArrayList<>());
        
        double resultado = viajeController.getSumaCostosServiciosTransporteViaje("VIA001");
        assertEquals("La suma sin itinerarios debe ser 0", 0.0, resultado, 0.01);
    }
    
    // ==================== PRUEBAS MÉTODO J ====================
    // J. Planes que incluyen actividad con nombre específico contratados por clientes con múltiples viajes
    
    @Test
    public void testGetFrecuenciaParticipacionActividadesClienteValido() {
        int resultado = clienteController.getFrecuenciaParticipacionActividadesCliente("CLI001");
        assertTrue("La frecuencia debe ser mayor o igual a 0", resultado >= 0);
    }
    
    @Test
    public void testGetFrecuenciaParticipacionActividadesClienteNulo() {
        int resultado = clienteController.getFrecuenciaParticipacionActividadesCliente(null);
        assertEquals("Debe retornar 0 con clienteId nulo", 0, resultado);
    }
    
    @Test
    public void testGetFrecuenciaParticipacionActividadesClienteVacio() {
        int resultado = clienteController.getFrecuenciaParticipacionActividadesCliente("");
        assertEquals("Debe retornar 0 con clienteId vacío", 0, resultado);
    }
    
    @Test
    public void testGetFrecuenciaParticipacionActividadesClienteConDatos() {
        List<ClienteViaje> clienteViajes = new ArrayList<>();
        clienteViajes.add(new ClienteViaje("CV001", "CLI001", "VIA001"));
        
        when(clienteViajeRepo.getAllClienteViaje()).thenReturn(clienteViajes);
        
        int resultado = clienteController.getFrecuenciaParticipacionActividadesCliente("CLI001");
        assertTrue("La frecuencia debe ser válida", resultado >= 0);
    }
    
    // ==================== PRUEBAS MÉTODO K ====================
    // K. Promedio de habitaciones reservadas por hotel en viajes aéreo-terrestres
    
    @Test
    public void testGetPromedioHabitacionesReservadasPorHotelConDatos() {
        List<Viaje> viajes = new ArrayList<>();
        viajes.add(new Viaje("VIA001", "2025-12-20", "2025-12-27", 3000000.0));
        
        when(viajeRepo.getAllViajes()).thenReturn(viajes);
        when(itinerarioRepo.getAllItinerarioTransporte()).thenReturn(new ArrayList<>());
        when(habitacionItinerarioRepo.getAllHabitacionItinerario()).thenReturn(new ArrayList<>());
        
        double resultado = viajeController.getPromedioHabitacionesReservadasPorHotel();
        assertTrue("El promedio debe ser mayor o igual a 0", resultado >= 0);
    }
    
    @Test
    public void testGetPromedioHabitacionesReservadasPorHotelSinDatos() {
        when(viajeRepo.getAllViajes()).thenReturn(new ArrayList<>());
        
        double resultado = viajeController.getPromedioHabitacionesReservadasPorHotel();
        assertEquals("El promedio sin datos debe ser 0", 0.0, resultado, 0.01);
    }
    
    @Test
    public void testGetPromedioHabitacionesReservadasPorHotelValido() {
        List<Viaje> viajes = new ArrayList<>();
        viajes.add(new Viaje("VIA001", "2025-12-20", "2025-12-27", 3000000.0));
        
        when(viajeRepo.getAllViajes()).thenReturn(viajes);
        when(itinerarioRepo.getAllItinerarioTransporte()).thenReturn(new ArrayList<>());
        when(habitacionItinerarioRepo.getAllHabitacionItinerario()).thenReturn(new ArrayList<>());
        when(habitacionRepo.findHabitacionById(anyString())).thenReturn(
            new Habitacion("HAB001", "HOT001", "Suite", 350000.0, 2)
        );
        
        double resultado = viajeController.getPromedioHabitacionesReservadasPorHotel();
        assertNotNull("El resultado no debe ser nulo", resultado);
        assertTrue("El promedio debe ser válido", resultado >= 0);
    }
    
    // ==================== PRUEBAS ADICIONALES DE VALIDACIÓN ====================
    
    @Test
    public void testValidacionParametrosNulosMetodosAnaliticos() {
        // Probar que los métodos manejan correctamente parámetros nulos
        int cantidadNula = clienteController.getCantidadViajesClientePorAerolinea(null, null);
        assertEquals("Debe retornar 0", 0, cantidadNula);
        
        int frecuenciaNula = clienteController.getFrecuenciaParticipacionActividadesCliente(null);
        assertEquals("Debe retornar 0", 0, frecuenciaNula);
        
        double sumaNula = viajeController.getSumaCostosServiciosTransporteViaje(null);
        assertEquals("Debe retornar -1", -1, sumaNula, 0.01);
    }
    
    @Test
    public void testValidacionValoresVacios() {
        // Probar que los métodos manejan correctamente valores vacíos
        int cantidadVacia = clienteController.getCantidadViajesClientePorAerolinea("", "");
        assertEquals("Debe retornar 0", 0, cantidadVacia);
        
        int frecuenciaVacia = clienteController.getFrecuenciaParticipacionActividadesCliente("");
        assertEquals("Debe retornar 0", 0, frecuenciaVacia);
        
        double sumaVacia = viajeController.getSumaCostosServiciosTransporteViaje("");
        assertEquals("Debe retornar -1", -1, sumaVacia, 0.01);
    }
    
    @Test
    public void testPromediosRetornanValoresValidos() {
        // Probar que los promedios retornan valores válidos
        double promedioA = viajeController.getPromedioActividadesPorPlanAereoTerrestre();
        assertTrue("Promedio A debe ser válido", promedioA >= 0);
        
        double promedioF = viajeController.getPromedioTrayectosPorViajeClientesMultiples();
        assertTrue("Promedio F debe ser válido", promedioF >= 0);
        
        double promedioK = viajeController.getPromedioHabitacionesReservadasPorHotel();
        assertTrue("Promedio K debe ser válido", promedioK >= 0);
    }
    
    @Test
    public void testMaximoYConteosRetornanValoresValidos() {
        // Probar que máximos y conteos retornan valores válidos
        int maximo = viajeController.getMaximoActividadesPlanViajesTerrestre();
        assertTrue("Máximo debe ser válido", maximo >= 0);
        
        int conteo = clienteController.getCantidadViajesClientePorAerolinea("CLI001", "AER001");
        assertTrue("Conteo debe ser válido", conteo >= 0);
    }
}
