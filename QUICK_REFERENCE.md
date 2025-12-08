# Quick Reference - AnalyticsController

## Resumen Ejecutivo

Se ha implementado un `AnalyticsController` con **11 métodos de análisis complejos** que integran múltiples repositorios para proporcionar insights sobre:
- **Pricing & Costos**
- **Cliente & Demanda**  
- **Ocupación & Recursos**
- **Patrones de Viaje**

---

## Ubicación del Archivo
```
src/Controllers/AnalyticsController.java
```

---

## Inicialización Rápida

```java
// Opción 1: Simple
AnalyticsController analytics = new AnalyticsController();

// Opción 2: Con inyección de dependencias (testing)
AnalyticsController analytics = new AnalyticsController(
    viajeData, clienteData, planData, viajePlanData, planActividadData,
    clienteViajeData, itinerarioData, servicioData, hotelData,
    habitacionData, habitacionItinerarioData, carroData, aeronaveData
);
```

---

## Métodos - Referencia Rápida

### Análisis de Actividades
| Método | Retorno | Descripción |
|--------|---------|-------------|
| `getPromedioActividadesPorPlanConTrayectosAereosYTerrestres()` | `double` | Promedio actividades en viajes mixtos |
| `getMaximoActividadesPlanViajesConTrayectoTerrestre()` | `int` | Max actividades en viajes terrestres |

### Análisis de Pricing
| Método | Parámetro | Retorno | Descripción |
|--------|-----------|---------|-------------|
| `getMinimosCostoTrayectoAereoParaAerolinea(aerolineaId)` | String | `double` | Tarifa mínima por aerolínea |
| `getSumaCostosServiciosTransporteViaje(viajeId)` | String | `double` | Costo total transporte en viaje |
| `getSumaCostosTrayectosVueloClienteEspecifico(clienteId)` | String | `double` | Costo vuelos por cliente |

### Análisis de Clientes
| Método | Retorno | Descripción |
|--------|---------|-------------|
| `getPromedioTrayectosPorViajeClientesConMasDeUnViaje()` | `double` | Promedio trayectos clientes frecuentes |
| `getPlanesConActividadEspecificaClientesMultiplesViajes(actividad)` | `List<Plan>` | Planes populares clientes frecuentes |
| `getConteoClientesAerolineaMunicipio(aerolineaId, municipioId)` | `int` | Clientes: aerolínea + municipio |

### Análisis de Ocupación
| Método | Retorno | Descripción |
|--------|---------|-------------|
| `getHotelesConHabitacionesReservadasEnViajesConPlanesConAlMenos3Actividades()` | `List<Hotel>` | Hoteles con planes complejos |
| `getPromedioHabitacionesReservadasPorHotelTrayectosAereosYTerrestres()` | `double` | Ocupación promedio viajes mixtos |

### Análisis de Operaciones
| Método | Parámetro | Retorno | Descripción |
|--------|-----------|---------|-------------|
| `getCantidadViajesConActividadYVehiculoHotelMenosHabitaciones(actividad)` | String | `int` | Viajes con actividad + carros hotel pequeño |

---

## Ejemplos Rápidos

### Ejemplo 1: Precio Mínimo por Aerolínea
```java
double minPrice = analytics.getMinimosCostoTrayectoAereoParaAerolinea("LATAM");
System.out.println("Precio mínimo: $" + minPrice);
```

### Ejemplo 2: Costo Total de Transporte en Viaje
```java
double cost = analytics.getSumaCostosServiciosTransporteViaje("VIAJE_001");
System.out.println("Costo transporte: $" + cost);
```

### Ejemplo 3: Hoteles Activos
```java
List<Hotel> hoteles = analytics.getHotelesConHabitacionesReservadasEnViajesConPlanesConAlMenos3Actividades();
System.out.println("Hoteles activos: " + hoteles.size());
```

### Ejemplo 4: Clientes Frecuentes + Actividad
```java
List<Plan> planes = analytics.getPlanesConActividadEspecificaClientesMultiplesViajes("Buceo");
System.out.println("Planes con Buceo (clientes frecuentes): " + planes.size());
```

### Ejemplo 5: Análisis Combinado
```java
double promedioActividades = analytics.getPromedioActividadesPorPlanConTrayectosAereosYTerrestres();
double promedioHabitaciones = analytics.getPromedioHabitacionesReservadasPorHotelTrayectosAereosYTerrestres();
int maxActividades = analytics.getMaximoActividadesPlanViajesConTrayectoTerrestre();

System.out.println("Viajes combinados:");
System.out.println("  Actividades promedio: " + promedioActividades);
System.out.println("  Habitaciones promedio: " + promedioHabitaciones);
System.out.println("  Max actividades (terrestres): " + maxActividades);
```

---

## Retorno de Valores

| Tipo | Valor por Defecto (Error) | Significado |
|------|---------------------------|-------------|
| `double` | `-1` | Error o parámetro inválido |
| `double` | `0.0` | Sin datos (pero operación exitosa) |
| `int` | `0` | Sin resultados |
| `List<T>` | Lista vacía | Sin resultados |

---

## Validación de Parámetros

**Todos los métodos validan:**
- ✓ Parámetros nulos
- ✓ Strings vacíos
- ✓ Existencia de entidades en base de datos
- ✓ Retornan valores seguros en caso de error

---

## Estado de Compilación
```
✅ AnalyticsController.java: Compila sin errores
✅ 22 Controllers CRUD existentes: Funcionan sin cambios
✅ No hay dependencias rotas
```

---

## Documentación Completa

Para detalles completos, ver:
- `ANALYTICS_CONTROLLER_DOCUMENTATION.md` - Documentación detallada
- `EJEMPLOS_ANALYTICS_USAGE.java` - Ejemplos de código
- `RESUMEN_ANALYTICS.md` - Resumen técnico completo

---

## Integración con Sistema Existente

El `AnalyticsController` se suma a la arquitectura existente:
- **22 Controllers CRUD** - Operaciones básicas de datos
- **1 AnalyticsController** - Consultas analíticas complejas
- **13 Repositorios** - Acceso a datos persistentes
- **21 Modelos** - Entidades del dominio

---

## Casos de Uso Principales

| Caso | Métodos |
|------|---------|
| **Reportería Ejecutiva** | A, F, K |
| **Análisis de Precios** | B, D, I |
| **Marketing & Segmentación** | H, J |
| **Operaciones** | C, E, G |

---

## Performance Note

Para bases de datos con miles de registros:
- Métodos leen desde repositorios en memoria (JsonRepository)
- Operaciones de O(n) a O(n²) dependiendo del método
- Se recomienda cachear resultados para reportes frecuentes
- Considerar ejecutar análisis en threads separados

---

## Soporte & Mantenimiento

**Método Auxiliares (Privados):**
- `tieneViajesTrayectosAereosYTerrestres(String)` - Validación viajes mixtos
- `tieneViajesTrayectosTerrestre(String)` - Validación viajes terrestres

Estos métodos son reutilizados por múltiples métodos de análisis para evitar duplicación.

---

## Fecha de Implementación
**8 de Diciembre de 2024**

---

**¿Necesitas más ayuda?** Ver archivos de documentación incluidos en el proyecto.
