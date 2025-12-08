# Documentación de Métodos Analytics Controller

## Descripción General
El `AnalyticsController` es una clase de controlador centralizada que proporciona consultas analíticas complejas sobre los datos de la agencia de viajes. Implementa patrones de inyección de dependencias con dos constructores (default y parametrizado para testing).

---

## Métodos Implementados

### A. `getPromedioActividadesPorPlanConTrayectosAereosYTerrestres()`
**Descripción:** Calcula el promedio de actividades por plan para viajes que incluyen al menos un trayecto aéreo y uno terrestre.

**Retorno:** `double` - El promedio de actividades, o 0.0 si no hay viajes válidos

**Lógica:**
1. Filtra viajes que tengan ambos tipos de trayectos (aéreo y terrestre)
2. Para cada viaje válido, obtiene sus planes asociados
3. Suma todas las actividades de todos los planes
4. Calcula el promedio: totalActividades / totalPlanes

**Caso de uso:** Análisis de complejidad de planes en viajes combinados

---

### B. `getMinimosCostoTrayectoAereoParaAerolinea(String aerolineaId)`
**Descripción:** Retorna el costo mínimo de un trayecto aéreo (vuelo) para una aerolínea específica.

**Parámetros:**
- `aerolineaId`: ID de la aerolínea a consultar

**Retorno:** `double` - El costo mínimo encontrado, o -1 si no hay resultados

**Lógica:**
1. Obtiene todas las aeronaves de la aerolínea especificada
2. Para cada aeronave, obtiene sus servicios de transporte asociados
3. Identifica el costo mínimo entre todos los servicios
4. Retorna el mínimo o -1 si no hay datos

**Caso de uso:** Consulta de precios más bajos por aerolínea

---

### C. `getCantidadViajesConActividadYVehiculoHotelMenosHabitaciones(String nombreActividad)`
**Descripción:** Cuenta viajes que incluyen un plan con una actividad específica Y que hayan utilizado vehículos del hotel con menos habitaciones.

**Parámetros:**
- `nombreActividad`: Nombre de la actividad a buscar

**Retorno:** `int` - Cantidad de viajes que cumplen ambas condiciones

**Lógica:**
1. Identifica el hotel con menos habitaciones
2. Obtiene todos los carros del hotel
3. Para cada viaje, verifica si tiene la actividad especificada
4. Verifica si ese viaje utilizó algún carro del hotel
5. Incrementa el contador si ambas condiciones se cumplen

**Caso de uso:** Análisis de utilización de vehículos hoteleros en viajes específicos

---

### D. `getSumaCostosTrayectosVueloClienteEspecifico(String clienteId)`
**Descripción:** Suma total de costos de todos los trayectos de tipo vuelo (aéreos) para un cliente específico.

**Parámetros:**
- `clienteId`: ID del cliente

**Retorno:** `double` - Suma total de costos de vuelos, o -1 si cliente no existe

**Lógica:**
1. Verifica que el cliente exista
2. Obtiene todos los viajes del cliente
3. Para cada viaje, busca sus itinerarios de transporte
4. Identifica servicios con aeronaves (vuelos)
5. Suma los costos de esos servicios

**Caso de uso:** Facturación y análisis de costos de transporte aéreo por cliente

---

### E. `getHotelesConHabitacionesReservadasEnViajesConPlanesConAlMenos3Actividades()`
**Descripción:** Retorna una lista de todos los hoteles que tienen habitaciones reservadas en viajes que incluyen un plan con al menos 3 actividades.

**Retorno:** `List<Hotel>` - Lista de hoteles que cumplen la condición

**Lógica:**
1. Para cada viaje, obtiene sus planes asociados
2. Para cada plan, cuenta sus actividades
3. Si el plan tiene ≥ 3 actividades, obtiene las habitaciones reservadas en ese viaje
4. Extrae los hoteles de esas habitaciones
5. Retorna la lista sin duplicados

**Caso de uso:** Identificar hoteles con mayor demanda en viajes con planes complejos

---

### F. `getPromedioTrayectosPorViajeClientesConMasDeUnViaje()`
**Descripción:** Calcula el promedio de trayectos por viaje para clientes que han realizado más de un viaje.

**Retorno:** `double` - Promedio de trayectos, o 0.0 si no hay clientes con múltiples viajes

**Lógica:**
1. Agrupa todos los viajes por cliente
2. Filtra solo los clientes con más de un viaje
3. Para cada viaje de estos clientes, cuenta los itinerarios de transporte
4. Calcula el promedio: totalTrayectos / totalViajes

**Caso de uso:** Análisis de patrones de viaje de clientes frecuentes

---

### G. `getMaximoActividadesPlanViajesConTrayectoTerrestre()`
**Descripción:** Retorna el máximo número de actividades en un plan para viajes que tienen al menos un trayecto terrestre.

**Retorno:** `int` - El número máximo de actividades encontrado

**Lógica:**
1. Filtra viajes que tengan al menos un trayecto terrestre
2. Para cada viaje válido, obtiene sus planes
3. Para cada plan, cuenta sus actividades
4. Retorna el máximo encontrado

**Caso de uso:** Análisis de intensidad de actividades en viajes terrestres

---

### H. `getConteoClientesAerolineaMunicipio(String aerolineaId, String municipioId)`
**Descripción:** Cuenta clientes que han utilizado una aerolínea específica Y han realizado al menos una actividad en un municipio específico.

**Parámetros:**
- `aerolineaId`: ID de la aerolínea
- `municipioId`: ID del municipio

**Retorno:** `int` - Cantidad de clientes que cumplen ambas condiciones

**Lógica:**
1. Para cada cliente-viaje, obtiene el viaje y cliente
2. Verifica si el viaje utilizó la aerolínea especificada
3. Verifica si el viaje incluyó actividades en el municipio especificado
4. Cuenta clientes únicos que cumplen ambas condiciones

**Caso de uso:** Análisis de cobertura de mercado: aerolínea + destino

---

### I. `getSumaCostosServiciosTransporteViaje(String viajeId)`
**Descripción:** Suma total de costos de todos los servicios de transporte (trayectos) para un viaje específico.

**Parámetros:**
- `viajeId`: ID del viaje

**Retorno:** `double` - Suma total de costos de transporte, o -1 si viaje no existe

**Lógica:**
1. Verifica que el viaje exista
2. Obtiene todos los itinerarios de transporte del viaje
3. Para cada itinerario, obtiene el servicio de transporte asociado
4. Suma todos los costos de los servicios

**Caso de uso:** Cálculo de costos totales de transporte en un viaje

---

### J. `getPlanesConActividadEspecificaClientesMultiplesViajes(String nombreActividad)`
**Descripción:** Retorna una lista de planes que incluyen una actividad específica Y que han sido contratados por clientes con más de un viaje.

**Parámetros:**
- `nombreActividad`: Nombre de la actividad a buscar

**Retorno:** `List<Plan>` - Lista de planes que cumplen ambas condiciones

**Lógica:**
1. Filtra clientes que han realizado más de un viaje
2. Para cada viaje de estos clientes, obtiene sus planes
3. Para cada plan, verifica si contiene la actividad especificada
4. Retorna la lista de planes únicos que cumplen

**Caso de uso:** Identificar planes populares entre clientes frecuentes

---

### K. `getPromedioHabitacionesReservadasPorHotelTrayectosAereosYTerrestres()`
**Descripción:** Calcula el promedio de habitaciones reservadas por hotel en viajes que incluyen al menos un trayecto aéreo y un trayecto terrestre.

**Retorno:** `double` - Promedio de habitaciones por hotel, o 0.0 si no hay datos

**Lógica:**
1. Filtra viajes que tengan ambos tipos de trayectos
2. Para cada viaje válido, obtiene sus itinerarios y habitaciones reservadas
3. Agrupa las habitaciones por hotel y cuenta
4. Calcula el promedio: totalHabitaciones / totalHoteles

**Caso de uso:** Análisis de ocupación hotelera en viajes combinados

---

## Métodos Auxiliares

### `tieneViajesTrayectosAereosYTerrestres(String viajeId)`
**Descripción:** Verifica si un viaje tiene al menos un trayecto aéreo (vuelo) y uno terrestre (carro).

**Lógica:**
1. Obtiene todos los itinerarios del viaje
2. Para cada itinerario, identifica el tipo de vehículo
3. Retorna true si hay ambos tipos, false en caso contrario

---

### `tieneViajesTrayectosTerrestre(String viajeId)`
**Descripción:** Verifica si un viaje tiene al menos un trayecto terrestre (utiliza carro, no aeronave).

**Lógica:**
1. Obtiene todos los itinerarios del viaje
2. Para cada itinerario, verifica si el vehículo es una aeronave
3. Retorna true si encuentra al menos un vehículo que NO es aeronave

---

## Inyección de Dependencias

### Constructor por Defecto
```java
new AnalyticsController()
```
Crea todas las instancias de repositorios internamente.

### Constructor Parametrizado
```java
new AnalyticsController(viajeData, clienteData, planData, ...)
```
Acepta todas las dependencias para facilitar testing y mockeo.

---

## Notas de Implementación

1. **Manejo de null:** Todos los métodos validan parámetros de entrada y retornan valores por defecto (-1 o 0) si hay datos inválidos.

2. **Eficiencia:** Los métodos utilizan streams de Java para operaciones de filtrado y mapeo donde es apropiado.

3. **Reutilización:** Los métodos auxiliares (`tieneViajesTrayectosAereosYTerrestres`, etc.) son utilizados por múltiples métodos de análisis.

4. **Completitud:** Todos los 11 métodos solicitados están implementados y compilables.

---

## Ejemplo de Uso

```java
AnalyticsController analytics = new AnalyticsController();

// Obtener promedio de actividades en viajes aéreo-terrestres
double promedio = analytics.getPromedioActividadesPorPlanConTrayectosAereosYTerrestres();

// Obtener mínimo costo para una aerolínea
double minCosto = analytics.getMinimosCostoTrayectoAereoParaAerolinea("AEROLINEA_001");

// Contar clientes con múltiples viajes
double promTrayectos = analytics.getPromedioTrayectosPorViajeClientesConMasDeUnViaje();
```
